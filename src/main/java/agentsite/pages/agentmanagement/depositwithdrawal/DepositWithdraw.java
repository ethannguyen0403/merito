package agentsite.pages.agentmanagement.depositwithdrawal;

import java.util.ArrayList;
import java.util.List;


public class DepositWithdraw {
    public Object action(DepositWithdraw.Actions type, int rowIndex) {
        return null;
    }

    public void verifyTotalBalanceCalculatedCorrect() {
    }

    public String getLabelText(String controlName) {
        return "";
    }

    public void filter(String username, String accountStatus, String level) {
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

    public boolean verifyBalanceUpdated(double amount, double mainBalance, DepositWithdraw.Actions transactionType) {
        return false;
    }


    public enum Actions {DEPOSIT, WITHDRAWAL, CHECK, USERNAME, SUCCESS_ICON, FAILURE_ICON, VIEW_LOG}
}
