package agentsite.pages.all.agentmanagement.depositwithdrawal;

import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;

public class WithdrawalPopup extends DepositToPopup {
    public Popup popupWithdrawal = Popup.xpath("//app-adjust-credit-cash-dialog//div[@id ='adjustCreditCashDialog']");
    private TextBox txtWithdrawalToAmount = TextBox.xpath("//div[@class='comp']//input");

    public void withdraw(String amount, String remark) {
        withdraw(amount,remark,true,false);
    }

    public void fillWithdrawInfo(String amount, String remark, boolean isCreditUpdate) {
       withdraw(amount,remark,isCreditUpdate,false);
    }
    public void withdraw(String amount, String remark, boolean isCreditUpdate, boolean isSubmit) {
        // waiting for loading
        btnSubmit.isInvisible(2);
        if (!amount.isEmpty()) {
            txtWithdrawalToAmount.sendKeys(amount);
        }
        if (!remark.isEmpty()) {
            txtRemark.sendKeys(remark);
        }
        if(rbtnCreditUpdate.isDisplayed()){
        if(!isCreditUpdate)
        {
            rbtnWinLossSettle.click();
        }
        else
            rbtnCreditUpdate.click();
        }
        if (isSubmit) {
            btnSubmit.click();
        }
    }
}
