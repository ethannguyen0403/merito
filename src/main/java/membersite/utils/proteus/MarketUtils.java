package membersite.utils.proteus;

import baseTest.BaseCaseTest;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Odds;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import com.paltech.utils.DateUtils;
import static common.MemberConstants.GMT_MINUS_4_30;
import static common.ProteusConstant.AUTHORIZATION_API;

public class MarketUtils extends BaseCaseTest {

    private static JSONObject getAllMarketUnderEventFromProviderAPI(String oddsType,int eventId) {
        String url = String.format("%s/proteus-member-service/odds/v3/%s",proteusAPIDomainURL,oddsType.toLowerCase());
        String jsn = String.format("{\n" +
                        "    \"eventId\": [\n" +
                        "        %s\n" +
                        "    ],\n" +
                        "    \"betType\": \"%s\"\n" +
                        "}"
                , eventId, "");
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(url, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        return jsonObject;
    }

    private static JSONObject getAllEventUnderSport(int sportID) {
        String url = String.format("%s/v3/fixtures?sportId=%s",proteusProviderAPIURL,sportID);
        Map<String, String> headersParam = new HashMap<String, String>() {
            {
                put("Authorization",AUTHORIZATION_API);
                put("Content-Type", Configs.HEADER_JSON);
            }
        };
        return WSUtils.getGETJSONObjectWithDynamicHeaders(url,headersParam);
    }


    public static Market getEventInfoUnderLeague(int sportID, String leagueName, String eventID) {
        JSONObject sportObj = getAllEventUnderSport(sportID);
        if (Objects.nonNull(sportObj)) {
            JSONArray leagueArr = sportObj.getJSONArray("league");
            for (int i = 0; i < leagueArr.length(); i++) {
                JSONObject objLeague = leagueArr.getJSONObject(i);
                if(objLeague.getString("name").equalsIgnoreCase(leagueName)) {
                    JSONArray eventArr = objLeague.getJSONArray("events");
                    for (int j = 0; j < eventArr.length(); j++) {
                        JSONObject eventObj = eventArr.getJSONObject(j);
                        if (Integer.toString(eventObj.getInt("id")).equals(eventID)) {
                            return new Market.Builder()
                                    .leagueName(objLeague.getString("name"))
                                    .eventStartTime(eventObj.getString("starts"))
                                    .homeName(eventObj.getString("home"))
                                    .awayName(eventObj.getString("away"))
                                    .build();
                        }
                    }
                }
            }
        }
        System.out.println(String.format(" There is no league under sport id %s has the event id %s",sportID, eventID));
        return null;

    }

    private static Odds getOddsAllSelectionUnderAMarketFromProviderAPI(JSONObject obj) {
        Odds _odds = new Odds.Builder()
                .odds(0)
                .team(obj.getString("team"))
                .side(obj.getString("side"))
                .hdp(obj.getDouble("hdp"))
                .build();
        //handle when odds is null
        if (obj.get("odds").equals(JSONObject.NULL) || obj.get("originalOdds").equals(JSONObject.NULL)) {
            _odds.setOriginalOdds(0);
            _odds.setOdds(0);
        }
        else {
            _odds.setOriginalOdds(obj.getDouble("originalOdds"));
            _odds.setOdds(obj.getDouble("odds"));
        }
       return _odds;
    }


    public static List<Market> getSportbookEventAPI(String oddsType, int eventId){
       return getSportbookEventAPI(oddsType,eventId,true);
    }

    public static List<Market> getSportbookEventAPI(String oddsType, int eventId, boolean isNegativeOdds){
        JSONObject jsonObject = getAllMarketUnderEventFromProviderAPI(oddsType,eventId);
        List<Market> lstEvents = new ArrayList<>();
        Market market;
        if (Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONArray oddObjLst = obj.getJSONArray("odds");
                List<Odds> lstOdds =  new ArrayList<>();
                for (int j = 0; j < oddObjLst.length(); j++) {
                    JSONObject oddsObj = oddObjLst.getJSONObject(j);
                    Odds odds = getOddsAllSelectionUnderAMarketFromProviderAPI(oddsObj);
                    lstOdds.add(odds);
                }
                market = new Market.Builder()
                        .periodId(obj.getInt("periodId"))
                        .eventStartTime(obj.getString("cutoff"))
                        .eventId(obj.getInt("eventId"))
                        .lineID(obj.getLong("lineId"))
                        .altLineID(obj.getLong("altLineId"))
                        .betType(obj.getString("betType"))
                        .handicap(obj.getDouble("handicap"))
                        .oddsKey(obj.getString("oddsKey"))
                        .team(obj.getString("team"))
                        .status(obj.getString("status"))
                        .oddsType(obj.getString("oddsType"))
                        .oddsFormat(obj.getString("oddsFormat"))
                        .marketKey(obj.getString("marketKey"))
                        .odds(lstOdds)
                        .build();
                // Add negative odd when Odds containing negative odd
                if(isNegativeOdds && market.isMarketContainsNegativeOdds()){
                    lstEvents.add(market);
                }
                // Add negative odd when Odds containing positive odd
                if(!isNegativeOdds && market.isMarketContainsPositiveOdds()){
                    lstEvents.add(market);
                }
//                if(market.isMarketContainsNegativeOdds() == isNegativeOdds)
//                   lstEvents.add(market);
            }
        }
        return lstEvents;
    }



