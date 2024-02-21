package membersite.testcases.casino;

import baseTest.BaseCaseTest;
import membersite.pages.casino.CasinoHomePage;
import membersite.pages.casino.CasinoProduct;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

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
}
