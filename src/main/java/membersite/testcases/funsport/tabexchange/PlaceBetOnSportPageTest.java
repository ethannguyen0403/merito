package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import membersite.objects.funsport.SelectedOdd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

public class PlaceBetOnSportPageTest extends BaseCaseMerito {
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
    @TestRails(id = "569")
    @Test(groups = {"smoke"})
    public void Place_Bet_Sport_Page_TC_001(){
        log("@title: Validate that user can place a bet with HOME - BACK successfully");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);

        log("Step 1: Navigate to Tennis page");
        SportPage page = memberHomePage.navigateTennis();

        log("Step 2: Click an odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step: Get bet info in bet slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);
        selectedOdd = page.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Step 4: Input stake and place this bet");
        page.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControloldUI.getWagers(isBack, 1);// just get the fist bet when it matched/unmatched
        Assert.assertFalse(Objects.isNull(lstWagers), "ERROR: There is no wager in my bet");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' >= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        log("Verify 2&3: Event name on My Bet and on Bet Slip is the same");
        Assert.assertEquals(page.myBetControloldUI.getEventMarketTitle(), String.format("%s - %s",selectedOdd.getEventName(), selectedOdd.getMarketName()),"ERROR: The selected event name on my bet is incorrect");
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
    @TestRails(id = "570")
    @Test (groups = {"smoke"})
    public void Place_Bet_Sport_Page_TC_002(){
        log("@title: Validate that user can place a bet with HOME - LAY successfully");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.LAY);

        log("Step 1: Navigate to Tennis page");
        SportPage page = memberHomePage.navigateTennis();

        log("Step 2: Click an odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, isBack, false, false,8, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControloldUI.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 2: Event name on My Bet and on Bet Slip is the same");
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' <= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(page.myBetControloldUI.getEventMarketTitle(), String.format("%s - %s",selectedOdd.getEventName(), selectedOdd.getMarketName()),"ERROR: The selected event name on my bet is incorrect");
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
    @TestRails(id = "571")
    @Test (groups = {"smoke"})
    public void Place_Bet_Sport_Page_TC_003(){
        log("@title: Validate that user can place a bet with DRAW-BACK successfully");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);

        log("Step 1: Navigate to Soccer page");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click an odd without empty at DRAW team and BACK type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.DRAW, isBack, false, false,8, 6);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControloldUI.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 2: Event name on My Bet and on Bet Slip is the same");
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet is '%s' >= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(page.myBetControloldUI.getEventMarketTitle(), String.format("%s - %s",selectedOdd.getEventName(), selectedOdd.getMarketName()),"ERROR: The selected event name on my bet is incorrect");
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
    @TestRails(id = "572")
    @Test (groups = {"smoke"})
    public void Place_Bet_Sport_Page_TC_004(){ log("@title: Validate that user can place a bet with DRAW-LAY successfully");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1: Navigate to Soccer page");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click an odd without empty at DRAW team and LAY type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.DRAW, isBack, false, false,8, 6);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControloldUI.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 2: Event name on My Bet and on Bet Slip is the same");
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't %s <= %s on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(page.myBetControloldUI.getEventMarketTitle(), String.format("%s - %s",selectedOdd.getEventName(), selectedOdd.getMarketName()),"ERROR: The selected event name on my bet is incorrect");
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
    @TestRails(id = "573")
    @Test (groups = {"smoke"})
    public void Place_Bet_Sport_Page_TC_005(){
        log("@title: Validate that user can place a bet with AWAY-BACK successfully");
        boolean isBack = true;
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);

        log("Step 1: Navigate to Cricket page");
        SportPage page = memberHomePage.navigateCricket();

        log("Step 2: Click an odd without empty at AWAY team and BACK type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.AWAY, isBack, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControloldUI.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 2: Event name on My Bet and on Bet Slip is the same");
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() >= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' >= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on my bet is '%s' but found on Bet Slip is '%s'", wager.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(wager.getMarketName(), selectedOdd.getMarketName(), String.format("ERROR: The market name on my bet is '%s' but found on Bet Slip is '%s'", wager.getMarketName(), selectedOdd.getMarketName()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is '%s'", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can place a bet with AWAY-LAY successfully
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
    @TestRails(id = "574")
    @Test (groups = {"smoke"})
    public void Place_Bet_Sport_Page_TC_006(){
        log("@title: Validate that user can place a bet with AWAY-LAY successfully");
        boolean isBack = false;
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1: Navigate to Cricket page");
        SportPage page = memberHomePage.navigateCricket();

        log("Step 2: Click an odd without empty at AWAY team and LAY type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.AWAY, isBack, false, false,8, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and place this bet");
        selectedOdd = page.betSlipControlOldUI.placeBet(selectedOdd, minBet);

        List<SelectedOdd> lstWagers = page.myBetControloldUI.getWagers(isBack, 1);
        Assert.assertTrue((lstWagers.size()==1), "ERROR: lstWagers size is less than 1");

        SelectedOdd wager = lstWagers.get(0);

        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same");
        log("Verify 2: Event name on My Bet and on Bet Slip is the same");
        log("Verify 3: Market name on My Bet and on Bet Slip is the same");
        log("Verify 4: Selected team on My Bet and on Bet Slip is the same");
        log("Verify 5: Liability on My Bet and on Bet Slip is the same");
        log("Verify 6: Profit on My Bet and on Bet Slip is the same");
        Assert.assertTrue(wager.getOddRate() <= selectedOdd.getOddRate(), String.format("ERROR: The odd rate on my bet isn't '%s' <= '%s' on Bet Slip", wager.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(wager.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on my bet is '%s' but found on Bet Slip is '%s'", wager.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(wager.getMarketName(), selectedOdd.getMarketName(), String.format("ERROR: The market name on my bet is '%s' but found on Bet Slip is '%s'", wager.getMarketName(), selectedOdd.getMarketName()));
        Assert.assertEquals(wager.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on my bet is '%s' but found on Bet Slip is %s", wager.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertTrue(wager.getLiability() <= selectedOdd.getLiability(), String.format("ERROR: The selected liability on my bet is '%s' but found on Bet Slip is '%s'", wager.getLiability(), selectedOdd.getLiability()));
        Assert.assertTrue(wager.getProfit() >= selectedOdd.getProfit(), String.format("ERROR: The profit my bet is '%s' but found on Bet Slip is '%s'", wager.getProfit(), selectedOdd.getProfit()));
        log("INFO: Executed completely");
    }

}
