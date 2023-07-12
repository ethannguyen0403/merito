package agentsite.testcase.marketsmanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.marketsmanagement.BlockRacingPage;
import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.SportPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

public class BlockRacingTest extends BaseCaseTest {
    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Block Racing
     * @expect: 1. Verify there is no console error display
     */
    @TestRails(id = "772")
    @Test(groups = {"http_request"})
    public void Agent_MM_Block_Racing_TC772() {
        log("@title: Validate there is no http responded error returned");

        log("Step 1. Navigate Markets Management >Block Racing");
        agentHomePage.navigateBlockRacingPage();

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3700")
    @Test(groups = {"interaction"})
    @Parameters({"username", "downlineAccount", "memberAccount", "password"})
    public void Agent_MM_Block_Racing_TC3700(String username, String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Verify Venue Name display/dissapear when block/unblock");
        log("Step Precondition Get horse racing info in Block Unblock event page in Today tab");
        String sportName = "Horse Racing";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
        page.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);
        String venueName = event.getEventName().split(" ")[0];
        if (Objects.isNull(eventList.get(0).getEventName())) {
            throw new SkipException("INFO: Skipping this test case as have no event " + event.getEventName() + " in today for " + sportName);
        }
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(), acc.getUserID(), AGConstant.HomePage.SPORT_ID.get(sportName));
        String marketName = marketList.get(0).getMarketName();

        log("Step Precondition: Unblock a horse racing event");
        page.filter("", sportName, "Today");
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Now");

        log("Step 1. Navigate Markets Management >Block Racing");
        BlockRacingPage blockRacingPage = agentHomePage.navigateBlockRacingPage();

        log("Step 2. Active Blocking tab and select Horse Racing");
        log("Step 3. Select downline and select venue name");
        log("Step 4. Click Update button");
        blockRacingPage.block("Horse Racing", event.getCountryName(), downlineAccount, venueName, marketName);

        log("Step 5 Login member site active Horse Racing");
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        sportPage.leftMenu.clickCompetition(event.getCountryCode());

        log("Verify Venus name disappear when block");
        Assert.assertFalse(sportPage.leftMenu.getLeftMenuList().contains(event.getEventName()), "FAILED! The venue " + event.getEventName() + "is display in the left menu when it is blocked");

        log("Step 6: Login agent site and ublock the racing");
        loginAgent(username, password, true);

        log("Verify Venus name disappear when block");
        blockRacingPage = agentHomePage.navigateBlockRacingPage();
        blockRacingPage.unblock(sportName, event.getCountryName(), downlineAccount, venueName, marketName);

        log("Verify Verify Venus name display when unblock");
        loginMember(memberAccount, password);
        sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        sportPage.leftMenu.clickCompetition(event.getCountryCode());
        sportPage.clickEventName(event.getEventName());
        Assert.assertTrue(sportPage.leftMenu.getLeftMenuList().contains(event.getEventName()), "FAILED! The venue " + event.getEventName() + "is not display in the left menu when it is unblocked");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3701")
    @Test(groups = {"interaction"})
    @Parameters({"username", "downlineAccount", "memberAccount", "password"})
    public void Agent_MM_Block_Racing_TC3701(String username, String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Verify Venue Name of Greyhound Racing display/disappear when block/unblock");
        log("Step Precondition Get Greyhound racing info in Block Unblock event page in Today tab");
        String sportName = "Greyhound Racing";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.navigateBlockUnblockEventsPage();
        page.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);
        String venueName = event.getEventName().split(" ")[0];
        if (Objects.isNull(eventList.get(0).getEventName())) {
            throw new SkipException("INFO: Skipping this test case as have no event " + event.getEventName() + " in today for " + sportName);
        }
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(), acc.getUserID(), AGConstant.HomePage.SPORT_ID.get(sportName));
        String marketName = marketList.get(0).getMarketName();

        log("Step Precondition: Unblock a Greyhound racing event");
        page.filter("", sportName, "Today");
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Now");

        log("Step 1. Navigate Markets Management >Block Racing");
        BlockRacingPage blockRacingPage = agentHomePage.navigateBlockRacingPage();

        log("Step 2. Active Blocking tab and select Greyhound Racing");
        log("Step 3. Select downline and select venue name");
        log("Step 4. Click Update button");
        blockRacingPage.block(sportName, event.getCountryName(), downlineAccount, venueName, marketName);

        log("Step 5 Login member site active Greyhound Racing");
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        sportPage.leftMenu.clickCompetition(event.getCountryCode());

        log("Verify Venus name disappear when block");
        Assert.assertFalse(sportPage.leftMenu.getLeftMenuList().contains(event.getEventName()), "FAILED! The venue " + event.getEventName() + "is display in the left menu when it is blocked");

        log("Step 6: Login agent site and unblock the Greyhound racing");
        loginAgent(username, password, true);

        log("Verify Venus name disappear when block");
        blockRacingPage = agentHomePage.navigateBlockRacingPage();
        blockRacingPage.unblock(sportName, event.getCountryName(), downlineAccount, venueName, marketName);

        log("Verify Verify Venus name display when unblock");
        loginMember(memberAccount, password);
        sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        sportPage.leftMenu.clickCompetition(event.getCountryCode());
        sportPage.clickEventName(event.getEventName());
        Assert.assertTrue(sportPage.leftMenu.getLeftMenuList().contains(event.getEventName()), "FAILED! The venue " + event.getEventName() + "is not display in the left menu when it is unblocked");

        log("INFO: Executed completely");
    }


}
