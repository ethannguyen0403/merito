package agentsite.pages.agentmanagement.createdownlineagent.accountinfosection;

import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import common.AGConstant;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OldUIAccountInforSection extends AccountInforSection {
    private String _xPath = "//div[@id='account']//app-agency-account-ui";
    private DropDownBox ddpLevel = DropDownBox.xpath(String.format("%s//tr[1]//td[8]//select", _xPath));
    private TextBox txtLoginID = TextBox.xpath("(//input[@name='lastName'])[1]");

    //Controls in Create Company
    private DropDownBox ddpCurrency = DropDownBox.xpath(String.format("%s//app-agency-account-ui//tr[2]//td[8]//select", _xPath));
    String listLabel = String.format("%s//td[contains(@class,'label')]", _xPath);

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
        List<String> lstInfo = getListLabelInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_LEVEL, "FAILED! Level label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
//        Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_PHONE, "FAILED! Phone display incorrect");
        Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
//        Assert.assertEquals(lstInfo.get(8), AGConstant.AgencyManagement.CreateAccount.LBL_FAX, "FAILED! Fax display incorrect");
//        Assert.assertEquals(lstInfo.get(9), AGConstant.AgencyManagement.CreateAccount.LBL_BASE_CURRENCY, "FAILED! Base Currency display incorrect");
//        Assert.assertEquals(lstInfo.get(10), AGConstant.AgencyManagement.CreateAccount.LBL_ALLOW_AG_EXTRA, "FAILED! Allow Extra display incorrect");
//        Assert.assertEquals(page.accInfoSection.lblUsernamePrefix.getText(),prefix, "FAILED! Login ID textbox does not display");
        Assert.assertTrue(txtLoginID.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
//        Assert.assertTrue(txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");
//        Assert.assertTrue(txtFax.isDisplayed(), "FAILED! Tax textbox does not display");
//        Assert.assertTrue(cbAllowExtraPT.isDisplayed(), "FAILED! Allow Extra PT checkbox does not display");
    }

}
