package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.components.SecurityPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.environment;

public class FunUIAccountInforSection extends AccountInforSection {
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");

    private DropDownBox ddpLevel = DropDownBox.xpath(String.format("%s//tr[2]//td[4]//select", _xPath));
    private Label lblUsernamePrefix
            = Label.xpath(String.format("%s//span[@id='username-prefix']", _xPath));
    private String lblUsernameCharXpath
            = String.format("%s//tr[1]//td[2]/span/span", _xPath);
    private String ddpUsernameCharXPath = String.format("//select[@name='userNameChar']");
    public DropDownBox ddrAccountStatus = DropDownBox.xpath("//select[@name='status']");
    String listLabel = String.format("%s//td[contains(@class,'label')]", _xPath);
    private Label lblBaseCurrencyValue = Label.xpath(String.format("%s//div[contains(@class,'column data')][10]", _xPath));
    private CheckBox cbAllowExtraPT = CheckBox.xpath(String.format("%s//input[@name='allowAutoPT']", _xPath));
    private CheckBox cbAllowCashout = CheckBox.xpath(String.format("%s//input[@name='allowCashOut']", _xPath));

    //Controls in Create Company
    private DropDownBox ddbCurrency = DropDownBox.xpath(String.format("%s//div[contains(@class,'column data')][9]//select", _xPath));
    Label lblTax = Label.xpath("//td[text()='Fax']");
    private TextBox txtLoginID = TextBox.xpath("(//input[@name='lastName'])[1]");
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
    public void inputInfo(String loginID, String password, String accountStatus) {
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        if (!accountStatus.isEmpty())
            ddrAccountStatus.selectByVisibleText(org.apache.commons.lang3.StringUtils.capitalize(accountStatus.toLowerCase()));

    }

    public void inputInfo(String password, String accountStatus) {
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
        if (!phone.isEmpty())
            txtPhone.sendKeys(phone);
        if (!lastName.isEmpty())
            txtLastName.sendKeys(lastName);
        if (!mobile.isEmpty())
            txtMobile.sendKeys(mobile);
        if (!fax.isEmpty())
            txtFax.sendKeys(fax);
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

    public List<String> getListLabelInfo() {
        List<String> lstInfo = new ArrayList<>();
        Label lblInfo = Label.xpath(listLabel);
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
    public void verifyUIDisplayedCorrect() {
        AccountInfo accountInfo = ProfileUtils.getProfile();
        List<String> lstInfo = getListLabelInfo();
        if(accountInfo.getLevel().equalsIgnoreCase("PO")) {
            Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.LBL_USERNAME, "FAILED! Username label display incorrect");
            Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
            Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
            Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
            Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
            Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_PHONE, "FAILED! Phone display incorrect");
            Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
            Assert.assertEquals(lstInfo.get(7), AGConstant.AgencyManagement.CreateAccount.LBL_FAX, "FAILED! Fax display incorrect");
            Assert.assertEquals(lstInfo.get(8), AGConstant.AgencyManagement.CreateAccount.LBL_BASE_CURRENCY, "FAILED! Base Currency display incorrect");
            Assert.assertEquals(lstInfo.get(9), AGConstant.AgencyManagement.CreateCompany.LBL_ALLOW_CASHOUT, "FAILED! Allow Cashout label display incorrect");
            Assert.assertEquals(lstInfo.get(10), AGConstant.AgencyManagement.CreateCompany.LBL_ALLOW_CO_EXTRA, "FAILED! Allow Extra display incorrect");
            Assert.assertEquals(lstInfo.get(11), AGConstant.AgencyManagement.CreateCompany.LBL_IS_CREDIT_CASH, "FAILED! Credit Cash label display incorrect");
            Assert.assertTrue(ddbCurrency.isDisplayed(), "FAILED! Base Currency dropdown box does not display");
            Assert.assertTrue(cbAllowCashout.isDisplayed(), "FAILED! Allow Cashout checkbox does not display");
            Assert.assertTrue(cbCreditCash.isDisplayed(), "FAILED! Credit Cash checkbox does not display");
        } else {
            Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.LBL_USERNAME, "FAILED! Login ID label display incorrect");
            Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
            Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
            Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_LEVEL, "FAILED! Level label display incorrect");
            Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
            Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
            Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_PHONE, "FAILED! Phone display incorrect");
            Assert.assertEquals(lstInfo.get(7), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
            Assert.assertEquals(lstInfo.get(8), AGConstant.AgencyManagement.CreateAccount.LBL_BASE_CURRENCY, "FAILED! Base Currency display incorrect");
            Assert.assertEquals(lblTax.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_FAX, "FAILED! Fax display incorrect");
            Assert.assertTrue(ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        }
        Assert.assertTrue(txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");
        Assert.assertTrue(txtFax.isDisplayed(), "FAILED! Tax textbox does not display");
    }

    public void selectCurrency(String currency) {
        if(ddbCurrency.isDisplayed()) {
            ddbCurrency.selectByVisibleText(currency);
            waitingLoadingSpinner();
        }
    }

    public boolean isAccountStatusDropdownLoadCorrect(List<String> lstStatus) {
        return ddrAccountStatus.areOptionsMatched(lstStatus);
    }

    public void selectAccountStatus(String status) {
        ddrAccountStatus.selectByVisibleText(status);
    }

    @Override
    public String getAccountStatus() {
     return ddrAccountStatus.getFirstSelectedOption();
    }

    @Override
    public List<String> getAccountStatusList() {
        return ddrAccountStatus.getOptions();
    }
}
