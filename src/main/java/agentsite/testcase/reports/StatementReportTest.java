package agentsite.testcase.reports;

import agentsite.pages.report.StatementReportPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.Report.*;
import static common.AGConstant.Report.StatementReport.TABLE_HEADER;

public class StatementReportTest extends BaseCaseTest {
    /**
     * @title: Validate Statement Report can filter correct data
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report >  Statement Report
     * 2. Search the data range has data of Exchange Product
     * @expect: 1. Report is display if have data
     */
    @TestRails(id = "800")
    @Test(groups = {"smoke"})
    @Parameters("username")
    public void Agent_Report_Statement_Report_800(String username) {
        log("@title: Validate Statement Report can filter correct data");
        log("Step 1. Navigate Report > Statement Report");
        StatementReportPage page = agentHomePage.navigateStatementReportPage();
        log("Step 2. Search the data range has data of Exchange Product");
        String today = DateUtils.getDate(0, "dd/MM/yyyy", AGConstant.timeZone);
        String lstWeek = DateUtils.getDate(-6, "dd/MM/yyyy", AGConstant.timeZone);
        page.btnLastWeek.click();
        page.waitingLoadingSpinner();

        Assert.assertEquals(page.lnlBreadcrumbSearchTitle.getText(), String.format(StatementReport.LBL_STATEMENT_REPORT_SEARCH_TITLE, lstWeek, today), "FAILED! Search title is incorrect");
        log("Verify 1. Report is display if have data");
        if (page.lblNoRecord.isDisplayed()) {
            log("PASSED By pass as no data found");
            Assert.assertTrue(true, "Bypass this test case");
            return;
        }
        List<ArrayList<String>> dataLst = page.tblReport.getRowsWithoutHeader(false);
        for (int i = 0; i < dataLst.size(); i++) {
            Assert.assertEquals(dataLst.get(i).get(page.colLoginId - 1), username, String.format("FAILED! Report display data of %s", dataLst.get(i).get(page.colLoginId - 1)));
        }
        log("INFO: Executed completely");
    }
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Statement Report
     * @expect: 1. There is no http responded error returned
     */
    @TestRails(id = "3742")
    @Test(groups = {"http_request"})
    public void Agent_Report_Statement_Report_3742() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report>  Statement Report");
        agentHomePage.navigateStatementReportPage();
        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3743")
    @Test(groups = {"regression"})
    public void Agent_Report_Statement_Report_3743() {
        log("@title: Validate Statement Report UI display correctly");
        log("Step 1. Navigate Report > Statement Report");
        String today = DateUtils.getDate(0, "dd/MM/yyyy", AGConstant.timeZone);
        StatementReportPage page = agentHomePage.navigateStatementReportPage();

        log("Verify 1. Verify Statement Report UI display correctly");
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"), today, "Failed! From date is incorrect");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"), today, "Failed! To date is incorrect");
        Assert.assertEquals(page.btnToday.getText(), BTN_TODAY, "Failed! Today button is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(), BTN_YESTERDAY, "Failed! Yesterday button is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(), LAST_BUSINESS_WEEK, "Failed! Last Week button is incorrect");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "Failed! Submit button is incorrect");
        Assert.assertEquals(page.lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS, "Failed! Submit button is incorrect");
        Assert.assertEquals(page.lnkHome.getText(), "Home", "FAILED! Home link is incorrect");
        Assert.assertEquals(page.lnlBreadcrumbSearchTitle.getText(), String.format(StatementReport.LBL_STATEMENT_REPORT_SEARCH_TITLE, today, today), "FAILED! Search title is incorrect");
        Assert.assertEquals(page.tblReport.getColumnNamesOfTable(), TABLE_HEADER, "FAILED!Table Header is incorrect");
        log("INFO: Executed completely");
    }

}
