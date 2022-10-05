package agentsite.pages.all.marketsmanagement.blockunblockevents;

import com.paltech.element.common.TextBox;
import agentsite.controls.Table;

public class MarketDetailsPopup {
    public TextBox txtMarketName = TextBox.xpath("//input[contains(@class,'input-market-name')]");
    private int totalCol = 2;
    public int colMarketName = 1;
    public int colBetable = 2;
    public Table tblMarket = Table.xpath("//table[@class='tables']", totalCol);
}
