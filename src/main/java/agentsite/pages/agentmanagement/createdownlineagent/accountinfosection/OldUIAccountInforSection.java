package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import com.paltech.element.common.*;

public class OldUIAccountInforSection extends AccountInforSection {
    private String _xPath = "//div[@id='account']//app-agency-account-ui";
    private DropDownBox ddpLevel = DropDownBox.xpath(String.format("%s//tr[1]//td[8]//select",_xPath));
    private TextBox txtLoginID = TextBox.xpath("(//input[@name='lastName'])[1]");
    public void selectAgentLevel(String levelName) {
        ddpLevel.selectByVisibleText(levelName);
    }

    public void inputInfo(String loginID, String password, String accountStatus)
    {
        if(!loginID.isEmpty()){
            txtLoginID.sendKeys(loginID);
        }
        if(!password.isEmpty()){
            txtPassword.sendKeys(password);
        }
        if(!accountStatus.isEmpty())
            ddrAccountStatus.selectByVisibleText(accountStatus);

    }

}
