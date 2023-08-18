package agentsite.testcase.reports;

import agentsite.pages.report.TransactionHistoryPage;
import agentsite.pages.report.WinLossDetailPage;
import agentsite.pages.report.WinLossSimplePage;
import agentsite.ultils.report.ReportslUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.TRANSACTION_HISTORY;
import static common.AGConstant.Report.*;
import static common.AGConstant.Report.TransactionHistory.TABLE_HEADER;

public class TransactionHistoryTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Transaction history
     * @expect: 1. There is no http responded error returned
     */
    @TestRails(id = "3753")
    @Test(groups = {"http_request"})
    public void Agent_Report_Transaction_History_3753() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Transaction history");
        agentHomePage.navigateTransactionHistoryPage();
        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data on Transaction History display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report> Transaction history
     * 2. Search Exchange Product and the date range has data
     * @expect: 1. Verify data of each competition display correctly
     */
    @TestRails(id = "811")
    @Test(groups = {"smoke"})
    public void Agent_Report_Transaction_History_811() {
        log("@title: Validate data on Transaction History display correctly");
        log("Step 1. Navigate Report >  Transaction History");
        TransactionHistoryPage page = agentHomePage.navigateTransactionHistoryPage();

        log("Step 2. Search Exchange Product and the date range has data");
        page.dpFrom.previousMonthWithDate(0, "1");
        page.filter("Exchange");

        log("Verify 1. Verify data of each competition display correctly");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), "No records found.", "FAILED! Incorrect text when have no record");
            return;
        }
        Assert.assertTrue(page.verifyVolumePnLTaxMatchWithDetail(), "FAILED! Summary data not match with Transaction Detail");
        log("INFO: Executed completely");
    }
    @TestRails(id = "812")
    @Test(groups = {"regression"})
    public void Agent_Report_Transaction_History_002() {
        log("@title: Validate data on Transaction History display correctly");
        log("Step 1. Navigate Report >  Transaction History");
        String today = DateUtils.getDate(0, "dd/MM/yyyy", "GMT-4:00");
        TransactionHistoryPage page = agentHomePage.navigateTransactionHistoryPage();

        log("Verify 1. Verify Transaction History display correctly");
        Assert.assertEquals(page.header.lblPageTitle.getText(), TRANSACTION_HISTORY, "Failed! Page title is incorrect");
        Assert.assertEquals(page.lblFrom.getText(), LBL_FROM, "Failed! Today button is incorrect");
        Assert.assertEquals(page.lblTo.getText(), LBL_TO, "Failed! Today button is incorrect");
        Assert.assertEquals(page.lblProduct.getText(), LBL_PRODUCT, "Failed! Today button is incorrect");
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"), today, "Failed! Today button is incorrect");
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"), today, "Failed! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(), BTN_YESTERDAY, "Failed! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(), LAST_WEEK, "Failed! Last Week button is incorrect");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "Failed! Submit button is incorrect");
        Assert.assertEquals(page.lblInfo.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS, "FAILED! Note message is incorrect displayed");
        Assert.assertEquals(page.tblReport.getColumnNamesOfTable(), TABLE_HEADER, "FAILED! Header title is incorrect");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3755")
    @Test(groups = {"regression"})
    public void Agent_Report_Transaction_History_3755() {
        log("@title: Validate data product dropdown is corrected");
        log("Step 1: Navigate Report > Transaction History");
        List<String> lstAllProductsExpected = ReportslUtils.getAllProducts(ReportslUtils.getProductActive());
//        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
//        String winLossDetailMenu = String.format(WIN_LOSS_BY_DETAIL, ProfileUtils.convertDownlineByBrand(lstUsers.get(0).getLevel(), ProfileUtils.getAppName()));
        WinLossDetailPage page = agentHomePage.navigateWinLossDetailPage();

        log("Step 2: Get all products in dropdown");
        List<String> lstProduct = page.ddbProduct.getAllOption(true);

        log("Verify 1: Products display correct");
        Assert.assertEquals(lstProduct, lstAllProductsExpected, "FAILED! List product is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3756")
    @Test(groups = {"regression"})
    public void Agent_Report_Transaction_History_3756() {
        log("@title: Validate that an error message is displayed when filtering without any product");
        log("Step 1: Navigate Report > Transaction History");
        WinLossSimplePage page = agentHomePage.navigateWinLossSimplePage();

        log("Step 2: Uncheck Product ddb");
        log("Step 3: Click Submit button");
        page.filter("UnSelect All");

        String msgErrorProduct = page.lblProductErrorMassage.getText();
        log("Verify 1: An error message is displayed if product is unchecked");
        Assert.assertEquals(msgErrorProduct, AGConstant.Report.ERROR_PRODUCT, String.format("ERROR: The expected error message is '%s' but found '%s'", AGConstant.Report.ERROR_PRODUCT, msgErrorProduct));
        log("INFO: Executed completely");
    }
}
