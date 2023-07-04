package membersite.utils.betplacement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import membersite.objects.sat.FancyMarket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.memberMarketServiceURL;
import static com.paltech.utils.WSUtils.getGETJSONArrayWithCookies;

public class CentralFancyUtils {

    private static JSONArray getWicketFancyJSON(String eventId) {
        String api = String.format("%s/api/event/fancy-markets.json?eventIds=%s&marketType=CENTRAL_FANCY", memberMarketServiceURL, eventId);
        return getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    private static JSONArray getWicketBookmakerJSON(String eventId) {
        String api = String.format("%s/api/event/bookmaker-markets.json?eventIds=%s&marketType=CENTRAL_BOOKMAKER", memberMarketServiceURL, eventId);
        return getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static boolean isEventHasCentralFancy(String eventId) {
        JSONArray eventObj = getWicketFancyJSON(eventId);
        if (eventObj.length() == 0) {
            return false;
        }
        return true;
    }


    /**
     * This action get all fancy market from api with the corresponding event
     *
     * @param eventID
     * @return
     */
    public static List<FancyMarket> getFancy(String eventID) {
        List<FancyMarket> lstMarket = new ArrayList<>();
        JSONArray marketJSONArray = getWicketFancyJSON(eventID);
        if (marketJSONArray.length() == 0) {
            System.out.println("DEBUG: No data get fancy market api of event id" + eventID);
            return null;
        }
        if (Objects.nonNull(marketJSONArray)) {
            for (int i = 0; i < marketJSONArray.length(); i++) {
                JSONObject marketObj = marketJSONArray.getJSONObject(i);
                lstMarket.add(new FancyMarket.Builder()
                        .eventName(marketObj.getString("eventName").trim())
                        .marketID(Integer.toString(marketObj.getInt("marketId")))
                        .marketName(marketObj.getString("marketName").trim())
                        .eventID(eventID)
                        .status(marketObj.getString("status"))
                        .marketType(marketObj.getString("marketType"))
                        .build());
            }
            return lstMarket;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + marketJSONArray.toString());
        return null;
    }


    /**
     * This action will get the list Wicket Fancy with the expected status
     *
     * @param eventID the list market get from api
     * @param status  the expected status
     * @return the list with expected status
     */
    public static List<FancyMarket> getWicketFancyByStatus(String eventID, String status) {
        List<FancyMarket> lstOutput = new ArrayList<>();
        List<FancyMarket> lstMarket = getFancy(eventID);
        if (Objects.nonNull(lstMarket)) {
            for (FancyMarket market : lstMarket) {
                if (market.getStatus().equalsIgnoreCase(status)) {
                    lstOutput.add(market);
                }
            }
            return lstOutput;
        }
        System.out.println(String.format("DEBUG: There is no wicket fancy display in the event %s", eventID));
        return null;
    }


}
