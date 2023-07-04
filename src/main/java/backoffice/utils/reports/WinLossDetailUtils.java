package backoffice.utils.reports;

import backoffice.objects.bo.reports.WinLossDetail;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DoubleUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static baseTest.BaseCaseTest.backofficeUrl;

public class WinLossDetailUtils {
    public static List<String> getPortals() {
        List<String> lstUsers = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/report/all-user-po", backofficeUrl);
        JSONArray jsonArray = WSUtils.getGETJSONArrayResponse(api, null, Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstUsers.add(jsonObject.getString("userCode"));
            }
        }
        Collections.sort(lstUsers);
        return lstUsers;
    }

    /**
     * Get Win Lost Report info
     *
     * @param fromDate yyyy-MM-dd
     * @param toDate   yyyy-MM-dd
     * @param product  EXCHANGE | EZUGI | EXCHANGE,EZUGI
     */
    public static WinLossDetail getWinLossReport(String fromDate, String toDate, String product) {
        product = product.equals("All") ? "EXCHANGE,EZUGI,DIGIENT,FAIR_FANCY,SUPER_SPADE,EXCH_GAMES,FOLLOWBET,VERONICA,WICKET_FANCY,WICKET_BOOKMAKER" : product;
        String filter = String.format("product=%s,&userId=0&fromDate=%s&toDate=%s&isBack=-1&loginUserId=0", product, fromDate, toDate);
        String api = String.format("%s/system-manager/web/report/win-loss-detail?%s", backofficeUrl, filter);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (jsonObject.has("report")) {
            JSONArray jsonArray = jsonObject.getJSONArray("report");
            if (jsonArray.length() == 0) {
                return new WinLossDetail.Builder().build();
            }
            JSONObject report = jsonArray.getJSONObject(jsonArray.length() - 1);
            return new WinLossDetail.Builder()
                    .totalWager(report.getInt("totalWager"))
                    .localTurnover(DoubleUtils.roundUpWithTwoPlaces(report.getDouble("localTurnover")))
                    .turnOver(DoubleUtils.roundUpWithTwoPlaces(report.getDouble("turnover")))
                    .build();
        }
        return new WinLossDetail.Builder().build();
    }

    /**
     * Get all portals summary data: Username, level, currency, total wager, turnover[L], turnover[f]
     *
     * @param fromDate yyyy-MM-dd
     * @param toDate   yyyy-MM-dd
     * @param product  EXCHANGE | EZUGI | EXCHANGE,EZUGI
     */
    public static List<ArrayList<String>> getPortalsSummaryData(String fromDate, String toDate, String product) {
        List<ArrayList<String>> lstResult = new ArrayList<>();
        product = product.equals("All") ? "EXCHANGE,EZUGI,DIGIENT,FAIR_FANCY,SUPER_SPADE,EXCH_GAMES,FOLLOWBET" : product;
        String filter = String.format("product=%s,&userId=0&fromDate=%s&toDate=%s&isBack=-1&loginUserId=0", product, fromDate, toDate);
        String api = String.format("%s/system-manager/web/report/win-loss-detail?%s", backofficeUrl, filter);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (jsonObject.has("report")) {
            JSONArray jsonArray = jsonObject.getJSONArray("report");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userObj = jsonArray.getJSONObject(i);
                lstResult.add(i, new ArrayList<>(Arrays.asList(
                        Integer.toString(userObj.getInt("userId")),
                        (userObj.get("userCode") == JSONObject.NULL) ? "null" : userObj.getString("userCode"),
                        (userObj.get("nickname") == JSONObject.NULL) ? "null" : userObj.getString("nickname"),
                        (userObj.get("currency") == JSONObject.NULL) ? "null" : userObj.getString("currency"),
                        translateLevel((userObj.get("levelName") == JSONObject.NULL) ? "null" : userObj.getString("levelName")),
                        Integer.toString(userObj.getInt("totalWager")),
                        Double.toString(userObj.getDouble("localTurnover")),
                        Double.toString(userObj.getDouble("turnover")))));
            }
        }
        return lstResult;
    }

    /**
     * Get detail data of all level of a portal: Win/los(L),Win/los(F), Tax/Com[L],Tax/Com[F],Total [L], Total[F]
     *
     * @param fromDate yyyy-MM-dd
     * @param toDate   yyyy-MM-dd
     * @param product  EXCHANGE | EZUGI | EXCHANGE,EZUGI
     */
    public static List<ArrayList<String>> getPortalsLevelDetailData(String fromDate, String toDate, String product, String userId) {
        List<ArrayList<String>> lstResult = new ArrayList<>();
        product = product.equals("All") ? "EXCHANGE,EZUGI,DIGIENT,FAIR_FANCY,SUPER_SPADE,EXCH_GAMES,FOLLOWBET" : product;
        String filter = String.format("product=%s,&userId=0&fromDate=%s&toDate=%s&isBack=-1&loginUserId=0", product, fromDate, toDate);
        String api = String.format("%s/system-manager/web/report/win-loss-detail?%s", backofficeUrl, filter);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (jsonObject.has("report")) {
            JSONArray jsonArray = jsonObject.getJSONArray("report");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userObj = jsonArray.getJSONObject(i);
                if (Integer.toString(userObj.getInt("userId")).equals(userId)) {
                    JSONArray childArray = userObj.getJSONArray("details");
                    for (int j = 0; j < childArray.length(); j++) {
                        JSONObject childObj = childArray.getJSONObject(j);
                        lstResult.add(i, new ArrayList<>(Arrays.asList(
                                translateLevel(userObj.getString("level")),
                                String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(childObj.getDouble("localPnl"))),
                                String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(childObj.getDouble("pnl"))),
                                String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(childObj.getDouble("localGrossComm"))),
                                String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(childObj.getDouble("grossComm"))),
                                String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(childObj.getDouble("localTotal"))),
                                String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(childObj.getDouble("total"))))));
                    }
                }
            }
        }
        return lstResult;
    }

    private static String translateLevel(String levelCode) {
        String level;
        switch (levelCode) {
            case "PO":
                level = "Portal";
                break;
            case "CO":
                level = "Company";
                break;
            case "PART":
                level = "Partner";
                break;
            case "CORP":
                level = "Corporator";
                break;
            case "SMA":
                level = "Senior Master Agent";
                break;
            case "MA":
                level = "Master Agent";
                break;
            case "AG":
                level = "Agent";
                break;
            case "PL":
                level = "Member";
                break;
            default:
                level = null;
                break;
        }
        return level;
    }

}
