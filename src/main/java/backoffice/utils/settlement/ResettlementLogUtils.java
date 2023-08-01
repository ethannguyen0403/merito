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

public class ResettlementLogUtils {
    /**
     * Getting events
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static List<ArrayList<String>> getSports(String date) {
        List<ArrayList<String>> lstSport = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/runner/list-sport-all?eventDate=%s&_=%s", backofficeUrl, date, DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstSport.add(i, new ArrayList<String>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("sportId")),
                        jsonObject.getString("sportName"))));
            }
        }
        return lstSport;
    }
    public static List<ArrayList<String>> getEvents(String sportID, String date) {
        List<ArrayList<String>> lstEvents = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/runner/list-event-all?sportId=%s&eventDate=%s", backofficeUrl, sportID, date);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstEvents.add(i, new ArrayList<String>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("eventId")),
                        jsonObject.getString("eventName"))));
            }
        }
        return lstEvents;
    }
    public static List<ArrayList<String>> getMarkets(String eventID) {
        List<ArrayList<String>> lstMarkets = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/runner/list-market-all?eventId=%s", backofficeUrl, eventID);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstMarkets.add(i, new ArrayList<String>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("marketId")),
                        jsonObject.getString("marketName"))));
            }
        }
        return lstMarkets;
    }
}
