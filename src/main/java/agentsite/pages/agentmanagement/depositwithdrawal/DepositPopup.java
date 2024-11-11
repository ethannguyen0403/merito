package agentsite.pages.agentmanagement.depositwithdrawal;

import com.paltech.element.common.*;

import static agentsite.pages.HomePage.waitingLoadingSpinner;

public class DepositPopup {
    public Popup popupDepositWithDraw = Popup.xpath("//app-adjust-credit-cash-dialog//div[@id ='adjustCreditCashDialog']");
    public Label lblTitle = Label.xpath("//div[@id='adjustCreditCashDialog']//div[contains(@class,'modal-header')]//div[contains(@class,'title')]");
    public Icon iconClose = Icon.xpath("//div[@id='adjustCreditCashDialog']//button[@class='close']");
    public Button btnSubmit = Button.id("submitBtn");
    public Label lblYourBalanceName = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//span[@class='left']");
    public Label lblYourBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//div[@class='amount']");
    public Label lblYourNewBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//div[@class='tip']");
    public Label lblMemberBalanceName = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//span[@class='left']");
    public Label lblMemberBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//div[@class='amount']");
    public Label lblMemberNewBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//div[@class='tip']");
    TextBox txtDepositToAmount = TextBox.xpath("//div[@class='comp']//input");
    TextBox txtRemark = TextBox.xpath("//textarea[@class='ng-untouched ng-pristine ng-valid']");
    Button btnCancel = Button.xpath("//div[@id='adjustCreditCashDialog']//button[@class='cancel']");
    RadioButton rbtnCreditUpdate = RadioButton.id("radio1");
    RadioButton rbtnWinLossSettle = RadioButton.id("radio2");
    public Label lblFailedMessage = Label.xpath("//div[contains(@class,'adjust-mgs failed')]/span");

    public void deposit(String amount, String remark) {
        deposit(amount, remark, true, true);
    }

    public void fillDepositInfo(String amount, String remark, boolean isCreditUpdate) {
        deposit(amount, remark, isCreditUpdate, false);
    }

    public void deposit(String amount, String remark, boolean isCreditUpdate, boolean isSubmit) {
        txtDepositToAmount.isPresent(2);
        if (!amount.isEmpty()) {
            txtDepositToAmount.sendKeys(amount);
            lblYourNewBalance.isPresent();
        }
        if (!remark.isEmpty()) {
            txtRemark.sendKeys(remark);
        }
        if (rbtnCreditUpdate.isDisplayed()) {
            if (!isCreditUpdate) {
                rbtnWinLossSettle.click();
            } else
                rbtnCreditUpdate.click();
        }
        if (isSubmit) {
            btnSubmit.click();
            btnSubmit.waitForControlInvisible(2, 4);
        }
    }

    public void closeDepositPopup() {
        if (btnCancel.isDisplayed()) {
            btnCancel.click();
        }
    }
}
