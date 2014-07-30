/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2013, MPL CodeInside http://codeinside.ru
 */

package ru.codeinside.gses.webui.form;

import com.google.common.base.Strings;
import com.vaadin.ui.Form;
import commons.Streams;
import eform.Property;
import org.apache.commons.lang.StringUtils;
import ru.codeinside.gses.activiti.FileValue;
import ru.codeinside.gses.activiti.forms.FormID;
import ru.codeinside.gses.activiti.forms.api.definitions.BlockNode;
import ru.codeinside.gses.activiti.forms.api.definitions.PropertyTree;
import ru.codeinside.gses.activiti.forms.api.definitions.VariableType;
import ru.codeinside.gses.activiti.forms.api.values.FormValue;
import ru.codeinside.gses.activiti.forms.api.values.PropertyValue;
import ru.codeinside.gses.activiti.forms.types.DateType;
import ru.codeinside.gses.activiti.forms.values.Block;
import ru.codeinside.gses.service.ActivitiService;
import ru.codeinside.gses.service.ExecutorService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

final public class EFormBuilder implements FormSeq {

  private FormID formId;
  String templateRef;
  eform.Form form;
  EForm eForm;
  FormValue formValue;


  public EFormBuilder(FormValue formValue, FormID formId) {
    this(formValue, formId, false);
  }

  public EFormBuilder(FormValue formValue, FormID formId, boolean archiveMode) {
    templateRef = formValue.getFormDefinition().getFormKey();
    form = createExternalForm(formValue);
    form.archiveMode = archiveMode;
    this.formValue = formValue;
    this.formId = formId;
  }


  @Override
  public String getCaption() {
    return "";
  }

  @Override
  public List<FormField> getFormFields() {
    return eForm.getFormFields();
  }

  @Override
  public Form getForm(FormID formId, FormSeq previous) {
    if (eForm == null) {

      eForm = new EForm(templateRef, form, formValue);
      templateRef = null;
      form = null;
      formValue = null;
    }
    return eForm;
  }

