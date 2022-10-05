package agentsite.pages.all.agentmanagement.depositwithdrawal;

import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;

public class WithdrawalPopup extends DepositToPopup {
    public Popup popupWithdrawal = Popup.xpath("//app-adjust-credit-cash-dialog//div[@id ='adjustCreditCashDialog']");
    private TextBox txtWithdrawalToAmount = TextBox.xpath("//div[@class='comp']//input");

    public void withdraw(String amount, String remark) {
        // waiting for loading
        waitingLoadingSpinner();
        btnSubmit.isInvisible(2);
        if (!amount.isEmpty()) {
            txtWithdrawalToAmount.sendKeys(amount);
        }
        if (!remark.isEmpty()) {
            txtRemark.sendKeys(remark);
        }

        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void withdraw(String amount, String remark,boolean isCreditUpdate) {
        // waiting for loading
        waitingLoadingSpinner();
        btnSubmit.isInvisible(2);
        if (!amount.isEmpty()) {
            txtWithdrawalToAmount.sendKeys(amount);
        }
        if (!remark.isEmpty()) {
            txtRemark.sendKeys(remark);
        }
        if(!isCreditUpdate)
        {
            rbtnWinLossSettle.click();
        }
        else
            rbtnCreditUpdate.click();
        // waiting for loading
        // btnSubmit.isDisplayedShort(3);
    }
    public void withdraw(String amount, String remark, boolean isCreditUpdate, boolean isSubmit) {
        withdraw(amount, remark, isCreditUpdate);
        if (isSubmit) {
            btnSubmit.click();
            waitingLoadingSpinner();
        }
    }
}
