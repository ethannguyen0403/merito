package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.FancyMarket;
import membersite.pages.MarketPage;
import membersite.pages.MyBetsPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.MemberConstants.*;
import static common.MemberConstants.HomePage.SPORT_ID;
import static common.MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER;
import static common.MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER;

public class ArtemisFancyTest extends BaseCaseTest {
    //Single Runner cases
    @TestRails(id = "2249")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_2249() {
        log("@title: Validate can place bet on Artemis Fancy on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2. Active the event that has Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on an odds of a fancy market then place bet");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fcMarket, true, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fcMarket, true, minStake);

        log("Validate Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager.getMarketName(), "FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(1), expectedWager.getRunnerName(), "FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(2), expectedWager.displayFancyOdds(), "FAILED! Odds is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(3), String.format("%s.00", minStake), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(3), String.format("%.2f", expectedWager.getLiabilityFancyWager()), "FAILED! Liability is incorrect");

        log("Validate Information of placed bet displays correctly");
        MyBetsPage myBetsPage = memberHomePage.openMyBet();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("MATCHED"));
        myBetsPage.verifyWagerInfo(expectedWager);
        log("INFO: Executed completely");
    }

    @TestRails(id = "15782")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15782() {
        log("@title: Validate able navigate to Artemis Fancy market page");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on an odds of a fancy market then place bet (wait until bet matched)");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Validate Event market page displays with Artemis Fancy market");
        Assert.assertTrue(!Objects.isNull(fancyMarket), "FAILED! Cannot find out Artemis Fancy market on event page");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15783")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15783() {
        log("@title: Validate able to open Ladder forecast score");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4. Click on ladder icon on any Fancy market");
        marketPage.openFancyLadderForecast(fancyMarket);
        log("Validate Forecast popup displays with name of selecting market");
        Assert.assertTrue(marketPage.isLadderForecastDisplay(fancyMarket), "FAILED! Ladder forecast does not show correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15784")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15784() {
        log("@title: Validate bet slip is cleared when navigate to another market");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        List<ArrayList> lstBefore = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertFalse(Objects.isNull(lstBefore), "FAILED! There is no record found in mini my bet bet slip");

        log("Step 3. Navigate to any another event and observe bet slip");
        memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        BookmakerMarket bmMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        marketPage = sportPage.clickEventName(bmMarket.getEventName());
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        List<ArrayList> lstAfter = marketPage.getFancyBetSlipMiniMyBet();

        log("Validate Bet slip is cleared");
        Assert.assertTrue(Objects.isNull(lstAfter), "FAILED! Bet slip is not cleared correctly");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15785")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15785() {
        log("@title: Validate able to choose multi selection");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi selection from any market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.addFancyOdds(fancyMarket, false);

        log("Validate Able to choose multi selection (selected selections show in bet slip)");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), fcMarket.getMarketName(), "FAILED! Bet 1 selection does not add into bet slip correctly");
        Assert.assertEquals(lstBetslip.get(5), fcMarket.getMarketName(), "FAILED! Bet 2 selection does not add into bet slip correctly");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15778")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15778() {
        log("@title: Validate multi tab (bet slip) is disabled");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi selection from any market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.addFancyOdds(fancyMarket, false);

        log("Validate Multi tab (bet slip) is disabled and does not work when click on");
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipEnabled(), "FAILED! Multi tab in bet slip is enabled");
        marketPage.myBetsContainer.clickMultiTabBetSlip();
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipSelected(), "FAILED! Multi tab in bet slip is enabled");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15787")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15787() {
        log("@title: Validate bet slip information show correctly for selection Yes");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log("Step 4. Input stake and observe bet slip information");
        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.betsSlipContainer.inputStake(minStake);

        log("Validate Bet slip displays with info market name, selection (Yes [L]), stake inputted and liability (stake * (payout/100))");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), expectedWager.getMarketName(), String.format("FAILED! Market name does not display correctly in bet slip expected %s actual %s", expectedWager.getMarketName(), lstBetslip.get(0)));
        Assert.assertEquals(lstBetslip.get(1), "Yes [L]", String.format("FAILED! Selection does not display correct expected Yes [L] actual %s", lstBetslip.get(1)));
        Assert.assertEquals(lstBetslip.get(2), String.format("%.0f", expectedWager.getOdds()), String.format("FAILED! Odds does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getOdds()), lstBetslip.get(2)));
        Assert.assertEquals(lstBetslip.get(3), String.format("%.0f", expectedWager.getStake()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getStake()), lstBetslip.get(3)));
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getProfitFancyWager()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getProfitFancyWager()), lstBetslip.get(4)));
    }

    @TestRails(id = "15788")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15788() {
        log("@title: Validate bet slip information show correctly for selection No");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_LAY_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));

        log("Step 4. Input stake and observe bet slip information");
        marketPage.addFancyOdds(fancyMarket, false);
        marketPage.betsSlipContainer.inputStake(minStake);

        log("Validate Bet slip displays with info market name, selection (Yes [L]), stake inputted and liability (stake * (payout/100))");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), expectedWager.getMarketName(), String.format("FAILED! Market name does not display correctly in bet slip expected %s actual %s", expectedWager.getMarketName(), lstBetslip.get(0)));
        Assert.assertEquals(lstBetslip.get(1), "No [K]", String.format("FAILED! Selection does not display correct expected Yes [L] actual %s", lstBetslip.get(1)));
        Assert.assertEquals(lstBetslip.get(2), String.format("%.0f", expectedWager.getOdds()), String.format("FAILED! Odds does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getOdds()), lstBetslip.get(2)));
        Assert.assertEquals(lstBetslip.get(3), String.format("%.0f", expectedWager.getStake()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getStake()), lstBetslip.get(3)));
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getLiabilityFancyWager()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getLiabilityFancyWager()), lstBetslip.get(4)));
    }

    @TestRails(id = "15789")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15789() {
        log("@title: Validate exposure is kept correctly when place on Yes");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }

        log("Step 4 Active Artemis Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fcMarket, true, minStake);

        log("Verify 1. Validate Exposure kept correctly when place on Yes section");
        Double liabilityWager = expectedWager.getLiabilityFancyWager();
        String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), liabilityWager, 0.0);
        String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
        Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15790")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15790() {
        log("@title: Validate exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2. Active the event that has Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4. Click on an odds of a fancy market then place bet");
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_LAY_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Validate Exposure kept correctly when place on No section");
        Double liabilityWager = expectedWager.getLiabilityFancyWager();
        String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), 0.0, liabilityWager);
        String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
        Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));
        log("INFO: Executed completely");
    }

    @TestRails(id = "15791")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15791() {
        log("@title: Validate exposure is kept correctly when place on Yes and No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2. Active the event that has Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4 Click on an odds and place on Yes and No option");
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));
        Wager expectedWager2 = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));

        marketPage.placeFancy(fancyMarket, true, minStake);
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Validate Exposure kept correctly when place on Yes and No section");
        Double liabilityWager = expectedWager.getLiabilityFancyWager();
        Double liabilityWager2 = expectedWager2.getLiabilityFancyWager();
        String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), liabilityWager, liabilityWager2);
        String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
        Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));
        log("INFO: Executed completely");
    }

    @TestRails(id = "15792")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15792() {
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        String stake = String.format("%.0f", Double.valueOf(BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE)) - 1);

        log(String.format("Step 5. Click on an odds of a fancy market then place bet with the stake less than min bet: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Validate Can NOT place bet");
        String expectedError = String.format(BetSlip.VALIDATE_STAKE_NOT_VALID, fcMarket.getMinSetting(), fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15793")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15793() {
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String stake = String.format("%.0f", Double.valueOf(BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE)) + 1);

        log(String.format("Step 4. Click on an odds of a fancy market then place bet with the stake greater than max bet: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Validate Can NOT place bet");
        String expectedError = String.format(BetSlip.VALIDATE_STAKE_NOT_VALID, fcMarket.getMinSetting(), fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15794")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15794() {
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake = String.format("%d", (int) (Double.valueOf(balance.getBalance().replaceAll(",", "")) + 1));

        log("Step 2. Active the event that have Fancy market");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log(String.format("Step 4. Click on an odds of a fancy market then place bet with the stake  greater than availablie balance: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Validate Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, BetSlip.ERROR_INSUFFICIENT_BALANCE, String.format("ERROR! Expected error message is %s but found %s", BetSlip.ERROR_INSUFFICIENT_BALANCE, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15814")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15814() {
        log("@title: Validate correct odds display when place on single runner market");
        log("Step 1. Open Artemis Fancy which has 1 runner market");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), SINGLE_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        String stake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 2. Place matched bets");
        marketPage.placeFancy(fcMarket, true, stake);
        Wager expectedWager = marketPage.defineFancyWager(fcMarket, true, Double.parseDouble(stake));

        log("Step 3. Observe odds show on mini my bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        log("Validate Odds displays correctly (if payout = 100 it will displays odds only -> 48, otherwise it will show along with payout -> 48:90)");
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(2), expectedWager.displayFancyOdds(), "FAILED! Odds is incorrect");

        log("Step 4. Navigate to My Bets page and search the matched bet and observe odds");
        MyBetsPage myBetsPage = memberHomePage.openMyBet();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("MATCHED"));
        log("Validate Odds displays correctly (if payout = 100 it will displays odds only -> 48, otherwise it will show along with payout -> 48:90)");
        myBetsPage.verifyWagerInfo(expectedWager);

        log("INFO: Executed completely");
    }

    //Multi Runner cases
    @TestRails(id = "2766")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_2766() {
        log("@title: Validate correct odds display when place on multi runners market");
        log("Step 1. Open Artemis Fancy which has 2 runners market");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        String stake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);

        log("Step 2. Place matched bets");
        marketPage.placeArtemisFancy(fcMarket, true, stake, 1);
        Wager expectedWager = marketPage.defineFancyWager(fcMarket, true, Double.parseDouble(stake));

        log("Step 3. Observe odds show on mini my bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        log("Validate Odds displays correctly (e.g 90, there is no payout in suffix)");
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(2), String.format("%.2f",expectedWager.getOdds()), "FAILED! Odds is incorrect");

        log("Step 4. Navigate to My Bets page and search the matched bet and observe odds");
        MyBetsPage myBetsPage = memberHomePage.openMyBet();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("MATCHED"));
        log("Validate Odds displays correctly (e.g 90, there is no payout in suffix)");
        myBetsPage.verifyWagerInfo(expectedWager);

        log("INFO: Executed completely");
    }

    @TestRails(id = "15795")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15795() {
        log("@title: Validate Ladder forecast score does not display");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);

        log("Step 4. Click on ladder icon on any Fancy market");
        marketPage.openFancyLadderForecast(fancyMarket);
        log("Validate Forecast popup displays with name of selecting market");
        Assert.assertFalse(marketPage.isLadderForecastDisplay(fancyMarket), "FAILED! Ladder forecast does not show correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15796")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15796() {
        log("@title: Validate bet slip is cleared when navigate to another market");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);

        marketPage.addArtemisFancyOdds(fancyMarket, true, 1);
        List<ArrayList> lstBefore = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertFalse(Objects.isNull(lstBefore), "FAILED! There is no record found in mini my bet bet slip");

        log("Step 3. Navigate to any another event and observe bet slip");
        memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        BookmakerMarket bmMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        marketPage = sportPage.clickEventName(bmMarket.getEventName());
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        List<ArrayList> lstAfter = marketPage.getFancyBetSlipMiniMyBet();

        log("Validate Bet slip is cleared");
        Assert.assertTrue(Objects.isNull(lstAfter), "FAILED! Bet slip is not cleared correctly");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15797")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15797() {
        log("@title: Validate able to choose multi runners");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi runner from any market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);
        FancyMarket fancyMarket2 = marketPage.getArtemisFancyMarketInfo(fcMarket, 2);

        marketPage.addArtemisFancyOdds(fancyMarket, true, 1);
        marketPage.addArtemisFancyOdds(fancyMarket2, true, 2);

        log("Validate Able to choose multi selection (selected selections show in bet slip)");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), fcMarket.getMarketName(), "FAILED! Bet 1 selection does not add into bet slip correctly");
        Assert.assertEquals(lstBetslip.get(5), fcMarket.getMarketName(), "FAILED! Bet 2 selection does not add into bet slip correctly");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15798")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15798() {
        log("@title: Validate multi tab (bet slip) is disabled");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi selection from any market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);
        FancyMarket fancyMarket2 = marketPage.getArtemisFancyMarketInfo(fcMarket, 2);

        marketPage.addArtemisFancyOdds(fancyMarket, true, 1);
        marketPage.addArtemisFancyOdds(fancyMarket2, true, 2);

        log("Validate Multi tab (bet slip) is disabled and does not work when click on");
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipEnabled(), "FAILED! Multi tab in bet slip is enabled");
        marketPage.myBetsContainer.clickMultiTabBetSlip();
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipSelected(), "FAILED! Multi tab in bet slip is enabled");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15799")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15799() {
        log("@title: Validate bet slip information show correctly for Runner 1");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log("Step 4. Input stake and observe bet slip information");
        marketPage.addArtemisFancyOdds(fancyMarket, true, 1);
        marketPage.betsSlipContainer.inputStake(minStake);

        log("Validate Bet slip displays with info market name, selection (Yes [L]), stake inputted and liability (stake * (payout/100))");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), expectedWager.getMarketName(), String.format("FAILED! Market name does not display correctly in bet slip expected %s actual %s", expectedWager.getMarketName(), lstBetslip.get(0)));
        Assert.assertEquals(lstBetslip.get(1), fancyMarket.getSelection(), String.format("FAILED! Selection does not display correct expected %s actual %s", fancyMarket.getSelection(),lstBetslip.get(1)));
        Assert.assertEquals(lstBetslip.get(2), String.format("%.0f", expectedWager.getOdds()), String.format("FAILED! Odds does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getOdds()), lstBetslip.get(2)));
        Assert.assertEquals(lstBetslip.get(3), String.format("%.0f", expectedWager.getStake()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getStake()), lstBetslip.get(3)));
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getProfitFancyWager()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getProfitFancyWager()), lstBetslip.get(4)));
    }

    @TestRails(id = "15800")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15800() {
        log("@title: Validate bet slip information show correctly for Runner 2");
        log("@Precondition: Get the event that have Artemis Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 2);
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log("Step 4. Input stake and observe bet slip information");
        marketPage.addArtemisFancyOdds(fancyMarket, true, 2);
        marketPage.betsSlipContainer.inputStake(minStake);

        log("Validate Bet slip displays with info market name, selection (Yes [L]), stake inputted and liability (stake * (payout/100))");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), expectedWager.getMarketName(), String.format("FAILED! Market name does not display correctly in bet slip expected %s actual %s", expectedWager.getMarketName(), lstBetslip.get(0)));
        Assert.assertEquals(lstBetslip.get(1), fancyMarket.getSelection(), String.format("FAILED! Selection does not display correct expected %s actual %s", fancyMarket.getSelection(),lstBetslip.get(1)));
        Assert.assertEquals(lstBetslip.get(2), String.format("%.0f", expectedWager.getOdds()), String.format("FAILED! Odds does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getOdds()), lstBetslip.get(2)));
        Assert.assertEquals(lstBetslip.get(3), String.format("%.0f", expectedWager.getStake()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getStake()), lstBetslip.get(3)));
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getProfitFancyWager()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getProfitFancyWager()), lstBetslip.get(4)));
    }

    @TestRails(id = "15801")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15801() {
        log("@title: Validate exposure is kept correctly when place on Runner 1");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }

        log("Step 4 Active Artemis Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeArtemisFancy(fcMarket, true, minStake, 1);

        log("Verify 1. Validate Exposure kept correctly when place on Yes section");
        Double liabilityWager = expectedWager.getLiabilityFancyWager();
        String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), liabilityWager, 0.0);
        String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
        Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15802")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15802() {
        log("@title: Validate exposure is kept correctly when place on Runner 2");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }

        log("Step 4 Active Artemis Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 2);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeArtemisFancy(fcMarket, true, minStake, 2);

        log("Verify 1. Validate Exposure kept correctly when place on Yes section");
        Double liabilityWager = expectedWager.getLiabilityFancyWager();
        String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), liabilityWager, 0.0);
        String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
        Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));

        log("INFO: Executed completely");
    }
    @TestRails(id = "15803")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15803() {
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);

        String stake = String.format("%.0f", Double.valueOf(BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE)) - 1);

        log(String.format("Step 5. Click on an odds of a fancy market then place bet with the stake less than min bet: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeArtemisFancy(fcMarket, true, stake, 1);

        log("Validate Can NOT place bet");
        String expectedError = String.format(BetSlip.VALIDATE_STAKE_NOT_VALID, fcMarket.getMinSetting(), fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15804")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15804() {
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);
        String stake = String.format("%.0f", Double.valueOf(BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE)) + 1);

        log(String.format("Step 4. Click on an odds of a fancy market then place bet with the stake greater than max bet: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeArtemisFancy(fcMarket, true, stake, 1);

        log("Validate Can NOT place bet");
        String expectedError = String.format(BetSlip.VALIDATE_STAKE_NOT_VALID, fcMarket.getMinSetting(), fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15805")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15805() {
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake = String.format("%d", (int) (Double.valueOf(balance.getBalance().replaceAll(",", "")) + 1));

        log("Step 2. Active the event that have Fancy market");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Fancy Artemis on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);

        log(String.format("Step 4. Click on an odds of a fancy market then place bet with the stake  greater than availablie balance: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeArtemisFancy(fcMarket, true, stake, 1);

        log("Validate Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, BetSlip.ERROR_INSUFFICIENT_BALANCE, String.format("ERROR! Expected error message is %s but found %s", BetSlip.ERROR_INSUFFICIENT_BALANCE, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15806")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15806() {
        log("@title: Validate forecast displays correctly when place matched bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Artemis Fancy");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_RUNNER_TYPE);
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Fancy Artemis");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }

        log("Step 3 Active Artemis Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);
        String minStake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log("Step 4: Click on an odds of any runner (e.g runner 1) of market then place bet with the valid stake (e.g 10) then wait until bet matches");
        log(String.format("Step 4 INFO: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeArtemisFancy(fcMarket, true, minStake, 1);

        log("Step 5: Observe forecast of Runner 1 and 2 show on market page");
        log("Verify 1. Forecast displays correctly with:\n" +
                "Runner 1 = stake *(odds/100)\n" +
                "Runner 2 = - stake");
        FancyMarket fancyMarketRunner1 = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);
        FancyMarket fancyMarketRunner2 = marketPage.getArtemisFancyMarketInfo(fcMarket, 2);
        Assert.assertEquals(String.format("%.2f",fancyMarketRunner1.getMarketLiability()), String.format("%.2f",expectedWager.getProfitFancyWager()), "FAILED! Forecast runner 1 is not correct");
        Assert.assertEquals(String.format("%.2f",fancyMarketRunner2.getMarketLiability()), String.format("%s%.2f","-",expectedWager.getLiabilityFancyWager()), "FAILED! Forecast runner 1 is not correct");

        log("INFO: Executed completely");
    }

    //Multi Bet cases
    @TestRails(id = "15807")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void ArtemisFancyTest_15807() {
        log("@title: Validate Ladder forecast score does not display");
        log("@Precondition: Get the event that have Artemis Fancy has multi bet selection");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Artemis Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenArtemisFancyMarketByRunner(SPORT_ID.get(LBL_CRICKET_SPORT), MULTI_BET_TYPE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Artemis Fancy");
            Assert.assertTrue(true, "By passed as has no Artemis Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(ARTEMIS_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getArtemisFancyMarketInfo(fcMarket, 1);

        log("Step 4. Click on ladder icon on any Fancy market");
        marketPage.openFancyLadderForecast(fancyMarket);
        log("Validate Forecast popup displays with name of selecting market");
        Assert.assertFalse(marketPage.isLadderForecastDisplay(fancyMarket), "FAILED! Ladder forecast does not show correct");
        log("INFO: Executed completely");
    }
}

