package agentsite.testcase.satsport.marketsmanagement;


import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.marketsmanagement.BlockingLogPage;

import static agentsite.common.AGConstant.HomePage.BLOCKING_LOG;
import static agentsite.common.AGConstant.HomePage.MARKET_MANAGEMENT;

public class BlockRacingTest extends BaseCaseMerito {
    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Block Racing
     * @expect: 1. Verify there is no console error display
     */
    @Test(groups = {"http_request"})
    public void Agent_MM_Block_Racing_TC001() {
        log("@title: Validate there is no http responded error returned");

        log("Step 1. Navigate Markets Management >Block Racing");
        agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCKING_LOG, BlockingLogPage.class);

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }


}
