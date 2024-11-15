package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;
import static common.AGConstant.SPORT_SOCCER;

public class SportPageTest extends BaseCaseTest {
    @TestRails(id = "72888")
    @Test(groups = {"http_request"})
    public void SportPage_72888() {
        log("@title: There is no http responded error returned");
        log("Step 1. Login and navigate to Sport Page");
        memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }
}

