package api.testcases;

import api.utils.testraildemo.APIClient;
import api.utils.testraildemo.APIException;
import api.utils.testraildemo.TestRails;
import com.paltech.driver.DriverProperties;
import com.paltech.utils.DateUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import objects.Environment;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCaseAPI {
    public static DriverProperties driverProperties;
    public static Environment environment;
    public static ExtentTest logger;
    public static ExtentReports report;
    public static String loginURL;
    public static String PROJECT_ID = "1";
    public static APIClient client;
    public static boolean isAddHeader = true;
    private static ApplicationContext context;
    private static boolean isAddTestRailResult = false;
    private static List<Long> lstCases = new ArrayList<>();

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite(ITestContext ctx) throws IOException, APIException {
        try {
            context = new ClassPathXmlApplicationContext("resources/settings/Setting.xml");
            report = new ExtentReports("", true);
        } catch (Exception ex) {
            throw new NullPointerException(String.format("ERROR: Exception occurs beforeSuite by '%s'", ex.getMessage()));
        }
        ctx.getName();
        if (isAddTestRailResult) {
            System.out.println("Add New Test Run in TestRails");
            client = new APIClient("https://paltech.testrail.io");
            client.setUser("tim.dang@pal.net.vn");
            client.setPassword("P@l332211");
            Map data = new HashMap();
            //data.put("suite_id",true);
            data.put("include_all", false);
            data.put("name", "Test Run of suite " + ctx.getName() + " on " + DateUtils.getDateFollowingGMT("GMT+7", "dd-MM-YYYY hh:mm:ss"));

            JSONObject c = (JSONObject) client.sendPost("add_run/" + PROJECT_ID, data);
            c.get("id");
            Long suite_id = (Long) c.get("id");
            ctx.setAttribute("suiteId", suite_id);
        }
    }

    @Parameters({"username", "password", "language", "isLogin", "isProxy", "isThrown", "currency"})
    @BeforeMethod(alwaysRun = true)
    public static void beforeMethod(String username, String password, String language, boolean isLogin, boolean isProxy, boolean isThrown, String currency, Method method, ITestResult result, ITestContext ctx) throws Exception {
        System.out.println("*** Map test case in script with test case in TestRail ***");
        if (isAddTestRailResult) {
            Method m = method;
            if (m.isAnnotationPresent(TestRails.class)) {
                TestRails ta = m.getAnnotation(TestRails.class);
                String caseId = ta.id();
                ctx.setAttribute("caseId", caseId);

            }
        }
        System.out.println("***************************Beginning TC's " + method.getName() + "*******************************");
        logger = report.startTest(method.getName(), method.getClass().getName());
        driverProperties.setMethodName(method.getName());
        driverProperties.setIsProxy(isProxy);
    }

    @AfterMethod(alwaysRun = true)
    public static void afterMethod(ITestResult result, ITestContext ctx) throws APIException, IOException {
        String testResult = "PASSED";
        if (!result.isSuccess()) {
            testResult = "FAILED";
            System.err.println(result.getThrowable().getMessage());
        }
        System.out.println("****************************Ending TC's name: " + result.getMethod().getMethodName() + " is " + testResult + " **********************");
        if (isAddTestRailResult) {
            String caseId = (String) ctx.getAttribute("caseId");
            Long suiteId = (Long) ctx.getAttribute("suiteId");
            Map data1 = new HashMap();
            // add test case for test run
            lstCases.add(Long.parseLong(caseId));
            data1.put("case_ids", lstCases);
            client.sendPost("update_run/" + suiteId, data1);
            //end add test case for test run
            //start add result for a test case
            Map data = new HashMap();
            if (result.isSuccess()) {
                data.put("status_id", 1);
            } else {
                data.put("status_id", 5);
                data.put("comment", result.getThrowable().toString());
            }
            client.sendPost("add_result_for_case/" + suiteId + "/" + caseId, data);
            //End add result for a test case
            System.out.println("******** Done Add Result in Test Run in Testrail *********");
        }
    }

    @AfterSuite
    public static void tearDownSuite(ITestContext ctx) throws APIException, IOException {
        client.sendPost("close_run/" + ctx.getAttribute("suiteId"), null);
        report.endTest(logger);
        report.flush();
        report.close();
    }

    public static String log(String message) {
        logger.log(LogStatus.INFO, message);//For extentTest HTML report
        System.out.println(message);
        Reporter.log(message);
        return message;
    }

    public static void logBug(String message) {
        logger.log(LogStatus.ERROR, message);
        System.err.println(message);
        Reporter.log(message);
    }

    @Parameters({"browser", "env", "language"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String browser, String env, String language) {
        try {
            environment = (Environment) context.getBean(env);
            // webService.setWebServiceURL(environment.getWsURL());
            driverProperties = (DriverProperties) context.getBean(browser);
            //   loginURL = String.format("%s", environment.getLoginURL());
            System.out.println(String.format("RUNNING ON %s under the link %s", env.toUpperCase(), loginURL));
        } catch (Exception ex) {
            throw new NullPointerException(String.format("ERROR: Exception occurs beforeClass by '%s'", ex.getMessage()));
        }
    }

}
