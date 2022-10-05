package backoffice.testcases.tools;

import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.tools.MarketBetListPage;
import baseTest.BaseCaseMerito;
import backoffice.utils.tools.MarketBetListUltils;

import java.util.ArrayList;
import java.util.List;

public class MarketBetListTest extends BaseCaseMerito{

    /**
     * @title: Validate can search market bet list info
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Market Bet List
     *          2. Search the market id that has bet in precondition
     * @expect: 1. Verify market Info display correctly:
     *          Sport, Competition, Event, Event Date, Market
     *          2. The market bet list table contains the wager in precondition
     */
    @Test (groups = {"smoke1"})
    public void BO_Tools_Market_Bet_List_001(){
        log("@title: Validate can search market bet list info");
        //TODO: Get market id that have bet in agent site using api in Analysis of Running Markets
        log("Step 1. Access Tool > Market Bet List");
        String marketId = "175328912";
        List<String> lstInfo = MarketBetListUltils.viewMarketInfo(marketId);
        List<ArrayList<String>> lstMarketInfo = MarketBetListUltils.viewMarketBetList(marketId);
        MarketBetListPage page = backofficeHomePage.navigateMarketBetList();

        log("Step 2. Search the market id that has bet in precondition");
        page.viewMarketBetList(marketId);

        log("Verify 1. Verify market Info display correctly: Sport, Competition, Event, Event Date, Market");
        Assert.assertEquals(lstInfo,page.tblMarketInfo.getColumn(1,false),"FAILED! Market info is incorrect");

        log("Verify 2. The market bet list table contains the wager in precondition");
        List<ArrayList<String>> lstMarketInfoActual = page.tblMarketBetList.getRowsWithoutHeader(20,false);
        Assert.assertEquals(lstMarketInfo,lstMarketInfoActual,"FAILED! Market bet List not display as expected");
        log("INFO: Executed completely");
    }



}
