package api.utils.B2B.appUitls;

import api.objects.B2B.resultObj.WagerObj;
import api.objects.B2B.resultObj.WagerResultObj;
import api.utils.B2B.B2BWSUtils;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static api.common.B2BAPIConstant.*;

public class GetWagerUtils {
    private static JSONObject getOrderAPI(String agentKey, String token, String json) throws UnsupportedEncodingException {
        String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token, json);
        String api = String.format("%s%s?body=%s", Environment.domainULR, GET_SETTLED_WAGER_URL, bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8, agentKey, token, null);
    }

    private static JSONObject getMatchOrderAPI(String agentKey, String token, String json) throws UnsupportedEncodingException {
        String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token, json);
        String api = String.format("%s%s?body=%s", Environment.domainULR, GET_MATCHED_WAGER_URL, bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8, agentKey, token, null);
    }

    public static WagerResultObj getReportResult(String agentKey, String token, String json) throws UnsupportedEncodingException {
        JSONObject jsonObject = getOrderAPI(agentKey, token, json);
        if (Objects.nonNull(jsonObject)) {
            int isSuccess = jsonObject.getInt("status");
            JSONObject resultObj = jsonObject.getJSONObject("data");
            return new WagerResultObj.Builder()
                    .result(isSuccess)
                    .orderList(getListWager(resultObj))
                    .build();
        }
        return null;

    }

  /*  public static WagerResultObj getWagerResultByStatus(String agentKey, String token, String json,String status) throws UnsupportedEncodingException {
        JSONObject jsonObject = getOrderAPI(agentKey,token,json);
        if(Objects.nonNull(jsonObject)){
            int isSuccess = jsonObject.getInt("status");
            JSONObject resultObj = jsonObject.getJSONObject("data");
            return new WagerResultObj.Builder()
                    .result(isSuccess)
                    .orderList(getListWagerByStatus(resultObj,status))
                    .build();
        }
        return null;
    }*/

    public static WagerResultObj getMatchedWagerResultByStatus(String agentKey, String token, String json, String status) throws UnsupportedEncodingException {
        JSONObject jsonObject = getMatchOrderAPI(agentKey, token, json);
        if (Objects.nonNull(jsonObject)) {
            int isSuccess = jsonObject.getInt("status");
            JSONObject resultObj = jsonObject.getJSONObject("data");
            return new WagerResultObj.Builder()
                    .result(isSuccess)
                    .orderList(getListWagerByStatus(resultObj, status))
                    .build();
        }
        return null;
    }

    private static List<WagerObj> getListWagerByStatus(JSONObject wagerListObj, String status) {
        JSONObject jsonObj;
        if (!status.equalsIgnoreCase("All")) {
            jsonObj = getWagerObjectbyStatus(wagerListObj, status);
            return getListWager(jsonObj);
        }
        List<WagerObj> lstWagers = new ArrayList<>();
        lstWagers = addListinExistList(lstWagers, wagerListObj, "MATCHED");
        lstWagers = addListinExistList(lstWagers, wagerListObj, "UNMATCHED");
        lstWagers = addListinExistList(lstWagers, wagerListObj, "CANCELLED");
        lstWagers = addListinExistList(lstWagers, wagerListObj, "LAPSED");
        return lstWagers;
    }

    private static List<WagerObj> addListinExistList(List<WagerObj> existList, JSONObject wagerListObj, String status) {
        List<WagerObj> lstWagers = new ArrayList<>();
        JSONObject jsonObj = getWagerObjectbyStatus(wagerListObj, status);
        if (Objects.nonNull(jsonObj)) {
            lstWagers = getListWager(jsonObj);
            if (Objects.nonNull(lstWagers))
                existList.addAll(lstWagers);
        }
        return existList;

    }

    /**
     * Get the list wager result
     *
     * @param wagerListObj
     * @return
     */
    private static JSONObject getWagerObjectbyStatus(JSONObject wagerListObj, String status) {
        //   JSONArray currentOrderArr = wagerListObj.getJSONArray("records");
        switch (status) {
            case "MATCHED":
                return wagerListObj.getJSONObject("matched");
            case "UNMATCHED":
                return wagerListObj.getJSONObject("unmatched");
            case "CANCELLED":
                return wagerListObj.getJSONObject("cancelled");
            default:
                return wagerListObj.getJSONObject("lapsed");
        }
    }

    /**
     * Get the list wager result
     *
     * @param wagerListObj
     * @return
     */
    private static List<WagerObj> getListWager(JSONObject wagerListObj) {
        JSONArray currentOrderArr = wagerListObj.getJSONArray("records");
        List<WagerObj> lst = new ArrayList<>();
        int totalItem = currentOrderArr.length();
        if (totalItem == 0) {
            System.out.println("There is no data in the list");
            return null;
        }
        JSONObject wagerObj;
        for (int i = 0; i < totalItem; i++) {
            wagerObj = currentOrderArr.getJSONObject(i);
            lst.add(mapOder(wagerObj));
        }
        return lst;
    }

    /**
     * Map api object to Wager Object
     *
     * @param jsonObject
     * @return
     */
    private static WagerObj mapOder(JSONObject jsonObject) {
        return new WagerObj.Builder()
                .userId(jsonObject.getString("userId"))
                .orderId(jsonObject.getInt("orderId"))
                .sportName(jsonObject.getString("sportName"))
                .competitionName(jsonObject.getString("competitionName"))
                .eventName(jsonObject.getString("eventName"))
                .marketName(jsonObject.getString("marketName"))
                .side(jsonObject.getString("side"))
                .stake(jsonObject.getDouble("stake"))
                .odds(jsonObject.getDouble("odds"))
                .pnl(jsonObject.getDouble("pnl"))
                .status(jsonObject.getString("status"))
                .betOutcome(jsonObject.getString("betOutcome"))
                .placeDate(jsonObject.getString("placedDate"))
                .matchedDate(jsonObject.getString("matchedDate"))
                .settledDate(jsonObject.getString("settledDate"))
                .selectionName(jsonObject.getString("selectionName"))
                .handicap(jsonObject.getDouble("handicap"))
                .oddsType(jsonObject.getString("oddsType"))
                .eventStartDate(jsonObject.getString("eventStartDate"))
                .build();
    }
}
