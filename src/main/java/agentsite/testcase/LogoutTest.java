package agentsite.testcase;

import common.AGConstant;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class LogoutTest extends BaseCaseTest {
    /**
     * @title: Validate that user can logout successfully
     * @steps:   1. Log in with a valid username and password
     *           2. Click Logout button
     * @expect:  1. Logout is successful
     */
    @TestRails(id = "672")
    @Test (groups = {"smoke"})
    public void Agent_Logout_001(){
        log("@title: Validate that user can logout successfully");
        log("Step 1: Log in with a valid username and password");
        log("Step 2: Click Logout button");
        agentLoginPage  = agentHomePage.logout();

        log("Verify: Logout is successful");
        Assert.assertTrue(agentLoginPage.txtUsername.isDisplayed(), "ERROR: Username text-box is not displayed after logging out");
        String login = agentLoginPage.lblLogin.getText();
        Assert.assertEquals(login, AGConstant.LoginPage.LOGIN, String.format("ERROR: The expected text is '%s' but found '%s'", AGConstant.LoginPage.LOGIN, login));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Logout page is kept when clicking Back button on the browser
     * @steps:   1. Log in with a valid username and password
     *           2. Click Logout button
     *           3. Click Back button on the browser
     * @expect:  1. Logout page is kept
     */
    @Test (groups = {"regression"})
    public void Agent_Logout_002(){
        log("@title: Validate that Logout page is kept when clicking Back button on the browser");
        log("Step 1: Log in with a valid username and password");
        log("Step 2: Click Logout button");
        agentLoginPage = agentHomePage.logout();

        log("Step 3: Click Logout button");
        DriverManager.getDriver().back();

        log("Verify: Logout page is kept");
        agentLoginPage.txtUsername.isDisplayed(5);
        Assert.assertTrue(agentLoginPage.txtUsername.isDisplayed(), "ERROR: Username text-box is not displayed after logging out");
        String login = agentLoginPage.lblLogin.getText();
        Assert.assertEquals(login, AGConstant.LoginPage.LOGIN, String.format("ERROR: The expected text is '%s' but found '%s'", AGConstant.LoginPage.LOGIN, login));
        log("INFO: Executed completely");
    }
}
