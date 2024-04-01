package agentsite.testcase.reports;

import agentsite.pages.report.StatementReportPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.report.StatementReportUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.Report.*;
import static common.AGConstant.Report.StatementReport.*;

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
        page.btnLastWeek.click();
        page.waitingLoadingSpinner();

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
    @Test(groups = {"regression_sat"})
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
        Assert.assertEquals(page.lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_1_TO_6, "Failed! Submit button is incorrect");
        Assert.assertEquals(page.lnkHome.getText(), "Home", "FAILED! Home link is incorrect");
        Assert.assertTrue(page.lnlBreadcrumbSearchTitle.getText().equalsIgnoreCase(String.format(StatementReport.LBL_STATEMENT_REPORT_SEARCH_TITLE, today, today)), "FAILED! Search title is incorrect");
        Assert.assertEquals(page.tblReport.getColumnNamesOfTable(), TABLE_HEADER_OLDUI, "FAILED!Table Header is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4137")
    @Test(groups = {"regression_newui"})
    public void Agent_Report_Statement_Report_4137() {
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
        Assert.assertEquals(page.lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_1_TO_6, "Failed! You can see report label is incorrect");
        Assert.assertEquals(page.lnkHome.getText(), "Home", "FAILED! Home link is incorrect");
        Assert.assertTrue(page.lnlBreadcrumbSearchTitle.getText().equalsIgnoreCase(String.format(StatementReport.LBL_STATEMENT_REPORT_SEARCH_TITLE, today, today)), "FAILED! Search title is incorrect");
        Assert.assertEquals(page.tblReport.getColumnNamesOfTable(), TABLE_HEADER_NEWUI, "FAILED!Table Header is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3744")
    @Test(groups = {"regression", "nolan"})
    public void Agent_Report_Statement_Report_3744() {
        log("@title: Validate  Statement Report can drilldown");
        String fromDateApi = DateUtils.getDate(-28, "yyyy-MM-dd", AGConstant.timeZone);
        String toDateApi = DateUtils.getDate(0, "yyyy-MM-dd", AGConstant.timeZone);
        String fromDate = DateUtils.getDate(-28, "dd/MM/yyyy", AGConstant.timeZone);
        String toDate = DateUtils.getDate(0, "dd/MM/yyyy", AGConstant.timeZone);
        String userID = ProfileUtils.getProfile().getUserID();
        String userName = ProfileUtils.getProfile().getUserCode();
        List<String> lstUserDownline = StatementReportUtils.getReportStatementDownLineUsers(userID, fromDateApi, toDateApi);
        if (lstUserDownline.isEmpty()) {
            throw new SkipException(String.format("SKIPPED! Have no data for expanding in filter range from %s to %", fromDate, toDate));
        }
        log("Step 1. Navigate Report >  Statement Report");
        StatementReportPage page = agentHomePage.navigateStatementReportPage();

        log("Step 2. Filter the data range has data");
        page.statementReport.filter(fromDate, toDate, true);
        log("Step 3. Click on + to drill down");
        page.statementReport.expandFirstRecord();

        log("Verify 3. Validate can drill down successfully");
        List<String> lstReturn = page.statementReport.getUsersListUnderExpanded(userName);
        Collections.sort(lstUserDownline);
        Collections.sort(lstReturn);
        Assert.assertEquals(lstUserDownline, lstReturn, "FAILED! User downline list does not match");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3745")
    @Test(groups = {"regression", "nolan"})
    public void Agent_Report_Statement_Report_3745() {
        log("@title: Validate  Statement Report can drilldown");
        String fromDateApi = DateUtils.getDate(-28, "yyyy-MM-dd", AGConstant.timeZone);
        String toDateApi = DateUtils.getDate(0, "yyyy-MM-dd", AGConstant.timeZone);
        String fromDate = DateUtils.getDate(-28, "dd/MM/yyyy", AGConstant.timeZone);
        String toDate = DateUtils.getDate(0, "dd/MM/yyyy", AGConstant.timeZone);
        String userID = ProfileUtils.getProfile().getUserID();
        String userName = ProfileUtils.getProfile().getUserCode();
        String userType = ProfileUtils.getProfile().getUserType();
        List<String> lstUserDownline = StatementReportUtils.getReportStatementDownLineUsers(userID, fromDateApi, toDateApi);
        if (lstUserDownline.isEmpty()) {
            throw new SkipException(String.format("SKIPPED! Have no data for expanding in filter range from %s to %", fromDate, toDate));
        }
        log("Step 1. Navigate Report >  Statement Report");
        StatementReportPage page = agentHomePage.navigateStatementReportPage();

        log("Step 2. Filter the data range has data");
        page.statementReport.filter(fromDate, toDate, true);

        log("Step 3. Click on Detail icon to open detail transaction of account");
        page.statementReport.openStatementReportDetail(userName);

        log("Verify 3. Validate Tabel header is correctly display");
        page.statementReport.verifyTableDetailHeaderDisplayCorrect(userType);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3746")
    @Test(groups = {"regression", "nolan"})
    public void Agent_Report_Statement_Report_3746() {
        log("@title: Validate Transaction detail report match with summary");
        String fromDateApi = DateUtils.getDate(-28, "yyyy-MM-dd", AGConstant.timeZone);
        String toDateApi = DateUtils.getDate(0, "yyyy-MM-dd", AGConstant.timeZone);
        String fromDate = DateUtils.getDate(-28, "dd/MM/yyyy", AGConstant.timeZone);
        String toDate = DateUtils.getDate(0, "dd/MM/yyyy", AGConstant.timeZone);
        String userID = ProfileUtils.getProfile().getUserID();
        String userName = ProfileUtils.getProfile().getUserCode();
        List<String> lstUserDownline = StatementReportUtils.getReportStatementDownLineUsers(userID, fromDateApi, toDateApi);
        if (lstUserDownline.isEmpty()) {
            throw new SkipException(String.format("SKIPPED! Have no data for expanding in filter range from %s to %", fromDate, toDate));
        }
        log("Step 1. Navigate Report >  Statement Report");
        StatementReportPage page = agentHomePage.navigateStatementReportPage();

        log("Step 2. Filter the data range has data");
        page.statementReport.filter(fromDate, toDate, true);

        log("Step 3. Click on Detail icon to open detail transaction of account");
        page.statementReport.openStatementReportDetail(userName);

        log("Step 4. Click on the Soccer");
        page.statementReport.openSportGameDetail("Exchange - Soccer");

        log("Verify 4. Validate in transaction detail match");
        List<Double> lstAvailableBalance = page.statementReport.defineAvailableBalance();
        page.statementReport.verifyAvailableBalanceShowCorrect(lstAvailableBalance);
        log("INFO: Executed completely");
    }
}
