package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.HomePage;
import membersite.pages.casino.CasinoHomePage;
import membersite.pages.casino.CasinoProduct;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.MemberConstants.Casino.*;

public class EvolutionTest extends BaseCaseTest {
    @TestRails(id = "20224")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20224() {
        log("@title: Validate successfully access Evolution on Member site");
        log("@Precondition: Account has been activated Evolution game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Evolution on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.EVOLUTION);
        log("@Verify 1:The list of game is displayed such as  'Evolution Black Jack', 'Evolution Roulette', 'Evolution Poker', 'Evolution Game Shows', 'Evolution  Baccarat & Sic Bo'…");
        List<String> productsList = casinoPage.evolution.getListProductsMenu();
        Assert.assertTrue(EVOLUTION_PRODUCTS_MENU.containsAll(productsList),
                String.format("FAILED! Products of Evolution is not correct. Actual: %s, expected: %s", productsList,
                        EVOLUTION_PRODUCTS_MENU));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20234")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20234() {
        log("@title: Validate can open Evolution game normally");
        log("@Precondition: Account has been activated Evolution game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Evolution on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.EVOLUTION);
        log("@Step 3: Click on any product");
        List<String> productsList = casinoPage.evolution.getListProductsMenu();
        casinoPage.evolution.selectProduct(productsList.get(0));
        log("@Verify 1: Able to open game without console error");
        Assert.assertTrue(casinoPage.verifyConsoleLogNotContainValue(ERROR_CODE_LIST), "FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20245")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20245(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Evolution game match with user's balance");
        log("@Precondition: Account has been activated Evolution game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Evolution on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.EVOLUTION);
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));

        log("@Step 3: Click on first product");
        casinoPage.evolution.selectProduct(casinoPage.evolution.getListProductsMenu().get(0));

        double balanceCasino = casinoPage.evolution.getBalance();
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(CasinoProduct.EVOLUTION.toString())), currency);
        log("@Step 4: Get rate of currency from BO with rate: " + rate);
        log("@Verify 1: The in game balance should match with user's balance");
        Assert.assertEquals(balanceCasino * rate, balance, "FAILED! Balance of Pragmatic not equals to balance user");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20256")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"userDeactivateCasino", "password"})
    public void Casino_Test_TC20256(String userDeactivateCasino, String password) throws Exception {
        log("@title: Validate could not access Evolution when disable product");
        log("@Precondition: Account has been activated Evolution game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        memberHomePage.logout();
        HomePage homePage = memberHomePage.login(userDeactivateCasino, StringUtils.decrypt(password), true);
        log("@Step 2: Access Evolution on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertTrue(!homePage.header.isProductTabDisplay("Live Dealer"), "FAILED! Live Dealer display on homepage menu.");
        log("@Step 2: Access Evolution by external link");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGameByLink(CasinoProduct.EVOLUTION, CasinoHomePage.getURLCasino(CasinoProduct.EVOLUTION));
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertTrue(!casinoPage.evolution.tabEvolution.isDisplayed(), "FAILED! Evolution product is displayed");
        log("INFO: Executed completely");
    }
}
