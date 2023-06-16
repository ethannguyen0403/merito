package membersite.utils.signin;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;

import static baseTest.BaseCaseTest.domainURL;

public class BetUtils {

    private static JSONObject getAppConfig() {
        String api = String.format("%s/member-service/app/sat/config", domainURL);
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

}
