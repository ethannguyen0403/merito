package backoffice.pages.bo.marketmanagement;

import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.DropDownBox;

public class EventMarketLogPage extends HomePage {

    public DropDownBox ddpType = DropDownBox.name("types");
    public DropDownBox ddpMarket = DropDownBox.name("markets");

}
