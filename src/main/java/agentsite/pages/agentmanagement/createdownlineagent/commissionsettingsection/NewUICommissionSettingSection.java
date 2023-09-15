package agentsite.pages.agentmanagement.createdownlineagent.commissionsettingsection;

import com.paltech.element.common.Label;

public class NewUICommissionSettingSection extends CommissionSettingSection {
    private String titleXpath = "//div[@id='%s-commission-settings']//div[text()='Commission ']";
    public NewUICommissionSettingSection(String types) {
        super(types);
    }

    public String getCommissionSettingSectionTitle(String productName) {
        productCode = mapProductNameToCode(productName);
        Label lblTitle = Label.xpath(String.format(titleXpath, productCode));
        return lblTitle.getText().trim();
    }
}
