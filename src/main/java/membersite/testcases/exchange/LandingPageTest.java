package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.pages.HomePage;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class LandingPageTest extends BaseCaseTest {

    @TestRails(id = "1213")
    @Test(groups = {"smoke","smoke_dev"})
    @Parameters({"username", "password"})
    public void Landing_Page_TC1213(String username, String password) throws Exception {
        log("@title:Validate that user can sign in successfully");
        log("@Step 1 Navigate to  login page");
        log("@Step 2 Click login button to open login popup or underage gambling popup");
        log("@Step 3 Log in with a valid username and password");
        String passwordDecrypt = StringUtils.decrypt(password);
        HomePage homePage = landingPage.login(username, passwordDecrypt, true);

        log("@Verify 1  Home page display with My account dropdown box");
        Assert.assertTrue(homePage.isMyAccountDisplay(), "Failed! My Account does not display after login");

        log("@Verify 2 in My Account dropdown box display Username correctly");
        Assert.assertTrue(homePage.isMyAccountContains(username), "Failed! My Account does not display after login");

        log("INFO: Executed completely");

    }


    @TestRails(id = "1214")
    @Test(groups = {"smoke"})
    @Parameters({"password"})
    public void Landing_Page_TC1214(String password) throws Exception {
        log("@title: Validate that user with invalid account cannot sign in successfully");
        log("Step 1: Navigate to login page");
        log("Step 2: Click Login button and confirm more than 18 years old");
        log("Step 3: Log in with an invalid username and correct password");
        String errorMessage = landingPage.loginInvalid("inavlid", StringUtils.decrypt(password));

        log("Verify 1: Verity the error message is display");
        Assert.assertEquals(errorMessage, MemberConstants.LoginPage.MSG_LOGIN_FAIL, String.format("ERROR: The expected error message is '%s' but found '%s'", MemberConstants.LoginPage.MSG_LOGIN_FAIL, errorMessage));

        log("INFO: Executed completely");
    }

    @TestRails(id = "1215")
    @Test(groups = {"smoke","smoke_123"})
    @Parameters({"username"})
    public void Landing_Page_TC1215(String username) {
        log("@title: Validate that user cannot login with incorrect username");
        log("Step 1: Navigate to login page");
        log("Step 2: Click Login button and confirm more than 18 years old");
        log("Step 3: 3 Log in with an valid username and invalid password");
        String errorMessage = landingPage.loginInvalid(username, "invalid");

        log("Verify 1: Verity the error message is display");
        Assert.assertEquals(errorMessage, MemberConstants.LoginPage.MSG_LOGIN_FAIL, String.format("ERROR: The expected error message is '%s' but found '%s'", MemberConstants.LoginPage.MSG_LOGIN_FAIL, errorMessage));

        log("INFO: Executed completely");
    }

    @TestRails(id = "1216")
    @Parameters({"brandname"})
    @Test(groups = {"regression_sat","regression_fs", "regression_f24"})// other whitelabel except Fair999
    public void Landing_Page_TC1216(String brandname) throws Exception {
        log("@title: Validate Underage gambling is prohibited popup");
        log("Step 1. Access member site");
        log("Step 2. Click on Login button");
        UnderageGamblingPopup underageGamblingPopup = landingPage.clickLogin();

        log("Verify 1. Validate Underage gambling is prohibited popup UI");
        Assert.assertEquals(underageGamblingPopup.getTitle(), MemberConstants.LoginPage.LBL_UNDERGAMLING_TITLE_MAP.get(brandname), "Failed! Underage Gambling title is mismatched");
        Assert.assertEquals(underageGamblingPopup.getContent(), MemberConstants.LoginPage.LBL_UNDERGAMLING_CONTENT_MAP.get(brandname), "Failed! Underage Gambling sub-text is mismatched");
        log("INFO: Executed completely");
    }

    @TestRails(id = "1217")
    @Test(groups = {"regression_sat", "regression_fs", "regression_f24"})// other whitelabel except Fair999
    public void Landing_Page_TC1217() throws Exception {
        log("@title: Validate Google page display when click on Exit");
        log("Step 1. Access member site");
        log("Step 2. Click on Login button to open Underage Gambling popup and click on Exit button");
        UnderageGamblingPopup underageGamblingPopup = landingPage.clickLogin();
        underageGamblingPopup.clickExit();

        log("Verify 1. Validate Underage gambling is prohibited popup UI");
        Assert.assertEquals(landingPage.getPageUrl(), "https://www.google.com/", "Failed! Google page is dispplay after clicking on Exit");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29504")
    @Test(groups = {"atlantic"})
    @Parameters({"username"})
    public void Landing_Page_TC29504(String username) {
        log("@title: Attlantic2020 - Validate that user can sign in successfully with valid account");
        log("Precondition: The player is added in Atlantic Access Management BO Site\n" +
                "Login URL: STG: https://f48stg.beatus88.com/atlantic/#/home\n" +
                "PRO: https://www.atlantic2020.com/atlantic/#/home");
        log("@Step 1 Access Login URL");
        log("@Step 2 Click CONSERVATION OF TUNA menu next to Home menu");
        log("@Step 3 Click on the Atlantic words in the sentence \" the regulators of Atlantic bluefin fishing\" to show Username and Password text box");
        log("@Step 4 Login with the valid account in precondition");

        log("@Verify 1 The page directs to correct domain");
        Assert.assertTrue(memberHomePage.getHomePageURL().contains(environment.getAtlanticURL()),String.format("FAILED! The URL is not correct expected contains %s but found %s", environment.getAtlanticURL(), memberHomePage.getHomePageURL()));
        log("@Verify 2 My account display correct login account");
        Assert.assertTrue(memberHomePage.isMyAccountDisplay(), "Failed! My Account does not display after login");
        Assert.assertTrue(memberHomePage.isMyAccountContains(username), "Failed! My Account does not display correct account name after login");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29563")
    @Test(groups = {"alpicfox"})
    @Parameters({"username"})
    public void Landing_Page_TC29563(String username) {
        log("@title: Alpicfox - Validate that user can sign in successfully with valid account");
        log("Precondition: The player is added in Atlantic Access Management BO Site\n" +
                "Login URL:\n" +
                "PRO: https://www.alpicfox.com/alpicfox/#/home");
        log("@Step 1 Access Login URL");
        log("@Step 2 Click on the word 'litter' to show Username and Password fields");
        log("@Step 3 Login with the valid account in precondition");

        log("@Verify 1 The page directs to correct domain");
        Assert.assertTrue(memberHomePage.getHomePageURL().contains(environment.getAlpicfoxURL()),String.format("FAILED! The URL is not correct expected contains %s but found %s", environment.getAtlanticURL(), memberHomePage.getHomePageURL()));
        log("@Verify 2 My account display correct login account");
        Assert.assertTrue(memberHomePage.isMyAccountDisplay(), "Failed! My Account does not display after login");
        Assert.assertTrue(memberHomePage.isMyAccountContains(username), "Failed! My Account does not display correct account name after login");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29564")
    @Test(groups = {"crypto"})
    @Parameters({"username"})
    public void Landing_Page_TC29564(String username) {
        log("@title: Cryptofact88 - Validate that user can sign in successfully with valid account");
        log("Precondition: The player is added in BO Site - Crypto Access Management\n" +
                "Login URL:\n" +
                "STG: https://crystg.beatus88.com/forex/#/home\n" +
                "PRO: https://www.cryptofact88.com/forex/#/home");
        log("@Step 1 Access Login URL");
        log("@Step 2 Click on 'How to own Bitcoin' next to Home menu");
        log("@Step 3 Click on the work 'USD' or 'Euros' to show Username and Password fields");
        log("@Step 4 Login with the valid account in precondition");

        log("@Verify 1 The page directs to correct domain");
        Assert.assertTrue(memberHomePage.getHomePageURL().contains(environment.getCryptoURL()),String.format("FAILED! The URL is not correct expected contains %s but found %s", environment.getAtlanticURL(), memberHomePage.getHomePageURL()));
        log("@Verify 2 My account display correct login account");
        Assert.assertTrue(memberHomePage.isMyAccountDisplay(), "Failed! My Account does not display after login");
        Assert.assertTrue(memberHomePage.isMyAccountContains(username), "Failed! My Account does not display correct account name after login");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29565")
    @Test(groups = {"eu1010"})
    @Parameters({"username"})
    public void Landing_Page_TC29565(String username) {
        log("@title: eu1010 - Validate that user can sign in successfully with valid account");
        log("Precondition: The player of White label 9 brand 'eu1010.com'\n" +
                "Login URL:\n" +
                "STG: https://w9stg.beatus88.com/plus/#/1/home/exchange/main/all\n" +
                "PRO: https://www.eu1010.com/plus/#/1/home/exchange/main/all");
        log("@Step 1 Access Login URL");
        log("@Step 2 At , click on '2. Origins of Wind Power' from 'Table of Contents' panel");
        log("@Step 3 Click on the comma after the word 'grains' to show Username and Password fields");
        log("@Step 4 Login with the valid account in precondition");

        log("@Verify 1 The page directs to correct domain");
        Assert.assertTrue(memberHomePage.getHomePageURL().contains(environment.getEu1010URL()),String.format("FAILED! The URL is not correct expected contains %s but found %s", environment.getAtlanticURL(), memberHomePage.getHomePageURL()));
        log("@Verify 2 My account display correct login account");
        Assert.assertTrue(memberHomePage.isMyAccountDisplay(), "Failed! My Account does not display after login");
        Assert.assertTrue(memberHomePage.isMyAccountContains(username), "Failed! My Account does not display correct account name after login");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29580")
    @Test(groups = {"powderpin"})
    @Parameters({"username"})
    public void Landing_Page_TC29580(String username) {
        log("@title: Powderpin - Validate that user can sign in successfully with valid account");
        log("Precondition: The player is NOT added in BO Site - Crypto Access Management\n" +
                "Login URL:\n" +
                "PRO: https://www.powderpin.com/landing/#/d");
        log("@Step 1 Access Login URL");
        log("@Step 2 Click Confirm button to open Login form");
        log("@Step 3 Login with the valid account in precondition");

        log("@Verify 1 The page directs to correct domain");
        Assert.assertTrue(memberHomePage.getHomePageURL().contains(environment.getPowderpinURL()),String.format("FAILED! The URL is not correct expected contains %s but found %s", environment.getAtlanticURL(), memberHomePage.getHomePageURL()));
        log("@Verify 2 My account display correct login account");
        Assert.assertTrue(memberHomePage.isMyAccountDisplay(), "Failed! My Account does not display after login");
        Assert.assertTrue(memberHomePage.isMyAccountContains(username), "Failed! My Account does not display correct account name after login");
        log("INFO: Executed completely");
    }
/*
    @Test (groups = {"smoke"})
    public void Login_TC_004() throws Exception {
        log("@title: Validate Underage gambling is prohibited popup");
        log("Step 1. Access member site");
        log("Step 2. Click on Login button");
        UnderageGamblingPopup underageGamblingPopup = landingPage.fsHeaderControl.openUnderGablingPopup();

        log("Verify 1. Validate Underage gambling is prohibited popup UI");
        Assert.assertEquals(underageGamblingPopup.lblTitle.getText(), MemberConstants.LoginPage.LBL_UNDERGAMLING_TITLE,"FAILED! Title is incorrect!");
        Assert.assertEquals(underageGamblingPopup.lblContent.getText(), MemberConstants.LoginPage.LBL_UNDERGAMLING_CONTENT,"FAILED! Content of Underage gambling is incorrect!");
        Assert.assertEquals(underageGamblingPopup.btnConfirm.getText(), MemberConstants.LoginPage.BTN_CONFIRM,"FAILED! Confirm button is incorrect!");
        Assert.assertEquals(underageGamblingPopup.btnExit.getText(), MemberConstants.LoginPage.BTN_EXIT,"FAILED! Exit button is incorrect!");
        log("INFO: Executed completely");
    }




    @Parameters({"username"})
    @Test (groups = {"smoke"})
    public void FE_Login_003(String username){
        log("@title: Validate that user with invalid account cannot sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        membersite.pages.all.beforelogin.popups.LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

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


    @Test (groups = {"smoke"})
    public void FE_Login_004(){
        log("@title: Validate UI of Underage gambling popup");
        log("Step 1. Access member site");
        log("Step 2. Click on Login button");
        membersite.pages.all.beforelogin.popups.LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

        log("Verify 1. Verify Login UI ");
        Assert.assertTrue(loginPopup.txtUsername.isDisplayed(),"ERROR! Username textbox is not displayed");
        Assert.assertTrue(loginPopup.txtUsername.isDisplayed(), "ERROR! Password textbox is not displayed");
        Assert.assertEquals(loginPopup.btnLogin.getText(),"Login","Login button is not displayed");
        Assert.assertEquals(loginPopup.btnExit.getText(),"Exit","Exit button is not displayed");
        log("INFO: Executed completely");
    }

    @Parameters({"username", "password","skinName"})
    @Test (groups = {"smoke_demo"})
    public void FE_Login_001_Demo(String username, String password,String skinName) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        membersite.pages.all.beforelogin.popups.LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

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

    @Parameters({"username", "password","skinName"})
    @Test (groups = {"smoke_demo"})
    public void FE_Login_001_Demo_FAILED(String username, String password,String skinName) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        membersite.pages.all.beforelogin.popups.LoginPopup loginPopup = landingPage.satHeaderControl.clickLoginBtn();

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
    }*/


}
