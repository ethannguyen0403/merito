package agentsite.pages.agentmanagement.ptlisting;

import com.paltech.element.common.CheckBox;
import com.paltech.element.common.DropDownBox;
import java.util.HashMap;
import java.util.Objects;

public class NewUIPositionTakingListing extends PositionTakingListing {
    private DropDownBox ddbLevelPreset = DropDownBox.xpath("//table[contains(@class,'selectionTable')]//td[contains(text(),'SMA ')]/select");

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

        // Select SMA Preset value
        ddbLevelPreset.selectByVisibleText(Integer.toString(PT));

        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();

    }

}
