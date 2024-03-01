package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import membersite.pages.casino.CasinoProduct;
import membersite.pages.casino.PragmaticPage;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.*;

public class PragmaticPageTest extends BaseCaseTest {
    @TestRails(id = "20232")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20232(){
        log("@title: Validate successfully access Pragmatic on Member site");
        log("@Precondition: Account has been activated Pragmatic game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Pragmatic on header menu");
        PragmaticPage pragmaticPage = memberHomePage.openPragmatic();
        log("@Verify 1: Header menu with list: 'Videos Slots', 'Classic slots', 'Blackjack', 'Baccarat', 'Baccarat New', 'Roulette', 'Scratch card', 'Live games', 'RGS-VSB' is displayed correctly");
        List<String> headerList = pragmaticPage.getListHeaderMenu();
        Assert.assertTrue(PRAGMATIC_HEADER_MENU.containsAll(headerList), String.format("FAILED! Header of Pragmatic is not correct. Actual: %s, expected: %s", headerList, PRAGMATIC_HEADER_MENU));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20241")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20241(){
        log("@title: Validate can open Pragmatic game normally");
        log("@Precondition: Account has been activated Pragmatic game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Pragmatic on header menu");
        PragmaticPage pragmaticPage = memberHomePage.openPragmatic();
        log("@Step 3: Click on any game");
        pragmaticPage.openRandomGame();
        log("@Verify 1: Able to open game without console error");
        Assert.assertTrue(pragmaticPage.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20252")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20252(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Pragmatic game match with user's balance");
        log("@Precondition: Account has been activated Pragmatic game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Pragmatic on header menu");
        PragmaticPage pragmaticPage = memberHomePage.openPragmatic();
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));

        log("@Step 3: Click on first game");
        pragmaticPage.openGameByIndex("1");

        double balanceCasino = CasinoUtils.getBalanceCasino(CasinoProduct.PRAGMATIC);
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(CasinoProduct.PRAGMATIC.toString())), currency);
        log("@Step 4: Get rate of currency from BO with rate: " + rate);
        log("@Verify 1: The in game balance should match with user's balance");
        Assert.assertEquals(balanceCasino * rate, balance, "FAILED! Balance of Pragmatic not equals to balance user");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20263")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20263() {
        log("@title: Validate could not access Pragmatic when disable product");
        log("@Precondition: Account has been activated Pragmatic game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Pragmatic on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertTrue(!memberHomePage.header.isProductTabDisplay(CasinoProduct.PRAGMATIC.toString()), "FAILED! Pragmatic display on homepage menu.");
        log("@Step 2: Access Pragmatic by external link");
        PragmaticPage pragmaticPage = (PragmaticPage) memberHomePage.openCasinoGameByUrl(PRAGMATIC);
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertTrue(!pragmaticPage.lblHeaderMenu.isDisplayed(),"FAILED! Pragmatic game is displayed");
        log("INFO: Executed completely");
    }
}
