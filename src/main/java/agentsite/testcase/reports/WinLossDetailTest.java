package agentsite.testcase.reports;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.report.WinLossDetailPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.report.ReportslUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.HomePage.WIN_LOSS_BY_DETAIL;
import static common.AGConstant.Report.LIST_EXTRA_RPODUCTS;

public class WinLossDetailTest extends BaseCaseTest {

    @Test(groups = {"http_request"})
    public void Agent_Report_WinLossDetail_001() {
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
    @Test(groups = {"smoke"})
    public void Agent_Report_WinLossDetail_002() {
        log("@title: Validate Win Loss Detail UI display correctly");
        log("Step 1. Navigate Report> Win Loss Detail");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        String winLossDetailMenu = String.format(WIN_LOSS_BY_DETAIL, ProfileUtils.convertDownlineByBrand(lstUsers.get(0).getLevel(), ProfileUtils.getAppName()));
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
    @Test(groups = {"smoke"})
    @Parameters("memberAccount")
    public void Agent_Report_WinLossDetail_003(String memberAccount) {
        log("@title: Validate can filter Win Loss Detail report");
        log("Step 1. Navigate Report > Win Loss Detail");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        String winLossDetailMenu = String.format(WIN_LOSS_BY_DETAIL, ProfileUtils.convertDownlineByBrand(lstUsers.get(0).getLevel(), ProfileUtils.getAppName()));
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
            Assert.assertTrue(observed.contains(memberAccount), String.format("ERROR: The expected username not containt account is '%s' but found '%s'", memberAccount, observed));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "796")
    @Test(groups = {"smoke"})
    public void Agent_Report_WinLossDetail_004() {
        log("@title: Validate data product dropdown is corrected");
        log("Step 1: Navigate Report > Win Loss Detail");
        List<String> lstAllProductsExpected = ReportslUtils.getAllProducts(ReportslUtils.getProductActive(), LIST_EXTRA_RPODUCTS);
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        String winLossDetailMenu = String.format(WIN_LOSS_BY_DETAIL, ProfileUtils.convertDownlineByBrand(lstUsers.get(0).getLevel(), ProfileUtils.getAppName()));
        WinLossDetailPage page = agentHomePage.navigateWinLossDetailPage();

        log("Step 2: Get all products in dropdown");
        List<String> lstProduct = page.ddbProduct.getAllOption(true);

        log("Verify 1: Products display correct");
        Assert.assertEquals(lstProduct, lstAllProductsExpected, "FAILED! List product is incorrect");
        log("INFO: Executed completely");
    }

}
