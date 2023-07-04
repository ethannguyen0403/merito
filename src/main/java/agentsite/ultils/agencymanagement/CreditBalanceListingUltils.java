package agentsite.ultils.agencymanagement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import common.AGConstant;
import membersite.objects.sat.Event;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class CreditBalanceListingUltils {
    public static List<Event> getEventList(String sportName, String userID, String time) {
        List<Event> lstEvent = new ArrayList<Event>();
        String sportID = AGConstant.HomePage.SPORT_ID.get(sportName);
        String api = String.format("%s/agent-services/user/getListingCreditBalance",
                domainURL, userID, sportID, time.toUpperCase(), DateUtils.getMilliSeconds());
        String jsn = "{\"currentPage\":1,\"numOfRows\":20,\"products\":\"EXCHANGE\",\"filter\":{\"userName\":\"\",\"status\":\"\",\"levelSearch\":\"ALL\",\"userId\":2256}}";
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("extraInfo")) {
                JSONObject jsnPageInfo = jsonObject.getJSONObject("extraInfo");
                int maxCredit = jsonObject.getInt("maxCredit");
                int memberMaxCredit = jsonObject.getInt("memberMaxCredit");
                int myCreditBalance = jsonObject.getInt("myCreditBalance");
            }
        }
        return lstEvent;
    }

}
