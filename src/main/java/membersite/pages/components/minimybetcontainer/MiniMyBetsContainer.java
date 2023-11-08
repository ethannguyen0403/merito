package membersite.pages.components.minimybetcontainer;

import membersite.objects.sat.Order;

import java.util.ArrayList;
import java.util.List;

public class MiniMyBetsContainer {

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

    public List<ArrayList> getFancyBetSlipInfo(boolean isBack) { return null;}
    public List<ArrayList> getBookmakerBetSlipInfo(boolean isBack) { return null;}
}
