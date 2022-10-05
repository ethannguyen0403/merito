package membersite.testcases.funsport.reports;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.funsport.tabexchange.MyBetsPage;
import baseTest.BaseCaseMerito;

import java.util.List;

import static membersite.common.FEMemberConstants.MyBetsPage.*;

public class MyBetPageTest extends BaseCaseMerito {
    /**
     * @title: Validate Product display correctly
     * @precondition: 1. Login member site
     * @step:   1. Active My Account> My Bets
     *
     * @expect: 1. The Product label display accordingly
     *          2. Controls display correctly
     */
    @Test(groups = {"smoke"})
    @Parameters("timeZone")
    public void FE_MyBetPage_TC001(String timeZone){
        log("@title: Validate Product display correctly");
        log("Step 1. Active My Account> My Bets");
        log("Step 2. Selection product dropdown box");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();
        page.selectProduct(DDB_PRODUCT_FILTER.get("Exchange"));

        log("Verify 1. The Product label display accordingly");
        Assert.assertEquals(page.lblProductTitle.getText(), DDB_PRODUCT_FILTER.get("Exchange"),String.format("ERROR! Expected product lable is %s but display %s", DDB_PRODUCT_FILTER.get("Exchange"), page.lblProductTitle.getText()));

        log("Verify 2. Controls display correctly");
        Assert.assertEquals(page.lblNote.getText(),String.format(NOTES,timeZone),"ERROR! Expected Note lable is incorrect");
        Assert.assertEquals(page.btnProductActive.getText(), DDB_PRODUCT_FILTER.get("Exchange"),String.format("ERROR! Expected product lable is %s but display %s", DDB_PRODUCT_FILTER.get("Exchange"), page.btnProductActive.getText()));
        Assert.assertEquals(page.btnCurrent.getText(),BUTTON_CURRENT, String.format("ERROR! Expected Current button but found %s",page.btnCurrent.getText()));
        Assert.assertEquals(page.btnSettle.getText(), BUTTON_PAST, String.format("ERROR! Expected Past buttonbut found %s",page.btnSettle.getText()));
        Assert.assertEquals(page.btnUnmatched.getText(),BUTTON_UNMATCH, String.format("ERROR! Expected Unmatched button but found %s",page.btnUnmatched.getText()));
        Assert.assertEquals(page.btnMatched.getText(),BUTTON_MATCH, String.format("ERROR!Expected Matched buton shows %s",page.btnMatched.getText()));
        Assert.assertEquals(page.lblAll.getText(),LABLE_ALL,String.format("ERROR! Expected All lable  but found %s",page.lblAll.getText()));
        Assert.assertEquals(page.lblOption.getText(),LABLE_OPTIONL,String.format("ERROR! Expected Options but found %s",page.lblOption.getText()));
        Assert.assertTrue(page.icDownload.isDisplayed(),"Failed! Download icon not displayed");
        Assert.assertTrue(page.icPrint.isDisplayed(),"Failed! Print icon not displayed");
        Assert.assertTrue(page.icReresh.isDisplayed(),"Failed! Refresh icon not displayed");

        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders.size(),TABLE_HEADER_FS.size(), String.format("ERROR: The expected no of columns is %s but found %s", TABLE_HEADER_FS.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders,TABLE_HEADER_FS,"ERROR! Header list not display as expected");

    }
    /**
     * @title: Validate can filter Settled bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets and click on Past button
     * @expect: 1. All settled bet is filtered
     */
    @Test(groups = {"smoke"})
    public void FE_MyBetPage_TC002() {
        log("@title: Validate can filter Settled bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();

        log("Step 2. Select Exchange Bets, and click Past button");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("SETTLED"));

        log("Verify 1. Verify the table display header correct when select past button");
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders,TABLE_SETTLED_HEADER_FS,"ERROR! Header list not display as expected");

