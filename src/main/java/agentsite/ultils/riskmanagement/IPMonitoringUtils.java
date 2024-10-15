package agentsite.ultils.riskmanagement;

import agentsite.objects.agent.AgentExposureLimitInfo;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainURL;

public class IPMonitoringUtils {
    public static ArrayList<List> getListIPMonitoring(String loginStatus, String ipAddress, String userName) {
        ArrayList<List> lst = new ArrayList<>();
        ArrayList<List> lstItem = new ArrayList<>();
        String api = String.format("%s/agent-services/ip-monitoring/ips", domainURL);
        String jsn = String.format("{\"currentPage\":1,\"numOfRows\":25,\"loginStatus\":\"%s\",\"ipAddress\":\"%s\",\"userName\":\"%s\",\"totalRecord\":0}", loginStatus.toUpperCase(), ipAddress, userName);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (jsonObject.has("data")) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArrayData.length(); i++) {
                JSONObject objData = jsonArrayData.getJSONObject(i);
                if (objData.getString("loginIp").equalsIgnoreCase("TOTAL_RECORDS")) {
                    return lst;
//                        JSONArray jsonTotalRecord = objData.getJSONArray("ipMonitoringRecords");
//                        for (int j = 0; j < jsonTotalRecord.length(); j++) {
//                            JSONObject obj = jsonTotalRecord.getJSONObject(j);
//                            lstItem.add(j, new ArrayList<>(
//                                    Arrays.asList(
//                                            Double.toString(obj.getDouble("loginLevelWinLoss")),
//                                            Double.toString(obj.getDouble("exposure")),
//                                            Double.toString(obj.getDouble("totalBet")),
//                                            Double.toString(obj.getDouble("winLoss")))));
//                        }
//                        return lst;
                }
                if (objData.has("ipMonitoringRecords")) {
                    JSONArray jsonArrayIP = objData.getJSONArray("ipMonitoringRecords");
                    for (int j = 0; j < jsonArrayIP.length(); j++) {
                        JSONObject obj = jsonArrayIP.getJSONObject(j);
                        lstItem.add(j, new ArrayList<>(
                                Arrays.asList(
                                        obj.getString("lastLoginIp"),
                                        obj.getString("nickName"),
                                        obj.getString("userCode"),
                                        Integer.toString(obj.getInt("totalBet")),
                                        Double.toString(obj.getDouble("exposure")),
                                        Double.toString(obj.getDouble("winLoss")),
                                        Double.toString(obj.getDouble("loginLevelWinLoss")),
                                        obj.getString("monitorStatus"),
                                        obj.getString("userStatus"),
                                        obj.getString("loginDate"),
                                        obj.getBoolean("notAllowChangeUserStatus"))));
                    }
                }
                lst.add(i, lstItem);
            }
            return lst;
        }
        return lst;
    }

    public static String getLoginIp(String loginStatus) {
        String api = String.format("%s/agent-services/ip-monitoring/ips", domainURL);
        String jsn = String.format("{\"currentPage\":1,\"numOfRows\":25,\"loginStatus\":\"%s\",\"ipAddress\":\"\",\"userName\":\"\",\"totalRecord\":0}", loginStatus.toUpperCase());
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (jsonObject.has("data")) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            JSONObject objData = jsonArrayData.getJSONObject(0);
            if (objData.getString("loginIp").equalsIgnoreCase("TOTAL_RECORDS")) {
                return "";
            } else {
                return objData.getString("loginIp");
            }
        }
        return "";
    }
}
