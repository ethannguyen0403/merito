package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class AccountInforSection {
    public Label createDownlineAgent = Label.xpath("//label[text()='Create Downline Agent']");
    public TextBox txtPassword = TextBox.xpath("//input[@name='password']");
    public DropDownBox ddrAccountStatus = DropDownBox.xpath("//select[@name='status']");
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void selectAgentLevel(String levelName) {

    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public void inputInfo(String loginID, String password, String accountStatus) {
    }

    public void inputInfo(String password, String accountStatus) {
    }

    public String getUserName() {
        return "";
    }

    public String getAccountInforSectionTitle() {
        return createDownlineAgent.getText();
    }
}
