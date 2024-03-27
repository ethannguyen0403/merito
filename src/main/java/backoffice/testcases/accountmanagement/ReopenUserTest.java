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
    @Test(groups = {"smoke","tim"})
    @Parameters({"usernameAgent", "memberPassword", "username", "password"})
    public void BO_MM_Reopen_User_617(String usernameAgent, String memberPassword, String username, String password) throws Exception {
        log("@title:  Validate can reopen user");
        log("Precondition: Get the account is closed in agent site");
        backofficeHomePage.logout();
        agentHomePage = loginAgent(usernameAgent, memberPassword, _brandname);
        String userId = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstAccountClosed = DownLineListingUtils.getDownLineUsers(userId, "PL", "Closed", _brandname);
        String closeAccount = lstAccountClosed.get(0).getUserCode();

        log("Step 1. Login to BO site");
        log("Step 1. Access Member Management > Reopen User");
        loginBackoffice(username, password, true);
        ReopenUserPage page = backofficeHomePage.navigateReopenUser();

        log("Step 2. Input the account in precondition and click Search");
        page.search(closeAccount);

        try {
            log("Step 3. Click Active");
            page.activeCloseAccount(closeAccount, page.colUserCode);

            log("Verify 1. Verify status change Close to Active after clicking on Active button");
            Assert.assertTrue(page.verifyStatusAccount(closeAccount, page.colUserCode, "ACTIVE"));

            log("Verify 2. Button Active is disable");
            Assert.assertTrue(page.verifyActionButtonIsDisable(closeAccount, page.colUserCode), "FAILED! The button of active account should be disable");
            page.logout();

            log("Step 4. Login agent site > downline listing > Search the account and check status");
            loginAgent(usernameAgent, memberPassword, _brandname);
            DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
            downLineListingPage.searchDownline(closeAccount, "", "");

            log("Verify 3. Account is active in agent site");
            String status = downLineListingPage.getAccountStatus(closeAccount);
            Assert.assertEquals(status, "Active", String.format("Failed! The user %s status is not correct expected Active but found %s", closeAccount, status));
        } finally {
            log("Post-condition: Revert player status to Closed");
            loginAgent(usernameAgent, memberPassword, _brandname);
            DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
            downLineListingPage.searchDownline(closeAccount, "", "");
            EditDownLinePage editDownLinePage = downLineListingPage.clickEditIcon(closeAccount);
            editDownLinePage.accountInforSection.selectAccountStatus("Closed");
            downLineListingPage.submitEditDownline();
            log("INFO: Executed completely");
        }

    }


}
