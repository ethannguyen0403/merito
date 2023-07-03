package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import api.objects.meritoAPI.Event;
import api.objects.meritoAPI.result.EventListResult;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static api.testcases.BaseCaseAPI.isAddHeader;

public class GetEventsUtils {
    private static JSONObject getEventsAPIJson(String token,String eventTypeId,String eventId, String competitionId){
        String api = String.format("%sbetting-api/sport/event/list/events", Environment.domainULR);
        String jsn = String.format("{\"eventTypeIds\":[\"%s\"],\"eventIds\":[\"%s\"],\"competitionIds\":[\"%s\"]}",eventTypeId,eventId,competitionId);
        if(!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api,Configs.HEADER_JSON ,jsn, "",Configs.HEADER_JSON,"token",token);
        }else {
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
    public static EventListResult getEventsAPI (String token, String eventTypeId, String eventId, String competitionId) {
        JSONObject jsonObject = getEventsAPIJson(token,eventTypeId,eventId,competitionId);
        List<Event> lst  = new ArrayList<>();
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONArray resultArr = jsonObject.getJSONArray("result");
            JSONObject eventResultObj;
            JSONObject eventObj;
            for(int i = 0; i< resultArr.length(); i++){
                eventResultObj = resultArr.getJSONObject(i);
                eventObj = eventResultObj.getJSONObject("event");

                Event event = new Event.Builder()
                        .id(eventObj.getInt("id"))
                        .name(eventObj.getString("name"))
                        .countryCode( eventObj.has("countryCode")?eventObj.getString("countryCode"):"")
                        .timezone(eventObj.getString("timezone"))
                        .openDate(eventObj.getString("openDate"))
                        .marketCount(eventResultObj.getInt("marketCount"))
                        .build();
                lst.add(event);
                return new EventListResult.Builder()
                        .isSuccess(isSuccess)
                        .eventList(lst)
                        .build();
            }
        }
        return null;
    }
}
