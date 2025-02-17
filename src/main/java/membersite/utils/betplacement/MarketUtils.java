package membersite.utils.betplacement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.DoubleUtils;
import com.paltech.utils.WSUtils;
import membersite.objects.AccountBalance;
import membersite.objects.sat.Event;
import membersite.objects.sat.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;


public class MarketUtils {

    public static String getEvent(String sport) {
        // String api = String.format()

        return "eventID";
    }

//    public static String getMinBet(SportPage.Sports sportName, SportPage.BetType betType) {
//        String api = String.format("%s/member-service/user/bdata?_=%s", domainURL, DateUtils.getMilliSeconds());
//        JSONObject jsonObject = WSUtils.getGETJSONResponse(api, null,Configs.HEADER_JSON);
//        if (Objects.nonNull(jsonObject)) {
//            if (jsonObject.has("betSettings")) {
//                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
//                String betTypeName = betType.equals(SportPage.BetType.BACK) ? "backMinBet" : "layMinBet";
//                for (int i=0; i < arraySports.length(); i++) {
//                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
//                    if (jsonSportSetting.getString("sportGroup").equals(sportName.toString())) {
//                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
//                    }
//                }
//            }
//        }
//        System.out.println("DEBUG: getGETJSONResponse is null");
//        return "-1";
//    }
//
//    public static String getMaxBet(SportPage.Sports sportName, SportPage.BetType betType) {
//        String api = String.format("%s/member-service/user/bdata?_=%s", domainURL, DateUtils.getMilliSeconds());
//        JSONObject jsonObject = WSUtils.getGETJSONResponse(api, null,Configs.HEADER_JSON);
//        if (Objects.nonNull(jsonObject)) {
//            if (jsonObject.has("betSettings")) {
//                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
//                String betTypeName = betType.equals(SportPage.BetType.BACK) ? "backMaxBet" : "layMaxBet";
//                for (int i=0; i < arraySports.length(); i++) {
//                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
//                    if (jsonSportSetting.getString("sportGroup").equals(sportName.toString())) {
//                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
//                    }
//                }
//            }
//        }
//        System.out.println("DEBUG: getGETJSONResponse is null");
//        return "-1";
//    }

    /**
     * To get placed order form api
     *
     * @author isabella.huynh
     * @created 10/3/2020
     */
    public static List<Order> getOrder() {
        List<Order> wagers = new ArrayList<>();
        String api = String.format("%s/member-service/order/place-bets?ofs=-420", domainURL);
        JSONObject jsonObject = WSUtils.getGETJSONResponse(api, "null", Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("selections")) {
                JSONArray arraySports = jsonObject.getJSONArray("selections");
                for (int i = 0; i < arraySports.length(); i++) {
                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
                    boolean isBack = jsonSportSetting.getString("side").equalsIgnoreCase("BACK") ? true : false;
                    Order wager = new Order.Builder()
                            .eventID(jsonSportSetting.getString("eventId"))
                            .isBack(isBack)
                            .stake(jsonSportSetting.getString("stake"))
                            .odds(jsonSportSetting.getString("odds"))
                            .persistenceType(jsonSportSetting.getString("persistenceType"))
                            .stakeMatched(jsonSportSetting.getString("stakeMatched"))
                            .newOdds(jsonSportSetting.getString("newOdds"))
                            .orderID(jsonSportSetting.getString("orderId"))
                            .placeDate(jsonSportSetting.getString("placedDate"))
                            .matchedDate(jsonSportSetting.getString("matchedDate"))
                            .errorMessage(jsonSportSetting.getString("errorMessage"))
                            .build();

                    wagers.add(wager);
                }
            }
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return wagers;
    }

