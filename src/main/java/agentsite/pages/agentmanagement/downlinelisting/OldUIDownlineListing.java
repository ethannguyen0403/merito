package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.controls.Cell;
import agentsite.pages.agentmanagement.EditDownLinePage;
import com.paltech.element.common.Link;
import org.testng.Assert;

import java.util.List;

import static baseTest.BaseCaseTest._brandname;
import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL;
import static common.AGConstant.AgencyManagement.DownlineListing.LST_ACCOUNT_STATUS;
import static common.AGConstant.AgencyManagement.DownlineListing.LST_DOWLINE_LISTING_TABLE_HEADER_OLDUI;
import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.DOWNLINE_LISTING;

public class OldUIDownlineListing extends DownlineListing {
    public OldUIDownlineListing(String types) {
        super(types);
    }
//    private int userCodeCol = 3;

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        editCol = getHeaderIndexValue("Edit");
        Cell cellValue = tblDowlineListing.getCellByName(loginID, false);
        int userCodeCol = Integer.parseInt(cellValue.getAttribute("cellIndex")) + 1;
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
        }
        waitingLoadingSpinner();
        return new EditDownLinePage(_brandname);
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return clickEditIcon(loginID, false);
    }

    public void verifyUIDisplayCorrect() {
        List<String> lstHeaderTable = tblDowlineListing.getHeaderNameOfRows();

        Assert.assertEquals(header.lblPageTitle.getText(), DOWNLINE_LISTING, "FAILED! Page title is incorrect displayed");
        Assert.assertEquals(lblLoginId.getText(), LOGIN_ID, "FAILED! Login ID is incorrect displayed");
        Assert.assertEquals(lblAccountStatus.getText(), ACCOUNT_STATUS, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(lblLevel.getText(), LEVEL, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(btnSearch.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect display");
        Assert.assertTrue(txtLoginID.isDisplayed(), "FAILED! Login ID textbox is incorrect display");
        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdownbox is incorrect display");
        Assert.assertTrue(ddbLevel.isDisplayed(), "FAILED! Level dropdown is incorrect display");
        Assert.assertEquals(ddbAccountStatus.getOptions(), LST_ACCOUNT_STATUS, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(ddbLevel.getOptions(), DDB_LEVEL, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(lstHeaderTable, LST_DOWLINE_LISTING_TABLE_HEADER_OLDUI, "FAILED! Table header is incorrect displayed");
    }

}
