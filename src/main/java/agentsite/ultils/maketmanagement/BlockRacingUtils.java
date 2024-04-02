package agentsite.ultils.maketmanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import common.AGConstant;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class BlockRacingUtils {

    public static List<ArrayList<String>> getCountryListOfSport(String sportName) {
        List<ArrayList<String>> countryInfo = new ArrayList<>();
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-blocking-management/gnh/listCountry.json?sportId=%s", domainURL, sportID);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                countryInfo.add(new ArrayList<>(Arrays.asList(
                        Integer.toString(jsonObject.getInt("id")),
                        jsonObject.getString("name"))));
            }
        }
        return countryInfo;
    }

    public static List<Event> getEventListRacing(String sportName) {
        List<ArrayList<String>> lstCountry = getCountryListOfSport(sportName);
        List<Event> lstEvent = new ArrayList<Event>();
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-blocking-management/gnh/summary.json?sportId=%s&countryId=%s&%s",
                domainURL, sportID, lstCountry.get(0).get(0), DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstEvent.add(new Event.Builder()
                        .id(Integer.toString(jsonObject.getInt("venueId")))
                        .eventName(jsonObject.getString("venueName"))
                        .countryName(lstCountry.get(0).get(1))
                        .build());
            }
        }
        return lstEvent;
    }

    public static List<Market> getMarketListRacing(Event event, String sportName) {
        List<ArrayList<String>> lstCountry = getCountryListOfSport(sportName);
        List<Market> lstMarket = new ArrayList<Market>();
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-blocking-management/gnh/summary.json?sportId=%s&countryId=%s&%s",
                domainURL, sportID, lstCountry.get(0).get(0), DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("venueName").equalsIgnoreCase(event.getEventName())) {
                    JSONArray marketArr = jsonObject.getJSONArray("markets");
                    for (int j = 0; j < marketArr.length(); j++) {
                        JSONObject jsonObjectMarket = marketArr.getJSONObject(j);
                        lstMarket.add(new Market.Builder()
                                .marketName(jsonObjectMarket.getString("name"))
                                .marketID(Integer.toString(jsonObjectMarket.getInt("id")))
                                .marketType(jsonObjectMarket.getString("code"))
                                .build());
                    }
                    return lstMarket;
                }
            }
        }
        return lstMarket;
    }

}
