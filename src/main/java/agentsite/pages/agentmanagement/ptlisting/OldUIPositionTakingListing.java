package agentsite.pages.agentmanagement.ptlisting;

import com.paltech.element.common.CheckBox;
import com.paltech.element.common.DropDownBox;
import common.AGConstant;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class OldUIPositionTakingListing extends PositionTakingListing {
    private DropDownBox ddbLevelPreset = DropDownBox.xpath("//table[contains(@class,'selectionTable')]//td[contains(text(),'SAD ')]/select");

    public void updatePT(String loginID, int PT, HashMap<String, Boolean> map) {
        if (Objects.nonNull(map)) {
            // Select sport to update PT
            enableSport(map);
        }
        // Select the checkbox corresponding with login ID
        if (!loginID.isEmpty()) {
            String chbDownlinexPath = tblDownline.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, usernameCol, 1, null, chbCol, "input", false, false);
            CheckBox chb = CheckBox.xpath(chbDownlinexPath);
            chb.click();
        }

        // Select SAD Preset value
        ddbLevelPreset.selectByVisibleText(Integer.toString(PT));

        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();

    }
    public void updatePTSport(String loginID, int PT, String sport){
        // Select sport to update PT
        selectSport(sport);
        // Select the checkbox corresponding with login ID
        if (!loginID.isEmpty()) {
            String chbDownlinexPath = tblDownline.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, usernameCol, 1, "span", chbCol, "input", false, false);
            CheckBox chb = CheckBox.xpath(chbDownlinexPath);
            chb.click();
            if (!chb.isSelected()){
                chb.jsClick();
            }
            waitingLoadingSpinner();
        }
        // Select SMA Preset value
        ddbLevelPreset.selectByVisibleText(Integer.toString(PT));
        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();
    }

    public void verifyTableHeaderProductDisplayCorrect(List<String> lstProduct) {
        for (String product : lstProduct) {
            //workaround for product Vivo/Ion -> result get = ViVo/Ion but UI show Vivo/ION -> cannot select by visible text
            if(product.equalsIgnoreCase("vivo")) {
                product = "Vivo";
            } else if (product.equalsIgnoreCase("ion")) {
                product = "ION";
            }
            search("", "", product, "");
            List<String> lstHeader = tblDownline.getHeaderList();
            switch (product.toLowerCase()) {
                case "exchange":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_HEADER_OLDUI));
                    break;
                case "exchange games":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_GAME_HEADER_OLDUI));
                    break;
                case "game hall":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_GAME_HALL_HEADER_OLDUI));
                    break;
                case "live dealer european":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_LIVE_DEALER_EUROPEAN_HEADER_OLDUI));
                    break;
                case "live dealer asian":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_LIVE_DEALER_ASIA_HEADER_OLDUI));
                    break;
                case "evolution":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EVOLUTION_HEADER_OLDUI));
                    break;
                case "supernowa casino":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_SUPERNOWA_HEADER_OLDUI));
                    break;
                case "ion":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_ION_OLDUI));
                    break;
                case "vivo":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_VIVO_OLDUI));
                    break;
                case "pragmatic":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_PRAGMATIC_OLDUI));
                    break;
                case "cmd sportsbook":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_CMD_SPORTSBOOK_OLDUI));
                    break;
                case "lottery & slots":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_LOTTERY_SLOT_HEADER_OLDUI));
                    break;
                case "rwb sports":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_RWB_SPORTSBOOK_OLDUI));
                    break;
                case "ps38 sports":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_PS38_SPORTSBOOK_OLDUI));
                    break;
                case "ps38":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_PS38_OLDUI));
                    break;
                case "q-tech":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_QTECH_OLDUI));
                    break;
                case "evolution (whitecliff)":
                    Assert.assertTrue(lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EVOLUTION_WHITECLIFF_OLDUI));
                    break;
                default:
                    System.out.println("Inputted product is not matched any case");
                    break;
            }
        }
    }
    public void verifyUI(String userCode) {
        Assert.assertTrue(txtUsername.isDisplayed(), "FAILED! Username textbox not display");
        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box not display");
        Assert.assertTrue(ddbProduct.isDisplayed(), "FAILED! Product dropdown not display");
        Assert.assertTrue(ddbLevel.isDisplayed(), "FAILED! Level dropdown not display");
        List<String> lstHeader = tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_HEADER_NEWUI, "FAILED! Header table not match");
        Assert.assertTrue(lblUsername.isDisplayed(), "FAILED! Username level does not correct");
        Assert.assertTrue(lblProduct.isDisplayed(), "FAILED! Product label does not correct");
        Assert.assertTrue(lblAccountStatus.isDisplayed(), "FAILED! Account Status label does not correct");
        Assert.assertTrue(lblLevel.isDisplayed(), "FAILED! Level label does not correct");
        Assert.assertEquals(lblBreadcrumb.getText(), userCode, "FAILED! Breadcrumb bar not display login account");
    }
}
