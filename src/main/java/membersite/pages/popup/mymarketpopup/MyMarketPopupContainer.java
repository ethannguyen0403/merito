package membersite.pages.popup.mymarketpopup;

import com.paltech.element.common.Label;
import controls.Table;

public class MyMarketPopupContainer {
    public Label lblTitle = Label.xpath("//div[contains(@class,'my-market-header')]/h4");
    public Label lblNote = Label.xpath("//td[@id='noteGMT4']");
    private int totalColMyMarkets = 4;
    public Table tbMyMarkets = Table.xpath("//table[@class='table table-hover table-bordered table-sm']", totalColMyMarkets);
    public void verifyMyMarketPopupUI(){}
}
