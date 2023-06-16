package backoffice.testcases.home;

import backoffice.common.BOConstants;
import backoffice.pages.bo.home.LoginPage;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseCaseTest {
    /**
     * @title: Validate that user can logout successfully
     * @steps: 1. Log in with a valid username and password
     * 2. Click Logout button
     * @expect: 1. Logout is successful
     */
    @Test(groups = {"smoke"})
    public void Agent_Logout_001() {
        log("@title: Validate that user can logout successfully");
        log("Step 1: Log in with a valid username and password");
        log("Step 2: Click Logout button");
        LoginPage loginPage = backofficeHomePage.logout();

        log("Verify: Logout is successful");
        Assert.assertTrue(loginPage.txtUsername.isDisplayed(), "ERROR: Username text-box is not displayed after logging out");
        String loginToYourAccount = loginPage.lblLoginToYourAccount.getText();
        Assert.assertEquals(loginToYourAccount, BOConstants.LoginPage.LOGIN_TO_YOUR_ACCOUNT, String.format("ERROR: The expected text is '%s' but found '%s'", BOConstants.LoginPage.LOGIN_TO_YOUR_ACCOUNT, loginToYourAccount));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Logout page is kept when clicking Back button on the browser
     * @steps: 1. Log in with a valid username and password
     * 2. Click Logout button
     * 3. Click Back button on the browser
     * @expect: 1. Logout page is kept
     */
    @Test(groups = {"regression"})
    public void Agent_Logout_002() {
        log("@title: Validate that Logout page is kept when clicking Back button on the browser");
        log("Step 1: Log in with a valid username and password");
        log("Step 2: Click Logout button");
        LoginPage loginPage = backofficeHomePage.logout();

        log("Step 3: Click Logout button");
        DriverManager.getDriver().back();

        log("Verify: Logout page is kept");
        Assert.assertTrue(loginPage.txtUsername.isDisplayed(), "ERROR: Username text-box is not displayed after logging out");
        String loginToYourAccount = loginPage.lblLoginToYourAccount.getText();
        Assert.assertEquals(loginToYourAccount, BOConstants.LoginPage.LOGIN_TO_YOUR_ACCOUNT, String.format("ERROR: The expected text is '%s' but found '%s'", BOConstants.LoginPage.LOGIN_TO_YOUR_ACCOUNT, loginToYourAccount));
        log("INFO: Executed completely");
    }
}