    public static AccountBalance getUserBalance() {
        String api = String.format("%s/member-service/user/balance?_t=%s", domainURL, DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayResponse(api, Configs.HEADER_JSON_CHARSET, Configs.HEADER_JSON);
        JSONObject jsonBalance = jsonArray.getJSONObject(0);
        if (Objects.nonNull(jsonBalance)) {
            return new AccountBalance.Builder()
                    .balance(String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(jsonBalance.getDouble("cashBalance") + jsonBalance.getDouble("outstanding") + jsonBalance.getDouble("givenCredit"))))
                    .exposure(String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(jsonBalance.getDouble("outstanding"))))
                    .creditRefer(String.format("%.2f", jsonBalance.getDouble("givenCredit")))
                    .build();
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return null;
    }

    public static boolean isEventHasMatchOddsMarket(String sportId, String eventId, String currency) {
        String api = String.format("%s/member-market/sat/left-menu.json", domainURL);
        String jsn = String.format("{\"type\":\"MARKET\",\"selectedId\":\"%s\",\"sportId\":\"%s\",\"upLevel\":true,\"currencyCode\":\"%s\",\"tzone\":\"GMT+05:30\"}", eventId, sportId, currency);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api,Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(jsonObject.getJSONArray("items").length() != 0) {
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                if(jsonItem.getString("marketType").equalsIgnoreCase("MATCH_ODDS")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isEventHasGoalLineMarket(String sportId, String eventId, String currency) {
        String api = String.format("%s/member-market/sat/left-menu.json", domainURL);
        String jsn = String.format("{\"type\":\"MARKET\",\"selectedId\":\"%s\",\"sportId\":\"%s\",\"upLevel\":true,\"currencyCode\":\"%s\",\"tzone\":\"GMT+05:30\"}", eventId, sportId, currency);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api,Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(jsonObject.getJSONArray("items").length() != 0) {
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                if(jsonItem.getString("marketType").equalsIgnoreCase("ALT_TOTAL_GOALS")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isEventHasInningRunMarket(String sportId, String eventId, String currency) {
        String api = String.format("%s/member-market/sat/left-menu.json", domainURL);
        String jsn = String.format("{\"type\":\"MARKET\",\"selectedId\":\"%s\",\"sportId\":\"%s\",\"upLevel\":true,\"currencyCode\":\"%s\",\"tzone\":\"GMT+05:30\"}", eventId, sportId, currency);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api,Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(jsonObject.getJSONArray("items").length() != 0) {
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                if(jsonItem.getString("marketType").contains("_OVR_LINE") || jsonItem.getString("marketType").contains("_RUNS_LINE")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static String findInningRunMarketName(String sportId, String eventId, String currency) {
        String api = String.format("%s/member-market/sat/left-menu.json", domainURL);
        String jsn = String.format("{\"type\":\"MARKET\",\"selectedId\":\"%s\",\"sportId\":\"%s\",\"upLevel\":true,\"currencyCode\":\"%s\",\"tzone\":\"GMT+05:30\"}", eventId, sportId, currency);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api,Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(jsonObject.getJSONArray("items").length() != 0) {
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                if(jsonItem.getString("marketType").contains("_OVR_LINE") || jsonItem.getString("marketType").contains("_RUNS_LINE")) {
                    return jsonItem.getString("name");
                }
            }
            return "";
        }
        return "";
    }

    public static boolean isEventHasHandicapMarket(String sportId, String eventId, String currency) {
        String api = String.format("%s/member-market/sat/left-menu.json", domainURL);
        String jsn = String.format("{\"type\":\"MARKET\",\"selectedId\":\"%s\",\"sportId\":\"%s\",\"upLevel\":true,\"currencyCode\":\"%s\",\"tzone\":\"GMT+05:30\"}", eventId, sportId, currency);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api,Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(jsonObject.getJSONArray("items").length() != 0) {
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                if(jsonItem.getString("marketType").contains("HANDICAP")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static Event getBetfairEvent(String eventId) {
        String api = String.format("%s/member-service/event-tab/event-info/%s", domainURL, eventId);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        return new Event.Builder().eventName(jsonObject.getString("eventName"))
                .inPlay(jsonObject.getBoolean("inplay"))
                .id(String.valueOf(jsonObject.getLong("eventId")))
                .build();
    }
}
