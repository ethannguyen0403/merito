package agentsite.pages.all.home;

import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import agentsite.pages.all.components.BasePage;
import agentsite.pages.all.components.DialogPopup;

import java.util.List;

public class LoginPage extends BasePage {
    public Icon iconLogo = Icon.xpath("//span[@class='applogo']");
    public Label lblLogin = Label.xpath("//app-login//div[contains(@class,'login-title')]");
    public TextBox txtUsername = TextBox.xpath("//app-login//input[@name='username']");
    public TextBox txtPassword = TextBox.xpath("//app-login//input[@name='password']");
    public Button btnLogIn = Button.xpath("//app-login//button[contains(@class,'btn-login')]");
    public TextBox txtCaptcha = TextBox.id("//app-login//input[contains(@class,'captcha')]");
    public Table tblFormLogin = Table.xpath("//app-login//table", 2);

    public void fillLoginForm(String userName, String password, String captcha, boolean isSubmit) {
        if (!userName.isEmpty())
            txtUsername.sendKeys(userName);
        if (!password.isEmpty())
            txtPassword.sendKeys(password);
        if (!captcha.isEmpty())
            txtCaptcha.sendKeys(captcha);
        if (isSubmit)
            btnLogIn.click();
    }

    public List<String> getLabels() {
        return tblFormLogin.getColumn(1, false);
    }

    public DialogPopup loginWitInvalidInfo(String userName, String password, String captcha, boolean isSubmit) {
        fillLoginForm(userName, password, captcha, true);
        return new DialogPopup();
    }

}
