package membersite.testcases.funsport.tabexchange;
import common.MemberConstants;
import membersite.controls.funsport.EventOddContentControl;
import membersite.controls.funsport.MarketOddControl;
import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import membersite.objects.funsport.SelectedOdd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.EventPage;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;

import java.util.List;
import java.util.Objects;

public class BetSlipMyBetMarketPageTest extends BaseCaseMerito {

    /**
     * @title: Verify default value of Bet Slip and My Bet
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click Market page
     * @expect:  1. Verify Bet Slip and Mini My bet UI when have no bet
     */
    @Test(groups = {"smoke"})
    public void Bet_Slip_My_Bet_Market_Page_001() {
        log("@title: Validate that user can remove a selected odd successfully on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        log("Step 1. Click Soccer page");
        SportPage sportPage = memberHomePage.navigateSoccer();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,6, 6);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step 2. Click an event Soccer page");
        EventPage eventPage = sportPage.oddPageControl.clickEvent(odd.getEventName());
        Odd eventOdd = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);

        log("Step 3. Click Match Odds market");
        MarketPage marketPage = eventPage.openMarketPage(eventOdd.getEventName());

        log("Verify 1.Verify Bet Slip and Mini My bet UI when have no bet");
        Assert.assertEquals(marketPage.betSlipControlOldUI.getGuideText(), MemberConstants.BetSlip.GUIDE_TEXT_MESSAGE,"FAILED! Bet slip message when have no bet is incorrect");
        Assert.assertEquals(marketPage.myBetControl.getGuideText(), MemberConstants.MiniMyBet.GUIDE_TEXT_MESSAGE1,"FAILED! Bet slip message when have no bet is incorrect");
        Assert.assertEquals(marketPage.myBetControl.getGuideTextSecondRow(), MemberConstants.MiniMyBet.GUIDE_TEXT_MESSAGE2,"FAILED! Bet slip message when have no bet is incorrect");
        log("INFO: Executed completely");
    }
    /**
     * @title: Verify 1Click button
     * @pre-condition
     *           1. Sign in
     * @steps:  1, On and Off 1 Click button
     * @expect: 1. Verify 1Click Off by default
     * 2.Verify 1Click button is on and off status
     */
    @Test(groups = {"smoke"})
    public void Bet_Slip_My_Bet_Market_Page_002() {
        log("@title: Verify 1Click button");
        log("Pre-condition: Player signs in successfully");
        log("Step 1. Click Soccer page");
        SportPage sportPage = memberHomePage.navigateSoccer();
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,6, 6);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step 2. Click an event Soccer page");
        EventPage eventPage = sportPage.oddPageControl.clickEvent(odd.getEventName());
        Odd eventOdd = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);

        log("Step 3. Click Match Odds market");
        MarketPage marketPage = eventPage.openMarketPage(eventOdd.getEventName());

        log("Verify 1. Verify 1Click Off by default");
        Assert.assertEquals(marketPage.oneClickBettingControl.getOneClickStatus(),"OFF","FAILED! Status should be OFF by default");
        Assert.assertEquals(marketPage.oneClickBettingControl.getOneClickLabel()," 1 Click Betting","FAILED! 1 Click Betting by default");

        log("Step 4. Active 1Click");
        marketPage.oneClickBettingControl.activeClick();

        log("Verify 2.Verify 1Click button is on");
        Assert.assertEquals(marketPage.oneClickBettingControl.getOneClickStatus(),"ON","FAILED! Status should be OFF by default");

        log("Step 4. Inactive 1Click");
        marketPage.oneClickBettingControl.activeClick();

        log("Verify 3.Verify 1Click button is on");
        Assert.assertEquals(marketPage.oneClickBettingControl.getOneClickStatus(),"OFF","FAILED! Status should be OFF by default");
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
    @Test(groups = {"smoke"})
    public void Bet_Slip_My_Bet_Market_Page_003(){
        log("@title: Validate that user can remove a selected odd successfully on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        log("Step 1: Navigate to Soccer page on Exchange");
        SportPage sportPage = memberHomePage.navigateSoccer();

        log("Step 2: Click a odd without empty at Home team and Back type");
        Odd odd = sportPage.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false,false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no odd");
            return;
        }
        log("Step 2: Click on Event Name");
        EventPage eventPage =sportPage.oddPageControl.clickEvent(odd);
        Odd eventOdds = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);

        log("Step 3. Click on Match odds market in the left menu");
        MarketPage page = eventPage.openMarketPage(eventOdds.getEventName());
        Odd marketOdd = page.marketOddControl.getMatchOdds(MarketOddControl.Team.HOME,true);
        page.clickOdd(marketOdd);

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
    public void Bet_Slip_My_Bet_Market_Page_004(){
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
    public void Bet_Slip_My_Bet_Market_Page_005(){
        log("@title: Validate that Place Bet button's behaviors are correct in case of inputted stake and no stake");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER,SportPage.BetType.BACK);

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
    public void Bet_Slip_My_Bet_Market_Page_006(){
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
    public void Bet_Slip_My_Bet_Market_Page_007(){
        log("@title: Validate that user cannot place bet when inputting a stake less than minimum stake");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        minBet = Double.toString(Double.parseDouble(minBet) + 1);

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
    @Test(groups = {"smoke1"})
    public void Bet_Slip_My_Bet_Market_Page_008(){
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
        Assert.assertEquals(odd.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
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
    public void Bet_Slip_My_Bet_Market_Page_009(){
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
        Assert.assertEquals(odd.getEventName(), selectedOdd.getEventName(), String.format("ERROR: The selected event name on Odd page is '%s' but found on Bet Slip is '%s'", odd.getEventName(), selectedOdd.getEventName()));
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
    public void Bet_Slip_My_Bet_Market_Page_010(){
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
    public void Bet_Slip_My_Bet_Market_Page_011() {
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
    public void Bet_Slip_My_Bet_Market_Page_012() {
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
    public void Bet_Slip_My_Bet_Market_Page_13() {
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
