package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.RiskSettingSection;
import com.paltech.element.common.Label;

public class OldUITaxSettingSection extends TaxSettingSection {
    private Label lblTaxSettingTitle = Label.xpath("//div[@id='EXCHANGE-tax-settings']//div[text()='Tax Settings']");

    public String getTaxSettingSectionTitle() {
        return lblTaxSettingTitle.getText();
    }

}
