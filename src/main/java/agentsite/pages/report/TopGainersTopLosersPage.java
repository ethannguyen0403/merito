package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.report.topgainerstoplosers.TopGainersTopLosers;
import com.paltech.element.common.*;

public class TopGainersTopLosersPage extends HomePage {
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'noRecord')]");
    public TextBox txtSearchFrom = TextBox.xpath("//label[text()='From']/parent::td/following-sibling::td[1]//input");
    public TextBox txtSearchTo = TextBox.xpath("//label[contains(text(),'To')]/parent::td/following-sibling::td[1]//input");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-datepicker-container");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-datepicker-container");

    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']/following::select[1]");

    public Button btnSearch = Button.xpath("//button[@class='pbtn']");
    public Label lblInfoReportRangeAvailable = Label.xpath("(//span[@class='pinfo']/following-sibling::label)[1]");
    public Label lblInfoDataSupport = Label.xpath("(//span[@class='pinfo']/following-sibling::label)[2]");
    public Label lblInfoReportPlaceTime = Label.xpath("(//span[@class='pinfo']/following-sibling::label)[3]");
    public Table tblTopGainers = Table.xpath("(//table[contains(@class,'table-sm')])[1]", 6);
    public Table tblTopLoser = Table.xpath("(//table[contains(@class,'table-sm')])[2]", 6);
    public Table tblBigStake = Table.xpath("(//table[contains(@class,'table-sm')])[3]", 6);

    public TopGainersTopLosers topGainersTopLosers;

    public TopGainersTopLosersPage(String types) {
        super(types);
        topGainersTopLosers = ComponentsFactory.topGainersTopLosers(types);
    }

    public void search(String fromday, String today, String product){
        if (!fromday.isEmpty()){
            dpFrom.selectDate(fromday, "yyyy-MM-dd");
        }
        if (!today.isEmpty()){
            dpTo.selectDate(today, "yyyy-MM-dd");
        }
        if (!product.isEmpty()){
            ddbProduct.selectByVisibleText(product);
        }
        btnSearch.click();
    }
}
