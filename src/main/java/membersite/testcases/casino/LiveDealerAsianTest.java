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

public class LiveDealerAsianTest extends BaseCaseTest {
    @TestRails(id = "20227")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20227(){
        log("@title: Validate successfully access Live Dealer Asian on Member site");
        log("@Precondition: Account has been activated Live Dealer Asian game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer Asian on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.LIVE_DEALER_ASIAN);
        log("@Verify 1: The list of game is displayed such as 'Dragon Tiger', 'Roulette', 'Amar Akbar Antony', 'Lucky7', 'Teenpatti 20-20'…");
        List<String> productsList = casinoPage.dealerAsian.getProductsList();
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
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.LIVE_DEALER_ASIAN);
        log("@Step 3: Click on any game");
        casinoPage.dealerAsian.openGameByIndex("1");
        log("@Verify 1: Able to open game without console error");
        Assert.assertTrue(casinoPage.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20245")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20245(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Live Dealer Asian game match with user's balance");
        log("@Precondition: Account has been activated Live Dealer Asian game in Agent Site" +
                "The currency convert rate in BO(Provider Currency Mapping) between provider and supported currency is 1:1");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer Asian on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.LIVE_DEALER_ASIAN);
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));

        log("@Step 3: Click on first product");
        casinoPage.dealerAsian.openGameByIndex("1");

        double balanceCasino = casinoPage.dealerAsian.getBalance();
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get("Live Dealer Asian")), currency);
        log("@Step 4: Get rate of currency from BO with rate: " + rate);
        log("@Verify 1: The in game balance should match with user's balance");
        Assert.assertEquals(balanceCasino * rate, balance, "FAILED! Balance of Live Dealer Asian not equals to balance user");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20258")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"userDeactivateCasino", "password"})
    public void Casino_Test_TC20258(String userDeactivateCasino, String password) throws Exception{
        log("@title: Validate could not access Live Dealer Asian when disable product");
        log("@Precondition: Account has been activated Live Dealer Asian game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        memberHomePage.logout();
        HomePage homePage =  memberHomePage.login(userDeactivateCasino, StringUtils.decrypt(password), true);
        log("@Step 2: Access Pragmatic on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertTrue(!homePage.header.isProductTabDisplay(CasinoProduct.LIVE_DEALER_ASIAN.toString()), "FAILED! Live Dealer Asian display on homepage menu.");
        log("@Step 3: Access Live Dealer Asian by external link");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGameByLink(CasinoProduct.LIVE_DEALER_ASIAN, CasinoHomePage.getURLCasino(CasinoProduct.LIVE_DEALER_ASIAN));
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertTrue(!casinoPage.dealerAsian.lnkProductsList.isDisplayed(),"FAILED! Live Dealer Asian game is displayed");
        log("INFO: Executed completely");
    }
}
