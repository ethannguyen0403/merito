package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.objects.proteus.Market;
import membersite.pages.ProfitAndLossPage;
import membersite.pages.proteus.AsianViewPage;
import membersite.pages.proteus.ProteusHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.ProteusConstant.*;

public class ProteusHomePageTest extends BaseCaseTest {

//    @TestRails(id = "4072")
//    @Test(groups = {"ps38_inactive_product","Proteus.2024.V.1.0_product_inactive"})
//    public void PS38_Member_TC4072() {
//        log("@title: Validate PS38 product doesn't display on member site");
//        log("Precondition: Login member site");
//        log("Step 1. Observe");
//        log("Validate PS38 product doesn't displays on top menu in member site");
//        try {
//            memberHomePage.activePS38Product();
//            Assert.assertTrue(false, "FAILED! PS38 product still display in member site while inactivated");
//        } catch (NullPointerException npe) {
//            Assert.assertTrue(true);
//        }
//        log("INFO: Executed completely");
//    }

    @TestRails(id = "4174")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    public void PS38_Member_TC4174() {
        log("@title: Validate to commission is correctly in Profit and loss report when bet is full settled for Verify high commission");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1. Active Ps38 product active Asian View");
        log("Step 2. Select any Fottboal today event");
        log("Step 3. Select AM odds odds type, pick a negative odds and place bet. @-103 with stake = 20 => toRisk = 20.6 and toWin=20");
        log("Step 4. Wait the order is settled");
        //Step 1 > Step 4 already has prepared fixed data as precondition (STG/PROD Football 13/01/2024, Soccer 23/01/2024)
        String startDate = "2024-01-13";
        String endDate = "2024-01-14";
        double commissionConfigValue = 0.025;
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);
        page.filter(startDate, endDate);
        page.openBetDetailsOfSportsBook("PS38 - Football", "NFL / Houston Texans vs Cleveland Browns");

        log("Verify Commission of the order = min (toWin, toRisk)=min(20.6,20)*2.5% = 20*2.5% = 0.5");
        page.verifyCommissionProteusSportsBook(commissionConfigValue);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4173")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    public void PS38_Member_TC4173() {
        log("@title: Validate to commission is correctly in Profit and loss report when bet is full settled for Soccer commission");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1. Active Ps38 product active Asian View");
        log("Step 2. Select any Soccer today event");
        log("Step 3. Select AM odds odds type, pick a negative odds and place bet. @-103 with stake = 20 => toRisk = 20.6 and toWin=20");
        log("Step 4. Wait the order is settled");
        //Step 1 > Step 4 already has prepared fixed data as precondition (STG/PROD Football 13/01/2024, Soccer 23/01/2024)
        String startDate = "2024-01-23";
        String endDate = "2024-01-24";
        double commissionConfigValue = 0.02;
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);
        page.filter(startDate, endDate);
        page.openBetDetailsOfSportsBook("PS38 - Soccer", "England - EFL Cup / Chelsea vs Middlesbrough");

        log("Verify Commission of the order = min (toWin, toRisk)=min(20.6,20)*2.5% = 20*2.5% = 0.5");
        page.verifyCommissionProteusSportsBook(commissionConfigValue);
        log("INFO: Executed completely");
    }

    @TestRails(id = "23655")
    @Test(groups = {"ps38_groupa","nolan_Proteus.2024.V.3.0"})
    public void PS38_Member_TC23655() {
        log("@title: Validate the number order display on Bet Slip is correct when adding odds on different market");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select any view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 3:  Click Odds of Home Team on 1x2 full match of market A");
        log("Step 4:  Click Odds of Home Team on 1x2 full match of market B");
        asianViewPage.addMultiBetsToBetSlip(2);
        log("Verify 1:  Verify the number on bet slip  is correctly \"BET SLIP 2\"");
        Assert.assertEquals(String.format("%s %s", asianViewPage.lblBetSlipTab.getText(), asianViewPage.lblBetSlipTabNumber.getText()),
                String.format("%s %d", BET_SLIP_TAB, 2), "FAILED! Label of Bet slip tab is not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "23656")
    @Test(groups = {"ps38_groupa","nolan_Proteus.2024.V.3.0"})
    public void PS38_Member_TC23656() {
        log("@title: Validate Bet Slip just display 1 bet when adding 2 odds on same market");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select any view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 3: In any 1x2 Full match of a market, Click on odds of Home Team and then click on odds of Away team");

        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(marketHDP, "HOME", false);
        asianViewPage.clickOdds(marketHDP, "AWAY", false);
        log("Verify 1: Verify the number on bet slip label is correct: BET SLIP 1");
        Assert.assertEquals(String.format("%s %s", asianViewPage.lblBetSlipTab.getText(), asianViewPage.lblBetSlipTabNumber.getText()),
                String.format("%s %d", BET_SLIP_TAB, 1), "FAILED! Label of Bet slip tab is not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "23657")
    @Test(groups = {"ps38_groupa","nolan_Proteus.2024.V.3.0"})
    public void PS38_Member_TC23657() {
        log("@title: Validate the number order display on Place bet button is correct when adding odds on different market");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select any view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 3:  Click Odds of Home Team on 1x2 full match of market A");
        log("Step 4:  Click Odds of Home Team on 1x2 full match of market B");
        asianViewPage.addMultiBetsToBetSlip(2);
        log("Verify 1: Verify the number on Place Bet button  is correctly \"PLACE 2 BETS\"");
        Assert.assertEquals(asianViewPage.btnPlaceBet.getText(), String.format(PLACE_BETS_BUTTON_TEXT, 2), "FAILED! Button place bet text is not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "23658")
    @Test(groups = {"ps38_groupa","nolan_Proteus.2024.V.3.0"})
    public void PS38_Member_TC23658() {
        log("@title: Validate the number order display on Place bet button is correct when adding odds on same market");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select any view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 3: In any 1x2 Full match of a market, Click on odds of Home Team and then click on odds of Away team");

        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(marketHDP, "HOME", false);
        asianViewPage.clickOdds(marketHDP, "AWAY", false);
        log("Verify 1: Verify the number on Place Bet button  is correctly \"PLACE 1 BETS\"");
        Assert.assertEquals(asianViewPage.btnPlaceBet.getText(), String.format(PLACE_BET_BUTTON_TEXT, 1), "FAILED! Button place bet text is not correct");
        log("INFO: Executed completely");
    }

}
