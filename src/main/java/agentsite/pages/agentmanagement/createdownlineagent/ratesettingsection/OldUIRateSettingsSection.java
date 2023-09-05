package agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection;

import com.paltech.element.common.Label;

public class OldUIRateSettingsSection extends RateSettingsSection {
    private Label lblRateSettingTitle = Label.xpath("//div[text()='Rate Setting']");

    public String getRateSettingSectionTitle() {
        return lblRateSettingTitle.getText().trim();
    }

}