        log("Verify 2.Verify status is display correctly when filter settle");
        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(),YOU_HAVE_NO_BETS_PERIOD,"FAILED! No record message is incorrect displayed");
            return;
        }
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("SETTLED")),"ERROR! Settled status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Matched bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets then Click on Matched button
     * @expect: 1. All matched bet is filtered
     */
    @Test(groups = {"smoke"})
    public void FE_MyBetPage_TC003() {
        log("@title: Validate can filter Matched bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();
        log("Step 2. Select Exchange Bets then Click on Matched button");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("MATCHED"));

        log("Verify 1. Verify the table display header correct when select past button");
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders,TABLE_HEADER_FS,"ERROR! Header list not display as expected");


        log("Verify 1. All matched bet is filtered");
        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(),YOU_HAVE_NO_BETS_PERIOD,"FAILED! No record message is incorrect displayed");
            return;
        }
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("MATCHED")),"ERROR! Matched status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Unmatched bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets then Click on Unmatched button
     * @expect: 1. All Unmatched bet is filtered
     */
    @Test(groups = {"smoke"})
    public void FE_MyBetPage_TC004() {
        log("@title: Validate can filter Settled bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();

        log("Step 2. Select Exchange Bets , Order Type: Unmatched , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));

        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(),YOU_HAVE_NO_BETS_PERIOD,"FAILED! No record message is incorrect displayed");
            return;
        }

        log("Verify 1. All Unmatched bet is filtered");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("UNMATCHED")),"ERROR! Unmatched status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Canceled bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets and click on Past button and select Cancelled Status
     * @expect: 1. All Cancelled bet is filtered
     */
    @Test(groups = {"smoke"})
    public void FE_MyBetPage_TC005() {

        log("@title: Validate can filter Cancelled bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();

        log("Step 2. Select Exchange Bets , Order Type: Cancelled , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("CANCELLED"));

        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(),YOU_HAVE_NO_BETS_PERIOD,"FAILED! No record message is incorrect displayed");
            return;
        }

        log("Verify 1. Bet info display correctly, all bets display with status: Canceled");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("CANCELLED")),"ERROR! Cancelled status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Lapsed bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *           2. Select Exchange Bets and click on Past button and select Lapsed Status
     * @expect: 1. Bet info display correctly, all bets display with status: Lapsed
     */
    @Test(groups = {"smoke"})
    public void FE_MyBetPage_TC006() {

        log("@title: Validate can filter Lapsed bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();

        log("Step 2.Select Exchange Bets , Order Type: Lapsed , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("LAPSED"));

        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(),YOU_HAVE_NO_BETS_PERIOD,"FAILED! No record message is incorrect displayed");
            return;
        }

        log("Verify 1. Bet info display correctly, all bets display with status: Lapsed");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("LAPSED")),"ERROR! Lapsed status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Voided bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets and click on Past button and select Voided Status
     * @expect: 1. Bet info display correctly, all bets display with status: Voided
     */
    @Test(groups = {"smoke"})
    public void FE_MyBetPage_TC007() {
        log("@title: Validate can filter Voided bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();

        log("Step 2.Select Exchange Bets , Order Type: Voided , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("VOIDED"));

        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(),YOU_HAVE_NO_BETS_PERIOD,"FAILED! No record message is incorrect displayed");
            return;
        }

        log("Verify 1. Bet info display correctly, all bets display with status: Voided");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("VOIDED")),"ERROR! Voided status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate no console error when navigate to My Bets
     * @precondition: 1. Login member site
     * @step: 1. Click My Account > My Bets
     * @expect: 1. There is no console error display
     */
    @Test(groups = {"http_request"})
    public void FE_MyBetPage_TC009(){
        log("@title: Validate no console error when navigate to My Bets Page");
        log("Step 1. 1. Click My Account > My Bets");
        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();

        log("Verify 1. There is no console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when navigating to My Bets page");
        log("INFO: Executed Completely!");
    }

}
