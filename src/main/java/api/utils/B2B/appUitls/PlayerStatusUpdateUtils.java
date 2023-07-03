package api.utils.B2B.appUitls;

import api.objects.B2B.resultObj.PlayerInfoObj;
import objects.Environment;
import org.json.JSONObject;
import api.utils.B2B.B2BWSUtils;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import static api.common.B2BAPIConstant.*;

public class PlayerStatusUpdateUtils {
    private static JSONObject update(String agentKey, String token,String json) throws UnsupportedEncodingException {
       String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token,json);
        String api = String.format("%s%s?body=%s", Environment.domainULR,PLAYER_STATUS_UPDATE,bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8,agentKey, token,null);
    }

    public static PlayerInfoObj updateStatus(String agentKey, String token, String json) throws UnsupportedEncodingException {
        JSONObject jsonObject = update(agentKey,token,json);
        if(Objects.nonNull(jsonObject)){
            int isSuccess = jsonObject.getInt("status");
            if(isSuccess == 1) {
                JSONObject resultObj = jsonObject.getJSONObject("data");
                return new PlayerInfoObj.Builder()
                        .result(isSuccess)
                        .userId(resultObj.getString("userId"))
                        .status(resultObj.getString("status"))
                        .build();
            }
        }
        return null;
    }
}
