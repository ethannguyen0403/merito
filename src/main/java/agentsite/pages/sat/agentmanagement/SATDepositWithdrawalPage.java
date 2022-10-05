package agentsite.pages.sat.agentmanagement;

import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import com.paltech.element.common.Label;
import com.paltech.utils.DoubleUtils;
import org.openqa.selenium.Keys;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.depositwithdrawal.DepositToPopup;
import agentsite.pages.all.agentmanagement.depositwithdrawal.ViewLogPopup;
import agentsite.pages.all.agentmanagement.depositwithdrawal.WithdrawalPopup;
import agentsite.ultils.account.ProfileUtils;

import java.util.List;
import java.util.Objects;
import java.util.Locale;

import static agentsite.pages.all.agentmanagement.DepositWithdrawalPage.Actions.WITHDRAWAL;

public class SATDepositWithdrawalPage extends DepositWithdrawalPage {
    public TextBox txtUsername = TextBox.xpath("//th[contains(@class,'nick-name-field')]//input");
    private int totalCol = 16;
    public int colUsername = 2;
    public int colAvailableBalance = 11;
    int colLog = 15;
    int colLoginID = 3;
    int colCheckAll = 5;
    int colTransfer = 14;
    private int colUpdateStatus = 16;
    public Table tblAccountBalance = Table.xpath("(//table[@class='ptable report'])[1]", 4);
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("(//table[contains(@class,'ptable report table-responsive')])//th[" + colAccountStatus + "]//select");
    public DropDownBox ddbLevel = DropDownBox.xpath("(//table[contains(@class,'ptable report table-responsive')])//th[" + colLevel + "]//select");
    public Table tblWithdrawalDeposit = Table.xpath("//table[contains(@class,'ptable report table-responsive')]", totalCol);
    public Label lblLoginAccountAvailableBalance = Label.xpath("//table[@class='ptable report'][1]//td[4]");

    @Override
    public void filter(String username, String accountStatus, String level) {
        if (!username.isEmpty()) {
            txtUsername.sendKeys(username);
            txtUsername.sendKeys(Keys.ENTER);
        }
        if (!accountStatus.isEmpty()) {
            ddbAccountStatus.selectByVisibleText(accountStatus);
        }
        if (!level.isEmpty()) {
            ddbLevel.selectByVisibleText(level);
        }
        waitingLoadingSpinner();
    }

    @Override
    public int defineDepositWithdrawTableColumn(String colName){
        String level = ProfileUtils.getProfile().getLevel();
         if(level.equalsIgnoreCase("PO")){
             colUsername = colLog +1;
             colTransfer = colTransfer +1;
         }
        switch (colName) {
            case "User Name":
                return colUsername;
            case "Transfer":
                return colTransfer;
            case "Log":
                return colLog;
            case "Login ID":
                return colLoginID;
            case "CheckBox":
                return colCheckAll;
            case "Status":
                return colUpdateStatus;
        }
        return 0;
    }

   @Override
    public  Object action(Actions type, int rowIndex) {
        if (rowIndex == 0)
            return null;
        Link lnk;
        switch (type) {
            case CHECK:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, defineDepositWithdrawTableColumn("CheckBox"), rowIndex, "input[@id='cItem']");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get checkbox at Check All column");
                    return null;
                }
                lnk.isClickable(2);
                lnk.click();
                return null;
            case DEPOSIT:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, defineDepositWithdrawTableColumn("Transfer"), rowIndex, "a[1]");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get Deposit link at Transfer column");
                    return null;
                }
                lnk.isClickable(2);
                lnk.click();
                DepositToPopup popup = new DepositToPopup();
                popup.lblYourBalance.isDisplayed();
                return popup;
            case WITHDRAWAL:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, defineDepositWithdrawTableColumn("Transfer"), rowIndex, "a[2]");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get WITHDRAWAL link at Transfer column");
                    return null;
                }
                lnk.isClickable(2);
                lnk.click();
                return new WithdrawalPopup();
            case USERNAME:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, defineDepositWithdrawTableColumn("User Name"), rowIndex, "span[@class='downline']");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get down-line link at Username column");
                    return null;
                }
                lnk.isClickable(2);
                lnk.click();
                return  tblWithdrawalDeposit;
            case SUCCESS_ICON:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, defineDepositWithdrawTableColumn("Status"), rowIndex, "span[contains(@class,'psuccess')]");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get successful icon at Update Status column");
                    return false;
                }
                return lnk.isDisplayedShort(2);

            case FAILURE_ICON:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, defineDepositWithdrawTableColumn("Status"), rowIndex, "span[contains(@class,'perror')]");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get failure icon at Update Status column");
                    return false;
                }
                return lnk.isDisplayedShort(2);
            case VIEW_LOG:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, defineDepositWithdrawTableColumn("Log"), rowIndex, "a[@class='action']");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get failure icon at Update Status column");
                    return false;
                }
                lnk.isClickable(2);
                lnk.click();
                ViewLogPopup popupViewLog = new ViewLogPopup();
                popupViewLog.tblLog.isDisplayed();
                return popupViewLog;
        }
        return null;
    }

    @Override
    public int getRowIndex(String usercode) {
        List<String> lstUsername = tblWithdrawalDeposit.getColumn(colUsername, false);
        for (int i = 0; i < lstUsername.size(); i++) {
            if (lstUsername.get(i).equals(usercode)) {
                return i + 1;
            }
        }
        System.out.println(String.format("Usercode %s not display in the list", usercode));
        return 0;
    }

    @Override
    public Object action(Actions type, String username) {
        int rowIndex = getRowIndex(username);
        return action(type, rowIndex);
    }

    @Override
    public String deposit(String usercode, String amount, String remark, boolean isCreditUpdate,boolean isClose){
       // DepositToPopup popup =  (DepositToPopup)action(DEPOSIT, usercode);
        DepositToPopup popup = (DepositToPopup)action(DepositWithdrawalPage.Actions.DEPOSIT, usercode);
        popup.deposit(amount, remark,isCreditUpdate,true);
        String messsage = popup.getMessage();
        if(isClose)
            popup.clickXIcon();
        return messsage;
    }

    @Override
    public String withdraw(String usercode, String amount, String remark, boolean isCreditUpdate,boolean isClose){
        WithdrawalPopup popup =  (WithdrawalPopup)action(WITHDRAWAL, usercode);
        popup.withdraw(amount, remark,isCreditUpdate);
        String messsage = popup.lblMessage.getText().trim();;
        if(isClose)
            popup.clickXIcon();
        return messsage;
    }

    @Override
    public boolean verifyBalanceUpdated(double amount, double mainBalance, Actions transactionType) {
        double expectedMainBalance;
        if (transactionType.equals(Actions.DEPOSIT)) {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBalance) + amount;
        } else {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBalance) - amount;
        }
        if (lblLoginAccountAvailableBalance.getText().trim().contains(String.format(Locale.getDefault(), "%,.2f", expectedMainBalance))) {
            System.out.println(String.format("PASSED! Main Available Balance correct Actual is %s expected is %s",
                    lblLoginAccountAvailableBalance.getText(), String.format(Locale.getDefault(), "%,.2f", expectedMainBalance)));
            return true;
        }
        return false;
    }
}
