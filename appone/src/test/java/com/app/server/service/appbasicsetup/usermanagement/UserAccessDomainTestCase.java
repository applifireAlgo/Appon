package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("ALuf27xc1vAKS2QvcfFET9JpwfLGifb3pm7KAZPk7XWSaVn1SL");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("ELVoKowBWyEqoQGZRqlXa0BKrW9K0zzTY4pe6JvpNoZHjRHP8f");
        useraccessdomain.setDomainIcon("Oi5LnriauTxiPJe3gPNDGCOwpdr91ocpbsMMbkui6QV12aIigp");
        useraccessdomain.setDomainName("vRE27LCZf79m4m2Gkes6RBO6Pfq4CRUfWplJDihTFqrrmWNZ08");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainDescription("QL685MU4NUUOuIsOCRihU0eRaqErkf0RKx2NwciDC7ApcthIRO");
            useraccessdomain.setUserAccessDomain(22030);
            useraccessdomain.setDomainHelp("8ndgQEuEf8efJddh5dpwcAN7NkEiD6AUkuVgYuy5RqD2g7AthW");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainIcon("55ho0ySSixm1VjmceTVbvTx5MAhJK2qAvEhQvU91J8SGbYjoDT");
            useraccessdomain.setDomainName("0MOjBW4d7wXeS4MhHFOueupVLQ0yTM63sXNB1RFALMK5wc57ck");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 152179));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "CfDTXOYyTOo1M55hVHy7K6mcf46V8l2PX58X6UjnvVZ9QzUQqqyUMlGHyoJ5MHqU7wG0SpajlOy4gYfZaD7xuQDCT3hYabD0amjFc4sAK132nn3Ulixaddvx9b64oK4NeGA1JEM5WR2pO3faf1WRakvCApthv1BxkzBceh4OsMXvMkjyBlhCyXvqeh2FTG8vUpYkiyfFkuJKqi8T5GpBtw5uBYrzzafXVBOxyJKdcWF5T0lYz9CIS9yRboYFN2vu6"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "75NlQQYxcGQS3diPLubobj0PxOsucm8MLL4bzc1v9RO7wm0LqnqjL15Twni1UC1y0kuLqGBjVdDtF3WG0TXwOzOWQ7d63q2aOqscjmVkwzT3gvLERZovQ3ordLssahxtEMzd3NAUIWRdwabfQxDkPvKjFBleviiFSzXTX295ZOVYAZhLFZ5MmOh1NVvPjXkqyX3qoUtiQtyUCfleUiaM93rf9ujghN2d1QcFigRjkgj0w46Oh0ryt1HI5g59Rz9qq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "mTDSbZuSIQaVeExVJowEmAP19ajYbG3oreOHj2aLVuHVo5wduVsEFhmmsBFtAeQqJ75EtlTtUzWQYaKRKlp3R0zRdz4e0WO4fKr1XyF6rTpRDDrWBtRWZoCWO0SSnWBVlNJX22G04v9hK97MWgR52xZdV4HTo5UHh17hjDxUD7fqcbjLcEvNxAHNCUxFFjAShzQPB3jM2rilAz8pJe2BThF8QW3Llykq1hKsOqIGnebjrKsKPLfjpzLeipfHc6wQp"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
