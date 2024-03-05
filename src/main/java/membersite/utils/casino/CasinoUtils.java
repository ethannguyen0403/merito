package membersite.utils.casino;


import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static baseTest.BaseCaseTest.domainURL;
import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;


public class CasinoUtils {

    public static double getProviderCurRate(List<ArrayList<String>> listProviderCur, String currencyCode) {
        double rate = -1;
        for (ArrayList<String> listItem : listProviderCur) {
            if (listItem.get(0).contains(String.format("(code: %s)", currencyCode))) {
                return Double.valueOf(listItem.get(2));
            }
        }
        return rate;
    }

    public static String getLaunchURLCasino(String product) {
        String url = "%s/member-service/product/game/login?code=%s";
        String endPoint = String.format(url, domainURL, PRODUCT_NAME_TO_CODE.get(product));
        JSONObject jsonObject =
                WSUtils.getGETJSONObjectWithCookies(endPoint, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(),
                        Configs.HEADER_JSON);
        return  jsonObject.getString("launchURL");
    }

}
