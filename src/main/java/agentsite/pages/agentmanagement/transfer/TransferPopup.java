package agentsite.pages.agentmanagement.transfer;

import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class TransferPopup extends ConfirmPopup {
    Button btnSubmit = Button.xpath("//app-transfer-dialog//button[@type='submit']");
    Button btnCancel = Button.xpath("//app-transfer-dialog//button[contains(@class,'cancel')][2]");
    private Label lblErroMessage = Label.xpath("//app-transfer-dialog//span[contains(@class,'error-messages')]");
    private Label lblAmount = Label.xpath("//app-transfer-dialog//tr[@class='transferAmount']//td[1]");
    private Label lblCurrency = Label.xpath("//app-transfer-dialog//tr[@class='transferAmount']//td[3]");
    private TextBox txtAmount = TextBox.xpath("//app-transfer-dialog//input[@name='amount']");

    public void transfer(String amount) {
        if (!amount.isEmpty()) {
            txtAmount.type(amount);
        }
        btnSubmit.click();
    }

    public String getCurrency() {
        return lblCurrency.getText().trim();
    }

    public String getErrorMessage() {
        return lblErroMessage.getText().trim();
    }

    public String getAmountLable() {
        return lblAmount.getText().trim();
    }

    public String getAmountValue() {
        return txtAmount.getAttribute("value");
    }

}
