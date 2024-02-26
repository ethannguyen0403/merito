package backoffice.testcases.settlement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.settlement.WagerResettlementPage;
import backoffice.utils.settlement.WagerResettlementUltils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class WagerResettlementTest extends BaseCaseTest {

    /**
     * @title: Validate UI in Resettlement display correctly
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Settlement > Wager Resettlement
     * @expect: 1. Verify UI is correctly
     * - Bet Type: Normal, Follow to external sites, Hedging, Small Bets
     * - Wager ID textbox, Product dropdown, Search button
     * - Table header: Wager ID, Selection Name, External Account, External ID, Stake (Original | Matched), Black/Lay, Odds, Currency, Created Date, Handicap, Status(FairExchange|Betfair), Result (FairExchange|Betfair), Action
     */
    @TestRails(id = "594")
    @Test(groups = {"smoke"})
    public void BO_Settlement_Wager_Resettlement_594() {
        log("@title: Validate UI in Resettlement display correctly");
        log("Step 1. Access Settlement > Wager Resettlement");
        WagerResettlementPage page = backofficeHomePage.navigateWagerResettlement();

        log("Verify 1. Verify UI is correctly");
        Assert.assertEquals(page.lblPageTitle.getText(), BOConstants.Settlement.WagerResettlement.TITLE, "FAILED! Page title is incorrect");
        Assert.assertTrue(page.rbByMarket.isDisplayed(), "FAILED! By Market Radio button does not display");
        Assert.assertTrue(page.rbByWager.isDisplayed(), "FAILED! By Wager Radio button does not display");
        Assert.assertTrue(page.ddbBetType.isDisplayed(), "FAILED! Bet Type Dropdown does not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAILED!Product Dropdown does not display");
        Assert.assertTrue(page.txtWagerId.isDisplayed(), "FAILED!Wager ID textbox does not display");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button does not display");

        List<String> lstHeader = page.tblWagerResettlement.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, BOConstants.Settlement.WagerResettlement.TABLE_HEADER, "FAILED! Table header not display as expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search wager resettlement
     * @pre-condition: 1. Login BO
     * 2. Have a wager is resettlement in the system
     * @steps: 1. Access Settlement > Wager Resettlement
     * 2. Select Bet Type: Normal
     * 3. Input wager id in precondtion and product = exchange
     * Then click Search
     * @expect: 1. Data is display, Resettle button display in Action column
     */
    @TestRails(id = "595")
    @Test(groups = {"smoke"})
    @Parameters({"wagerID", "env"})
    public void BO_Settlement_Wager_Resettlement_595(String wagerID, String env) {
        log("@title: Validate can search wager resettlement");
        log("Step 1. Access Settlement > Wager Resettlement");
        wagerID = env.equalsIgnoreCase("green")? "170360015": wagerID;

        WagerResettlementPage page = backofficeHomePage.navigateWagerResettlement();
        List<String> lstInfo = WagerResettlementUltils.getMarketInfo(wagerID, WagerResettlementPage.BetType.NORMAL, "EXCHANGE");
        log("Step 2. Select Bet Type: Normal");
        log("Step 3. Input wager id in precondtion and product = exchange");
        page.searchByWager("Normal", wagerID, "Exchange");

        log("Verify 1. Data is display, Resettle button display in Action column");
        String infoLbl = page.lblInfo.getText();
        String expectedInfo = String.format("Sport: %s | Competition: %s\n" +
                "Event: %s | Event Date: %s\n" +
                "Market: %s | Market start time: %s", lstInfo.get(0), lstInfo.get(1), lstInfo.get(2), lstInfo.get(3), lstInfo.get(4), lstInfo.get(5));
        Assert.assertEquals(infoLbl, expectedInfo, "FAILED! Info of the wager is incorrect");
        Assert.assertTrue(page.isResettleButtonDisplay(wagerID), "FAILED! Resettle button not display if FairExchange result and Betfair result is different");

        log("INFO: Executed completely");
    }
}
