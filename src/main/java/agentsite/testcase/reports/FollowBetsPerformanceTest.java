package agentsite.testcase.reports;

import agentsite.pages.report.FollowBetPerformancePage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class FollowBetsPerformanceTest extends BaseCaseTest {
    /**
     * @title: Validate Follow Bets Performance display correctly
     * @pre-condition: 1. Log in successfully by PO level
     * @steps: 1. Navigate Report >Follow & Small Bets Performance
     * @expect: 1. Verify Follow Bets Performance UI display correctly
     */
    @TestRails(id = "825")
    @Test(groups = {"smoke_po"})
    public void Agent_Report_Follow_Small_Bets_Performance_825() {
        log("@title: Validate Follow & Small Bets Performance display correctly");
        log("Step  1. Navigate Report >Follow Bets Performance");
        FollowBetPerformancePage page = agentHomePage.navigateFollowBetPerformancePage();
        log("Verify 1. Verify Follow & Small Bets Performance UI display correctly");
        Assert.assertEquals(page.lblUserName.getText(), AGConstant.LBL_USERNAME, "FAILED! Small Bets label not display as expected");
        Assert.assertEquals(page.txtUsername.getAttribute("placeholder"), AGConstant.LBL_USERNAME_PLACE_HOLDER, "FAILED! Place holder of Username textbox not display as expected");
        Assert.assertTrue(page.ddbAccountToBet.isDisplayed(), "FAILED! Account to bet dropdown box not display");
        Assert.assertEquals(page.btnSearch.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button not display");
        Assert.assertEquals(page.lblInfo.getText(), AGConstant.Report.FollowAndSmallBetsPerformance.INFO, "FAILED! Info text not display as expected");
        Assert.assertEquals(page.tblFollowBet.getHeaderNameOfRows(), AGConstant.Report.FollowAndSmallBetsPerformance.TABLE_FOLLOW_BETS_HEADER, "FAILED Follow Bet table header not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can filter follow bet
     * @pre-condition: 1. Log in successfully by PO level
     * @steps: 1. Navigate Report >Follow & Small Bets Performance
     * 2. Select follow Bets
     * 3. Enter username or Login ID and select account to bet then click Submit button
     * @expect: 1. Verify Follow Bets info display
     */
    @TestRails(id = "826")
    @Test(groups = {"http_request"})
    public void Agent_Report_Follow_Small_Bets_Performance_826() {
        log("@title: Validate can filter follow bet");
        log("Step 1. Navigate Report >Follow & Small Bets Performance");
        FollowBetPerformancePage page = agentHomePage.navigateFollowBetPerformancePage();
        String startDate = DateUtils.getDate(85, "MM/dd/yyyy", "GMT-4");
        String endDate = DateUtils.getDate(0, "MM/dd/yyyy", "GMT-4");


        log("Step 2. Select follow Bets");
        log("Step  3. Enter username or Login ID and select account to bet then click Submit button");
        page.searchFollowBet("", "", startDate, endDate);

        log("Verify 1. Verify Follow Bets info display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }


}
