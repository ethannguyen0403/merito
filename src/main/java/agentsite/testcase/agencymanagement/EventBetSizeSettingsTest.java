package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.EventBetSizeSettingsPage;
import agentsite.pages.components.ConfirmPopup;
import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.EventBetSizeSettingUtils;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.TextBox;
import common.MemberConstants;
import membersite.objects.AccountBalance;
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

import static common.AGConstant.AgencyManagement.EventBetSiteSetting.*;
import static common.AGConstant.*;
import static common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static common.AGConstant.HomePage.EVENT_BET_STIE_SETTINGS;

public class EventBetSizeSettingsTest extends BaseCaseTest {
    /**
     * @title: Verify can search  event name with correct value
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Event Bet Size Settings
     * 2. Input event Name with a prefix "e.g. :  FC Hapoel Ashkelon v Hapoel Afula
     * @expect: 1. There is a event name "FC Hapoel Ashkelon v Hapoel Afula" display
     */
    @TestRails(id = "705")
    @Test(groups = {"smoke_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_705() {
        log("@title: Verify can search  event name with correct value");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);

        log(String.format("Step 2. Input event Name with a prefix : %s", event.getEventName()));
        page.eventBetSizeSetting.filter("", "Cricket", "Today");
        page.eventBetSizeSetting.searchEventInfo("", event.getEventName(), "");

        List<ArrayList<String>> result = page.eventBetSizeSetting.getTableInfo();
        log(String.format("Verify 1. There is a event name \"%s\" display", event.getEventName()));
        Assert.assertTrue(result.get(0).get(page.eventBetSizeSetting.colEventName - 1).contains(event.getEventName()), String.format("FAILED! Result not contains event %s", event.getEventName()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3564")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3564() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        agentHomePage.navigateEventBetSizeSettingsPage();

        log("Verify 1. Verify Event Bet Size Settings page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3571")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3571() {
        log("@title: Validate Min-Max value of Fancy is read only when login for PO level");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage eventBetSizeSettingsPage = agentHomePage.navigateEventBetSizeSettingsPage();

        log("Verify 1. Validate data on PO > Event Bet Size Setting display for Fancy correctly and readonly");
        Assert.assertEquals(TextBox.xpath(eventBetSizeSettingsPage.eventBetSizeSetting.tblEvent.getxPathOfCell(1, 5, 1, "input[1]")).getAttribute("disabled"), "true", "FAILED! Min Fancy is not disabled");
        Assert.assertEquals(TextBox.xpath(eventBetSizeSettingsPage.eventBetSizeSetting.tblEvent.getxPathOfCell(1, 5, 1, "input[2]")).getAttribute("disabled"), "true", "FAILED! Max Fancy is not disabled");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3572")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3572() {
        log("@title: Validate only Open event display on the list");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        String closedEventId = "32502254";

        log("Step 2. Select Soccer and Active Old Events tab");
        page.eventBetSizeSetting.filter("", "Soccer","Old Events");
        page.eventBetSizeSetting.searchEventInfo("","", closedEventId);

        log("Verify 2. Validate Closed event not display in the list");
        Assert.assertTrue(page.eventBetSizeSetting.lblNoRecordFound.isDisplayed(),"FAILED! Closed event displays in list result");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3573")
    public void Agent_AM_Event_Bet_Site_Settings_3573() {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "3565")
    @Test(groups = {"regression_po"})
    public void Agent_AM_Event_Bet_Site_Settings_3565() {
        log("@title:Validate Event Bet Size Settings display when login by PO levels");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();

        log("Verify 1. Verify Event Bet Size Settings page is displayed");
        Assert.assertEquals(page.header.lblPageTitle.getText(), EVENT_BET_STIE_SETTINGS, "FAILED! Page Title is incorrect");

        log("Verify 2. Display level control Blocking Dropdown box");
        Assert.assertTrue(page.eventBetSizeSetting.ddbSADLevel.isDisplayed(), "FAILED! Level Control Blocking dropdown box is not displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3566")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3566() {
        log("@title:Verify Event Bet Size Settings display when login by Control Login levels");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();

        log("Verify 1. Verify Event Bet Size Settings page is displayed");
        Assert.assertEquals(page.header.lblPageTitle.getText(), EVENT_BET_STIE_SETTINGS, "FAILED! Page Title is incorrect");

        log("Verify 2. Not Display level control Blocking Dropdown box");
        Assert.assertFalse(page.eventBetSizeSetting.ddbSADLevel.isDisplayed(), "FAILED! Level Control Blocking dropdown box is not displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3567")
    @Test(groups = {"regression_ag"})
    public void Agent_AM_Event_Bet_Site_Settings_3567() {
        log("@title:Verify Event Bet Size Settings NOT display when login with the level not is PO or Control Blocking Level");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        List<String> lstLeftMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 1. Verify Event Bet Size Settings page is NOT displayed");
        Assert.assertFalse(lstLeftMenu.contains(EVENT_BET_STIE_SETTINGS), "FAILED! Event Bet Site Setting display when login at low level");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3568")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3568() {
        log("@title: Verify UI on Event Bet Size Setting when login for SAD level");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();

        log("Verify 1. Verify UI on Event Bet Size Setting display correctly");
        List<String> lstHeader = page.eventBetSizeSetting.tblEvent.getHeaderNameOfRows();
        List<String> lstTabs = page.eventBetSizeSetting.mnTabs.getListSubMenu();
        Assert.assertEquals(page.eventBetSizeSetting.lblSport.getText(), "Sport", "FAILED! Sport lable is incorrect");
        Assert.assertEquals(lstTabs, TAB_DAYS, "FAILED! Date tabs in incorrect");
        Assert.assertEquals(lstHeader, TABLE_HEADER, "FAILED!Table Header in incorrect");
        Assert.assertEquals(page.eventBetSizeSetting.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is not display");
        Assert.assertEquals(page.eventBetSizeSetting.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3569")
    @Test(groups = {"regression_po"})
    public void Agent_AM_Event_Bet_Site_Settings_3569() {
        log("@title: Verify UI on Event Bet Size Setting when login for PO level");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();

        log("Verify 1. Verify UI on Event Bet Size Setting at PO level display correctly");
        List<String> lstHeader = page.eventBetSizeSetting.tblEvent.getHeaderNameOfRows();
        List<String> lstTabs = page.eventBetSizeSetting.mnTabs.getListSubMenu();
        Assert.assertEquals(page.eventBetSizeSetting.lblSport.getText(), "Sport", "FAILED! Sport lable is incorrect");
        Assert.assertEquals(lstTabs, TAB_DAYS, "FAILED! Date tabs in incorrect");
        Assert.assertEquals(lstHeader, TABLE_HEADER_PO, "FAILED!Table Header in incorrect");
        Assert.assertEquals(page.eventBetSizeSetting.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is not display");
        Assert.assertEquals(page.eventBetSizeSetting.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3570")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3570() {
        log("@title: Verify Min-Max value is read only when login for PO level");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();

        log("1. Navigate Agency Management > Event Bet Size Settings" +
                "        2. Update Min-Max field for normal market" +
                "        3. Login PO level > Event Bet Size Setting");
        log("Verify 1. Verify UI on Event Bet Size Setting at PO level display correctly");
        List<String> lstHeader = page.eventBetSizeSetting.tblEvent.getHeaderNameOfRows();
        List<String> lstTabs = page.eventBetSizeSetting.mnTabs.getListSubMenu();
        Assert.assertEquals(page.eventBetSizeSetting.lblSport.getText(), "Sport", "FAILED! Sport lable is incorrect");
        Assert.assertEquals(lstTabs, TAB_DAYS, "FAILED! Date tabs in incorrect");
        Assert.assertEquals(lstHeader, TABLE_HEADER, "FAILED!Table Header in incorrect");
        Assert.assertEquals(page.eventBetSizeSetting.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is not display");
        Assert.assertEquals(page.eventBetSizeSetting.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3574")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3574() {
        log("@title: Verify can search competition with prefix value");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String prefix = event.getCompetitionName().substring(0, 5);

        log("Step 2: Input competition Name with a prefix \'e.g. : Span\'");
        page.eventBetSizeSetting.searchEventInfo(prefix, "", "");

        log("Verify 1. All competition has prefix Span display");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colCompetitionName, true);
        Assert.assertTrue(page.eventBetSizeSetting.searchResultContainsKey(lstResult, prefix));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3575")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3575() {
        log("@title: Verify can search competition with correct value");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String competitionName = event.getCompetitionName();

        log("Step 2. Input competition Name with a prefix \"e.g. : English League 1");
        page.eventBetSizeSetting.searchEventInfo(competitionName, "", "");

        log("Verify 1.There is a competition display");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colCompetitionName, true);
        Assert.assertTrue(page.eventBetSizeSetting.searchResultContainsKey(lstResult, competitionName));
        Assert.assertTrue(lstResult.size() == 1, "FAILED! There are more than 1 competition when searching with correct value");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3576")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3576() {
        log("@title: Verify no competition display when search with invalid data");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();

        log("Step 2. Input competition Name with invalid data ");
        page.eventBetSizeSetting.searchEventInfo("invalidCompetition", "", "");

        log("Verify 1.Verify message \"No records found\" display");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colCompetitionName, false);
        Assert.assertEquals(lstResult.get(0), NO_RECORD_FOUND, "FAILED! Should display No records found when searching invalid competition");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3577")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3577() {
        log("@title: Verify can search event name that contains search value");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventNamePrefix = event.getEventName().substring(0, 5);

        log("Step 2. Input  event name  with a prefix \"e.g. :  Hapoel ");
        page.eventBetSizeSetting.searchEventInfo("", eventNamePrefix, "");

        log("Verify 1. All event contains \"Hapoel\" display");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colEventName, true);
        Assert.assertTrue(page.eventBetSizeSetting.searchResultContainsKey(lstResult, eventNamePrefix));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3578")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3578() {
        log("@title: Verify no  event  display when search with invalid data");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();

        log("Step 2. Input event name with invalid data ");
        page.eventBetSizeSetting.searchEventInfo("", "invalid", "");

        log("Verify 1. Verify message \"No record found\" display");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colCompetitionName, false);
        Assert.assertEquals(lstResult.get(0), NO_RECORD_FOUND, "FAILED! Should display No records found when searching invalid competition");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3579")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3579() {
        log("@title: Verify can search event ID  that contains search value");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID().substring(0, 6);

        log("Step 2. Input  event ID  with a prefix \"e.g. :  2986546");
        page.eventBetSizeSetting.searchEventInfo("", "", eventId);

        log("Verify 1. All event ID contains 2986546\" is displayed");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colEventID, false);
        Assert.assertTrue(page.eventBetSizeSetting.searchResultContainsKey(lstResult, eventId), "FAILED! Result list not contain event id");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3580")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3580() {
        log("@title: Verify can search event ID with correct value");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        page.eventBetSizeSetting.filter("", "Cricket", "Today");
        String eventId = event.getID();

        log("Step 2.Input event ID  with a correct event id");
        page.eventBetSizeSetting.searchEventInfo("", "", eventId);

        log("Verify 1. All event ID is displayed");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colEventID, false);
        Assert.assertEquals(lstResult.get(0), eventId, "FAILED! EventID is incorrect displayed after searching");
        Assert.assertTrue(lstResult.size() == 1, "FAILED! Result list is incorrect display when searching exact event id");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3581")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3581() {
        log("@title:Verify no  event  display when search with invalid event Id");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        page.eventBetSizeSetting.filter("", "Cricket", "Today");
        log("Step 2. Input Event id with invalid data ");
        page.eventBetSizeSetting.searchEventInfo("", "", "invalidid");

        log("Verify 1.Verify message \"No record found\" display");
        List<String> lstResult = page.eventBetSizeSetting.tblEvent.getColumn(page.eventBetSizeSetting.colCompetitionName, false);
        Assert.assertEquals(lstResult.get(0), NO_RECORD_FOUND, "FAILED! Should display No records found when searching invalid event id");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3583")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3583() {
        log("@title: Verify can update and remove min event setting in Fancy MIN-Max");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        String sportName = "Cricket";
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        AccountInfo acc = ProfileUtils.getProfile();
        Event event = EventBetSizeSettingUtils.getEventList(sportName, acc.getUserID(), "TODAY").get(0);
        int min = 15;
        int max = 50;

        log("Step 2. Select Soccer, and select Today or Tomorrow tab that has event");
        log("Step 3. Update min that greater min bet setting for an event and click submit button ");
        page.eventBetSizeSetting.filter("", sportName, "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Fancy", Integer.toString(min), Integer.toString(max));

        log("Verify 1. Verify min-max value should be updated");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Fancy"), String.format("%s-%s", min, max, "FAILED! Min-Max not update as the expected"));

        log("Step 4. Remove the setting ");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Fancy", "", "");

        log("Verify 1. Verify min-max value should be removed");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Fancy"), "-", "FAILED! Min-Max not update as the expected");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3582")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3582() {
        log("@title: Verify can update and remove min event setting in normal product");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        String sportName = "Cricket";
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        AccountInfo acc = ProfileUtils.getProfile();
        Event event = EventBetSizeSettingUtils.getEventList(sportName, acc.getUserID(), "TODAY").get(0);
        int min = 15;
        int max = 50;

        log("Step 2. Select Soccer, and select Today or Tomorrow tab that has event");
        log("Step 3. Update min that greater min bet setting for an event and click submit button ");
        page.eventBetSizeSetting.filter("", sportName, "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Exchange", Integer.toString(min), Integer.toString(max));

        log("Verify 1. Verify min-max value should be updated");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Exchange"), String.format("%s-%s", min, max, "FAILED! Min-Max not update as the expected"));

        log("Step 4. Remove the setting ");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Exchange", "", "");

        log("Verify 1. Verify min-max value should be removed");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Exchange"), "-", "FAILED! Min-Max not update as the expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update and remove max and min event setting
     * @pre-condition: 1. Log in successfully by SAD level
     * 2. Get Min max bet setting of Others
     * @steps: 1. Navigate Agency Management > Event Bet Size Settings
     * 2. Select HR and select Today or Tomorrow tab that has event
     * 3. Update min-max for an event and click submit button
     * 4. Remove max event setting for the event and click submit
     * @expect: 1. Verify min-max is updated
     * 2. Verify member site display message when place bet with stake greater than max event setting
     * 3. Verify Max bet is removed
     * 4. Verify member site will get max setting when max event setting is removed
     */
    @TestRails(id = "706")
    @Test(groups = {"smoke_sat"})
    @Parameters({"downlineAccount"})
    public void Agent_AM_Event_Bet_Site_Settings_706(String downlineAccount) {
        log("@title:Verify can update and remove max and min event setting");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        AccountInfo acc = ProfileUtils.getProfile();
//        List<String> betSettingInfo = EventBetSizeSettingUtils.getUserBetSetting("EXCHANGE", acc.getUserID(), "CRICKET");
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        int min = 5;
        int max = 300;

        log("Step 2. Select Cricket, and select Today or Tomorrow tab that has event");
        page.eventBetSizeSetting.filter("", "Cricket", "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());

        log("Step 3. Update min-max for an event and click submit button");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Exchange", Integer.toString(min), Integer.toString(max));

        log("Verify 1. Verify min-max is updated");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Exchange"), String.format("%d-%d", min, max, "FAILED! Min-Max not update as the expected"));

        log("Precondition step: Unblock today market for Soccer and Cricket sport");
        DriverManager.getDriver().switchToParentFrame();
        BlockUnblockEventPage blockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockEventPage.searchDownline(downlineAccount);
        blockEventPage.filter("", "Cricket", "Today");
        blockEventPage.searchEvent(event.getID());
        blockEventPage.blockUnblockEvent("all", "all", "Unblock Now", "", 1);

        log("Step 4. Remove max event setting for the event and click submit");
        //loginAgent(username,password);
//        loginAgent(sosAgentURL, agentSecurityCodeURL, username, password, environment.getSecurityCode());
        page = agentHomePage.navigateEventBetSizeSettingsPage();
        page.eventBetSizeSetting.filter("", "Cricket", "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Exchange", "", "");

        log("Verify 3. Verify Max bet is removed");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Exchange"), "-", "FAILED! Min-Max not update as the expected");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3584")
    @Test(groups = {"regression_po"})
    @Parameters({"portalSubAccount", "password"})
    public void Agent_AM_Event_Bet_Site_Settings_3584(String portalSubAccount, String password) throws Exception {
        log("@title:Verify Event Bet Size Setting at PO level is updated following SAD level");
        log("Step 1.Log in successfully by SAD level > Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        AccountInfo acc = ProfileUtils.getProfile();
        String userCodeLoginId = acc.getUserCodeAndLoginID("%s (%s)");
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        int min = 1;
        int max = 100;

        log("Step 2. Select Cricket and select Today or Tomorrow tab that has event");
        page.eventBetSizeSetting.filter("", "Cricket", "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());

        log("Step 3. Update min-max for an event and click submit button");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Exchange", Integer.toString(min), Integer.toString(max));
        page.logout();

        log("Step  4. Login agent by PO level and select the according SAD and sport");
        loginAgent(sosAgentURL, agentSecurityCodeURL, portalSubAccount, password, environment.getSecurityCode());
        page = agentHomePage.navigateEventBetSizeSettingsPage();
        page.eventBetSizeSetting.filter(userCodeLoginId, "Cricket", "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());

        log("Verify 1. Verify PO can only view min-max value that updated by SAD");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Exchange"), String.format("%d-%d", min, max, "FAILED! Min-Max not update as the expected"));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3585")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3585() {
        log("@title:Verify can update min-max for Fancy");
        log("Step 1.Log in successfully by SAD level > Navigate Agency Management > Event Bet Size Settings");
        String sportName = "Cricket";
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        AccountInfo acc = ProfileUtils.getProfile();
        String userCodeLoginId = acc.getUserCodeAndLoginID("%s (%s)");
        Event event = EventBetSizeSettingUtils.getEventList(sportName, acc.getUserID(), "TODAY").get(0);
        int min = 15;
        int max = 100;

        log("Step 2. Select Cricket and select Today or Tomorrow tab that has event");
        page.eventBetSizeSetting.filter("", sportName, "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());

        log("Step 3. Update Fancy min-max for an event and click submit button");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Fancy", Integer.toString(min), Integer.toString(max));

        log("Verify 1. Min-max value of a Fancy event is updated");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Fancy"), String.format("%d-%d", min, max, "FAILED! Min-Max not update as the expected"));

        //TODO: handle steps below
//        log("Step  4. Login agent by PO level and select the according SAD and sport");
//        loginAgent(sosAgentURL, agentSecurityCodeURL, portalSubAccount, popassword, environment.getSecurityCode());
//        page = agentHomePage.navigateEventBetSizeSettingsPage();
//        page.eventBetSizeSetting.filter(userCodeLoginId, "Cricket", "Today");
//        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());
//
//        log("Verify 1. Verify PO can only view min-max value that updated by SAD");
//        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Fancy"), String.format("%d-%d", min, max, "FAILED! Min-Max not update as the expected"));
//
//        log("INFO: Executed completely");
    }

    @TestRails(id = "3586")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3586() {
        log("@title: Only can update for min");
        log("Step 1.Log in successfully by SAD level > Navigate Agency Management > Event Bet Size Settings");
        String sportName = "Cricket";
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        AccountInfo acc = ProfileUtils.getProfile();
        Event event = EventBetSizeSettingUtils.getEventList(sportName, acc.getUserID(), "TODAY").get(0);
        int min = 15;

        log("Step 2/ Only input for min and click Save button");
        page.eventBetSizeSetting.filter("", sportName, "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Fancy", Integer.toString(min), "");

        log("Verify 1 Can input only min");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Fancy"), String.format("%s-%s", min, "", "FAILED! Min-Max not update as the expected"));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3587")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3587() {
        log("@title: Only can update for max");
        log("Step 1.Log in successfully by SAD level > Navigate Agency Management > Event Bet Size Settings");
        String sportName = "Cricket";
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        AccountInfo acc = ProfileUtils.getProfile();
        Event event = EventBetSizeSettingUtils.getEventList(sportName, acc.getUserID(), "TODAY").get(0);
        int max = 15;

        log("Step 2/ Only input for max and click Save button");
        page.eventBetSizeSetting.filter("", sportName, "Today");
        page.eventBetSizeSetting.searchEventInfo("", "", event.getID());
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Fancy", "", Integer.toString(max));

        log("Verify 1 Can input only min");
        Assert.assertEquals(page.eventBetSizeSetting.getMinMax(event.getID(), "Fancy"), String.format("%s-%s", "", max, "FAILED! Min-Max not update as the expected"));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3588")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3588() {
        log("@title: Validate validate message display when input max less than min");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID();
        int min = 100;
        int max = 50;
        log("Step 2. Select Cricket and select Today or Tomorrow tab that has event");
        page.eventBetSizeSetting.filter("", "Cricket", "Today");

        log("Step 3.Input min value greater than max bet setting in Min-Max column");
        log("Step 4.Click on X icon");
        page.eventBetSizeSetting.inputMinMax(event.getID(), "Exchange", Integer.toString(min), Integer.toString(max));
        ConfirmPopup popup = page.eventBetSizeSetting.openErrorPopup(eventId, "Exchange");

        log("Verify 1. Verify error message display :\"Min must be less than or equal to Max\" at Min-Max column");
        Assert.assertEquals(popup.getTitle(), "Error", "FAILED! Error popup title is incorrect");
        Assert.assertEquals(popup.getContentMessage(), ERROR_MAX_LESS_THAN_MIN, "FAILED! Error message is incorrect");
        popup.confirm();

        log("Step 3.Input min value greater than max bet setting in Fancy Min-Max column");
        log("Step 4.Click on X icon");
        page.eventBetSizeSetting.inputMinMax(event.getID(), "Fancy", Integer.toString(min), Integer.toString(max));
        popup = page.eventBetSizeSetting.openErrorPopup(eventId, "Fancy");

        log("Verify 2. Verify error message display :\"Min must be less than or equal to Max\" at Fancy Min-Max column");
        Assert.assertEquals(popup.getTitle(), "Error", "FAILED! Error popup title is incorrect");
        Assert.assertEquals(popup.getContentMessage(), ERROR_MAX_LESS_THAN_MIN, "FAILED! Error message is incorrect");
        popup.confirm();

        log("Step 3.Input min value greater than max bet setting in Bookmaker Min-Max column");
        log("Step 4.Click on X icon");
        page.eventBetSizeSetting.inputMinMax(event.getID(), "Bookmaker", Integer.toString(min), Integer.toString(max));
        popup = page.eventBetSizeSetting.openErrorPopup(eventId, "Bookmaker");

        log("Verify 2. Verify error message display :\"Min must be less than or equal to Max\" at Bookmaker Min-Max column");
        Assert.assertEquals(popup.getTitle(), "Error", "FAILED! Error popup title is incorrect");
        Assert.assertEquals(popup.getContentMessage(), ERROR_MAX_LESS_THAN_MIN, "FAILED! Error message is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3589")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Event_Bet_Site_Settings_3589() {
        log("@title: Verify cancel button works");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID();
        int min = 100;
        int max = 123;
        log("Step 2. Select Soccer and select Today or Tomorrow tab that has event");
        page.eventBetSizeSetting.filter("", "Cricket", "Today");

        log("Step 3. Input min max value for all column:Min-Max, Fancy Min-Max, Bookmaker Min-Max, then click on Cancel ");
        page.eventBetSizeSetting.inputMinMax(event.getID(), "Exchange", Integer.toString(min), Integer.toString(max));
        page.eventBetSizeSetting.inputMinMax(event.getID(), "Fancy", Integer.toString(min), Integer.toString(max));
        page.eventBetSizeSetting.inputMinMax(event.getID(), "Bookmaker", Integer.toString(min), Integer.toString(max));
        page.eventBetSizeSetting.btnCancel.click();

        log("Verify 1. Verify the input value of all column is cleared");
        Assert.assertEquals(page.eventBetSizeSetting.getMinTextBoxofEvent(eventId, "Exchange").getAttribute("value"), "", "FAILED! Min text box of event " + eventId + " at column Min -Max does not clear after clicking Cancel button ");
        Assert.assertEquals(page.eventBetSizeSetting.getMaxTextBoxofEvent(eventId, "Exchange").getAttribute("value"), "", "FAILED! Max text box of event " + eventId + " at column Min -Max does not clear after clicking Cancel button ");
        Assert.assertEquals(page.eventBetSizeSetting.getMinTextBoxofEvent(eventId, "Fancy").getAttribute("value"), "", "FAILED! Min text box of event " + eventId + " at column Fancy Min -Max does not clear after clicking Cancel button ");
        Assert.assertEquals(page.eventBetSizeSetting.getMaxTextBoxofEvent(eventId, "Fancy").getAttribute("value"), "", "FAILED! Max text box of event " + eventId + "at column Fancy Min -Max does not clear after clicking Cancel button ");
        Assert.assertEquals(page.eventBetSizeSetting.getMinTextBoxofEvent(eventId, "Bookmaker").getAttribute("value"), "", "FAILED! Min text box of event " + eventId + "at column Bookmaker Min -Max does not clear after clicking Cancel button ");
        Assert.assertEquals(page.eventBetSizeSetting.getMaxTextBoxofEvent(eventId, "Bookmaker").getAttribute("value"), "", "FAILED! Max text box of event " + eventId + " at column Bookmaker Min -Max does not clear after clicking Cancel button ");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3590")
    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password", "downlineAccount"})
    public void Agent_AM_Event_Bet_Site_Settings_3590(String memberAccount, String password, String downlineAccount) throws Exception {
        log("@title:Cannot place bet when stake less than min setting in Event Bet Site Setting for normal market");
        AccountInfo acc = ProfileUtils.getProfile();
        int min = 100;
        int max = 123;
        String sportName = "Cricket";
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList(sportName, acc.getUserID(), "TMR").get(0);
        String eventId = event.getID();
        String eventName = event.getEventName();

        log("Step 2. Update Min bet for an event (first column)");
        page.eventBetSizeSetting.filter("", sportName, "Tomorrow");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Exchange", Integer.toString(min), Integer.toString(max));

        log("Step precondition: Unblock the event in Block/Unbock Event page");
        BlockUnblockEventPage blockUnblockEventPage = page.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2));
        blockUnblockEventPage.blockUnblockEvent(downlineAccount, eventName, "Unblock Now", "", 1);
        blockUnblockEventPage.logout();

        log("Step 3. Login in member site and place on BF market of the event in step 2");
        loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        event = sportPage.setEventLink(event);
        MarketPage marketPage = sportPage.clickEvent(event);
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        String odds = market.getBtnOdd().getText();
        market.getBtnOdd().click();

        log(String.format("Step 4. Place bet with odds:%s Stake: %s", odds, min));
        AccountBalance balance = marketPage.header.getUserBalance();
        marketPage.betsSlipContainer.placeBet(odds, Integer.toString(min));

        log(String.format("Verify 1: Verify cannot place bet if min bet less than the setting"));
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f", min), String.format("%(,.2f", max), String.format("%.2f", min));
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3591")
    @Test(groups = {"interaction"})
    public void Agent_AM_Event_Bet_Site_Settings_3591() {
        log("@title:Cannot place bet when stake less than max setting in Event Bet Site Setting for normal market");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        // Get the unblock event in Block Unblock event
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID();
        String eventName = event.getEventName();
        int min = 100;
        int max = 123;
        log("Step 2. Update Max bet for an event (first colum)");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Exchange", Integer.toString(min), Integer.toString(max));

        log("Step 3 Login in member site and place on BF market of the event in step 2 ");


        log("Verify Verify cannot place bet if max bet greater than the setting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3592")
    @Test(groups = {"interaction"})
    public void Agent_AM_Event_Bet_Site_Settings_3592() throws Exception {
        log("@title: Cannot place bet when stake less than min setting in Event Bet Site Setting for Fancy market");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        // Get the unblock event in Block Unblock event
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID();
        String eventName = event.getEventName();
        int min = 100;
        int max = 123;
        log("Step 2. Update Min bet for an event in Fancy Min-Max Column");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Fancy", Integer.toString(min), Integer.toString(max));

        log("Step 3 Login in member site and place on Fancy market under event has setting min max ");


        log("Verify Verify cannot place bet if min bet less than the setting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3593")
    @Test(groups = {"interaction"})
    public void Agent_AM_Event_Bet_Site_Settings_3593() {
        log("@title:Cannot place bet when stake greater than max setting in Event Bet Site Setting for Fancy market");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        // Get the unblock event in Block Unblock event
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID();
        String eventName = event.getEventName();
        int min = 100;
        int max = 123;
        log("Step 2. Update Max bet for an event in Fancy Min-Max Column");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Fancy", Integer.toString(min), Integer.toString(max));

        log("Step 3 Login in member site and place on Fancy market under event has setting min max with stake > max bet setting");


        log("Verify cannot place bet if max bet greater than the setting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3594")
    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Event_Bet_Site_Settings_3594(String memberAccount, String password) throws Exception {
        log("@title: Cannot place bet when stake greater than max setting in Event Bet Site Setting for Bookmaker market");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        // Get the unblock event in Block Unblock event
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID();
        String eventName = event.getEventName();
        int min = 100;
        int max = 123;
        log("Step 2. Update Max bet for an event in Bookmaker Min-Max Column");
        page.eventBetSizeSetting.updateMinMax(event.getID(), "Bookmaker", Integer.toString(min), Integer.toString(max));

        log("Step 3 Login in member site and place on Bookmaker market under event has setting min max with stake < max bet setting");
        memberHomePage = loginMember(memberAccount, password);

        log("Verify Verify cannot place bet if min bet less than the setting");


        log("INFO: Executed completely");
    }

    @TestRails(id = "3595")
    @Test(groups = {"interaction"})
    public void Agent_AM_Event_Bet_Site_Settings_3595() {
        log("@title:Cannot place bet when stake greater than max setting in Event Bet Site Setting for Bookmaker market");
        log("Step 1. Navigate Agency Management > Event Bet Size Settings");
        AccountInfo acc = ProfileUtils.getProfile();
        // Get the unblock event in Block Unblock event
        EventBetSizeSettingsPage page = agentHomePage.navigateEventBetSizeSettingsPage();
        Event event = EventBetSizeSettingUtils.getEventList("Cricket", acc.getUserID(), "TODAY").get(0);
        String eventId = event.getID();
        String eventName = event.getEventName();
        int min = 100;
        int max = 123;
        log("Step 2. Update Max bet for an event in Bookmaker Min-Max Column");
        page.eventBetSizeSetting.filter("", "Cricket", "Today");

        log("Step 3 Login in member site and place on Bookmaker market under event has setting min max with stake > max bet setting ");

        log("Verify Verify cannot place bet if max bet greater than the setting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4181")
    public void Agent_AM_Event_Bet_Site_Settings_4181() {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "4182")
    public void Agent_AM_Event_Bet_Site_Settings_4182() {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "4183")
    public void Agent_AM_Event_Bet_Site_Settings_4183() {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }
}


