package agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection;

import com.paltech.element.common.Label;

public class OldUIAccountBalanceTransferConditionSection extends AccountBalanceTransferConditionInforSection {
    private Label lblTransferConditionTitle = Label.xpath("//div[@class='psection' and text()='Account Balance Transfer Condition']");

    public String getAccountBalanceTransferConditionTitle() {
        return lblTransferConditionTitle.getText().trim();
    }

}
