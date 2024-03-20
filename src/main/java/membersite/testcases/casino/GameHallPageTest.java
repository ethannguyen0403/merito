package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import membersite.pages.casino.CasinoHomePage;
import membersite.pages.casino.GameHallPage;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.*;

public class GameHallPageTest extends BaseCaseTest {
    @TestRails(id = "20229")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20229(){
        log("@title: Validate successfully access Game Hall on Member site");
        log("@Precondition: Account has been activated Game Hall in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Game Hall on header menu");
        CasinoHomePage gameHallPage = memberHomePage.openGameHall();
        log("@Verify 1: The W logo is displayed on iframe");
        Assert.assertTrue(gameHallPage.verifyCasinoDisplay(), "FAILED! Logo W is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20238")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20238(){
        log("@title: Validate can open Game Hall game normally");
        log("@Precondition: Account has been activated Game Hall in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Game Hall on header menu");
        CasinoHomePage gameHallPage = memberHomePage.openGameHall();
        log("@Step 3: Click on any game");
        gameHallPage.selectCasinoGame();
        log("@Verify 1: The game is opened in new popup successfully without console log error");
        Assert.assertTrue(gameHallPage.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20249")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20249(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Game Hall  game match with user's balance");
        log("@Precondition: Account has been activated Game Hall in Agent Site\n" +
                "The currency convert rate in BO(Provider Currency Mapping) between provider and supported currency is 1:1");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Game Hall on header menu");
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        CasinoHomePage gameHallPage = memberHomePage.openGameHall();
        double balanceCasino = gameHallPage.getBalance();
        log("@Step 3: Observe in game balance");
        log("@Step 4: Get rate of currency from BO");
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(GAME_HALL)), currency);
        log("@Verify 1: The in game balance should match with user's balance");
        gameHallPage.checkBalance(balance, balanceCasino, rate);
        log("INFO: Executed completely");
    }

    @TestRails(id = "20260")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20260(){
        log("@title: Validate could not access Game Hall when disable product");
        log("@Precondition: Account has been disable Game Hall in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Game Hall on header menu");
        log("Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertFalse(memberHomePage.isProductDisplayed(GAME_HALL), "FAILED! Inactive product still displays on header menu");
        log("@Step 3: Access Game Hall by external link (e.g.: /home/custom?code=GAME_HALL)");
        CasinoHomePage gameHallPage = memberHomePage.openCasinoGameByUrl(GAME_HALL);
        log("Verify 2: User could not access product and was brought back to home page");
        Assert.assertFalse(gameHallPage.getListProductSize() > 0, "FAILED! Game Hall is able to access by URL while it's inactivated");
        log("INFO: Executed completely");
    }
}
