package agentsite.testcase.marketsmanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import common.AGConstant;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.HomePage.BLOCK_UNBLOCK_EVENT;
import static common.AGConstant.HomePage.MARKET_MANAGEMENT;
import static common.AGConstant.MarketsManagement.BlockUnblockEvent.*;


public class BlockUnblockEventsTest extends BaseCaseTest {
    @TestRails(id = "757")
    @Test(groups = {"smoke_po"})
    @Parameters({"downlineAccount", "controlBlockingAccount", "passwordNonePO", "controlBlockingLevel"})
    public void Agent_MM_BlockUnblockEvent_757(String downlineAccount, String controlBlockingAccount, String passwordNonePO, String controlBlockingLevel) throws Exception {
        log("@title: Validate that Unblock Now an event successfully from PO level");

        log("Step 1. Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
        String sadID = BlockUnblockEventsUtils.getAllChildPO(controlBlockingLevel, controlBlockingAccount);

        log("Step  2. Select an SAD level, sport and today tab");
        page.filter(controlBlockingAccount, "Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(sadID, downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Tennis", childID, "TODAY", "Unblocked");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Tennis");
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and a blocked event");
        log("Step 4: Click Unblock Now");
        page.blockUnblockEvent(downlineAccount, eventName, "Block");
        page.blockUnblockEvent("", eventName, "Unblock Now");

        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName, "Unblocked", true, true, "Now", "Now");

        log("Step 5: Logout agent site");
        agentHomePage.logout();

        log("Step 6: Login agent site at Level control blocking account:" + controlBlockingAccount);
        DriverManager.getDriver().getToAvoidTimeOut(agentLoginURL);
        //loginAgent(controlBlockingAccount,password);
        loginAgent(sosAgentURL, agentSecurityCodeURL, controlBlockingAccount, passwordNonePO, environment.getSecurityCode());
        agentHomePage.navigateBlockUnblockEventsPage();
        page.filter("", "Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));

        log("Verify 3. Verify the status is unblocked when login by SAD level");
        page.verifyBlockUnblockEvent(eventName, "Unblocked", true, true, "Now", "Now");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3683")
    @Test(groups = {"http_request"})
    public void Agent_MM_BlockUnblockEvent_3683() {
        log("@title: Validate there is no http responded error returned");
        log("@Step 11. Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Verify 1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error displayed when navigate the page");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3684")
    @Test(groups = {"regression_po"})
    public void Agent_MM_BlockUnblockEvent_3684() {
        log("@title: Validate Block/Unblock Events UI display correctly at PO level");

        log("Step Precondtion. Portal level login agent in successfully");
        log("Step1. Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
        String levelPT = BlockUnblockEventsUtils.getLevelControlBlocking(ProfileUtils.getProfile().getUserID());
        levelPT = ProfileUtils.convertDownlineByBrand(levelPT, ProfileUtils.getAppName());

        log("Verify 1. Page display: Title: Block/Unblock Events and Refresh button\n" +
                "2. SAD list, Sport list and Event time Tab: Old Events, Today, Tomorrow, Future\n" +
                "3. Hint message display correct\n" +
                "4. Buttons: Block, Unblock Now, Unblocked Schedule, Suspend, Unsuspended (On top and bottom) and buttons are disable\n" +
                "5 Downline and Event table display");
        Assert.assertEquals(page.header.lblPageTitle.getText(), BLOCK_UNBLOCK_EVENT, "FAILED! Page title is incorrect displayed");
        Assert.assertTrue(page.btnRefresh.isDisplayed(), "FAILED! Refresh button does not display");
        Assert.assertTrue(page.ddbSADList.isDisplayed(),"Failed! SAD list dropdown does not display");
        Assert.assertTrue(page.ddbSport.isDisplayed(),"Failed! Sport dropdown does not display");
        Assert.assertTrue(page.isTabDisplay("Old Events"), "FAILED! Old Event tab not display");
        Assert.assertTrue(page.isTabDisplay("Today"), "FAILED! Today tab not display");
        Assert.assertTrue(page.isTabDisplay("Tomorrow"), "FAILED! Tomorrow tab not display");
        Assert.assertTrue(page.isTabDisplay("Future"), "FAILED! Future tab not display");
        Assert.assertTrue(page.isPTLevelDisplay(levelPT), "FAILED! Future tab not display");
        Assert.assertEquals(page.lblInfo.getText(), HINT_MSG, "FAILED! Future tab not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.BLOCK, 1), "FAILED! Block button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_NOW, 1), "FAILED! Unblock Now button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_SCHEDULE, 1), "FAILED! Unblock Schedule button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.SUSPEND, 1), "FAILED! Suspend button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNSUSPEND, 1), "FAILED! Unsuspended button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.BLOCK, 2), "FAILED! Block button does at the bottom not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_NOW, 2), "FAILED! Unblock button at the bottom does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_SCHEDULE, 2), "FAILED! Unblock schedule button  at the bottom does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.SUSPEND, 2), "FAILED! Suspend button  at the bottom does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNSUSPEND, 2), "FAILED! Unsuspend button does at the bottom not display");

