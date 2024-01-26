package agentsite.testcase.reports;

import agentsite.pages.report.UnsettledBetPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.Report.UnsettleBet.*;

public class UnsettledBetTest extends BaseCaseTest {

    @TestRails(id = "3724")
    @Test(groups = {"http_request"})
    public void Agent_Report_Unsettled_Bet_3724() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report > Unsettled Bet");
        agentHomePage.navigateUnsettledBetPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3725")
    @Test(groups = {"regression"})
    public void Agent_Report_Unsettled_Bet_3725() {
        log("@title: Validate Unsettled Bet - Last Bet Mode UI display correctly");
        log("Step 1. Navigate Report > Unsettled Bet");
        String today = DateUtils.getDate(0, "dd/MM/yyyy", "GMT-4:00");
        UnsettledBetPage page = agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Last Bets Mode");
        page.selectMode("Last Bets Mode");

        log("Verify1. Verify Last Bet Mode UI display correctly");
        //TODO: handle for oldui/newui
        Assert.assertEquals(page.rdModeGroup.getAllLables(), MODE_LIST, "FAILED! Mode radio button list is incorrect ");
//        Assert.assertEquals(page.rdBetType.getAllLables(), LIST_BET_TYPE_RADIO_BUTTON, "FAILED! Mode radio button list is incorrect ");
        Assert.assertEquals(page.lblInfo.getText(), HINT_MESSAGE, "FAILED! Note message is incorrect");
//        Assert.assertEquals(page.tblLastBetsMode.getColumnNamesOfTable(), LAST_BETS_MODE_TABLE_HEADER, "FAILED! Table header is incorrect");
        Assert.assertEquals(page.txtFrom.getAttribute("value"), today, "FAILED! From date is incorrect");
        Assert.assertEquals(page.txtTo.getAttribute("value"), today, "FAILED! To date is incorrect");
//        Assert.assertEquals(page.txtUsername.getAttribute("placeholder"), "Username or Login ID", "FAILED! Username placehoder is incorrect");
        Assert.assertEquals(page.lblSatkeAll.getText(), "All", "FAILED! Stake all is incorrect displayed");
        Assert.assertEquals(page.btnSearch.getText(), "Submit", "FAILED! Submit button is incorrect displayed");
        Assert.assertTrue(page.txtWagerID.isDisplayed(), "FAILED! Wager id textbox is not displayed");
        Assert.assertTrue(page.ddbSport.isDisplayed(), "FAILED! Sport dropdown is not displayed");
        Assert.assertTrue(page.ddbEvent.isDisplayed(), "FAILED! Event dropdown is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3726")
    @Test(groups = {"regression_po"})
    public void Agent_Report_Unsettled_Bet_3726() {
        log("@title:Validate Unsettled Bet - Hierarchy Mode UI display correctly");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Hierarchy Mode");
        page.selectMode("Hierarchy Mode");

        log("Verify 1. Verify Hierarchy Mode Ui display correctly");
        Assert.assertTrue(page.ddpSportHiearchyMode.isDisplayed(), "FAILED! sport dropdown is not displayed");
        Assert.assertTrue(page.ddpEventHiearchyMode.isDisplayed(), "FAILED! Event dropdown is not displayed");
        Assert.assertEquals(page.btnSearch.getText(), "Submit", "FAILED! Submit button is incorrect");
        Assert.assertEquals(page.tblHierarchyMode.getColumnNamesOfTable(), HIERARCHY_MODE_PO_TABLE_HEADER, "FAILED! Table header is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3727")
    @Test(groups = {"regression_po"})
    public void Agent_Report_Unsettled_Bet_3727() {
        log("@title:Validate Unsettled Bet - Sport Mode UI display correctly");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Sport Mode");
        page.selectMode("Sport Mode");

        log("Verify 1. Verify Sport Mode Ui display correctly");
        Assert.assertTrue(Objects.nonNull(page.unsettleBetSportModeContainer.getAllSports()), "FAILED! Sport list not displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Last Best Mode - Can filter Matched bet
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Unsettled Bet
     * 2. Select Last Best Mode > Select Matched and click Submit
     * @expect: 1. Match bet display correctly
     */
    @TestRails(id = "786")
    @Test(groups = {"smoke"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_786(String memberAccount) {
        log("@title: Validate Last Best Mode - Can filter Matched bet");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Last Best Mode > Select Matched and click Submit");
        page.selectMode("Last Best Mode");
        String fromDate = DateUtils.getDate(-15, "dd/MM/yyyy", "GMT-4:00");
        page.search("Matched", "", memberAccount, "", "", "",fromDate,"");

        log("Verify 1. Match bet display correctly");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND, "FAILED! No record label is incorrect");
            return;
        }
        List<ArrayList<String>> data = page.tblLastBetsMode.getRowsWithoutHeader(false);
        page.verifySearchLastBetsMode(data, memberAccount, "Matched", "", "", "", "");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3728")
    @Test(groups = {"regression"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_3728(String memberAccount) {
        log("@title: Validate Last Best Mode - Can filter Unmatched bet");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.navigateUnsettledBetPage();

        log("Step Select Last Best Mode > Select Unmatched and click Submit");
        String fromDate = DateUtils.getDate(-15, "dd/MM/yyyy", "GMT-4:00");
        page.search("Un-matched", "", memberAccount, "", "", "",fromDate,"");

        log("Verify 1. Unmatch bet display correctly");
        List<ArrayList<String>> data = page.tblLastBetsMode.getRowsWithoutHeader(false);
        if (data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND)) {
            Assert.assertEquals(page.lblLastBetsModeNoRecord.getText(), AGConstant.NO_RECORD_FOUND, "FAILED! No record label is incorrect");
            return;
        }
        page.verifySearchLastBetsMode(data, memberAccount, "Matched", "", "", "", "");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3729")
    @Test(groups = {"regression"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_3729(String memberAccount) {
        log("@title: Validate Last Best Mode - Can filter Cancelled bet");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.navigateUnsettledBetPage();

        log("Step Select Last Best Mode > Select Cancelled and click Submit");
        String fromDate = DateUtils.getDate(-15, "dd/MM/yyyy", "GMT-4:00");
        page.search("Cancelled", "", memberAccount, "", "", "",fromDate,"");

        log("Verify 1. Unmatch bet display correctly");
        List<ArrayList<String>> data = page.tblLastBetsMode.getRowsWithoutHeader(false);
        if (data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND)) {
            Assert.assertEquals(page.lblLastBetsModeNoRecord.getText(), AGConstant.NO_RECORD_FOUND, "FAILED! No record label is incorrect");
            return;
        }
        page.verifySearchLastBetsMode(data, memberAccount, "Cancelled", "", "", "", "");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Sport Mode - Total bet list is correct
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Unsettled Bet
     * 2. Select Sport Mode
     * 3. If have bet click on sport then get total bet
     * 4. Click on event
     * @expect: 1. Verify bet number is matched with total bet
     */
    @TestRails(id = "787")
    @Test(groups = {"smoke"})
    public void Agent_Report_Unsettled_Bet_787() {
        log("@title: Validate Sport Mode - Total bet list is correct");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Sport Mode");
        page.selectMode("Sport Mode");

        log("Verify 3. If have bet click on sport then get total bet");
        if (page.lblNoRecordSportMode.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecordSportMode.getText(), AGConstant.NO_RECORD_FOUND, "FAILED! No record label is incorrect");
            return;
        }
        String sport = page.unsettleBetSportModeContainer.getAllSports().get(0);
        String totalBetFirstRow = page.unsettleBetSportModeContainer.getdataofSport(sport).get(0).get(2).split("Total Bets")[1].trim();

        log("Verify  4. Click on event");
        page.unsettleBetSportModeContainer.tblSportMode.scrollToTop();
        page.unsettleBetSportModeContainer.clickEvent(sport);

        List<ArrayList<String>> betListInfo = page.unsettleBetSportModeContainer.tblSportModeDetail.getRowsWithoutHeader(true);
        Assert.assertEquals(betListInfo.size(), Integer.parseInt(totalBetFirstRow.trim()), "Failed! Bet list number not display as total bet");
        log("INFO: Executed completely");
    }


}
