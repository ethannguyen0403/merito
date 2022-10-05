package agentsite.testcase.satsport.report;

import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.report.WinLossDetailPage;
import agentsite.ultils.account.ProfileUtils;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.WIN_LOSS_BY_DETAIL;

public class WinLossDetailTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Win Loss Detail
     * @expect: 1. Win Loss Simple page is displayed
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_WinLossDetail_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > Win Loss Detail");
        agentHomePage.clickSubMenu(REPORT, WIN_LOSS_BY_DETAIL, WinLossDetailPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Win Loss Detail UI display correctly
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Report> Win Loss Detail
     * @expect:  1. Verify Win Loss Detail UI display correctly
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_WinLossDetail_002(){
        log("@title: Validate Win Loss Detail UI display correctly");
        log("Step 1. Navigate Report> Win Loss Detail");
        String directDownlineWinLossDetai = String.format(WIN_LOSS_BY_DETAIL,ProfileUtils.convertDownlineByBrand(ProfileUtils.getDownlineBalanceInfo().get(0).get(0),ProfileUtils.getAppName()));
        WinLossDetailPage page = agentHomePage.clickSubMenu(REPORT, directDownlineWinLossDetai, WinLossDetailPage.class);

        log("Verify 1. Verify Win Loss Detail UI display correctly");
        Assert.assertTrue(page.txtFromSearch.isDisplayed(),"FAilED! From textbox does not display");
        Assert.assertTrue(page.txtToSearch.isDisplayed(),"FAilED! To textbox does not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(),"FAilED! Product dropdown does not display");
        Assert.assertEquals(page.btnToday.getText(),AGConstant.Report.BTN_TODAY,"FAilED! Today button text is incorrect");
        Assert.assertEquals(page.btnYesterday.getText(),AGConstant.Report.BTN_YESTERDAY,"FAilED! Yesterday button text is incorrect");
        Assert.assertEquals(page.btnLastWeek.getText(),AGConstant.Report.LAST_WEEK,"FAilED! Last Business Week button text is incorrect");
        Assert.assertEquals(page.lblShowTotal.getText(),AGConstant.Report.WinLossDetails.LBL_SHOW_TOTAL,"FAILED! Show total only label display incorrect text");
        Assert.assertEquals(page.lblInfo.getText(),AGConstant.Report.WinLossDetails.LBL_INFO,"FAILED! Hint info label display incorrect text");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can filter Win Loss Detail report
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Report > Win Loss Simple
     *           2. Search the data range that have data
     * @expect:  1. Verify can display data
     */
    @Test (groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_Report_WinLossDetail_003(String downlineAccount){
        log("@title: Validate can filter Win Loss Detail report");
        log("Step 1. Navigate Report > Win Loss Detail");
        String directDownlineWinLossDetai = String.format(WIN_LOSS_BY_DETAIL,ProfileUtils.convertDownlineByBrand(ProfileUtils.getDownlineBalanceInfo().get(0).get(0),ProfileUtils.getAppName()));
        WinLossDetailPage page = agentHomePage.clickSubMenu(REPORT, directDownlineWinLossDetai, WinLossDetailPage.class);

        log("Step 2. Search the data range that have data");
        page.dpFrom.previousMonthWithDate(-1,"25");
        page.filter("Exchange");
        List<String> lstAccount = page.tblReport.getColumn(page.colUserName,1, false);

        if(lstAccount.size()==0){
            log("Verify 1. Verify as has no data");
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record message not display as has no data");
            return;
        }
        log("Verify 1. Verify can display data");
        for(String observed : lstAccount) {
            Assert.assertTrue(observed.contains(downlineAccount), String.format("ERROR: The expected username not containt account is '%s' but found '%s'", downlineAccount, observed));
        }
        log("INFO: Executed completely");
    }


}
