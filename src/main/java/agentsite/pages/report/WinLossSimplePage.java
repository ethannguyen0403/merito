package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.report.winlosssimple.WinLossSimple;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class WinLossSimplePage extends HomePage {
    public Label lblProductErrorMassage = Label.xpath("//div[@id='search-region']//div[@class='error']");
    public int colUsername = 2;
    TextBox txtSearchFrom = TextBox.name("fromDate");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-datepicker-container");
    TextBox txtSearchTo = TextBox.name("toDate");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-datepicker-container");
    int totalCol = 7;
    public Table tblSMA = Table.xpath("(//table[contains(@class, 'backlayTable')])[1]", totalCol);
    public Table tblAG = Table.xpath("(//table[contains(@class, 'backlayTable')])[2]", totalCol);
    public WinLossSimple winLossSimple;
    public WinLossSimplePage(String types) {
        super(types);
        winLossSimple = ComponentsFactory.winLossSimple(types);
    }

    public void filter(String productName) {
        winLossSimple.filter(productName);
    }

    public void verifyUIDisplaysCorrect() {
        winLossSimple.verifyUIDisplaysCorrect();
    }
}
