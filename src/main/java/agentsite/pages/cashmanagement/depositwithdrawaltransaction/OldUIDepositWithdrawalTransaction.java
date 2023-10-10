package agentsite.pages.cashmanagement.depositwithdrawaltransaction;

import agentsite.controls.Cell;
import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import com.paltech.element.common.*;

import static agentsite.pages.HomePage.waitingLoadingSpinner;

public class OldUIDepositWithdrawalTransaction extends DepositWithdrawalTransaction {
    private int totalCol = 11;
    private TextBox txtUsername = TextBox.xpath("//app-deposit-withdrawal-transaction//input[@placeholder='Username or Login ID']");
    private DropDownBox ddbTransactionStatus = DropDownBox.xpath("//app-deposit-withdrawal-transaction//label[text()='Transaction Status']//..//select");
    private DropDownBox ddbPaymentType = DropDownBox.xpath("//app-deposit-withdrawal-transaction//label[text()='Payment Type']//..//select");
    private TextBox txtFromSearch = TextBox.xpath("//app-deposit-withdrawal-transaction//label[text()='From']//..//input");
    private DateTimePicker dpFrom = DateTimePicker.xpath(txtFromSearch, "//bs-calendar-layout");
    private TextBox txtToSearch = TextBox.xpath("//app-deposit-withdrawal-transaction//label[text()='To']//..//input");
    private DateTimePicker dpTo = DateTimePicker.xpath(txtToSearch, "//bs-calendar-layout");
    private TextBox txtInternalRefNo = TextBox.xpath("//app-deposit-withdrawal-transaction//label[text()='Internal Ref No']//..//input");
    private Button btnSearch = Button.xpath("//app-deposit-withdrawal-transaction//button[text()='Search']");
    private Table tblDeposit = Table.xpath("//app-deposit-withdrawal-transaction//table", totalCol);
    private TextBox txtComment = TextBox.xpath("//app-transaction-detail//textarea");
    private RadioButton rdApprove = RadioButton.id("approve");
    private RadioButton rdReject = RadioButton.id("reject");
    private Button btnSubmitTransactionDetail = Button.xpath("//app-transaction-detail//button[text()=' Submit']");
    private Button btnOk = Button.xpath("//app-alert//button[text()='OK']");
    private Label lblTitle = Label.xpath(" //app-deposit-withdrawal-transaction//div[@class='title']//label");

    public String getPageTitle() { return lblTitle.getText().trim();}

    public void filter (String userName, String transactionStatus, String paymentType, String fromDate, String toDate, String refNo) {
        if (!userName.isEmpty()) {
            txtUsername.sendKeys(userName);
        }
        if (!transactionStatus.isEmpty()) {
            ddbTransactionStatus.selectByVisibleText(transactionStatus);
        }
        if (!paymentType.isEmpty()) {
            ddbPaymentType.selectByVisibleText(paymentType);
        }
        if (!fromDate.isEmpty()) {
            dpFrom.selectDate(fromDate, "yyyy-MM-dd");
        }
        if (!toDate.isEmpty()) {
            dpTo.selectDate(toDate, "yyyy-MM-dd");
        }
        if (!refNo.isEmpty()) {
            txtInternalRefNo.sendKeys(refNo);
        }
        btnSearch.click();
        waitingLoadingSpinner();
    }

    public void actionTransaction(String action, String comment, boolean isSubmit) {
        Cell reviewCell = tblDeposit.getCellByName("Review", false);
        reviewCell.click();
        txtComment.waitForElementToBePresent(txtComment.getLocator(), 2);
        if (!comment.isEmpty()) {
            txtComment.sendKeys(comment);
        }
        if(action.equalsIgnoreCase("approve")) {
            rdApprove.click();
        } else if (action.equalsIgnoreCase("reject")) {
            rdReject.click();
        }
        if (isSubmit) {
            btnSubmitTransactionDetail.click();
            txtComment.waitForControlInvisible();
        }
    }

    public void closeActionTransactionAlertMessage() {
        btnOk.click();
    }

}
