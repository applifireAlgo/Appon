package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setIsDeleted(1);
        user.setPasswordAlgo("VRrSX5ENLd9kIAVpofj6wpoWncbyHhqEeTCjzCKn9RFSIk31UW");
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("WYr20XIwXODdu6oP4LhOFxxhbA3afLqS0WjlIE6FNRQi1jTvhm");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("rhS4oqmyafLsdmNg4F8yoS6pGz8vRusrtkvi5QrYMEyFJo1lWb");
        useraccesslevel.setLevelName("oivJ3ifC7RtHXBfRH5k5LKQLvoLwhDLXjhjc7HZEyKKJ4m3HOA");
        useraccesslevel.setLevelDescription("vupsyjcqPNZUTyGw17y0Srg4dJkC6nFo99bHbbJJQ0zqsH2Rzt");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("MdClmQSXdae9otxoxw3RjIXpXHhhkyrqMaoWMNQo5084dGoZKL");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("2kRu5FVCZb8iwOgeSitLB0tAYpwiM6zQjD5yBmHwgUcQNYohbo");
        useraccessdomain.setDomainIcon("81yV1jsPD2sBe1O7MxiQwPBIAbN5BLMevCpNpLQt2sIuZec7jv");
        useraccessdomain.setDomainName("zV8XMCh0BtQdbHPGfR5Mr2qWDgeQm70eJUPQqllDExLplWkguH");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setIsDeleted(1);
        user.setPasswordAlgo("xZ0fcLYKZft64tvrIlPu7BhWAzKsA7KMfZySX95kcz6c7ERtmq");
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setAllowMultipleLogin(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setGenTempOneTimePassword(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1466571751613l));
        user.setSessionTimeout(1166);
        user.setMultiFactorAuthEnabled(1);
        user.setChangePasswordNextLogin(1);
        user.setUserAccessCode(6538);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1466571751614l));
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("T43qurbjwcuwhfd7kcpatbP364L0Q536ZlXiexLbEqyZ5Z88YC");
        Question question = new Question();
        question.setQuestionIcon("a290UemR58ru3MwkJjDYB7xzGCER7D0gu6AIlMoWHAHWQ5ZQgE");
        question.setLevelid(8);
        question.setQuestionDetails("TzFEZgZIhp");
        question.setQuestion("EXLamTLAjZcbwkp77z04ayuxxjGIxiCAXcNAPu98Sj1cVdUdXV");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("UInFIwPGZo7fMLgA6Bw6ct2KYT5BZn7mzByPVyDjXWrZyH8wIU");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePassword("VD5JGO2DPGkNGqEc6ne9npP4M0PfPocp");
        userdata.setPassword("oZi32YiuiWgWCl6AJA7XxDbpPYgVzMGXLJz6NT7hUBHPH5Anxl");
        userdata.setLast5Passwords("sHJFw6ZCEWwl6UWBCCkOX3oK9L2vLUyAtzGOynbbImLYcI5Zrf");
        userdata.setOneTimePasswordExpiry(4);
        userdata.setOneTimePassword("rp4pAgOttdUNqquud4kxjj90aFO3Il1f");
        userdata.setPassword("mEQPfexXhH1GvrJFLDs8ygujCwDdDnriWlgVB6JgujGvY0PYgg");
        userdata.setLast5Passwords("nQy7FbkVy4DGgngN8ReNcxTlJzyoUiNoWOJlSRVKJhyMDf94om");
        userdata.setUser(user);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1466571752188l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setPhoneNumber("r6osOoGRxQgGYMQhMIii");
        Title title = new Title();
        title.setTitles("mliLL3jvSKDuldnkPEy97BpNYr2m2bWrIBHaUHSopJQg6bNLsA");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(11);
        timezone.setTimeZoneLabel("8Xsw5vg4lkkReYWuPiP8QQZsjQ3oFVwZhMVsVURi4ERg6Wknaj");
        timezone.setCountry("hNAXtOkuTa559TpW3n36CFP7OCdIfToQXsNaEX0qaoyzKdgPOe");
        timezone.setGmtLabel("NI4BoixihWlVMEMAaeuiPjbK4obsF3ufUckF7TfEwSGDIzpFrZ");
        timezone.setCities("p5fW6FlTsWQkNTk3iJ6deon2mzzwQ1iFw4PQnP4jzpQf7PkX4t");
        Gender gender = new Gender();
        gender.setGender("rwPtoKC3yWUnsaqCmdUQY8326svxYLxIbmXCxcoz97vsM81Le4");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguage("f7trODsMa5Pd4rnGWuUZxhd9CHpyZEyObyh7n8w8GN9eaSbHAo");
        language.setLanguageIcon("wkqJfDU8axheo9su6UZtTV7hfEKysbUh9MAat8oPfHkQy2P2LN");
        language.setLanguageDescription("6YvU02wxAypqzgehJpkv0M7zSTTsgMbgDziMcPxy5Oi0KsVxiv");
        language.setAlpha3("iJk");
        language.setAlpha2("Ub");
        language.setAlpha4parentid(4);
        language.setLanguageType("jV4SU3Yf4gyOLF0ZEBGokrtH4RKab46H");
        language.setAlpha4("TNWN");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setPhoneNumber("aSSeqEvls80KynBWLFgX");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1466571752516l));
        corecontacts.setNativeMiddleName("xcgLCD1beBWiyF9bWEQfjNt6VtxU7p7fC4Uo6SBePJV4OVzNMw");
        corecontacts.setAge(60);
        corecontacts.setMiddleName("wsfbsrCWxoa3qo3O6Z0LoDuRxPDsijFoTA0OnRc6RTwNjgI8l7");
        corecontacts.setFirstName("icQmnQNiXdy9cNrUYoubqt8gdxKzhc6gtrZCFg3xgDJZJKvbiD");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("ece3stGsIp1LfMMEiolrXORL7B1OzZ8iDMz60ybmPIJ09wVUwy");
        corecontacts.setLastName("4tjprBKDQQD1gKwZXV3vGvOelTT0nJuGhaWqnFicTcAXohxIEA");
        corecontacts.setNativeTitle("v7j6VHnCNMKf713BLjvKfIpSkL87FnJPuk0NJqupquNo3cTWe0");
        corecontacts.setEmailId("5DTQoK7S3ZZT0VraXTywHb7sl5RtA8iZDysIVplIxw7Jr6I6eT");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1466571752675l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("DTgF1RaCGisYfQTbQZgJJUQACvuz3AjxCldGsHVZgNA996v8lu");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("2WUtPOPiBEBche5QdWaaeXoSqYl54HocbbjwXwkimcVwK4b7GG");
        communicationtype.setCommTypeName("KrlTzHXGofVlw7cbxjXXc6brAfpS4JXI8lW0x2OFsHyyObA9qA");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("Sa6UdozGyf8MMGPc8stR0aQNSNss04Rm7rf9do3fQqzZ24TEIt");
        communicationgroup.setCommGroupDescription("L4QW6P2zeWBxrqfNwRN5OzDrEGCSQXuZ6vrruhImrreT4iKuxC");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("mTQYBNEShaADgvH4EMrHlgujf2AKvuXHWxBvVQpm7Fn45RTUAq");
        communicationtype.setCommTypeName("f0zvPRdFv5cRqqliSdvvxwlZbMKN67rWRT3xF8avUB2p7VT8lJ");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("6W9yXhHlpK");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("4sLVJa4pxJaUAVVy3tpr6RHn6t7ReoqueY96f08OgCxoK9ds9N");
        address.setLongitude("nSFmaDiBjJRB78RlhwEMMdSjdikY27yCyWzwbYFhK5Mlm2OqV3");
        address.setZipcode("lAbscZ");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("bC4Jq4sie3U7gAnENITBK93nImHWMunVRMGE0KzDCLAZ6JlXVy");
        addresstype.setAddressTypeIcon("k7kuGy32jLc7ssStG5nNG7zKXH5tAYINSDkfNI5dMujH1QYcwb");
        addresstype.setAddressType("gP7msu3d5bzBpNOBlL0CZ2pnhZ5a9BNJwJudnw9cTHecvKvd5A");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCountryCode1("QX7");
        country.setCapitalLatitude(7);
        country.setCurrencyCode("CYZ");
        country.setIsoNumeric(659);
        country.setCapital("fPZxlFFlEtfUgBdOoN3g9qM320uI3JLC");
        country.setCountryCode2("7O9");
        country.setCapitalLongitude(6);
        country.setCountryName("dZpcbKGvUPvbgVrXZTP0vYybN6VFbcBG5Jg8rnH0wMwEvHxqnE");
        country.setCurrencyName("n0XtXvzwEg0eINpUJ3DcsUZ0TopCVmbqq6veidW2nGjQsiGmRI");
        country.setCountryFlag("oKZEeHPxGdmBWlbtJLVWhYNpUXFuORxebO5VojK0KLJfNDQErx");
        country.setCurrencySymbol("uYEJMwxRYUyWGpOaZjWqyDhz8MbrVvq1");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("0d2qsTO91cKaDeafbz9uRi9ejQmnRkEnA8lnoK1cixVLSKIOIk");
        state.setStateFlag("qnm6WUivW6Lfc2ybp3Nce3D6R0IMYmR39qcHhkzLssjJOk0YhN");
        state.setStateCapital("U1KHNj3Ft6dwiX7Lldzhs0ab7ufNDT5vy1XwyNa93iHRjgOox6");
        state.setStateCapitalLongitude(8);
        state.setStateCodeChar3("q2YiACE43SQOzj0pCh41taZcYTfKbcsf");
        state.setStateCode(1);
        state.setStateCodeChar2("XATJ5bISME7TdsgZlGbJrfqfJ5vYuutc");
        state.setStateCapitalLatitude(6);
        state.setStateDescription("QwCo3VV2AtbWV5JvBJSexV8NES9vkiTNGJ069vgCEvLnMqf44g");
        state.setStateName("yhYI9QUKy0nj2BjN51a8Wk0ijguWsAhtrskSCl5RqK7LJwYRYq");
        state.setStateFlag("T9PRVnucZlU37BSMqAv8ru5TJsqTLQzPKQ9Ofp86QYyd22OwC4");
        state.setStateCapital("8O2nfkHNHQCCaNPWFMFULQg1bFH3nVzEYlUfQypqxxBQBNnj9j");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar3("bEpFOhVgNBsG5MoXQDsnQMgsQ8aAPfy5");
        state.setStateCode(1);
        state.setStateCodeChar2("OXONyVIj8TeZt6s66tN5VGLfx41kl26m");
        state.setStateCapitalLatitude(4);
        state.setStateDescription("MMwDdPnkGLXgVw4sjkDtc3ppUUwAVyLHZ0m6uLVus0xyauVKH0");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(8);
        city.setCityLatitude(4);
        city.setCityLongitude(8);
        city.setCityLatitude(10);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("bATygvpAEAfh5uuPrF8Z965qM2EYIcoStfyAIUJLX8Pzg4GjOL");
        city.setCityCodeChar2("7IQhIRZnJ8hNYXVFhmRs6x55GCHSYJnt");
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("ifjZPqNsmGmErsHrr5slP9aik9zgwWD6LkB78x6kVtRXWvHrlO");
        city.setCityName("LQVEwbHQ0oVXtSwQxNLvBg1PTO3LMQye2g7ip6gvMiMYBj6PyZ");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress2("2hi2yjxg5TMXqy1bRiwEbi6TO6IeMsapV25BACxedQCNVCcZ4m");
        address.setLongitude("D8k6xIzFLJrnlKPol3S3hYsOX0TkCkThd8JWENtsifi60T32ef");
        address.setZipcode("8BFQFI");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("ijvA1EhwNrys8FxBtn4KqsDea2gp1gqzRzrhgrOPQLDow1PV1s");
        address.setAddress3("FeOQ9dtLEfUPf6KrylrEVHkFHG7f32KQzvmEifCq38ljaU0Vy8");
        address.setLatitude("vZh9T4kQbv4X1g5hgCwrrxKezAWFyh2GpXos25j27XgThq8oQy");
        address.setAddressLabel("ETWWSHO5CO0");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setLoginId("5gvqERFkIBNPWSGqzn83E2stJRa8JXB4382AzGkGUZwYlSQnXV");
        login.setIsAuthenticated(true);
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthText("WJYsGyW9t5NMsn8O");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setFailedLoginAttempts(11);
        login.setServerAuthImage("FLflKsP59fsBruyTYSIGhTLy5e0iOced");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setLoginId("A5lhPN3aphxvWvjREf4aMosiVFPhgtL9iCPZPTJBCF6X0mB4OH");
            login.setServerAuthText("CTRgUaainHn6pfCc");
            login.setFailedLoginAttempts(11);
            login.setServerAuthImage("W1qWxOZiqCx6bgpkKcwAEJmQkqxchbkw");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "3L4kHiIZFjdLT3sJz9TL28ltV5mxWhXkMMsiyhAK05KQ51cD2Ua5MRFNvQl2rpnSqqiWtG84k5rPcYIOtQGu6vBb4QgeaXtMgjgrnrvMoLywquizbKJVXmJyJ2D9Sp890MyLMvuq5MVMdA1EJvV0zh6mFvalAfo3vugcqQgGfmSO3laN5JJgK6d4mkR0oqky0sYN3b1F8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "2pMzt20C5v41SHKH6gdqS9W3ZHbKiqGvq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "X0RHMy79joETD5pob"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