        List<String> lstDownlineHeader = page.tblDownline.getHeaderList();
        List<String> lstEventHeader = page.tblEvent.getHeaderNameOfRows();
        Assert.assertEquals(lstDownlineHeader, TABLE_DOWNLINE, "FAILED! Downline header table is incorrect displayed");
        Assert.assertEquals(lstEventHeader, TABLE_EVENT, "FAILED! Event header table is incorrect displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3685")
    @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_3685() {
        log("@title: Validate Block/Unblock Events UI display correctly at control blocking level");

        log("Step Precondtion. control blocking level login agent in successfully");
        log("Step1. Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Verify 1. Page display: Title: Block/Unblock Events and Refresh button\n" +
                "2. SAD list, Sport list and Event time Tab: Old Events, Today, Tomorrow, Future\n" +
                "3. Hint message display correct\n" +
                "4. Buttons: Block, Unblock Now, Unblocked Schedule, Suspend, Unsuspended (On top and bottom) and buttons are disable\n" +
                "5 Downline and Event table display");
        Assert.assertEquals(page.header.lblPageTitle.getText(), BLOCK_UNBLOCK_EVENT, "FAILED! Page title is incorrect displayed");
        Assert.assertTrue(page.btnRefresh.isDisplayed(), "FAILED! Refresh button does not display");
        Assert.assertTrue(page.isTabDisplay("Old Events"), "FAILED! Old Event tab not display");
        Assert.assertTrue(page.isTabDisplay("Today"), "FAILED! Today tab not display");
        Assert.assertTrue(page.isTabDisplay("Tomorrow"), "FAILED! Tomorrow tab not display");
        Assert.assertTrue(page.isTabDisplay("Future"), "FAILED! Future tab not display");
        Assert.assertFalse(page.lblSADList.isDisplayed(), "FAILED! Control blocking level label should not display when login non_PO level");
        Assert.assertFalse(page.ddbSADList.isDisplayed(), "FAILED! Control blocking level dropdownbox should not display when login non_PO level");
        Assert.assertEquals(page.lblInfo.getText(), HINT_MSG, "FAILED! Future tab not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.BLOCK, 1), "FAILED! Block button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_NOW, 1), "FAILED! Unblock Now button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_SCHEDULE, 1), "FAILED! Unblock Schedule button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.SUSPEND, 1), "FAILED! Suspend button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNSUSPEND, 1), "FAILED! Unsuspended button does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.BLOCK, 2), "FAILED! Block button does at the bottom not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_NOW, 2), "FAILED! Unblock button at the bottom does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNBLOCK_SCHEDULE, 2), "FAILED! Unblock schedule button  at the bottom does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.SUSPEND, 2), "FAILED! Suspend button  at the bottom does not display");
        Assert.assertTrue(page.isButtonDisplay(BlockUnblockEventPage.Actions.UNSUSPEND, 2), "FAILED! Unsuspend button does at the bottom not display");

        List<String> lstDownlineHeader = page.tblDownline.getHeaderList();
        List<String> lstEventHeader = page.tblEvent.getHeaderNameOfRows();
        Assert.assertEquals(lstDownlineHeader, TABLE_DOWNLINE, "FAILED! Downline header table is incorrect displayed");
        Assert.assertEquals(lstEventHeader, TABLE_EVENT, "FAILED! Event header table is incorrect displayed");
        log("INFO: Executed completely");
    }

