package agentsite.testcase.reports;

import agentsite.pages.report.WinLossDetailPage;
import agentsite.ultils.report.ReportslUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Collections;
import java.util.List;

public class WinLossDetailTest extends BaseCaseTest {
    @TestRails(id = "3738")
    @Test(groups = {"http_request"})
    public void Agent_Report_WinLossDetail_3738() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > Win Loss Detail");
        agentHomePage.navigateWinLossDetailPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Win Loss Detail UI display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report> Win Loss Detail
     * @expect: 1. Verify Win Loss Detail UI display correctly
     */
    @TestRails(id = "794")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.4.0"})
    public void Agent_Report_WinLossDetail_794() {
        log("@title: Validate Win Loss Detail UI display correctly");
        log("Step 1. Navigate Report> Win Loss Detail");
        WinLossDetailPage page = agentHomePage.navigateWinLossDetailPage();

        log("Verify 1. Verify Win Loss Detail UI display correctly");
        Assert.assertTrue(page.txtFromSearch.isDisplayed(), "FAilED! From textbox does not display");
        Assert.assertTrue(page.txtToSearch.isDisplayed(), "FAilED! To textbox does not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAilED! Product dropdown does not display");
        //Assert.assertEquals(page.lblPageTitle.getText(), FEAGConstant.Report.WinLossDetails.TITLE,"FAilED! Today button text is incorrect");
        Assert.assertEquals(page.btnToday.getText(), AGConstant.Report.BTN_TODAY, "FAilED! Today button text is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(), AGConstant.Report.BTN_YESTERDAY, "FAilED! Yesterday button text is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(), AGConstant.Report.LAST_WEEK, "FAilED! Last Business Week button text is incorrect");
        Assert.assertEquals(page.lblShowTotal.getText(), AGConstant.Report.WinLossDetails.LBL_SHOW_TOTAL, "FAILED! Show total only label display incorrect text");
        Assert.assertEquals(page.lblInfo.getText(), AGConstant.Report.WinLossDetails.LBL_INFO, "FAILED! Hint info label display incorrect text");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can filter Win Loss Detail report
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Search the data range that have data
     * @expect: 1. Verify can display data
     */
    @TestRails(id = "795")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.4.0"})
    @Parameters("downlineAccount")
    public void Agent_Report_WinLossDetail_795(String downlineAccount) {
        log("@title: Validate can filter Win Loss Detail report");
        log("Step 1. Navigate Report > Win Loss Detail");
        WinLossDetailPage page = agentHomePage.navigateWinLossDetailPage();

        log("Step 2. Search the data range that have data");
        page.dpFrom.previousMonthWithDate(-1, "25");
        page.filter("Exchange");
        List<String> lstAccount = page.tblReport.getColumn(page.colUserName, 1, false);

        if (lstAccount.size() == 0) {
            log("Verify 1. Verify as has no data");
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND, "FAILED! No record message not display as has no data");
            return;
        }
        log("Verify 1. Verify can display data");
        for (String observed : lstAccount) {
            Assert.assertTrue(observed.contains(downlineAccount), String.format("ERROR: The expected username not contain account is '%s' but found '%s'", downlineAccount, observed));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "796")
    @Test(groups = {"smoke", "Fairenter.MER.Maintenance.2024.V.5.0"})
    public void Agent_Report_WinLossDetail_796() {
        log("@title: Validate data product dropdown is corrected");
        log("Step 1: Navigate Report > Win Loss Detail");
        List<String> lstAllProductsExpected = ReportslUtils.getAllProducts(ReportslUtils.getProductActive());
        WinLossDetailPage page = agentHomePage.navigateWinLossDetailPage();

        log("Step 2: Get all products in dropdown");
        List<String> lstProduct = page.ddbProduct.getAllOption(true);
        //handle remove Q-Tech out of list product since it's hidden in PROD but dropdown still kept for reporting
        lstProduct.removeAll(Collections.singleton("Q-tech"));

        log("Verify 1: Products display correct");
        Assert.assertTrue( lstAllProductsExpected.containsAll(lstProduct), String.format("FAILED! List product is incorrect. Expected: %s. Actual: %s",lstAllProductsExpected, lstProduct));
        log("INFO: Executed completely");
    }

}
