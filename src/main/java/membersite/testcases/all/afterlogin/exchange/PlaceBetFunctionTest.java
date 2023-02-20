package membersite.testcases.all.afterlogin.exchange;

import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.controls.sat.RacingMarketControl;
import membersite.objects.AccountBalance;
import membersite.objects.sat.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.MyBetsPage;
import membersite.pages.all.tabexchange.SoccerPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class PlaceBetFunctionTest extends BaseCaseMerito {
/*
   */
/**
     * @title: Validate that user can NOT place Back bet if Stake less than min setting
     * @steps:  1. Click Soccer menu
     *          2. Click on an event
     *          3. Click on an Back odds without empty of the selection have the high potential win
     *          4. Input stake less than min bet
     * @expect: 1. Error Cannot place bet display: "Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s."
     *//*

   @TestRails(id="555")
   @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC001(){
        log("@title: Validate that user can NOT place matched Back bet if Stake less than min setting");
        String odds ="20.00";
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        String stake = Integer.toString(Integer.parseInt(minBet)-1);
        if(minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true,"Bypass this test case");
            return;
        }

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        log("Step 2: Click on an event");
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);

        log("Step 3:Click on an Back odds without empty of the selection have the high potential win");
        Market market = page.marketContainerControl.getMarket(event,1,true);
        market.getBtnOdd().click();

        log("Step 4. Input stake and click submit");
        page.betSlipControl.placeBet(odds,stake);

        log("Verify: Error Cannot place bet display");
        String actualError = page.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minBet)),String.format("%(,.2f",Double.parseDouble(maxBet)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate that user can place Matched Back bet on Soccer market
     * @Precondition:  1. Login member site
     * @steps:      1. Click Soccer menu
     *              2. Click on an event
     *              3. Click on an Back odds without empty of the selection have the higher potential win
     *              4. Input stake and click submit
     * @expect:     1. Odd rate on My Bet and on Bet Slip is the same
     *              2. Market name on My Bet and on Bet Slip is the same
     *              3. Event name on My Bet and on Bet Slip is the same
     *              4. Selected team on My Bet and on Bet Slip is the same
     *              5. Liability on My Bet and on Bet Slip is the same
     *              6. Profit on My Bet and on Bet Slip is the same
     */
/*
    @TestRails(id="556")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC002(){
        log("@title: Validate that user can place Matched Back bet on Soccer market");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        log("Step 2. Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 3. Update odds > offer odds and Input valid stake");
        Market market = page.marketContainerControl.getMarket(event, 1, true);
        String odds = market.getBtnOdd().getText();
        String expectedProfit = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));
        market.getBtnOdd().click();

        log(String.format("Step 4. Place bet with odds:%s Stake: %s",odds,minBet));
        AccountBalance balance = memberHomePage.getUserBalance();
        page.betSlipControl.placeBet(odds, minBet);
        List<Order> wagers = page.myBetControl.getOrder(true, true, 1);

        AccountBalance balanceExpected = page.getUserBalance();
        String expectedBalance = page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());

        log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
        Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
        Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
        Assert.assertEquals(expectedProfit, wagers.get(0).getProfit(), "Incorrect Profit");

        log("Verify: Account Balance/Outstanding updated correctly");
        Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
        Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate that user can place Matched Lay bet on Soccer market
     * @Precondition:  1. Login member site
     * @steps:      1. Click Soccer menu
     *              2. Click on an event
     *              3. Click on an Lay odds without empty of the selection have the higher potential win
     *              4. Input stake and click submit
     * @expect:     1. Odd rate on My Bet and on Bet Slip is the same
     *              2. Market name on My Bet and on Bet Slip is the same
     *              3. Event name on My Bet and on Bet Slip is the same
     *              4. Selected team on My Bet and on Bet Slip is the same
     *              5. Liability on My Bet and on Bet Slip is the same
     *              6. Profit on My Bet and on Bet Slip is the same
     *//*

    @TestRails(id="557")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC003(){
        log("@title: Validate that user can place Matched Lay bet on Soccer market");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        log("Step 2. Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 3. Update odds > offer odds and Input valid stake");
        int selectionIndex = page.marketContainerControl.getSelectionHaveMinOdds(false);
        Market market = page.marketContainerControl.getMarket(event,selectionIndex,false);
        String odds = market.getBtnOdd().getText();
        String expectedLiability = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));
        market.getBtnOdd().click();

        log("Step 4. Input stake and click submit");
        log(String.format("----- Place bet  %s@%s ------",odds,minBet));
        AccountBalance balance = memberHomePage.getUserBalance();
        page.betSlipControl.placeBet(odds, minBet);
        List<Order> wagers = page.myBetControl.getOrder(true, false, 1);

        AccountBalance balanceExpected = page.getUserBalance();
        String expectedBalance = page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());
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

    */
/**
     * @title: Validate can place unmatched Back bet successfully for Tennis
     * @Precondition:  1. Login member site
     * @steps:         1. Active any market of Tennis
     *                 2. Click on any Back odds
     *                 3. Update odds to 100 and Input valid stake
     *                 4. Click place bet
     * @expect:         1. Bet is display in unmatched section
     *                  2. Remove unmatched bet icon display in front off selection name
     *                  3. Selection , Odds, Stake, Profit display correctly
     *                  4. At in-play will check  on Cancel option by default
     *                  5. Back bet background is green #C9E6EF
     *                  6. Account Balance/Outstanding updated correctly
     *//*

    @TestRails(id="558")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC004(){
        log("@title: Validate can place unmatched Back bet successfully for Tennis");
        String odds ="20.00";

        String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.BACK);
        String expectedProfit = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));

        log("Step 1. Active any market of Tennis");
        SportPage page = memberHomePage.navigateSportMenu("Tennis",SportPage.class);

        try {
            log("Step 2: Click on an event");
            //TODO: Update Odd to Event object
            Event event = page.eventContainerControl.getEventRandom(false,false);
            if (Objects.isNull(event)) {
                log("DEBUG: There is no event available");
                return;
            }
            page.clickEvent(event);
            page.marketContainerControl.waitControlLoadCompletely(5);

            log("Step 3. Update odds > offer odds and Input valid stake");
            Market market = page.marketContainerControl.getMarket(event, 1, true);
            market.getBtnOdd().click();

            log("Step 4. Input stake and click submit");
            AccountBalance balance = memberHomePage.getUserBalance();
            page.betSlipControl.placeBet(odds, minBet);
            List<Order> wagers = page.myBetControl.getOrder(false, true, 1);

            AccountBalance balanceExpected = page.getUserBalance();
            String expectedBalance =page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());

            log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedProfit, wagers.get(0).getProfit(), "Incorrect Profit");

            log("Verify: Account Balance/Outstanding updated correctly");
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
        }finally {
            log("Post Condition: Cancel all unmatched bets");
            page.myBetControl.cancelAllBetUnmatched();
            log("INFO: Executed completely");
        }
    }

    */
/**
     * @title: Validate can place unmatched Lay bet successfully for Tennis
     * @Precondition:  1. Login member site
     * @steps:        1. Active any market of Tennis
     *               2. Click on any Lay odds
     *               3. Update odd to 1.01, Input valid stake
     *               4. Click place bet
     * @expect: 1. Bet is display in unmatched section
     *          2. Remove unmatched bet icon display in front off selection name
     *          3. Selection , Odds, Stake, Profit display correctly
     *          4. At in-play will check  on Cancel option by default
     *          5. Lay bet background is pink #F9E6ED
     *          6. Account Balance/Outstanding updated correctly
     *//*

    @TestRails(id="559")
    @Test(groups = {"smoke"})
     public void FE_Place_Bet_Function_TC005(){
            log("@title: Validate can place unmatched Lay bet successfully for Tennis");
            String odds ="1.01";
            AccountBalance balance = memberHomePage.getUserBalance();
            String minBet = BetUtils.getMinBet(SportPage.Sports.TENNIS, SportPage.BetType.LAY);
            String expectedLiability = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));

            log("Step 1. Active any market of Tennis");
            SportPage page = memberHomePage.navigateSportMenu("Tennis",SportPage.class);
            try {
                Event event = page.eventContainerControl.getEventRandom(false,false);
                if(Objects.isNull(event)) {
                    log("DEBUG: There is no event available");
                    return;
                }
                page.clickEvent(event);
                page.marketContainerControl.waitControlLoadCompletely(5);

                log("Step 2. Click on any Lay odds");
                Market market = page.marketContainerControl.getMarket(event,1,false);
                market.getBtnOdd().click();

                log("Step 3. Update odds > offer odds and Input valid stake");
                page.betSlipControl.placeBet(odds,minBet);
                List<Order> wagers =page.myBetControl.getOrder(false,false,1);

                log("Step 4. Click place bet");
                log("Verify 1. Bet is display in unmatched section");
                log("Verify 2. Remove unmatched bet icon display in front off selection name");
                log("Verify 3. Selection , Odds, Stake, Profit display correctly");
                Assert.assertEquals(market.getSelectionName(),wagers.get(0).getSelectionName(),"Place on incorrect selection");
                Assert.assertEquals(odds,wagers.get(0).getOdds(),"Incorrect Odds");
                Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(),"Incorrect Stake");
                Assert.assertEquals(expectedLiability,wagers.get(0).getLiability(),"Incorrect Liability");

                log("Verify 4. At in-play will check  on Cancel option by default");
                log("Verify 5. Lay bet background is pink #F9E6ED");
                log("Verify 6. Account Balance/Outstanding updated correctly");
                AccountBalance balanceExpected = page.getUserBalance();
                String expectedBalance =page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());
                Assert.assertEquals(balanceExpected.getBalance(),expectedBalance,"Balance update incorrectly after place bet");
                Assert.assertEquals(balanceExpected.getExposure(),String.format("%.2f",Double.parseDouble(balance.getExposure())-Double.parseDouble(wagers.get(0).getLiability())),"Outstanding update incorrectly after place bet");
                log("INFO: Executed completely");
            } finally {
                log("Post Condition: Cancel all unmatched bets");
                page.myBetControl.cancelAllBetUnmatched();
            }
     }

    */
/**
     * @title: Validate cancel bet icon works
     * @Precondition:  1. Login member site
     * @steps:          1. Active any market of soccer
     *                  2. Place an unmatched bet
     *                  3. Wait for the unmatched bets display
     *                  4. Click on remove icon before the selection name label
     *                  5 Open My bet and filter Cancel option
     * @expect:         1. Bet is cancel
     *                  2. Bet in My bet display with the status cancel
     *//*

    @TestRails(id="560")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC006(){
        log("@title: Validate cancel bet icon works");
        String odds ="1.01";
        String sportName ="Soccer";
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1. Active any market of soccer");
        SportPage page = memberHomePage.navigateSportMenu(sportName,SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Place an unmatched bet");
        Market market = page.marketContainerControl.getMarket(event,1,false);
        market.getBtnOdd().click();

        page.betSlipControl.placeBet(odds,minBet);
        Order wagers =page.myBetControl.getOrder(false,false);

        log("Step 3. Open My Bet Page and get Wager info");
        MyBetsPage myBetsPage = page.satHeaderControl.openMyBets();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));
        List<ArrayList<String>> lstRecords = myBetsPage.tblReport.getRowsWithoutHeader(1, false);
        wagers.setOdrerID(lstRecords.get(0).get(1));
        wagers.setPlacedDate(lstRecords.get(0).get(9));

        log("Step 4: Close My Bet Page tab");
        myBetsPage.switchToPreviousTab();

        log("Step 5. Click on remove icon before the selection name label");
        page.myBetControl.removeBet(false);

        log("Verify 1. Bet is cancel");
        Assert.assertFalse(page.myBetControl.isUnmatchedBetsEmpty(),"ERROR! Unmatched Bet is not empty, Bet still not be cancelled!");

        log("Step 6. Open My bet and filter Cancel option");
        page.switchToPreviousTab();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"));
        lstRecords = myBetsPage.tblReport.getRowsWithoutHeader(1, false);

        log("Verify 2. Bet in My bet display with the status cancel");
       // Assert.assertEquals(lstRecords.get(0).get(0),String.format("%s / %s / %s",sportName,market.getEventName(), market.getMarketName()),String.format("ERROR: Expected Market Name is %s but found %s", String.format("%s / %s / %s",sportName,market.getEventName(), market.getMarketName()),lstRecords.get(0).get(0),lstRecords.get(0).get(0)));
        Assert.assertEquals(lstRecords.get(0).get(1), wagers.getOrderID(), String.format("ERROR! Expected Order OI is %s but found %s",wagers.getOrderID(),lstRecords.get(0).get(1)));
        Assert.assertEquals(lstRecords.get(0).get(3), market.getSelectionName(),String.format("ERROR! Expected Selection name is %s but found %s",market.getSelectionName(),lstRecords.get(0).get(3)));
        Assert.assertEquals(lstRecords.get(0).get(4), "Lay",String.format("ERROR! Expected Type is Back but found %s",lstRecords.get(0).get(4)));
        Assert.assertEquals(lstRecords.get(0).get(5),odds,String.format("ERROR! Expected Odds is %s but found %s",odds,lstRecords.get(0).get(5)));
        Assert.assertEquals(lstRecords.get(0).get(6), String.format("%.2f",Double.parseDouble(minBet)),String.format("ERROR! Expected Stake is %s but found %s",String.format("%.2f",Double.parseDouble(minBet)),lstRecords.get(0).get(6)));
        Assert.assertEquals(lstRecords.get(0).get(7),"--",String.format("ERROR! Expected Profit is -- but found %s",lstRecords.get(0).get(7)));
        Assert.assertEquals(lstRecords.get(0).get(8), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"),String.format("ERROR! Expected status is Cancelled but found %s",lstRecords.get(0).get(8)));
        Assert.assertEquals(lstRecords.get(0).get(9),wagers.getPlaceDate(),String.format("ERROR! Expected Place date is %s but found %s ",wagers.getPlaceDate(),lstRecords.get(0).get(9)));

        log("Verify 3. Cancelled bet not display in Unmatched list anymore");
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));
        if(myBetsPage.lblNoRecord.isDisplayed()){
            Assert.assertEquals(myBetsPage.lblNoRecord.getText(), MemberConstants.MyBetsPage.NO_RECORD_FOUND,"FAILED, Message there is no record in unmatched list is incorrect");
        }else {
            lstRecords = myBetsPage.tblReport.getRowsWithoutHeader(1, false);
            Assert.assertFalse(StringUtils.isListContainText(lstRecords, wagers.getOrderID(), 1), "ERROR! Expected Order ID not display but found ");
        }
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate cancel all bet icon works
     * @Precondition:  1. Login member site
     * @steps:          1. Active any market of soccer
     *                  2. Place an unmatched bet for Lay and Back bet
     *                  3. Wait for the unmatched bets display
     *                  4. Click on Cancel all link
     *                  5 Open My bet and filter Cancel option
     * @expect:     1. Back and Lay bet are canceled
     *              2. Bets in My bet display with the status cancel
     *//*

    @TestRails(id="561")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC007(){
        log("@title: Validate cancel bet icon works");
        String oddsLay ="1.01";
        String oddsBack ="20";
        String sportName ="Soccer";
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1. Active any market of soccer");
        SoccerPage page = memberHomePage.navigateSportMenu(sportName,SoccerPage.class);

        Event event = page.eventContainerControl.getEvent(false,false,30,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Place an unmatched bet for Lay and Back bet");
        Market market = page.marketContainerControl.getMarket(event,1,false);
        market.getBtnOdd().click();
        page.betSlipControl.placeBet(oddsLay,minBet);

        market = page.marketContainerControl.getMarket(event,1,true);
        market.getBtnOdd().click();
        page.betSlipControl.placeBet(oddsBack,minBet);

        Order wager1 =page.myBetControl.getOrder(false,false);
        Order wager2 =page.myBetControl.getOrder(false,true);

        log("Step 3. Open My Bet Page and get Wager info");
        MyBetsPage myBetsPage = page.satHeaderControl.openMyBets();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));
        List<ArrayList<String>> lstUnmatched = myBetsPage.tblReport.getRowsWithoutHeader(3, false);

        log("Step 4. Back to market page and click Cancel all link");
        myBetsPage.switchToPreviousTab();
        page.myBetControl.cancelAllBetUnmatched();

        log("Verify 1. Bet is cancel");
        Assert.assertFalse(page.myBetControl.isUnmatchedBetsEmpty(),"ERROR! Unmatched Bet is not empty, Bet still not be cancelled!");

        log("Step 5. Open My bet and filter Cancel option");
        myBetsPage = page.satHeaderControl.openMyBets();
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"));
        List<ArrayList<String>> lstCancelled = myBetsPage.tblReport.getRowsWithoutHeader(2, false);

        log("Verify 2. Bet in My bet display with the status cancel");
        for (int i = 0, n= lstCancelled.size(); i<n; i++){
            Assert.assertEquals(lstCancelled.get(i).get(1),lstUnmatched.get(i).get(1), String.format("ERROR! Expected Order ID is %s but found %s",lstUnmatched.get(i).get(1),lstCancelled.get(0).get(1)));
            Assert.assertEquals(lstCancelled.get(i).get(8), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("CANCELLED"),String.format("ERROR! Expected status is Cancelled but found %s",lstCancelled.get(0).get(8)));
        }

        log("Verify 3. Cancelled bet not display in Unmatched list anymore");
        myBetsPage.filter(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"), MemberConstants.MyBetsPage.DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));

        if(myBetsPage.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(myBetsPage.lblNoRecord.getText(), MemberConstants.MyBetsPage.NO_RECORD_FOUND,"FAILED, Message there is no record in unmatch list is incorrect");
        }
        else{
            lstUnmatched= myBetsPage.tblReport.getRowsWithoutHeader(1, false);
            Assert.assertFalse(StringUtils.isListContainText(lstUnmatched,wager1.getOrderID(),1), "ERROR! Expected Order ID not display but found ");
            Assert.assertFalse(StringUtils.isListContainText(lstUnmatched,wager2.getOrderID(),1), "ERROR! Expected Order ID not display but found ");
        }


        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate can place unmatched Back bet successfully for Cricket
     * @Precondition:  1. Login member site
     * @steps:        1. Active any market of Cricket
     *               2. Click on any Back odds
     *               3. Update odds to 100 and Input valid stake
     *               4. Click place bet
     * @expect: 1. Bet is display in unmatched section
     *          2. Remove unmatched bet icon display in front off selection name
     *          3. Selection , Odds, Stake, Profit display correctly
     *          4. At in-play will check  on Cancel option by default
     *          5. Lay  bet background is pink #F9E6ED
     *          6. Account Balance/Outstanding updated correctly
     *//*

    @TestRails(id="562")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC008(){
        log("@title: Validate can place unmatched Back bet successfully for Cricket");
        String odds ="30";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.BACK);

        log("Step 1. Active any market of Cricket");
        SportPage page = memberHomePage.navigateSportMenu("Cricket", SportPage.class);
        try {
            Event event = page.eventContainerControl.getEventRandom(false,false);
            if(Objects.isNull(event)) {
                log("DEBUG: There is no event available");
                return;
            }
            page.clickEvent(event);
            page.marketContainerControl.waitControlLoadCompletely(5);

            log("Step 2. Click on any Back odds");
            Market market = page.marketContainerControl.getMarket(event,1,true);
            market.getBtnOdd().click();

            log("Step 3. Update odds > offer odds and Input valid stake");
            page.betSlipControl.placeBet(odds,minBet);
            List<Order> wagers =page.myBetControl.getOrder(false,true,1);

            log("Step 4. Click place bet");
            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(),wagers.get(0).getSelectionName(),"Place on incorrect selection");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(odds)),wagers.get(0).getOdds(),"Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(),"Incorrect Stake");
            Assert.assertEquals(wagers.get(0).getStake(),wagers.get(0).getLiability(),"Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Back bet background is green #C9E6EF");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = page.getUserBalance();
             String expectedBalance =page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(),expectedBalance,"Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(),String.format("%.2f",Double.parseDouble(balance.getExposure())-Double.parseDouble(wagers.get(0).getLiability())),"Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            page.myBetControl.cancelAllBetUnmatched();
        }
    }

    */
/**
     * @title: Validate can place unmatched Lay bet successfully for Cricket
     * @Precondition:  1. Login member site
     * @steps:        1. Active any market of Cricket
     *               2. Click on any Lay odds
     *               3. Update odd to 1.01, Input valid stake
     *               4. Click place bet
     * @expect: 1. Bet is display in unmatched section
     *          2. Remove unmatched bet icon display in front off selection name
     *          3. Selection , Odds, Stake, Profit display correctly
     *          4. At in-play will check  on Cancel option by default
     *          5. Lay bet background is pink #F9E6ED
     *          6. Account Balance/Outstanding updated correctly
     *//*

    @TestRails(id="563")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC009(){
        log("@title: Validate can place unmatched Lay bet successfully for Cricket");
        String odds ="1.01";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet(SportPage.Sports.CRICKET, SportPage.BetType.LAY);
        String expectedLiability = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));

        log("Step 1. Active any market of Cricket");
        SportPage page = memberHomePage.navigateSportMenu("Cricket",SportPage.class);
        try {
            Event event = page.eventContainerControl.getEventRandom(false,false);
            if(Objects.isNull(event)) {
                log("DEBUG: There is no event available");
                return;
            }
            page.clickEvent(event);
            page.marketContainerControl.waitControlLoadCompletely(5);

            log("Step 2. Click on any Lay odds");
            Market market = page.marketContainerControl.getMarket(event,1,false);
            market.getBtnOdd().click();

            log("Step 3. Update odds > offer odds and Input valid stake");
            page.betSlipControl.placeBet(odds,minBet);
            List<Order> wagers =page.myBetControl.getOrder(false,false,1);

            log("Step 4. Click place bet");
            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(),wagers.get(0).getSelectionName(),"Place on incorrect selection");
            Assert.assertEquals(odds,wagers.get(0).getOdds(),"Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(),"Incorrect Stake");
            Assert.assertEquals(expectedLiability,wagers.get(0).getLiability(),"Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Lay bet background is pink #F9E6ED");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = page.getUserBalance();
             String expectedBalance =page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(),expectedBalance,"Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(),String.format(Locale.getDefault(),"%,.2f",Double.parseDouble(balance.getExposure())-Double.parseDouble(wagers.get(0).getLiability())),"Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            page.myBetControl.cancelAllBetUnmatched();
        }
    }

    */
/**
     * @title: Validate can place unmatched Back bet successfully for Horse Racing
     * @Precondition:  1. Login member site
     * @steps:  1. Active any market of Horse Racing
     *          2. Click on any Back odds
     *          3. Update odds to 100 and Input valid stake
     *          4. Click place bet
     * @expect: 1. Bet is display in unmatched section
     *          2. Remove unmatched bet icon display in front off selection name
     *          3. Selection , Odds, Stake, Profit display correctly
     *          4. At in-play will check  on Cancel option by default
     *          5. Back bet background is green #C9E6EF
     *//*

    @TestRails(id="564")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC010(){
        log("@title: Validate can place unmatched Back bet successfully for Horse Racing");
        boolean isBack = true;
        String odds ="100.00";
        String minBet = BetUtils.getMinBet(SportPage.Sports.OTHER, SportPage.BetType.BACK);
        String expectedProfit = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));

        log("Step 1. Active any market of Horse Racing");
        SportPage page = memberHomePage.navigateSportMenu("Horse Racing",SportPage.class);
        if(page.racingContainerControl.lblNoRace.isDisplayed())
        {
            log("DEBUG: There is no event available");
            return;
        }
        try{
        String country = page.racingContainerControl.getCountry(0);
        List<String> trackLst = page.racingContainerControl.getAllTrackName(country);
        String trackName = trackLst.get(trackLst.size()-1);
        List<String> racelst =page.racingContainerControl.getAllRacingList(country,trackName);
        String race = racelst.get(racelst.size()-1);
        RacingMarketControl raceMarketContainer = page.racingContainerControl.clickRacing(country, trackName,race);
        Market market = raceMarketContainer.getRace(1,isBack);

        log("Step 2. Click on any Back odds");
        market.getBtnOdd().click();

        log("Step 2.1 Get balance before place bet");
        AccountBalance balance = memberHomePage.getUserBalance();

        log("Step 3. Place bet - Update odd to 100, Input valid stake");

        page.betSlipControl.placeBet(odds, minBet);

        log("Step 5. Get unmatched info after place bet");
        List<Order> wagers = page.myBetControl.getOrder(false, isBack, 1);

        log("Step 6. Get balance after place bet");
        AccountBalance balanceExpected = page.getUserBalance();
        String expectedBalance = page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());

        log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
        Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
        Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
        Assert.assertEquals(String.format(Locale.getDefault(),"%,.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
        Assert.assertEquals(expectedProfit, wagers.get(0).getProfit(), "Incorrect Profit");

        log("Verify: Account Balance/Outstanding updated correctly");
        Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
        Assert.assertEquals(balanceExpected.getExposure(), String.format(Locale.getDefault(),"%,.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");

        }finally {
            log("Post Condition: Cancel all unmatch bets");
            page.myBetControl.cancelAllBetUnmatched();
            log("INFO: Executed completely");
        }
    }

    */
/**
     * @title:  As support #44512: Lay odds is empty and are not allowed to click to add on bet slip
     * @Precondition:  1. Login member site
     * @steps:  1. Active any market of Horse Racing
     *          2. Click on any Lay odds
     * @expect: 1.  As support #44512: Lay odds is empty and are not allowed to click to add on bet slip
     *//*

    @TestRails(id="565")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC011(){
        log("@title:  As support #44512: Lay odds is empty and are not allowed to click to add on bet slip");
        log("Step 1. Active any market of Horse Racing");
        SportPage page = memberHomePage.navigateSportMenu("Horse Racing",SportPage.class);
        if(page.racingContainerControl.lblNoRace.isDisplayed())
        {
            log("DEBUG: There is no event available");
            return;
        }
        String country = page.racingContainerControl.getCountry(0);
        List<String> trackLst = page.racingContainerControl.getAllTrackName(country);
        String trackName = trackLst.get(trackLst.size()-1);
        String race = page .racingContainerControl.getAllRacingList(country,trackName).get(0);
        RacingMarketControl raceMarketContainer = page.racingContainerControl.clickRacing(country, trackName,race);
        Market market = raceMarketContainer.getRace(1,false);

        log(" Verify: As support #44512: Lay odds is empty and are not allowed to click to add on bet slip ");
        Assert.assertEquals(market.getBtnOdd().getText(),"","ERROR! Lay odds HR should not display value ");
        Assert.assertFalse(market.getBtnOdd().isClickable(1),"ERROR! Expected Lay odds HR should not clickable but cam");
        log("INFO: Executed completely");


    }

    */
/**
     * @title: Validate that user can place Unmatched Back bet on Soccer market
     * @Precondition:  1. Login member site
     * @steps:  1. Active any market of Soccer
     *          2. Click on any Back odds
     *          3. Update odds < offer odds and Input valid stake
     *          4. Click place bet
     * @expect: 1. Bet is display in unmatched section
     *          2. Remove unmatched bet icon display in front off selection name
     *          3. Selection , Odds, Stake, Profit display correctly
     *          4. At in-play will check  on Cancel option by default
     *          5. Back bet background is green #C9E6EF
     *          6. Account Balance/Outstanding updated correctly
     *//*

    @TestRails(id="566")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC021(){
        log("@title: Validate that user can place unmatched Back bet on Soccer market");
        String odds ="20.00";

        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        String expectedProfit = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));

        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        try {
            log("Step 2: Click on an event");
            //TODO: Update Odd to Event object
            Event event = page.eventContainerControl.getEventRandom(false,false);
            if (Objects.isNull(event)) {
                log("DEBUG: There is no event available");
                return;
            }
            page.clickEvent(event);
            page.marketContainerControl.waitControlLoadCompletely(5);

            log("Step 3. Update odds > offer odds and Input valid stake");
            Market market = page.marketContainerControl.getMarket(event, 1, true);
            market.getBtnOdd().click();

            log("Step 4. Input stake and click submit");
            AccountBalance balance = memberHomePage.getUserBalance();
            page.betSlipControl.placeBet(odds, minBet);
            List<Order> wagers = page.myBetControl.getOrder(false, true, 1);
            AccountBalance balanceExpected = page.getUserBalance();
            String expectedBalance =page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());

            log("Verify: Mini My Bet display correct info, Selection name, Odds, Stake, Profit/Liability");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(odds, wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(expectedProfit, wagers.get(0).getProfit(), "Incorrect Profit");

            log("Verify: Account Balance/Outstanding updated correctly");
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format(Locale.getDefault(),"%,.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
        }finally {
            log("Post Condition: Cancel all unmatch bets");
            page.myBetControl.cancelAllBetUnmatched();
            log("INFO: Executed completely");
        }
    }

    */
/**
     * @title: Validate that user can place Unmatched Lay bet on Soccer market
     * @Precondition:  1. Login member site
     * @steps:  1. Active any market of Soccer
     *          2. Click on any Lay odds
     *          3. Update odds < offer odds and Input valid stake
     *          4. Click place bet
     * @expect: 1. Bet is display in unmatched section
     *          2. Remove unmatched bet icon display in front off selection name
     *          3. Selection , Odds, Stake, Profit display correctly
     *          4. At in-play will check  on Cancel option by default
     *          5. Back bet background is green #C9E6EF
     *          6. Account Balance/Outstanding updated correctly
     *//*

    @TestRails(id="567")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC022(){
        log("@title: Validate that user can place unmatched Lay bet on Soccer market");
        String odds ="1.01";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);
        String expectedLiability = String.format("%.2f",(Double.parseDouble(odds)-1)*Double.parseDouble(minBet));

        log("Step 1. Active any market of Soccer");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);
        try {
            Event event = page.eventContainerControl.getEventRandom(false,false);
            if(Objects.isNull(event)) {
                log("DEBUG: There is no event available");
                return;
            }
            page.clickEvent(event);
            page.marketContainerControl.waitControlLoadCompletely(5);

            log("Step 2. Click on any Lay odds");
            Market market = page.marketContainerControl.getMarket(event,1,false);
            market.getBtnOdd().click();

            log("Step 3. Update odds > offer odds and Input valid stake");
            log("Step 4. Click place bet");

            page.betSlipControl.placeBet(odds,minBet);
            List<Order> wagers =page.myBetControl.getOrder(false,false,1);

            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(),wagers.get(0).getSelectionName(),"Place on incorrect selection");
            Assert.assertEquals(odds,wagers.get(0).getOdds(),"Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(),"Incorrect Stake");
            Assert.assertEquals(expectedLiability,wagers.get(0).getLiability(),"Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Back bet background is green #C9E6EF");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = page.getUserBalance();
            String expectedBalance ="";
            String expectedExposure ="";
            expectedBalance = page.calculateBalance(balance.getBalance(),wagers.get(0).getLiability());
            expectedExposure =  page.calculateBalance(balance.getExposure(),wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(),expectedBalance,"Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(),expectedExposure,"Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            page.myBetControl.cancelAllBetUnmatched();
        }
    }

    */
/**
     * @title: Validate that cannot place Back bet if exposure exceed available balance
     * @precondition:  1. Login member site
     * @steps:  1. Active any market, and place  Back odds
     *          2. Input stake that the exposure is greater than user available balance
     * @expect: 1. Error message "Error : Cannot place bet. Your Main balance is insufficient." display
     *//*

    @TestRails(id="568")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC023(){
        log("@title: Validate that cannot place Back bet if exposure exceed available balance");
        AccountBalance balance = BetUtils.getUserBalance();
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        log("Step 1.Active any market, and place  Back odds");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEvent(false,false,20,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);
        Market market = page.marketContainerControl.getMarket(event,1,true);
        market.getBtnOdd().click();
        int stake = (int)(BetUtils.stakeMakeInsufficientBalance(balance.getBalance(),market.getBtnOdd().getText(),true) +10);
        log("Step 2. Input stake that the exposure is greater than user available balance");
        page.betSlipControl.placeBet(Integer.toString(stake));

        log("Verify: Error message \"Error : Cannot place bet. Your Main balance is insufficient.\" display");
        String actualError = page.myBetControl.getPlaceBetErrorMessage();
        String expectedError = page.defineErrorMessage(Double.valueOf(stake),Double.valueOf(minBet),Double.valueOf(maxBet),BetUtils.getUserBalance());
      //  String expectedError = FEMemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE;
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate that cannot place Lay bet if exposure exceed available balance
     * @precondition:  1. Login member site
     * @steps:  1. Active any market, and place Lay odds
     *          2. Input stake that the exposure is greater than user available balance
     * @expect: 1. Error message "Error : Cannot place bet. Your Main balance is insufficient." display
     *//*

    @Test(groups = {"regression"})
    public void FE_Place_Bet_Function_TC024(){
        log("@title: Validate that cannot place Lay bet if exposure exceed available balance");
        AccountBalance balance = BetUtils.getUserBalance();
        log("Step 1 Active any market, and place  Lay odds ");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEvent(false,false,20,2);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);
        Market market = page.marketContainerControl.getMarket(event,1, false);
        market.getBtnOdd().click();
        int stake = (int)(BetUtils.stakeMakeInsufficientBalance(balance.getBalance(),market.getBtnOdd().getText(), false))+5;

        log("Step 2. Input stake that the exposure is greater than user available balance");
        page.betSlipControl.placeBet(Integer.toString(stake));

        log("Verify: Error message \"Error : Cannot place bet. Your Main balance is insufficient.\" display");
        String actualError = page.myBetControl.getPlaceBetErrorMessage();
        String expectedError = MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE;
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate that user can NOT place Lay bet if Stake less than min setting
     * @steps:  1. Active any market page
     *          2. Click on an Lay Odds
     *          3. Input stake less than min bet
     * @expect: 1. Error Cannot place bet display: "Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s."
     *//*

    @TestRails(id="584")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC025(){
        log("@title: Validate that user can NOT place Lay bet if Stake less than min setting");
        String odds ="1.01";

        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);
        String stake = Integer.toString(Integer.parseInt(minBet)-1);
        if(minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true,"Bypass this test case");
            return;
        }
        log("Step 1. Active any market page");
        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);

        log("Step 2: Click on an event");
        //TODO: Update Odd to Event object
        Event event = page.eventContainerControl.getEvent(false,false,30,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Click on an Lay Odds");
        Market market = page.marketContainerControl.getMarket(event,1,false);
        market.getBtnOdd().click();

        log("Step 3. Input stake and click submit");
        page.betSlipControl.placeBet(odds,stake);

        log("Verify 1. Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = page.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minBet)),String.format("%(,.2f",Double.parseDouble(maxBet)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate that user can NOT place Back bet if Stake greater than max setting
     * @precondition 1 There is an unblocked soccer event
     *              2 Login member site
     *              3. Get max bet setting of login  account
     * @steps:      1. Active any market page
     *              2. Click on an Back Odds
     *              3. Input stake greater than max bet
     * @expect:     1  Error Cannot place bet display: "Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.""
     *//*

    @TestRails(id="585")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC026(){
        log("@title: Validate that user can NOT place Back bet if Stake greater than max setting");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        String stake = Integer.toString(Integer.parseInt(maxBet)+1);

        log("Step 1. Active any market page");

        if(minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true,"Bypass this test case");
            return;
        }

        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEvent(false,false,30,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Click on an Back Odds");
        Market market = page.marketContainerControl.getMarket(event,1,true);
        market.getBtnOdd().click();

        log("Step 3. Input stake greater than max bet");
        page.betSlipControl.placeBet(stake);

        log("Verify: 1  Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from %s to %s. Current Stake is %s.");
        String actualError = page.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minBet)),String.format("%(,.2f",Double.parseDouble(maxBet)),String.format("%,.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate that user can NOT place Lay bet if Stake greater than max setting
     * @precondition 1 There is an unblocked soccer event
     *              2 Login member site
     *              3. Get max bet setting of login  account
     * @steps:      1. Active any market page
     *              2. Click on an Lay Odds
     *              3. Input stake greater than max bet
     * @expect:     1  Error Cannot place bet display: "Error : Cannot place bet. The stake must be from [min] to [max]. Current Stake is [stake].""
     *//*

    @TestRails(id="586")
    @Test(groups = {"smoke"})
    public void FE_Place_Bet_Function_TC027(){
        log("@title: Validate that user can NOT place Lay bet if Stake greater than max setting");
        String odds ="1.01";
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.LAY);

        log("Step 1. Active any market page");
        String stake = Integer.toString(Integer.parseInt(maxBet)+1);
        if(minBet.equals("1")) {
            log("DEBUG: Min bet is already = 1, Cannot continue run the test case");
            Assert.assertTrue(true,"Bypass this test case");
            return;
        }

        SportPage page = memberHomePage.navigateSportMenu("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEvent(false,false,30,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Click on an Lay Odds");
        Market market = page.marketContainerControl.getMarket(event,1,false);
        market.getBtnOdd().click();

        log("Step 3. Input stake greater than max bet");
        page.betSlipControl.placeBet(odds,stake);

        log("Verify: 1 Error Cannot place bet display: \"Error : Cannot place bet. The stake must be from [min] to [max]. Current Stake is [stake].");
        String actualError = page.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minBet)),String.format("%(,.2f",Double.parseDouble(maxBet)),String.format("%,.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));
        log("INFO: Executed completely");
    }
*/


}
