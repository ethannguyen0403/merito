package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import common.MemberConstants;
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

public class FancyTest extends BaseCaseTest {
    @TestRails(id = "549")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_549() {
        log("@title: Validate can place bet on Fancy market page");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarketWithSameOdds(SPORT_ID.get(LBL_CRICKET_SPORT));
//        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Active 27 Fancy tab, Click on an odds of a fancy market then place bet");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));
        String oddsActual = expectedWager.displayFancyOdds();
        try {
            log(String.format("INFO: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
            marketPage.placeFancy(fancyMarket, true, minStake);

            log("Validate Can place bet");
            List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
            Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
            Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager.getMarketName(), "FAILED! FC Market Name is incorrect");
            Assert.assertEquals(lstFCBet.get(0).get(1), expectedWager.getRunnerName(), "FAILED! Runner Name is incorrect");
            Assert.assertEquals(lstFCBet.get(0).get(2), oddsActual, "FAILED! Odd is incorrect");
            Assert.assertEquals(lstFCBet.get(0).get(3), String.format("%.2f", (double) fancyMarket.getMinSetting()), "FAILED! Stake is incorrect");
            Assert.assertEquals(lstFCBet.get(0).get(4), String.format("%.2f", expectedWager.getProfitFancyWager()), "FAILED! Liability is incorrect");

            log("INFO: Executed completely");
        } finally {
            log("Post-condition: Place on opposite selection to reduce liability");
            marketPage.placeFancy(fancyMarket, false, minStake);
        }

    }

    @TestRails(id = "550")
    @Test(groups = {"smoke1"})
    public void FancyTest_550() {
        log("@title: Validate exposure is kept correctly when place on No");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        FancyMarket fcMarket = BetUtils.findOpenFancyMarketWithSameOdds(SPORT_ID.get(LBL_CRICKET_SPORT));
//        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        log("Step 2 Get and click on the event that has 27 Fancy");
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Active 27 Fancy tab, Click on an odds of No selection of a fancy market then place bet");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));

        try {
            log(String.format("INFO: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
            marketPage.placeFancy(fancyMarket, false, minStake);

            log("Validate Exposure kept correctly when place on No section");
            String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), null, expectedWager);
            String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
            Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));
            log("INFO: Executed completely");
        } finally {
            log("Post-condition: Place on opposite selection to reduce liability");
            marketPage.placeFancy(fancyMarket, true, minStake);
        }

    }

    @TestRails(id = "15753")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15753() {
        log("@title: Validate exposure is kept correctly when place on Yes");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        FancyMarket fcMarket = BetUtils.findOpenFancyMarketWithSameOdds(SPORT_ID.get(LBL_CRICKET_SPORT));
//        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        log("Step 2 Active the event that have Fancy market");
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Active 27 Fancy tab, Click on an odds of No selection of a fancy market then place bet");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        try {
            log(String.format("INFO: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
            marketPage.placeFancy(fancyMarket, true, minStake);

            log("Validate Exposure kept correctly when place on Yes selection (current liability + (stake * (payout/100)))");
            String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), expectedWager, null);
            String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
            Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));
            log("INFO: Executed completely");
        } finally {
            log("Post-condition: Place on opposite selection to reduce liability");
            marketPage.placeFancy(fancyMarket, false, minStake);
        }

    }

    @TestRails(id = "551")
    @Test(groups = {"smoke1"})
    public void FancyTest_551() {
        log("@title: Validate exposure is kept correctly when place on Yes and No");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        FancyMarket fcMarket = BetUtils.findOpenFancyMarketWithSameOdds(SPORT_ID.get(LBL_CRICKET_SPORT));
//        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        log("Step 2 Get and click on the event that has 27 Fancy");
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Active 27 Fancy tab, Click on an odds of No and Yes selection of a fancy market then place bet");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));
        Wager expectedWager2 = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, true, minStake);
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Validate Exposure kept correctly when place on Yes and No section");
        String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), expectedWager, expectedWager2);
        String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
        Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));
        log("INFO: Executed completely");
    }

    @TestRails(id = "552")
    @Test(groups = {"smoke1"})
    public void FancyTest_552() {
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3 Active 27 Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String minStake = marketPage.getMinMaxOFFancyMarket(minMax, true);
        String stake = Double.toString(Double.valueOf(minStake) - 1);
        String maxStake = marketPage.getMinMaxOFFancyMarket(minMax, false);

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f", Double.parseDouble(minStake)), String.format("%(,.2f", Double.parseDouble(maxStake)), String.format("%.2f", Double.parseDouble(stake)));
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "553")
    @Test(groups = {"smoke1"})
    public void FancyTest_553() {
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 3. Get 27 Fancy available");
        log("Step 4 Active 27 Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String minStake = marketPage.getMinMaxOFFancyMarket(minMax, true);
        String maxStake = marketPage.getMinMaxOFFancyMarket(minMax, false);
        String stake = Double.toString(Double.valueOf(maxStake) + 1);
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake), Double.parseDouble(minStake), Double.parseDouble(maxStake), BetUtils.getUserBalance());

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "554")
    @Test(groups = {"smoke1"})
    public void FancyTest_554() {
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake = Double.toString(Double.valueOf(balance.getBalance().replaceAll(",", "").toString()) + 1);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String minStake = marketPage.getMinMaxOFFancyMarket(minMax, true);
        String maxStake = marketPage.getMinMaxOFFancyMarket(minMax, false);
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake), Double.parseDouble(minStake), Double.parseDouble(maxStake), BetUtils.getUserBalance());

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15755")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15755() {
        log("@title: Validate able navigate to Fancy market page");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        log("Step 2 From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Validate Event market page displays with Fancy market");
        Assert.assertTrue(!Objects.isNull(fancyMarket), "FAILED! Cannot find out Fancy market on event page");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15763")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15763() {
        log("@title: Validate My Bet display correct after place bet on Fancy market");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarketWithSameOdds(SPORT_ID.get(LBL_CRICKET_SPORT));
