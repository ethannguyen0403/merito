package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Order;
import membersite.pages.proteus.EuroViewPage;
import membersite.pages.proteus.ProteusHomePage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.ProteusConstant.*;
public class EuroViewPageTest extends BaseCaseTest {

    @TestRails(id = "4067")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4067() {
        log("@title: Validate Decimal odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = Decimal");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(DECIMAL);
        
        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,DECIMAL);

        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market,"HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market,"HOME",DECIMAL);

        log("INFO: Executed completely");
    }
    @TestRails(id = "4068")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4068() {
        log("@title: Validate HK odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = HONGKONG");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(HONGKONG);

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);        
        Market market = euroViewPage.getEventInfo(SOCCER,HONGKONG);

        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market,"HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market,"HOME",HONGKONG);

        log("INFO: Executed completely");
    }

    @TestRails(id = "4069")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4069() {
        log("@title: Validate ML odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = MALAY");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,MALAY);
        euroViewPage.selectOddsType(MALAY);

        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market,"HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market,"HOME",MALAY);

        log("INFO: Executed completely");
    }

    @TestRails(id = "4070")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4070() {
        log("@title: Validate AM odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = AMERICAN");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(AMERICAN);

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,AMERICAN);


        log("Step 5. Click on event of 1x2 market has odds");
        euroViewPage.clickOdds(market,"HOME");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
        euroViewPage.verifyBetSlipInfo(market,"HOME",AMERICAN);

        log("INFO: Executed completely");
    }

    @TestRails(id = "4117")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Member_TC4117(String currency) {
        log("@title: Validate can place bet on AM odds Euro view when input in Stake");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select American odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(AMERICAN);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,AMERICAN);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        Order order =euroViewPage.addOddToBetSlipAndPlaceBet(market,"HOME","minbet",false,true);

        log("Step 6. Click on pending bet and observer the info");
        log("Verify 1 Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure");
        log("Verify 2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyPendingBetInfo(order,currency);
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }

    @TestRails(id = "4119")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Member_TC4119(String currency) {
        log("@title: Validate can place bet on DC odds Euro view when input in Stake");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Decimal odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(DECIMAL);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,DECIMAL);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        Order order =euroViewPage.addOddToBetSlipAndPlaceBet(market,"HOME","minbet",false,true);

        log("Step 6. Click on pending bet and observer the info");
        log("Verify 1 Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure");
        log("Verify 2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyPendingBetInfo(order,currency);
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }
    @TestRails(id = "4118")
    @Test(groups = {"ps38","Proteus.2024.V.1.0","isa"})
    @Parameters({"currency"})
    public void PS38_Member_TC4118(String currency) {
        log("@title: Validate can place bet on MY odds Euro view when input in Stake = min bet");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Malay odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(MALAY);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,MALAY);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        Order order =euroViewPage.addOddToBetSlipAndPlaceBet(market,"HOME","minbet",false,true);

        log("Step 6. Observer the info in Pending bet");
        log("Verify 1Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure\n" +
                "2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyPendingBetInfo(order,currency);
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }

    @TestRails(id = "4061")
    @Test(groups = {"ps38","Proteus.2024.V.1.0","isa"})
    @Parameters({"currency"})
    public void PS38_Member_TC4061(String currency) {
        log("@title: Validate user balance and exposure is correct when placing an order on HK odds");
        log("Precondition:  The player is active PS38 product and has balance to place a minimum bet");
        log("Step 1. Login member site and get user balance/exposure before place bet");
        log("Step 2. Active PS38 product");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and HK odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(HONGKONG);

        log("Step 4. Select an early event of any sports");
        log("Step 5. Click on an odds of the selection that has higher potential win");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,MALAY);

        log("Step 6 Input stake = min bet");
        log("Step 7 Click Place bet and confirm to place");
        log("Step 8. Get the risk of the order and check user balance and exposure");
        Order order =euroViewPage.addOddToBetSlipAndPlaceBet(market,"HOME","minbet",false,true);

        log("Verify 1. Verify user exposure = exposure before place bet - risk of bet\n" +
                "user balance = balance before place bet - user exposure" );
        euroViewPage.verifyUserBalanceAfterPlacePS38(euroViewPage.calculateExpecteBalance(userBalance, order));

        log("INFO: Executed completely");
    }


}
