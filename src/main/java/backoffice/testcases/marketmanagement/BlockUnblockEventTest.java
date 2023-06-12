package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.marketmanagement.BlockUnblockEventPage;
import backoffice.utils.marketmanagement.BlockUnblockEventUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BlockUnblockEventTest extends BaseCaseTest {

    @TestRails(id = "1661")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1661() {
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 1. Verify page title displays Block/Unblock Events");
        Assert.assertEquals("Block/Unblock Events", page.lblPageTitle.getText(), "FAILED ! Page title is not displayed correctly, actual: " + page.lblPageTitle.getText());
        log("INFO: Executed completely");
    }

    @TestRails(id = "1662")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1662() {
        log("@title: Validate can filter Upline");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Select filter criteria of Upline and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId);
        List<String> lstDownlineUI = page.getListDownlineUsers();

        log("Step 2. Validate when filter with Upline the result show with downline of selected Upline accordingly");
        for (int i = 0; i < lstDownlineApi.size(); i++) {
            Assert.assertTrue(lstDownlineApi.get(i).get(4).equalsIgnoreCase(lstDownlineUI.get(i)), "FAILED! List downline does not match expected: " + lstDownlineApi.get(i).get(4) +
                    " and actual: " + lstDownlineUI.get(i));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "2054")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_2054() {
        log("@title: Validate can filter Sport");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Select filter criteria of Sport and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(5), "");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        ArrayList<String>[] arr = page.getListCompetitionAndEvent();

        log("Step 2. Validate when filter with Sport the result show with competition/event belong to selected sport accordingly");
        for (int i = 0; i < lstLeagueAndEventApi.size(); i++) {
            Assert.assertTrue(lstLeagueAndEventApi.get(i).get(1).equalsIgnoreCase(arr[1].get(i)), "FAILED! List event does not match expected: " + lstLeagueAndEventApi.get(i).get(1) +
                    " actual: " + arr[1].get(i));
            Assert.assertTrue(lstLeagueAndEventApi.get(i).get(2).equalsIgnoreCase(arr[0].get(i)), "FAILED! List competition does not match expected: " + lstLeagueAndEventApi.get(i).get(2) +
                    " actual: " + arr[0].get(i));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "2055")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_2055() {
        log("@title: Validate can filter Today");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter with Today and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0),
                BOConstants.Operations.BlockUnblockEvent.FILTER_PERIOD.get(1));
        List<String> lstEventDateTime = page.getListEventDateTime();
        String currentDate = DateUtils.getDate(0, "yyyy-MM-dd", "GMT-4");

        log("Step 2. Validate when filter for Today displays with KOT in current date");
        for (int i = 0; i < lstEventDateTime.size(); i++) {
            String[] parts = lstEventDateTime.get(i).split(" ");
            Assert.assertTrue(parts[0].equalsIgnoreCase(currentDate), "FAILED! Event Date Time is not in today expected: " + currentDate +
                    " actual: " + parts[0]);
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "2056")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_2056() {
        log("@title: Validate can filter Tomorrow");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter with Tomorrow and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0),
                BOConstants.Operations.BlockUnblockEvent.FILTER_PERIOD.get(2));
        List<String> lstEventDateTime = page.getListEventDateTime();
        String tomorrowDate = DateUtils.getDate(1, "yyyy-MM-dd", "GMT-4");

        log("Step 2. Validate when filter for Tomorrow displays with KOT in current date + 1");
        for (int i = 0; i < lstEventDateTime.size(); i++) {
            String[] parts = lstEventDateTime.get(i).split(" ");
            Assert.assertTrue(parts[0].equalsIgnoreCase(tomorrowDate), "FAILED! Event Date Time is not in tomorrow expected: " + tomorrowDate +
                    " actual: " + parts[0]);
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "2057")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_2057() throws ParseException {
        log("@title: Validate can filter Future");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter with Future and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0),
                BOConstants.Operations.BlockUnblockEvent.FILTER_PERIOD.get(3));
        List<String> lstEventDateTime = page.getListEventDateTime();
        String tomorrowDate = DateUtils.getDate(1, "yyyy-MM-dd", "GMT-4");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        log("Step 2. Validate when filter for Future displays with KOT > tomorrow date");
        for (int i = 0; i < lstEventDateTime.size(); i++) {
            String[] parts = lstEventDateTime.get(i).split(" ");
            Assert.assertTrue(sdf.parse(parts[0]).after(sdf.parse(tomorrowDate)), "FAILED! Event Date Time is not in future date expected greater: " + tomorrowDate +
                    " actual: " + parts[0]);
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "1663")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1663() throws ParseException {
        log("@title: Validate can filter Old Events");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter with Old Events and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0),
                BOConstants.Operations.BlockUnblockEvent.FILTER_PERIOD.get(0));
        List<String> lstEventDateTime = page.getListEventDateTime();
        String currentDate = DateUtils.getDate(0, "yyyy-MM-dd", "GMT-4");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        log("Step 2. Validate when filter with Old Event, result only displays with events that have KOT < current date");
        for (int i = 0; i < lstEventDateTime.size(); i++) {
            String[] parts = lstEventDateTime.get(i).split(" ");
            Assert.assertTrue(sdf.parse(parts[0]).before(sdf.parse(currentDate)), "FAILED! Event Date Time is not in future date expected greater: " + currentDate +
                    " actual: " + parts[0]);
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "1664")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1664() {
        log("@title: Validate can filter Downline User Name for selected portal");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter for a downline username and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId);
        String firstUsername = String.valueOf(lstDownlineApi.get(0).get(4));
        page.filterDownlineUser(firstUsername);

        log("Step 2. Validate result is filtered correctly with username inputted");
        Assert.assertTrue(page.isFilterDownlineUserAppears(firstUsername), "FAILED! Filter downline user is not appeared in list");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1665")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1665() {
        log("@title: Validate can filter downline user with no record found");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter an unexists downline user and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        page.filterDownlineUser("nouserfound");

        log("Step 2. Validate result show with No record found");
        Assert.assertTrue(page.isNoRecordFoundDisplays(), "FAILED! No record found message does not display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1666")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1666() {
        log("@title: Validate can filter Event Name");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Get am event name of sport Soccer");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        String eventName = lstLeagueAndEventApi.get(0).get(1);

        log("Step 3. In Result table, Input event name for event and observe");
        page.filterEvent(eventName);
        log("Step 3. Validate result is filtered correctly with event name inputted");
        Assert.assertTrue(page.isFilterEventAppears(eventName), "FAILED! Filter event is not appeared in list");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1667")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1667() {
        log("@title: Validate can filter Event Id");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Get an event ID of sport Soccer");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        String eventId = lstLeagueAndEventApi.get(0).get(0);

        log("Step 3. In Result table, Input event id for event and observe");
        page.filterEvent(eventId);
        log("Step 3. Validate result is filtered correctly with event id inputted");
        Assert.assertTrue(page.isFilterEventAppears(eventId), "FAILED! Filter event is not appeared in list");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1668")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1668() {
        log("@title: Validate can add/remove favourites");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user to click favourites");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId);
        String firstUsername = String.valueOf(lstDownlineApi.get(0).get(4));
        page.filterDownlineUser(firstUsername);
        log("Step 3. Click star icon on options under Select All and observe");
        page.clickFavouriteIcon(firstUsername);
        log("Step 3. Validate option is added to favourites section");
        Assert.assertTrue(page.isUserAddedToFavourites(firstUsername), "FAILED! User is not added into Favourites");

        log("Step 4. Under Favourites section, click on star icon of any option and observe");
        page.clickUnfavouriteIcon(firstUsername);
        log("Step 4. Validate option is removed out of favourites section");
        Assert.assertFalse(page.isUserAddedToFavourites(firstUsername), "FAILED! User is not added into Favourites");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1669")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1669() {
        log("@title: Validate can select all for Favourites section");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user to click favourites");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId);
        String firstUsername = String.valueOf(lstDownlineApi.get(0).get(4));
        page.filterDownlineUser(firstUsername);
        log("Step 3. Click star icon on a downline user then click Select All in Favourite section and observe");
        page.clickFavouriteIcon(firstUsername);
        page.clickSelectAllFavourite();

        log("Step 3. Validate All options are selected");
        Assert.assertTrue(page.isDownlineCheckboxChecked(), "FAILED! All downline users checkbox in Favourites section are not checked");

        log("Post-condition: Unfavourite the favourited user");
        page.clickUnfavouriteIcon(firstUsername);
        log("INFO: Executed completely");
    }

    @TestRails(id = "1670")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1670() {
        log("@title: Validate can select all for Un-favourites  section");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user to click favourites");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId);
        String firstUsername = String.valueOf(lstDownlineApi.get(0).get(4));
        page.filterDownlineUser(firstUsername);
        log("Step 3. Click star icon on a downline user then click Select All in Favourite section and observe");
        page.clickSelectAllUnfavourite();

        log("Step 3. Validate All options are selected");
        Assert.assertTrue(page.isDownlineCheckboxChecked(), "FAILED! All downline users checkbox in Unfavourites section are not checked");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1671")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1671() {
        log("@title: Validate can select all for Un-favourites  section");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user with Sport = Soccer to show Event");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");

        log("Step 3. Click checkbox in Event to Select All");
        page.clickSelectAllEvent();

        log("Step 3. Validate All options are selected");
        Assert.assertTrue(page.isEventCheckboxChecked(), "FAILED! All events checkbox are not checked");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1672")
    @Test(groups = {"regression"})
    public void BO_Market_Management_BlockUnblock_Event_1672() {
        log("@title: Validate can show details");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user with Sport = Soccer to show Event");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");

        log("Step 3. Open Detail Status Log of first event record");
        page.openDetailsStatusLog();

        log("Step 3. Validate Block/Unblock Events Details popup displays with title Block/Unblock Events Details -");
        Assert.assertTrue(page.isDetailsStatusLogTitleDisplayedCorrect(), "FAILED! Details popup is not displayed with correct title");

        log("INFO: Executed completely");
    }

}
