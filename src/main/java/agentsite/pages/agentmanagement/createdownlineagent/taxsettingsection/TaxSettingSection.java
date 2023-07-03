package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;
import com.paltech.element.common.TextBox;

public class TaxSettingSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Table tblTaxSetting = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table",7);

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getTaxSettingSectionTitle() {return ""; }
}
