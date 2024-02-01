package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;

public class TaxSettingSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Table tblTaxSettingEX = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table[contains(@class,'betTable')]", 9);
    public Table tblTaxSettingEG = Table.xpath("//div[@id='EXCH_GAMES-tax-settings']//table[contains(@class,'betTable')]",8);
    protected Label lblUplineEX = Label.xpath("//div[@id='EXCHANGE-tax-settings']//td[text()='Upline']");
    protected Label lblTaxEX = Label.xpath("//div[@id='EXCHANGE-tax-settings']//td[text()='Tax']");
    protected Label lblUplineEG = Label.xpath("//div[@id='EXCH_GAMES-tax-settings']//td[text()='Upline']");
    protected Label lblTaxEG = Label.xpath("//div[@id='EXCH_GAMES-tax-settings']//td[text()='Tax']");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getTaxSettingSectionTitle(String product) {return ""; }

    public void verifyUIDisplayCorrect(String product){
    }
}
