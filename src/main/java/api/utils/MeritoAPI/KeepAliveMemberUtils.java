package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KeepAliveMemberUtils {
    public static List<String> keepAlive(String token) {
        List<String> lst = new ArrayList<>();
        String api = String.format("%sbetting-api/member/login?loginId=%s&password=%s");
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED,null, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(Objects.nonNull(jsonObject)){
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            lst.add(Boolean.toString(isSuccess));
            if(isSuccess){
                JSONObject resultObj = jsonObject.getJSONObject("result");
                lst.add(resultObj.getString("token"));
            }else{
                JSONObject errObj = jsonObject.getJSONObject("error");
                lst.add(errObj.getString("code"));
                lst.add(errObj.getString("message"));
            }

        }
        return lst;
    }

}
