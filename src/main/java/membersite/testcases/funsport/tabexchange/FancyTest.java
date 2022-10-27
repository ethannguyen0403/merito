package membersite.testcases.funsport.tabexchange;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.funsport.SelectedOdd;
import membersite.objects.sat.FancyMarket;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.EventPage;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static membersite.common.FEMemberConstants.BetSlip;
import static membersite.common.FEMemberConstants.BetSlip.ERROR_STAKE_MESSAGE_MIN_BET;
import static membersite.common.FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID_MAX_BET;
import static membersite.common.FEMemberConstants.FANCY_CODE;

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
    public void FancyTest_001(){
        log("@title: Validate can place bet on Fancy on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateCricket();
        log("Step 2 Get and click on the event that has 27Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        String eventName = fcMarket.getEventName();

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickOnRowofEventName(eventName);
        MarketPage marketPage = page.openMarketPage("Match Odds");

        log("Step 4 Active 27 Fancy tab");
        FancyMarket fancyMarket =  marketPage.setF27ancyMarketInfo(fcMarket);
         String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,true,Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.place27Fancy(fancyMarket,true,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList<String>> lstWagers = marketPage.myBetControloldUI.getFancyWager();
        Assert.assertTrue((lstWagers.size()>1), "ERROR: lstWagers size is less than 1");
        Assert.assertEquals(lstWagers.get(1).get(0),String.format("%s - %s",eventName,expectedWager.getMarketName()),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(1),expectedWager.getRunnerName(),"FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(2),expectedWager.displa27yFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(3),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(4),String.format("%.2f",expectedWager.getLiabilityFancyWager()),"FAILED! Liability is incorrect");
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
    @Test(groups = {"smoke"})
    public void FancyTest_002(){
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateCricket();
        log("Step 2 Get and click on the event that has 27Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        String eventName = fcMarket.getEventName();

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickOnRowofEventName(eventName);
        MarketPage marketPage = page.openMarketPage("Match Odds");

        log("Step 4 Get 27 Fancy Info");
        FancyMarket fancyMarket =  marketPage.setF27ancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,false,Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Lay odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.place27Fancy(fancyMarket,false,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList<String>> lstWagers = marketPage.myBetControloldUI.getFancyWager();
        Assert.assertTrue((lstWagers.size()>1), "ERROR: lstWagers size is less than 1");
        Assert.assertEquals(lstWagers.get(1).get(0),String.format("%s - %s",eventName,expectedWager.getMarketName()),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(1),expectedWager.getRunnerName(),"FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(2),expectedWager.displa27yFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(3),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(4),String.format("%.2f",expectedWager.getLiabilityFancyWager()),"FAILED! Liability is incorrect");

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
    @Test(groups = {"smoke"})
    public void FancyTest_003(){
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        AccountBalance balance = memberHomePage.fsHeaderControl.getUserBalance();
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateCricket();
        log("Step 2 Get and click on the event that has 27Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        String eventName = fcMarket.getEventName();

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickOnRowofEventName(eventName);
        MarketPage marketPage = page.openMarketPage("Match Odds");

        log("Step 4 Get 27 Fancy Info");
        FancyMarket fancyMarket =  marketPage.setF27ancyMarketInfo(fcMarket);
        List<Wager> lstMatchedBets = BetUtils.get27FanycMatchedOpenBet(fancyMarket.getEventID(),fancyMarket.getMarketID());
        Double liabilityBeforePlaceBet = marketPage.liabilityFCMarket(lstMatchedBets);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFamcyWager(fancyMarket,true,Double.parseDouble(minStake));
        Wager expectedWager2 = marketPage.defineFamcyWager(fancyMarket,false,Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.place27Fancy(fancyMarket,true,minStake);
        marketPage.place27Fancy(fancyMarket,false,minStake);

        log("Verify 1. Can place bet");
        List<ArrayList<String>> lstWagers = marketPage.myBetControloldUI.getFancyWager();
        Assert.assertTrue((lstWagers.size()>1), "ERROR: lstWagers size is less than 1");
        Assert.assertEquals(lstWagers.get(1).get(0),String.format("%s - %s",eventName,expectedWager.getMarketName()),"FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(1),expectedWager2.getRunnerName(),"FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(2),expectedWager2.displa27yFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(3),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstWagers.get(2).get(4),String.format("%.2f",expectedWager2.getLiabilityFancyWager()),"FAILED! Liability is incorrect");
        Assert.assertEquals(lstWagers.get(4).get(1),expectedWager.getRunnerName(),"FAILED! Runner Name is incorrect");
        Assert.assertEquals(lstWagers.get(4).get(2),expectedWager.displa27yFancyOdds(),"FAILED! Odd is incorrect");
        Assert.assertEquals(lstWagers.get(4).get(3),String.format("%.2f",(double)fancyMarket.getMinSetting()),"FAILED! Stake is incorrect");
        Assert.assertEquals(lstWagers.get(4).get(4),String.format("%.2f",expectedWager.getLiabilityFancyWager()),"FAILED! Liability is incorrect");

        fancyMarket =  marketPage.setF27ancyMarketInfo(fcMarket);
        List<Wager> lstMatchedBetsAfterPlaceBEt = BetUtils.get27FanycMatchedOpenBet(fancyMarket.getEventID(),fancyMarket.getMarketID());
        Double liabilityAfterPlaceBetPlaceBet = marketPage.liabilityFCMarket(lstMatchedBetsAfterPlaceBEt);
        Assert.assertEquals(fancyMarket.getMarketLiability(),liabilityAfterPlaceBetPlaceBet *(-1),"FAILED! Market Liability is incorrect");
        String expectedBalance = marketPage.calculateBalanceAfterPlaceBet(balance.getBalance(),liabilityBeforePlaceBet,liabilityAfterPlaceBetPlaceBet);

        String balanceActual = marketPage.getUserBalance().getBalance();
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
    @Test(groups = {"smoke"})
    public void FancyTest_004(){
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateCricket();
        log("Step 2 Get and click on the event that has 27Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        String eventName = fcMarket.getEventName();

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickOnRowofEventName(eventName);
        MarketPage marketPage = page.openMarketPage("Match Odds");

        log("Step 4 Get 27 Fancy Info");
        FancyMarket fancyMarket =  marketPage.setF27ancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        String maxStake = String.valueOf(fancyMarket.getMaxSetting());
        String stake  = Double.toString( Double.valueOf(minStake) -1);

        log(String.format("Step 5: On market %s Place on Yes odds with stake %s ",fcMarket.getMarketID(),minStake));
        marketPage.place27Fancy(fancyMarket,true,stake);
        SelectedOdd selectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds27Fancy(1).get(0);
        selectedOdd.getLblProfit().click();
        boolean isDisabled = marketPage.betSlipControlOldUI.isPlaceBetDisabled();

        log("Verify 1. Can NOT place bet");
        log("Verify 1: At step 2, Place bets button is disabled");
        log("Verify 2: At step 4, Place bets button is disabled after inputting a stake more than min stake");
        Assert.assertTrue(isDisabled, "ERROR: The expected disable of Place bet button should be true on Bet Slip before inputting a stake");
        Assert.assertTrue(marketPage.betSlipControlOldUI.isPlaceBetDisabled(), "ERROR: The expected disable of Place bet button should be true on Bet Slip after the inputted stake more than max bet");
        Assert.assertEquals(marketPage.betSlipControlOldUI.getBetSlipErrorStakeMsg(),ERROR_STAKE_MESSAGE_MIN_BET,"FAILED! Stake error message are incorrect");
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
    @Test(groups = {"smoke3"})
    public void FancyTest_005(){
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateCricket();
        log("Step 2 Get and click on the event that has 27Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        String eventName = fcMarket.getEventName();

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickOnRowofEventName(eventName);
        MarketPage marketPage = page.openMarketPage("Match Odds");

        log("Step 4 Get 27 Fancy Info");
        FancyMarket fancyMarket = marketPage.setF27ancyMarketInfo(fcMarket);
        String maxStake = String.valueOf(fancyMarket.getMaxSetting());
        String stake  = Double.toString( Double.valueOf(maxStake) + 1);

        log(String.format("Step 5: On market %s Place on Yes odds with stake %s ",fcMarket.getMarketID(),stake));
        marketPage.place27Fancy(fancyMarket,true,stake);
        SelectedOdd selectedOdd = marketPage.betSlipControlOldUI.getSelectedOdds27Fancy(1).get(0);
        selectedOdd.getLblProfit().click();
        boolean isDisabled = marketPage.betSlipControlOldUI.isPlaceBetDisabled();

        log("Verify 1. Can NOT place bet");
        Assert.assertTrue(isDisabled, "ERROR: The expected disable of Place bet button should be true on Bet Slip before inputting a stake");
        Assert.assertTrue(marketPage.betSlipControlOldUI.isPlaceBetDisabled(), "ERROR: The expected disable of Place bet button should be true on Bet Slip after the inputted stake more than max bet");
        Assert.assertEquals(marketPage.betSlipControlOldUI.getBetSlipErrorStakeMsg(),ERROR_STAKE_NOT_VALID_MAX_BET,"FAILED! Stake error message are incorrect");
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
    @Test(groups = {"smoke"})
    public void FancyTest_006(){
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake =Double.toString( Double.valueOf(balance.getBalance().replaceAll(",", "").toString())+ 1);
        String sportID = "4";
        SportPage sportPage = memberHomePage.navigateCricket();
        log("Step 2 Get and click on the event that has 27Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(sportID,FANCY_CODE);
        if(Objects.isNull(fcMarket)){
            log("DEBUG: Skip as have no event has 27 Fancy");
            Assert.assertTrue(true,"By passed as has no 27 Fancy on all available event");
            return;
        }
        String eventName = fcMarket.getEventName();

        log("Step 2: Click on Event Name");
        EventPage page = sportPage.oddPageControl.clickOnRowofEventName(eventName);
        MarketPage marketPage = page.openMarketPage("Match Odds");

        log("Step 4 Get 27 Fancy Info");
        FancyMarket fancyMarket = marketPage.setF27ancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        String maxStake = String.valueOf(fancyMarket.getMaxSetting());
        String expectedError = marketPage.defineErrorMessageOldUI(Double.valueOf(stake),Double.parseDouble(minStake),Double.parseDouble(maxStake),BetUtils.getUserBalance());

        log(String.format("Step 5: On market %s Place on Yes odds with stake %s ",fcMarket.getMarketName(),stake));
        marketPage.place27Fancy(fancyMarket,true,stake);
        SelectedOdd order = marketPage.betSlipControlOldUI.getErrorBet(1).get(0);

        log("Verify 1. Can NOT place bet");
        Assert.assertEquals(marketPage.betSlipControlOldUI.getErrorTitle(),"Error","FAILED! Error title is incorrect displayed");
        Assert.assertEquals(order.getErrorMessage(), expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,order.getErrorMessage()));

        log("INFO: Executed completely");
    }

}
