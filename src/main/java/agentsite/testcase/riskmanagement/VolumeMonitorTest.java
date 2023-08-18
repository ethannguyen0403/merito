package agentsite.testcase.riskmanagement;

import agentsite.pages.riskmanagement.VolumeMonitorPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.HomePage.VOLUME_MONITOR;
import static common.AGConstant.MarketsManagement.BlockedUserPopup.LBL_EVENT;
import static common.AGConstant.NO_RECORD_FOUND;
import static common.AGConstant.RiskManagement.VolumeMonitor.*;


public class VolumeMonitorTest extends BaseCaseTest {
    /***
     * This test cases only available for F24 and Betclub9
     */
    @TestRails(id = "830")
    @Test(groups = {"smoke_po"})
    public void Agent_RM_VolumeMonitorTest_830() {
        log("@title: Verify UI display correctly");
        log("Step 1.Navigate Risk Management > Volumn Monitor");
        VolumeMonitorPage page = agentHomePage.navigateVolumeMonitorPage();

        log("Verify 1 Verify UI is correctly displayed");
        Assert.assertEquals(page.header.lblPageTitle.getText(), VOLUME_MONITOR, "FAILED! Volume Monitor page title is incorrect");
        Assert.assertEquals(page.lblLevel.getText(), LBL_EVENT, "FAILED! Level label is incorrect");
        Assert.assertEquals(page.lblSport.getText(), LBL_SPORT, "FAILED! Sport label is incorrect");
        Assert.assertEquals(page.cbBetType.getAllLables(), LIST_BET_TYPES, "FAILED! List Bet Type is incorrect");
        Assert.assertEquals(page.btnToday.getText(), BTN_TODAY, "FAILED! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(), BTN_YESTERDAY, "FAILED! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLast7Days.getText(), BTN_LAST_WEEK, "FAILED! Last 7 Days button is incorrect");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect");
        Assert.assertEquals(page.lblNote.getText(), AGConstant.RiskManagement.VolumeMonitor.NOTE, "FAILED! Note message is incorrect");
        Assert.assertTrue(page.ddbLevel.isDisplayed(), "FAILED! Level Dropdown list is not display");
        Assert.assertTrue(page.ddpSport.isDisplayed(), "FAILED! Sport Dropdown list is not display");
        Assert.assertTrue(page.btnRefreshButton.isDisplayed(), "FAILED!Refresh button is not display");
        Assert.assertEquals(page.tblReport.getColumnNamesOfTable(), TABLE_HEADER, "FAILED! Header table not match with the expected when selection Event Type");

        log("INFO: Executed completely");
    }

    @TestRails(id = "831")
    @Test(groups = {"smoke_po"})
    public void Agent_RM_VolumeMonitorTest_831() {
        log("@title: Data is display when filter");
        log("Step 1.Navigate Risk Management > Volume Monitor");
        VolumeMonitorPage page = agentHomePage.navigateVolumeMonitorPage();
        String fromDate = DateUtils.getDate(-14, "dd/MM/yyyy", "GMT-4");
        String level = "MA";

        log("Step 2: Filter matched bet and the sport has order");
        page.filter(level, "All", "Select All", fromDate, "");

        log("Verify 1 Verify data Is displayed");
        if (page.lblNoRecord.isDisplayed()) {
            log("Verify 1.1 Verify data Is displayed");
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "Failed! No data message is incorrectly");
        } else {
            List<ArrayList<String>> lstReport = page.tblReport.getRowsWithoutHeader(1, false);
            Assert.assertEquals(lstReport.get(0).get(page.colLevel - 1), level, "Failed! Data is not display or level is mismatch with filter data");
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "832")
    @Test(groups = {"smoke_po"})
    public void Agent_RM_VolumeMonitorTest_832() {
        log("@title: Can drilldown to member level and bet history icon display");
        log("Step 1.Navigate Risk Management > Volume Monitor");
        VolumeMonitorPage page = agentHomePage.navigateVolumeMonitorPage();
        String fromDate = DateUtils.getDate(-14, "dd/MM/yyyy", "GMT-4");
        String level = "MA";

        log("Step 2: Filter matched bet and the sport has order");
        page.filter(level, "All", "Select All", fromDate, "");

        log("Step 3 Click on Username and drill down to member level");
        page.drilldownToLevel("Member");

        log(" Verify 1 Level is Member and Bet History display icon");
        List<ArrayList<String>> lstReport = page.tblReport.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstReport.get(0).get(page.colLevel - 1), "Member", "Failed! Data is not display or level is mismatch with filter data");

        log("INFO: Executed completely");
    }

    @TestRails(id = "833")
    @Test(groups = {"smoke_po"})
    public void Agent_RM_VolumeMonitorTest_833() {
        log("@title: Can open Bet History");
        log("Step 1.Navigate Risk Management > Volume Monitor");
        VolumeMonitorPage page = agentHomePage.navigateVolumeMonitorPage();
        String fromDate = DateUtils.getDate(-14, "dd/MM/yyyy", "GMT-4");

        log("Step 2. Filter the sport has order in any status");
        page.filter("MA", "Soccer", "Select All", fromDate, "");

        log("Step 3 Click on Username and drill down to member level");
        page.drilldownToLevel("Member");

        log("Step 4 Click on Bet History icon");
        List<ArrayList<String>> lstDataFirstRow = page.tblReport.getRowsWithoutHeader(1, false);
        String username = lstDataFirstRow.get(0).get(page.colUsername - 1);
        page.clickBetHistory(username);

        log("Verify 1.Verify Bet History table is display");
        Assert.assertTrue(page.isBetHistoryofUserName(username), "Failed! Bet history contains invalid user");

        log("INFO: Executed completely");
    }
}
