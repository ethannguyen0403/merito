package backoffice.testcases.temp;

import backoffice.common.BOConstants;
import backoffice.pages.bo.temp.NetProfitPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NetProfitTest extends BaseCaseTest {

    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Net Profit
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void BO_Report_Net_Profit_001() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Access Reports > Net Profit");
        backofficeHomePage.navigateNetProfit();

        log("Verify 1. There is no http responded error returned");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI load correctly
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Net Profit
     * @expect: 1. Verify UI display correctly:
     * - Year dropdown
     * - Brand dropdown, data in brand is correct loaded
     * - Search button
     * - Note message: Report data is available since January 2020.
     * - Table header is correct
     */
    @Test(groups = {"smoke"})
    public void BO_Report_Net_Profit_002() {
        log("@title: Validate UI load correctly");
        log("Step 1. Access Reports > Net Profit");
        NetProfitPage page = backofficeHomePage.navigateNetProfit();

        log("Verify Validate UI load correctly");
        Assert.assertEquals(page.lblPageTitle.getText(), BOConstants.Reports.NetProfit.TITLE, "FAILED! Page title is incorrect displayed");
        Assert.assertEquals(page.lblYear.getText(), BOConstants.Reports.NetProfit.YEAR, "FAILED! Year label is incorrect displayed");
        Assert.assertTrue(page.ddbYear.isDisplayed(), "FAILED! Year dropdown is not displayed");
        Assert.assertEquals(page.lblBrand.getText(), BOConstants.Reports.NetProfit.BRAND, "FAILED! Brand label is incorrect displayed");
        Assert.assertTrue(page.ddbBrand.isDisplayed(), "FAILED! Brand dropdown is not displayed");
        Assert.assertEquals(page.btnSearch.getText(), BOConstants.Reports.NetProfit.SEARCH, "FAILED! Search button is incorrect displayed");
        Assert.assertEquals(page.lblNotes.getText(), BOConstants.Reports.NetProfit.NOTE, "FAILED! Note label is incorrect displayed");
        Assert.assertEquals(page.tblNetProfitReport.getHeaderNameOfRows(), BOConstants.Reports.NetProfit.LST_HEADER, "FAILED!Header table is incorrect displayed");
        Assert.assertTrue(page.imgTurnoverChart.isDisplayed(), "FAILED! Turnover chart is not displayed");
        Assert.assertTrue(page.imgProfitChart.isDisplayed(), "FAILED! Profit chart is not displayed");

        log("INFO: Executed completely");
    }

}
