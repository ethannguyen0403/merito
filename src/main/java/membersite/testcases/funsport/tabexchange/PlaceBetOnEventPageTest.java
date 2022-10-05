package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.EventOddContentControl;
import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import membersite.objects.funsport.SelectedOdd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.EventPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;

import java.util.List;
import java.util.Objects;

public class PlaceBetOnEventPageTest extends BaseCaseMerito {
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
    @Test(groups = {"smoke"})
    public void Place_Bet_Event_Page_TC_001(){
        log("@title: Validate that user can place a bet with HOME - BACK successfully");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);

        log("Step 1: Navigate to Tennis page");
        SportPage sportPage = memberHomePage.navigateTennis();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false,true,3, 3);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd.getSelectedTeam());

        log("Step 2: Click an odd without empty at Home team and Back type");

        Odd marketOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);
        page.clickOdd(marketOdds);

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControl.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);
        selectedOdd = page.betSlipControl.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Step 4: Input stake and place this bet");
        page.betSlipControl.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' >= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        Assert.assertEquals(wager.getMarketName(), selectedOdd.getMarketName(), String.format("ERROR: The market name on my bet is '%s' but found on Bet Slip is '%s'", wager.getMarketName(), selectedOdd.getMarketName()));
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
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
    @Test (groups = {"smoke"})
    public void Place_Bet_Event_Page_TC_002(){
        log("@title: Validate that user can place a bet with HOME - LAY successfully");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.LAY);

        log("Step 1: Navigate to Tennis page");
        SportPage sportPage = memberHomePage.navigateTennis();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false,true,10, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd.getSelectedTeam());

        log("Step 3: Click an odds of Home team and Lay  type");
        Odd marketOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,isBack);
        page.clickOdd(marketOdds);

        log("Step 3: Click an odd empty at Home team and Back type");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControl.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControl.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 2: Event name on My Bet and on Bet Slip is the same");
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' <= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getMarketName(), selectedOdd.getMarketName(), String.format("ERROR: The market name on my bet is '%s' but found on Bet Slip is '%s'", wager.getMarketName(), selectedOdd.getMarketName()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can place a bet with DRAW-BACK successfully
     * @steps:  1. Navigate to Soccer page on Exchange
     *          2. Click an odd without empty at DRAW team and BACK type
     *          3. Input stake and place this bet
     * @expect: 1. Odd rate on My Bet and on Bet Slip is the same
     *          2. Event name on My Bet and on Bet Slip is the same
     *          3. Market name on My Bet and on Bet Slip is the same
     *          4. Selected team on My Bet and on Bet Slip is the same
     *          5. Liability on My Bet and on Bet Slip is the same
     *          6. Profit on My Bet and on Bet Slip is the same
     */
    @Test (groups = {"smoke"})
    public void Place_Bet_Event_Page_TC_003(){
        log("@title: Validate that user can place a bet with DRAW-BACK successfully");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);

        log("Step 1: Navigate to Soccer page");
        SportPage sportPage = memberHomePage.navigateTennis();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.AWAY, isBack, false,true,10, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);
        Odd marketOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.AWAY,isBack);

        log("Step 2: Click an odd without empty at DRAW team and BACK type");
        page.clickOdd(marketOdds);

        List<SelectedOdd> lstSelectedOdd = page.betSlipControl.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControl.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet is '%s' >= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
       Assert.assertEquals(wager.getMarketName(), selectedOdd.getMarketName(), String.format("ERROR: The market name on my bet is '%s' but found on Bet Slip is '%s'", wager.getMarketName(), selectedOdd.getMarketName()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >=selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can place a bet with DRAW - LAY successfully
     * @steps:  1. Navigate to Soccer page on Exchange
     *          2. Click an odd without empty at DRAW team and LAY type
     *          3. Input stake and place this bet
     * @expect: 1. Odd rate on My Bet and on Bet Slip is the same
     *          2. Event name on My Bet and on Bet Slip is the same
     *          3. Market name on My Bet and on Bet Slip is the same
     *          4. Selected team on My Bet and on Bet Slip is the same
     *          5. Liability on My Bet and on Bet Slip is the same
     *          6. Profit on My Bet and on Bet Slip is the same
     */
    @Test (groups = {"smoke"})
    public void Place_Bet_Event_Page_TC_004(){
        log("@title: Validate that user can place a bet with DRAW-LAY successfully");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.LAY);

        log("Step 1: Navigate to Soccer page");
        SportPage sportPage = memberHomePage.navigateTennis();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.AWAY, isBack, false,true,10, 5);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickEvent(odd);
        Odd marketOdds = page.oddPageControl.getMatchOdds(EventOddContentControl.Team.AWAY,isBack);

        log("Step 2: Click an odd without empty at DRAW team and BACK type");
        page.clickOdd(marketOdds);
        List<SelectedOdd> lstSelectedOdd = page.betSlipControl.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControl.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControl.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");

        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't %s <= %s on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
         Assert.assertEquals(wager.getMarketName(), selectedOdd.getMarketName(), String.format("ERROR: The market name on my bet is '%s' but found on Bet Slip is '%s'", wager.getMarketName(), selectedOdd.getMarketName()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

}
