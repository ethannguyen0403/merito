package backoffice.utils.system;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;
import static baseTest.BaseCaseTest.environment;

public class CurrencyManagementUtils {
    public static Float getCurrencyRate(String fromCurrency) {
        Float rate = null;
        String api = String.format("%s/system-manager/web/sv/currency/getAll", backofficeUrl);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject orderObj = jsonArray.getJSONObject(i);
                    if (orderObj.getString("currencyCode").equals(fromCurrency)) {
                        rate = orderObj.getFloat("rate");
                        return rate;
                    }
                }
            }
        }
        return rate;
    }
}



