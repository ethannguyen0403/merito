package agentsite.pages.cashmanagement;

import agentsite.controls.Table;
import com.paltech.element.common.Label;
import org.testng.Assert;

import static common.AGConstant.CashManagement.PaymentChannelManagement.TBL_HEADER_HISTORY_POPUP;


public class PaymentHistoryPopup {
    private int totalCol = 4;
    private Label lblTitle = Label.xpath("//app-history-channel-dialog//div[@class='title']//span");
    private Table tblHistory = Table.xpath("//app-history-channel-dialog//table[contains(@class,'history-channel-table')]", totalCol);

    public void verifyUIDisplayCorrect(String paymentChannel) {
        Assert.assertTrue(lblTitle.getText().trim().equals(String.format("History - %s", paymentChannel)), "FAILED! Title displays incorrectly");
        Assert.assertEquals(tblHistory.getHeaderList(), TBL_HEADER_HISTORY_POPUP, "FAILED! Table header list displays incorrectly");
    }
}
