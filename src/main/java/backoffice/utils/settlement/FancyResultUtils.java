package backoffice.utils.settlement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;

public class FancyResultUtils {
    /**
     * Getting events
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static List<String> getEvents(String date) {
        List<String> lstEvents = new ArrayList<>();
        String api = String.format("%s/system-manager/web/fancy/list-event?date=%s", backofficeUrl, date);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstEvents.add(jsonObject.getString("eventName"));
            }
        }
        return lstEvents;
    }
}
