package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import membersite.objects.funsport.SelectedOdd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;

import java.util.List;
import java.util.Objects;

public class HomePageTest extends BaseCaseMerito {
    /**
     * @title: Validate UI Home page
     * @pre-condition
     *           1. Sign in
     * @steps:  1. Click on Home page
     * @expect: 1. UI is correcly: Next Race section
     * Sport highlight session
     * Today Racing
     */
    @Test(groups = {"smoke"})
    public void Home_Page_TC_001(){
        log("@title: Validate UI Home page");
        log("Pre-condition: Player signs in successfully");
        log("Step 1. Click on Home page");
        log("Verify 1. UI is correctly: Next Race section\n" +
                "     * Sport highlight session\n" +
                "     * Today Racing");
        Assert.assertTrue(memberHomePage.highLightRaceControl.getNexRaceLabel().contains("Next Race"),"FAILED! Next Race label is incorrect");
        Assert.assertEquals(memberHomePage.lblSportHighLight.getText(),"Sport Highlights","FAILED! Sport highlight label is incorrect");
        Assert.assertEquals(memberHomePage.lblTodayRacing.getText(),"Today's racing","FAILED! Today's racing label is incorrect");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate sport in sport highlight display as left menu
     * @pre-condition
     *           1. Sign in
     * @steps:  1. Click on Home page
     * @expect: Verify Three main first sports in Highlight tab matched the left menu
     */
    @Test(groups = {"smoke"})
    public void Home_Page_TC_002(){
        log("@title: Validate UI Home page");
        log("Pre-condition: Player signs in successfully");
        log("Step 1. Click on Home page");
        List<String> lstPopularSport = memberHomePage.getPopularSports();
        List<String> lstSportHighlight = memberHomePage.getSportHighLightTab();
        log("Verify Verify Three main first sports in Highlight tab matched the left menu");
        Assert.assertEquals(lstPopularSport.get(0), lstSportHighlight.get(0),"FAILED! First sport not match with the leftmenu");
        Assert.assertEquals(lstPopularSport.get(1), lstSportHighlight.get(1),"FAILED!Second sport not match with the leftmenu");
        Assert.assertEquals(lstPopularSport.get(2), lstSportHighlight.get(2),"FAILED!Third sport not match with the leftmenu");

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
    public void Home_Page_TC_003(){
        log("@title: Validate that a selected odd at HOME-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);

        log("Step 1: Navigate to Home page on Exchange");
        HomePage page = memberHomePage.navigateHomePage();
        page.clickSportHighlight("Soccer");

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
    public void Home_Page_TC_004(){
        log("@title: Validate that a selected odd at HOME-LAY displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.LAY);

        log("Step 1: Navigate to Home page on Exchange");
        HomePage page = memberHomePage.navigateHomePage();
        page.clickSportHighlight("Tennis");
        
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
    public void Home_Page_TC_005(){
        log("@title: Validate that a selected odd at DRAW-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);

        log("Step 1: Navigate to Home page on Exchange");
        HomePage page = memberHomePage.navigateHomePage();
        page.clickSportHighlight("Soccer");

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
    public void Home_Page_TC_006() {
        log("@title: Validate that a selected odd at DRAW-LAY displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1: Navigate to Home page on Exchange");
        HomePage page = memberHomePage.navigateHomePage();
        page.clickSportHighlight("Soccer");

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
    public void Home_Page_TC_007() {
        log("@title: Validate that a selected odd at AWAY-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);

        log("Step 1: Navigate to Home page on Exchange");
        HomePage page = memberHomePage.navigateHomePage();
        memberHomePage.clickSportHighlight("Cricket");

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
    public void Home_Page_TC_008() {
        log("@title: Validate that a selected odd at AWAY-BACK displays correct data both Odd page and on Bet Slip");
        log("Pre-condition: Player signs in successfully");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);

        log("Step 1: Navigate to Home page on Exchange");
        HomePage page = memberHomePage.navigateHomePage();
        memberHomePage.clickSportHighlight("Cricket");

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
