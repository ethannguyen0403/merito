package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import membersite.objects.funsport.SelectedOdd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;

import java.util.List;
import java.util.Objects;

public class BetSlipMyBetSportPageTest extends BaseCaseMerito {

    /**
     * @title: Validate that there is no http request error when clicking Soccer page
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Soccer page on Exchange
     * @expect:  1. There is no http request error
     */
    @Test(groups = {"http_request"})
    public void Bet_Slip_My_Bet_Sport_Page_001(){
        log("@title: Validate that there is no http request error when clicking Soccer page");
        log("Step 1: Navigate to Soccer page on Exchange");
        memberHomePage.navigateSoccer();

        boolean isError = hasHTTPRespondedOK();
        log("Verify: There is no http requests error");
        Assert.assertTrue(isError, "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can remove a selected odd successfully on Bet Slip
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Soccer page on Exchange
     *           2. Click a odd without empty at Home team and Back type
     *           3. Get the selected Odd on Bet Slip
     *           4. CLick x icon to remove the selected odd
     * @expect:  1. The selected odd is removed successfully
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_002(){
        log("@title: Validate that user can remove a selected odd successfully on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        log("Step 1: Navigate to Soccer page on Exchange");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: CLick x icon to remove the selected odd");
        selectedOdd.getIconRemove().click();

        log("Verify 1: The selected odd is removed successfully");
        Assert.assertTrue(page.betSlipControlOldUI.lblClickOnOdds.isDisplayed(), "ERROR: The selected odd still displays after clicking x icon on Bet Slip");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that a selected odd is removed successfully on Bet Slip by clicking Cancel All selections button
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Soccer page on Exchange
     *           2. Click a odd without empty at Home team and Back type
     *           3. Get the selected Odd on Bet Slip
     *           4. Click Cancel All Selections to remove the selected odd
     * @expect:  1. The selected odd is removed successfully
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_003(){
        log("@title: Validate that a selected odd is removed successfully on Bet Slip by clicking Cancel All selections button");
        log("Pre-condition: Player signs in successfully");
        log("Step 1: Navigate to Soccer page on Exchange");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Click Cancel All Selections to remove the selected odd");
        page.betSlipControlOldUI.btnCancelAllSelections.click();

        log("Verify 1: The selected odd is removed successfully");
        Assert.assertTrue(page.betSlipControlOldUI.lblClickOnOdds.isDisplayed(), "ERROR: The selected odd still displays after clicking x icon on Bet Slip");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Place Bet button's behaviors are correct in case of inputted stake and no stake
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Soccer page on Exchange
     *           2. Click a odd without empty at Home team and Back type
     *           3. Get the selected Odd on Bet Slip
     *           4. Input stake into Stake text-box
     * @expect:  1. At step 2, Place bets button is disabled
     *           2. At step 4, Place bets button is enabled
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_004(){
        log("@title: Validate that Place Bet button's behaviors are correct in case of inputted stake and no stake");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1: Navigate to Soccer page on Exchange");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        boolean isDisabled = page.betSlipControlOldUI.isPlaceBetDisabled();
        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake into Stake text-box");
        page.betSlipControlOldUI.inputStake(selectedOdd, minBet);

        log("Verify 1: At step 2, Place bets button is disabled");
        log("Verify 2: At step 4, Place bets button is enabled");
        Assert.assertTrue(isDisabled, "ERROR: The expected disable of Place bet button should be true on Bet Slip before inputting a stake");
        Assert.assertFalse(page.betSlipControlOldUI.isPlaceBetDisabled(), "ERROR: The expected enable of Place bet button should be true on Bet Slip after the inputted stake");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user cannot place bet when inputting a stake more than maximum stake
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Soccer page on Exchange
     *           2. Click a odd without empty at Home team and Back type
     *           3. Get the selected Odd on Bet Slip
     *           4. Input a stake more than maximum stake into Stake text-box
     * @expect:  1. At step 2, Place bets button is disabled
     *           2. At step 4, Place bets button is disabled after inputting a stake more than max stake
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_005(){
        log("@title: Validate that user cannot place bet when inputting a stake more than maximum stake");
        log("Pre-condition: Player signs in successfully");
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        maxBet = Double.toString(Double.parseDouble(maxBet) + 1);

        log("Step 1: Navigate to Soccer page on Exchange");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);
        boolean isDisabled = page.betSlipControlOldUI.isPlaceBetDisabled();

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input a stake into Stake text-box");
        page.betSlipControlOldUI.inputStake(selectedOdd, maxBet);

        log("Verify 1: At step 2, Place bets button is disabled");
        log("Verify 2: At step 4, Place bets button is disabled after inputting a stake more than max stake");
        Assert.assertTrue(isDisabled, "ERROR: The expected disable of Place bet button should be true on Bet Slip before inputting a stake");
        Assert.assertTrue(page.betSlipControlOldUI.isPlaceBetDisabled(), "ERROR: The expected disable of Place bet button should be true on Bet Slip after the inputted stake more than max bet");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user cannot place bet when inputting a stake less than minimum stake
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Soccer page on Exchange
     *           2. Click a odd without empty at Home team and Back type
     *           3. Get the selected Odd on Bet Slip
     *           4. Input a stake less than minimum stake into Stake text-box
     * @expect:  1. At step 2, Place bets button is disabled
     *           2. At step 4, Place bets button is disabled after inputting a stake less than min stake
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_006(){
        log("@title: Validate that user cannot place bet when inputting a stake less than minimum stake");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        minBet = Double.toString(Double.parseDouble(minBet) -1);

        log("Step 1: Navigate to Soccer page on Exchange");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);
        boolean isDisabled = page.betSlipControlOldUI.isPlaceBetDisabled();

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input a stake into Stake text-box");
        page.betSlipControlOldUI.inputStake(selectedOdd, minBet);

        log("Verify 1: At step 2, Place bets button is disabled");
        log("Verify 2: 2. At step 4, Place bets button is disabled after inputting a stake less than min stake");
        Assert.assertTrue(isDisabled, "ERROR: The expected disable of Place bet button should be true on Bet Slip before inputting a stake");
        Assert.assertTrue(page.betSlipControlOldUI.isPlaceBetDisabled(), "ERROR: The expected disable of Place bet button should be true on Bet Slip after the inputted stake less than min bet");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that a selected odd at HOME-BACK displays correct data both Odd page and on Bet Slip
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Home page on Exchange
     *           2. Click an odd without empty at Home team and Back type
     *           3. Get the selected Odd info on Bet Slip
     *           4. Input stake and calculate Profit and Liability
     * @expect:  1. Odd rate on Odd page and on Bet Slip is the same
     *           2. Selected team on Odd page and on Bet Slip is the same
     *           3. Event name on Odd page and on Bet Slip is the same
     *           4. Is in-play of this event is the same on Odd page and Bet Slip
     */
    @Test(groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_007(){
        log("@title: Validate that a selected odd at HOME-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);

        log("Step 1: Navigate to Home page on Exchange");
        SportPage page = memberHomePage.navigateTennis();

        log("Step 2: Click a odd without empty at Home team and Back type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and calculate Profit and Liability");
        selectedOdd = page.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same");
        log("Verify 2: Selected team on Odd page and on Bet Slip is the same");
        log("Verify 3: Event name on Odd page and on Bet Slip is the same");
        log("Verify 4: Is in-play of this event is the same on Odd page and Bet Slip");
        Assert.assertTrue(selectedOdd.getOddRate() >= Double.parseDouble(odd.getOddRate()), String.format("ERROR: The odd rate on Odd page is '%s' but found on Bet Slip is '%s'", odd.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(odd.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on Odd page is '%s' but found on Bet Slip is '%s'", odd.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertEquals(odd.getDataEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(odd.getIsInPlay(), selectedOdd.getIsInPlay(), String.format("ERROR: The selected odd on Odd page is in-play '%s' but found on Bet Slip is '%s'", odd.getIsInPlay(), selectedOdd.getIsInPlay()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that a selected odd at HOME-LAY displays correct data both Odd page and on Bet Slip
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Home page on Exchange
     *           2. Click an odd without empty at HOME team and LAY type
     *           3. Get the selected Odd info on Bet Slip
     *           4. Input stake and calculate Profit and Liability
     * @expect:  1. Odd rate on Odd page and on Bet Slip is the same
     *           2. Selected team on Odd page and on Bet Slip is the same
     *           3. Event name on Odd page and on Bet Slip is the same
     *           4. Is in-play of this event is the same on Odd page and Bet Slip
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_008(){
        log("@title: Validate that a selected odd at HOME-LAY displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.LAY);

        log("Step 1: Navigate to Home page on Exchange");
        SportPage page = memberHomePage.navigateTennis();

        log("Step 2: Click a odd without empty at HOME team and LAY type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, false, false,false,8, 8);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and calculate Profit and Liability");
        selectedOdd = page.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same");
        log("Verify 2: Selected team on Odd page and on Bet Slip is the same");
        log("Verify 3: Event name on Odd page and on Bet Slip is the same");
        log("Verify 4: Is in-play of this event is the same on Odd page and Bet Slip");
        Assert.assertTrue(selectedOdd.getOddRate() <= Double.parseDouble(odd.getOddRate()), String.format("ERROR: The odd rate on Odd page is '%s' but found on Bet Slip is '%s'", odd.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(odd.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on Odd page is '%s' but found on Bet Slip is '%s'", odd.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertEquals(odd.getDataEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(odd.getIsInPlay(), selectedOdd.getIsInPlay(), String.format("ERROR: The selected odd on Odd page is in-play '%s' but found on Bet Slip is '%s'", odd.getIsInPlay(), selectedOdd.getIsInPlay()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that a selected odd at DRAW-BACK displays correct data both Odd page and on Bet Slip
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Home page on Exchange
     *           2. Click an odd without empty at DRAW team and BACK type
     *           3. Get the selected Odd info on Bet Slip
     *           4. Input stake and calculate Profit and Liability
     * @expect:  1. Odd rate on Odd page and on Bet Slip is the same
     *           2. Selected team on Odd page and on Bet Slip is the same
     *           3. Event name on Odd page and on Bet Slip is the same
     *           4. Is in-play of this event is the same on Odd page and Bet Slip
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_009(){
        log("@title: Validate that a selected odd at DRAW-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);

        log("Step 1: Navigate to Home page on Exchange");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at DRAW team and BACK type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.DRAW, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and calculate Profit and Liability");
        selectedOdd = page.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same");
        log("Verify 2: Selected team on Odd page and on Bet Slip is the same");
        log("Verify 3: Event name on Odd page and on Bet Slip is the same");
        log("Verify 4: Is in-play of this event is the same on Odd page and Bet Slip");
        Assert.assertTrue(selectedOdd.getOddRate() >= Double.parseDouble(odd.getOddRate()), String.format("ERROR: The odd rate on Odd page is '%s' but found on Bet Slip is '%s'", odd.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(odd.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on Odd page is '%s' but found on Bet Slip is '%s'", odd.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertEquals(odd.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(odd.getIsInPlay(), selectedOdd.getIsInPlay(), String.format("ERROR: The selected odd on Odd page is in-play '%s' but found on Bet Slip is '%s'", odd.getIsInPlay(), selectedOdd.getIsInPlay()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that a selected odd at DRAW-LAY displays correct data both Odd page and on Bet Slip
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Home page on Exchange
     *           2. Click an odd without empty at DRAW team and LAY type
     *           3. Get the selected Odd info on Bet Slip
     *           4. Input stake and calculate Profit and Liability
     * @expect:  1. Odd rate on Odd page and on Bet Slip is the same
     *           2. Selected team on Odd page and on Bet Slip is the same
     *           3. Event name on Odd page and on Bet Slip is the same
     *           4. Is in-play of this event is the same on Odd page and Bet Slip
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_010() {
        log("@title: Validate that a selected odd at DRAW-LAY displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1: Navigate to Home page on Exchange");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at DRAW team and LAY type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.DRAW, false, false,false,8, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and calculate Profit and Liability");
        selectedOdd = page.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same");
        log("Verify 2: Selected team on Odd page and on Bet Slip is the same");
        log("Verify 3: Event name on Odd page and on Bet Slip is the same");
        log("Verify 4: Is in-play of this event is the same on Odd page and Bet Slip");
        Assert.assertTrue(selectedOdd.getOddRate() <= Double.parseDouble(odd.getOddRate()), String.format("ERROR: The odd rate on Odd page is '%s' but found on Bet Slip is '%s'", odd.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(odd.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on Odd page is '%s' but found on Bet Slip is '%s'", odd.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertEquals(odd.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(odd.getIsInPlay(), selectedOdd.getIsInPlay(), String.format("ERROR: The selected odd on Odd page is in-play '%s' but found on Bet Slip is '%s'", odd.getIsInPlay(), selectedOdd.getIsInPlay()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that a selected odd at AWAY-BACK displays correct data both Odd page and on Bet Slip
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Home page on Exchange
     *           2. Click an odd without empty at AWAY team and BACK type
     *           3. Get the selected Odd info on Bet Slip
     *           4. Input stake and calculate Profit and Liability
     * @expect:  1. Odd rate on Odd page and on Bet Slip is the same
     *           2. Selected team on Odd page and on Bet Slip is the same
     *           3. Event name on Odd page and on Bet Slip is the same
     *           4. Is in-play of this event is the same on Odd page and Bet Slip
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_011() {
        log("@title: Validate that a selected odd at AWAY-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);

        log("Step 1: Navigate to Home page on Exchange");
        SportPage page = memberHomePage.navigateCricket();

        log("Step 2: Click a odd without empty at AWAY team and BACK type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.AWAY, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and calculate Profit and Liability");
        selectedOdd = page.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same");
        log("Verify 2: Selected team on Odd page and on Bet Slip is the same");
        log("Verify 3: Event name on Odd page and on Bet Slip is the same");
        log("Verify 4: Is in-play of this event is the same on Odd page and Bet Slip");
        Assert.assertTrue(selectedOdd.getOddRate() >= Double.parseDouble(odd.getOddRate()), String.format("ERROR: The odd rate on Odd page is '%s' but found on Bet Slip is '%s'", odd.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(odd.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on Odd page is '%s' but found on Bet Slip is '%s'", odd.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertEquals(odd.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(odd.getIsInPlay(), selectedOdd.getIsInPlay(), String.format("ERROR: The selected odd on Odd page is in-play '%s' but found on Bet Slip is '%s'", odd.getIsInPlay(), selectedOdd.getIsInPlay()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that a selected odd at AWAY-LAY displays correct data both Odd page and on Bet Slip
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Navigate to Home page on Exchange
     *           2. Click an odd without empty at AWAY team and LAY type
     *           3. Get the selected Odd info on Bet Slip
     *           4. Input stake and calculate Profit and Liability
     * @expect:  1. Odd rate on Odd page and on Bet Slip is the same
     *           2. Selected team on Odd page and on Bet Slip is the same
     *           3. Event name on Odd page and on Bet Slip is the same
     *           4. Is in-play of this event is the same on Odd page and Bet Slip
     */
    @Test (groups = {"smoke"})
    public void Bet_Slip_My_Bet_Sport_Page_012() {
        log("@title: Validate that a selected odd at AWAY-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);

        log("Step 1: Navigate to Home page on Exchange");
        SportPage page = memberHomePage.navigateCricket();

        log("Step 2: Click a odd without empty at AWAY team and LAY type");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.AWAY, false, false, false,8, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        page.clickOdd(odd);

        log("Step 3: Get the selected Odd info on Bet Slip");
        List<SelectedOdd> lstSelectedOdd = page.betSlipControlOldUI.getSelectedOdds(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);

        log("Step 4: Input stake and calculate Profit and Liability");
        selectedOdd = page.betSlipControlOldUI.updateInfoAfterSetStake(selectedOdd, minBet);

        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same");
        log("Verify 2: Selected team on Odd page and on Bet Slip is the same");
        log("Verify 3: Event name on Odd page and on Bet Slip is the same");
        log("Verify 4: Is in-play of this event is the same on Odd page and Bet Slip");
        Assert.assertTrue(selectedOdd.getOddRate() <= Double.parseDouble(odd.getOddRate()), String.format("ERROR: The odd rate on Odd page is '%s' but found on Bet Slip is '%s'", odd.getOddRate(), selectedOdd.getOddRate()));
        Assert.assertEquals(odd.getSelectedTeam(), selectedOdd.getSelectedTeam(), String.format("ERROR: The selected team on Odd page is '%s' but found on Bet Slip is '%s'", odd.getSelectedTeam(), selectedOdd.getSelectedTeam()));
        Assert.assertEquals(odd.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
        Assert.assertEquals(odd.getIsInPlay(), selectedOdd.getIsInPlay(), String.format("ERROR: The selected odd on Odd page is in-play '%s' but found on Bet Slip is '%s'", odd.getIsInPlay(), selectedOdd.getIsInPlay()));
        log("INFO: Executed completely");
    }

}
