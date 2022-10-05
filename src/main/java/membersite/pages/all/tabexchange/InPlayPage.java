package membersite.pages.all.tabexchange;

import membersite.controls.sat.EventContainerControl;
import membersite.controls.sat.MarketContainerControl;
import membersite.controls.sat.RacingContainerControl;
import membersite.controls.sat.RacingMarketControl;

public class InPlayPage extends SportPage {
    public EventContainerControl eventContainerControl = EventContainerControl.xpath("//div[@class='sport-highlight-content']");
    public MarketContainerControl marketContainerControl = MarketContainerControl.xpath("//div[contains(@class,'highlight-page market')]");
    public RacingMarketControl racingMarketControl = RacingMarketControl.xpath("//app-racing-market");
    public RacingContainerControl racingContainerControl = RacingContainerControl.xpath("//div[contains(@class,'highlight-page')]/app-sport-highlight-racing");
}
