package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.controls.Cell;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static agentsite.pages.HomePage.waitingLoadingSpinner;
import static baseTest.BaseCaseTest._brandname;
import static baseTest.BaseCaseTest.log;
import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL;
import static common.AGConstant.AgencyManagement.DownlineListing.LST_ACCOUNT_STATUS;
import static common.AGConstant.AgencyManagement.DownlineListing.LST_DOWLINE_LISTING_TABLE_HEADER_OLDUI;
import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.DOWNLINE_LISTING;
import static agentsite.pages.agentmanagement.DownLineListingPage.*;

public class OldUIDownlineListing extends DownlineListing {
    private int accountStatusCol = 7;
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[contains(@class, 'title')]");
    private Button btnOK = Button.xpath("//button[text()='Ok']");
    private int changePasswordCol = 9;
//    private int userCodeCol = 3;

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        if(loginID.isEmpty()) {
            log("DEBUG: login ID is empty cannot detect index to click Edit");
            return null;
        }
        editCol = getHeaderIndexValue("Edit");
        Cell cellValue = tblDowlineListing.getCellByName(loginID, false);
        int userCodeCol = Integer.parseInt(cellValue.getAttribute("cellIndex")) + 1;
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
            waitingLoadingSpinner();
        }
        return new EditDownLinePage(_brandname);
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return clickEditIcon(loginID, false);
    }

    public void verifyUIDisplayCorrect() {
        List<String> lstHeaderTable = tblDowlineListing.getHeaderNameOfRows();

        Assert.assertEquals(lblPageTitle.getText(), DOWNLINE_LISTING, "FAILED! Page title is incorrect displayed");
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

    public SuccessPopup updateAccountStatus(String userCode, String status) {
        int userCodeIndex = getUserCodeIndex(userCode);
        if (userCodeIndex == 0) {
            System.out.println(String.format("DEBUG! There is no usercode %s in the table", userCode));
            return null;
        }
        DropDownBox ddbAccountStatusByUserCode = DropDownBox.xpath(
                tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(
                        userCode, 1, userCodeCol, userCodeIndex, null, 7, "select[contains(@class,'com-status')]", false, false));
        Button icSave = Button.xpath(tblDowlineListing.getxPathOfCell(1, 7, userCodeIndex, "i[contains(@class,'ico-save')]"));
        ddbAccountStatusByUserCode.selectByVisibleContainsText(status);
        icSave.click();
        return SuccessPopup.xpath("//app-comfirm");
    }

    public boolean isAccountStatusCorrect(String userCode, String expectedStatus) {
        DropDownBox ddbAccountStatusByUserCode = getAccountStatusDropdown(userCode);
        String actualStatus = ddbAccountStatusByUserCode.getFirstSelectedOption();
        if (!actualStatus.equals(expectedStatus)) {
            System.out.println(String.format("FAILED! Expected satus of the account %s is %s but found %s", userCode, expectedStatus, actualStatus));
            return false;
        }
        return true;
    }

    public DropDownBox getAccountStatusDropdown(String userCode) {
        int userCodeIndex = getUserCodeIndex(userCode);
        if (userCodeIndex == 0) {
            System.out.println(String.format("DEBUG! There is no usercode %s in the table", userCode));
            return null;
        }
        return DropDownBox.xpath(
                tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(
                        userCode, 1, userCodeCol, userCodeIndex, null, accountStatusCol, "select[contains(@class,'com-status')]", false, false));
    }

    public List<String> getAccountStatus() {
        List<String> lstAccountStatus = new ArrayList<>();
        int index = 1;
        DropDownBox ddbAccountStatus;
        while (true) {
            ddbAccountStatus = DropDownBox.xpath(tblDowlineListing.getxPathOfCell(1, accountStatusCol, index, "select[contains(@class,'com-status')]"));
            if (!ddbAccountStatus.isDisplayed()) {
                return lstAccountStatus;
            }
            lstAccountStatus.add(ddbAccountStatus.getFirstSelectedOption());
            index = index + 1;
        }
    }

    public String getAccountStatus(String userCode) {
        int userCodeIndex = getUserCodeIndex(userCode);
        if (userCodeIndex == 0) {
            System.out.println(String.format("DEBUG! There is no usercode %s in the table", userCode));
            return null;
        }
        String xpath = tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(
                userCode, 1, userCodeCol, userCodeIndex, null, accountStatusCol, "select[contains(@class,'com-status')]", false, false);
        //handle when userCode input is loginId
        if(Objects.isNull(xpath)) {
            xpath = tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(
                    userCode, 1, loginIDCol, userCodeIndex, null, accountStatusCol, "select[contains(@class,'com-status')]", false, false);
        }
        DropDownBox ddpAccountStatus = DropDownBox.xpath(xpath);
        if (ddpAccountStatus.isDisplayed())
            return ddpAccountStatus.getFirstSelectedOption().trim();
        else
            return Label.xpath(tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(userCode, 1, userCodeCol, userCodeIndex, null, accountStatusCol, null, false, false)).getText().trim();

    }

    public String changePassword(String loginID, String newPassword) throws InterruptedException {
        tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, changePasswordCol, "a", false, false).click();
        ChangePasswordPopup popup = new ChangePasswordPopup();
        return popup.changePassword(newPassword, newPassword);
    }

    public void closeSubmitEditDownlinePopup() {
        if (btnOK.isClickable(1)) {
            btnOK.click();
        }
        waitingLoadingSpinner();
    }
}
