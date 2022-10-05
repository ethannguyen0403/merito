package membersite.testcases.all.afterlogin.exchange;

import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.AccountStatementPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import java.util.ArrayList;
import java.util.List;
import static membersite.common.FEMemberConstants.AccountStatementPage.*;

public class AccountStatementTest extends BaseCaseMerito {

    /**
     * @title: Validate data in account statement is correctly
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Account Statement
     *          2. Filter in a date range
     *          3. Get any market and drill down
     * @expect: Data display correct at API
     */
    @Test(groups = {"smoke"})
    public void FE_AccountStatement_TC001(){
        log("@title: Validate data in account statement is correctly");
        log("Step 1. Click My Account > Account Statement");

        String startDate = DateUtils.getDate(-4,"yyyy-MM-dd","IST");
        String endDate = DateUtils.getDate(0,"yyyy-MM-dd","IST");
        List<ArrayList<String>> lstReport = BetUtils.getAccountStatement(startDate,endDate,"+05:30");
        AccountStatementPage page = memberHomePage.openAccountStatementPage();

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
    @Test(groups = {"smoke"})
    public void FE_AccountStatement_TC003(){
        log("@title: Validate data in account statement is correctly");
        log("Step 1. Click My Account > Account Statement");
        String startDate = DateUtils.getDate(-15,"yyyy-MM-dd","IST");
        String endDate = DateUtils.getDate(0,"yyyy-MM-dd","IST");
        AccountStatementPage page =memberHomePage.openAccountStatementPage();

        log("Step 2 & 3. Filter in a date range amd click Load Report");
        page.filter(startDate,endDate);
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);

        log("Verify 1. Report summary table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_SUMMARY_HEADER,"ERROR! Sport header table not match as expected");

        List<ArrayList<String>> lst = page.tblReport.getRowsWithoutHeader(1,false);
        if(lst.get(0).get(2).equals(OPENING_BALANCE))
        {
            log("Verify Pass as no report in search range");
            Assert.assertTrue(true,"By pass as there is no account statement report in search range");
            return;
        }

        log("Step 4. Click on the first Narration and check header details");
        page.tblReport.getControlOfCell(1,page.colNarration,1,"span[@class='hover hyperlink']").click();
        tblHeaders = page.tblDetailReport.getColumnNamesOfTable();

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
    @Test(groups = {"smoke"})
    public void FE_AccountStatement_TC005(){
        log("@title: Validate Home page display when clicking on home icon");
        log("Step 1.Active My Account> Account Statement");
        AccountStatementPage page =memberHomePage.openAccountStatementPage();

        log("Step 2. Click hoe Image");
        page.imgHome.click();
        page.imgSpinner.isDisplayed();
        log("Verify 1. Home page is displayed");
        Assert.assertTrue(page.getPageUrl().contains(FEMemberConstants.HomePage.URL),String.format("ERROR! HOME page does not display, Home page url not correct!"));
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
        AccountStatementPage page =memberHomePage.openAccountStatementPage();
        log("Verify 1. There is no console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when navigating to Account Statement page");
    }

}
