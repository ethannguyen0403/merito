package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import com.paltech.element.common.Icon;

public class BetSettingSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getBetSettingSectionTitle() {
        return "";
    }
}
