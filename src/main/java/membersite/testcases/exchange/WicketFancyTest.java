package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
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

public class WicketFancyTest extends BaseCaseTest {
    @TestRails(id = "611")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_611() {
        log("@title: Validate can place bet on Fancy on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2. Active the event that has Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on an odds of a fancy market then place bet");
        memberHomePage.leftMenu.openFancyMarket(WICKET_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fcMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fcMarket, true, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fcMarket, true, minStake);

        log("Validate Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager.getMarketName(), "FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(1), expectedWager.getRunnerName(), "FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(2), expectedWager.displayFancyOdds(), "FAILED! Odds is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(3), String.format("%.2f", (double) fcMarket.getMinSetting()), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(3), String.format("%.2f", expectedWager.getLiabilityFancyWager()), "FAILED! Liability is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "612")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_612() {
        log("@title: Validate exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2. Active the event that has Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(WICKET_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4. Click on an odds of a fancy market then place bet");
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Validate Exposure kept correctly when place on No section");
        Double liabilityWager = expectedWager.getLiabilityFancyWager();
        String liabilityExpected = memberHomePage.header.calculateLiabilityAfterPlaceBet(String.valueOf(liabilityBeforePlaceBet), 0.0, liabilityWager);
        String liabilityAfterPlaceBet = marketPage.header.getUserBalance().getExposure();
        Assert.assertEquals(liabilityAfterPlaceBet, liabilityExpected, String.format("FAILED! Liability does not show correct expected %s but actual %s", liabilityExpected, liabilityAfterPlaceBet));
        log("INFO: Executed completely");
    }

    @TestRails(id = "613")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_613() {
        log("@title: Validate exposure is kept correctly when place on Yes and No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2. Active the event that has Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(WICKET_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4 Click on an odds and place on Yes and No option");
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
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

    @TestRails(id = "614")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_614() {
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(WICKET_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        String stake = String.format("%.0f", Double.valueOf(fcMarket.getMinSetting()) - 1);

        log(String.format("Step 5. Click on an odds of a fancy market then place bet with the stake less than min bet: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Validate Can NOT place bet");
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID, fcMarket.getMinSetting(), fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "615")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_615() {
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(WICKET_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String stake = String.format("%.0f", Double.valueOf(fcMarket.getMaxSetting()) + 1);

        log(String.format("Step 4. Click on an odds of a fancy market then place bet with the stake greater than max bet: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Validate Can NOT place bet");
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID, fcMarket.getMinSetting(), fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "616")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_616() {
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake = String.format("%d", (int) (Double.valueOf(balance.getBalance().replaceAll(",", "").toString()) + 1));

        log("Step 2. Active the event that have Fancy market");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on a Fancy market");
        memberHomePage.leftMenu.openFancyMarket(WICKET_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log(String.format("Step 4. Click on an odds of a fancy market then place bet with the stake  greater than availablie balance: market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Validate Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, BetSlip.ERROR_INSUFFICIENT_BALANCE, String.format("ERROR! Expected error message is %s but found %s", BetSlip.ERROR_INSUFFICIENT_BALANCE, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15773")
    @Test(groups = {"smoke"})
    public void CentralFancyTest_15773() {
        log("@title: Validate My Bet display correct after place bet on Wicket Fancy market");
        log("@Precondition: Get the event that have Wicket Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Wicket Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Wicket Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on an odds of a fancy market then place bet (wait until bet matched)");
        memberHomePage.leftMenu.openFancyMarket(WICKET_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log(String.format("INFO: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, true, minStake);

        log("Step 4. Navigate to My Bets page and filter the matched bet and observe information");
        MyBetsPage myBetsPage = memberHomePage.openMyBet();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("MATCHED"));

        log("Validate Information of placed bet displays correctly");
        myBetsPage.verifyWagerInfo(expectedWager);
        log("INFO: Executed completely");
    }

}

