package backoffice.pages.bo.tools;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;

public class MarketBetListPage extends HomePage {
    public TextBox txtMarketID = TextBox.name("market-id");
    public Button btnView = Button.xpath("//button[contains(@class,'btn btn-sm btn-core')]");
    public Table tblMarketInfo = Table.xpath("//table[contains(@class,'table-market-info')]",2);
    public StaticTable tblMarketBetList = StaticTable.xpath("//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",14);

    public void viewMarketBetList(String marketId){
        txtMarketID.sendKeys(marketId);
        btnView.click();
    }

}
