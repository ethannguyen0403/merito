package membersite.utils.exchangegame;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class GetDataUtils {

    private static JSONObject getEGData() {
        String api = String.format("%s/member-service/user/egdata", domainURL);
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static String getMinBet(String gameName, String betType) {
        JSONObject jsonObject = getEGData();
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("betSettings")) {
                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
                String betTypeName = betType.equals("BACK") ? "backMinBet" : "layMinBet";
                for (int i = 0; i < arraySports.length(); i++) {
                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
                    if (jsonSportSetting.getString("sportGroup").equals(gameName.toUpperCase())) {
                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
                    }
                }
            }
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return "-1";
    }

    public static String getMaxBet(String gameName, String betType) {
        JSONObject jsonObject = getEGData();
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("betSettings")) {
                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
                String betTypeName = betType.equals("BACK") ? "backMaxBet" : "layMaxBet";
                for (int i = 0; i < arraySports.length(); i++) {
                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
                    if (jsonSportSetting.getString("sportGroup").equals(gameName)) {
                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
                    }
                }
            }
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return "-1";
    }

}
