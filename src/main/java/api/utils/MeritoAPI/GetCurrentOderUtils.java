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

public class GetCurrentOderUtils {
    private static JSONObject getCurrentOrderJson(String token,String betId,String orderProjection, String fromRecord,String recordCount){
        //placedate:1635966120000
       // orderBy=BY_MARKET
        //sortDir=EARLIEST_TO_LATEST
        //betIds=324324&orderProjection=ALL&placedDateFrom=1635793680000&placedDateTo=1635966480000&fromRecord=&recordCount=
       /* String api = String.format("%sbetting-api/orders/list/current?betIds=91789&marketIds=%s&orderProjection=%s&placedDateFrom=%s&placedDateTo=%s&orderBy=%s&sortDir=%s&fromRecord=%s&recordCount=%s",
                Environment.domainULR);*/
        String api = String.format("%sbetting-api/orders/list/current?betIds=%s&orderProjection=%s&fromRecord=&recordCount=",
                Environment.domainULR,betId,orderProjection,fromRecord,recordCount);

        if(!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,null, "",Configs.HEADER_JSON,"token",token);
        }else {
            String id_random = StringUtils.generateNumeric(10);
            Map<String, String> headersParam = new HashMap<String, String>() {
                {
                    put("X-Request-ID", id_random);
                    put("X-Request-Meta", "%*re0");
                    put("token", token);
                    put("Content-Type", Configs.HEADER_JSON);
                }
            };
            return WSUtils.getPOSTJSONObjectWithDynamicHeaders(api, null, headersParam);
        }
    }


    public static OrderResult getCurrentOrder (String token,String betId,String orderProjection, String fromRecord,String recordCount) {

      JSONObject jsonObject = getCurrentOrderJson(token,betId,orderProjection,fromRecord,recordCount);
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
          JSONArray currentOrderArr = resultObj.getJSONArray("currentOrders");
          JSONObject orderObj;
          for (int i = 0; i<currentOrderArr.length() ;i++){
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
        String placeDate ="";
        String errorMessage ="";
        String persistenceType ="";
        if( jObj.has("matchedDate")){
            matchedDate = jObj.getString("matchedDate");
        }
        if( jObj.has("placeDate")){
            matchedDate = jObj.getString("placeDate");
        }
        if( jObj.has("errorMessage")){
            errorMessage = jObj.getString("errorMessage");
        }

        if( jObj.has("persistenceType")){
            persistenceType = jObj.getString("persistenceType");
        }

        return new Order.Builder()
                .eventId(jObj.getInt("eventId"))
                .marketId(jObj.getString("marketId"))
                .selectionId(jObj.getInt("selection"))
                .side(jObj.getString("side"))
                //.orderType(jObj.getString("orderType"))
                .handicap(jObj.getDouble("handicap"))
                .size(jObj.getDouble("orderSize"))
                .price(jObj.getDouble("orderPrice"))
                .persistenceType(persistenceType)
                .sizeMatched(jObj.getDouble("sizeMatched"))
                .betId(jObj.getInt("betId"))
                .placedDate(placeDate)
                .matchedDate( matchedDate)
                .errorMessage(errorMessage)
                .build();
    }

   
}