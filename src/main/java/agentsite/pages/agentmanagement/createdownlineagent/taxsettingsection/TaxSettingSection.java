package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;

public class TaxSettingSection {
    public Table tblTaxSetting = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table", 7);
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getTaxSettingSectionTitle() {
        return "";
    }
}
