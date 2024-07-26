package agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection;

import agentsite.controls.Table;
import com.paltech.element.common.Label;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_SPORT_HEADER;
import static common.AGConstant.AgencyManagement.CreateUser.*;

public class OldUIPositionTakingSection extends PositionTakingSection {
    private String productCode;
    private String positionTakingTitleXpath = "//div[@id='%s-position-taking']//div[text()='Position Taking']";

    public String getPositionTakingSectionTitle(String product) {
        productCode = mapProductNameToCode(product);
        Label lblPositionTakingTitle = Label.xpath(String.format(positionTakingTitleXpath, productCode));
        return lblPositionTakingTitle.getText().trim();
    }

    public void verifyUIDisplayCorrect(String product) {
        productCode = mapProductNameToCode(product);
        if(product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
//            Table tblPositionTaking = Table.xpath(String.format(positionTakingTableXpath, productCode), totalColumnExchange);
            List<String> lstSportHeader = new ArrayList<>();
            Label lblSport = Label.xpath("//app-ptsetting//strong");
            for (int i = 0; i < lblSport.getWebElements().size();i++){
                lstSportHeader.add(Label.xpath(String.format("(//app-ptsetting//strong)[%s]",i+1)).getText());
            }
            Assert.assertEquals(lstSportHeader, LST_POSITION_TAKING_SPORT_HEADER, "FAILED! Position header list is not shown correct");
        } else if (product.equalsIgnoreCase(AGConstant.EXCHANGE_GAMES)) {
            Table tblPositionTaking = Table.xpath(String.format(positionTakingTableXpath, productCode), totalColumnExchangeGames);
            ArrayList<String> lstHeader = tblPositionTaking.getHeaderNameOfRows();
            Assert.assertEquals(lstHeader, EG_BET_TAX_PT_SETTING_HEADER_OLDUI,"FAILED! Position header does not display correct");
        } else {
            Table tblPositionTaking = Table.xpath(String.format(positionTakingTableXpath, productCode), totalOther);
            ArrayList<String> lstHeader = tblPositionTaking.getHeaderNameOfRows();
            if(product.equalsIgnoreCase(AGConstant.LIVE_DEALER_ASIAN) || product.equalsIgnoreCase(AGConstant.LIVE_DEALER_EUROPEAN) || product.equalsIgnoreCase(AGConstant.PRAGMATIC)
                    || product.equalsIgnoreCase(AGConstant.VIVO) || product.equalsIgnoreCase(AGConstant.ION)) {
                Assert.assertEquals(lstHeader.get(1).toLowerCase(), product.toLowerCase(),"FAILED! List position header does not display correct");
            } else if (product.equalsIgnoreCase(AGConstant.EVOLUTION)) {
                Assert.assertEquals(lstHeader, EVOLUTION_BET_PT_SETTING_HEADER,"FAILED! List tax header does not display");
            } else if (product.equalsIgnoreCase(AGConstant.GAME_HALL)) {
                Assert.assertEquals(lstHeader, GAME_HALL_PT_SETTING_HEADER,"FAILED! List tax header does not display");
            }
        }
    }
}
