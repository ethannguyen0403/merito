package agentsite.testcase.marketsmanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.objects.agent.account.MarketInfo;
import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import agentsite.pages.marketsmanagement.SuspendUnsuspendMarketPage;
import agentsite.pages.marketsmanagement.suspenunsuspendmarkets.MarketDetailsPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import agentsite.ultils.maketmanagement.SuspendUnsuspendMarketsUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.HomePage.*;
import static common.AGConstant.MarketsManagement.SuspendUnsuspendMarket.*;

public class SuspendUnsuspendMarketsTest extends BaseCaseTest {
    @TestRails(id = "782")
    @Test(groups = {"smoke"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC782() {
        log("@title: Verify Control Level Blocking cannot access the page");

        log("Step 1 Login agent site the level control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagementLeftMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Verify 1. Verify left menu Suspend/Unsuspend Markets menu does not dissplay");
        Assert.assertFalse(lstMarketManagementLeftMenu.contains(AGConstant.MarketsManagement.SuspendUnsuspendMarket.TITLE_PAGE), "FAILED! Suspend/Unsuspend Market only display under control blocking level, other level can not view");
        log("INFO: Executed completely");
    }

    @TestRails(id = "783")
    @Test(groups = {"smoke_ma"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC783() {
        log("@title: Verify can access the page at direct level under Control Blocking level");

        log("Step 1 Login agent site - the level under control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagementLeftMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Verify 1. Verify menu is display in the left menu");
        Assert.assertTrue(lstMarketManagementLeftMenu.contains(AGConstant.MarketsManagement.SuspendUnsuspendMarket.TITLE_PAGE), "FAILED! Suspend/Unsuspend Market only display under control blocking level, other level can not view");
        log("INFO: Executed completely");
    }

    @TestRails(id = "784")
    @Test(groups = {"smoke_ma"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC784() {
        log("@title: Verify UI is displayed correctly");
        log("Step 1 Login agent site - the level under control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        log("Step 3 Access the menu");
        SuspendUnsuspendMarketPage page = agentHomePage.navigateSuspendUnsuspendMarketPage();

        log("Verify 1. Verify page title is correct");
        Assert.assertEquals(page.header.lblPageTitle.getText(), AGConstant.MarketsManagement.SuspendUnsuspendMarket.TITLE_PAGE, "FAILED! Page title is incorrect");

        log("Verify 2/ Controls are correctly displayed");
        Assert.assertEquals(page.lblSport.getText(), AGConstant.MarketsManagement.CurrentBlocking.LBL_SPORT);
        Assert.assertTrue(page.ddbSport.isDisplayed(), "FAILED! Sport dropdown not exist");
        Assert.assertTrue(page.tabOldEvents.isDisplayed(), "FAILED! Old Events tab should display");
        Assert.assertTrue(page.tabToday.isDisplayed(), "FAILED! Today tab  tab should display");
        Assert.assertTrue(page.tabTomorrow.isDisplayed(), "FAILED! Tomorrow tab should display");
        Assert.assertTrue(page.tabFuture.isDisplayed(), "FAILED! Future tab tab should display");
        Assert.assertEquals(page.tblEvent.getColumnNamesOfTable(), AGConstant.MarketsManagement.SuspendUnsuspendMarket.TABLE_EVENT, "FAILED! Header table not match with the expected when selection Event Type");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3717")
    @Test(groups = {"smoke_ma"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC3717() {
        String loginUserId = ProfileUtils.getProfile().getUserID();
        log("@title: Verify Market Detail popup UI is correctly display");
        log("Step 1 Login agent site - the level under control blocking");
        log("Step 2 Expand  Bet/Market Management section in the left menu");
        log("Step 3 Access the menu");
        SuspendUnsuspendMarketPage page = agentHomePage.navigateSuspendUnsuspendMarketPage();

        log("Step 4 Filter a sport that have event and click on Detail link");
        page.filterEvent("Cricket", "Today");
        List<MarketInfo> lstEventAPI = SuspendUnsuspendMarketsUtils.getListEvent("4", loginUserId, "TODAY");
        Assert.assertFalse(Objects.isNull(lstEventAPI), "SKIP! No event for Today Cricket so cannot to continue next step");
        MarketDetailsPopup popup = page.openDetailMarket(lstEventAPI.get(0).getEventName());
        ArrayList<String> lstHeaderTable = popup.tblMarket.getColumnNamesOfTable();

        log("Verify 1 Verify Market Detail UI display correctly, competition name and Event Name");
        Assert.assertEquals(popup.getTitle(), MARKET_DETAIL, "FAILED! Popup title is incorrect displayed");
        Assert.assertEquals(popup.lblCompetitionName.getText(), COMPETITION_NAME + ":", "FAILED! Competition label is incorrect displayed");
        Assert.assertEquals(popup.lblEventName.getText(), EVENT_NAME + ":", "FAILED! Event label is incorrect displayed");
        Assert.assertEquals(popup.lblCompetitionValue.getText(), lstEventAPI.get(0).getCompetitionName(), "FAILED! Competition value is incorrect displayed");
        Assert.assertEquals(popup.lblEventNameValue.getText(), lstEventAPI.get(0).getEventName(), "FAILED!Event value is incorrect displayed");
        Assert.assertEquals(popup.lblNote.getText(), NOTE, "FAILED! Note message is incorrect displayed");
        Assert.assertEquals(popup.btnSuspend.getText(), SUSPEND, "FAILED! Suspend button is incorrect displayed");
        Assert.assertEquals(popup.btnUnSuspend.getText(), UNSUSPEND, "FAILED! Unsuspend button is incorrect displayed");
        Assert.assertEquals(lstHeaderTable, MARKET_TABLE_HEADER, "FAILED! Table header is incorrect displayed");
        Assert.assertEquals(popup.txtMarketName.getAttribute("placeholder"), MARKET_NAME, "FAILED! Market Name place holder is incorrect displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "785")
    @Test(groups = {"smoke_ma"})
    @Parameters({"downlineAccount", "password"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC785(String downlineAccount, String password) throws Exception {
        log("@title: Verify can suspend line market");
        log("Step Precondition: Unblock an event has Line market at level control blocking");
        String sportName = "Cricket";
        AccountInfo acc = ProfileUtils.getProfile();
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Market market = BlockUnblockEventsUtils.getAnOpenLineMarket(eventList, childID, "4", "OPEN");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, "TODAY");
        blockUnblockEventPage.blockUnblockEvent(downlineAccount, market.getEventName(), "Unblock Now");
        blockUnblockEventPage.blockUnblockEvent("", market.getEventName(), "Suspend");

        log("Step 2  Login the downline level under level control blocking");
        loginAgent(downlineAccount, password, true);

        log("Step 5 Suspend a line market in Suspend Unsuspend Market page");
        SuspendUnsuspendMarketPage suspendUnsuspendMarketPage = agentHomePage.navigateSuspendUnsuspendMarketPage();
        suspendUnsuspendMarketPage.suspendUnsuspendMarket("Today", sportName, market.getEventName(), market.getMarketName(), true, false);

        log("Verify 1 Verify Market Detail UI display correctly, competition name and Event Name");
        Assert.assertTrue(suspendUnsuspendMarketPage.verifymarketInfo(market.getMarketName(), "Suspended", downlineAccount, ""));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3718")
    @Test(groups = {"interaction", "nolan"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC3718(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Verify Line market in member site is suspend ");
        log("Step Precondition: Unblock an event has Line market at level control blocking");
        String sportName = "Cricket";
        AccountInfo acc = ProfileUtils.getProfile();
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Market market = BlockUnblockEventsUtils.getAnOpenLineMarket(eventList, childID, SPORT_ID.get("Cricket"), "OPEN");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, "TODAY");
        blockUnblockEventPage.blockUnblockEvent(downlineAccount, market.getEventName(), "Unblock Now");
        blockUnblockEventPage.blockUnblockEvent("", market.getEventName(), "Suspend");

        log("Step 2  Login the downline level under level control blocking");
        loginAgent(downlineAccount, password, true);

        log("Step 5 Suspend a line market in Suspend Unsuspend Market page");
        SuspendUnsuspendMarketPage suspendUnsuspendMarketPage = agentHomePage.navigateSuspendUnsuspendMarketPage();
        suspendUnsuspendMarketPage.suspendMarket("Today", sportName, market.getEventName(), market.getMarketName());

        log("Step 6 Login member site and check the line market");
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        MarketPage marketPage = sportPage.clickEventName(market.getEventName());

        log("Verify 1. Line market is unsuspend, odds is clickable");
        Assert.assertTrue(marketPage.marketOddControl.lblSuspend.isDisplayed(), "Failed the market does not suspend after suspend event in agent site");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3719")
    @Test(groups = {"interaction", "nolan"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC3719(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Verify Line market in member site is unsuspend ");
        log("Step Precondition: Unblock an event has Line market at level control blocking");
        String sportName = "Cricket";
        AccountInfo acc = ProfileUtils.getProfile();
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Market market = BlockUnblockEventsUtils.getAnOpenLineMarket(eventList, childID, "4", "OPEN");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, "TODAY");
        blockUnblockEventPage.blockUnblockEvent(downlineAccount, market.getEventName(), "Unblock Now");

        log("Step 2  Login the downline level under level control blocking");
        loginAgent(downlineAccount, password, true);

        log("Step 5 Suspend a line market in Suspend Unsuspend Market page");
        SuspendUnsuspendMarketPage suspendUnsuspendMarketPage = agentHomePage.navigateSuspendUnsuspendMarketPage();
        suspendUnsuspendMarketPage.suspendMarket("Today", sportName, market.getEventName(), market.getMarketName());
        suspendUnsuspendMarketPage.unSuspendMarket("Today", sportName, market.getEventName(), market.getMarketName());

        log("Step 6 Login member site and check the line market");
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        MarketPage marketPage = sportPage.clickEventName(market.getEventName());

        log("Verify Line market is unsuspend, odds is clickable");
        Assert.assertFalse(marketPage.marketOddControl.lblSuspend.isDisplayed(), "Failed the market does not suspend after suspend event in agent site");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3720")
//    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC3720(String downlineAccount, String memberAccount, String password) throws Exception {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }

    //
//    @Test(groups = {"interaction"})
//    @Parameters({"downlineAccount","memberAccount", "password","boAccount","bopassword"})
//    public void Agent_MM_SuspendUnsuspendMarkets_TC008(String downlineAccount, String password,String boAccount,String bopassword) throws Exception {
//        log("@title: Verify suspend market info display correctly in BO");
//        String sportName = "Cricket";
//        AccountInfo acc = ProfileUtils.getProfile();
//        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
//        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName,childID,"TODAY");
//        Market market = BlockUnblockEventsUtils.getAnOpenLineMarket(eventList,childID,"4", "OPEN");
//        if(Objects.isNull(market)){
//            log("Skip test case as no line markets for all event in "+sportName + " today");
//            return;
//        }
//
//        log("Step 1 Unblock an event that has line market in Block/Unblock event then suspend the event");
//        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
//        page.filter("", sportName,"TODAY" );
//        page.blockUnblockEvent(downlineAccount, market.getEventName(), "Unblock Now");
//        page.blockUnblockEvent("", market.getEventName(), "Suspend");
//
//        log("Step 2  Login the downline level that suspend in step 1");
//        loginAgent(downlineAccount,password,true);
//        String loginUserId = ProfileUtils.getProfile().getUserID();
//
//        log("Step 3 Active Suspend/Unsuspend Markets and unsuspend a market in event step 1");
//        SuspendUnsuspendMarketPage suspendUnsuspendMarketPage = agentHomePage.navigateSuspendUnsuspendMarketPage();
//        suspendUnsuspendMarketPage.suspendMarket("Today",sportName, market.getEventName(),market.getMarketName());
//
//        log("Step 6 Login BO > Find Block Market > Search username and event id and market id");
//        loginBackoffice(boAccount,bopassword,true);
//        FindBlockedMarketPage findBlockedMarketPage = backofficeHomePage.navigateFindBlockedMarket();
//        findBlockedMarketPage.search(downlineAccount,market.getEventID(),market.getMarketID());
//
//        log("Verify 1 Verify line market is Suspend at col Suspend/Unsuspend Markets ");
//        findBlockedMarketPage.isMarketIsSuspended(loginUserId);
//
//        log("Verify 2 Verify line market is ubblocked at col Block/Unblock markets ");
//        findBlockedMarketPage.isMarketBlocked(loginUserId,"UnBlocked");
//
//        log("INFO: Executed completely");
//    }
    @TestRails(id = "3721")
    @Test(groups = {"interaction", "nolan"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC3721(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Verify Suspend function in Block Unblock Event will override setting in Suspend/Unsuspend Market page");
        log("Step precondition 1: Login agent by direct agent at control blocking");
        String sportName = "Cricket";
        AccountInfo acc = ProfileUtils.getProfile();
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Market market = BlockUnblockEventsUtils.getAnOpenLineMarket(eventList, childID, "4", "OPEN");

        log("Step 1 Unblock an event that has line market in Block/Unblock event then suspend the event");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
        page.filter("", sportName, "TODAY");
        page.blockUnblockEvent(downlineAccount, market.getEventName(), "Unblock Now");
        page.blockUnblockEvent("", market.getEventName(), "Suspend");

        log("Step 2  Login the downline level that suspend in step 1");
        loginAgent(downlineAccount, password, true);

        log("Step 3 Active Suspend/Unsuspend Markets and unsuspend a market in event step 1");
        SuspendUnsuspendMarketPage suspendUnsuspendMarketPage = agentHomePage.navigateSuspendUnsuspendMarketPage();
        suspendUnsuspendMarketPage.suspendMarket("Today", sportName, market.getEventName(), market.getMarketName());

        log("Step 3 Login member site and active the line market");
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        MarketPage marketPage = sportPage.clickEventName(market.getEventName());

        log("Verify the line markets of the event still suspended");
        Assert.assertTrue(marketPage.marketOddControl.lblSuspend.isDisplayed(), "Failed the market does not suspend after suspend event in agent site");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3722")
//    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC322(String downlineAccount, String memberAccount, String password) throws Exception {
        //TODO: implement this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "3723")
    @Test(groups = {"interaction", "nolan"})
    @Parameters({"downlineAccount", "password"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC3723(String downlineAccount, String password) throws Exception {
        log("@title: Agent level only level under control blocking can see the menu");
        log("Step precondition: Login agent by level control blocking");
        log("Step 1 Expand  Bets/Market Management and observer the menu Suspend/Unsuspend Market page");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Step 1 Verify the menu doesnot display at control blocking level");
        Assert.assertFalse(lstSubReprotMenu.contains(SUSPEND_UNSUSPEND_MARKETS), "FAILED! Verify the menu should not display at control blocking level");

        log("Step 2 Get a direct dowline agent under login account the login to agent site");
        loginAgent(downlineAccount, password, true);
        lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Verify  2 Verify can see menu Suspend/Unsupend Market page under control blocking level");
        Assert.assertTrue(lstSubReprotMenu.contains(SUSPEND_UNSUSPEND_MARKETS), "FAILED! Verify the menu should  display at direct agent under control blocking level");
        log("INFO: Executed completely");
    }

    @TestRails(id = "72967")
    @Test(groups = {"http_request"})
    public void Agent_MM_SuspendUnsuspendMarkets_TC72967() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Navigate Markets Management > Suspend/Unsuspend Markets");
        agentHomePage.navigateSuspendUnsuspendMarketPage();
        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }
}
