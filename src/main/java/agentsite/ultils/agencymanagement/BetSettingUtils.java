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

import static baseTest.BaseCaseMerito.domainURL;
import static baseTest.BaseCaseMerito.environment;

public class BetSettingUtils {
    public static List<BetSetting> getEventList(String product, String userID, String sports) {
        List<BetSetting> lstBetSetting = new ArrayList<BetSetting>();
        List<String> sportList = new ArrayList<String>(Arrays.asList(sports.split(",")));
        String api = String.format("%s/agent-services-new/betSetting/getBetSettingList",domainURL);
        String jsn = String.format("{\"currentPage\":1,\"numOfRows\":20,\"products\":\"%s\",\"filter\":{\"levelSearch\":\"ALL\",\"userId\":%s,\"userName\":\"\",\"status\":\"\",\"sports\":\"%s\"}}",
                product,userID,sports);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api,Configs.HEADER_JSON,jsn,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
//        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("contentList");
                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject sportObj = jsonArray.getJSONObject(i);
                    JSONObject settingObj = sportObj.getJSONObject("sports");
                    for(int j =0 ; j < sportList.size(); j++) {
                        lstBetSetting.add(getSettingBySport(settingObj, sportList.get(j)));
                    }
            }
        }
        return lstBetSetting;
    }

    private static BetSetting getSettingBySport(  JSONObject settingObj, String sport){
        return new BetSetting.Builder()
                .level(settingObj.getString("level"))
                .currency(settingObj.getString("level"))
                .minBet(settingObj.getDouble("minBet"))
                .maxBet(settingObj.getDouble("maxBet"))
                .maxBetPerMatch(settingObj.getDouble("maxBetPerMatch"))
                .maxWinPerMatch(settingObj.getDouble("maxWinPerMatch"))
                .userCode(settingObj.getString("userCode"))
                .userID(settingObj.getString("userId"))
                .build();

    }
}
