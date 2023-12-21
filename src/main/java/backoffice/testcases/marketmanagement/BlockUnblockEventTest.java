package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.marketmanagement.BlockUnblockEventPage;
import backoffice.pages.bo.marketmanagement.CompetitionBlockingPage;
import backoffice.utils.marketmanagement.BlockUnblockEventUtils;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static backoffice.common.BOConstants.NO_RECORDS_FOUND;

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
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_1662(String fair999PortalCode) {
        log("@title: Validate can filter Upline");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Select filter criteria of Upline and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId,"SMA");
        List<String> lstDownlineUI = page.getFullListDownlineUsers();

        log("Step 2. Validate when filter with Upline the result show with downline of selected Upline accordingly");
        for (int i = 0; i < lstDownlineApi.size(); i++) {
            Assert.assertEquals(page.defineDownLineAlias(lstDownlineApi.get(i).get(1),lstDownlineApi.get(i).get(4),lstDownlineApi.get(i).get(2)),lstDownlineUI.get(i), "FAILED! List downline does not match expected");
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
        page.waitSpinIcon();
        List<String> lstEventDateTime = page.getListEventDateTime();
        log("Step 2. Validate when filter for Today displays with KOT in current date");
        Assert.assertTrue(page.isTheDateOfEventDisplayCorrect(lstEventDateTime),"FAILED! Event Date Time is not in today expected");
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
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_1664(String fair999PortalCode) {
        log("@title: Validate can filter Downline User Name for selected portal");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter for a downline username and observe");
        page.filter(fair999PortalCode, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId,"SMA");
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
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_1668(String fair999PortalCode) {
        log("@title: Validate can add/remove favourites");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user to click favourites");
        page.filter(fair999PortalCode, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId,"SMA");
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
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_1669(String fair999PortalCode) {
        log("@title: Validate can select all for Favourites section");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user to click favourites");
        page.filter(fair999PortalCode, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId,"SMA");
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
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_1670(String fair999PortalCode) {
        log("@title: Validate can select all for Un-favourites  section");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Filter a user to click favourites");
        page.filter(fair999PortalCode, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0), "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId,"SMA");
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

        log("Verify 1. Validate All options are selected");
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

    @TestRails(id = "15728")
    @Test(groups = {"regression","2023.11.3"})
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_15728(String fair999PortalCode) {
        log("@title: Validate a competition is blocked at BO - Competition Blocking does not display when filtering when filtering in Old Events tab");
        log("Precondition step  1. Login BO > Competition Blocking");
        CompetitionBlockingPage competitionBlockingPage = backofficeHomePage.navigateCompetitionBlocking();
        log("Precondition step  2. Get the blocked event of soccer");
        String sport = "Soccer";
        competitionBlockingPage.filter(sport,"","Blocked","","");
        String eventID = competitionBlockingPage.getFirstEventIDofTheFirstCompetition();
        competitionBlockingPage.closeActiveTab();
        if(eventID.equalsIgnoreCase(NO_RECORDS_FOUND)) {
            log("By passed test case as has no Soccer competition is blocked");
            return;
        }

        DriverManager.getDriver().switchToDefaultContent();
        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = competitionBlockingPage.navigateBlockUnblockEvents();

        log("Step 2. Select any portal , sport: soccer and Old Event Tab");
        page.filter(fair999PortalCode, sport, "Old Events");

        log("Step 3. Search the event blocked in precondition");
        page.filterEvent(eventID);

        log("Step 3. Verify the event does not display");
        Assert.assertFalse(page.isNoRecordFoundDisplays(), "FAILED!The event display");

        log("INFO: Executed completely");
    }
    @TestRails(id = "15729")
    @Test(groups = {"regression","2023.11.30"})
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_15729(String fair999PortalCode) {
        log("@title: Validate a competition is blocked at BO - Competition Blocking does not display when filtering when filtering in Today tab");
        log("Precondition step  1. Login BO > Competition Blocking");
        CompetitionBlockingPage competitionBlockingPage = backofficeHomePage.navigateCompetitionBlocking();
        log("Precondition step  2. Get the blocked event of soccer");
        String sport = "Soccer";
        competitionBlockingPage.filter(sport,"","Blocked","","");
        String eventID = competitionBlockingPage.getFirstEventIDofTheFirstCompetition();
        if(eventID.equalsIgnoreCase(NO_RECORDS_FOUND)) {
            log("By passed test case as has no Soccer competition is blocked");
            return;
        }

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = competitionBlockingPage.navigateBlockUnblockEvents();

        log("Step 2. Select any portal , sport: soccer and Today Tab");
        page.filter(fair999PortalCode, sport, "Today");

        log("Step 3. Search the event blocked in precondition");
        page.filterEvent(eventID);

        log("Step 3. Verify the event does not display");
        Assert.assertFalse(page.isNoRecordFoundDisplays(), "FAILED!The event display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15730")
    @Test(groups = {"regression","2023.11.30"})
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_15730(String fair999PortalCode) {
        log("@title: Validate a competition is blocked at BO - Competition Blocking does not display when filtering when filtering in Tomorrow tab");
        log("Precondition step  1. Login BO > Competition Blocking");
        CompetitionBlockingPage competitionBlockingPage = backofficeHomePage.navigateCompetitionBlocking();
        log("Precondition step  2. Get the blocked event of soccer");
        String sport = "Soccer";
        competitionBlockingPage.filter(sport,"","Blocked","","");
        String eventID = competitionBlockingPage.getFirstEventIDofTheFirstCompetition();
        if(eventID.equalsIgnoreCase(NO_RECORDS_FOUND)) {
            log("By passed test case as has no Soccer competition is blocked");
            return;
        }

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = competitionBlockingPage.navigateBlockUnblockEvents();

        log("Step 2. Select any portal , sport: soccer and Tomorrow Tab");
        page.filter(fair999PortalCode, sport, "Tomorrow");

        log("Step 3. Search the event blocked in precondition");
        page.filterEvent(eventID);

        log("Step 3. Verify the event does not display");
        Assert.assertFalse(page.isNoRecordFoundDisplays(), "FAILED!The event display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15731")
    @Test(groups = {"regression","2023.11.30"})
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_15731(String fair999PortalCode) {
        log("@title: Validate a competition is blocked at BO - Competition Blocking does not display when filtering when filtering in Future tab");
        log("Precondition step  1. Login BO > Competition Blocking");
        CompetitionBlockingPage competitionBlockingPage = backofficeHomePage.navigateCompetitionBlocking();
        log("Precondition step  2. Get the blocked event of soccer");
        String sport = "Soccer";
        competitionBlockingPage.filter(sport,"","Blocked","","");
        String eventID = competitionBlockingPage.getFirstEventIDofTheFirstCompetition();
        if(eventID.equalsIgnoreCase(NO_RECORDS_FOUND)) {
            log("By passed test case as has no Soccer competition is blocked");
            return;
        }

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = competitionBlockingPage.navigateBlockUnblockEvents();

        log("Step 2. Select any portal , sport: soccer and Future Tab");
        page.filter(fair999PortalCode, sport, "Future");

        log("Step 3. Search the event blocked in precondition");
        page.filterEvent(eventID);

        log("Step 3. Verify the event does not display");
        Assert.assertFalse(page.isNoRecordFoundDisplays(), "FAILED!The event display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15732")
    @Test(groups = {"regression","2023.11.30"})
    @Parameters("fair999PortalCode")
    public void BO_Market_Management_BlockUnblock_Event_15732(String fair999PortalCode) {
        log("@title: Validate an unblocked competition at BO - Competition Blocking display when filtering when filtering");
        log("Precondition step  1. Login BO > Competition Blocking");
        CompetitionBlockingPage competitionBlockingPage = backofficeHomePage.navigateCompetitionBlocking();
        log("Precondition step  2. GGet the unblocked event of soccer");
        String sport = "Soccer";
        competitionBlockingPage.filter(sport,"","Unblocked","","");
        String eventID = competitionBlockingPage.getFirstEventIDofTheFirstCompetition();
        if(eventID.equalsIgnoreCase(NO_RECORDS_FOUND)) {
            log("By passed test case as has no Soccer competition is blocked");
            return;
        }

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = competitionBlockingPage.navigateBlockUnblockEvents();

        log("Step 2. Select any portal , sport: soccer and Future Tab");
        page.filter(fair999PortalCode, sport, "Today");

        log("Step 3. Search the event blocked in precondition");
        page.filterEvent(eventID);

        log("Verify 3. Verify the event does not display");
        Assert.assertFalse(page.isNoRecordFoundDisplays(), "FAILED!The event display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15733")
    @Test(groups = {"regression","2023.11.302"})
    @Parameters({"fair999PortalCode","fair999ControlLevel","agentPassword"})
    public void BO_Market_Management_BlockUnblock_Event_15733(String fair999PortalCode,String fair999ControlLevel,String agentPassword) throws Exception {
        log("@title: Validate event is blocked will be reflected in agent");
        log("Precondition step 1/Login BO");
        String sport = "Soccer";

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2.  Select PO of Fair999 brand");
        log("Step 3. Select Soccer and Today tab and select a control level blocking");
        page.filter(fair999PortalCode, sport, "Today");

        log("Step 4. Get the first event and block it");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        String eventId = lstLeagueAndEventApi.get(0).get(0);
        page.blockUnblockEvent(fair999ControlLevel,eventId,"Block","");

        log("Step 5. Login agent site of Fair999 brand at the level control blocking in step 3");
        loginAgent(fair999ControlLevel, agentPassword,"fairexchange");
        log("Step 6. Active Block/Unblock Event page and search the event in step 3 and check the status");
        agentsite.pages.marketsmanagement.BlockUnblockEventPage blockUnblockEventPage =  agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.searchEvent(eventId);

        log("Verify 1: Verify the status of the event is blocked");
        blockUnblockEventPage.verifyBlockUnblockEvent(eventId, "Blocked", false, false, false, "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15734")
    @Test(groups = {"regression","2023.11.301"})
    @Parameters({"satPortalCode","satSADAgentLoginID","agentPassword"})
    public void BO_Market_Management_BlockUnblock_Event_15734(String satPortalCode,String satSADAgentLoginID,String agentPassword) throws Exception {
        log("@title: Validate event is unblocked will be reflected in agent");
        log("Precondition step 1/Login BO");
        String sport = "Soccer";

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2.  Select PO of SAT brand");
        log("Step 3. Select Soccer and Today tab and select a control level blocking");
        page.filter(satPortalCode, sport, "Today");

        log("Step 4. Get the first event and unblock it");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        String eventId = lstLeagueAndEventApi.get(0).get(0);
        page.blockUnblockEvent(satSADAgentLoginID,eventId,"Unblock","");
        page.logout();

        log("Step 5. Login agent site of Fair999 brand at the level control blocking in step 4");
        loginAgent(satSADAgentLoginID, agentPassword,true);
        log("Step 6. Active Block/Unblock Event page and search the event in step 3 and check the status");
        agentsite.pages.marketsmanagement.BlockUnblockEventPage blockUnblockEventPage =  agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.searchDownline("abc");
        blockUnblockEventPage.searchEvent(eventId);

        log("Verify 1: Verify the status of the event is blocked");
        blockUnblockEventPage.verifyBlockUnblockEvent(eventId, "Unblocked", true, false, true, "Now", "Now");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15735")
    @Test(groups = {"regression","2023.11.301"})
    @Parameters({"satPortalCode","satSADAgentLoginID","agentPassword"})
    public void BO_Market_Management_BlockUnblock_Event_15735(String satPortalCode,String satSADAgentLoginID,String agentPassword) throws Exception {
        log("@title: Validate event is unblocked scheduler in 25 minutes will be reflected in agent");
        log("Precondition step 1/Login BO");
        String sport = "Soccer";

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Select PO of SAT brand");
        log("Step 3. Select Soccer and Tomorrow tab and select a control level blocking");
        page.filter(satPortalCode, sport, "Tomorrow");

        log("Step 4. Get the first event and unblock schedule in 25 minutes");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        String eventId = lstLeagueAndEventApi.get(0).get(0);
        page.blockUnblockEvent(satSADAgentLoginID,eventId,"Unblock Schedule","25 minutes");
        page.logout();

        log("Step 5. Login agent site of Fair999 brand at the level control blocking in step 4");
        loginAgent(satSADAgentLoginID, agentPassword,true);
        log("Step 6.  Active Block/Unblock Event page and search the event in step 4 and check the status");
        agentsite.pages.marketsmanagement.BlockUnblockEventPage blockUnblockEventPage =  agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.searchDownline("abc");
        blockUnblockEventPage.searchEvent(eventId);

        log("Verify 1: Verify the status of the event is unblocked in 25 minutes");
        blockUnblockEventPage.verifyBlockUnblockEvent(eventId, "Blocked", true, false, true, "25 minutes", "25 minutes");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15736")
    @Test(groups = {"regression","2023.11.301"})
    @Parameters({"satPortalCode","satSADAgentLoginID","agentPassword"})
    public void BO_Market_Management_BlockUnblock_Event_15736(String satPortalCode,String satSADAgentLoginID,String agentPassword) throws Exception {
        log("@title: Validate event is unblocked scheduler in 24 hours will be reflected in agent");
        log("Precondition step 1/Login BO");
        String sport = "Soccer";

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Select PO of SAT brand");
        log("Step 3. Select Soccer and Tomorrow tab and select a control level blocking");
        page.filter(satPortalCode, sport, "Tomorrow");

        log("Step 4. Get the first event and unblock schedule in 25 minutes");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        String eventId = lstLeagueAndEventApi.get(0).get(0);
        page.blockUnblockEvent(satSADAgentLoginID,eventId,"Unblock Schedule","24 hours");
        page.logout();

        log("Step 5. Login agent site of Fair999 brand at the level control blocking in step 4");
        loginAgent(satSADAgentLoginID, agentPassword,true);
        log("Step 6.  Active Block/Unblock Event page and search the event in step 4 and check the status");
        agentsite.pages.marketsmanagement.BlockUnblockEventPage blockUnblockEventPage =  agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.searchDownline("abc");
        blockUnblockEventPage.searchEvent(eventId);

        log("Verify 1: Verify the status of the event is unblocked in 25 minutes");
        blockUnblockEventPage.verifyBlockUnblockEvent(eventId, "Unblocked", true, false, true, "24 hours", "25 minutes");
        log("INFO: Executed completely");
    }


    @TestRails(id = "15737")
    @Test(groups = {"regression","2023.11.30"})
    @Parameters("satPortalCode")
    public void BO_Market_Management_BlockUnblock_Event_15737(String satPortalCode) {
        log("@title: Validate display level control blocking if selection a PO is not level control blocking ");

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2.  Select PO of SAT brand");
        page.filter(satPortalCode, "", "");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId,"SAD");
        List<String> lstDownlineUI = page.getFullListDownlineUsers();

        log("Verify 1: Verify all downline display SAD");
        for (int i = 0; i < lstDownlineApi.size(); i++) {
            Assert.assertEquals(lstDownlineUI.get(i),page.defineDownLineAlias(lstDownlineApi.get(i).get(1),lstDownlineApi.get(i).get(4),lstDownlineApi.get(i).get(2)),"FAILED! List downline does not match expected");
        }
        log("INFO: Executed completely");
    }
    @TestRails(id = "15738")
    @Test(groups = {"regression","2023.11.30"})
    @Parameters("funsportPortalCode")
    public void BO_Market_Management_BlockUnblock_Event_15738(String funsportPortalCode) {
        log("@title: Validate only display PO level if it is level control blocking");

        log("Step 1. Active Block/Unblock Event page");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 2. Select PO of Funsport brand");
        page.filter(funsportPortalCode, "", "Today");

        log("Verify 1: Verify only display PO");
        Assert.assertTrue(page.getFullListDownlineUsers().size() == 1, "FAILED! Only display 1 item PO ");
        Assert.assertEquals(page.getFullListDownlineUsers().get(0),String.format("PO:%s",funsportPortalCode), "FAILED! Display list value is incorrect");

        log("INFO: Executed completely");
    }
}
