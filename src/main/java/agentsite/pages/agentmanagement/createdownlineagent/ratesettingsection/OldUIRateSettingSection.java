package agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection;

import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.CreditBalanceSection;
import com.paltech.element.common.Label;

public class OldUIRateSettingSection extends RateSettingSection {
    private Label lblRateSettingTitle = Label.xpath("//div[text()='Rate Setting']");
    public String getRateSettingSectionTitle() {
        return lblRateSettingTitle.getText();
    }

}
