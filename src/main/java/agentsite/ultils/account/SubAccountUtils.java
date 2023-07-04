package agentsite.ultils.account;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class SubAccountUtils {
    private static JSONObject getAllSubUser() {
        String api = String.format("%s/agent-services/subuser/getAllSubUser?%s", domainURL, DateUtils.getMilliSeconds());
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static JSONArray getListSubUser() {
        JSONObject job = getAllSubUser();
        return job.getJSONArray("listSubUser");
    }

    public static JSONArray getListPermission() {
        JSONObject job = getAllSubUser();
        return job.getJSONArray("permission");
    }

    private static int getUserIndex(String userName) {
        JSONArray arrListSubUser = getListSubUser();
        if (Objects.nonNull(arrListSubUser)) {
            for (int i = 0; i < arrListSubUser.length(); i++) {
                JSONObject userObj = arrListSubUser.getJSONObject(i);
                if (userObj.getString("userCode").contains(userName)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static JSONObject getPermissionOfAccount(String userName) {
        JSONArray arrPermission = getListPermission();
        int index = getUserIndex(userName);
        return arrPermission.getJSONObject(index);

    }


}
