package backoffice.utils.settlement;

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

import static baseTest.BaseCaseTest.backofficeUrl;

public class RunnerStatusUtils {
    public static List<ArrayList<String>> getSports(String date) {
        List<ArrayList<String>> lstSports = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/runner/list-sport?eventDate=%s&_=%s",backofficeUrl, date, DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstSports.add(i,new ArrayList<String>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("sportId")),
                        jsonObject.getString("sportName"))));
            }
        }
        return lstSports;
    }

    /**
     * Getting events
     * @param date yyyy-MM-dd
     * @return
     */
    public static List<ArrayList<String>> getEvents(String sportID, String date) {
        List<ArrayList<String>> lstEvents = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/runner/list-event?sportId=%s&eventDate=%s",backofficeUrl, sportID,date);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstEvents.add(i,new ArrayList<String>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("eventId")),
                        jsonObject.getString("eventName"))));
            }
        }
        return lstEvents;
    }

    public static List<ArrayList<String>> getMarkets(String eventID) {
        List<ArrayList<String>> lstMarkets = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/runner/list-market?eventId=%s",backofficeUrl, eventID);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstMarkets.add(i,new ArrayList<String>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("marketId")),
                        jsonObject.getString("marketName"))));
            }
        }
        return lstMarkets;
    }
    public static List<ArrayList<String>> getRunner(String eventID,String marketID) {
        List<ArrayList<String>> lstRunners = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/runner/list-runner?eventId=%s&marketId=%s",backofficeUrl, eventID, marketID);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstRunners.add(i,new ArrayList<String>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("eventID")),
                        Integer.toString(jsonObject.getInt("marketID")),
                        jsonObject.getString("marketStatus"),
                        Integer.toString(jsonObject.getInt("runnerId")),
                        jsonObject.getString("runnerName"),
                        Integer.toString(jsonObject.getInt("handicap")),
                        jsonObject.getString("runnerStatus"),
                        jsonObject.getString("lastUpdateBy"))));
            }
        }
        return lstRunners;
    }
}
