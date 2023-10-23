package membersite.pages.components.deposit.transactionhistory;

import java.text.ParseException;

public class TransactionHistoryContainer {

    public void filterTransactionHistory(String startDate, String endDate) {}

    public void clickPeriodTime(String periodTime) {}

    public void verifyTransactionHistoryInfo(String refNo, String type, String status, String startBalance, String amount, String endBalance, String transactionDate) {}

    public void verifyFilterDateTimeShowCorrect(String startDate, String endDate) throws ParseException {}

    public String getValueOfEndDate() {return "";}
}
