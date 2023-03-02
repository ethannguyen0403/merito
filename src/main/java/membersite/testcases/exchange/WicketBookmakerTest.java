package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import membersite.utils.betplacement.FancyUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.MemberConstants.*;

public class WicketBookmakerTest extends BaseCaseTest {
    /**
     * @title Validate can place bet on Wicket Bookmaker on Match odds market page
     * @Precondition:  1. Get the event that have Central Bookmaker market
     * @Step 1/ Login member site
     * 2/ Active the event that have Central Bookmaker market
     * 3/ Click Match odds
     * 4/ Click on an odds of a Central Bookmaker market then place bet
     * @Expected 1. Can place bet
     */
    @TestRails(id="535")
    @Test(groups = {"smoke"})
    public void WicketBookmakerTest_535(){
        log("@title: Validate can place bet on Central Bookmaker on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        String stake = BetUtils.getMinBet("CRICKET", "BACK");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.header.navigateSportMenu(sportName, _brandname);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket("4",WICKET_BOOKMAKER_CODE,"ONLINE");
        if(Objects.isNull(bookmakerMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);

        log("Step 5 Get Wicket Bookmaker market has runner available");
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,true);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }

        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("",stake);
        Wager expectedWager = marketPage.defineWager(market,true,Double.parseDouble(stake),0);

        log("Verify 1. Can place bet. Info in my bet is correct");
        List<ArrayList> lstFCBet = marketPage.getBookmakerMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),market.getMarketName(),"FAILED! Selection Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0),expectedWager.getRunnerName(),"FAILED! Selection Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1),String.format("%,.f",Double.valueOf(expectedWager.getOdds())),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2),String.format("%,.2f",Double.valueOf(stake)),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3),String.format("%,.2f",expectedWager.getProfitWicketBookmakerWager()),"FAILED! Liability is incorrect");
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
    @TestRails(id="536")
    @Test(groups = {"smoke"})
    public void WicketBookmakerTest_536(){
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        String minBet = BetUtils.getMinBet("CRICKET", "BACK");
        String maxBet = BetUtils.getMaxBet("CRICKET", "LAY");
        String stake = Integer.toString(Integer.parseInt(minBet)-1);
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.header.navigateSportMenu(sportName, _brandname);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket("4",WICKET_BOOKMAKER_CODE,"ONLINE");
        if(Objects.isNull(bookmakerMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);

        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,true);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("",stake);

        log("Verify 1. Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedError = String.format(BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minBet)),String.format("%(,.2f",Double.parseDouble(maxBet)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake greater than max bet
     * @Precondition:  1. Get the event that have Wicket Bookmaker market
     * @Step 1/ Login member site
     * 2/Active the event that have Bookmaker market
     * 3/Click on a Bookmaker market
     * 4/ Click on an odds of a Bookmaker market then place bet with the stake greater than max bet
     * @Expected 1. Can NOT place bet
     */
    @TestRails(id="537")
    @Test(groups = {"smoke"})
    public void WicketBookmakerTest_537(){
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        String minBet = BetUtils.getMinBet("CRICKET", "BACK");
        String maxBet = BetUtils.getMaxBet("CRICKET", "LAY");
        String stake = Integer.toString(Integer.parseInt(maxBet)+1);
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.header.navigateSportMenu(sportName, _brandname);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket("4",WICKET_BOOKMAKER_CODE,"ONLINE");
        if(Objects.isNull(bookmakerMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,false);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
       marketPage.betsSlipContainer.placeBet("",stake);

        log("Verify 1. Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),Double.parseDouble(minBet),Double.parseDouble(maxBet),BetUtils.getUserBalance());
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake less is greater than available balance
     * @Precondition:  1. Get the event that have Wicket Bookmaker market
     * @Step 1/ Login member site
     * 2/Active the event that have Bookmaker market
     * 3/Click on a Bookmaker market
     * 4/ Click on an odds of a Bookmaker market then place bet with the stake  greater than available balance
     * @Expected 1. Can NOT place bet
     */
    @TestRails(id="538")
    @Test(groups = {"smoke"})
    public void WicketBookmakerTest_538(){
        log("@title: Verify Cannot place bet if stake less is greater than availablie balance");
        log("Step 1. Login member site and click on Cricket");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake =String.format("%d",(int) (Double.valueOf(balance.getBalance().replaceAll(",", "").toString())+ 1));
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.header.navigateSportMenu(sportName, _brandname);

        log("Step 2 Get the event that has Wicket Bookmaker market");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket("4",WICKET_BOOKMAKER_CODE,"ONLINE");
        if(Objects.isNull(bookmakerMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        log("Step 3. Click on the event that has Wicket Bookmaker market");
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 4 Active Wicket Bookmaker tab");
        marketPage.activeProduct(WICKET_BOOKMAKER_TITLE);

        log("Step 5 Click on an odds of a Bookmaker market then place bet with the stake  greater than available balance");
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket,true);
        if(Objects.isNull(marketPage)){
            log("DEBUG: Skip as have no event has Wicket Bookmaker market");
            Assert.assertFalse(true,"Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
       marketPage.betsSlipContainer.placeBet("",stake);

        log("Verify 1. Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, BetSlip.ERROR_INSUFFICIENT_BALANCE,String.format("ERROR! Expected error message is %s but found %s", BetSlip.ERROR_INSUFFICIENT_BALANCE,actualError));

        log("INFO: Executed completely");
    }




}

