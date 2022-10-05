package agentsite.testcase.all.reports;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.ProfitAndLossPage;
import agentsite.pages.all.report.components.TransactionDetailsPopup;
import agentsite.ultils.report.ReportslUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.BTN_SUBMIT;
import static agentsite.common.AGConstant.HomePage.*;
import static agentsite.common.AGConstant.NO_RECORD_FOUND;
import static agentsite.common.AGConstant.Report.*;
import static agentsite.common.AGConstant.Report.ProfitAndLoss.*;

public class ProfitAndLossTest extends BaseCaseMerito {

    @Test (groups = {"http_request"})
    public void Agent_Report_Profit_And_Loss_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report > Profit And Loss");
        agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    @Test (groups = {"regression5"})
    public void Agent_Report_Profit_And_Loss_002(){
        log("@title:Validate Profit and Loss UI display correctly");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Verify:1. Verify  Profit And Loss UI display correctly");
        Assert.assertEquals(page.lblTimeZone.getText(),"Timezone","FAILED! Timezone label is incorrect");
        Assert.assertEquals(page.lblFrom.getText(),LBL_FROM,"FAILED! From label is incorrect");
        Assert.assertEquals(page.lblTo.getText(),LBL_TO,"FAILED! To label is incorrect");
        Assert.assertEquals(page.lblProduct.getText(),"Product","FAILED! Product label is incorrect");
        Assert.assertEquals(page.btnToday.getText(),BTN_TODAY,"FAILED! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(),BTN_YESTERDAY,"FAILED! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(), LAST_WEEK,"FAILED! Last Week button is incorrect");
        Assert.assertEquals(page.btnSubmit.getText(),BTN_SUBMIT,"FAILED! Submit is incorrect");
        Assert.assertEquals(page.lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS,"FAILED Hint message is incorrect");
        Assert.assertEquals(page.lblForISTTimeZoneReportAvailable.getText(),LBL_REPORT_AVAILABLE_IST,"FAILED Hint message is incorrect");
        Assert.assertEquals(page.lblUplineProfitAndLoss.getText(),LBL_UPLINE_PROFIT_AND_LOST,"FAILED Upline profit and lost message is incorrect");
        Assert.assertEquals(page.lblDownlineProfitAndLoss.getText(),LBL_DOWNLINE_PROFIT_AND_LOST,"FAILED Downline profit and lost message is incorrect");
        Assert.assertEquals(page.tblUplineProfitAndLoss.getHeaderList(),TBL_UPLINE_TABLE,"FAILED! Upline table is incorrect");
        Assert.assertEquals(page.tblDownLineProfitAndLoss.getHeaderList(),TBL_DOWNLINE_TABLE,"FAILED! Dowline tablie is incorrect");
        log("INFO: Executed completely");
    }
    @Test (groups = {"poregression"})
    public void Agent_Report_Profit_And_Loss_008(){
        log("@title:Validate Profit and Loss UI display correctly at Portal level");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Verify:1. Verify  Profit And Loss UI display correctly at PO level");
        Assert.assertEquals(page.lblTimeZone.getText(),"Timezone","FAILED! Timezone label is incorrect");
        Assert.assertEquals(page.lblFrom.getText(),LBL_FROM,"FAILED! From label is incorrect");
        Assert.assertEquals(page.lblTo.getText(),LBL_TO,"FAILED! To label is incorrect");
        Assert.assertEquals(page.lblProduct.getText(),"Product","FAILED! Product label is incorrect");
        Assert.assertEquals(page.btnToday.getText(),BTN_TODAY,"FAILED! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(),BTN_YESTERDAY,"FAILED! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(), LAST_WEEK,"FAILED! Last Week button is incorrect");
        Assert.assertEquals(page.btnSubmit.getText(),BTN_SUBMIT,"FAILED! Submit is incorrect");
        Assert.assertEquals(page.lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS,"FAILED Hint message is incorrect");
        Assert.assertEquals(page.lblForISTTimeZoneReportAvailable.getText(),LBL_REPORT_AVAILABLE_IST,"FAILED Hint message is incorrect");
        Assert.assertEquals(page.lblUplineProfitAndLoss.getText(),LBL_UPLINE_PROFIT_AND_LOST_PO,"FAILED Upline profit and lost message is incorrect");
        Assert.assertEquals(page.lblDownlineProfitAndLoss.getText(),LBL_DOWNLINE_PROFIT_AND_LOST_PO,"FAILED Downline profit and lost message is incorrect");
        Assert.assertEquals(page.tblUplineProfitAndLoss.getHeaderList(),TBL_UPLINE_TABLE,"FAILED! Upline table is incorrect");
        Assert.assertEquals(page.tblDownLineProfitAndLoss.getHeaderList(),TBL_DOWNLINE_TABLE,"FAILED! Dowline tablie is incorrect");
        log("INFO: Executed completely");
    }

