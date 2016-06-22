package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("ffwzWluiR3FplWu1d8PhMKpdxOZWRSj4DTATLd4lSnf8fYY3X8");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(7);
        timezone.setTimeZoneLabel("VD4hXxlyUVeqQyShF03U1HcmMDOyOmOTurD81n4JVFWQTkffuE");
        timezone.setCountry("WhrKLJ67EHpGlW0piQadnHVurJlaACVUhr8wGexdno7OzbRoh2");
        timezone.setGmtLabel("XD0DcHwVqxpPQNKCeYYCP5UQfBXlpDFmn4ZCscYjrZGWFL3JoA");
        timezone.setCities("BwQfMBuepP9zY7FFSTzp2p5P6gGa8yLSKjaSI8qvsAc3E8dSxw");
        Gender gender = new Gender();
        gender.setGender("9HcxpueZJovCjRnWLyp8QQU3SCOJ6hmuYImySOvi3lHucAgYCp");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguage("wA2xgRIfWiyd9uttWv1koXNiZIXr8OZrVdshRMwAiVYhiJFUDi");
        language.setLanguageIcon("2rFM2afZfjWfnZ9ObtDdbuWh0pCHoxyb63cS0ItKrtOTe4IocJ");
        language.setLanguageDescription("3hzEwiR0m3obbTXTToRjlPCKvSR5gsnWGCNQGj1icMxDCTgl4J");
        language.setAlpha3("3YP");
        language.setAlpha2("WP");
        language.setAlpha4parentid(11);
        language.setLanguageType("dasYbWYtbD0l6RpJ8jofTeH3a3UboY5z");
        language.setAlpha4("cRMr");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setPhoneNumber("OoBd2cHpiK69HrfGC757");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1466571732246l));
        corecontacts.setNativeMiddleName("GooJHUAf7PRq6KqHcgjq4ozCj0Mz2gz0J0ivozaIwLXMlRUNcc");
        corecontacts.setAge(121);
        corecontacts.setMiddleName("JO56ms5qi5NU7xmsNEDpdv0cR6j4oz6hUNbnlTk4lONlknVK7h");
        corecontacts.setFirstName("I5v0nM2LBdkFa3XsHgsE5Bj8iV5jNIx9gCKeX8YvNxiickVcrd");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("uV7r4ZKfyX7h12v3oogTEp8FTSjonGRsooH6P1WXuL0ZkRsFdQ");
        corecontacts.setLastName("usFzCA4OqzbYQlObL3jaqiXz25l73LSvlERE83UcWbpfZcu69N");
        corecontacts.setNativeTitle("w4Rwd1HioG6HwZu37X7TXoaaczmJ6709Mlvo0lQn07lpFjswb9");
        corecontacts.setEmailId("V39UI68BiulgmLvUrwZpveimVtNUHqO3JGj8dHveZXbnyVchGY");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1466571732422l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("c6Tn8ZWPdBwQbqiubvCfqiWtXP04LHJi0GRxFe3lkZHbH1CK0F");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("Urd3yV2eUXrNSe0uMsNMTtzTplmTd3WhtIHQlAFzfWn6a9HRAd");
        communicationtype.setCommTypeName("b6pyeRUbHDNoX1tNcLWSkuiRw6AcLNoRPZdVKSjMPjrjat2qkU");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("uGCzjh5DsWFj9Gm83zxwHwzZeqHvPLsiPvFZchFvu7HVX547Y1");
        communicationgroup.setCommGroupDescription("OdrKt8WP62zihZPlhAVXN2kFbDwcT7fJLhlD2CnfhMSDYDuF1R");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("eVgzPak3BcQueB2EtOByUEgSpQuznmsle3fb7hhX0UnVVBPgU6");
        communicationtype.setCommTypeName("Z5NYmPHid4ombKjCAoMViDEpqMNqS3hQZzqZEhGpZTRZOtGmI3");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("RThWgwNH1v");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("D8cLIaqRun5HhATSBMSbCXUWjYPdxxlkriLpkj3bvQOQDNglku");
        address.setLongitude("rQXUfO243GWnvUSLOFqi2f6cotKPKIj13E6hf2MgeKFT8AsX1m");
        address.setZipcode("kUbpae");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("XbnTp50Xu1T4FGQaAHmvvWXgAOIKlXFpFH3MXis5XTcZVobA9v");
        addresstype.setAddressTypeIcon("IahnRhyiBw5Rx4XHGJhfdZnzlmQ4CdkjDY8nt5dksCVc7rNBpc");
        addresstype.setAddressType("nqIPKdlbzsJIkBM9TQbclQmLXDTExgBPgHD4QpW0xyHUPHvE0X");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCountryCode1("VaO");
        country.setCapitalLatitude(4);
        country.setCurrencyCode("yyM");
        country.setIsoNumeric(210);
        country.setCapital("rNbRM2VIN7mQpBaVqcsKlEJqVDt5HI8A");
        country.setCountryCode2("jJy");
        country.setCapitalLongitude(7);
        country.setCountryName("bJd4AbZpKRRsrSQKvcGgtV6rr5hYLTLcO6l87HhaKHzpFwDrNJ");
        country.setCurrencyName("NTw6PN39S9abV6opiEpQdUK2qUjf2LT9vhpinLZFe7Z4JSaG0K");
        country.setCountryFlag("wNNELEM2ONPKsDMyNMjUitxxouI4gxfFoZr2DxzG8CfCFIsK2f");
        country.setCurrencySymbol("35vuW4hPdUjm4tYv0hPvsTk6VbpzlIZx");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("tw4x4YXJFVF2grCLiI3G6Ha2SHxv5wXKfSqmrokdQn9gAk683X");
        state.setStateFlag("kmww1fozoHEcEPeRXZw4HmaDWIVtgUCbXmof0bRYMB0vyrQEAT");
        state.setStateCapital("MryshH48hzOtdg2JD4RQvmdcPjyQzzhXXg7SzR0vEfkMDKU9M2");
        state.setStateCapitalLongitude(8);
        state.setStateCodeChar3("C3ngp7l5j6qf0zW8Fsvs1o4SpuO4q8ZZ");
        state.setStateCode(2);
        state.setStateCodeChar2("Gympt2vdhSV0YegShMoI4ihTtvKw39t2");
        state.setStateCapitalLatitude(5);
        state.setStateDescription("ZlJRBfsGtQEIrbBgjDZ8ONSNE96h6V1FrXMxCVUGPo3BtYauKf");
        state.setStateName("EEyV2NMLqBb3OBEpwdlz8Iuh3BDoeVXZHzPN9ADD44pf6ba3Kn");
        state.setStateFlag("12QRITr8q9F5W605RCSCwhuOONTuBTontyfo2WUW4b2e28qiNG");
        state.setStateCapital("neZEEZMsYHY8GvaTU6xmSlrkehldGEa7Hu8pBI7rVtttEfIoGs");
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar3("Tp8FJ4boydvJsi9Z16AVZQuPmUcDAEjp");
        state.setStateCode(1);
        state.setStateCodeChar2("8vLIltcsFPVpmEE7SbSpzgPe5tDN9H4Y");
        state.setStateCapitalLatitude(7);
        state.setStateDescription("HZF7YAgfA9augA55IZOoT1rLeXeNVbkWKtYO6AOjKo8i4u2q5K");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(7);
        city.setCityLatitude(7);
        city.setCityLongitude(4);
        city.setCityLatitude(7);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("TMwBY3OZc4Rgc3op5ryHkvFgX41wUTljxTatkrs9W1kNSkh0pY");
        city.setCityCodeChar2("fSTVm3VvO6D3ivbcOW7fmjHWt1EoXUD9");
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("78XHUysPJ2qsQ0nW0bqNfLB0leTirWlxdJ0TFfLKJoRkXCNzgs");
        city.setCityName("u1HvJ2sgG3IJJTnG6c9fklvGTQP5bGrDLymKUYNi0riDD1ofAt");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress2("s0OfkWwGwplpz5JDfyyjv07bc41XzVhW6Ivi1jD5Eh9Sm5OjMB");
        address.setLongitude("W44TuxRa213r3c74ZtGf80MLRZ74g8uuKLyFI5Onci8tv0shWf");
        address.setZipcode("CCIqFN");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("lSovtHik7PzjLY9ltw5hVxLZMdVgbrG08k1KMTVIhrb2INsbYL");
        address.setAddress3("yNDNt9s6ev5akcOWRIifFwTPBogdnmV45ZmZ85MaB0J3U33BDn");
        address.setLatitude("GgYMsqagq9MBYve49I0wdlbXcdk5jsXAreCcGSXzEf1or3JXlt");
        address.setAddressLabel("b0tC7o5tz0X");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setPhoneNumber("MivQbVqDxox9DW5oL6yG");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1466571733438l));
            corecontacts.setNativeMiddleName("xZu3T3p6C4i2FyqtDRX5OxKMWITVq3J1MoTT0sNM09NCnVTs1s");
            corecontacts.setAge(76);
            corecontacts.setMiddleName("gUosruwDopNCRfQrgWPnNlt9x3MKwBlECvrecTcNsG8RooyL4A");
            corecontacts.setFirstName("gIAAyX9g2dVpZ39TSTmm8R3GJ8MzH9QSSbwwgSX1qgYu0TOWJR");
            corecontacts.setNativeFirstName("8LMcKr6uzi9hbXsYMNpg65qohaVkA5TH0jnWWWfLw2jwy9uY8g");
            corecontacts.setLastName("56FR65pnMEos0znRSiwbQJqz0ReeIlN0qp7nT9yy3VVuQX1HSJ");
            corecontacts.setNativeTitle("o0JwhWmMdRYrzEH3LfKZfl9dCT8lUSsAP7C9OAbWk1ThmTS7aB");
            corecontacts.setEmailId("rXgb7eKsy0DrL4rKDq3N4CPKINdQCYtzY5G5IHyufw6NAfbqVv");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1466571733724l));
            corecontacts.setVersionId(1);
            corecontacts.setNativeLastName("Fi03AplnKViM9AKU7cWwjDCT0JmMffkZqH530lw7Yp6EZca8WW");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "plu7BOdbzrQJ4kRIN6dIKiJzSxe16zMa0xibCJ5gcbHQkc4BNsZhSPrYxslQi5ByyPLYFggUhBnFHO36iefSIjuPaL4VpnIcFPLyrQ0RFRr6Ry8WRi4QKq3yhOmP37q99Q94ZzzrTG4GQEnHKEXwOT0AXJ59Enpq5hDpzF4gbfFS1t2IJdj1B1g45fInjOLfa5iTBRX7KBTO8rD05IHFcCBXBubvJwT1J7zbhB9eVMfw5q6YtyiDfqdYFM9AyCWRw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "LI2oNcQkB6LDdVTTCEBKwuRkMhwcu2S9lRejILcH5VFXyg9EoAVeoPjJBU2Vmz1DcRPcUKzRoscXVU9HGKRS2Cv4MldiYlDh5Ure1A4cToOnVJk9rLNayoCKuhIEuPWxlugDJBv5rr9IKkc78Re488VIdl45HZRrVqPhQchhCMZ0U1wC4Hq4Tk3MdKZDJmbnTZkj27Bo3vtUBDEjMlU95uyWh5U7lS9FMNd5b8QLpjrkt2phKGJsAOL70V1if8mtl"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "tyUSdaWWR1k3hDsJ02XFGTqhBGQxSym2DLk4qiwRwbpwLhAW2X2cYa7p75lQUuBqC3VCDUavQDAe469HFdFAieeyuoJEaTmStM4yI6uppWTR8wsdu8BFCeKD06nZq4Hyr4QG3GufwwehJKYjMgvFU8GFm1kv0SOikBts2ABiQjcmnRoPEpDpVxVOaBUfmRBxpvxmwR87RVLF0yd8dmkxOSVghsHzme0tZxzNsJDUh8crmqnlATGxlqMIPV0LwrgaP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "t7vQh1aptPJ3zSUaHJz9wvgiAH2SgAQTpde1SllU8wLHSkGd4mNx8ZCLsUe4zaRRV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "yHJXJ6N70rhUWkAuzurtxIHghP8AcKGJDGoEjocUPHK6Tmb0rlG6FQj7WCzQd1NjiELgWk8jSueLA1taM0gFVWaUdrr5c6V2fLG6BVRTUjCOPUSh2NrVYm1RK0DaAszEhn0JMtQbnve2g9jq0CvIkO9Zb2ucRIbvR1TAhKZH3yx9oDoCxhE4edMIetOzhK53xVv8z7nqaU1iehhyZXEa9hmWbfqmF5I8Lvcl3ebZ1bHG7iTYoQH5pqizSANso4ZP1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "p0gy5uEgCnbHuAxzY5HjRhsmZHwAM9iG5di0ObYYHXWcbcrSYGCzL4TNPEtTPiveURGzizcgorgLB3A0IYaW8r8NFMqaarQEB5XLqPyTGFuD9PsDnLe6X35lVhCMQkvBLPlKZKmIfPJ2ZZhzDJfapbaOc9XifpVog4xIhHZsdR2glVsdQvbPXW0s3uiecXy2GIs4nDK9ObYCkvnoZpOjvc6LwUGM0AAcldv6KvjrPkrx7Cgq1wrJsfUDxBetZmtKW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "CR5or5fegK13XNHpto3Xy5Rv0T4AwpAuNVyUiMoHGopDbwgqUYsOMgADDN769ljAXJnZmaQR2AcpMAblIYOyataNHNKNQ3063Gs70yZA0jeEBbc5eSXGPh77SOVUwJto3BU7UmbRjzz3Uj4Iyb1TnYIpZVKMqPyMrlEGzEHOXZmiNwMcgK2XeVSRmXn8pbnftnoG2AadDEUHvwAo8KkGDJDXPkTkHrl7XZJYYW0ti7b5aQC6MURjbj17SdV9tjLg9"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 131));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "OMNOvFLG96WscnnijHnzxMaiPlIr0rL959oTm0llkQSUyJpWfYb58p87Nrdo6bGPRolYZoBDTLtraseEvbE627Y0avd69WPfzaisj17izBRWmQR2GkSMhuUN0PngmZhsV45CehdOD9wWgxcjrsMyTM25yvFiy8c7ocxxDwvtegeTmjVwfkREmeeG3eWWzBSITf0WizbpnElOHKIzQOLNNRgXe1TLwos0h6gsuZUomunZt2Rjln9oWz5KYyKP4SGf5"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "f8EiUMLKVsxyJ36dvEeD6"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
