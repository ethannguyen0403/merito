package api.utils.B2B.appUitls;

import api.objects.B2B.resultObj.LoginObj;
import api.utils.B2B.B2BWSUtils;
import objects.Environment;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import static api.common.B2BAPIConstant.*;

public class LoginUtils {
    private static JSONObject login(String agentKey, String token, String json) throws UnsupportedEncodingException {
        //ulr not allow contains special character => need encode it
        String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token, json);
        String api = String.format("%s%s?body=%s", Environment.domainULR, LOGIN_URL, bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8, agentKey, token, null);
    }

    private static JSONObject logout(String agentKey, String token, String json) throws UnsupportedEncodingException {
        //ulr not allow contains special character => need encode it
        String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token, json);
        String api = String.format("%s%s?body=%s", Environment.domainULR, LOGOUT_URL, bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8, agentKey, token, null);
    }

    public static LoginObj getLoginData(String agentKey, String token, String json) throws UnsupportedEncodingException {
        JSONObject jsonObject = login(agentKey, token, json);
        if (Objects.nonNull(jsonObject)) {
            int isSuccess = jsonObject.getInt("status");
            if (isSuccess == 1) {
                JSONObject resultObj = jsonObject.getJSONObject("data");
                return new LoginObj.Builder()
                        .result(isSuccess)
                        .userId(resultObj.getString("userId"))
                        .userName(resultObj.getString("username"))
                        .loginUrl(resultObj.getString("loginUrl"))
                        .build();
            }

        }
        return null;
    }

    public static LoginObj getLogoutData(String agentKey, String token, String json) throws UnsupportedEncodingException {
        JSONObject jsonObject = logout(agentKey, token, json);
        if (Objects.nonNull(jsonObject)) {
            int isSuccess = jsonObject.getInt("status");
            if (isSuccess == 1) {
                JSONObject resultObj = jsonObject.getJSONObject("data");
                return new LoginObj.Builder()
                        .result(isSuccess)
                        .userId(resultObj.getString("userId"))
                        .userName(resultObj.getString("username"))
                        .loginUrl(resultObj.getString("loginUrl"))
                        .build();
            }

        }
        return null;
    }
}
