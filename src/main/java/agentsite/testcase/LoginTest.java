package agentsite.testcase;

import agentsite.pages.LoginPage;
import agentsite.pages.SecurityCodePage;
import agentsite.pages.components.ConfirmPopup;
import baseTest.BaseCaseTest;
import com.paltech.constant.Helper;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class LoginTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @steps: 1. Log in with a valid username and password
     * @expect: 1. Home page is displayed
     */
    @TestRails(id = "670")
    @Test(groups = {"http_request"})
    public void Agent_Login_670() {
        log("@title: There is no http responded error returned");
        log("Step 1: Log in with a valid username and password");
        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can sign in successfully
     * @steps: 1. Log in with a valid information(username, password, captcha, security code)
     * @expect: 1. Home page is displayed
     */
    @TestRails(id = "671")
    @Test(groups = {"smoke_newui", "smoke_dev"})
    @Parameters({"username"})
    public void Agent_Login_671(String username) {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Log in with a valid information(username, password, captcha, security code)");
        log("Verify 1: Logout button is displayed");
        Assert.assertTrue(agentHomePage.header.isSignOutDisplay(), "ERROR: Logout button is not displayed");

        log("Verify 2: Home page is displayed");
        Assert.assertEquals(agentHomePage.leftMenu.lblLoginID.getText(), username, "Failed!, Login ID label display incorrect");
        Assert.assertEquals(agentHomePage.leftMenu.lblWelcome.getText(), AGConstant.HomePage.WELCOME_BACK, "Failed!, Welcome Back label display incorrect");
        log("INFO: Executed completely");
    }


    @TestRails(id = "3448")
    @Test(groups = {"regression"})
    @Parameters("password")
    public void Agent_Login_3448(String password) throws Exception {
        log("@title: Validate cannot login to agent if input incorrect username and password - New UI");
        log("Step 1. Access Agent site login page");
        log("Step 2. Enter incorrect username/password");
        log("Step3. Input valid captcha then click on Login button");
        Helper.loginAgentIgnoreCaptchaTest(sosAgentURL, agentSecurityCodeURL, "invalid.LoginID", password);
        LoginPage loginPage = new LoginPage(_brandname);

        log("Verify 1. Verify cannot login by pass api when input incorrect username and password");
        Assert.assertEquals(loginPage.lblLogin.getText().toLowerCase(), "login", "FAILED! Login Lable is incorrect");
        Assert.assertTrue(loginPage.txtUsername.isDisplayed(), "FAILED! Login Username textbox is not displayed");
        Assert.assertTrue(loginPage.txtPassword.isDisplayed(), "FAILED! Login Password textbox is not displayed");
        Assert.assertEquals(loginPage.btnLogIn.getText(), "Login", "FAILED! Login label is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3449")
    @Test(groups = {"regression"})
    @Parameters({"username", "password"})
    public void Agent_Login_3449(String username, String password) throws Exception {
        log("@title: Validate cannot login to agent if input incorrect captcha");
        String invalidCaptcha = "0010";
        log("Step 1. Access Agent site login page");
        log("Step 2. Enter valid username/password");
        log("Step 3. Input invalid captcha then click on Login button");
        Helper.loginAgentIgnoreCaptchaTest(sosAgentURL, agentSecurityCodeURL, "invalid.LoginID", password);
        LoginPage loginPage = new LoginPage(_brandname);
        ConfirmPopup popup = loginPage.loginWitInvalidInfo(username, StringUtils.decrypt(password), invalidCaptcha, true);

        log("Verify 1.Verify cannot login agent site. Message \"Invalid captcha.\" displayed");
        String errorMessage = popup.getContentMessage();
        Assert.assertEquals(errorMessage, "Invalid captcha.", "FAILED! Incorrect message when login with invalid captcha");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3450")
    @Test(groups = {"regression"})
    @Parameters({"username", "password"})
    public void Agent_Login_3450(String username, String password) throws Exception {
        log("@title: Validate Security Code display when login with valid username + password + captcha");
        log("Step 1. Access Agent site login page");
        log("Step 2. Enter valid username/password + captcha and click Login button");
        SecurityCodePage securityCodePage = loginAgentWithoutSecurityCode(sosAgentURL, agentSecurityCodeURL, username, password);

        log("Verify 1. Verify Security code display");
        Assert.assertTrue(securityCodePage.lblTitle.getText().equalsIgnoreCase("Security Code"), "FAILED! Security Code Title is incorrect display");
        Assert.assertTrue(securityCodePage.txtSecurityCode.isDisplayed(), "FAILED! Security Code textbox is incorrect display");
        Assert.assertEquals(securityCodePage.btnSubmit.getText(), "Submit", "FAILED! Submit button is incorrect display");
        Assert.assertEquals(securityCodePage.btnBackToLoginPage.getText(), "Back To Login Page", "FAILED! Back To Login Page button is incorrect display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3451")
    @Test(groups = {"regression"})
    @Parameters({"username", "password"})
    public void Agent_Login_3451(String username, String password) throws Exception {
        log("@title: Validate Cannot login if input invalid security code");
        String invalidSecurity = "0021";
        log("Step 1. Access Agent site login page");
        log("Step 2. Enter valid username/password + captcha and click Login button");
        SecurityCodePage securityCodePage = loginAgentWithoutSecurityCode(sosAgentURL, agentSecurityCodeURL, username, password);

        log("Step 3. Input invalid security code and click on Submit");
        ConfirmPopup popup = securityCodePage.setInvalidSecurityCode(invalidSecurity);

        log("Verify 1.Verify cannot login agent site. Message \"Security Code is not correct.\" displayed");
        String errorMessage = popup.getContentMessage();
        Assert.assertEquals(errorMessage, "Security Code is not correct.", "FAILED! Incorrect message when login with invalid Security Code");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3452")
    @Test(groups = {"regression"})
    @Parameters({"username", "password"})
    public void Agent_Login_3452(String username, String password) throws Exception {
        log("@title: Validate Back to Login Page works");
        log("Step 1. Access Agent site login page");
        log("Step 2. Enter valid username/password + captcha and click Login button");
        SecurityCodePage securityCodePage = loginAgentWithoutSecurityCode(sosAgentURL, agentSecurityCodeURL, username, password);

        log("Step 3. Click on Back To Login Page on Security Code popup");
        securityCodePage.clickBackBtn();
        LoginPage loginPage = new LoginPage(_brandname);

        log("Verify 1. Verify Login form display");
        Assert.assertEquals(loginPage.lblLogin.getText().toLowerCase(), "login", "FAILED! Login Lable is incorrect");
        Assert.assertTrue(loginPage.txtUsername.isDisplayed(), "FAILED! Login label is incorrect");
        Assert.assertTrue(loginPage.txtPassword.isDisplayed(), "FAILED! Login label is incorrect");
        Assert.assertEquals(loginPage.btnLogIn.getText(), "Login", "FAILED! Login label is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3454")
    @Parameters({"username", "password"})
    public void Agent_Login_3454(String username, String password) throws Exception {
            //TODO: implement this case
        log("@title: Validate menu list item display correctly");
        log("INFO: Executed completely");
    }


    /**
     * @title: Validate that user can sign in successfully
     * @steps: 1. Log in with a valid information(username, password, captcha, security code)
     * @expect: 1. Home page is displayed
     */
//    @Test(groups = {"smoke_sat"})
//    public void Agent_Login_Old_UI_002() {
//        log("@title: Validate that CONFIGURE OTP display after login");
//        log("Verify 1: Check CONFIGURE OTP label displays");
//        Assert.assertEquals(agentHomePage.leftMenu.getConfigureOTP(), AGConstant.HomePage.CONFIGURE_OTP, "Failed!, My Account button not displayed");
//        log("INFO: Executed completely");
//    }

}
