package membersite.testcases.aposta;

import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.FancyMarket;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.MemberConstants.CENTRAL_FANCY_CODE;
import static common.MemberConstants.FANCY_CODE;

public class FancyTest extends BaseCaseMerito {

    /**
     * @title Validate can place bet on Fancy on Match odds market page
     * @Precondition:  1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click Match odds
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Can place bet
     */
    @Test(groups = {"smoke"})
    @TestRails(id="549")
    public void FancyTest_001(){
        log("@title: Validate can place bet on Fancy on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        memberHomePage.openLeftMenu();
        SportPage sportPage =  memberHomePage.apLeftMenuControl.clickLeftMenuItem(sportName,SportPage.class);

        log("Step 2 Get and click on the event that has 27 Fancy");
        String eventName = fcMarket.getEventName();
        MarketPage marketPage = sportPage.eventContainerControl.clickOnRowofEventName(eventName);

        log("Step 4 Active 27 Fancy tab");
        FancyMarket fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,true,Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fancyMarket,true,minStake);

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
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        memberHomePage.openLeftMenu();
        SportPage sportPage =  memberHomePage.apLeftMenuControl.clickLeftMenuItem(sportName,SportPage.class);

        log("Step 2 Get and click on the event that has 27 Fancy");
        String eventName = fcMarket.getEventName();
        MarketPage marketPage = sportPage.eventContainerControl.clickOnRowofEventName(eventName);

        log("Step 4 Active 27 Fancy tab");
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
        AccountBalance balance = memberHomePage.getUserBalanceSAT();
        String sportName = "Cricket";
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        memberHomePage.openLeftMenu();
        SportPage sportPage =  memberHomePage.apLeftMenuControl.clickLeftMenuItem(sportName,SportPage.class);

        log("Step 2 Get and click on the event that has 27 Fancy");
        String eventName = fcMarket.getEventName();
        MarketPage marketPage = sportPage.eventContainerControl.clickOnRowofEventName(eventName);

        log("Step 4. Active 27 Fancy Market int the left menu");
        marketPage = marketPage.satLeftMenuControl.clickLeftMenuItem(fcMarket.getMarketName(),MarketPage.class);

        log("Step 4 Active 27 Fancy tab");
        FancyMarket fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        List<Wager> lstMatchedBets = BetUtils.getMatchedOpenBet("4",fancyMarket.getEventID(),fancyMarket.getMarketID(),CENTRAL_FANCY_CODE);
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

        fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityAfterPlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);
        Assert.assertEquals(fancyMarket.getMarketLiability(),liabilityAfterPlaceBet,"FAILED! Market Liability is incorrect");
        String expectedBalance = marketPage.calculateBalanceAfterPlaceBet(balance.getBalance(),liabilityBeforePlaceBet,liabilityAfterPlaceBet);

        String balanceActual = marketPage.getUserBalanceSAT().getBalance();
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
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        memberHomePage.openLeftMenu();
        SportPage sportPage =  memberHomePage.apLeftMenuControl.clickLeftMenuItem(sportName,SportPage.class);

        log("Step 2 Get and click on the event that has 27 Fancy");
        String eventName = fcMarket.getEventName();
        MarketPage marketPage = sportPage.eventContainerControl.clickOnRowofEventName(eventName);

        log("Step 4. Active 27 Fancy Market int the left menu");
        marketPage = marketPage.satLeftMenuControl.clickLeftMenuItem(fcMarket.getMarketName(),MarketPage.class);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String stake  = Double.toString( Double.valueOf(fcMarket.getMinSetting()) -1);

        log(String.format("Step 5: On market %s Place on No odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),fcMarket.getMinSetting(),fcMarket.getMaxSetting(),BetUtils.getUserBalance());
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
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        memberHomePage.openLeftMenu();
        SportPage sportPage =  memberHomePage.apLeftMenuControl.clickLeftMenuItem(sportName,SportPage.class);

        log("Step 2 Get and click on the event that has 27 Fancy");
        String eventName = fcMarket.getEventName();
        MarketPage marketPage = sportPage.eventContainerControl.clickOnRowofEventName(eventName);

        log("Step 4. Active 27 Fancy Market int the left menu");
        marketPage = marketPage.satLeftMenuControl.clickLeftMenuItem(fcMarket.getMarketName(),MarketPage.class);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String stake  = Double.toString( Double.valueOf(fcMarket.getMaxSetting()) +1);

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),fcMarket.getMinSetting(),fcMarket.getMaxSetting(),BetUtils.getUserBalance());
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
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        memberHomePage.openLeftMenu();
        SportPage sportPage =  memberHomePage.apLeftMenuControl.clickLeftMenuItem(sportName,SportPage.class);

        log("Step 2 Get and click on the event that has 27 Fancy");
        String eventName = fcMarket.getEventName();
        MarketPage marketPage = sportPage.eventContainerControl.clickOnRowofEventName(eventName);

        log("Step 4. Active 27 Fancy Market int the left menu");
        marketPage = marketPage.satLeftMenuControl.clickLeftMenuItem(fcMarket.getMarketName(),MarketPage.class);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),fcMarket.getMinSetting(),fcMarket.getMaxSetting(),BetUtils.getUserBalance());
        Assert.assertEquals(actualError, expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));

        log("INFO: Executed completely");
    }

}
