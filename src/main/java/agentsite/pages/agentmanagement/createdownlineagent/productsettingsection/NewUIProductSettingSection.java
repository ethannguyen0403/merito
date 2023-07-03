package agentsite.pages.agentmanagement.createdownlineagent.productsettingsection;

import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.CreditBalanceSection;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;

public class NewUIProductSettingSection extends ProductSettingSection {
    private Label lblProductSettingTitle = Label.xpath("//div[text()='Product Settings ']");
    public String getCreditSectionTitle() {
        return lblProductSettingTitle.getText();
    }

}
