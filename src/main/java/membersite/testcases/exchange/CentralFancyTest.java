package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
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

public class CentralFancyTest extends BaseCaseTest {

    @TestRails(id = "543")
    @Test(groups = {"smoke_sat"})
    public void CentralFancyTest_543() {
        log("@title: Validate can place bet on Fancy on Match odds market page");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Central Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 4 Active Central Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), minStake));
        marketPage.placeFancy(fcMarket, true, minStake);

        log("Verify 1. Can place bet");
        List<ArrayList> lstFCBet = marketPage.getFancyMiniMyBet();
        Assert.assertTrue(Objects.nonNull(lstFCBet), "FAILED! FC my bet section does NOT display");
        Assert.assertEquals(lstFCBet.get(0).get(0), expectedWager.getMarketName(), "FAILED! FC Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(1), expectedWager.getRunnerName(), "FAILED! Market Name is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(2), expectedWager.displayFancyOdds(), "FAILED! Odd is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(3), String.format("%.2f", (double) fancyMarket.getMinSetting()), "FAILED! Stake is incorrect");
        Assert.assertEquals(lstFCBet.get(0).get(4), String.format("%.2f", expectedWager.getLiabilityFancyWager()), "FAILED! Liability is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "544")
    @Test(groups = {"smoke_sat1"})
    public void CentralFancyTest_544() {
        log("@title: Verify exposure is kept correctly when place on No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Central Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 4 Active Central Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        double originalExposure = liabilityBeforePlaceBet - fancyMarket.getMarketLiability();

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fancyMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Verify 1. Validate Exposure kept correctly when place on No section");
        marketPage.verifyExposureKeptCorrectly(originalExposure, fancyMarket);

        log("INFO: Executed completely");
    }

    @TestRails(id = "545")
    @Test(groups = {"smoke_sat1"})
    public void CentralFancyTest_545() {
        log("@title: Verify exposure is kept correctly when place on Yes and No");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Central Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 4 Active Central Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        double originalExposure = liabilityBeforePlaceBet - fancyMarket.getMarketLiability();

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fancyMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, true, minStake);
        marketPage.placeFancy(fancyMarket, false, minStake);

        log("Verify 1. Validate Exposure kept correctly when place on Yes and No section");
        marketPage.verifyExposureKeptCorrectly(originalExposure, fancyMarket);

        log("INFO: Executed completely");
    }

    @TestRails(id = "15772")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15772() {
        log("@title: Validate exposure is kept correctly when place on Yes");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Central Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy Wicket");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 4 Active Central Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        Double liabilityBeforePlaceBet = Double.valueOf(marketPage.header.getUserBalance().getExposure());
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        double originalExposure = liabilityBeforePlaceBet - fancyMarket.getMarketLiability();

        log(String.format("Step 5: On market %s Place on No odds with stake %s ", fancyMarket.getMarketID(), minStake));
        marketPage.placeFancy(fancyMarket, true, minStake);

        log("Verify 1. Validate Exposure kept correctly when place on Yes section");
        marketPage.verifyExposureKeptCorrectly(originalExposure, fancyMarket);

        log("INFO: Executed completely");
    }

    @TestRails(id = "546")
    @Test(groups = {"smoke_sat"})
    public void CentralFancyTest_546() {
        log("@title: Verify Cannot place bet if stake less than min bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Central Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 4 Active Central Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String stake = Double.toString(Double.valueOf(fcMarket.getMinSetting()) - 1);
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID,fcMarket.getMinSetting(),fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "547")
    @Test(groups = {"smoke_sat"})
    public void CentralFancyTest_547() {
        log("@title: Verify Cannot place bet if stake greater than max bet");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.header.navigateSportMenu(LBL_CRICKET_SPORT, _brandname);

        log("Step 2 Get and click on the event that has Central Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 4 Active Central Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);
        String stake = Double.toString(Double.valueOf(fcMarket.getMaxSetting()) + 1);
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID,fcMarket.getMinSetting(),fcMarket.getMaxSetting(), String.format("%.0f", Double.parseDouble(stake)));

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.placeFancy(fcMarket, true, stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getBetslipErrorMessage();
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "548")
    @Test(groups = {"smoke_sat"})
    public void CentralFancyTest_548() {
        log("@title: Verify Cannot place bet if stake less is greater than available balance");
        log("Step 1. Login member site and get account balance form api");
        AccountBalance balance = BetUtils.getUserBalance();
        String stake = Double.toString(Double.valueOf(balance.getBalance().replaceAll(",", "").toString()) + 1);
        log("Step 2/ Active the event that have Fancy market");
        log("Step Debug Click Cricket menu");
        String sportName = "Cricket";
        SportPage sportPage = memberHomePage.header.navigateSportMenu(sportName, _brandname);

        log("Step 2 Get and click on the event that has Central Fancy");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());
        log("Step 4 Active Central Fancy tab");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        fcMarket = marketPage.getFancyMarketInfo(fcMarket);

        log(String.format("Step 5: On market %s Place on Back odds with stake %s ", fcMarket.getMarketID(), stake));
        marketPage.betsSlipContainer.placeBet(stake);

        log("Verify 1. Can NOT place bet");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, BetSlip.ERROR_INSUFFICIENT_BALANCE, String.format("ERROR! Expected error message is %s but found %s", BetSlip.ERROR_INSUFFICIENT_BALANCE, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "15764")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15764() {
        log("@title: Validate My Bet display correct after place bet on Central Fancy market");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on an odds of a fancy market then place bet (wait until bet matched)");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
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

    @TestRails(id = "15765")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15765() {
        log("@title: Validate able navigate to Central Fancy market page");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. Click on an odds of a fancy market then place bet (wait until bet matched)");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Validate Event market page displays with Central Fancy market");
        Assert.assertTrue(!Objects.isNull(fancyMarket), "FAILED! Cannot find out Central Fancy market on event page");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15766")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15766() {
        log("@title: Validate able to open Ladder forecast score");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        log("Step 4. Click on ladder icon on any Fancy market");
        marketPage.openFancyLadderForecast(fancyMarket);
        log("Validate Forecast popup displays with name of selecting market");
        Assert.assertTrue(marketPage.isLadderForecastDisplay(fancyMarket), "FAILED! Ladder forecast does not show correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "15767")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15767() {
        log("@title: Validate bet slip is cleared when navigate to another market");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any market from the one at precondition");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        List<ArrayList> lstBefore = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertFalse(Objects.isNull(lstBefore), "FAILED! There is no record found in mini my bet bet slip");

        log("Step 3. Navigate to any another event and observe bet slip");
        memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        BookmakerMarket bmMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        marketPage = sportPage.clickEventName(bmMarket.getEventName());
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        List<ArrayList> lstAfter = marketPage.getFancyBetSlipMiniMyBet();

        log("Validate Bet slip is cleared");
        Assert.assertTrue(Objects.isNull(lstAfter), "FAILED! Bet slip is not cleared correctly");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15768")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15768() {
        log("@title: Validate able to choose multi selection");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi selection from any market");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.addFancyOdds(fancyMarket, false);

        log("Validate Able to choose multi selection (selected selections show in bet slip)");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), fcMarket.getMarketName(), "FAILED! Bet 1 selection does not add into bet slip correctly");
        Assert.assertEquals(lstBetslip.get(5), fcMarket.getMarketName(), "FAILED! Bet 2 selection does not add into bet slip correctly");
        log("INFO: Executed completely");
    }
    @TestRails(id = "15769")
    @Test(groups = {"smoke_stg", "2024.01.19"})
    public void CentralFancyTest_15769() {
        log("@title: Validate multi tab (bet slip) is disabled");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select multi selection from any market");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);

        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.addFancyOdds(fancyMarket, false);

        log("Validate Multi tab (bet slip) is disabled and does not work when click on");
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipEnabled(), "FAILED! Multi tab in bet slip is enabled");
        marketPage.myBetsContainer.clickMultiTabBetSlip();
        Assert.assertFalse(marketPage.myBetsContainer.isMultiTabBetSlipSelected(), "FAILED! Multi tab in bet slip is enabled");

        log("INFO: Executed completely");
    }

    @TestRails(id = "15770")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15770() {
        log("@title: Validate bet slip information show correctly for selection Yes");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, true, Double.parseDouble(minStake));

        log("Step 4. Input stake and observe bet slip information");
        marketPage.addFancyOdds(fancyMarket, true);
        marketPage.betsSlipContainer.inputStake(minStake);

        log("Validate Bet slip displays with info market name, selection (Yes [L]), stake inputted and liability (stake * (payout/100))");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), expectedWager.getMarketName(), String.format("FAILED! Market name does not display correctly in bet slip expected %s actual %s", expectedWager.getMarketName(), lstBetslip.get(0)));
        Assert.assertEquals(lstBetslip.get(1), "Yes [L]", String.format("FAILED! Selection does not display correct expected Yes [L] actual %s", lstBetslip.get(1)));
        Assert.assertEquals(lstBetslip.get(2), String.format("%.0f", expectedWager.getOdds()), String.format("FAILED! Odds does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getOdds()), lstBetslip.get(2)));
        Assert.assertEquals(lstBetslip.get(3), String.format("%.0f", expectedWager.getStake()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getStake()), lstBetslip.get(3)));
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getProfitFancyWager()),String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getProfitFancyWager()), lstBetslip.get(4)));
    }

    @TestRails(id = "15771")
    @Test(groups = {"smoke_sat1", "2024.01.19"})
    public void CentralFancyTest_15771() {
        log("@title: Validate bet slip information show correctly for selection No");
        log("@Precondition: Get the event that have Central Fancy market");
        log("Step 1. Login member site and click on Cricket");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2. Active the event that have Central Fancy market");
        FancyMarket fcMarket = BetUtils.findOpenFancyMarket(SPORT_ID.get(LBL_CRICKET_SPORT), CENTRAL_FANCY_CODE);
        if (Objects.isNull(fcMarket)) {
            log("DEBUG: Skip as have no event has Central Fancy");
            Assert.assertTrue(true, "By passed as has no Central Fancy on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(fcMarket.getEventName());

        log("Step 3. From the left menu open Competition > Event > Fancy > select any Yes selection");
        memberHomePage.leftMenu.openFancyMarket(CENTRAL_FANCY_TITLE, fcMarket.getMarketName());
        FancyMarket fancyMarket = marketPage.getFancyMarketInfo(fcMarket);
        String minStake = String.valueOf(fancyMarket.getMinSetting());
        Wager expectedWager = marketPage.defineFancyWager(fancyMarket, false, Double.parseDouble(minStake));

        log("Step 4. Input stake and observe bet slip information");
        marketPage.addFancyOdds(fancyMarket, false);
        marketPage.betsSlipContainer.inputStake(minStake);

        log("Validate Bet slip displays with info market name, selection (Yes [L]), stake inputted and liability (stake * (payout/100))");
        List<ArrayList> lstBetslip = marketPage.getFancyBetSlipMiniMyBet();
        Assert.assertEquals(lstBetslip.get(0), expectedWager.getMarketName(), String.format("FAILED! Market name does not display correctly in bet slip expected %s actual %s", expectedWager.getMarketName(), lstBetslip.get(0)));
        Assert.assertEquals(lstBetslip.get(1), "No [K]", String.format("FAILED! Selection does not display correct expected Yes [L] actual %s", lstBetslip.get(1)));
        Assert.assertEquals(lstBetslip.get(2), String.format("%.0f", expectedWager.getOdds()), String.format("FAILED! Odds does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getOdds()), lstBetslip.get(2)));
        Assert.assertEquals(lstBetslip.get(3), String.format("%.0f", expectedWager.getStake()), String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.0f", expectedWager.getStake()), lstBetslip.get(3)));
        Assert.assertEquals(lstBetslip.get(4), String.format("%.2f", expectedWager.getLiabilityFancyWager()),String.format("FAILED! Stake does not display correct expected %s actual %s", String.format("%.2f", expectedWager.getLiabilityFancyWager()), lstBetslip.get(4)));
    }

}

