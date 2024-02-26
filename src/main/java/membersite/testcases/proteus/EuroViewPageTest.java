package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Order;
import membersite.pages.proteus.EuroViewDetailsPage;
import membersite.pages.proteus.EuroViewPage;
import membersite.pages.proteus.ProteusHomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MemberConstants.LBL_SOCCER_SPORT;
import static common.MemberConstants.LBL_TENNIS_SPORT;
import static common.ProteusConstant.*;

public class EuroViewPageTest extends BaseCaseTest {

    @TestRails(id = "4067")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4067() {
        log("@title: Validate Decimal odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = Decimal");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(DECIMAL);

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);

        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market, "HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market, "HOME", DECIMAL);

        log("INFO: Executed completely");
    }

    @TestRails(id = "4068")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4068() {
        log("@title: Validate HK odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = HONGKONG");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(HONGKONG);

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, HONGKONG);

        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market, "HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market, "HOME", HONGKONG);

        log("INFO: Executed completely");
    }

    @TestRails(id = "4069")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4069() {
        log("@title: Validate ML odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = MALAY");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        euroViewPage.selectOddsType(MALAY);
        Market market = euroViewPage.getEventInfo(SOCCER, MALAY);

        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market, "HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market, "HOME", MALAY);

        log("INFO: Executed completely");
    }

    @TestRails(id = "4070")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4070() {
        log("@title: Validate AM odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = AMERICAN");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(AMERICAN);

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, AMERICAN);


        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market, "HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market, "HOME", AMERICAN);

        log("INFO: Executed completely");
    }

    @TestRails(id = "4117")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Member_TC4117(String currency) {
        log("@title: Validate can place bet on AM odds Euro view when input in Stake");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select American odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(AMERICAN);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, AMERICAN);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        Order order = euroViewPage.addOddToBetSlipAndPlaceBet(market, "HOME", "minbet", false, true);

        log("Step 6. Click on pending bet and observer the info");
        log("Verify 1 Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure");
        log("Verify 2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyPendingBetInfo(order, currency);
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }

    @TestRails(id = "4119")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Member_TC4119(String currency) {
        log("@title: Validate can place bet on DC odds Euro view when input in Stake");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select Decimal odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(DECIMAL);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        Order order = euroViewPage.addOddToBetSlipAndPlaceBet(market, "HOME", "minbet", false, true);

        log("Step 6. Click on pending bet and observer the info");
        log("Verify 1 Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure");
        log("Verify 2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyPendingBetInfo(order, currency);
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }

    @TestRails(id = "4118")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0", "isa"})
    @Parameters({"currency"})
    public void PS38_Member_TC4118(String currency) {
        log("@title: Validate can place bet on MY odds Euro view when input in Stake = min bet");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select Malay odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(MALAY);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, MALAY);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        Order order = euroViewPage.addOddToBetSlipAndPlaceBet(market, "HOME", "minbet", false, true);

        log("Step 6. Observer the info in Pending bet");
        log("Verify 1Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure\n" +
                "2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyPendingBetInfo(order, currency);
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }

    @TestRails(id = "4061")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0", "isa"})
    @Parameters({"currency"})
    public void PS38_Member_TC4061(String currency) {
        log("@title: Validate user balance and exposure is correct when placing an order on HK odds");
        log("Precondition:  The player is active PS38 product and has balance to place a minimum bet");
        log("Step 1. Login member site and get user balance/exposure before place bet");
        log("Step 2. Active PS38 product");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and HK odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(HONGKONG);

        log("Step 4. Select an early event of any sports");
        log("Step 5. Click on an odds of the selection that has higher potential win");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, MALAY);

        log("Step 6 Input stake = min bet");
        log("Step 7 Click Place bet and confirm to place");
        log("Step 8. Get the risk of the order and check user balance and exposure");
        Order order = euroViewPage.addOddToBetSlipAndPlaceBet(market, "HOME", "minbet", false, true);

        log("Verify 1. Verify user exposure = exposure before place bet - risk of bet\n" +
                "user balance = balance before place bet - user exposure");
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }

    @TestRails(id = "4126")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4126() {
        log("@title: Validate can add Handicap Soccer market odds to bet slip in EU view list event");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu and click on Soccer");
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        log("Step 4. Click on Handicap tab");
        euroViewPage.selectMarketTypeTab(TEXT_HDP);
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);

        log("Step 5. Add an handicap odds of Home team to bet slip");
        euroViewPage.clickOdds(market, "HOME");
        log("Verify Check odds handicap info display in bet slip correctly:\n" +
                "Selection, handicap point (negative/positive sign), odds");
        euroViewPage.verifyBetSlipInfo(market, "HOME", DECIMAL);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4127")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4127() {
        log("@title: Validate can add Over Under Soccer market odds to bet slip in EU view list event");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu and click on Soccer");
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        log("Step 4. Click on Over Under tab");
        euroViewPage.selectMarketTypeTab(TEXT_OVER_UNDER);
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);

        log("Step 5. Add an Over Under odds of Home team to bet slip");
        euroViewPage.clickOdds(market, "HOME");

        log("Verify Check odds handicap info display in bet slip correctly:\n" +
                "Selection, over under point (negative/positive sign), odds");
        euroViewPage.verifyBetSlipInfo(market, "OVER", DECIMAL);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4123")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4123() {
        log("@title: Validate can navigate Soccer in header menu EU view");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu Click on Soccer in Header menu");
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectSportHeaderMenu(LBL_SOCCER_SPORT);

        log("Verify Soccer is active and Soccer Match title displays");
        Assert.assertEquals(euroViewPage.lblSportHeader.getText(), LBL_SOCCER_SPORT, "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4124")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4124() {
        log("@title: Validate can navigate Tennis in header menu EU view");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu Click on Tennis in Header menu");
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectSportHeaderMenu(LBL_TENNIS_SPORT);

        log("Verify Soccer is active and Tennis Match title displays");
        Assert.assertEquals(euroViewPage.lblSportHeader.getText(), LBL_TENNIS_SPORT, "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4125")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4125() {
        log("@title: Validate can navigate Soccer Early in left menu EU view");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Click on Early > Soccer in Left menu");
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);

        log("Verify Soccer is active and Soccer Match title displays");
        Assert.assertEquals(euroViewPage.lblSportHeader.getText(), LBL_SOCCER_SPORT, "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4062")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4062() {
        log("@title: Validate player can switch to Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        proteusHomePage.selectEuroView();

        log("Verify View name change to Asia View");
        Assert.assertEquals(proteusHomePage.lblView.getText(), "Asian View", "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4063")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4063() {
        log("@title: Validate player can select Decimal odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = Decimal");
        euroViewPage.selectOddsType(DECIMAL);

        log("Verify 1: Verify odds type change to DECIMAL");
        Assert.assertEquals(euroViewPage.getOddsType(), DECIMAL.toUpperCase(), "Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4064")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4064() {
        log("@title: Validate player can select HONGKONG odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = HONGKONG");
        euroViewPage.selectOddsType(HONGKONG);

        log("Verify 1: Verify odds type change to HONGKONG");
        Assert.assertEquals(euroViewPage.getOddsType(), HONGKONG.toUpperCase(), "Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4065")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4065() {
        log("@title: Validate player can select MALAY odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = MALAY");
        euroViewPage.selectOddsType(MALAY);

        log("Verify 1: Verify odds type change to MALAY");
        Assert.assertEquals(euroViewPage.getOddsType(), MALAY.toUpperCase(), "Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4066")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4066() {
        log("@title: Validate player can select AMERICAN odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();

        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = AMERICAN");
        euroViewPage.selectOddsType(AMERICAN);

        log("Verify 1: Verify odds type change to AMERICAN");
        Assert.assertEquals(euroViewPage.getOddsType(), AMERICAN.toUpperCase(), "Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4128")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4128() {
        log("@title: Validate odds of 1x2 market display in Decimal when selecting Malay odds in EU view list event");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu and click on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        euroViewPage.selectMarketTypeTab(TEXT_1X2);

        log("Step 4. Get Decimal odds and change odds type to Malay");
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);
        euroViewPage.selectOddsType(MALAY);

        log("Verify Check odds when changing to Malay is same as odds when selected Decimal odds type");
        Market marketUI = euroViewPage.getEventInfoUI(market.getEventId());
        euroViewPage.verifyOddsShowCorrect(market.getOdds(), marketUI.getOdds());
        log("INFO: Executed completely");
    }

    @TestRails(id = "4129")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Member_TC4129() {
        log("@title: Validate odds of 1x2 market display in Decimal when selecting HK odds in EU view list event");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu and click on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        euroViewPage.selectMarketTypeTab(TEXT_1X2);

        log("Step 4. Get Decimal odds and change odds type to Malay");
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);
        euroViewPage.selectOddsType(HONGKONG);

        log("Verify Check odds when changing to Malay is same as odds when selected Hongkong odds type");
        Market marketUI = euroViewPage.getEventInfoUI(market.getEventId());
        euroViewPage.verifyOddsShowCorrect(market.getOdds(), marketUI.getOdds());
        log("INFO: Executed completely");
    }

    @TestRails(id = "4122")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0","isa"})
    public void PS38_Member_TC4122() {
        log("@title: Validate detail page display when clicking on soccer event name ");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Click on Early tab and click on Soccer in left menu");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);

        log("Step 4. Click on any event name");
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);
        EuroViewDetailsPage euroViewDetailsPage = euroViewPage.opentDetail(market);

        log("Verify 1. Check details page display correctly info:League name\n" +
                "Event name\n" +
                "Home and Away name of 1x2 market");
        euroViewDetailsPage.verifyPageContainsMarketInfo(market);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4073")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4073() {
        log("@title: Validate top menu displays correctly items on PS38 product in Euro View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View and observe top menu");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Verify The items on top menu displays correctly (refer to PS38 Sport)");
        euroViewPage.verifyTopMenuShowCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "4037")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4037() {
        log("@title: Validate cannot place bet when place bet in member site smaller than min bet setting");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        String minStake = "1.00";
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        log("Step 3. Placing bet with Stake/Risk smaller than min bet > observe");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);
        euroViewPage.addOddToBetSlipAndPlaceBet(market, "HOME", minStake, false, false);

        log("Validate An error message should display as:\n" +
                "Your stake cannot be lower than the minimum bet. It has been automatically adjusted to the minimum bet amount.");
        String errorMessage = proteusHomePage.lblPlaceBetError.getText();
        Assert.assertEquals(errorMessage, MIN_STAKE_ERROR_MSG, String.format("FAILED! Stake error message does not show correct expected %s actual %s", errorMessage, MIN_STAKE_ERROR_MSG));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4038")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4038() {
        log("@title: Validate cannot place bet when place bet in member site greater than max bet setting");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        String maxStake = "100000000";
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 3. Placing bet with Stake/Risk greater than max bet > observe");
        Market market = euroViewPage.getEventInfo(SOCCER, DECIMAL);
        euroViewPage.addOddToBetSlipAndPlaceBet(market, "HOME", maxStake, false, false);

        log("Validate An error message should display as:\n" +
                "Your stake cannot be greater than the maximum bet. It has been automatically adjusted to the maximum bet amount.");
        String errorMessage = proteusHomePage.lblPlaceBetError.getText();
        Assert.assertEquals(errorMessage, MAX_STAKE_ERROR_MSG, String.format("FAILED! Stake error message does not show correct expected %s actual %s", errorMessage, MAX_STAKE_ERROR_MSG));
        log("INFO: Executed completely");
    }
}
