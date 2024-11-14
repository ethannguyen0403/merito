package backoffice.utils.operations;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.backofficeUrl;


public class WagerVoidUnvoidUtils {
    public static List<String> getListWagerIdOfMarketType(List<String> marketType, String userName, String startDate, String endDate) {
        List<String> lstWagers = new ArrayList<>();
        //TODO: need to research to remove sameSite=Lax when init driver
        String cookies = DriverManager.getDriver().getCookies().toString().replace(" sameSite=Lax,",";");
        cookies = cookies.replace(" sameSite=Lax","");
        String api = String.format("%s/system-manager/web/sv/void-wager/list.sv", backofficeUrl);
        String jsn = String.format("page=1&rp=50&sortname=wagerId&sortorder=desc&query=&qtype=&wagerFilter=BY_USERNAME&vWagerId=&vUserName=%s&startDate=%s&endDate=%s&eventDate=&sportId=-1&competitionId=-1&eventId=-1&productCode=EXCHANGE", userName, startDate, endDate);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED, jsn , cookies, Configs.HEADER_JSON);
        if(Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("records");
            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject items = jsonArray.getJSONObject(i);
                //only get id match with market type and status != VOIDED
                if(marketType.contains(items.getString("marketType")) && !items.getString("statusSBOrder").equalsIgnoreCase("VOIDED")) {
                    lstWagers.add(String.valueOf(items.getLong("wagerId")));
                }
            }
            return lstWagers;
        }
        return null;
    }

    public static void voidWagers(List<String> listWagersId) {
        String api = String.format("%s/system-manager/web/sv/void-wager/update-wager.sv", backofficeUrl);
        for (int i = 0; i < listWagersId.size(); i++) {
            String remark = String.format("Post-condition: Automation QC Void Fancy Matched Bet %s", listWagersId.get(i));
            String jsn = String.format("wagerId=%s&action=VOID&remark=%s&productCode=EXCHANGE&multiple=false", listWagersId.get(i), remark);
            WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_FORM_URLENCODED, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        }
    }

}
