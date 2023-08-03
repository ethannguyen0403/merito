package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import com.paltech.element.common.Label;
import common.AGConstant;

import java.util.ArrayList;
import java.util.List;

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

    public List<ArrayList<String>> getBetSettingValidationValueLst(String currecyCode) {
        String value;
        List<ArrayList<String>> betSettingValidateion = tblBetSetting.getRowsWithoutHeader(false);
        for (int i = 0; i < betSettingValidateion.size(); i++) {
            for (int j = 1; j < betSettingValidateion.get(i).size(); j++) {
                value = betSettingValidateion.get(i).get(j).split(currecyCode)[1].trim();
                betSettingValidateion.get(i).set(j, value);
            }
        }
        return betSettingValidateion;
    }

}
