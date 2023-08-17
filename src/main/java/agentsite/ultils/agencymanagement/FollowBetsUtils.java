package agentsite.ultils.agencymanagement;

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

public class FollowBetsUtils {
    static JSONArray getList() {
        List<ArrayList<String>> lstGroup = new ArrayList<>();
        String api = String.format("%s/agent-bet-setting/group/list", domainURL);
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_FORM_URLENCODED, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        return jsonArray;
    }
    public static ArrayList<String> getGroupId() {
        ArrayList<String> lstGroupId = new ArrayList<>();
        JSONArray jsonArray = getList();
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstGroupId.add(i, String.valueOf(jsonObject.getInt("groupId")));
            }
        }
        return lstGroupId;
    }
    public static ArrayList<String> getLstGroupName() {
        ArrayList<String> lstGroupName = new ArrayList<>();
        JSONArray jsonArray = getList();
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstGroupName.add(i, String.valueOf(jsonObject.getString("groupName")));
            }
        }
        return lstGroupName;
    }
    public static List<ArrayList<String>> getListUserName(String groupId) {
        List<ArrayList<String>> lstUserName = new ArrayList<>();
        String username = null;
        String api = String.format("%s/agent-bet-setting/group/list-users", domainURL);
        String jsn = "groupId="+groupId;
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_FORM_URLENCODED, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstUserName.add(i, new ArrayList<>(
                        Arrays.asList(
                                jsonObject.getString("userCode"),
                                jsonObject.getString("level")
                        )
                ));
            }
        }
        return lstUserName;
    }
    public static String getUsernameByLevel(String level){
        ArrayList<String> lstGroupId = getGroupId();
        String username = null;
        for (int i = 0; i < lstGroupId.size();i++){
            List<ArrayList<String>> lstUserName = getListUserName(lstGroupId.get(i));
            for (int j = 0; j < lstUserName.size();j++){
                if (lstUserName.get(j).get(1).equals(level)){
                    username = lstUserName.get(j).get(0);
                    break;
                }
            }
        }
            return username;
    }
}
