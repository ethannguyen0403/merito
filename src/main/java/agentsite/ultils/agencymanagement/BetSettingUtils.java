package agentsite.ultils.agencymanagement;

import agentsite.objects.agent.BetSetting;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class BetSettingUtils {
    public static List<BetSetting> getEventList(String product, String userID, String userName, String sports) {
        List<BetSetting> lstBetSetting = new ArrayList<BetSetting>();
        List<String> sportList = new ArrayList<String>(Arrays.asList(sports.split(",")));
        String api = String.format("%s/agent-services-new/betSetting/getBetSettingList", domainURL);
        String jsn = String.format("{\"currentPage\":1,\"numOfRows\":20,\"products\":\"%s\",\"filter\":{\"levelSearch\":\"ALL\",\"userId\":%s,\"userName\":\"%s\",\"status\":\"\",\"sports\":\"%s\"}}",
                product, userID, userName, sports);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
//        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("contentList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject sportObj = jsonArray.getJSONObject(i);
                JSONObject settingObj = sportObj.getJSONObject("sports");
                for (int j = 0; j < sportList.size(); j++) {
                    lstBetSetting.add(getSettingBySport(settingObj, sportList.get(j)));
                }
            }
        }
        return lstBetSetting;
    }

    private static BetSetting getSettingBySport(JSONObject settingObj, String sport) {
        JSONObject jsonSport = settingObj.getJSONObject(sport.toUpperCase());
        return new BetSetting.Builder()
                .level(jsonSport.getString("level"))
                .currency(jsonSport.getString("currency"))
                .minBet(jsonSport.getDouble("minBet"))
                .maxBet(jsonSport.getDouble("maxBet"))
                .maxBetPerMatch(jsonSport.getDouble("maxBetPerMatch"))
                .maxWinPerMatch(jsonSport.getDouble("maxWinPerMatch"))
                .userCode(jsonSport.getString("userCode"))
                .userID(String.valueOf(jsonSport.getInt("userId")))
                .build();

    }
}
