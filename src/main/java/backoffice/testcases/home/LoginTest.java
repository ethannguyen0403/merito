package backoffice.testcases.home;

import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseCaseTest {
    /**
     * @title: There is no http(s) responded error returned
     * @steps: 1. Navigate to login page
     * 2. Log in with a valid username and password
     * @expect: 1. Home page is displayed
     */
    @Test(groups = {"http_request"})
    public void BO_Login_001() {
        log("@title: There is no http(s) responded error returned");
        log("Step 1: Navigate to login page");
        log("Step 2: Log in with a valid username and password");

        boolean hasNoError = hasHTTPRespondedOK();
        log("Verify: Home page is displayed successfully");
        Assert.assertTrue(hasNoError, "ERROR: There is at least an response code >=400");
        log("INFO: This test case executed completely");
    }

    /**
     * @title: Validate that user can sign in successfully
     * @steps: 1. Log in with a valid username and password
     * @expect: 1. Home page is displayed
     */
    @Parameters({"username"})
    @Test(groups = {"smoke"})
    public void BO_Login_002(String username) {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Log in with a valid username and password");
        String observedUsername = backofficeHomePage.lblUsername.getText();
        log("Verify 1: Logout button is displayed");
        Assert.assertTrue(backofficeHomePage.btnLogOut.isDisplayed(), "ERROR: btnLogOut is displayed");

        log("Verify 2: Home page is displayed");
        Assert.assertEquals(observedUsername, String.format("Welcome %s", username), "ERROR: The expected Welcome");
        log("INFO: Executed completely");
    }
}
