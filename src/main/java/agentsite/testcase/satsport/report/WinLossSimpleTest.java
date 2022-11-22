package agentsite.testcase.satsport.report;

import agentsite.common.AGConstant;
import agentsite.ultils.report.ReportslUtils;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.report.WinLossSimplePage;
import util.testraildemo.TestRails;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.WIN_LOSS_SIMPLE;
import static agentsite.common.AGConstant.Report.LIST_EXTRA_RPODUCTS;

public class WinLossSimpleTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * @expect: 1. Win Loss Simple page is displayed
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_WinLossSimple_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > Win Loss Simple");
        agentHomePage.clickSubMenu(REPORT, WIN_LOSS_SIMPLE, WinLossSimplePage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Valid can filter Win Loss Simple report
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Report > Win Loss Simple
     *           2. Select data range that have data and Exchange product
     * @expect:  1. Win Loss Simple Report display correctly
     */
    @TestRails(id="791")
    @Test (groups = {"smoke"})
    @Parameters("memberAccount")
    public void Agent_Report_WinLossSimple_003(String memberAccount){
        log("@title: Valid can filter Win Loss Simple report");
        log("Step 1: Navigate Report > Win Loss Simple");
        WinLossSimplePage page = agentHomePage.clickSubMenu(REPORT, WIN_LOSS_SIMPLE, WinLossSimplePage.class);

        log("Step 2. Select data range that have data and Exchange product");
        page.dpFrom.previousMonthWithDate(-1, "20");

        log("Step 3: Click Submit button");
        page.filter("Exchange");
        List<String> lstAccount = page.tblSMA.getColumn(page.colNickname,1, false);
        log("Verify 1. Win Loss Simple Report display correctly");
        for(String observed : lstAccount) {
            Assert.assertEquals(memberAccount, observed, String.format("ERROR: The expected account display is '%s' but found '%s'", memberAccount, observed));
        }

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that an error message is displayed when filtering without any product
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Report > Win Loss Simple
     *           2. Uncheck Product ddb
     *           3. Click Submit button
     * @expect:  1. An error message is displayed if product is unchecked
     */
    @TestRails(id="7921")
    @Test (groups = {"smoke"})
    public void Agent_Report_WinLossSimple_004(){
        log("@title: Validate that an error message is displayed when filtering without any product");
        log("Step 1: Navigate Report > Win Loss Simple");
        WinLossSimplePage page = agentHomePage.clickSubMenu(REPORT, WIN_LOSS_SIMPLE, WinLossSimplePage.class);

        log("Step 2: Uncheck Product ddb");
        page.ddbProduct.uncheckAll(true);

        log("Step 3: Click Submit button");
        page.filter("");

        String msgErrorProduct = page.lblProductErrorMassage.getText();
        log("Verify 1: An error message is displayed if product is unchecked");
        Assert.assertEquals(msgErrorProduct, AGConstant.Report.ERROR_PRODUCT, String.format("ERROR: The expected error message is '%s' but found '%s'", AGConstant.Report.ERROR_PRODUCT, msgErrorProduct));
        log("INFO: Executed completely");
    }

    @TestRails(id="793")
    @Test (groups = {"smoke"})
    public void Agent_Report_WinLossSimple_005(){
        log("@title: Validate data product dropdown is corrected");
        log("Step 1: Navigate Report > Win Loss Simple");
        List<String> lstAllProductsExpected = ReportslUtils.getAllProducts(ReportslUtils.getProductActive(),LIST_EXTRA_RPODUCTS);
        WinLossSimplePage page = agentHomePage.clickSubMenu(REPORT, WIN_LOSS_SIMPLE, WinLossSimplePage.class);

        log("Step 2: Get all products in dropdown");
        List<String> lstProduct = page.ddbProduct.getAllOption(true);

        log("Verify 1: Products display correct");
        Assert.assertEquals(lstProduct,lstAllProductsExpected,"FAILED! List product is incorrect");


        log("INFO: Executed completely");
    }


}
