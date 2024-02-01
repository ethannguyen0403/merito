package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.pages.proteus.EuroViewPage;
import membersite.pages.proteus.ProteusHomePage;
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

    @TestRails(id = "4119")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4119() {
        log("@title: Validate can place bet on DC odds Euro view when input in Stake");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Decimal odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(AMERICAN);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,AMERICAN);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        euroViewPage.clickOdds(market,"HOME");
        euroViewPage.placeNoBet(market,"15",false,true);

        log("Step 6. Click on pending bet and observer the info");
        // check order

        log("Verify 1Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure\n" +
                "2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyBetSlipInfo(market,"HOME",AMERICAN);

        log("INFO: Executed completely");
    }
    @TestRails(id = "4118")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4118() {
        log("@title: Validate can place bet on DC odds Euro view when input in Stake");
        log("Precondition: 11/Login member site-  the player active PS38 product");
        log("Step 1. Select Ps38 product");
        log("Step 2. Select Euro View");
        AccountBalance userBalance = memberHomePage.getUserBalance();
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Decimal odds type");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(AMERICAN);

        log("Step 4. Search Early tab in the left menu and select on Soccer");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = euroViewPage.getEventInfo(SOCCER,AMERICAN);

        log("Step 5. Click on any Event 1x2 odds and input risk = min bet then place bet");
        euroViewPage.clickOdds(market,"HOME");
        euroViewPage.placeNoBet(market,"15",false,true);

        log("Step 6. Click on pending bet and observer the info");
        // check order

        log("Verify 1Check user balance and exposure is correct:\n" +
                "Exposure = Before place bet exposure - risk of bet\n" +
                "Balance = Balance before place bet - exposure\n" +
                "2 Check Pending bet display correctly info:\n" +
                "League, event name, market type, market name, selection name, odds, odds type, risk, stake, potential win");
        euroViewPage.verifyBetSlipInfo(market,"HOME",AMERICAN);

        log("INFO: Executed completely");
    }




}
