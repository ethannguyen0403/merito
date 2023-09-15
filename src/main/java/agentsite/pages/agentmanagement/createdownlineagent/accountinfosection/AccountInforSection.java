package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import agentsite.controls.Table;
import com.paltech.element.common.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AccountInforSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    protected String _xPath = "//div[@id='account']//app-agency-account-ui";
    protected CheckBox cbCreditCash = CheckBox.xpath(String.format("%s//input[@name='creditcash']", _xPath));
    public Label lblTitlePage = Label.xpath("//div[@class='title']//label");
    public TextBox txtPassword = TextBox.xpath("//input[@name='password']");
    public DropDownBox ddrAccountStatus = DropDownBox.xpath("//select[@name='status']");
    public TextBox txtFirstName = TextBox.xpath("//input[@name='firstName']");
    public TextBox txtLastName = TextBox.xpath("//input[@name='lastName']");
    public TextBox txtPhone = TextBox.xpath("//input[@name='phone']");
    public TextBox txtMobile = TextBox.xpath("//input[@name='mobile']");
    public TextBox txtFax = TextBox.xpath("//input[@name='fax']");
    public Table tblAccountInfo = Table.xpath("//table[contains(@class,'ptable info account-table')]", 8);
    public Label lblPasswordHint = Label.xpath("//input[@name='password']/following::span[@class='pinfo'][1]");
    String listEditDownlineLabel = String.format("%s//div[contains(@class,'column header')]", _xPath);

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

    public void verifyUIDisplayedCorrect() {};

    public List<String> getListLabelInfo() {return Collections.singletonList("");};

    public List<String> getListLabelEditDownlineInfo() {
        List<String> lstInfo = new ArrayList<>();
        Label lblInfo = Label.xpath(listEditDownlineLabel);
        if (Objects.isNull(lblInfo)) {
            System.out.println("Cannot get all label in account info section");
            return null;
        }
        List<WebElement> lsElement = lblInfo.getWebElements();
        for (int i = 0; i < lsElement.size(); i++) {
            lstInfo.add(lsElement.get(i).getText().trim());
        }
        return lstInfo;
    }

    public void selectCurrency(String currency) {};

}
