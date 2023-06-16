package agentsite.testcase.reports;

import agentsite.pages.report.ClientLedgerPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static common.AGConstant.Report.ClientLedger.TABLE_HEADER_ALL;
import static common.AGConstant.Report.ClientLedger.TABLE_HEADER_BETTING;

public class ClientLegerTest extends BaseCaseTest {
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
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    @Test (groups = {"regression"})
    public void Agent_Report_Client_Ledger_002(){
        log("@title: Validate Client Ledger UI display correctly when view By Betting Report");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();
        String today = DateUtils.getDate(0,"dd/MM/yyyy","GMT-4:00");

        log("Step 2. Select Type : Betting Report");
        page.filter(today,today,"Betting report","","","");

        log("Verify 1. Verify Client Ledger UI display correctly when view By Betting Report");
        Assert.assertEquals(page.tblClientLedger.getColumnNamesOfTable(),TABLE_HEADER_BETTING,"Failed! Incorrect header when select Betting Report");

        log("INFO: Executed completely");
    }
    @Test (groups = {"regression"})
    public void Agent_Report_Client_Ledger_003(){
        log("@title: Validate Client Ledger UI display correctly when view By Balance Reportt");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();
        String today = DateUtils.getDate(0,"dd/MM/yyyy","GMT-4:00");

        log("Step 2. Select Type : Balance Report");
        page.filter(today,today,"Balance report","","","");

        log("Verify 1. Verify  Client Ledger UI display correctly when view By Balance Report");
        Assert.assertEquals(page.tblClientLedger.getColumnNamesOfTable(),TABLE_HEADER_BETTING,"Failed! Incorrect header when select Balance Report");

        log("INFO: Executed completely");
    }
    @Test (groups = {"regression"})
    public void Agent_Report_Client_Ledger_004(){
        log("@title: Validate Client Ledger UI display correctly when view By All Type");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();
        String today = DateUtils.getDate(0,"dd/MM/yyyy","GMT-4:00");

        log("Step 2. Select Type : All");
        page.filter(today,today,"All","","","");

        log("Verify 1. Verify  Client Ledger UI display correctly when view By Betting Report");
        Assert.assertEquals(page.tblClientLedger.getColumnNamesOfTable(),TABLE_HEADER_ALL,"Failed! Incorrect header when select Balance Report");

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
    @Test (groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_Report_Client_Ledger_005(String downlineAccount){
        log("@title: Validate can filter report by username");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();

        log("Step 2. Select Type : All");
        log("Step 3. Search Username and click submit button");
        page.filter("","","",downlineAccount,"","");

        log("Verify 1. Verify can filter report by username");
        List<String> lsLoginID =  page.tblClientLedger.getColumn(page.colUsername,false);
        Assert.assertEquals(lsLoginID.size(),2,"FAILED! There is more than 2 rows contains the search username");
        Assert.assertEquals(lsLoginID.get(0),downlineAccount,"FAILED! Login ID not correct as search criteria");

        log("INFO: Executed completely");
    }


}
