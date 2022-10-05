package agentsite.testcase.satsport.report;

import com.paltech.utils.DateUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.BFVoidedDiscrepancyPage;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.BF_VOIDED_DISCREAPANCY;
import static agentsite.common.AGConstant.HomePage.REPORT;

public class BFVoidedDiscrepancyTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> BF Voided Discrepancy
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_BF_Void_Discrepancy_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> BF Voided Discrepancy");
        agentHomePage.clickSubMenu(REPORT, BF_VOIDED_DISCREAPANCY, BFVoidedDiscrepancyPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate BF Voided Discrepancy display correctly
     * @pre-condition:
     *           1. Log in successfully by PO level
     * @steps: 1. Navigate Report > BF Voided Discrepancy
     * @expect: 1. Verify BF Voided Discrepancy UI display correctly
     */
    @Test (groups = {"smokePO"})
    public void Agent_Report_BF_Void_Discrepancy_002(){
        log("@title: Validate BF Voided Discrepancy display correctly");
        log("Step 1. Navigate Report> BF Voided Discrepancy");
        BFVoidedDiscrepancyPage page = agentHomePage.clickSubMenu(REPORT, BF_VOIDED_DISCREAPANCY, BFVoidedDiscrepancyPage.class);

        log("Verify 1. Verify BF Voided Discrepancy UI display correctly");
        String today = DateUtils.getDate(0,"dd/MM/yyyy", AGConstant.timeZone);
        String todayYYMMDD = DateUtils.getDate(0,"yyyy/MM/dd", AGConstant.timeZone);
        String searchRangeTitleMsg = String.format(AGConstant.Report.BFVoidedDiscrepancy.LBL_SEARCH_RANGE_TITLE,todayYYMMDD,todayYYMMDD);
        Assert.assertEquals(page.txtFromSearch.getAttribute("value"),today,"FAILED, From textbox not display today day");
        Assert.assertEquals(page.txtToSearch.getAttribute("value"),today,"FAILED, To textbox not display today day");
        Assert.assertEquals(page.btnToday.getText(),AGConstant.Report.BTN_TODAY,"FAILED! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(),AGConstant.Report.BTN_YESTERDAY,"FAILED! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLastBusinessWeek.getText(),AGConstant.Report.LAST_WEEK,"FAILED! Last Business Week button is incorrect");
        Assert.assertTrue(page.txtWagerId.isDisplayed(),"FAILED! Wager Id textbox is not display ");
        Assert.assertTrue(page.ddbSport.isDisplayed(),"FAILED! Sport dropdown is not display ");
        Assert.assertTrue(page.ddbEvent.isDisplayed(),"FAILED! Event dropdown is not display ");
        Assert.assertEquals(page.lblInfo.getText(),AGConstant.Report.BFVoidedDiscrepancy.LBL_INFO,"FAILED! Hint message display incorrect");
        AGConstant.Report.BFVoidedDiscrepancy.TABLE_HEADER.set(0,searchRangeTitleMsg);
        ArrayList<String> headerTitle = page.tblReport.getHeaderNameOfRows();
        Assert.assertEquals(headerTitle,AGConstant.Report.BFVoidedDiscrepancy.TABLE_HEADER,"FAILED! Header title is not correct");
        if(page.lblNoReport.isDisplayed())
        {
            Assert.assertEquals(page.lblNoReport.getText(),AGConstant.NO_RECORD_FOUND,"FAILED! Incorrect no record message");
            Assert.assertEquals(page.ddbSport.getFirstSelectedOption(),"No sports","FAILED! Incorrect value if have no sport");
            Assert.assertEquals(page.ddbEvent.getFirstSelectedOption(),"No events","FAILED! Incorrect default value if have no event");
        }
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can filter the report BF voided Discrepancy
     * @pre-condition:
     *           1. Log in successfully by PO level
     * @steps: 1. Navigate Report > BF Voided Discrepancy
     *         2. Enter valid data and filter
     * @expect: 1. BF voided report display with valid data
     */
    @Test (groups = {"smokePO"})
    public void Agent_Report_BF_Void_Discrepancy_003(){
        log("@title: Validate can filter the report BF voided Discrepancy");
        log("Step 1. Navigate Report> BF Voided Discrepancy");
        BFVoidedDiscrepancyPage page = agentHomePage.clickSubMenu(REPORT, BF_VOIDED_DISCREAPANCY, BFVoidedDiscrepancyPage.class);
        log("Step 2. Enter valid data and filter");
        page.dtpFrom.previousMonthWithDate(-1,"20");
        page.btnSubmit.click();
        page.waitingLoadingSpinner();

        log("Verify 1. Verify BF Voided Discrepancy UI display correctly");
        if(page.lblNoReport.isDisplayed()) {
            Assert.assertEquals(page.lblNoReport.getText(),AGConstant.NO_RECORD_FOUND,"FAILED! Incorrect no record message");
            return;
        }
        List<ArrayList<String>> report = page.tblReport.getRowsWithoutHeader(false);
        for(int i = 0; i <report.size()-2; i++)
        {
            Assert.assertTrue(report.get(i).get(page.colStatus-1).contains("Voided"),"FAILED! Display the wager is not in voided status");
        }

        log("INFO: Executed completely");
    }
}
