package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.FileUtils;
import common.MemberConstants;
import membersite.pages.AccountStatementPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static common.MemberConstants.AccountStatementPage.*;
import static common.MemberConstants.TIMEZONE_BRAND;


public class AccountStatementTest extends BaseCaseTest {

    /**
     * @title: Validate data in account statement is correctly
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Account Statement
     * 2. Filter in a date range
     * 3. Get any market and drill down
     * @expect: Data display correct at API
     */
    @TestRails(id = "524")
    @Test(groups = {"smoke","smoke_dev"})
    public void AccountStatement_TC524() {
        log("@title: Validate data in account statement is correctly");
        log("Step 1. Click My Account > Account Statement");
        String startDate = DateUtils.getDate(-4, "yyyy-MM-dd", TIMEZONE_BRAND.get(_brandname));
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", TIMEZONE_BRAND.get(_brandname));
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);
        List<ArrayList<String>> lstReport = page.getAPIReport(startDate, endDate, TIMEZONE_BRAND.get(_brandname));

        log("Step 2. Filter in a date range");
        page.filter(startDate, endDate);

        log("Verify 1.Data display correct at API");
        Assert.assertTrue(page.verifyBalance(lstReport));
        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate Table header when clicking on sport and market
     * @precondition: 1. Login member site
     * @step: 1. Active My Account>  Account Statement
     * 2. Filter in a date range
     * 3. Click on Load report
     * 4. Click on any sport and check details
     * @expect: 1 Table header display correctly when clicking on sport> event
     */
    @TestRails(id = "525")
    @Test(groups = {"smoke","smoke_dev"})
    public void AccountStatement_TC525() {
        log("@title: Validate Table header when clicking on sport and market");
        log("Step 1. Click My Account > Account Statement");
        String startDate = DateUtils.getDate(-15, "yyyy-MM-dd", TIMEZONE_BRAND.get(_brandname));
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", TIMEZONE_BRAND.get(_brandname));
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);

        log("Step 2 & 3. Filter in a date range amd click Load Report");
        page.filter(startDate, endDate);

        log("Verify 1. Report summary table header display correctly");
        page.verifyHeaderOfTableReport();

        List<ArrayList<String>> lst = page.getTblReport().getRowsWithoutHeader(1, false);
        if (lst.get(0).get(2).equals(OPENING_BALANCE)) {
            log("Verify Pass as no report in search range");
            Assert.assertTrue(true, "By pass as there is no account statement report in search range");
            return;
        }
        log("Step 4. Click on the first Narration and check header details");
        page.clickNarrationOnTheFirstRow();
        List<String> tblHeaders = page.getReportDetailHeader();

        log("Verify 2. Report detail table header display correctly");
        Assert.assertEquals(tblHeaders, TABLE_DETAIL_HEADER, "ERROR!Detail table header not match as expected");
        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate Home page display when clicking on the home icon
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Account Statement
     * 2. Click Logo Image
     * @expect: 1. Home page is displayed
     */
    @TestRails(id = "526")
    @Test(groups = {"smoke_sat"})
    public void AccountStatement_TC526() {
        // This test case cannot run on Fair999 as this brand is remove logo as ticket #ITS-1298
        log("@title: Validate Home page display when clicking on home icon");
        log("Step 1.Active My Account> Account Statement");
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);
        log("Step 2. Click logo Image");
        page.clickLogo();
        page.waitPageLoad();
        log("Verify 1. Home page is displayed");
        Assert.assertTrue(page.getPageUrl().contains(MemberConstants.HomePage.URL), String.format("ERROR! HOME page does not display, Home page url not correct!"));
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1111")
    @Test(groups = {"regression"})
    public void AccountStatement_TC1111() {
        log("@title: Validate Back button work Account Statement");
        log("Step 1. Click My Account > Account Statement");
        String startDate = DateUtils.getDate(-15, "yyyy-MM-dd", TIMEZONE_BRAND.get(_brandname));
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", TIMEZONE_BRAND.get(_brandname));
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);

        log("Step 2. Filter in a date range");
        page.filter(startDate, endDate);

        log("Step 3. Get any market and drill down");
        List<String> firstRowData = page.getTblReport().getRow(1);
        page.clickNarrationOnTheFirstRow();

        log("Step 4. Click back button");
        page.accountStatementContainer.btnBack.click();
        List<String> firstRowDataAfter = page.getTblReport().getRow(1);
        Assert.assertEquals(firstRowData, firstRowDataAfter, "ERROR: Data when click on Back button is incorrect");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1112")
    @Test(groups = {"regression"})
    public void AccountStatement_TC1112() {
        log("@title: Validate export button works");
        log("Step 1. Click My Account > Account Statement");
        String timeZone = TIMEZONE_BRAND.get(_brandname);
        String startDate = DateUtils.getDate(-15, "yyyy-MM-dd", timeZone);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", timeZone);
        String fileName = String.format("Account_Statement_MARKET_%s_%s.csv", DateUtils.getDate(-15, "ddMMMyyyy", timeZone), DateUtils.getDate(0, "ddMMMyyyy", timeZone));
        String dowloadPath = DriverManager.getDriver().getDriverSetting().getDownloadPath() + fileName;
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);

        log("Step 2. Filter in a date range");
        page.filter(startDate, endDate);

        log("Step 4. Click on export icon");
        page.accountStatementContainer.btnExport.click();
        Assert.assertTrue(FileUtils.doesFileNameExist(dowloadPath), "Failed to download Expected document");

        log("@Post-condition: delete download file");
        try {
            FileUtils.removeFile(dowloadPath);
        } catch (IOException e) {
            log(e.getMessage());
        }
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "9452")
    @Parameters({"cashOutBetID"})
    @Test(groups = {"ps38", "Cash_out"})
    public void AccountStatement_Cash_out_TC9452(String cashOutBetID) {
        log("@title: Validate showing cash out bet in account statement page");

        log("Step 1. Click My Account > Account Statement");
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);

        // Cash out bet should use fixed data for verifying
        log("Step 2. Filter in a date range");
        page.filter("2024-05-15","2024-05-15");

        log("Step 3. Drilling down to the details of bet");
        page.clickNarrationOnTheFirstRow();

        log("Verify 1. The cashed out bet displays with status as 'Cashed Out'");
        log("Verify 2. After clicking the status, it show the cashed out details");
        page.verifyCashOutHistoryCorrect(cashOutBetID);
        log("INFO: Executed Completely!");
    }

}
