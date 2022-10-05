package backoffice.pages.bo.adminmanagement.component;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.TextBox;

public class UserPopup{
    TextBox txtLoginId = TextBox.xpath("//app-edit-admin//span[text()='Login Id']/parent::div/following-sibling::input");
    TextBox txtName = TextBox.xpath("//app-edit-admin//span[text()='Name']/parent::div/following-sibling::input");
    TextBox txtEmail = TextBox.xpath("//app-edit-admin//span[text()='Email']/parent::div/following-sibling::input");
    TextBox txtPassword = TextBox.xpath("//app-edit-admin//span[text()='Password']/parent::div/following-sibling::input");
    TextBox txtConfirmPassword = TextBox.xpath("//app-edit-admin//span[text()='Confirm Password']/parent::div/following-sibling::input");
    DropDownBox ddbStatus = DropDownBox.xpath("//app-edit-admin//span[text()='Status']/parent::div/following-sibling::select");
    public Button btnClose = Button.xpath("//app-edit-admin//button[@class='btn btn-sm btn-outline-secondary mr-2']");
    public Button btnSaveChanges = Button.xpath("//app-edit-admin//button[@class='btn btn-sm btn-core']");

     public void inputData(String loginID, String name, String email, String password, String confirmPassword, String status){
         if(!loginID.isEmpty())
             txtLoginId.sendKeys(loginID);
         if(!name.isEmpty())
             txtName.sendKeys(name);
         if(!email.isEmpty())
             txtEmail.sendKeys(email);
         if(!password.isEmpty())
             txtPassword.sendKeys(password);
         if(!confirmPassword.isEmpty())
             txtConfirmPassword.sendKeys(confirmPassword);
         if(!status.isEmpty())
             ddbStatus.selectByVisibleText(status);
        btnSaveChanges.click();
        System.out.println("Create account success: "+loginID);
    }
}
