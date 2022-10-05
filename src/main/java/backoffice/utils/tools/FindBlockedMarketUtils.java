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

public class FindBlockedMarketUtils {
    public static List<String> getMarketInfo(String username,String eventID, String marketID) {
        List<String> lstMarket = new ArrayList<>();
        String filter = String.format("username=%s&eventId=%s&market=%s", username, eventID,marketID);
        String api = String.format("%s/system-manager/web/find-blocked-market/list?%s",environment.getBackofficeURL(), filter);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (Objects.nonNull(jsonObject)) {
                    JSONArray marketArray = jsonObject.getJSONArray("market");
                    JSONObject marketObject = marketArray.getJSONObject(0);
                    JSONObject marketO = marketObject.getJSONObject("market");
                    lstMarket.add(String.format("%s (%d)",marketO.getString("name"),marketO.getInt("id")));
                    lstMarket.add(marketObject.getString("marketStatus"));
                    JSONObject marketTypeO = marketObject.getJSONObject("marketType");
                    lstMarket.add(String.format("%s (%s)",marketTypeO.getString("name"),marketTypeO.getString("id")));
                    lstMarket.add(DateUtils.convertMillisToDateTime(Long.toString(marketObject.getLong("startTime")),"yyyy-MM-dd HH:mm:ss"));
                    lstMarket.add(String.format("%.2f %s",marketObject.getDouble("totalMatched"),marketObject.getString("totalMatchedCurrency")));
                    lstMarket.add(marketObject.getString("minuteToStart"));
                }
            }
        }
        return lstMarket;
    }
    public static List<String> getEventInfo(String username,String eventID, String marketID) {
        List<String> lstEvent = new ArrayList<>();
        String filter = String.format("username=%s&eventId=%s&market=%s", username, eventID,marketID);
        String api = String.format("%s/system-manager/web/find-blocked-market/list?%s",environment.getBackofficeURL(), filter);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(Objects.nonNull(jsonObject)) {
                    JSONObject eventObject = jsonObject.getJSONObject("event");
                    JSONObject sportObject = eventObject.getJSONObject("sport");
                    lstEvent.add(String.format("%s (%d)",sportObject.getString("name"),sportObject.getInt("id")));
                    JSONObject compObject = eventObject.getJSONObject("competition");
                    lstEvent.add(String.format("%s (%d)",compObject.getString("name"),compObject.getInt("id")));
                    JSONObject countryObject = eventObject.getJSONObject("country");
                    lstEvent.add(String.format("%s (%s)",countryObject.getString("name"),countryObject.getString("id")));
                    lstEvent.add(eventObject.getString("venue"));
                    JSONObject eventO = eventObject.getJSONObject("event");
                    lstEvent.add(String.format("%s (%s)",eventO.getString("name"),eventO.getInt("id")));
                    lstEvent.add(eventObject.getString("eventStatus"));
                    lstEvent.add(DateUtils.convertMillisToDateTime(Long.toString(eventObject.getLong("openTime")),"yyyy-MM-dd HH:mm:ss"));
                }
            }
        }
        return lstEvent;
    }
    public static List<ArrayList<String>> getUserLevel(String username,String eventID, String marketID) {
        List<ArrayList<String>>  lstLevels = new ArrayList<>();
        String filter = String.format("username=%s&eventId=%s&market=%s", username, eventID,marketID);
        String api = String.format("%s/system-manager/web/find-blocked-market/list?%s",environment.getBackofficeURL(), filter);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (Objects.nonNull(jsonObject)) {
                    JSONObject marketObject = jsonObject.getJSONObject("market");
                    JSONArray jsArray = marketObject.getJSONArray("levels");
                    for (int j = 0; j < jsArray.length(); j++) {
                        JSONObject obj = jsArray.getJSONObject(i);
                        lstLevels.add(new ArrayList<String>(
                                Arrays.asList(obj.getString("userName"),
                                        obj.getString("loginId"),
                                        obj.getString("level"),
                                        obj.getString("currency"),
                                        Boolean.toString(obj.getBoolean("nonLive")),
                                        Boolean.toString(obj.getBoolean("live")),
                                        obj.getString("allowedBy"),
                                        DateUtils.convertMillisToDateTime(Long.toString(obj.getLong("allowedDate")),"yyyy-MM-dd HH:mm:ss"),
                                        Boolean.toString(obj.getBoolean("activeSportStatus")),
                                        obj.getString("activeSportAllowedBy"),
                                        DateUtils.convertMillisToDateTime(Long.toString(obj.getLong("activeSportAllowedDate")),"yyyy-MM-dd HH:mm:ss"),
                                        Boolean.toString(obj.getBoolean("activeMarketTypeStatus")),
                                        obj.getString("activeMarketTypeAllowedBy"),
                                        DateUtils.convertMillisToDateTime(Long.toString(obj.getLong("activeMarketTypeAllowedDate")),"yyyy-MM-dd HH:mm:ss"),
                                        Long.toString(jsonObject.getLong("startTime")),
                                        obj.getString("blockedStatus"),
                                        obj.getString("timeToOpen"),
                                        obj.getString("timeToBet"),
                                        obj.getString("unblockSchedule"),
                                        obj.getString("suspend"),
                                        obj.getString("updateBy"),
                                        obj.getString("updateDate"),
                                        obj.getString("agentBlockStatus"),
                                        obj.getString("agentBlockCompetitionBy"),
                                        DateUtils.convertMillisToDateTime(Long.toString(obj.getLong("agentBlockCompetitionDate")),"yyyy-MM-dd HH:mm:ss"),
                                        obj.getString("blockedBy"),
                                        obj.getString("blockedDate"),
                                        obj.getString("effect"),
                                        Integer.toString(obj.getInt("liquidityThresholdNonLive")),
                                        Integer.toString(obj.getInt("liquidityThresholdLive")),
                                        obj.getString("settingBy"),
                                        DateUtils.convertMillisToDateTime(Long.toString(obj.getLong("settingDate")),"yyyy-MM-dd HH:mm:ss"))));
                    }
                }
            }
        }
        return lstLevels;
    }
}



