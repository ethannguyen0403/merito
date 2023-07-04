package api.utils.MeritoAPI;

import api.objects.meritoAPI.Competition;
import api.objects.meritoAPI.result.CompetitionResult;
import com.paltech.constant.Configs;
import com.paltech.utils.WSUtils;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetCompetitionUtils {
    private static JSONObject getCompetitionAPIJson(String token, String eventTypeId, String inplayOnly) {
        String api = String.format("%sbetting-api/sport/competition/list", Environment.domainULR);
        String jsn = String.format("{\"eventTypeIds\":[\"%s\"],\"inPlayOnly\":\"%s\"}", eventTypeId, inplayOnly);
        return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON, jsn, "", Configs.HEADER_JSON, "token", token);


    }

    public static CompetitionResult getCompetitionAPI(String token, String id, String isInPlay) {
        JSONObject jsonObject = getCompetitionAPIJson(token, id, isInPlay);
        List<Competition> lst = new ArrayList<>();
        String competitionName = "";
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONArray resultArr = jsonObject.getJSONArray("result");
            JSONObject competitionObjResult;
            JSONObject competitionObj;
            for (int i = 0; i < resultArr.length(); i++) {
                competitionObjResult = resultArr.getJSONObject(i);
                // System.out.println("Get competiton index:"+i);
                competitionObj = competitionObjResult.getJSONObject("competition");
                if (competitionObj.has("name"))
                    competitionName = competitionObj.getString("name");
                Competition competition = new Competition.Builder()
                        .id(competitionObj.getInt("id"))
                        .name(competitionName)
                        .marketCount(competitionObjResult.getInt("marketCount"))
                        .marketRegion(competitionObjResult.getString("competitionRegion"))
                        .build();
                lst.add(competition);
            }
            return new CompetitionResult.Builder()
                    .isSuccess(isSuccess)
                    .competitionList(lst)
                    .build();
        }
        return null;
    }

}
