package api.utils.B2B.appUitls;

import api.objects.B2B.resultObj.ResultB2BObj;

import objects.Environment;
import org.json.JSONObject;
import api.utils.B2B.B2BWSUtils;

import java.util.Objects;

import static api.common.B2BAPIConstant.TOKEN_URL;
import static api.common.B2BAPIConstant.*;

public class GetTokenUtils {
    private static JSONObject getToken(String agentKey, String authorization){
        String api = String.format("%s%s", Environment.domainULR,TOKEN_URL);
        return B2BWSUtils.getPOSTJSONObjectWithHeaders(api, HEADER_FORM_URLENCODED_NONUTF8,agentKey, authorization,null);
    }

    private static JSONObject getAuthenticate(String agentKey, String secret){
        String api = String.format("%s%s", Environment.domainULR,AUTHORIZATION_URL);
        return B2BWSUtils.getPOSTJSONObjectWithSecretHeaders(api, HEADER_FORM_URLENCODED_NONUTF8,agentKey, secret);
    }

    public static ResultB2BObj getTokenValue(String agentKey, String authorization) {

        JSONObject jsonObject = getToken(agentKey,authorization);
        if(Objects.nonNull(jsonObject)){
            int isSuccess = jsonObject.getInt("status");
            if(isSuccess == 1){
                JSONObject resultObj = jsonObject.getJSONObject("data");
                return new ResultB2BObj.Builder()
                        .result(isSuccess)
                        .message(resultObj.getString("token"))
                        .build();
            }else{
                JSONObject errObj = jsonObject.getJSONObject("data");
                return new ResultB2BObj.Builder()
                        .result(isSuccess)
                        .message(errObj.getString("errorMessage"))
                        .code(errObj.getInt("errorCode"))
                        .build();
            }
        }
        return null;
    }
    public static ResultB2BObj getAuthorization(String agentKey, String secret) {

        JSONObject jsonObject = getAuthenticate(agentKey,secret);
        if(Objects.nonNull(jsonObject)){
            int isSuccess = jsonObject.getInt("status");
            String authenticateValue = jsonObject.getString("data");
            return new ResultB2BObj.Builder()
                    .result(isSuccess)
                    .message(authenticateValue)
                    .build();

        }
        return null;
    }
}
