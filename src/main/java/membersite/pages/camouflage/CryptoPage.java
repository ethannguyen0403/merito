package membersite.pages.camouflage;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import membersite.pages.BasePage;
import membersite.pages.HomePage;

public class CryptoPage extends BasePage {
    protected String _type;
    private Label lblNavigateToLogin = Label.xpath("//a[text()='How to own Bitcoin']");
    private Label lblLogin = Label.xpath("//font[text()='USD, Euros']");
    private TextBox txtUsername = TextBox.name("username");
    private TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//input[@value=' Login ']");
    public CryptoPage(String types) {
        _type = types;
    }

    public HomePage login(String username, String password) {
        lblNavigateToLogin.click();
        lblLogin.waitForElementToBePresent(lblLogin.getLocator(),1);
        lblLogin.click();
        txtUsername.waitForElementToBePresent(txtUsername.getLocator(), 1);
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return new HomePage(_type);
    }
}
