package membersite.utils.exchangegame;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import membersite.pages.all.tabexchange.SportPage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

import static baseTest.BaseCaseMerito.domainURL;
import static baseTest.BaseCaseMerito.environment;

public class GetDataUtils {

    private static JSONObject getEGData(){
        String api = String.format("%s/member-service/user/egdata", domainURL);
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    public static String getMinBet(String gameName, SportPage.BetType betType) {
        JSONObject jsonObject = getEGData();
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("betSettings")) {
                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
                String betTypeName = betType.equals(SportPage.BetType.BACK) ? "backMinBet" : "layMinBet";
                for (int i=0; i < arraySports.length(); i++) {
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
    public static String getMaxBet(SportPage.Sports sportName, SportPage.BetType betType) {
        JSONObject jsonObject = getEGData();
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("betSettings")) {
                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
                String betTypeName = betType.equals(SportPage.BetType.BACK) ? "backMaxBet" : "layMaxBet";
                for (int i=0; i < arraySports.length(); i++) {
                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
                    if (jsonSportSetting.getString("sportGroup").equals(sportName.toString())) {
                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
                    }
                }
            }
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return "-1";
    }

}
