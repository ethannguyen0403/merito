package agentsite.ultils.maketmanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;
import static baseTest.BaseCaseTest.environment;

public class BlockUnblockCompetitionUtils {

    public static List<String> getCompetitionList(String sportID, String childUSerID, String competitionStatus) {
        List<String> lstCompetition = new ArrayList<String>();
        String api = String.format("%s/agent-blocking-management/event/sat/competition-list.json?sportId=%s&userId=%s",
                domainURL,sportID,childUSerID);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("status").equalsIgnoreCase(competitionStatus))
                {
                    lstCompetition.add(jsonObject.getString("competitionName"));
                }
            }
        }
        return lstCompetition;
    }
}
