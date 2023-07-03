package agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;
import com.paltech.element.common.TextBox;

public class RateSettingSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getRateSettingSectionTitle() {return ""; }
}
