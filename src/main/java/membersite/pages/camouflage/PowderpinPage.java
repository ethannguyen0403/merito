package membersite.pages.camouflage;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import membersite.pages.BasePage;
import membersite.pages.HomePage;

public class PowderpinPage extends BasePage {
    protected String _type;
    private Button btnConfirm = Button.xpath("//div[@class='button-login']//span[text()='Confirm']");
    private TextBox txtUsername = TextBox.name("username");
    private TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.id("login-btn-popup");
    public PowderpinPage(String types) {
        _type = types;
    }

    public HomePage login(String username, String password) {
        btnConfirm.click();
        txtUsername.waitForElementToBePresent(txtUsername.getLocator(), 1);
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return new HomePage(_type);
    }
}
