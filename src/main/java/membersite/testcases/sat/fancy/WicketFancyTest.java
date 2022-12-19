package membersite.testcases.sat.fancy;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.FancyMarket;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.MemberConstants.*;

public class WicketFancyTest extends BaseCaseMerito {
    /**
     * @title Validate can place bet on Fancy on Match odds market page
     * @Precondition:  1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click Match odds
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Can place bet
     */
    @TestRails(id="611")
    @Test(groups = {"smoke"})
    @Parameters({"isCredit"})
    public void WicketFancyTest_001(boolean isCredit){
        log("@title: Validate can place bet on Fancy on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",WICKET_FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minStake=  String.valueOf(fcMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fcMarket,true,Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fcMarket,true,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),expectedWager.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0),expectedWager.getRunnerName(),"FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1),expectedWager.displayFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2),String.format("%.2f",(double)fcMarket.getMinSetting()),"FAILED! Stake is incorrect");
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
    @TestRails(id="613")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_002(){
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",WICKET_FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);

        log("Step 3. Active Wicket Fancy Market int the left menu");
        marketPage = marketPage.satLeftMenuControl.clickLeftMenuItem(fcMarket.getMarketName(),MarketPage.class);

        log("Step 4 Active Wicket Fancy tab");
        FancyMarket fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String minStake=  String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,false,Double.parseDouble(minStake));


        log(String.format("Step 5: On market %s Place on No odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fancyMarket,false,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),expectedWager.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0),expectedWager.getRunnerName(),"FAILED! Market Name is incorrect");
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
    @TestRails(id="613")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_003(){
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);
        AccountBalance balance = sportPage.getUserBalanceSAT();

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",WICKET_FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4 Get Wicket Fancy available");
        FancyMarket fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
        List<Wager> lstMatchedBets = BetUtils.getMatchedOpenBet("4",fancyMarket.getEventID(),fancyMarket.getMarketID(),"WICKET_FANCY");
        Double liabilityBeforePlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);

        String minStake=  String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,true,Double.parseDouble(minStake));
        Wager expectedWager2 = marketPage.defineFamcyWager(fancyMarket,false,Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.placeFancy(fancyMarket,true,minStake);
        marketPage.placeFancy(fancyMarket,false,minStake);

        log("Verify 1. Can place bet");
       List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet),"FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0),expectedWager2.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(0),expectedWager2.getRunnerName(),"FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(1),expectedWager2.displayFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(2),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(1).get(3),expectedWager2.getLiabilityFancyWager(),"FAILED! Liability is incorrect");
        Assert.assertEquals(lstFCBet.get(2).get(0),expectedWager.getMarketName(),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(0),expectedWager.getRunnerName(),"FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(1),expectedWager2.displayFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(2),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(3).get(3),expectedWager.getLiabilityFancyWager(),"FAILED! Liability is incorrect");

       fancyMarket =  marketPage.getFancyMarketInfo(fcMarket);
       Double liabilityAfterPlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);
       Assert.assertEquals(fancyMarket.getMarketLiability(),liabilityAfterPlaceBet,"FAILED! Market Liability is incorrect");
       String expectedBalance = marketPage.calculateBalanceAfterPlaceBet(balance.getBalance(),liabilityBeforePlaceBet,liabilityAfterPlaceBet);

       String balanceActual = marketPage.getUserBalanceSAT().getBalance();
       Assert.assertEquals(balanceActual,expectedBalance,"FAILED! Balance is incorrect");

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
    @TestRails(id="614")
    @Test(groups = {"smoke"})
    @Parameters({"isCredit"})
    public void WicketFancyTest_004(boolean isCredit){
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",WICKET_FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String stake  = Double.toString( Double.valueOf(fcMarket.getMinSetting()) -1);
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),fcMarket.getMinSetting(),fcMarket.getMaxSetting(),BetUtils.getUserBalance());

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
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
    @TestRails(id="615")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_005(){
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has wicket Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",WICKET_FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);
        String stake  = Double.toString( Double.valueOf(fcMarket.getMaxSetting()) +1);
        String expectedError = marketPage.defineErrorMessage(Double.valueOf(stake),fcMarket.getMinSetting(),fcMarket.getMaxSetting(),BetUtils.getUserBalance());

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
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
    @TestRails(id="616")
    @Test(groups = {"smoke"})
    public void WicketFancyTest_006(){
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake =String.format("%d",(int) (Double.valueOf(balance.getBalance().replaceAll(",", "").toString())+ 1));
        
        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log("Step 2 Get and click on the event that has wicket Fanyc");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket("4",WICKET_FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true,"By passed as has no Fancy Wicket on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4 Active Wicket Fancy tab");
        marketPage.activeProduct(WICKET_FANCY_TITILE);
        fcMarket =  marketPage.getFancyMarketInfo(fcMarket);

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.placeFancy(fcMarket,true,stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetControlSAT.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,String.format("ERROR! Expected error message is %s but found %s", MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,actualError));

        log("INFO: Executed completely");
    }

   /* *//**
     * @title Verify Wicket Fancy display in the left menu
     * @Precondition:   1. Get the event that have Fancy market
     * @Step    1/ Login member site
     *          2/ Active the event that have Fancy market
     *          3/ Observe the left menu
     * @Expected 1. Fancy market display in the left menu match with the api
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_001(){
        log("@title: Verify Wicket Fancy display in the left menu");
        log("Step 1. Login member site");
        log("Step 2. Click Cricket menu");
        String sportName = "Cricket";
        memberHomePage.clickSport(sportName);

        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }

        log("Step 2/ Active the event that have Fancy market");

        String competitionName = lstFancy.get(0).getCompetitionName();
        String eventName = lstFancy.get(0).getEventName();



        log(String.format("Step Debug: Click Competition: %s",competitionName));
        memberHomePage.clickCompetition(competitionName);

        log(String.format("Step Debug: Click Event: %s",eventName));
        memberHomePage.clickEvent(eventName);

        log("Verify 1. Fancy market display in the left menu match with the api");
        List<String> marketList = memberHomePage.getFancyMarkets();
        Assert.assertEquals(marketList.size(), lstFancy.size(),"FAILED! Total wicket fancy in the left menu not matched with the api");
        for(int i =0; i< marketList.size(); i++){
            Assert.assertEquals(marketList.get(i).trim(),lstFancy.get(i).getMarketName().trim(),"FAILED! Market Name not matched with the api. ");
        }

        log("INFO: Executed completely");
    }

    *//**
     * @title Verify Cannot place bet if stake less than min bet
     * @Precondition:   1. Get the event that have Fancy market
     * @Step    1/ Login member site
     *          2/ Active the event that have Fancy market
     *          3/ Click Match odds
     * @Expected 1. Wicket Fancy market display under match odds market matched with the api
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_002(){
        log("@title: Verify Wicket Fancy display in the under match odds market");
        log("Step 1. Login member site");
         List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }

        log("Step 2/ Active the event that have Fancy market");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);
        String competitionName = lstFancy.get(0).getCompetitionName();
        String eventName = lstFancy.get(0).getEventName();
        String marketMatchodds ="Match Odds";

        log("Step Debug Click Cricket menu");
        sportPage.clickSport(sportName);
        log(String.format("Step Debug: Click Competition: %s",competitionName));
        sportPage.clickCompetition(competitionName);

        log(String.format("Step Debug: Click Event: %s",eventName));
        sportPage.clickEvent(eventName);

        log("Click Match odds market");
        sportPage.clickMarket(marketMatchodds);

        log("Verify 1.Verify Title Fancy Wicket");
        String title = sportPage.wcFancyContainerControl.getTitle();
        Assert.assertEquals(title, WICKET_FANCY_TITILE,"FAILED! Fancy Title is not corrected");

        log("Verify 2 Wicket Fancy market display under match odds market matched with the api");
        List<String> marketList = sportPage.wcFancyContainerControl.getListWicketFancy();
        Assert.assertEquals(marketList.size(), lstFancy.size(),"FAILED! Total wicket fancy in the left menu not matched with the api");
        for(int i =0; i< marketList.size(); i++){
            Assert.assertEquals(marketList.get(i).trim(),lstFancy.get(i).getMarketName().trim(),"FAILED! Market Name not matched with the api. ");
        }
        log("INFO: Executed completely");
    }

    *//**
     * @title Validate can place bet on Fancy on Match odds market page
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click Match odds
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Can place bet
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_003(){
        log("@title: Validate can place bet on Fancy on Match odds market page");
        log("Step 1. Login member site");
        List<FancyMarket> lstFancy = WicketFancyUtils.getWicketFancyByStatus("30406706","ONLINE");
        *//*List<FancyMarket> lstFancy = BetUtils.findEventHasFancyMarket("4","ONLINE");*//*
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);
        List<Wager> lstWagerBeforePlace = BetUtils.getMatchedOpenBet("4",fcMarket.getEventID(), fcMarket.getMarketID(), FEMemberConstants.WICKET_FANCY);
        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);
    //   List<Wager> wg = BetUtils.getMatchedOpenBet("4","30565346","183915893","MATCH_ODDS");
        *//*String competitionName = lstFancy.get(0).getCompetitionName();
        String eventName = lstFancy.get(0).getEventName();*//*
        String marketMatchodds ="Match Odds";

        log(String.format("Step Debug: Click Competition: %s",fcMarket.getCompetitionName()));
        sportPage.clickCompetition(fcMarket.getCompetitionName());

        log(String.format("Step Debug: Click Event: %s",fcMarket.getEventName()));
        sportPage.clickEvent(fcMarket.getEventName());

        log("Step 3: Click Match odds market");
        sportPage.clickMarket(marketMatchodds);

        log(String.format("Step 4: Click Back Odds of Fancy Market: %s",fcMarket.getMarketName()));
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
        String minStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[0].trim();

        log("Step 5 Place bet with stake");
        sportPage.betSlipControl.placeBet(minStake);
        List<Wager> lstWagerAfterPlace = BetUtils.getMatchedOpenBet("4",fcMarket.getEventID(), fcMarket.getMarketID(), FEMemberConstants.WICKET_FANCY);

        log("Verify 1. Can place bet");
        Assert.assertTrue(lstWagerAfterPlace.size() > lstWagerBeforePlace.size());

        log("INFO: Executed completely");
    }

    *//**
     * @title Validate can place bet on Fancy market page
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Can place bet
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_004(){
        log("@title: Validate can place bet on Fancy market page");
        log("Step 1. Login member site");
        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);

        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        List<Wager> lstWagerBfPlace = BetUtils.getMatchedOpenBet("4",fcMarket.getEventID(), fcMarket.getMarketID(), FEMemberConstants.WICKET_FANCY);
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log(String.format("Step Debug: Click Competition: %s",fcMarket.getCompetitionName()));
        sportPage.clickCompetition(fcMarket.getCompetitionName());

        log(String.format("Step Debug: Click Event: %s",fcMarket.getEventName()));
        sportPage.clickEvent(fcMarket.getEventName());

        log("Step 3/ Click on a Fancy market");
        sportPage.clickMarket(fcMarket.getMarketName());

        log(String.format("Step 4: Click Back Odds of Fancy Market: %s",fcMarket.getMarketName()));
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
        String minMax = marketPage.getMinMaxLable(fcMarket);
        String minStake = Double.toString( Double.valueOf(marketPage.getMinMaxOFFancyMarket(minMax,true)) -1);

        log("Step Debug: Input valid stake and place bet");
        sportPage.betSlipControl.placeBet(minStake);

        log("Verify 1. Can place bet");
        List<Wager> lstWagerAfterPlace = BetUtils.getMatchedOpenBet("4",fcMarket.getEventID(), fcMarket.getMarketID(), FEMemberConstants.WICKET_FANCY);


        log("INFO: Executed completely");
    }

    *//**
     * @title Verify exposure is kept correctly when place on No
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Exposure kept correctly when place on No section
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_005(){
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site");
        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);

        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log(String.format("Step Debug: Click Competition: %s",fcMarket.getCompetitionName()));
        sportPage.clickCompetition(fcMarket.getCompetitionName());

        log(String.format("Step Debug: Click Event: %s",fcMarket.getEventName()));
        sportPage.clickEvent(fcMarket.getEventName());

        log("Step 3/ Click on a Fancy market");
        sportPage.clickMarket(fcMarket.getMarketName());

        log(String.format("Step 4: Click Back Odds of Fancy Market: %s",fcMarket.getMarketName()));
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
        String minStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[0].trim();

        log("Step Debug: Input valid stake and place bet");
        sportPage.betSlipControl.placeBet(minStake);

        log("Verify 1. Exposure kept correctly when place on No section");

        log("INFO: Executed completely");
    }

    *//**
     * @title Verify exposure is kept correctly when place on Yes
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet
     * @Expected 1. Exposure kept correctly when place on Yes section
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_006(){
        log("@title: Verify exposure is kept correctly when place on Yes");
        log("Step 1. Login member site");
        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);

        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log(String.format("Step Debug: Click Competition: %s",fcMarket.getCompetitionName()));
        sportPage.clickCompetition(fcMarket.getCompetitionName());

        log(String.format("Step Debug: Click Event: %s",fcMarket.getEventName()));
        sportPage.clickEvent(fcMarket.getEventName());

        log("Step 3/ Click on a Fancy market");
        sportPage.clickMarket(fcMarket.getMarketName());

        log(String.format("Step 4: Click Back Odds of Fancy Market: %s",fcMarket.getMarketName()));
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
        String minStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[0].trim();

        log("Step Debug: Input valid stake and place bet");
        sportPage.betSlipControl.placeBet(minStake);

        log("Verify 1. Exposure kept correctly when place on Yes section");

        log("INFO: Executed completely");
    }

    *//**
     * @title Verify exposure is kept correctly when place on Yes and No
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds and place on Yes and No option
     * @Expected 1. Exposure kept correctly when place on Yes and No section
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_007(){
        log("@title: Verify exposure is kept correctly when place on Yes and No");
        log("Step 1. Login member site");
        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);

        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log(String.format("Step Debug: Click Competition: %s",fcMarket.getCompetitionName()));
        sportPage.clickCompetition(fcMarket.getCompetitionName());

        log(String.format("Step Debug: Click Event: %s",fcMarket.getEventName()));
        sportPage.clickEvent(fcMarket.getEventName());

        log("Step 3/ Click on a Fancy market");
        sportPage.clickMarket(fcMarket.getMarketName());

        log(String.format("Step 4/ Click on an odds and place on Yes and No option of Fancy Market: %s",fcMarket.getMarketName()));
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
        String minStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[0].trim();

        log("Step Debug: Input valid stake and place bet");
        sportPage.betSlipControl.placeBet(minStake);

        log("Verify 1. Exposure kept correctly when place on Yes and No section");

        log("INFO: Executed completely");
    }

    *//**
     * @title Verify Cannot place bet if stake less than min bet
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     *      * 2/ Active the event that have Fancy market
     *      * 3/ Click on a Fancy market
     *      * 4/ Click on an odds of a fancy market then place bet with the stake less than min bet
     * @Expected 1. Can NOT place bet
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_008(){
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site");
        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);
        String sportName = "Cricket";
        String competitionName =fcMarket.getCompetitionName();
        String eventName = fcMarket.getEventName();
        String marketName =fcMarket.getMarketName();

        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log(String.format("Step Debug: Click Competition: %s",competitionName));
        sportPage.clickCompetition(competitionName);

        log(String.format("Step Debug: Click Event: %s",eventName));
        sportPage.clickEvent(eventName);

        log(String.format("Step 3: Click market: %s",marketName));
        sportPage.clickMarket(marketName);

        log("Step 4/ Click on an odds of a fancy market then place bet with the stake less than min bet");
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
        String minStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[0].trim();
        String maxStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[1].trim();
        String stake = Integer.toString(Integer.parseInt(minStake) - 1);
        sportPage.betSlipControl.placeBet(stake);

        log("Verify 1. Can NOT place bet");
        String actualError = sportPage.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minStake)),String.format("%(,.2f",Double.parseDouble(maxStake)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));

        log("INFO: Executed completely");
    }

    *//**
     * @title Verify Cannot place bet if stake greater than max bet
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet with the stake greater than max bet
     * @Expected 1. Verify cannot place bet
     *//*
    @Test(groups = {"smoke"})
    public void FE_WicketFancyTest_009(){
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site");
        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);
        String competitionName =fcMarket.getCompetitionName();
        String eventName = fcMarket.getEventName();
        String marketName =fcMarket.getMarketName();

        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log(String.format("Step Debug: Click Competition: %s",competitionName));
        sportPage.clickCompetition(competitionName);

        log(String.format("Step Debug: Click Event: %s",eventName));
        sportPage.clickEvent(eventName);

        log(String.format("Step 3/ Click on a Fancy market: %s",marketName));
        sportPage.clickMarket(marketName);

        log("Step 4/ Click on an odds of a fancy market then place bet with the stake greater than max bet");
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
        String minStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[0].trim();
        String maxStake = sportPage.wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket)[1].trim();
        String stake = Integer.toString(Integer.parseInt(maxStake) + 1);
        sportPage.betSlipControl.placeBet(stake);

        log("Verify 1. Can NOT place bet");
        String actualError = sportPage.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minStake)),String.format("%(,.2f",Double.parseDouble(maxStake)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));

        log("INFO: Executed completely");
    }

    *//**
     * @title Verify Cannot place bet if stake less is greater than available balance
     * @Precondition:   1. Get the event that have Fancy market
     * @Step 1/ Login member site
     * 2/ Active the event that have Fancy market
     * 3/ Click on a Fancy market
     * 4/ Click on an odds of a fancy market then place bet with the stake  greater than available balance
     * @Expected 1. Verify cannot place bet
     *//*
    @Test(groups = {"smoke"})
    @Parameters({"isCredit"})
    public void FE_WicketFancyTest_010(boolean isCredit){
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site");
        AccountBalance balance = BetUtils.getUserBalance(isCredit);

        List<FancyMarket> lstFancy = WicketFancyUtils.findEventHasFancyMarket("4","ONLINE");
        if(Objects.isNull(lstFancy)){
            log("DEBUG: Have no event has Fancy Wicket");
            return;
        }
        FancyMarket fcMarket = lstFancy.get(0);
        String competitionName =fcMarket.getCompetitionName();
        String eventName = fcMarket.getEventName();
        String marketName =fcMarket.getMarketName();

        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        log(String.format("Step Debug: Click Competition: %s",competitionName));
        sportPage.clickCompetition(competitionName);

        log(String.format("Step Debug: Click Event: %s",eventName));
        sportPage.clickEvent(eventName);

        log(String.format("Step 3/ Click on a Fancy market: %s",marketName));
        sportPage.clickMarket(marketName);

        log("Step 4/ Click on an odds of a fancy market then place bet with the stake  greater than available balance");
        sportPage.wcFancyContainerControl.clickFancyOdds(fcMarket,true);
       // sportPage.betSlipControl.placeBet(stake);

        log("Verify 1. Can NOT place bet");
        String actualError = sportPage.myBetControl.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError,FEMemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,String.format("ERROR! Expected error message is %s but found %s", FEMemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,actualError));

        log("INFO: Executed completely");
    }*/
}

