package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class StatementReportPage extends HomePage {
    public Label lblNoRecord = Label.xpath("//tfoot[@class='no-record']//td");
    public TextBox txtSearchFrom = TextBox.name("fromDate");
    public TextBox txtSearchTo = TextBox.name("toDate");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public Button btnToday = Button.xpath("//div[@id='search-region']//table//tr[1]//td[5]//button");
    public Button btnYesterday = Button.xpath("//div[@id='search-region']//table//tr[1]//td[6]//button");
    public Button btnLastWeek = Button.xpath("//div[@id='search-region']//table//tr[1]//td[7]//button");
    public Button btnSubmit = Button.xpath("//div[@id='search-region']//table//tr[1]//td[8]//button");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Label lnlBreadcrumbSearchTitle = Label.xpath("//span[contains(@class,'my-breadcrumb')]/span[2]");
    public Label lnkHome = Label.xpath("//span[contains(@class,'my-breadcrumb')]/span[1]");
    public Label lblYouCanSeeReportData = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public int tblReportTotalCol = 2;
    public int colUsername = 1;
    public int colLoginId = 2;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report table-home')]", tblReportTotalCol);
    public Table tblDetailReport = Table.xpath("//table[@class='ptable report']", 5);
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]");

    public StatementReportPage(String types) {
        super(types);
    }

    public Table detailStatement(String username) {
        String xPath = tblReport.getControlxPathBasedValueOfDifferentColumnOnRow(username, 1, 1, 1, null, 3, null, false, false);
        Icon.xpath(xPath).click();
        waitingLoadingSpinner();
        return tblDetailReport;
    }

}
