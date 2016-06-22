package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode1("xNg");
        country.setCapitalLatitude(8);
        country.setCurrencyCode("hB7");
        country.setIsoNumeric(670);
        country.setCapital("Ec6vCDh1U1x47pa8ma4ylZGUA83Tlccl");
        country.setCountryCode2("oQm");
        country.setCapitalLongitude(11);
        country.setCountryName("wzL1GGQJtAkUxTH89Nm0bBJdVkHxgBzkYK2PwQ9DacugAkN12o");
        country.setCurrencyName("8ew3iY3LFSMDv8VbNNEcfGk8Wg9hIaBrH03TdNwLJqUoaHEhwE");
        country.setCountryFlag("VnKdVLvUD3sE3KoDHB0fGHCZURWrZw4GXsczjqvY98VmHcssqj");
        country.setCurrencySymbol("nIu0vzpEVn9Qnq0G3bNH7WPsNnXoUH1o");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("cak3eGg1NO288Z9dD3d276yS4XNtmNhhtjmtZ4IZx8hsTRUq3D");
        state.setStateFlag("NCEO1OLGWs6sR0boDOqTCVoQft0OP71g220IsIEtQscJVKi9in");
        state.setStateCapital("Czy0e0DlhBGhPiXI8JeNER3DOp1xx4ip5kBaow16CiqxPM63w5");
        state.setStateCapitalLongitude(4);
        state.setStateCodeChar3("6QjEX7vSw4NgKOLXYZNASvpAjjelGvGs");
        state.setStateCode(1);
        state.setStateCodeChar2("wHHUUi8WIqh8RvfqPwssj8mQYxNFLqxC");
        state.setStateCapitalLatitude(10);
        state.setStateDescription("dhykhd3aEobbeQB03cDOTTHm0FCTyGCfW1EWNAOUCnzmkDYTff");
        state.setStateName("iKSyzcHIZiC1ROLsnubz4A2R0hVZ0nnh3GNRIDkF6doOEL8T9O");
        state.setStateFlag("YrPV8jBJM0xsNqBK4e2m2uvczxS9VluhnLIlUMCpT96Eojakab");
        state.setStateCapital("xk3NiQ8el2GRyeOfJO2HrlRbC4zB43sTSodFGUKZFGYdMA0oEC");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar3("UjvUWTnhmQF9EcUhy4eUk45Z2gyX5e23");
        state.setStateCode(1);
        state.setStateCodeChar2("qkT1PW7fCuMME6TSukMJlXy509DCj4Ha");
        state.setStateCapitalLatitude(5);
        state.setStateDescription("C3qmaCl36cj1lY6RtHkysL4KFOuShUWuARmcNERsq0kLHOr0ZQ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(4);
        city.setCityLatitude(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("a1lQBM58qDM20pTVVCUobYDYnou4f4jGd1XmJwkX5eRDByPf0k");
        city.setCityCodeChar2("AaGYmwVTWKxPhrmsURMIdv9p7tTF0e6K");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setCityDescription("IDYingoElPFDQ5oJ9SPIeF5GOZ6RgcXxWFsgQ2nrxe3w1R4A0K");
        city.setCityName("awSodbIR9iq3Ajibrr7blLmakfV8cvsXqir5bTHAd5si07tXjL");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityLongitude(1);
            city.setCityLatitude(4);
            city.setCityFlag("56K9C3vK01M4rGMjsqbrWV9SQtAUri1V3OG4v0XmB2HAstD5eW");
            city.setCityCodeChar2("uj6MnKYk42nnLvrx1SNKdonzvcADqxLa");
            city.setVersionId(1);
            city.setCityCode(1);
            city.setCityDescription("6aOOWnjgXcOwB5HzLmcRqEsZH2d5uaTvXfbehVLRpse8KNFikN");
            city.setCityName("dQpHy2IyJ4jnXV3k5Vzks9RIcqAZymsg3sgt70mx5cONTfoa95");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "5wbQX2Q7xxe9VYp7O5gnkLOrFC7eGgPs4PtgBAAGaq5Dz6X4vt2GZzYLHxW8HpchWpKiXbUhSYjxsYlqkeHfFyHRcsXlFy5EJVyDP3ac4vwj4prmj5ISSH4Bvv4fYuWPxbjatDIwPG817y22RGNuy7CBMpNA1Cgco9uqZsOY6uqgP8Nq8T0Xfe3us0X9M4Aw72gaffkTW7EF2snIlXwuuDA7HpeqGLEuBmtHfXImdYOfjbepy21Ngh4RqD0OrIUNt"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "8ig1gLrcDQKYP07ukgupcB7OIaVzzvzRc"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "Z1DTlCLsJ9WL3e41blGdlD0SvBB5hb7s2y4lrQlyMBCcnp5ZqmGGLfJWdHzG7cQ02CK9kCu3NGsc6nS286f4SsGCGJF5a2GTrPOYO3vgh93h5d8yu78bBCesbFjaWGQHY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "FOubKekunOcRGAg80JHQ5yQi3iLax5dBjXAEfUP6ppBa2EU2VB7PnzY4gnzaWplGOd9S7PL0XSHcY3afGKXrvr0TeaQfdUJQru2PTSn1rFpqWtfJI5o1miND53QMENyyW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 18));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 16));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
