package agentsite.testcase.reports;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.report.ClientLedgerPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.Report.ClientLedger.TABLE_HEADER_ALL;
import static common.AGConstant.Report.ClientLedger.TABLE_HEADER_BETTING;

public class ClientLegerTest extends BaseCaseTest {
    @TestRails(id = "3747")
    @Test(groups = {"http_request"})
    public void Agent_Report_Client_Ledger_3747() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3748")
    @Test(groups = {"regression"})
    public void Agent_Report_Client_Ledger_3748() {
        log("@title: Validate Client Ledger UI display correctly when view By Betting Report");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();
        String today = DateUtils.getDate(0, "dd/MM/yyyy", "GMT-4:00");

        log("Step 2. Select Type : Betting Report");
        page.filter(today, today, "Betting report", "", "", "");

        log("Verify 1. Verify Client Ledger UI display correctly when view By Betting Report");
        Assert.assertEquals(page.tblClientLedger.getColumnNamesOfTable(), TABLE_HEADER_BETTING, "Failed! Incorrect header when select Betting Report");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3749")
    @Test(groups = {"regression"})
    public void Agent_Report_Client_Ledger_3749() {
        log("@title: Validate Client Ledger UI display correctly when view By Balance Reportt");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();
        String today = DateUtils.getDate(0, "dd/MM/yyyy", "GMT-4:00");

        log("Step 2. Select Type : Balance Report");
        page.filter(today, today, "Balance report", "", "", "");

        log("Verify 1. Verify  Client Ledger UI display correctly when view By Balance Report");
        Assert.assertEquals(page.tblClientLedger.getColumnNamesOfTable(), TABLE_HEADER_BETTING, "Failed! Incorrect header when select Balance Report");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3750")
    @Test(groups = {"regression"})
    public void Agent_Report_Client_Ledger_3750() {
        log("@title: Validate Client Ledger UI display correctly when view By All Type");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();
        String today = DateUtils.getDate(0, "dd/MM/yyyy", "GMT-4:00");

        log("Step 2. Select Type : All");
        page.filter(today, today, "All", "", "", "");

        log("Verify 1. Verify  Client Ledger UI display correctly when view By Betting Report");
        Assert.assertEquals(page.tblClientLedger.getColumnNamesOfTable(), TABLE_HEADER_ALL, "Failed! Incorrect header when select Balance Report");

        log("INFO: Executed completely");
    }

    @TestRails(id = "804")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.5.0"})
    public void Agent_Report_Client_Ledger_804() {
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("@title: Validate can filter report by username");
        log("Step 1. Navigate Report > Client Ledger");
        ClientLedgerPage page = agentHomePage.navigateClientLedgerPage();

        log("Step 2. Select Type : All");
        log("Step 3. Search Username and click submit button");
        page.filter("", "", "", loginID, "", "");

        log("Verify 1. Verify can filter report by username");
        List<String> lsLoginID = page.tblClientLedger.getColumn(page.colUsername, false);
        Assert.assertEquals(lsLoginID.size(), 1, "FAILED! There is more than 2 rows contains the search username");
        Assert.assertEquals(lsLoginID.get(0), loginID, "FAILED! Login ID not correct as search criteria");

        log("INFO: Executed completely");
    }


}
