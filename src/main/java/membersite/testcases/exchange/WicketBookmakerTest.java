package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.Market;
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

public class WicketBookmakerTest extends BaseCaseTest {
    @TestRails(id = "535")
    @Test(groups = {"smoke", "2024.01.19", "nolan_stabilize"})
    public void WicketBookmakerTest_535() {
        log("@title: Validate can place bet on Wicket Bookmaker on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        String stake = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_BOOKMAKER_CODE, "ONLINE");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        memberHomePage.leftMenu.openBookmakerMarket(WICKET_BOOKMAKER);

        log("Step 5 Get Wicket Bookmaker market has runner available");
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }

        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("", stake);
        Wager expectedWager = marketPage.defineWager(market, true, Double.parseDouble(stake), 0);

        log("Verify 1. Can place bet. Info in my bet is correct");
        List<ArrayList> lstFCBet = marketPage.getBookmakerMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0), market.getSelectionName(), "FAILED! Selection Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager.getRunnerName(), "FAILED! Selection Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(1), String.format("%,.0f", expectedWager.getOdds()), "FAILED! Odds is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(2), String.format("%,.2f", Double.valueOf(stake)), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(3), String.format("%,.2f", expectedWager.getProfitWicketBookmakerWager()), "FAILED! Liability is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "536")
    @Test(groups = {"smoke", "2024.01.19", "nolan_stabilize"})
    public void WicketBookmakerTest_536() {
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        String maxBet = BetUtils.getMaxBet(LBL_CRICKET_SPORT, LBL_LAY_TYPE);
        String stake = Integer.toString(Integer.parseInt(minBet) - 1);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_BOOKMAKER_CODE, "ONLINE");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        memberHomePage.leftMenu.openBookmakerMarket(WICKET_BOOKMAKER);

        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("", stake);

        log("Verify 1. Error Cannot place bet display: The stake must be from <min> to <max>. Current Stake is <stake>.");
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        String expectedError = String.format(BetSlip.VALIDATE_STAKE_NOT_VALID, minBet, maxBet, stake);
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "537")
    @Test(groups = {"smoke", "2024.01.19", "nolan_stabilize"})
    public void WicketBookmakerTest_537() {
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        String maxBet = BetUtils.getMaxBet(LBL_CRICKET_SPORT, LBL_LAY_TYPE);
        String stake = Integer.toString(Integer.parseInt(maxBet) + 1);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_BOOKMAKER_CODE, "ONLINE");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        memberHomePage.leftMenu.openBookmakerMarket(WICKET_BOOKMAKER);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, false);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("", stake);

        log("Verify 1. Error Cannot place bet display: Error : Cannot place bet. The stake must be from <min> to <max>. Current Stake is <stake>.");
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        String expectedError = String.format(BetSlip.VALIDATE_STAKE_NOT_VALID, minBet, maxBet, stake);
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "538")
    @Test(groups = {"smoke", "2024.01.19", "nolan_stabilize"})
    public void WicketBookmakerTest_538() {
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and click on Cricket");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake = String.format("%d", (int) (Double.valueOf(balance.getBalance().replaceAll(",", "").toString()) + 1));
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), WICKET_BOOKMAKER_CODE, "ONLINE");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        memberHomePage.leftMenu.openBookmakerMarket(WICKET_BOOKMAKER);

        log("Step 5 Click on an odds of a Bookmaker market then place bet with the stake  greater than available balance");
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("", stake);

        log("Verify 1. Error Cannot place bet display: Error: Cannot place bet. Your Main balance is insufficient.");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, BetSlip.ERROR_INSUFFICIENT_BALANCE, String.format("ERROR! Expected error message is %s but found %s", BetSlip.ERROR_INSUFFICIENT_BALANCE, actualError));

        log("INFO: Executed completely");
    }


}

