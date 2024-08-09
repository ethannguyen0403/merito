package membersite.pages.components.minimybetcontainer;

import com.paltech.element.common.Link;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;

import java.util.ArrayList;
import java.util.List;

public class MiniMyBetsContainer {
    public Link lnkCancelAll = Link.xpath("//div[contains(@class,'cancel-all-bet')]//a[text()='cancel all']");
    public void cancelAllBetUnmatched() {
    }

    public String getPlaceBetErrorMessage() {
        return "";
    }
    public String getBetslipErrorMessage() {
        return "";
    }

    public List<Order> getOrder(boolean isMatched, boolean isBack, int limit) {
        return null;
    }

    public Order getOrder(boolean isMatched, boolean isBack) {
        return null;
    }

    public void verifyInfoBetSlipAndOddsPage(Market market, Order order) {}

    public void removeBet(boolean isBack) {
    }

    public boolean isUnmatchedBetsEmpty() {
        return false;
    }

    public List<ArrayList<String>> forecastLstBasedMatchedBetFromAPI(List<String> marketInfo, List<String> lstSelection) {
        return null;
    }

    public List<ArrayList> getBookmakerMatchBets() {
        return null;
    }

    public List<ArrayList> getMatchedFancyInMiniMyBet() {
        return null;
    }

    public List<ArrayList> getFancyBetSlipInfo() { return null;}
    public List<ArrayList> getBookmakerBetSlipInfo() { return null;}

    public boolean isMultiTabBetSlipEnabled() {return false;}

    public void clickMultiTabBetSlip() {}
    public boolean isMultiTabBetSlipSelected() {
        return false;
    }
}
