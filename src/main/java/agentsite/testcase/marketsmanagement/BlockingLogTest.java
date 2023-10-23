package agentsite.testcase.marketsmanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import agentsite.pages.marketsmanagement.BlockingLogPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import membersite.objects.sat.Event;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.timeZone;

public class BlockingLogTest extends BaseCaseTest {

    @TestRails(id = "3709")
    @Test(groups = {"http_request"})
    public void Agent_MM_Blocking_Log_TC3709() {
        log("@title: Validate there is no http responded error returned");

        log("Step 1. Navigate Markets Management >Block Racing");
        agentHomePage.navigateBlockingLogPage();

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3710")
    @Test(groups = {"regression"})
    public void Agent_MM_Blocking_Log_TC3710() {
        log("@title Verify Blocking Log UI display correctly");
        log("Step 1. Navigate Markets Management > Blocking Log");
        BlockingLogPage page = agentHomePage.navigateBlockingLogPage();

        log("Verify  1. Verify Blocking Log UI display correctly");
        Assert.assertEquals(page.lblTitle.getText().trim(), AGConstant.MarketsManagement.BlockingLog.TITLE_PAGE, "FAILED! Page title is not correct");
        Assert.assertEquals(page.lblEventDate.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_DATE, "FAILED! Event Date is not correct");
        Assert.assertEquals(page.lblSport.getText().trim(), AGConstant.MarketsManagement.BlockingLog.SPORT, "FAILED! Sport is not correct");
        Assert.assertEquals(page.lblCompeition.getText().trim(), AGConstant.MarketsManagement.BlockingLog.COMPETITON, "FAILED! Competition is not correct");
        Assert.assertEquals(page.lblEventID.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_ID, "FAILED! Event is not correct");
        Assert.assertEquals(page.lblEventName.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_NAME, "FAILED! Event Name title is not correct");
        Assert.assertEquals(page.lblEventStatus.getText().trim(), AGConstant.MarketsManagement.BlockingLog.EVENT_STATUS, "FAILED! Event Status is not correct");
        Assert.assertEquals(page.txtSearchSport.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_SPORT, "FAILED! Search Sport textbox place holder is not correct");
        Assert.assertEquals(page.txtSearchCompetition.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_COMPETITION, "FAILED! Search Competition place holder is not correct");
        Assert.assertEquals(page.txtSearchEventID.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_EVENT_ID, "FAILED! Search Event id place holder is not correct");
        Assert.assertEquals(page.txtSearchEventName.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_EVENT_NAME, "FAILED! Search Event name place holder is not correct");
        Assert.assertEquals(page.txtSearchStatus.getAttribute("placeholder").trim(), AGConstant.MarketsManagement.BlockingLog.SEARCH_EVETS_STATUS, "FAILED! Search event status place holder is not correct");
        Assert.assertTrue(page.btnRefresh.isDisplayed(), "FAILED! Refresh button not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3711")
    @Test(groups = {"regression"})
    public void Agent_MM_Blocking_Log_TC3711() throws InterruptedException {
        log("@title Validate can view log of an event");
        log("Precondition: Log in successfully by SAD, There is a event is blocked/Unblocked by SAD");
        String sportName = "Cricket";
        AccountInfo acc = ProfileUtils.getProfile();
        List<AccountInfo> lstUser = DownLineListingUtils.getAllDownLineUsers(_brandname, acc.getUserCode(),acc.getUserID());
        BlockUnblockEventPage pageBlockEvent = agentHomePage.navigateBlockUnblockEventsPage();
        pageBlockEvent.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, lstUser.get(0).getUserID(), "TODAY");
        Event event = eventList.get(eventList.size()-1);
        pageBlockEvent.searchEvent(event.getEventName());
        pageBlockEvent.blockUnblockEvent(lstUser.get(0).getUserCode(), event.getEventName(), "Unblock Now");
        String dateTimeUnblock = DateUtils.getDate(0, "yyyy-MM-dd HH:mm", timeZone);

        log("Step 1. Navigate Markets Management > Blocking Log");
        BlockingLogPage pageBlockingLog = agentHomePage.navigateBlockingLogPage();

        log("Step 2. Filter the event date, the select Sport, Competition and Event in the precondition");
        pageBlockingLog.search(DateUtils.getDate(0,"dd/MM/yyyy", timeZone), sportName, event.getCompetitionName(), event.getID(), "","");

        log("Verify Event Show correctly as the time do Block/unblock");
        pageBlockingLog.verifyEventLogDisplayCorrect(dateTimeUnblock, lstUser.get(0).getUserCode(), "Unblock Now", acc.getUserCode());
        log("INFO: Executed completely");
    }

}
