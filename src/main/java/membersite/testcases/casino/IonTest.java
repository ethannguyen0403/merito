package membersite.testcases.casino;

import baseTest.BaseCaseTest;
import membersite.pages.casino.Ion;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.CasinoConstant.*;

public class IonTest extends BaseCaseTest {
    @TestRails(id = "20231")
    @Test(groups = {"casino", "Casino.2024.V.1.0"})
    public void Casino_Test_TC20231(){
        log("@title: Validate successfully access Ion on Member site");
        log("@Precondition: Account has been activated Ion in Agent Site");
        log("@Step 1: Login member site with precondition account");
        log("@Step 2: Access Ion on header menu");
        Ion ion = memberHomePage.openIon();
        log("@Verify 1: Header menu with list: 'All', 'Roulette', 'Blackjack', 'Limitless Blackjack', 'Baccarat', 'Casino Hold'em', 'Teen patti', 'Andar Bahar' is displayed");
        List<String> productsList = ion.getProductsList();
        Assert.assertTrue(ION_PRODUCTS_MENU.containsAll(productsList), String.format("FAILED! The list of Ion game is not correct. Actual: %s, expected: %s", productsList, ION_PRODUCTS_MENU));
        log("INFO: Executed completely");
    }

}
