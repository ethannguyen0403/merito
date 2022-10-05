package agentsite.testcase.satsport.marketsmanagement;

import com.paltech.driver.DriverManager;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import membersite.objects.sat.Event;
import org.testng.Assert;
import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.marketsmanagement.BlockUnblockCompetitionPage;
import agentsite.pages.all.marketsmanagement.BlockUnblockEventPage;
import agentsite.pages.all.marketsmanagement.CurrentBlockingPage;
import agentsite.pages.all.marketsmanagement.currentblocking.BlockedUserPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import agentsite.ultils.maketmanagement.CurrentBlockingUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;

public class CurrentBlockingTest extends BaseCaseMerito {
    /**
     * @title: Validate Current Blocking UI display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Markets Management > Current Blocking
     * 2. Select  Type = Event, Sport = Soccer
     * @expect: 1. Verify Current Blocking UI display correctly
     */
    @Test(groups = {"smoke"})
    public void Agent_MM_CurrentBlocking_TC002() {
        log("@title: Validate Current Blocking UI display correctly");

        log("Step 1. Navigate Markets Management > Current Blocking");
        CurrentBlockingPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, CURRENT_BLOCKING, CurrentBlockingPage.class);

        log("Step 2. Select  Type = Event, Sport = Soccer");
        page.filter("Event","Soccer","");

