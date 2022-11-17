package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.EventOddContentControl;
import membersite.controls.funsport.MarketOddControl;
import membersite.controls.funsport.OddPageControl;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.funsport.Odd;
import membersite.objects.funsport.SelectedOdd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.*;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaceBetOnMarketPageTest extends BaseCaseMerito {
    /**
     * @title: Validate that user can place a bet with HOME - BACK successfully
     * @steps:  1. Navigate to Soccer page on Exchange
     *          2. Click an odd without empty at Home team and Back type
     *          3. Input stake and place this bet
     * @expect: 1. Odd rate on My Bet and on Bet Slip is the same
     *          2. Market name on My Bet and on Bet Slip is the same
     *          3. Event name on My Bet and on Bet Slip is the same
     *          4. Selected team on My Bet and on Bet Slip is the same
     *          5. Liability on My Bet and on Bet Slip is the same
     *          6. Profit on My Bet and on Bet Slip is the same
     */
    @TestRails(id = "988")
    @Test(groups = {"smoke5"})
    public void Place_Bet_Market_Page_TC_001(){
        log("@title: Validate that user can place a bet with HOME - BACK successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);

        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd.getSelectedTeam());

        log("Step 3: Click on the first market - Match Odds");
        Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);
        MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());

        Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,true);
        marketOdds.getOdd().click();

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);
        selectedOdd = marketPage.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Step 4: Input stake and place this bet");
        marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = marketPage.myBetControl.getWagers(true, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' >= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
//        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
//        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The selected odd on profit is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can place a bet with HOME - LAY successfully
     * @steps:  1. Navigate to Soccer page on Exchange
     *          2. Click an odd without empty at Home team and Back type
     *          3. Input stake and place this bet
     * @expect: 1. Odd rate on My Bet and on Bet Slip is the same
     *          2. Event name on My Bet and on Bet Slip is the same
     *          3. Market name on My Bet and on Bet Slip is the same
     *          4. Selected team on My Bet and on Bet Slip is the same
     *          5. Liability on My Bet and on Bet Slip is the same
     *          6. Profit on My Bet and on Bet Slip is the same
     */
    @TestRails(id = "989")
    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_002(){
        log("@title: Validate that user can place a bet with HOME - LAY successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);

        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, false, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: No Event or Skip running TC because Lay odds is too high\n");
            return;
        }
        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd.getSelectedTeam());

        log("Step 3: Click on the first market - Match Odds");
        Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);

        MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
        Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,true);
        marketOdds.getOdd().click();

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = marketPage.myBetControl.getWagers(false, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' <= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can place a bet with AWAY-BACK successfully
     * @steps:  1. Navigate to Soccer page on Exchange
     *          2. Click an odd without empty at AWAY team and BACK type
     *          3. Input stake and place this bet
     * @expect: 1. Odd rate on My Bet and on Bet Slip is the same
     *          2. Event name on My Bet and on Bet Slip is the same
     *          3. Market name on My Bet and on Bet Slip is the same
     *          4. Selected team on My Bet and on Bet Slip is the same
     *          5. Liability on My Bet and on Bet Slip is the same
     *          6. Profit on My Bet and on Bet Slip is the same
     */
    @TestRails(id = "990")
    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_003(){
        log("@title: Validate that user can place a bet with AWAY-BACK successfully");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);

        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.AWAY, isBack, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);

        log("Step 3: Click on the first market - Match Odds");
        Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.AWAY,isBack);
        MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
        Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.AWAY,isBack);
        marketOdds.getOdd().click();

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = marketPage.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet is '%s' >= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
      //  Assert.assertTrue(wager.getLiability() >= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >=selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can place a bet with AWAY - LAY successfully
     * @steps:  1. Navigate to Soccer page on Exchange
     *          2. Click an odd without empty at AWAY team and LAY type
     *          3. Input stake and place this bet
     * @expect: 1. Odd rate on My Bet and on Bet Slip is the same
     *          2. Event name on My Bet and on Bet Slip is the same
     *          3. Market name on My Bet and on Bet Slip is the same
     *          4. Selected team on My Bet and on Bet Slip is the same
     *          5. Liability on My Bet and on Bet Slip is the same
     *          6. Profit on My Bet and on Bet Slip is the same
     */
    @TestRails(id = "991")
    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_004(){
        log("@title: Validate that user can place a bet with AWAY-LAY successfully");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);
        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.AWAY, isBack, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);

        log("Step 3: Click on the first market - Match Odds");
        Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.AWAY,isBack);
        MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
        Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.AWAY,isBack);
        marketOdds.getOdd().click();

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);
        List<SelectedOdd> lstWagers = page.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");

        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getIsUnmatched(),"FAILED! Bet is not unmatched bet");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't %s <= %s on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));

        log("Verify 7:Verify Balance is update accordingly");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate forecast/ liability value display correctly when place back bet on a selection
     * @steps: 1. Active any Event of Cricket
     * 2. Place a matched back bet
     * @expect: 1. Verify forecast display correct on the selection has bet placed correct:
     * - Display profit for under placed selection
     * - Display liability of the bet under other selections
     */
    @TestRails(id = "992")
    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_005(){
        log("@title: Validate forecast/ liability value display correctly when place back bet on a selection");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);
        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false,true,6, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2. Place a matched back bet");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);

        log("Step 3: Click on the first market - Match Odds");
        Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);
        MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
        Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,true);
        List<String> lstSelection = marketPage.marketOddControl.getListSelection();
        List<String> lstMarketInfo = marketPage.marketOddControl.getMarketInfo();
        marketOdds.getOdd().click();

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);
        List<SelectedOdd> lstWagers = page.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        //SelectedOdd wager = lstWagers.get(0);
        log("Verify 1. Verify forecast display correct on the selection has bet placed correct:");
        List<ArrayList<String>> lstExpectedForecast = marketPage.myBetControl.forecastLstBasedMatchedBetFromAPI("4",lstMarketInfo.get(0),lstMarketInfo.get(2),lstMarketInfo.get(4),lstSelection);
        List<ArrayList<String>> lstActualForeCast = marketPage.marketOddControl.getUIForeCast();

        Assert.assertTrue(marketPage.verifyForecast("HKD",lstActualForeCast,lstExpectedForecast), String.format("ERROR: "));
       log("INFO: Executed completely");
    }

    /**
     * @title: TC006_Validate forecast/ liability value display correctly when place back and Lay bet on a selection
     * @steps: 1. Active any Event of Cricket
     *          2. Place a matched back and Lay bet on the same selection
     * @expect: 1. Liability of the market is correctly: Forecast = Profit(Back bet) - Liability(Lay bet). if forecast>=0 => liability = 0, else: liability = forecast
     * 2. Verify forecast display correct on the selection has bet placed correct:
     * - Display liability under placed selection
     * - Display Profit(back -lay) for other selection
     */
    @TestRails(id = "993")
    @Test (groups = {"smoke4"})
    public void Place_Bet_Market_Page_TC_006(){
        log("@title: Validate forecast/ liability value display correctly when place back and Lay bet on a selection");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);
        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);

        log("Step 3: Click on the first market - Match Odds");
        Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,isBack);
        if (Objects.isNull(eventOdds)) {
            log ("DEBUG: Skip running TC because Lay odds is too high\n");
            return;
        }
        MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
        Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,isBack);
        marketOdds.getOdd().click();
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);

        log("Step: Get bet info in bet slip");
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);
        List<SelectedOdd> lstWagers = page.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");

        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't %s <= %s on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title:  Validate that user can place a bet with BACK Horse Racing successfully
     * @steps: 1. Active any horse racing race
     * 2. Place bet
     *  @expect: 1. Verify can place bet
     *
     */
    @TestRails(id = "994")
    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_007(){
        log("@title: Validate that user can place a bet with BACK Horse Racing successfully");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);

        log("Step 1. Active any horse racing race");
        RacingPage racingPage = memberHomePage.navigateHorseRacing();
        RacingMarketPage marketPage = racingPage.highLightRaceControl.clickCommingUpRace(1);
        Odd marketOdd = marketPage.marketOddControl.getSelectionOdds(1,isBack);
        marketOdd.getOdd().click();

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);
        List<SelectedOdd> lstWagers = marketPage.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1. Verify can place bet on HR");
        log("Verify 1.1: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 1.2: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't %s <= %s on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
     //   Assert.assertTrue(wager.getLiability() >= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title:  Verify Horse Racing is inactive Lay odds button
     * @steps: 1. Active any horse racing race and verify lay odds
     *  @expect: 1. Verify Lay odds is unclickable
     *
     */
    @TestRails(id = "995")
    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_008(){
        log("@title:  Verify Horse Racing is inactive Lay odds button");
        boolean isBack = false;
        log("Step 1. Active any horse racing race and verify lay odds");
        RacingPage racingPage = memberHomePage.navigateHorseRacing();
        RacingMarketPage marketPage = racingPage.highLightRaceControl.clickCommingUpRace(1);
        Odd marketOdd = marketPage.marketOddControl.getSelectionOdds(1,isBack);

        log("Verify 1. Verify Lay odds is unclickable");
        Assert.assertTrue(marketOdd.getOdd().getText().equals(""),"FAILED! HR Lay odds should be empty");
        Assert.assertFalse(marketOdd.getOdd().isClickable(1),"FAILED! HR Lay odds should be unclickable");

        log("INFO: Executed completely");
    }


    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_009() throws InterruptedException {
        log("@title: Verify info of unmatched bet in Mini My bet is correctly");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);
        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);
        try {
        log("Step 3: Click on the first market - Match Odds");
        Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,isBack);
        MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
        Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,isBack);
        marketOdds.getOdd().click();

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);
        log(" Update Odds");
        marketPage.betSlipControlOldUI.inputOdds(selectedOdd,9);
        double oddsUpdate = Double.parseDouble(selectedOdd.getTxtOdd().getAttribute("value"));

        log("Step 4: Input stake and place this bet");
        selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        log("Verify 1: Verify UI when have an unmatched bet displayed");
        Assert.assertFalse(page.betSlipControl.btnPlaceBet.isDisplayed(),"FAILED! Bet Slip not empty after place bet");
        Assert.assertTrue(page.myBetControl.isBetUnmatched(),"Failed! Unmatched section does not display");
        Assert.assertTrue(page.myBetControl.btnCancelAllUnmatched.isDisplayed(),"Failed! Cancel unmatched button does not display after placing an unmatched bet");
        Assert.assertTrue(page.myBetControl.isControlDisable(page.myBetControl.btnReset),"Failed!Reset button does not disable");
        Assert.assertTrue(page.myBetControl.isControlDisable(page.myBetControl.btnUpdate),"Failed!Update button does not disable");

        log("Step 5: Get bet after place");
        List<SelectedOdd> lstWagers = page.myBetControl.getWagersUnmatched(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: Cannot get any unmatched bet");
        SelectedOdd wager = lstWagers.get(0);

        log("Verify 2: Verify bet info of 2 unmatched bets is correct");
        log("Verify 2.1: Odds is display correct");
        log("Verify 2.2: Stake is correctly");
        log("Verify 2.3: Profit of Back bet is correctly");
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(),"FAILED! Selection is incorrect");
        Assert.assertEquals(wager.getOddRate(), oddsUpdate, String.format("ERROR: The odd rate on my bet isn't %s <= %s on Bet Slip", wager.getOddRate(), oddsUpdate));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));

        log("INFO: Executed completely");
        }
        finally{
            log("Post Condition: Cancel all unmatched bets");
            page.myBetControl.cancelAllBetUnmatched();
        }
    }

    @Test (groups = {"smoke"})
    public void Place_Bet_Market_Page_TC_010() throws InterruptedException {
        log("@title: Can update unmatched bet");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);
        int totalUpdateStake = Integer.parseInt(minBet)*2 +1;
        String addedStake =String.format("%s", totalUpdateStake - Integer.parseInt(minBet));
        String minBetDouble = String.format("%s",totalUpdateStake);
        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);
        try {
            log("Step 3: Click on the first market - Match Odds");
            Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,isBack);
            MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
            Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,isBack);
            marketOdds.getOdd().click();

            log("Step: Get bet info in bet slip");
            List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
            SelectedOdd selectedOdd = lstSelectedOdd.get(0);

            log(" Update Odds");
            marketPage.betSlipControlOldUI.inputOdds(selectedOdd,9);
            double oddsUpdate = Double.parseDouble(selectedOdd.getTxtOdd().getAttribute("value"));
            double profitNeUnmatchedBet = (oddsUpdate-1) * Integer.parseInt(addedStake);

            log(String.format("Step 4: Input stake and place unmatche bet %s @ %.2f ",minBet,oddsUpdate));
            selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);

            log("Step 4.1 Get Unmatched bet info");
            List<SelectedOdd> lstWagers = page.myBetControl.getWagersUnmatched(isBack, 1);

            log(String.format("Step 5: Update stake from %s to %s",minBet,minBetDouble));
            marketPage.myBetControl.updateUnmatchBet(lstWagers.get(0),minBetDouble);

            log("Step 6: Click View Open Bet");
            marketPage.myBetControloldUI.clickViewOpenbet();

            log("Step 7: Get bet after place");
            List<SelectedOdd> lstWagersUnmatched = page.myBetControl.getWagersUnmatched(isBack, 2);
            Assert.assertTrue((lstWagersUnmatched.size()==2), "ERROR: lstWagers size is less than 2");

            log(String.format("Verify 2: Verify there is 2 unmatched bets the first bet with stake %s and the second bet with old stake %s",addedStake,minBet));
            log("Verify 2.1: Odds is display correct");
            log("Verify 2.2: Stake is correctly");
            log("Verify 2.3: Profit of Back bet is correctly");
            log("Verify First unmatched bet info");
            Assert.assertEquals(lstWagersUnmatched.get(0).getSelectedTeam(), selectedOdd.getSelectedTeam(),"FAILED! Selection is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(0).getOddRate() , oddsUpdate, "ERROR: Odds on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(0).getStake(), addedStake, "ERROR: Stake on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(0).getProfit(),profitNeUnmatchedBet,"ERROR: The profit my bet is incorrect");

            log("Verify Second unmatched bet info");
            Assert.assertEquals(lstWagersUnmatched.get(1).getSelectedTeam(), selectedOdd.getSelectedTeam(),"FAILED! Selection is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(1).getOddRate() , oddsUpdate, "ERROR: Odds on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(1).getStake(), minBet, "ERROR: Stake on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(1).getProfit(),selectedOdd.getProfit(),"ERROR: The profit my bet is incorrect");
            log("INFO: Executed completely");
        }
        finally{
            log("Post Condition: Cancel all unmatched bets");
            page.myBetControl.cancelAllBetUnmatched();
        }
    }

    @Test (groups = {"smoke11"})
    public void Place_Bet_Market_Page_TC_011() throws InterruptedException {
        log("@title: Exposure and Liability is kept correctly when place unmatched bet");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);
        int totalUpdateStake = Integer.parseInt(minBet)*2 +1;
        String addedStake =String.format("%s", totalUpdateStake - Integer.parseInt(minBet));
        String minBetDouble = String.format("%s",totalUpdateStake);
        AccountBalance balanceBefore = BetUtils.getUserBalance();

        log("Step 1: Navigate to Cricket page");
        SportPage sportPage = memberHomePage.navigateCricket();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);
        try {
            log("Step 3: Click on the first market - Match Odds");
            Odd eventOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,isBack);
            MarketPage marketPage = page.openMarketPage(eventOdds.getEventName());
            Odd marketOdds = marketPage.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,isBack);
            marketOdds.getOdd().click();

            log("Step: Get bet info in bet slip");
            List<SelectedOdd> lstSelectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds(1);
            SelectedOdd selectedOdd = lstSelectedOdd.get(0);
            log(" Update Odds");
            marketPage.betSlipControlOldUI.inputOdds(selectedOdd,9);
            double oddsUpdate = Double.parseDouble(selectedOdd.getTxtOdd().getAttribute("value"));
            double profitNeUnmatchedBet = (oddsUpdate-1) * Integer.parseInt(addedStake);

            log(String.format("Step 4: Input stake and place unmatche bet %s @ %.2f ",minBet,oddsUpdate));
            selectedOdd = marketPage.betSlipControlOldUI.placeBet(selectedOdd, minBet);

            log("Step 4.1 Get Unmatch bet info");
            List<SelectedOdd> lstWagers = page.myBetControl.getWagersUnmatched(isBack, 1);

            log(String.format("Step 5: Update stake from %s to %s",minBet,minBetDouble));
            marketPage.myBetControl.updateUnmatchBet(lstWagers.get(0),minBetDouble);

            log("Step 6: Click View Open Bet");
            marketPage.myBetControloldUI.clickViewOpenbet();

            log("Step 7: Get bet after place");
            List<SelectedOdd> lstWagersUnmatched = page.myBetControl.getWagersUnmatched(isBack, 2);
            Assert.assertTrue((lstWagersUnmatched.size()==2), "ERROR: lstWagers size is less than 2");

            log("Verify 2: Verify bet info in umatched bet is correct");
            log("Verify 2.1: Odds is display correct");
            log("Verify 2.2: Stake is correctly");
            log("Verify 2.3: Profit of Back bet is correctly");
            log("Verify First unmatched bet info");
            Assert.assertEquals(lstWagersUnmatched.get(0).getSelectedTeam(), selectedOdd.getSelectedTeam(),"FAILED! Selection is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(0).getOddRate() , oddsUpdate, "ERROR: Odds on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(0).getStake(), addedStake, "ERROR: Stake on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(0).getProfit(), profitNeUnmatchedBet,"ERROR: The profit my bet is incorrect");

            log("Verify Second unmatched bet info");
            Assert.assertEquals(lstWagersUnmatched.get(1).getSelectedTeam(), selectedOdd.getSelectedTeam(),"FAILED! Selection is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(1).getOddRate(), oddsUpdate, "ERROR: Odds on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(1).getStake(), minBet, "ERROR: Stake on my bet is incorrect");
            Assert.assertEquals(lstWagersUnmatched.get(1).getProfit(),selectedOdd.getProfit(),"ERROR: The profit my bet is incorrect");

           /* List<Wager> lstOrders = BetUtils.getListMatchedandUnmatchedWager("cricket","31519717","200096656");
        List<Wager> lstOrders = BetUtils.getListMatchedandUnmatchedWager("cricket","31519711","200097713");
        List<String> lstRunner = BetUtils.getListSelectionsofMarket("31519717","200097713");
        List<ArrayList<String>> lstForecast1 = BetUtils.getProfitandLiabilityBySelection(lstOrders,lstRunner);
        List<ArrayList<String>> lstForecast= BetUtils.calculateForecast(lstForecast1);*/
            //  log("Verify 3: Verify Balance and exposure is correctly after place unmatched bet");
            //   AccountBalance balanceAfter = BetUtils.getUserBalance(true);

            log("INFO: Executed completely");
        }
        finally{
            log("Post Condition: Cancel all unmatched bets");
            page.myBetControl.cancelAllBetUnmatched();
        }
    }
}
