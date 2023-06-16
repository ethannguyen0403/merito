package agentsite.ultils.riskmanagement;

import agentsite.objects.agent.account.MarketInfo;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static baseTest.BaseCaseTest.domainURL;

public class NetExposurelUtils {

    private static JSONArray getSportNexExposure(){
        String api = String.format("%s/agent-services/analysis-net-exposure/sports",domainURL);
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    private static JSONArray getMarketODdsNexExposure(String sportId, boolean isTotalBook, boolean isInplay, boolean isFull){
        String api = String.format("%s/agent-services/analysis-net-exposure/market-odds?sportId=%s,&isTotalBook=%s&t=%s&inPlay=%s&isFull=%s", domainURL,sportId,isTotalBook, DateUtils.getMilliSeconds(), isInplay, isFull);
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    public static List<String> getSportID() {
        JSONArray sportArrJS = getSportNexExposure();
        List<String> lstSportId= new ArrayList<>();
       if(sportArrJS.length()==0)
           return null;
        for (int i=0;i< sportArrJS.length();i++) {

            JSONObject itemObj= sportArrJS.getJSONObject(i);
            lstSportId.add(Integer.toString(itemObj.getInt("id")));
        }
        return lstSportId;
    }
    public static List<MarketInfo> getEventList(String sportId, boolean isTotalBook, boolean isInplay, boolean isFull) {

        JSONArray sportArrJS = getMarketODdsNexExposure(sportId,isTotalBook,isInplay,isFull);
        List<MarketInfo> lstSportId= new ArrayList<>();
        if(sportArrJS.length()==0)
            return null;
        for (int i=0;i< sportArrJS.length();i++) {
            JSONObject itemObj= sportArrJS.getJSONObject(i);
            lstSportId.add(new MarketInfo.Builder()
                    .eventName(itemObj.getString("eventName"))
                    .marketName(itemObj.getString("marketName"))
                    .build());
        }
        return lstSportId;
    }



}
