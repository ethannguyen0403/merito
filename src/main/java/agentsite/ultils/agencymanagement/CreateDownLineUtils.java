package agentsite.ultils.agencymanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseMerito.domainURL;

public class CreateDownLineUtils {
    public static List<String> getUserBetSetting(String productName, String sportGroup) {
        List<String> betSetting = new ArrayList<String>();
        String api = String.format("%s/agent-services-new/user/getCurrentUser?createMember=true", domainURL);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api,Configs.HEADER_JSON,DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {

            if (jsonObject.has("enableWlp")) {
                JSONObject jsnProfile = jsonObject.getJSONObject("enableWlp");
                JSONObject jsnInfo = jsnProfile.getJSONObject("info");
                JSONObject jsnUpline= jsnInfo.getJSONObject("upline");
                JSONObject jsnProductModle = jsnUpline.getJSONObject("productModels");
                JSONObject jsnProduct =jsnProductModle.getJSONObject(productName.toUpperCase());
                JSONObject jsnBetSetting = jsnProduct.getJSONObject("betSetting");
                JSONObject jsnSetting = jsnBetSetting.getJSONObject("settings");
                if(jsnSetting.has(sportGroup.toUpperCase()))
                {
                    JSONObject  jsnSportGroup = jsnSetting.getJSONObject(sportGroup.toUpperCase());
                    betSetting.add(Double.toString(jsnSportGroup.getDouble("minBet")));
                    betSetting.add(Double.toString(jsnSportGroup.getDouble("maxBet")));
                    betSetting.add( Double.toString(jsnSportGroup.getDouble("maxBetPerMatch")));
                    betSetting.add(Double.toString(jsnSportGroup.getDouble("maxWinPerMatch")));
                }
            }
        }
        return betSetting;
    }
}
