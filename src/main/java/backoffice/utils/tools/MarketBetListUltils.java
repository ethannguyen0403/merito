package backoffice.utils.tools;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseMerito.environment;

public class MarketBetListUltils {
    public static List<String> viewMarketInfo(String marketId) {
        List<String> lst = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/markets/view-market-info?marketId=%s",environment.getBackofficeURL(),marketId);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(Objects.nonNull(jsonObject)){
            JSONArray infoArray = jsonObject.getJSONArray("marketInfo");
            for(int i = 0; i<infoArray.length(); i++) {
                JSONObject infoObject = infoArray.getJSONObject(i);
                if(Objects.nonNull(infoObject)){
                    lst.add(String.format("%s (%d)",infoObject.getString("sportName"),infoObject.getInt("sportId")));
                    lst.add(String.format("%s (%d)",infoObject.getString("compName"),infoObject.getInt("compId")));
                    lst.add(String.format("%s (%d)",infoObject.getString("eventName"),infoObject.getInt("eventId")));
                    lst.add(String.format("%s", DateUtils.convertMillisToDateTime(Long.toString(infoObject.getLong("eventDate")),"dd-MMM-yyy hh:mm:ss")));
                    lst.add(String.format("%s (%d)",infoObject.getString("marketName"),infoObject.getInt("marketId")));
                }
            }
        }
        return lst;
    }
    private static String getEventID(String marketID) {
        String api = String.format("%s/system-manager/web/sv/markets/view-market-info?marketId=%s",environment.getBackofficeURL(), marketID);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONArray infoArray = jsonObject.getJSONArray("marketInfo");
            for (int i = 0; i < infoArray.length(); i++) {
                JSONObject infoObject = infoArray.getJSONObject(i);
                if (Objects.nonNull(infoObject)) {
                    return Integer.toString(infoObject.getInt("eventId"));
                }
            }
        }
        return null;
    }
    public static List<ArrayList<String>> viewMarketBetList(String marketId) {
        List<ArrayList<String>> lst = new ArrayList<>();
        String eventID = getEventID(marketId);
        String api = String.format("%s/system-manager/web/sv/markets/view-market-bet-list?marketId=%s&eventId=%s&rp=20&page=1",environment.getBackofficeURL(),marketId,eventID);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONArray infoArray = jsonObject.getJSONArray("records");
            for (int i = 0; i < infoArray.length(); i++) {
                JSONObject infoObject = infoArray.getJSONObject(i);
                if(Objects.nonNull(infoObject)){
                    String status ="";
                    if(infoObject.getString("status").equals("CONFIRMED"))
                        status = "MATCHED";
                    if(infoObject.getString("status").equals("CANCELLED")){
                        if(infoObject.getString("statusReason").equals("MANUAL_VOIDED"))
                            status = "OPERATOR VOIDED";
                        else
                            status = "SYSTEM VOIDED";
                    }
                    if(infoObject.getString("status").equals("SETTLED")){
                        if(infoObject.getDouble("isWin")==-1.0)
                            status = "LOST";
                        else
                            status = "WON";
                    }
                    lst.add(i, new ArrayList<String>(
                            Arrays.asList(Integer.toString(i+1),
                                    infoObject.getString("userCode"),
                                    infoObject.getString("nickName"),
                                    DateUtils.convertMillisToDateTime(Long.toString(infoObject.getLong("placedDate")),"dd-MMM-yyyy HH:mm:ss"),
                                    Integer.toString(infoObject.getInt("orderId")),
                                    status,
                                    infoObject.getString("runnerName"),
                                    DateUtils.convertMillisToDateTime(Long.toString(infoObject.getLong("matchedTime")),"dd-MMM-yyyy HH:mm:ss"),
                                    infoObject.getString("type"),
                                    String.format("%.2f",infoObject.getDouble("placedOdds")),
                                    String.format("%.2f",infoObject.getDouble("placedOddsStr")),
                                    String.format("%.2f",infoObject.getDouble("stake")),
                                    infoObject.getString("currencyCode"),
                                   String.format("%d",infoObject.getInt("handicap")))));
                }
            }

        }

        return lst;

    }
}



