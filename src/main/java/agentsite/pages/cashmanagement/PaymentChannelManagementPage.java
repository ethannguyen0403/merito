package agentsite.pages.cashmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Label;

import java.util.HashMap;
import java.util.Map;

public class PaymentChannelManagementPage extends HomePage {
    private int totalCol = 6;
    private int colPaymentChannel = 2;
    private int colStatus = 4;
    protected Label lblTitle = Label.xpath("//app-payment-channel//div[@class='title']");
    protected Table tblPaymentChannel = Table.xpath("//app-payment-channel//table[contains(@class,'payment-channel-table')]", totalCol);
    public PaymentChannelManagementPage(String types) {
        super(types);
    }

    public Map<String, String> getListPaymentStatus() {
        Map<String, String> mapChannel = new HashMap<>();
        int totalRow = tblPaymentChannel.getNumberOfRows(false, false);
        for (int i = 0; i < totalRow; i++) {
            String paymentChannel = Label.xpath(tblPaymentChannel.getxPathOfCell(1, colPaymentChannel, i + 1, "span")).getText();
            String status =  Label.xpath(tblPaymentChannel.getxPathOfCell(1, colStatus, i + 1, "span")).getText();
            mapChannel.put(paymentChannel, status);
        }
        return mapChannel;
    }
}
