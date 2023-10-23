package agentsite.pages.cashmanagement.depositwithdrawaltransaction;

import agentsite.controls.Cell;
import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.SkipException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static agentsite.pages.HomePage.waitingLoadingSpinner;
import static common.AGConstant.CashManagement.DepositWithdrawalTransaction.*;
import static common.AGConstant.LBL_USERNAME_PLACE_HOLDER_SAT;

public class OldUIDepositWithdrawalTransaction extends DepositWithdrawalTransaction {
    private int totalCol = 11;
    private Label lblDepositRadio = Label.xpath("//app-deposit-withdrawal-transaction//label[@for='deposit']");
    private RadioButton rbDeposit = RadioButton.id("deposit");
    private Label lblUsername = Label.xpath("//app-deposit-withdrawal-transaction//label[text()='Username']");
    private Label lblTransactionStatus = Label.xpath("//app-deposit-withdrawal-transaction//label[text()='Transaction Status']");
    private Label lblPaymentType = Label.xpath("//app-deposit-withdrawal-transaction//label[text()='Payment Type']");
    private Label lblFrom = Label.xpath("//app-deposit-withdrawal-transaction//label[text()='From']");
    private Label lblTo = Label.xpath("//app-deposit-withdrawal-transaction//label[text()='To']");
    private Label lblInternalRefNo = Label.xpath("//app-deposit-withdrawal-transaction//label[text()='Internal Ref No']");
    private TextBox txtUsername = TextBox.xpath("//app-deposit-withdrawal-transaction//input[@placeholder='Username or Login ID']");
    private DropDownBox ddbTransactionStatus = DropDownBox.xpath("//app-deposit-withdrawal-transaction//label[text()='Transaction Status']//..//select");
    private DropDownBox ddbPaymentType = DropDownBox.xpath("//app-deposit-withdrawal-transaction//label[text()='Payment Type']//..//select");
    private TextBox txtFrom = TextBox.xpath("//app-deposit-withdrawal-transaction//label[text()='From']//..//input");
    private DateTimePicker dpFrom = DateTimePicker.xpath(txtFrom, "//bs-calendar-layout");
    private TextBox txtTo = TextBox.xpath("//app-deposit-withdrawal-transaction//label[text()='To']//..//input");
    private DateTimePicker dpTo = DateTimePicker.xpath(txtTo, "//bs-calendar-layout");
    private TextBox txtInternalRefNo = TextBox.xpath("//app-deposit-withdrawal-transaction//label[text()='Internal Ref No']//..//input");
    private Button btnSearch = Button.xpath("//app-deposit-withdrawal-transaction//button[text()='Search']");
    private Table tblDeposit = Table.xpath("//app-deposit-withdrawal-transaction//table", totalCol);
    private TextBox txtComment = TextBox.xpath("//app-transaction-detail//textarea");
    private RadioButton rdApprove = RadioButton.id("approve");
    private RadioButton rdReject = RadioButton.id("reject");
    private Button btnSubmitTransactionDetail = Button.xpath("//app-transaction-detail//button[text()=' Submit']");
    private Button btnOk = Button.xpath("//app-alert//button[text()='OK']");
    private Label lblTitle = Label.xpath(" //app-deposit-withdrawal-transaction//div[@class='title']//label");
    private Label lblNoRecordFound = Label.xpath("//app-deposit-withdrawal-transaction//table//td[text()='No records found.']");

    public String getPageTitle() { return lblTitle.getText().trim();}

