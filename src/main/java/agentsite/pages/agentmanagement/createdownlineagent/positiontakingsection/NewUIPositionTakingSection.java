package agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection;

import com.paltech.element.common.Label;
import common.AGConstant;

public class NewUIPositionTakingSection extends PositionTakingSection {
    private Label lblPositionTakingTitleEX = Label.xpath("//div[@id='EXCHANGE-position-taking']//div[text()='Position Taking ']");
    private Label lblPositionTakingTitleEG = Label.xpath("//div[@id='EXCH_GAMES-position-taking']//div[text()='Position Taking ']");
    public String getPositionTakingSectionTitle(String product) {
        if (product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            return lblPositionTakingTitleEX.getText().trim();
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            return lblPositionTakingTitleEG.getText().trim();
        }
        return "";
    }

}
