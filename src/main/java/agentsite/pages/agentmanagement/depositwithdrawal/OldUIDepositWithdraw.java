package agentsite.pages.agentmanagement.depositwithdrawal;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.DepositWithdrawalPage;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.utils.DoubleUtils;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.*;

import static agentsite.pages.agentmanagement.DepositWithdrawalPage.*;

public class OldUIDepositWithdraw extends DepositWithdraw {
    int totalCol = 13;
    int colSubBalance = 10;
    int colTotalBalance = 9;
    int colAvailableBalance = 10;
    public int colCreditInitiation = 8;
    private int totalColAccountBalanceTable = 4;
    private Label lblLoginAccountAvailableBalance = Label.xpath("//app-agency-deposit-withdraw//table[@class='ptable report']//tbody[1]//tr[1]//td[4]");
    private Label lblUsername = Label.xpath("//app-agency-deposit-withdraw//table[@class='ptable report table-responsive']//thead/tr[1]/th[2]");
    private Label lblAccountStatus = Label.xpath("//app-agency-deposit-withdraw//table[@class='ptable report table-responsive']//thead/tr[1]/th[6]");
    private Label lblLevel = Label.xpath("//app-agency-deposit-withdraw//table[@class='ptable report table-responsive']//thead/tr[1]/th[7]");
    private DropDownBox ddbAccountStatus = DropDownBox.xpath("//table[@class='ptable report table-responsive']//thead//th[text()='Account Status ']//select");
    private DropDownBox ddbLevel = DropDownBox.xpath("//table[@class='ptable report table-responsive']//thead//th[text()='Level ']//select");
    public Table tblAccountBalance = Table.xpath("(//table[@class='ptable report'])[1]", totalColAccountBalanceTable);
    private int colCheckAll = 5;
    private int colTransfer = 14;
    private int colUpdateStatus = 16;
    private int colLog = 15;

    public void verifyTotalBalanceCalculatedCorrect() {
        List<String> subBalance = tblWithdrawalDeposit.getColumn(colSubBalance, 200, false);
        List<String> availableBalance = tblWithdrawalDeposit.getColumn(colAvailableBalance, 200, false);
        List<String> totalBalance = tblWithdrawalDeposit.getColumn(colTotalBalance, 200, false);
        for (int i = 0; i < subBalance.size(); i++) {
            String subBalanceText = subBalance.get(i).trim().replaceAll(",", "");
            if(subBalanceText.equalsIgnoreCase("-")) {
                subBalanceText = "0.00";
            }
            double subBalanceValue = Double.parseDouble(subBalanceText);
            double availableBalanceValue = Double.parseDouble(availableBalance.get(i).trim().replaceAll(",", ""));
            double totalBalanceCalculate =  subBalanceValue + availableBalanceValue;
            Assert.assertEquals(Double.parseDouble(totalBalance.get(i)), Math.floor(totalBalanceCalculate * 100)/ 100, 0.1, "");
        }
    }

    @Override
    public boolean areOptionsMatched(List<String> expectedList, String controlDropDownName) {
        Map<String, DropDownBox> vars = new HashMap<>();
        vars.put("ddbAccountStatus",ddbAccountStatus);
        vars.put("ddbLevel",ddbLevel);
        return vars.get(controlDropDownName).areOptionsMatched(expectedList);
    }

    public String getLabelText(String controlNameString) {
        final Map<String, Label> vars = new HashMap<>();
        vars.put("lblLoginAccountAvailableBalance",lblLoginAccountAvailableBalance);
        vars.put("lblUsername", lblUsername);
        vars.put("lblAccountStatus", lblAccountStatus);
        vars.put("lblLevel", lblLevel);
        return vars.get(controlNameString).getText().trim();
    }

    public List<ArrayList<String>> getLoginAccountBalanceInfo() {
        return tblAccountBalance.getRowsWithoutHeader(1, false);
    }

