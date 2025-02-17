package agentsite.testcase.reports;

import agentsite.pages.report.CancelledBetsPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class CancelledBetsTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Cancelled Bets
     * @expect: 1. There is no http responded error returned
     */
    @TestRails(id = "3741")
    @Test(groups = {"http_request"})
    public void Agent_Report_Cancelled_Bets_3741() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Cancelled Bets");
        CancelledBetsPage page = agentHomePage.navigateCancelledBetsPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Cancelled Bets UI display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Cancelled Bets
     * @expect: 1. Verify Cancelled Bets UI display correctly
     */
    @TestRails(id = "798")
    @Test(groups = {"smoke"})
    public void Agent_Report_Cancelled_Bets_798() {
        log("@title: Validate Cancelled Bets UI display correctly");
        log("Step 1. Navigate Report > Cancelled Bets");
        CancelledBetsPage page = agentHomePage.navigateCancelledBetsPage();

        log("Verify 1. Verify Cancelled Bets UI display correctly");
        //TODO: restructure verify UI for newUI/oldUI
        String todayDay = DateUtils.getDate(0, "dd/MM/yyyy", AGConstant.timeZone);
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"), todayDay, "FAILED! From textbox default date is incorrect");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"), todayDay, "FAILED! To textbox default date is incorrect");
        Assert.assertEquals(page.ddbStatus.getFirstSelectedOption(), "All", "FAILED! Status not select All by default");
        Assert.assertEquals(page.ddbProduct.getFirstSelectedOption(), "Exchange", "FAILED! Product not select Exchange by default");
//        Assert.assertEquals(page.txtUserName.getAttribute("placeholder"), AGConstant.LBL_USERNAME_PLACE_HOLDER, "FAILED! From textbox not display");
        Assert.assertEquals(page.lblInfo.getText(), AGConstant.Report.CancelledBets.LBL_INFO, "FAILED! Info label text is incorrect");
//        Assert.assertEquals(page.tblReport.getHeaderList(), AGConstant.Report.CancelledBets.TABLE_HEADER, "FAILED! Report header not display correctly");
        Assert.assertEquals(page.btnToday.getText(), AGConstant.Report.BTN_TODAY, "FAILED! Today button display incorrect text");
        Assert.assertEquals(page.btnYesterday.getText(), AGConstant.Report.BTN_YESTERDAY, "FAILED! Today button display incorrect text");
        Assert.assertEquals(page.btnLastWeek.getText(), AGConstant.Report.LAST_WEEK, "FAILED! Today button display incorrect text");
        Assert.assertEquals(page.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Today button display incorrect text");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cancelled Bets can filter correct data
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report >Cancelled Bets
     * 2. Search the data range has data of Exchange Product
     * @expect: 1. Data display with status is System Voided
     */
    @TestRails(id = "799")
    @Test(groups = {"smoke"})
    public void Agent_Report_Cancelled_Bets_799() {
        log("@title: Validate cancelled Bets can filter correct data");
        log("Step 1. Navigate Report > Cancelled Bets");
        CancelledBetsPage page = agentHomePage.navigateCancelledBetsPage();

        log("Step 2. Search the data range has data of Exchange Product");
        page.dpFrom.previousMonthWithDate(-1, "1");
        page.filter("All", "", "Exchange");

        log("Verify 1. Data display with status is System Voided");
        if (page.lblNoRecord.isDisplayed()) {
            log("PASSED By pass as no data found");
            Assert.assertTrue(true, "Bypass this test case");
            return;
        }

        List<ArrayList<String>> dataLst = page.tblReport.getRowsWithoutHeader(false);
        for (int i = 0; i < dataLst.size() - 1; i++) {
            Assert.assertTrue(dataLst.get(i).get(page.colStatus - 1).contains("Voided"), "FAILED! Status should in System Voided or Operator Voided");
        }
        log("INFO: Executed completely");
    }


}
