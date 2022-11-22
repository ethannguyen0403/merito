package agentsite.testcase.satsport.report;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.ViewLogPage;
import util.testraildemo.TestRails;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.VIEW_LOG;

public class ViewLogTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > View Log
     * @expect: 1. Win Loss Simple page is displayed
     */
    @TestRails(id="815")
    @Test(groups = {"http_request"})
    public void Agent_Report_View_Log_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > View Log");
        agentHomePage.clickSubMenu(REPORT, VIEW_LOG, ViewLogPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }



}
