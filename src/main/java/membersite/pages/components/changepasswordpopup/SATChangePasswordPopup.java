package membersite.pages.components.changepasswordpopup;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;
import common.MemberConstants;

public class SATChangePasswordPopup extends ChangePasswordPopup {
    public Popup popupChangePassword = Popup.xpath("//div[@class='modal-content']/app-change-password");
    public Label lblTitle = Label.xpath("//app-change-password//div[@class='modal-header']//h4");
    private Button btnClose = Button.xpath("//app-change-password//div[@class='modal-header']//button");
    private Button btnCancel = Button.xpath("//app-change-password//button[@class='btn btn-default']");
    private Button btnSaveChange = Button.xpath("//app-change-password//button[contains(@class,'btn-change-password')]");
    private TextBox txtNewPassword = TextBox.id("password-input");
    private TextBox txtConfirmPassword = TextBox.id("password-confirmation-input");
    private TextBox txtOldPassword = TextBox.id("current-password-input");
    private Label lblSuccessMsg = Label.xpath("//app-change-password//span[@class='text-success']");
    private Label lblValidateNewPasswordErrorMsg = Label.xpath("//input[@id='password-input']/following::div[@class='text-danger'][1]/span");
    private Label lblConfirmPasswordErrorMsg = Label.xpath("//input[@id='password-confirmation-input']/following::div[@class='text-danger'][1]");
    private Label lblOldPasswordErrorMsg = Label.xpath("//input[@id='current-password-input']/following::span[@class='text-danger'][1]");


    public void clickCancelBtn() {
        btnCancel.click();
        btnCancel.isInvisible(1);
    }

    public void clickSaveChangeBtn() {
        btnSaveChange.click();
    }

    public String changePassword(String newPassword, String confirmPassword, String oldPassword) {
        inputChangePassword(newPassword, confirmPassword, oldPassword);
        clickSaveChangeBtn();
        lblSuccessMsg.isTextDisplayed(MemberConstants.ChangePasswordPopup.MSG_SUCCESS, 3);
        return lblSuccessMsg.getText();
    }

    public void inputChangePassword(String newPassword, String confirmPassword, String oldPassword) {
        txtNewPassword.type(newPassword);
        txtConfirmPassword.type(confirmPassword);
        txtOldPassword.type(oldPassword);
    }

    public String getErrorMsg() {
        return lblOldPasswordErrorMsg.getText();
    }

    public String getNewPasswordErrorMsg() {
        return lblValidateNewPasswordErrorMsg.getText();
    }

    public String getConfirmPasswordErrorMsg() {
        return lblConfirmPasswordErrorMsg.getText();
    }

    public String getCurrentPasswordError() {
        return lblOldPasswordErrorMsg.getText();
    }

    public String getNewPasswordError() {
        return lblValidateNewPasswordErrorMsg.getText();
    }

    public String getConfirmMessageError() {
        return lblConfirmPasswordErrorMsg.getText();
    }

    public void closePopup() {
        btnClose.click();
        btnClose.isInvisible(1);
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    public boolean isDisplayed() {
        return lblTitle.isDisplayed();
    }
}
