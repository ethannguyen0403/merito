package backoffice.testcases.reports;

import backoffice.common.BOConstants;
import backoffice.objects.bo.reports.WinLossDetail;
import backoffice.pages.bo.reports.WinLossDetailPage;
import backoffice.pages.bo.reports.component.TransactionDetailsPopup;
import backoffice.utils.reports.WinLossDetailUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinLossDetailTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Reports > Win Loss Detail
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void BO_Report_WinLossDetail_015() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Reports > Win Loss Details");
        backofficeHomePage.navigateWinLossDetails();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can drill down to member level
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Win Loss Details
     * 2. Filter Today data
     * 3. Drill down to member level
     * @expect: 1. Can drill down to member level, at Member level there no hyperlink
     */
    @Test(groups = {"smoke"})
    public void BO_Report_WinLossDetail_001() {
        log("@title: Validate can drill down to member level");
        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2. Filter Today data");
        page.filterWithLastWeek();

        log("Step 3. Drill down to member level");
        page.drillDown("Member");

        log("Verify 1. Can drill down to member level, at Member level there no hyperlink");
        Assert.assertFalse(page.isUsernameAsHyperlink("Member"), "FAILED! Username displays as hyperlink when level is Member");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can filter portal
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Win Loss Details
     * 2. Filter Today data all product
     * 3. If there is more than 1 Portal display data. Filter a portal in the list
     * @expect: 1. Verify only the filtered portal is displayed
     */
    @Test(groups = {"smoke"})
    public void BO_Report_WinLossDetail_004() {
        log("@title: Validate can drill down to member level");
        log("Step 1: Navigate Reports > Win Loss Details");
        String fromDate = DateUtils.getDate(-5, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String toDate = DateUtils.getDate(0, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String fromDateApi = DateUtils.getDate(-5, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDateApi = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstPortal = WinLossDetailUtils.getPortalsSummaryData(fromDateApi, toDateApi, "EXCHANGE");
        if (Objects.isNull(lstPortal)) {
            Assert.assertTrue(true, "By Pass as has no data");
            return;
        }
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2. Filter Today data all product");
        log("Step 3. If there is more than 1 Portal display data. Filter a portal in the list");
        page.filter(fromDate, toDate, "Exchange", lstPortal.get(0).get(1), "All");

        log("Verify  1. Verify only the filtered portal is displayed");
        List<String> lstPO = page.tblWinLossDetail.getColumn(page.colUsername, false);
        Assert.assertEquals(lstPO.get(0), lstPortal.get(0).get(1), "FAILED! Portal does not display as searching");
        Assert.assertTrue(lstPO.size() == 2, "Failed! More than 1 portals display when only filter 1 portal");
        Assert.assertTrue(page.isUsernameAsHyperlink("Portal"), "FAILED! Username displays as hyperlink when level is Member");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Transaction Detail display all product if filter all product
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Win Loss Details
     * 2. Filter Today data all product
     * 3. Click on total wage of any portal
     * @expect: 1. Verify tab display all products
     */
    @Test(groups = {"smoke"})
    public void BO_Report_WinLossDetail_006() {
        log("@title: Validate Transaction Detail display all product if filter all product");
        log("Step 1: Navigate Reports > Win Loss Details");
        String fromDate = DateUtils.getDate(-30, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String toDate = DateUtils.getDate(0, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String fromDateApi = DateUtils.getDate(-5, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDateApi = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstPortal = WinLossDetailUtils.getPortalsSummaryData(fromDateApi, toDateApi, "All");
        if (Objects.isNull(lstPortal)) {
            log("By pass test case as no data");
            Assert.assertTrue(true, "By Pass as has no data");
            return;
        }
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2. Filter Today data all product");
        page.filter(fromDate, toDate, "", lstPortal.get(0).get(1), "All");

        log("Step 3. Click on total wage of any portal");
        TransactionDetailsPopup popup = page.openTransactionDetail(lstPortal.get(0).get(1));

        log("Verify 1. Verify tab display all products");
        List<String> lstProducts = popup.getProductTab();
        Assert.assertEquals(lstProducts, BOConstants.Reports.WinLossDetail.DDB_PRODUCT, "FAILED! Product list is not matched with the expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that this page loading is successful
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * @expect: 1. Items on Product dropdown-box are loaded correctly
     * 2. Items on Type Currency dropdown-box are loaded correctly
     * 3. Items on Type Portal dropdown-box are loaded correctly
     */
    @Test(groups = {"smoke"})
    public void BO_Report_WinLossDetail_007() {
        log("@title: Validate that this page loading is successful");
        List<String> lstPortals = WinLossDetailUtils.getPortals();

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();
        page.waitSpinIcon();

        boolean isProductItems = page.ddbProduct.areOptionsMatched(BOConstants.Reports.WinLossDetail.DDB_PRODUCT, true);
        boolean isTypeCurrencyItems = page.ddbTypeCurrency.areOptionsMatched(BOConstants.Reports.WinLossDetail.DDB_TYPE_CURRENCY);
        boolean isPortalItems = page.ddbPortal.areOptionsMatched(lstPortals, true);
        String pageTitle = page.lblPageTitle.getText();

        log("Verify 1: Items on Product dropdown-box are loaded correctly");
        Assert.assertTrue(isProductItems, "ERROR: At least an item on Product ddb is incorrect");
        log("Verify 2: Items on Type Currency dropdown-box are loaded correctly");
        Assert.assertTrue(isTypeCurrencyItems, "ERROR: At least an item on Type Currency ddb is incorrect");

        log("Verify 3: Items on Type Portal dropdown-box are loaded correctly");
        Assert.assertTrue(isPortalItems, "ERROR: At least an item on Portal ddb is incorrect");
        Assert.assertEquals(pageTitle, BOConstants.Reports.WinLossDetail.TITLE, String.format("ERROR: The expected page title is '%s' but found '%s'", pageTitle, BOConstants.Reports.WinLossDetail.TITLE));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Total wager displayed at Total Wager column on this table is correct
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Select Show total only
     * 3. Filter with default value
     * @expect: 1. Total wager displayed at Total Wager column on this table is correct
     */
    @Test(groups = {"smoke"})
    public void BO_Report_WinLossDetail_003() {
        log("@title: Validate that Total wager displayed at Total Wager column on this table is correct");
        String yesterday = DateUtils.getDateBeforeCurrentDate(1, BOConstants.DASH_YYYY_MM_DD);
        WinLossDetail oWinLossDetail = WinLossDetailUtils.getWinLossReport(yesterday, yesterday, "All");

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2: Select Show total only check-box");
        page.chkShowTotalOnly.select();
        page.waitSpinIcon();

        log("Step 3: Filter with default value");
        page.filterWithYesterday();

        log("Step 4: Get Total Wager");
        List<Integer> lstInfo = page.getTotalWager();
        if (Objects.isNull(lstInfo)) {
            log("By passe as has no data");
            Assert.assertTrue(true, "PASSED");
            return;
        }
        int sumTotalWager = lstInfo.get(0);
        int totalWagerLastRow = lstInfo.get(1);
        log("Verify 1: Total wager displayed at Total Wager column on this table is correct");
        Assert.assertEquals(sumTotalWager, oWinLossDetail.getTotalWager(), String.format("ERROR: The expected Total Wager is %s but found on this table is %s", oWinLossDetail.getTotalWager(), sumTotalWager));
        Assert.assertEquals(totalWagerLastRow, oWinLossDetail.getTotalWager(), String.format("ERROR: The expected Total Wager is %s but found on this table is %s", oWinLossDetail.getTotalWager(), totalWagerLastRow));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Total wager displayed at Total Wager column on this table is correct
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Select Show total only
     * 3. Click Last Week button
     * @expect: 1. Total wager displayed at Total Wager column on this table is correct
     */
    @Test(groups = {"regression"})
    public void BO_Report_WinLossDetail_008() {
        log("@title: Validate that Total wager displayed at Total Wager column on this table is correct");
        String yesterday = DateUtils.getDateBeforeCurrentDate(1, BOConstants.DASH_YYYY_MM_DD);
        String sevenDays = DateUtils.getDateBeforeCurrentDate(7, BOConstants.DASH_YYYY_MM_DD);
        WinLossDetail oWinLossDetail = WinLossDetailUtils.getWinLossReport(sevenDays, yesterday, "All");

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2: Select Show total only check-box");
        page.chkShowTotalOnly.select();

        log("Step 3: Click Last Week button");
        page.filterWithLastWeek();

        log("Step 4: Get Total Wager");
        List<Integer> lstInfo = page.getTotalWager();
        int sumTotalWager = lstInfo.get(0);
        int totalWagerLastRow = lstInfo.get(1);

        log("Verify 1: Total wager displayed at Total Wager column on this table is correct");
        Assert.assertEquals(sumTotalWager, oWinLossDetail.getTotalWager(), String.format("ERROR: The expected Total Wager is %s but found on this table is %s", oWinLossDetail.getTotalWager(), sumTotalWager));
        Assert.assertEquals(totalWagerLastRow, oWinLossDetail.getTotalWager(), String.format("ERROR: The expected Total Wager is %s but found on this table is %s", oWinLossDetail.getTotalWager(), totalWagerLastRow));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Total wager displayed at Total Wager column on this table is correct
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Select Show total only
     * 3. Filter Exchange product
     * @expect: 1. Total wager displayed at Total Wager column on this table is correct
     */
    @Test(groups = {"regression"})
    public void BO_Report_WinLossDetail_009() {
        log("@title: Validate that Total wager displayed at Total Wager column on this table is correct");
        String product = BOConstants.Reports.WinLossDetail.DDB_PRODUCT.get(0);
        String yesterday = DateUtils.getDateBeforeCurrentDate(1, BOConstants.DASH_YYYY_MM_DD);
        WinLossDetail oWinLossDetail = WinLossDetailUtils.getWinLossReport(yesterday, yesterday, product.toUpperCase());

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2: Select Show total only check-box");
        page.chkShowTotalOnly.select();

        log(String.format("Step 3: Filter with product '%s'", product));
        page.filter(product);

        log("Step 4: Get Total Wager");
        List<Integer> lstInfo = page.getTotalWager();
        int sumTotalWager = lstInfo.get(0);
        int totalWagerLastRow = lstInfo.get(1);

        log("Verify 1: Total wager displayed at Total Wager column on this table is correct");
        Assert.assertEquals(sumTotalWager, oWinLossDetail.getTotalWager(), String.format("ERROR: The expected Total Wager is %s but found on this table is %s", oWinLossDetail.getTotalWager(), sumTotalWager));
        Assert.assertEquals(totalWagerLastRow, oWinLossDetail.getTotalWager(), String.format("ERROR: The expected Total Wager is %s but found on this table is %s", oWinLossDetail.getTotalWager(), totalWagerLastRow));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Date From and Date To are displayed correctly when filtered within a month
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Filter a month earlier
     * 3. Filter with default value
     * @expect: 1. Date From on the main bar is generated correctly
     * 2. Date To on the main bar is generated correctly
     */
    @Test(groups = {"regression"})
    public void BO_Report_WinLossDetail_010() {
        log("@title: Validate that Date From and Date To are displayed correctly when filtered within a month");
        String expectedDateTo = DateUtils.getDateBeforeCurrentDate(1, BOConstants.SLASH_DD_MM_YYYY);
        String expectedDateFrom = String.format("01/%s", DateUtils.getMonthYear(BOConstants.GMT_FOUR, -1, BOConstants.SLASH_MM_YYYY));

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2: Filter a month earlier");
        page.dpFrom.previousMonthWithDate(-1, "1");

        log("Step 3: Filter with default value");
        page.filter();

        String dateFrom = page.getDateFrom();
        String dateTo = page.getDateTo();
        log("Verify 1: Date From on the main bar is generated correctly");
        log("Verify 2: Date To on the main bar is generated correctly");
        Assert.assertEquals(expectedDateFrom, dateFrom, String.format("ERROR: The expected Date From is %s but found on the main bar is %s", expectedDateFrom, dateFrom));
        Assert.assertEquals(expectedDateTo, dateTo, String.format("ERROR: The expected Date To is %s but found on the main bar is %s", expectedDateTo, dateTo));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Date From and Date To are displayed correctly when filtered within yesterday
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Click Yesterday button
     * @expect: 1. Date From on the main bar is generated correctly
     * 2. Date To on the main bar is generated correctly
     */
    @Test(groups = {"regression"})
    public void BO_Report_WinLossDetail_011() {
        log("@title: Validate that Date From and Date To are displayed correctly when filtered within yesterday");
        String expectedDateTo = DateUtils.getDateBeforeCurrentDate(1, BOConstants.SLASH_DD_MM_YYYY);

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2: Click Yesterday button");
        page.filterWithYesterday();

        String dateFrom = page.getDateFrom();
        String dateTo = page.getDateTo();
        String txtDateFrom = page.txtDateFrom.getAttribute();
        String txtDateTo = page.txtDateTo.getAttribute();
        log("Verify 1: Date From on the main bar is generated correctly");
        log("Verify 2: Date To on the main bar is generated correctly");
        Assert.assertEquals(expectedDateTo, dateFrom, String.format("ERROR: The expected Date From is %s but found on the main bar is %s", expectedDateTo, dateFrom));
        Assert.assertEquals(expectedDateTo, dateTo, String.format("ERROR: The expected Date To is %s but found on the main bar is %s", expectedDateTo, dateTo));
        Assert.assertEquals(expectedDateTo, txtDateFrom, String.format("ERROR: The expected Date From is %s but found on Date From text-box is %s", expectedDateTo, txtDateFrom));
        Assert.assertEquals(expectedDateTo, txtDateTo, String.format("ERROR: The expected Date To is %s but found on Date To text-box is %s", expectedDateTo, txtDateTo));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Date From and Date To are displayed correctly when filtered within a week
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Click Yesterday button
     * @expect: 1. Date From on the main bar is generated correctly
     * 2. Date To on the main bar is generated correctly
     */
    @Test(groups = {"regression"})
    public void BO_Report_WinLossDetail_012() {
        log("@title: Validate that Date From and Date To are displayed correctly when filtered within a week");
        String expectedDateTo = DateUtils.getDateBeforeCurrentDate(1, BOConstants.SLASH_DD_MM_YYYY);
        String expectedDateFrom = DateUtils.getDateBeforeCurrentDate(7, BOConstants.SLASH_DD_MM_YYYY);

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2: Click Yesterday button");
        page.filterWithLastWeek();

        String dateFrom = page.getDateFrom();
        String dateTo = page.getDateTo();
        String txtDateFrom = page.txtDateFrom.getAttribute();
        String txtDateTo = page.txtDateTo.getAttribute();
        log("Verify 1: Date From on the main bar is generated correctly");
        log("Verify 2: Date To on the main bar is generated correctly");
        Assert.assertEquals(expectedDateFrom, dateFrom, String.format("ERROR: The expected Date From is %s but found on the main bar is %s", expectedDateFrom, dateFrom));
        Assert.assertEquals(expectedDateTo, dateTo, String.format("ERROR: The expected Date To is %s but found on the main bar is %s", expectedDateTo, dateTo));
        Assert.assertEquals(expectedDateFrom, txtDateFrom, String.format("ERROR: The expected Date From is %s but found on Date From text-box is %s", expectedDateFrom, txtDateFrom));
        Assert.assertEquals(expectedDateTo, txtDateTo, String.format("ERROR: The expected Date To is %s but found on Date To text-box is %s", expectedDateTo, txtDateTo));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there are error messages displayed when deselected Product and Portal ddb
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Simple
     * 2. Deselect Product ddb
     * 3. Deselect Portal ddb
     * @expect: 1. Product error message is displayed
     * 2. Portal error message is displayed
     */
    @Test(groups = {"regression"})
    public void BO_Report_WinLossDetail_013() {
        log("@title: Validate that there are error messages displayed when deselected Product and Portal ddb");

        log("Step 1: Navigate Reports > Win Loss Details");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2: Deselect Product ddb");
        page.ddbProduct.deSelectAll(true);

        log("Step 3: Deselect Portal ddb");
        page.ddbPortal.deSelectAll(true);

        String portalErrorText = page.lblPortalError.getText();
        String productErrorText = page.lblProductError.getText();
        log("Verify 1: Product error message is displayed");
        log("Verify 2: Portal error message is displayed");
        Assert.assertEquals(productErrorText, BOConstants.Reports.WinLossDetail.ERROR_PRODUCT_MSG, String.format("ERROR: The expected product message is '%s' but found '%s'", BOConstants.Reports.WinLossDetail.ERROR_PRODUCT_MSG, productErrorText));
        Assert.assertEquals(portalErrorText, BOConstants.Reports.WinLossDetail.ERROR_PORTAL_MSG, String.format("ERROR: The expected portal message is '%s' but found '%s'", BOConstants.Reports.WinLossDetail.ERROR_PORTAL_MSG, portalErrorText));
        log("INFO: Executed completely");
    }
}
