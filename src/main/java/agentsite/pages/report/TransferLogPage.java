package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class TransferLogPage extends HomePage {
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
    public int tblReportTotalCol = 10;
    public int colUsername = 3;
    public int colLoginId = 4;
    public Table tblReport = Table.xpath("//tab[@class='tabs pt-1 tab-pane active']//table[contains(@class,'ptable report')]", tblReportTotalCol);

    public TransferLogPage(String types) {
        super(types);
    }

    public void filter(String userName, String auditType) {
        if (!userName.isEmpty())
            txtUserName.sendKeys(userName);
        if (!auditType.isEmpty())
            ddpAuditType.selectByVisibleText(auditType);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void verifyLogShowCorrectly(List<ArrayList<String>> logData, List<ArrayList<String>> expectedData){
        //get the latest log transfer
        int index = logData.size() - 1;
        Assert.assertEquals(logData.get(index).get(1).trim(), expectedData.get(1).get(1).trim(), "FAILED! Audit Date is not correct");
        Assert.assertEquals(logData.get(index).get(7).trim(), expectedData.get(1).get(7).trim(), "FAILED! Amount is not correct");
        Assert.assertEquals(logData.get(index).get(9).trim(), expectedData.get(1).get(9).trim(), "FAILED! Description is not correct");
    }

}
