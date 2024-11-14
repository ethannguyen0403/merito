package membersite.testcases.casino;

import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import membersite.pages.casino.EvolutionWhiteCliffPage;
import membersite.utils.casino.CasinoUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;
import static common.CasinoConstant.EVOLUTION;
import static common.CasinoConstant.EVOLUTION_WHITE_CLIFF;


public class EvolutionPageWhiteCliffTest extends BaseCaseTest {

    @TestRails(id = "20225")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20225(){
        log("@title: Validate successfully access Evolution (WhiteCliff) on Member site");
        log("@Precondition: Account has been activated Evolution (WhiteCliff) game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Evolution (WhiteCliff) on header menu");
        EvolutionWhiteCliffPage evolutionWhiteCliffPage = memberHomePage.openEvolutionWhiteCliff();
        log("@Verify 1: Title 'Evolution' is displayed correctly on iframe");
        Assert.assertTrue(evolutionWhiteCliffPage.verifyCasinoDisplay(),"FAILED! Title Evolution casino is not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20244")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20244() {
        log("@title: Validate balance in Evolution (WhiteCliff) game match with user's balance");
        log("@Precondition: Account has been activated Evolution (WhiteCliff) game in Agent Site");
        double balance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Evolution (WhiteCliff) on header menu");
        EvolutionWhiteCliffPage evolutionWhiteCliffPage = memberHomePage.openEvolutionWhiteCliff();

        log("@Step 3: Click on first game");
        evolutionWhiteCliffPage.selectCasinoGame();
        double balanceCasino = evolutionWhiteCliffPage.getBalance();
        log("@Verify 1: The in game balance should match with user's balance");
        Assert.assertEquals(balance,balanceCasino,"FAILED! Balance of Casino game not equals to balance user");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20255")
    @Test(groups = {"casino_product_inactive", "Casino.2024.V.1.0_product_inactive"})
    public void Casino_Test_TC20255() {
        log("@title: Validate could not access Evolution (WhiteCliff) when disable product");
        log("@Precondition: Account has been activated  Evolution (WhiteCliff) game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Evolution (WhiteCliff) on header menu");
        log("@Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertFalse(memberHomePage.header.isProductTabDisplay(EVOLUTION), "FAILED! Access Evolution (WhiteCliff) display on homepage menu.");
        log("@Step 3: Access Pragmatic by external link");
        EvolutionWhiteCliffPage evolutionWhiteCliffPage = (EvolutionWhiteCliffPage) memberHomePage.openCasinoGameByUrl(EVOLUTION_WHITE_CLIFF);
        log("@Verify 2: User could not access product and was brought back to home page");
        Assert.assertFalse(evolutionWhiteCliffPage.verifyCasinoDisplay(),"FAILED! Evolution (WhiteCliff) game is displayed");
        log("INFO: Executed completely");
    }
}
