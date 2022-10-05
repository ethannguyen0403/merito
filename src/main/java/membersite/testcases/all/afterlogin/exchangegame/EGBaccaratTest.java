package membersite.testcases.all.afterlogin.exchangegame;

import membersite.common.EGConstants;
import membersite.common.FEMemberConstants;
import membersite.objects.AccountBalance;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.SportPage;
import membersite.pages.all.tabexchangegame.*;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;
import membersite.utils.exchangegame.GetDataUtils;

public class EGBaccaratTest extends BaseCaseMerito {
        /**
     * @title: Verify can place Baccarat Turbo game
     * @precondition: 1. Login member site
     * @step: 1.Navigate to exchange game > Baccarat Turbo
     * 2. Place on any selection with Lay 1.02 and min stake
     * @expect: Verify can place bet
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Baccarat_TC001(String skinName){
        log("@title:  Verify can place Baccarat Turbo game");
        log("Step precondition. get min bet of Baccarat");
        String minBet = GetDataUtils.getMinBet(EGConstants.BACCARAT, SportPage.BetType.BACK);
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Step 1. Navigate to exchange game  ");
        BaccaratPage baccaratPage = egHomePage.navigateGameFromMainMenu(EGConstants.BACCARAT,BaccaratPage.class);

        log("Step 2: Place on Player  with Lay 1.02 and min stake");
        baccaratPage.placebet("Player",false,"1.02",minBet);

        log("Verify Verify can place bet");
        Assert.assertTrue(baccaratPage.getUmatchedBetId().startsWith("Bet Id:"),"Failed! Cannot place bet as unmatched section not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can place Baccarat Turbo game
     * @precondition: 1. Login member site
     * @step: 1.Navigate to exchange game > Baccarat Turbo
     * 2. Place on any selection with Lay 1.02 and min stake
     * @expect: Verify can place bet
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Baccarat_TC002(String skinName){
        log("@title: Verify can place Baccarat Turbo game");
        AccountBalance balanceAPI = BetUtils.getUserBalance();
        String stake =Double.toString( Double.valueOf(balanceAPI.getBalance().replaceAll(",", "").toString())+ 1);
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Step 1. Navigate to exchange game");
        BaccaratPage baccaratPage = egHomePage.navigateGameFromMainMenu(EGConstants.BACCARAT,BaccaratPage.class);

        log("Step 2. Place a Back bet that exceed available balance");
        baccaratPage.placebet("Player",true,"",stake);

        log("1.1 Verify error message display");
        Assert.assertEquals(baccaratPage.betSlipControl.lblErrorMessage.getText(),EGConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE,"FAILED! Error message is incorrect");

        log("INFO: Executed completely");
    }


}
