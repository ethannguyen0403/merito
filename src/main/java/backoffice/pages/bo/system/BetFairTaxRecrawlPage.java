package backoffice.pages.bo.system;

import backoffice.controls.DateTimePicker;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class BetFairTaxRecrawlPage extends HomePage {
    public Label lblTitlePage = Label.id("bo-page-title");
    public DropDownBox dpAccountBetfair = DropDownBox.xpath("//div[@id='betfairTaxRecrawl']//select");
    public TextBox txtDateRangeFrom = TextBox.name("fromDate");
    private DateTimePicker dtpDateRangeFrom = DateTimePicker.xpath(txtDateRangeFrom,"//bs-days-calendar-view");
    public TextBox txtDateRangeTo = TextBox.name("toDate");
    private DateTimePicker dtpDateRangeTo = DateTimePicker.xpath(txtDateRangeTo,"//bs-days-calendar-view");
    private Button btnSubmit = Button.name("submit");
    public Label lblStatus = Label.xpath("//div[@class='status']//span");

    public void search(String accountBetfair, String fromDate, String toDate, boolean isSubmit) {
        if (!accountBetfair.isEmpty())
            dpAccountBetfair.selectByVisibleText(accountBetfair);
        if(!fromDate.isEmpty())
            dtpDateRangeFrom.selectDate(fromDate,"yyyy/MM/dd");
        if(!toDate.isEmpty())
            dtpDateRangeTo.selectDate(toDate,"yyyy/MM/dd");
        if (isSubmit) {
            btnSubmit.click();
        }
    }
}
