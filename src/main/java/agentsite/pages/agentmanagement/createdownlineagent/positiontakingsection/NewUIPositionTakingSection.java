package agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection;

import com.paltech.element.common.Label;

public class NewUIPositionTakingSection extends PositionTakingSection {
    private Label lblPositionTakingTitle = Label.xpath("//div[@id='EXCHANGE-position-taking']//div[text()='Position Taking ']");

    public String getPositionTakingSectionTitle() {
        return lblPositionTakingTitle.getText();
    }

}
