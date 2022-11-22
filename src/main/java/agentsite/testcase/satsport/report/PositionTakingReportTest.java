package agentsite.testcase.satsport.report;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.PositionTakingReportPage;
import util.testraildemo.TestRails;

import static agentsite.common.AGConstant.HomePage.POSITION_TAKING_REPORT;
import static agentsite.common.AGConstant.HomePage.REPORT;

public class PositionTakingReportTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Position Taking Report
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Posision_Taking_Report_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Position Taking Report");
        agentHomePage.clickSubMenu(REPORT, POSITION_TAKING_REPORT, PositionTakingReportPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate summary data is match with details
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Position Taking Report
     *          2. Search the data range has data of Exchange Product
     * @expect: 1. Verify Volume, Pnl, Tax max with detail
     */
    @TestRails(id="797")
    @Test (groups = {"smoke"})
    public void Agent_Report_Position_Taking_Report_003(){
        log("@title: Validate can filter Win Loss Detail report");
        log("Step 1. Navigate Report > Position Taking Report");
        PositionTakingReportPage page = agentHomePage.clickSubMenu(REPORT, POSITION_TAKING_REPORT, PositionTakingReportPage.class);

        log("Step 2. Search the data range has data of Exchange Product");
       page.dpFrom.currentMonthWithDate("1");
       page.filter("Exchange");

        log("Verify 1. Verify Volume, Pnl, Tax max with detail");
        if(page.lblNoRecord.isDisplayed()){
            log("PASSED By pass as no data found");
            Assert.assertTrue(true,"Bypass this test case");
            return;
        }
        Assert.assertTrue(page.verifyDataMatchWithDetail(),"FAILED ! Total data not match when sum detail");
      
        log("INFO: Executed completely");
    }


}