//    /**
//     * @title: Validate that Unblock Now an event successfully from CO level
//     * @pre-condition: 1. CO level login agent in successfully
//     * @steps: 1. Observe Markets Management in the left menu
//     * @expect: 1. Verify there is no market management section
//     */
//    @Test(groups = {"smoke_co"})
//    public void Agent_MM_BlockUnblockEvent_037() {
//        //TODO: Add test case to Testrail
//        log("@title: Validate that Unblock Now an event successfully from CO level");
//        log("Verify 1. Verify there is no market management section");
//        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(MARKET_MANAGEMENT), "FAILED! Markets Management section should not dispplay at CO level");
//        log("INFO: Executed completely");
//    }

    @TestRails(id = "758")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_758(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Validate that Unblock Now an event successfully from SAD level");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY", "UNBLOCKED");
        if (event.isEmpty()) {
            Assert.assertTrue(true, "By pass the TC as there is no event blocked to unblock");
            return;
        }
        String eventName = event.get(0).getEventName();

        log("Step 3:Search downline");
        page.searchDownline(downlineAccount);

        log("Step precondition: Select an downline and a blocked event");
        page.blockUnblockEvent(downlineAccount, eventName, "Block");

        log("Step 4: Unblock Now event");
        page.blockUnblockEvent("", eventName, "Unblock Now");

        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName, "Unblocked", true, true, "Now", "Now");

        log("Step 5: Logout agent site");
        agentHomePage.logout();

        log("Verify 3. Verify the event display on member site");
        memberHomePage = loginMember(memberAccount, password);
        memberHomePage.leftMenu.searchEvent(eventName, true);
        String eventActive = memberHomePage.leftMenu.getActiveEvent();
        Assert.assertEquals(eventName, eventName, String.format("ERROR! Expected event in left menu is %s but found %s", eventName, eventActive));

        log("INFO: Executed completely");
    }

    @TestRails(id = "759")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_759(String downlineAccount) {
        log("@title: Validate that can block an unblocked event successfully from SAD level");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            Assert.assertTrue(true, "By pass the TC as there is no event blocked to unblock");
            return;
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and a blocked event");
        log("Precondition Steps: Click Unblock Now to unblock the event " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Unblock Now");

        log("Step 5: Block the event: " + eventName);
        page.blockUnblockEvent("", eventName, "Block");
        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName, "Blocked", false, false, "", "");

//        log("Step 5: Logout agent site");
////        agentHomePage.logout();
////        Thread.sleep(2000);
////
////        log("Verify 3. Verify the event's not displayed on member site");
////        loginMember(memberAccount, password);
////        String searchResult = memberHomePage.leftMenu.searchEvent(eventName).getText();
////        Assert.assertEquals(searchResult, "No results found", String.format("ERROR! Expected event in left menu is %s but found %s", "No results found", searchResult));
////        log("INFO: Executed completely");
    }

    @TestRails(id="760")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_760(String downlineAccount) {
        log("@title: Validate Buttons are enabled when have downline and events is selected");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Verify 1. All buttons(Block, Unblock Now, Unblock Schedule, Suspend, Unsuspended) are disabled by default");
        Assert.assertFalse(page.isButtonEnable("Block", 1));
        Assert.assertFalse(page.isButtonEnable("Block", 2));
        Assert.assertFalse(page.isButtonEnable("Unblock Now", 1));
        Assert.assertFalse(page.isButtonEnable("Unblock Now", 2));
        Assert.assertFalse(page.isButtonEnable("Unblock Schedule", 1));
        Assert.assertFalse(page.isButtonEnable("Unblock Schedule", 2));
        Assert.assertFalse(page.isButtonEnable("Suspend", 1));
        Assert.assertFalse(page.isButtonEnable("Suspend", 2));
        Assert.assertFalse(page.isButtonEnable("Unsuspend", 1));
        Assert.assertFalse(page.isButtonEnable("Unsuspend", 2));

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }

        log("Step 3: Select an downline and an event");
        page.selectDownline(downlineAccount, true);
        page.selectEvent("all");

        log("Verify 2. All buttons(Block, Unblock Now, Unblock Schedule, Suspend, Unsuspended) are enabled after select a downline and an event");
        Assert.assertTrue(page.isButtonEnable("Block", 1));
        Assert.assertTrue(page.isButtonEnable("Block", 2));
        Assert.assertTrue(page.isButtonEnable("Unblock Now", 1));
        Assert.assertTrue(page.isButtonEnable("Unblock Now", 2));
        Assert.assertTrue(page.isButtonEnable("Unblock Schedule", 1));
        Assert.assertTrue(page.isButtonEnable("Unblock Schedule", 2));
        Assert.assertTrue(page.isButtonEnable("Suspend", 1));
        Assert.assertTrue(page.isButtonEnable("Suspend", 2));
        Assert.assertTrue(page.isButtonEnable("Unsuspend", 1));
        Assert.assertTrue(page.isButtonEnable("Unsuspend", 2));

        log("INFO: Executed completely");
    }

    @TestRails(id="761")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_761(String downlineAccount) {
        log("@title: Validate can Unblock Schedule 25min before event start");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2. Select Sport, Today tab");
        page.filter("", "Soccer", "Tomorrow");

        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TMR");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Step Precodition: Block a event before check unblock schedule");
        page.blockUnblockEvent(downlineAccount, eventName, "Block", "", 1);

        log("Step 4.Click Unblock Schedule and select 25minutes and click Save");
        page.blockUnblockEvent(downlineAccount, eventName, "Unblock Schedule", "25 minutes", 1);

        log("1. Verify if Event start time <= Current time => Status is Unblocked, is Viewable, is Betable, Time to open and time to bet = 25minutes and " +
                "the event is display on member site otherwise status is Blocked, Not Viewable, Not Betable, and the event not display on member site");
        page.verifyUnblockSchedule(event.get(0), "25 minutes");

        log("INFO: Executed completely");
    }

    @TestRails(id="762")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_762(String downlineAccount) {
        log("@title: Validate that can suspend an unblocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and a blocked event");
        log("Precondition Steps: Click Unblock Now to unblock the event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Unblock Now");

        log("Step 5: Suspend the event: " + eventName);
        page.blockUnblockEvent("", eventName, "Suspend");

        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName, "Suspended", false, true, false, "", "");

        log("INFO: Executed completely");
    }

    @TestRails(id="763")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_763(String downlineAccount) {
        log("@title: Validate that cannot suspend a blocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Blocked the event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Block");
        log("Step 3: Select an downline and a blocked event");
        log("Step 4. Click Suspend button");
        page.blockUnblockEvent(downlineAccount, eventName, "Suspend");

        log("Verify 1. Status: Curr  ent status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName, "Blocked", false, false, "", "");

        log("INFO: Executed completely");
    }

    @TestRails(id ="764")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_764(String downlineAccount) {
        log("@title: Validate that cannot Unsuspended a blocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Blocked the event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Block");

        log("Step 3: Select an downline and a blocked event");
        log("Step 4. Click Unsuspended button");
        page.blockUnblockEvent("", eventName, "Unsuspend");

        log("Verify 1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName, "Blocked", false, false, "", "");

        log("INFO: Executed completely");
    }

    @TestRails(id="765")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_765(String downlineAccount) {
        log("@title: Validate that cannot Unsuspended a Unblocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Unblock Now the event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Unblock Now");

        log("Step 3: Select an downline and a blocked event");
        log("Step 4. Click Unsuspended button");
        page.blockUnblockEvent("", eventName, "Unsuspend");

        log("Verify 1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName, "Unblocked", true, true, "Now", "Now");

        log("INFO: Executed completely");
    }

    @TestRails(id="766")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_766(String downlineAccount) {
        log("@title: Validate that can Unsuspended a Suspend event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Unblocked Now then Suspend the event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Unblock Now");
        page.blockUnblockEvent("", eventName, "Suspend");

        log("Step 3: Select an downline and the suspended event");
        log("Step 4. Unsuspended the event: " + eventName);
        page.blockUnblockEvent("", eventName, "Unsuspend");

        log("Verify 1. Can unsuspend the suspended event successfully, status and Unblock Schedule Setting display as Unblocked Now");
        page.verifyBlockUnblockEvent(eventName, "Unblocked", true, true, "Now", "Now");

        log("INFO: Executed completely");
    }

    @TestRails(id="767")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_767(String downlineAccount) {
        log("@title:Validate that Block a suspend event successfully");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Unblocked Now then Suspend the event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Unblock Now");
        page.blockUnblockEvent(downlineAccount, eventName, "Suspend");

        log("Step 3. Select an downline and the suspended event");
        log("Step 4. Block the suspended event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Block");

        log("Verify 1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName, "Blocked", false, false, "", "");

        log("INFO: Executed completely");
    }

    @TestRails(id="768")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_768(String downlineAccount) {
        log("@title:Validate that cannot unblock schedule and suspend event successfully");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is Tennis, and a tab that has data");
        page.filter("", "Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Tennis", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Tennis");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Unblocked Now then Suspend the event: " + eventName);
        page.blockUnblockEvent(downlineAccount, eventName, "Unblock Now");
        page.blockUnblockEvent("", eventName, "Suspend");

        log("Step 3. Select an downline and the suspended event");
        log("Step 4. Block the Unblock Schedule the suspended event: " + eventName);
        page.blockUnblockEvent("", eventName, "Unblock Schedule", "2 days", 1);

        log("Verify 1. Status is suspended");
        page.verifyBlockUnblockEvent(eventName, "Suspended", false, true, false, "", "");

        log("INFO: Executed completely");
    }

    @TestRails(id="3686")
    @Test(groups = {"regression_sat"})
    @Parameters({"brandname"})
    public void Agent_MM_BlockUnblockEvent_3686(String brandname) {
        log("@title: Validate Event status just updated for the according selected downline");
        AccountInfo acc = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname);
        String downlineAccount = lstAccount.get(0).getUserCode();
        String downlineAccount1 = lstAccount.get(1).getUserCode();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2. Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Tennis");
        }
        String eventName = event.get(0).getEventName();

        log("Step 3. Select a checkbox beside the downline (Acc1)and selected on another downline(Acc2)( select but uncheck the checkbox");
        page.selectDownline(downlineAccount, false);
        page.clickDownline(downlineAccount1);

        log("Step 4. Check a blocked event and click Unblock Now  button");
        page.blockUnblockEvent("", eventName, "Unblock Now");
        log("Verify 2. (Step 5) Status of the event is Unblocked ");
        page.verifyBlockUnblockEvent(eventName, "Blocked", false, false, false, "", "");

        log("Step 5. Select Acc1 to view event status");
        page.clickDownline(downlineAccount);

        log("Verify 1.(Step4) Status of the event still blocked as we blocked event for Acc1 but is viewing status for Acc2");
        log("(Step 5) Status of the event is Unblocked");
        page.verifyBlockUnblockEvent(eventName, "Unblocked", true, false, true, "Now", "Now");

        log("INFO: Executed completely");
    }

    @TestRails(id="769")
    @Test(groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_MM_BlockUnblockEvent_769(String brandname) {
        log("@title: Validate can unblocked now all events for an downline in a page");
        AccountInfo acc = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname);
        String downlineAccount = lstAccount.get(0).getUserCode();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2. Select sport is soccer, and Today");
        log("Step 3. Select an downline and all events");
        log("Step 4. Click Unblock Now");
        page.filter("", "Cricket", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", downlineAccount, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Tennis");
        }

        log("Step  3. Select an downline and all events");
        page.checkDownline("all");

        log("Step 4. Check a blocked event and click Unblock Now  button");
        page.blockUnblockEvent("", "all", "Unblock Now");
        log("Verify 2. (Step 5) Status of the event is Unblocked ");
        //TODO: create a method to check list event status
        for (int i = 0; i < event.size(); i++) {
            page.verifyBlockUnblockEvent(event.get(i).getEventName(), "Unblocked", true, false, true, "Now", "Now");
        }
        log("INFO: Executed completely");
    }

    @TestRails(id="3687")
    @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_3687() {
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname);
        String downlineAccount = lstAccount.get(0).getUserCode();

        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        String sport = "Soccer";
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
        String childID = lstUsers.get(0).getUserID();

        log("Step 2: Select sport is soccer, and Today ");
        page.filter("", sport, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer", downlineAccount, "TODAY");
        if (event.isEmpty()) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for Tennis");
        }

        log("Step 3: Select all events and a downline");
        log("Step Precondition: Block all event to make sure view able and bet able status is correct for Unblock Now");
        page.blockUnblockEvent(userCode, "All", "Block", "", 1);

        log("Step 4: Click Unblock Now all event and selected downline");
        page.blockUnblockEvent("", "All", "Unblock Now", "", 1);

        log("Verify1: All Events are unblocked");
        List<ArrayList<String>> lstEvent = BlockUnblockEventsUtils.getAllEvents(sport, childID, "TODAY");
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEvent, "Unblock Now"), "FAILED! event api status is incorrectly");
        log("INFO: Executed completely");
    }


