package agentsite.ultils.report;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static baseTest.BaseCaseTest.domainURL;
import static baseTest.BaseCaseTest.environment;

public class ReportslUtils {

    private static JSONObject getProductsActiveJSON(){
        String api = String.format("%s/agent-services/products/reports?%s", domainURL, DateUtils.getMilliSeconds());
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    public static List<String> getProductActive() {
        List<String> lstProductActive = new ArrayList<>();
        JSONObject productReportJSONOBJ = getProductsActiveJSON();
        JSONArray arrProducts;
        if(productReportJSONOBJ.has("REPORT")){
            arrProducts = productReportJSONOBJ.getJSONArray("REPORT");
        }else{
            if(productReportJSONOBJ.has("ALL")){
                arrProducts = productReportJSONOBJ.getJSONArray("ALL");
            } else {
                arrProducts = productReportJSONOBJ.getJSONArray("All");
            }
        }
        for (int i=0;i< arrProducts.length();i++) {
            JSONObject itemObj= arrProducts.getJSONObject(i);
            if(itemObj.getString("status").equals("ACTIVE")){
                lstProductActive.add(itemObj.getString("productName"));
            }
        }
        return lstProductActive;
    }

    public static List<String> getAllProducts(List<String> activeProducts, List<String> extraProducts) {
        List<String> lstProduct = new ArrayList<>(activeProducts);
        lstProduct.addAll(extraProducts);
        lstProduct = lstProduct.stream().sorted().collect(Collectors.toList());
        return lstProduct;
    }

}
