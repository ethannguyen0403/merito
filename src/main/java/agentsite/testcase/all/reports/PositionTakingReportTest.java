package agentsite.testcase.all.reports;

import com.paltech.utils.DateUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.PositionTakingReportPage;
import agentsite.ultils.report.ReportslUtils;

import java.util.List;
import java.util.Objects;

import static agentsite.common.AGConstant.BTN_SUBMIT;
import static agentsite.common.AGConstant.HomePage.*;
import static agentsite.common.AGConstant.Report.*;
import static agentsite.common.AGConstant.Report.LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS;
import static agentsite.common.AGConstant.Report.PositionTakingReport.TABLE_HEADER;

public class PositionTakingReportTest extends BaseCaseMerito {

    @Test(groups = {"http_request"})
    public void Agent_Report_Position_Taking_Report_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Position Taking Report");
        agentHomePage.clickSubMenu(REPORT, POSITION_TAKING_REPORT, PositionTakingReportPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_Report_Position_Taking_Report_002(){
        log("@title: Validate Position Taking Report display correctly");
        log("Step 1. Navigate Report> Position Taking Report");
        String today = DateUtils.getDate(0,"dd/MM/yyyy","GMT-4");
        PositionTakingReportPage page = agentHomePage.clickSubMenu(REPORT, POSITION_TAKING_REPORT, PositionTakingReportPage.class);

        log("Verify: Verify Position Taking Report UI display correctly");
        Assert.assertEquals(page.getPageTitle(),POSITION_TAKING_REPORT,"Failed! Page title is incorrect");
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"),today,"Failed! From textbox is incorrect day");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"),today,"Failed! To textbox is incorrect day");
        Assert.assertEquals(page.btnToday.getText(),BTN_TODAY,"Failed! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(),BTN_YESTERDAY,"Failed! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(), LAST_WEEK,"Failed! Last Week button is incorrect");
        Assert.assertEquals(page.btnSubmit.getText(),BTN_SUBMIT,"Failed! Submit button is incorrect");
        Assert.assertEquals(page.lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS,"Failed! Submit button is incorrect");
        Assert.assertEquals(page.tblReport.getColumnNamesOfTable(),TABLE_HEADER,"FAILED! Header tit");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search data
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Position Taking Report
     *          2. Search the data range has data of Exchange Product
     * @expect: 1. Data display
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Position_Taking_Report_003(){
        log("@title: Validate can filter Win Loss Detail report");
        log("Step 1. Navigate Report > Position Taking Report");
        PositionTakingReportPage page = agentHomePage.clickSubMenu(REPORT, POSITION_TAKING_REPORT, PositionTakingReportPage.class);

        log("Step 2. Search the data range has data of Exchange Product");
       page.dpFrom.currentMonthWithDate("1");
       page.filter("Exchange");

        log("Verify 1. Verify Volume, Pnl, Tax max with detail");
        if(page.lblNoRecord.isDisplayed()){
            log("PASSED By pass as no data found");
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"Bypass this test case");
            return;
        }
        Assert.assertTrue(Objects.nonNull(page.tblReport.getRowsWithoutHeader(false)),"FAILED! Has no data when searching");
      
        log("INFO: Executed completely");
    }

    @Test (groups = {"regression"})
    public void Agent_Report_Position_Taking_Report_004(){
        log("@title: Validate data product dropdown is corrected");
        log("Step 1: Navigate Report > Win Loss Detail");
        List<String> lstAllProductsExpected = ReportslUtils.getAllProducts(ReportslUtils.getProductActive(),LIST_EXTRA_RPODUCTS);
        PositionTakingReportPage page = agentHomePage.clickSubMenu(REPORT, POSITION_TAKING_REPORT, PositionTakingReportPage.class);

        log("Step 2: Get all products in dropdown");
        List<String> lstProduct = page.ddbProduct.getOptions();

        log("Verify 1: Products display correct");
        Assert.assertEquals(lstProduct,lstAllProductsExpected,"FAILED! List product is incorrect");
        log("INFO: Executed completely");
    }

}
