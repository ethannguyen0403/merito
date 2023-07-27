package agentsite.ultils.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import common.AGConstant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class CommissionSettingListingUtils {
    public static Double getAgentCommissionSettingByProduct(String downlineName, String productCode) {
        double settingValue = 0;
        AccountInfo accountInfo = ProfileUtils.getProfile();
        String api = String.format("%s/agent-services/commissions/getChildCommissionSettings", domainURL);
        String jsn = String.format("{\"userId\":%s,\"userName\":\"\",\"status\":\"\",\"product\":\"%s\",\"currentPage\":1,\"numOfRows\":20}", accountInfo.getUserID(), productCode);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("pageInfo")) {
                JSONObject jsnPageInfo = jsonObject.getJSONObject("pageInfo");
                JSONArray arrItems = jsnPageInfo.getJSONArray("items");
                for (int i = 0; i < arrItems.length(); i++) {
                    JSONObject item = arrItems.getJSONObject(i);
                    if(item.get("userCode").equals(downlineName)) {
                        JSONObject jsnProductSettingValue = item.getJSONObject("groups");
                        settingValue = (double) jsnProductSettingValue.get(AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_CODE_TO_NAME.get(productCode));
                        return settingValue;
                    }

                }
            }
        }
        return settingValue;
    }
}
