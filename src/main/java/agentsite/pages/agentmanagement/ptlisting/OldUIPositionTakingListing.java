package agentsite.pages.agentmanagement.ptlisting;

import com.paltech.element.common.CheckBox;
import com.paltech.element.common.DropDownBox;
import common.AGConstant;

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

    public boolean isTableHeaderProductDisplayCorrect(String product) {
        List<String> lstHeader = tblDownline.getHeaderList();
        switch (product.toLowerCase()) {
            case "exchange":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_HEADER_OLDUI);
            case "exchange games":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_GAME_HEADER_OLDUI);
            case "game hall":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_GAME_HALL_HEADER_OLDUI);
            case "live dealer european":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_LIVE_DEALER_EUROPEAN_HEADER_OLDUI);
            case "live dealer asian":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_LIVE_DEALER_ASIA_HEADER_OLDUI);
            case "evolution":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EVOLUTION_HEADER_OLDUI);
            case "supernowa casino":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_SUPERNOWA_HEADER_OLDUI);
            case "ion":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_ION_OLDUI);
            case "vivo":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_VIVO_OLDUI);
            case "pragmatic":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_PRAGMATIC_OLDUI);
            case "cmd sportsbook":
                return lstHeader.equals(AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_CMD_SPORTSBOOK_OLDUI);
            default:
                System.out.println("Inputted product is not matched any case");
                return false;
        }
    }

}
