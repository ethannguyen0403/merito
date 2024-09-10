package agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection;

import com.paltech.element.common.Label;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;

import static common.AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER_NEWUI;
import static common.AGConstant.AgencyManagement.CreateUser.EG_BET_TAX_PT_SETTING_HEADER_NEWUI;

public class FunUITaxSettingSection extends TaxSettingSection {
    private Label lblTaxSettingTitleEX = Label.xpath("//div[@id='EXCHANGE-tax-settings']//div[text()='Tax Settings ']");
    private Label lblTaxSettingTitleEG = Label.xpath("//div[@id='EXCH_GAMES-tax-settings']//div[text()='Tax Settings ']");

    public String getTaxSettingSectionTitle(String product) {
        if (product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            return lblTaxSettingTitleEX.getText().trim();
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            return lblTaxSettingTitleEG.getText().trim();
        }
        return "";
    }

    public void verifyUIDisplayCorrect(String product) {
        if(product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            Assert.assertTrue(lblUplineEX.isDisplayed(), "FAILED! Upline label does not display");
            Assert.assertTrue(lblTaxEX.isDisplayed(), "FAILED! Tax label does not display");
            ArrayList<String> lstHeader = tblTaxSettingEX.getHeaderNameOfRows();
            Assert.assertEquals(lstHeader, LST_TAX_SETTING_HEADER_NEWUI,"FAILED! List tax header does not display");
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            Assert.assertTrue(lblUplineEG.isDisplayed(), "FAILED! Upline label does not display");
            Assert.assertTrue(lblTaxEG.isDisplayed(), "FAILED! Tax label does not display");
            ArrayList<String> lstHeader = tblTaxSettingEG.getHeaderNameOfRows();
            Assert.assertEquals(lstHeader, EG_BET_TAX_PT_SETTING_HEADER_NEWUI,"FAILED! List tax header does not display");
        }

    }

}
