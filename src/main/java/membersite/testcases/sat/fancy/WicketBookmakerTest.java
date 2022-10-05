package membersite.testcases.sat.fancy;

import membersite.common.FEMemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerEvent;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Market;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import membersite.utils.betplacement.FancyUtils;
import membersite.utils.betplacement.WicketBookmakerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static membersite.common.FEMemberConstants.*;

public class WicketBookmakerTest extends BaseCaseMerito {
    /**
     * @title Validate can place bet on Central Bookmaker on Match odds market page
     * @Precondition:  1. Get the event that have Central Bookmaker market
     * @Step 1/ Login member site
     * 2/ Active the event that have Central Bookmaker market
     * 3/ Click Match odds
     * 4/ Click on an odds of a Central Bookmaker market then place bet
     * @Expected 1. Can place bet
     */
    @Test(groups = {"smoke2"})
    public void WicketBookmakerTest_001(){
        log("@title: Validate can place bet on Central Bookmaker on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        String stake = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);

        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has Central Bookmaker market");
        String eventId = sportPage.getEventIDHasProductData(WICKET_BOOKMAKER_CODE);
        MarketPage marketPage = sportPage.clickOnEvent(eventId);
        if(Objects.isNull(marketPage)){
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }

        log("Step 3. Get Central Bookmaker market available");
        List<BookmakerMarket> lstBookmaker = FancyUtils.getListCentralBookmakerInEvent(eventId);

        if(Objects.isNull(lstBookmaker)){
            log("DEBUG: Skip as have no Central Bookmaker market is Ball Start or Suspend");
            Assert.assertFalse(true,"By passed as has no Central Bookmaker market on all available event");
            return;
        }
        BookmakerMarket bookmakerMarket = lstBookmaker.get(0);

        log("Step 4 Active Central Fancy tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);

        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,true);
        market.getBtnOdd().click();
        marketPage.betSlipControlSAT.placeBet("",stake);
        Wager expectedWager = marketPage.defineWager(market,true,Double.parseDouble(stake),0);

        log("Verify 1. Can place bet. Info in my bet is correct");
        List<ArrayList> lstFCBet = marketPage.getBookmakerMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),expectedWager.getRunnerName(),"FAILED! Selection Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(1),String.format("%,.2f",Double.valueOf(expectedWager.getOdds())),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(2),String.format("%,.2f",Double.valueOf(stake)),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(3),String.format("%,.2f",expectedWager.getProfitWicketBookmakerWager()),"FAILED! Liability is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake less than min bet
     * @Precondition:  1. Get the event that have Central Bookmaker market
     * @Step 1/ Login member site
     * 2/Active the event that have Bookmaker market
     * 3/Click on a Bookmaker market
     * 4/ Click on an odds of a Bookmaker market then place bet with the stake less than min bet
     * @Expected 1. Can NOT place bet
     */
    @Test(groups = {"smoke"})
    @Parameters({"isCredit"})
    public void WicketBookmakerTest_003(boolean isCredit){
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);
        String stake = Integer.toString(Integer.parseInt(minBet)-1);
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has Central Bookmaker market");
        String eventId = sportPage.getEventIDHasProductData(WICKET_BOOKMAKER_CODE);
        MarketPage marketPage = sportPage.clickOnEvent(eventId);
        if(Objects.isNull(marketPage)){
            Assert.assertTrue(true,"By passed as has no Central Bookmaker market on all available event");
            return;
        }

        log("Step 3. Get Central Bookmaker market available");
        List<BookmakerMarket> lstFancy = FancyUtils.getListWicketBookmakerInEvent(eventId);
        BookmakerMarket bookmakerMarket = lstFancy.get(0);

        log("Step 4 Active Central Fancy tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);

        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,true);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Central Bookmaker market");
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        marketPage.betSlipControlSAT.placeBet("",stake);

        log("Verify 1. Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
        String expectedError = String.format(FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minBet)),String.format("%(,.2f",Double.parseDouble(maxBet)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake greater than max bet
     * @Precondition:  1. Get the event that have Central Bookmaker market
     * @Step 1/ Login member site
     * 2/Active the event that have Bookmaker market
     * 3/Click on a Bookmaker market
     * 4/ Click on an odds of a Bookmaker market then place bet with the stake greater than max bet
     * @Expected 1. Can NOT place bet
     */
    @Test(groups = {"smoke"})
    public void WicketBookmakerTest_004(){
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);
        String stake = Integer.toString(Integer.parseInt(maxBet)+1);
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has Central Bookmaker market");
        String eventId = sportPage.getEventIDHasProductData(WICKET_BOOKMAKER_CODE);
        MarketPage marketPage = sportPage.clickOnEvent(eventId);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Central Bookmaker market");
            Assert.assertTrue(true,"By passed as has no Central Bookmaker market on all available event");
            return;
        }

        log("Step 3. Get Central Bookmaker market available");
        List<BookmakerMarket> lstFancy = FancyUtils.getListCentralBookmakerInEvent(eventId);
        BookmakerMarket bookmakerMarket = lstFancy.get(0);

        log("Step 4 Active Central Fancy tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,false);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Central Bookmaker market");
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        marketPage.betSlipControlSAT.placeBet("",stake);

        log("Verify 1. Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),Double.parseDouble(minBet),Double.parseDouble(maxBet),BetUtils.getUserBalance());
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake less is greater than available balance
     * @Precondition:  1. Get the event that have Central Bookmaker market
     * @Step 1/ Login member site
     * 2/Active the event that have Bookmaker market
     * 3/Click on a Bookmaker market
     * 4/ Click on an odds of a Bookmaker market then place bet with the stake  greater than available balance
     * @Expected 1. Can NOT place bet
     */
    @Test(groups = {"smoke2"})
    public void WicketBookmakerTest_005(){
        log("@title: Verify Cannot place bet if stake less is greater than availablie balance");
        log("Step 1. Login member site and click on Cricket");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake =String.format("%d",(int) (Double.valueOf(balance.getBalance().replaceAll(",", "").toString())+ 1));
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has Central Bookmaker market");
        String eventId = sportPage.getEventIDHasProductData(WICKET_BOOKMAKER_CODE);
        MarketPage marketPage = sportPage.clickOnEvent(eventId);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Central Bookmaker market");
            Assert.assertTrue(true,"By passed as has no Central Bookmaker market on all available event");
            return;
        }

        log("Step 3. Get Central Bookmaker market available");
        List<BookmakerMarket> lstFancy = FancyUtils.getListCentralBookmakerInEvent(eventId);
        BookmakerMarket bookmakerMarket = lstFancy.get(0);

        log("Step 4 Active Central Fancy tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);

        log("Step 5 Click on an odds of a Bookmaker market then place bet with the stake  greater than available balance");
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,true);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Central Bookmaker market");
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        marketPage.betSlipControlSAT.placeBet("",stake);

        log("Verify 1. Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError,FEMemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,String.format("ERROR! Expected error message is %s but found %s", FEMemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,actualError));

        log("INFO: Executed completely");
    }





}

