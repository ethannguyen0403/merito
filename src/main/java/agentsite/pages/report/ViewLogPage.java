package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class ViewLogPage extends HomePage {
    DropDownBox ddbMA = DropDownBox.xpath("(//div[@id='report-payment']//table//select)[1]");
    DropDownBox ddbAG = DropDownBox.xpath("(//div[@id='report-payment']//table//select)[2]");
    TextBox txtSearchFrom = TextBox.xpath("//input[contains(@ng-model, 'fromSearchDate')]");
    TextBox txtSearchTo = TextBox.xpath("//input[contains(@ng-model, 'toSearchDate')]");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public agentsite.controls.DropDownBox ddbProduct = agentsite.controls.DropDownBox.xpath("//multiselect[@ng-model='selectedProduct']", "//multiselect[@ng-model='selectedProduct']//li[@class='ng-scope']//div");
    Button btnToday = Button.name("today");
    Button btnYesterday = Button.name("yesterday");
    Button btnLastWeek = Button.name("lastWeek");
    Button btnSubmit = Button.name("search");
    Label lblDownLineBar = Label.xpath("//div[@class='downline-bar']//span[@class='ng-binding']");
    public Label lblProductErrorMassage = Label.xpath("//div[@id='search-region']//div[@class='error ng-binding']");

    int totalCol = 7;
    public int colNo = 1;
    public int colUsername = 2;
    public int colNickname = 3;
    public int colUpLine = 4;
    public int colCurrency = 5;

    public Table tblSMA = Table.xpath("(//table[contains(@class, 'ptable report backlayTable')])[1]", totalCol);
    public Table tblAG = Table.xpath("(//table[contains(@class, 'ptable report backlayTable')])[2]", totalCol);
    public ViewLogPage(String types){
        super(types);
    }
    public void filter(String productName) {
        if (!productName.isEmpty()) {
            ddbProduct.selectByVisibleText(productName, true, true);
        }
        btnSubmit.click();
    }

}
