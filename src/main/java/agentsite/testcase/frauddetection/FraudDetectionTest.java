package agentsite.testcase.frauddetection;

import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class FraudDetectionTest extends BaseCaseTest {
    @TestRails(id = "827")
    @Test(groups = {"http_request"})
    public void Agent_Fraud_Detection_827(){
        //Set isProxy = true
        log("@title: Validate There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Fraud Detection");
        agentHomePage.navigateFraudDetectionPage();
        log("Verify 1. Fraud Detection page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");
        log("INFO: Executed completely");
    }
}
