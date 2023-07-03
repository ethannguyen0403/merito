package backoffice.pages.bo.accountmanagement;

import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class ResetAccountPasswordPage extends HomePage {
    public TextBox txtLoginId = TextBox.name("username");
    public TextBox txtnewPassword = TextBox.name("new-password");
    public TextBox txtConfirmPassword = TextBox.name("confirm-password");
    public Button btnSubmit = Button.name("submit");
    public Label lblPasswordPolicy = Label.xpath("//div[@class='password-policy']");

    public void resetAccountPassword(String loginID, String newPassword, String confirmPassword) {
        txtLoginId.sendKeys(loginID);
        txtnewPassword.sendKeys(newPassword);
        txtConfirmPassword.sendKeys(confirmPassword);
        btnSubmit.click();
    }

}

