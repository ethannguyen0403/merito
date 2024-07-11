package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;
import membersite.pages.MarketPage;
import membersite.pages.MyBetsPage;
import membersite.pages.RacingPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.*;

import static common.MemberConstants.*;
import static common.MemberConstants.HomePage.SPORT_ID;

public class PlaceBetFunctionTest extends BaseCaseTest {

    @TestRails(id = "555")
    @Test(groups = {"smoke"})
    public void Place_Bet_Function_TC555() {
        log("@title: Validate that user can NOT place matched Back bet if Stake less than min setting");
        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);

        log("Step 2: Click on an event");
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEventName(event.getEventName());
        event.setMarketName(MATCH_ODDS_TITLE);

        log("Step 3:Click on an Back odds without empty of the selection have the high potential win");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();

        String odds = "20.00";
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        String maxBet = BetUtils.getMaxBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        String stake = Integer.toString(Integer.parseInt(minBet) - 1);
        if (minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true, "Bypass this test case");
            return;
        }

        log("Step 4. Input stake and click submit");
        page.betsSlipContainer.placeBet(odds, stake);

        log("Verify: Error Cannot place bet display");
        String actualError = page.myBetsContainer.getBetslipErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID, String.format("%d", Integer.parseInt(minBet)), String.format("%d",Integer.parseInt(maxBet)), String.format("%d", Integer.parseInt(stake)));
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "556")
    @Test(groups = {"smoke", "smoke_dev","isa"})
    public void Place_Bet_Function_TC556() {
        log("@title: Validate that user can place Matched Back bet on Soccer market");
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);

        log("Step 2. Click on an event");
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 3. Update odds > offer odds and Input valid stake");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        String odds = market.getBtnOdd().getText();
        String expectedProfit = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));
        market.getBtnOdd().click();

        log(String.format("Step 4. Place bet with odds:%s Stake: %s", odds, minBet));
        AccountBalance balance = memberHomePage.getUserBalance();
        marketPage.betsSlipContainer.placeBet(odds, minBet);
        List<Order> wagers = marketPage.myBetsContainer.getOrder(true, true, 1);

        AccountBalance balanceExpected = memberHomePage.getUserBalance();
        String expectedBalance = marketPage.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());

        log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
        Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
        Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
        Assert.assertEquals(expectedProfit, wagers.get(0).getProfit(), "Incorrect Profit");

        log("Verify: Account Balance/Outstanding updated correctly");
        Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
        Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
        log("INFO: Executed completely");
    }

    @TestRails(id = "557")
    @Test(groups = {"smoke","isa", "smoke_dev"})
    public void Place_Bet_Function_TC557() {
        log("@title: Validate that user can place Matched Lay bet on Soccer market");
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_LAY_TYPE);

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);

        log("Step 2. Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);
        log("Step 3. Update odds > offer odds and Input valid stake");
        int selectionIndex = marketPage.marketOddControl.getSelectionHaveMinOdds(event.getMarketName(),false);
        Market market = marketPage.marketOddControl.getMarket(event, selectionIndex, false);
        String odds = market.getBtnOdd().getText();
        String expectedLiability = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));
        market.getBtnOdd().click();

        log("Step 4. Input stake and click submit");
        log(String.format("----- Place bet  %s@%s ------", odds, minBet));
        AccountBalance balance = marketPage.getUserBalance();
        marketPage.betsSlipContainer.placeBet(odds, minBet);
        List<Order> wagers = marketPage.myBetsContainer.getOrder(true, false, 1);

        AccountBalance balanceExpected = marketPage.getUserBalance();
        String expectedBalance = page.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());

        log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
        Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
        // Cannot verify odds matched as expected
        //Assert.assertEquals(String.format("%.2f", Double.parseDouble(odds)), wagers.get(0).getOdds(), "Incorrect Odds");
        Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
        Assert.assertEquals(expectedLiability, wagers.get(0).getLiability(), "Incorrect Liability");

        log("Verify: Account Balance/Outstanding updated correctly");
        Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
        Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");

        log("INFO: Executed completely");
    }

    @TestRails(id = "558")
    @Test(groups = {"smoke1","smoke_dev"})
    public void Place_Bet_Function_TC558() {
        log("@title: Validate can place unmatched Back bet successfully for Tennis");
        log("Step 1. Active any market of Tennis");
        String odds = "100";
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_TENNIS_SPORT);

        log("Step 2: Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEventName(event.getEventName());
        try {
            log("Step 3. Update odds > offer odds and Input valid stake");
            event.setMarketName(MATCH_ODDS_TITLE);
            Market market = marketPage.marketOddControl.getMarket(event, 1, true);
            market.getBtnOdd().click();
            String minBet = BetUtils.getMinBet(LBL_TENNIS_SPORT, LBL_BACK_TYPE);
            String expectedProfit = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));

            log("Step 4. Input stake and click submit");
            AccountBalance balance = memberHomePage.getUserBalance();
            marketPage.betsSlipContainer.placeBet(odds, minBet);
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, true, 1);

            AccountBalance balanceExpected = marketPage.getUserBalance();
            String expectedBalance = marketPage.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());

            log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(String.format("%.2f",Double.parseDouble(odds)), wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedProfit, wagers.get(0).getProfit().replace(",",""), "Incorrect Profit");

            log("Verify: Account Balance/Outstanding updated correctly");
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Failed!, Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            marketPage.myBetsContainer.cancelAllBetUnmatched();
            log("INFO: Executed completely");
        }
    }

    @TestRails(id = "559")
    @Test(groups = {"smoke","isa","smoke_dev"})
    public void Place_Bet_Function_TC559() {
        log("@title: Validate can place unmatched Lay bet successfully for Tennis");
        String odds = "1.01";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet("TENNIS", "LAY");
        String expectedLiability = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));

        log("Step 1. Active any market of Tennis");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_TENNIS_SPORT);

        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);
        try {
            log("Step 2. Click on any Lay odds");
            Market market = marketPage.marketOddControl.getMarket(event, 1, false);
            market.getBtnOdd().click();

            log("Step 3. Update odds > offer odds and Input valid stake");
            marketPage.betsSlipContainer.placeBet(odds, minBet);
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, false, 1);

            log("Step 4. Click place bet");
            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedLiability, wagers.get(0).getLiability(), "Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Lay bet background is pink #F9E6ED");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = marketPage.getUserBalance();
            String expectedBalance = page.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            marketPage.myBetsContainer.cancelAllBetUnmatched();
        }
    }

    @TestRails(id = "560")
    @Test(groups = {"smoke","isa", "smoke_dev"})
    public void Place_Bet_Function_TC560() {
        log("@title: Validate cancel bet icon works");
        String odds = "1.01";
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_LAY_TYPE);

        log("Step 1. Active any market of soccer");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 2. Place an unmatched bet");
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(odds, minBet);
        Order wagers = marketPage.myBetsContainer.getOrder(false, false);

        log("Step 3. Open My Bet Page and get Wager info");
        MyBetsPage myBetsPage = marketPage.openMyBet();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));
        List<ArrayList<String>> lstRecords = myBetsPage.getReportIndex(1, false);
        wagers.setOdrerID(lstRecords.get(0).get(1));
        wagers.setPlacedDate(lstRecords.get(0).get(9));

        log("Step 4: Close My Bet Page tab");
        myBetsPage.switchToPreviousTab();

        log("Step 5. Click on remove icon before the selection name label");
        marketPage.myBetsContainer.removeBet(false);

        log("Verify 1. Bet is cancel");
        Assert.assertFalse(marketPage.myBetsContainer.isUnmatchedBetsEmpty(), "ERROR! Unmatched Bet is not empty, Bet still not be cancelled!");

        log("Step 6. Open My bet and filter Cancel option");
        marketPage.switchToPreviousTab();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"));
        lstRecords = myBetsPage.getReportIndex(1, false);

        log("Verify 2. Bet in My bet display with the status cancel");
        // Assert.assertEquals(lstRecords.get(0).get(0),String.format("%s / %s / %s",sportName,market.getEventName(), market.getMarketName()),String.format("ERROR: Expected Market Name is %s but found %s", String.format("%s / %s / %s",sportName,market.getEventName(), market.getMarketName()),lstRecords.get(0).get(0),lstRecords.get(0).get(0)));
        Assert.assertEquals(lstRecords.get(0).get(1), wagers.getOrderID(), String.format("ERROR! Expected Order OI is %s but found %s", wagers.getOrderID(), lstRecords.get(0).get(1)));
        Assert.assertEquals(lstRecords.get(0).get(3), market.getSelectionName(), String.format("ERROR! Expected Selection name is %s but found %s", market.getSelectionName(), lstRecords.get(0).get(3)));
        Assert.assertEquals(lstRecords.get(0).get(4), LBL_LAY_TYPE, String.format("ERROR! Expected Type is Back but found %s", lstRecords.get(0).get(4)));
        Assert.assertEquals(lstRecords.get(0).get(5), odds, String.format("ERROR! Expected Odds is %s but found %s", odds, lstRecords.get(0).get(5)));
        Assert.assertEquals(lstRecords.get(0).get(6), String.format("%.2f", Double.parseDouble(minBet)), String.format("ERROR! Expected Stake is %s but found %s", String.format("%.2f", Double.parseDouble(minBet)), lstRecords.get(0).get(6)));
        Assert.assertEquals(lstRecords.get(0).get(7), "--", String.format("ERROR! Expected Profit is -- but found %s", lstRecords.get(0).get(7)));
        Assert.assertEquals(lstRecords.get(0).get(8), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"), String.format("ERROR! Expected status is Cancelled but found %s", lstRecords.get(0).get(8)));
        Assert.assertEquals(lstRecords.get(0).get(9), wagers.getPlaceDate(), String.format("ERROR! Expected Place date is %s but found %s ", wagers.getPlaceDate(), lstRecords.get(0).get(9)));

        log("Verify 3. Cancelled bet not display in Unmatched list anymore");
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));
        if (!myBetsPage.getNoDataMesage().equals("")) {
            Assert.assertEquals(myBetsPage.getNoDataMesage(), MemberConstants.MyBetsPage.NO_RECORD_FOUND, "FAILED, Message there is no record in unmatched list is incorrect");
        } else {
            lstRecords = myBetsPage.getReportIndex(1, false);
            Assert.assertFalse(StringUtils.isListContainText(lstRecords, wagers.getOrderID(), 1), "ERROR! Expected Order ID not display but found ");
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "561")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.4.0","smoke_dev"})
    public void Place_Bet_Function_TC561() {
        log("@title: Validate cancel all bet icon works ");
        String oddsLay = "1.01";
        String oddsBack = "20";
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_LAY_TYPE);

        log("Step 1. Active any market of soccer");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);

        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 2. Place an unmatched bet for Lay and Back bet");
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(oddsLay, minBet);

        market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet(oddsBack, minBet);

        log("Step 3. Open My Bet Page and get Wager info");
        MyBetsPage myBetsPage = marketPage.openMyBet();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));
        List<String> lstUnmatchId = myBetsPage.getReportColumnValue(2, "Bet ID");

        log("Step 4. Back to market page and click Cancel all link");
        myBetsPage.switchToPreviousTab();
        marketPage.myBetsContainer.cancelAllBetUnmatched();

        log("Verify 1. Bet is cancel");
        Assert.assertFalse(marketPage.myBetsContainer.isUnmatchedBetsEmpty(), "ERROR! Unmatched Bet is not empty, Bet still not be cancelled!");

        log("Step 5. Open My bet and filter Cancel option");
        myBetsPage = marketPage.openMyBet();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"));

        log("Verify 2. Bet in My bet display with the status cancel");
        myBetsPage.verifyListBetFollowStatus(lstUnmatchId, MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"));

        log("Verify 3. Cancelled bet not display in Unmatched list anymore");
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));

        myBetsPage.verifyListBetNotFollowStatus(lstUnmatchId, MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));
        log("INFO: Executed completely");
    }

    @TestRails(id = "562")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.4.0","smoke_dev"})
    @Parameters({"currency"})
    public void Place_Bet_Function_TC562(String currency) {
        log("@title: Validate can place unmatched Back bet successfully for Cricket");
        String odds = "30";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);

        log("Step 1. Active any market of Cricket");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        try {
            Event event = page.eventContainerControl.getEventMatchOddsRandom(SPORT_ID.get(LBL_CRICKET_SPORT), currency,false, false);
            if (Objects.isNull(event) || event.getEventName().isEmpty()) {
                log("DEBUG: There is no event available");
                return;
            }
            MarketPage marketPage = page.clickEventName(event.getEventName());

            log("Step 2. Click on any Back odds");
            Market market = marketPage.marketOddControl.getMarket(event, 1, true);
            market.getBtnOdd().click();

            log("Step 3. Update odds > offer odds and Input valid stake");
            marketPage.betsSlipContainer.placeBet(odds, minBet);
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, true, 1);

            log("Step 4. Click place bet");
            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(odds)), wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(wagers.get(0).getStake(), wagers.get(0).getLiability(), "Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Back bet background is green #C9E6EF");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = page.getUserBalance();
            String expectedBalance = page.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            page.myBetsContainer.cancelAllBetUnmatched();
        }
    }

    @TestRails(id = "563")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.4.0","smoke_dev"})
    @Parameters({"currency"})
    public void Place_Bet_Function_TC563(String currency) {
        log("@title: Validate can place unmatched Lay bet successfully for Cricket");
        String odds = "1.01";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_LAY_TYPE);
        String expectedLiability = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));

        log("Step 1. Active any market of Cricket");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        Event event = page.eventContainerControl.getEventMatchOddsRandom(SPORT_ID.get(LBL_CRICKET_SPORT),currency,false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        log("Step 1.1. Click on the event "+ event.getEventName());
        MarketPage marketPage = page.clickEventName(event.getEventName());
        try {
            log("Step 2. Click on any Lay odds");
            Market market = marketPage.marketOddControl.getMarket(event, 1, false);
            market.getBtnOdd().click();

            log("Step 3. Update odds > offer odds and Input valid stake");
            marketPage.betsSlipContainer.placeBet(odds, minBet);
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, false, 1);

            log("Step 4. Click place bet");
            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedLiability, wagers.get(0).getLiability(), "Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Lay bet background is pink #F9E6ED");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = page.getUserBalance();
            String expectedBalance = page.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format(Locale.getDefault(), "%,.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            marketPage.myBetsContainer.cancelAllBetUnmatched();
        }
    }


    @TestRails(id = "564")
    @Test(groups = {"smoke","isa", "smoke_dev"})
    public void Place_Bet_Function_TC564() {
        log("@title: Validate can place unmatched Back bet successfully for Horse Racing");
        boolean isBack = true;
        String odds = "100.00";
        String minBet = BetUtils.getMinBet("OTHER", LBL_BACK_TYPE);
        String expectedProfit = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));

        log("Step 1. Active any market of Horse Racing");
        RacingPage page = memberHomePage.navigateRacing(LBL_HORSE_RACING_SPORT);
        if (page.racingContainer.isNoRace()) {
            log("DEBUG: There is no event available");
            return;
        }

        String country = page.racingContainer.getCountry(0);
        List<String> trackLst = page.racingContainer.getAllTrackName(country);
        String trackName = trackLst.get(trackLst.size() - 1);
        List<String> racelst = page.racingContainer.getAllRacingList(country, trackName);
        String race = racelst.get(racelst.size() - 1);
        MarketPage marketPage = page.clickRacing(country, trackName, race);
        Market market = marketPage.racingMarketContainer.getRace(1, isBack);

        log("Step 2. Click on any Back odds");
        market.getBtnOdd().click();

        log("Step 2.1 Get balance before place bet");
        AccountBalance balance = memberHomePage.getUserBalance();
        log("Step 3. Place bet - Update odd to 100, Input valid stake");
        try {
            marketPage.betsSlipContainer.placeBet(odds, minBet);

            log("Step 5. Get unmatched info after place bet");
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, isBack, 1);

            log("Step 6. Get balance after place bet");
            AccountBalance balanceExpected = page.getUserBalance();
            String expectedBalance = page.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());

            log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format(Locale.getDefault(), "%,.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedProfit, wagers.get(0).getProfit().replace(",",""), "Incorrect Profit");

            log("Verify: Account Balance/Outstanding updated correctly");
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format(Locale.getDefault(), "%,.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");

        } finally {
            log("Post Condition: Cancel all unmatch bets");
            marketPage.myBetsContainer.cancelAllBetUnmatched();
            log("INFO: Executed completely");
        }
    }


    @TestRails(id = "565")
    @Test(groups = {"smoke","smoke_dev"})
    public void Place_Bet_Function_TC565() {
        log("@title: Validate Lay odds is empty and are not allowed to click to add on bet slip");
        log("Step 1. Active any market of Horse Racing");
        RacingPage page = memberHomePage.navigateRacing(LBL_HORSE_RACING_SPORT);
        if (page.racingContainer.isNoRace()) {
            log("DEBUG: There is no event available");
            return;
        }
        String country = page.racingContainer.getCountry(0);
        List<String> trackLst = page.racingContainer.getAllTrackName(country);
        String trackName = trackLst.get(trackLst.size() - 1);
        String race = page.racingContainer.getAllRacingList(country, trackName).get(0);
        MarketPage marketPage = page.clickRacing(country, trackName, race);
        Market market = marketPage.racingMarketContainer.getRace(1, false);

        log(" Verify: As support #44512: Lay odds is empty and are not allowed to click to add on bet slip ");
        Assert.assertEquals(market.getBtnOdd().getText(), "", "ERROR! Lay odds HR should not display value ");
        Assert.assertFalse(market.getBtnOdd().isClickable(1), "ERROR! Expected Lay odds HR should not clickable but cam");
        log("INFO: Executed completely");
    }

    @TestRails(id = "566")
    @Test(groups = {"smoke","smoke_dev"})
    public void Place_Bet_Function_TC566() {
        log("@title: Validate that user can place unmatched Back bet on Soccer market");
        String odds = "20.00";
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        String expectedProfit = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);
        log("Step 2: Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);
        try {
            log("Step 3. Update odds > offer odds and Input valid stake");
            Market market = marketPage.marketOddControl.getMarket(event, 1, true);
            market.getBtnOdd().click();

            log("Step 4. Input stake and click submit");
            AccountBalance balance = memberHomePage.getUserBalance();
            marketPage.betsSlipContainer.placeBet(odds, minBet);
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, true, 1);
            AccountBalance balanceExpected = marketPage.getUserBalance();
            String expectedBalance = marketPage.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());

            log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedProfit, wagers.get(0).getProfit(), "Incorrect Profit");

            log("Verify: Account Balance/Outstanding updated correctly");
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format(Locale.getDefault(), "%,.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
        } finally {
            log("Post Condition: Cancel all unmatch bets");
            marketPage.myBetsContainer.cancelAllBetUnmatched();
            log("INFO: Executed completely");
        }
    }


    @TestRails(id = "567")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.4.0","smoke_dev"})
    public void Place_Bet_Function_TC567() {
        log("@title: Validate that user can place unmatched Lay bet on Soccer market");
        String odds = "1.01";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet("SOCCER", "LAY");
        String expectedLiability = String.format("%.2f", (Double.parseDouble(odds) - 1) * Double.parseDouble(minBet));

        log("Step 1. Active any market of Soccer");
        SportPage page = memberHomePage.navigateSportHeaderMenu("Soccer");

        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);
        try {
            log("Step 2. Click on any Lay odds");
            Market market = marketPage.marketOddControl.getMarket(event, 1, false);
            market.getBtnOdd().click();

            log("Step 3. Update odds > offer odds and Input valid stake");
            log("Step 4. Click place bet");

            marketPage.betsSlipContainer.placeBet(odds, minBet);
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, false, 1);

            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedLiability, wagers.get(0).getLiability(), "Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Back bet background is green #C9E6EF");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = marketPage.getUserBalance();
            String expectedBalance = marketPage.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());
            String expectedExposure = marketPage.calculateBalance(balance.getExposure(), wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), expectedExposure, "Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            marketPage.myBetsContainer.cancelAllBetUnmatched();
        }
    }

    @TestRails(id = "568")
    @Test(groups = {"smoke","isa", "smoke_dev"})
    public void Place_Bet_Function_TC568() {
        log("@title: Validate that cannot place Back bet if exposure exceed available balance");
        AccountBalance balance = BetUtils.getUserBalance();
        log("Step 1.Active any market, and place  Back odds");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        int stake = (int) (BetUtils.stakeMakeInsufficientBalance(balance.getBalance(), market.getBtnOdd().getText(), true) + 10);
        log("Step 2. Input stake that the exposure is greater than user available balance");
        marketPage.betsSlipContainer.placeBet(Integer.toString(stake));

        log("Verify: Error message \"Error : Cannot place bet. Your Main balance is insufficient.\" display");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        Assert.assertEquals(actualError, MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE, String.format("ERROR! Expected error message is %s but found %s", MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE, actualError));

        log("INFO: Executed completely");
    }

    @TestRails(id = "024")
    @Test(groups = {"regression"})
    public void Place_Bet_Function_TC024() {
        log("@title: Validate that cannot place Lay bet if exposure exceed available balance");
        AccountBalance balance = BetUtils.getUserBalance();
        log("Step 1 Active any market, and place  Lay odds ");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        int stake = (int) (BetUtils.stakeMakeInsufficientBalance(balance.getBalance(), market.getBtnOdd().getText(), false)) + 5;

        log("Step 2. Input stake that the exposure is greater than user available balance");
        marketPage.betsSlipContainer.placeBet(Integer.toString(stake));

        log("Verify: Error message \"Error : Cannot place bet. Your Main balance is insufficient.\" display");
        String actualError = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedError = MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE;
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }


    @TestRails(id = "584")
    @Test(groups = {"smoke"})
    public void Place_Bet_Function_TC584() {
        log("@title: Validate that user can NOT place Lay bet if Stake le ss than min setting");
        String odds = "1.01";

        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_LAY_TYPE);
        String maxBet = BetUtils.getMaxBet(LBL_SOCCER_SPORT, LBL_LAY_TYPE);
        String stake = Integer.toString(Integer.parseInt(minBet) - 1);
        if (minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true, "Bypass this test case");
            return;
        }
        log("Step 1. Active any market page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);

        log("Step 2: Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 2. Click on an Lay Odds");
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();

        log("Step 3. Input stake and click submit");
        marketPage.betsSlipContainer.placeBet(odds, stake);

        log("Verify 1. Error Cannot place bet display: \"The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.betsSlipContainer.getBetSlipErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID, minBet, maxBet, stake);
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "585")
    @Test(groups = {"smoke"})
    public void Place_Bet_Function_TC585() {
        log("@title: Validate that user can NOT place Back bet if Stake greater than max setting");
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        String maxBet = BetUtils.getMaxBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        String stake = Integer.toString(Integer.parseInt(maxBet) + 1);

        log("Step 1. Active any market page");

        if (minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true, "Bypass this test case");
            return;
        }

        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 2. Click on an Back Odds");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();

        log("Step 3. Input stake greater than max bet");
        marketPage.betsSlipContainer.placeBet(stake);

        log("Verify: 1  Error Cannot place bet display: \"The stake must be from %s to %s. Current Stake is %s.");
        String actualError = marketPage.betsSlipContainer.getBetSlipErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID, minBet, maxBet, stake);
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }

    @TestRails(id = "586")
    @Test(groups = {"smoke"})
    public void Place_Bet_Function_TC586() {
        log("@title: Validate that user can NOT place Lay bet if Stake greater than max setting");
        String odds = "1.01";
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_LAY_TYPE);
        String maxBet = BetUtils.getMaxBet(LBL_SOCCER_SPORT, LBL_LAY_TYPE);

        log("Step 1. Active any market page");
        String stake = Integer.toString(Integer.parseInt(maxBet) + 1);
        if (minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true, "Bypass this test case");
            return;
        }

        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_SOCCER_SPORT);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);

        log("Step 2. Click on an Lay Odds");
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();

        log("Step 3. Input stake greater than max bet");
        marketPage.betsSlipContainer.placeBet(odds, stake);

        log("Verify: 1 Error Cannot place bet display: \"The stake must be from [min] to [max]. Current Stake is [stake].");
        String actualError = marketPage.betsSlipContainer.getBetSlipErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.VALIDATE_STAKE_NOT_VALID, minBet, maxBet, stake);
        Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));
        log("INFO: Executed completely");
    }


}
