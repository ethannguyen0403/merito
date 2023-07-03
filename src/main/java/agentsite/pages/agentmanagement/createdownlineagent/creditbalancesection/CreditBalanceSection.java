package agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;
import com.paltech.element.common.TextBox;

public class CreditBalanceSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public TextBox txtCreditLimit = TextBox.xpath("//input[@name='creditLimit']");
    public TextBox txtAGMaxCredit = TextBox.xpath("//input[@name='smaMaxCredit']");
    public TextBox txtMemberMaxCredit = TextBox.xpath("//input[@name='memberMaxCredit']");
    public Table tblCreditBalance = Table.xpath("(//table[contains(@class,'credit-balance-table')])[1]",2);

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getCreditSectionTitle() {return ""; }
}
