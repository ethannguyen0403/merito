package agentsite.pages.all.components.netexposure;

import agentsite.controls.Table;
import com.paltech.element.common.Label;

public class BetListNetExposure {
    public Label lblNetExposure = Label.xpath("//app-bet-list-net-exposure//div[contains(@class,'link-back-to-overview')]");
    public Label lblEventName = Label.xpath("//app-bet-list-net-exposure//div[contains(@class,'link-back-to-drill-down')]/span");
    public Table tblBetList = Table.xpath("//app-bet-list-net-exposure//table",21);

}
