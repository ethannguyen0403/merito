package agentsite.testcase.all.fraudetection;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.fraudetection.FraudDetectionPage;

import static agentsite.common.AGConstant.HomePage.FRAUD_DETECTION;
import static agentsite.common.AGConstant.HomePage.FRAUD_PERMISSION;

public class FraudPermissionTest extends BaseCaseMerito {
    /**
     * @title: Validate there is no http responded error returned
     * @steps: 1. Navigate Markets Management > Fraud Permission
     * @expect: 1. Verify there is no console error display
     */
    @Test(groups = {"http_request"})
    public void Agent_FD_Fraud_Permission_001() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Navigate Markets Management > Fraud Permission");
        FraudDetectionPage page = agentHomePage.clickSubMenu(FRAUD_DETECTION, FRAUD_PERMISSION, FraudDetectionPage.class);

        log("Verify 1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }


}
