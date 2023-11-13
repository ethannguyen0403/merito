package agentsite.pages.agentmanagement.betsettinglisting;

import agentsite.pages.agentmanagement.BetSettingListingPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OldUIBetSettingListing extends BetSettingListing {
    private int soccerCol = 9;
    private int cricketCol = 10;
    private int fancyCol = 11;
    private int bookmakerCol = 12;
    private int tennisCol = 13;
    private int basketballCol = 14;
    private int otherCol = 15;



    public List<ArrayList<String>> defineActualDataForOneAccount(HashMap<String, Boolean> sportList, double minBet, double maxBet, double maxLiabilityPerMarket, double maxWinPerMarket) {
        // this function define data for an account
        List<ArrayList<String>> lstExpectedData = BetSettingListingPage.getBetSettingofAccount(sportList);
        if (minBet != -1) {
            if(sportList.get("Soccer")) {lstExpectedData.get(0).set(soccerCol - 1, String.format("%.2f", minBet));}
            if(sportList.get("Cricket")) {lstExpectedData.get(0).set(cricketCol - 1, String.format("%.2f", minBet));}
            if(sportList.get("Fancy")) {lstExpectedData.get(0).set(fancyCol - 1, String.format("%.2f", minBet));}
            if(sportList.get("Bookmaker")) {lstExpectedData.get(0).set(bookmakerCol - 1, String.format("%.2f", minBet));}
            if(sportList.get("Tennis")) {lstExpectedData.get(0).set(tennisCol - 1, String.format("%.2f", minBet));}
            if(sportList.get("Basketball")) {lstExpectedData.get(0).set(basketballCol - 1, String.format("%.2f", minBet));}
            if(sportList.get("Other")) {lstExpectedData.get(0).set(otherCol - 1, String.format("%.2f", minBet));}
        }
        if (maxBet != -1) {
            if(sportList.get("Soccer")) { lstExpectedData.get(1).set(1, String.format("%.2f", maxBet)); }
            if(sportList.get("Cricket")) { lstExpectedData.get(1).set(2, String.format("%.2f", maxBet)); }
            if(sportList.get("Fancy")) { lstExpectedData.get(1).set(3, String.format("%.2f", maxBet)); }
            if(sportList.get("Bookmaker")) { lstExpectedData.get(1).set(4, String.format("%.2f", maxBet)); }
            if(sportList.get("Tennis")) { lstExpectedData.get(1).set(5, String.format("%.2f", maxBet)); }
            if(sportList.get("Basketball")) { lstExpectedData.get(1).set(6, String.format("%.2f", maxBet)); }
            if(sportList.get("Other")) { lstExpectedData.get(1).set(7, String.format("%.2f", maxBet)); }
        }
        if(maxLiabilityPerMarket != -1) {
            if(sportList.get("Soccer")) { lstExpectedData.get(2).set(1, String.format("%.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Cricket")) { lstExpectedData.get(2).set(2, String.format("%.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Fancy")) { lstExpectedData.get(2).set(3, String.format("%.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Bookmaker")) { lstExpectedData.get(2).set(4, String.format("%.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Tennis")) { lstExpectedData.get(2).set(5, String.format("%.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Basketball")) { lstExpectedData.get(2).set(6, String.format("%.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Other")) { lstExpectedData.get(2).set(7, String.format("%.2f", maxLiabilityPerMarket)); }
        }
        if(maxWinPerMarket != -1) {
            if(sportList.get("Soccer")) { lstExpectedData.get(3).set(1, String.format("%.2f", maxWinPerMarket)); }
            if(sportList.get("Cricket")) { lstExpectedData.get(3).set(2, String.format("%.2f", maxWinPerMarket)); }
            if(sportList.get("Fancy")) { lstExpectedData.get(3).set(3, String.format("%.2f", maxWinPerMarket)); }
            if(sportList.get("Bookmaker")) { lstExpectedData.get(3).set(4, String.format("%.2f", maxWinPerMarket)); }
            if(sportList.get("Tennis")) { lstExpectedData.get(3).set(5, String.format("%.2f", maxWinPerMarket)); }
            if(sportList.get("Basketball")) { lstExpectedData.get(3).set(6, String.format("%.2f", maxWinPerMarket)); }
            if(sportList.get("Other")) { lstExpectedData.get(3).set(7, String.format("%.2f", maxWinPerMarket)); }
        }
        return lstExpectedData;
    }

}
