package api.utils.MeritoAPI;

import api.objects.meritoAPI.BetSettingGroup;
import api.objects.meritoAPI.MemberInfo;
import com.paltech.constant.Configs;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetMemberInfoUtils {
    private static JSONObject getMembrInfoAPI(String token) {
        String api = String.format("%sbetting-api/member/memberInfo?_ts=%s", Environment.domainULR, DateUtils.getMilliSeconds());
        return WSUtils.sendGETRequestWithCookiesHasHeader(api, Configs.HEADER_JSON, "", Configs.HEADER_JSON, "token", token);
    }

    public static MemberInfo getMembrInfo(String token) {
        JSONObject jsonObject = getMembrInfoAPI(token);
        // List<MemberInfo> lst = new ArrayList<>();
        // List<BetSettingGroup> lst = new ArrayList<>();
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONObject resultObj = jsonObject.getJSONObject("result");
            JSONObject betSetting = resultObj.getJSONObject("betSetting");
            List<BetSettingGroup> lst = new ArrayList<>();
            lst.add(getBetSettingGroup(betSetting, "OTHER"));
            lst.add(getBetSettingGroup(betSetting, "FANCY"));
            lst.add(getBetSettingGroup(betSetting, "CRICKET"));
            lst.add(getBetSettingGroup(betSetting, "TENNIS"));
            lst.add(getBetSettingGroup(betSetting, "SOCCER"));
            lst.add(getBetSettingGroup(betSetting, "BASKETBALL"));
            return new MemberInfo.Builder()
                    .isSuccess(isSuccess)
                    .availableBalance(resultObj.getDouble("availableBalance"))
                    .currency(resultObj.getString("currency"))
                    .lstBetSetting(lst)
                    .build();


        }
        return null;
    }

    private static BetSettingGroup getBetSettingGroup(JSONObject jsonObject, String group) {
        JSONArray otherSetting = jsonObject.getJSONArray(group);
        if (Objects.nonNull(otherSetting)) {
            JSONObject jsonObj = otherSetting.getJSONObject(0);
            return new BetSettingGroup.Builder()
                    .minBet(jsonObj.getDouble("minBet"))
                    .maxBet(jsonObj.getDouble("maxBet"))
                    .maxBetPerMatch(jsonObj.getDouble("maxBetPerMatch"))
                    .maxWinPerMatch(jsonObj.getDouble("maxWinPerMatch"))
                    .build();
        }
        return null;
    }

}
