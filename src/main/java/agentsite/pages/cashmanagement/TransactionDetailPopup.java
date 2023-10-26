package agentsite.pages.cashmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;
import org.testng.Assert;


public class TransactionDetailPopup {
    private TextBox txtLoginId = TextBox.xpath("//app-transaction-detail//span[text()='Login ID']//..//following-sibling::div//input");
    private TextBox txtRefNo = TextBox.xpath("//app-transaction-detail//span[text()='Ref No']//..//following-sibling::div//input");
    private TextBox txtPaymentType = TextBox.xpath("//app-transaction-detail//span[text()='Payment Type']//..//following-sibling::div//input");
    private TextBox txtStatus = TextBox.xpath("//app-transaction-detail//span[text()='Status']//..//following-sibling::div//input");
    private TextBox txtAmount = TextBox.xpath("//app-transaction-detail//span[text()='Amount']//..//following-sibling::div//div");
    private TextBox txtCommentApprove = TextBox.xpath("//app-transaction-detail//span[text()='Comment']//..//following-sibling::div//div");
    private TextBox txtCommentReject = TextBox.xpath("//app-transaction-detail//span[text()='Comment']//..//following-sibling::div//textarea");
    public Button btnCancel = Button.xpath("//app-transaction-detail//button[text()='Cancel']");
    public RadioButton rdApprove = RadioButton.id("approve");
    public RadioButton rdReject = RadioButton.id("reject");
    public Button btnSubmitTransactionDetail = Button.xpath("//app-transaction-detail//button[text()=' Submit']");

    public void verifyInfoDetailDisplayCorrect(String username, String refNo, String paymentType, String status, String amount, String comment) {
        if(!username.isEmpty()) {
            Assert.assertEquals(txtLoginId.getAttribute("value").trim(), username, String.format("FAILED! Login ID displays incorrectly expected %s but actual %s", username, txtLoginId.getAttribute("value")));
        }
        if(!refNo.isEmpty()) {
            Assert.assertEquals(txtRefNo.getAttribute("value").trim(), refNo, String.format("FAILED! Ref No displays incorrectly expected %s but actual %s", refNo, txtRefNo.getAttribute("value")));
        }
        if(!paymentType.isEmpty()) {
            Assert.assertEquals(txtPaymentType.getAttribute("value").trim(), paymentType, String.format("FAILED! Payment Type displays incorrectly expected %s but actual %s", paymentType, txtPaymentType.getAttribute("value")));
        }
        if(!status.isEmpty()) {
            Assert.assertEquals(txtStatus.getAttribute("value").trim(), status, String.format("FAILED! Status displays incorrectly expected %s but actual %s", status, txtStatus.getAttribute("value")));
        }
        if(!amount.isEmpty()) {
            Assert.assertEquals(txtAmount.getText().trim(), amount, String.format("FAILED! Amount displays incorrectly expected %s but actual %s", amount, txtAmount.getText()));
        }
        if(status.equalsIgnoreCase("approved")) {
            Assert.assertEquals(txtCommentApprove.getText().trim(), comment, String.format("FAILED! Comment displays incorrectly expected %s but actual %s", comment, txtCommentApprove.getText()));
        } else {
            Assert.assertEquals(txtCommentReject.getAttribute("value").trim(), comment, String.format("FAILED! Comment displays incorrectly expected %s but actual %s", comment, txtCommentReject.getText()));
        }
    }
 }
