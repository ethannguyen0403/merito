package agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection;

import com.paltech.element.common.Icon;

public class RateSettingsSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getRateSettingSectionTitle() {
        return "";
    }
}
