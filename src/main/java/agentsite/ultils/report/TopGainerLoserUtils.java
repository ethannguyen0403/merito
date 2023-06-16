package agentsite.ultils.report;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;

import static baseTest.BaseCaseTest.domainURL;
import static baseTest.BaseCaseTest.environment;

public class TopGainerLoserUtils {

    private static JSONObject getBigStakeJSON(){
        String api = String.format("%s/agent-fraud-service/report/get-bigstake", domainURL);
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    public static int getBigStake() {
       JSONObject jsonObject = getBigStakeJSON();
       return jsonObject.getInt("config");
    }


}
