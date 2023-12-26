package agentsite.ultils.agencymanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class PositionTakingListingUtils {
    public static List<String> getListProductsActive() {
        List<String> lstProduct = new ArrayList<>();
        String api = String.format("%s/agent-services/products/reports?%s", domainURL, DateUtils.getMilliSeconds());
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("ALL");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectProduct = jsonArray.getJSONObject(i);
                if(jsonObjectProduct.getString("status").equalsIgnoreCase("active")) {
                    lstProduct.add(jsonObjectProduct.getString("productName"));
                }
            }
            return lstProduct;
        }
        System.out.println("JSONObject is null");
        return null;
    }

}
