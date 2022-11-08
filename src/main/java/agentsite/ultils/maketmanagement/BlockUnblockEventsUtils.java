package agentsite.ultils.maketmanagement;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseMerito.domainURL;
import static baseTest.BaseCaseMerito.environment;

public class BlockUnblockEventsUtils {

    private static JSONArray getListMarketOfEventJSON(String userID, String eventID, String sportID){
        String api = String.format("%s/agent-blocking-management/event/sat/get-user-block-detail-markets.json?userId=%s&eventId=%s&sportId=%s", domainURL, userID,eventID,sportID);
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }
    public static String getLevelControlBlocking(String userID) {
        String api = String.format("%s/agent-blocking-management/event/sat/get-level-name.json?userId=%s", domainURL, userID);
        JSONObject obh = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        return obh.getString("levelPT");
    }

    public static String getchildUserID(String parentID, String downlineAccount) {
        String api = String.format("%s/agent-services/event-management/sat/get-all-child?userId=%s&%s", domainURL, parentID, DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("loginId").equals(downlineAccount) || jsonObject.getString("userCode").equals(downlineAccount)) {
                    return Integer.toString(jsonObject.getInt("userId"));
                }
            }
        }
        return null;
    }
    public static String getAllChildPO(String levelPT, String controlBlockingAccount)
    {
        AccountInfo acc = ProfileUtils.getProfile();
        String api = String.format("%s/agent-services/event-management/sat/get-all-child-po?userId=%s&levelPT=%s&%s", domainURL,acc.getUserID(),levelPT.toUpperCase(), DateUtils.getMilliSeconds());
        JSONArray jsonArray =  WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("userCode").equals(controlBlockingAccount)||jsonObject.getString("loginId").equals(controlBlockingAccount)) {
                    return Integer.toString(jsonObject.getInt("userId"));
                }
            }
        }
        return null;
    }

    public static List<Market> getListMarketOfEvent(String eventId, String userId, String sportID){
        JSONArray jsonArray = getListMarketOfEventJSON(userId,eventId,sportID);
        List<Market> lstMarket = new ArrayList<Market>();
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
               JSONObject jsonObject = jsonArray.getJSONObject(i);
               lstMarket.add(new Market.Builder()
                        .marketID(Integer.toString(jsonObject.getInt("marketId")))
                        .marketName(jsonObject.getString("marketName"))
                        .marketStatus(jsonObject.getString("status"))
                        .marketStartTime(Long.toString(jsonObject.getLong("marketStartTime")))
                        .build());
            }
        }
        return lstMarket;
    }

    public static List<Event> getEventList(String sportName, String userID, String time) {
        List<Event> lstEvent = new ArrayList<Event>();
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-blocking-management/event/sat/listEvent.json?isBlck=1&sportId=%s&userId=%s&time=%s&currentPage=1&numOfRows=50&filter=&_=%s",
                domainURL, sportID, userID, time.toUpperCase(), DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getBoolean("valid")) {
                    lstEvent.add(new Event.Builder()
                            .id(Integer.toString(jsonObject.getInt("id")))
                            .eventName(jsonObject.getString("name"))
                            .startTime(Long.toString(jsonObject.getLong("open")))
                            .inPlay(jsonObject.getBoolean("inplay"))
                            .competitionName(jsonObject.getString("competitionName"))
                            .countryCode(jsonObject.getString("countryCode"))
                            .countryName(jsonObject.getString("countryName"))
                            .build());
                }
            }
        }
        return lstEvent;
    }

    public static List<ArrayList<String>> getAllEvents(String sportName, String userID, String time) {
        List<ArrayList<String>> eventInfo = new ArrayList<>();
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-blocking-management/event/sat/listEvent.json?isBlck=1&sportId=%s&userId=%s&time=%s&currentPage=1&numOfRows=50&filter=&_=%s",
                domainURL, sportID, userID, time.toUpperCase(), DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getBoolean("valid")) {
                    eventInfo.add( new ArrayList<> (Arrays.asList(
                            Boolean.toString(jsonObject.getBoolean("allowable")),
                            Boolean.toString(jsonObject.getBoolean("allowed")),
                            Integer.toString(jsonObject.getInt("allowedUserCount")),
                            Integer.toString(jsonObject.getInt("betable")),
                            Boolean.toString(jsonObject.getBoolean("blocked")),
                            Integer.toString(jsonObject.getInt("blockedUserCount")),
                            Integer.toString(jsonObject.getInt("competitionId")),
                            jsonObject.getString("competitionName"),
                            jsonObject.getString("countryCode"),
                            jsonObject.getString("countryName"),
                            Integer.toString(jsonObject.getInt("id")),
                            Boolean.toString(jsonObject.getBoolean("inplay")),
                            jsonObject.getString("name"),
                            Long.toString(jsonObject.getLong("open")),
                            Integer.toString(jsonObject.getInt("timeToBet")),
                            Integer.toString(jsonObject.getInt("unblockBeforeStart")),
                            Boolean.toString(jsonObject.getBoolean("unblockable")),
                            Boolean.toString(jsonObject.getBoolean("valid")),
                            Integer.toString(jsonObject.getInt("viewable")))));
                }
            }
        }
        return eventInfo;
    }

    public static List<Event> getEventList(String sportName, String userID, String time, String status) {
        List<Event> lstEvent = new ArrayList<Event>();
        int _status = -1;
        if(status.toUpperCase().equals("BLOCKED"))
            _status = 0;
        if(status.toUpperCase().equals("UNBLOCKED"))
            _status = 1;
       /* if(status.toUpperCase().equals("25 MINUTES"))
            _status = 25;*/
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-blocking-management/event/sat/listEvent.json?isBlck=1&sportId=%s&userId=%s&time=%s&currentPage=1&numOfRows=50&filter=&_=%s",
                domainURL, sportID, userID, time.toUpperCase(), DateUtils.getMilliSeconds());
        JSONArray jsonArray =  WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getBoolean("valid")) {
                    if(!status.isEmpty()){
                        if (jsonObject.getInt("viewable") == _status){
                            lstEvent.add(new Event.Builder()
                                    .id(Integer.toString(jsonObject.getInt("id")))
                                    .eventName(jsonObject.getString("name"))
                                    .startTime(Long.toString(jsonObject.getLong("open")))
                                    .inPlay(jsonObject.getBoolean("inplay"))
                                    .competitionName(jsonObject.getString("competitionName"))
                                    .build());
                        }
                    }else{
                        lstEvent.add(new Event.Builder()
                                .id(Integer.toString(jsonObject.getInt("id")))
                                .eventName(jsonObject.getString("name"))
                                .startTime(Long.toString(jsonObject.getLong("open")))
                                .inPlay(jsonObject.getBoolean("inplay"))
                                .competitionName(jsonObject.getString("competitionName"))
                                .build());
                    }

                }
            }
        }
        return lstEvent;
    }

    public static Market getAnOpenLineMarket(List<Event> lsEvent, String userID,String sportID,String status){
        for (Event e: lsEvent
             ) {
            List<Market> lsMarket = getListMarketOfEvent(e.getID(),userID,sportID);
            for (Market market:lsMarket
                 ) {
               if(market.getMarketName().contains("Runs Line") && market.getMarketStatus().equals(status)){
                   market.setEventNamE(e.getEventName());
                   market.setEventID(e.getID());
                   return market;
               }
            }
        }
        System.out.println("DEBUG! There is no line market in the list input event");
        return null;

    }
}
