package backoffice.testcases.accountmanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import backoffice.pages.bo.accountmanagement.ReopenUserPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class ReopenUserTest extends BaseCaseTest {
    /**
     * @title: Validate can reopen user
     * @pre-condition: 1. Get the account is closed in agent site
     * 2. Login BO
     * @steps: 1. Access Member Management >Reopen User
     * 2. Input the account in precondition and click Search
     * 3. Click Active
     * 4. Login agent site > downline listing > Search the account and check status
     * @expect: 1. Verify status change Close to Active after clicking on Active button
     * 2. Button Active is disable
     * 3. Account is active in agent site
     */
    @TestRails(id = "617")
    @Test(groups = {"smoke"})
    @Parameters({"satSADAgentLoginID", "memberPassword", "username", "password"})
    public void BO_MM_Reopen_User_617(String satSADAgentLoginID, String memberPassword, String username, String password) throws Exception {
        log("@title:  Validate can reopen user");
        log("Precondition: Get the account is closed in agent site");
        backofficeHomePage.logout();
        //change _brandname to satsport for using UI
        this._brandname = "satsport";
        agentHomePage = loginAgent(satSADAgentLoginID, memberPassword, _brandname);
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        downLineListingPage.searchDownline("","Closed","Member");
        List<String> lstAccountClosed = downLineListingPage.getAccountUserName();
        String closedAccount = lstAccountClosed.get(0);

        log("Step 1. Login to BO site");
        log("Step 1. Access Member Management > Reopen User");
        this._brandname = "backoffice";
        loginBackoffice(username, password, true);
        ReopenUserPage page = backofficeHomePage.navigateReopenUser();

        log("Step 2. Input the account in precondition and click Search");
        page.search(closedAccount);

        try {
            log("Step 3. Click Active");
            page.activeCloseAccount(closedAccount, page.colUserCode);

            log("Verify 1. Verify status change Close to Active after clicking on Active button");
            Assert.assertTrue(page.verifyStatusAccount(closedAccount, page.colUserCode, "ACTIVE"));

            log("Verify 2. Button Active is disable");
            Assert.assertTrue(page.verifyActionButtonIsDisable(closedAccount, page.colUserCode), "FAILED! The button of active account should be disable");
            page.logout();

            log("Step 4. Login agent site > downline listing > Search the account and check status");
            this._brandname = "satsport";
            loginAgent(satSADAgentLoginID, memberPassword, _brandname);
            downLineListingPage = agentHomePage.navigateDownlineListingPage();
            downLineListingPage.searchDownline(closedAccount, "", "");

            log("Verify 3. Account is active in agent site");
            String status = downLineListingPage.getAccountStatus(closedAccount);
            Assert.assertEquals(status, "Active", String.format("Failed! The user %s status is not correct expected Active but found %s", closedAccount, status));
        } finally {
            log("Post-condition: Revert player status to Closed");
            loginAgent(satSADAgentLoginID, memberPassword, "satsport");
            downLineListingPage = agentHomePage.navigateDownlineListingPage();
            downLineListingPage.searchDownline(closedAccount, "", "");
            EditDownLinePage editDownLinePage = downLineListingPage.clickEditIcon(closedAccount);
            editDownLinePage.accountInforSection.selectAccountStatus("Closed");
            downLineListingPage.submitEditDownline();
            log("INFO: Executed completely");
        }

    }


}
