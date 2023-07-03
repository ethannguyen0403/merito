package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import api.objects.meritoAPI.Market;
import api.objects.meritoAPI.Selection;
import api.objects.meritoAPI.result.MarketResult;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static api.testcases.BaseCaseAPI.isAddHeader;

public class GetMarketChangeUtils {
    private static JSONObject getMarketChangeAPIJson(String token,String marketId){
        String api = String.format("%sbetting-api/market/market-change", Environment.domainULR);
        String jsn = String.format("[\"%s\"]",marketId);
        if(!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api,Configs.HEADER_JSON ,jsn, "",Configs.HEADER_JSON,"token",token);
        }else {
            String id_random = StringUtils.generateNumeric(10);
            Map<String, String> headersParam = new HashMap<String, String>() {
                {
                    put("X-Request-ID", id_random);
                    put("X-Request-Meta", jsn);
                    put("token", token);
                    put("Content-Type", Configs.HEADER_JSON);
                }
            };
            return WSUtils.getPOSTJSONObjectWithDynamicHeaders(api, jsn, headersParam);
        }
    }
    private static JSONObject getMarketChangeV2APIJson(String token,String marketId){
        String api = String.format("%sbetting-api/v2/market/market-change", Environment.domainULR);
        String jsn = String.format("{\"marketIds\":[\"%s\"]}",marketId);
        return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,jsn, "",Configs.HEADER_JSON,"token",token);
    }

    public static MarketResult getMarketChangeAPI (String token,String marketId){
        JSONObject jsonObject = getMarketChangeAPIJson(token,marketId);
       return getMarketChange(jsonObject,marketId);
    }
    public static MarketResult getMarketChangeV2API (String token,String marketId){
        JSONObject jsonObject = getMarketChangeV2APIJson(token,marketId);
        return getMarketChange(jsonObject,marketId);
    }

    private static MarketResult getMarketChange( JSONObject jsonObject,String marketID){
        List<Market> lst  = new ArrayList<>();
        Market market;
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONObject resultArr = jsonObject.getJSONObject("result");
            JSONObject marketChangeObj = resultArr.getJSONObject("marketChange");
            JSONObject marketIDObj = marketChangeObj.getJSONObject(marketID);
            market = getMarket(marketIDObj);
            lst.add(market);
            return new MarketResult.Builder()
                    .isSuccess(isSuccess)
                    .marketList(lst)
                    .build();
           /* for(int i = 0; i< resultArr.length(); i++){
                resultObj = resultArr.getJSONObject(i);
                market = getMarket(resultObj);
                lst.add(market);
                return new MarketResult.Builder()
                        .isSuccess(isSuccess)
                        .marketList(lst)
                        .build();
            }*/
        }
        return null;
    }
    private static Market getMarket(JSONObject resultObj){
        return new Market.Builder()
                .marketId(resultObj.getString("marketId"))
                .selectionList(getListRunner(resultObj))
                .build();
    }

    private static Selection getRunner(JSONObject jsObj) {
        int sortPriority =0;
        if(jsObj.has("sortPriority"))
            sortPriority = jsObj.getInt("sortPriority");
        return new Selection.Builder()
                .id(jsObj.getInt("selectionId"))
                .name(jsObj.getString("runnerName"))
                .status(jsObj.getString("status"))
                .handicap(jsObj.getInt("handicap"))
                .sortPriority(sortPriority)
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
