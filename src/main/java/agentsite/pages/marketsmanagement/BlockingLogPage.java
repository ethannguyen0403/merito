package agentsite.pages.marketsmanagement;

import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;


public class BlockingLogPage extends HomePage {
    public Label lblTitle = Label.xpath("//app-blocking-log-event//div[@class='title']");
    public Button btnRefresh = Button.xpath("//app-blocking-log-event//div[@class='title']//span");
    public TextBox txtEventDate = TextBox.xpath("//div[@class='blocking-log-event']//div[contains(@class,'form-group')]//input");
    public Label lblEventDate = Label.xpath("//div[@class='blocking-log-event']//div[contains(@class,'form-group')]//label");
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
    public BlockingLogPage(String types) {
        super(types);

    }
}
