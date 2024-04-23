package membersite.pages.camouflage;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import membersite.pages.BasePage;
import membersite.pages.HomePage;

public class AtlanticPage extends BasePage {
    protected String _type;
    private Label lblNavigateToLogin = Label.xpath("//header//a[text()='CONSERVATION OF TUNA ']");
    private Label lblLogin = Label.xpath("//app-tuna-conservation//table//p//font[text()=' Atlantic ']");
    private TextBox txtUsername = TextBox.name("username");
    private TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//input[@value=' LOGIN ']");
    public AtlanticPage(String types) {
        _type = types;
    }

    public HomePage login(String username, String password) {
        lblNavigateToLogin.click();
        lblLogin.waitForElementToBePresent(lblLogin.getLocator(), 1);
        lblLogin.click();
        txtUsername.waitForElementToBePresent(txtUsername.getLocator(), 1);
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return new HomePage(_type);
    }
}
