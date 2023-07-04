package agentsite.pages.agentmanagement.createdownlineagent.productsettingsection;

import com.paltech.element.common.Icon;

public class ProductSettingSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getProductSettingSectionTitle() {
        return "";
    }
}
