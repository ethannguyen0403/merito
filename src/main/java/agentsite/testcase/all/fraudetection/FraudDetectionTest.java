package agentsite.testcase.all.fraudetection;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.fraudetection.FraudDetectionPage;

import static agentsite.common.AGConstant.HomePage.FRAUD_DETECTION;

public class FraudDetectionTest extends BaseCaseMerito {
    /**
     * @title: Validate there is no http responded error returned
     * @steps: 1. Navigate Markets Management > Fraud Detection
     * @expect: 1. Verify there is no console error display
     */
    @Test(groups = {"http_request"})
    public void Agent_FD_Fraud_Detection_001() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Navigate Markets Management > Fraud Detection");
        FraudDetectionPage page = agentHomePage.clickSubMenu(FRAUD_DETECTION, FRAUD_DETECTION, FraudDetectionPage.class);

        log("Verify 1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate there is no http responded error returned
     * @steps:
     * 1. Log in successfully by PO level
     * 1. Navigate Markets Management > Fraud Detection
     * 2. Select Place Date option
     * 3. Input the valid search  criterial
     * 4. Click search funtion
     * 5. Click on the links: Download, KPI link
     * @expect:  1. Verify links work
     */
    @Test (groups = {"smoke"})
    public void Agent_FD_Fraud_Detection_006(){
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Navigate Markets Management > Fraud Detection");
        FraudDetectionPage page = agentHomePage.clickSubMenu(FRAUD_DETECTION, FRAUD_DETECTION, FraudDetectionPage.class);
        log("Verify 1. Verify links work");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }


}
