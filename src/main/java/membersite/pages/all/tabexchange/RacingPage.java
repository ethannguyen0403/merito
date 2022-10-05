package membersite.pages.all.tabexchange;

import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import membersite.controls.funsport.HighLightRaceControl;
import membersite.controls.sat.RacingContainerControl;

public class RacingPage extends SportPage {
    public Label lblNextRace = Label.xpath("//div[@id='middle-content']//h1");
    public Tab tabToday = Tab.xpath("//li[@id='today']//div");
    public Tab tabTomorrow = Tab.xpath("//li[@id='tomorrow']//div");
    public Tab tabNexDate = Tab.xpath("//li[@id='other-date']//div");
    public HighLightRaceControl highLightRaceControl = HighLightRaceControl.xpath("//div[@id='coming-up-race']");
    public RacingContainerControl racingContainer = RacingContainerControl.xpath("//div[@class='container-event-info']");
}
