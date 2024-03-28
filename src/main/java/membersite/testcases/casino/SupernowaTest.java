package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import membersite.pages.casino.CasinoHomePage;
import membersite.pages.casino.SupernowaCasinoPage;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.ERROR_CODE_LIST;
import static common.CasinoConstant.SUPERNOWA;

public class SupernowaTest extends BaseCaseTest {

    @TestRails(id = "20223")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20223(){
        log("@title: Validate successfully access Supernowa Casino on Member site");
        log("@Precondition: Account has been activated Supernowa Casino game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Supernowa Casino on header menu");
        SupernowaCasinoPage supernowa = memberHomePage.openSupernowa();
        log("@Verify 1: Title 'SUPERNOWA' is displayed correctly on iframe");
        Assert.assertTrue(supernowa.verifyCasinoDisplay(), "FAILED! Title Supernowa casino is not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20233")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20233(){
        log("@title: Validate can open Supernowa Casino game normally");
        log("@Precondition: Account has been activated Supernowa Casino game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Supernowa Casino on header menu");
        SupernowaCasinoPage supernowa = memberHomePage.openSupernowa();
        log("@Step 3: Click on any card game on iframe");
        supernowa.selectCasinoGame();
        log("@Verify 1: The game is loaded successfully on screen without any console log error");
        Assert.assertTrue(supernowa.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20242")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    @Parameters({"BOLoginId", "BOLoginPwd", "currency"})
    public void Casino_Test_TC20242(String BOLoginId, String BOLoginPwd, String currency) throws Exception {
        log("@title: Validate balance in Supernowa Casino game match with user's balance");
        log("@Precondition: Account has been activated Supernowa Casino game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Supernowa Casino on header menu");
        memberHomePage.waitMenuLoading();
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        log("@Step 3: Click on any card game on iframe");
        log("@Verify 1: The in game balance should match with user's balance");
        SupernowaCasinoPage supernowa = memberHomePage.openSupernowa();
        double balanceCasino = supernowa.getBalance();
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        double rate = CasinoUtils.getProviderCurRate(ProviderCurrencyMappingUltils.getProviderCurrencyMapping(
                PRODUCT_NAME_TO_CODE.get(SUPERNOWA)), currency);

        supernowa.checkBalance(balance, balanceCasino, rate);
        log("INFO: Executed completely");
    }

    @TestRails(id = "20253")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20253() {
        log("@title: Validate could not access Supernowa Casino when disable product");
        log("@Precondition: Account has been activated Supernowa Casino game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Supernowa Casino on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertTrue(!memberHomePage.header.isProductTabDisplay(SUPERNOWA), "FAILED! Supernowa display on homepage menu.");
        log("@Step 2: Access Supernowa Casino by external link");
        SupernowaCasinoPage supernowa = (SupernowaCasinoPage) memberHomePage.openCasinoGameByUrl(SUPERNOWA);
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertTrue(!supernowa.verifyCasinoDisplay(),"FAILED! Supernowa casino is displayed");
        log("INFO: Executed completely");
    }

}
