package backoffice.pages.bo.settlement;

import backoffice.controls.DateTimePicker;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.TextBox;

public class FancyResultPage extends HomePage {
    public final int colMarketName = 2;
    private final int totalCol = 13;
    public DropDownBox ddbEvent = DropDownBox.xpath("//select[contains(@class,'custom-select')]");
    public Table tblHeader = Table.xpath("//table[contains(@class, 'table-header')]", totalCol);
    public Table tblBody = Table.xpath("//div[@class='ps-content']//table", totalCol);
    TextBox txtEventDate = TextBox.name("dp");
    /*
    public DropDownBox ddpBetType = DropDownBox.name("bet-types");
     public TextBox txtWagerID = TextBox.name("wager-id");
     public DropDownBox ddpProduct = DropDownBox.name("products");
     public Button btnSubmit =Button.name("submit");
     public Button btnCheckExteraal = Button.name("check-external");
     public TextBox txtMarketId = TextBox.name("market-id");
     public Button btnQueue =Button.name("queue");*/
    public DateTimePicker dpEventDate = DateTimePicker.xpath(txtEventDate, "//div[@class='bs-calendar-container']");
    public Button btnSearch = Button.xpath("//input[@value='Search']");
    TextBox txtMarketName = TextBox.xpath("//input[@aria-label='Market name']");

    public void filerOnTableHeader(String marketName) {
        if (!marketName.isEmpty()) {
            txtMarketName.sendKeys(marketName);
        }
        // waiting for ajax loading
        txtMarketName.isInvisible(1);
    }

    public void filter(String event) {
        if (!event.isEmpty()) {
            ddbEvent.selectByVisibleText(event);
        }
        btnSearch.click();
        // waiting for loading
        btnSearch.isInvisible(2);
    }
}
