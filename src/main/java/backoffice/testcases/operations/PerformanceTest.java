package backoffice.testcases.operations;

import backoffice.common.BOConstants;
import backoffice.pages.bo.operations.PerformancePage;
import backoffice.utils.operations.PerformanceUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.io.IOException;
import java.util.List;

public class PerformanceTest extends BaseCaseTest {

    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Performance
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void BO_Report_Performance_001() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Access Reports > Performance");
        backofficeHomePage.navigatePerformance();

        log("Verify 1. There is no http responded error returned");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI correctly when click Create New Line button
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Performance
     * @expect: 1. Verify UI after clicking on Create/Manage Lines display correctly
     */
    @TestRails(id = "639")
    @Test(groups = {"smoke"})
    public void BO_Report_Performance_639() {
        log("@title: Validate UI correctly when click Create New Line button");
        log("Step 1. Access Reports > Performance");
        PerformancePage page = backofficeHomePage.navigatePerformance();
        page.btnCreateManageLine.click();

        log("Verify 1. Verify UI after clicking on Create/Manage Lines display correctly");
        Assert.assertEquals(page.lblCreateNewLine.getText(), BOConstants.Reports.Performance.CREATE_NEW_LINE, "FAILED! Create new line title is incorrect");
        Assert.assertEquals(page.btnBack.getText(), BOConstants.Reports.Performance.BACK, "FAILED! Back buton is incorrect");
        Assert.assertTrue(page.ddbBrand.isDisplayed(), "FAILED! Brand Dropdown does not display");
        Assert.assertTrue(page.ddbLevel.isDisplayed(), "FAILED! Level Dropdown does not display");
        Assert.assertTrue(page.ddbUplineId.isDisplayed(), "FAILED! Upline ID Dropdown does not display");
        Assert.assertTrue(page.txtLineName.isDisplayed(), "FAILED! Line Name textbox does not display");
        Assert.assertTrue(page.txtMappedAccountID.isDisplayed(), "FAILED!Mapped Account ID does not display");
        Assert.assertEquals(page.btnCreate.getText(), BOConstants.Reports.Performance.CREATE, "FAILED! Create button text is incorrect");
        Assert.assertTrue(page.txtSearchBrand.isDisplayed(), "FAILED Search Brand Textbox does not display");
        Assert.assertTrue(page.txtSearchLevel.isDisplayed(), "FAILED Search Level Textbox does not display");
        Assert.assertTrue(page.txtSearchLine.isDisplayed(), "FAILED Search Line Textbox does not display");
        Assert.assertTrue(page.txtSearchUplineID.isDisplayed(), "FAILED Search Upline ID Textbox does not display");
        Assert.assertTrue(page.txtSearhID.isDisplayed(), "FAILED Search Mapped Account ID Textbox does not display");
        Assert.assertEquals(page.tblLine.getHeaderNameOfRows(), BOConstants.Reports.Performance.TBL_HEADER, "FAILED! HEader table is incorrect as expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI correctly
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Performance
     * @expect: 1. UI on Performance page is correct
     */
    @TestRails(id = "640")
    @Test(groups = {"smoke"})
    public void BO_Report_Performance_640() {
        log("@title: Validate UI correctly");
        log("Step 1. Access Reports > Performance");
        PerformancePage page = backofficeHomePage.navigatePerformance();

        log("Verify 1.1 UI Control on header section display correctly");
        Assert.assertEquals(page.btnCreateManageLine.getText(), BOConstants.Reports.Performance.BTN_CREATE_MANAGE_LINE, "FAILED! Create Manage Line button displays incorrect");
        Assert.assertTrue(page.ddpLine.isDisplayed(), "FAILED! Line Dropdown is not displayed");
        Assert.assertTrue(page.txtFrom.isDisplayed(), "FAILED! From Text box is not displayed");
        Assert.assertTrue(page.txtTo.isDisplayed(), "FAILED! To Text box is not displayed");
        Assert.assertTrue(page.btnLastWeek.isDisplayed(), "FAILED! Last Week button is not displayed");
        Assert.assertTrue(page.btnLast30Days.isDisplayed(), "FAILED! Last 30 Days button is not displayed");
        Assert.assertTrue(page.btnLast90Days.isDisplayed(), "FAILED! Last 90 Days button is not displayed");
        Assert.assertTrue(page.btnLast365Days.isDisplayed(), "FAILED! Last 365 Days button is not displayed");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button is not displayed");

        log("Verify 1.2 UI Control on PT SETTING section display correctly");
        Assert.assertEquals(page.lblPTSetting.getText(), BOConstants.Reports.Performance.PT_SETTING, "FAILED! PT SETTING header label is incorrect");
        Assert.assertEquals(page.lblNoOfBEt.getText(), BOConstants.Reports.Performance.LBL_NO_OF_BET, "FAILED! No. of bets label is incorrect");
        Assert.assertTrue(page.txtNoOfBet.isDisplayed(), "FAILED! No .of bet textbox is not displayed");
        Assert.assertTrue(page.txtMemberWinLossFrom.isDisplayed(), "FAILED! Member Win/loss From textbox is not displayed");
        Assert.assertTrue(page.txtMemberWinLossTo.isDisplayed(), "FAILED! Member Win/loss To textbox is not displayed");
        Assert.assertTrue(page.ddbCurrencyType.isDisplayed(), "FAILED! Currency Type dropdown is not displayed");
        Assert.assertEquals(page.btnSubmitPTSetting.getText(), BOConstants.Reports.Performance.BTN_SUBMIT, "FAILED! Submit button in PT Setting section is incorrect");
        List<String> lstPTSettingHeaderTable = page.tblPTSetting.getHeaderNameOfRows();
        Assert.assertEquals(lstPTSettingHeaderTable, BOConstants.Reports.Performance.TBL_HEADER_PT_SETTING, "FAILED! Table header PT Setting is incorrect displayed");

        log("Verify 1.3 UI Control on LINE OVERVIEW section display correctly");
        page.lblLineOverview.scrollDownInDistance();
        Assert.assertEquals(page.lblLineOverview.getText(), BOConstants.Reports.Performance.LINE_OVERVIEW, "FAILED! LINE OVERVIEW  header label is incorrect");
        Assert.assertEquals(page.btnMemberTree.getText(), BOConstants.Reports.Performance.MEMBER_TREE, "FAILED! Member Tree button is incorrect displayed");
        Assert.assertEquals(page.lblLineOverviewNote.getText(), BOConstants.Reports.Performance.NOTE, "FAILED! Note message in Line Overview section is incorrect displayed");
        Assert.assertEquals(page.lblGeneralInformation.getText(), BOConstants.Reports.Performance.GENERAL_INFORMATION, "FAILED! General Information label is incorrect displayed");
        Assert.assertEquals(page.lblTurnover.getText(), BOConstants.Reports.Performance.TURNOVER, "FAILED! Turnover label is incorrect displayed");
        Assert.assertEquals(page.lblAverageStake.getText(), BOConstants.Reports.Performance.AVERAGE_STAKE, "FAILED! Average Stake label is incorrect displayed");
        Assert.assertEquals(page.lblPTByTurnover.getText(), BOConstants.Reports.Performance.PT_BY_TURNOVER, "FAILED! PT by Turnover label is incorrect displayed");
        Assert.assertEquals(page.lblWinLoss.getText(), BOConstants.Reports.Performance.WIN_LOSS, "FAILED! Win/Loss label is incorrect displayed");
        Assert.assertEquals(page.lblPerformance.getText(), BOConstants.Reports.Performance.PERFORMANCE, "FAILED! Performance label is incorrect displayed");

        Assert.assertEquals(page.tblGeneralInformation1.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_GENERAL_INFORMATION_1, "FAILED! General Information 1 header is incorrect");
        Assert.assertEquals(page.tblGeneralInformation2.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_GENERAL_INFORMATION_2, "FAILED! General Information 2 header is incorrect");
        Assert.assertEquals(page.tblTurnover.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_TURNOVER, "FAILED! Turnover header is incorrect");
        Assert.assertEquals(page.tblAverageStake.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_TURNOVER, "FAILED! Average Stake header is incorrect");
        Assert.assertEquals(page.tblPTByTurnover.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_PT_BY_TURNOVER, "FAILED! PT By Turnover header is incorrect");
        Assert.assertEquals(page.tblWinLoss.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_WIN_LOSS, "FAILED! Win/Loss table header is incorrect");
        Assert.assertEquals(page.tblPerformance.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_PERFORMANCE, "FAILED! Performance header is incorrect");


        log("Verify 1.3 UI Control on TOP PERFORMERS section display correctly");
        page.lblTopPerformance.scrollDownInDistance();
        Assert.assertEquals(page.lblTopPerformance.getText(), BOConstants.Reports.Performance.TOP_PERFORMERS, "FAILED! TOP PERFORMERS header label is incorrect");
        Assert.assertEquals(page.lblTopPerformanceNote.getText(), BOConstants.Reports.Performance.NOTE, "FAILED! Note message in TOP PERFORMERS section is incorrect displayed");
        Assert.assertEquals(page.btnMember.getText(), BOConstants.Reports.Performance.MEMBER, "FAILED! Member button is incorrect displayed");
        Assert.assertEquals(page.btnLine.getText(), BOConstants.Reports.Performance.LINE, "FAILED! Line button is incorrect displayed");
        Assert.assertEquals(page.tblTopPerformer.getColumnNamesOfTable(), BOConstants.Reports.Performance.TBL_HEADER_TOP_PERFORMERS, "FAILED! Top Performers header table is incorrect displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "29645")
    @Parameters({"satSADAgentLoginID", "agentLevel"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void BO_Line_Performance_29645(String satSADAgentLoginID, String agentLevel) throws IOException {
        log("@title: Validate UI correctly when create/update Line");
        log("Precondition: Create new Line");
        String brandName = "FairExchange";
        String newLineName = satSADAgentLoginID + 1;
        try {
            log("Step 1: Access Reports > Performance > Create/Manage Lines");
            PerformancePage page = backofficeHomePage.navigatePerformance();
            log("Step 2: Create new Line");
            page.createNewLine(brandName, agentLevel, satSADAgentLoginID, "", satSADAgentLoginID);
            log("Step 3: Filter created Line");
            page.searchLineInManagePage("", "", satSADAgentLoginID, "", "");
            log("Verify 1: Create new Line");
            Assert.assertTrue(page.isLineCorrect(brandName, agentLevel, satSADAgentLoginID, "", satSADAgentLoginID), "FAILED! The Line is not correct");
            log("Step 4: Click on edit button");
            log("Step 5: Update the valid data criterial");
            log("Step 6: Click on Update button >> observe");
            page.editLine("", "", satSADAgentLoginID, newLineName, "", "");
            log("Verify 2: Line displayed updated data correctly");
            Assert.assertTrue(page.isLineCorrect(brandName, agentLevel, newLineName, "", satSADAgentLoginID), "FAILED! The Line is not updated");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Remove created line");
            String lineID = PerformanceUtils.getLineID(satSADAgentLoginID);
            PerformanceUtils.deleteLine(lineID);
            String lineIDNew = PerformanceUtils.getLineID(newLineName);
            PerformanceUtils.deleteLine(lineIDNew);
        }
    }

    @TestRails(id = "29646")
    @Parameters({"satSADAgentLoginID", "agentLevel"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void BO_Line_Performance_29646(String satSADAgentLoginID, String agentLevel) throws IOException {
        log("@title: Validate UI correctly when delete Line ");
        log("Precondition: Create new Line");
        String brandName = "FairExchange";
        PerformanceUtils.createNewLine(PerformanceUtils.getBrandID(brandName), agentLevel, satSADAgentLoginID, satSADAgentLoginID);
        try {
            log("Step 1: Access Reports > Performance > Create/Manage Lines");
            PerformancePage page = backofficeHomePage.navigatePerformance();
            page.clickOnCreateManageLineButton();
            log("Step 2: Select any Line");
            log("Step 3: Click on delete button then click on Confirm button");
            page.searchLineInManagePage("", "", satSADAgentLoginID, "", "");
            page.deleteLine(satSADAgentLoginID);
            log("Verify 1: Line displayed updated data correctly");
            Assert.assertFalse(page.isLineCorrect("", "", satSADAgentLoginID, "", ""), "FAILED! The Line is not deleted successfully");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Remove created line");
            String lineID = PerformanceUtils.getLineID(satSADAgentLoginID);
            PerformanceUtils.deleteLine(lineID);;
        }
}

    @TestRails(id = "29647")
    @Parameters({"satSADAgentLoginID", "agentLevel"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void BO_Line_Performance_29647(String satSADAgentLoginID, String agentLevel) throws IOException {
        log("@title: Validate showing Current PT if settings on all groups are the same");
        log("Precondition-Step 1: Create new Line");
        String ptAmount = "15";
        String brandName = "FairExchange";
        PerformanceUtils.createNewLine(PerformanceUtils.getBrandID(brandName), agentLevel, satSADAgentLoginID, satSADAgentLoginID);
        log("Precondition-Step 2: Have some lines with settings on all groups are the same (e,g. Current PT of all groups is 15)");
        String lineID = PerformanceUtils.getLineID(satSADAgentLoginID);
        String memberID = PerformanceUtils.getMemberID(lineID);
        PerformanceUtils.updatePTLine(PerformanceUtils.setAllPTToSports(ptAmount), memberID);
        try {
            log("Step 1: Access Reports > Performance");
            PerformancePage page = backofficeHomePage.navigatePerformance();
            log("Step 2: Filter data at pre-condition\n");
            page.searchLine(satSADAgentLoginID);
            log("Verify 1: Showing Current PT is 15");
            Assert.assertEquals(page.getCurrentPTShow(1), ptAmount, "FAILED! PT is show wrong");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Remove created line");
            PerformanceUtils.deleteLine(lineID);;
        }
    }

    @TestRails(id = "29648")
    @Parameters({"satSADAgentLoginID", "agentLevel"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void BO_Line_Performance_29648(String satSADAgentLoginID, String agentLevel) throws IOException {
        log("@title: Validate showing Current PT If there are 2 or above distinct settings");
        log("Precondition-Step 1: Create new Line");
        String ptAmount = "15";
        String brandName = "FairExchange";
        PerformanceUtils.createNewLine(PerformanceUtils.getBrandID(brandName), agentLevel, satSADAgentLoginID, satSADAgentLoginID);
        log("Precondition-Step 2: Have some lines with settings on 2 or above distinct settings (e,g. Current PT of soccer is 15, Current PT of soccer is 10)\n");
        String lineID = PerformanceUtils.getLineID(satSADAgentLoginID);
        String memberID = PerformanceUtils.getMemberID(lineID);
        PerformanceUtils.ptMap.put("Soccer", ptAmount);
        PerformanceUtils.ptMap.put("Cricket", ptAmount);
        PerformanceUtils.updatePTLine(PerformanceUtils.ptMap, memberID);
        try {
            log("Step 1: Access Reports > Performance");
            PerformancePage page = backofficeHomePage.navigatePerformance();
            log("Step 2: Filter data at pre-condition\n");
            page.searchLine(satSADAgentLoginID);
            log("Verify 1: Showing Current PT is '-'");
            Assert.assertEquals(page.getCurrentPTShow(1), "-", "FAILED! PT is show wrong");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Remove created line");
            PerformanceUtils.deleteLine(lineID);;
        }
    }

    @TestRails(id = "29649")
    @Parameters({"satSADAgentLoginID", "agentLevel"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void BO_Line_Performance_29649(String satSADAgentLoginID, String agentLevel) throws IOException {
        log("@title: Validate showing Current PT If there is 1 settings ");
        log("Precondition-Step 1: Create new Line");
        String ptAmount = "15";
        String brandName = "FairExchange";
        PerformanceUtils.createNewLine(PerformanceUtils.getBrandID(brandName), agentLevel, satSADAgentLoginID, satSADAgentLoginID);
        log("Precondition-Step 2:Have some lines with 1 setting (e,g. Current PT of soccer is 15)");
        String lineID = PerformanceUtils.getLineID(satSADAgentLoginID);
        String memberID = PerformanceUtils.getMemberID(lineID);
        PerformanceUtils.ptMap.put("Soccer", ptAmount);
        PerformanceUtils.updatePTLine(PerformanceUtils.ptMap, memberID);
        try {
            log("Step 1: Access Reports > Performance");
            PerformancePage page = backofficeHomePage.navigatePerformance();
            log("Step 2: Filter data at pre-condition\n");
            page.searchLine(satSADAgentLoginID);
            log("Verify 1:Showing Current PT is '-'");
            Assert.assertEquals(page.getCurrentPTShow(1), "-", "FAILED! PT is show wrong");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Remove created line");
            PerformanceUtils.deleteLine(lineID);;
        }
    }

    @TestRails(id = "29650")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void BO_Line_Performance_29650() {
        log("@title: Validate showing Max Available PT if there are 2 or above distinct settings");
        log("Precondition-Step 1:Have some lines with total PT of PO and CO on 2 or above groups have distinct settings (e,g. Max Available PT of soccer is 85, cricket is 80)");
        log("Step 1: Access Reports > Performance");
        PerformancePage page = backofficeHomePage.navigatePerformance();
        log("Step 2: Filter data at pre-condition\n");
        page.searchLine("All");
        log("Verify 1:Showing Max PT is '-'");
        page.verifyMaxPTSettingShowCorrect(true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "29651")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void BO_Line_Performance_29651() {
        log("@title: \tValidate showing Max Available PT if setting of all sports are same ");
        log("Precondition-Step 1: Have some lines with total PT of PO and CO on all groups are the same (e,g. Max Available PT of all groups is 85)");
        log("Step 1: Access Reports > Performance");
        PerformancePage page = backofficeHomePage.navigatePerformance();
        log("Step 2: Filter data at pre-condition\n");
        page.searchLine("All");
        log("Verify 1: Showing Max available PT is total PT of PO and CO (show 85)");
        page.verifyMaxPTSettingShowCorrect(false);
        log("INFO: Executed completely");
    }
}
