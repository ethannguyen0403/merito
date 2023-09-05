package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class AccountInforSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Label lblTitlePage = Label.xpath("//div[@class='title']//label");
    public TextBox txtPassword = TextBox.xpath("//input[@name='password']");
    public DropDownBox ddrAccountStatus = DropDownBox.xpath("//select[@name='status']");
    public TextBox txtFirstName = TextBox.xpath("//input[@name='firstName']");
    public TextBox txtLastName = TextBox.xpath("//input[@name='lastName']");
    public TextBox txtPhone = TextBox.xpath("//input[@name='phone']");
    public TextBox txtMobile = TextBox.xpath("//input[@name='mobile']");
    public TextBox txtFax = TextBox.xpath("//input[@name='fax']");

    public void selectAgentLevel(String levelName) {

    }
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public void inputInfo(String loginID, String password, String accountStatus) {
    }
    public void inputInfo(String password, String accountStatus) {}

    public void inputInfo(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax) {
    }

    public String getUserName() {return "";}
    public String getAccountInforSectionTitle() { return lblTitlePage.getText();}
}
