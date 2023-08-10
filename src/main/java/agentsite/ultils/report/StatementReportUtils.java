package agentsite.ultils.report;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.DoubleUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;


public class StatementReportUtils {

    public static List<String> getReportStatementDownLineUsers(String userId, String fromDate, String toDate) {
        List<String> lstUsers = new ArrayList<>();
        String api = String.format("%s/agent-report-statement/report/statement-member-list", domainURL);
        String jsn = String.format("{\"products\":\"EXCHANGE\",\"userId\":%s,\"groupBy\":\"USER\",\"fromDate\":\"%s\",\"toDate\":\"%s\",\"parentId\":\"\"}", userId, fromDate, toDate);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("statements")) {
                JSONObject jsnStatement = jsonObject.getJSONObject("statements");
                JSONArray arrItems = jsnStatement.getJSONArray("USER");
                for (int i = 0; i < arrItems.length(); i++) {
                    JSONObject item = arrItems.getJSONObject(i);
                    lstUsers.add(item.getString("userCode"));
                }
            }
        }
        return lstUsers;
    }

}