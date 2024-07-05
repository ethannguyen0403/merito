package membersite.testcases;

import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.report.ViewLogPage;
import backoffice.pages.bo.accountmanagement.PlayerInfoPage;
import backoffice.pages.bo.accountmanagement.ReopenUserPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.List;

import static common.MemberConstants.LoginPage.MSG_LOGIN_BLOCKED;
import static common.MemberConstants.MEMBER_BLOCKED_LOG_MESSAGE;


public class LoginTest extends BaseCaseTest {

    @TestRails(id = "29805")
    @Parameters({"username", "password"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void Member_login_TC29805(String username, String password) throws Exception {
        log("@title: Validate that the user can login successfully for Desktop on Member site when the correct password is inputted after 4 consecutive incorrect password");
        log("Precondition 1: The account login had 4 consecutive incorrect password");
        LandingPage landingPage = memberHomePage.logout();
        landingPage.loginInvalid(username, password, 4);
        log("Step 1: Valid login");
        landingPage.login(username, StringUtils.decrypt(password), true);
        log("Verify 1: The user can login successfully");
        Assert.assertTrue(memberHomePage.isLoginSuccess(), "FAILED! User login failed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29807")
    @Parameters({"username", "password", "usernameSAT"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void Member_login_TC29807(String username, String password, String usernameSAT) throws Exception {
        log("@title: Validate that display the error message after 5 consecutive incorrect password are inputted for Desktop on Member site");
        log("Precondition 1: The account login had 5 consecutive incorrect password");
        try{
            LandingPage landingPage = memberHomePage.logout();
            landingPage.loginInvalid(username, password, 5);
            log("Step 1: Login at the 5 attempt");
            String blockMessage = landingPage.loginInvalid(username, password);
            log("Verify 1: User cannot login and display the error message ' Your account has been blocked. Please contact your Upline for help ' in Login dialog");
            Assert.assertEquals(blockMessage, MSG_LOGIN_BLOCKED, "FAILED! User login failed");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29809")
    @Parameters({"username", "password", "usernameSAT"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void Member_login_TC29809(String username, String password, String usernameSAT) throws Exception {
        log("@title: Validate that the user cannot login when the correct password is inputted after 5 consecutive incorrect password for Desktop on Member site");
        log("Precondition 1: The account login had 5 consecutive incorrect password");
        try{
            LandingPage landingPage = memberHomePage.logout();
            landingPage.loginInvalid(username, password, 5);
            log("Step 1: Login at the 6 attempt with correct password");
            String blockMessage = landingPage.loginInvalid(username, StringUtils.decrypt(password));
            log("Verify 1: User cannot login and display the error message ' Your account has been blocked. Please contact your Upline for help ' in Login dialog");
            Assert.assertEquals(blockMessage, MSG_LOGIN_BLOCKED, "FAILED! User login failed");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29811")
    @Parameters({"username", "password", "usernameSAT"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0", "interaction"})
    public void Member_login_TC29811(String username, String password, String usernameSAT) throws Exception {
        log("@title: Validate that the user cannot login when the correct password is inputted after 5 consecutive incorrect password for Desktop on Member site");
        log("Precondition 1: The player account has been locked after logged in 5 consecutive incorrect password on Member site");
        LandingPage landingPage = memberHomePage.logout();
        landingPage.loginInvalid(username, password, 5);
        try{
            log("Step 1: Access to Agent site");
            loginAgent(usernameSAT, password, true);
            log("Step 2: Go to Downline Listing page");
            DownLineListingPage downLinePage = agentHomePage.navigateDownlineListingPage();
            log("Step 3: Try to find the player account has been blocked");
            downLinePage.searchDownline(username, "Blocked", "");
            log("Verify 1: Display status ' Blocked ' for the player account has been locked after logged in 5 consecutive incorrect password");
            List<String> lstRecord = downLinePage.tblDowlineListing.getColumn(downLinePage.userCodeCol, false);
            Assert.assertEquals(lstRecord.get(0), username, String.format("Failed! Expected login id %s display but found %s", username, lstRecord.get(0)));
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29812")
    @Parameters({"username", "password", "usernameSAT"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0", "interaction"})
    public void Member_login_TC29812(String username, String password, String usernameSAT) throws Exception {
        log("@title: Validate that the upline agent account can change status in Agent site for the player account has status ' Blocked '");
        log("Precondition 1: The player account has been locked after logged in 5 consecutive incorrect password on Member site");
        LandingPage landingPage = memberHomePage.logout();
        landingPage.loginInvalid(username, password, 5);
        try{
            log("Step 1: Access to Agent site");
            loginAgent(usernameSAT, password, true);
            log("Step 2: Go to Downline Listing page");
            DownLineListingPage downLinePage = agentHomePage.navigateDownlineListingPage();
            log("Step 3: Find the player account has been locked");
            downLinePage.searchDownline(username, "Blocked", "");
            log("Step 4: Try to change status from Blocked to any status Active/ Inactive/ Suspended");
            EditDownLinePage editDownLinePage = downLinePage.clickEditIcon(username);
            log("Verify 1: Check if the upline agent can change status to Active");
            editDownLinePage.accountInforSection.selectAccountStatus("Active");
            Assert.assertEquals( editDownLinePage.accountInforSection.getAccountStatus(), "Active", "FAILED! Can not change status to Active" );
            log("Verify 2: Check if the upline agent can change status to Suspended");
            editDownLinePage.accountInforSection.selectAccountStatus("Suspended");
            Assert.assertEquals( editDownLinePage.accountInforSection.getAccountStatus(), "Suspended", "FAILED! Can not change status to Suspended" );
            log("Verify 1: Check if the upline agent can change status to Inactive");
            editDownLinePage.accountInforSection.selectAccountStatus("Inactive");
            Assert.assertEquals( editDownLinePage.accountInforSection.getAccountStatus(), "Inactive", "FAILED! Can not change status to Inactive" );
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29813")
    @Parameters({"username", "password", "usernameSAT"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0", "interaction"})
    public void Member_login_TC29813(String username, String password, String usernameSAT) throws Exception {
        log("@title: Validate that the upline agent account can view the log record in Agent site for the player account has been locked after logged in 5 consecutive incorrect password");
        log("Precondition 1: The player account has been locked after logged in 5 consecutive incorrect password on Member site");
        LandingPage landingPage = memberHomePage.logout();
        landingPage.loginInvalid(username, password, 5);
        try{
            log("Step 1: Access to Agent site");
            loginAgent(usernameSAT, password, true);
            log("Step 2: Go to View Log page");
            ViewLogPage viewLogPage = agentHomePage.navigateViewLogPage();
            log("Step 3: Find the player account has been locked");
            viewLogPage.filter("", "", "User Profile", "");
            log("Verify 1: The log record on ‘View Log’ page – User Profile with the Remark ‘Account Status changed - Blocked due to 5 consecutive incorrect password attempts.'");
            Assert.assertEquals(viewLogPage.getRemarkLogTable(1), MEMBER_BLOCKED_LOG_MESSAGE, "FAILED! Log record is not correct");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29814")
    @Parameters({"username", "password", "usernameSAT"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0", "interaction"})
    public void Member_login_TC29814(String username, String password, String usernameSAT) throws Exception {
        log("@title: Validate that the upline agent cannot change the status to 'Blocked' for any player account that does not have the 'Blocked' status in Agent site , even if the player account has been un-locked before");
        log("Precondition 1: The player account has been changed to Active/ Inactive/ Suspended status from Blocked status before");
        LandingPage landingPage = memberHomePage.logout();
        landingPage.loginInvalid(username, password, 5);
        loginAgent(usernameSAT, password, true);
        try{
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
            agentHomePage.leftMenu.switchMainMenu();
            log("Step 1: Access to Agent site");
            log("Step 2: Go to Downline Listing page");
            DownLineListingPage downLinePage = agentHomePage.navigateDownlineListingPage();
            log("Step 3: Go to Edit Member page for the player account as precondition");
            downLinePage.searchDownline(username, "", "");
            EditDownLinePage editDownLinePage = downLinePage.clickEditIcon(username);
            log("Verify 1: Cannot change the status to 'Blocked'");
            Assert.assertEquals(editDownLinePage.accountInforSection.getAccountStatusList(), Arrays.asList("Active", "Suspended", "Inactive"), "FAILED! Can not change status to Active" );
        }finally {
            log("Post-Condition: Unblock user");
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29815")
    @Parameters({"username", "password", "usernameSAT"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void Member_login_TC29816(String username, String password, String usernameSAT) throws Exception {
        log("@title: Validate that the player can login successfully on Member site after the upline agent change to the status is ‘Active’ or 'Suspended' from Blocked status for Desktop on Member site");
        log("Precondition 1: The player account has been changed to Active/ Suspended status from Blocked status by the upline agent");
        try{
            LandingPage landingPage = memberHomePage.logout();
            landingPage.loginInvalid(username, password, 5);
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
            log("Step 1: Access to Member site > Login successfully");
            loginMember(username, password);
            log("Verify 1: The user can login successfully");
            Assert.assertTrue(memberHomePage.isLoginSuccess(), "FAILED! The user is not login successfully");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29817")
    @Parameters({"username", "password", "usernameSAT", "BOLoginId", "BOLoginPwd"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void Member_login_TC29817(String username, String password, String usernameSAT, String BOLoginId, String BOLoginPwd) throws Exception {
        log("@title: Validate that display status ' Blocked ' in Player Info page - BO site for the player account has been locked after logged in 5 consecutive incorrect password");
        log("Precondition 1: The player account has been locked after logged in 5 consecutive incorrect password on Member site");
        try{
            LandingPage landingPage = memberHomePage.logout();
            landingPage.loginInvalid(username, password, 5);
            log("Step 1: Access to BO site");
            loginBackoffice(BOLoginId, BOLoginPwd, true);
            log("Step 2: Go to Player Info page");
            PlayerInfoPage playerInfoPage = backofficeHomePage.navigatePlayerInfo();
            log("Step 3: Try to find the player account has been locked as precondition");
            playerInfoPage.viewPlayer(username);
            log("Verify 1: Display status ' Blocked ' for the player account has been locked after logged in 5 consecutive incorrect password");
            Assert.assertEquals(playerInfoPage.tblPlayerInfo.getControlOfCell(1, 2, 11, null).getText(), "BLOCKED", "FAILED! Account status is not blocked.");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }

    @TestRails(id = "29818")
    @Parameters({"username", "password", "usernameSAT", "BOLoginId", "BOLoginPwd"})
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void Member_login_TC29818(String username, String password, String usernameSAT, String BOLoginId, String BOLoginPwd) throws Exception {
        log("@title: Validate that the operators in BO site can active for the player account has been locked after logged in 5 consecutive incorrect password");
        log("Precondition 1: The player account has been locked after logged in 5 consecutive incorrect password on Member site");
        try{
            LandingPage landingPage = memberHomePage.logout();
            landingPage.loginInvalid(username, password, 5);
            log("Step 1: Access to BO site");
            loginBackoffice(BOLoginId, BOLoginPwd, true);
            log("Step 2: Go to Reopen User page");
            ReopenUserPage reopenUserPage = backofficeHomePage.navigateReopenUser();
            log("Step 3: Try to find the player account has been locked as precondition");
            reopenUserPage.search(username);
            log("Step 3: Click Active button");
            reopenUserPage.activeCloseAccount(username, 2);
            log("Step 4: Access to Member site, try to login with password is correct for player as precondition >>  Observe");
            loginMember(username, password);
            log("Verify 1: The player can login successfully for Desktop/Mobile on Member site");
            Assert.assertTrue(memberHomePage.isLoginSuccess(), "FAILED! The user is not login successfully");
            log("INFO: Executed completely");
        }finally {
            log("Post-Condition: Unblock user");
            loginAgent(usernameSAT, password, true);
            agentHomePage.leftMenu.switchQuickSearch();
            agentHomePage.quickSearch.updateStatus(username, "Active", true);
        }
    }
}
