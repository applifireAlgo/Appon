package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode1("c28");
        country.setCapitalLatitude(10);
        country.setCurrencyCode("akl");
        country.setIsoNumeric(937);
        country.setCapital("tV4O0z7vm1CRJQ5VmJpElV14xXyKVPls");
        country.setCountryCode2("492");
        country.setCapitalLongitude(4);
        country.setCountryName("zeYn1Gc2yQRio5VfUWYwGLhtedCBZ8DFbWo8fUAOncEyQSz09o");
        country.setCurrencyName("eSdDSEPLN1aUDyCHmcsgjx9IcS7DgheguKilnf2eJnod5w4oWx");
        country.setCountryFlag("qQqA1uv4xq7bNrP1TM3yfXkPSn01FSe81jpHsBpos9CipHUfkt");
        country.setCurrencySymbol("PI0bQCweOnBg294S7EKPwj5iDlptO2OW");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("dDMtA3umZHYd9OfamdMYFjV8hrZ09gYAQys5FpoysTXFoNWm68");
        state.setStateFlag("S9HvtWqhwNM7ZJ0Eg2hAgAaOMwPUSmxgFmraaDYEmPLCBS95z0");
        state.setStateCapital("B3M30wIKA1Aux71SyVvqX7L6aDqRv83VSTrRtctzRayJ5VFTtt");
        state.setStateCapitalLongitude(4);
        state.setStateCodeChar3("s65i38jU1n9UEdcZmZJxXj7QOw8DjqIS");
        state.setStateCode(1);
        state.setStateCodeChar2("CqC20RdibnIP1T31ZAeLLcadxTqWa48G");
        state.setStateCapitalLatitude(3);
        state.setStateDescription("ulfykATECIekJnsvFji3n5z69gddohZ5E7l9ofXQ5t0yU3jXlp");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setVersionId(1);
            state.setStateName("lKjDHuIcpXKKFSbZ5CqasUZ1J1BA1Fz6Km5OzfKzimCe5Agx04");
            state.setStateFlag("lF1v3ocs6FEs32PX0TdYIkJ05L9qxXOW5khKXJQOZUtSZM5ECI");
            state.setStateCapital("BgR8lHL6GKo3q2f9TiycDe4ledLbHXuHifr1eSWKzu4cknGvLj");
            state.setStateCapitalLongitude(9);
            state.setStateCodeChar3("RpYDA3bbj1civ5jFR8B0sNBQWQWsgzBn");
            state.setStateCode(1);
            state.setStateCodeChar2("iZ9D21N5p4A4ow5nnaDOPoM7M5SxEQOT");
            state.setStateCapitalLatitude(5);
            state.setStateDescription("N2Vl6cSxGZoSWf38pzoayLD8E3HBr9MWyVpf5pfsSW1qXVtPgI");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "XWmf2fwfN9lZSovScmEWj5Za72zWgfdJK0x6D4MPIVxHm5bAGbZ7hyeBM5HhkRXOFK2xSOdgTXeR8isdgGXjgZb9BABsuLh5iihvSsNoBMGOGxBHe7nriOQ4W8B42ZiaXDrzB2kaCtqyxScyAXIsCeOK2Lnoj5vmRPTUDYx04nF0RMvXx9JYy63aWPs1a79DcbYh8qsbDuwSnMP3Ub0mQKV2L5jerbRjZsqR0FIxOozlaUg9BexQ1MbNtqlkCnXin"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "gkJYEpqsy9zRwKGhe50ru955BUkBwPXF5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "zycztGfYD5wTNIbUZbbx9gw6jomCDCImH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "3Rw2YNN7fS3zVYtArYCxTFLLNWPGBUa3z0uUApEII7rXH7DdwC9og9L8Bf61gsq6UK64TrRDJ9c0DVaUZZhpqw71zHcRv5817mcFOEhsloJ8WGRZJMOT6pNvr2NK396wUCyXl99CB2HJW7VXZlWwYljQatxwipXfMTwCvkx2UjXfsD8iAyiNDDeF6miXdlIbLVV0nKniXQao2vF0LhkJSGllOy3Si6pemeWlBE8vutPZWwN7Rw565Rs0QSDlwCc0A"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "nZQbnBYO63YP0KuA1tGKqUt15igDQh9QlM1Lyk0fyMjdoEANvCwtAircaQeZFWI9K1bBbopia1nRTy6QmxLOoqnMZ4neTvWYXtY1lrJC8CMfQ1k2dFHeBaN89ZXObB46s"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "J4PkJNwNFRJidnvLIWEiVZDksFzSROERn40s4J4pt6jdhUizQQ8NEbYO817nnBj3GArAWjPRvt92WfbD8PdnBIwG6lIPLeuam2Yb1Ve7pLAnaHez4X6speWqMKq2WNgm5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 17));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
