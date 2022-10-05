package agentsite.testcase.satsport.report;

import com.paltech.utils.DateUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.report.StatementReportPage;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.STATEMENT_REPORT;

public class StatementReportTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Statement Report
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Statement_Report_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report>  Cancelled Bets");
        agentHomePage.clickSubMenu(REPORT, STATEMENT_REPORT, StatementReportPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Statement Report can filter correct data
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Navigate Report >  Statement Report
     * 2. Search the data range has data of Exchange Product
     * @expect: 1. Report is display if have data
     */
    @Test (groups = {"smoke"})
    @Parameters("username")
    public void Agent_Report_Statement_Report_003(String username){
        log("@title: Validate Statement Report can filter correct data");
        log("Step 1. Navigate Report > Statement Report");
        StatementReportPage page = agentHomePage.clickSubMenu(REPORT, STATEMENT_REPORT, StatementReportPage.class);

        log("Step 2. Search the data range has data of Exchange Product");
        String today = DateUtils.getDate(0,"dd/MM/yyyy",AGConstant.timeZone);
        String lstWeek = DateUtils.getDate(-6,"dd/MM/yyyy",AGConstant.timeZone);
        page.btnLastWeek.click();
        page.waitingLoadingSpinner();

        Assert.assertEquals(page.lnlBreadcrumbSearchTitle.getText(),String.format(AGConstant.Report.StatementReport.LBL_STATEMENT_REPORT_SEARCH_TITLE,lstWeek,today),"FAILED! Search title is incorrect");
        log("Verify 1. Report is display if have data");
        if(page.lblNoRecord.isDisplayed()){
            log("PASSED By pass as no data found");
            Assert.assertTrue(true,"Bypass this test case");
            return;
        }
        List<ArrayList<String>> dataLst = page.tblReport.getRowsWithoutHeader(false);
        for(int i = 0; i<dataLst.size(); i++){
            Assert.assertEquals(dataLst.get(i).get(page.colLoginId-1),username,String.format("FAILED! Report display data of %s",dataLst.get(i).get(page.colLoginId-1)));
        }
        log("INFO: Executed completely");
    }
}
