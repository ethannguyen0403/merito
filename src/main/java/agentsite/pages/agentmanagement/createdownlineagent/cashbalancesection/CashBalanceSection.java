package agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection;

import com.paltech.element.common.Icon;
import com.paltech.element.common.TextBox;

public class CashBalanceSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public TextBox txtCreditInitiation = TextBox.xpath("//input[@name='CreditReference']");
    public TextBox txtFirstTimeDeposit = TextBox.xpath("//input[@name='cashBalance']");
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getCashSectionTitle() {return ""; }
    public void inputCashBalanceInfo(String creditInit, String firstTimeDeposit) {}
    public void inputCashBalanceInfo(String firstTimeDeposit) {}
}
