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

    @TestRails(id = "23659")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23659(){
        log("@title: Validate cannot add more than 10 bet into bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        log("Step 4: Click on odds of 11 different markets to bet slip");
        asianViewPage.add11BetsToBetSlip();
        log("Verify 1: Verify the popup message \"Maximum is 10 selections\" display");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent(), MAX_SELECTIONS_MSG, "FAILED! Pop up maximum selection is not displayed correctly");
        log("Step 5: Click on OK button");
        asianViewPage.confirmModulePopup.confirm();
        log("Verify 2: Verify the popup message is no longer displayed");
        Assert.assertFalse(asianViewPage.confirmModulePopup.isDisplayed(),"FAILED! Pop up still displayed");
    }

    @TestRails(id = "23660")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23660(){
        log("@title: Validate Remove All popup display when clicking Remove button in bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);

        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true);
        log("Step 4: Click on Remove All button");
        asianViewPage.btnRemoveAll.click();
        log("Verify 1: Verify Remove all popup display with the message \"Do you want to empty you Bet Slip?\"\n" +
                "\n");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent(), REMOVE_ALL_MSG, "FAILED! Pop up Remove all is not displayed correctly");
    }

    @TestRails(id = "23661")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23661(){
        log("@title: Validate nothing happen when cancelling to empty bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);

        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true);
        log("Step 4: Click on Remove All button");
        asianViewPage.btnRemoveAll.click();
        log("Step 5: Cancel remove all popup");
        asianViewPage.confirmModulePopup.cancelPopup();
        log("Verify 1:  Verify the popup is no longer display, Bet slip still display the odds in bet slip");
        Assert.assertFalse(asianViewPage.confirmModulePopup.isDisplayed(),"FAILED! Pop up still displayed");
        asianViewPage.verifyBetSlipInfo(market, market.getOdds().get(0).getTeam(), DECIMAL);
    }


    @TestRails(id = "23662")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23662(){
        log("@title: Validate bet slip is empty when confirm to remove all");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);

        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true);
        log("Step 4: Click on Remove All button");
        asianViewPage.btnRemoveAll.click();
        log("Step 5: Confirm remove all popup");
        asianViewPage.confirmModulePopup.confirm();
        log("Verify 1:   Verify the popup is no longer display, Bet Slip are empty:\n" +
                "\n" +
                "Bet Slip title does not display number\n" +
                "Display the message \"No bets selected yet.\"\n" +
                "Display the message \"Click on the respective odds to place a new bet.\"");
        asianViewPage.verifyBetSlipIsEmpty();
    }

    @TestRails(id = "23663")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23663(){
        log("@title: Validate can remove bet out bet slip when click on the according remove icon");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add an odds to bet slip");
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true);
        log("Step 5: Click on x icon of the added bet");
        asianViewPage.removeAddedBet(1);
        log("Verify 1:   Verify the popup is no longer display, Bet Slip are empty:\n" +
                "\n" +
                "Bet Slip title does not display number\n" +
                "Display the message \"No bets selected yet.\"\n" +
                "Display the message \"Click on the respective odds to place a new bet.\"");
        asianViewPage.verifyBetSlipIsEmpty();
    }

    @TestRails(id = "23664")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23664(){
        log("@title: Validate bet slip and place bet number are update accordingly when removing a bet out bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Click on odds of 2 different markets to bet slip");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        Market marketOU = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_OVER_UNDER, true, false);
        asianViewPage.clickOdds(marketHDP, true);
        asianViewPage.clickOdds(marketOU, true);
        log("Step 4: Click on x icon of the 2nd bet");
        asianViewPage.removeAddedBet(2);
        log("Verify 1:  Verify Bet slip title: Bet Slip 1");
        Assert.assertEquals(asianViewPage.lblBetSlipTab.getText() + asianViewPage.lblBetSlipTabNumber.getText(), BET_SLIP_TAB +1, "FAILED! Bet slip tab not correct");
        log("Verify 2:  Verify Place bet button: PLACE 1 BET");
        log("Verify 3:  Bet Slip just display info of the 1st order");
        asianViewPage.verifyBetSlipInfo(marketHDP, marketHDP.getOdds().get(0).getTeam(), DECIMAL);
    }

}
