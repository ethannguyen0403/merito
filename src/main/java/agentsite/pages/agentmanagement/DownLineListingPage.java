package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.downlinelisting.ChangePasswordPopup;
import agentsite.pages.agentmanagement.downlinelisting.DownlineListing;
import agentsite.pages.agentmanagement.downlinelisting.EditDownlinePopup;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.common.*;

import java.util.List;

public class DownLineListingPage extends CreateDownLineAgentPage {
    public static TextBox txtLoginID = TextBox.id("username");
    public static DropDownBox ddbAccountStatus = DropDownBox.id("status");
    public static DropDownBox ddbLevel = DropDownBox.id("userLevel");
    public static Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public static Label lblLoginId = Label.xpath("//label[@for='username']");
    public static Label lblAccountStatus = Label.xpath("//label[@for='status']");
    public static Label lblLevel = Label.xpath("//label[@for='userLevel']");
    private static int totalColumn = 19;
    public int loginIDCol = 3;
    public static int changePasswordCol = 9;
    public static int userCodeCol = 2;
    public static int accountStatusCol = 4;
    public static int editCol;

    public static Table tblDowlineListing = Table.xpath("//table[contains(@class,'ptable report')]", totalColumn);
    public Label lblErrorMsg = Label.xpath("//div[@class='paction']/span[@id='error-msg']");
    public Label lblNoRecord = Label.xpath("//table[contains(@class,'ptable report')]//span[contains(@class,'no-record')]");
    public EditDownlinePopup editDownlinePopup;
    public DownlineListing downlineListing;
    public DownLineListingPage(String type) {
        super(type);
        _type = type;
        downlineListing = ComponentsFactory.downlineListingPage(_type);
    }

    public void searchDownline(String loginId, String accountStatus, String level) {
        if (!loginId.isEmpty())
            txtLoginID.sendKeys(loginId);
        if (!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if (!level.isEmpty())
            ddbLevel.selectByVisibleText(level);
        btnSearch.click();
        waitingLoadingSpinner();
    }

    public String changePassword(String loginID, String newPassword) throws InterruptedException {
        tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, changePasswordCol, "a", false, false).click();
        ChangePasswordPopup popup = new ChangePasswordPopup();
        return popup.changePassword(newPassword, newPassword);
    }

    public void clickUserName(String userName) {
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(userName, 1, userCodeCol, 1, null, userCodeCol, "span[contains(@class,'downline')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
        }
        waitingLoadingSpinner();
    }

    public String getAccountStatus(String userCode) {
        return downlineListing.getAccountStatus(userCode);
    }


    public static int getUserCodeIndex(String userCode) {
        int index = 1;
        while (true) {
            Link lnkUserCode = (Link) tblDowlineListing.getControlOfCell(1, userCodeCol, index, null);
            if (!lnkUserCode.isDisplayed())
                return 0;
            String userCodeValue = lnkUserCode.getText().trim();
            if (userCodeValue.equals(userCode)) {
                return index;
            }
            index = index + 1;
        }
    }

    public void submitEditDownline() {
        downlineListing.submitEditDownline();
    }

    public String getMessageUpdate(boolean isClose) {
        SuccessPopup successPopup;
        String message;
        switch (_type) {
            case "satsport":
                successPopup = SuccessPopup.xpath("//app-comfirm");
                message = successPopup.getContentMessage();
                break;
            default:
                successPopup = SuccessPopup.xpath("//app-alert");
                message = successPopup.getContentMessage();
                break;
        }
        if (isClose) {
            successPopup.close();
        }
        return message;
    }

    public SuccessPopup updateAccountStatus(String userCode, String status) {
        return downlineListing.updateAccountStatus(userCode, status);
    }

    public boolean isAccountStatusCorrect(String userCode, String expectedStatus) {
        return downlineListing.isAccountStatusCorrect(userCode, expectedStatus);
    }

    public DropDownBox getAccountStatusDropdown(String userCode) {
        return downlineListing.getAccountStatusDropdown(userCode);
    }

    public List<String> getAccountStatus() {
        return downlineListing.getAccountStatus();
    }

    public String getBreadcrumb() {
        String breadcrumb = "";
        Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']/span[contains(@class,'ng-star-inserted')]");
        int n = lblBreadcrumb.getWebElements().size();
        for (int i = 0; i < n; i++) {
            Label lblSlash = Label.xpath(String.format("%s[%d]%s", "//span[@class='my-breadcrumb']/span[contains(@class,'ng-star-inserted')]", i + 1, "//span[@class='ng-star-inserted']"));
            if (lblSlash.isDisplayed())
                breadcrumb = String.format(" %s%s", breadcrumb, lblSlash.getText());
            Label lblBreadcrumbi = Label.xpath(String.format("%s[%d]%s ", "//span[@class='my-breadcrumb']/span[contains(@class,'ng-star-inserted')]", i + 1, "//span[@class='downline']"));
            breadcrumb = breadcrumb + lblBreadcrumbi.getText();
        }
        return breadcrumb.trim();
    }

    public void closeSubmitEditDownlinePopup() {
        downlineListing.closeSubmitEditDownlinePopup();
    }

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        return downlineListing.clickEditIcon(loginID, inputSecurityCode);
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return downlineListing.clickEditIcon(loginID);
    }

    public static int getHeaderIndexValue(String headerName) {
        int cellIndex = 0;
        List<String> lstHeaderList = tblDowlineListing.getHeaderList();
        for (int i = 0; i < lstHeaderList.size(); i++) {
            if (headerName.equals(lstHeaderList.get(i))) {
                cellIndex = i + 1;
                return cellIndex;
            }
        }
        return cellIndex;
    }

    public void verifyUIDisplayCorrect() {
        downlineListing.verifyUIDisplayCorrect();
    }
}
