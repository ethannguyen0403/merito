package agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class CreditBalanceSection {
    public Label lblCashBalanceTitle = Label.xpath("//app-credit-setting-exchange//div[@class='psection']");
    public Table tblCashBalance = Table.xpath(("//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]"), 2);
    public Label lblDownlineAGMaxCreditLimit = Label.xpath("//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'maxCredit')]");
    public TextBox txtCreditLimit = TextBox.xpath("//input[@name='creditLimit']");
    public TextBox txtAGMaxCredit = TextBox.xpath("//input[@name='smaMaxCredit']");
    public TextBox txtMemberMaxCredit = TextBox.xpath("//input[@name='memberMaxCredit']");
    public Table tblCreditBalance = Table.xpath("(//table[contains(@class,'credit-balance-table')])[1]", 2);
    public Label lblCreditLimitValue = Label.xpath("//div[@id='credit-balance-setting']//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'creditLimit')]");
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    private Label lblMemberMaxCreditLimit = Label.xpath("//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]//span[contains(@class,'memberMaxCredit')]");
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getCreditSectionTitle() {
        return "";
    }

    public double getCreditLimit(String currency) {
        return -1;
    }

    public int getMaxPlayerLitmitCredit() {
        String value = lblMemberMaxCreditLimit.getText();
        int convertoInt = (int) (Double.parseDouble(value.split("<=")[1].replace(",", "").trim()));
        return convertoInt;
    }

    public void updateCashBalance(String maxPlayerCredit) {
        if (!maxPlayerCredit.isEmpty())
            txtMemberMaxCredit.sendKeys(maxPlayerCredit);
    }
}
