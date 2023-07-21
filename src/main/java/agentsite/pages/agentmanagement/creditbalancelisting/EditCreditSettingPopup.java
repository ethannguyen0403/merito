package agentsite.pages.agentmanagement.creditbalancelisting;

import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.Keys;

public class EditCreditSettingPopup extends ConfirmPopup {
    Button btnSubmit = Button.id("submitBtn");
    private Label lblCreditGiven = Label.xpath("//app-edit-credit-dialog//table//tr[1]//td[1]");
    private Label lblMaxCredit = Label.xpath("//app-edit-credit-dialog//table//tr[2]//td[1]");
    private Label lblMemberMaxCredit = Label.xpath("//app-edit-credit-dialog//table//tr[3]//td[1]");
    private TextBox txtCreditGiven = TextBox.xpath("//app-edit-credit-dialog//table//tr[@class='creditGiven']//td[2]//input");
    private TextBox txtMaxCredit = TextBox.xpath("//app-edit-credit-dialog//table//tr[@class='maxCredit ng-star-inserted']//td[2]//input");
    private TextBox txtMemberMaxCredit = TextBox.xpath("//app-edit-credit-dialog//table//tr[@class='playerCredit ng-star-inserted']//td[2]//input");
    private Label lblCreditGivenLimit = Label.xpath("//app-edit-credit-dialog//table//tr[1]//td[3]");
    private Label lblMaxCreditLimit = Label.xpath("//app-edit-credit-dialog//table//tr[2]//td[3]");
    private Label lblMemberMaxCreditLimit = Label.xpath("//app-edit-credit-dialog//table//tr[3]//td[3]");

    public void editCreditSetting(String creditGiven, String maxCredit, String memberMaxCredit) {
        if (!creditGiven.isEmpty()) {
            txtCreditGiven.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
            txtCreditGiven.sendKeys(creditGiven);
        }
        if (!maxCredit.isEmpty()) {
            txtMaxCredit.sendKeys(maxCredit);
        }
        if (!memberMaxCredit.isEmpty()) {
            txtMemberMaxCredit.sendKeys(memberMaxCredit);
        }
        btnSubmit.click();
    }

}
