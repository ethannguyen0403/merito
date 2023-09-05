package agentsite.pages.agentmanagement.createdownlineagent;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountInfoSection extends BaseElement {
    public Table tblAccountInfo = Table.xpath("//table[contains(@class,'ptable info account-table')]", 8);
    public TextBox txtLoginID = TextBox.xpath("(//input[@name='lastName'])[1]");
    public Label lblLoginIDHint = Label.xpath("(//input[@name='lastName'])[1]/following::span[@class='pinfo'][1]");
    public Label lblPasswordHint = Label.xpath("//input[@name='password']/following::span[@class='pinfo'][1]");
    String ddpUsernameCharXPath = String.format("//select[@name='userNameChar']");
    private String _xPath = "//div[@id='account']//app-agency-account-ui";
    String listLable = String.format("%s//td[contains(@class,'label')]", _xPath);
    String listEditDownlineLabel = String.format("%s//div[contains(@class,'column header')]", _xPath);
    String lblUsernameCharXpath = String.format("%s//div[contains(@class,'column data')][1]/span/span", _xPath);
    public Label lblUsernamePrefix = Label.xpath(String.format("%s//span[@id='username-prefix']", _xPath));
    //public DropDownBox ddpUsernameChar = DropDownBox.xpath(String.format("%s//div[contains(@class,'column data')][1]/span/span",_xPath));
    // public Label lblUsernameChar = Label.xpath(String.format("%s//div[contains(@class,'column data')][1]/span/span",_xPath));
    public DropDownBox ddpUsernamesecondChar = DropDownBox.xpath(String.format("%s//div[contains(@class,'column data')][1]/span/span[2]//select[1]", _xPath));
    public Icon iconUserCodStatus = Icon.xpath(String.format("%s//input[@name='usercode-status']", _xPath));
    public TextBox txtPassword = TextBox.xpath(String.format("%s//input[@name='password']", _xPath));
    public DropDownBox ddrAccountStatus = DropDownBox.xpath(String.format("%s//select[@name='status']", _xPath));
    public DropDownBox ddrAccountStatusSAT = DropDownBox.xpath(String.format("%s//select[@class='full-width ng-pristine ng-valid ng-touched']", _xPath));
    public TextBox txtFirstName = TextBox.xpath(String.format("%s//input[@name='firstName']", _xPath));
    public TextBox txtLastName = TextBox.xpath(String.format("%s//input[@name='lastName']", _xPath));
    public TextBox txtPhone = TextBox.xpath(String.format("%s//input[@name='phone']", _xPath));
    public TextBox txtMobile = TextBox.xpath(String.format("%s//input[@name='mobile']", _xPath));
    public DropDownBox ddpLevel = DropDownBox.xpath(String.format("%s//div[contains(@class,'column data')][4]//select", _xPath));
    // public Label lblPasswordHint = Label.xpath(String.format("%s//span[@class='pinfo']",_xPath));
    public TextBox txtFax = TextBox.xpath(String.format("%s//input[@name='fax']", _xPath));
    public Label lblBaseCurrencyValue = Label.xpath(String.format("%s//div[contains(@class,'column data')][10]", _xPath));
    public CheckBox cbAllowExtraPT = CheckBox.xpath(String.format("%s//input[@name='allowAutoPT']", _xPath));

    public AccountInfoSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
    }

    public static AccountInfoSection xpath(String xpathExpression) {
        return new AccountInfoSection(By.xpath(xpathExpression), xpathExpression);
    }

    public List<String> getListLabelInfo() {
        List<String> lstInfo = new ArrayList<>();
        Label lblInfo = Label.xpath(listLable);
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

    public void selectUserName(String userName, String prefix) {
        String bb = userName.split(prefix)[1];
        bb.split("0");
    }

//    public void inputInfo(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax) {
//        if (!password.isEmpty()) {
//            txtPassword.sendKeys(password);
//        }
//        if (!accountStatus.isEmpty())
//            ddrAccountStatus.selectByVisibleText(accountStatus);
//        if (!firstName.isEmpty())
//            txtFirstName.sendKeys(firstName);
//        if (!phone.isEmpty())
//            txtPhone.sendKeys(phone);
//        if (!lastName.isEmpty())
//            txtLastName.sendKeys(lastName);
//        if (!mobile.isEmpty())
//            txtMobile.sendKeys(mobile);
//        if (!fax.isEmpty())
//            txtFax.sendKeys(fax);
//    }

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
}
