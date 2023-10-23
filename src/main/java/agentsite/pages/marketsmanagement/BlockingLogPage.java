package agentsite.pages.marketsmanagement;

import agentsite.controls.DateTimePicker;
import agentsite.pages.HomePage;
import backoffice.controls.bo.StaticTable;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class BlockingLogPage extends HomePage {
    public Label lblTitle = Label.xpath("//app-blocking-log-event//div[@class='title']");
    public Button btnRefresh = Button.xpath("//app-blocking-log-event//div[@class='title']//span");
    public TextBox txtEventDate = TextBox.xpath("//app-blocking-log-event//div[contains(@class,'form-group')]//input");
    private DateTimePicker dtpEventDate = DateTimePicker.xpath(txtEventDate, "//bs-calendar-layout");
    public Label lblEventDate = Label.xpath("//app-blocking-log-event//div[contains(@class,'form-group')]//label");
    public TextBox txtSearchSport = TextBox.xpath("//div[@class='sport-container']//input");
    public Label lblSport = Label.xpath("//div[@class='sport-container']//div[contains(@class,'header')]/span");
    public Label lblCompeition = Label.xpath("//div[@class='competition-container']//div[contains(@class,'header')]//span");
    public Label lblEventID = Label.xpath("//div[@class='event-container']//div[contains(@class,'header')]/div[1]//span");
    public Label lblEventName = Label.xpath("//div[@class='event-container']//div[contains(@class,'header')]/div[2]//span");
    public Label lblEventStatus = Label.xpath("//div[@class='event-container']//div[contains(@class,'header')]/div[3]//span");
    public TextBox txtSearchCompetition = TextBox.xpath("//div[@class='competition-container']//input");
    public TextBox txtSearchEventID = TextBox.xpath("//div[@class='event-container']//div[contains(@class,'header row')]/div[1]//input");
    public TextBox txtSearchEventName = TextBox.xpath("//div[@class='event-container']//div[contains(@class,'header row')]/div[2]//input");
    public TextBox txtSearchStatus = TextBox.xpath("//div[@class='event-container']//div[contains(@class,'header row')]/div[3]//input");
    private StaticTable tblEventLog = StaticTable.xpath("//app-blocking-log-event","div[@class='event-container row']","div[@class='td row']","div[@class='col-3']", 4);

    public BlockingLogPage(String types) {
        super(types);

    }

    public void search(String date, String sport, String competition, String eventId, String eventName, String status) throws InterruptedException {
        if(!date.isEmpty()) {
            dtpEventDate.selectDate(date, "dd/MM/yyyy");
        }
        if(!sport.isEmpty()) {
            txtSearchSport.sendKeys(sport);
            Thread.sleep(2000);
            Label lblSport = Label.xpath(String.format("//div[@class='sport-container']//span[text()=' %s']", sport));
            lblSport.click();
            Thread.sleep(1000);
        }
        if (!competition.isEmpty()) {
            txtSearchCompetition.sendKeys(competition);
            Label lblCompetition = Label.xpath(String.format("//div[@class='competition-container']//span[text()='%s']", competition));
            lblCompetition.click();
            Thread.sleep(1000);
        }
        if(!eventId.isEmpty()) {
            txtSearchEventID.sendKeys(eventId);
            Label lblEventId = Label.xpath(String.format("//div[@class='event-container']//div[text()='%s']", eventId));
            lblEventId.click();
            Thread.sleep(1000);
        }
        if(!eventName.isEmpty()) {
            txtSearchEventName.sendKeys(eventName);
        }
        if(!status.isEmpty()) {
            txtSearchStatus.sendKeys(status);
        }
    }

    public void verifyEventLogDisplayCorrect(String dateTime, String blockedUser, String actions, String updatedBy) {
        List<ArrayList<String>> lstEventLog = tblEventLog.getRowsWithoutHeader(1, false);
        Assert.assertTrue(lstEventLog.get(0).get(0).contains(dateTime),"FAILED! Update Date event log does not show correct");
        Assert.assertTrue(lstEventLog.get(0).get(0).contains(blockedUser),"FAILED! Blocked user event log does not show correct");
        Assert.assertTrue(lstEventLog.get(0).get(0).contains(actions),"FAILED! Actions event log does not show correct");
        Assert.assertTrue(lstEventLog.get(0).get(0).contains(updatedBy),"FAILED! Updated by event log does not show correct");
    }
}
