package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import com.paltech.element.common.Label;

public class NewUIBetSettingSection extends BetSettingSection {
    private Label lblBetSettingTitle = Label.xpath("//div[@id='EXCHANGE-bet-settings']//div[text()='Bet Settings ']");

    public String getBetSettingSectionTitle() {
        return lblBetSettingTitle.getText();
    }

}
