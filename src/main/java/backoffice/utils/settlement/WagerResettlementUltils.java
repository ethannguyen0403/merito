package backoffice.utils.settlement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import backoffice.pages.bo.settlement.WagerResettlementPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static baseTest.BaseCaseTest.backofficeUrl;

public class WagerResettlementUltils {
    /**
     * Getting events
     * @param orderId,type,product
     * @return
     */
    public static List<String> getMarketInfo(String orderId, WagerResettlementPage.BetType type,String product) {
        List<String> lstMarket = new ArrayList<>();
        String api = String.format("%s/backoffice-settlement/audit.json?orderId=%s&betTypeFilter=%s&productCode=%s&_=%s",backofficeUrl, orderId,type,product, DateUtils.getMilliSeconds());
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONObject marketObj = jsonObject.getJSONObject("market");
            lstMarket.add(String.format("%s (%d)",marketObj.getString("sportName"),marketObj.getInt("sportId")));
            lstMarket.add(String.format("%s (%d)",marketObj.getString("compName"),marketObj.getInt("compId")));
            lstMarket.add(String.format("%s (%d)",marketObj.getString("eventName"),marketObj.getInt("eventId")));
            lstMarket.add(DateUtils.convertMillisToDateTime(Long.toString(marketObj.getLong("openDate")),"yyyy-MM-dd HH:mm:ss"));
            lstMarket.add(String.format("%s (%d)",marketObj.getString("marketName"),marketObj.getInt("marketId")));
            lstMarket.add(DateUtils.convertMillisToDateTime(Long.toString(marketObj.getLong("startTime")),"yyyy-MM-dd HH:mm:ss"));
        }
        return lstMarket;
    }

    public static List<String> getOrderInfo(String orderId, WagerResettlementPage.BetType type,String product) {
        List<String> lstOrders = new ArrayList<>();
        String api = String.format("%s/backoffice-settlement/audit.json?orderId=%s&betTypeFilter=%s&productCode=%s&_=%s",backofficeUrl, orderId,type,product, DateUtils.getMilliSeconds());
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, null,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONArray orderArray = jsonObject.getJSONArray("order");
            JSONObject orderObj = orderArray.getJSONObject(0);
            lstOrders.add(Integer.toString(orderObj.getInt("orderId")));
            lstOrders.add(orderObj.getString("selectionName"));
            lstOrders.add(orderObj.getString("betfairAccount"));
            lstOrders.add(orderObj.getString("betfairOrderId"));
            lstOrders.add(String.format("%.2f",orderObj.getDouble("betfairStake")));
            lstOrders.add(String.format("%.2f",orderObj.getDouble("matchedStake")));
            lstOrders.add(orderObj.getBoolean("back")?"Back":"Lay");
            lstOrders.add(String.format("%.2f",orderObj.getDouble("oddOrigin")));
            lstOrders.add(orderObj.getString("currencyCode"));
            lstOrders.add(DateUtils.convertMillisToDateTime(Long.toString(orderObj.getLong("createdDate")),"yyyy-MM-dd HH:mm:ss"));
            lstOrders.add(Integer.toString(orderObj.getInt("handicap")));
            lstOrders.add(orderObj.getString("outcomeStatus"));
            lstOrders.add(orderObj.getString("betfairSettlementStatus"));
            lstOrders.add(orderObj.getString("outcomeStatus"));
            lstOrders.add(orderObj.getString("betfairOutcomeStatus"));

        }
        return lstOrders;
    }
}
