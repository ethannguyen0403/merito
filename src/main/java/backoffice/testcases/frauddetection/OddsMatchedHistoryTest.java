package backoffice.testcases.frauddetection;

import backoffice.common.BOConstants;
import baseTest.BaseCaseMerito;
import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.frauddetection.OddsMatchedHistoryPage;
import util.testraildemo.TestRails;

public class OddsMatchedHistoryTest extends BaseCaseMerito {

    /**
     * @title: Validate can search Odds Matched History without http error returned
     * @pre-condition:
     *          2. Login BO
     * @steps:  1. Access Fraud Detection > Odds Matched History
     *          2. Enter an event has bet
     *          3. Click search button
     * @expect: 1. Verify there is no console error
     */
    @Test (groups = {"http_request"})
    public void BO_BO_Fraud_Detection_Odds_Matched_History_001(){
        log("@title: Validate can search Odds Matched History without http error returned");
        log("Step 1. Access Fraud Detection > Odds Matched History");
        OddsMatchedHistoryPage page = backofficeHomePage.navigateOddsMatchedHistory();

        log("Step 2. Enter an event has bet");
        log("Step 3. Click search button");
        page.search("30093369","","","");

        log("Verify 1. Verify there is no console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate UI is correct
     * @pre-condition:
     *          2. Login BO
     * @steps: 1. Access Fraud Detection > Odds Matched History
     * @expect: 1. Verify UI is display correctly
     */
    @TestRails(id = "604")
    @Test (groups = {"smoke"})
    public void BO_BO_Fraud_Detection_Odds_Matched_History_002(){
        log("@title: Validate UI is correct");
        log("Step 1. Access Fraud Detection > Odds Matched History");
        OddsMatchedHistoryPage page = backofficeHomePage.navigateOddsMatchedHistory();

        log("Verify 1. Verify UI is display correctly");
        Assert.assertEquals(page.lblPageTitle.getText(), BOConstants.FraudDetection.OddsMatchedHistory.TITLE,"FAILED! Page title is incorrect");
        Assert.assertEquals(page.txtEventID.getAttribute("placeholder").trim(),BOConstants.FraudDetection.OddsMatchedHistory.EVENT_ID,"Event ID textbox place holder is incorrect");
        Assert.assertTrue(page.ddbTimeOrder.isDisplayed(),"FAILED! Time Order dropdown box is incorrect");
        Assert.assertTrue(page.ddbBettingOn.isDisplayed(),"FAILED! Betting on dropdown is incorrect");
        Assert.assertTrue(page.ddbMarketName.isDisplayed(),"FAILED! Market Name dropdownbox is incorrect");
        Assert.assertEquals(page.btnSearch.getText(),BOConstants.FraudDetection.OddsMatchedHistory.SEARCH,"FAILED! Search button is incorrect");
        Assert.assertEquals(page.tblOddMatchedHistory.getColumnNamesOfTable(),BOConstants.FraudDetection.OddsMatchedHistory.TABLE_HEADER, "FAILED!! Table header is incorrect");

        log("INFO: Executed completely");
    }

}