    public static Market getMarketByOddsKey(String oddsType, int eventID, String oddsKey){
        List<Market> lstMarket = getSportbookEventAPI(oddsType, eventID);
        for (Market m: lstMarket) {
            if(m.getOddsKey().equalsIgnoreCase(oddsKey))
                return m;
        }
        return null;
    }

    public static Market getMarketByOddsKey(String oddsType, int eventID, String oddsKey, boolean isNegativeOdds){
        List<Market> lstMarket = getSportbookEventAPI(oddsType, eventID, isNegativeOdds);
        for (Market m: lstMarket) {
            if(m.getOddsKey().equalsIgnoreCase(oddsKey))
                return m;
        }
        return null;
    }
//    public static Market getMarketByOddsKey(String oddsType, int eventID, String oddsKey, int isNegativeOdds){
//        List<Market> lstMarket = getSportbookEventAPI(oddsType, eventID);
//
//        for (Market m: lstMarket) {
//            if(m.getOddsKey().equalsIgnoreCase(oddsKey))
//            {
//                if(m.isMarketContainsNegativeOdds() && isNegativeOdds > 0)
//                    return m;
//                else if(m.isMarketContainsNegativeOdds() && isNegativeOdds < 0)
//                    return m;
//            }
//            return m;
//        }
//        return null;
//    }

    public static JSONObject getMarketJSON(int eventId, String betType) {
//        String url = "https://www.ps388win.com/proteus-member-service/odds/v3/decimal";
        String url = String.format("%s/proteus-member-service/odds/v3/decimal",proteusAPIDomainURL);
        String jsn = String.format("{\n" +
                        "    \"eventId\": [\n" +
                        "        %s\n" +
                        "    ],\n" +
                        "    \"betType\": \"%s\"\n" +
                        "}"
                , eventId, betType);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(url, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        return jsonObject;
    }

    private static JSONObject getListLeagueJSON(String periodType) {
        String fromDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_MINUS_4_30);
        String url = String.format("%s/proteus-member-service/before-login/league-period/league/v1/sport-id/29/market-type/ALL/period-type/%s/from-date/%sT00:00:00/to-date/9998-12-31T13:00:00/timezone/-04:00/locale/en-US", proteusAPIDomainURL, periodType, fromDate);
        return WSUtils.getGETJSONObjectWithCookies(url, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static ArrayList<String> getListLeagues(String periodType) {
        ArrayList<String> lstLeagues = new ArrayList<>();
        JSONObject object = getListLeagueJSON(periodType);
        if (Objects.nonNull(object)) {
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                lstLeagues.add(array.getJSONObject(i).getString("leagueName"));
            }
            return lstLeagues;
        }
        return lstLeagues;
    }
    private static JSONObject getListActiveSportJSON() {
        String url = String.format("%s/proteus-member-service/before-login/left-menu/menu-item/euro_view/id/sports/tz/-04:00/locale/en-US", proteusAPIDomainURL);
        return WSUtils.getGETJSONObjectWithCookies(url, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static List<String> getListActiveSports() {
        JSONObject jsonObject = getListActiveSportJSON();
        List<String> lstSports = new ArrayList<>();
        if (Objects.nonNull(jsonObject)) {
            JSONObject jsonObjectMenu = jsonObject.getJSONObject("data").getJSONObject("menu");
            JSONArray jsonArray = jsonObjectMenu.getJSONArray("child");
            for (int i = 0; i < jsonArray.length(); i++) {
                lstSports.add(jsonArray.getJSONObject(i).getString("name"));
            }
            return lstSports;
        }
        return lstSports;
    }
    public static List<Market> getListMarketByMarketType(String oddsType, int eventID, String marketType, boolean isFullMatch){
        int periodId;
        if(isFullMatch) {
            periodId = 0;
        } else {
            periodId = 1;
        }
        List<Market> lstMarketApi = getSportbookEventAPI(oddsType, eventID);
        List<Market> lstMarketReturn = new ArrayList<>();
        for (Market m: lstMarketApi) {
            if(marketType.equalsIgnoreCase("TEAM_TOTAL_POINTS")) {
                if(m.getBetType().equalsIgnoreCase(marketType) && m.getPeriodId() == periodId)
                    lstMarketReturn.add(m);
            } else {
                //for market HDP/OverUnder there is redundant record (altLineId = -1) from api without show on UI > need to exclude this
                if(m.getBetType().equalsIgnoreCase(marketType) && m.getPeriodId() == periodId && m.getAltLineID() != -1)
                    lstMarketReturn.add(m);
            }
        }
        return lstMarketReturn;
    }
}
