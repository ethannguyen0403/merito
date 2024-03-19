package agentsite.ultils.account;

import agentsite.objects.agent.account.AccountInfo;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class ProfileUtils {
    public static AccountInfo getProfile() {
        String api = String.format("%s/agent-services/users/profile?%s", domainURL, DateUtils.getMilliSeconds());
        //  JSONObject jsonObject = WSUtils.getGETJSONResponse(api, Configs.HEADER_JSON_CHARSET,Configs.HEADER_JSON);//, DriverManager.getDriver().getCookies().toString(),true);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);

        if (Objects.nonNull(jsonObject)) {

            if (jsonObject.has("profile")) {
                JSONObject jsnProfile = jsonObject.getJSONObject("profile");
                return new AccountInfo.Builder()
                        .userID(Integer.toString(jsnProfile.getInt("userId")))
                        .userCode(jsnProfile.getString("userCode"))
                        .loginID(jsnProfile.getString("loginId"))
                        .status(jsnProfile.getString("status"))
                        .level(jsnProfile.getString("levelName"))
                        .currencyCode(jsnProfile.getString("currencyCode"))
                        .build();
            }

        } else {
            System.err.println("ERROR: jsonObject is null at getProfile");
        }
        return new AccountInfo.Builder().build();
    }

    private static JSONObject balanceProductJson() {
        String api = String.format("%s/agent-services/balance-product?product=EXCHANGE&%s", domainURL, DateUtils.getMilliSeconds());
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    private static JSONObject getAppConfig() {
        String api = String.format("%s/agent-services/app/config", domainURL, DateUtils.getMilliSeconds());
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static String getAppName() {
        JSONObject jsonObject = getAppConfig();
        return jsonObject.getString("name");
    }

    public static int getNewUI() {
        JSONObject jsonObject = getAppConfig();
        return jsonObject.getInt("newui");
    }

    public static List<String> getAccountBalanceInfo() {
        JSONObject balanceObj = balanceProductJson();
        ArrayList<String> lstArray = new ArrayList<>();
        if (Objects.nonNull(balanceObj)) {
            if (balanceObj.has("ACCOUNT_EXCHANGE")) {
                JSONArray accountExch = balanceObj.getJSONArray("ACCOUNT_EXCHANGE");
                for (int i = 0; i < accountExch.length(); i++) {
                    JSONObject item = accountExch.getJSONObject(i);
                    lstArray.add(Double.toString(item.getDouble("availableBalance")));
                    lstArray.add(Double.toString(item.getDouble("cashBalance")));
                    //  lstArray.add(Double.toString(item.getJSONObject("createUsed")));
                    lstArray.add(Double.toString(item.getDouble("creditLimit")));
                    lstArray.add(Double.toString(item.getDouble("creditRefer")));
                    lstArray.add(Double.toString(item.getDouble("currency")));
                    lstArray.add(Double.toString(item.getDouble("outstanding")));
                    lstArray.add(Double.toString(item.getDouble("todayPnl")));
                    lstArray.add(Double.toString(item.getDouble("totalBalance")));
                    lstArray.add(Double.toString(item.getDouble("totalDeposit")));
                    lstArray.add(Double.toString(item.getDouble("totalOutstanding")));
                    lstArray.add(Double.toString(item.getDouble("ycashBalance")));
                    lstArray.add(Double.toString(item.getDouble("yesterdayPnl")));
                    lstArray.add(Double.toString(item.getDouble("ytotalBalance")));
                }
            }
        }
        return lstArray;
    }

    public static List<ArrayList<String>> getDownlineBalanceInfo() {
        List<ArrayList<String>> lstAccountBalanceInfo = new ArrayList<>();
        JSONObject balanceObj = balanceProductJson();
        if (Objects.nonNull(balanceObj)) {
            if (balanceObj.has("DOWNLINE_EXCHANGE")) {
                JSONArray accountExch = balanceObj.getJSONArray("DOWNLINE_EXCHANGE");
                for (int i = 0; i < accountExch.length(); i++) {
                    JSONObject item = accountExch.getJSONObject(i);
                    ArrayList<String> lstArray = new ArrayList<>();
                    lstArray.add(item.getString("level"));
                    lstArray.add(Double.toString(item.getDouble("availableBalance")));
                    lstArray.add(Double.toString(item.getDouble("creditLimit")));
                    lstArray.add(Integer.toString(item.getInt("active")));
                    lstArray.add(Integer.toString(item.getInt("closed")));
                    lstArray.add(Integer.toString(item.getInt("suspended")));
                    lstArray.add(Integer.toString(item.getInt("inactive")));
                    lstArray.add(Integer.toString(item.getInt("block")));
                    lstAccountBalanceInfo.add(i, lstArray);
                }
            }
        }
        System.err.println("ERROR: jsonObject is null at balanceProductJson");
        return lstAccountBalanceInfo;
    }

    public static String convertDownlineByBrand(String level, String brand) {
        if (!brand.equalsIgnoreCase("satsport"))
            return level;
        else {
            return changeLevel(level);
        }
    }

    private static String changeLevel(String level) {
        switch (level) {
            case "CORP":
                return "SAD";
            case "SMA":
                return "AD";
            case "MA":
                return "SMA";
            case "AG":
                return "MA";
            default:
                return level;
        }
    }


}
