package agentsite.ultils.riskmanagement;

import agentsite.objects.agent.AgentExposureLimitInfo;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

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

    private static ArrayList<List> getListUserData(String loginStatus) {
        ArrayList<List> lstUsersInfo = new ArrayList<>();
        String api = String.format("%s/agent-services/ip-monitoring/ips", domainURL);
        String jsn = String.format("{\"currentPage\":1,\"numOfRows\":25,\"loginStatus\":\"%s\",\"ipAddress\":\"\",\"userName\":\"\",\"totalRecord\":0}", loginStatus.toUpperCase());
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (jsonObject.has("data")) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            JSONObject objData = jsonArrayData.getJSONObject(0);
            if (objData.getString("loginIp").equalsIgnoreCase("TOTAL_RECORDS")) {
                return null;
            } else {
                JSONArray lstUserInfoArr = objData.getJSONArray("ipMonitoringRecords");
                for (int i = 0; i < lstUserInfoArr.length(); i++) {
                    ArrayList<String> lstUserInfo = new ArrayList<>();
                    JSONObject objectUser = lstUserInfoArr.getJSONObject(i);
                    lstUserInfo.add(Arrays.asList(String.valueOf(objectUser.getInt("userId")), objectUser.getString("lastLoginIp"), objectUser.getString("nickName"),
                            objectUser.getString("userCode"), String.valueOf(objectUser.getInt("totalBet")), String.valueOf(objectUser.getFloat("exposure")),
                            String.valueOf(objectUser.getFloat("winLoss")), objectUser.getString("monitorStatus"), objectUser.getString("userStatus"), objectUser.getString("loginDate"),
                            String.valueOf(objectUser.getFloat("loginLevelWinLoss"))).toString());
                    lstUsersInfo.add(i, lstUserInfo);
                }
                return lstUsersInfo;
            }
        }
        return null;
    }

    public static ArrayList<List> getListUserHasBet(String loginStatus) {
        ArrayList<List> lstUsersInfo = getListUserData(loginStatus);
        ArrayList<List> lstUsersHasBet = new ArrayList<>();
        if(Objects.isNull(lstUsersInfo)) {
            return null;
        }
        for (int i = 0; i < lstUsersInfo.size(); i++) {
            String[] info = lstUsersInfo.get(i).get(0).toString().split(",");
            //get value of total bet for comparing
            String totalBet = info[4].trim();
            if(Integer.valueOf(totalBet) > 0) {
                lstUsersHasBet.add(lstUsersInfo.get(i));
            }
        }
        return lstUsersHasBet;
    }
}
