package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class ClientLedgerPage extends HomePage {

    public Label lblFrom = Label.xpath("//div[@id='search-region']/table[1]//tr[1]//td[1]");
    public Label lblTo = Label.xpath("//div[@id='search-region']/table[1]//tr[1]//td[2]");
    public Label lblType = Label.xpath("//div[@id='search-region']/table[1]//tr[1]//td[3]");
    public Label lblUserName = Label.xpath("//div[@id='search-region']/table[2]//tr[1]//td[1]");
    public Label lblMode = Label.xpath("//div[@id='search-region']/table[2]//tr[1]//td[2]");
    public Label lblProduct = Label.xpath("//div[@id='search-region']/table[2]//tr[1]//td[3]");
    public TextBox txtSearchFrom = TextBox.xpath("//div[@id='search-region']/table[1]//tr[2]//td[1]//input");
    public TextBox txtSearchTo = TextBox.xpath("//div[@id='search-region']/table[1]//tr[2]//td[2]//input");
    public TextBox txtUsername = TextBox.xpath("//div[@id='search-region']/table[2]//tr[2]//td[1]//input");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public DropDownBox ddbType = DropDownBox.xpath("//div[@id='search-region']/table[1]//tr[2]//td[3]//select");
    public DropDownBox ddbMode = DropDownBox.xpath("//div[@id='search-region']/table[2]//tr[2]//td[2]//select");
    public DropDownBox ddbProduct = DropDownBox.xpath("//div[@id='search-region']/table[2]//tr[2]//td[3]//select");
    public int colUsername = 1;
    public int colLoginID = 2;
    public int colProfitAndLoss = 3;
    public int colTransfer = 4;
    Button btnToday = Button.name("today");
    Button btnYesterday = Button.name("yesterday");
    Button btnLastWeek = Button.name("lastWeek");
    Button btnSubmit = Button.name("search");
    Label lblTimeRange = Label.xpath("//div[@class='downline-bar']//span[@class='my-breadcrumb']//span[@class='extension']");
    Label lblBreadcrumb = Label.xpath("//div[@class='downline-bar']//span[@class='my-breadcrumb']");
    int totalCol = 8;
    public Table tblClientLedger = Table.xpath("(//table[contains(@class, 'ptable report')])", totalCol);

    public ClientLedgerPage(String types) {
        super(types);
    }

    public void filter(String from, String to, String type, String userName, String mode, String product) {
        if (!from.isEmpty()) {
            dpFrom.selectDate(from, "dd/MM/yyyy");
        }
        if (!to.isEmpty()) {
            dpTo.selectDate(from, "dd/MM/yyyy");
        }
        if (!type.isEmpty()) {
            ddbType.selectByVisibleText(type);
            waitingLoadingSpinner();
        }
        if (!mode.isEmpty()) {
            ddbMode.selectByVisibleText(mode);
        }
        if (!product.isEmpty()) {
            ddbProduct.selectByVisibleText(product);
            waitingLoadingSpinner();
        }
        if (!userName.isEmpty()) {
            txtUsername.sendKeys(userName);
        }
        btnSubmit.click();
        waitingLoadingSpinner();
    }

}