    public void filter(String username, String accountStatus, String level) {
        try {
            if (!username.isEmpty()) {
                txtUsername.sendKeys(username);
                txtUsername.sendSingleKey(Keys.ENTER);
                Thread.sleep(2000);
            }
            if (!accountStatus.isEmpty()) {
                ddbAccountStatus.selectByVisibleText(accountStatus);
                Thread.sleep(2000);
            }
            if (!level.isEmpty()) {
                ddbLevel.selectByVisibleText(level);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.getMessage();
            e.getCause();
        }

    }

    public Object action(DepositWithdraw.Actions type, int rowIndex) {
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

    public boolean verifyBalanceUpdated(double amount, double mainBlance, List<ArrayList<String>> lstDownlineBefore, List<ArrayList<String>> lstDownlineAfter, boolean isDeposit, DepositWithdraw.Actions statusType) {
        double expectedMainBalance;
        double expecteddownlineBalance;
        double actualDownlineBalance;
        int totalDownline = lstDownlineBefore.size();
        double totalAmountDeposit = totalDownline * amount;
        // Calculate main balance after deposit
        if (isDeposit) {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBlance) - totalAmountDeposit;
        } else {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBlance) + totalAmountDeposit;
        }

        if (!getLabelText("lblLoginAccountAvailableBalance").contains(String.format(Locale.getDefault(), "%,.2f", expectedMainBalance))) {
            System.out.println(String.format("FAILED! Main Available Balance incorrect after deposit/withdraw. Actual is %s but expected is $s",
                    getLabelText("lblLoginAccountAvailableBalance"), String.format(Locale.getDefault(), "%,.2f", expectedMainBalance)));
            return false;
        }
        for (int i = 0; i < lstDownlineBefore.size(); i++) {
            expecteddownlineBalance = Double.valueOf(lstDownlineBefore.get(i).get(colAvailableBalance).replaceAll(",", ""));
            actualDownlineBalance = Double.valueOf(lstDownlineAfter.get(i).get(colAvailableBalance).replaceAll(",", ""));
            // Calculate downline balance
            if (isDeposit) {
                expecteddownlineBalance = expecteddownlineBalance + amount;
            } else {
                expecteddownlineBalance = expecteddownlineBalance - amount;
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
                        if (!(boolean) DepositWithdrawalPage.action(DepositWithdraw.Actions.SUCCESS_ICON, lstDownlineBefore.get(i).get(colUsername - 1))) {
                            System.out.println(String.format("FAILED! Expected update status of username %s not show Success as expected ", lstDownlineBefore.get(i).get(colUsername - 1)));
                            return false;
                        }
                        break;
                    case FAILURE_ICON:
                        if (!(boolean) DepositWithdrawalPage.action(DepositWithdraw.Actions.FAILURE_ICON, lstDownlineBefore.get(i).get(colUsername - 1))) {
                            System.out.println(String.format("FAILED! Expected update status of username %s not show Error as expected ", lstDownlineBefore.get(i).get(colUsername - 1)));
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }

    public boolean isUpdateStatusSuccess(String username) {
        boolean result = (boolean) DepositWithdrawalPage.action(DepositWithdraw.Actions.SUCCESS_ICON, username);
        System.out.println(String.format("Update status of username %s is %s", username, result));
        return result;
    }

    public boolean verifyBalanceUpdated(double amount, double mainBalance, DepositWithdraw.Actions transactionType) {
        double expectedMainBalance;
        if (transactionType.equals(DepositWithdraw.Actions.DEPOSIT)) {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBalance) + amount;
        } else {
            expectedMainBalance = DoubleUtils.roundUpWithTwoPlaces(mainBalance) - amount;
        }
        if (getLabelText("lblLoginAccountAvailableBalance").contains(String.format(Locale.getDefault(), "%,.2f", expectedMainBalance))) {
            System.out.println(String.format("PASSED! Main Available Balance correct Actual is %s expected is %s",
                    getLabelText("lblLoginAccountAvailableBalance"), String.format(Locale.getDefault(), "%,.2f", expectedMainBalance)));
            return true;
        }
        return false;
    }

    public List<String> getAccountsAvailableBalance(List<ArrayList<String>> lstAccount, boolean isDownline) {
        List<String> lstBalance = new ArrayList<>();
        if(isDownline) {
            for (int i = 0; i < lstAccount.size(); i++) {
                lstBalance.add(lstAccount.get(i).get(10).replace(",",""));
            }
            return lstBalance;
        } else {
            for (int i = 0; i < lstAccount.size(); i++) {
                lstBalance.add(lstAccount.get(i).get(3).replace(",",""));
            }
            return lstBalance;
        }
    }
}
