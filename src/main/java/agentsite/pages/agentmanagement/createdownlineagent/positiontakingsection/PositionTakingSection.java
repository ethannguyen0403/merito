package agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;

public class PositionTakingSection {
    int totalColumn = 9;
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Table tblPositionTakingEX = Table.xpath("//div[@id='EXCHANGE-position-taking']//table",totalColumn);
    public Table tblPositionTakingEG = Table.xpath("//div[@id='EXCH_GAMES-position-taking']//table",8);

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getPositionTakingSectionTitle(String product) {return ""; }
}
