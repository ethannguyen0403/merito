package membersite.pages.funsport.home.popups;

import com.paltech.element.common.*;
import membersite.pages.all.tabexchange.HomePage;

public class FairenterLoginPopup {
    public Popup popupLogin = Popup.xpath("//div[@class='login-popup-content']");
    private Icon iconX = Icon.xpath("//div[@class='login-popup-content']//span[@class='close']/i");
    public Label lblTitle = Label.xpath("//div[@class='login-popup-content']//b");
    public Label lblErrorMessage = Label.xpath("//div[@class='message-error']");
    public Label lblUsername = Label.xpath("//div[@class='login-popup-content']//input[@id='username']/parent::div/span[1]");
    public Label lblPassword = Label.xpath("//div[@class='login-popup-content']//input[@id='pword']/parent::div/span[1]");
    public Label lblRememberPassword = Label.xpath("//label[@for='frmLoginRemember']");
    public Label lblShowPassword = Label.xpath("//div[@class='login-popup-content']//input[@id='pword']/parent::div/span[2]");
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    public Button btnLogin = Button.xpath("//input[@class='btn btn-login btn-sm']");

    public void clickXIcon(){
        iconX.click();
    }

    public HomePage login(String username, String password){
        if(!username.isEmpty()){
            txtUsername.sendKeys(username);
        }
        if(!password.isEmpty()){
            txtPassword.sendKeys(password);
        }
        btnLogin.click();
        // waiting for loading
        btnLogin.isInvisible(2);
        return new HomePage();
    }

}
