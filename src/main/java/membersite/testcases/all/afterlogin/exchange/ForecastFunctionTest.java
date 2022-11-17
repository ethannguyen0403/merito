package membersite.testcases.all.afterlogin.exchange;

import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ForecastFunctionTest extends BaseCaseMerito {

    /**
     * @title: Validate that user can place Matched Back bet on Soccer market
     * @Precondition:  1. Login member site
     * @steps:      1. Active any market of Soccer
     *              2. Place a matched back bet
     * @expect: 1. Liability display correct
     *          2. Verify forecast display correct on the selection has bet placed correct:
     *           -  Display profit for under placed selection
     *          - Display liability of the bet under other selections
     */
    @TestRails(id="581")
    @Test(groups = {"smoke"})
    public void ForecastFunction_TC001(){
        log("@title: Validate that user can place Matched Back bet on Soccer market");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        log("Step 2. Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);

        log("Step 3.Cancel all unmatched bet and click on Back Odds");
        int selectionIndex = page.marketContainerControl.getSelectionHaveMinOdds(true);
        Market market = page.marketContainerControl.getMarket(event,selectionIndex,true);
        log("Cancel unmatched if having");
        page.myBetControl.cancelAllBetUnmatched();
        String odds = market.getBtnOdd().getText();
        market.getBtnOdd().click();

        log("Step 4. Input stake and click submit");
        page.betSlipControl.placeBet(odds, minBet);

        List<ArrayList<String>> lstExpectedForecast = page.calculateForecastFromAPIWager();
        List<ArrayList<String>> lstactualForeCast = page.marketContainerControl.getUIForeCast();

        log("Verified. Verify forecast display correct on the selection has bet placed correct");
        Assert.assertEquals(lstactualForeCast,lstExpectedForecast,"FAILED! Forecast display incorrectly");

        log("INFO: Executed completely");

    }
    /**
     * @title: Validate forecast/ liability value display correctly when place back and Lay bet on a selection
     * @Precondition:  1. Login member site
     * @steps:          1. Active any market of Soccer
     *                  2. Place a matched back and Lay bet on the same selection
     * @expect:         1 . Liability of the market is correctly: Forecast = Profit(Back bet) - Liability(Lay bet). if forecast>=0 => liability = 0, else: liability = forecast
     *                  2. Verify forecast display correct on the selection has bet placed correct:
     *                  - Display liability under placed selection
     *                  - Display Profit(back -lay) for other selection
     */
    @TestRails(id = "582")
    @Test(groups = {"smoke"})
    public void ForecastFunction_TC002(){
        log("@title:  Validate forecast/ liability value display correctly when place back and Lay bet on a selection");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        log("Step 2. Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 3 Get Back and Lay odds on a selection");
        int selectionIndex = page.marketContainerControl.getSelectionHaveMinOdds(false);
        Market market = page.marketContainerControl.getMarket(event,selectionIndex,true);
        Market market2 = page.marketContainerControl.getMarket(event,selectionIndex,false);
       // List<String> foreCastBeforePlace = page.marketContainerControl.getForeCast();

        // Calulate profit and lost of back bet
        String odds = market.getBtnOdd().getText();
        String expectedProfit = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));

        log(String.format("Step 4 Click Odds and place on Back bet of selection  %s",market.getSelectionName()));
        market.getBtnOdd().click();
        page.betSlipControl.placeBet(odds, minBet);

        String odds2 =  market2.getBtnOdd().getText();
        String expectedLiability = String.format("%.2f",(Double.parseDouble(odds2)-1)*Double.parseDouble(minBet));
        log(String.format("Step 5 Click Odds and place on Lay bet of selection %s",market.getSelectionName()));
        market2.getBtnOdd().click();
        page.betSlipControl.placeBet(odds2, minBet);

        log("Verified. Verify forecast display correct on the selection has bet placed correct");
        String profit =String.format( "%.2f",Double.parseDouble(expectedProfit) -  Double.parseDouble(expectedLiability));
       /* List<String> actualForeCast = page.marketContainerControl.getForeCast();
        List<String> expectedForeCast = page.marketContainerControl.calculateForecast(foreCastBeforePlace,selectionIndex,true,profit,"0");*/
        List<ArrayList<String>> lstExpectedForecast = page.calculateForecastFromAPIWager();
        List<ArrayList<String>> lstactualForeCast = page.marketContainerControl.getUIForeCast();
        Assert.assertEquals(lstactualForeCast,lstExpectedForecast,"FAILED! Forecast display incorrectly");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate forecast/ liability value display correctly when place Lay bet on a selection
     * @Precondition:  1. Login member site
     * @steps:      1. Click Soccer menu
     *              2. Click on an event
     *              3. Click on an Lay odds without empty of the selection have the higher potential win
     *              4. Input stake and click submit
     * @expect:     1. Odd rate on My Bet and on Bet Slip is the same
     *              2. Market name on My Bet and on Bet Slip is the same
     *              3. Event name on My Bet and on Bet Slip is the same
     *              4. Selected team on My Bet and on Bet Slip is the same
     *              5. Liability on My Bet and on Bet Slip is the same
     *              6. Profit on My Bet and on Bet Slip is the same
     */
    @TestRails(id = "583")
    @Test(groups = {"smoke"})
    public void ForecastFunction_TC012(){
        log("@title: Validate forecast/ liability value display correctly when place Lay bet on a selection");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        log("Step 2. Click on an event");
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 3. Update odds > offer odds and Input valid stake");
        int selectionIndex = page.marketContainerControl.getSelectionHaveMinOdds(false);
        Market market = page.marketContainerControl.getMarket(event,selectionIndex,false);

        String odds = market.getBtnOdd().getText();
        //String expectedLiability = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));
        market.getBtnOdd().click();

        log("Step 4. Input stake and click submit");
        page.betSlipControl.placeBet(odds, minBet);
        List<ArrayList<String>> lstExpectedForecast = page.calculateForecastFromAPIWager();
        List<ArrayList<String>> lstactualForeCast = page.marketContainerControl.getUIForeCast();

       /* List<String> actualForeCast = page.marketContainerControl.getForeCast();
        List<String> expectedForeCast = page.marketContainerControl.calculateForecast(actualForeCast,selectionIndex,false,odds,expectedLiability);*/
        Assert.assertEquals(lstactualForeCast,lstExpectedForecast,"FAILED! Forecast display incorrectly");

        log("INFO: Executed completely");

    }

}
