package backoffice.testcases.home;

import backoffice.common.BOConstants;
import backoffice.pages.bo.home.LoginPage;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchMenuTest extends BaseCaseTest {
    /**
     * @title: Validate can search and navigate to the correct menu
     * @precondition: Login BO
     * @steps: 1. Input a correct menu e.g.: Wager Void/Unvoid
     * 2. Click on the page in the suggest list
     * @expect: 1. Page is displayed when clicking on search list
     */
    @Test(groups = {"smoke"})
    public void Agent_SearchMenu_001() {
        log("@title: Validate can search and navigate to the correct menu");
        log("Step 1. Input a correct menu e.g.: Wager Void/Unvoid");
        log("Step 2. Click on the page in the suggest list");
        backofficeHomePage.searchPage("Wager Void / Un-void");

        log("Verify 1. Page is displayed when clicking on search list");
        backofficeHomePage.tabActive.isTextDisplayed("Wager Void / Un-void", 3);
        Assert.assertTrue(backofficeHomePage.tabActive.getText().contains("Wager Void / Un-void"), "FAILED! Active tab should be the search page");

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
