package backoffice.utils.tools;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseMerito.environment;

public class PlayerInfoUtils {
    public static List<String> getInfo(String loginID) {
        List<String> lst = new ArrayList<>();
        String api = String.format("%s/system-manager/web/view-info.sv?userId=%s",environment.getBackofficeURL(),loginID);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(Objects.nonNull(jsonObject)){
            JSONObject infoObject =  jsonObject.getJSONObject("info");
            lst.add(infoObject.getString("loginId"));
            lst.add(infoObject.getString("userCode"));
            lst.add(Boolean.toString(infoObject.getBoolean("loginViaEmail")));
            lst.add(infoObject.getString("ezugiUserCode"));
            lst.add(infoObject.getString("ladderUserCode"));
            lst.add(infoObject.getString("currency"));
            lst.add(Double.toString(infoObject.getDouble("balance")));
            lst.add(Double.toString(infoObject.getDouble("exposure")));
            lst.add(Double.toString(infoObject.getDouble("retainTax")));
            lst.add(infoObject.getString("status"));
            lst.add(infoObject.getString("brand"));
            lst.add(infoObject.getString("brandType"));
            lst.add(infoObject.getString("uplineUserCode"));
            lst.add(infoObject.getString("uplineLoginId"));
            lst.add(infoObject.getString("userType"));
            lst.add("Show");// for add upline status
            lst.add(infoObject.getString("userGroup"));
            lst.add(Boolean.toString(infoObject.getBoolean("shadowPlayer")));
            lst.add(Boolean.toString(infoObject.getBoolean("ptPlayer")));
            lst.add(Boolean.toString(infoObject.getBoolean("cashout")));
            lst.add(Boolean.toString(infoObject.getBoolean("followBet")));
            lst.add(Boolean.toString(infoObject.getBoolean("oneclickStatus")));
            lst.add(Integer.toString(infoObject.getInt("defaultStake")));

            lst.add(Integer.toString(infoObject.getInt("betDelay")));
            lst.add(Boolean.toString(infoObject.getBoolean("confirmPlacebet")));
            lst.add(Boolean.toString(infoObject.getBoolean("threshold")));
            lst.add(Integer.toString(infoObject.getInt("autoRefreshOdds")));
            lst.add("N/A");
            lst.add(infoObject.getString("lastLoginIP"));
            lst.add(Long.toString(infoObject.getLong("lastLoginDate")));
            lst.add(infoObject.getString("products"));
            lst.add(Long.toString(infoObject.getLong("createdDate")));
        }
        return lst;
    }
    public static List<ArrayList<String>> getActivities(String loginID) {
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/system-manager/web/view-info.sv?userId=%s",environment.getBackofficeURL(), loginID);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONArray activitiesArray = jsonObject.getJSONArray("activities");
            for(int i = 0; i<activitiesArray.length(); i++){
                JSONObject activitiesInfo = activitiesArray.getJSONObject(i);
                if(Objects.nonNull(activitiesInfo))
                    lst.add(i, new ArrayList<String>(
                            Arrays.asList(Long.toString(jsonObject.getLong("logDate")),jsonObject.getString("ipAddress"),jsonObject.getString("userAgent"),jsonObject.getString("browserType"))));
            }
        }
        return lst;
    }

    private static String getUserID(String loginID){
        String api = String.format("%s/system-manager/web/view-info.sv?userId=%s",environment.getBackofficeURL(),loginID);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString());
        if(Objects.nonNull(jsonObject)) {
            JSONObject infoObject = jsonObject.getJSONObject("info");
            return Integer.toString(infoObject.getInt("userId"));
        }
        return null;
    }
    public static List<ArrayList<String>> getUpline(String loginID) {
        String userID = getUserID(loginID);
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/system-manager/web/user-status-product?userId=%s",environment.getBackofficeURL(), userID);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);

        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject rowObject = jsonArray.getJSONObject(i);
            ArrayList<String> levelInfo = new ArrayList<>();
            if(Objects.nonNull(rowObject)) {
                levelInfo = new ArrayList<String>(
                        Arrays.asList(Integer.toString(rowObject.getInt("userId")),
                                rowObject.getString("level"),
                                rowObject.getString("userStatus")));
              /*  lst.add(i, new ArrayList<String>(
                                        Arrays.asList(Integer.toString(rowObject.getInt("userId")),
                                                rowObject.getString("level"),
                                rowObject.getString("userStatus"))));*/
                // add product status sort by : Exchange, Live Dealer European, Lottery & Slot, Live Dealer Asian, Exchange Game, Veronica Canio
                JSONArray productArray = rowObject.getJSONArray("statusProducts");
                if (Objects.nonNull(productArray)) {
                    for (int j = 0; j < productArray.length(); j++) {
                        JSONObject productObject = productArray.getJSONObject(j);
                        String product = productObject.getString("productName");
                        for(int b = 0; b<=j; b++){
                            if(product.equals("Exchange")){
                                levelInfo.add(productObject.getString("status"));
                                break;
                            }
                            if(product.equals("Live Dealer European")){
                                levelInfo.add(productObject.getString("status"));
                                break;
                            }

                            if(product.equals("Lottery & Slots")) {
                                levelInfo.add(productObject.getString("status"));
                                break;
                            }
                            if(product.equals("Live Dealer Asian")) {
                                levelInfo.add(productObject.getString("status"));
                                break;
                            }
                            if(product.equals("Exchange Games")) {
                                levelInfo.add(productObject.getString("status"));
                                break;
                            }
                            if(product.equals("Veronica Casino")) {
                                levelInfo.add(productObject.getString("status"));
                                break;
                            }
                        }
                    }
                }
            }

        }

        return lst;
    }
}



