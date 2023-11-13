package agentsite.pages.cashmanagement.quickdepositconfiguration;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import org.testng.Assert;
import java.util.*;

public class OldUIQuickDepositConfiguration extends QuickDepositConfiguration {
    private Button btnClear = Button.xpath("//app-quick-deposit-configuration//button[text()='Clear']");
    private String xpathQuickDeposit = "(//app-quick-deposit-configuration//div[@class='form-row']//input[contains(@class,'form-control')])[%s]";
    private TextBox txtQuickDepositValue = TextBox.xpath("//app-quick-deposit-configuration//div[@class='form-row']//input[contains(@class,'form-control')]");
    private Button btnSubmit = Button.xpath("//app-quick-deposit-configuration//button[text()='Submit']");
    private Button btnOk = Button.xpath("//app-alert//button[text()='OK']");
    public void clickClear() {
        btnClear.click();
    }

    public List<String> getListQuickDepositAmount() {
        List<String> lstValue = new ArrayList<>();
        for (int i = 0; i < txtQuickDepositValue.getWebElements().size(); i++) {
            TextBox txtQuickDeposit = TextBox.xpath(String.format(xpathQuickDeposit, i + 1));
            lstValue.add(txtQuickDeposit.getAttribute("value"));
        }
        return lstValue;
    }

    public void updateQuickDepositAmount(List<String> lstUpdateValue) {
        for (int i = 0; i < lstUpdateValue.size(); i++) {
            TextBox txtQuickDeposit = TextBox.xpath(String.format(xpathQuickDeposit, i + 1));
            txtQuickDeposit.sendKeys(lstUpdateValue.get(i));
        }
        btnSubmit.click();
        btnOk.waitForElementToBePresent(btnOk.getLocator(), 2);
    }

    public void closeAlertPopup() {
       if(btnOk.isDisplayed())
            btnOk.click();
    }

    public void verifyQuickDepositAmount(List<String> lstUpdateValue) {
        for (int i = 0; i < lstUpdateValue.size(); i++) {
            TextBox txtQuickDeposit = TextBox.xpath(String.format(xpathQuickDeposit, i + 1));
            Assert.assertEquals(txtQuickDeposit.getAttribute("value"), lstUpdateValue.get(i), String.format("FAILED! Quick Deposit amount shows incorrectly expected %s but found %s", lstUpdateValue.get(i), txtQuickDeposit.getAttribute("value")));
        }
    }
}
