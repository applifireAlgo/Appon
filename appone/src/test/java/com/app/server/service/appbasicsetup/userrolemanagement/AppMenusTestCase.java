package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setMenuHead(true);
        appmenus.setAppType(2);
        appmenus.setRefObjectId("eSUXNJ2amXp2uVA2ouCSckOqy4kR3GqssmNYiSSiBzzvPHn0fH");
        appmenus.setMenuIcon("m42A6EyObI0IqC4BRimU6rlF4W5CwsPFqWQzGzciyYwQYv79nr");
        appmenus.setMenuAccessRights(8);
        appmenus.setMenuLabel("7Tb9v8xiucppH9z8aoLTk3ffnor2DA9ey6fqKkIO4DihZcTkxL");
        appmenus.setAppId("FGmTbZLnSBhiDqzKGw0mcqYtBA2xJoUKTD2Fi7NZb5XisqJoNV");
        appmenus.setMenuAction("ozKRoZPDEFngAMPqnDOecTSqMyjrm6SI85AEx7xqjL6Qitv3Xl");
        appmenus.setMenuTreeId("000CmQ4XUDBp9SzbYN8zZ33fRm0Yg751zITkZ8L8U0un7ZDmJX");
        appmenus.setMenuCommands("PFB4sqE0aAIWqhYWcnNuRt85ekvV37Gbh9MycvrdDKGWs6NZfO");
        appmenus.setMenuDisplay(true);
        appmenus.setUiType("tyo");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setAppType(2);
            appmenus.setRefObjectId("lUOSAzkdZTbA6nKJlN2zjvkEsAOwjOGKHmnhrlbNF3UzMyR3lj");
            appmenus.setMenuIcon("1ix5m5wCQIH6sB4FoWSkovEc3NqDVcvJTHwcGvpe7fyljBtOEK");
            appmenus.setMenuAccessRights(7);
            appmenus.setMenuLabel("j03tQbXnhwRe8USizmSmhzdKuky631WYkGhPqvVmMsU1plhEdS");
            appmenus.setVersionId(1);
            appmenus.setAppId("4QZ70x9fj9dq3RxfkPlgFjw0atzy1RnOabvcJzcRUW5CHA4D2f");
            appmenus.setMenuAction("L5fLB8ff73xLhwwE4lyUUrtCMmTI2Q3R9Sc66EMl9fL7Q0dYQM");
            appmenus.setMenuTreeId("UvfZGppfi0L6kH7XlFXTxe0X4D8Y8bjm99TVKeIxGwvIY6SsZ0");
            appmenus.setMenuCommands("64g8nnrxdAo6MgCDizjbAs0x6AMCwFN6Yl34LmCa1J6uDe0Yu0");
            appmenus.setUiType("1hf");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "VnFI6AGFU9hj0malxIdMdNDqnPrgEzFjvTXfqvkQMQSw1iVgI4vSoioMLaVWHjXjyfdQqiBhU9BiJBMNpSr2XYDM3iReh9FGeooVQtNJaMVaFXRcXPBeES6cIAqK8sPurKsGTS5Wq36L35eRLKKmXIKukg48KyCOdO11nYa6Qd5vfyLWtPqPs4TU7vOh8n5FIx6Ww06QyunpLo0ap9m9VhoxQAMZUkj2dtyEfNpMkOrtwEaJEn0zqXlOs7LKOHVA7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "nAREWflH1ebrl8YZ4RhGF63p3B0rUrK2wXBp0wdoXpnu42cXqBqxvFaYBldpImHwVIK56g9LGhAcN1CgsfPZjUKp7xAy7TNtNC4nAj0K26qXCMFiplyotNVhUIRexGdjwllZKVq77WmzPpOw1kgZ5rSv9ucdHO8iWz4lvowo9kNvPLbtfHONGeON0Ojzv7GX01hCNrxHiMu1emBKo9EChZpUYxOn7wLHjv8XFf6BOA6saRpfkRgXmkyMKOvxbrkMu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "2803m4G9JPmccRM2oX2nxy5oGVnSrreA8E3nzdlPReJMokw7lA5KzSa9CypEQBTduqB8W21ADNOWpcybnVL7kHuWxAM1GLd3kduyKhEDCMOU9KgrNtt9r0mV3JJaDNmdbJk5r6Ly9vlncYUeA6P6jgiZttUJzuHmseE5q2AjQNT8OE9mrJFV21StVPWSaNih03R4NQUNtY5Ud1yoGW4sy2pQux7GANmdeSr3O8YRtwdl7rL7mogXX864n9p1dz0GP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "GAvLJe2xJCCczfcleRlxVW0FJHPxMYZkIV2yIkSOtEkRFXxokiGwAbTMzxZxv1nsL"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "KaXSJFTpTzQVvOjQ048BRRBQKLh4VE6qhVCGUfiWozCeDJrd9zJQrjZgbZqmEZB7XxhlBdpqgn7e2B7p13nvXGzVhG80oRsrFZDlRDRcHrF4R4uiWvpEuRo51usevUt2nTYnsMpjRznqg49sv4ue9s1AUpy1bXd4hTdcbMGhJN9DweDTbwAdwFnL2ORn3NafQY4RfQCdb7zGRZb383cgCFFsnOoe4UdOSK5vOFSiqizRyEwgWliel34BJQlRjRaTs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "ptcK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "LxO24ofSaTnCGZQsijGoDzhubs9aEkVgPWhDhVP75tgnBLJcjrbY4z8JwcxCs3VdqJHiEEQZKpNzFlFLElAfC76nHduTOzf7xGu52RTbKVfsjHJtk0fYIj9W5thC2wobEyPrFSfgR0zO1dgtow5tmQsNEyK5vk8uW04vAFjUh0ded9taIOYsSbA7Ld8eiXKQSHhccpt2ns2tdDpCBQ8wukT8P1BIJdLRC0yfwpyWj88XaZhWUHAD2J7LlS3ddoyOv"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 18));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "oGfwdhF1bnqflvuZxk9ZOS0EmuTfesgeGDYGkIwBfUZPd659NVldkX6q6tRYRdDLSaO4VgDYS7YWVTW7KFSIfDnjzAIwz8QlOQJvUVZbhUrUrsHFbaxFOw0MmpYDvmNdFGvpqrOzyf1Ha9H6z0HED22AJm1L5OtH1FWqJ8QEbkBdSpkFdgUo5U9WLNiNUn3ap5wdX8PbQBfVSQYg8MWv0QCuorcO0wfl1FIYoY9Bjw1CQtKG9VHjsvLZl2eLu0zA6"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
