package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.controls.Cell;
import agentsite.pages.agentmanagement.EditDownLinePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

import static agentsite.pages.HomePage.waitingLoadingSpinner;
import static agentsite.pages.agentmanagement.DownLineListingPage.*;
import static baseTest.BaseCaseTest._brandname;
import static baseTest.BaseCaseTest.environment;
import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL;
import static common.AGConstant.AgencyManagement.DownlineListing.*;
import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.DOWNLINE_LISTING;

public class NewUIDownlineListing extends DownlineListing {
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[contains(@class, 'title')]");
    private Button btnOK = Button.xpath("//button[text()='OK']");
    private int changePasswordCol = 6;
//    private int userCodeCol = 2;

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        editCol = getHeaderIndexValue("Edit");
        Cell cellValue = tblDowlineListing.getCellByName(loginID, false);
        int userCodeCol = Integer.parseInt(cellValue.getAttribute("cellIndex")) + 1;
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (inputSecurityCode) {
            confirmSecurityCode(environment.getSecurityCode());
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
        Assert.assertEquals(lblPageTitle.getText(), DOWNLINE_LISTING, "FAILED! Page title is incorrect displayed");
        Assert.assertEquals(lblLoginId.getText(), LBL_USERNAME, "FAILED! Login ID is incorrect displayed");
        Assert.assertEquals(lblAccountStatus.getText(), ACCOUNT_STATUS, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(lblLevel.getText(), LEVEL, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(btnSearch.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect display");
        Assert.assertTrue(txtLoginID.isDisplayed(), "FAILED! Login ID textbox is incorrect display");
        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdownbox is incorrect display");
        Assert.assertTrue(ddbLevel.isDisplayed(), "FAILED! Level dropdown is incorrect display");
        Assert.assertEquals(ddbAccountStatus.getOptions(), LST_ACCOUNT_STATUS, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(ddbLevel.getOptions(), DDB_LEVEL, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(lstHeaderTable, LST_DOWLINE_LISTING_TABLE_HEADER_NEWUI, "FAILED! Table header is incorrect displayed");
    }

    public List<String> getAccountStatus() {
        List<String> lstAccountStatus = new ArrayList<>();
        int index = 1;
        Label lblAccountStatus;
        while (true) {
            lblAccountStatus = Label.xpath(tblDowlineListing.getxPathOfCell(1, accountStatusCol, index, null));
            if (!lblAccountStatus.isDisplayed()) {
                return lstAccountStatus;
            }
            lstAccountStatus.add(lblAccountStatus.getText());
            index = index + 1;
        }
    }

    public String getAccountStatus(String userCode) {
        int userCodeIndex = getUserCodeIndex(userCode);
        if (userCodeIndex == 0) {
            System.out.println(String.format("DEBUG! There is no usercode %s in the table", userCode));
            return null;
        }
        DropDownBox ddpAccountStatus = DropDownBox.xpath(
                tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(
                        userCode, 1, userCodeCol, userCodeIndex, null, accountStatusCol, "select[contains(@class,'com-status')]", false, false));
        if (ddpAccountStatus.isDisplayed())
            return ddpAccountStatus.getFirstSelectedOption().trim();
        else
            return Label.xpath(tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(userCode, 1, userCodeCol, userCodeIndex, null, accountStatusCol, null, false, false)).getText().trim();

    }

    public void closeSubmitEditDownlinePopup() {
        if (btnOK.isClickable(1)) {
            btnOK.click();
        }
        waitingLoadingSpinner();
    }

    public String changePassword(String loginID, String newPassword) throws InterruptedException {
        tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, changePasswordCol, "a", false, false).click();
        ChangePasswordPopup popup = new ChangePasswordPopup();
        return popup.changePassword(newPassword, newPassword);
    }
}
