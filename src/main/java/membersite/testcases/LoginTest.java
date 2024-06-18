package membersite.testcases;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MemberConstants.LoginPage.MSG_LOGIN_BLOCKED;


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
}
