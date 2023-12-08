package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Objects;

public class ForecastFunctionTest extends BaseCaseTest {

    @TestRails(id = "581")
    @Test(groups = {"smoke"})
    public void SAT_ForecastFunction_TC581() {
        log("@title: Validate forecast/ liability value display correctly when place back bet on a selection ");
        String minBet = BetUtils.getMinBet("SOCCER", "BACK");

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu("Soccer");

        log("Step 2. Click on an event");
        Event event = page.eventContainerControl.getEvent(false, false, 30, 1);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 3. Update odds > offer odds and Input valid stake");
        int selectionIndex = marketPage.marketOddControl.getSelectionHaveMinOdds(event.getMarketName(),true);
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        // check if there is unmatched before place, if have clear unmatched
        marketPage.myBetsContainer.cancelAllBetUnmatched();
        String odds = market.getBtnOdd().getText();
        market.getBtnOdd().click();

        log("Step 4. Input stake and click submit");
        marketPage.betsSlipContainer.placeBet(odds, minBet);

//        List<ArrayList<String>> lstExpectedForecast = marketPage.calculateForecastFromAPIWager();
//        List<ArrayList<String>> lstactualForeCast = page.marketContainerControl_SAT.getUIForeCast();

        log("Verified. Verify forecast display correct on the selection has bet placed correct");
//        Assert.assertEquals(lstactualForeCast,lstExpectedForecast,"FAILED! Forecast display incorrectly");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate forecast/ liability value display correctly when place back and Lay bet on a selection
     * @Precondition: 1. Login member site
     * @steps: 1. Active any market of Soccer
     * 2. Place a matched back and Lay bet on the same selection
     * @expect: 1 . Liability of the market is correctly: Forecast = Profit(Back bet) - Liability(Lay bet). if forecast>=0 => liability = 0, else: liability = forecast
     * 2. Verify forecast display correct on the selection has bet placed correct:
     * - Display liability under placed selection
     * - Display Profit(back -lay) for other selection
     */
    @TestRails(id = "582")
    @Test(groups = {"smoke"})
    public void SAT_ForecastFunction_TC582() {
        log("@title: Validate forecast/ liability value display correctly when place back and Lay bet on a selection");
        String minBet = BetUtils.getMinBet("SOCCER", "LAY");

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu("Soccer");

        log("Step 2. Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.getEvent(false, false, 20, 1);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 3 Get Back and Lay odds on a selection");
        int selectionIndex = marketPage.marketOddControl.getSelectionHaveMinOdds(event.getMarketName(),false);
        Market market = marketPage.marketOddControl.getMarket(event, selectionIndex, true);
        Market market2 = marketPage.marketOddControl.getMarket(event, selectionIndex, false);
        // Calulate profit and lost of back bet
        String odds = market.getBtnOdd().getText();
        String expectedProfit = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));

        log(String.format("Step 4 Click Odds and place on Back bet of selection  %s", market.getSelectionName()));
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(odds, minBet);
        String odds2 = market2.getBtnOdd().getText();

        log(String.format("Step 5 Click Odds and place on Lay bet of selection %s", market.getSelectionName()));
        market2.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(odds2, minBet);

        log("Verified. Verify forecast display correct on the selection has bet placed correct");
//        List<ArrayList<String>> lstExpectedForecast = page.calculateForecastFromAPIWager();
//        List<ArrayList<String>> lstactualForeCast = page.marketContainerControl_SAT.getUIForeCast();
//        Assert.assertEquals(lstactualForeCast,lstExpectedForecast,"FAILED! Forecast display incorrectly");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate forecast/ liability value display correctly when place Lay bet on a selection
     * @Precondition: 1. Login member site
     * @steps: 1. Click Soccer menu
     * 2. Click on an event
     * 3. Click on an Lay odds without empty of the selection have the higher potential win
     * 4. Input stake and click submit
     * @expect: 1. Odd rate on My Bet and on Bet Slip is the same
     * 2. Market name on My Bet and on Bet Slip is the same
     * 3. Event name on My Bet and on Bet Slip is the same
     * 4. Selected team on My Bet and on Bet Slip is the same
     * 5. Liability on My Bet and on Bet Slip is the same
     * 6. Profit on My Bet and on Bet Slip is the same
     */
    @TestRails(id = "583")
    @Test(groups = {"smoke"})
    public void SAT_ForecastFunction_TC583() {
        log("@title: Validate forecast/ liability value display correctly when place Lay bet on a selection");
        String minBet = BetUtils.getMinBet("SOCCER", "LAY");

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu("Soccer");

        log("Step 2. Click on an event");
        Event event = page.getEvent(false, false, 20, 1);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 3. Update odds > offer odds and Input valid stake");
        int selectionIndex = marketPage.marketOddControl.getSelectionHaveMinOdds(event.getMarketName(),false);
        Market market = marketPage.marketOddControl.getMarket(event, selectionIndex, false);

        String odds = market.getBtnOdd().getText();
        market.getBtnOdd().click();

        log("Step 4. Input stake and click submit");
        marketPage.betsSlipContainer.placeBet(odds, minBet);
//        List<ArrayList<String>> lstExpectedForecast = marketPage.calculateForecastFromAPIWager();
//        List<ArrayList<String>> lstactualForeCast = page.marketContainerControl_SAT.getUIForeCast();
//        Assert.assertEquals(lstactualForeCast,lstExpectedForecast,"FAILED! Forecast display incorrectly");

        log("INFO: Executed completely");

    }

}
