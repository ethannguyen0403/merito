package agentsite.ultils.riskmanagement;

import agentsite.objects.agent.AgentExposureLimitInfo;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class AgentExposureLimitUtils {

     private static JSONObject getRiskLimitJson(String userid){
        String api = String.format("%s/agent-fraud-service/report/risk-limit",domainURL);
        String jns =String.format("currentPage=1&numOfRows=200&filter%5BuId%5D=%s&filter%5BuserName%5D=&products=EXCHANGE",userid);
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET,jns,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    public static  List<AgentExposureLimitInfo> getAgentExposureList(String userID){
         JSONObject riskJson = getRiskLimitJson(userID);
        JSONObject jsonData = riskJson.getJSONObject("data");
        JSONArray jsArrayContentList = jsonData.getJSONArray("contentList");
        List<AgentExposureLimitInfo> lstAgentExposure= new ArrayList<>();
        if(Objects.nonNull(jsArrayContentList))
        {
            for(int i =0; i < jsArrayContentList.length(); i++){
                JSONObject iObject = jsArrayContentList.getJSONObject(i);
                lstAgentExposure.add(
                new AgentExposureLimitInfo.Builder()
                        .userID(Integer.toString(iObject.getInt("userId")))
                        .levelName(iObject.getString("levelName"))
                        .parentID(iObject.getString("parentID"))
                        .loginID(iObject.getString("loginId"))
                        .riskAvailable(iObject.getDouble("riskAvailable"))
                        .riskBalance(iObject.getDouble("riskBalance"))
                        .riskLimit(iObject.getDouble("riskLimit"))
                        .build());
            }
            return lstAgentExposure;
        }
        return null;
    }




}
