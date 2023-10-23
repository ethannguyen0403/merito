package agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection;

import com.paltech.element.common.Label;

public class NewUIProductStatusSettingSection extends ProductStatusSettingSection {
    private Label lblProductSettingTitle = Label.xpath("//div[text()='Product Settings']");
    public String getProductSettingSectionTitle() {
        return lblProductSettingTitle.getText().trim();
    }
}
