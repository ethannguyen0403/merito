package agentsite.pages.components;

import com.paltech.element.common.*;

public class UserProfilePopup {
    public Label lblTitle = Label.xpath("//app-user-profile//div[contains(@class,'modal-header')]/div");
    public Icon icClose = Icon.xpath("//app-user-profile//button[@class='close']");
    public TextBox txtNewPassword = TextBox.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][1]//input");
    public TextBox txtConfirmPassword = TextBox.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][2]//input");
    public TextBox txtFirstName = TextBox.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][3]//input");
    public TextBox txtLastName = TextBox.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][4]//input");
    public TextBox txtMobile = TextBox.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][6]//input");
    public TextBox txtPhone = TextBox.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][5]//input");
    public TextBox txtFax = TextBox.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][7]//input");
    public Label lblNewPassword = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][1]//label");
    public Label lblConfirmPassword = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][2]//label");
    public Label lblFirstName = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][3]//label");
    public Label lblLastName = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][4]//label");
    public Label lblMobile = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][5]//label");
    public Label lblPhone = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][6]//label");
    public Label lblFax = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][7]//label");
    public Button btnSave = Button.xpath("//app-user-profile//button[contains(@class,'btn-submit')]");
    public Button btnCancle = Button.xpath("//app-user-profile//button[contains(@class,'btn-cancle')]");
    public Label lblMessageUpdate = Label.xpath("//app-user-profile//div[@class='setting-user-profile']//div[contains(@class,'form-group')][8]//p");

    public String updateProfile(String newPassword, String confirmPassword, String firstName, String lastName, String mobile, String phone, String fax,boolean isClose){
        if(!newPassword.isEmpty())
            txtNewPassword.sendKeys(newPassword);
        if(!confirmPassword.isEmpty())
            txtConfirmPassword.sendKeys(confirmPassword);
        if(!firstName.isEmpty())
            txtFirstName.sendKeys(firstName);
        if(!lastName.isEmpty())
            txtLastName.sendKeys(lastName);
        if(!mobile.isEmpty())
            txtMobile.sendKeys(mobile);
        if(!phone.isEmpty())
            txtPhone.sendKeys(phone);
        if(!fax.isEmpty())
            txtFax.sendKeys();
        btnSave.click();
        String message = lblMessageUpdate.getText();
        if(isClose){
            icClose.click();
        }
        return message;
    }
}
