package membersite.testcases.aposta;

import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.aposta.MyBetsPage;
import baseTest.BaseCaseMerito;

import java.util.List;

import static membersite.common.FEMemberConstants.MyBetsPage.*;

public class MyBetPageTest extends BaseCaseMerito {
    /**
     * @title: Validate Product display correctly
     * @precondition: 1. Login member site
     * @step:   1. Active My Account> My Bets
     *          2. Selection product dropdown box
     * @expect: 1. The Product label display accordingly
     *          2. Controls display correctly
     */
    @Test(groups = {"smoke"})
    @Parameters("timeZone")
    public void MyBetPage_TC001(String timeZone){
        log("@title: Validate Product display correctly");
        log("Step 1. Active My Account> My Bets");
        log("Step 2. Selection product dropdown box");
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();

        page.menuProduct.clickSubMenu(DDB_PRODUCT_FILTER.get("Exchange"));

        log("Verify 1. The Product label display accordingly");
        log("Verify 2. Controls display correctly");
        //Assert.assertEquals(page.lblNote.getText(),String.format(NOTES,timeZone), String.format("ERROR! Current Note label shows %s",page.lblNote.getText()));
        Assert.assertEquals(page.lblProductTile.getText(), DDB_PRODUCT_FILTER.get("Exchange"),String.format("ERROR! Expected product lable is %s but display %s", DDB_PRODUCT_FILTER.get("Exchange"), page.lblProductTile.getText()));
       Assert.assertEquals(page.lblSettled.getText(), DDB_ORDER_TYPE_FILTER.get("SETTLED"),"FAILED! Settled label is incorrect");
        Assert.assertEquals(page.lblMatched.getText(), DDB_ORDER_TYPE_FILTER.get("MATCHED"),"FAILED! Settled label is incorrect");
        Assert.assertEquals(page.lblSUnmatched.getText(), DDB_ORDER_TYPE_FILTER.get("UNMATCHED"),"FAILED! Settled label is incorrect");
        Assert.assertEquals(page.lblCancelled.getText(), DDB_ORDER_TYPE_FILTER.get("CANCELLED"),"FAILED! Settled label is incorrect");
        Assert.assertEquals(page.lblVoided.getText(), DDB_ORDER_TYPE_FILTER.get("VOIDED"),"FAILED! Settled label is incorrect");
        Assert.assertEquals(page.lblLapsed.getText(), DDB_ORDER_TYPE_FILTER.get("LAPSED"),"FAILED! Settled label is incorrect");
        Assert.assertEquals(page.btnLoadReport.getText(),LOAD_REPORT,String.format("ERROR! Expected Load Report but found %s",page.btnLoadReport.getText()));
        Assert.assertEquals(page.btnPnL.getText(),MyBetsPageNewView.BTNPLREPORT,String.format("ERROR! Expected P&N Report button but found %s",page.btnLoadReport.getText()));
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders.size(),MyBetsPageNewView.TABLE_HEADER_MATCHED.size(), String.format("ERROR: The expected no of columns is %s but found %s",MyBetsPageNewView.TABLE_HEADER_MATCHED.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders,MyBetsPageNewView.TABLE_HEADER_MATCHED,"ERROR! Header list not display as expected");
        log("INFO: Executed Completely!");
    }
    /**
     * @title: Validate can filter Settled bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets , Order Type: Settled , Start and End Date, click Load Report
     * @expect: 1. All settled bet is filtered
     */
    @Test(groups = {"smoke"})
    public void MyBetPage_TC002() {
        log("@title: Validate can filter Settled bet");
        log("Step 1. Active My Account> My Bets");
        String startDate  = DateUtils.getDate(-30,"yyyy-MM-dd", FEMemberConstants.TIMEZONE);
        String endDate  = DateUtils.getDate(0,"yyyy-MM-dd", FEMemberConstants.TIMEZONE);
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();

        log("Step 2. Select Exchange Bets , Order Type: Settled , Start and End Date, click Load Report");
       // page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("SETTLED"),startDate,endDate);
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("SETTLED"));

        if(page.tblReport.getRowsWithoutHeader(1,false).get(0).get(0).equals(FEMemberConstants.NO_RECORD_FOUND)){
            Assert.assertTrue(true,"By Passed as there is no data when filter Settled wagers");
            return;
        }

