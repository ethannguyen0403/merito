package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.*;

public class TopGainersTopLosersPage extends HomePage {
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'noRecord')]");
    public TextBox txtSearchFrom = TextBox.xpath("//label[text()='From']/following::input[1]");
    public TextBox txtSearchTo = TextBox.xpath("//label[contains(text(),'To')]/following::input[1]");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");

    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']/following::select[1]");

    public Button btnSearch = Button.xpath("//button[@class='pbtn']");
    public Label lblInfoReportRangeAvailable = Label.xpath("(//span[@class='pinfo']/following-sibling::label)[1]");
    public Label lblInfoDataSupport = Label.xpath("(//span[@class='pinfo']/following-sibling::label)[2]");
    public Label lblInfoReportPlaceTime = Label.xpath("(//span[@class='pinfo']/following-sibling::label)[3]");
    public Table tblTopGainers = Table.xpath("(//table[contains(@class,'table-sm')])[1]", 6);
    public Table tblTopLoser = Table.xpath("(//table[contains(@class,'table-sm')])[2]", 6);
    public Table tblBigStake = Table.xpath("(//table[contains(@class,'table-sm')])[3]", 6);
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]");

    public TopGainersTopLosersPage(String types) {
        super(types);
    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(1, 1);
    }


}
