package membersite.pages.components.loginform;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import membersite.pages.ChangePasswordPage;

public class FunLoginPopup extends LoginPopup {
    public TextBox txtUsername = TextBox.id("username");
    public TextBox txtPassword = TextBox.id("pword");
    public Button btnLogin = Button.xpath("//button[@id='login-btn-popup']/span[1]");
    public Label lblErrorMessage = Label.xpath("//div[contains(@class,'message-error')]");

    public void login(String username, String password, boolean skipByDefault) {
        txtUsername.isDisplayed();
        if (!username.isEmpty()) {
            txtUsername.sendKeys(username);
        }
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        btnLogin.click();
        btnLogin.isInvisible(2);

        if (skipByDefault) {
            ChangePasswordPage changePasswordPage = new ChangePasswordPage();
            if (changePasswordPage.btnChangePassword.isDisplayed()) {
                changePasswordPage.skip();
            }
        }
    }

    public boolean isLoginDisplay() {
        return btnLogin.isDisplayed();
    }

}
