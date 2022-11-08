package agentsite.testcase.all.marketsmanagement;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.all.marketsmanagement.BlockRacingPage;
import agentsite.pages.all.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.marketsmanagement.BlockingLogPage;

import java.util.List;
import java.util.Objects;

import static agentsite.common.AGConstant.HomePage.*;

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
        agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_RACING, BlockRacingPage.class);

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @Test(groups = {"interaction"})
    @Parameters({"username","downlineAccount","memberAccount","password"})
    public void Agent_MM_Block_Racing_TC006(String username,String downlineAccount, String memberAccount,String password) throws Exception {
        log("@title: Verify Venue Name display/dissapear when block/unblock");
        log("Step Precondition Get horse racing info in Block Unblock event page in Today tab");
        String sportName= "Horse Racing";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("",sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName,childID,"TODAY");
        Event event = eventList.get(0);
        String venueName = event.getEventName().split(" ")[0];
        if(Objects.isNull(eventList.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event "+event.getEventName()+" in today for "+ sportName);
        }
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(),acc.getUserID(),AGConstant.HomePage.SPORT_ID.get(sportName));
        String marketName = marketList.get(0).getMarketName();

        log("Step Precondition: Unblock a horse racing event");
        page.filter("", sportName,"Today" );
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Now");

        log("Step 1. Navigate Markets Management >Block Racing");
        BlockRacingPage blockRacingPage =agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_RACING, BlockRacingPage.class);

        log("Step 2. Active Blocking tab and select Horse Racing");
        log("Step 3. Select downline and select venue name");
        log("Step 4. Click Update button");
        blockRacingPage.block("Horse Racing",event.getCountryName(),downlineAccount,venueName,marketName);

        log("Step 5 Login member site active Horse Racing");
        loginMember(memberAccount,password);
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName,SportPage.class);
        sportPage.clickCompetition(event.getCountryCode());

        log("Verify Venus name disappear when block");
        Assert.assertFalse(sportPage.getLeftMenuList().contains(event.getEventName()),"FAILED! The venue "+ event.getEventName() + "is display in the left menu when it is blocked");

        log("Step 6: Login agent site and ublock the racing");
        loginAgent(username,password,true);

        log("Verify Venus name disappear when block");
        blockRacingPage =agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_RACING, BlockRacingPage.class);
        blockRacingPage.unblock(sportName,event.getCountryName(),downlineAccount,venueName,marketName);

        log("Verify Verify Venus name display when unblock");
        loginMember(memberAccount,password);
        sportPage = memberHomePage.navigateSportMenu(sportName,SportPage.class);
        sportPage.clickCompetition(event.getCountryCode());
        sportPage.clickEventName(event.getEventName());
        Assert.assertTrue(sportPage.getLeftMenuList().contains(event.getEventName()),"FAILED! The venue "+ event.getEventName() + "is not display in the left menu when it is unblocked");

        log("INFO: Executed completely");
    }

    @Test(groups = {"interaction"})
    @Parameters({"username","downlineAccount","memberAccount","password"})
    public void Agent_MM_Block_Racing_TC007(String username,String downlineAccount, String memberAccount,String password) throws Exception {
        log("@title: Verify Venue Name of Greyhound Racing display/disappear when block/unblock");
        log("Step Precondition Get Greyhound racing info in Block Unblock event page in Today tab");
        String sportName= "Greyhound Racing";
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("",sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName,childID,"TODAY");
        Event event = eventList.get(0);
        String venueName = event.getEventName().split(" ")[0];
        if(Objects.isNull(eventList.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event "+event.getEventName()+" in today for "+ sportName);
        }
        List<Market> marketList = BlockUnblockEventsUtils.getListMarketOfEvent(event.getID(),acc.getUserID(),AGConstant.HomePage.SPORT_ID.get(sportName));
        String marketName = marketList.get(0).getMarketName();

        log("Step Precondition: Unblock a Greyhound racing event");
        page.filter("", sportName,"Today" );
        page.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Now");

        log("Step 1. Navigate Markets Management >Block Racing");
        BlockRacingPage blockRacingPage =agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_RACING, BlockRacingPage.class);

        log("Step 2. Active Blocking tab and select Greyhound Racing");
        log("Step 3. Select downline and select venue name");
        log("Step 4. Click Update button");
        blockRacingPage.block(sportName,event.getCountryName(),downlineAccount,venueName,marketName);

        log("Step 5 Login member site active Greyhound Racing");
        loginMember(memberAccount,password);
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName,SportPage.class);
        sportPage.clickCompetition(event.getCountryCode());

        log("Verify Venus name disappear when block");
        Assert.assertFalse(sportPage.getLeftMenuList().contains(event.getEventName()),"FAILED! The venue "+ event.getEventName() + "is display in the left menu when it is blocked");

        log("Step 6: Login agent site and unblock the Greyhound racing");
        loginAgent(username,password,true);

        log("Verify Venus name disappear when block");
        blockRacingPage =agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_RACING, BlockRacingPage.class);
        blockRacingPage.unblock(sportName,event.getCountryName(),downlineAccount,venueName,marketName);

        log("Verify Verify Venus name display when unblock");
        loginMember(memberAccount,password);
        sportPage = memberHomePage.navigateSportMenu(sportName,SportPage.class);
        sportPage.clickCompetition(event.getCountryCode());
        sportPage.clickEventName(event.getEventName());
        Assert.assertTrue(sportPage.getLeftMenuList().contains(event.getEventName()),"FAILED! The venue "+ event.getEventName() + "is not display in the left menu when it is unblocked");

        log("INFO: Executed completely");
    }


}
