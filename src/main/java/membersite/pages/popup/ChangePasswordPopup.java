package membersite.pages.popup;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;
import common.MemberConstants;

public class ChangePasswordPopup {
    public Popup popupChangePassword = Popup.xpath("//div[@class='modal-content']/app-change-password");
    public Label lblTitle = Label.xpath("//div[@class='modal-header']//h4");
    private Button btnClose = Button.xpath("//div@class='modal-header']//button");
    private Button btnCancel = Button.xpath("//app-change-password//button[@class='btn btn-default']");
    private Button btnSaveChange = Button.xpath("//button[@class='btn btn-primary']");
    private TextBox txtNewPassword = TextBox.id("password-input");
    private TextBox txtConfirmPassword = TextBox.id("password-confirmation-input");
    private TextBox txtOldPassword = TextBox.id("current-password-input");
    private Label lblSuccessMsg = Label.xpath("//span[@class='text-success']");
    private Label lblErrorMsg = Label.xpath("//span[@class='text-danger']");
    private Label lblValidateNewPasswordErrorMsg = Label.xpath("//input[@id='password-input']/following::div[@class='text-danger'][1]");
    private Label lblConfirmPasswordErrorMsg = Label.xpath("//input[@id='password-confirmation-input']/following::div[@class='text-danger'][1]");

    public void clickCancelBtn(){
        btnCancel.click();
        btnCancel.isInvisible(1);
    }
    public void clickSaveChangeBtn(){
        btnSaveChange.click();
    }

    public String changePassword(String oldPassword, String newPassword, String confirmPassword){
       inputChangePassword(oldPassword,newPassword,confirmPassword);
       clickSaveChangeBtn();
       lblSuccessMsg.isTextDisplayed(MemberConstants.ChangePasswordPopup.MSG_SUCCESS,3);
       return lblSuccessMsg.getText();
    }
    public void inputChangePassword(String oldPassword, String newPassword, String confirmPassword){
        txtNewPassword.type(newPassword);
        txtConfirmPassword.type(confirmPassword);
        txtOldPassword.type(oldPassword);
    }

    public String getErrorMsg ()
    {
        return lblErrorMsg.getText();
    }

    public String getNewPasswordErrorMsg ()
    {
        return lblValidateNewPasswordErrorMsg.getText();
    }
    public String getConfirmPasswordErrorMsg ()
    {
        return lblConfirmPasswordErrorMsg.getText();
    }

    public void closePopup(){
        btnClose.click();
    }

    public String getTitle(){
        return lblTitle.getText();
    }
}
