package agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection;

import agentsite.controls.Table;
import com.paltech.element.common.TextBox;

public class CashBalanceSection {
    public Table tblCashBalance = Table.xpath(("//app-credit-setting-exchange//table[contains(@class,'credit-balance-table')]"), 2);
    public TextBox txtCreditInitiation = TextBox.xpath("//input[@name='CreditReference']");
    public TextBox txtFirstTimeDeposit = TextBox.xpath("//input[@name='cashBalance']");

    public String getCashSectionTitle() {return ""; }
    public void inputCashBalanceInfo(String creditInit, String firstTimeDeposit) {}
    public void inputCashBalanceInfo(String firstTimeDeposit) {}
}
