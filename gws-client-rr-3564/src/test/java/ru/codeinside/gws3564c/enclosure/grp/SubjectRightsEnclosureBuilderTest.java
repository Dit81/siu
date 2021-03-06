/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2013, MPL CodeInside http://codeinside.ru
 */

package ru.codeinside.gws3564c.enclosure.grp;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.codeinside.gws.api.ExchangeContext;
import ru.codeinside.gws3564c.DummyContext;
import ru.grp.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class SubjectRightsEnclosureBuilderTest {
  private ExchangeContext context;
  private SubjectRightsEnclosureBuilder enclosureBuilder;

  @Before
  public void setUp() throws Exception {
    context = new DummyContext();
    enclosureBuilder = new SubjectRightsEnclosureBuilder(context);
  }

  @Test
  public void testFillTerritory() throws Exception {
    context.setVariable("territory", 2L);
    context.setVariable("territoryCode_1", "23");
    context.setVariable("territoryCode_2", "24");
    final RequestGRP.Request.RequiredData.RequiredDataSubject.ExtractSubjectRegion.Territory territory = enclosureBuilder
        .fillTerritory();
    final List<String> regionCodes = territory.getRegions().getRegion();
    assertEquals(2, regionCodes.size());
    assertEquals("23", regionCodes.get(0));
    assertEquals("24", regionCodes.get(1));
  }

  @Test
  public void testFillPersonOwner() throws Exception {
    fillContext();
    context.setVariable("ownerType", "PERSON");
    RequestGRP request = enclosureBuilder.createRequest( "id");
    List<TOwner> owners = request.getRequest()
                                 .getRequiredData()
                                 .getRequiredDataSubject()
                                 .getExtractSubjectRegion()
                                 .getOwner();
    assertNotNull(owners);
    assertEquals(1, owners.size());
    TOwner tOwner = owners.get(0);
    final TPersonOwner personOwner = tOwner.getPersonO();
    assertEquals("Иванов", personOwner.getFIO().getSurname());
    assertEquals("Иван", personOwner.getFIO().getFirst());
    assertEquals("Иванович", personOwner.getFIO().getPatronymic());
    assertEquals(TestUtils.getDateValue("2012-07-12"), personOwner.getDateBirth().toGregorianCalendar().getTime());
    assertEquals("birthLocation", personOwner.getPlaceBirth());
    assertEquals("112-233-332 33", personOwner.getSNILS());
    assertEquals("contactInfo", personOwner.getContactInfo());
    assertEquals("citizenShip", personOwner.getCitizenship());
    assertEquals("familyStatus", personOwner.getFamilyStatus());
    assertEquals("Phone", personOwner.getPhone());
    assertEquals("E-mail", personOwner.getEMail());
    assertNotNull(personOwner.getLocationTemporary());
    assertNotNull(personOwner.getDocument());
  }

  @Test
  public void testFillGovernanceOwner() throws Exception {
    fillContext();
    context.setVariable("ownerType", "GOVERNANCE");

    context.setVariable("ownerGovernanceName", "Пенсионный фонд");
    context.setVariable("ownerGovernanceCode", "007001001001");
    context.setVariable("ownerGovernanceEmail", "test@test.ru");
    context.setVariable("ownerGovernancePhone", "phone");
    context.setVariable("ownerGovernanceContactInfo", "contactInfo");
    RequestGRP request = enclosureBuilder.createRequest( "id");
    List<TOwner> owners = request.getRequest()
                                 .getRequiredData()
                                 .getRequiredDataSubject()
                                 .getExtractSubjectRegion()
                                 .getOwner();
    assertNotNull(owners);
    assertEquals(1, owners.size());
    TOwner tOwner = owners.get(0);
    final TGovernanceOwner governanceOwner = tOwner.getGovernanceO();
    assertEquals("Пенсионный фонд", governanceOwner.getName() );
    assertEquals("007001001001", governanceOwner.getGovernanceCode() );
    assertEquals("test@test.ru", governanceOwner.getEMail() );
    assertEquals("phone", governanceOwner.getPhone() );
    assertEquals("contactInfo", governanceOwner.getContactInfo() );
  }

  @Test
  public void testFillOrganization() throws Exception {
    fillContext();
    context.setVariable("ownerType", "ORGANISATION");
    context.setVariable("ownerLegalPersonName", "declLegalPersonName");
    context.setVariable("ownerLegalPersonOPF", "declLegalPersonOPF");

    context.setVariable("ownerLegalPersonKPP", "declLegalPersonKPP");
    context.setVariable("ownerLegalPersonINN", "declLegalPersonINN");
    context.setVariable("ownerLegalPersonOGRN", "declLegalPersonOGRN");
    context.setVariable("ownerLegalPersonEmail", "declLegalPersonEmail");
    context.setVariable("ownerLegalPersonPhone", "declLegalPersonPhone");
    context.setVariable("ownerLegalPersonContactInfo", "declLegalPersonContactInfo");
    context.setVariable("ownerLegalPersonRegDate", TestUtils.getDateValue("2001-01-01")); //тип date
    context.setVariable("ownerLegalPersonRegAgency", "declLegalPersonRegAgency");
    context.setVariable("ownerLegalPersonDocumentCode", "declLegalPersonDocumentCode");

    context.setVariable("ownerLegalPersonDocumentName", "declLegalPersonDocumentName");
    context.setVariable("ownerLegalPersonDocumentSeries", "declLegalPersonDocumentSeries");
    context.setVariable("ownerLegalPersonDocumentNumber", "declLegalPersonDocumentNumber");
    context.setVariable("ownerLegalPersonDocumentDate", TestUtils.getDateValue("2001-09-01")); //тип date
    context.setVariable("ownerLegalPersonDocumentIssueOrgan", "declLegalPersonDocumentIssueOrgan");
    context.setVariable("ownerLegalPersonDocumentDesc", "declLegalPersonDocumentDesc");
    RequestGRP request = enclosureBuilder.createRequest( "id");
    List<TOwner> owners = request.getRequest()
                                 .getRequiredData()
                                 .getRequiredDataSubject()
                                 .getExtractSubjectRegion()
                                 .getOwner();
    assertNotNull(owners);
    assertEquals(1, owners.size());
    TOwner tOwner = owners.get(0);
    final TOrganizationOwner organization = tOwner.getOrganizationO();
    assertEquals("declLegalPersonName", organization.getName());
    assertEquals("declLegalPersonOPF", organization.getCodeOPF());
    assertEquals("declLegalPersonKPP", organization.getCodeCPP());
    assertEquals("declLegalPersonINN", organization.getINN());
    assertEquals("declLegalPersonOGRN", organization.getCodeOGRN());
    assertEquals("declLegalPersonEmail", organization.getEMail());
    assertEquals("declLegalPersonPhone", organization.getPhone());
    assertEquals("declLegalPersonContactInfo", organization.getContactInfo());
  }

  private void fillContext() {
    //owner
    context.setVariable("ownerPersonFIOSurname", "Иванов");
    context.setVariable("ownerPersonFIOFirst", "Иван");
    context.setVariable("ownerPersonFIOPatronymic", "Иванович");
    context.setVariable("ownerPersonDateBirth", TestUtils.getDateValue("2012-07-12"));
    context.setVariable("ownerPersonPlaceBirth", "birthLocation");
    context.setVariable("ownerPersonEmail", "E-mail");
    context.setVariable("ownerPersonPhone", "Phone");
    context.setVariable("ownerPersonCitizenShip", "citizenShip");
    context.setVariable("ownerPersonFamilyStatus", "familyStatus");


    context.setVariable("ownerPersonSNILS", "112-233-332 33");
    context.setVariable("ownerPersonContactInfo", "contactInfo");

    context.setVariable("ownerTemporaryLocationOKATO", "ownerTemporaryLocationOKATO"); //тип string
    context.setVariable("ownerTemporaryLocationCLADR", "ownerTemporaryLocationCLADR"); //тип string
    context.setVariable("ownerTemporaryLocationPostalCode", "ownerTemporaryLocationPostalCode"); //тип string
    context.setVariable("ownerTemporaryLocationRegion", "ownerTemporaryLocationRegion"); //тип string
    context.setVariable("ownerTemporaryLocationDistrictName", "ownerTemporaryLocationDistrictName"); //тип string
    context.setVariable("ownerTemporaryLocationDistrictType", "р-н"); //тип enum

    context.setVariable("ownerTemporaryLocationCityName", "ownerTemporaryLocationCityName"); //тип string
    context.setVariable("ownerTemporaryLocationDCity", "г"); //тип string
    context.setVariable("ownerTemporaryLocationUrbanDistictName",
                        "ownerTemporaryLocationUrbanDistictName"); //тип string
    context.setVariable("ownerTemporaryLocationUrbanDistictType", "р-н"); //тип string

    context.setVariable("ownerTemporaryLocationSovietVillageName",
                        "ownerTemporaryLocationSovietVillageName"); //тип string
    context.setVariable("ownerTemporaryLocationSovietVillageType", "волость"); //тип string

    context.setVariable("ownerTemporaryLocationLocalityName", "ownerTemporaryLocationLocalityName"); //тип string
    context.setVariable("ownerTemporaryLocationLocalityType", "аал"); //тип string

    context.setVariable("ownerTemporaryLocationStreetName", "ownerTemporaryLocationStreetName"); //тип string
    context.setVariable("ownerTemporaryLocationDStreets", "аллея"); //тип enum

    context.setVariable("ownerTemporaryLocationLocationLevel1Type", "д"); //тип enum

    context.setVariable("ownerTemporaryLocationLocationLevel1Value",
                        "ownerTemporaryLocationLocationLevel1Value"); //тип string
    context.setVariable("ownerTemporaryLocationLocationLevel2Type", "корп"); //тип enum

    context.setVariable("ownerTemporaryLocationLocationLevel2Value",
                        "ownerTemporaryLocationLocationLevel2Value"); //тип string
    context.setVariable("ownerTemporaryLocationLocationLevel3Type", "блок"); //тип enum

    context.setVariable("ownerTemporaryLocationLocationLevel3Value",
                        "ownerTemporaryLocationLocationLevel3Value"); //тип string
    context.setVariable("ownerTemporaryLocationLocationApartmentType", "кв"); //тип enum

    context.setVariable("ownerTemporaryLocationLocationApartmentValue",
                        "ownerTemporaryLocationLocationApartmentValue"); //тип string
    context.setVariable("ownerTemporaryLocationLocationOther", "ownerTemporaryLocationLocationOther"); //тип string
    context.setVariable("ownerTemporaryLocationLocationNote", "ownerTemporaryLocationLocationNote"); //тип string

    context.setVariable("ownerPDocumentCode", "ownerPDocumentCode"); //тип enum

    context.setVariable("ownerPDocumentSeries", "ownerPDocumentSeries"); //тип string
    context.setVariable("ownerPDocumentNumber", "ownerPDocumentNumber"); //тип string
    context.setVariable("ownerPDocumentDate", TestUtils.getDateValue("2001-11-01")); //тип date
    context.setVariable("ownerPDocumentIssueOrgan", "ownerPDocumentIssueOrgan"); //тип string
    context.setVariable("ownerPDocumentDesc", "ownerPDocumentDesc"); //тип string
    //Territory
    context.setVariable("territoryRegion", "40");     //req Attr


    //declarant
    context.setVariable("declarantType", "GOVERNANCE");
    context.setVariable("declKind", "357007000000");      //Required
    context.setVariable("declGovernanceName", "Пенсионный фонд");
    context.setVariable("declGovernanceCode", "007001001001");
    context.setVariable("declGovernanceEmail", "test@test.ru");

    //AppliedDocument
    context.setVariable("applied", 1l);
    context.setVariable("appliedADocumentCode_1", "558102100000");
    context.setVariable("appliedADocumentName_1",
                        "Запрос о предоставлении сведений, содержащихся в Едином государственном реестре прав на недвижимое имущество и сделок с ним");
    context.setVariable("appliedADocumentNumber_1", "26-0-1-21/4001/2011-166");
    context.setVariable("appliedADocumentDate_1", TestUtils.getDateValue("2012-07-24"));
    context.setVariable("appliedADocumentOriginalQuantity_1", 1L);
    context.setVariable("appliedADocumentOriginalQuantitySheet_1", 1L);
    context.setVariable("appliedADocumentCopyQuantity_1", 1L);
    context.setVariable("appliedADocumentCopyQuantitySheet_1", 1L);
    //ReasonFreeDocument
    context.setVariable("freeACodeDocument", "555005000000");
    context.setVariable("freeADocumentName",
                        "Запрос о предоставлении сведений, содержащихся в Едином государственном реестре прав на недвижимое имущество и сделок с ним");
    context.setVariable("freeADocumentNumber", "");
    context.setVariable("freeADocumentDate", TestUtils.getDateValue("2012-07-24"));
    context.setVariable("freeADocumentOriginalQuantity", "1");
    context.setVariable("freeADocumentOriginalQuantitySheet", "1");
    context.setVariable("isPaymentFree", false);
    context.setVariable("freePayment", 1L);
    context.setVariable("payment", 0L);
    context.setVariable("territory", 0L);
    context.setVariable("smevTest", "Первичный запрос");


  }

  @Test
  public void testRealtyRightsByOwner() throws Exception {
    createContextForGetRealtyRightsByOwner();
    testCreateEnclosure("/enclosure/grp/realty_rights_by_owner.xml", "7b983700-651d-43d8-bdb4-26dd683df535");
  }


  private void createContextForGetRealtyRightsByOwner() throws ParseException {
    context.setVariable("smevTest", "Первичный запрос");
    context.setVariable("okato", "60000000000"); // string
    context.setVariable("oktmo", "01234567"); // string
    context.setVariable("requestType", "558102100000"); // string
    context.setVariable("enclosure_request_type", "SUBJECT_RIGHTS"); // string
    context.setVariable("declarantType", "GOVERNANCE"); // string
    context.setVariable("ownerPersonFIOSurname", "Иванов");
    context.setVariable("ownerPersonFIOFirst", "Иван");
    context.setVariable("ownerPersonFIOPatronymic", "Иваныч");
    context.setVariable("ownerPersonSNILS", "112-233-332 33");
    context.setVariable("ownerPersonDateBirth", TestUtils.getDateValue("2012-07-12"));

    context.setVariable("ownerPersonPDocumentCode", "008001001000"); //тип string
    context.setVariable("ownerPersonPDocumentSeries", "1111"); //тип string
    context.setVariable("ownerPersonPDocumentNumber", "222222"); //тип string
    context.setVariable("ownerPersonPDocumentDate", TestUtils.getDateValue("2001-01-01")); //тип date
    context.setVariable("ownerPersonPDocumentIssueOrgan", "ОВД Калуга, 2321"); //тип string
    context.setVariable("ownerTemporaryLocationRegion", "40"); //тип string
    context.setVariable("ownerTemporaryLocationCityName", "Калуга"); //тип string
    context.setVariable("ownerTemporaryLocationDCity", "г"); //тип string
    context.setVariable("ownerTemporaryLocationStreetName", "Ленина"); //тип string
    context.setVariable("ownerTemporaryLocationDStreets", "уч-к"); //тип enum
    context.setVariable("ownerTemporaryLocationLocationLevel1Type", "д"); //тип enum
    context.setVariable("ownerTemporaryLocationLocationLevel1Value", "32"); //тип string
    context.setVariable("ownerTemporaryLocationLocationLevel2Type", "корп"); //тип enum
    context.setVariable("ownerTemporaryLocationLocationLevel2Value", "2"); //тип string
    context.setVariable("ownerTemporaryLocationLocationLevel3Type", "литера"); //тип enum

    context.setVariable("ownerTemporaryLocationLocationLevel3Value", "а"); //тип string
    context.setVariable("ownerTemporaryLocationLocationApartmentType", "кв"); //тип enum

    context.setVariable("ownerTemporaryLocationLocationApartmentValue", "32"); //тип string
    context.setVariable("ownerTemporaryLocationLocationOther", "Иное"); //тип string
    context.setVariable("ownerTemporaryLocationLocationNote", "Неформализованное описнаие");



    context.setVariable("applied", 1l);
    context.setVariable("territory", 2L);
    context.setVariable("territoryCode_1", "01");
    context.setVariable("territoryCode_2", "02");

    context.setVariable("payment", 0l);
    context.setVariable("appliedADocumentDate_1", TestUtils.getDateValue("2012-07-24")); // string
    context.setVariable("appliedADocumentOriginalQuantity_1", 1L); // string
    context.setVariable("appliedADocumentOriginalQuantitySheet_1", 1L); // string

    context.setVariable("appliedADocumentName_1",
                        "Запрос о предоставлении сведений, содержащихся в Едином государственном реестре прав на недвижимое имущество и сделок с ним");
    context.setVariable("appliedADocumentCode_1", "558102100000"); // string
    context.setVariable("appliedADocumentNumber_1", "26-0-1-21/4001/2011-166"); // string

    context.setVariable("declarantType", "GOVERNANCE");
    context.setVariable("declKind", "357007000000");      //Required
    context.setVariable("declGovernanceName", "Пенсионный фонд");
    context.setVariable("declGovernanceCode", "007001001001");
    context.setVariable("declGovernanceEmail", "test@test");

    context.setVariable("agentFIOSurname", "Антонов");
    context.setVariable("agentFIOFirst", "Антон");
    context.setVariable("agentFIOPatronymic", "Антонович");
    context.setVariable("agentSNILS", "233-234-434 34");
    context.setVariable("agentKind", "356003000000");
    context.setVariable("agentEmail", "test@test.ru");
    context.setVariable("agentPhone", "213-23-12");
    context.setVariable("agentContactInfo", "Адрес для почтовой корреспонденции");

    context.setVariable("agentPDocumentCode", "008001001000"); //тип string
    context.setVariable("agentPDocumentSeries", "2222"); //тип string
    context.setVariable("agentPDocumentNumber", "222222"); //тип string
    context.setVariable("agentPDocumentDate", TestUtils.getDateValue("2010-10-10")); //тип date
    context.setVariable("agentPDocumentIssueOrgan", "УВД, Код подразделен"); //тип string
    context.setVariable("agentTemporaryLocationRegion", "40"); //тип string
    context.setVariable("agentTemporaryLocationPostalCode", "113563"); //тип string
    context.setVariable("agentTemporaryLocationCityName", "Калуга"); //тип string
    context.setVariable("agentTemporaryLocationDCity", "г"); //тип string
    context.setVariable("agentTemporaryLocationStreetName", "Советская"); //тип string
    context.setVariable("agentTemporaryLocationDStreets", "ул"); //тип enum
    context.setVariable("agentTemporaryLocationLocationLevel1Type", "д"); //тип enum
    context.setVariable("agentTemporaryLocationLocationLevel1Value", "10"); //тип string
    context.setVariable("agentTemporaryLocationLocationLevel2Type", "корп"); //тип enum
    context.setVariable("agentTemporaryLocationLocationLevel2Value", "1"); //тип string
    context.setVariable("agentTemporaryLocationLocationLevel3Type", "литера"); //тип enum

    context.setVariable("agentTemporaryLocationLocationLevel3Value", "а"); //тип string
    context.setVariable("agentTemporaryLocationLocationApartmentType", "к"); //тип enum

    context.setVariable("agentTemporaryLocationLocationApartmentValue", "1"); //тип string
    context.setVariable("agentTemporaryLocationLocationOther", "Иное"); //тип string
    context.setVariable("agentTemporaryLocationLocationNote", "Неформализованное описание");


    //ReasonFreeDocument
    context.setVariable("freePaymentADocumentCode_1", "555005000000");
    context.setVariable("freePaymentADocumentName_1",
                        "Документ, подтверждающий право заявителя на безвозмездное получение сведений");
    context.setVariable("freePaymentADocumentNumber_1", "");
    context.setVariable("freePaymentADocumentDate_1", TestUtils.getDateValue("2012-07-24"));
    context.setVariable("freePaymentADocumentOriginalQuantity_1", 1L);
    context.setVariable("freePaymentADocumentOriginalQuantitySheet_1", 1L);
    context.setVariable("isPaymentFree", true);
    context.setVariable("freePayment", 1L);
    context.setVariable("ownerType", "PERSON");
  }

  private void testCreateEnclosure(String fileWithExpectedEnclosure, String guid) throws IOException {
    String xml = enclosureBuilder.createEnclosure(guid);
    InputStream is = this.getClass().getResourceAsStream(fileWithExpectedEnclosure);
    String expectedContent = IOUtils.toString(is, "UTF-8");
    Assert.assertEquals(expectedContent, xml);
  }
}
