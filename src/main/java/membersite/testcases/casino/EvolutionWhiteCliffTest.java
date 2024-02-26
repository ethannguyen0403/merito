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

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;


public class EvolutionWhiteCliffTest extends BaseCaseTest {

    @TestRails(id = "20225")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20225(){
        log("@title: Validate successfully access Evolution (WhiteCliff) on Member site");
        log("@Precondition: Account has been activated Evolution (WhiteCliff) game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Evolution (WhiteCliff) on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.EVOLUTION_WHITE_CLIFF);
        log("@Verify 1: Title 'Evolution' is displayed correctly on iframe");
        Assert.assertTrue(casinoPage.evolutionWhiteCliff.iconLogo.isDisplayed(),"FAILED! Title Evolution casino is not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20244")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20244(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Evolution (WhiteCliff) game match with user's balance");
        log("@Precondition: Account has been activated Evolution (WhiteCliff) game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Pragmatic on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.EVOLUTION_WHITE_CLIFF);
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));

        log("@Step 3: Click on first game");
        casinoPage.evolutionWhiteCliff.openGameByIndex("1");
        double balanceCasino = casinoPage.evolutionWhiteCliff.getBalanceInGame();
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(CasinoProduct.PRAGMATIC.toString())), currency);
        log("@Step 4: Get rate of currency from BO with rate: " + rate);
        log("@Verify 1: The in game balance should match with user's balance");
        Assert.assertEquals(balanceCasino * rate, balance, "FAILED! Balance of Pragmatic not equals to balance user");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20255")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"userDeactivateCasino", "password"})
    public void Casino_Test_TC20255(String userDeactivateCasino, String password) throws Exception{
        log("@title: Validate could not access Evolution (WhiteCliff) when disable product");
        log("@Precondition: Account has been activated  Evolution (WhiteCliff) game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        memberHomePage.logout();
        HomePage homePage =  memberHomePage.login(userDeactivateCasino, StringUtils.decrypt(password), true);
        log("@Step 2: Access Evolution (WhiteCliff) on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertTrue(!homePage.header.isProductTabDisplay(CasinoProduct.EVOLUTION_WHITE_CLIFF.toString()), "FAILED! Access Evolution (WhiteCliff) display on homepage menu.");
        log("@Step 3: Access Pragmatic by external link");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGameByLink(CasinoProduct.EVOLUTION_WHITE_CLIFF, CasinoHomePage.getURLCasino(CasinoProduct.EVOLUTION_WHITE_CLIFF));
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertTrue(!casinoPage.evolutionWhiteCliff.iconLogo.isDisplayed(),"FAILED! Evolution (WhiteCliff) game is displayed");
        log("INFO: Executed completely");
    }
}