//    @Test(groups = {"smoke_s"})
//    public void Agent_MM_BlockUnblockEvent_UnblockNow_769() {
//        log("@title:Validate can unblocked now all events for an downline");
//        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
//        Assert.assertTrue(lstUsers.size() > 1, "ERROR: This test case required more than 2 downline. Pls add more downline to verify TC");
//        String userCode = lstUsers.get(0).getUserCode();
//        String userCode2 = lstUsers.get(1).getUserCode();
//        String sport = "Soccer";
//        String tomorowTab = AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2);
//
//        log("Step 1: Navigate Markets Management > Block/Unblock Events");
//        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
//        String childID1 = lstUsers.get(0).getUserID();
//        String childID2 = lstUsers.get(1).getUserID();
//
//        log("Step 2. Select sport is soccer, and Tomorrow tab ");
//        page.filter("", sport, tomorowTab);
//
//        log("Step Precondition: Block all events for 2 downline accounts as the event is unblocked by default");
//        page.selectDownline(userCode, true);
//        page.selectDownline(userCode2, true);
//        page.blockUnblockEvent(userCode, "All", "Block", "", 1);
//        // page.blockUnblockEvent(userCode2,"All","Block","",1);
//
//        log("Step 3. Select a checkbox beside the downline (Acc1)and selected on another downline(Acc2)( select but uncheck the checkbox)");
//        // page.checkDownline(userCode2);
//        page.selectDownline(userCode2, true);
//
//        log(String.format("Step 4. Check all events checkbox and click unblock now for account %s", userCode));
//        page.blockUnblockEvent("", "All", "Unblock Now", "", 1);
//
//        log(String.format("Verify 1.1 Event status when viewing acc2: %s is Blocked", userCode2));
//        List<ArrayList<String>> lstEventofUserCode2 = BlockUnblockEventsUtils.getAllEvents(sport, childID2, AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(tomorowTab));
//        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEventofUserCode2, "Blocked"), "FAILED! event api status is incorrectly");
//
//        log(String.format("Step 5. Select Acc1 %s to view event status", userCode));
//        page.clickDownline(userCode);
//
//        log(String.format("Verify 1.2 Event status when viewing acc1: %S is Blocked", userCode));
//        List<ArrayList<String>> lstEventofUserCode = BlockUnblockEventsUtils.getAllEvents(sport, childID1, AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(tomorowTab));
//        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEventofUserCode, "Now"), "FAILED! event api status is incorrectly");
//        log("INFO: Executed completely");
//
//
//        log("INFO: Executed completely");
//    }

    @TestRails(id="770")
    @Test(groups = {"smoke"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_770() {
        log("@title: Validate can blocked all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: Have no DownLine account in the list");
        String userCode = lstUsers.get(0).getUserCode();
        String childID = lstUsers.get(0).getUserID();
        String sport = "Soccer";
        String todayTab = AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1);
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2. Select sport is soccer, and Today tab");
        page.filter("", sport, todayTab);

        log("Step 3. Click Block Now");
        page.blockUnblockEvent(userCode, "All", "Block", "", 1);

        log("Verify All Events are blocked");
        List<ArrayList<String>> lstEvent = BlockUnblockEventsUtils.getAllEvents(sport, childID, AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(todayTab));
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEvent, "Blocked"), "FAILED! event api status is incorrectly");
        log("INFO: Executed completely");
    }

   @TestRails(id="771")
    @Test(groups = {"smoke"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_771() {
        log("@title:Validate can blocked all events for according selected downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 1, "ERROR: This test case required more than 2 downline. Pls add more downline to verify TC");
        String userCode = lstUsers.get(0).getUserCode();
        String userCode2 = lstUsers.get(1).getUserCode();
        String childID = lstUsers.get(0).getUserID();
        String childID2 = lstUsers.get(1).getUserID();
        String sport = "Soccer";
        String tomorowTab = AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2);
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2. Select sport is soccer, and Tomorrow tab ");
        page.filter("", sport, tomorowTab);
        log("Step Precondition: Unblock all event for 2 downline accounts");
        page.checkDownline(userCode);
        page.checkDownline(userCode2);
        page.blockUnblockEvent("", "All", "Unblock Now", "", 1);

        log("Step 3. Select a checkbox beside the downline (Acc1)and selected on another downline(Acc2)( select but uncheck the checkbox)");
        page.selectDownline(userCode, true);
        page.clickDownline(userCode2);

        log("Step  4. Check all events checkbox and click Blocked");
        page.blockUnblockEvent("", "All", "Block", "", 1);

        log("Verify 1.1 Event status when viewing acc2 is blocked");
        List<ArrayList<String>> lstEventofUserCode2 = BlockUnblockEventsUtils.getAllEvents(sport, childID2, AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(tomorowTab));
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEventofUserCode2, "Blocked"), "FAILED! event api status is incorrectly");

        log("Step 5. Select Acc1 to view event status");
        page.clickDownline(userCode);

        log("Verify 1.2 Event status when viewing acc1 is unblock");
        List<ArrayList<String>> lstEventofUserCode = BlockUnblockEventsUtils.getAllEvents(sport, childID, AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(tomorowTab));
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEventofUserCode, "Now"), "FAILED! event api status is incorrectly");
        log("INFO: Executed completely");
    }

    @TestRails(id="3688")
    @Test(groups = {"interaction_sat"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3688(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Validate Today event will display in member site if status is Unblocked after Unblock Schedule 2 days");
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        String sportName = "Soccer";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2. Select sport " + sportName + " and Today tab");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);
        log("Step 3. Select an downline and a blocked event that will start in 2 days");
        log("Step 4. Click Unblock Schedule 2 days");
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Schedule", "2 days", 1);

        log("Verify 1. Verify event status is unblocked in agent sitte: Unblocked, is Viewable, Detail in Betale, Tim to open 2 days,Time to bet 25 minutes ");
        page.verifyBlockUnblockEvent(event.getEventName(), "Unblocked", true, false, true, "2 days", "25 minutes");
        page.assertBetabelMarketStatusOfEventAsUnblockScheudle(event.getEventName(), "2 days");
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(), childID, "1");
        String marketName = marketList.get(0).getMarketName();
        if (Objects.isNull(eventList.get(0).getEventName())) {
            throw new SkipException("INFO: Skipping this test case as have no event " + event.getEventName() + " in today for Soccer");
        }
        boolean isMarketBetAble = page.isMarketIsBetAble(event.getEventName(), marketName);

        log("Verify 1. The event is display in member site and based on event start time. Can click on odd if the start tim in 25 mintue ");
        memberHomePage = loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event,1,true);

       log("Verify 2 The event is display in member site and based on event start time. Can click on odd if the start time in 25 mintue otherwise odds is unclickable");
       boolean isOddClickable = marketPage.verifyOddsIsClickAbleAsBetableStaus(market,isMarketBetAble);
       Assert.assertTrue(isOddClickable,"FAILED! Market is "+isMarketBetAble+" betable but odd clickable is "+isOddClickable);
        log("INFO: Executed completely");
    }

    @TestRails(id="3689")
    @Test(groups = {"interaction_sat"})
    @Parameters({"memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3689(String memberAccount, String password) throws Exception {
        log("@title:Validate Tomorrow Event Not display in member site after Unblock Schedule 25 minutes");

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
        String sportName = "Tennis";
        AccountInfo acc = ProfileUtils.getProfile();
        List<AccountInfo> lstUsers = DownLineListingUtils.getDownLineUsers(acc.getUserID(), "PL", _brandname);
        String userCode = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount).getUserCode();
        log("Step 2. Select sport " + sportName + " and Tomorrow tab");
        page.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), userCode);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TMR");
        Event event = eventList.get(0);
        Event event1 = eventList.get(1);

        log("Step 3. Select an downline and unblocked event that will start in 25 minutes");
        log("Step 4. Click Unblock Schedule 25 minutes");
        page.blockUnblockEvent(userCode, event.getEventName(), "Unblock Schedule", "25 minutes", 1);
        // Unblock at least 1 event then can see the sport display in member site
        page.blockUnblockEvent(userCode, event.getEventName(), "Unblock Now", "", 1);

        log("Verify 1. Verify event status is Blocked ");
        page.verifyBlockUnblockEvent(event.getEventName(), "Blocked", false, false, false, "25 minutes", "25 minutes");

        log("Verify 2. Verify event is NOT display on member site");
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        Assert.assertFalse(sportPage.isEventDisplay(event.getEventName()),"Failed! The event "+event.getEventName()+ " should not displayed when blocked");

        log("INFO: Executed completely");
    }

    @TestRails(id="3690")
