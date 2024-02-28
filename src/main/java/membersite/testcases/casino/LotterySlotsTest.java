package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.HomePage;
import membersite.pages.casino.CasinoProduct;
import membersite.pages.casino.LotterySlots;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.*;

public class LotterySlotsTest extends BaseCaseTest {

    @TestRails(id = "20226")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20226(){
        log("@title: Validate successfully access Lottery & Slots on Member site");
        log("@Precondition: Account has been activated Lottery & Slots game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Lottery & Slots on header menu");
        LotterySlots lotterySlots = memberHomePage.openLotteryAndSlots();
        log("@Verify 1: Header menu with list: 'Slot games', 'Table games', 'Draw game', 'Roulette games' is displayed correctly");
        List<String> headerList = lotterySlots.getListHeaderMenu();
        Assert.assertTrue(LOTTERY_SLOTS_HEADER_MENU.containsAll(headerList), String.format("FAILED! Header of Pragmatic is not correct. Actual: %s, expected: %s", headerList, LOTTERY_SLOTS_HEADER_MENU));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20235")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20235(){
        log("@title: Validate can open Lottery & Slots game normally");
        log("@Precondition: Account has been activated Lottery & Slots game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Lottery & Slots on header menu");
        LotterySlots lotterySlots = memberHomePage.openLotteryAndSlots();
        log("@Step 3: Click on any game");
        lotterySlots.openRandomGame();
        log("@Verify 1: Able to open game without console error");
        Assert.assertTrue(lotterySlots.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20246")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20246(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Lottery & Slots game match with user's balance");
        log("@Precondition: Account has been activated Lottery & Slots game in Agent Site");
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Lottery & Slots on header menu");
        LotterySlots lotterySlots = memberHomePage.openLotteryAndSlots();


        log("@Step 3: Click on first game");
        lotterySlots.openGameByIndex("1");
        //Use console log to verify balance in game of Lottery Slots in Slot game tab
        double balanceCasino = lotterySlots.getBalanceFromLogConsole(lotterySlots.getConsoleLog("_url"));

        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(CasinoProduct.LOTTERY_SLOTS.toString())), currency);
        log("@Step 4: Get rate of currency from BO with rate: " + rate);
        log("@Verify 1: The in game balance should match with user's balance");
        Assert.assertEquals(balanceCasino * rate, balance, "FAILED! Balance of Lottery & Slots not equals to balance user");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20257")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20257() {
        log("@title: Validate could not access Lottery & Slots when disable product");
        log("@Precondition: Account has been activated Lottery & Slots game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Lottery & Slots on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertTrue(!memberHomePage.header.isProductTabDisplay(CasinoProduct.LOTTERY_SLOTS.toString()), "FAILED! Lottery & Slots display on homepage menu.");
        log("@Step 2: Access Pragmatic by external link");
        LotterySlots lotterySlots = (LotterySlots) memberHomePage.openCasinoGameByUrl(LOTTERY_AND_SLOTS);
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertTrue(!lotterySlots.lblHeaderMenu.isDisplayed(),"FAILED! Pragmatic game is displayed");
        log("INFO: Executed completely");
    }
}
