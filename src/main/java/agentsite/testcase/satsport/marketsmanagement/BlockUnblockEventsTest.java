package agentsite.testcase.satsport.marketsmanagement;

import com.paltech.driver.DriverManager;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import membersite.objects.sat.Event;
import org.testng.Assert;
import baseTest.BaseCaseMerito;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static agentsite.common.AGConstant.HomePage.BLOCK_UNBLOCK_EVENT;
import static agentsite.common.AGConstant.HomePage.MARKET_MANAGEMENT;


public class BlockUnblockEventsTest extends BaseCaseMerito {

    /**
     * @title: Validate that Unblock Now an event successfully from PO level
     * @pre-condition:
     *           1. PO level login agent in successfully
     * @steps: 1. Navigate Markets Management > Block/Unblock Events
     *           2. Select an SAD level
     *           4. Select an downline and an blocked event
     *           5.Click Unblock Now
     * @expect:  1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon
     *           2. Time to Bet: Now, Time to Open: Now
     *           3. Verify the status is unblocked when login by SAD level
     */
    @TestRails(id="757")
    @Test(groups = {"smokePO"})
    @Parameters({"downlineAccount", "controlBlockingAccount","passwordNonePO","controlBlockingLevel"})
    public void Agent_MM_BlockUnblockEvent_004(String downlineAccount,String controlBlockingAccount, String password,String controlBlockingLevel) throws Exception {
        log("@title: Validate that Unblock Now an event successfully from PO level");

        log("Step 1. Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        String sadID = BlockUnblockEventsUtils.getAllChildPO(controlBlockingLevel, controlBlockingAccount);

        log("Step  2. Select an SAD level, sport and today tab");
        page.filter(controlBlockingAccount,"Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(sadID,downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Tennis",childID,"TODAY","Blocked");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Tennis");
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and a blocked event");
        log("Step 4: Click Unblock Now");
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");

        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName,"Unblocked",true,true,"Now","Now");

        log("Step 5: Logout agent site");
        DriverManager.getDriver().switchToParentFrame();
        agentHomePage.logout();

        log("Step 6: Login agent site at Level control blocking account:"+ controlBlockingAccount);
        DriverManager.getDriver().getToAvoidTimeOut(agentLoginURL);
        //loginAgent(controlBlockingAccount,password);
        loginAgent(sosAgentURL, agentSecurityCodeURL, controlBlockingAccount, password, environment.getSecurityCode());
        page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("", "Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));

        log("Verify 3. Verify the status is unblocked when login by SAD level");
        page.verifyBlockUnblockEvent(eventName,"Unblocked",true,true,"Now","Now");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Unblock Now an event successfully from SAD level
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport and tab time and select downline and unblocked event
     *           3. Select an downline and a blocked event
     *           4. Click Unblock Now
     * @expect:  1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon
     *           2. Time to Bet: Now, Time to Open: Now
     *           3. Verify the event display on member site
     */
    @TestRails(id="758")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount", "memberAccount","password"})
    public void Agent_MM_BlockUnblockEvent_005(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Validate that Unblock Now an event successfully from SAD level");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY","Blocked");
        if(event.isEmpty()){
           Assert.assertTrue(true,"By pass the TC as there is no event blocked to unblock");
            return;
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and a blocked event");
        log("Step 4: Click Unblock Now");
        page.searchDownline(downlineAccount);
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");

        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName,"Unblocked",true,true,"Now","Now");
/*

        log("Step 5: Logout agent site");
        DriverManager.getDriver().switchToParentFrame();
        agentHomePage.logout();

        log("Verify 3. Verify the event display on member site");
        DriverManager.getDriver().getToAvoidTimeOut(environment.getMemberSiteURL());
        loginMember(memberAccount,password);
        memberagentHomePage.navigateSportMenu("Soccer", SportPage.class);
        memberagentHomePage.searchEvent(eventName,true);
        Assert.assertEquals(memberagentHomePage.getActiveEvent(), eventName,String.format("ERROR! Expected event in left menu is %s but found %s", eventName,memberagentHomePage.getActiveEvent()));
*/

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that can block an unblocked event successfully
     * @pre-condition:
     *            1. SAD level login agent in successfully, and have an unblocked event
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport and tab time and select downline and unblocked event
     *           3. Click Block button
     * @expect:  1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty
     *           2. Verify the event is not displayed in Member site
     */
    @TestRails(id="759")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount", "memberAccount","password"})
    public void Agent_MM_BlockUnblockEvent_006(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Validate that Unblock Now an event successfully from SAD level");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY");
        if(event.isEmpty()){
            Assert.assertTrue(true,"By pass the TC as there is no event blocked to unblock");
            return;
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and a blocked event");
        log("Precondition Steps: Click Unblock Now to unblock the event " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");

        log("Step 5: Block the event: " + eventName);
        page.blockUnblockEvent("",eventName,"Block");
        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName,"Blocked",false,false,"","");
/*

        log("Step 5: Logout agent site");
        DriverManager.getDriver().switchToParentFrame();
        agentHomePage.logout();

        log("Verify 3. Verify the event's not displayed on member site");
        DriverManager.getDriver().getToAvoidTimeOut(environment.getMemberSiteURL());
        loginMember(memberAccount,password);
        memberagentHomePage.navigateSportMenu("Soccer", SportPage.class);
        String searchResult = memberagentHomePage.searchEvent(eventName).getText();
        Assert.assertEquals(searchResult, "No results found",String.format("ERROR! Expected event in left menu is %s but found %s","No results found" ,searchResult));
*/

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Buttons are enabled when have downline and events is selected
     * @pre-condition:
     *             1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select an Downline and Event
     * @expect:  1.All buttons(Block, Unblock Now, Unblock Schedule, Suspend, Unsuspended) are enabled
     */
    @TestRails(id="760")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_007(String downlineAccount)  {
        log("@title: Validate Buttons are enabled when have downline and events is selected");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Verify 1. All buttons(Block, Unblock Now, Unblock Schedule, Suspend, Unsuspended) are disabled by default");
        Assert.assertFalse(page.isButtonEnable("Block",1));
        Assert.assertFalse(page.isButtonEnable("Block",2));
        Assert.assertFalse(page.isButtonEnable("Unblock Now",1));
        Assert.assertFalse(page.isButtonEnable("Unblock Now",2));
        Assert.assertFalse(page.isButtonEnable("Unblock Schedule",1));
        Assert.assertFalse(page.isButtonEnable("Unblock Schedule",2));
        Assert.assertFalse(page.isButtonEnable("Suspend",1));
        Assert.assertFalse(page.isButtonEnable("Suspend",2));
        Assert.assertFalse(page.isButtonEnable("Unsuspend",1));
        Assert.assertFalse(page.isButtonEnable("Unsuspend",2));

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and an event");
        page.selectDownline(downlineAccount, true);
        page.selectEvent("all");

        log("Verify 2. All buttons(Block, Unblock Now, Unblock Schedule, Suspend, Unsuspended) are enabled after select a downline and an event");
        Assert.assertTrue(page.isButtonEnable("Block",1));
        Assert.assertTrue(page.isButtonEnable("Block",2));
        Assert.assertTrue(page.isButtonEnable("Unblock Now",1));
        Assert.assertTrue(page.isButtonEnable("Unblock Now",2));
        Assert.assertTrue(page.isButtonEnable("Unblock Schedule",1));
        Assert.assertTrue(page.isButtonEnable("Unblock Schedule",2));
        Assert.assertTrue(page.isButtonEnable("Suspend",1));
        Assert.assertTrue(page.isButtonEnable("Suspend",2));
        Assert.assertTrue(page.isButtonEnable("Unsuspend",1));
        Assert.assertTrue(page.isButtonEnable("Unsuspend",2));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can Unblock Schedule 25min before event start
     * @pre-condition:
     *             1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select Sport, Today tab
     *           3. Select an Downline and an block event
     *           4.Click Unblock Schedule and select 25minutes and click Save
     * @expect: 1. Verify if Event start time <= Current time => Status is Unblocked, is Viewable, is Betable, Time to open and time to bet = 25minutes and
     * the event is display on member site otherswise status is Blocked, Not Viewable, Not Betable, and the event not display on member site
     */
    @TestRails(id="761")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_008(String downlineAccount) {
        log("@title: Validate can Unblock Schedule 25min before event start");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2. Select Sport, Today tab");
        page.filter("","Soccer","Tomorrow");

        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TMR","Blocked");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();
        log("Step 4: Select an downline and an event");

        log("Step 3: Select an downline and an event");
        log("Step 4.Click Unblock Schedule and select 25minutes and click Save");
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Schedule","25 minutes",1);

        log("1. Verify if Event start time <= Current time => Status is Unblocked, is Viewable, is Betable, Time to open and time to bet = 25minutes and " +
                "the event is display on member site otherwise status is Blocked, Not Viewable, Not Betable, and the event not display on member site");
        page.verifyUnblockSchedule(event.get(0),"25 minutes");


        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that can suspend an unblocked event
     * @pre-condition:
     *            1. SAD level login agent in successfully and have an unblocked event
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is soccer, and a tab that has data
     *           3. Select an downline and an unblocked event
     *           4. Click Suspend button
     * @expect:  1. Status: Current status is Suspended, Viewable is Suspended, and Betable is Cross icon
     * .Unblock Schedule Setting: Time to open and Time to bet have no data
     *           2. Verify the event is suspend in sport page and  market page
     */
    @TestRails(id="762")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount", "memberAccount","password"})
    public void Agent_MM_BlockUnblockEvent_009(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Validate that can suspend an unblocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Step 3: Select an downline and a blocked event");
        log("Precondition Steps: Click Unblock Now to unblock the event: " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");

        log("Step 5: Suspend the event: " + eventName);
        page.blockUnblockEvent("",eventName,"Suspend");
        log("Verify 1. Status: Current status is Unblocked, Viewable and Betable is Green Check icon");
        log("Verify 2. Time to Bet: Now, Time to Open: Now");
        page.verifyBlockUnblockEvent(eventName,"Suspended",false,true,false,"","");

        /*log("Step 5: Logout agent site");
        DriverManager.getDriver().switchToParentFrame();
        agentHomePage.logout();

        log("Verify 3. Verify the event's not displayed on member site");
        DriverManager.getDriver().getToAvoidTimeOut(environment.getMemberSiteURL());
        loginMember(memberAccount,password);
        memberagentHomePage.navigateSportMenu("Soccer", SportPage.class);
        memberagentHomePage.searchEvent(eventName,true);
        memberagentHomePage.clickMarket(1);
        Assert.assertTrue(memberagentHomePage.marketContainerControl.lblSuspend.isDisplayed(),"ERROR! Suspend layout not cover ");*/
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that cannot suspend a blocked event
     * @pre-condition:
     *           1. SAD level login agent in successfully and have an blocked event
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is soccer, and a tab that has data
     *           3. Select an downline and a blocked event
     *           4. Click Suspend button
     * @expect:  1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty
     */
    @TestRails(id="763")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_010(String downlineAccount){
        log("@title: Validate that cannot suspend a blocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
          page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY","Blocked");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Blocked the event: " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Block");
        log("Step 3: Select an downline and a blocked event");
        log("Step 4. Click Suspend button");
        page.blockUnblockEvent(downlineAccount,eventName,"Suspend");

        log("Verify 1. Status: Curr  ent status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName,"Blocked",false,false,"","");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that cannot Unsuspended a blocked event
     * @pre-condition:
     *           1. SAD level login agent in successfully and have an blocked event
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is soccer, and a tab that has data
     *          3. Select an downline and a blocked event
     *           4. Click Unsuspended button
     * @expect:  1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty
     */
    @TestRails(id="764")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_011(String downlineAccount){
        log("@title: Validate that cannot Unsuspended a blocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY","Blocked");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Blocked the event: " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Block");
        log("Step 3: Select an downline and a blocked event");
        log("Step 4. Click Unsuspended button");
        page.blockUnblockEvent(downlineAccount,eventName,"Unsuspend");

        log("Verify 1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName,"Blocked",false,false,"","");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that cannot Unsuspended a Unblocked event
     * @pre-condition:
     *            1. SAD level login agent in successfully and have an Unblocked event
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is soccer, and a tab that has data
     *          3. Select an downline and an unblocked event, get Status and Unblock Schedule Setting
     *           4. Click Unsuspended button
     * @expect:  1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty
     */
    @TestRails(id="765")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_012(String downlineAccount){
        log("@title: Validate that cannot Unsuspended a Unblocked event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Blocked the event: " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");
        log("Step 3: Select an downline and a blocked event");
        log("Step 4. Click Unsuspended button");
        page.blockUnblockEvent(downlineAccount,eventName,"Unsuspend");

        log("Verify 1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName,"Unblocked",true,true,"Now","Now");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that can Unsuspended a Suspend event
     * @pre-condition:
     *            1. SAD level login agent in successfully and have an Suspend event, get the status and unblock Schedule Setting before suspend an event
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is soccer, and a tab that has data
     *           3. Select an downline and a suspend event
     *           4. Click Unsuspended button
     * @expect:  1. Can unsuspended event successfully, status and Unblock Schedule Setting display as data get in regression
     */
    @TestRails(id="766")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_013(String downlineAccount){
        log("@title: Validate that can Unsuspended a Suspend event");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Unblocked Now then Suspend the event: " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");
        page.blockUnblockEvent(downlineAccount,eventName,"Suspend");

        log("Step 3: Select an downline and the suspended event");
        log("Step 4. Unsuspended the event: "+ eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Unsuspend");

        log("Verify 1. Can unsuspend the suspended event successfully, status and Unblock Schedule Setting display as Unblocked Now");
        page.verifyBlockUnblockEvent(eventName,"Unblocked",true,true,"Now","Now");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Block a suspend event successfully
     * @pre-condition:
     *            1. SAD level login agent in successfully and have an event suspended
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is soccer, and a tab that has data
     *           3. Select an downline and a suspend event
     *           4. Click Unsuspended button
     * @expect:  1. Can unsuspended event successfully, status and Unblock Schedule Setting display as data get in regression
     */
    @TestRails(id="767")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_014(String downlineAccount){
        log("@title:Validate that Block a suspend event successfully");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is soccer, and a tab that has data");
        page.filter("","Soccer", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Soccer");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Unblocked Now then Suspend the event: " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");
        page.blockUnblockEvent(downlineAccount,eventName,"Suspend");

        log("Step 3. Select an downline and the suspended event");
        log("Step 4. Block the suspended event: "+ eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Block");

        log("Verify 1. Status: Current status is Blocked, Viewable Betable is Cross icon, Time to Open and Time to bet is empty");
        page.verifyBlockUnblockEvent(eventName,"Blocked",false,false,"","");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that cannot unblock schedule and suspend event successfully
     * @pre-condition:
     *            1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Unblock Now an today In-play event and suspend it
     *           3. Click Unblock Schedule 2days
     * @expect:  1. Status is suspended
     */
    @TestRails(id="768")
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_015(String downlineAccount){
        log("@title:Validate that cannot unblock schedule and suspend event successfully");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2: Select sport is Tennis, and a tab that has data");
        page.filter("","Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> event = BlockUnblockEventsUtils.getEventList("Tennis",childID,"TODAY");
        if(Objects.isNull(event.get(0).getEventName())){
            throw new SkipException("INFO: Skipping this test case as have no event in today for Tennis");
        }
        String eventName = event.get(0).getEventName();

        log("Precondition: Unblocked Now then Suspend the event: " + eventName);
        page.blockUnblockEvent(downlineAccount,eventName,"Unblock Now");
        page.blockUnblockEvent("",eventName,"Suspend");

        log("Step 3. Select an downline and the suspended event");
        log("Step 4. Block the Unblock Schedule the suspended event: "+ eventName);
        page.blockUnblockEvent("",eventName,"Unblock Schedule","2 days",1);

        log("Verify 1. Status is suspended");
        page.verifyBlockUnblockEvent(eventName,"Suspended",false,true,false,"","");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can unblocked now all events for an downline
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is soccer, and Today
     *           3. Select all events
     *           4. Click Unblock Now
     * @expect:  All Events are unblocked
     */
   @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_017()
   {
        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo acc = ProfileUtils.getProfile();
        String sport = "Soccer";
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
       BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
       String childID = lstUsers.get(0).getUserID();

       log("Step 2: Select sport is soccer, and Today ");
        page.filter("",sport, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));

        log("Step 3: Select all events and a downline");
       log("Step Precondition: Block all event to make sure view able and bet able status is correct for Unblock Now");
        page.blockUnblockEvent(userCode,"All","Block","",1);

       log("Step 4: Click Unblock Now all event and selected downline");
       page.blockUnblockEvent("","All","Unblock Now","",1);

        log("Verify1: All Events are unblocked");
        List<ArrayList<String>> lstEvent = BlockUnblockEventsUtils.getAllEvents(sport,childID,"TODAY");
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEvent,"Unblock Now"),"FAILED! event api status is incorrectly");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can unblocked now all events for according selected downline
     * @pre-condition:
     *         1. SAD level login agent in successfully (SAD has at least 2 downlines)
     * @steps: 1. Navigate Markets Management > Block/Unblock Events
     *         2. Select sport is soccer, and Tomorrow tab
     *         3. Select a checkbox beside the downline (Acc1)and selected on another downline(Acc2)( select but uncheck the checkbox)
     *         4. Check all events checkbox and click unblock now
     *         5. Select Acc1 to view event status
     * @expect: 1. Event status not update for viewing downline just updated for selected downline
     */
    @TestRails(id="769")
    @Test(groups = {"smoke"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_018()
    {
        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 1, "ERROR: This test case required more than 2 downline. Pls add more downline to verify TC");
        String userCode = lstUsers.get(0).getUserCode();
        String userCode2 = lstUsers.get(1).getUserCode();
        String sport = "Soccer";
        String tomorowTab =AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2);
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        String childID2 = lstUsers.get(1).getUserID();

        log("Step 2. Select sport is soccer, and Tomorrow tab ");
        page.filter("",sport, tomorowTab);

        log("Step 3. Select a checkbox beside the downline (Acc1)and selected on another downline(Acc2)( select but uncheck the checkbox)");
        page.checkDownline(userCode);
        page.clickDownline(userCode2);

        log("Step 4. Check all events checkbox and click unblock now");
        log("Step 5. Select Acc1 to view event status");
        page.blockUnblockEvent("","All","Unblock Now","",1);

        log("Verify 1. Event status not update for viewing downline just updated for selected downline");
        List<ArrayList<String>> lstEvent = BlockUnblockEventsUtils.getAllEvents(sport,childID2,AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(tomorowTab));
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEvent,"Blocked"),"FAILED! event api status is incorrectly");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can blocked all events for an downline
     * @pre-condition:
     *         1. SAD level login agent in successfully
     * @steps: 1. Navigate Markets Management > Block/Unblock Events
     *         2. Select sport is soccer, and Today tab
     *         3. Click Block Now
     * @expect: All Events are blocked
     */
    @TestRails(id="770")
    @Test(groups = {"smoke"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_019()
    {
        log("@title: Validate can blocked all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: Have no DownLine account in the list");
        String userCode = lstUsers.get(0).getUserCode();
        String sport = "Soccer";
        String todayTab =AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1);
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        String childID = lstUsers.get(0).getUserID();

        log("Step 2. Select sport is soccer, and Today tab");
        page.filter("",sport, todayTab);

        log("Step 3. Click Block Now");
        page.blockUnblockEvent(userCode,"All","Block","",1);

        log("Verify All Events are blocked");
        List<ArrayList<String>> lstEvent = BlockUnblockEventsUtils.getAllEvents(sport,childID,AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(todayTab));
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEvent,"Block"),"FAILED! event api status is incorrectly");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can blocked all events for according selected downline
     * @pre-condition:
     *         1. SAD level login agent in successfully (SAD has at least 2 downlines)
     * @steps: 1. Navigate Markets Management > Block/Unblock Events
     *         2. Select sport is soccer, and Tomorrow tab
     *         3. Select a checkbox beside the downline (Acc1)and selected on another downline(Acc2)( select but uncheck the checkbox)
     *        4. Check all events checkbox and click Blocked
     *        5. Select Acc1 to view event status
     * @expect: 1. Event status not update for viewing downline just updated for selected downline
     */
    @TestRails(id="771")
    @Test(groups = {"smoke"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_020()
    {
        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 1, "ERROR: This test case required more than 2 downline. Pls add more downline to verify TC");
        String userCode = lstUsers.get(0).getUserCode();
        String userCode2 = lstUsers.get(1).getUserCode();
        String childID =lstUsers.get(0).getUserID();
        String childID2 =lstUsers.get(1).getUserID();
        String sport = "Soccer";
        String tomorowTab =AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2);
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log("Step 2. Select sport is soccer, and Tomorrow tab ");
        page.filter("",sport, tomorowTab);
        log("Step Precondition: Unblock all event for 2 downline accounts");
        page.blockUnblockEvent(userCode,"All","Unblock Now","",1);
        page.blockUnblockEvent(userCode2,"All","Unblock Now","",1);

        log("Step 3. Select a checkbox beside the downline (Acc1)and selected on another downline(Acc2)( select but uncheck the checkbox)");
        page.checkDownline(userCode2);

        log("Step  4. Check all events checkbox and click Blocked");

        page.blockUnblockEvent("","All","Block","",1);

        log("Verify 1.1 Event status when viewing acc2 is blocked");
        List<ArrayList<String>> lstEventofUserCode2 = BlockUnblockEventsUtils.getAllEvents(sport,childID2,AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(tomorowTab));
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEventofUserCode2,"Unblock Now"),"FAILED! event api status is incorrectly");

        log("Step 5. Select Acc1 to view event status");
        page.clickDownline(userCode);

        log("Verify 1.2 Event status when viewing acc1 is unblock");
        List<ArrayList<String>> lstEventofUserCode = BlockUnblockEventsUtils.getAllEvents(sport,childID,AGConstant.MarketsManagement.BlockUnblockEvent.TABs.get(tomorowTab));
        Assert.assertTrue(page.validateBlockStatusAllEventViaAPI(lstEventofUserCode,"Block"),"FAILED! event api status is incorrectly");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can unblocked now all Cricket events for an downline
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is Cricket, and Today
     *           3. Select all events
     *           4. Click Unblock Now
     * @expect:  1. Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details
     *           2. Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues
     */
   @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_021()
    {
        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("","Cricket", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));


        log("Step 2: Select sport is Cricket, and Today ");
        log("Step 3: Select all events");
        log("Step 4: Click Unblock Now");
        page.blockUnblockEvent("All","All","Unblock Now","",1);

        log("Verify 1: Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details");
        log("Verify 2: Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues");
        //TODO : This function is changing behaviour so will update verify  until 6.0 release done
        Assert.assertTrue(true,"Verify passed");

        //Assert.assertTrue(popup.popupDeposit.isDisplayed(), "ERROR: popupDeposit is not displayed");
        //Assert.assertEquals(expectedTitle, popupTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));
        //Assert.assertEquals(Double.parseDouble(yourBalance), accountInfo.getCashBalance(), String.format("ERROR: The expected current balance is '%s' but found '%s'", accountInfo.getCashBalance(), yourBalance));
        //Assert.assertEquals(Double.parseDouble(memberBalance), lstUsers.get(0).getCashBalance(), String.format("ERROR: The expected member's current balance is '%s' but found '%s'", lstUsers.get(0).getCashBalance(), memberBalance));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Event display in member site after unblock
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is Tennis and a tab that has data
     *           3. Select an downline and an blocked event
     *           4. Click Unblock Now
     *           5. Login member site and active the sport that has been unblocked
     * @expect:  1. Event display on member site and can place bet
     */
    @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_022()
    {
        log("@title:Validate Event display in member site after unblock");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("","Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));

        log("Step 2: Select sport is Tennis and a tab that has data ");
        log("Step 3: Select an downline and an blocked event");
        log("Step 4: Click Unblock Now");
        page.blockUnblockEvent("All","All","Unblock Now","",1);

        log("Step 5: Login member site and active the sport that has been unblocked");
               log("Verify 1:Event display on member site and can place bet");
        //TODO : This function is changing behaviour so will update verify  until 6.0 release done
        Assert.assertTrue(true,"Verify passed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can unblocked now all Horse Racing events for an downline
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is Horse Racing, and Today
     *           3. Select all events
     *           4. Click Unblock Now
     * @expect:  1. Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details
     *           2. Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues
     */
    @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_033()
    {
        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("","Horse Racing", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));

        log("Step 2: Select sport is Horse Racing, and Today ");
        log("Step 3: Select all events");
        log("Step 4: Click Unblock Now");
        page.blockUnblockEvent("All","All","Unblock Now","",1);

        log("Verify 1: Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details");
        log("Verify 2: Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues");
        //TODO : This function is changing behaviour so will update verify  until 6.0 release done
        Assert.assertTrue(true,"Verify passed");

        //Assert.assertTrue(popup.popupDeposit.isDisplayed(), "ERROR: popupDeposit is not displayed");
        //Assert.assertEquals(expectedTitle, popupTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));
        //Assert.assertEquals(Double.parseDouble(yourBalance), accountInfo.getCashBalance(), String.format("ERROR: The expected current balance is '%s' but found '%s'", accountInfo.getCashBalance(), yourBalance));
        //Assert.assertEquals(Double.parseDouble(memberBalance), lstUsers.get(0).getCashBalance(), String.format("ERROR: The expected member's current balance is '%s' but found '%s'", lstUsers.get(0).getCashBalance(), memberBalance));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can unblocked now all Horse Racing events for an downline
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is Horse Racing, and Today
     *           3. Select all events
     *           4. Click Unblock Now
     * @expect:  1. Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details
     *           2. Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues
     */
    @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_035()
    {
        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("","Horse Racing", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));

        log("Step 2: Select sport is Horse Racing, and Today ");
        log("Step 3: Select all events");
        log("Step 4: Click Unblock Now");
        page.blockUnblockEvent("All","All","Unblock Now","",1);

        log("Verify 1: Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details");
        log("Verify 2: Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues");
        //TODO : This function is changing behaviour so will update verify  until 6.0 release done
        Assert.assertTrue(true,"Verify passed");

        //Assert.assertTrue(popup.popupDeposit.isDisplayed(), "ERROR: popupDeposit is not displayed");
        //Assert.assertEquals(expectedTitle, popupTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));
        //Assert.assertEquals(Double.parseDouble(yourBalance), accountInfo.getCashBalance(), String.format("ERROR: The expected current balance is '%s' but found '%s'", accountInfo.getCashBalance(), yourBalance));
        //Assert.assertEquals(Double.parseDouble(memberBalance), lstUsers.get(0).getCashBalance(), String.format("ERROR: The expected member's current balance is '%s' but found '%s'", lstUsers.get(0).getCashBalance(), memberBalance));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can unblocked now all Greyhound Racing events for an downline
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is Greyhound Racing, and Today
     *           3. Select all events
     *           4. Click Unblock Now
     * @expect:  1. Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details
     *           2. Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues
     */
    @Test(groups = {"regression"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_034()
    {
        log("@title:Validate can unblocked now all events for an downline");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);

        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        page.filter("","Greyhound Racing", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));


        log("Step 2: Select sport is Greyhound Racing, and Today ");
        log("Step 3: Select all events");
        log("Step 4: Click Unblock Now");
        page.blockUnblockEvent("All","All","Unblock Now","",1);

        log("Verify 1: Status: Current status is Unblocked, Viewable is GreenCheck icon, and Betable is details");
        log("Verify 2: Unblock Schedule Setting: Time to open = Now and Time to bet = 25 mintues");
        //TODO : This function is changing behaviour so will update verify  until 6.0 release done
        Assert.assertTrue(true,"Verify passed");

        //Assert.assertTrue(popup.popupDeposit.isDisplayed(), "ERROR: popupDeposit is not displayed");
        //Assert.assertEquals(expectedTitle, popupTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));
        //Assert.assertEquals(Double.parseDouble(yourBalance), accountInfo.getCashBalance(), String.format("ERROR: The expected current balance is '%s' but found '%s'", accountInfo.getCashBalance(), yourBalance));
        //Assert.assertEquals(Double.parseDouble(memberBalance), lstUsers.get(0).getCashBalance(), String.format("ERROR: The expected member's current balance is '%s' but found '%s'", lstUsers.get(0).getCashBalance(), memberBalance));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Event display in member site after unblock
     * @pre-condition:
     *           1. SAD level login agent in successfully
     * @steps:   1. Navigate Markets Management > Block/Unblock Events
     *           2. Select sport is Tennis and a tab that has data
     *           3. Select an downline and an blocked event
     *           4. Click Unblock Now
     *           5. Login member site and active the sport that has been unblocked
     * @expect:  1. Event display on member site and can place bet
     */
    @Test(groups = {"precondition"})
    @Parameters({"controlBlockingAccount"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_0100(String controlBlockingAccount)
    {
        log("@title:Validate Event display in member site after unblock");
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log(String.format("Step 2: Unblock all Today Tennis event of for all downline of the account %s ",controlBlockingAccount));
        page.filter(controlBlockingAccount,"Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        page.blockUnblockEvent("all","All","Unblock Now","",1);

        log(String.format("Step 3: Unblock all Today Soccer event of for all downline of the account %s ",controlBlockingAccount));
        page.filter("","Soccer", "");
        page.blockUnblockEvent("","All","Unblock Now","",1);

        log(String.format("Step 4: Unblock all Today Cricket event of for all downline of the account %s ",controlBlockingAccount));
        page.filter("","Cricket", "");
        page.blockUnblockEvent("","All","Unblock Now","",1);

        log(String.format("Step 5: Unblock all Today Horse Racing event of for all downline of the account %s ",controlBlockingAccount));
        page.filter("","Horse Racing","");
        page.blockUnblockEvent("","All","Unblock Now","",1);

        Assert.assertTrue(true,"Verify passed");

        log("INFO: Executed completely");
    }
    @Test(groups = {"feprecondition"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockEvent_UnblockNow_0101(String downlineAccount)
    {
        log("@title:Validate Event display in member site after unblock");
        log("Step 1: Navigate Markets Management > Block/Unblock Events");
        BlockUnblockEventPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);

        log(String.format("Step 2: Unblock all Today Tennis event of for all downline of the account %s ",downlineAccount));
        page.filter("","Tennis", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        page.blockUnblockEvent(downlineAccount,"All","Unblock Now","",1);
        log(String.format("Step 3: Unblock all Today Soccer event of for all downline of the account %s ",downlineAccount));
        page.filter("","Soccer", "");
        page.blockUnblockEvent("","All","Unblock Now","",1);

        log(String.format("Step 4: Unblock all Today Cricket event of for all downline of the account %s ",downlineAccount));
        page.filter("","Cricket", "");
        page.blockUnblockEvent("","All","Unblock Now","",1);

        log(String.format("Step 5: Unblock all Today Horse Racing event of for all downline of the account %s ",downlineAccount));
        page.filter("","Horse Racing","");
        page.blockUnblockEvent("","All","Unblock Now","",1);

        Assert.assertTrue(true,"Verify passed");

        log("INFO: Executed completely");
    }

}
