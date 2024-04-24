package membersite.pages.camouflage;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import membersite.pages.BasePage;
import membersite.pages.HomePage;

public class Eu1010Page extends BasePage {
    protected String _type;
    private Label lblNavigateToLogin = Label.xpath("//span[text()='Origins of Wind Power']");
    private Label lblLogin = Label.xpath("//span[text()=',']");
    private TextBox txtUsername = TextBox.name("username");
    private TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//span[text()='Login']");
    public Eu1010Page(String types) {
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
