package backoffice.utils.operations;

import backoffice.objects.bo.operations.Brand;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;


public class BannerManagementUtils {
    public static List<Brand> getBrands() {
        List<Brand> lstBrands = new ArrayList<>();
        String api = String.format("%s/banner-manager/web/sv/banner-management/banner-brands.sv",backofficeUrl);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstBrands.add(new Brand.Builder()
                        .brandId(jsonObject.getInt("brandID"))
                        .brandName(jsonObject.getString("brandName"))
                        .displayName(jsonObject.getString("displayName"))
                        .build());
            }
        }
        return lstBrands;
    }

    public static List<String> getBrandNames() {
        List<Brand> lstBrands = getBrands();
        List<String> lstBrandNames = new ArrayList<>();
        for(Brand brand : lstBrands) {
            lstBrandNames.add(brand.getDisplayName());
        }
        return lstBrandNames;
    }
}
