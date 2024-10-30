package membersite.pages.components.loginform;

import com.paltech.element.common.*;
import membersite.pages.ChangePasswordPage;

public class SATLoginPopup extends LoginPopup {
    public Popup popupLogin = Popup.xpath("//div[@class='login-popup-content']");
    public Label lblTitle = Label.xpath("//div[@class='login-popup-content']//b");
    public Label lblErrorMessage = Label.xpath("//div[contains(@class,'message-error')]");
    public Label lblRememberMe = Label.xpath("//div[@class='row remember']//span[@class='square-icon']");
    public CheckBox cbRememberMe = CheckBox.xpath("//div[@class='row remember']//i");
    public TextBox txtUsername = TextBox.id("username");
    public TextBox txtPassword = TextBox.id("pword");
    public Button btnLogin = Button.xpath("//button[contains(@class,'btn-verification btn-confirm')]");
    public Button btnExit = Button.xpath("//button[@class='btn-verification']");
    public Icon icAge = Icon.xpath("//div[contains(@class,'text-under-18')]//div[@class='icon-18']");
    public Label lblAge = Label.xpath("//div[contains(@class,'text-under-18')]//label[@class='text-18']");
    private Icon iconX = Icon.xpath("//div[@class='login-popup-content']//span[@class='close']/i");

    public void login(String username, String password, boolean skipByDefault) {
        txtUsername.waitForElementToBePresent(txtUsername.getLocator(), 2);
        if (!username.isEmpty()) {
            txtUsername.sendKeys(username);
        }
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        btnLogin.click();
        // waiting for loading
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



