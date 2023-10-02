package membersite.pages.components.signinform;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import org.testng.Assert;

public class SATSignInPopup extends SignInPopup {
    public Popup signInPopup = Popup.xpath("//div[@class='signup-popup-content']");
    TextBox txtpassword = TextBox.name("newpassword");
    TextBox txtconfirmPassword = TextBox.name("newcfPassword");
    TextBox txtEmail = TextBox.name("emailAddress");
    DropDownBox ddbSelectCurrency = DropDownBox.name("currency");
    DropDownBox ddbCountry = DropDownBox.xpath("//select[@class='form-control']");
    TextBox txtCountryCode = TextBox.xpath("//input[@aria-describedby = 'mobileCode']");
    CheckBox cbAgree = CheckBox.id("flexCheckDefault");
    public Button btnJoinNow = Button.xpath("//button[contains(@class,'btn-join-now')]");
    CheckBox cbCapcha = CheckBox.xpath("//span[@id='recaptcha-anchor']");
    private TextBox txtusername = TextBox.name("newusername");
    public TextBox txtPhoneNumber = TextBox.name("mobileNumber");
    private Label lblCountryCode = Label.xpath("//input[@value='+91']");
    public Label errorPhoneNumber = Label.xpath("//span[contains(text(),'Please enter a valid Mobile Number!')]");
    public Button btnSendOTP = Button.xpath("//button[contains(@class,'btn-send-otp')]");


    public void signin(String username, String password, String email, String currency, String phone) {
        txtusername.sendKeys(username);
        txtpassword.sendKeys(password);
        txtconfirmPassword.sendKeys(password);
        txtEmail.sendKeys(email);
        ddbSelectCurrency.selectByVisibleContainsText(currency);
        txtPhoneNumber.sendKeys(phone);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DriverManager.getDriver().switchTo().frame(0);
//        cbCapcha.click();
        DriverManager.getDriver().switchTo().defaultContent();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cbAgree.click();
        btnJoinNow.click();
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public boolean isCountryCodeEnable() {
        return lblCountryCode.isEnabled();
    }

    public void inputToVerifyPhoneNumber(String phoneNumber) {
        txtPhoneNumber.sendKeys(phoneNumber);
        txtusername.click();
    }

    @Override
    public boolean isPhoneNumberErrorMessage() {
        if (errorPhoneNumber.isDisplayed()) {
            return true;
        }
        return false;
    }

    @Override
    public String getPhoneNumber() {
        return txtPhoneNumber.getAttribute("value");
    }

    @Override
    public String phoneNumberError() {
        return errorPhoneNumber.getText();
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertTrue(txtusername.isDisplayed(), "FAILED! Username textbox does not display");
        Assert.assertTrue(txtpassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(txtconfirmPassword.isDisplayed(), "FAILED! Confirm password textbox does not display");
        Assert.assertTrue(txtEmail.isDisplayed(), "FAILED! Email textbox does not display");
        Assert.assertTrue(ddbCountry.isDisplayed(), "FAILED! Country dropdown does not display");
        Assert.assertTrue(ddbSelectCurrency.isDisplayed(), "FAILED! Select currency dropdown does not display");
        Assert.assertTrue(txtPhoneNumber.isDisplayed(), "FAILED! Phone number textbox does not display");
        Assert.assertTrue(txtCountryCode.isDisplayed(), "FAILED! Country code textbox does not display");
        Assert.assertEquals(txtCountryCode.getAttribute("value"),"+91", "FAILED! Country code value display incorrect");
        Assert.assertTrue(cbCapcha.isDisplayed(), "FAILED! Capcha checkbox does not display");
        Assert.assertTrue(cbAgree.isDisplayed(), "FAILED! Agree checkbox does not display");
        Assert.assertTrue(btnJoinNow.isDisplayed(), "FAILED! Join now button does not display");
    }

    public void verifyPlaceholderDisplayCorrect() {
        Assert.assertTrue(txtusername.getAttribute("placeholder").equals("Username"), "FAILED! Username textbox does not display");
        Assert.assertTrue(txtpassword.getAttribute("placeholder").equals("Password"), "FAILED! Password textbox does not display");
        Assert.assertTrue(txtconfirmPassword.getAttribute("placeholder").equals("Confirm Password"), "FAILED! Confirm password textbox does not display");
        Assert.assertTrue(txtEmail.getAttribute("placeholder").equals("Email Address"), "FAILED! Email textbox does not display");
        Assert.assertTrue(ddbCountry.getFirstSelectedOption().equals("India"), "FAILED! Country dropdown does not display");
        Assert.assertTrue(ddbSelectCurrency.getFirstSelectedOption().equals("[Select Currency]"), "FAILED! Select currency dropdown does not display");
        Assert.assertTrue(txtPhoneNumber.getAttribute("placeholder").equals("Mobile Number"), "FAILED! Phone number textbox does not display");
        Assert.assertEquals(txtCountryCode.getAttribute("value"),"+91", "FAILED! Country code value display incorrect");
    }
}



