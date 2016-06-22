package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("N6kJ9zOYOaN8IRXATdtFK1AxILrbgmRNPVBJfVP1OyTAEm8qOD");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("Q8239HFV1sxu02SQ5AZvnHpUZ7ntIl6HxHkJHLO4Hnj17uQu3U");
        useraccesslevel.setLevelName("XQqX80uW7EInIa1IJ6UV76p5MQmb7Ec1NqlDeuyGCD8Xvyl8Zw");
        useraccesslevel.setLevelDescription("fZAgXMEQ09PCUyzu0PhgUYuLmGsOSwQJ8P61UCvTVC9J2eDY6i");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelIcon("RfFYjgcL1YNeNytpz9mSLqlWolDKjBsOgb9IL64BwLN6NBURGP");
            useraccesslevel.setUserAccessLevel(72538);
            useraccesslevel.setLevelHelp("MrujbfvPQOH0nvfPjAmwcOpX2f337jbcxQhRJvDdMqnsUkUpAU");
            useraccesslevel.setLevelName("8FlwNcHIAOJ1AIVVO4HPk0IrZo1ej6ZyMdhDjqPLUbhqk2ReJP");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("zRQkQAdcp9rYr3WkYtO38fraH05IGgNCZBhTzpD8CZMYxDi7gD");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 160679));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "C9aWy47HdWL25mvOf56S11Q70nS5joPjEGGis8PY0EpDLnBOIQQQ3dXdzRgWmVaRzGSbQjckydlLGakX84ceAmaRmNGiD55Y1NBBZWKHMLKw1WuNwPmUkblgxMzyf0gTWpcKP0It9RKxZ4dgyRFxX2i36K9aNz1gokDy7eydifudJfD4EbmGce1aGNRYcQwbpA9DgDAfP4BwtREOBD2ZI1m7fI5IVO4F03mfWYLTsaPwZmvue84CHP833QP0kzVIT"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "eulqE20nGzI6fG0yuksazGG1L8IvatY7bQzzUxvyUx1zoLxVIjO4R2RFTVVfMgyknpOLat0tWJNBbGxI2IaYDF3f0awNSS3KknpTvmVECwxN1bM8DCdBgSBITL4krwtAPnOanFxwCBkbEJDjs2L1Xb2UYQhpiiOyyu5U5OWf3EiJr4rKpZHYoxjJqarAAULI5R1U58zNzz8wQI1UNhUHvpTeXuqCsaeagrDBiSY1XiI2MLm2nd8Qx1rRCIxiza8gb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "WTHxTL4u3ZyxN4mbVgNKiK0LZgwzhyoj7VvTjVThbaVj9qqg9dhVVMhiRdQeVpkOz2HLuofFjrNVycSOsZjbGjSoGenwzkaRvEg1ci9Mrxj3PNEtpjhANZryQxYrSZ55E8xYwLwhOkk5M8dUya2InFq9ZFzrM0Ol27z8xXsRukZVlyE0dUY5SVvz3dtC3FLi49T7jXp52LdnULN6z52Fqp8zT7gRGfjK21a1rIOalvHAaO97x6IoB81GsmNaxZzuP"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
