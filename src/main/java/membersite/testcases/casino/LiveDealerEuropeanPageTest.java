package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import membersite.pages.casino.CasinoHomePage;
import membersite.pages.casino.LiveDealerEuropeanPage;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.*;

public class LiveDealerEuropeanPageTest extends BaseCaseTest {
    @TestRails(id = "20228")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20228(){
        log("@title: Validate successfully access Live Dealer European on Member site");
        log("@Precondition: Account has been activated Live Dealer European game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer European on header menu");
        LiveDealerEuropeanPage liveDealerEuropeanPage = memberHomePage.openLiveDealerEuro();
        log("@Verify 1: The list of game is displayed such as 'Teen Patti', 'Bet on Teen Patti', 'One Teen Patti Classic'…");
        List<String> productsList = liveDealerEuropeanPage.getListProductsMenu();
        Assert.assertTrue(LIVE_DEALER_EUROPEAN_PRODUCTS_MENU.containsAll(productsList), String.format("FAILED! The list of Live Dealer European game is not correct. Actual: %s, expected: %s", productsList, LIVE_DEALER_EUROPEAN_PRODUCTS_MENU));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20237")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20237(){
        log("@title: Validate can open Live Dealer European game normally");
        log("@Precondition: Account has been activated Live Dealer European in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer European on header menu");
        LiveDealerEuropeanPage liveDealerEuropeanPage = memberHomePage.openLiveDealerEuro();
        log("Step 3: Click on any game");
        liveDealerEuropeanPage.selectCasinoGame();
        log("@Verify 1: The game is opened in new popup successfully without console log error");
        Assert.assertTrue(liveDealerEuropeanPage.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20248")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20248(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        //use account INR
        log("@title: Validate balance in Live Dealer European  game match with user's balance");
        log("@Precondition: Account has been activated Live Dealer European in Agent Site\n" +
                "The currency convert rate in BO(Provider Currency Mapping) between provider and supported currency is 1:1");
        log("@Step 1: Access Live Dealer > Live Dealer European on header menu");
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        LiveDealerEuropeanPage liveDealerEuropeanPage = memberHomePage.openLiveDealerEuro();
        log("@Step 2: Click on any game and observe in game balance");
        liveDealerEuropeanPage.selectCasinoGame();

        log("@Step 3: Observe in game balance");
        double balanceCasino = liveDealerEuropeanPage.getBalance();
        log("@Step 4: Get rate of currency from BO");
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(LIVE_DEALER_EURO)), currency);

        log("@Verify 1: The in game balance should match with user's balance");
        liveDealerEuropeanPage.checkBalance(balance, balanceCasino, rate);
        log("INFO: Executed completely");
    }

    @TestRails(id = "20259")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20259(){
        log("@title: Validate could not access Live Dealer European when disable product");
        log("@Precondition: Account has been disable Live Dealer European in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer European on header menu");
        log("Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertFalse(memberHomePage.isProductDisplayed(LIVE_DEALER_TEXT), "FAILED! Inactive product still displays on header menu");
        log("@Step 3: Access Live Dealer European by external link (e.g.: /home/live-dealer/ezugi)");
        LiveDealerEuropeanPage liveDealerEuropeanPage = (LiveDealerEuropeanPage) memberHomePage.openCasinoGameByUrl(LIVE_DEALER_EURO);
        log("Verify 2: User could not access product and was brought back to home page");
        Assert.assertFalse(liveDealerEuropeanPage.getListProductSize() > 0, "FAILED! Live Dealer European is able to access by URL while it's inactivated");
        log("INFO: Executed completely");
    }
}
