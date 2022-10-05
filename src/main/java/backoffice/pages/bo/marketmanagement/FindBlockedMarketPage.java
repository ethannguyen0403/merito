package backoffice.pages.bo.marketmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;

public class FindBlockedMarketPage extends HomePage {
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtEventID = TextBox.name("event-id");
    public TextBox txtMarketID = TextBox.name("market");
    public Button btnSearch = Button.name("submit");
    public Button btnConfigColumns = Button.name("config-columns");
    public Label lblEventHeader  =Label.xpath("//h6[contains(@class,'header-event')]");
    public Label lblMarketHeader = Label.xpath("//h6[contains(@class,'header-market')]");
    private int totalColumn = 33;
    public int colUsername = 1;
    public int colLoginId = 2;
    public int colLevel = 3;
    public int colBlockUnblockEventStatus = 15;
    public int colBlockUnblockEventUnblockSchedule = 18;
    public int colBlockUnblockEventUpdateBy = 20;

    public Table tblBlockedMarket = Table.xpath("//table[contains(@class,'my-table')]",totalColumn);

    public void search(String username, String eventID, String marketID){
        txtUsername.sendKeys(username);
        txtEventID.sendKeys(eventID);
        txtMarketID.sendKeys(marketID);
        btnSearch.click();
        waitSpinIcon();
    }

   /* public boolean verifyEventHeaderInfo(List<String> expectedEventInfo){
        String actualInfo = lblEventHeader.getText();
        String expectedInfo = String.format("Sport: %s |" +
                " Competition: %s |" +
                " Country: %s |" +
                " Venue: %s |" +
                " Event: %s |" +
                " Status: %s | Open Time: %s",expectedEventInfo.get(0), expectedEventInfo.get(1),expectedEventInfo.get(2),expectedEventInfo.get(3),expectedEventInfo.get(4),expectedEventInfo.get(5),expectedEventInfo.get(6));
        return actualInfo.equals(expectedInfo);
    }
*/

}
