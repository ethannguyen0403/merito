package agentsite.pages.report.profitandloss;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class ProfitAndLoss {
    protected Label lblTimeZone = Label.xpath("//div[@id='search-region']//table//tr[1]/td[1]");
    protected Label lblFrom = Label.xpath("//div[@id='search-region']//table//tr[1]/td[3]");
    protected Label lblTo = Label.xpath("//div[@id='search-region']//table//tr[1]/td[5]");
    protected Label lblProduct = Label.xpath("//div[@id='search-region']//table//tr[1]/td[7]");
    protected DropDownBox ddbTimeZone = DropDownBox.id("timezone");
    protected TextBox txtSearchTo = TextBox.xpath("//div[@id='search-region']//table//tr[1]/td[6]//input");
    protected TextBox txtSearchFrom = TextBox.xpath("//div[@id='search-region']//table//tr[1]/td[4]//input");
    protected DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-calendar-layout");
    protected DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-calendar-layout");
    protected Button btnToday = Button.name("today");
    protected Button btnYesterday = Button.name("yesterday");
    protected Button btnLastWeek = Button.name("lastWeek");
    protected Button btnSubmit = Button.name("search");
    protected Label lblYouCanSeeReportData = Label.xpath("(//span[@class='pinfo']/following::label)[1]");
    protected Label lblForISTTimeZoneReportAvailable = Label.xpath("(//span[@class='pinfo']/following::label)[2]");
    protected Label lblUplineProfitAndLoss = Label.xpath("//div[@class='paymenTitle'][1]");
    protected Label lblDownlineProfitAndLoss = Label.xpath("//div[@class='paymenTitle'][2]");
    protected Table tblUplineProfitAndLoss = Table.xpath("//table[contains(@class ,'ptable report backlayTable')][1]", 7);
    protected Table tblDownLineProfitAndLoss = Table.xpath("//table[contains(@class , 'ptable report backlayTable')][2]", 12);
   public void verifyUIDisplayCorrect(boolean isLevelLoginPO) {}
}
