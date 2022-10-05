package agentsite.testcase.all.riskmanagement;

import agentsite.common.AGConstant;
import baseTest.BaseCaseMerito;
import org.testng.Assert;
import org.testng.annotations.Test;
import agentsite.pages.all.report.AnalysisOfRunningMarketsPage;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.ANALYSIS_OF_RUNNING_MARKETS;
import static agentsite.common.AGConstant.HomePage.REPORT;


public class AnalysisOfRunningMarketsTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Analysis of Running Markets
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Analysis_Of_Running_Markets_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Analysis of Running Markets");
        AnalysisOfRunningMarketsPage page = agentHomePage.clickSubMenu(REPORT, ANALYSIS_OF_RUNNING_MARKETS, AnalysisOfRunningMarketsPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Analysis of Running Markets display correctly
     * @pre-condition:
     *           1. Log in successfully by PO level
     * @steps: 1. Navigate Report> Analysis of Running Markets
     * @expect: 1. Verify Analysis of Running Markets UI display correctly
     */
    @Test (groups = {"smokePO","smoke"})
    public void Agent_Report_Analysis_Of_Running_Markets_002(){
        log("@title: Validate Analysis of Running Markets display correctly ");
        log("Step 1. Navigate Report> Analysis of Running Markets");
        AnalysisOfRunningMarketsPage page = agentHomePage.clickSubMenu(REPORT, ANALYSIS_OF_RUNNING_MARKETS, AnalysisOfRunningMarketsPage.class);

        log("Verify 1. Verify Analysis of Running Markets UI display correctly");
        if(page.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! Incorrect no record message");
            return;
        }
        List<String> lstSport = page.getSport();
        page.expandSport(lstSport.get(0));
        Assert.assertTrue(page.isListEventDisplay(lstSport.get(0)),"FAILED! Sport table is NOT display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Market info display correctly
     * @pre-condition:
     *           1. Log in successfully by PO level
     * @steps: 1. Navigate Report >Analysis of Running Markets
     *          2. Select a sport and click on the event
     ** @expect: 1. Market title display correct include competition, event name ,event start time, market name
     */
    @Test (groups = {"smokePO","smoke"})
    public void Agent_Report_Analysis_Of_Running_Markets_003(){
        log("@title: Can drill down and open bet list");
        log("Step 1. Navigate Report >Analysis of Running Markets");
        AnalysisOfRunningMarketsPage page = agentHomePage.clickSubMenu(REPORT, ANALYSIS_OF_RUNNING_MARKETS, AnalysisOfRunningMarketsPage.class);

        log("Step 2. Select a sport and click on the event");
        if(page.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! Incorrect no record message");
            return;
        }
        String sportName = page.getSport().get(0);
        page.expandSport(sportName);
        List<ArrayList<String>> lsMarket = page.getMarketInfo(sportName);
        String competitionEventName = lsMarket.get(0).get(0);
        String marketName = lsMarket.get(0).get(1);
        String eventStarttime = lsMarket.get(0).get(2);
        page.openMarketInfo(sportName,competitionEventName,marketName);

        log("Verify 1. Market title display correct include competition, event name ,event start time, market name");
       int downlineNumber = page.tblDownline.getRowsWithoutHeader(true).size();
        String expecteMarketInfoTitle = String.format("%s (%s) | %s | Total %s Clients Are Working",competitionEventName,eventStarttime,marketName, downlineNumber);
        Assert.assertTrue(page.tblMarketInfo.getColumnNamesOfTable().get(0).contains(expecteMarketInfoTitle),"FAILED! Market title is incorrect");

        log("INFO: Executed completely");
    }

}
