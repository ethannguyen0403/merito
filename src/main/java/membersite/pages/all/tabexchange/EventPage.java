package membersite.pages.all.tabexchange;

import com.paltech.element.common.Icon;
import membersite.controls.funsport.BetSlipControl;
import membersite.controls.funsport.EventOddContentControl;
import membersite.controls.funsport.MyBetControl;
import membersite.objects.funsport.Odd;
import membersite.pages.all.tabexchange.components.LeftMenu;
import membersite.pages.all.tabexchange.components.MainMenu;

public class EventPage extends MainMenu {
    private String addFavoriteIconSportHighlight="//div[@class='sport-highlight-content']//ul/li[%s]//i[@class='multi-market add-multi-market glyphicon glyphicon-plus-sign'][1]";
    private String removeFavoriteIconSportHighlight="//div[@class='sport-highlight-content']//ul/li[%s]//i[@class='multi-market add-multi-market glyphicon glyphicon-minus-sign'][1]";
    public EventOddContentControl oddPageControl = EventOddContentControl.xpath(" //div[@id='odds-content']", true);
    public BetSlipControl betSlipControl = BetSlipControl.xpath("//div[@id='betslip-container']");
    public MyBetControl myBetControl = MyBetControl.xpath("//div[@id='openbets']");
    public void clickOdd(Odd odd) {
        System.out.println("Debug: Click on Odd of the market: "+ odd.getEventName() +"Selection "+odd.getSelectedTeam() +"Odd Type"+odd.getOdd().getText());
        odd.getOdd().click();
    }
}
