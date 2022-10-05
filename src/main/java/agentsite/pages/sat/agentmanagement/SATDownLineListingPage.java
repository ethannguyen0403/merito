package agentsite.pages.sat.agentmanagement;

import com.paltech.element.common.*;
import agentsite.controls.Table;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.pages.all.agentmanagement.EditDownLinePage;
import agentsite.pages.all.agentmanagement.createdownlineagent.CashBalanceSection;
import agentsite.pages.all.agentmanagement.downlinelisting.ChangePasswordPopup;
import agentsite.pages.all.agentmanagement.downlinelisting.EditDownlinePopup;
import agentsite.pages.all.components.SecurityPopup;
import agentsite.pages.all.components.SuccessPopup;
import agentsite.pages.oldUI.agentmanagement.EditDownLinePageOldUI;

public class SATDownLineListingPage extends DownLineListingPage {
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public EditDownlinePopup editDownlinePopup = EditDownlinePopup.xpath("//app-agency-edit");
    public SuccessPopup successPopup = SuccessPopup.xpath("//app-alert");
    public TextBox txtLoginID = TextBox.id("username");
    public DropDownBox ddbAccountStatus = DropDownBox.id("status");
    public DropDownBox ddbLevel = DropDownBox.id("userLevel");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public Button btnSubmit = Button.id("submitBtn");
    public int userCodeCol = 2;
    public int loginIDCol = 3;
    public int changePasswordCol = 8;
    public int editCol = 7;
    public int levelCol = 9;
    public int delayBetCol = 10;
    public int accountStatusCol = 6;
    public Label lblErrorMsg = Label.xpath("//div[@class='paction']/span[@id='error-msg']");
    // Cash Balance Section
    public CashBalanceSection cashBalanceSection = CashBalanceSection.xpath("//div[@id='credit-balance-setting']//app-credit-setting-exchange");
    private Image imgSpin = Image.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    private int totalColumn = 19;
    public Table tblDowlineListing = Table.xpath("//table[contains(@class,'ptable report')]", totalColumn);

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

    public EditDownLinePage clickEditIcon(String loginID) {
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
            waitingLoadingSpinner();
        }
        return new EditDownLinePage();
    }

    public String getAccountStatus(String loginID) {
        String xPath = tblDowlineListing.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, accountStatusCol, null, false, false);
        Label lblAccountStatus = Label.xpath(xPath);
        return lblAccountStatus.getText().trim();
    }

    public void submitEditDownline() {
        editDownlinePopup.btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void confirmSecurityCode(String securityCode) {
        securityPopup.submitSecurityCode(securityCode);
        waitingLoadingSpinner();
    }

    public String getMessageUpdate(boolean isClose) {
        String message = successPopup.getContentMessage();
        if (isClose) {
            successPopup.close();
        }
        return message;
    }

    public Object clickEditIconOldUI(String loginID, String securityCode) {

        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            if (securityCode.isEmpty()) {
                return securityPopup;
            }
            lnkEdit.click();
            if (securityPopup.isDisplayed()) {
                confirmSecurityCode(securityCode);
            }
            return new EditDownLinePageOldUI();
        }
        return null;
    }


}
