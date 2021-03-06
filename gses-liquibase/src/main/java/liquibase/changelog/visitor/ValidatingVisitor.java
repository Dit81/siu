package liquibase.changelog.visitor;

import liquibase.change.Change;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.changelog.RanChangeSet;
import liquibase.database.Database;
import liquibase.exception.*;
import liquibase.precondition.core.DBMSPrecondition;
import liquibase.precondition.core.ErrorPrecondition;
import liquibase.precondition.core.FailedPrecondition;
import liquibase.precondition.core.PreconditionContainer;
import liquibase.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidatingVisitor implements ChangeSetVisitor {

    private List<ChangeSet> invalidMD5Sums = new ArrayList<ChangeSet>();
    private List<FailedPrecondition> failedPreconditions = new ArrayList<FailedPrecondition>();
    private List<ErrorPrecondition> errorPreconditions = new ArrayList<ErrorPrecondition>();
    private Set<ChangeSet> duplicateChangeSets = new HashSet<ChangeSet>();
    private List<SetupException> setupExceptions = new ArrayList<SetupException>();
    private List<Throwable> changeValidationExceptions = new ArrayList<Throwable>();
    private ValidationErrors validationErrors = new ValidationErrors();
    private Warnings warnings = new Warnings();

    private Set<String> seenChangeSets = new HashSet<String>();

    private List<RanChangeSet> ranChangeSets;
    private Database database;
    final Logger logger = Logger.getLogger(getClass().getName());

    public ValidatingVisitor(List<RanChangeSet> ranChangeSets) {
        this.ranChangeSets = ranChangeSets;
    }

    public void validate(Database database, DatabaseChangeLog changeLog) {
        this.database = database;
        PreconditionContainer preconditions = changeLog.getPreconditions();
        try {
            if (preconditions == null) {
                return;
            }
            preconditions.check(database, changeLog, null);
        } catch (PreconditionFailedException e) {
            logger.log(Level.FINE,"Precondition Failed: " + e.getMessage(), e);
            failedPreconditions.addAll(e.getFailedPreconditions());
        } catch (PreconditionErrorException e) {
            logger.log(Level.FINE,"Precondition Error: "+e.getMessage(), e);
            errorPreconditions.addAll(e.getErrorPreconditions());
        } finally {
            try {
                database.rollback();
            } catch (DatabaseException e) {
                logger.log(Level.WARNING,"Error rolling back after precondition check", e);
            }
        }
    }

    public Direction getDirection() {
        return ChangeSetVisitor.Direction.FORWARD;
    }

    public void visit(ChangeSet changeSet, DatabaseChangeLog databaseChangeLog, Database database) {
        for (Change change : changeSet.getChanges()) {
            try {
                change.init();
            } catch (SetupException se) {
                setupExceptions.add(se);
            }

            warnings.addAll(change.warn(database));

            try {
                ValidationErrors foundErrors = change.validate(database);
                if (foundErrors != null && foundErrors.hasErrors()) {
                    if (changeSet.getOnValidationFail().equals(ChangeSet.ValidationFailOption.MARK_RAN)) {
                        logger.info("Skipping changeSet "+changeSet+" due to validation error(s): "+ StringUtils.join(foundErrors.getErrorMessages(), ", "));
                        changeSet.setValidationFailed(true);
                    } else {
                        validationErrors.addAll(foundErrors, changeSet);
                    }
                }
            } catch (Throwable e) {
                changeValidationExceptions.add(e);
            }
        }

        for (RanChangeSet ranChangeSet : ranChangeSets) {
            if (ranChangeSet.getId().equalsIgnoreCase(changeSet.getId())
                    && ranChangeSet.getAuthor().equalsIgnoreCase(changeSet.getAuthor())
                    && ranChangeSet.getChangeLog().equalsIgnoreCase(changeSet.getFilePath())) {
                if (!changeSet.isCheckSumValid(ranChangeSet.getLastCheckSum())) {
                    if (!changeSet.shouldRunOnChange()) {
                        invalidMD5Sums.add(changeSet);
                    }
                }
            }
        }


        String changeSetString = changeSet.toString(false);
        if (seenChangeSets.contains(changeSetString)) {
            duplicateChangeSets.add(changeSet);
        } else {
            seenChangeSets.add(changeSetString);
        }
    }

    public List<ChangeSet> getInvalidMD5Sums() {
        return invalidMD5Sums;
    }


    public List<FailedPrecondition> getFailedPreconditions() {
        return failedPreconditions;
    }

    public List<ErrorPrecondition> getErrorPreconditions() {
        return errorPreconditions;
    }

    public Set<ChangeSet> getDuplicateChangeSets() {
        return duplicateChangeSets;
    }

    public List<SetupException> getSetupExceptions() {
        return setupExceptions;
    }

    public List<Throwable> getChangeValidationExceptions() {
        return changeValidationExceptions;
    }

    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }

    public Warnings getWarnings() {
        return warnings;
    }

    public boolean validationPassed() {
        return invalidMD5Sums.size() == 0
                && failedPreconditions.size() == 0
                && errorPreconditions.size() == 0
                && duplicateChangeSets.size() == 0
                && changeValidationExceptions.size() == 0
                && setupExceptions.size() == 0
                && !validationErrors.hasErrors();
    }

    public Database getDatabase() {
        return database;
    }
}
