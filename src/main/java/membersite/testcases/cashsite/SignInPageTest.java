package membersite.testcases.cashsite;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInPageTest extends BaseCaseTest {


    @Test (groups = {"smoke"},invocationCount = 2)
    public void Register_Page_TC1213() {
        log("@title:Validate that user can sign in successfully");
        log("@Step 1 Navigate to  login page");
        log("@Step 2 Click login button to open login popup or underage gambling popup");
        log("@Step 3 Log in with a valid username and password");
        String usernameNew = "Auto"+StringUtils.generateAlphabetic(5);
        String passwordNew = "13Ae"+ StringUtils.generateAlphabetic(5);
        String email = usernameNew+"@yopmail.com";
        String phone = StringUtils.generateNumeric(7);
        String currency = "INR";
        HomePage homePage = landingPage.signin(usernameNew,passwordNew,email,currency,phone);

        log("@Verify 1  Home page display with My account dropdown box");
        Assert.assertTrue(homePage.isMyAccountDisplay(),"My account is display");

        log("INFO: Executed completely");

    }


}
