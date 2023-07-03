package backoffice.utils.system;

import backoffice.objects.bo.system.Product;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;

public class ProductMaintenanceUtils {
    public static List<Product> getProducts() {
        List<Product> lstProducts = new ArrayList<>();
        String api = String.format("%s/system-manager/web/sv/product-maintenance/list.sv", backofficeUrl);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstProducts.add(new Product.Builder()
                        .productId(jsonObject.getInt("productId"))
                        .productName(jsonObject.getString("name"))
                        .productCode(jsonObject.getString("code"))
                        .status(jsonObject.getString("status"))
                        .build());
            }
        }
        return lstProducts;
    }

    /**
     * Updating a product status
     *
     * @param product
     * @param status  MAINTENANCE | ACTIVE
     * @param message
     */
    public static boolean updateProduct(Product product, String status, String message) {
        String api = String.format("%s/system-manager/web/sv/product-maintenance/update.sv", backofficeUrl);
        String jsn = String.format("{\"id\":%s,\"status\":\"%s\",\"messageMaintenance\":\"<p>%s</p>\",\"productCode\":\"%s\"}", product.getProductId(), status.toUpperCase(), message, product.getProductCode());
        int responseCode = WSUtils.getPOSTResponseCode(api, Configs.HEADER_JSON, jsn, Configs.HEADER_JSON);
        boolean isSuccessful = (responseCode == 200);
        if (isSuccessful) {
            System.out.println(String.format("INFO: Updating Product name '%s' to '%s' status is SUCCESSFUL", product.getProductName(), status));
        } else {
            System.err.println(String.format("INFO: Updating Product name '%s' to '%s' status is FAILED", product.getProductName(), status));
        }
        return responseCode == 200;
    }
}
