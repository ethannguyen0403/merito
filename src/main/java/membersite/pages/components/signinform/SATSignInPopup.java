package membersite.pages.components.signinform;

import com.paltech.driver.Driver;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import org.openqa.selenium.WebDriver;

public class SATSignInPopup extends SignInPopup {
    public Popup signInPopup = Popup.xpath("//div[@class='login-popup-content']");
    TextBox txtusername = TextBox.name("newusername");
    TextBox txtpassword = TextBox.name("newpassword");
    TextBox txtconfirmPassword = TextBox.name("newcfPassword");
    TextBox txtEmail = TextBox.name("emailAddress");
    DropDownBox dddSelectCurrency = DropDownBox.name("currency");
    TextBox txtPhoneNumber = TextBox.name("mobileNumber");
    CheckBox cbAgree = CheckBox.id("flexCheckDefault");
    Button btnJoinNow = Button.xpath("//button[contains(@class,'btn-join-now')]");
    CheckBox capthcarcb = CheckBox.xpath("//span[@id='recaptcha-anchor']");

    public void signin(String username, String password, String email, String currency, String phone){
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
}



