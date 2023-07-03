package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.utils.WSUtils;
import api.objects.meritoAPI.*;
import api.objects.meritoAPI.result.MarketResult;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetMarketCatalogUtils {
    private static JSONObject getMarketCatalogAPIJson(String token,String eventTypeId,String eventId, String competitionId,String marketId, String sort){
        String api = String.format("%sbetting-api/sport/market/list/marketCatalogue", Environment.domainULR);
        String jsn = String.format("{\"filter\":{\"eventTypeIds\":[\"%s\"],\"eventIds\":[\"%s\"],\"competitionIds\":[\"%s\"],\"marketIds\":[\"%s\"]},\"sort\":\"%s\"}",eventTypeId,eventId,competitionId,marketId,sort);
        return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,jsn, "",Configs.HEADER_JSON,"token",token);
    }
    private static JSONObject getMarketCatalogV2APIJson(String token,String eventTypeId,String eventId, String competitionId,String marketId, String sort){
        String api = String.format("%sbetting-api/v2/sport/market/list/marketCatalogue", Environment.domainULR);
        String jsn = String.format("{\"eventTypeIds\":[\"%s\"],\"eventIds\":[\"%s\"],\"competitionIds\":[\"%s\"],\"marketIds\":[\"%s\"],\"sort\":\"%s\"}",eventTypeId,eventId,competitionId,marketId,sort);
        return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,jsn, "",Configs.HEADER_JSON,"token",token);
    }
    private static JSONObject getMarketCatalogV2APIJson1(String token,String eventTypeId,String eventId, String competitionId,String marketId, String sort){
//        String api = String.format("%sbetting-api/v2/sport/market/list/marketCatalogue", Environment.domainULR);
        String api = String.format("%sbetting-api/sport/market/list/marketCatalogue", Environment.domainULR);
//        https://apistg.beatus88.com/betting-api/sport/market/list/marketCatalogue
        String jsn = String.format("{\n" +
                        "    \"filter\": {\n" +
                        "    },\n" +
                        "    \"sort\": \"FIRST_TO_START\"\n" +
                        "}"
                ,eventTypeId,eventId,competitionId,marketId,sort);
        return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,jsn, "",Configs.HEADER_JSON,"token",token);
    }

    public static MarketResult getMarketCatalogAPI (String token, String eventTypeId, String eventId, String competitionId,String marketId, String sort){
        JSONObject jsonObject = getMarketCatalogAPIJson(token,eventTypeId,eventId,competitionId,marketId,sort);
       return getMarketCatalog(jsonObject);
    }
    public static MarketResult getMarketCatalogV2API1 (String token, String eventTypeId, String eventId, String competitionId,String marketId, String sort){
        JSONObject jsonObject = getMarketCatalogV2APIJson1(token,eventTypeId,eventId,competitionId,marketId,sort);
        return getMarketCatalog(jsonObject);
    }
    public static MarketResult getMarketCatalogV2API (String token, String eventTypeId, String eventId, String competitionId,String marketId, String sort){
        JSONObject jsonObject = getMarketCatalogV2APIJson(token,eventTypeId,eventId,competitionId,marketId,sort);
        return getMarketCatalog(jsonObject);
    }

    private static MarketResult getMarketCatalog( JSONObject jsonObject){
        List<Market> lst  = new ArrayList<>();
        Market market;
        JSONObject resultObj;
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONArray resultArr = jsonObject.getJSONArray("result");
            for(int i = 0; i< resultArr.length(); i++){
                resultObj = resultArr.getJSONObject(i);
                market = getMarket(resultObj);
                lst.add(market);
                return new MarketResult.Builder()
                        .isSuccess(isSuccess)
                        .marketList(lst)
                        .build();
            }
        }
        return null;
    }
    private static Market getMarket(JSONObject resultObj){
        return new Market.Builder()
                .marketId(resultObj.getString("marketId"))
                .marketName(resultObj.getString("marketName"))
                .marketStartTime(resultObj.getString("marketStartTime"))
                .totalMatch(resultObj.getInt("totalMatched"))
                .event(getEvent(resultObj.getJSONObject("event")))
                .eventType(getEventType(resultObj.getJSONObject("eventType")))
                .competition(getCompetition(resultObj.getJSONObject("competition")))
                .marketDescription(getMarketDescription(resultObj.getJSONObject("description")))
                .selectionList(getListRunner(resultObj))
                .build();
    }
    private static Event getEvent(JSONObject jsObj){
        return new Event.Builder()
                .id(jsObj.getInt("id"))
                .name(jsObj.getString("name"))
                .countryCode(jsObj.getString("countryCode"))
                .timezone(jsObj.getString("timezone"))
                .openDate(jsObj.getString("openDate"))
                .build();
    }
    private static SportType getEventType(JSONObject jsObj){
        return new SportType.Builder()
                .id(jsObj.getInt("id"))
                .name(jsObj.getString("name"))
                .build();
    }
    private static Competition getCompetition(JSONObject jsObj){
        return new Competition.Builder()
                .id(jsObj.getInt("id"))
                .name(jsObj.getString("name"))
                .build();
    }
    private static MarketDescription getMarketDescription(JSONObject jsObj){
        return new MarketDescription.Builder()
                .persistenceEnabled(jsObj.getBoolean("persistenceEnabled"))
                .marketTime(jsObj.getString("marketTime"))
                .suspendTime(jsObj.getString("suspendTime"))
                .bettingType(jsObj.getString("bettingType"))
                .turnInPlayEnabled(jsObj.getBoolean("turnInPlayEnabled"))
                .marketType(jsObj.getString("marketType"))
                .regulator(jsObj.getString("regulator"))
                .marketBaseRate(jsObj.getInt("marketBaseRate"))
                .discountAllowed(jsObj.getBoolean("discountAllowed"))
                .wallet(jsObj.getString("wallet"))
                .rules(jsObj.getString("rules"))
                .rulesHasDate(jsObj.getBoolean("rulesHasDate"))
                .eachWayDivisor(jsObj.getInt("eachWayDivisor"))
                .build();
    }

    private static Selection getRunner(JSONObject jsObj) {
        return new Selection.Builder()
                .id(jsObj.getInt("selectionId"))
                .name(jsObj.getString("runnerName"))
                .handicap(jsObj.getInt("handicap"))
                .sortPriority(jsObj.getInt("sortPriority"))
                .build();

    }
    private static List<Selection> getListRunner(JSONObject jsObj){
        JSONArray resultArr = jsObj.getJSONArray("runners");
        JSONObject runnertObj;
        List<Selection> lst = new ArrayList<>();
        for(int i = 0; i< resultArr.length(); i++){
            runnertObj = resultArr.getJSONObject(i);
            lst.add(getRunner(runnertObj));
        }
        return lst;
    }
    
}
