package agentsite.testcase.reports;

import agentsite.pages.report.PositionTakingReportPage;
import agentsite.ultils.report.ReportslUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.POSITION_TAKING_REPORT;
import static common.AGConstant.Report.*;
import static common.AGConstant.Report.PositionTakingReport.TABLE_HEADER;

public class PositionTakingReportTest extends BaseCaseTest {

    @TestRails(id = "3739")
    @Test(groups = {"http_request"})
    public void Agent_Report_Position_Taking_Report_3739() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Position Taking Report");
        agentHomePage.navigatePositionTakingReportPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3740")
    @Test(groups = {"regression","tim"})
    public void Agent_Report_Position_Taking_Report_3740() {
        log("@title: Validate Position Taking Report display correctly");
        log("Step 1. Navigate Report> Position Taking Report");
        String today = DateUtils.getDate(0, "dd/MM/yyyy", "GMT-4");
        PositionTakingReportPage page = agentHomePage.navigatePositionTakingReportPage();

        log("Verify: Verify Position Taking Report UI display correctly");
        Assert.assertEquals(page.header.lblPageTitle.getText(), POSITION_TAKING_REPORT, "Failed! Page title is incorrect");
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"), today, "Failed! From textbox is incorrect day");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"), today, "Failed! To textbox is incorrect day");
        Assert.assertEquals(page.btnToday.getText(), BTN_TODAY, "Failed! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(), BTN_YESTERDAY, "Failed! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(), LAST_WEEK, "Failed! Last Week button is incorrect");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "Failed! Submit button is incorrect");
        Assert.assertEquals(page.lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS, "Failed! Submit button is incorrect");
        Assert.assertEquals(page.tblReport.getColumnNamesOfTable(), TABLE_HEADER, "FAILED! Header tit");
        log("INFO: Executed completely");
    }

    @TestRails(id = "797")
    @Test(groups = {"smoke"})
    public void Agent_Report_Position_Taking_Report_797() {
        log("@title: Validate can search data");
        log("Step 1. Navigate Report > Position Taking Report");
        PositionTakingReportPage page = agentHomePage.navigatePositionTakingReportPage();

        log("Step 2. Search the data range has data of Exchange Product");
        page.dpFrom.currentMonthWithDate("1");
        page.filter("Exchange");

        log("Verify 1. Verify data is displayed");
        if (page.lblNoRecord.isDisplayed()) {
            log("PASSED By pass as no data found");
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND, "Bypass this test case");
            return;
        }
        Assert.assertTrue(Objects.nonNull(page.tblReport.getRowsWithoutHeader(false)), "FAILED! Has no data when searching");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4088")
    @Test(groups = {"regression","tim"})
    public void Agent_Report_Position_Taking_Report_4088() {
        log("@title: Validate data product dropdown is corrected");
        log("Step 1: Navigate Report > Position Taking Report");
        List<String> lstAllProductsExpected = ReportslUtils.getAllProducts(ReportslUtils.getProductActive());
        PositionTakingReportPage page = agentHomePage.navigatePositionTakingReportPage();

        log("Step 2: Get all products in dropdown");
        List<String> lstProduct = page.ddbProduct.getOptions();

        log("Verify 1: Products display correct");
        Assert.assertTrue(lstProduct.containsAll(lstAllProductsExpected), String.format("FAILED! List product is incorrect expected %s actual %s", lstAllProductsExpected, lstProduct));
        log("INFO: Executed completely");
    }

}
