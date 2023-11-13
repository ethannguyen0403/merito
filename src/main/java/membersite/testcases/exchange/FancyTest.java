package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.FancyMarket;
import membersite.pages.MarketPage;
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

public class FancyTest extends BaseCaseTest {
    @TestRails(id = "549")
    @Test(groups = {"smoke"})
    public void FancyTest_549() {
        log("@title: Validate can place bet on Fancy on Match odds market page");
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

        log("Step 4 Active 27 Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));
        String oddsActual = expectedWager.displayFancyOdds();

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, true, minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager.getMarketName(), "FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0), expectedWager.getRunnerName(), "FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1), oddsActual, "FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2), String.format("%.2f", (double) fancyMarket.getMinSetting()), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3), String.format("%.2f", expectedWager.getLiabilityFancyWager()), "FAILED! Liability is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "550")
    @Test(groups = {"smoke"})
    public void FancyTest_550() {
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        log("Step 2 Get and click on the event that has 27 Fancy");
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active 27 Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager.getMarketName(), "FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0), expectedWager.getRunnerName(), "FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1), expectedWager.displayFancyOdds(), "FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2), String.format("%.2f", (double) fancyMarket.getMinSetting()), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3), String.format("%.2f", expectedWager.getLiabilityFancyWager()), "FAILED! Liability is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "551")
    @Test(groups = {"smoke"})
    public void FancyTest_551() {
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        AccountBalance balance = sportPage.header.getUserBalance();

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true, "By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 2.1. Get 27 Fancy available");
        log("Step 4. Active 27 Fancy Market int the left menu");
        marketPage = marketPage.clickMarketonLeftMenu(fcMarket.getMarketName());

        log("Step 4 Active 27 Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        List<Wager> lstMatchedBets = BetUtils.getMatchedOpenBet(SPORT_ID.get(LBL_CRICKET_SPORT), fancyMarket.getEventID(), fancyMarket.getMarketID(), "FANCY");
        Double liabilityBeforePlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);

        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));
        Wager expectedWager2 = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));


        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, true, minStake);
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager2.getMarketName(), "FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0), expectedWager2.getRunnerName(), "FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1), expectedWager2.displayFancyOdds(), "FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2), String.format("%.2f", (double) fancyMarket.getMinSetting()), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3), String.format("%.2f", expectedWager2.getLiabilityFancyWager()), "FAILED! Liability is incorrect");
        Assert.assertEquals(lstFCBet.get(2).get(0), expectedWager.getMarketName(), "FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(0), expectedWager.getRunnerName(), "FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(1), expectedWager.displayFancyOdds(), "FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(2), String.format("%.2f", (double) fancyMarket.getMinSetting()), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(3), String.format("%.2f", expectedWager2.getLiabilityFancyWager()), "FAILED! Liability is incorrect");

        List<Wager> lstMatchedBetsAfterPlaceBet = BetUtils.getMatchedOpenBet(SPORT_ID.get(LBL_CRICKET_SPORT), fancyMarket.getEventID(), fancyMarket.getMarketID(), "FANCY");
        //      Double liabilityBeforePlaceBetAfterPlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);
        fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityAfterPlaceBet = marketPage.liabilityFCMarket(lstMatchedBetsAfterPlaceBet);
        Assert.assertEquals(fancyMarket.getMarketLiability(), liabilityAfterPlaceBet, "FAILED! Market Liability is incorrect");
        String expectedBalance = marketPage.calculateBalanceAfterPlaceBet(balance.getBalance(), liabilityBeforePlaceBet, liabilityAfterPlaceBet);

        String balanceActual = marketPage.header.getUserBalance().getBalance();
        Assert.assertEquals(balanceActual, expectedBalance, "FAILED! Available balance is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "552")
    @Test(groups = {"smoke"})
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

        log("Step 3. Get 27 Fancy available");
        log("Step 4 Active 27 Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String minStake = marketPage.getMinMaxOFFancyMarket(minMax, true);
        String stake = Double.toString(Double.valueOf(minStake) - 1);
        String maxStake = marketPage.getMinMaxOFFancyMarket(minMax, false);

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f", Double.parseDouble(minStake)), String.format("%(,.2f", Double.parseDouble(maxStake)), String.format("%.2f", Double.parseDouble(stake)));
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "553")
    @Test(groups = {"smoke"})
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
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "554")
    @Test(groups = {"smoke"})
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
}
