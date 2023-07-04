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

import static baseTest.BaseCaseTest.backofficeUrl;


public class LiquidityThresholdLogUltils {
    public static List<ArrayList<String>> getLog(String username) {
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/sport-manager/web/liquidity-threshold/log-list?userName=%s&page=1&pagerows=20", backofficeUrl, username);
        JSONArray jArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jArray)) {
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                if (Objects.nonNull(jObject)) {
                    lst.add(new ArrayList<String>(
                            Arrays.asList(
                                    Integer.toString(i + 1),
                                    jObject.getString("userCode"),
                                    jObject.getString("userName"),
                                    jObject.getString("userBrand"),
                                    jObject.getString("level"),
                                    jObject.getBoolean("skip") ? "YES" : "NO",
                                    jObject.getString("real_status"),
                                    jObject.getString("line"),
                                    jObject.getString("lastUpdateBy"),
                                    jObject.getString("lastUpdateDate"), "View")));
                }
            }
        }
        return lst;
    }

    public static List<ArrayList<String>> getLiquidityHistory(String username) {
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/sport-manager/web/liquidity-threshold/getLiquidityHistory?userName=%s&page=1&pagerows=20", backofficeUrl, username);
        JSONArray jArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jArray)) {
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                if (Objects.nonNull(jObject)) {
                    lst.add(new ArrayList<String>(
                            Arrays.asList(
                                    Integer.toString(i + 1),
                                    jObject.getString("action"),
                                    jObject.getString("lastUpdateDate"),
                                    jObject.getString("lastUpdateBy"))));
                }
            }
        }
        return lst;
    }
}



