package backoffice.utils.FraudDetection;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static baseTest.BaseCaseTest.backofficeUrl;

public class FraudDetectionUtils {
    public static List<ArrayList<String>> getListSportHasPlace(String date, String status) {
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/fraud-detection/event/list-sport-by-placeddate.json",backofficeUrl);
       String jsn = String.format("{\"placedDate\":\"%s\",\"status\":\"%s\"}",date,status);
       // WSUtils.getPOSTJSONObjectResponse(api, Configs.HEADER_FORM_URLENCODED,jsn);
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_JSON,jsn, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(!jsonArray.isEmpty()){
            for(int i =0; i<jsonArray.length(); i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                lst.add(i, new ArrayList<String>(
                        Arrays.asList(
                                Integer.toString(obj.getInt("sportId")),
                                obj.getString("sportName"))));
            }
            return lst;
        }
      return null;
    }
    public static List<ArrayList<String>> getListCompetition(String date, String sportId, String competitionId) {
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/fraud-detection/event/list-competition.json",backofficeUrl);
        String jsn = String.format("{\"matchDate\":\"%s\",\"sportId\":\"%s\",\"competitionId\":\"%s\"}",date,sportId,competitionId);
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_JSON,jsn,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(!jsonArray.isEmpty()){
            for(int i =0; i<jsonArray.length(); i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                lst.add(i, new ArrayList<String>(
                        Arrays.asList(
                                Integer.toString(obj.getInt("competitionId")),
                                obj.getString("competitionName"))));
            }
        }
        return lst;
    }

}
