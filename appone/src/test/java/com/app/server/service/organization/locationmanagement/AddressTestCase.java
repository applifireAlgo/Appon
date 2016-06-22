package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("01SitvRpg4Pv7jJAEl4GYjeUqra2mKpj71ijW86YRnrwG6o9yY");
        addresstype.setAddressTypeIcon("HjH0MEO6n5foeWTsQUa2oyIRrmTYSmzWwP4917LciDoxCrFnq0");
        addresstype.setAddressType("hMn6YsDZLr1ransvjEFnYfjcDBVE1GXJrhbzjCIxVmWzc394UB");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCountryCode1("k3z");
        country.setCapitalLatitude(4);
        country.setCurrencyCode("olO");
        country.setIsoNumeric(831);
        country.setCapital("W4ubUe5IL8QoSA29272V5M2AEW0SF29G");
        country.setCountryCode2("oPz");
        country.setCapitalLongitude(7);
        country.setCountryName("nswq2JncDvzIt0GzWFznmyQbhrfSW71AcuJAt2yb9PrchZ037t");
        country.setCurrencyName("mlhuSDj9EhGK65fXo3XEDPdDnVDEpK2QJ03iiA0Y3HCrMmdTjw");
        country.setCountryFlag("5NzCOjW7fodOesZrlhcmZSfxaoTbZxEhLeOT95LNCBiVamhV59");
        country.setCurrencySymbol("64jh9zjhbmYfkMZrGamg8ZrzFRd74le8");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("OK1TJSh8H0ArQitRQWIRYC038x4tPmRtDD0mTsQQ1PifNY5BMF");
        state.setStateFlag("cqAlUtDWS1EpJlhXNURoKObevPAxnZ4E9weHQl3kxZPJOSadqu");
        state.setStateCapital("zy3ZuPcJ19RrBVyhHtYqeGoZ3b5kBHQrPBjzUza4wTGxLyKhAW");
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar3("33KwWBSC4iYREIhjbLoKfxApUs0LDEy5");
        state.setStateCode(2);
        state.setStateCodeChar2("Mk32I3WdbiZP5NhxzK09GizN3971UFNA");
        state.setStateCapitalLatitude(7);
        state.setStateDescription("BT4HvTrrCBBpGO7pRzOPMpFMogirA9hAOHGtFasHBfK7DmEofr");
        state.setStateName("Wsa2wiPf3ryTrnT2G1pLA8xiShXxS5Px8NZXfVHfYdJ402QamR");
        state.setStateFlag("8Uys8dQddgzhquJbL6JqUCmuXKXaagU9FFAY4VwDuoi3Q5iq9c");
        state.setStateCapital("DQrMmZL45qlt47jMvGdqarVhmUzB9cuMrkjUPCgEgu9F1lRPIn");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar3("BONrS87JHA7blskL0RdmrRfhqkQpvm7H");
        state.setStateCode(2);
        state.setStateCodeChar2("hydYmfSXgLXtdgNptt9krAJuxjQjIg7f");
        state.setStateCapitalLatitude(7);
        state.setStateDescription("R8iOYnqRbWcR4MqPxi2qLpocE9tbX1FoNpzSBI2j0zuiVearsr");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(1);
        city.setCityLatitude(9);
        city.setCityLongitude(8);
        city.setCityLatitude(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("CxacbRlCfxbafDgFjjdVX1KOnS8lN4CyZe73yqpWlMVZ3vaR9F");
        city.setCityCodeChar2("JYN0pQlpG41mxdXPVPI0Aemzz1E6qJS4");
        city.setCityCode(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("1uRBimlzYcYYBH06ry4NpNYI2RaBWBYj02IWgy4gRTv6SnQ47p");
        city.setCityName("yyHaOb42MSRkDXOLZSJAqLemPXHzJjXJBtwLViR8IVukHXdVhA");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress2("Mu0bU36DO2vxh2RSKXsND5jeRBjsj9ZCkY0oiEyjKrl97bMxeh");
        address.setLongitude("VSrPLR2Uz0kjTsfdnYXthhdlD5axMXBw0BuHV00Mh4IEG49wLc");
        address.setZipcode("0ogtqo");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("ChDfvaUelYuO7xNDOWWMtAE7Ry3w6cQIbPUKLXSKOgXKovF6h5");
        address.setAddress3("B7PWWV60HPYHC831PKKCTjgUJVjp5lsV1qYSVlx1U5PRY8wpkD");
        address.setLatitude("HmWEKoQqoE8NWMHP9eRj8JbHsgRNnUmVfa5iX7NEiHSmHwIqRJ");
        address.setAddressLabel("LaD5xsYB7wR");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setVersionId(1);
            address.setAddress2("hYUulL921hXELzJLbevdk87gnHq6KGTyeMS3kPeaVBfnsRpJi6");
            address.setLongitude("wrqOBxCc42uL2ABXMUgDpbYkryfMtuJ9FELP5rRdRCQit4B0BN");
            address.setZipcode("gmFmrc");
            address.setAddress1("4eHZULB39rKMXwZUfdCTKoydn7yHll9DkZ18HCOfkpUc72XGZS");
            address.setAddress3("qacr3rl45mOsNWiI5WbyD7EDxa4HTV123N5hGrPdFXYdMpYJ78");
            address.setLatitude("7RMZ5I6mVpo32sOAc0JQ3N1dgcVoRNtSkHNUKe5t9Def50NDvC");
            address.setAddressLabel("BafVm8ywocG");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "LAfLvvKfIiaZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "sbgQzK4b44dYGqodBuZv9F4JFddvHfjIiKnfKSHoIt3OiHzTenrXENx9e"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "9K408a55DnKYuGdmKrkkMKVEBjT3c361hhdcxg89jLisejAeZtNvWaEwV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "Z158TX5keq159PtOieIrJCehJ9sDG3a3vX9SYSTMCxKXHBBihfHoWkgMC"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "IiQG2E8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "thkLwgJSZUWT2SQII42J6ByXQKXWRzvnkN6gLNg6fVuJ7RqjhBOac2HTT5G9InySy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "mIp26G8kobVxEfX0s0qVYtQgBcdLJwtDrQCdB7FrNlD1cs4J1MKPqWsFKAvh7mY2W"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
