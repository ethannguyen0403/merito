package backoffice.pages.bo.home;

import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class LoginPage {
    public Icon iconLogo = Icon.id("logo");
    public Label lblLoginToYourAccount = Label.xpath("//div[@id='login']//h1");
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    public TextBox txtCaptcha = TextBox.name("captcha");
    Button btnLogIn = Button.name("commit");
}
