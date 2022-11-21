package agentsite.testcase.satsport.report;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.report.ClientLedgerPage;
import util.testraildemo.TestRails;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.CLIENT_LEDGER;
import static agentsite.common.AGConstant.HomePage.REPORT;

public class ClientLegerTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Client Ledger
     * @expect: 1.  There is no http requests error
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Client_Ledger_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > Client Ledger");
        agentHomePage.clickSubMenu(REPORT, CLIENT_LEDGER, ClientLedgerPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can filter report by username
     * @pre-condition:
     *           1. Log in successfully by SAD levle
     * @steps: 1. Navigate Report >  Client Ledger
     *          2. Select Type : All
     *          3. Search Username and click submit button
     * @expect: 1. Verify can filter report by username
     */
    @TestRails(id="804")
    @Test (groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_Report_Client_Ledger_005(String downlineAccount){
        log("@title: Validate can filter report by username");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.clickSubMenu(REPORT, CLIENT_LEDGER, ClientLedgerPage.class);

        log("Step 2. Select Type : All");
        log("Step 3. Search Username and click submit button");
        page.filter("","","All",downlineAccount,"","");

        log("Verify 1. Verify can filter report by username");
        List<String> lsLoginID =  page.tblClientLedger.getColumn(page.colLoginID,false);
        Assert.assertEquals(lsLoginID.size(),1,"FAILED! There is more than 2 rows contains the search username");
        Assert.assertEquals(lsLoginID.get(0),downlineAccount,"FAILED! Login ID not correct as search criteria");

        log("INFO: Executed completely");
    }


}
