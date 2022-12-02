package baseTest;

import agentsite.pages.all.ChangePasswordPage;
import agentsite.pages.all.home.SecurityCodePage;
import com.paltech.constant.Helper;
import com.paltech.driver.DriverManager;
import com.paltech.driver.DriverProperties;
import com.paltech.utils.DateUtils;
import com.paltech.utils.ScreenShotUtils;
import com.paltech.utils.StringUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import membersite.pages.all.home.LandingPage;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.HarEntry;
import objects.Environment;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import util.testraildemo.APIClient;
import util.testraildemo.APIException;
import util.testraildemo.TestRails;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static common.MeritoConstant.*;

public class BaseCaseMerito {
    private static ApplicationContext context;
    public static DriverProperties driverProperties;
    public static Environment environment;
    //public static WebService webService = new WebService();
  //  public static AgentBlockingManagement agentBlockingManagementURL = new AgentBlockingManagement();
    public static ExtentTest logger;
    public static ExtentReports report;
    public static agentsite.pages.all.home.LoginPage agentLoginPage;
    public static backoffice.pages.bo.home.HomePage backofficeHomePage;
    public static agentsite.pages.all.home.HomePage agentHomePage;
    public static membersite.pages.all.tabexchange.HomePage memberHomePage;
    public static membersite.pages.all.home.ChangePasswordPage changePasswordPage;
    public static membersite.pages.all.home.LandingPage landingPage;
    public static BrowserMobProxy browserMobProxy;
    public static String domainURL;
    public static String memberLoginURL;
    public static String memberSOSUrl;
    public static String agentLoginURL;
    public static String sosAgentURL;
    public static String sosValidationAgentURL;
    public static String agentSecurityCodeURL;
    public static String agentNewAccURL;
    public static String backofficeUrl;
    public static String backofficeSOSUrl;
    public static String backofficeDashboardUrl;
    public static String userCurrency;
    public static String memberMarketServiceURL;
    public static String _brandname;
    public static String PROJECT_ID="1";
    public static APIClient client;
    private static boolean isAddTestRailResult = true;
    private static  List<Long> lstCases= new ArrayList<>();

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite(ITestContext ctx) throws APIException, IOException {
        try{
            context = new ClassPathXmlApplicationContext("resources/settings/Setting.xml");
            report = new ExtentReports("", true);
        } catch(Exception ex) {
            throw new NullPointerException(String.format("ERROR: Exception occurs beforeSuite by '%s'", ex.getMessage()));
        }
        // Create Test Run in Test Rails
        ctx.getName();
        if(isAddTestRailResult) {
            System.out.println("Add New Test Run in TestRails");
            client = new APIClient("https://paltech.testrail.io/");
            client.setUser("isabella.huynh@pal.net.vn");
            client.setPassword("P@l332211");
            Map data = new HashMap();
            //data.put("suite_id",true);
            data.put("include_all", false);
            data.put("name", "Test Run of suite " + ctx.getName() + " on" + DateUtils.getDateFollowingGMT("GMT+7", "dd-MM-YYYY hh:mm:ss"));
            // data.put("milestone_id",1);

            JSONObject c = (JSONObject) client.sendPost("add_run/" + PROJECT_ID, data);
            c.get("id");
            Long suite_id = (Long) c.get("id");
            ctx.setAttribute("suiteId", suite_id);
        }
    }