        log("Verify 1. Verify UI display correct when select Event type");
        Assert.assertEquals(page.lblType.getText(), AGConstant.MarketsManagement.CurrentBlocking.LBL_TYPE);
        Assert.assertTrue(page.ddbType.isDisplayed(),"FAILED! Type dropdown not exist");
        Assert.assertEquals(page.lblSport.getText(), AGConstant.MarketsManagement.CurrentBlocking.LBL_SPORT);
        Assert.assertTrue(page.ddbSport.isDisplayed(),"FAILED! Sport dropdown not exist");
        Assert.assertTrue(page.tabOldEvents.isDisplayed(),"FAILED! Old Events tab should  display when select Event");
        Assert.assertTrue(page.tabToday.isDisplayed(),"FAILED! Today tab  tab should display when select Event");
        Assert.assertTrue(page.tabTomorrow.isDisplayed(),"FAILED! Tomorrow tab should display when select Event");
        Assert.assertTrue(page.tabFuture.isDisplayed(),"FAILED! Future tab tab should  display when select Event");
        Assert.assertEquals(page.tblEvent.getColumnNamesOfTable(), AGConstant.MarketsManagement.CurrentBlocking.TABLE_EVENT,"FAILED! Header table not match with the expected when selection Event Type");
        log("INFO: Executed completely");
    }


    /**
     * @title: Verify can view blocked user for an event
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Markets Management > Current Blocking
     *           2. Select  Type = Event, Sport = Soccer, and Today tab
     *           3. Select an event name and click on Current cell
     * @expect:  1. Verify Blocked User popup display, UI display correctly
     *           2. The number blocked in the list matched with the number in current column
     */
    @Test(groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_MM_CurrentBlocking_TC003(String downlineAccount) {
        log("@title: Verify can view blocked user for an event");
        log("Step 1. Navigate Markets Management > Current Blocking");
        String userID = ProfileUtils.getProfile().getUserID();
        CurrentBlockingPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, CURRENT_BLOCKING, CurrentBlockingPage.class);

        log("Step 2. Select  Type = Event, Sport = Soccer, and Today tab");
        page.filter("Event","Soccer","Today");

        log("Step 3. Select an event name and click on Current cell");
        String eventName = CurrentBlockingUtils.getFirstEventName(userID,"1","TODAY");
        String eventID = CurrentBlockingUtils.getEventID(userID,"1","TODAY",eventName);
        String currentBlockingUserCount = Integer.toString(CurrentBlockingUtils.getCurrentBlockingUser(eventID).size());

        List<ArrayList<String>> info = page.tblEvent.getRowsWithoutHeader(1,false);
        String expectedCompetition = info.get(0).get(page.colCompetitionName-1);
        String expecteEventName = info.get(0).get(1).trim();
        String expectedCurrent = info.get(0).get(page.colCurrent-1).trim();
        BlockedUserPopup popup = page.openBlockedUser(1);
        String actualEvent = String.format("%s: %s", AGConstant.MarketsManagement.BlockedUserPopup.LBL_EVENT,expecteEventName);
        String blockedUserCount = CurrentBlockingUtils.getBlockedUserCount(userID,"1","TODAY",eventName);

        log("Verify 1. Verify Blocked User popup display, UI display correctly");
        Assert.assertEquals( popup.lblTitle.getText(), AGConstant.MarketsManagement.BlockedUserPopup.TITLE_PAGE,"FAILED! Title is incorrect");
        Assert.assertEquals(popup.lblCompetitionName.getText(), String.format("%s: %s", AGConstant.MarketsManagement.BlockedUserPopup.LBL_COMPETITION,expectedCompetition) );
        Assert.assertEquals("Event Name: "+eventName,popup.lblEvent.getText(),"FAILED! Event name not matched");
        Assert.assertEquals( popup.btnUnBlockNow.getText(), AGConstant.MarketsManagement.BlockedUserPopup.BTN_UNBLOCK_NOW,"FAILED! Unblock Now button is incorrect");
        Assert.assertEquals( popup.btnUnblockSchedule.getText(), AGConstant.MarketsManagement.BlockedUserPopup.BTN_UNBLOCK_SCHEDULE,"FAILED! Unblock Schedule button is incorrect");
        Assert.assertEquals( popup.btnClose.getText(), AGConstant.MarketsManagement.BlockedUserPopup.BTN_CLOSE,"FAILED! Close button is incorrect");
        Assert.assertEquals(popup.tblHeader.getHeaderNameOfRows(), AGConstant.MarketsManagement.BlockedUserPopup.TABLE_EVENT,"FAILED! Header does not match with the expected");

        log("Verify 2. The number blocked in the list matched with the number in current column");
        Assert.assertEquals(blockedUserCount,currentBlockingUserCount,"FAILED!, Current number not match with list login ID ");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can unblocked Now event for a user
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Markets Management > Current Blocking
     *           2. Select  Type = Event, Sport = Soccer, and Today tab
     *           3. Select an event name and click on Current cell
     *           4. Check on the row that want to unblock for a Login ID
     * and click on Unblock Now button
     * @expect:  1. Verify the account is remove in the list Blocked User
     *           2. Close the popup and verify the blocked number is deducted
     */
    @Test(groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_MM_CurrentBlocking_TC004(String downlineAccount) {
        log("@title: Verify can unblocked Now event for a user");
        log("Pre-condition Step: Block an event");
        BlockUnblockEventPage blockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        AccountInfo acc = ProfileUtils.getProfile();
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> lstEvent= BlockUnblockEventsUtils.getEventList("Soccer",childID,"TODAY");
        if(lstEvent.size()== 0)
        {
            System.out.println("By pass test case as have no Soccer today event");
            Assert.assertTrue(true,"");
            return;
        }
        blockEventPage.filter("","Soccer","Today");
        String eventName = lstEvent.get(0).getEventName();
        blockEventPage.blockUnblockEvent(downlineAccount,eventName,"Block");

        log("Step 1. Navigate Markets Management > Current Blocking");
        DriverManager.getDriver().switchToParentFrame();
        CurrentBlockingPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, CURRENT_BLOCKING, CurrentBlockingPage.class);

        log("Step 2. Select  Type = Event, Sport = Soccer, and Today tab");
        page.filter("Event","Soccer","Today");

        log("Step 3. Select an event name and click on Current cell");
        BlockedUserPopup popup = page.openBlockedUser(eventName);

        log("Step 4. Check on the row that want to unblock for a Login ID");
        if( popup.unblockUser(downlineAccount))
        {
            boolean isUnblockSuccessfully = popup.verifyUserIsUnblock(downlineAccount);
            Assert.assertTrue(isUnblockSuccessfully, String.format("FAILED!, Login ID %s still display in the list after unblocked now",downlineAccount));
            String currentBlockedUser = popup.getCurrentBlockingNumber();
            popup.close();

            log("Verify 2. Close the popup and verify the blocked number is deducted");
            String blockedUserNumberAfter = page.getBlockedUser(eventName);
            Assert.assertEquals(blockedUserNumberAfter,currentBlockedUser,"FAILED! block number does not deducted after unblock now event for an account");
        }
        else{
            Assert.assertEquals(popup.tblBlockedUser.getColumn(1,false).get(0),"There is no user blocked","FAILED! Message no user blocked is incorrect");
        }

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Current Blocking UI when selecting Competition
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Markets Management > Current Blocking
     *           2. Select  Type = Event, Sport = Soccer
     * @expect:  1. Verify UI display correct when select Competition type
     */
    @Test(groups = {"smoke"})
    public void Agent_MM_CurrentBlocking_TC005() {
        log("@title: Verify Current Blocking UI when selecting Competition");
        String userID = ProfileUtils.getProfile().getUserID();
        log("Step 1. Navigate Markets Management > Current Blocking");
        CurrentBlockingPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, CURRENT_BLOCKING, CurrentBlockingPage.class);

        log("Step 2. Select  Type = Event, Sport = Soccer");
        page.filter("Competition","Soccer","");

        log("Verify 1. Verify UI display correct when select Competition type");
        Assert.assertEquals(page.lblType.getText(), AGConstant.MarketsManagement.CurrentBlocking.LBL_TYPE);
        Assert.assertTrue(page.ddbType.isDisplayed(),"FAILED! Type dropdown not exist");
        Assert.assertEquals(page.lblSport.getText(), AGConstant.MarketsManagement.CurrentBlocking.LBL_SPORT);
        Assert.assertTrue(page.ddbSport.isDisplayed(),"FAILED! Sport dropdown not exist");
        Assert.assertFalse(page.tabOldEvents.isDisplayed(),"FAILED! Old Events tab should not display when select Competition");
        Assert.assertFalse(page.tabToday.isDisplayed(),"FAILED! Today tab  tab should not display when select Competition");
        Assert.assertFalse(page.tabTomorrow.isDisplayed(),"FAILED! Tomorrow tab should not display when select Competition");
        Assert.assertFalse(page.tabFuture.isDisplayed(),"FAILED! Future tab tab should not display when select Competition");
        Assert.assertEquals(page.tblEvent.getColumnNamesOfTable(), AGConstant.MarketsManagement.CurrentBlocking.TABLE_COMPETITION,"FAILED! Header table when select competition not match with the expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can unblock competition
     * @pre-condition:
     *           1. Log in successfully by SAD
     *           2. In Block/Unblock Event, block Soccer Competition
     * @steps:   1. Navigate Markets Management > Current Blocking
     *           2. Select  Type = Competition, Sport = Soccer
     *           3. Search the competition that blocked in precondition
     *           4. Click on Current column
     *           5. Select the user and click on Unblock
     * @expect:  1. Verify the account is remove in the list Blocked User
     * 2. Close the popup and verify the blocked number is deducted
     */
    @Test(groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_MM_CurrentBlocking_TC006(String downlineAccount) {
        log("@title: Verify Current Blocking UI when selecting Competition");
        log("Precondition. Block a competition");
        CurrentBlockingPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, CURRENT_BLOCKING, CurrentBlockingPage.class);
        log("Step 2. Select  Type = Competition, Sport = Soccer");
        page.filter("Competition","Soccer","");
        String competitionName =  page.tblEvent.getRowsWithoutHeader(1,false).get(0).get(0);

        BlockUnblockCompetitionPage blockCompetitionPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_COMPETITION, BlockUnblockCompetitionPage.class);
        blockCompetitionPage.blockUblockCompetition("Soccer",downlineAccount,competitionName,true);

        log("Step 1. Navigate Markets Management > Current Blocking");
        page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, CURRENT_BLOCKING, CurrentBlockingPage.class);

        log("Step 2. Select  Type = Competition, Sport = Soccer");
        page.filter("Competition","Soccer","");

        log("Step 3. Search the competition that blocked in precondition");
        log("Step 4. Click on Current column");
        String currentBlockedCompeitionNumber = page.getBlockedUser(competitionName,false,true);
        BlockedUserPopup popup = new BlockedUserPopup();

        log("Step 5. Select the user and click on Unblock");
        popup.unblockCompeition(downlineAccount);

        log("Verify 1. Verify the account is remove in the list Blocked User");
        popup.verifyUserIsUnblock(downlineAccount);
        String currentBlockedUser = popup.getCurrentBlockingNumber();
        popup.close();
        String blockedUserNumberAfter = page.getBlockedUser(competitionName,false);

        log("Verify 2. Close the popup and verify the blocked number is deducted");
        if(!currentBlockedUser.equals(AGConstant.MarketsManagement.BlockedUserPopup.LBL_NO_USER_BLOCKED)){
            Assert.assertEquals(blockedUserNumberAfter,currentBlockedUser,"FAILED! block number does not deducted after unblock competition for an account");
        }
        else {
            Assert.assertEquals(blockedUserNumberAfter,"","FAILED! block number does not deducted after unblock competition for an account");
        }
        log("INFO: Executed completely");
    }


}
