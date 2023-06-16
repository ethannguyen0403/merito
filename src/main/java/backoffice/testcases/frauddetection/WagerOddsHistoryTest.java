package backoffice.testcases.frauddetection;

import backoffice.pages.bo.frauddetection.WagerOddsHistoryPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WagerOddsHistoryTest extends BaseCaseTest {

    /**
     * @title: Vaidate can view wager odds info
     * @pre-condition: 1. Place a unmatched/matched bet in member site
     * *          2. Login BO
     * @steps: 1. Access Fraud Detection > Wager Odds History
     * @expect: 1. Verify there is no console error
     */
    @Test(groups = {"http_request"})
    public void BO_Fraud_Detection_Wager_Odds_History_001() {
        log("@title: Vaidate can view wager odds info");
        log("Step Precondition: Place a unmatched/matched bet in member site");
        log("Step 1. Access Fraud Detection > Wager Odds History");
        WagerOddsHistoryPage page = backofficeHomePage.navigateWagerOddsHistory();
        log("Verify 1. Verify there is no console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }


}
