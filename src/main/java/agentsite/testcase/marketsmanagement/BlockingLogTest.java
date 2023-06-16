package agentsite.testcase.marketsmanagement;

import common.AGConstant;
import agentsite.pages.marketsmanagement.BlockingLogPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlockingLogTest extends BaseCaseTest {
    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Block Racing
     * @expect: 1. Verify there is no console error display
     */
    @Test(groups = {"http_request"})
    public void Agent_MM_Blocking_Log_TC001() {
        log("@title: Validate there is no http responded error returned");

        log("Step 1. Navigate Markets Management >Block Racing");
        agentHomePage.navigateBlockingLogPage();

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify Blocking Log UI display correctly
     * @pre-condition:
     *           11. Log in successfully by SAD
     * @steps:   1. Navigate Markets Management > Blocking Log
     * @expect:  1. Verify Blocking Log UI display correctly
     */
    @Test(groups = {"smoke"})
    public void Agent_MM_Blocking_Log_TC002() {
        log("@title Verify Blocking Log UI display correctly");
        log("Step 1. Navigate Markets Management > Blocking Log");
        BlockingLogPage page =  agentHomePage.navigateBlockingLogPage();

        log("Verify  1. Verify Blocking Log UI display correctly");
        Assert.assertEquals(page.lblTitle.getText().trim(), AGConstant.MarketsManagement.BlockingLog.TITLE_PAGE,"FAILED! Page title is not correct");
        Assert.assertEquals(page.lblEventDate.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_DATE,"FAILED! Event Date is not correct");
        Assert.assertEquals(page.lblSport.getText().trim(), AGConstant.MarketsManagement.BlockingLog.SPORT,"FAILED! Sport is not correct");
        Assert.assertEquals(page.lblCompeition.getText().trim(), AGConstant.MarketsManagement.BlockingLog.COMPETITON,"FAILED! Competition is not correct");
        Assert.assertEquals(page.lblEventID.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_ID,"FAILED! Event is not correct");
        Assert.assertEquals(page.lblEventName.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_NAME,"FAILED! Event Name title is not correct");
        Assert.assertEquals(page.lblEventStatus.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_STATUS,"FAILED! Event Status is not correct");
        Assert.assertEquals(page.txtSearchSport.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_SPORT,"FAILED! Search Sport textbox place holder is not correct");
        Assert.assertEquals(page.txtSearchCompetition.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_COMPETITION,"FAILED! Search Competition place holder is not correct");
        Assert.assertEquals(page.txtSearchEventID.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_EVENT_ID,"FAILED! Search Event id place holder is not correct");
        Assert.assertEquals(page.txtSearchEventName.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_EVENT_NAME,"FAILED! Search Event name place holder is not correct");
        Assert.assertEquals(page.txtSearchStatus.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_EVETS_STATUS,"FAILED! Search event status place holder is not correct");
        Assert.assertTrue(page.btnRefresh.isDisplayed(),"FAILED! Refresh button not display");
        log("INFO: Executed completely");
    }

}
