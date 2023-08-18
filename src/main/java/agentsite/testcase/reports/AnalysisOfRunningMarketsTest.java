package agentsite.testcase.reports;

import agentsite.pages.report.AnalysisOfRunningMarketsPage;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;


public class AnalysisOfRunningMarketsTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Analysis of Running Markets
     * @expect: 1. There is no http responded error returned
     */
    @TestRails(id = "822")
    @Test(groups = {"http_request"})
    public void Agent_Report_Analysis_Of_Running_Markets_822() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Analysis of Running Markets");
        AnalysisOfRunningMarketsPage page = agentHomePage.navigateAnalysisOfRunningMarketsPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    @TestRails(id = "823")
    @Test(groups = {"smoke_po"})
    public void Agent_Report_Analysis_Of_Running_Markets_823() {
        log("@title: Validate Analysis of Running Markets display correctly ");
        log("Step 1. Navigate Report> Analysis of Running Markets");
        AnalysisOfRunningMarketsPage page = agentHomePage.navigateAnalysisOfRunningMarketsPage();

        log("Verify 1. Verify Analysis of Running Markets UI display correctly");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND, "FAILED! Incorrect no record message");
            return;
        }
        List<String> lstSport = page.getSport();
        page.expandSport(lstSport.get(0));
        Assert.assertTrue(page.isListEventDisplay(lstSport.get(0)), "FAILED! Sport table is NOT display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "824")
    @Test(groups = {"smoke_po"})
    public void Agent_Report_Analysis_Of_Running_Markets_824() {
        log("@title: Market info display correctly");
        log("Step 1. Navigate Report >Analysis of Running Markets");
        AnalysisOfRunningMarketsPage page = agentHomePage.navigateAnalysisOfRunningMarketsPage();

        log("Step 2. Select a sport and click on the event");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND, "FAILED! Incorrect no record message");
            return;
        }
        String sportName = page.getSport().get(0);
        page.expandSport(sportName);
        List<ArrayList<String>> lsMarket = page.getMarketInfo(sportName);
        String competitionEventName = lsMarket.get(0).get(0);
        String marketName = lsMarket.get(0).get(1);
        String eventStarttime = lsMarket.get(0).get(2);
        page.openMarketInfo(sportName, competitionEventName, marketName);

        log("Verify 1. Market title display correct include competition, event name ,event start time, market name");
        int downlineNumber = page.tblDownline.getRowsWithoutHeader(true).size();
        String expecteMarketInfoTitle = String.format("%s (%s) | %s | Total %s Clients Are Working", competitionEventName, eventStarttime, marketName, downlineNumber);
        Assert.assertTrue(page.tblMarketInfo.getColumnNamesOfTable().get(0).contains(expecteMarketInfoTitle), "FAILED! Market title is incorrect");

        log("INFO: Executed completely");
    }

}
