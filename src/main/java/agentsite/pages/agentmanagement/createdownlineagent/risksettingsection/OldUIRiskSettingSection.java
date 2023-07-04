package agentsite.pages.agentmanagement.createdownlineagent.risksettingsection;

import com.paltech.element.common.Label;

public class OldUIRiskSettingSection extends RiskSettingSection {
    private Label lblRiskSettingTitle = Label.xpath("//div[@class='psection' and text()='Risk Setting']");

    public String getRiskSettingTitle() {
        return lblRiskSettingTitle.getText();
    }

}
