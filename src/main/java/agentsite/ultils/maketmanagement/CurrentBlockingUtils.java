package agentsite.ultils.maketmanagement;

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

import static baseTest.BaseCaseMerito.domainURL;
import static baseTest.BaseCaseMerito.environment;

public class CurrentBlockingUtils {

    public static List<AccountInfo> getCurrentBlockingUser(String eventID) {
        List<AccountInfo> lstAccount = new ArrayList<AccountInfo>();
        String api = String.format("%s/agent-blocking-management/event/sat/current-user-blocking.json?eventId=%s",
                domainURL,eventID);
        JSONArray jsonArray =  WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstAccount.add(new AccountInfo.Builder()
                        .userID(Integer.toString(jsonObject.getInt("userId")))
                        .userCode(jsonObject.getString("userCode"))
                        .level(jsonObject.getString("levelName"))
                        .build());
            }
            return lstAccount;
        }
        return null;
    }

    private static JSONArray getListEventJson(String userId, String sportId, String time ){
        //time: TODAY, sportid = 4, userId = 73891
        String api = String.format("%s/agent-blocking-management/event/sat/listEvent.json?isBlck=0&sportId=%s&userId=%s&time=%s&currentPage=1&numOfRows=50&filter=&_=%s",
                domainURL,sportId,userId,time, DateUtils.getMilliSeconds());
      return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);

    }
    public static String getBlockedUserCount(String userId, String sportId, String time, String eventName) {

        JSONArray jsonArray = getListEventJson(userId,sportId,time);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("name").equals(eventName)){
                    return Integer.toString(jsonObject.getInt("blockedUserCount"));
                }
            }
        }
        return null;
    }
    public static String getEventID(String userId, String sportId, String time, String eventName) {
        JSONArray jsonArray = getListEventJson(userId,sportId,time);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("name").equals(eventName)){
                    return Long.toString(jsonObject.getLong("id"));
                }
            }
        }
        return null;
    }
    public static String getFirstEventName(String userId, String sportId, String time) {
        JSONArray jsonArray = getListEventJson(userId,sportId,time);
        if (Objects.nonNull(jsonArray)) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return jsonObject.getString("name");
        }
        return null;
    }

}
