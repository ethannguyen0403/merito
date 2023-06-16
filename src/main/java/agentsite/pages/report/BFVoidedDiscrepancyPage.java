package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class BFVoidedDiscrepancyPage extends HomePage {
    public TextBox txtFromSearch = TextBox.xpath("//div[@id='cancelbet']//div[@class='search-region']//tr[1]/td[2]//input");
    public TextBox txtToSearch = TextBox.xpath("//div[@id='cancelbet']//div[@class='search-region']//tr[1]/td[4]//input");
    public DateTimePicker dtpFrom = DateTimePicker.xpath(txtFromSearch,"//bs-days-calendar-view");
    public DateTimePicker dtpTo = DateTimePicker.xpath(txtToSearch,"//bs-days-calendar-view");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    public Button btnLastBusinessWeek = Button.name("lastWeek");
    public Button btnSubmit = Button.name("search");
    public TextBox txtWagerId = TextBox.xpath("//div[@id='cancelbet']//div[@class='search-region']//tr[2]/td[2]//input");
    public DropDownBox ddbSport = DropDownBox.xpath("//div[@id='cancelbet']//div[@class='search-region']//tr[2]/td[4]//select");
    public DropDownBox ddbEvent = DropDownBox.xpath("//div[@id='cancelbet']//div[@class='search-region']//tr[2]/td[6]//select");
    public Label lblInfo= Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Label lblSearchRangeTitle = Label.xpath("//div[@class='downline-bar']/span");
    public Label lblNoReport = Label.xpath("//td[contains(@class,'noRecord')]");

    public int tblReportTotalCoumn = 13;
    public int colWagerId = 2;
    public int colUsercode = 3;
    public int colLoginID = 4;
    public int colStatus = 9;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]",tblReportTotalCoumn);
    public BFVoidedDiscrepancyPage(String types){
        super(types);
    }

}
