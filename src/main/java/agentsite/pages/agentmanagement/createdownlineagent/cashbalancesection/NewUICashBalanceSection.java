package agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection;

import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class NewUICashBalanceSection extends CashBalanceSection {
    private Label lblCashBalanceTitle = Label.xpath("//div[@class='psection' and text()='Cash Balance ']");
    private TextBox txtFirstTimeDeposit = TextBox.xpath("//input[@name='cashBalance']");

    public String getCashSectionTitle() {
        return lblCashBalanceTitle.getText();
    }

    public void inputCashBalanceInfo(String firstTimeDeposit) {
        if (!firstTimeDeposit.isEmpty()) {
            txtFirstTimeDeposit.sendKeys(firstTimeDeposit);
        }
    }

}
