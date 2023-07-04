package agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection;

import com.paltech.element.common.Icon;
import com.paltech.element.common.TextBox;

public class CashBalanceSection {
    public TextBox txtInitialDeposit = TextBox.xpath("//input[@name='cashBalance']");
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getCashSectionTitle() {
        return "";
    }
}
