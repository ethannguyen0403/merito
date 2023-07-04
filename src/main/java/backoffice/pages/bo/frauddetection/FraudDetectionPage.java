package backoffice.pages.bo.frauddetection;

import backoffice.controls.DateTimePicker;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.*;

public class FraudDetectionPage extends HomePage {
    public RadioButton rbEventDate = RadioButton.xpath("//input[@value='BY_EVENT_DATE']");
    public RadioButton rbPlacedDate = RadioButton.xpath("//input[@value='BY_PLACED_DATE']");
    public DropDownBox ddpSearchBy = DropDownBox.name("search-by");
    public TextBox txtEventDate = TextBox.name("dp");
    public DateTimePicker dtpEventDate = DateTimePicker.xpath(txtEventDate, "//div[contains(@ng-switch,'datepickerMode')]");
    public DropDownBox ddpSport = DropDownBox.name("sports");
    public DropDownBox ddpCompetition = DropDownBox.name("competitons");
    public DropDownBox ddpEvent = DropDownBox.name("events");
    public DropDownBox ddpMarket = DropDownBox.name("markets");
    public DropDownBox ddpStatus = DropDownBox.name("status");
    public DropDownBox ddpWonAmount = DropDownBox.name("won-amount");
    public DropDownBox ddpMatchedWithin = DropDownBox.name("matched-within");
    public Button btnSearchEventDate = Button.name("submit");

    public DropDownBox ddpSportPlaceDate = DropDownBox.xpath("//div[contains(text(),'Sport')]/following::select[1]");
    public DropDownBox ddpStatusPlaceDate = DropDownBox.xpath("//div[contains(text(),'Status')]/following::select[1]");
    public DropDownBox ddpWonAmountPlaceDate = DropDownBox.xpath("//div[contains(text(),'Won Amount')]/following::select[1]");
    public DropDownBox ddpMatchedWithinPlaceDate = DropDownBox.xpath("//div[contains(text(),'Matched Within')]/following::select[1]");
    public Button btnSearchPlaceDate = Button.xpath("//button[contains(@class,'btn btn-core')]");
    public Label lblNoRecord = Label.xpath("//div[@class='row']//span[contains(@class,'no-record')]");
    private int totalColumn = 16;
    public StaticTable tblReport = StaticTable.xpath("//div[@class='custom-table']", "div[@class='custom-table-body']",
            "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);

    public void searchByEventDate(String eventDate, String sportName, String competitionName, String eventName, String marketName, String status, String matchedWithin, String wonAmount) {
        ddpSearchBy.selectByVisibleContainsText("Event Date");
        waitSpinIcon();
        if (!eventDate.isEmpty())
            dtpEventDate.selectDate(eventDate, "yyyy-MM-dd");
        if (!sportName.isEmpty()) {
            ddpSport.selectByVisibleText(sportName);
            waitSpinIcon();
        }
        if (!competitionName.isEmpty()) {
            ddpCompetition.selectByVisibleText(competitionName);
            waitSpinIcon();
        }
        if (!eventName.isEmpty())
            ddpEvent.selectByVisibleText(eventName);
        if (!marketName.isEmpty())
            ddpMarket.selectByVisibleText(marketName);
        if (!status.isEmpty())
            ddpStatus.selectByVisibleText(status);
        if (!matchedWithin.isEmpty()) {
            ddpMatchedWithin.selectByVisibleText(matchedWithin);
        }
        if (!wonAmount.isEmpty())
            ddpWonAmount.selectByVisibleText(wonAmount);
        btnSearchEventDate.click();
        waitSpinIcon();
    }

    public void searchByPlacedDate(String eventDate, String sportName, String status, String matchedWithin, String wonAmount) {
        ddpSearchBy.selectByVisibleContainsText("Placed Date");
        waitSpinIcon();
        if (!eventDate.isEmpty())
            dtpEventDate.selectDate(eventDate, "MMM dd yyyy");

        if (!sportName.isEmpty()) {
            System.out.println("Select sport value");
            ddpSportPlaceDate.selectByVisibleText(sportName);
            waitSpinIcon();
        }
        if (!status.isEmpty()) {
            System.out.println("Select Status value");
            ddpStatusPlaceDate.selectByVisibleText(status);
            waitSpinIcon();
        }
        if (!matchedWithin.isEmpty()) {
            System.out.println("Select Matched Within value");
            ddpMatchedWithinPlaceDate.selectByVisibleText(matchedWithin);
        }
        if (!wonAmount.isEmpty()) {
            System.out.println("Select Won Amount value");
            ddpWonAmountPlaceDate.selectByVisibleText(wonAmount);
        }
        btnSearchPlaceDate.click();
        waitSpinIcon();
    }
}
