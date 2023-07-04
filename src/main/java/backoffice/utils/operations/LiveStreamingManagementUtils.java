package backoffice.utils.operations;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import membersite.objects.sat.Event;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;

public class LiveStreamingManagementUtils {

    public static List<Event> getListEvent(String date, String sport) {
        return getListEvent(date, "", "", "", "", sport);
    }

    public static List<Event> getListEvent(String date, String leagueName, String eventName, String lcLeagueName, String lcEventNAme, String sport) {
        List<Event> lstEvent = new ArrayList<>();
        String filter = String.format("date=%s&leagueName=%s&eventName=%s&lcLeagueName=%s&lcEventName=%s&filter=true&unmap=true&mapped=true&sport=%s", date, leagueName, eventName, lcLeagueName, lcEventNAme, sport);
        String api = String.format("%s/system-manager/web/sv/live-center/list?%s", backofficeUrl, filter);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("events")) {
                JSONArray jsnList = jsonObject.getJSONArray("events");
                for (int i = 0; i < jsnList.length(); i++) {
                    JSONObject item = jsnList.getJSONObject(i);
                    if (Objects.nonNull(item)) {
                        lstEvent.add(new Event.Builder()
                                .competitionName(item.getString("competitionName"))
                                .startTime(Long.toString(item.getLong("eventDate")))
                                .eventName(item.getString("eventName"))
                                .id(Integer.toString(item.getInt("id")))
                                .build());
                    }
                }
                return lstEvent;
            }
        }
        return null;
    }

    public static List<Event> getListLCEvent(String date, String sport) {
        return getListLCEvent(date, "", "", "", "", sport);
    }

    public static List<Event> getListLCEvent(String date, String leagueName, String eventName, String lcLeagueName, String lcEventNAme, String sport) {
        List<Event> lstEvent = new ArrayList<>();
        String filter = String.format("date=%s&leagueName=%s&eventName=%s&lcLeagueName=%s&lcEventName=%s&filter=true&unmap=true&mapped=true&sport=%s", date, leagueName, eventName, lcLeagueName, lcEventNAme, sport);
        String api = String.format("%s/system-manager/web/sv/live-center/list?%s", backofficeUrl, filter);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("lcEvents")) {
                JSONArray jsnList = jsonObject.getJSONArray("lcEvents");
                for (int i = 0; i < jsnList.length(); i++) {
                    JSONObject item = jsnList.getJSONObject(i);
                    if (Objects.nonNull(item)) {
                        lstEvent.add(new Event.Builder()
                                .competitionName(item.getString("competitionName"))
                                .startTime(Long.toString(item.getLong("eventDate")))
                                .eventName(item.getString("eventName"))
                                .id(Integer.toString(item.getInt("id")))
                                .build());
                    }
                }
                return lstEvent;
            }
        }
        return null;
    }

    public static List<String> getSportLiveCenter() {
        List<String> lstSport = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/live-center/sport", backofficeUrl);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstSport.add(jsonObject.getString("sportName"));
            }
            return lstSport;
        }
        return null;

    }
}
