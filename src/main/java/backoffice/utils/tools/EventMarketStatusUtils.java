package backoffice.utils.tools;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;

public class EventMarketStatusUtils {
    public static List<ArrayList<String>> getSport(String date, boolean open) {
        // date format in : 2020-10-26
        List<ArrayList<String>> lstSport = new ArrayList<>();
        String filter = String.format("eventDate=%s&open=%s", date, open);
        String api = String.format("%s/market-tools/list-sport.json?%s",backofficeUrl, filter);
        //String api = String.format("%s/market-tools/list-sport.json?_=%s",backofficeUrl, DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);

        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(Objects.nonNull(jsonObject))
                    lstSport.add(i, new ArrayList<String>(
                        Arrays.asList(Integer.toString(jsonObject.getInt("sportId")),jsonObject.getString("sportName"))));

            }
        }
        return lstSport;
    }
    public static List<ArrayList<String>> getCompetition(String date, boolean open,String sportId) {
        // date format in : 2020-10-26
        List<ArrayList<String>> lstCompetition = new ArrayList<>();
        String filter = String.format("eventDate=%s&open=%s&sportId=%s", date, open,sportId);
        String api = String.format("%s/market-tools/list-comp.json?%s",backofficeUrl, filter);
        //String api = String.format("%s/market-tools/list-sport.json?_=%s",backofficeUrl, DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);

        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstCompetition.add(i, new ArrayList<String>(
                        Arrays.asList(Integer.toString(jsonObject.getInt("compId")),jsonObject.getString("compName"))));
            }
            return lstCompetition;
        }
        return null;
    }
    public static List<ArrayList<String>> getEvent(String date, boolean open,String sportId,String compId) {
        // date format in : 2020-10-26
        List<ArrayList<String>> lstEvent = new ArrayList<>();
        String filter = String.format("eventDate=%s&open=%s&sportId=%s&compId=%s", date, open,sportId,compId);
        String api = String.format("%s/market-tools/list-event.json?%s",backofficeUrl, filter);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstEvent.add(i, new ArrayList<String>(
                        Arrays.asList(Integer.toString(jsonObject.getInt("eventId")),
                                jsonObject.getString("eventName"),
                                jsonObject.getString("status"),
                                Long.toString(jsonObject.getLong("openDate")))));
            }
            return lstEvent;
        }
        return null;
    }
    public static List<ArrayList<String>> getMarket(String date, boolean open,String eventId) {
        // date format in : 2020-10-26
        List<ArrayList<String>> lstEvent = new ArrayList<>();
        String filter = String.format("eventDate=%s&open=%s&eventId=%s", date, open,eventId);
        String api = String.format("%s/market-tools/list-market.json.json?%s",backofficeUrl, filter);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstEvent.add(i, new ArrayList<String>(
                        Arrays.asList(Integer.toString(jsonObject.getInt("marketId")),
                                jsonObject.getString("marketName"),
                                jsonObject.getString("marketTypeName"),
                                Long.toString(jsonObject.getLong("startTime")),
                                jsonObject.getString("status"))));
            }
        }
        return lstEvent;
    }
}



