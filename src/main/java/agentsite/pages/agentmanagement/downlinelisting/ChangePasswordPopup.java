package agentsite.pages.agentmanagement.downlinelisting;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.Alert;

public class ChangePasswordPopup {

    public TextBox txtNewPassword = TextBox.xpath("//input[@name='newPassword']");
    public TextBox txtConfirmPassword = TextBox.xpath("//input[@name='confirmPassword']");
    public Button btnSubmit = Button.xpath("//button[@type='submit']");
    public Button btnCancel = Button.xpath("//button[contains(@class,'cancel')]");

    public String changePassword(String newPassword, String confirmPassword) throws InterruptedException {
        txtNewPassword.isDisplayed(5);
        if (!newPassword.isEmpty())
            txtNewPassword.sendKeys(newPassword);
        if(!confirmPassword.isEmpty())
            txtConfirmPassword.sendKeys(confirmPassword);
        btnSubmit.click();
        //waitingLoadingSpinner();
        Alert al = DriverManager.getDriver().switchToAlert();
        String message = al.getText();
        al.accept();
        return message;
    }
}
