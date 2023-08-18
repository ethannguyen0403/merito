package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.controls.Cell;
import agentsite.pages.agentmanagement.EditDownLinePage;
import com.paltech.element.common.Link;
import com.paltech.utils.StringUtils;
import org.testng.Assert;


import java.util.List;

import static baseTest.BaseCaseTest._brandname;
import static baseTest.BaseCaseTest.environment;
import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL;
import static common.AGConstant.AgencyManagement.DownlineListing.*;
import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.DOWNLINE_LISTING;

public class NewUIDownlineListing extends DownlineListing {
    public NewUIDownlineListing(String types) {
        super(types);
    }
//    private int userCodeCol = 2;

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        editCol = getHeaderIndexValue("Edit");
        Cell cellValue = tblDowlineListing.getCellByName(loginID, false);
        int userCodeCol = Integer.parseInt(cellValue.getAttribute("cellIndex")) + 1;
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
        }
        if (inputSecurityCode) {
            try {
                confirmSecurityCode(environment.getSecurityCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        waitingLoadingSpinner();
        return new EditDownLinePage(_brandname);
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return clickEditIcon(loginID, true);
    }

    public void verifyUIDisplayCorrect() {
        List<String> lstHeaderTable = tblDowlineListing.getHeaderNameOfRows();
        lstHeaderTable.remove(0);
        lstHeaderTable.remove(0);
        Assert.assertEquals(header.lblPageTitle.getText(), DOWNLINE_LISTING, "FAILED! Page title is incorrect displayed");
        Assert.assertEquals(lblLoginId.getText(), LBL_USERNAME, "FAILED! Login ID is incorrect displayed");
        Assert.assertEquals(lblAccountStatus.getText(), ACCOUNT_STATUS, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(lblLevel.getText(), LEVEL, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(btnSearch.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect display");
        Assert.assertTrue(txtLoginID.isDisplayed(), "FAILED! Login ID textbox is incorrect display");
        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdownbox is incorrect display");
        Assert.assertTrue(ddbLevel.isDisplayed(), "FAILED! Level dropdown is incorrect display");
        Assert.assertEquals(ddbAccountStatus.getOptions(), LST_ACCOUNT_STATUS, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(ddbLevel.getOptions(), DDB_LEVEL, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(lstHeaderTable.remove(0), LST_DOWLINE_LISTING_TABLE_HEADER_NEWUI, "FAILED! Table header is incorrect displayed");
    }
}
