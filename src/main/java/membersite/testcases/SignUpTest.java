package membersite.testcases;

import baseTest.BaseCaseTest;
import com.paltech.element.common.TextBox;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SignUpTest extends BaseCaseTest {

    /**
     * @title: Validate Country Code disable with default value +91
     * @steps: 1. Click on Join Now button
     ** @expect: 1. Country Code textbox is disabled with default value is +91
     */
    @Test (groups = {"smoke1"})
    public void SAT_Sign_Up_TC001() throws Exception {
        log("@title:Validate Country Code disable with default value +91");
        log("Step 1: Click on Join Now Button");
        landingPage.openSignIn();
        log("Verify 1: Country Code textbox is disabled with default value is +91");
        Assert.assertFalse(landingPage.signInPopup.isCountryCodeEnable());
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate when user input Phone Number 10 digits
     * @steps: 1. Click on Join Now button
     *         2. Input Phone Number 10 digits
     ** @expect: 2. Do not show any notifies "Please enter a valid Mobile Number!"
     */
    @Test (groups = {"smoke1"})
    public void SAT_Sign_Up_TC002() throws Exception {
        log("@title: Validate when user input Phone Number 10 digits");
        log("Step 1: Click on Join Now Button");
        landingPage.openSignIn();
        log("Step 2: Input Phone Number 10 digits");
        landingPage.signInPopup.inputToVerifyPhoneNumber("0123456789");
        log("Verify 1: Do not show any notifies \"Please enter a valid Mobile Number!\"");
        Assert.assertFalse(landingPage.signInPopup.isPhoneNumberErrorMessage());
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate when user input Phone Number under 10 digits
     * @steps: 1. Click on Join Now button
     *         2. Input Phone Number under 10 digits
     ** @expect: 2. Do not show any notifies
     */
    @Test (groups = {"smoke1"})
    public void SAT_Sign_Up_TC003() throws Exception {
        log("@title: Validate when user input Phone Number under 10 digits");
        log("Step 1: Click on Join Now Button");
        landingPage.openSignIn();
        log("Step 2: Input Phone Number 10 digits");
        landingPage.signInPopup.inputToVerifyPhoneNumber("01234567");
        log("Verify 1: Show notify \"Please enter a valid Mobile Number!\"");
        Assert.assertEquals(landingPage.signInPopup.phoneNumberError(),"Please enter a valid Mobile Number!");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate when user input Phone Number over 10 digits
     * @steps: 1. Click on Join Now button
     *         2. Input Phone Number over 10 digits
     ** @expect: 2. Phone Number Textbox just has 10 digits
     */
    @Test (groups = {"smoke1"})
    public void SAT_Sign_Up_TC004() throws Exception {
        log("@title: Validate when user input Phone Number over 10 digits");
        log("Step 1: Click on Join Now Button");
        landingPage.openSignIn();
        log("Step 2: Input Phone Number 10 digits");
        landingPage.signInPopup.inputToVerifyPhoneNumber("01234567890");
        log("Verify 1: Phone Number Textbox just has 10 digits");
        Assert.assertEquals(landingPage.signInPopup.getPhoneNumber().length(),10);
        log("INFO: Executed completely");
    }

}
