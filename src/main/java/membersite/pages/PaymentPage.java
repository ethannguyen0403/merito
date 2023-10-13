package membersite.pages;

import agentsite.controls.DropDownList;
import com.paltech.element.common.Label;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.deposit.deposit.DepositContainer;
import membersite.pages.components.deposit.transactionhistory.TransactionHistoryContainer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PaymentPage extends HomePage {

    public Label lblTitle = Label.xpath("//app-deposit//h5");
    public Label lblNoRecordFound = Label.xpath("//app-transaction//tbody/tr[1]/td[@class='text-center']");
    private DropDownList ddlMenuTab = DropDownList.xpath("//app-payments//div[@class='top-navbar-container']", "//ul[@class='navbar-nav align-items-center']//li");
    public DepositContainer depositContainer;
    public TransactionHistoryContainer transactionHistoryContainer;
    public PaymentPage(String types) {
        super(types);
        depositContainer = ComponentsFactory.depositContainerObject(types);
        transactionHistoryContainer = ComponentsFactory.transactionHistoryContainerObject(types);
    }

    public List<String> getListPaymentChannel() {
        return depositContainer.getListPaymentChannel();
    }

    public void verifyListPaymentChannelDisplayCorrect(Map<String, String> mapPaymentSetting) {
        depositContainer.verifyListPaymentChannelDisplayCorrect(mapPaymentSetting);
        waitPageLoad();
    }

    public void switchTab(String tabName) {
        ddlMenuTab.clickMenu(tabName.toUpperCase());
        waitPageLoad();
    }

    public void selectPaymentType(String paymentType) {
        depositContainer.selectPaymentType(paymentType);
    }

    public void deposit(String paymentType, String amount, String transactionId, boolean isUploadImg, boolean isSubmit) {
        depositContainer.deposit(paymentType, amount, transactionId, isUploadImg, isSubmit);
    }

    public void verifyDepositSuccessMessage(String brandName) {
        depositContainer.verifyDepositSuccessMessage(brandName);
    }

    public String getRefNo() {
        return depositContainer.getRefNo();
    }

    public void filterTransactionHistory(String startDate, String endDate) {
        transactionHistoryContainer.filterTransactionHistory(startDate, endDate);
    }

    public void clickPeriodTime(String periodTime) {
        transactionHistoryContainer.clickPeriodTime(periodTime);
    }

    public void verifyTransactionHistoryInfo(String refNo, String type, String status, String startBalance, String amount, String endBalance, String transactionDate) {
        transactionHistoryContainer.verifyTransactionHistoryInfo(refNo, type, status, startBalance, amount, endBalance, transactionDate);
    }

    public void verifyFilterDateTimeShowCorrect(String startDate, String endDate) throws ParseException {
        transactionHistoryContainer.verifyFilterDateTimeShowCorrect(startDate, endDate);
    }

    public String getValueOfEndDate() {return transactionHistoryContainer.getValueOfEndDate();}
}
