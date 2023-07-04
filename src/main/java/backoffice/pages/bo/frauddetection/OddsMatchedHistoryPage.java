package backoffice.pages.bo.frauddetection;

import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Image;
import com.paltech.element.common.TextBox;

public class OddsMatchedHistoryPage extends HomePage {
    public TextBox txtEventID = TextBox.id("eventId");
    public DropDownBox ddbMarketName = DropDownBox.id("selectMarket");
    public DropDownBox ddbBettingOn = DropDownBox.id("selectRunner");
    public DropDownBox ddbTimeOrder = DropDownBox.id("timeSortDirection");
    public Button btnSearch = Button.xpath("//button[contains(@class,'btn btn-core')]");
    public Image imgPriceVolumeChart = Image.xpath("//div[@id='chart_div']/img");
    public Table tblOddMatchedHistory = Table.xpath("//app-odds-matched-history//table", 3);

    public void search(String eventId, String eventName, String bettingOn, String timeOrder) {
        txtEventID.sendKeys(eventId);
        ddbMarketName.selectByVisibleText(eventName);
        if (!bettingOn.isEmpty())
            ddbBettingOn.selectByVisibleText(bettingOn);
        if (!timeOrder.isEmpty())
            ddbTimeOrder.selectByVisibleText(timeOrder);
        btnSearch.click();
    }


}
