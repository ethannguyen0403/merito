package agentsite.pages.cashmanagement.depositwithdrawaltransaction;

import agentsite.pages.cashmanagement.TransactionDetailPopup;

public class DepositWithdrawalTransaction {
    public void actionTransaction(String action, String comment, boolean isSubmit) {}
    public void search(String userName, String transactionStatus, String paymentType, String fromDate, String toDate, String refNo) {}
    public void closeActionTransactionAlertMessage() {}
    public String getPageTitle() {return "";}

    public void verifyUIDisplayCorrect() {}

    public void verifySearchResult(String username, String loginId, String transactionStatus, String paymentType, String fromDate, String toDate, String refNo) {}
    public TransactionDetailPopup openTransactionDetail(String action) {return null;}

    public String getFirstRefNoByStatus(String status, String fromDate, String toDate) {return "";}

}
