package membersite.pages.funsport.home.popups;

import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;
import membersite.pages.all.home.ChangePasswordPage;
import membersite.pages.all.tabexchange.HomePage;

public class LoginPopup {
    public Popup popupLogin = Popup.xpath("//div[@class='login-popup-content']");
    private Icon iconX = Icon.xpath("//div[@class='login-popup-content']//span[@class='close']/i");
    public Label lblTitle = Label.xpath("//div[@class='login-popup-content']//b");
    public Label lblErrorMessage = Label.xpath("//div[@class='message-error']");
    public Label lblUsername = Label.xpath("//div[@class='login-popup-content']//input[@id='username']/parent::div/span[1]");
    public Label lblPassword = Label.xpath("//div[@class='login-popup-content']//input[@id='pword']/parent::div/span[1]");
    public Label lblRememberPassword = Label.xpath("//div[@class='login-popup-content']//input[@id='username']/parent::div/span[2]");
    public Label lblShowPassword = Label.xpath("//div[@class='login-popup-content']//input[@id='pword']/parent::div/span[2]");
    public TextBox txtUsername = TextBox.id("username");
    public TextBox txtPassword = TextBox.id("pword");
    public Button btnLogin = Button.xpath("//button[@id='login-btn-popup']/span[1]");

    public void clickXIcon(){
        iconX.click();
    }

    public HomePage login(String username, String password, boolean skipByDefault){
        if(!username.isEmpty()){
            txtUsername.sendKeys(username);
        }
        if(!password.isEmpty()){
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
        return new HomePage();
    }

}
