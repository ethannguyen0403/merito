package agentsite.pages.all.report;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;

public class WinLossSimplePage extends LeftMenu {
    DropDownBox ddbMA = DropDownBox.xpath("(//div[@id='report-payment']//table//select)[1]");
    DropDownBox ddbAG = DropDownBox.xpath("(//div[@id='report-payment']//table//select)[2]");
    TextBox txtSearchFrom = TextBox.name("fromDate");
    TextBox txtSearchTo = TextBox.name("toDate");

    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public agentsite.controls.DropDownBox ddbProduct = agentsite.controls.DropDownBox.xpath("//td[@class='product-multiselect']", "//div[contains(@class,'dropdown-list')]//ul//li");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    public Button btnLastWeek = Button.name("lastWeek");
    public Button btnSubmit = Button.name("search");
    public Label lblDownLineBar = Label.xpath("//div[@class='downline-bar']//span[@class='ng-binding']");
    public Label lblProductErrorMassage = Label.xpath("//div[@id='search-region']//div[@class='error']");
    public Label lblYouCanSeeReportData = Label.xpath("(//span[@class='pinfo']/following::label)[1]");
    int totalCol = 7;
    public int colNo = 1;
    public int colUsername = 2;
    public int colNickname = 3;
    public int colUpLine = 4;
    public int colCurrency = 5;

    public Table tblSMA = Table.xpath("(//table[contains(@class, 'ptable report backlayTable')])[1]", totalCol);
    public Table tblAG = Table.xpath("(//table[contains(@class, 'ptable report backlayTable')])[2]", totalCol);

    public void filter(String productName) {
        if(productName.equalsIgnoreCase("UnSelect All")){
            ddbProduct.uncheckAll(true);
        }
        if(productName.equalsIgnoreCase("Select ALl"))
            ddbProduct.checkAll(true);
        if (!productName.isEmpty()) {
            ddbProduct.selectByVisibleText(productName, true, true);
        }
        btnSubmit.click();
    }

}
