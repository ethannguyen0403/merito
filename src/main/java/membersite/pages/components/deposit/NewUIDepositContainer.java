package membersite.pages.components.deposit;

import agentsite.controls.DropDownList;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static common.MemberConstants.DepositPage.DEPOSIT_SUCCESS_HEADER_BRAND;


public class NewUIDepositContainer extends DepositContainer {
    private DropDownList ddlPaymentChannel = DropDownList.xpath("//app-deposit", "//ul[@class='left-content px-3']//li");
    private TextBox txtAmount = TextBox.xpath("//app-deposit//input[@placeholder='Amount']");
    private TextBox txtReferenceID = TextBox.xpath("(//app-deposit//input[contains(@class,'form-control')])[2]");
    private TextBox txtOrderTxnId = TextBox.xpath("//app-deposit//input[@placeholder='Order Transaction ID']");
    private TextBox txtUTRTxnNo = TextBox.xpath("//app-deposit//input[@placeholder='UTR Transaction No']");
    private TextBox txtUPITxnNo = TextBox.xpath("//app-deposit//input[@placeholder='UPI Transaction No']");
    private Button btnChooseFile = Button.xpath("//app-deposit//input[@type='file']");
    private Button btnSubmit = Button.xpath("//app-deposit//button[contains(@class,'btn-submit')]");
    private Label lblDepositSuccessHeader = Label.xpath("//app-deposit//div[contains(@class,'payment-detail')]//h3");
    private Label lblDepositSuccessHeaderDetail = Label.xpath("(//app-deposit//div[contains(@class,'payment-detail')]//div[contains(@class,'detail')])[1]");
    private Label lblDepositSuccessHeaderRef = Label.xpath("(//app-deposit//div[contains(@class,'payment-detail')]//div[contains(@class,'detail')])[2]");
    private Label lblDepositSuccessHeaderEmail = Label.xpath("(//app-deposit//div[contains(@class,'payment-detail')]//div[contains(@class,'detail')])[3]");
    public List<String> getListPaymentChannel() {
        List<String> lstPayment = ddlPaymentChannel.getMenuList();
        return lstPayment;
    }
    public void verifyListPaymentChannelDisplayCorrect(Map<String, String> mapPaymentSetting) {
        List<String> lstChannelAgent = new ArrayList<>();
        for (Map.Entry<String, String> entry : mapPaymentSetting.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(value.equalsIgnoreCase("Active")) {
                lstChannelAgent.add(key);
            }
        }
        List<String> lstChannelMember = getListPaymentChannel();
        for (int i = 0; i < lstChannelAgent.size(); i++) {
            Assert.assertTrue(lstChannelMember.contains(lstChannelAgent.get(i).toUpperCase()),"FAILED!");
        }
    }

    public void selectPaymentType(String paymentType) {
        try {
            ddlPaymentChannel.clickMenu(paymentType.toUpperCase());
        } catch (Exception e) {
            System.out.println(String.format("There is no payment type %s for select", paymentType));
            throw new SkipException("SKIPPED! Does not have payment type for execute test case");
        }

    }

    public void deposit(String paymentType, String amount, String transactionId, boolean isUploadImg, boolean isSubmit) {
        selectPaymentType(paymentType);
        switch (paymentType.toUpperCase()) {
            case "BANK TRANSFER":
                txtAmount.sendKeys(amount);
                txtReferenceID.sendKeys(transactionId);
                break;
            case "PAYTM":
                txtAmount.sendKeys(amount);
                txtOrderTxnId.sendKeys(transactionId);
                break;
            case "PHONEPE":
                txtAmount.sendKeys(amount);
                txtUTRTxnNo.sendKeys(transactionId);
                break;
            case "GPAY":
                txtAmount.sendKeys(amount);
                txtUPITxnNo.sendKeys(transactionId);
                break;
            case "UPI":
                txtAmount.sendKeys(amount);
                txtUTRTxnNo.sendKeys(transactionId);
                break;
            case "QR CODE":
                txtAmount.sendKeys(amount);
                break;
            default:
                System.out.println("Please input payment channel type for inputting infor");
                break;
        }
        if (isUploadImg) {
            TextBox fileInput = TextBox.xpath("//input[@type='file']");
            String uploadPath = System.getProperty("user.dir")+"\\src\\main\\resources\\image\\payment.png";
            System.out.println(String.format("Upload image path %s", uploadPath));
            fileInput.sendKeys(uploadPath);
        }
        if (isSubmit) {
            btnSubmit.click();
        }

    }

    public void verifyDepositSuccessMessage(String brandName) {
        String successHeader = String.format("Thank You for your deposit with %s", DEPOSIT_SUCCESS_HEADER_BRAND.get(brandName));
        String successDetail = "Here are you deposit details:\nDeposit details successfully received and currently under approval";
        String successRef = "Reference ID: ";
        String successEst = "Estimated time of approval: 15mins";
        String successEmail = "You will get an approval message on your email once deposit is available in your account";
        Assert.assertEquals(lblDepositSuccessHeader.getText().trim(), successHeader, String.format("FAILED! Header message expected %s but show %s", lblDepositSuccessHeader.getText().trim(), successHeader));
        Assert.assertEquals(lblDepositSuccessHeaderDetail.getText().trim(), successDetail, String.format("FAILED! Detail message expected %s but show %s", lblDepositSuccessHeaderDetail.getText().trim(), successDetail));
        Assert.assertEquals(lblDepositSuccessHeaderEmail.getText().trim(), successEmail, String.format("FAILED! Email message expected %s but show %s", lblDepositSuccessHeaderEmail.getText().trim(), successEmail));
        Assert.assertTrue(lblDepositSuccessHeaderRef.getText().contains(successRef), String.format("FAILED! Ref message does not contain %s", successRef));
        Assert.assertTrue(lblDepositSuccessHeaderRef.getText().contains(successEst), String.format("FAILED! Ref message does not contain %s", successEst));
    }

}
