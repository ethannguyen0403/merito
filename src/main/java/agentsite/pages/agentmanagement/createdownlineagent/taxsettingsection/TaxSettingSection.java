package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;

public class TaxSettingSection {
    int totalColumn = 7;
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Table tblTaxSettingEX = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table[contains(@class,'betTable')]",totalColumn);
    public Table tblTaxSettingEG = Table.xpath("//div[@id='EXCH_GAMES-tax-settings']//table[contains(@class,'betTable')]",8);

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getTaxSettingSectionTitle(String product) {return ""; }
}
