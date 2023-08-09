package agentsite.ultils.report;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DoubleUtils;
import com.paltech.utils.WSUtils;
import membersite.objects.sat.Event;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class TopGainerLoserUtils {

    private static JSONObject getBigStakeJSON() {
        String api = String.format("%s/agent-fraud-service/report/get-bigstake", domainURL);
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static int getBigStake() {
        JSONObject jsonObject = getBigStakeJSON();
        return jsonObject.getInt("config");
    }

}
