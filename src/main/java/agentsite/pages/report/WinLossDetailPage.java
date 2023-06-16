package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.DropDownBox;
import agentsite.controls.Table;
import agentsite.pages.HomePage;

import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class WinLossDetailPage extends HomePage {
    public TextBox txtFromSearch = TextBox.xpath("//div[@id='search-region']//table//tr[1]/td[2]//input");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtFromSearch,"//bs-days-calendar-view");
    public TextBox txtToSearch = TextBox.xpath("//div[@id='search-region']//table//tr[1]/td[4]//input");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtToSearch,"//bs-days-calendar-view");
    public DropDownBox ddbProduct = DropDownBox.xpath("//td[@class='product-multiselect']//angular2-multiselect", "//div[contains(@class,'dropdown-list')]//ul//li");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    public Button btnLastWeek = Button.name("lastWeek");
    public Button btnSubmit = Button.name("search");
    public Label lblShowTotal = Label.xpath("//label[@for='isShowTotal']");
    public CheckBox chbShowTotal = CheckBox.name("isShowTotal");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Label lblErrorMassage = Label.xpath("//div[@id='search-region']//div[contains(@class,'error ng-binding')]");
    public int tblReportTotalColumnFixed = 6;
    public int colUserName = 2;
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'no-record')]");
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report hasDownline')]",tblReportTotalColumnFixed);

    public WinLossDetailPage(String types){
        super(types);
    }
    public void filter(String productName) {
        if (!productName.isEmpty()) {
            ddbProduct.selectByVisibleText(productName, true, true);
        }
        btnSubmit.click();
    }
}
