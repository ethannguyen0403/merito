package membersite.utils.casino;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import membersite.pages.casino.CasinoProduct;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SupernowaUtils extends CasinoUtils{

    protected double getBalance(CasinoProduct product) {
        double balance = -1;
        String launchURL = getLaunchURLCasino(product);
        String token = launchURL.split("Token=")[1];
        String urlCasino = launchURL.split("lobby?")[0];
        String endpoint = String.format("%sapi/Game/GetLobby?token=%s", urlCasino, token);
        Map<String, String> headersParam = new HashMap<String, String>() {
            {
                put("Accept", Configs.HEADER_JSON);
            }
        };
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithDynamicHeaders(endpoint, null, headersParam);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("playerDetail")) {
                JSONObject jsnPlayer = jsonObject.getJSONObject("playerDetail");
                balance = jsnPlayer.getDouble("balance");
            }
        }
        return balance;
    }
}
