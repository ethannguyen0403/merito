package membersite.testcases.casino;

import baseTest.BaseCaseTest;
import membersite.pages.casino.CasinoHomePage;
import membersite.pages.casino.CasinoProduct;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MemberConstants.Casino.*;

public class SupernowaTest extends BaseCaseTest {

    @TestRails(id = "20223")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20223(){
        log("@title: Validate successfully access Supernowa Casino on Member site");
        log("@Precondition: Account has been activated Supernowa Casino game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Supernowa Casino on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.SUPERNOWA_CASINO);
        log("@Verify 1: Title 'SUPERNOWA' is displayed correctly on iframe");
        Assert.assertEquals(casinoPage.supernowa.lblTitle.getText().trim(), SUPERNOWA,"FAILED! Title Supernowa casino is not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20233")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20233(){
        log("@title: Validate can open Supernowa Casino game normally");
        log("@Precondition: Account has been activated Supernowa Casino game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Supernowa Casino on header menu");
        CasinoHomePage casinoPage = memberHomePage.openCasinoGame(CasinoProduct.SUPERNOWA_CASINO);
        log("@Step 3: Click on any card game on iframe");
        casinoPage.supernowa.openFirstSupernowaGame();
        log("@Verify 1: The game is loaded successfully on screen without any console log error");
        Assert.assertTrue(casinoPage.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

}
