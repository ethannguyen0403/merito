package api.utils.MeritoAPI;

import api.objects.meritoAPI.SportType;
import api.objects.meritoAPI.result.EventListTypeResult;
import com.paltech.constant.Configs;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static api.testcases.BaseCaseAPI.isAddHeader;

public class GetEventListTypeUtils {
    private static JSONObject getEventTypeAPIJson(String token, String eventTypeId) {
        String api = String.format("%sbetting-api/sport/event/list/eventType", Environment.domainULR);
        String jsn = String.format("{\"eventTypeIds\":[\"%s\"]}", eventTypeId);
        if (!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON, jsn, "", Configs.HEADER_JSON, "token", token);
        } else {
            String id_random = StringUtils.generateNumeric(10);
            Map<String, String> headersParam = new HashMap<String, String>() {
                {
                    put("X-Request-ID", id_random);
                    put("X-Request-Meta", jsn);
                    put("token", token);
                    put("Content-Type", Configs.HEADER_JSON);
                }
            };
            return WSUtils.getPOSTJSONObjectWithDynamicHeaders(api, jsn, headersParam);
        }

    }

    public static EventListTypeResult getEventTypeAPI(String token, String id) {
        JSONObject jsonObject = getEventTypeAPIJson(token, id);
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONArray resultArr = jsonObject.getJSONArray("result");
            JSONObject resultObj = resultArr.getJSONObject(0);
            JSONObject eventTypeObj = resultObj.getJSONObject("eventType");
            SportType sportType = new SportType.Builder()
                    .id(eventTypeObj.getInt("id"))
                    .name(eventTypeObj.getString("name"))
                    .build();
            return new EventListTypeResult.Builder()
                    .isSuccess(isSuccess)
                    .marketCount(resultObj.getInt("marketCount"))
                    .sportType(sportType)
                    .build();
        }
        return null;
    }

}
