package backoffice.pages.bo.temp;

import backoffice.controls.DateTimePicker;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.TextBox;

public class RunnerStatusPage extends HomePage {
    public Button btnSearch = Button.xpath("//button[text()='Search']");
    public Button btnCheckExternal = Button.xpath("//button[text()='Check External']");
    public Button btnSave = Button.xpath("//button[text()='Save']");
    public int colEventID = 1;
    public int colMarketID = 2;
    TextBox txtEventDate = TextBox.name("dp");
    DateTimePicker dtpEventDate = DateTimePicker.xpath(txtEventDate, "/,ngb-datepicker");
    DropDownBox ddpSport = DropDownBox.xpath("//span[text()='Sport']/parent::div/following::select[1]");
    DropDownBox ddpEvent = DropDownBox.xpath("//span[text()='Event']/parent::div/following::select[1]");
    DropDownBox ddpMarket = DropDownBox.xpath("//span[text()='Market']/parent::div/following::select[1]");
    private int totalColumn = 8;
    public StaticTable tblRunnerStatus = StaticTable.xpath("//div[@class='custom-table']", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);

    public void search(String date, String sportName, String eventName, String marketName) {
        if (!date.isEmpty())
            dtpEventDate.selectDate(date, "yyyy-MM-dd");
        if (!sportName.isEmpty())
            ddpSport.selectByVisibleText(sportName);
        if (!eventName.isEmpty())
            ddpEvent.selectByVisibleText(eventName);
        if (!marketName.isEmpty())
            ddpMarket.selectByVisibleText(marketName);
        btnSearch.click();
    }

}
