package backoffice.utils.paymentmanagement;

import backoffice.objects.bo.system.Product;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static baseTest.BaseCaseTest.backofficeUrl;

public class PaymentConfigurationPopupUtils {
    public static List<ArrayList<String>> getData(String userId) {
        List<ArrayList<String>> lstData = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/payment/get-configuration-history", backofficeUrl);
        String jsn = String.format("{\n" +
                "  \"loginId\": \"%s\",\n" +
                "  \"paymentMethod\": {\n" +
                "    \"code\": \"KINGSPAY\",\n" +
                "    \"name\": \"KINGSPAY\"\n" +
                "  }\n" +
                "}",userId);
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstData.add(i, new ArrayList<String>(
                        Arrays.asList(
                                jsonObject.getString("action"),
                                jsonObject.getString("lastUpdateBy"),
                                jsonObject.getString("lastUpdateDate")
                )));
            }
        }
        return lstData;
    }


}
