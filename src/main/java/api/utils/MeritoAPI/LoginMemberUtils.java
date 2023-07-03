package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.utils.WSUtils;
import api.objects.meritoAPI.result.ResultObject;
import objects.Environment;
import org.json.JSONObject;

import java.util.Objects;

public class LoginMemberUtils {
    private static JSONObject getLoginAPI(String username, String password){
        String api = String.format("%sbetting-api/member/login?loginId=%s&password=%s", Environment.domainULR,username,password);
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED,null, "",Configs.HEADER_JSON);
    }

    public static ResultObject loginAPISuccess(String username, String password) {

        JSONObject jsonObject = getLoginAPI(username,password);
        if(Objects.nonNull(jsonObject)){
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            if(isSuccess){
                JSONObject resultObj = jsonObject.getJSONObject("result");
                return new ResultObject.Builder()
                        .isSuccess(isSuccess)
                        .message(resultObj.getString("token"))
                        .build();
            }else{
                JSONObject errObj = jsonObject.getJSONObject("error");
                return new ResultObject.Builder()
                        .isSuccess(isSuccess)
                        .message(errObj.getString("message"))
                        .code(errObj.getString("code"))
                        .build();
            }
        }
        return null;
    }

}
