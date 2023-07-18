package backoffice.utils.paymentmanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;

public class PaymentConfigurationUtils {
    public static List<String> getUsername() {
        List<String> lstData = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/payment/list-payment-configuration", backofficeUrl);
        String jsn = "{\n" +
                "  \"loginId\": \"\",\n" +
                "  \"paymentMethod\": null\n" +
                "}";
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,jsn,DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstData.add(jsonObject.getString("loginId"));
            }
        }
        return lstData;
    }

}
