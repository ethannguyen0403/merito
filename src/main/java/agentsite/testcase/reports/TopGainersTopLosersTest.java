package agentsite.testcase.reports;

import agentsite.pages.report.BigStakeConfigurationPage;
import agentsite.pages.report.TopGainersTopLosersPage;
import agentsite.pages.report.WinLossSimplePage;
import agentsite.ultils.report.TopGainerLoserUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class TopGainersTopLosersTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Transaction history
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Top_Gainers_Top_Losers_001() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Transaction history");
        agentHomePage.navigateTopGainersTopLosersPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Top Gainers & Top Losers UI display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Top Gainers & Top Losers
     * @expect: 1. Verify Top Gainers & Top Losers UI display correctly
     */
    @Test(groups = {"smoke"})
    public void Agent_Report_Top_Gainers_Top_Losers_002() {
        log("@title: Validate Top Gainers & Top Losers UI display correctly ");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = agentHomePage.navigateTopGainersTopLosersPage();
        log("Verify 1. Verify Top Gainers & Top Losers UI display correctly");
        String today = DateUtils.getDate(0, "dd/MM/yyyy", AGConstant.timeZone);
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"), today, "FAILED! Incorrect default date in search from textbox");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"), today, "FAILED! Incorrect default date in search to textbox");
        Assert.assertEquals(page.ddbProduct.getFirstSelectedOption(), "Exchange", "FAILED! Default product is incorrect");

        Assert.assertEquals(page.btnSearch.getText().trim(), AGConstant.Report.TopGainersTopLosers.BTN_SEARCH, "FAILED! Search button text is incorrect ");
        Assert.assertEquals(page.lblInfoReportRangeAvailable.getText(), AGConstant.Report.TopGainersTopLosers.LBL_INFO_REPORT_VALID, String.format("FAILED! Expected label text is %s but found %s", AGConstant.Report.TopGainersTopLosers.LBL_INFO_REPORT_VALID, page.lblInfoReportRangeAvailable.getText()));
        Assert.assertEquals(page.lblInfoDataSupport.getText(), AGConstant.Report.TopGainersTopLosers.LBL_INFO_SUPPORT_YESTERDAY_DATA, String.format("FAILED! Expected label text is %s but found %s", AGConstant.Report.TopGainersTopLosers.LBL_INFO_SUPPORT_YESTERDAY_DATA, page.lblInfoDataSupport.getText()));
        Assert.assertEquals(page.lblInfoReportPlaceTime.getText(), AGConstant.Report.TopGainersTopLosers.LBL_INFO_PLACE_TIME, String.format("FAILED! Expected label text is %s but found %s", AGConstant.Report.TopGainersTopLosers.LBL_INFO_PLACE_TIME, page.lblInfoReportPlaceTime.getText()));

        Assert.assertTrue(page.topGainersTopLosers.isTopGainersHeaderTable(), "FAILED! Incorrect header of top gainers");
        Assert.assertTrue(page.topGainersTopLosers.isTopLosersHeaderTable(), "FAILED! Incorrect header of top loser");
        Assert.assertTrue(page.topGainersTopLosers.isBigStakeHeaderTable(), "FAILED! Incorrect header of big stake");

        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke"})
    public void Agent_Report_Top_Gainers_Top_Losers_004() {
        log("@title: Validate Top Gainers & Top Losers UI display correctly");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = agentHomePage.navigateTopGainersTopLosersPage();
        log("Step 2. Filter Exchange product that have the date has data");
        log("Verify 1. Verify Big Stake table display correct data");

        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke"})
    public void Agent_Report_Top_Gainers_Top_Losers_005() {
        log("@title: Validate Top Losers  data display correctly");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = agentHomePage.navigateTopGainersTopLosersPage();
        log("Step 2. Filter Exchange product that have the date has data");
        log("Verify 1. Verify Big Stake table display correct data");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3757")
    @Test(groups = {"http_request"})
    public void Agent_Report_Top_Gainers_Top_Losers_3757() {
        log("@title: There is no http responded error returned");
        log("@pre-condition 1: Log in successfully by SAD");
        log("Step 1. Navigate Report> Transaction history");
        agentHomePage.navigateTopGainersTopLosersPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3758")
    @Test(groups = {"regression"})
    public void Agent_Report_Top_Gainers_Top_Losers_3758() {
        log("@title: Validate Top Gainers data display correctly");
        log("@pre-condition 1: Log in successfully by SAD");
        log("@pre-condition 2: Go to Win Loss Simple > Search a date range that has the account has placed a bet and bets are settled for Exchange product only (not including Fancy or Bookmaker)");
        WinLossSimplePage winLossSimplePage = agentHomePage.navigateWinLossSimplePage();
        String fromDate = DateUtils.getDate(-60, "yyyy-MM-dd", AGConstant.timeZone);
        String toDate = DateUtils.getDate(0, "yyyy-MM-dd", AGConstant.timeZone);
        winLossSimplePage.dpFrom.selectDate(fromDate,"yyyy-MM-dd");
        winLossSimplePage.dpTo.selectDate(toDate,"yyyy-MM-dd");
        winLossSimplePage.btnSubmit.click();
        log("@pre-condition 3: Get Win/Loss and Total tax/comm of any player: for example Win/Loss: 0.90, 0.00, and Total tax/comm");
        List<ArrayList<String>> winner = winLossSimplePage.winLossSimple.getListSMAWinnerInfor();
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = winLossSimplePage.navigateTopGainersTopLosersPage();
        log("Step 2. Filter Exchange product that have the date has data");
        page.search(fromDate,toDate,"Exchange");
        log("Verify 1. Validate Top Gainers table display correct data");
        Assert.assertTrue(page.topGainersTopLosers.isCheckUserDisplayInTopGainersTableCorrect(winner),"FAILED! Data of Top gainers displayed incorrect!");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3759")
    @Test(groups = {"regression_sat"})
    public void Agent_Report_Top_Gainers_Top_Losers_3759() {
        log("@title: Validate Big Stake data display correctly");
        log("@pre-condition 1: Log in successfully by SAD");
        log("@pre-condition 2: configure big stake = minbet + 1");
        BigStakeConfigurationPage bigStakeConfigurationPage = agentHomePage.navigateBigStakeConfigurationPage();
        bigStakeConfigurationPage.waitingLoadingSpinner();
        String bigStake = bigStakeConfigurationPage.getBigStakeCurrently();
        log("@pre-condition 3: Have bet matched");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = bigStakeConfigurationPage.navigateTopGainersTopLosersPage();
        log("Step 2. Filter Exchange product that have the date has data");
        String fromDate = DateUtils.getDate(-60, "yyyy-MM-dd", AGConstant.timeZone);
        String toDate = DateUtils.getDate(0, "yyyy-MM-dd", AGConstant.timeZone);
        page.search(fromDate,toDate,"Exchange");
        log("Verify 1. Validate  Big Stake  table display correct data");
        Assert.assertTrue(page.topGainersTopLosers.isCheckUserDisplayInBigStakeTableCorrect(bigStake),"FAILED! Data of Big Stake displayed incorrect!");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3760")
    @Test(groups = {"regression"})
    public void Agent_Report_Top_Gainers_Top_Losers_3760() {
        log("@title: Validate Top Gainers data display correctly");
        log("@pre-condition 1: Log in successfully by SAD");
        log("@pre-condition 2: Go to Win Loss Simple > Search a date range that has the account has placed a bet and bets are settled for Exchange product only (not including Fancy or Bookmaker)");
        WinLossSimplePage winLossSimplePage = agentHomePage.navigateWinLossSimplePage();
        String fromDate = DateUtils.getDate(-60, "yyyy-MM-dd", AGConstant.timeZone);
        String toDate = DateUtils.getDate(0, "yyyy-MM-dd", AGConstant.timeZone);
        winLossSimplePage.dpFrom.selectDate(fromDate,"yyyy-MM-dd");
        winLossSimplePage.dpTo.selectDate(toDate,"yyyy-MM-dd");
        winLossSimplePage.btnSubmit.click();
        log("@pre-condition 3: Get Win/Loss and Total tax/comm of any player has settled Lose < 0: for example Win/Loss: -0.90, 0.00, and Total tax/comm");
        List<ArrayList<String>> losers = winLossSimplePage.winLossSimple.getListSMALoserInfor();
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = winLossSimplePage.navigateTopGainersTopLosersPage();
        log("Step 2. Filter Exchange product that have the date has data");
        page.search(fromDate,toDate,"Exchange");
        log("Verify 1. Validate Top Gainers table display correct data");
        Assert.assertTrue(page.topGainersTopLosers.isCheckUserDisplayInTopLosersTableCorrect(losers),"FAILED! Data of Top Losers displayed incorrect!");
        log("INFO: Executed completely");
    }

}
