package membersite.pages.all.tabexchange;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import membersite.controls.funsport.OddPageControl;
import membersite.controls.sat.*;
import membersite.objects.AccountBalance;
import membersite.objects.funsport.Odd;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// including some mutual controls on middle content of odd page for Soccer, Tennis, Basket, Cricket
public class SportPage extends HomePage {
    public enum BetType {BACK, LAY}
    public enum Sports {SOCCER, BASKETBALL, TENNIS, CRICKET,OTHER,HORSERACING}
    public Label lblSportHighLight = Label.xpath("//div[contains(@class,'highlight-page')]//h2");
    public Label lblHeadingMarketName = Label.xpath("//div[@id='middle-content']//h1");
    public BetSlipControl betSlipControl = BetSlipControl.xpath("//div[contains(@class,'container-bet-slip')]");
    public MyBetControl myBetControl = MyBetControl.xpath("//div[contains(@class,'container-mybets')]");
    private String addFavoriteIconSportHighlight="//div[@class='sport-highlight-content']//ul/li[%s]//i[@class='multi-market add-multi-market glyphicon glyphicon-plus-sign'][1]";
    private String removeFavoriteIconSportHighlight="//div[@class='sport-highlight-content']//ul/li[%s]//i[@class='multi-market add-multi-market glyphicon glyphicon-minus-sign'][1]";

    public Label lblMarketName = Label.xpath("//div[@id='content-odds']//div[@class='runner-names-heading']");
    public OddPageControl oddPageControl = OddPageControl.xpath("//div[@id='odds-content']//div[@class='sport-highlight-content']", false);
    public OddPageControl oddPageControlSportHighlightCompetition = OddPageControl.xpath("//div[@id='odds-content']//div[@class='sport-highlight-content competition']", false);

    public void clickOdd(Odd odd) {
        System.out.println("Debug: Click on Odd of the event: "+ odd.getEventName() +"Selection "+odd.getSelectedTeam() +"Odd Type"+odd.getOdd().getAttribute(""));
        odd.getOdd().click();
    }

    public Event getEvent(boolean isInplay, boolean isSuspend, int limit, int eventIndex){
        String app = BetUtils.getAppName();
        if(app.contains("satsport"))
            return eventContainerControl_SAT.getEvent(isInplay,isSuspend,limit,eventIndex);
        return eventContainerControl.getEvent(isInplay, isSuspend,limit,eventIndex);
    }
    public void addRemoveFavorite(int eventIndex, boolean isAdd){
        Icon icFav;
        if(isAdd)
            icFav = Icon.xpath(String.format(addFavoriteIconSportHighlight,eventIndex));
        else
            icFav = Icon.xpath(String.format(removeFavoriteIconSportHighlight,eventIndex));
        if(icFav.isDisplayed())
            icFav.click();
    }
    public boolean isIconFavoriteDisplay(int eventIndex, boolean isAdd){
        Icon icFav;
        if(isAdd)
            icFav = Icon.xpath(String.format(addFavoriteIconSportHighlight,eventIndex));
        else
            icFav = Icon.xpath(String.format(removeFavoriteIconSportHighlight,eventIndex));
        return  icFav.isDisplayed();
    }
    public String getMaxLiabiliy(List<String> expectedForeCast) {

        if(Objects.isNull(expectedForeCast)){
            return "0";
        }
        double min =Double.parseDouble(expectedForeCast.get(0));

        // get min value
        for(int i =0, n= expectedForeCast.size(); i<n; i++)
        {
            if(Double.parseDouble(expectedForeCast.get(i)) < min ) {
                min = Double.parseDouble(expectedForeCast.get(i));
            }
        }
        //get max liability
        double maxliabilty = min;
        if(min < 0.00)
        {
            for(int i =0, n= expectedForeCast.size(); i<n; i++)
            {
                if(Double.parseDouble(expectedForeCast.get(i))> maxliabilty && Double.parseDouble(expectedForeCast.get(i)) <0 ) {
                    maxliabilty = Double.parseDouble(expectedForeCast.get(i));
                }
            }
        }

        return  String.format("%.2f",maxliabilty);
    }
    public List<ArrayList<String>> calculateForecastFromAPIWager(){
      /*  List<String> lstString =marketContainerControl.getInfoFromURL();
       List<String> lstSelection =marketContainerControl.getListSelection();*/
        return myBetControl.forecastLstBasedMatchedBetFromAPI(marketContainerControl.getInfoFromURL(),marketContainerControl.getListSelection());
    }

    public String getEventIDHasProductData(String product){
        return eventContainerControl_SAT.getEventIDHasMarketData(product);
    }

    public String getEventIDHasProductData(String product,String status){
        return eventContainerControl_SAT.getEventIDHasMarketData(product);
    }
    public MarketPage clickOnEvent(String eventId){
        return eventContainerControl_SAT.clickOnRowofEventID(eventId);
    }

    public Event setEventLink(Event event){
        String appName = BetUtils.getAppName();
        if(appName.equals("satsport")){
            return eventContainerControl_SAT.setEventLink(event);
        }else
           return eventContainerControl.setEventLink(event);

    }

    public void clickOdd(Market market){
        marketContainerControl.clickOdds(market);
    }

}
