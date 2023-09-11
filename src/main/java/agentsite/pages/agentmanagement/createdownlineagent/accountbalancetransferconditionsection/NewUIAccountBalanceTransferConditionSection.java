package agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection;

import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.CashBalanceSection;
import com.paltech.element.common.Label;

public class NewUIAccountBalanceTransferConditionSection extends AccountBalanceTransferConditionInforSection {
    private Label lblTransferConditionTitle = Label.xpath("//div[@class='psection' and text()='Account Balance Transfer Condition ']");

    public String getAccountBalanceTransferConditionTitle() {
        return lblTransferConditionTitle.getText().trim();
    }

}
