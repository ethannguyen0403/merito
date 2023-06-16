package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositPopup;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositToPopup;
import agentsite.pages.agentmanagement.depositwithdrawal.ViewLogPopup;
import agentsite.pages.agentmanagement.depositwithdrawal.WithdrawalPopup;
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

import static agentsite.pages.agentmanagement.DepositWithdrawalPage.Actions.WITHDRAWAL;

public class DepositWithdrawalPage extends HomePage {
    public Popup popupSecurityCode = Popup.xpath("//div[@class='otp-dialog ng-scope']");
    public Label lblDownlineBar = Label.xpath("//span[@class='my-breadcrumb']");
    public enum Actions {DEPOSIT, WITHDRAWAL, CHECK, USERNAME, SUCCESS_ICON, FAILURE_ICON,VIEW_LOG}
    public Label lblLoginAccountAvailableBalance = Label.xpath("//span[@class='creditBalanceextension']");
    public TextBox txtUsername = TextBox.xpath("//th[contains(@class,'nick-name-field')]//input | //input[@id='userName']");
    public Label lblUsername = Label.xpath("//label[@for='userName']");
    public Label lblAccountStatus = Label.xpath("//label[@for='status']");
    public Label lblLevel = Label.xpath("//label[@for='userLevel']");
    public Button btnSubmit = Button.xpath("//table[contains(@class,'ptable info')]//button[@class='pbtn search']");
    public Button btnDeposit = Button.id("bntUpdateDeposit");
    public Button btnWithdraw = Button.id("bntUpdateWithdraw");
    public CheckBox chkAll = CheckBox.id("cAll");
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public int colNo = 1;
    private  int colUsername = 2;
    public int colNickname = 3;
    public int colClientName = 4;
    private  int colCheckAll = 5;
    public int colAccountStatus = 6;
    public int colLevel = 7;
    public int colCurrency = 8;
    public int colTotalPlayerOutStanding = 9;
    public int colRetainAmount = 10;
    public int colAvailableBalance = 11;
    private  int colTransfer = 12;
    private  int colUpdateStatus = 13;
    public String downlineXPath = "//span[text()='%s']";
    public Label lblNoRecord = Label.xpath("//span[contains(@class,'no-record')]");
    public DropDownBox ddpPage = DropDownBox.xpath("//div[contains(@class,'item-per-page')]//select");
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'spin')]");
    private Label lblAvailableBalance = Label.xpath("//span[@class='creditBalanceextension']");
    private  int totalCol = 13;
    public  Table tblWithdrawalDeposit = Table.xpath("//table[contains(@class,'ptable report table-responsive')]", totalCol);
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("(//table[contains(@class,'ptable report table-responsive')])//th["+colAccountStatus+"]//select");
    public DropDownBox ddbLevel = DropDownBox.xpath("(//table[contains(@class,'ptable report table-responsive')])//th["+colLevel+"]//select");
    private int colLoginID = 3;
    public int colCreditInitiation = 8;
    public int colTotalBalance =9;
    public int colSubBalance = 1;
    public int colWinloss = 12;
    public int colExposure = 13;
    private int colLog=15;
    private int totalColAccountBalanceTable = 4;
    public int colMyCreditHeader = 1;
    public int colTotalBalanceHeader = 2;
    public int colSubBalanceHeader = 3;
    public int   colMainAvailableBalance = 4;
    public Table tblAccountBalance = Table.xpath("(//table[@class='ptable report'])[1]",totalColAccountBalanceTable);
    public DepositWithdrawalPage(String types) {
        super(types);
    }
    public List<ArrayList<String>> getLoginAccountBalanceInfo()
    {
        return tblAccountBalance.getRowsWithoutHeader(1,false);
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

    public void filter(String username, String accountStatus, String level) {
        if (!username.isEmpty()) {
            txtUsername.sendKeys(username);
        }
        if (!accountStatus.isEmpty()) {
            ddbAccountStatus.selectByVisibleText(accountStatus);
        }
        if (!level.isEmpty()) {
            ddbLevel.selectByVisibleText(level);
        }
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public  int getRowIndex(String usercode) {
        List<String> lstUsername = tblWithdrawalDeposit.getColumn(colUsername, false);
        for (int i = 0; i < lstUsername.size(); i++) {
            if (lstUsername.get(i).equals(usercode)) {
                return i + 1;
            }
        }
        System.out.println(String.format("Usercode %s not display in the list", usercode));
        return 0;
    }

    public  Object action(Actions type, String username) {
        int rowIndex = getRowIndex(username);
        return action(type, rowIndex);
    }

    public double getDataByColumn(String username, int columnIndex){
        String value =tblWithdrawalDeposit.getControlBasedValueOfDifferentColumnOnRow(username,1,colUsername,1,null,columnIndex,null,true,false).getText();
        double returnValue = 0;
        try {
            returnValue = DecimalFormat.getNumberInstance().parse(value).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e.getCause());
        }
        return returnValue;
    }

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
        }
        return 0;
    }
    public void selectUser(String userName){
        String cbCheck = tblWithdrawalDeposit.getControlxPathBasedValueOfDifferentColumnOnRow(userName,1,colUsername,1,null,colCheckAll,"input",false,false);
        if(CheckBox.xpath(cbCheck).isDisplayed()){
            System.out.println("Select username: "+ userName);
            CheckBox.xpath(cbCheck).click();
        }
    }
    public  Object action(Actions type, int rowIndex) {
        if (rowIndex == 0)
            return null;
        Link lnk;
        switch (type) {
            case CHECK:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colCheckAll, rowIndex, "input[@id='cItem']");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get checkbox at Check All column");
                    return null;
                }
                lnk.isClickable(2);
                lnk.click();
                return null;
            case DEPOSIT:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colTransfer, rowIndex, "a[1]");
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
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colTransfer, rowIndex, "a[2]");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get WITHDRAWAL link at Transfer column");
                    return null;
                }
                lnk.isClickable(2);
                lnk.click();
                return new WithdrawalPopup();
            case USERNAME:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colUsername, rowIndex, "span[@class='downline']");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get down-line link at Username column");
                    return null;
                }
                lnk.isClickable(2);
                lnk.click();
                return tblWithdrawalDeposit;
            case SUCCESS_ICON:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colUpdateStatus, rowIndex, "span[contains(@class,'psuccess')]");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get successful icon at Update Status column");
                    return false;
                }
                return lnk.isDisplayedShort(2);
            case VIEW_LOG:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colLog, rowIndex, "a");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get failure icon at Update Status column");
                    return false;
                }
                lnk.click();
                ViewLogPopup viewLogPopup = new ViewLogPopup();
                viewLogPopup.btnClose.isDisplayed();
                return viewLogPopup;
            case FAILURE_ICON:
                lnk = (Link) tblWithdrawalDeposit.getControlOfCell(1, colUpdateStatus, rowIndex, "span[contains(@class,'perror'])");
                if (Objects.isNull(lnk)) {
                    System.err.println("ERROR: Cannot get failure icon at Update Status column");
                    return false;
                }
                return lnk.isDisplayedShort(2);
        }
        return null;
    }

    public void selectAllCheckBoxes() {
        chkAll.click();
    }

    public void selectUser(List<ArrayList<String>> listDownlineInfo) {
        for (int i = 0; i < listDownlineInfo.size(); i++) {
            action(Actions.CHECK, i + 1);
        }
    }

    public void selectPageNumber(String page) {
        ddpPage.selectByVisibleText(page);
        waitingLoadingSpinner();
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

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(1, 1);
    }

    public List<ArrayList<String>> getMemberInfo(int memberNumber) {
        return tblWithdrawalDeposit.getRowsWithoutHeader(memberNumber, false);
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
                subBalance = subBalance- totalAmount;//Double.parseDouble(newList.get(0).get(colSubBalanceHeader - 1)) - totalAmount;
                availableBalance =  availableBalance+ totalAmount;//Double.parseDouble(newList.get(0).get(colMainAvailableBalance - 1)) + totalAmount;
            }
            newList.get(0).set(colSubBalanceHeader - 1, String.format(Locale.getDefault(), "%,.2f", subBalance));
            newList.get(0).set(colMainAvailableBalance - 1,  String.format(Locale.getDefault(), "%,.2f", availableBalance));
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
            newList.get(i).set(colCreditInitiation - 1, String.format("%.2f",creditInitiation));
            newList.get(i).set(colTotalBalance - 1,String.format("%.2f",totalBalance)  );
            newList.get(i).set(colAvailableBalance - 1,String.format("%.2f",availableBalance));
            newList.get(i).set(colWinloss - 1,String.format("%.2f",winloss));
            newList.get(i).set(colExposure - 1,String.format("%.2f",exposure));

        }
        return newList;
    }

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
    public boolean verifyBalanceUpdated(double amount, double mainBlance, List<ArrayList<String>> lstDownlineBefore, List<ArrayList<String>> lstDownlineAfter, boolean isDeposit, Actions statusType) {
        double expectedMainBalance;
        double expecteddownlineBalance;
        double actualDownlineBalance;
        int totalDownline = lstDownlineBefore.size();
        double totalAmountDeposit = totalDownline * amount;
        // Calculate main balance after deposit
        if (isDeposit) {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBlance)  - totalAmountDeposit;
        } else {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBlance) + totalAmountDeposit;
        }

        if (!lblLoginAccountAvailableBalance.getText().trim().contains(String.format(Locale.getDefault(), "%,.2f", expectedMainBalance))) {
            System.out.println(String.format("FAILED! Main Available Balance incorrect after deposit/withdraw. Actual is %s but expected is $s",
                    lblLoginAccountAvailableBalance.getText(), String.format(Locale.getDefault(), "%,.2f", expectedMainBalance)));
            return false;
        }
        for (int i = 0; i < lstDownlineBefore.size(); i++) {
            expecteddownlineBalance = Double.valueOf(lstDownlineBefore.get(i).get(colAvailableBalance - 1).replaceAll(",", "").toString());
            actualDownlineBalance = Double.valueOf(lstDownlineAfter.get(i).get(colAvailableBalance - 1).replaceAll(",", "").toString());
            // Calculate downline balance
            if (isDeposit) {
                expecteddownlineBalance = expecteddownlineBalance + amount;
            } else {
                expecteddownlineBalance = expectedMainBalance - amount;
            }

            // Verify downline balance after deposit/withdraw
            if (expecteddownlineBalance != actualDownlineBalance) {
                System.out.println(String.format("FAILED! Expected available balance of username %s is %s but found %s", lstDownlineBefore.get(i).get(colUsername - 1),
                        String.format(Locale.getDefault(), "%,.2f", actualDownlineBalance), String.format(Locale.getDefault(), "%,.2f", expecteddownlineBalance)));
                return false;
            }

            if (Objects.nonNull(statusType)) {
                switch (statusType) {
                    case SUCCESS_ICON:
                        if (!(boolean) action(Actions.SUCCESS_ICON, lstDownlineBefore.get(i).get(colUsername - 1))){
                            System.out.println(String.format("FAILED! Expected update status of username %s not show Success as expected ", lstDownlineBefore.get(i).get(colUsername - 1)));
                        return false;}
                        break;
                    case FAILURE_ICON:
                        if (!(boolean) action(Actions.FAILURE_ICON, lstDownlineBefore.get(i).get(colUsername - 1))){
                            System.out.println(String.format("FAILED! Expected update status of username %s not show Error as expected ", lstDownlineBefore.get(i).get(colUsername - 1)));
                        return false;}
                        break;
                }
            }
        }
        return true;
    }

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstMember, boolean status) {
        for (int i = 0; i < lstMember.size(); i++) {
            boolean result = (boolean) action(Actions.SUCCESS_ICON, lstMember.get(i).get(colUsername - 1));
            if (result != status) {
                System.out.println(String.format("Expected update status of username %s is but % found %s", lstMember.get(i).get(colUsername - 1), status, result));
                return false;
            }
        }
        return true;
    }

    public boolean isUpdateStatusSuccess(String username){
        boolean result =  (boolean) action(Actions.SUCCESS_ICON, username);
        System.out.println(String.format("Update status of username %s is %s", username, result));
        return result;
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

    public  String deposit(String usercode, String amount, String remark, boolean isCreditUpdate,boolean isClose){
        DepositToPopup popup =  (DepositToPopup)action(Actions.DEPOSIT, usercode);
        popup.deposit(amount, remark,isCreditUpdate,true);
        String messsage = popup.lblMessage.getText().trim();;
        if(isClose)
            popup.clickXIcon();
        return messsage;
    }

    public String withdraw(String usercode, String amount, String remark, boolean isCreditUpdate, boolean isClose) {
        WithdrawalPopup popup = (WithdrawalPopup) action(WITHDRAWAL, usercode);
        popup.withdraw(amount, remark, isCreditUpdate,true);
        String messsage = popup.lblMessage.getText().trim();
        if (isClose)
            popup.clickXIcon();
        return messsage;
    }

    public String getCreditInitation(String userCode) {
        String creditInitiation = tblWithdrawalDeposit.getControlBasedValueOfDifferentColumnOnRow(userCode, 1, colUsername, 1, null, colCreditInitiation, null, false, false).getText().trim();
        return String.format("%s", (int) Double.parseDouble(creditInitiation));

    }

}
