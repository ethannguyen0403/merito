package backoffice.utils.tools;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static baseTest.BaseCaseTest.backofficeUrl;

public class BetFairAccountInfoUtils {
    public static List<ArrayList<String>> getBetFairAccount() {
        List<ArrayList<String>> lstAccount = new ArrayList<>();
        String api = String.format("%s/system-manager/web/betfair-account.sv",backofficeUrl);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        ArrayList<String> accountInfo = new ArrayList<>();
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(Objects.nonNull(jsonObject)){
                    accountInfo.add(Integer.toString(jsonObject.getInt("accountId")));
                    accountInfo.add(jsonObject.getString("accountName"));
                }
                JSONObject balancesObj = jsonObject.getJSONObject("balances");
                if(Objects.nonNull(balancesObj)){
                    JSONObject exchangeObj = balancesObj.getJSONObject("EXCHANGE");
                    accountInfo.add(Double.toString(getDouble(exchangeObj,"availableBalance")));
                    accountInfo.add(Double.toString(getDouble(exchangeObj,"exposure")));
                    accountInfo.add(Double.toString(getDouble(exchangeObj,"exposureLimit")));

                    JSONObject exchangeGameObj = balancesObj.getJSONObject("EXCH_GAMES");
                    accountInfo.add(Double.toString(getDouble(exchangeGameObj,"availableBalance")));
                    accountInfo.add(Double.toString(getDouble(exchangeGameObj,"exposure")));
                    accountInfo.add(Double.toString(getDouble(exchangeGameObj,"exposureLimit")));
                }
                lstAccount.add(i,accountInfo);
            }
        }
        return lstAccount;
    }
    private static double getDouble(JSONObject obj, String objectProperty){
        try {
            return obj.getDouble(objectProperty);
        }catch (JSONException ex){
            return 0;
        }
    }
}



