package membersite.testcases.cashsite;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.HomePage;
import membersite.pages.LandingPage;
import membersite.pages.components.signinform.SATSignInPopup;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class SignUpPageTest extends BaseCaseTest {
    @Test(groups = {"cashsite", "2022.10.31"})
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
    @Test(groups = {"cashsite", "2022.10.31"})
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

    @TestRails(id = "197")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Register_Page_TC197() {
        log("@title: Validate UI 'Create Your Account' page");
        log("@Step 1 Navigate to member site");
        log("@Step 2 Click button 'JOIN NOW'");
        LandingPage landingPage = memberHomePage.logout();
        SATSignInPopup satSignInPopup = (SATSignInPopup) landingPage.header.openSigninPopup();

        log("Verify The page 'Create Your Account' is shown with the following controls");
        satSignInPopup.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "198")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Register_Page_TC198() {
        log("@title: Validate the placeholder on all textboxs");
        log("@Step 1 Navigate to member site");
        log("@Step 2 Click button 'JOIN NOW'");
        LandingPage landingPage = memberHomePage.logout();
        SATSignInPopup satSignInPopup = (SATSignInPopup) landingPage.header.openSigninPopup();

        log("Validate the placeholder of controls in page 'Create Your Account'");
        satSignInPopup.verifyPlaceholderDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "199")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Register_Page_TC199() {
        log("@title: Validate behavior when signing up without data");
        log("@Step 1 Navigate to member site");
        log("@Step 2 Click button 'JOIN NOW'");
        LandingPage landingPage = memberHomePage.logout();
        SATSignInPopup satSignInPopup = (SATSignInPopup) landingPage.header.openSigninPopup();

        log("@Step 3 Leave all fields as empty and click Join Now");
        log("Verify the button Join Now is disabled");
        Assert.assertEquals(satSignInPopup.btnJoinNow.getAttribute("disabled"), "true");
        log("INFO: Executed completely");
    }

    @TestRails(id = "200")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Register_Page_TC200() {
        log("@title: Validate button 'Send OTP' when player input number in Mobile Number textbox");
        String username = "Auto" + StringUtils.generateAlphabetic(5);
        String password = "qwer" + StringUtils.generateAlphabetic(5);
        String email = username + "@yopmail.com";
        String phoneInvalid = StringUtils.generateNumeric(4);
        String phoneValid = StringUtils.generateNumeric(10);
        String currency = "INR";
        log("@Step 1 Navigate to member site");
        log("@Step 2 Click button 'JOIN NOW'");
        LandingPage landingPage = memberHomePage.logout();
        SATSignInPopup satSignInPopup = (SATSignInPopup) landingPage.header.openSigninPopup();

        log("@Step 3 Enter number in Mobile Number textbox and observe button 'Send OTP'");
        landingPage.signin(username, password, email, currency, phoneInvalid);

        log("Verify Button 'Send OTP' is disabled when player inputs invalid number for Mobile Number field");
        Assert.assertEquals(satSignInPopup.btnSendOTP.getAttribute("disabled"), "true", "FAILED! Send OTP button is not disabled");
        log("Verify 'Please enter a valid Mobile Number!' message appears");
        Assert.assertEquals(satSignInPopup.errorPhoneNumber.getText(), "Please enter a valid Mobile Number!", "FAILED! Error message invalid phone number does not display");

        log("@Step 3 Enter number in Mobile Number textbox and observe button 'Send OTP'");
        landingPage.signin(username, password, email, currency, phoneValid);
        log("Verify Button 'Send OTP' is enabled when player inputs valid number for Mobile Number field");
        Assert.assertEquals(satSignInPopup.btnSendOTP.getAttribute("disabled"), "false", "FAILED! Send OTP button is not enabled");
        log("Verify 'Please enter a valid Mobile Number!' message does not appear");
        Assert.assertFalse(satSignInPopup.errorPhoneNumber.isDisplayed(), "FAILED! Error message invalid phone number is displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3884")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Register_Page_TC3884() {
        log("@title: Validate button 'Send OTP' when player input number in Mobile Number textbox");
        String username = "Auto" + StringUtils.generateAlphabetic(5);
        String password = "qwer" + StringUtils.generateAlphabetic(5);
        String email = username + "@yopmail.com";
        String phoneInvalid = StringUtils.generateNumeric(11);
        String currency = "INR";
        log("@Step 1 Navigate to member site");
        log("@Step 2 Click button 'JOIN NOW'");
        LandingPage landingPage = memberHomePage.logout();
        SATSignInPopup satSignInPopup = (SATSignInPopup) landingPage.header.openSigninPopup();

        log("@Step 3 Input all valid fileds and phone number more than 10 number");
        landingPage.signin(username, password, email, currency, phoneInvalid);

        log("Verify user cannot input phone number more than 10 numbers");
        Assert.assertEquals(satSignInPopup.txtPhoneNumber.getAttribute("value"), 10, "FAILED! User able to input more than 10 number into Phone Number");
        log("INFO: Executed completely");
    }

}
