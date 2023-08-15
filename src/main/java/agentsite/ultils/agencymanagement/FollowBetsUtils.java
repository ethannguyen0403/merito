package agentsite.ultils.agencymanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class FollowBetsUtils {
    public static String getGroupId() {
        String groupId = null;
        String api = String.format("%s/agent-bet-setting/group/list", domainURL);
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_FORM_URLENCODED, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            groupId = String.valueOf(jsonObject.getInt("groupId"));
        }
        return groupId;
    }
    public static String getUserName(String groupId,String level) {
        String username = null;
        String api = String.format("%s/agent-bet-setting/group/list-users", domainURL);
        String jsn = "groupId="+groupId;
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_FORM_URLENCODED, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("level").equals(level)){
                    username = jsonObject.getString("userCode");
                    break;
                }
            }
        }
        return username;
    }

}
