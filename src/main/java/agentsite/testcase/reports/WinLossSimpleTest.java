package agentsite.testcase.reports;

import agentsite.pages.report.WinLossSimplePage;
import agentsite.ultils.report.ReportslUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class WinLossSimpleTest extends BaseCaseTest {

    @TestRails(id = "3736")
    @Test(groups = {"http_request"})
    public void Agent_Report_WinLossSimple_3736() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > Win Loss Simple");
        agentHomePage.navigateWinLossSimplePage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3737")
    @Test(groups = {"regression","tim"})
    public void Agent_Report_WinLossSimple_3737() {
        log("@title: Validate Win Loss Simple UI display correctly");
        log("Step 1: Navigate Report > Win Loss Simple");
        WinLossSimplePage page = agentHomePage.navigateWinLossSimplePage();

        log("Step 1. Verify Win Loss Simple UI display correctly");
        page.verifyUIDisplaysCorrect();

        log("INFO: Executed completely");
    }

    /**
     * @title: Valid can filter Win Loss Simple report
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Select data range that have data and Exchange product
     * @expect: 1. Win Loss Simple Report display correctly
     */
    @TestRails(id = "791")
    @Test(groups = {"smoke"})
    @Parameters("memberAccount")
    public void Agent_Report_WinLossSimple_791(String memberAccount) {
        log("@title: Valid can filter Win Loss Simple report");
        log("Step 1: Navigate Report > Win Loss Simple");
        WinLossSimplePage page = agentHomePage.navigateWinLossSimplePage();

        log("Step 2. Select data range that have data and Exchange product");
        page.dpFrom.previousMonthWithDate(-1, "20");

        log("Step 3: Click Submit button");
        page.filter("Select All");
        List<String> lstAccount = page.tblSMA.getColumn(page.colUsername, 1, false);

        log("Verify 1. Win Loss Simple Report display correctly");
        for (String observed : lstAccount) {
            Assert.assertTrue(observed.contains(memberAccount), String.format("ERROR: The expected account display is '%s' but found '%s'", memberAccount, observed));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that an error message is displayed when filtering without any product
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Uncheck Product ddb
     * 3. Click Submit button
     * @expect: 1. An error message is displayed if product is unchecked
     */
    @TestRails(id = "792")
    @Test(groups = {"smoke"})
    public void Agent_Report_WinLossSimple_792() {
        log("@title: Validate that an error message is displayed when filtering without any product");
        log("Step 1: Navigate Report > Win Loss Simple");
        WinLossSimplePage page = agentHomePage.navigateWinLossSimplePage();

        log("Step 2: Uncheck Product ddb");
        log("Step 3: Click Submit button");
        page.filter("UnSelect All");

        String msgErrorProduct = page.lblProductErrorMassage.getText();
        log("Verify 1: An error message is displayed if product is unchecked");
        Assert.assertEquals(msgErrorProduct, AGConstant.Report.ERROR_PRODUCT, String.format("ERROR: The expected error message is '%s' but found '%s'", AGConstant.Report.ERROR_PRODUCT, msgErrorProduct));
        log("INFO: Executed completely");
    }

    @TestRails(id = "793")
    @Test(groups = {"smoke"})
    public void Agent_Report_WinLossSimple_793() {
        log("@title: Validate data product dropdown is corrected");
        log("Step 1: Navigate Report > Win Loss Simple");
        List<String> lstAllProductsExpected = ReportslUtils.getAllProducts(ReportslUtils.getProductActive());
        WinLossSimplePage page = agentHomePage.navigateWinLossSimplePage();

        log("Step 2: Get all products in dropdown");
        List<String> lstProduct = page.winLossSimple.ddbProduct.getAllOption(true);

        log("Verify 1: Products display correct");
        Assert.assertTrue(lstAllProductsExpected.containsAll(lstProduct), String.format("FAILED! List product is incorrect. Actual: %s, Expected: %s",lstProduct, lstAllProductsExpected));
        log("INFO: Executed completely");
    }

}
