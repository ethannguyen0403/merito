package agentsite.pages.cashmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.cashmanagement.depositwithdrawaltransaction.DepositWithdrawalTransaction;
import agentsite.pages.components.ComponentsFactory;

public class DepositWithdrawalTransactionPage extends HomePage {
    public DepositWithdrawalTransaction depositWithdrawalTransaction;
    public DepositWithdrawalTransactionPage(String types) {
        super(types);
        _type = types;
        depositWithdrawalTransaction = ComponentsFactory.depositWithdrawalTransaction(_type);
    }

    public void search(String userName, String transactionStatus, String paymentType, String fromDate, String toDate, String refNo) { depositWithdrawalTransaction.search(userName, transactionStatus, paymentType, fromDate, toDate, refNo);}

    public void actionTransaction(String action, String comment, boolean isSubmit) { depositWithdrawalTransaction.actionTransaction(action, comment, isSubmit);}

    public void closeActionTransactionAlertMessage() {depositWithdrawalTransaction.closeActionTransactionAlertMessage();}
    public String getPageTitle() {return depositWithdrawalTransaction.getPageTitle();}

    public void verifyUIDisplayCorrect() {depositWithdrawalTransaction.verifyUIDisplayCorrect();}

    public void verifySearchResult(String username, String loginId, String transactionStatus, String paymentType, String fromDate, String toDate, String refNo) {depositWithdrawalTransaction.verifySearchResult(username, loginId, transactionStatus, paymentType, fromDate, toDate, refNo);}
}