  private eform.Form createExternalForm(final FormValue formValue) {
    final PropertyTree propertyTree = formValue.getFormDefinition();
    final eform.Form form = new eform.Form() {
      @Override
      public Map<String, Property> plusBlock(String login, String name, String suffix, Integer newVal) {
        BlockNode cloneNode = ((BlockNode) propertyTree.getIndex().get(name));
        List<PropertyValue<?>> clones = ActivitiService.INSTANCE.get()
          .withEngine(new Fetcher(login), formId, cloneNode, suffix + "_" + newVal);

        Map<String, Property> map = new LinkedHashMap<String, Property>();
        for (PropertyValue<?> propertyValue : clones) {
          Property property = propertyToTree(propertyValue, suffix + "_" + newVal, eForm.fields);
          if (property != null) {
            map.put(propertyValue.getNode().getId() + suffix + "_" + newVal, property);
          }
        }
        Property cloneProperty = this.getProperty(name + suffix);
        cloneProperty.updateValue(newVal.toString());
        if (cloneProperty.children == null) {
          cloneProperty.children = new ArrayList<Map<String, Property>>();
        }
        cloneProperty.children.add(map);
        return map;
      }

      @Override
      public void minusBlock(String name, String suffix, Integer newVal) {
        Property cloneProperty = this.getProperty(name + suffix);
        cloneProperty.updateValue(newVal.toString());
        if (cloneProperty.children != null) {
          Map<String, Property> map = cloneProperty.children.remove(newVal.intValue());
          for (String key : propertyKeySet(map)) {
            eForm.fields.remove(key);
          }
        }
      }

      @Override
      public List<String> save() {
        List<String> messages = new ArrayList<String>();
        String taskId = formId.taskId;
        if (taskId != null) {
          for (EField eField : eForm.fields.values()) {
            if (eField.property.isModified()) {
              String value = eField.property.value;
              if (eField.node.getVariableType().getJavaType() == FileValue.class) {
                File file = (File) eField.property.content()[0];
                String mime = (String) eField.property.content()[1];
                ExecutorService.INSTANCE.get().saveBytesBuffer(taskId, eField.id, eField.property.value, mime, file);
              } else if (eField.node.getVariableType().getJavaType() == Long.class) {
                try {
                  ExecutorService.INSTANCE.get().saveBuffer(taskId, eField.id, Strings.isNullOrEmpty(value)
                    ? null : Long.parseLong(value));
                } catch (NumberFormatException e) {
                  messages.add(e.getMessage());
                }
              } else if (eField.node.getVariableType().getJavaType() == Date.class) {
                Date parse = null;
                if (!Strings.isNullOrEmpty(value)) {
                  String pattern = StringUtils.trimToNull(eField.node.getPattern()) == null
                    ? DateType.PATTERN1
                    : eField.node.getPattern();
                  try {
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);
                    simpleDateFormat1.setLenient(false);
                    parse = simpleDateFormat1.parse(value);
                  } catch (ParseException e1) {
                    try {
                      SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(DateType.PATTERN2);
                      simpleDateFormat1.setLenient(false);
                      parse = simpleDateFormat1.parse(value);
                    } catch (ParseException e) {
                      messages.add(e.getMessage());
                      continue;
                    }
                  }
                }
                ExecutorService.INSTANCE.get().saveBuffer(taskId, eField.id, parse == null ? null : parse.getTime());
              } else if (eField.node.getVariableType().getJavaType() == Boolean.class) {
                ExecutorService.INSTANCE.get().saveBuffer(taskId, eField.id, Boolean.TRUE.equals(Boolean.parseBoolean(value)) ? 1L : 0L);
              } else {
                ExecutorService.INSTANCE.get().saveBuffer(taskId, eField.id, value);
              }
              eField.property.setSaved();
            }
          }
        }
        return messages;
      }
    };
    for (PropertyValue propertyValue : formValue.getPropertyValues()) {
      Property property = propertyToTree(propertyValue, "", null);
      if (property != null) {
        form.props.put(propertyValue.getNode().getId(), property);
      }
    }
    return form;
  }

  Property propertyToTree(PropertyValue<?> propertyValue,
                          String suffix, Map<String, EField> fields) {

    if (propertyValue == null) {
      return null;
    }
    Property property = createProperty(propertyValue, suffix);
    if (fields != null) {
      fields.put(propertyValue.getId(), new EField(propertyValue.getId(), property, propertyValue.getNode()));
    }
    if (propertyValue instanceof Block) {
      final List<List<PropertyValue<?>>> clones = ((Block) propertyValue).getClones();
      int value;
      try {
        value = Integer.parseInt(property.value);
      } catch (NumberFormatException e) {
        value = 0;
      }
      for (int i = 1; i <= value; i++) {
        Map<String, Property> map = new LinkedHashMap<String, Property>();
        for (PropertyValue<?> childValue : clones.get(i - 1)) {
          Property child = propertyToTree(childValue, suffix + "_" + i, fields);
          if (child != null) {
            map.put(childValue.getNode().getId() + suffix + "_" + i, child);
          }
        }
        if (!map.isEmpty()) {
          if (property.children == null) {
            property.children = new ArrayList<Map<String, Property>>();
          }
          property.children.add(map);
        }
      }
    }
    return property;
  }

  public Property createProperty(PropertyValue<?> propertyValue, String suffix) {
    String prefix;
    if (suffix.isEmpty()) {
      prefix = suffix;
    } else {
      prefix = suffix.substring(1).replace('_', '.') + ") ";
    }
    Property property = new Property();
    property.label = prefix + propertyValue.getNode().getName();
    VariableType type = propertyValue.getNode().getVariableType();
    property.type = type == null ? "string" : type.getName();
    property.required = propertyValue.getNode().isFiledRequired();
    property.writable = propertyValue.getNode().isVarWritable();
    if (propertyValue.getAudit() != null) {
      property.sign = propertyValue.getAudit().isVerified();
      if (propertyValue.getAudit().isVerified()) {
        property.certificate = propertyValue.getAudit().getLogin() + "(" + propertyValue.getAudit().getOrganization() + ")";
      }
    }
    if (!(propertyValue.getValue() instanceof FileValue)) {
      Object value = propertyValue.getValue();
      if (value instanceof byte[]) {
        try {
          property.value = new String((byte[]) value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
          Logger.getAnonymousLogger().info("can't decode model!");
        }
      } else if (value instanceof Date) {
        String pattern = StringUtils.trimToNull(propertyValue.getNode().getPattern()) == null
          ? DateType.PATTERN2
          : propertyValue.getNode().getPattern();
        property.value = new SimpleDateFormat(pattern).format(value);
      } else {
        property.value = value == null ? null : value.toString();
      }
    } else {
      FileValue value = (FileValue) propertyValue.getValue();
      try {
        property.updateContent(value.getFileName(), value.getMimeType(), Streams.copyToTempFile(new ByteArrayInputStream(value.getContent()), "efrom-", ".attachment"), false);
      } catch (IOException e) {
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "can't create tmpFile", e);
      }
    }
    return property;
  }

}
