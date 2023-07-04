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

public class PlaceOrderUtils {
    private static JSONObject placeOrderJson(String token, String eventId, String selectionId, String marketId, String handicap, String price, String size, String orderType, String side, String persistenceType) {
        String api = String.format("%sbetting-api/orders/place", Environment.domainULR);
        String jsn = String.format("[{\"eventId\":\"%s\",\"selectionId\":\"%s\",\"marketId\":\"%s\",\"handicap\":\"%s\",\"price\":\"%s\",\"size\":\"%s\",\"orderType\":\"%s\",\"side\":\"%s\",\"persistenceType\":\"%s\"}]",
                eventId, selectionId, marketId, handicap, price, size, orderType, side, persistenceType);
        if (!isAddHeader) {
//            WSUtils.getPOSTJSONArrayWithDynamicHeaders(api,Configs.HEADER_JSON ,jsn, "",Configs.HEADER_JSON,"token",token);
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

    public static OrderResult placeOrder(String token, String eventId, String selectionId, String marketId, String handicap, String price, String size, String orderType, String side, String persistenceType) {
        if (side == "LAY") {
            if (Double.parseDouble(price) < 4) {
                System.out.println(String.format("Debug: Won place the bet as Lay Price = %s > 4", price));
                return null;
            }
        }
        if (Double.parseDouble(price) == 0) {
            System.out.println(String.format("Debug: Won place the bet a Lay Price is empty", price));
            return null;
        }
        JSONObject jsonObject = placeOrderJson(token, eventId, selectionId, marketId, handicap, price, size, orderType, side, persistenceType);
        List<Order> lst = new ArrayList<>();
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONArray resultArr = jsonObject.getJSONArray("result");
            JSONObject eventResultObj = resultArr.getJSONObject(0);
            lst.add(getOrder(eventResultObj));
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
                .orderType(jObj.getString("orderType"))
                .handicap(jObj.getDouble("handicap"))
                .size(jObj.getDouble("size"))
                .price(jObj.getDouble("price"))
                .persistenceType(jObj.getString("persistenceType"))
                .sizeMatched(jObj.getDouble("sizeMatched"))
                .priceMatched(jObj.getDouble("priceMatched"))
                .betId(jObj.getInt("betId"))
                .placedDate(placeDate)
                .matchedDate(matchedDate)
                .errorMessage(errorMessage)
                .build();
    }


}