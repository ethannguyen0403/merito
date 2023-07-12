package agentsite.pages.agentmanagement.createdownlineagent.productsettingsection;

import com.paltech.element.common.Label;

public class NewUIProductSettingSection extends ProductSettingSection {
    private Label lblProductSettingTitle = Label.xpath("//div[text()='Product Settings ']");
    public String getProductSettingSectionTitle() {
        return lblProductSettingTitle.getText();
    }

}
