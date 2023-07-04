package agentsite.ultils.agencymanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import common.AGConstant;
import membersite.objects.sat.Event;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class EventBetSizeSettingUtils {
    public static List<Event> getEventList(String sportName, String userID, String time) {
        List<Event> lstEvent = new ArrayList<Event>();
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-bet-setting/list-event-bet-setting?userId=%s&sportId=%s&time=%s&_=%s",
                domainURL, userID, sportID, time.toUpperCase(), DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstEvent.add(new Event.Builder()
                        .id(Integer.toString(jsonObject.getInt("eventId")))
                        .eventName(jsonObject.getString("eventName"))
                        .competitionName(jsonObject.getString("competitionName"))
                        .inPlay(jsonObject.getBoolean("inplay"))
                        .build());
            }
        }
        return lstEvent;
    }

    public static List<String> getUserBetSetting(String productName, String userID, String sportGroup) {
        List<String> betSetting = new ArrayList<String>();
        String api = String.format("%s/agent-services-new/betSetting/getUserBetSetting", domainURL);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {

            if (jsonObject.has("productModels")) {
                JSONObject jsnProfile = jsonObject.getJSONObject("productModels");
                if (Objects.nonNull(jsnProfile)) {
                    if (jsnProfile.has(productName.toUpperCase())) {
                        JSONObject jsnProduct = jsnProfile.getJSONObject(productName.toUpperCase());
                        if (Objects.nonNull(jsnProduct)) {
                            if (jsnProduct.has("betSetting")) {
                                JSONObject jsnBetSetting = jsnProduct.getJSONObject("betSetting");
                                if (Objects.nonNull(jsnBetSetting)) {
                                    if (jsnBetSetting.has("settings")) {
                                        JSONObject jsnSetting = jsnBetSetting.getJSONObject("settings");
                                        {
                                            if (Objects.nonNull(jsnSetting)) {
                                                if (jsnSetting.has(sportGroup.toUpperCase())) {
                                                    JSONObject jsnSportGroup = jsnSetting.getJSONObject(sportGroup.toUpperCase());
                                                    betSetting.add(Double.toString(jsnSportGroup.getDouble("minBet")));
                                                    betSetting.add(Double.toString(jsnSportGroup.getDouble("maxBet")));
                                                    betSetting.add(Double.toString(jsnSportGroup.getDouble("maxBetPerMatch")));
                                                    betSetting.add(Double.toString(jsnSportGroup.getDouble("maxWinPerMatch")));
                                                }
                                            }
                                        }
                                    }
                                }
                            }


                        }
                    }

                }
            }
        }

        return betSetting;
    }

}
