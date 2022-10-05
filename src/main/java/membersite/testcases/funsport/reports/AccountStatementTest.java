package membersite.testcases.funsport.reports;

import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.funsport.tabexchange.AccountStatementPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;

import static membersite.common.FEMemberConstants.AccountStatementPage.*;
import static membersite.common.FEMemberConstants.MyBetsPage.*;

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

        String todayDate = DateUtils.getDate(0,"yyyy-MM-dd","IST");
        List<ArrayList<String>> lstReport = BetUtils.getAccountStatement(todayDate,todayDate,"+05:30");
        AccountStatementPage page = memberHomePage.fsHeaderControl.openAccountStatement();

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
        AccountStatementPage page = memberHomePage.fsHeaderControl.openAccountStatement();

        log("Step 2 & 3. Filter in a date range amd click Load Report");
        page.filter(startDate,endDate);
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);

        log("Verify 1. Report summary table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_SUMMARY_HEADER_FS,"ERROR! Sport header table not match as expected");

        List<ArrayList<String>> lst = page.tblReport.getRowsWithoutHeader(1,false);
        if(lst.get(0).get(2).equals(FEMemberConstants.AccountStatementPage.OPENING_BALANCE))
        {
            log("Verify Pass as no report in search range");
            Assert.assertTrue(true,"By pass as there is no account statement report in search range");
            return;
        }

        log("Step 4. Click on the first Narration and check header details");
        page.tblReport.getControlOfCell(1,page.colNarration,1,"span[@class='hover hyperlink']").click();
        tblHeaders = page.tblDetailReport.getColumnNamesOfTable();

        log("Verify 2. Report detail table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_DETAIL_SPORT_HEADER,"ERROR!Detail table header not match as expected");
    }

    /**
     * @title: Validate Home page display when clicking on the Logo icon
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Account Statement
     *          2. Click Logo Image
     * @expect: 1. Home page is displayed
     */
    @Test(groups = {"regression"})
    public void FE_AccountStatement_TC005(){
        log("@title: Validate Home page display when clicking on home icon");
        log("Step 1.Active My Account> Account Statement");
        AccountStatementPage page = memberHomePage.fsHeaderControl.openAccountStatement();

        log("Step 2. Click home Image");
        page.fsHeaderControl.clickLogo();
        page.imgSpinner.isDisplayed();
        log("Verify 1. Home page is displayed");
        Assert.assertEquals(page.getPageUrl(), memberLoginURL ,String.format("ERROR! HOME page does not display, Home page url not correct!"));
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
        AccountStatementPage page = memberHomePage.fsHeaderControl.openAccountStatement();
        log("Verify 1. There is no console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when navigating to Account Statement page");
    }

    @Test(groups = {"smoke"})
    public void FE_AccountStatement_TC007()
    {
        log("@title: Validate UI when access Account Statement Page");
        log("Step 1. Click My Account > Account Statement");
        AccountStatementPage page = memberHomePage.fsHeaderControl.openAccountStatement();
        log("Verify 1. The Product label display accordingly");
        Assert.assertEquals(page.lblProductTitle.getText(), "Account Statement",String.format("ERROR! Expected product lable is %s but display %s","Account Statement", page.lblProductTitle.getText()));

        log("Verify 2. Controls display correctly");
        Assert.assertEquals(page.btnMainWalletStatement.getText(), DDB_PRODUCT_FILTER.get("Exchange"),String.format("ERROR! Expected product lable is %s but display %s", DDB_PRODUCT_FILTER.get("Exchange"), page.btnProductActive.getText()));
        Assert.assertFalse(page.btnCurrent.isDisplayed(),"ERROR! Expected Current button should not display");
        Assert.assertFalse(page.btnSettle.isDisplayed(),"ERROR! Expected Past button should not display");
        Assert.assertFalse(page.btnUnmatched.isDisplayed(), "ERROR! Expected Unmatched buttonshould not display");
        Assert.assertFalse(page.btnMatched.isDisplayed(),  "ERROR!Expected Matched buton should not display");
        Assert.assertFalse(page.lblAll.isDisplayed(),"ERROR! Expected All label should not display");
        Assert.assertEquals(page.lblOption.getText(),"Options",String.format("ERROR! Expected Options but found %s",page.lblOption.getText()));
        Assert.assertTrue(page.icDownload.isDisplayed(),"Failed! Download icon not displayed");
        Assert.assertTrue(page.icPrint.isDisplayed(),"Failed! Print icon not displayed");
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders.size(),TABLE_SUMMARY_HEADER_FS.size(), String.format("ERROR: The expected no of columns is %s but found %s", TABLE_SUMMARY_HEADER_FS.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders,TABLE_SUMMARY_HEADER_FS,"ERROR! Header list not display as expected");
    }
}
