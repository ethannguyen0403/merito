package agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection;

import agentsite.controls.Table;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

public class CreditBalanceSection {
    public TextBox txtCreditLimit = TextBox.xpath("//input[@name='creditLimit']");
    public TextBox txtAGMaxCredit = TextBox.xpath("//input[@name='smaMaxCredit']");
    public TextBox txtMemberMaxCredit = TextBox.xpath("//input[@name='memberMaxCredit']|//input[@name='playerMaxCredit']");
    public Label lblDownlineAGMaxCreditLimit = Label.xpath("//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'maxCredit')]");

    public Label lblCreditLimitValue = Label.xpath("//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'creditLimit')]");
    private Label lblMemberMaxCreditLimit = Label.xpath("//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'memberMaxCredit')] | //app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'playerMaxCredit')]" +
            "| //app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'maxCredit')]");
    public Table tblCreditBalance = Table.xpath("(//table[contains(@class,'credit-balance-table')])[1]", 2);
    public String getCreditSectionTitle() {
        return "";
    }

    public double getCreditLimit(String currency) {
        return -1;
    }

    public int getMaxPlayerLitmitCredit(String currency) {
        String value = lblMemberMaxCreditLimit.getText();
        int convertoInt = (int) (Double.parseDouble(value.split("<=")[1].replace(",", "").replace(currency,"").trim()));
        return convertoInt;
    }

    public void updateCashBalance(String maxPlayerCredit) {
        if (!maxPlayerCredit.isEmpty())
            txtMemberMaxCredit.sendKeys(maxPlayerCredit);
    }

    public void updateCreditBalance(double maxPlayerCredit) {
        txtCreditLimit.sendKeys(String.format("%.0f",maxPlayerCredit));
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertTrue(txtCreditLimit.isDisplayed(),"FAILED! Credit Limit textbox does not display");
        Assert.assertTrue(txtAGMaxCredit.isDisplayed(),"FAILED! AG Max Credit textbox does not display");
        Assert.assertTrue(txtMemberMaxCredit.isDisplayed(),"FAILED! Player Max Credit textbox does not display");
        Assert.assertTrue(lblCreditLimitValue.isDisplayed(),"FAILED! Credit Limit label does not display");
        Assert.assertTrue(lblDownlineAGMaxCreditLimit.isDisplayed(),"FAILED! AG Max Credit label does not display");
        Assert.assertTrue(lblMemberMaxCreditLimit.isDisplayed(),"FAILED! Player Max Credit label does not display");
    }
}
