package backoffice.utils.operations;

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

public class BlockingSettingsUtils {
    public static List<ArrayList<String>> getBlockingSetting() {
        List<ArrayList<String>> lstBrands = new ArrayList<>();
        String api = String.format("%s/system-manager/web/blocking/list-config",backofficeUrl);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("users")) {
                JSONArray jsnArray = jsonObject.getJSONArray("users");
                if (Objects.nonNull(jsnArray)) {
                    for (int i = 0; i < jsnArray.length(); i++) {
                        JSONObject jsonArrayObject = jsnArray.getJSONObject(i);
                        lstBrands.add(i, new ArrayList<String>(
                        Arrays.asList(
                                jsonArrayObject.getString("userCode"),
                                jsonArrayObject.getString("brand"),
                                jsonArrayObject.getString("sportNames"),
                                jsonArrayObject.getString("marketNames"),
                                Boolean.toString(jsonArrayObject.getBoolean("blocked")),
                                jsonArrayObject.getString("levelName"),
                                Integer.toString(jsonArrayObject.getInt("betBeforeStart")))));
                    }
                }
            }
        }
        return lstBrands;
    }
}
