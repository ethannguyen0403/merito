package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import agentsite.pages.components.SecurityPopup;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.utils.StringUtils;

import static baseTest.BaseCaseTest.environment;

public class NewUIAccountInforSection extends AccountInforSection {
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    private String _xPath = "//div[@id='account']//app-agency-account-ui";
    private DropDownBox ddpLevel = DropDownBox.xpath(String.format("%s//div[contains(@class,'column data')][4]//select", _xPath));
    private Label lblUsernamePrefix
            = Label.xpath(String.format("%s//span[@id='username-prefix']", _xPath));
    private String lblUsernameCharXpath
            = String.format("%s//div[contains(@class,'column data')][1]/span/span", _xPath);
    private String ddpUsernameCharXPath = String.format("//select[@name='userNameChar']");

    public void selectAgentLevel(String levelName) {
        if (securityPopup.isDisplayed()) {
            try {
                securityPopup.submitSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            waitingLoadingSpinner();
        }
        ddpLevel.selectByVisibleText(levelName);
    }

    public void inputInfo(String password, String accountStatus) {
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        if (!accountStatus.isEmpty())
            ddrAccountStatus.selectByVisibleText(accountStatus);

    }

    public String getUserName() {
        String username = lblUsernamePrefix.getText();
        Label lblUsernameChar = Label.xpath(lblUsernameCharXpath);
        DropDownBox ddpUsernameChar;
        int total = lblUsernameChar.getWebElements().size();
        if (total != 0) {
            for (int i = 0; i < total; i++) {
                ddpUsernameChar = DropDownBox.xpath(String.format("%s[%s]%s", lblUsernameCharXpath, i + 1, ddpUsernameCharXPath));
                if (ddpUsernameChar.isDisplayed()) {
                    username = username + ddpUsernameChar.getFirstSelectedOption().trim();
                }
            }
            return username;
        }
        System.out.println("There is no Username dropdown display");
        return null;
    }
}
