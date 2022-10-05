package backoffice.pages.bo.adminmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.TextBox;
import backoffice.pages.bo.home.HomePage;

public class AdminProfilePage extends HomePage {
    public TextBox txtLoginID = TextBox.name("loginId");
    public TextBox txtUserCode  = TextBox.name("usercode");
    public TextBox txtName = TextBox.name("fullName");
    public TextBox txtEmail = TextBox.name("email");
    public TextBox txtCurrentPassword = TextBox.name("currentPassword");
    public TextBox txtPassword = TextBox.name("password");
    public TextBox txtConfirmPassword = TextBox.name("confirmPassword");
    public Button btnSave = Button.xpath("//button[@class='btn btn-warning float-right']");

    public void editProfile(String name, String email, String currentPassword, String password, String confirmPassword){
        if(!name.isEmpty())
            txtName.sendKeys(name);
        if(!email.isEmpty())
            txtEmail.sendKeys(email);
        if(!currentPassword.isEmpty())
            txtCurrentPassword.sendKeys(currentPassword);
        if(!password.isEmpty())
            txtPassword.sendKeys(password);
        if(!confirmPassword.isEmpty())
            txtConfirmPassword.sendKeys(confirmPassword);
        btnSave.click();
    }


}
