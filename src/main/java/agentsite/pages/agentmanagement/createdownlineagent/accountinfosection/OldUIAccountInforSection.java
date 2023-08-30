package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.TextBox;

public class OldUIAccountInforSection extends AccountInforSection {
    private String _xPath = "//div[@id='account']//app-agency-account-ui";
    private DropDownBox ddpLevel = DropDownBox.xpath(String.format("%s//tr[1]//td[8]//select", _xPath));
    private TextBox txtLoginID = TextBox.xpath("(//input[@name='lastName'])[1]");

    public void selectAgentLevel(String levelName) {
        ddpLevel.selectByVisibleText(levelName);
    }

    public void inputInfo(String loginID, String password, String accountStatus) {
        if (!loginID.isEmpty()) {
            txtLoginID.sendKeys(loginID);
        }
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        if (!accountStatus.isEmpty())
            ddrAccountStatus.selectByVisibleText(accountStatus);

    }

    public void inputInfo(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax) {
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        if (!accountStatus.isEmpty())
            ddrAccountStatus.selectByVisibleText(accountStatus);
        if (!firstName.isEmpty())
            txtFirstName.sendKeys(firstName);
        if (!lastName.isEmpty())
            txtLastName.sendKeys(lastName);
        if (!mobile.isEmpty())
            txtMobile.sendKeys(mobile);
    }

}
