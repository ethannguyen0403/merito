package agentsite.pages.agentmanagement.betsettinglisting;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.BetSettingListingPage;
import com.paltech.element.common.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static agentsite.pages.agentmanagement.BetSettingListingPage.getSpotColumn;

public class OldUIBetSettingListing extends BetSettingListing {
    private int totalColum = 17;
    private int soccerCol = 9;
    private int cricketCol = 10;
    private int fancyCol = 11;
    private int virtualCricketCol = 12;
    private int bookmakerCol = 13;
    private int tennisCol = 14;
    private int basketballCol = 15;
    private int otherCol = 16;
    private int updateStatusCol = 17;
    private Table tblDownline = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";


    public List<ArrayList<String>> defineActualDataForOneAccount(HashMap<String, Boolean> sportList, double minBet, double maxBet, double maxLiabilityPerMarket, double maxWinPerMarket) {
        // this function define data for an account
        List<ArrayList<String>> lstExpectedData = BetSettingListingPage.getBetSettingofAccount(sportList);
        if (minBet != -1) {
            if(sportList.get("Soccer")) {lstExpectedData.get(0).set(soccerCol - 1, String.format("%,.2f", minBet));}
            if(sportList.get("Cricket")) {lstExpectedData.get(0).set(cricketCol - 1, String.format("%,.2f", minBet));}
            if(sportList.get("Fancy")) {lstExpectedData.get(0).set(fancyCol - 1, String.format("%,.2f", minBet));}
            if(sportList.get("Virtual Cricket")) {lstExpectedData.get(0).set(virtualCricketCol - 1, String.format("%,.2f", minBet));}
            if(sportList.get("Bookmaker")) {lstExpectedData.get(0).set(bookmakerCol - 1, String.format("%,.2f", minBet));}
            if(sportList.get("Tennis")) {lstExpectedData.get(0).set(tennisCol - 1, String.format("%,.2f", minBet));}
            if(sportList.get("Basketball")) {lstExpectedData.get(0).set(basketballCol - 1, String.format("%,.2f", minBet));}
            if(sportList.get("Other")) {lstExpectedData.get(0).set(otherCol - 1, String.format("%,.2f", minBet));}
        }
        if (maxBet != -1) {
            if(sportList.get("Soccer")) { lstExpectedData.get(1).set(1, String.format("%,.2f", maxBet)); }
            if(sportList.get("Cricket")) { lstExpectedData.get(1).set(2, String.format("%,.2f", maxBet)); }
            if(sportList.get("Fancy")) { lstExpectedData.get(1).set(3, String.format("%,.2f", maxBet)); }
            if(sportList.get("Virtual Cricket")) { lstExpectedData.get(1).set(4, String.format("%,.2f", maxBet)); }
            if(sportList.get("Bookmaker")) { lstExpectedData.get(1).set(5, String.format("%,.2f", maxBet)); }
            if(sportList.get("Tennis")) { lstExpectedData.get(1).set(6, String.format("%,.2f", maxBet)); }
            if(sportList.get("Basketball")) { lstExpectedData.get(1).set(7, String.format("%,.2f", maxBet)); }
            if(sportList.get("Other")) { lstExpectedData.get(1).set(8, String.format("%,.2f", maxBet)); }
        }
        if(maxLiabilityPerMarket != -1) {
            if(sportList.get("Soccer")) { lstExpectedData.get(2).set(1, String.format("%,.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Cricket")) { lstExpectedData.get(2).set(2, String.format("%,.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Fancy")) { lstExpectedData.get(2).set(3, String.format("%,.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Virtual Cricket")) { lstExpectedData.get(2).set(4, String.format("%,.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Bookmaker")) { lstExpectedData.get(2).set(5, String.format("%,.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Tennis")) { lstExpectedData.get(2).set(6, String.format("%,.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Basketball")) { lstExpectedData.get(2).set(7, String.format("%,.2f", maxLiabilityPerMarket)); }
            if(sportList.get("Other")) { lstExpectedData.get(2).set(8, String.format("%,.2f", maxLiabilityPerMarket)); }
        }
        if(maxWinPerMarket != -1) {
            if(sportList.get("Soccer")) { lstExpectedData.get(3).set(1, String.format("%,.2f", maxWinPerMarket)); }
            if(sportList.get("Cricket")) { lstExpectedData.get(3).set(2, String.format("%,.2f", maxWinPerMarket)); }
            if(sportList.get("Fancy")) { lstExpectedData.get(3).set(3, String.format("%,.2f", maxWinPerMarket)); }
            if(sportList.get("Virtual Cricket")) { lstExpectedData.get(3).set(4, String.format("%,.2f", maxWinPerMarket)); }
            if(sportList.get("Bookmaker")) { lstExpectedData.get(3).set(5, String.format("%,.2f", maxWinPerMarket)); }
            if(sportList.get("Tennis")) { lstExpectedData.get(3).set(6, String.format("%,.2f", maxWinPerMarket)); }
            if(sportList.get("Basketball")) { lstExpectedData.get(3).set(7, String.format("%,.2f", maxWinPerMarket)); }
            if(sportList.get("Other")) { lstExpectedData.get(3).set(8, String.format("%,.2f", maxWinPerMarket)); }
        }
        return lstExpectedData;
    }

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstData, boolean isSuccess, HashMap<String, Boolean> sportList) {
        int sportColumn = getSpotColumn(sportList);
        int totalColum = 9;
        totalColum = sportColumn + totalColum;
        tblDownline = Table.xpath("//table[contains(@class,'ptable report table-responsive')]", totalColum);
        updateStatusCol = totalColum;
        String cell_xpath;
        for (int i = 0; i < lstData.size(); i++) {
            if (i % 4 == 0) {
                cell_xpath = String.format("%s//tr[%s]//td[%s]", "//table[contains(@class,'ptable report table-responsive')]", i + 1, updateStatusCol);
                Label lblIcon;
                if (isSuccess) {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, successIcon));
                } else {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, errorIcon));
                }
                if (!lblIcon.isDisplayed())
                    return false;
            }
        }
        return true;
    }

}
