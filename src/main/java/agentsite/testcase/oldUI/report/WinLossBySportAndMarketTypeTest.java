package agentsite.testcase.oldUI.report;

import com.paltech.utils.DateUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.WinLossBySportAndMarketTypePage;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.WIN_LOSS_BY_MARKET_TYPE;

public class WinLossBySportAndMarketTypeTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Win Loss By Sport And Market Type
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Win_Loss_By_Sport_And_Market_Type_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report>  Win Loss By Sport And Market Type");
        WinLossBySportAndMarketTypePage page = agentHomePage.clickSubMenu(REPORT, WIN_LOSS_BY_MARKET_TYPE, WinLossBySportAndMarketTypePage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Win Loss By Sport And Market Type UI display correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Win Loss By Sport And Market Type
     * @expect: 1. Verify  Win Loss By Sport And Market Type UI display correctly
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Win_Loss_By_Sport_And_Market_Type_002(){
        log("@title: Validate Win Loss By Sport And Market Type UI display correctly");
        log("Step 1. Navigate Report > Win Loss By Sport And Market Type");
        WinLossBySportAndMarketTypePage page = agentHomePage.clickSubMenu(REPORT, WIN_LOSS_BY_MARKET_TYPE, WinLossBySportAndMarketTypePage.class);

        log("Verify 1. Verify  Win Loss By Sport And Market Type UI display correctly");
        String today = DateUtils.getDate(0,"dd/MM/yyyy", AGConstant.timeZone);
        Assert.assertEquals(page.lblPageTitle.getText().trim(), AGConstant.Report.WinLossBySportAndMarketType.LBL_TITLE,"FAILED! Page title is incorrect");
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"),today,"FAILED! Incorrect default date in search from textbox");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"),today,"FAILED! Incorrect default date in search to textbox");
        Assert.assertEquals(page.btnYesterday.getText(), AGConstant.Report.BTN_YESTERDAY, "FAILED! Yesterday button text is incorrect ");
        Assert.assertEquals(page.btnLastWeek.getText(), AGConstant.Report.LAST_WEEK, "FAILED! Last Business Week button text is incorrect ");
        Assert.assertEquals(page.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button text is incorrect ");
        Assert.assertEquals(page.btnToday.getText(), AGConstant.Report.BTN_TODAY, "FAILED! Today button text is incorrect ");
        Assert.assertEquals(page.lblInfo.getText(), AGConstant.Report.WinLossBySportAndMarketType.LBL_INFO,"FAILED! Incorrect hint message");
        Assert.assertEquals(page.lblReportTitle.getText(),String.format(AGConstant.Report.WinLossBySportAndMarketType.LBL_SEARCH_TITLE,today,today),"FAILED! Search title range is not correct" );
        if(page.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record text is incorrect");
        }

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Grand total row is correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Win Loss By Sport And Market Type
     *          2. Filter Exchange product that have data
     * @expect: 1. Verify Grand total row is sum by total of all sports
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Win_Loss_By_Sport_And_Market_Type_003(){
        log("@title: Validate Grand total row is correctly");
        log("Step 1. Navigate Report > Win Loss By Sport And Market Type");
        WinLossBySportAndMarketTypePage page = agentHomePage.clickSubMenu(REPORT, WIN_LOSS_BY_MARKET_TYPE, WinLossBySportAndMarketTypePage.class);

        log("Step 2. Filter Exchange product that have data ");
        page.dpFrom.previousMonthWithDate(-1,"20");
        page.filter("Exchange","All",false);
        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record text is incorrect validate grand total row");
            return;
        }

        log("Step 3. Expand all sports and sum data of Total rows");
        List<ArrayList<String>> lstData = page.getReportData();
        ArrayList<String> expectedData = page.sumTotalAllSports(lstData);

        log("Verify 1. Verify Grand total row is sum by total of all sports");
        Assert.assertEquals(lstData.get(lstData.size()-1),expectedData,"FAILED! Grand Total Row not match when sum with total row of all sport");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data on when click on Turnover is matched with details
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Win Loss By Sport And Market Type
     *          2. Filter Exchange product that have data
     *          3. Click on Turnover of a market type
     * @expect: 1 Verify Turnover in summary report match when sum player stake in detail report
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Win_Loss_By_Sport_And_Market_Type_004(){
        log("@title: Validate data on when click on Turnover is matched with details");
        log("Step 1. Navigate Report > Win Loss By Sport And Market Type");

        WinLossBySportAndMarketTypePage page = agentHomePage.clickSubMenu(REPORT, WIN_LOSS_BY_MARKET_TYPE, WinLossBySportAndMarketTypePage.class);
        log("Step 2. Filter Exchange product that have data ");
        page.dpFrom.previousMonthWithDate(-1,"25");
        page.filter("Exchange","All",false);
        if(page.lblNoRecord.isDisplayed()){
            log("By Passed as ther is no data in the report");
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record text is incorrect validate grand total row");
            return;
        }

        log("Step 3. Expand all Sport and get data");
        List<ArrayList<String>> lstData = page.getReportData();

        log("Verify 1 Verify Turnover in summary report match when detail when click on Turnover of a market");
        Assert.assertTrue(page.dataMatchTransactionDetail(lstData),"FAILED! Turnover in summary report NOT match when sum player stake in detail report");

        log("INFO: Executed completely");
    }
}
