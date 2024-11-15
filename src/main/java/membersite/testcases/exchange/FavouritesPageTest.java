package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class FavouritesPageTest extends BaseCaseTest {
    @TestRails(id = "72891")
    @Test(groups = {"http_request"})
    public void FavouritesPage_72891() {
        log("@title: There is no http responded error returned");
        log("Step 1. Login and navigate to Favourites Page");
        memberHomePage.navigateFavouritesPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

}
