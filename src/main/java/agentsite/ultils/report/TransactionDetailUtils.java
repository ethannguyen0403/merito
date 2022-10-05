package agentsite.ultils.report;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseMerito.domainURL;
import static baseTest.BaseCaseMerito.environment;

public class TransactionDetailUtils {
    public static  List<String> getLevelList(String fromDate,String toDate, String playerID) {
        List<String> lstLevel = new ArrayList<>();
        String api = String.format("%s/agent-betlist-report-service/report/getBetDetail", domainURL);
        String jns ="currentPage=1&numOfRows=20&filter%5BfromDate%5D="+fromDate+"&filter%5BtoDate%5D="+toDate+"&filter%5BsportId%5D=-1&filter%5BcompetitionId%5D=-1&filter%5BplayerId%5D="+playerID+"&&filter%5Bproduct%5D=EXCHANGE&filter%5BselectedProducts%5D=EXCHANGE&filter%5Bmid%5D=1&timezone=IST";
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET,jns,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);

        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("levelList")) {
                JSONArray jsnList = jsonObject.getJSONArray("levelList");
                for (int i=0;i<jsnList.length();i++) {
                    lstLevel.add(jsnList.getString(i));
                }
            }
        }
        return lstLevel;
    }
}
