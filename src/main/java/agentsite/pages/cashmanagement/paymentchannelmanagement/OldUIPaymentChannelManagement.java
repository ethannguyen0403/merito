package agentsite.pages.cashmanagement.paymentchannelmanagement;

import agentsite.controls.Table;
import com.paltech.element.common.Label;
import org.testng.Assert;

import java.util.*;

import static agentsite.pages.HomePage.waitingLoadingSpinner;
import static common.AGConstant.CashManagement.DepositWithdrawalTransaction.LST_PAYMENT_TYPE;

public class OldUIPaymentChannelManagement extends PaymentChannelManagement {
    private int totalCol = 6;
    private int colPaymentChannel;
    private int colStatus;
    private int colHistory;
    protected Label lblTitle = Label.xpath("//app-payment-channel//div[@class='title']");
    protected Table tblPaymentChannel = Table.xpath("//app-payment-channel//table[contains(@class,'payment-channel-table')]", totalCol);
    public Map<String, String> getListPaymentStatus() {
        Map<String, String> mapChannel = new HashMap<>();
        int totalRow = tblPaymentChannel.getNumberOfRows(false, false);
        colPaymentChannel = tblPaymentChannel.getColumnIndexByName("Payment Channel");
        colStatus = tblPaymentChannel.getColumnIndexByName("Status");
        for (int i = 0; i < totalRow; i++) {
            String paymentChannel = Label.xpath(tblPaymentChannel.getxPathOfCell(1, colPaymentChannel, i + 1, "span")).getText();
            String status =  Label.xpath(tblPaymentChannel.getxPathOfCell(1, colStatus, i + 1, "span")).getText();
            mapChannel.put(paymentChannel, status);
        }
        return mapChannel;
    }

    public void verifyListChannelDisplayCorrect() {
        Map<String, String> lstMapPaymentChannel = getListPaymentStatus();
        List<String> lstPaymentChannel = new ArrayList<>();
        List<String> lstExpected = Arrays.asList("BANK TRANSFER", "PAYTM", "PHONEPE", "GPAY", "UPI", "QR Code");
        for (Map.Entry<String, String> entry : lstMapPaymentChannel.entrySet()) {
            lstPaymentChannel.add(entry.getKey());
        }
        Collections.sort(lstPaymentChannel);
        Collections.sort(lstExpected);
        Assert.assertEquals(lstPaymentChannel, lstExpected , String.format("FAILED! List payment channel does not show correct expected %s but actual %s", LST_PAYMENT_TYPE, lstPaymentChannel));
    }

    public void clickViewLink(String paymentChannel) {
        int totalRow = tblPaymentChannel.getNumberOfRows(false, true);
        colHistory = tblPaymentChannel.getColumnIndexByName("History");
        colPaymentChannel = tblPaymentChannel.getColumnIndexByName("Payment Channel");
        for (int i = 0; i < totalRow; i++) {
            if(tblPaymentChannel.getControlOfCell(1, colPaymentChannel, i+1, "span").getText().equalsIgnoreCase(paymentChannel)) {
                tblPaymentChannel.getControlOfCell(1, colHistory, i + 1, "a").click();
                waitingLoadingSpinner();
                break;
            }
        }
    }

}
