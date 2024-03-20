package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import membersite.pages.casino.CasinoHomePage;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.*;

public class LiveDealerAsianPageTest extends BaseCaseTest {
    @TestRails(id = "20227")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20227(){
        log("@title: Validate successfully access Live Dealer Asian on Member site");
        log("@Precondition: Account has been activated Live Dealer Asian game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer Asian on header menu");
        CasinoHomePage dealerAsian = memberHomePage.openLiveDealerAsian();
        log("@Verify 1: The list of game is displayed such as 'Dragon Tiger', 'Roulette', 'Amar Akbar Antony', 'Lucky7', 'Teenpatti 20-20'…");
        List<String> productsList = dealerAsian.getListProductsMenu();
        Assert.assertTrue(LIVE_DEALER_ASIAN_PRODUCTS_MENU.containsAll(productsList), String.format("FAILED! The list of Live Dealer Asian game is not correct. Actual: %s, expected: %s", productsList, LOTTERY_SLOTS_HEADER_MENU));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20235")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20235(){
        log("@title: Validate can open Live Dealer Asian game normally");
        log("@Precondition: Account has been activated Live Dealer Asian game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer Asian on header menu");
        CasinoHomePage dealerAsian = memberHomePage.openLiveDealerAsian();
        log("@Step 3: Click on any game");
        dealerAsian.selectCasinoGame();
        log("@Verify 1: Able to open game without console error");
        Assert.assertTrue(dealerAsian.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20245")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20245(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Live Dealer Asian game match with user's balance");
        log("@Precondition: Account has been activated Live Dealer Asian game in Agent Site" +
                "The currency convert rate in BO(Provider Currency Mapping) between provider and supported currency is 1:1");
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer Asian on header menu");
        CasinoHomePage dealerAsian = memberHomePage.openLiveDealerAsian();
        log("@Step 3: Click on first product");
        dealerAsian.selectCasinoGame();

        double balanceCasino = dealerAsian.getBalance();
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get("Live Dealer Asian")), currency);
        log("@Step 4: Get rate of currency from BO with rate: " + rate);
        log("@Verify 1: The in game balance should match with user's balance");
        dealerAsian.checkBalance(balance, balanceCasino, rate);
        log("INFO: Executed completely");
    }

    @TestRails(id = "20258")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20258() {
        log("@title: Validate could not access Live Dealer Asian when disable product");
        log("@Precondition: Account has been activated Live Dealer Asian game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Pragmatic on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertTrue(!memberHomePage.header.isProductTabDisplay(LIVE_DEALER_ASIAN), "FAILED! Live Dealer Asian display on homepage menu.");
        log("@Step 3: Access Live Dealer Asian by external link");
        CasinoHomePage dealerAsian = memberHomePage.openCasinoGameByUrl(LIVE_DEALER_ASIAN);
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertTrue(dealerAsian.getListProductSize() > 0,"FAILED! Live Dealer Asian game is displayed");
        log("INFO: Executed completely");
    }
}