    @Parameters({"browser", "env", "language","brandname"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String browser, String env, String language,String brandname) {
        System.out.println("BrandName at Before Class"+ brandname);
        _brandname = brandname;
        try{
            environment = (Environment) context.getBean(env);
            driverProperties = (DriverProperties) context.getBean(browser);
            memberMarketServiceURL = defineMemberService(brandname);

            domainURL = defineURL(brandname,"");
           // Define Member Site URL
            memberLoginURL = defineURL(brandname,MEMBER_URL_SUFFIX.get(brandname));
            memberSOSUrl = defineURL(brandname,MEMBER_SOS_URL_SUFFIX);

            // define Agent site URLs
            agentLoginURL = defineURL(brandname,"/agent");
            sosAgentURL = defineURL(brandname,AGENT_SOS_URL_SUFFIX);
            sosValidationAgentURL = defineURL(brandname,AGENT_SOS_BY_PASS_CAPTCHA_URL_SUFFIX);
            agentSecurityCodeURL = defineURL(brandname, AGENT_SECURITY_CODE_URL_SUFFIX.get(brandname));
            agentNewAccURL = defineURL(brandname,LOGIN_NEW_ACC_AGENT_URL_SUFFIX.get(brandname));

            // define Backoffice url: Login url, loginByPassCaptcha URL, Dashboard url
            backofficeUrl = environment.getBackofficeURL();
            backofficeSOSUrl = String.format("%s%s",environment.getBackofficeURL(),BACKOFFICE_SOS_URL);
            backofficeDashboardUrl = String.format("%s%s",environment.getBackofficeURL(),BACKOFFICE_DASHBOARD_URL);
        } catch(Exception ex) {
            throw new NullPointerException(String.format("ERROR: Exception occurs beforeClass by '%s'", ex.getMessage()));
        }

    }

    @Parameters({"appname","username", "password", "language", "isLogin", "isProxy", "isThrown", "currency",})
    @BeforeMethod(alwaysRun = true)
    public static void beforeMethod(String appname,String username, String password, String language, boolean isLogin, boolean isProxy, boolean isThrown, String currency, Method method, ITestResult result,ITestContext ctx) throws Exception {
        System.out.println("*** Map test case in script with test case in TestRail ***");
        if(isAddTestRailResult){
            Method m = method;
            if (m.isAnnotationPresent(TestRails.class)) {
                TestRails ta = m.getAnnotation(TestRails.class);
                String caseId = ta.id();
                ctx.setAttribute("caseId",caseId);

            }
        }
        System.out.println("***************************Beginning TC's " + method.getName() +"*******************************");
        logger = report.startTest(method.getName(), method.getClass().getName());
        driverProperties.setMethodName(method.getName());
        driverProperties.setIsProxy(isProxy);
        switch (appname){
            case "agentsite":
            loginAgent(username,password,isLogin);
            break;
            case "membersite":
              loginMember(username,password,isLogin,language,currency,isThrown);
              break;
            default:
               loginBackoffice(username,password,isLogin);
               break;
        }
        if (isProxy){
            browserMobProxy = driverProperties.getBrowserMobProxy();
        }
    }

    public static void loginAgent(String username, String password,boolean isLogin) throws Exception {
      createDriver(agentLoginURL);
      if(isLogin)
      {
          agentHomePage = loginAgent(sosAgentURL,agentSecurityCodeURL,username,password,environment.getSecurityCode());
      }
      else {
          agentLoginPage = new agentsite.pages.all.home.LoginPage();
      }
  }
    public static void loginMember(String username, String password,boolean isLogin, String language,String currency,boolean isThrown) throws Exception {
        createDriver(memberLoginURL);
        if (isLogin){
            Helper.loginFairExchange(memberSOSUrl,memberLoginURL, username, password, isThrown);
            userCurrency = currency;
            memberHomePage = new membersite.pages.all.tabexchange.HomePage();
            memberHomePage.imgSpinner.isDisplayed(3);
            if(memberHomePage.isPopupBannerDisplay())
            {
                System.out.println("$$$$$$$$$Closed Banner Popup after login %%%%%%%%%%");
                memberHomePage.closeBannerPopup();
            }
            if(!language.isEmpty()){
                memberHomePage.apHeaderControl.selectLanguage(language);
                memberHomePage.apLeftMenuControl.isDisplayed(5);
            }

        } else {
            landingPage = new LandingPage();
        }
    }
    public static membersite.pages.all.tabexchange.HomePage loginMember(String username, String password) throws Exception {
         loginMember( username, password,true,"","",true);
         return memberHomePage;
    }
    public static void loginBackoffice(String username, String password,boolean isLogin) throws Exception {
        createDriver(backofficeUrl);
        if(isLogin) {
            Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl, username, password, true);
            backofficeHomePage = new backoffice.pages.bo.home.HomePage();
            if(!backofficeHomePage.smProductMaintenance.isDisplayed(1)) {
                backofficeHomePage.refresh();
                if (!backofficeHomePage.btnLogOut.isDisplayed(5)) {
                    Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl, username, password, true);
                }
            }
        }/*else {

                }*/
    }
    public static SecurityCodePage loginAgentWithoutSecurityCode(String sosURL, String securityCodeUrl, String username, String password) throws Exception {
        Helper.loginAgentIgnoreCaptchaTest(sosURL, securityCodeUrl, username, password);
        SecurityCodePage securityPage = new SecurityCodePage();
        securityPage.waitingLoadingSpinner();
        return securityPage;
    }
    public static agentsite.pages.all.home.HomePage loginAgent(String sosURL, String securityCodeUrl, String username, String password, String securityCode) throws Exception {
        Helper.loginAgentIgnoreCaptchaTest(sosURL, securityCodeUrl, username, password);
        SecurityCodePage securityCodePage = new SecurityCodePage();
        securityCodePage.setSecurityCode(StringUtils.decrypt(securityCode), StringUtils.decrypt(securityCode));
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        changePasswordPage.changePassword(StringUtils.decrypt(password), StringUtils.decrypt(password), StringUtils.decrypt(password));
        agentHomePage = new agentsite.pages.all.home.HomePage();
        // Work arround after login show blank page
        if (!agentHomePage.btnSignOut.isDisplayed()) {
            DriverManager.getDriver().refresh();
            agentHomePage.waitingLoadingSpinner();
        }
        return agentHomePage;
    }
     // this function for login new agent account
    public static void loginNewAccount(String sosURL, String loginURL,String username,String password, String securityCode)throws Exception{
        Helper.loginAgentIgnoreCaptchaTest(sosURL,loginURL, username,password);
        SecurityCodePage securityPage = new SecurityCodePage();
        securityPage.setSecurityCode(securityCode,securityCode);
        ChangePasswordPage changePWPage = new ChangePasswordPage();
        String psDecrypt = StringUtils.decrypt(password);
        changePWPage.changePassword(psDecrypt,psDecrypt,psDecrypt);
        agentHomePage = new agentsite.pages.all.home.HomePage();
    }

    @AfterMethod(alwaysRun = true)
    public static void afterMethod(ITestResult result,ITestContext ctx) throws APIException, IOException {
        String testResult = "PASSED";
        if(!result.isSuccess()) {
            String srcBase64 = ScreenShotUtils.captureScreenshotWithBase64(DriverManager.getDriver().getWebDriver());
            result.setAttribute(result.getMethod().getMethodName(), srcBase64);
            testResult = "FAILED";
            System.err.println(result.getThrowable().getMessage());
        }
        if (driverProperties.isProxy()){
            log("INFO: Quitting BrowserMobProxy's port is " + browserMobProxy.getPort());
            browserMobProxy.stop();
        }
        DriverManager.quitAll();
        System.out.println("****************************Ending TC's name: " + result.getMethod().getMethodName() + " is " + testResult + " **********************");
        System.out.println("*** Add result to TestRails");
        if(isAddTestRailResult) {
            String caseId = (String) ctx.getAttribute("caseId");
            Long suiteId = (Long) ctx.getAttribute("suiteId");
            Map data1 = new HashMap();
            // add test case for test run
            lstCases.add(Long.parseLong(caseId));
            data1.put("case_ids",lstCases);
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
        client.sendPost("close_run/" + ctx.getAttribute("suiteId"),null);
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

    protected boolean hasHTTPRespondedOK(){
        browserMobProxy.waitForQuiescence(1, 5, TimeUnit.SECONDS);
        List<HarEntry> entries = browserMobProxy.getHar().getLog().getEntries();
        for (HarEntry entry : entries) {
            // skip 423 status due to sending a request in too short time
            // skip 429 status due to a user's sending too many requests
            if(entry.getResponse().getStatus() >= 400 && entry.getResponse().getStatus() != 423
                    && entry.getResponse().getStatus() != 429
                    && !entry.getRequest().getUrl().contains("aws.cloud.es.io/intake/v2/rum/events")
                    && !entry.getRequest().getUrl().contains("mewebsocket/mews")) {
                log(String.format("ERROR URL: %s - STATUS CODE: %s", entry.getRequest().getUrl(), entry.getResponse().getStatus()));
                return false;
            }
        }
        return true;
    }

    /**********************
     * Private methods
     *******************/
    private static void createDriver(String url) throws MalformedURLException, UnexpectedException {
        int count = 3;
        DriverManager.quitAll();
        while (count-- > 0){
            DriverManager.createWebDriver(driverProperties);
            DriverManager.getDriver().setLoadingTimeOut(100);
            DriverManager.getDriver().maximize();
            if (DriverManager.getDriver().getToAvoidTimeOut(url) || count==0) {
                log(String.format("RUNNING under the link %s", url));
                log(String.format("DEBUG: CREATED DRIVER SUCCESSFULLY with COUNT %s and Map Size %s", count, DriverManager.driverMap.size()));
                System.out.print(String.format("Width x Height is %sx%s", DriverManager.getDriver().getWidth(), DriverManager.getDriver().getHeight()));
                break;
            } else {
                log("DEBUG: QUIT BROWSER DUE TO NOT CONNECTED");
                DriverManager.quitAll();
            }
        }
    }

    public static String defineURL(String brandName,String suffix){
        return String.format("%s%s",getURL(brandName), suffix);
    }
    private static String defineMemberService(String brandName){
        switch (brandName) {
            case "satsport":
                return String.format("%s%s", getURL(brandName), "/market-service");
            default:
                return String.format("%s%s", getURL(brandName), "/market-service");
        }
    }

    private static String defineMemberSiteURL(String brandName){
        return String.format("%s%s",getURL(brandName), MEMBER_URL_SUFFIX.get(brandName));
    }
    private static String defineAgentSecurityCodeURL(String brandName){
        return String.format("%s%s",getURL(brandName), AGENT_SECURITY_CODE_URL_SUFFIX.get(brandName));
    }
    public static String defineAgentSiteURL(String brandName){
        return  String.format("%s/agent",getURL(brandName));
    }
    private static String getURL(String brandName){
        switch (brandName){
            case "fairexchange":
                return environment.getFairURL();
            case "satsport":
                return environment.getSatURL();
            case "funsport":
                return environment.getFunsportURL();
            case "fairenter":
                return environment.getFairenterURL() ;
            case "laser365":
                return environment.getLaser365URL();
          /*  case "backoffice":
                return String.format("%s%s",environment.getBackofficeDomain(), MEMBER_URL_SUFFIX.get(brandName));*/
            default:
                return "";
        }
    }

}


