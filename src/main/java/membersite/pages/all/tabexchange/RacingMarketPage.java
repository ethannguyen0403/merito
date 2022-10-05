package membersite.pages.all.tabexchange;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import membersite.controls.funsport.BetSlipControl;
import membersite.controls.funsport.MyBetControl;
import membersite.controls.funsport.OneClickBettingControl;
import membersite.controls.funsport.RacingMarketOddControl;
import membersite.objects.funsport.Odd;


public class RacingMarketPage extends RacingPage {
    public RacingMarketOddControl marketOddControl = RacingMarketOddControl.xpath("//div[@id='fullMarketOddsRace']", false);
    public Label lblVenusName = Label.xpath("//span[@class='venue-name']");
    public Label lblEventDate = Label.xpath("//span[@class='event-date']");
    public Label lblMarketName = Label.xpath("//span[contains(@class,'market-name')]");
    public Icon icFavorite = Icon.xpath("//i[contains(@class,'multi-market add-multi-market glyphicon')]");
    public Label lblMarketStartTime = Label.xpath("//div[@class='sports star-favourites-container']//div[@id='market-date']");
    public BetSlipControl betSlipControl = BetSlipControl.xpath("//div[@id='betslip-container']");
    public MyBetControl myBetControl = MyBetControl.xpath("//div[@id='openbets']");
    public OneClickBettingControl oneClickBettingControl = OneClickBettingControl.xpath("//div[@id='one-click-betting']");
    public void clickOdd(Odd odd) {
        System.out.println("Debug: Click on Odd of the market: "+ odd.getEventName() +"Selection "+odd.getSelectedTeam() +"Odd Type"+odd.getOdd().getText());
        odd.getOdd().click();
    }
}
