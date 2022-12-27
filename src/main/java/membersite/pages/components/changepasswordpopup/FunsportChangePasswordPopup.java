package membersite.pages.components.changepasswordpopup;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;
import common.MemberConstants;

public class FunsportChangePasswordPopup extends ChangePasswordPopup{
    public Popup popupChangePassword = Popup.xpath("//div[@id='my-change-pass']");
    public Label lblTitle = Label.xpath("//div[@class='dialog-header']//span[@class='title']");
    private Button btnClose = Button.xpath("//div[@class='dialog-header']//span[@class='close-dialog']");
    private Button btnCancel = Button.xpath("//div[@id='my-change-pass']//button[@id='cancel-edit-about-you']");
    private Button btnSaveChange = Button.xpath("//div[@id='my-change-pass']//button[@id='save-edit-about-you']");
    private TextBox txtNewPassword = TextBox.id("newPassword");
    private TextBox txtConfirmPassword = TextBox.id("confirmNewPassword");
    private TextBox txtOldPassword = TextBox.id("oldPassword");
    public Label lblSuccessMsg = Label.xpath("//div[@class='successMsg']");
    public Label lblErrorMsg = Label.xpath("//div[contains(@class,'oldPassword-container')]//label[@class='errorMsg']");
    private Label lblValidateNewPasswordErrorMsg = Label.xpath("//div[contains(@class,'newPassword-container')]//label[@class='errorMsg']");
    private Label lblConfirmPasswordErrorMsg = Label.xpath("//div[contains(@class,'confirmNewPassword-container')]//label[@class='errorMsg']");

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
    public void inputChangePassword( String newPassword, String confirmPassword,String oldPassword){
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
    public boolean isDisplayed(){
        return lblTitle.isDisplayed();
    }
}
