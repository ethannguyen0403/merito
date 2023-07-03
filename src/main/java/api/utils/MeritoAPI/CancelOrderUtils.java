package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import objects.Environment;
import api.objects.meritoAPI.Order;
import api.objects.meritoAPI.result.OrderResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static api.testcases.BaseCaseAPI.isAddHeader;

public class CancelOrderUtils {
    private static JSONObject cancelOrderJson(String token,String betId, String sizeReduction){
        String api = String.format("%sbetting-api/orders/cancel", Environment.domainULR);
        String jsn = String.format("[{\"betId\":\"%s\",\"sizeReduction\":\"%s\"}]", betId,sizeReduction);
        if(!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,jsn, "",Configs.HEADER_JSON,"token",token);
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

    public static OrderResult cancelOrder(String token, String betId, String sizeReduction) {
        JSONObject jsonObject = cancelOrderJson(token,betId,sizeReduction);
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
        String errormessage ="";
        if(jObj.has("errorMessage"))
            errormessage = jObj.getString("errorMessage");

        return new Order.Builder()
                .betId(jObj.getInt("betId"))
                .errorMessage(errormessage)
                .build();
    }

}