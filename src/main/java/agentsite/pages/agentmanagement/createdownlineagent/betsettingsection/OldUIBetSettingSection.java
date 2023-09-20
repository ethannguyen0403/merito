package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import com.paltech.element.common.Label;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER;
import static common.AGConstant.AgencyManagement.CreateUser.EG_BET_TAX_PT_SETTING_HEADER_OLDUI;

public class OldUIBetSettingSection extends BetSettingSection {
    private Label lblBetSettingTitleEX = Label.xpath("//div[@id='EXCHANGE-bet-settings']//div[text()='Bet Settings']");
    private Label lblBetSettingTitleEG = Label.xpath("//div[@id='EXCH_GAMES-bet-settings']//div[text()='Bet Settings']");
    public String getBetSettingSectionTitle(String product) {
        if (product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            return lblBetSettingTitleEX.getText().trim();
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            return lblBetSettingTitleEG.getText().trim();
        }
        return "";
    }

    public List<ArrayList<String>> getBetSettingValidationValueLst(String currecyCode) {
        String value;
        List<ArrayList<String>> betSettingValidateion = tblBetSetting.getRowsWithoutHeader(false);
        for (int i = 0; i < betSettingValidateion.size(); i++) {
            for (int j = 1; j < betSettingValidateion.get(i).size(); j++) {
                value = betSettingValidateion.get(i).get(j).split("=")[1].trim();
                betSettingValidateion.get(i).set(j, value);
            }
        }
        return betSettingValidateion;
    }

    public void verifyUIDisplayCorrect(String product) {
        if (product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            Assert.assertTrue(lblMinBetEX.isDisplayed(), "FAILED! Min Bet label does not display");
            Assert.assertTrue(lblMaxBetEX.isDisplayed(), "FAILED! Max Bet label does not display");
            Assert.assertTrue(lblMaxLiabilityEX.isDisplayed(), "FAILED! Max Liability label does not display");
            Assert.assertTrue(lblMaxWinEX.isDisplayed(), "FAILED! Max Win label does not display");
            ArrayList<String> lstHeader = tblBetSettingEX.getHeaderNameOfRows();
            Assert.assertEquals(lstHeader, LST_BET_SETTING_HEADER);
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            Assert.assertTrue(lblMinBetEG.isDisplayed(), "FAILED! Min Bet label does not display");
            Assert.assertTrue(lblMaxBetEG.isDisplayed(), "FAILED! Max Bet label does not display");
            Assert.assertTrue(lblMaxLiabilityEG.isDisplayed(), "FAILED! Max Liability label does not display");
            Assert.assertTrue(lblMaxWinEG.isDisplayed(), "FAILED! Max Win label does not display");
            ArrayList<String> lstHeader = tblBetSettingEG.getHeaderNameOfRows();
            Assert.assertEquals(lstHeader, EG_BET_TAX_PT_SETTING_HEADER_OLDUI);
        }
    }
}
