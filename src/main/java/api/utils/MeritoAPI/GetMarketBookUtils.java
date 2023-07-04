package api.utils.MeritoAPI;

import api.objects.meritoAPI.Market;
import api.objects.meritoAPI.Selection;
import api.objects.meritoAPI.result.MarketResult;
import com.paltech.constant.Configs;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static api.testcases.BaseCaseAPI.isAddHeader;

public class GetMarketBookUtils {
    private static JSONObject getMarketBookAPIJson(String token, String marketId) {
        String api = String.format("%sbetting-api/sport/market/list/marketBook", Environment.domainULR);
        String jsn = String.format("{\"marketIds\":[\"%s\"]}", marketId);
        if (!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON, jsn, "", Configs.HEADER_JSON, "token", token);
        } else {
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

    public static MarketResult getMarketBookAPI(String token, String marketId) {
        JSONObject jsonObject = getMarketBookAPIJson(token, marketId);
        return getMarketBook(jsonObject);
    }


    private static MarketResult getMarketBook(JSONObject jsonObject) {
        List<Market> lst = new ArrayList<>();
        Market market;
        JSONObject resultObj;
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONArray resultArr = jsonObject.getJSONArray("result");
            for (int i = 0; i < resultArr.length(); i++) {
                resultObj = resultArr.getJSONObject(i);
                market = getMarket(resultObj);
                lst.add(market);
            }
            return new MarketResult.Builder()
                    .isSuccess(isSuccess)
                    .marketList(lst)
                    .build();
        }
        return null;
    }

    private static Market getMarket(JSONObject resultObj) {
        return new Market.Builder()
                .marketId(resultObj.getString("marketId"))
                .status(resultObj.getString("status"))
                .inPlay(resultObj.getBoolean("inplay"))
                .numberOfWinners(resultObj.getInt("numberOfWinners"))
                .numberOfRunners(resultObj.getInt("numberOfRunners"))
                .numberOfActiveRunners(resultObj.getInt("numberOfActiveRunners"))
                .totalMatch(resultObj.getDouble("totalMatched"))
                .totalAvailable(resultObj.getDouble("totalAvailable"))
                .selectionList(getListRunner(resultObj))
                .build();
    }

    private static Selection getRunner(JSONObject jsObj) {
        return new Selection.Builder()
                .id(jsObj.getInt("selectionId"))
                .handicap(jsObj.getDouble("handicap"))
                .status(jsObj.getString("status"))
                .availableBack(getOdds(jsObj, "availableToBack"))
                .availableLay(getOdds(jsObj, "availableToLay"))
                .build();
    }

    private static List<Selection> getListRunner(JSONObject jsObj) {
        JSONArray resultArr = jsObj.getJSONArray("runners");
        JSONObject runnertObj;
        List<Selection> lst = new ArrayList<>();
        for (int i = 0; i < resultArr.length(); i++) {
            runnertObj = resultArr.getJSONObject(i);
            lst.add(getRunner(runnertObj));
        }
        return lst;
    }

    public static double getOdds(JSONObject jsonObject, String property) {
        JSONObject backLayOddsObj = jsonObject.getJSONObject("ex");
        JSONArray jArray = backLayOddsObj.getJSONArray(property);
        if (jArray.length() != 0) {
            JSONObject oddObj = jArray.getJSONObject(0);
            if (Objects.nonNull(oddObj)) {
                return oddObj.getDouble("price");
            }
            return 0.0;
        }
        return 0.0;
    }
}
