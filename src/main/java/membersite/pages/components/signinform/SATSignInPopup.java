package membersite.pages.components.signinform;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;

public class SATSignInPopup extends SignInPopup {
    public Popup signInPopup = Popup.xpath("//div[@class='login-popup-content']");
    TextBox txtpassword = TextBox.name("newpassword");
    TextBox txtconfirmPassword = TextBox.name("newcfPassword");
    TextBox txtEmail = TextBox.name("emailAddress");
    DropDownBox dddSelectCurrency = DropDownBox.name("currency");
    CheckBox cbAgree = CheckBox.id("flexCheckDefault");
    Button btnJoinNow = Button.xpath("//button[contains(@class,'btn-join-now')]");
    CheckBox capthcarcb = CheckBox.xpath("//span[@id='recaptcha-anchor']");
    private TextBox txtusername = TextBox.name("newusername");
    private TextBox txtPhoneNumber = TextBox.name("mobileNumber");
    private Label lblCountryCode = Label.xpath("//input[@value='+91']");
    private Label errorPhoneNumber = Label.xpath("//span[contains(text(),'Please enter a valid Mobile Number!')]");


    public void signin(String username, String password, String email, String currency, String phone) {
        txtusername.sendKeys(username);
        txtpassword.sendKeys(password);
        txtconfirmPassword.sendKeys(password);
        txtEmail.sendKeys(email);
        dddSelectCurrency.selectByVisibleContainsText(currency);
        txtPhoneNumber.sendKeys(phone);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DriverManager.getDriver().switchTo().frame(0);
        capthcarcb.click();
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
}



