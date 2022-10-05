package agentsite.testcase.all.marketsmanagement;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.MarketInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.marketsmanagement.SuspendUnsuspendMarketPage;
import agentsite.pages.all.marketsmanagement.suspenunsuspendmarkets.MarketDetailsPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.SuspendUnsuspendMarketsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static agentsite.common.AGConstant.HomePage.*;
import static agentsite.common.AGConstant.MarketsManagement.SuspendUnsuspendMarket.*;

public class SuspendUnsuspendMarketsTest extends BaseCaseMerito {

    @Test(groups = {"smoke","smokePO"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC001() {
        log("@title: Verify Control Level Blocking cannot access the page");

        log("Step 1 Login agent site the level control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        agentHomePage.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagementLeftMenu = agentHomePage.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Verify 1. Verify left menu Suspend/Unsuspend Markets menu does not dissplay");
        Assert.assertFalse(lstMarketManagementLeftMenu.contains(AGConstant.MarketsManagement.SuspendUnsuspendMarket.TITLE_PAGE), "FAILED! Suspend/Unsuspend Market only display under control blocking level, other level can not view");
        log("INFO: Executed completely");
    }

    @Test(groups = {"smokeMA"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC002() {
        log("@title: Verify can access the page at direct level under Control Blocking level");

        log("Step 1 Login agent site - the level under control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        agentHomePage.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagementLeftMenu = agentHomePage.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Verify 1. Verify menu is display in the left menu");
        Assert.assertTrue(lstMarketManagementLeftMenu.contains(AGConstant.MarketsManagement.SuspendUnsuspendMarket.TITLE_PAGE), "FAILED! Suspend/Unsuspend Market only display under control blocking level, other level can not view");
        log("INFO: Executed completely");
    }

    @Test(groups = {"smokeMA"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC003() {
        log("@title: Verify UI is displayed correctly");
        log("Step 1 Login agent site - the level under control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        log("Step 3 Access the menu");
        SuspendUnsuspendMarketPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT,SUSPEND_UNSUSPEND_MARKETS, SuspendUnsuspendMarketPage.class);

        log("Verify 1. Verify page title is correct");
        Assert.assertEquals(page.lblPageTitle.getText(),AGConstant.MarketsManagement.SuspendUnsuspendMarket.TITLE_PAGE, "FAILED! Page title is incorrect");

        log("Verify 2/ Controls are correctly displayed");
        Assert.assertEquals(page.lblSport.getText(), AGConstant.MarketsManagement.CurrentBlocking.LBL_SPORT);
        Assert.assertTrue(page.ddbSport.isDisplayed(),"FAILED! Sport dropdown not exist");
        Assert.assertTrue(page.tabOldEvents.isDisplayed(),"FAILED! Old Events tab should display");
        Assert.assertTrue(page.tabToday.isDisplayed(),"FAILED! Today tab  tab should display");
        Assert.assertTrue(page.tabTomorrow.isDisplayed(),"FAILED! Tomorrow tab should display");
        Assert.assertTrue(page.tabFuture.isDisplayed(),"FAILED! Future tab tab should display");
        Assert.assertEquals(page.tblEvent.getColumnNamesOfTable(),AGConstant.MarketsManagement.SuspendUnsuspendMarket.TABLE_EVENT,"FAILED! Header table not match with the expected when selection Event Type");
        log("INFO: Executed completely");
    }
    @Test(groups = {"smokeMA1"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC004() {
        String loginUserId = ProfileUtils.getProfile().getUserID();
        log("@title: Verify Market Detail popup UI is correctly display");
        log("Step 1 Login agent site - the level under control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        log("Step 3 Access the menu");
        SuspendUnsuspendMarketPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT,SUSPEND_UNSUSPEND_MARKETS, SuspendUnsuspendMarketPage.class);

        log("Step 4 Filter a sport that have event and click on Detail link");
        page.filterEvent("Cricket","Today");
        List<MarketInfo> lstEventAPI = SuspendUnsuspendMarketsUtils.getListEvent("4",loginUserId,"TODAY");
        Assert.assertFalse(Objects.isNull(lstEventAPI),"SKIP! No event for Today Cricket so cannot to continue next step");
        MarketDetailsPopup popup = page.openDetailMarket(lstEventAPI.get(0).getEventName());
        ArrayList<String> lstHeaderTable = popup.tblMarket.getColumnNamesOfTable();

        log("Verify 1 Verify Market Detail UI display correctly, competition name and Event Name");
        Assert.assertEquals(popup.getTitle(),MARKET_DETAIL,"FAILED! Popup title is incorrect displayed");
        Assert.assertEquals(popup.lblCompetitionName.getText(),COMPETITION_NAME +":","FAILED! Competition label is incorrect displayed");
        Assert.assertEquals(popup.lblEventName.getText(),EVENT_NAME +":","FAILED! Event label is incorrect displayed");
        Assert.assertEquals(popup.lblCompetitionValue.getText(),lstEventAPI.get(0).getCompetitionName(),"FAILED! Competition value is incorrect displayed");
        Assert.assertEquals(popup.lblEventNameValue.getText(),lstEventAPI.get(0).getEventName(),"FAILED!Event value is incorrect displayed");
        Assert.assertEquals(popup.lblNote.getText(),NOTE,"FAILED! Note message is incorrect displayed");
        Assert.assertEquals(popup.btnSuspend.getText(),SUSPEND,"FAILED! Suspend button is incorrect displayed");
        Assert.assertEquals(popup.btnUnSuspend.getText(),UNSUSPEND,"FAILED! Unsuspend button is incorrect displayed");
        Assert.assertEquals(lstHeaderTable,MARKET_TABLE_HEADER,"FAILED! Table header is incorrect displayed");
        Assert.assertEquals(popup.txtMarketName.getAttribute("placeholder"),MARKET_NAME,"FAILED! Market Name place holder is incorrect displayed");

        log("INFO: Executed completely");
    }
    @Test(groups = {"smokeMA"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC005() {
        String loginUserId = ProfileUtils.getProfile().getUserID();
        String loginID = ProfileUtils.getProfile().getUserCode();
        log("@title: Verify can suspend line market");
        log("Step 1 Login agent site - the level under control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        log("Step 3 Access the menu");
        SuspendUnsuspendMarketPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT,SUSPEND_UNSUSPEND_MARKETS, SuspendUnsuspendMarketPage.class);

        log("Step 4 Filter a sport that have event and click on Detail link");
        page.filterEvent("Cricket","Today");
        List<MarketInfo> lstEventAPI = SuspendUnsuspendMarketsUtils.getListEvent("4",loginUserId,"TODAY");
        Assert.assertFalse(Objects.isNull(lstEventAPI),"SKIP! No event for Today Cricket so cannot to continue next step");
        MarketDetailsPopup popup = page.openDetailMarket(lstEventAPI.get(0).getEventName());
        List<MarketInfo> lstMarketAPI = SuspendUnsuspendMarketsUtils.getListMarket(lstEventAPI.get(0).getEventID(),loginUserId);
        Assert.assertFalse(Objects.isNull(lstMarketAPI),"SKIP! The is no market to suspend so cannot to continue the next step");

        log("Step 5 Suspend a line market");
        popup.suspendMarket(lstMarketAPI.get(0).getMarketName());

        log("Verify 1 Verify Market Detail UI display correctly, competition name and Event Name");
        Assert.assertTrue(popup.verifymarketInfo(lstMarketAPI.get(0).getMarketName(),"Suspended",loginID,""));

        log("INFO: Executed completely");
    }
}
