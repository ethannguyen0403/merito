package membersite.testcases.casino;

import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import membersite.objects.AccountBalance;
import membersite.pages.casino.CasinoProduct;
import membersite.pages.casino.LiveDealerEuropean;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.MemberConstants.Casino.*;

public class LiveDealerEuropeanTest extends BaseCaseTest {
    @TestRails(id = "20228")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20228(){
        log("@title: Validate successfully access Live Dealer European on Member site");
        log("@Precondition: Account has been activated Live Dealer European game in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer European on header menu");
        LiveDealerEuropean liveDealerEuropean = (LiveDealerEuropean) memberHomePage.openCasinoGame2(CasinoProduct.LIVE_DEALER_EUROPEAN);
        log("@Verify 1: The list of game is displayed such as 'Teen Patti', 'Bet on Teen Patti', 'One Teen Patti Classic'…");
        List<String> productsList = liveDealerEuropean.getProductsList();
        Assert.assertTrue(LIVE_DEALER_EUROPEAN_PRODUCTS_MENU.containsAll(productsList), String.format("FAILED! The list of Live Dealer European game is not correct. Actual: %s, expected: %s", productsList, LIVE_DEALER_EUROPEAN_PRODUCTS_MENU));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20237")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20237(){
        log("@title: Validate can open Live Dealer European game normally");
        log("@Precondition: Account has been activated Live Dealer European in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer European on header menu");
        LiveDealerEuropean liveDealerEuropean = (LiveDealerEuropean) memberHomePage.openCasinoGame2(CasinoProduct.LIVE_DEALER_EUROPEAN);
        log("Step 3: Click on any game");
        liveDealerEuropean.openGameByIndex("1");
        log("@Verify 1: The game is opened in new popup successfully without console log error");
        Assert.assertTrue(liveDealerEuropean.verifyConsoleLogNotContainValue(ERROR_CODE_LIST),"FAILED! Console log contain error code");
        log("INFO: Executed completely");
    }

    @TestRails(id = "20248")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20248(){
        //use account INR
        log("@title: Validate balance in Live Dealer European  game match with user's balance");
        log("@Precondition: Account has been activated Live Dealer European in Agent Site\n" +
                "The currency convert rate in BO(Provider Currency Mapping) between provider and supported currency is 1:1");
        memberHomePage.waitMenuLoading();
        AccountBalance userBalance = memberHomePage.getUserBalance();
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer European on header menu");
        LiveDealerEuropean liveDealerEuropean = (LiveDealerEuropean) memberHomePage.openCasinoGame2(CasinoProduct.LIVE_DEALER_EUROPEAN);
        log("Step 3: Click on any game");
        liveDealerEuropean.openGameByIndex("1");
        log("@Verify 1: The game is opened in new popup successfully without console log error");
        String balanceCasino = liveDealerEuropean.getUserBalance();
        Assert.assertEquals(userBalance.getBalance(), balanceCasino, String.format("FAILED! User balance is not correct expected %s actual %s", userBalance.getBalance(), balanceCasino));
        log("INFO: Executed completely");
    }

    @TestRails(id = "20259")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20259(){
        log("@title: Validate could not access Live Dealer European when disable product");
        log("@Precondition: Account has been disable Live Dealer European in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Live Dealer European on header menu");
        memberHomePage.clickProduct("Live Dealer");
        log("Verify 1: The product should not displayed on header menu to prevent user from accessing");
        Assert.assertFalse(memberHomePage.isCasinoProductDisplayed("European Room"), "FAILED! Inactive product still displays on header menu");
        log("@Step 3: Access Live Dealer European by external link (e.g.: /home/live-dealer/ezugi)");
        String urlProduct = defineCasinoURL(_brandname,LIVE_DEALER_EUROPEAN_SUFFIX_LINK);
        memberHomePage.openCasinoGameByLink2(urlProduct);
        log("Verify 2: User could not access product and was brought back to home page");
        String urlCurrent = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(!urlCurrent.equals(urlProduct), String.format("FAILED! User is not redirect back to home when access inactive product by URL %s, current URL is %s",urlProduct,urlCurrent));
        log("INFO: Executed completely");
    }
}
