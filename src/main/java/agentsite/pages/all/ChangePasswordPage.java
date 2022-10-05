package agentsite.pages.all;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import agentsite.pages.all.components.BasePage;

public class ChangePasswordPage extends BasePage {
    public TextBox txtCurrentPassword = TextBox.id("oldPassword");
    public TextBox txtNewPassword = TextBox.id("newPassword");
    public TextBox txtConfirmPassword = TextBox.id("confirmPassword");

    public Button btnSubmit = Button.xpath("//table[contains(@class,'login-table')]//span[text()='Submit']");

    public void changePassword(String oldPassword, String newPassword, String confirmPassword)
    {
        if(txtCurrentPassword.isDisplayed()){
            txtCurrentPassword.sendKeys(oldPassword);
            txtNewPassword.sendKeys(newPassword);
            txtConfirmPassword.sendKeys(confirmPassword);
            btnSubmit.click();
            waitingLoadingSpinner();
        }
    }

}
