package agentsite.ultils.topgainerslosers;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;
import java.util.Objects;
import static baseTest.BaseCaseTest.domainURL;

public class BigStakeUtils {

    private static JSONObject getBigStakeConfigJSON() {
        String api = String.format("%s/agent-fraud-service/report/get-bigstake", domainURL);
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static int getBigStakeConfigValue() {
        int bigStake = 0;
        JSONObject productReportJSONOBJ = getBigStakeConfigJSON();
        if(Objects.nonNull(productReportJSONOBJ)) {
            return productReportJSONOBJ.getInt("config");
        } else {
            return bigStake;
        }
    }

}
