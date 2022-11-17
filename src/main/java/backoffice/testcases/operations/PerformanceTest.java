package backoffice.testcases.operations;

import backoffice.common.BOConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.operations.PerformancePage;
import baseTest.BaseCaseMerito;
import util.testraildemo.TestRails;

import java.util.List;

public class PerformanceTest extends BaseCaseMerito{

    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Access Reports > Performance
     * @expect: 1. There is no http responded error returned
     */
    @Test (groups = {"http_request"})
    public void BO_Report_Performance_001(){
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Access Reports > Performance");
        backofficeHomePage.navigatePerformance();

        log("Verify 1. There is no http responded error returned");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI correctly when click Create New Line button
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Access Reports > Performance
     * @expect: 1. Verify UI after clicking on Create/Manage Lines display correctly
     */
    @TestRails(id = "639")
    @Test (groups = {"smoke"})
    public void BO_Report_Performance_002(){
        log("@title: Validate UI correctly when click Create New Line button");
        log("Step 1. Access Reports > Performance");
        PerformancePage page =backofficeHomePage.navigatePerformance();
        page.btnCreateManageLine.click();

        log("Verify 1. Verify UI after clicking on Create/Manage Lines display correctly");
        Assert.assertEquals( page.lblCreateNewLine.getText(), BOConstants.Reports.Performance.CREATE_NEW_LINE,"FAILED! Create new line title is incorrect");
        Assert.assertEquals( page.btnBack.getText(), BOConstants.Reports.Performance.BACK,"FAILED! Back buton is incorrect");
        Assert.assertTrue( page.ddbBrand.isDisplayed(),"FAILED! Brand Dropdown does not display");
        Assert.assertTrue( page.ddbLevel.isDisplayed(),"FAILED! Level Dropdown does not display");
        Assert.assertTrue( page.ddbUplineId.isDisplayed(),"FAILED! Upline ID Dropdown does not display");
        Assert.assertTrue( page.txtLineName.isDisplayed(),"FAILED! Line Name textbox does not display");
        Assert.assertTrue( page.txtMappedAccountID.isDisplayed(),"FAILED!Mapped Account ID does not display");
        Assert.assertEquals(page.btnCreate.getText(),BOConstants.Reports.Performance.CREATE,"FAILED! Create button text is incorrect");
        Assert.assertTrue(page.txtSearchBrand.isDisplayed(),"FAILED Search Brand Textbox does not display");
        Assert.assertTrue(page.txtSearchLevel.isDisplayed(),"FAILED Search Level Textbox does not display");
        Assert.assertTrue(page.txtSearchLine.isDisplayed(),"FAILED Search Line Textbox does not display");
        Assert.assertTrue(page.txtSearchUplineID.isDisplayed(),"FAILED Search Upline ID Textbox does not display");
        Assert.assertTrue(page.txtSearhID.isDisplayed(),"FAILED Search Mapped Account ID Textbox does not display");
        Assert.assertEquals(page.tblLine.getHeaderNameOfRows(),BOConstants.Reports.Performance.TBL_HEADER,"FAILED! HEader table is incorrect as expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI correctly
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Access Reports > Performance
     * @expect: 1. UI on Performance page is correct
     */
    @TestRails(id = "640")
    @Test (groups = {"smoke"})
    public void BO_Report_Performance_003(){
        log("@title: Validate UI correctly");
        log("Step 1. Access Reports > Performance");
        PerformancePage page =backofficeHomePage.navigatePerformance();

        log("Verify 1.1 UI Control on header section display correctly");
        Assert.assertEquals(page.btnCreateManageLine.getText(),BOConstants.Reports.Performance.BTN_CREATE_MANAGE_LINE,"FAILED! Create Manage Line button displays incorrect");
        Assert.assertTrue(page.ddpLine.isDisplayed(),"FAILED! Line Dropdown is not displayed");
        Assert.assertTrue(page.txtFrom.isDisplayed(),"FAILED! From Text box is not displayed");
        Assert.assertTrue(page.txtTo.isDisplayed(),"FAILED! To Text box is not displayed");
        Assert.assertTrue(page.btnLastWeek.isDisplayed(),"FAILED! Last Week button is not displayed");
        Assert.assertTrue(page.btnLast30Days.isDisplayed(),"FAILED! Last 30 Days button is not displayed");
        Assert.assertTrue(page.btnLast90Days.isDisplayed(),"FAILED! Last 90 Days button is not displayed");
        Assert.assertTrue(page.btnLast365Days.isDisplayed(),"FAILED! Last 365 Days button is not displayed");
        Assert.assertTrue(page.btnSubmit.isDisplayed(),"FAILED! Submit button is not displayed");

        log("Verify 1.2 UI Control on PT SETTING section display correctly");
        Assert.assertEquals(page.lblPTSetting.getText(), BOConstants.Reports.Performance.PT_SETTING,"FAILED! PT SETTING header label is incorrect");
        Assert.assertEquals(page.lblNoOfBEt.getText(), BOConstants.Reports.Performance.LBL_NO_OF_BET,"FAILED! No. of bets label is incorrect");
        Assert.assertTrue(page.txtNoOfBet.isDisplayed(),"FAILED! No .of bet textbox is not displayed");
        Assert.assertTrue(page.txtMemberWinLossFrom.isDisplayed(),"FAILED! Member Win/loss From textbox is not displayed");
        Assert.assertTrue(page.txtMemberWinLossTo.isDisplayed(),"FAILED! Member Win/loss To textbox is not displayed");
        Assert.assertTrue(page.ddbCurrencyType.isDisplayed(),"FAILED! Currency Type dropdown is not displayed");
        Assert.assertEquals(page.btnSubmitPTSetting.getText(),BOConstants.Reports.Performance.BTN_SUBMIT,"FAILED! Submit button in PT Setting section is incorrect");
        List<String> lstPTSettingHeaderTable = page.tblPTSetting.getHeaderNameOfRows();
        Assert.assertEquals(lstPTSettingHeaderTable,BOConstants.Reports.Performance.TBL_HEADER_PT_SETTING,"FAILED! Table header PT Setting is incorrect displayed");

        log("Verify 1.3 UI Control on LINE OVERVIEW section display correctly");
        page.lblLineOverview.scrollDownInDistance();
        Assert.assertEquals(page.lblLineOverview.getText(), BOConstants.Reports.Performance.LINE_OVERVIEW,"FAILED! LINE OVERVIEW  header label is incorrect");
        Assert.assertEquals(page.btnMemberTree.getText(),BOConstants.Reports.Performance.MEMBER_TREE,"FAILED! Member Tree button is incorrect displayed");
        Assert.assertEquals(page.lblLineOverviewNote.getText(),BOConstants.Reports.Performance.NOTE,"FAILED! Note message in Line Overview section is incorrect displayed");
        Assert.assertEquals(page.lblGeneralInformation.getText(),BOConstants.Reports.Performance.GENERAL_INFORMATION,"FAILED! General Information label is incorrect displayed");
        Assert.assertEquals(page.lblTurnover.getText(),BOConstants.Reports.Performance.TURNOVER,"FAILED! Turnover label is incorrect displayed");
        Assert.assertEquals(page.lblAverageStake.getText(),BOConstants.Reports.Performance.AVERAGE_STAKE,"FAILED! Average Stake label is incorrect displayed");
        Assert.assertEquals(page.lblPTByTurnover.getText(),BOConstants.Reports.Performance.PT_BY_TURNOVER,"FAILED! PT by Turnover label is incorrect displayed");
        Assert.assertEquals(page.lblWinLoss.getText(),BOConstants.Reports.Performance.WIN_LOSS,"FAILED! Win/Loss label is incorrect displayed");
        Assert.assertEquals(page.lblPerformance.getText(),BOConstants.Reports.Performance.PERFORMANCE,"FAILED! Performance label is incorrect displayed");

        Assert.assertEquals(page.tblGeneralInformation1.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_GENERAL_INFORMATION_1,"FAILED! General Information 1 header is incorrect");
        Assert.assertEquals(page.tblGeneralInformation2.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_GENERAL_INFORMATION_2,"FAILED! General Information 2 header is incorrect");
        Assert.assertEquals(page.tblTurnover.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_TURNOVER,"FAILED! Turnover header is incorrect");
        Assert.assertEquals(page.tblAverageStake.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_TURNOVER,"FAILED! Average Stake header is incorrect");
        Assert.assertEquals(page.tblPTByTurnover.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_PT_BY_TURNOVER,"FAILED! PT By Turnover header is incorrect");
        Assert.assertEquals(page.tblWinLoss.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_WIN_LOSS,"FAILED! Win/Loss table header is incorrect");
        Assert.assertEquals(page.tblPerformance.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_PERFORMANCE,"FAILED! Performance header is incorrect");


        log("Verify 1.3 UI Control on TOP PERFORMERS section display correctly");
        page.lblTopPerformance.scrollDownInDistance();
        Assert.assertEquals(page.lblTopPerformance.getText(), BOConstants.Reports.Performance.TOP_PERFORMERS,"FAILED! TOP PERFORMERS header label is incorrect");
        Assert.assertEquals(page.lblTopPerformanceNote.getText(), BOConstants.Reports.Performance.NOTE,"FAILED! Note message in TOP PERFORMERS section is incorrect displayed");
        Assert.assertEquals(page.btnMember.getText(),BOConstants.Reports.Performance.MEMBER,"FAILED! Member button is incorrect displayed");
        Assert.assertEquals(page.btnLine.getText(),BOConstants.Reports.Performance.LINE,"FAILED! Line button is incorrect displayed");
        Assert.assertEquals(page.tblTopPerformer.getColumnNamesOfTable(),BOConstants.Reports.Performance.TBL_HEADER_TOP_PERFORMERS,"FAILED! Top Performers header table is incorrect displayed");

        log("INFO: Executed completely");
    }
}
