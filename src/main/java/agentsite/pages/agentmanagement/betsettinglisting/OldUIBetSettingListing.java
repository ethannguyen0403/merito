package agentsite.pages.agentmanagement.betsettinglisting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OldUIBetSettingListing extends BetSettingListing {

    public List<ArrayList<String>> defineActualDataForOneAccount(HashMap<String, Boolean> sportList, double minBet, double maxBet, double maxLiabilityPerMarket, double maxWinPerMarket) {
        // this function define data for an account
        List<ArrayList<String>> lstExpectedData = getBetSettingofAccount(sportList);
        lstExpectedData.get(0).set(soccerCol - 8, String.format("%.2f", minBet));
        lstExpectedData.get(0).set(cricketCol - 8, String.format("%.2f", minBet));
        lstExpectedData.get(0).set(tennisCol - 8, String.format("%.2f", minBet));
        lstExpectedData.get(0).set(basketballCol - 8, String.format("%.2f", minBet));
        lstExpectedData.get(0).set(fancyCol - 8, String.format("%.2f", minBet));
        lstExpectedData.get(0).set(otherCol - 8, String.format("%.2f", minBet));
        lstExpectedData.get(1).set(1, String.format("%.2f", maxBet));
        lstExpectedData.get(1).set(2, String.format("%.2f", maxBet));
        lstExpectedData.get(1).set(3, String.format("%.2f", maxBet));
        lstExpectedData.get(1).set(4, String.format("%.2f", maxBet));
        lstExpectedData.get(1).set(5, String.format("%.2f", maxBet));
        lstExpectedData.get(1).set(6, String.format("%.2f", maxBet));
        lstExpectedData.get(2).set(1, String.format("%.2f", maxLiabilityPerMarket));
        lstExpectedData.get(2).set(2, String.format("%.2f", maxLiabilityPerMarket));
        lstExpectedData.get(2).set(3, String.format("%.2f", maxLiabilityPerMarket));
        lstExpectedData.get(2).set(4, String.format("%.2f", maxLiabilityPerMarket));
        lstExpectedData.get(2).set(5, String.format("%.2f", maxLiabilityPerMarket));
        lstExpectedData.get(2).set(6, String.format("%.2f", maxLiabilityPerMarket));
        lstExpectedData.get(3).set(1, String.format("%.2f", maxWinPerMarket));
        lstExpectedData.get(3).set(2, String.format("%.2f", maxWinPerMarket));
        lstExpectedData.get(3).set(3, String.format("%.2f", maxWinPerMarket));
        lstExpectedData.get(3).set(4, String.format("%.2f", maxWinPerMarket));
        lstExpectedData.get(3).set(5, String.format("%.2f", maxWinPerMarket));
        lstExpectedData.get(3).set(6, String.format("%.2f", maxWinPerMarket));
        return lstExpectedData;
    }

}
