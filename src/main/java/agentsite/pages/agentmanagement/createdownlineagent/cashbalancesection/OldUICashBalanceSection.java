package agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection;

import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class OldUICashBalanceSection extends CashBalanceSection {
    private Label lblCashBalanceTitle = Label.xpath("//div[@class='psection' and text()='Cash Balance']");
    private TextBox txtCreditInitiation = TextBox.xpath("//input[@name='CreditReference']");
    private TextBox txtMaxPlayerCredit = TextBox.xpath("//input[@name='playerMaxCredit']");
//    private TextBox txtFirstTimeDeposit = TextBox.xpath("//input[@name='cashBalance']");
    public String getCashSectionTitle() {
        return lblCashBalanceTitle.getText().trim();
    }
    public void inputCashBalanceInfo(String creditInit, String firstTimeDeposit) {
        if (!creditInit.isEmpty()) {
            txtCreditInitiation.sendKeys(creditInit);
        }
        if (!firstTimeDeposit.isEmpty()) {
            txtFirstTimeDeposit.sendKeys(firstTimeDeposit);
        }
    }

}
