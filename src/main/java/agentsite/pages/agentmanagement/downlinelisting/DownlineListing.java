package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.components.SecurityPopup;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.common.*;
import java.util.List;


public class DownlineListing extends CreateDownLineAgentPage {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");

    public TextBox txtLoginID = TextBox.id("username");
    public DropDownBox ddbAccountStatus = DropDownBox.id("status");
    public DropDownBox ddbLevel = DropDownBox.id("userLevel");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public Button btnSubmit = Button.id("submitBtn");
    public Label lblLoginId = Label.xpath("//label[@for='username']");
    public Label lblAccountStatus = Label.xpath("//label[@for='status']");
    public Label lblLevel = Label.xpath("//label[@for='userLevel']");
    private int totalColumn = 19;
    public int changePasswordCol = 9;
    public int userCodeCol = 2;
    public int accountStatusCol = 4;
    public int editCol = 8;
    public Table tblDowlineListing = Table.xpath("//table[contains(@class,'ptable report')]", totalColumn);

    public DownlineListing(String types) {
        super(types);
    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 4);
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
        return null;
    }


    protected int getUserCodeIndex(String userCode) {
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
    }

//    public void confirmSecurityCode(String securityCode) {
//        if (securityPopup.isDisplayed()) {
//            if (!securityCode.isEmpty()) {
//                securityPopup.submitSecurityCode(securityCode);
//            }
//        }
//    }

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
        return null;
    }

    public boolean isAccountStatusCorrect(String userCode, String expectedStatus) {
        return false;
    }

    public DropDownBox getAccountStatusDropdown(String userCode) {
        return null;
    }

    public List<String> getAccountStatus() {
        return null;
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
    }

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        return null;
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return null;
    }

    protected int getHeaderIndexValue(String headerName) {
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
    }
}
