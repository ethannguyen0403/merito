package membersite.testcases.cashsite;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.HomePage;
import membersite.pages.LandingPage;
import membersite.pages.components.signinform.SATSignInPopup;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class SignInPageTest extends BaseCaseTest {
    @Test(groups = {"smoke"}, invocationCount = 2)
    public void Register_Page_TC1213() {
        log("@title:Validate that user can sign in successfully");
        log("@Step 1 Navigate to  login page");
        log("@Step 2 Click login button to open login popup or underage gambling popup");
        log("@Step 3 Log in with a valid username and password");
        String usernameNew = "Auto" + StringUtils.generateAlphabetic(5);
        String passwordNew = "13Ae" + StringUtils.generateAlphabetic(5);
        String email = usernameNew + "@yopmail.com";
        String phone = StringUtils.generateNumeric(7);
        String currency = "INR";
        HomePage homePage = landingPage.signin(usernameNew, passwordNew, email, currency, phone);

        log("@Verify 1  Home page display with My account dropdown box");
        Assert.assertTrue(homePage.isMyAccountDisplay(), "My account is display");

        log("INFO: Executed completely");

    }

    @TestRails(id = "196")
    @Test(groups = {"function_sat"})
    public void Register_Page_TC196() {
        log("@title: Validate 'Create Your Account' page show when click 'Join now' button");
        log("@Step 1 Navigate to member site");
        log("@Step 2 Click button 'JOIN NOW'");
        LandingPage landingPage = memberHomePage.logout();
        SATSignInPopup satSignInPopup = (SATSignInPopup) landingPage.header.openSigninPopup();

        log("Verify The 'Create Your Account' page is opened");
        Assert.assertTrue(satSignInPopup.signInPopup.isDisplayed(), "FAILED! Sign up popup does not display");
        log("INFO: Executed completely");

    }

}
