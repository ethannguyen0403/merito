package membersite.testcases.fair999;

import com.paltech.utils.StringUtils;
import common.MemberConstants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.tabexchange.HomePage;
import baseTest.BaseCaseMerito;

import java.util.List;

public class LoginTest extends BaseCaseMerito {
    /**
     * @title: Validate that user can sign in successfully
     * @steps:   1. Navigate to login page
     *           2. Click Login button and confirm more than 18 years old
     *           3. Log in with a valid username and password
     * @expect: 1. Exchange Tab is active by default
     *          2. Home page is displayed
     */
    @Parameters({"username", "password"})
    @Test (groups = {"smoke"})
    public void FE_Login_001(String username, String password) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Step 3: Log in with a valid username and password");
        memberHomePage = loginPopup.login(username, StringUtils.decrypt(password),true);

        log("Step: Close the popup if displayed");
        memberHomePage.closeBannerPopup();

        log("Verify: Exchange Tab is active by default");
        log("Verify: Home page is displayed");
        memberHomePage.waitMenuLoading();
        Assert.assertTrue(memberHomePage.satHeaderControl.ddbMyAccount.isDisplayed(5), "ERROR: ddbMyAccount doesn't display after signed in");
        Assert.assertTrue(memberHomePage.tabExchange.isDisplayed(5), "ERROR: tabExchange doesn't display after signed in");
        Assert.assertTrue(memberHomePage.tabExchangeGames.isDisplayed(5), "ERROR: tabExchangeGame doesn't display after signed in");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is no http request error after login
     * @steps:   1. Login the site
     * @expect:  1. There is no http requests error
     */
    @Test (groups = {"http_request"})
    @Parameters({"username", "password"})
    public void FE_Login_008(String username, String password) throws Exception {
        log("@title: Validate that there is no http request error after login");
        log("Step 1: Login the site ");
        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Step 3: Log in with a valid username and password");
        HomePage page = loginPopup.login(username, StringUtils.decrypt(password),true);

        boolean isError = hasHTTPRespondedOK();
        log( "Verify: There is no http requests error");
        Assert.assertTrue(isError, "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user with invalid account cannot sign in successfully
     * @steps:   1. Navigate to login page
     *           2. Click Login button and confirm more than 18 years old
     *           3. Log in with an invalid username and correct password
     * @expect:  1. An login popup is displayed
     *           2. The error message is 'Invalid Username OR Password.'
     *           3. Login button still displays
     */

    @Test (groups = {"smoke"})
    public void FE_Login_002(){
        log("@title: Validate that user with invalid account cannot sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Step 3: Log in with a valid username and invalid password");
        loginPopup.login(StringUtils.generateString("test",5), StringUtils.generatePassword(10),true);

        String errorMessage = loginPopup.lblErrorMessage.getText();

        log("Verify 1: An error login popup is displayed");
        log("Verify 2: btnLogin still displays");
        log(String.format("Verify 3: The content message is '%s'", MemberConstants.LoginPage.MSG_LOGIN_FAIL));
        Assert.assertTrue(loginPopup.popupLogin.isDisplayed(), "ERROR: popupLogin doesn't display after signed in failed");
        Assert.assertEquals(errorMessage, MemberConstants.LoginPage.MSG_LOGIN_FAIL, String.format("ERROR: The expected error message is '%s' but found '%s'", MemberConstants.LoginPage.MSG_LOGIN_FAIL, errorMessage));
        Assert.assertTrue(memberHomePage.btnLogin.isDisplayedShort(3), "ERROR: btnLogin doesn't display after signed in with an invalid password");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user with invalid password cannot sign in successfully
     * @steps:   1. Navigate to login page
     *           2. Click Login button and confirm more than 18 years old
     *           3. Log in with an invalid username and invalid password
     * @expect:  1. An login popup is displayed
     *           2. The error message is 'Invalid Username OR Password.'
     *           3. Login button still displays
     */
    @Parameters({"username"})
    @Test (groups = {"smoke"})
    public void FE_Login_003(String username){
        log("@title: Validate that user with invalid account cannot sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Step 3: Log in with a valid username and invalid password");
        loginPopup.login(username, StringUtils.generatePassword(10),true);

        String errorMessage = loginPopup.lblErrorMessage.getText();

        log("Verify 1: An error login popup is displayed");
        log("Verify 2: btnLogin still displays");
        log(String.format("Verify 3: The content message is '%s'", MemberConstants.LoginPage.MSG_LOGIN_FAIL));
        Assert.assertTrue(loginPopup.popupLogin.isDisplayed(), "ERROR: popupLogin doesn't display after signed in failed");
        Assert.assertEquals(errorMessage, MemberConstants.LoginPage.MSG_LOGIN_FAIL, String.format("ERROR: The expected error message is '%s' but found '%s'", MemberConstants.LoginPage.MSG_LOGIN_FAIL, errorMessage));
        Assert.assertTrue(memberHomePage.btnLogin.isDisplayedShort(3), "ERROR: btnLogin doesn't display after signed in with an invalid password");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI of  Underage gambling popup
     * @steps:  1. Access member site
     *          2. Click on Login button
     * @expect: 1. Message" Underage gambling is prohibited.
     *          Please confirm if you are 18 years old and above as of today."
     *          2. Confirm and Exit button display
     */

    @Test (groups = {"smoke"})
    public void FE_Login_004(){
        log("@title: Validate UI of Underage gambling popup");
        log("Step 1. Access member site");
        log("Step 2. Click on Login button");
        LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Verify 1. Verify Login UI ");
        Assert.assertTrue(loginPopup.txtUsername.isDisplayed(),"ERROR! Username textbox is not displayed");
        Assert.assertTrue(loginPopup.txtUsername.isDisplayed(), "ERROR! Password textbox is not displayed");
        Assert.assertEquals(loginPopup.btnLogin.getText(),"Login","Login button is not displayed");
        Assert.assertEquals(loginPopup.btnExit.getText(),"Exit","Exit button is not displayed");
      log("INFO: Executed completely");
    }
    /**
     * @title: Validate that user can sign in successfully
     * @steps:   1. Navigate to login page
     *           2. Click Login button and confirm more than 18 years old
     *           3. Log in with a valid username and password
     * @expect: 1. Exchange Tab is active by default
     *          2. Home page is displayed
     */
    @Parameters({"username", "password","skinName"})
    @Test (groups = {"smoke_demo"})
    public void FE_Login_001_Demo(String username, String password,String skinName) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Step 3: Log in with a valid username and password");
        memberHomePage= loginPopup.login(username, StringUtils.decrypt(password),true);

        log("Step: Close the popup if displayed");
        memberHomePage.closeBannerPopup();
        memberHomePage.satHeaderControl.ddbMyAccount.click();
        List<String> lst =memberHomePage.getAccountMenu(skinName);

        log("Verify 1: My Account drop-downbox displayed");
        Assert.assertTrue(memberHomePage.satHeaderControl.ddbMyAccount.isDisplayed(),"Failed! my account dropdown is not display");

        Assert.assertEquals(lst.get(1),username,"Failed! username is incorrectly displayed");
        Assert.assertEquals(lst.get(0), MemberConstants.Header.My_ACCOUNT,String.format("ERROR: Expected login account is %s but found %s", MemberConstants.Header.My_ACCOUNT,lst.get(0)));

        log("Verify 2: Login account in My Account dropdown display corrected:" + lst.get(1));
        Assert.assertEquals(lst.get(1),username,String.format("ERROR: Expected login account is %s but found %s", username,lst.get(1)));

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate that user can sign in successfully
     * @steps:   1. Navigate to login page
     *           2. Click Login button and confirm more than 18 years old
     *           3. Log in with a valid username and password
     * @expect: 1. Exchange Tab is active by default
     *          2. Home page is displayed
     */
    @Parameters({"username", "password","skinName"})
    @Test (groups = {"smoke_demo"})
    public void FE_Login_001_Demo_FAILED(String username, String password,String skinName) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Step 3: Log in with a valid username and password");
        memberHomePage = loginPopup.login(username, StringUtils.decrypt(password),true);

        log("Step: Close the popup if displayed");
        memberHomePage.closeBannerPopup();
        memberHomePage.satHeaderControl.ddbMyAccount.click();
        List<String> lst = memberHomePage.getAccountMenu(skinName);

        log("Verify 1: My Account drop-downbox displayed");
        Assert.assertEquals(lst.get(0), MemberConstants.Header.My_ACCOUNT, String.format("ERROR: Expected login account is %s but found %s", MemberConstants.Header.My_ACCOUNT, lst.get(0)));

        log("Verify 2: Login account in My Account dropdown display corrected:" + lst.get(1));
        Assert.assertEquals(lst.get(1), "VP FAILED", String.format("FAILED!: Expected login account is %s but found %s","VP FAILED", lst.get(1)));

        log("INFO: Executed completely");
    }
}
