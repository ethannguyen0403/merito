package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;

public class BetSettingSection {
    int totalColumn = 7;
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Table tblBetSettingEX = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')]",totalColumn);
    public Table tblBetSettingEG = Table.xpath("//div[@id='EXCH_GAMES-bet-settings']//table[contains(@class,'betTable')]",8);


    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getBetSettingSectionTitle(String product) {return ""; }
}
