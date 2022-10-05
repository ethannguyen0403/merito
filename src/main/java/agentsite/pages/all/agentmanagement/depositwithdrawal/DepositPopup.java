package agentsite.pages.all.agentmanagement.depositwithdrawal;

import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import com.paltech.element.common.*;
import agentsite.pages.all.components.BasePage;

public class DepositPopup extends BasePage {
    public Popup popupDepositWithDraw = Popup.xpath("//app-adjust-credit-cash-dialog//div[@id ='adjustCreditCashDialog']");
    public Label lblTitle = Label.xpath("//div[@id='adjustCreditCashDialog']//div[contains(@class,'modal-header')]//div[contains(@class,'title')]");
    public Icon iconClose = Icon.xpath("//div[@id='adjustCreditCashDialog']//button[@class='close']");
    TextBox txtDepositToAmount = TextBox.xpath("//div[@class='comp']//input");
    TextBox txtRemark = TextBox.xpath("//textarea[@class='ng-untouched ng-pristine ng-valid']");
    public Button btnSubmit = Button.id("submitBtn");
    Button btnCancel = Button.xpath("//div[@id='adjustCreditCashDialog']//button[@class='cancel']");
    public Label lblYourBalanceName = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//span[@class='left']");
    public Label lblYourBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//div[@class='amount']");
    public Label lblYourNewBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//div[@class='tip']");
    public Label lblMemberBalanceName = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//span[@class='left']");
    public Label lblMemberBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//div[@class='amount']");
    public Label lblMemberNewBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//div[@class='tip']");
    RadioButton rbtnCreditUpdate = RadioButton.id("radio1");
    RadioButton rbtnWinLossSettle = RadioButton.id("radio2");
    public void deposit(String amount, String remark) {
        txtDepositToAmount.isPresent(2);
        if (!amount.isEmpty()) {
            txtDepositToAmount.sendKeys(amount);
            lblYourNewBalance.isPresent();
        }
        if (!remark.isEmpty()) {
            txtRemark.sendKeys(remark);
        }
        btnSubmit.click();
        waitingLoadingSpinner();

    }

    public void deposit(String amount, String remark, boolean isCreditUpdate) {
        txtDepositToAmount.isPresent(2);
        if (!amount.isEmpty()) {
            txtDepositToAmount.sendKeys(amount);
            lblYourNewBalance.isPresent();
        }
        if (!remark.isEmpty()) {
            txtRemark.sendKeys(remark);
        }
        if (!isCreditUpdate) {
            rbtnWinLossSettle.click();
        } else
            rbtnCreditUpdate.click();

    }

   public void deposit(String amount, String remark, boolean isCreditUpdate, boolean isSubmit) {
        deposit(amount, remark, isCreditUpdate);
        if (isSubmit) {
            btnSubmit.click();
            waitingLoadingSpinner();
        }
    }

    public DepositWithdrawalPage closeDepositPopup() {
        if(btnCancel.isDisplayed()) {
            btnCancel.click();
            waitingLoadingSpinner();
        }
        return new DepositWithdrawalPage();
    }
}
