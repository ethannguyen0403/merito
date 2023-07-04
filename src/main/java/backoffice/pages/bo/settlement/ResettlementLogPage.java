package backoffice.pages.bo.settlement;

import backoffice.controls.DateTimePicker;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;

public class ResettlementLogPage extends HomePage {
    public RadioButton rbSearchByWagerID = RadioButton.xpath("//input[@value='WAGER_PAGE']");
    public RadioButton rbSearchByEventMarket = RadioButton.xpath("//input[@value='EVENT_PAGE']");
    public RadioButton rbSearchByUsername = RadioButton.xpath("//input[@value='USER_PAGE']");
    public TextBox txtWagerID = TextBox.name("wager-id");
    public TextBox txtEventDate = TextBox.name("event-date");
    public DateTimePicker dtpEventDate = DateTimePicker.xpath(txtEventDate, "//bs-datepicker-container");
    public DropDownBox ddbSport = DropDownBox.name("sports");
    public DropDownBox ddbEvent = DropDownBox.name("events");
    public DropDownBox ddbMarket = DropDownBox.name("markets");
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtResettleFromDate = TextBox.name("start-date");
    public DateTimePicker ddpResettleFromDate = DateTimePicker.xpath(txtResettleFromDate, "//bs-datepicker-container");
    public TextBox txtResettleToDate = TextBox.name("end-date");
    public DateTimePicker ddpResettleToDate = DateTimePicker.xpath(txtResettleToDate, "//bs-datepicker-container");
    public Button btnSearch = Button.name("search");
    public int colBetID = 3;
    public int colAffectPlayer = 4;
    public int colDescription = 2;
    private int totalColumn = 7;
    public StaticTable tblResettlementLog = StaticTable.xpath("//div[@class='custom-table']", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);

    public void searchByWagerID(String wagerId) {
        rbSearchByWagerID.click();
        txtWagerID.sendKeys(wagerId);
        btnSearch.click();
        waitSpinIcon();
    }

    public void searchByEventMarket(String eventDate, String sport, String event, String market) {
        rbSearchByEventMarket.click();
        waitSpinIcon();
        if (!eventDate.isEmpty()) {
            dtpEventDate.selectDate(eventDate, "yyyy/MM/dd");
            waitSpinIcon();
        }
        if (!sport.isEmpty())
            ddbSport.selectByVisibleContainsText(sport);
        if (!event.isEmpty())
            ddbEvent.selectByVisibleText(event);
        if (!market.isEmpty())
            ddbMarket.selectByVisibleText(market);
        btnSearch.click();
        waitSpinIcon();
    }

    public void searchByUsername(String startDate, String endDate, String username) {
        rbSearchByUsername.click();
        if (!startDate.isEmpty())
            ddpResettleFromDate.selectDate(startDate, "yyyy/MM/dd");
        if (!startDate.isEmpty())
            ddpResettleToDate.selectDate(endDate, "yyyy/MM/dd");

        txtUsername.sendKeys(username);
        btnSearch.click();
        waitSpinIcon();
    }


}
