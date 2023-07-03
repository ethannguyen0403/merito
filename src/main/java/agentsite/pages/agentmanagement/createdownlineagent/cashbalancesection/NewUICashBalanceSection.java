package agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection;

import com.paltech.element.common.Label;

public class NewUICashBalanceSection extends CashBalanceSection {
    private Label lblCashBalanceTitle = Label.xpath("//div[@class='psection' and text()='Cash Balance ']");

    public String getCashSectionTitle() {
        return lblCashBalanceTitle.getText();
    }

}