//        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on an odds of a fancy market then place bet (wait until bet matched)");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log(String.format("INFO: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
        //place on opposite selection (Lay) to reduce liability
        marketPage.placeFancy(fancyMarket, false, minStake);
        marketPage.placeFancy(fancyMarket, true, minStake);

        log("Step 4. Navigate to My Bets page and filter the matched bet and observe information");
        MyBetsPage myBetsPage = memberHomePage.openMyBet();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("MATCHED"));

        log("Validate Information of placed bet displays correctly");
        myBetsPage.verifyWagerInfo(expectedWager);
        log("INFO: Executed completely");
    }

    @TestRails(id = "15756")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15756() {
        log("@title: Validate able to open Ladder forecast score");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4. Click on ladder icon on any Fancy market");
        marketPage.openFancyLadderForecast(fancyMarket);
        log("Validate Forecast popup displays with name of selecting market");
        Assert.assertTrue(marketPage.isLadderForecastDisplay(fancyMarket), "FAILED! Ladder forecast does not show correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15757")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15757() {
        log("@title: Validate bet slip is cleared when navigate to another market");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
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

    @TestRails(id = "15758")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15758() {
        log("@title: Validate able to choose multi selection");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi selection from any market");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.addFancyOdds(fancyMarket, false);

        log("Validate Able to choose multi selection (selected selections show in bet slip)");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), fcMarket.getMarketName(), "FAILED! Bet 1 selection does not add into bet slip correctly");
        Assert.assertEquals(lstBetslip.get(5), fcMarket.getMarketName(), "FAILED! Bet 2 selection does not add into bet slip correctly");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15759")
    @Test(groups = {"smoke_stg", "2024.01.19"})
    public void FancyTest_15759() {
        log("@title: Validate multi tab (bet slip) is disabled");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi selection from any market");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.addFancyOdds(fancyMarket, false);

        log("Validate Multi tab (bet slip) is disabled and does not work when click on");
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipEnabled(), "FAILED! Multi tab in bet slip is enabled");
        marketPage.myBetsContainer.clickMultiTabBetSlip();
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipSelected(), "FAILED! Multi tab in bet slip is enabled");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15760")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15760() {
        log("@title: Validate bet slip information show correctly for selection Yes");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
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
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getProfitFancyWager()),String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getProfitFancyWager()), lstBetslip.get(4)));
    }

    @TestRails(id = "15761")
    @Test(groups = {"smoke1", "2024.01.19"})
    public void FancyTest_15761() {
        log("@title: Validate bet slip information show correctly for selection Yes");
        log("@Precondition: Get the event that have Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
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
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getLiabilityFancyWager()),String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getLiabilityFancyWager()), lstBetslip.get(4)));
    }
}
