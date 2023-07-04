package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import com.paltech.element.common.Label;

public class OldUITaxSettingSection extends TaxSettingSection {
    private Label lblTaxSettingTitle = Label.xpath("//div[@id='EXCHANGE-tax-settings']//div[text()='Tax Settings']");

    public String getTaxSettingSectionTitle() {
        return lblTaxSettingTitle.getText();
    }

}
