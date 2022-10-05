package agentsite.pages.all.report;

import com.paltech.element.common.*;
import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;

public class TransferLogPage extends LeftMenu {
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'no-record')]");
    public TextBox txtSearchFrom = TextBox.id("fromDate");
    public TextBox txtSearchTo = TextBox.id("//div[@id='search-region']//tr[2]//td[4]//input");
    public TextBox txtUserName = TextBox.xpath("//div[@id='search-region']//tr[2]//td[1]//input");
    public DropDownBox ddpAuditType = DropDownBox.xpath("//div[@id='search-region']//tr[2]//td[2]//select");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    public Button btnLastWeek = Button.name("lastWeek last-week");
    public Button btnSubmit = Button.name("search");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]");

    public int tblReportTotalCol = 10;
    public int colUsername = 3;
    public int colLoginId = 4;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]",tblReportTotalCol);

    public void filter(String userName, String auditType){
        if(!userName.isEmpty())
            txtUserName.sendKeys(userName);
        if(!auditType.isEmpty())
            ddpAuditType.selectByVisibleText(auditType);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void  waitingLoadingSpinner(){
        iconLoadSpinner.waitForControlInvisible(1,1);
    }

}
