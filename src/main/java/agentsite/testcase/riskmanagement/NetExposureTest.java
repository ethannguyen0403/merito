package agentsite.testcase.riskmanagement;

import agentsite.objects.agent.account.MarketInfo;
import agentsite.pages.riskmanagement.NetExposurePage;
import agentsite.ultils.riskmanagement.NetExposurelUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.HomePage.NET_EXPOSURE;
import static common.AGConstant.RiskManagement.NetExposure.*;


public class NetExposureTest extends BaseCaseTest {
    /***
     * This test cases only available for F24 and Betclub9
     */
    @TestRails(id = "834")
    @Test(groups = {"smoke5", "smokePO"})
    public void Agent_RM_NetExposureTest_834() {
        log("@title: Verify UI display correctly");
        log("Step 1.Navigate Risk Management > Volumn Monitor");
        NetExposurePage page = agentHomePage.navigateNetExposurePage();

        log("Verify 1 Verify UI is correctly displayed");
        Assert.assertEquals(page.header.lblPageTitle.getText(), NET_EXPOSURE, "FAILED! Volume Monitor page title is incorrect");
        Assert.assertEquals(page.lblMyPT.getText(), LBL_MYPT, "FAILED! Level label is incorrect");
        Assert.assertEquals(page.lblTotalBook.getText(), LBL_TOTAL_BOOK, "FAILED! Sport label is incorrect");
        Assert.assertEquals(page.lblInPlay.getText(), LBL_IN_PLAY, "FAILED! List Bet Type is incorrect");
        Assert.assertEquals(page.lblSportSelection.getText(), LBL_SPORT_SELECTION, "FAILED! Today button is incorrect");
        Assert.assertEquals(page.lblMultipleSportSelection.getText(), LBL_MULTI_SPORT_SELECTION, "FAILED! Yesterday button is incorrect");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), LBL_NO_RECORD, "FAILED! No data message is incorrect displayed");
        }

        log("INFO: Executed completely");
    }

    @TestRails(id = "835")
    @Test(groups = {"smokePO"})
    public void Agent_RM_NetExposureTest_835() {
        log("@title:Validate Order place on match odds market display correct section My PT mode");
        log("Step 1.Navigate Risk Management > Net Exposure");
        NetExposurePage page = agentHomePage.navigateNetExposurePage();
        log("Step 2. Select my PT mode> 2-3 Selection");
        page.filter("My PT", false, true, "");

        log("Verify 1 In case has data, UI display correctly");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), LBL_NO_RECORD, "FAILED! No data message is incorrect displayed");
        } else {
            Assert.assertEquals(page.tblResult.getColumnNamesOfTable(), RESULT_TABLE_HEADER, "FAILE! Result table is incorrect");
        }

        log("INFO: Executed completely");
    }

    @TestRails(id = "836")
    @Test(groups = {"smoke", "smokePO"})
    public void Agent_RM_NetExposureTest_836() {
        log("@title: Verify can open bet list in PT Mode");
        log("Step 1.Navigate Risk Management > Volume Monitor");
        NetExposurePage page = agentHomePage.navigateNetExposurePage();
        List<MarketInfo> lstMarket = NetExposurelUtils.getEventList(NetExposurelUtils.getSportID().get(0), false, false, false);

        log("Step 2. Select my PT mode> 2-3 Selection");
        page.filter("My PT", false, true, "");

        log("Step 3 If have data in matched odds section, click on Bet List icon of a market");
        page.openBetList("Cricket", lstMarket.get(0).getEventName(), lstMarket.get(0).getMarketName());

        log("Verify Verify Bet list display with correct UI and has data");
        Assert.assertEquals(page.lblNetExposure.getText(), "Net Exposure", "FAILED! Exposure is incorrect");
        Assert.assertEquals(page.lblEventName.getText(), lstMarket.get(0).getEventName(), "FAILED! Bet list display incorrect event name");
        Assert.assertEquals(page.tblBetList.getColumnNamesOfTable(), BET_LIST_HEADER_TBL, "FAILED! Table header bet list is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "837")
    @Test(groups = {"poregression"})
    public void Agent_RM_NetExposureTest_837() {
        log("@title: Verify can open Downline");
        log("Step 1.Navigate Risk Management > Volume Monitor");
        NetExposurePage page = agentHomePage.navigateNetExposurePage();
        List<MarketInfo> lstMarket = NetExposurelUtils.getEventList(NetExposurelUtils.getSportID().get(0), false, false, false);
        log("Step 2. Select my PT mode> 2-3 Selection");
        page.filter("My PT", false, true, "");

        log("Step 3 If have data in matched odds section, click on Downline icon");
        page.openDownline("Cricket", lstMarket.get(0).getEventName(), lstMarket.get(0).getMarketName());

        log("Verify 1.The direct downline level display");

        log("INFO: Executed completely");
    }
}