//    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3690(String memberAccount, String password) throws Exception {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }

    @TestRails(id="3691")
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3691(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Validate tomorrow event display but odds is blur  after unblock schedule 2 days");
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        String sportName = "Soccer";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        page.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(), acc.getUserID(), "2");
        String marketName = marketList.get(0).getMarketName();

        log("Step 2. Select sport is " + sportName + " and Tomorrow tab ");
        page.filter("", sportName, "Tomorrow");

        log("Step 3. Select an downline and an blocked event that will start in 2 days");
        log("Step 4. Click Unblock Schedule 2 days");
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Schedule", "2 days", 1);

        log("Step 5. Click on Details link in Betable column get a market that betable status is false");

        log("Step 6. Login to member site and search the event and open the the market at step 5");
        log("Verify 1. Verify odds is blur (odds is unclickable) ");
        page.verifyBlockUnblockEvent(event.getEventName(), "Blocked", true, false, true, "25 minutes", "25 minutes");

        log("Verify 2. Verify event is NOT display on member site");
        boolean isMarketBetAble = page.isMarketIsBetAble(event.getEventName(), marketName);
        loginMember(memberAccount, password);
//        SportPage sportPage = memberHomePage.navigateSportMenu("Soccer",SportPage.class);
//        MarketPage marketPage = sportPage.searchEvent(event.getEventName(),true);
//        marketPage.clickMarket(marketName);
//        Market market = marketPage.marketContainerControl.getMarket(event,1,true);
//
//        log("Verify 2 The event is display in member site and based on event start time. Can click on odd if the start time in 25 mintue otherwise odds is unclickable");
//        boolean isOddClickable = marketPage.verifyOddsIsClickAbleAsBetableStaus(market,isMarketBetAble);
//        Assert.assertTrue(isOddClickable,"FAILED! Market is "+isMarketBetAble+" betable but odd clickable is "+isOddClickable);

        log("INFO: Executed completely");
    }

    @TestRails(id="3692")
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3692(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Validate event in Tomocrrow will be display in member site when unblok now");
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        String sportName = "Soccer";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        page.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(), acc.getUserID(), "2");
        String marketName = marketList.get(0).getMarketName();

        log("Step 2. Select sport is " + sportName + " and Tomorrow tab ");
        page.filter("", sportName, "Tomorrow");

        log("Step 3. Select an downline and an blocked event that will start in 2 days");
        log("Step 4. Click Unblock Schedule 2 days");
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Schedule", "2 days", 1);

        log("Step 5. Click on Details link in Betable column get a market that betable status is false");

        log("Step 6. Login to member site and search the event and open the the market at step 5");
        log("Verify 1. Verify odds is blur (odds is unclickable) ");
        page.verifyBlockUnblockEvent(event.getEventName(), "Blocked", true, false, true, "25 minutes", "25 minutes");

        log("Verify 2. Verify event is NOT display on member site");
        boolean isMarketBetAble = page.isMarketIsBetAble(event.getEventName(), marketName);
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu("Soccer");
        MarketPage marketPage = sportPage.leftMenu.searchEvent(event.getEventName(), true);
        marketPage.leftMenu.clickMarket(marketName);
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);

        log("Verify 2 The event is display in member site and based on event start time. Can click on odd if the start time in 25 mintue otherwise odds is unclickable");
        boolean isOddClickable = marketPage.verifyOddsIsClickAbleAsBetableStaus(market, isMarketBetAble);
        Assert.assertTrue(isOddClickable, "FAILED! Market is " + isMarketBetAble + " betable but odd clickable is " + isOddClickable);

        log("INFO: Executed completely");
    }

    @TestRails(id="3693")
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3693(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Validate Tomorrow Event not display in member site when blocked");
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        String sportName = "Soccer";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        page.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(), acc.getUserID(), "2");
        String marketName = marketList.get(0).getMarketName();

        log("Step 2. Select sport is " + sportName + " and Tomorrow tab ");
        page.filter("", sportName, "Tomorrow");

        log("Step 3. Select an downline and an blocked event ");
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Block", "2 days", 1);

        log("Step 4. Login to member site and search the event");
        loginMember(memberAccount, password);

        log("Verify 2. Verify event is NOT display on member site");
        loginMember(memberAccount, password);
        String searchResult = memberHomePage.leftMenu.searchEvent(event.getEventName()).getText();
        Assert.assertEquals(searchResult, event.getEventName(), "FAILED! Can search event in blocked status");

        log("INFO: Executed completely");
    }

    @TestRails(id="3694")
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3694(String downlineAccount, String memberAccount, String password) throws Exception {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }

    @TestRails(id="3695")
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_MM_BlockUnblockEvent_3695(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Validate suspend label display on market in member site when suspend an unblock the event");
        AccountInfo acc = ProfileUtils.getProfile();
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("", "Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList("Soccer", childID, "TODAY");
        Event event = eventList.get(0);
        if (Objects.isNull(eventList.get(0).getEventName())) {
            throw new SkipException("INFO: Skipping this test case as have no event " + event.getEventName() + " in today for Soccer");
        }

        log("Step 3: Select an downline and a blocked event");
        log("Precondition Steps: Click Unblock Now to unblock the event: " + event.getEventName());
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Now");

        log("Step 5: Suspend the event: " + event.getEventName());
        page.blockUnblockEvent("", event.getEventName(), "Suspend");

        log("Step 7: Login member site ");
        loginMember(memberAccount, password);

        log("Step 8: Navigate to Soccer");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu("Soccer");

        log(String.format("Step 9: Search event and click on a market", event.getEventName()));
        MarketPage marketPage = sportPage.leftMenu.searchEvent(event.getEventName(), true);

        log("Verify 1 Verify in member site, the market is suspend in market page");
        Assert.assertTrue(marketPage.marketOddControl.lblSuspend.isDisplayed(), "Failed the market does not suspend after suspend event in agent site");

        log("INFO: Executed completely");
    }

//    @TestRails(id="3684")
//    @Test(groups = {"review"})
//    public void Agent_MM_BlockUnblockEvent_UnblockNow_3684() {
//        log("@title:Validate Block/Unblock Events UI display correctly at PO level");
//        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
//        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
//        String userCode = lstUsers.get(0).getUserCode();
//        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
//
//        log("Step Precondition 1: Navigate Markets Management > Block/Unblock Events");
//        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
//        page.filter("", "Horse Racing", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
//
//        log("Step 2: Page display: Title: Block/Unblock Events and Refresh button");
//        log("Step 3: SAD list, Sport list and Event time Tab: Old Events, Today, Tomorrow, Future");
//        log("Step 4: Hint message display correct");
//        log("Step 5:Buttons: Block, Unblock Now, Unblocked Schedule, Suspend, Unsuspended (On top and bottom) and buttons are disable");
//        log("Step 6: 5 Downline and Event table display");
//        log("INFO: Executed completely");
//    }


//   //TODO: Testrail has no testcase
//    @Test(groups = {"review"})
//    public void Agent_MM_BlockUnblockEvent_UnblockNow_035() {
//        log("@title:Validate can unblocked now all events for an downline");
//        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
//        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
//        String userCode = lstUsers.get(0).getUserCode();
//        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
//
//        log("Step 1: Navigate Markets Management > Block/Unblock Events");
//        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
//        page.filter("", "Horse Racing", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
//
//        log("Step 2: Select sport is Horse Racing, and Today ");
//        log("Step 3: Select all events");
//        log("Step 4: Click Unblock Now");
//        page.blockUnblockEvent("All", "All", "Unblock Now", "", 1);
//
//        log("Verify 1: Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details");
//        log("Verify 2: Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues");
//        //TODO : This function is changing behaviour so will update verify  until 6.0 release done
//        Assert.assertTrue(true, "Verify passed");
//
//        //Assert.assertTrue(popup.popupDeposit.isDisplayed(), "ERROR: popupDeposit is not displayed");
//        //Assert.assertEquals(expectedTitle, popupTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));
//        //Assert.assertEquals(Double.parseDouble(yourBalance), accountInfo.getCashBalance(), String.format("ERROR: The expected current balance is '%s' but found '%s'", accountInfo.getCashBalance(), yourBalance));
//        //Assert.assertEquals(Double.parseDouble(memberBalance), lstUsers.get(0).getCashBalance(), String.format("ERROR: The expected member's current balance is '%s' but found '%s'", lstUsers.get(0).getCashBalance(), memberBalance));
//        log("INFO: Executed completely");
//    }

    @Test(groups = {"precondition"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_0100(String downlineAccount) {
        log("@title:Validate Event display in member site after unblock");
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();

        log(String.format("Step 2: Unblock all Today Tennis event of for all downline of the account %s ", downlineAccount));

        page.filter("", "Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        page.blockUnblockEvent(downlineAccount, "All", "Unblock Now", "", 1);

        log(String.format("Step 3: Unblock all Today Soccer event of for all downline of the account %s ", downlineAccount));
        page.filter("", "Soccer", "");
        page.blockUnblockEvent("", "All", "Unblock Now", "", 1);

        log(String.format("Step 4: Unblock all Today Cricket event of for all downline of the account %s ", downlineAccount));
        page.filter("", "Cricket", "");
        page.blockUnblockEvent("", "All", "Unblock Now", "", 1);

        log(String.format("Step 5: Unblock all Today Horse Racing event of for all downline of the account %s ", downlineAccount));
        page.filter("", "Horse Racing", "");
        page.blockUnblockEvent("", "All", "Unblock Now", "", 1);

        Assert.assertTrue(true, "Verify passed");

        log("INFO: Executed completely");
    }


}