        log("Verify 2. Select Exchange Bets , Order Type: Settled , Start and End Date, click Load Report");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("SETTLED")),"ERROR! Settled status not filter correctly.");

        log("INFO: Executed Completely!");
    }
    /**
     * @title: Validate can filter Matched bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets , Order Type: Matched , Start and End Date, click Load Report
     * @expect: 1. All matched bet is filtered
     */
    @Test(groups = {"smoke"})
    public void MyBetPage_TC003() {
        log("@title: Validate can filter Matched bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();
        log("Step 2. Select Exchange Bets , Order Type: Matched , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("MATCHED"));

        if(page.tblReport.getRowsWithoutHeader(1,false).get(0).get(0).equals(FEMemberConstants.NO_RECORD_FOUND)){
            Assert.assertTrue(true,"By Passed as there is no data when filter Settled wagers");
            return;
        }
        log("Verify 1. All matched bet is filtered");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("MATCHED")),"ERROR! Matched status not filter correctly.");

        log("INFO: Executed Completely!");
    }
    /**
     * @title: Validate can filter Unmatched bet
     * @precondition: 1. Login member site
     * @step:c  1. Active My Account> My Bets
     *          2. Select Exchange Bets , Order Type: Unmatched , Start and End Date, click Load Report
     * @expect: 1. All Unmatched bet is filtered
     */
    @Test(groups = {"smoke"})
    public void MyBetPage_TC004() {
        log("@title: Validate can filter Settled bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();

        log("Step 2. Select Exchange Bets , Order Type: Unmatched , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));

        if(page.tblReport.getRowsWithoutHeader(1,false).get(0).get(0).equals(FEMemberConstants.NO_RECORD_FOUND)){
            Assert.assertTrue(true,"By Passed as there is no data when filter Settled wagers");
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
     *          2. Select Exchange Bets , Order Type: Cancelled , Start and End Date, click Load Report
     * @expect: 1. All Cancelled bet is filtered
     */
    @Test(groups = {"smoke"})
    public void MyBetPage_TC005() {

        log("@title: Validate can filter Cancelled bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();

        log("Step 2. Select Exchange Bets , Order Type: Cancelled , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("CANCELLED"));

        if(page.tblReport.getRowsWithoutHeader(1,false).get(0).get(0).equals(FEMemberConstants.NO_RECORD_FOUND)){
            Assert.assertTrue(true,"By Passed as there is no data when filter Settled wagers");
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
     *          2. Select Exchange Bets , Order Type: Lapsed , Start and End Date, click Load Report
     * @expect: 1. Bet info display correctly, all bets display with status: Lapsed
     */
    @Test(groups = {"smoke"})
    public void MyBetPage_TC006() {

        log("@title: Validate can filter Lapsed bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();

        log("Step 2.Select Exchange Bets , Order Type: Lapsed , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("LAPSED"));

        if(page.tblReport.getRowsWithoutHeader(1,false).get(0).get(0).equals(FEMemberConstants.NO_RECORD_FOUND)){
            Assert.assertTrue(true,"By Passed as there is no data when filter Settled wagers");
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
     *          2. Select Exchange Bets , Order Type: Voided , Start and End Date, click Load Report
     * @expect: 1. Bet info display correctly, all bets display with status: Voided
     */
    @Test(groups = {"smoke"})
    public void MyBetPage_TC007() {
        log("@title: Validate can filter Voided bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();

        log("Step 2.Select Exchange Bets , Order Type: Voided , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("VOIDED"));

        if(page.tblReport.getRowsWithoutHeader(1,false).get(0).get(0).equals(FEMemberConstants.NO_RECORD_FOUND)){
            Assert.assertTrue(true,"By Passed as there is no data when filter Settled wagers");
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
    public void MyBetPage_TC009(){
        log("@title: Validate no console error when navigate to My Bets Page");
        log("Step 1. 1. Click My Account > My Bets");
        MyBetsPage page = memberHomePage.apHeaderControl.openMyBetPage();
        log("Verify 1. There is no console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when navigating to My Bets page");
        log("INFO: Executed Completely!");
    }

}
