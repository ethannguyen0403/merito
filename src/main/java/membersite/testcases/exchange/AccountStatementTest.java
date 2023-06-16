package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.MemberConstants;
import membersite.pages.AccountStatementPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.MemberConstants.AccountStatementPage.*;
import static common.MemberConstants.TIMEZONE_BRAND;


public class AccountStatementTest extends BaseCaseTest {

    /**
     * @title: Validate data in account statement is correctly
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Account Statement
     *          2. Filter in a date range
     *          3. Get any market and drill down
     * @expect: Data display correct at API
     */
    @TestRails(id="524")
    @Test(groups = {"smokeSAT"})
    public void AccountStatement_TC524(){
        log("@title: Validate data in account statement is correctly");
        log("Step 1. Click My Account > Account Statement");
        String startDate = DateUtils.getDate(-4,"yyyy-MM-dd",TIMEZONE_BRAND.get(_brandname));
        String endDate = DateUtils.getDate(0,"yyyy-MM-dd",TIMEZONE_BRAND.get(_brandname));
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);
        List<ArrayList<String>> lstReport = page.getAPIReport(startDate,endDate,TIMEZONE_BRAND.get(_brandname));

        log("Step 2. Filter in a date range");
        page.filter(startDate,endDate);

        log("Verify 1.Data display correct at API");
        Assert.assertTrue(page.verifyBalance(lstReport));
    }
    /**
     * @title: Validate Table header when clicking on sport and market
     * @precondition: 1. Login member site
     * @step: 1. Active My Account>  Account Statement
     *        2. Filter in a date range
     *        3. Click on Load report
     *        4. Click on any sport and check details
     * @expect: 1 Table header display correctly when clicking on sport> event
     */
    @TestRails(id="525")
    @Test(groups = {"smoke"})
    public void AccountStatement_TC525(){
        log("@title: Validate Table header when clicking on sport and market");
        log("Step 1. Click My Account > Account Statement");
        String startDate = DateUtils.getDate(-15,"yyyy-MM-dd","IST");
        String endDate = DateUtils.getDate(0,"yyyy-MM-dd","IST");
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);

        log("Step 2 & 3. Filter in a date range amd click Load Report");
        page.filter(startDate,endDate);
        List<String> tblHeaders = page.getTblReport().getColumnNamesOfTable(1);

        log("Verify 1. Report summary table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_SUMMARY_HEADER,"ERROR! Sport header table not match as expected");

        List<ArrayList<String>> lst = page.getTblReport().getRowsWithoutHeader(1,false);
        if(lst.get(0).get(2).equals(OPENING_BALANCE))
        {
            log("Verify Pass as no report in search range");
            Assert.assertTrue(true,"By pass as there is no account statement report in search range");
            return;
        }

        log("Step 4. Click on the first Narration and check header details");

        page.clickNarration();
        tblHeaders = page.getTblReport().getColumnNamesOfTable();

        log("Verify 2. Report detail table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_DETAIL_HEADER,"ERROR!Detail table header not match as expected");
    }

    /**
     * @title: Validate Home page display when clicking on the home icon
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Account Statement
     *          2. Click Logo Image
     * @expect: 1. Home page is displayed
     */
    @TestRails(id="526")
    @Test(groups = {"smoke"})
    public void FE_AccountStatement_TC526(){
        log("@title: Validate Home page display when clicking on home icon");
        log("Step 1.Active My Account> Account Statement");
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);
        log("Step 2. Click logo Image");
        page.clickLogo();
        page.waitPageLoad();
        log("Verify 1. Home page is displayed");
        Assert.assertTrue(page.getPageUrl().contains(MemberConstants.HomePage.URL),String.format("ERROR! HOME page does not display, Home page url not correct!"));
    }

    /**
     * @title: Validate no console error when navigate to Account Statement Page
     * @precondition: 1. Login member site
     * @step: 1. Click My Account > Account Statement
     * @expect: 1. There is no console error display
     */
    @Test(groups = {"http_request"})
    public void FE_AccountStatement_TC006(){
        log("@title: Validate no console error when navigate to Account Statement Page");
        log("Step 1. Click My Account > Account Statement");
         AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);
        log("Verify 1. There is no console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when navigating to Account Statement page");
    }

}
