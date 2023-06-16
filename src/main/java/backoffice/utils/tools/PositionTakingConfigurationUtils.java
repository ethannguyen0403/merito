package backoffice.utils.tools;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;


public class PositionTakingConfigurationUtils {
    public static List<ArrayList<String>> getListPTAccount() {
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/system-manager/web/mixpt/listing?username=&page=0&row=20",backofficeUrl);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api,null, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(Objects.nonNull(jsonArray)){
            for(int i = 0; i< jsonArray.length() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lst.add(i,new ArrayList<String>(
                        Arrays.asList(Integer.toString(i+1),
                                jsonObject.getString("userCode"),
                                jsonObject.getString("loginId"),
                                jsonObject.getString("levelName"),
                                jsonObject.getString("status"),
                                "NO",
                                jsonObject.getString("brand"),
                                jsonObject.getString("upline"),
                                jsonObject.getString("updateBy"),
                                DateUtils.convertMillisToDateTime(Long.toString(jsonObject.getLong("updateDate")),"yyyy-MM-dd HH:mm:ss"))));
            }
        }
        return lst;
    }
}



