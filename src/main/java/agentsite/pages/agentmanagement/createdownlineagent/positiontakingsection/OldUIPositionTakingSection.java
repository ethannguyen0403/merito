package agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection;

import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.TaxSettingSection;
import com.paltech.element.common.Label;

public class OldUIPositionTakingSection extends PositionTakingSection {
    private Label lblPositionTakingTitle = Label.xpath("//div[@id='EXCHANGE-position-taking']//div[text()='Position Taking']");

    public String getPositionTakingSectionTitle() {
        return lblPositionTakingTitle.getText();
    }

}
