package membersite.pages.components.sportcontainer;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import membersite.objects.funsport.Odd;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.exchangegames.controls.BetSlipControl;
import membersite.pages.exchangegames.controls.MyBetControl;

import java.util.List;
import java.util.Objects;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class NewUISportContainerControl extends SportContainerControl {
    public Label lblSportHighLight = Label.xpath("//div[contains(@class,'highlight-page')]//h2");
    public Label lblHeadingMarketName = Label.xpath("//div[@id='middle-content']//h1");
    public BetSlipControl betSlipControl = BetSlipControl.xpath("//div[contains(@class,'container-bet-slip')]");
    public MyBetControl myBetControl = MyBetControl.xpath("//div[contains(@class,'container-mybets')]");
    public Label lblMarketName = Label.xpath("//div[@id='content-odds']//div[@class='runner-names-heading']");
    private String addFavoriteIconSportHighlight = "//div[@class='sport-highlight-content']//ul/li[%s]//i[@class='multi-market add-multi-market glyphicon glyphicon-plus-sign'][1]";
    private String removeFavoriteIconSportHighlight = "//div[@class='sport-highlight-content']//ul/li[%s]//i[@class='multi-market add-multi-market glyphicon glyphicon-minus-sign'][1]";

    public void clickOdd(Odd odd) {
        System.out.println("Debug: Click on Odd of the event: " + odd.getEventName() + "Selection " + odd.getSelectedTeam() + "Odd Type" + odd.getOdd().getAttribute(""));
        odd.getOdd().click();
    }

    public void addRemoveFavorite(int eventIndex, boolean isAdd) {
        Icon icFav;
        if (isAdd)
            icFav = Icon.xpath(String.format(addFavoriteIconSportHighlight, eventIndex));
        else
            icFav = Icon.xpath(String.format(removeFavoriteIconSportHighlight, eventIndex));
        if (icFav.isDisplayed())
            icFav.click();
    }

    public boolean isIconFavoriteDisplay(int eventIndex, boolean isAdd) {
        Icon icFav;
        if (isAdd)
            icFav = Icon.xpath(String.format(addFavoriteIconSportHighlight, eventIndex));
        else
            icFav = Icon.xpath(String.format(removeFavoriteIconSportHighlight, eventIndex));
        return icFav.isDisplayed();
    }

    public String getMaxLiabiliy(List<String> expectedForeCast) {

        if (Objects.isNull(expectedForeCast)) {
            return "0";
        }
        double min = Double.parseDouble(expectedForeCast.get(0));

        // get min value
        for (int i = 0, n = expectedForeCast.size(); i < n; i++) {
            if (Double.parseDouble(expectedForeCast.get(i)) < min) {
                min = Double.parseDouble(expectedForeCast.get(i));
            }
        }
        //get max liability
        double maxliabilty = min;
        if (min < 0.00) {
            for (int i = 0, n = expectedForeCast.size(); i < n; i++) {
                if (Double.parseDouble(expectedForeCast.get(i)) > maxliabilty && Double.parseDouble(expectedForeCast.get(i)) < 0) {
                    maxliabilty = Double.parseDouble(expectedForeCast.get(i));
                }
            }
        }
        return String.format("%.2f", maxliabilty);
    }

    public String getEventIDHasProductData(String product) {
        return "";
    }

    public String getEventIDHasProductData(String product, String status) {
        return "";
    }

    public Event setEventLink(Event event) {
        return null;
    }

    public void clickOdd(Market market) {
    }

    public enum BetType {BACK, LAY}

    public enum Sports {SOCCER, BASKETBALL, TENNIS, CRICKET, OTHER, HORSERACING}
}
