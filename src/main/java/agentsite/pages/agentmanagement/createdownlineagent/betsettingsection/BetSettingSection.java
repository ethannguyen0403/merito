package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.util.ArrayList;
import java.util.List;

public class BetSettingSection {
    int totalColumn = 7;
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    protected Label lblMinBetEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Min Bet']");
    protected Label lblMaxBetEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Max Bet']");
    protected Label lblMaxLiabilityEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Max Liability ']");
    protected Label lblMaxWinEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Max Win ']");
    protected Label lblMinBetEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Min Bet']");
    protected Label lblMaxBetEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Max Bet']");
    protected Label lblMaxLiabilityEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Max Liability ']");
    protected Label lblMaxWinEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Max Win ']");
    public Table tblBetSettingEX = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')]",totalColumn);
    public Table tblBetSettingEG = Table.xpath("//div[@id='EXCH_GAMES-bet-settings']//table[contains(@class,'betTable')]",8);
    public Table tblBetSetting = Table.xpath("(//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')])[1]", totalColumn);

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getBetSettingSectionTitle(String product) {return ""; }

    public List<ArrayList<String>> getBetSettingValidationValueLst(String currecyCode) {
        return null;
    }
    public void inputBetSetting(List<ArrayList<String>> lstSetting) {
        TextBox txtSport;
        int n = lstSetting.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < lstSetting.get(i).size(); j++) {
                if (!(lstSetting.get(i).get(j)).isEmpty()) {
                    txtSport = TextBox.xpath(tblBetSetting.getxPathOfCell(1, j + 1, i + 1, "input"));
                    txtSport.sendKeys(lstSetting.get(i).get(j));
                }
            }
        }
    }

    public void verifyUIDisplayCorrect(String product) {
    }

}
