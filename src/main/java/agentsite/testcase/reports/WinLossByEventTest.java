package agentsite.testcase.reports;

import common.AGConstant;
import agentsite.pages.report.WinLossByEventPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WinLossByEventTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Win Loss By Event
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Win_Loss_By_Event_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Win Loss By Event");
        agentHomePage.navigateWinLossByEventPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Win Loss By Event UI display correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Navigate Report >  Win Loss By Event
     * @expect: 1. Verify Win Loss By Event UI display correctly
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Win_Loss_By_Event_002(){
        log("@title: Validate Win Loss By Sport And Market Type UI display correctly");
        log("Step 1. Navigate Report >  Win Loss By Event");
        WinLossByEventPage page = agentHomePage.navigateWinLossByEventPage();

        log("Verify 1. Verify Win Loss By Event UI display correctly");
        String today = DateUtils.getDate(0,"dd/MM/yyyy", AGConstant.timeZone);
        String todayYYYYMMDD = DateUtils.getDate(0,"yyyy-MM-dd", AGConstant.timeZone);
        Assert.assertEquals(page.header.lblPageTitle.getText(), AGConstant.Report.WinLossByEvent.LBL_TITLE,"FAILED! Incorrect default date in search from textbox");
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"),today,"FAILED! Incorrect default date in search from textbox");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"),today,"FAILED! Incorrect default date in search to textbox");
        Assert.assertEquals(page.ddbProduct.getFirstSelectedOption(),"Exchange", "FAILED! Default product is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(), AGConstant.Report.BTN_YESTERDAY, "FAILED! Yesterday button text is incorrect ");
        Assert.assertEquals(page.btnLastWeek.getText(), AGConstant.Report.LAST_WEEK, "FAILED! Last Business Week button text is incorrect ");
        Assert.assertEquals(page.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button text is incorrect ");
        Assert.assertEquals(page.lblInfo.getText(), AGConstant.Report.WinLossByEvent.LBL_INFO,"FAILED! Incorrect hint message");
        Assert.assertEquals(page.lblReportTitle.getText(),String.format(AGConstant.Report.WinLossByEvent.LBL_SEARCH_TITLE,todayYYYYMMDD,todayYYYYMMDD),"FAILED! Search title range is not correct" );
        Assert.assertEquals(page.btnToday.getText(), AGConstant.Report.BTN_TODAY, "FAILED! Today button text is incorrect ");
        if(page.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(page.ddbSport.getFirstSelectedOption(),"No Sport", "FAILED! Default selected Sport is incorrect");
            Assert.assertEquals(page.ddbCompetition.getFirstSelectedOption(),"No Competition", "FAILED! Default selected Sport is incorrect");
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record text is incorrect");
        }

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Grand total row is correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Navigate Report >  Win Loss By Event
     *          2. Filter Exchange product that have data
     *          3. Expand the competition
     * @expect: 1. Verify Grand total row is sum by total of all competitions
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Win_Loss_By_Event_003(){
        log("@title: Validate Win Loss By Sport And Market Type UI display correctly");
        log("Step 1. Navigate Report >  Win Loss By Event");
        WinLossByEventPage page = agentHomePage.navigateWinLossByEventPage();

        log("Step 2. Filter Exchange product that have data");
        page.btnLastWeek.click();
        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record text is incorrect validate grand total row");
            return;
        }
        log("Step 3. Expand the competition");
        List<String> lstCompetitions = page.getAllCompetitions(true);

        log("Verify 1. Verify Grand total row is sum by total of all competitions");
        ArrayList<String> expectedGrandTotal = page.sumTotalRowAllCompetition(lstCompetitions);
        Assert.assertEquals(page.getGrandTotalRow(),expectedGrandTotal,"FAILED!  Grand total row is NOT matched when sum total row of all competitions");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Turnover match with summary Player stake in bets list
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Navigate Report >  Win Loss By Event
     *          2. Filter Exchange product that have data
     *          3. Expand the competition
     *          4. Click on Turn over number
     * @expect: 1. Verify Turnover match with summary Player stake in bets list
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Win_Loss_By_Event_004(){
        log("@title: Validate Turnover match with summary Player stake in bets list");
        log("Step 1. Navigate Report >  Win Loss By Event");
        WinLossByEventPage page = agentHomePage.navigateWinLossByEventPage();

        log("Step 2. Filter Exchange product that have data");
        page.btnLastWeek.click();
        if(page.lblNoRecord.isDisplayed()){
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record text is incorrect validate grand total row");
            return;
        }

        log("Step 3. Expand the competition");
        List<ArrayList<String>> allData = page.tblReport.getRowsWithoutHeader(false);
        String eventName = allData.get(1).get(0);
        String turnover = allData.get(1).get(1);
        log("Step 4. Click on Turn over number");
        double totalPlayerStake = page.clickTurnoverLink(eventName).sumPlayerStake();

        log("Verify 1. Verify Turnover match with summary Player stake in bets list");
        Assert.assertEquals(turnover,String.format("%.2f",totalPlayerStake),"FAILED! Turnover not matc when sum Player stake in the bet list");
        log("INFO: Executed completely");
    }
}
