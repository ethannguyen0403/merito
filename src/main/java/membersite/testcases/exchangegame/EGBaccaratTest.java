package membersite.testcases.exchangegame;

import baseTest.BaseCaseTest;
import common.EGConstants;
import membersite.objects.AccountBalance;
import membersite.pages.exchangegames.BaccaratPage;
import membersite.pages.exchangegames.EGHomePage;
import membersite.utils.betplacement.BetUtils;
import membersite.utils.exchangegame.GetDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class EGBaccaratTest extends BaseCaseTest {
    /**
     * @title: Validate can place BACCARAT standard
     * @precondition: 1. Login member site
     * @step: 1.Navigate to exchange game > Baccarat Standar
     * 2. Place on any selection with Lay 1.02 and min stake
     * @expect: Verify can place bet
     */
    @TestRails(id = "533")
    @Test(groups = {"smoke", "smoke_dev"})
    public void EG_Baccarat_TC533() {
        log("@title: Validate can place BACCARAT standard");
        log("Step precondition. get min bet of Baccarat");
        String minBet = GetDataUtils.getMinBet("BACCARAT", "BACK");

        log("Step 1. Navigate to exchange game  ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();
        BaccaratPage baccaratPage = (BaccaratPage) egHomePage.navigateGameFromMainMenu(EGConstants.BACCARAT);

        log("Step 2: Place on Player  with Lay 1.02 and min stake");
        baccaratPage.placeBet("Player", false, "1.02", minBet);

        log("Verify Verify can place bet");
        Assert.assertEquals(baccaratPage.isUnmatchedBetDisplayed(),"Player", "Failed! Cannot place bet as unmatched section not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can Not place BACCARAT if exceed available balance
     * @precondition: 1. Login member site
     * @step: 1.Navigate to exchange game > Baccarat Turbo
     * 2. Place on any selection with Lay 1.02 and min stake
     * @expect: Verify error message display
     */
    @TestRails(id = "534")
    @Test(groups = {"smoke", "smoke_dev"})
    public void EG_Baccarat_TC534() {
        log("@title: Validate can Not place BACCARAT if exceed available balance");
        AccountBalance balanceAPI = BetUtils.getUserBalance();
        String minBet = GetDataUtils.getMinBet(EGConstants.BACCARAT, "BACK");
        String stake = String.format("%.0f", Double.max(Double.valueOf(balanceAPI.getBalance().replaceAll(",", "")) + 1, Integer.valueOf(minBet)));
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Step 1. Navigate to exchange game > Baccarat");
        BaccaratPage baccaratPage = (BaccaratPage) egHomePage.navigateGameFromMainMenu(EGConstants.BACCARAT);

        log("Step 2. Place a Back bet that exceed available balance");
        baccaratPage.placeBet("Player", true, "", stake);

        log("Verify 1: Verify error message display");
        Assert.assertEquals(baccaratPage.betSlipControl.lblErrorMessage.getText(), EGConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE, "FAILED! Error message is incorrect");

        log("INFO: Executed completely");
    }


}
