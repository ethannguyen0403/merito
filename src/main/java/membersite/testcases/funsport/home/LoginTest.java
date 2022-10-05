package membersite.testcases.funsport.home;

import com.paltech.utils.StringUtils;
import membersite.common.FEMemberConstants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.funsport.home.popups.LoginPopup;
import membersite.pages.funsport.home.popups.UnderageGamblingPopup;
import baseTest.BaseCaseMerito;

public class LoginTest extends BaseCaseMerito {
    /**
     * @title: Validate that user can sign in successfully
     * @steps:   1. Navigate to login page
     *           2. Click Login button and confirm more than 18 years old
     *           3. Log in with a valid username and password
     * @expect:  1. Home page is displayed
     */
    @Parameters({"username", "password"})
    @Test (groups = {"smoke"})
    public void Login_TC_001(String username, String password) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");
        String passDecrypt= StringUtils.decrypt(password);

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.fsHeaderControl.openUnderGablingPopup().clickConfirmation();

        log("Step 3: Log in with a valid username and password");
        HomePage page = loginPopup.login(username, passDecrypt,true);

        log("Verify: Home page is displayed");
        Assert.assertTrue(page.fsHeaderControl.ddbMyAccount.isDisplayed(), "ERROR: ddbMyAccount doesn't display after signed in");
        Assert.assertTrue(page.fsHeaderControl.tabExchange.isDisplayed(), "ERROR: tabExchange doesn't display after signed in");
        Assert.assertTrue(page.fsHeaderControl.tabLiveDealer.isDisplayed(), "ERROR: tabLiveDealer doesn't display after signed in");
        Assert.assertTrue(page.fsHeaderControl.tabExchangeGames.isDisplayed(), "ERROR: tabExchangeGame doesn't display after signed in");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user with invalid password cannot sign in successfully
     * @steps:   1. Navigate to login page
     *           2. Click Login button and confirm more than 18 years old
     *           3. Log in with an invalid username and invalid password
     * @expect:  1. An login popup is displayed
     *           2. The content message is 'You have entered an incorrect Password. Please try again.'
     *           3. Login button still displays
     */
    @Parameters({"username"})
    @Test (groups = {"smoke"})
    public void Login_TC_002(String username){
        log("@title: Validate that user with invalid password cannot sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.fsHeaderControl.openUnderGablingPopup().clickConfirmation();

        log("Step 3: Log in with a valid username and invalid password");
        loginPopup.login(username, StringUtils.generatePassword(10),true);

        String errorMessage = loginPopup.lblErrorMessage.getText();

        log("Verify 1: An error login popup is displayed");
        log(String.format("Verify 3: The content message is '%s'", FEMemberConstants.LoginPage.MSG_INVALID_PASSWORD));

        log("Verify 2: btnLogin still displays");
        Assert.assertTrue(loginPopup.popupLogin.isDisplayed(), "ERROR: popupLogin doesn't display after signed in failed");
        Assert.assertEquals(errorMessage, FEMemberConstants.LoginPage.MSG_INVALID_PASSWORD, String.format("ERROR: The expected error message is '%s' but found '%s'", FEMemberConstants.LoginPage.MSG_INVALID_PASSWORD, errorMessage));
        Assert.assertTrue(loginPopup.btnLogin.isDisplayedShort(3), "ERROR: btnLogin doesn't display after signed in with an invalid password");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Login form display when click confirm
     * @steps: 1. Access member site
     *         2. Click on Login button and click Confirm button
     * @expect: 1. Login form display with username, password, Login button
     */

    @Test (groups = {"smoke"})
    public void Login_TC_006() throws Exception {
        log("@title: 1. Login form display with username, password, Login button");
        log("Step 1. Access member site");
        log("Step 2. Click on Login button and click Confirm button");
        LoginPopup loginPopup = landingPage.fsHeaderControl.openUnderGablingPopup().clickConfirmation();

        log("Verify1. Login form display with username, password, Login button");
        Assert.assertEquals(loginPopup.lblUsername.getText(),FEMemberConstants.LoginPage.LBL_USERNAME,"FAILED! Email/Username label is incorrectly displayed");
        Assert.assertEquals(loginPopup.lblRememberPassword.getText(),FEMemberConstants.LoginPage.LBL_REMEMBER_PASSWORD,"FAILED! Remember Password label is incorrectly displayed");
        Assert.assertEquals(loginPopup.lblPassword.getText(),FEMemberConstants.LoginPage.LBL_PASSWORD,"FAILED! Password label is incorrectly displayed");
        Assert.assertEquals(loginPopup.lblShowPassword.getText(),FEMemberConstants.LoginPage.LBL_SHOW_PASSWORD,"FAILED!Show Password label is incorrectly displayed");
        Assert.assertEquals(loginPopup.btnLogin.getText(),FEMemberConstants.LoginPage.BTN_LOGIN,"FAILED!Login button is incorrectly displayed");
        Assert.assertTrue(loginPopup.txtPassword.isDisplayed(),"FAILED! Email/Username textbox is incorrectly displayed");
        Assert.assertTrue(loginPopup.txtPassword.isDisplayed(),"FAILED! Password textbox is incorrectly displayed");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Underage gambling is prohibited popup
     * @steps: 1. Access member site
     *         2. Click on Login button
     * @expect: 1. Validate Underage gambling is prohibited popup UI
     */

    @Test (groups = {"smoke"})
    public void Login_TC_004() throws Exception {
        log("@title: Validate Underage gambling is prohibited popup");
        log("Step 1. Access member site");
        log("Step 2. Click on Login button");
        UnderageGamblingPopup underageGamblingPopup = landingPage.fsHeaderControl.openUnderGablingPopup();

        log("Verify 1. Validate Underage gambling is prohibited popup UI");
        Assert.assertEquals(underageGamblingPopup.lblTitle.getText(),FEMemberConstants.LoginPage.LBL_UNDERGAMLING_TITLE,"FAILED! Title is incorrect!");
        Assert.assertEquals(underageGamblingPopup.lblContent.getText(),FEMemberConstants.LoginPage.LBL_UNDERGAMLING_CONTENT,"FAILED! Content of Underage gambling is incorrect!");
        Assert.assertEquals(underageGamblingPopup.btnConfirm.getText(),FEMemberConstants.LoginPage.BTN_CONFIRM,"FAILED! Confirm button is incorrect!");
        Assert.assertEquals(underageGamblingPopup.btnExit.getText(),FEMemberConstants.LoginPage.BTN_EXIT,"FAILED! Exit button is incorrect!");
        log("INFO: Executed completely");
    }
}
