package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import membersite.pages.casino.Vivo;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.*;

public class VivoTest extends BaseCaseTest {
    @TestRails(id = "20230")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20230(){
        log("@title: Validate successfully access Vivo on Member site");
        log("@Precondition: Account has been activated Vivo in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Vivo on header menu");
        Vivo vivo = memberHomePage.openVivo();
        log("@Verify 1: Header menu with list: 'All', 'Roulette', 'Blackjack', 'Limitless Blackjack', 'Baccarat', 'Casino Hold'em', 'Teen patti', 'Andar Bahar' is displayed");
        List<String> productsList = vivo.getProductsList();
        Assert.assertTrue(VIVO_PRODUCTS_MENU.containsAll(productsList), String.format("FAILED! The list of Vivo game is not correct. Actual: %s, expected: %s", productsList, VIVO_PRODUCTS_MENU));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20239")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20239(){
        log("@title: Validate can open Vivo game normally");
        log("@Precondition: Account has been activated Vivo in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Vivo on header menu");
        Vivo vivo = memberHomePage.openVivo();
        log("@Step 3: Click on any game");
        vivo.openRandomGame();
        log("@Verify 1: Able to open game without console error");
        Assert.assertTrue(vivo.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20250")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20250(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Vivo game match with user's balance");
        log("@Precondition: Account has been activated Vivo in Agent Site\n" +
                "The currency convert rate in BO(Provider Currency Mapping) between provider and supported currency is 1:1");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Vivo on header menu");
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        Vivo vivo = memberHomePage.openVivo();
        double balanceCasino = vivo.getCasinoBalance();
        log("@Step 3: Observe in game balance");
        log("@Step 4: Get rate of currency from BO");
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(VIVO)), currency);

        log("@Verify 1: The in game balance should match with user's balance");
        Assert.assertEquals(balanceCasino * rate, balance, "FAILED! Balance of Live Dealer Euro not equals to balance user");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20261")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20261(){
        log("@title: Validate could not access Vivo when disable product");
        log("@Precondition: Account has been disabled Vivo in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Vivo on header menu");
        log("Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertFalse(memberHomePage.isCasinoProductDisplayed(MAPPING_CASINO_PRODUCT_UI.get("VIVO")), "FAILED! Inactive product still displays on header menu");
        log("@Step 3: Access Vivo by external link (e.g.: /home/custom?code=VIVO)");
        Vivo vivo = (Vivo) memberHomePage.openCasinoGameByUrl(VIVO);
        log("Verify 2: User could not access product and was brought back to home page");
        Assert.assertFalse(vivo.lnkHeaderProductsLst.isDisplayed(), "FAILED! Vivo is able to access by URL while it's inactivated");
        log("INFO: Executed completely");
    }
}
