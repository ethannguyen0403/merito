package agentsite.pages.agentmanagement.depositwithdrawal;

import agentsite.controls.Table;
import agentsite.pages.components.SecurityPopup;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.element.common.*;
import com.paltech.utils.DoubleUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class DepositWithdraw {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public TextBox txtUsername = TextBox.xpath("//th[contains(@class,'nick-name-field')]//input | //input[@id='userName']");
    public Button btnSubmit = Button.xpath("//table[contains(@class,'ptable info')]//button[@class='pbtn search']");
    public Table tblWithdrawalDeposit = Table.xpath("//table[contains(@class,'ptable report table-responsive')]", 13);
    public Table tblAccountBalance = Table.xpath("(//table[@class='ptable report'])[1]", 4);
    public CheckBox chkAll = CheckBox.id("cAll");
    private Button btnDeposit = Button.id("bntUpdateDeposit");
    private Button btnWithdraw = Button.id("bntUpdateWithdraw");
    public DropDownBox ddbAccountStatus = DropDownBox.id("status");
    public DropDownBox ddbLevel = DropDownBox.id("userLevel");
    private String downlineXPath = "//span[text()='%s']";
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public Label lblNoRecord = Label.xpath("//span[contains(@class,'no-record')]");
    protected int colUsername = 2;
    public int colLevel = 7;
    public int colAccountStatus = 6;
    private int colLog = 15;
    private int colTransfer = 12;
    private int colLoginID = 3;
    private int colCheckAll = 5;
    private int colSubBalanceHeader = 3;
    private int colMainAvailableBalance = 4;
    public int colCreditInitiation = 8;
    public int colTotalBalance = 9;
    public int colWinloss = 12;
    private int colExposure = 13;
    public int colAvailableBalance = 11;
    public int colTotalBalanceHeader = 2;

    public boolean isTotalBalanceCalculatedCorrect() {
        return false;
    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getLabelText(String controlName) {
        return "";
    }

    public void filter(String username, String accountStatus, String level) {
    }

    public Object action(DepositWithdraw.Actions type, int rowIndex) {
        return null;
    }

    public Object action(DepositWithdraw.Actions type, String username) {
        int rowIndex = getRowIndex(username);
        return action(type, rowIndex);
    }

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

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstMember, boolean status) {
        for (int i = 0; i < lstMember.size(); i++) {
            boolean result = (boolean) action(DepositWithdraw.Actions.SUCCESS_ICON, lstMember.get(i).get(colUsername - 1));
            if (result != status) {
                System.out.println(String.format("Expected update status of username %s is but % found %s", lstMember.get(i).get(colUsername - 1), status, result));
                return false;
            }
        }
        return true;
    }

    public boolean verifyBalanceUpdated(double amount, double mainBlance, List<ArrayList<String>> lstDownlineBefore, List<ArrayList<String>> lstDownlineAfter, boolean isDeposit, DepositWithdraw.Actions statusType) {
        return false;
    }

    public boolean isUpdateStatusSuccess(String username) {
        return false;
    }

    public List<ArrayList<String>> getLoginAccountBalanceInfo() {
        return null;
    }

    public List<ArrayList<String>> getMemberInfo(int memberNumber) {
        return tblWithdrawalDeposit.getRowsWithoutHeader(memberNumber, false);
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

    public void clickDownline(String downlineAcount) {
        Label.xpath(String.format(downlineXPath, downlineAcount)).click();
        waitingLoadingSpinner();
    }

    public String deposit(String usercode, String amount, String remark, boolean isCreditUpdate, boolean isClose) {
        DepositToPopup popup = (DepositToPopup) action(DepositWithdraw.Actions.DEPOSIT, usercode);
        popup.deposit(amount, remark, isCreditUpdate, true);
        String messsage = popup.lblMessage.getText().trim();
        ;
        if (isClose)
            popup.clickXIcon();
        return messsage;
    }

    public String withdraw(String usercode, String amount, String remark, boolean isCreditUpdate, boolean isClose) {
        WithdrawalPopup popup = (WithdrawalPopup) action(DepositWithdraw.Actions.WITHDRAWAL, usercode);
        popup.withdraw(amount, remark, isCreditUpdate, true);
        String messsage = popup.lblMessage.getText().trim();
        if (isClose)
            popup.clickXIcon();
        return messsage;
    }

    public double getDataByColumn(String username, int columnIndex) {
        String value = tblWithdrawalDeposit.getControlBasedValueOfDifferentColumnOnRow(username, 1, colUsername, 1, null, columnIndex, null, true, false).getText();
        double returnValue = 0;
        try {
            returnValue = DecimalFormat.getNumberInstance().parse(value).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e.getCause());
        }
        return returnValue;
    }

    public int defineDepositWithdrawTableColumn(String colName) {
        String level = ProfileUtils.getProfile().getLevel();
        if (level.equalsIgnoreCase("PO")) {
            colUsername = colLog + 1;
            colTransfer = colTransfer + 1;
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
        }
        return 0;
    }

    public void selectUser(String userName) {
        String cbCheck = tblWithdrawalDeposit.getControlxPathBasedValueOfDifferentColumnOnRow(userName, 1, colUsername, 1, null, colCheckAll, "input", false, false);
        if (CheckBox.xpath(cbCheck).isDisplayed()) {
            System.out.println("Select username: " + userName);
            CheckBox.xpath(cbCheck).click();
        }
    }

    public void selectUser(List<ArrayList<String>> listDownlineInfo) {
        for (int i = 0; i < listDownlineInfo.size(); i++) {
            action(DepositWithdraw.Actions.CHECK, i + 1);
        }
    }

    public void selectAllCheckBoxes() {
        chkAll.click();
    }

    public DepositPopup openDepositPopup() {
        btnDeposit.click();
        return new DepositPopup();
    }

    public WithdrawalPopup openWithdrawalPopup() {
        btnWithdraw.click();
        return new WithdrawalPopup();
    }

    public boolean verifyFilterByLevel(String actual, String expected) {
        if (expected.equals("Member"))
            return actual.equals(expected);
        return true;
    }

    public List<String> getRowContainsUsercode(String usercode) {
        List<ArrayList<String>> lstData = tblWithdrawalDeposit.getRowsWithoutHeader(false);
        for (int i = 0; i < lstData.size(); i++)
            if (lstData.get(i).get(colUsername - 1).contains(usercode))
                return lstData.get(i);
        System.out.println(String.format("Usercode not exit in the list", usercode));
        return null;

    }

    public List<ArrayList<String>> calculateMainAccountInfo(List<ArrayList<String>> lstBalanceInfo, double totalAmount, boolean isDeposit) {
        try {
            List<ArrayList<String>> newList = lstBalanceInfo;
            //  double availableBalance;
            double subBalance = DoubleUtils.parseDouble(newList.get(0).get(colSubBalanceHeader - 1));
            double availableBalance = DoubleUtils.parseDouble(newList.get(0).get(colMainAvailableBalance - 1));
            if (isDeposit) {
                subBalance = subBalance + totalAmount;
                availableBalance = availableBalance - totalAmount;
            } else {
                subBalance = subBalance - totalAmount;//Double.parseDouble(newList.get(0).get(colSubBalanceHeader - 1)) - totalAmount;
                availableBalance = availableBalance + totalAmount;//Double.parseDouble(newList.get(0).get(colMainAvailableBalance - 1)) + totalAmount;
            }
            newList.get(0).set(colSubBalanceHeader - 1, String.format(Locale.getDefault(), "%,.2f", subBalance));
            newList.get(0).set(colMainAvailableBalance - 1, String.format(Locale.getDefault(), "%,.2f", availableBalance));
            return newList;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<ArrayList<String>> calculateDownlineBalanceInfo(List<ArrayList<String>> lstMember, double amount, boolean isDeposit, boolean isCreditUpdate) {
        List<ArrayList<String>> newList = lstMember;
        double creditInitiation;
        double totalBalance;
        double subBalance;
        double availableBalance;
        double winloss;
        double exposure;

        for (int i = 0; i < newList.size(); i++) {
            creditInitiation = Double.parseDouble(newList.get(i).get(colCreditInitiation - 1));
            totalBalance = Double.parseDouble(newList.get(i).get(colTotalBalance - 1));
            availableBalance = Double.parseDouble(newList.get(i).get(colAvailableBalance - 1));
            winloss = Double.parseDouble(newList.get(i).get(colWinloss - 1));
            exposure = Double.parseDouble(newList.get(i).get(colExposure - 1));
            if (isDeposit) {
                totalBalance = totalBalance + amount;
                availableBalance = availableBalance + amount;
                if (isCreditUpdate)
                    creditInitiation = creditInitiation + amount;
                else
                    winloss = winloss + amount;
            } else {
                totalBalance = totalBalance - amount;
                availableBalance = availableBalance - amount;
                if (isCreditUpdate)
                    creditInitiation = creditInitiation - amount;
                else
                    winloss = winloss - amount;
            }
            newList.get(i).set(colCreditInitiation - 1, String.format("%.2f", creditInitiation));
            newList.get(i).set(colTotalBalance - 1, String.format("%.2f", totalBalance));
            newList.get(i).set(colAvailableBalance - 1, String.format("%.2f", availableBalance));
            newList.get(i).set(colWinloss - 1, String.format("%.2f", winloss));
            newList.get(i).set(colExposure - 1, String.format("%.2f", exposure));

        }
        return newList;
    }

    public boolean verifyBalanceUpdated(double amount, double mainBalance, DepositWithdraw.Actions transactionType) {
        return false;
    }

    public boolean verifyDepositWithDrawLink(int row, boolean isEnable) {
        Link lnk;
        Link lnkWithdraw;
        if (row != 0) {
            lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colTransfer, row, "a[1]");
            lnkWithdraw = (Link) tblWithdrawalDeposit.getControlOfCell(1, colTransfer, row, "a[2]");
            if (Objects.isNull(lnk) || Objects.isNull(lnkWithdraw)) {
                System.err.println("ERROR: Cannot get successful Deposit/Withdraw link in Transfer column");
                return false;
            }
            if (lnk.isEnabled() != isEnable || lnkWithdraw.isEnabled() != isEnable) {
                System.err.println(String.format("ERROR: Expected Deposit / Withdraw link enable %s but found %s", isEnable, lnk.isEnabled()));
                return false;
            }
        }
        return true;
    }

    public boolean verifyDepositWithDrawLink(boolean isEnable) {
        int n = tblWithdrawalDeposit.getNumberOfRows(false, false);
        boolean result = true;
        for (int i = 0; i <= n; i++) {
            result = verifyDepositWithDrawLink(i + 1, isEnable);
        }
        return result;
    }

    public boolean isTotalBalanceHeaderCalculatedCorrect() {
        double totalBalance = Double.parseDouble(tblAccountBalance.getControlOfCell(1, colSubBalanceHeader, 1, null).getText().trim().replaceAll(",", "")) +
                Double.parseDouble(tblAccountBalance.getControlOfCell(1, colMainAvailableBalance, 1, null).getText().trim().replaceAll(",", ""));
        if (String.format("%,.2f", totalBalance).equals(tblAccountBalance.getControlOfCell(1, colTotalBalanceHeader, 1, null).getText())) {
            return true;
        }
        return false;
    }

    public boolean isSubBalanceHeaderCalculatedCorrect() {
        double subBalanceHeader = 0;
        List<String> subBalance = tblWithdrawalDeposit.getColumn(colTotalBalance, 100, false);
        for (int i = 0; i < subBalance.size(); i++) {
            subBalanceHeader = subBalanceHeader + Double.parseDouble(subBalance.get(i).trim().replaceAll(",", ""));
        }
        DoubleUtils.roundUpWithTwoPlaces(subBalanceHeader);
        if (String.format("%,.2f", subBalanceHeader).equals(tblAccountBalance.getControlOfCell(1, colSubBalanceHeader, 1, null).getText())) {
            return true;
        }
        return false;
    }

    public enum Actions {DEPOSIT, WITHDRAWAL, CHECK, USERNAME, SUCCESS_ICON, FAILURE_ICON, VIEW_LOG}
}
