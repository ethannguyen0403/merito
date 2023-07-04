package api.utils.MeritoAPI;

import api.objects.meritoAPI.Order;
import api.objects.meritoAPI.result.OrderResult;
import com.paltech.constant.Configs;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static api.testcases.BaseCaseAPI.isAddHeader;

public class GetClearedOderUtils {
    private static JSONObject getClearedOrderJson(String token, String betStatus, String betId, String marektID, String eventId, String sportID, String side) {
        //placedate:1635966120000
        // orderBy=BY_MARKET
        //sortDir=EARLIEST_TO_LATEST
        //betIds=324324&orderProjection=ALL&placedDateFrom=1635793680000&placedDateTo=1635966480000&fromRecord=&recordCount=
       /* String api = String.format("%sbetting-api/orders/list/current?betIds=91789&marketIds=%s&orderProjection=%s&placedDateFrom=%s&placedDateTo=%s&orderBy=%s&sortDir=%s&fromRecord=%s&recordCount=%s",
                Environment.domainULR);*/
        String api = String.format("%sbetting-api/orders/list/cleared?betStatus=%s&betIds=%s&marketIds=%s&eventIds=%s&eventTypeIds=%s&side=%s&fromRecord=&recordCount=",
                Environment.domainULR, betStatus, betId, marektID, eventId, sportID, side);
//        return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,null, "",Configs.HEADER_JSON,"token",token);
        if (!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON, null, "", Configs.HEADER_JSON, "token", token);
        } else {
            String id_random = StringUtils.generateNumeric(10);
            Map<String, String> headersParam = new HashMap<String, String>() {
                {
                    put("X-Request-ID", id_random);
                    put("X-Request-Meta", "empty");
                    put("token", token);
                    put("Content-Type", Configs.HEADER_JSON);
                }
            };
            return WSUtils.getPOSTJSONObjectWithDynamicHeaders(api, null, headersParam);
        }
    }


    public static OrderResult getClearedOrder(String token, String betStatus, String betId, String marektID, String eventId, String sportID, String side) {

        JSONObject jsonObject = getClearedOrderJson(token, betStatus, betId, marektID, eventId, sportID, side);
        //wait for query data
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Order> lst = new ArrayList<>();
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONObject resultObj = jsonObject.getJSONObject("result");
            JSONArray currentOrderArr = resultObj.getJSONArray("clearedOrders");
            JSONObject orderObj;
            for (int i = 0; i < currentOrderArr.length(); i++) {
                orderObj = currentOrderArr.getJSONObject(i);
                lst.add(getOrder(orderObj));
            }
            return new OrderResult.Builder()
                    .isSuccess(isSuccess)
                    .orderList(lst)
                    .build();
        }
        return null;
    }

    private static Order getOrder(JSONObject jObj) {
        String matchedDate = "";
        String placeDate = "";
        String errorMessage = "";
        if (jObj.has("matchedDate")) {
            matchedDate = jObj.getString("matchedDate");
        }
        if (jObj.has("placeDate")) {
            matchedDate = jObj.getString("placeDate");
        }
        if (jObj.has("errorMessage")) {
            errorMessage = jObj.getString("errorMessage");
        }
        return new Order.Builder()
                .eventId(jObj.getInt("eventId"))
                .marketId(jObj.getString("marketId"))
                .selectionId(jObj.getInt("selectionId"))
                .side(jObj.getString("side"))
                // .orderType(jObj.getString("orderType"))
                .handicap(jObj.getDouble("handicap"))
                // .size(jObj.getDouble("orderSize"))
                // .price(jObj.getDouble("orderPrice"))
                .persistenceType(jObj.getString("persistenceType"))
                //.sizeMatched(jObj.getDouble("sizeMatched"))
                .betId(jObj.getInt("betId"))
                .placedDate(placeDate)
                .matchedDate(matchedDate)
                .errorMessage(errorMessage)
                .build();
    }


}