    public void search (String userName, String transactionStatus, String paymentType, String fromDate, String toDate, String refNo) {
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
        openTransactionDetail(TRANSACTION_DETAIL_ACTION_LST.get(0));
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

    public void verifyUIDisplayCorrect() {
        Assert.assertEquals(lblTitle.getText(), LBL_PAGE_TITLE, "FAILED! Page title displays incorrectly");
        Assert.assertEquals(lblDepositRadio.getText().trim(), "Deposit", "FAILED! Deposit radio button label displays incorrectly");
        Assert.assertEquals(lblUsername.getText().trim(), "Username", "FAILED! Deposit radio button label displays incorrectly");
        Assert.assertEquals(lblTransactionStatus.getText().trim(), "Transaction Status", "FAILED! Deposit radio button label displays incorrectly");
        Assert.assertEquals(lblPaymentType.getText().trim(), "Payment Type", "FAILED! Deposit radio button label displays incorrectly");
        Assert.assertEquals(lblFrom.getText().trim(), "From", "FAILED! Deposit radio button label displays incorrectly");
        Assert.assertEquals(lblTo.getText().trim(), "To", "FAILED! Deposit radio button label displays incorrectly");
        Assert.assertEquals(lblInternalRefNo.getText().trim(), "Internal Ref No", "FAILED! Deposit radio button label displays incorrectly");
        Assert.assertTrue(rbDeposit.isDisplayed(), "FAILED! Deposit radio button does not display");
        Assert.assertTrue(txtUsername.isDisplayed(), "FAILED! Username textbox does not display");
        Assert.assertTrue(ddbTransactionStatus.isDisplayed(), "FAILED! Transaction Status dropdown does not display");
        Assert.assertTrue(ddbPaymentType.isDisplayed(), "FAILED! Payment Type dropdown does not display");
        Assert.assertTrue(txtFrom.isDisplayed(), "FAILED! From textbox does not display");
        Assert.assertTrue(txtTo.isDisplayed(), "FAILED! To textbox does not display");
        Assert.assertTrue(txtInternalRefNo.isDisplayed(), "FAILED! Internal Ref No textbox does not display");
        List<String> lstTableHeader = tblDeposit.getHeaderList();
        Assert.assertEquals(lstTableHeader, TBL_DEPOSIT_WITHDRAWAL_HEADER, "FAILED! Header list of table does not show correct");
        Assert.assertEquals(txtUsername.getAttribute("placeholder"), LBL_USERNAME_PLACE_HOLDER_SAT, "FAILED! Placeholder of username textbox does not display correct");
        Assert.assertEquals(ddbTransactionStatus.getOptions(), LST_TRANSACTION_STATUS, "FAILED! List options of Transaction Status does not display correct");
        Assert.assertEquals(ddbPaymentType.getOptions(), LST_PAYMENT_TYPE, "FAILED! List options of Payment Type does not display correct");
        Assert.assertTrue(isDateFollowFormat(txtFrom.getAttribute("value"), "yyyy-MM-dd"));
        Assert.assertTrue(isDateFollowFormat(txtTo.getAttribute("value"), "yyyy-MM-dd"));
    }

    public boolean isDateFollowFormat(String dateValidate, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(dateValidate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(dateValidate.equals(sdf.format(date))) {
            return true;
        } else {
            return false;
        }
    }

    public void verifySearchResult(String username, String loginId, String transactionStatus, String paymentType, String fromDate, String toDate, String refNo) {
        if(lblNoRecordFound.isDisplayed()) {
            throw new SkipException("Have no transaction record found for testing");
        }
        int colIndex;
        int totalRow = tblDeposit.getNumberOfRows(false, true);
        //verify Username
        if(!username.isEmpty()) {
            colIndex = tblDeposit.getColumnIndexByName("Username");
            for (int i = 0; i < totalRow; i++) {
                Assert.assertEquals(tblDeposit.getControlOfCell(1, colIndex, i + 1, null).getText(), username);
            }
        }
        //verify LoginID
        if (!loginId.isEmpty()) {
            colIndex = tblDeposit.getColumnIndexByName("Login ID");
            for (int i = 0; i < totalRow; i++) {
                Assert.assertEquals(tblDeposit.getControlOfCell(1, colIndex, i + 1, null).getText(), loginId);
            }
        }
        //verify Transaction Status
        if (!transactionStatus.isEmpty()) {
            colIndex = tblDeposit.getColumnIndexByName("Status");
            if(transactionStatus.equalsIgnoreCase("All")) {
                List<String> lstStatus = Arrays.asList("Pending", "Approved", "Rejected");
                for (int i = 0; i < totalRow; i++) {
                    Assert.assertTrue(lstStatus.contains(tblDeposit.getControlOfCell(1, colIndex, i + 1, null).getText()));
                }
            } else {
                for (int i = 0; i < totalRow; i++) {
                    Assert.assertEquals(tblDeposit.getControlOfCell(1, colIndex, i + 1, null).getText(), transactionStatus);
                }
            }
        }
        //verify Payment Type
        if (!paymentType.isEmpty()) {
            colIndex = tblDeposit.getColumnIndexByName("Status");
            if(paymentType.equalsIgnoreCase("All")) {
                List<String> lstPaymentType = Arrays.asList("BANK TRANSFER", "PAYTM", "PHONEPE", "GPAY", "UPI", "QR Code");
                for (int i = 0; i < totalRow; i++) {
                    Assert.assertTrue(lstPaymentType.contains(tblDeposit.getControlOfCell(1, colIndex, i + 1, null).getText()));
                }
            } else {
                for (int i = 0; i < totalRow; i++) {
                    Assert.assertEquals(tblDeposit.getControlOfCell(1, colIndex, i + 1, null).getText(), paymentType);
                }
            }
        }
        //verify From/To Date
        if(!fromDate.isEmpty() && !toDate.isEmpty()) {
            colIndex = tblDeposit.getColumnIndexByName("Created Date");
            List<String> lst = tblDeposit.getColumn(colIndex, true);
            try {
                for (int i = 0; i < colIndex; i++) {
                    Assert.assertTrue(DateUtils.isDateBetweenPeriod(tblDeposit.getControlOfCell(1, colIndex, i + 1, null).getText(), fromDate, toDate, "yyyy-MM-dd"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void openTransactionDetail(String action) {
        Cell reviewCell = tblDeposit.getCellByName(action, false);
        reviewCell.click();
    }

}
