package agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;

public class PositionTakingSection {
    public Table tblPositionTaking = Table.xpath("//div[@id='EXCHANGE-position-taking']//table", 9);
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getPositionTakingSectionTitle() {
        return "";
    }
}