    @Test (groups = {"regression"})
    public void Agent_Report_Profit_And_Loss_003(){
        log("@title: Validate can filter report of all product");
        log("Step 1. Navigate Report > Profit And Loss");
        List<String> lstAllProducts =ReportslUtils.getAllProducts(ReportslUtils.getProductActive(),LIST_EXTRA_RPODUCTS);
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Step 2.Filter date range that have settled bet and select all product");
        page.filterbyProduct("Select All");
        if(!page.lblNoRecordDowLinePL.isDisplayed()){
            Assert.assertEquals(page.lblNoRecordDowLinePL.getText(),NO_RECORD_FOUND,"FAILED! No record message incorrect display");
            return;
        }
        log("Step 3. Drill down to Member level and click on the user name ");
        String username = page.drilldown("Member");
        TransactionDetailsPopup popup = page.openTransactionDetails(username);
        List<String> productTabs = popup.getProductsListTab();

        log("Verify 2. Verify Transaction detail popup display with all products");
        Assert.assertEquals(productTabs,lstAllProducts,"FAILED!Products list in transaction details is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can filter data on summary display correctly with total in detail
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Profit And Loss
     *          2.Filter date range that have settled bet and select Exchange
     *          3. Get summary data then Drill down to Member level and click on the user name
     * @expect:  1. Verify data display correctly on summary and transaction detail at Member result column
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Profit_And_Loss_004(){
        log("@title: Validate can filter data on summary display correctly with total in detail");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Step 2.Filter date range that have settled bet and select Exchange");
        page.dpTo.previousMonthWithDate(-1, "27");

        if(!page.lblNoRecordDowLinePL.isDisplayed()){
            Assert.assertEquals(page.lblNoRecordDowLinePL.getText(),"","FAILED! No record message incorrect display");
            return;
        }

        log("Step 3. Get summary data then Drill down to Member level and click on the user name");
        List<ArrayList<String>> list = page.drilldownToLevel("Member",true);
        TransactionDetailsPopup popup = new TransactionDetailsPopup();
        ArrayList<String> totalRowData = popup.getTotalRowData();

        log("Verify 1. Verify data display correctly on summary and transaction detail at Member result column");
        Assert.assertEquals(totalRowData.get(5),list.get(1).get(page.colBalance-1),"FAILED! Balance of member in Downine Profit and Loss not match with Total- Member result in Transaction Details");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify data in transaction report display correctly
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Profit And Loss
     *          2.Filter date range that have settled bet and select Exchange
     *          3. Drill down to Member level and click on the user name
     * @expect:  1. Sum member result column and verify it match with total column
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Profit_And_Loss_005(){
        log("@title: Validate can filter data on summary display correctly with total in detail");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Step 2.Filter date range that have settled bet and select Exchange");
        page.dpTo.currentMonthWithDate("1");
        page.filterbyProduct("Exchange");

        if(page.lblNoRecordDowLinePL.isDisplayed()){
            log("Verify Message when have no data");
            Assert.assertEquals(page.lblNoRecordDowLinePL.getText(),NO_RECORD_FOUND,"FAILED! No record message incorrect display");
            return;
        }

        log("Step 3. Get summary data then Drill down to Member level and click on the user name");
        page.drilldownToLevel("Member",true);
        TransactionDetailsPopup popup = new TransactionDetailsPopup();

        log("Verify 1. Sum member result column and verify it match with total column");
        Assert.assertTrue(popup.isColumnDataMatchedWithTotal("Member Result", ProfitAndLossPage.downlineLevelList));

        log("INFO: Executed completely");
    }


    @Test (groups = {"regression"})
    public void Agent_Report_Profit_And_Loss_006(){
        log("@title: Verify data in timezone and product dropdown is correct");
        log("Step 1. Navigate Report > Profit And Loss");
        List<String> lstAllProducts =ReportslUtils.getAllProducts(ReportslUtils.getProductActive(),LIST_EXTRA_RPODUCTS);
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Step 2. Get all data in Timezone and Product dropdown");
       List<String> lstProduct = page.getProductDataDropdown();

        log("Verify 1. Verify data in Timezone is correct display");
        Assert.assertEquals(page.ddbTimeZone.getOptions(),TIMEZONE_LIST,"FAILED! List timezone is incorrect");

        log("Verify 2. Verify data in Product is correct display");
        Assert.assertEquals(lstProduct,lstAllProducts,"FAILED! List timezone is incorrect");
        log("INFO: Executed completely");
    }

    @Test (groups = {"regression"})
    public void Agent_Report_Profit_And_Loss_007(){
        log("@title: Verify Validate message display when filter without any products selected");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Step 2. Get all data in Timezone and Product dropdown");
        page.filterbyProduct("UnSelect All");

        log("Verify 1/messsage dissplay\"Please select at least 1 product.\"");
        Assert.assertEquals(page.lblProductErrorMassage.getText(),LBL_PLEASE_SELECT_PRODUCT,"FAILED! List timezone is incorrect");
        log("INFO: Executed completely");
    }

}
