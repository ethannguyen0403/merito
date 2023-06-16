package membersite.pages;


import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.highlightracecontainer.HighLightRaceContainer;
import membersite.pages.components.racingcontainer.RacingContainer;

public class RacingPage extends HomePage{
    public Label lblNextRace = Label.xpath("//div[@id='middle-content']//h1");
    public Tab tabToday = Tab.xpath("//li[@id='today']//div");
    public Tab tabTomorrow = Tab.xpath("//li[@id='tomorrow']//div");
    public Tab tabNexDate = Tab.xpath("//li[@id='other-date']//div");
    public HighLightRaceContainer highLightRaceContainer ;
    public RacingContainer racingContainer ;

    public RacingPage(String types) {
        super(types);
        highLightRaceContainer= ComponentsFactory.highLightRaceContainerObject(types);
        racingContainer = ComponentsFactory.racingContainerObject(types);
    }

    public MarketPage clickRacing(String country, String trackName, String race)
    {
       return racingContainer.clickRacing(country,trackName,race,this._type);
    }

}
