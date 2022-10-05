package agentsite.testcase.satsport.report;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.TransactionHistoryPage;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.TRANSACTION_HISTORY;

public class TransactionHistoryTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Transaction history
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Transaction_History_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Transaction history");
        agentHomePage.clickSubMenu(REPORT, TRANSACTION_HISTORY, TransactionHistoryPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data on Transaction History display correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report> Transaction history
     *          2. Search Exchange Product and the date range has data
     * @expect: 1. Verify data of each competition display correctly
     */
    @Test (groups = {"smoke"})
    public void  Agent_Report_Transaction_History_003 (){
        log("@title: Validate data on Transaction History display correctly y");
        log("Step 1. Navigate Report >  Win Loss By Event");
        TransactionHistoryPage page = agentHomePage.clickSubMenu(REPORT, TRANSACTION_HISTORY, TransactionHistoryPage.class);

        log("Step 2. Search Exchange Product and the date range has data");
        page.dpFrom.previousMonthWithDate(0,"1");
        page.filter("Exchange");

        log("Verify 1. Verify data of each competition display correctly");
        if(page.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecord.getText(), "No records found.","FAILED! Incorrect text when have no record");
            return;
        }
        Assert.assertTrue(page.verifyVolumePnLTaxMatchWithDetail(),"FAILED! Summary data not match with Transaction Detail");


        log("INFO: Executed completely");
    }
}
