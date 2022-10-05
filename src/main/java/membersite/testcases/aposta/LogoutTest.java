package membersite.testcases.aposta;

import com.paltech.driver.DriverManager;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.home.LandingPage;
import baseTest.BaseCaseMerito;

public class LogoutTest extends BaseCaseMerito {
    /**
     * @title: Validate that user can sign out successfully
     * @steps:   1. Navigate to login page
     *           2. Click login button and confirm more than 18 years old
     *           3. Log in with a valid username and password
     *           4. Click Logout link from User Account dropdown
     * @expect:  1. Login button on Login page is displayed
     *
     */
    @Parameters({"username", "password"})
    @Test (groups = {"smoke"})
    public void Logout_001(String username, String password) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");
        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.apHeaderControl.clickLoginBtn();

        log("Step 3: Log in with a valid username and password");
        loginPopup.login(username, StringUtils.decrypt(password),true);

        log("Step: Close the popup if displayed");
        memberHomePage.closeBannerPopup();

        log("Step 4: Click Logout link on My Account drop-down list");
        memberHomePage.apHeaderControl.logout();

        log("Verify 1: Login button on Login page is displayed");
        log("Verify 2: imgExchangeGame on Login page is displayed");
        Assert.assertTrue(landingPage.apHeaderControl.isLoginBtnDisplay(), "ERROR: btnLogin doesn't displayed after signed out");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can sign out successfully
     * @steps:   1. Navigate to login page
     *           2. Log in with a valid username and password
     *           3. Click Logout link on My Account drop-down list
     *           4. Click Back button on this browser
     * @expect:  1. Login button on Login page is displayed
     *           2. imgBannerBet on Login page is displayed
     */
    @Parameters({"username", "password"})
    @Test (groups = {"smoke"})
    public void Logout_002(String username, String password) throws Exception {
        log("@title: Validate that user can sign in successfully");
        log("Step 1: Navigate to login page");

        log("Step 2: Click Login button and confirm more than 18 years old");
        LoginPopup loginPopup = landingPage.apHeaderControl.clickLoginBtn();

        log("Step 2: Log in with a valid username and password");
        loginPopup.login(username,StringUtils.decrypt(password),true);

        log("Step: Close the popup if displayed");
        memberHomePage.closeBannerPopup();

        log("Step 3: Click Logout link on My Account drop-down list");
        LandingPage page = memberHomePage.apHeaderControl.logout();

        log("Step 4: Click Back button on this browser");
        DriverManager.getDriver().back();

        log("Verify 1: Login button on Login page is displayed");
        Assert.assertTrue(page.apHeaderControl.isLoginBtnDisplay(), "ERROR: btnLogin doesn't displayed after signed out");
        log("INFO: Executed completely");
    }
}
