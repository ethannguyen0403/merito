package membersite.pages.components.loginform;

import com.paltech.element.common.*;
import membersite.pages.ChangePasswordPage;

public class FairenterLoginPopup  extends LoginPopup {
    public Label lblTitle = Label.xpath("//div[@class='login-popup-content']//b");
    public Label lblErrorMessage = Label.xpath("//div[contains(@class,'message-error')]");
    public Label lblRememberMe = Label.xpath("//label[@for='frmLoginRemember']");
    public CheckBox cbRememberMe = CheckBox.id("frmLoginRemember");
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    public Button btnLogin = Button.xpath("//button[contains(@class,'btn-login')] | //input[@value='Login']");

    public void login(String username, String password, boolean skipByDefault){
        txtUsername.isDisplayed();
        if (!username.isEmpty()) {
            txtUsername.sendKeys(username);
        }
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        btnLogin.click();
        // waiting for loading
        btnLogin.isInvisible(2);
        if(skipByDefault) {
            ChangePasswordPage changePasswordPage = new ChangePasswordPage();
            if (changePasswordPage.btnChangePassword.isDisplayed()) {
                changePasswordPage.skip();
            }
        }

    }

    public boolean isLoginDisplay(){return btnLogin.isDisplayed();}

}



