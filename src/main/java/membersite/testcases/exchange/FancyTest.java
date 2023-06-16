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

import static common.MemberConstants.FANCY_CODE;
import static common.MemberConstants.FANCY_TITILE;

public class FancyTest extends BaseCaseTest {
    /**
     * @title Validate can place bet on Fancy on Match odds market page
     * @Precondition:  1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click Match odds
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Can place bet
     */
    @TestRails(id="549")
    @Test(groups = {"smoke"})
    public void FancyTest_001(){
        log("@title: Validate can place bet on Fancy on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active 27 Fancy tab");
        marketPage.activeProduct(FANCY_TITILE);
        FancyMarket fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,true,Double.parseDouble(minStake));
        String oddsActual = expectedWager.displayFancyOdds();

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fancyMarket,true,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),expectedWager.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0),expectedWager.getRunnerName(),"FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1),oddsActual,"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3),String.format("%.2f",expectedWager.getLiabilityFancyWager()),"FAILED! Liability is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title Verify exposure is kept correctly when place on No
     * @Precondition: 1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Exposure kept correctly when place on No section
     */
    @TestRails(id="550")
    @Test(groups = {"smoke"})
    public void FancyTest_002(){
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        log("Step 2 Get and click on the event that has 27 Fancy");
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active 27 Fancy tab");
        marketPage.activeProduct(FANCY_TITILE);
        FancyMarket fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,false,Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on No odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fancyMarket,false,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),expectedWager.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0),expectedWager.getRunnerName(),"FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1),expectedWager.displayFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3),String.format("%.2f",expectedWager.getLiabilityFancyWager()),"FAILED! Liability is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title Verify exposure is kept correctly when place on Yes and No
     * @Precondition: 1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds and place on Yes and No option
     * @Expected 1. Exposure kept correctly when place on No section
     */
    @TestRails(id="551")
    @Test(groups = {"smoke"})
    public void FancyTest_003(){
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
        AccountBalance balance = sportPage.header.getUserBalance();

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 2.1. Get 27 Fancy available");
        log("Step 4. Active 27 Fancy Market int the left menu");
        marketPage = marketPage.clickMarketonLeftMenu(fcMarket.getMarketName());

        log("Step 4 Active 27 Fancy tab");
        marketPage.activeProduct(FANCY_TITILE);
        FancyMarket fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        List<Wager> lstMatchedBets = BetUtils.getMatchedOpenBet("4",fancyMarket.getEventID(),fancyMarket.getMarketID(),"FANCY");
        Double liabilityBeforePlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);

        String minStake=  String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,true,Double.parseDouble(minStake));
        Wager expectedWager2 = marketPage.defineFamcyWager(fancyMarket,false,Double.parseDouble(minStake));


        log(String.format("Step 5: On market %s Place on No odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fancyMarket,true,minStake);
        marketPage.placeFancy(fancyMarket,false,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),expectedWager2.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0),expectedWager2.getRunnerName(),"FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1),expectedWager2.displayFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3),String.format("%.2f",expectedWager2.getLiabilityFancyWager()),"FAILED! Liability is incorrect");
        Assert.assertEquals(lstFCBet.get(2).get(0),expectedWager.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(0),expectedWager.getRunnerName(),"FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(1),expectedWager.displayFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(2),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(3),String.format("%.2f",expectedWager2.getLiabilityFancyWager()),"FAILED! Liability is incorrect");

        List<Wager> lstMatchedBetsAfterPlaceBet = BetUtils.getMatchedOpenBet("4",fancyMarket.getEventID(),fancyMarket.getMarketID(),"FANCY");
  //      Double liabilityBeforePlaceBetAfterPlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);
        fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityAfterPlaceBet = marketPage.liabilityFCMarket(lstMatchedBetsAfterPlaceBet);
        Assert.assertEquals(fancyMarket.getMarketLiability(),liabilityAfterPlaceBet,"FAILED! Market Liability is incorrect");
        String expectedBalance = marketPage.calculateBalanceAfterPlaceBet(balance.getBalance(),liabilityBeforePlaceBet,liabilityAfterPlaceBet);

        String balanceActual = marketPage.header.getUserBalance().getBalance();
        Assert.assertEquals(balanceActual,expectedBalance,"FAILED! Available balance is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake less than min bet
     * @Precondition:  1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet with the stake less than min bet
     * @Expected 1. Verify cannot place bet
     */
    @TestRails(id="552")
    @Test(groups = {"smoke"})
    public void FancyTest_004(){
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Get 27 Fancy available");
        log("Step 4 Active 27 Fancy tab");
        marketPage.activeProduct(FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String  minStake=  marketPage.getMinMaxOFFancyMarket(minMax,true);
        String stake  = Double.toString( Double.valueOf(minStake) -1);
        String maxStake = marketPage.getMinMaxOFFancyMarket(minMax,false);

        log(String.format("Step 5: On market %s Place on No odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minStake)),String.format("%(,.2f",Double.parseDouble(maxStake)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));

        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake greater than max bet
     * @Precondition:  1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet with the stake  greater than available balance
     * @Expected 1. Verify cannot place bet
     */
    @TestRails(id="553")
    @Test(groups = {"smoke"})
    public void FancyTest_005(){
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 3. Get 27 Fancy available");
        log("Step 4 Active 27 Fancy tab");
        marketPage.activeProduct(FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String minStake=  marketPage.getMinMaxOFFancyMarket(minMax,true);
        String maxStake = marketPage.getMinMaxOFFancyMarket(minMax,false);
        String stake  = Double.toString( Double.valueOf(maxStake) +1);
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),Double.parseDouble(minStake),Double.parseDouble(maxStake),BetUtils.getUserBalance());

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    /**
     * @title Verify Cannot place bet if stake less is greater than available balance
     * @Precondition:  1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet with the stake  greater than available balance
     * @Expected 1. Verify cannot place bet
     */
    @TestRails(id="554")
    @Test(groups = {"smoke"})
    public void FancyTest_006(){
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake =Double.toString( Double.valueOf(balance.getBalance().replaceAll(",", "").toString())+ 1);
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);

        log("Step 2 Get and click on the event that has 27 Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Fancy tab");
        marketPage.activeProduct(FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String minStake=  marketPage.getMinMaxOFFancyMarket(minMax,true);
        String maxStake = marketPage.getMinMaxOFFancyMarket(minMax,false);
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),Double.parseDouble(minStake),Double.parseDouble(maxStake),BetUtils.getUserBalance());

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,actualError));

        log("INFO: Executed completely");
    }
}
