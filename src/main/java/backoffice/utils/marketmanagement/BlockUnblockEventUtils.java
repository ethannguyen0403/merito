package backoffice.utils.marketmanagement;


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

public class BlockUnblockEventUtils {
    public static List<ArrayList<String>> getDownlineList(String userId) {
        List<ArrayList<String>> lstDownline = new ArrayList<>();
        String api = String.format("%s/system-manager/block-unlock-event/child-po.sv?userId=%s&levelPT=SMA",backofficeUrl, userId);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonArrayObject = jsonArray.getJSONObject(i);
                        lstDownline.add(i, new ArrayList<String>(
                        Arrays.asList(
                                Integer.toString(jsonArrayObject.getInt("isFav")),
                                jsonArrayObject.getString("levelName"),
                                jsonArrayObject.getString("loginId"),
                                Integer.toString(jsonArrayObject.getInt("parentId")),
                                jsonArrayObject.getString("userCode"),
                                Integer.toString(jsonArrayObject.getInt("userId"))
                        )));
                    }
                }
        return lstDownline;
    }
    public static List<ArrayList<String>> getLeagueAndEventList(String userId, String sportId) {
        List<ArrayList<String>> lstReturn = new ArrayList<>();
        String api = String.format("%s/system-manager/block-unlock-event/events.sv", backofficeUrl);
        String jsn = String.format("{\n" +
                        "    \"userId\": %s,\n" +
                        "    \"sportId\": \"%s\",\n" +
                        "    \"filterPeriod\": \"TODAY\",\n" +
                        "    \"levelControlBlocking\": \"SMA\",\n" +
                        "    \"pageSize\": 30,\n" +
                        "    \"pageNo\": 1,\n" +
                        "    \"eventNameFilter\": \"\" \n" +
                        "  }\n"
                , userId, sportId);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, jsn, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON  );
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("data")) {
                JSONArray jsnArray = jsonObject.getJSONArray("data");
                if (Objects.nonNull(jsnArray)) {
                    for (int i = 0; i < jsnArray.length(); i++) {
                        JSONObject jsonArrayObject = jsnArray.getJSONObject(i);
                        lstReturn.add(i, new ArrayList<String>(
                                Arrays.asList(
                                        Integer.toString(jsonArrayObject.getInt("id")),
                                        jsonArrayObject.getString("name"),
                                        jsonArrayObject.getString("competitionName"),
                                        Integer.toString(jsonArrayObject.getInt("competitionId"))
                                )));
                    }
                }
            }
        }
        return lstReturn;
    }

}

