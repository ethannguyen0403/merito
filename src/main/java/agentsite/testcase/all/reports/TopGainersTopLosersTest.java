package agentsite.testcase.all.reports;

import com.paltech.utils.DateUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.TopGainersTopLosersPage;
import agentsite.ultils.report.TopGainerLoserUtils;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.TOP_GAINER_TOP_LOSER;

public class TopGainersTopLosersTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Transaction history
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Top_Gainers_Top_Losers_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Transaction history");
        agentHomePage.clickSubMenu(REPORT, TOP_GAINER_TOP_LOSER, TopGainersTopLosersPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Top Gainers & Top Losers UI display correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Top Gainers & Top Losers
     * @expect: 1. Verify Top Gainers & Top Losers UI display correctly
     */
    @Test (groups = {"smoke"})
    public void  Agent_Report_Top_Gainers_Top_Losers_002 (){
        log("@title: Validate Top Gainers & Top Losers UI display correctly ");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = agentHomePage.clickSubMenu(REPORT, TOP_GAINER_TOP_LOSER, TopGainersTopLosersPage.class);
        log("Verify 1. Verify Top Gainers & Top Losers UI display correctly");
        String today = DateUtils.getDate(0,"dd/MM/yyyy", AGConstant.timeZone);
        Assert.assertEquals(page.txtSearchFrom.getAttribute("value"),today,"FAILED! Incorrect default date in search from textbox");
        Assert.assertEquals(page.txtSearchTo.getAttribute("value"),today,"FAILED! Incorrect default date in search to textbox");
        Assert.assertEquals(page.ddbProduct.getFirstSelectedOption(),"Exchange", "FAILED! Default product is incorrect");

        Assert.assertEquals(page.btnSearch.getText().trim(), AGConstant.Report.TopGainersTopLosers.BTN_SEARCH, "FAILED! Search button text is incorrect ");
        Assert.assertEquals(page.lblInfoReportRangeAvailable.getText(), AGConstant.Report.TopGainersTopLosers.LBL_INFO_REPORT_VALID,String.format("FAILED! Expected label text is %s but found %s", AGConstant.Report.TopGainersTopLosers.LBL_INFO_REPORT_VALID,page.lblInfoReportRangeAvailable.getText()));
        Assert.assertEquals(page.lblInfoDataSupport.getText(), AGConstant.Report.TopGainersTopLosers.LBL_INFO_SUPPORT_YESTERDAY_DATA,String.format("FAILED! Expected label text is %s but found %s", AGConstant.Report.TopGainersTopLosers.LBL_INFO_SUPPORT_YESTERDAY_DATA,page.lblInfoDataSupport.getText()));
        Assert.assertEquals(page.lblInfoReportPlaceTime.getText(), AGConstant.Report.TopGainersTopLosers.LBL_INFO_PLACE_TIME,String.format("FAILED! Expected label text is %s but found %s", AGConstant.Report.TopGainersTopLosers.LBL_INFO_PLACE_TIME,page.lblInfoReportPlaceTime.getText()));

        Assert.assertEquals(page.tblTopGainers.getHeaderNameOfRows(), AGConstant.Report.TopGainersTopLosers.TABLE_TOP_GAINERS_HEADER,"FAILED! Incorrect header of top gainers");
        Assert.assertEquals(page.tblTopLoser.getHeaderNameOfRows(), AGConstant.Report.TopGainersTopLosers.TABLE_TOP_LOSERS_HEADER,"FAILED! Incorrect header of top loser");
        Assert.assertEquals(page.tblBigStake.getHeaderNameOfRows(), AGConstant.Report.TopGainersTopLosers.TABLE_BIG_STAKE_HEADER,"FAILED! Incorrect header of big stake");

        log("INFO: Executed completely");
    }
    @Test (groups = {"regression"})
    public void  Agent_Report_Top_Gainers_Top_Losers_003 (){
        log("@title: Validate Top Gainer data display correctly");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        int bigStake = TopGainerLoserUtils.getBigStake();
        TopGainersTopLosersPage page = agentHomePage.clickSubMenu(REPORT, TOP_GAINER_TOP_LOSER, TopGainersTopLosersPage.class);
        log("Step 2. Filter Exchange product that have the date has data");
        log("Verify 1. Verify Top Gainers table display correct data");

        log("INFO: Executed completely");
    }
    @Test (groups = {"smoke"})
    public void  Agent_Report_Top_Gainers_Top_Losers_004 (){
        log("@title: Validate Top Gainers & Top Losers UI display correctly");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = agentHomePage.clickSubMenu(REPORT, TOP_GAINER_TOP_LOSER, TopGainersTopLosersPage.class);
        log("Step 2. Filter Exchange product that have the date has data");
        log("Verify 1. Verify Big Stake table display correct data");

        log("INFO: Executed completely");
    }

    @Test (groups = {"smoke"})
    public void  Agent_Report_Top_Gainers_Top_Losers_005 (){
        log("@title: Validate Top Losers  data display correctly");
        log("Step 1. Navigate Report > Top Gainers & Top Losers");
        TopGainersTopLosersPage page = agentHomePage.clickSubMenu(REPORT, TOP_GAINER_TOP_LOSER, TopGainersTopLosersPage.class);
        log("Step 2. Filter Exchange product that have the date has data");
        log("Verify 1. Verify Big Stake table display correct data");

        log("INFO: Executed completely");
    }
}
