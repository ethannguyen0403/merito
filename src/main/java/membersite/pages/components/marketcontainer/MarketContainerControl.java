package membersite.pages.components.marketcontainer;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import membersite.objects.funsport.Odd;
import membersite.objects.sat.Event;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Market;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class MarketContainerControl {
    public Label lblSuspend = Label.xpath("//div[contains(@class,'highlight-page market')]//span[contains(@class,'suspended-label')]");
    public String xpathLblBackOddsList = "//div[contains(@class,'table-odds')]//div[contains(@class,'market-container')]//div[contains(@class,'meto-bg-back')]";
    public String xpathLblLayOddsList = "//div[contains(@class,'table-odds')]//div[contains(@class,'market-container')]//div[contains(@class,'meto-bg-lay')]";

    public String getEventName() {
        return "";
    }

    public boolean isViewColumnEnable() {
        return false;
    }

    public boolean isLineViewEnable() {
        return false;
    }

    public Odd getMatchOdds(Team team, boolean isBack) {
        return null;
    }

    public Odd getSelectionOdds(int selectionIndex, boolean isBack) {
        return null;
    }

    public List<ArrayList<String>> getUIForeCast() {
        return null;
    }

    public List<String> getMarketInfo() {
        return null;
    }

    public List<String> getListSelection() {
        return null;
    }

    private Odd getOdd(Link lnkSelectedOdd, String oddRate, String marketName, boolean isInPlay, String selectedTeam, int eventIndex) {
        return null;
    }

    private Odd getOdd(Link lnkSelectedOdd, String oddRate, String homeTeam, String awayTeam, boolean isInPlay, String selectedTeam, int eventIndex) {
        return null;
    }

    public String getTitle() {
        return "";
    }

    public Market getMarket(Event event, int selectionIndex, boolean isBack) {
        return null;
    }

    public int getSelectionHaveMinOdds(String marketName,boolean isBack) {
        return 0;
    }

    public void activeProduct(String products) {
    }

    public FancyMarket getFancyMarketInfo(FancyMarket fcMarket) {
        return null;
    }

    public FancyMarket getArtemisFancyMarketInfo(FancyMarket fcMarket, int runnerNo) {
        return null;
    }

    public enum Team {HOME, DRAW, AWAY}

    public enum Status {NA, IN_PLAY, COMING}
    public void clickRuleButton(){}

    public boolean verifyOddsIsClickable(boolean isClickable) {return false;}

    public void openFancyLadderForecast(FancyMarket fcMarket) {}

    public boolean isLadderForecastDisplay(FancyMarket fcMarket) {return false;}
    public boolean isMarketInfoSectionDisplayed() {return false;}

    public List<Label> getOddsListLabel(String marketName, int selectionIndex, boolean isBack){
        return null;
    }

    public List<Label> getAllOddsListLabel(boolean isBack){
        List<Label> list = new ArrayList<>();
        String targetXpath = isBack? xpathLblBackOddsList: xpathLblLayOddsList;
        int countOddsLabel = Label.xpath(targetXpath).getWebElements().size();
        for (int i =1; i<= countOddsLabel;i++) {
            list.add(Label.xpath(String.format("(%s)[%s]",targetXpath, i)));
        }
        return list;
    }
}
