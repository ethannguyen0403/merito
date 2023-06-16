package agentsite.ultils.maketmanagement;

import agentsite.objects.agent.account.MarketInfo;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;
import static baseTest.BaseCaseTest.environment;

public class SuspendUnsuspendMarketsUtils {

    private static JSONArray getEventListJson(String sportID, String loginUserID, String date){
        String api = String.format("%s/agent-blocking-management/event/sat/listEvent.json?isBlck=1&byLevelControlLocking=true&sportId=%s&userId=%s&time=%s&currentPage=1&numOfRows=50&filter=&_=%s",
                domainURL,sportID, loginUserID, date,DateUtils.getMilliSeconds());
       return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }
    private static JSONObject getMarketJson(String eventID, String loginUserID){
        String api = String.format("%s/agent-blocking-management/suspend-market/markets.json",domainURL);
        String jsn = String.format("{\"userId\":%s,\"eventId\":%s,\"marketNameSearch\":\"\",\"pageSize\":10,\"pageNo\":1}",loginUserID,eventID);
        return  WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, jsn,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    /**
     * Get list event api
     * @param sportID
     * @param loginUserID
     * @param date [input value: OLD, TODAY, TMR, FUTURE]
     * @return List<MarketInfo>
     */
    public static List<MarketInfo> getListEvent(String sportID, String loginUserID, String date)
    {
        JSONArray jArr = getEventListJson(sportID,loginUserID,date);
        List<MarketInfo> lstMarkets = new ArrayList<>();
        MarketInfo marketInfo;
        if(Objects.nonNull(jArr)){
            for (int i =0; i< jArr.length(); i++){
                JSONObject jObIndex = jArr.getJSONObject(i);
                marketInfo = new MarketInfo.Builder()
                        .competitionID(Long.toString(jObIndex.getLong("competitionId")))
                        .competitionName(jObIndex.getString("competitionName"))
                        .eventID(Long.toString(jObIndex.getLong("id")))
                        .eventName(jObIndex.getString("name"))
                        .build();
                lstMarkets.add(marketInfo);
            }
            return lstMarkets;
        }
       return null;
    }
    public static List<MarketInfo> getListMarket(String eventID,String loginUserID) {
        JSONObject jOb = getMarketJson(eventID,loginUserID);
        int totalRecord = jOb.getInt("totalRecord");
        List<MarketInfo> lstMarkets = new ArrayList<>();
        MarketInfo marketInfo;
        if(totalRecord!=0){
            JSONArray arrayObj = jOb.getJSONArray("data");
            for (int i =0; i< arrayObj.length(); i++){
                JSONObject jObIndex = arrayObj.getJSONObject(i);
                marketInfo = new MarketInfo.Builder()
                        .marketID(Long.toString(jObIndex.getLong("marketId")))
                        .marketName(jObIndex.getString("marketName"))
                        .build();
                lstMarkets.add(marketInfo);
            }
            return lstMarkets;
        }
        return null;
    }

}
