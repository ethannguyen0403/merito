package agentsite.pages.agentmanagement.createdownlineagent.productsettingsection;

import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.CreditBalanceSection;
import com.paltech.element.common.Label;

public class OldUIProductSettingSection extends ProductSettingSection {
    private Label lblProductSettingTitle = Label.xpath("//div[text()='Product Settings']");
    public String getProductSettingSectionTitle() {
        return lblProductSettingTitle.getText();
    }

}
