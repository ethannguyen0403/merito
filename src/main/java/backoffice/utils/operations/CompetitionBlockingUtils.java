package backoffice.utils.operations;

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

public class CompetitionBlockingUtils {
    public static List<ArrayList<String>> getSportList() {
        List<ArrayList<String>> lstSport = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/competition/sport-list",backofficeUrl);
        JSONArray jsnArray  = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsnArray)) {
        for (int i = 0; i < jsnArray.length(); i++) {
            JSONObject jsonObject = jsnArray.getJSONObject(i);
            if (Objects.nonNull(jsonObject)) {
                    lstSport.add(i, new ArrayList<String>(
                    Arrays.asList(
                            Integer.toString(jsonObject.getInt("sportId")),
                            jsonObject.getString("sportName"))));
                }
            }
        }
        return lstSport;
    }

    public static String getSportID(List<ArrayList<String>> lstSport,String sportName){
        for(int i = 0; i<lstSport.size(); i++){
            if(lstSport.get(i).get(1).equals(sportName)){
                return lstSport.get(i).get(0);
            }
        }
        return null;
    }
    public static List<ArrayList<String>> getCompetitions(String sportID) {
        List<ArrayList<String>> lstCompetition = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/competition/competition-list?sportId=%s",backofficeUrl,sportID);
        JSONArray jsnArray  = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsnArray)) {
            for (int i = 0; i < jsnArray.length(); i++) {
                JSONObject jsonObject = jsnArray.getJSONObject(i);
                if (Objects.nonNull(jsonObject)) {
                    lstCompetition.add(i, new ArrayList<String>(
                            Arrays.asList(
                                    Integer.toString(jsonObject.getInt("competitionId")),
                                    jsonObject.getString("competitionName"),
                                    jsonObject.getString("status"),
                                    jsonObject.getString("lastUpdateBy"),
                                    Long.toString(jsonObject.getLong("lastUpdateDate")))));
                }
            }
        }
        return lstCompetition;
    }
}
