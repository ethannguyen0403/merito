package agentsite.testcase.satsport.home;

import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SATLoginTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @steps:   1. Log in with a valid username and password
     * @expect:  1. Home page is displayed
     */
    @Test (groups = {"http_request"})
    public void Agent_Login_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Log in with a valid username and password");

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can sign in successfully
     * @steps:   1. Log in with a valid information(username, password, captcha, security code)
     * @expect:  1. Home page is displayed
     */
    @Test (groups = {"smoke"})
    @Parameters({"username"})
    public void Agent_Login_002(String username){
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Log in with a valid information(username, password, captcha, security code)");
        log("Verify 1: Logout button is displayed");
        Assert.assertTrue(agentHomePage.btnSignOut.isDisplayed(), "ERROR: Logout button is not displayed");

        log("Verify 2: Home page is displayed");
        Assert.assertEquals(agentHomePage.lblLoginID.getText(),username,"Failed!, Login ID label display incorrect");
        Assert.assertEquals(agentHomePage.lblWelcome.getText(), AGConstant.HomePage.WELCOME_BACK,"Failed!, Welcome Back label display incorrect");
        Assert.assertEquals(agentHomePage.lnkConfigureOTP.getText(), AGConstant.HomePage.CONFIGURE_OTP,"Failed!, Configure OTP label display incorrect");
//        Assert.assertTrue(agentHomePage.lblTitle.isDisplayed(), "ERROR: Title bar info is NOT displayed");
        log("INFO: Executed completely");
    }

}
