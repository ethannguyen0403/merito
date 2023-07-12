package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import com.paltech.element.common.Label;
import common.AGConstant;

public class NewUITaxSettingSection extends TaxSettingSection {
    private Label lblTaxSettingTitleEX = Label.xpath("//div[@id='EXCHANGE-tax-settings']//div[text()='Tax Settings ']");
    private Label lblTaxSettingTitleEG = Label.xpath("//div[@id='EXCH_GAMES-tax-settings']//div[text()='Tax Settings ']");

    public String getTaxSettingSectionTitle(String product) {
        if (product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            return lblTaxSettingTitleEX.getText();
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            return lblTaxSettingTitleEG.getText();
        }
        return "";
    }

}
