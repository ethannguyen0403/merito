package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.Market;
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

public class ArtemisBookmakerTest extends BaseCaseTest {
    @TestRails(id = "2058")
    @Test(groups = {"smoke"})
    public void ArtemisBMTest_2058() {
        log("@title: Validate correct Forecast displays");
        log("Step 1. Login member site");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Open Match odds market of any Artemis event");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker");
            Assert.assertTrue(true, "By passed as has no Artemis Bookmaker on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 3. Added an odds into Bet slip and enter Stake");
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        String maxBet = BetUtils.getMaxBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.inputStake(maxBet);

        log("Validate The correct forecast liability with the same formula of Wicket Bookmaker displays");
        List<ArrayList> lstBMBetSlip = marketPage.getBookmakerBetSlipMiniMyBet();
        Wager wg = marketPage.defineBookmakerWager(bookmakerMarket, true, Double.valueOf(maxBet));
        Assert.assertEquals(Double.valueOf(String.valueOf(lstBMBetSlip.get(3)).replace(",","")), wg.getProfitWicketBookmakerWager(), "FAILED! Profit/Liability does not show correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "2247")
    @Test(groups = {"smoke"})
    public void ArtemisBMTest_2247() {
        log("@title: Validate can place Artemis Bookmaker bet on Match odds market page");
        log("Step 1. Login member site");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Open Match odds market of any Artemis event");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker");
            Assert.assertTrue(true, "By passed as has no Artemis Bookmaker on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 3. Click on an odds of an Artemis Bookmaker market then place bet");
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }

        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(minBet);
        Wager expectedWager = marketPage.defineBookmakerWager(bookmakerMarket, true, Double.valueOf(minBet));

        log("Validate Placed bet is displayed with correct info (odd, stake, pnl) on mini My Bets");
        List<ArrayList> lstBMBetSlip = marketPage.getBookmakerMiniMyBet();

        Assert.assertTrue(Objects.nonNull(lstBMBetSlip), "FAILED! Artemis Bookmaker my bet section does NOT display");
        Assert.assertEquals(lstBMBetSlip.get(0).get(0), expectedWager.getMarketName(), "FAILED! Artemis Bookmaker Market Name is incorrect");
        Assert.assertEquals(lstBMBetSlip.get(0).get(0), expectedWager.getRunnerName(), "FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstBMBetSlip.get(0).get(1), expectedWager.getOdds(), "FAILED! Odd is incorrect");
        Assert.assertEquals(lstBMBetSlip.get(0).get(2), String.format("%.2f", Double.valueOf(minBet)), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstBMBetSlip.get(0).get(3), String.format("%.2f", expectedWager.getProfitWicketBookmakerWager()), "FAILED! Liability is incorrect");
        log("INFO: Executed completely");
    }
    @TestRails(id = "2248")
    @Test(groups = {"smoke"})
    public void ArtemisBMTest_2248() {
        log("@title: Validate cannot place bet if stake less than min bet");
        log("Step 1. Login member site");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Open Match odds market of any Artemis event");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker");
            Assert.assertTrue(true, "By passed as has no Artemis Bookmaker on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 3. Click on an odds of an Artemis Bookmaker market then place bet");
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }

        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        String maxBet = BetUtils.getMaxBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        String stake = Integer.toString(Integer.parseInt(minBet) - 1);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(stake);

        log("Validate An error message should display as 'The stake must be from [min stake] to [max stake]. Current Stake is [current stake]");
        String actualErrorMsg = marketPage.myBetsContainer.getBetslipErrorMessage();
        String expectedErrorMsg = String.format(BetSlip.VALIDATE_STAKE_NOT_VALID, minBet, maxBet, stake);
        Assert.assertEquals(expectedErrorMsg, actualErrorMsg, String.format("FAILED! Error message does not display correct expected %s but found %s", expectedErrorMsg, actualErrorMsg));
        log("INFO: Executed completely");
    }

    @TestRails(id = "2765")
    @Test(groups = {"smoke"})
    public void ArtemisBMTest_2765() {
        log("@title: Validate correct odds display when place Bookmaker market");
        log("Step 1. Login member site");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Open Match odds market of any Artemis event");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker");
            Assert.assertTrue(true, "By passed as has no Artemis Bookmaker on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 3. Place matched bets");
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }

        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(minBet);
        Wager expectedWager = marketPage.defineBookmakerWager(bookmakerMarket, true, Double.valueOf(minBet));
        log("Step 4. Observe odds on mini my bet");
        log("Validate Placed bet is displayed with correct info (odd, stake, pnl) on mini My Bets");
        List<ArrayList> lstBMBetSlip = marketPage.getBookmakerMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstBMBetSlip), "FAILED! Artemis Bookmaker my bet section does NOT display");
        Assert.assertEquals(lstBMBetSlip.get(0).get(1), expectedWager.getOdds(), "FAILED! Odd is incorrect");
        log("Step 5. Navigate to My Bets page and search the matched bet then observe odds");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("MATCHED"));
        page.verifyWagerInfo(expectedWager);
        log("INFO: Executed completely");
    }

}
