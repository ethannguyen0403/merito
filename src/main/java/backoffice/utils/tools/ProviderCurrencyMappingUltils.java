package backoffice.utils.tools;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseMerito.environment;

public class ProviderCurrencyMappingUltils {
    public static List<ArrayList<String>> getProviderCurrencyMapping(String provider) {
        List<ArrayList<String>> lst = new ArrayList<>();
        String api = String.format("%s/system-manager/web/provider-currency-mapping",environment.getBackofficeURL());
        JSONObject jsonO = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if(Objects.nonNull(jsonO)){
            JSONArray jsonArray =  jsonO.getJSONArray("listCurrencyMapping");
            for(int i = 0; i<jsonArray.length(); i++) {
                JSONObject infoObject = jsonArray.getJSONObject(i);
                if(Objects.nonNull(infoObject)){
                    if(infoObject.getString("productCode").equals(provider)){
                        lst.add(new ArrayList<String>(
                                Arrays.asList(
                                        infoObject.getString("supportedCurrency"),
                                        infoObject.getString("providerCurrency"),
                                        String.format("%.2f",infoObject.getDouble("rate")))));
                    }

                }
            }
        }
        return lst;
    }
}



