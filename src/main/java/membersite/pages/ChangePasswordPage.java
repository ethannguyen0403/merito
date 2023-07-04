package membersite.pages;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class ChangePasswordPage {

    public Label lbltitle = Label.xpath("//form[contains(@class,'changePassForm')]//h3");
    public TextBox txtOldPassword = TextBox.id("oldPassword");
    public TextBox txtNewPassword = TextBox.id("newPassword");
    public TextBox txtConfirnmPassword = TextBox.id("confirmNewPassword");
    public Button btnChangePassword = Button.name("ChangePassword");
    private Button btnSkip = Button.xpath("//button[@name='Skip'] | //button[contains(@class,'btn-change-skip')]");

    /*
        public HomePage changePassword(String oldPw, String newPw)
        {
            txtOldPassword.sendKeys(oldPw);
            txtNewPassword.sendKeys(newPw);
            txtConfirnmPassword.sendKeys(newPw);
            btnChangePassword.click();
            HomePage hompage = new HomePage();
            hompage.waitMenuLoading();
            return hompage;
        }*/
    public void skip() {
        btnSkip.isDisplayedShort(5);
        btnSkip.click();
    }


}
