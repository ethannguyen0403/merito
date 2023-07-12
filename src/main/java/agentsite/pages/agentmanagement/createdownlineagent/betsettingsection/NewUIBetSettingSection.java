package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import com.paltech.element.common.Label;
import common.AGConstant;

public class NewUIBetSettingSection extends BetSettingSection {
    private Label lblBetSettingTitleEX = Label.xpath("//div[@id='EXCHANGE-bet-settings']//div[text()='Bet Settings ']");
    private Label lblBetSettingTitleEG = Label.xpath("//div[@id='EXCH_GAMES-bet-settings']//div[text()='Bet Settings ']");
    public String getBetSettingSectionTitle(String product) {
        if (product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            return lblBetSettingTitleEX.getText();
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            return lblBetSettingTitleEG.getText();
        }
        return "";
    }

}
