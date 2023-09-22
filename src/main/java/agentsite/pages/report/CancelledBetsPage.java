package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.*;

public class CancelledBetsPage extends HomePage {
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'no-record')]");
    public TextBox txtSearchFrom = TextBox.xpath("//input[@name='fromDate']");
    public TextBox txtSearchTo = TextBox.xpath("//input[@name='toDate']");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public Button btnToday = Button.id("btn-today");
    public Button btnYesterday = Button.id("btn-yesterday");
    public Button btnLastWeek = Button.id("btn-submit");
    public Button btnSubmit = Button.xpath("//button[text()='Submit']");
    public DropDownBox ddbStatus = DropDownBox.id("select-status");
    public TextBox txtUserName = TextBox.id("username");
    public DropDownBox ddbProduct = DropDownBox.id("select-product");
    public Label lblInfo = Label.xpath("//span[@class='info-statement-report-guide']");
    public int tblReportTotalCol = 10;
    public int colNo = 1;
    public int colUsername = 2;
    public int colLoginId = 3;
    public int colVoidedDate = 4;
    public int colDescription = 5;
    public int colType = 6;
    public int colOdds = 7;
    public int colStake = 8;
    public int colStatus = 9;
    public int colRemark = 10;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]", tblReportTotalCol);

    public CancelledBetsPage(String types) {
        super(types);
    }

    public void filter(String status, String username, String productName) {
        if (!status.isEmpty())
            ddbStatus.selectByVisibleText(status);
        if (!username.isEmpty())
            txtUserName.sendKeys(username);
        if (!productName.isEmpty()) {
            ddbProduct.selectByVisibleText(productName);
        }
        btnSubmit.click();
        waitingLoadingSpinner();
    }



}
