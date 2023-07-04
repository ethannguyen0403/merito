package agentsite.pages.agentmanagement.createdownlineagent.risksettingsection;

import com.paltech.element.common.Icon;
import com.paltech.element.common.TextBox;

public class RiskSettingSection {
    public TextBox txtMaxExposure = TextBox.xpath("//input[@name='riskSetting']");
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getRiskSettingTitle() {
        return "";
    }
}
