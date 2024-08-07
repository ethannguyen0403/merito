package agentsite.pages.agentmanagement.ptlisting;

import com.paltech.element.common.CheckBox;
import com.paltech.element.common.DropDownBox;
import common.AGConstant;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class FairenterPositionTakingListing extends PositionTakingListing {
    private DropDownBox ddbLevelPreset = DropDownBox.xpath("//table[contains(@class,'selectionTable')]//td[contains(text(),'SMA ')]/select");
    private int chbCol = 6;
    public int soccerCol = 10;
    public void verifyUI(String userCode) {
        Assert.assertTrue(txtUsername.isDisplayed(), "FAILED! Username textbox not display");
        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box not display");
        Assert.assertTrue(ddbProduct.isDisplayed(), "FAILED! Product dropdown not display");
        Assert.assertTrue(ddbLevel.isDisplayed(), "FAILED! Level dropdown not display");
        List<String> lstHeader = tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_HEADER_FAIRENTER, "FAILED! Header table not match");
        Assert.assertTrue(lblUsername.isDisplayed(), "FAILED! Username level does not correct");
        Assert.assertTrue(lblProduct.isDisplayed(), "FAILED! Product label does not correct");
        Assert.assertTrue(lblAccountStatus.isDisplayed(), "FAILED! Account Status label does not correct");
        Assert.assertTrue(lblLevel.isDisplayed(), "FAILED! Level label does not correct");
        Assert.assertEquals(lblBreadcrumb.getText(), userCode, "FAILED! Breadcrumb bar not display login account");
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
}
