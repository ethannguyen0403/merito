package agentsite.pages.agentmanagement.creditbalancelisting.creditbalancelisting;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.creditbalancelisting.EditCreditSettingPopup;
import com.paltech.element.common.*;

public class CreditBalanceListing extends HomePage {
    protected int colUsername = 2;
    protected int colEdit = 5;
    protected int colVailabaleBalance = 18;
    public TextBox txtUserName = TextBox.id("userName");
    public DropDownBox ddpAccountStatus = DropDownBox.id("status");
    public DropDownBox ddpLevel = DropDownBox.id("userLevel");
    public Button btnSubmit = Button.xpath("//button[@class='pbtn search']");
    public Table tblAccountList = Table.xpath("//table[contains(@class,'report')]", 22);

    public CreditBalanceListing(String types) {
        super(types);
    }

    public int getRowIndexofUserName(String username) {
        int i = 1;
        String xpath = tblAccountList.getxPathOfCell(1, colUsername, i, null);
        Label lblUserName = Label.xpath(xpath);
        while (true) {
            if (!lblUserName.isDisplayed()) {
                System.out.println("DEBUG! Username " + username + " does not exist in the table");
                return 0;
            }
            xpath = tblAccountList.getxPathOfCell(1, colUsername, i, null);
            lblUserName = Label.xpath(xpath);
            if (lblUserName.getText().trim().equals(username)) {
                return i;
            }
            i = i + 1;
        }
    }
    public AccountInfo getCreditInfoAccount(String userName) {
        return null;
    }

    public EditCreditSettingPopup clickEditIcon(String username) {
        int i = getRowIndexofUserName(username);
        if (i == 0) {
            return null;
        }
        String xpath = tblAccountList.getxPathOfCell(1, colEdit, i, "a[@class='pedit']");
        Icon icEdit = Icon.xpath(xpath);
        icEdit.click();
        return new EditCreditSettingPopup();
    }

    public String getBalance(String username) {
        int i = getRowIndexofUserName(username);
        String xpath = tblAccountList.getxPathOfCell(1, colVailabaleBalance, i, null);
        Label lblBalance = Label.xpath(xpath);
        return lblBalance.getText().trim();
    }

    public void filter(String username, String accountStatus, String level) {
        if (!username.isEmpty())
            txtUserName.sendKeys(username);
        if (!accountStatus.isEmpty())
            ddpAccountStatus.selectByVisibleText(accountStatus);
        if (!level.isEmpty())
            ddpLevel.selectByVisibleText(level);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void updateCreditSetting(String username, String creditGiven, String maxCredit, String memberMaxCredit) {
        EditCreditSettingPopup popup = clickEditIcon(username);
        popup.editCreditSetting(creditGiven, maxCredit, memberMaxCredit);
    }
}
