package agentsite.ultils.agencymanagement;

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


public class DownLineListingUtils {
    private static String defineAPIUrl(String brandName) {
        switch (brandName) {
            case "satsport":
                return String.format("%s/agent-services/user/sad-downline-list", domainURL);
            default:
                return String.format("%s/agent-services/user/getListingDownline", domainURL);
        }

    }

    private static JSONObject getDownLineJson(String brandName, String loginID) {
        String api = defineAPIUrl(brandName);
        String jsn = "";
        switch (brandName) {
            case "satsport":
                jsn = String.format("{\"userName\":\"\",\"loginId\":\"%s\",\"isAgentOnly\":null,\"accStatus\":\"ALL\",\"t\":%s,\"currentPage\":1,\"numOfRows\":200}", loginID, DateUtils.getMilliSeconds());
                break;
            default:
                jsn = String.format("{\"userName\":\"\",\"isAgentOnly\":null,\"accStatus\":\"ALL\",\"t\":%s,\"currentPage\":1,\"numOfRows\":200}", DateUtils.getMilliSeconds());
        }
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static JSONObject getLineInfoJson(String brandName, String userName) {
        String api = defineAPIUrl(brandName);
        String jsn = String.format("{\"userName\":\"%s\",\"isAgentOnly\":null,\"accStatus\":\"ALL\",\"t\":%s,\"currentPage\":1,\"numOfRows\":20}", userName, DateUtils.getMilliSeconds());
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static List<AccountInfo> getUplineInfoOfUser(String brandName, String userName) {
        List<AccountInfo> lstUsers = new ArrayList<>();
        JSONObject jsonObject = getLineInfoJson(brandName, userName);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("lineInfo")) {
                JSONArray jsnLineInfo = jsonObject.getJSONArray("lineInfo");
                for (int i = 0; i < jsnLineInfo.length(); i++) {
                    JSONObject item = jsnLineInfo.getJSONObject(i);
                    AccountInfo a = new AccountInfo.Builder()
                            .userID(Integer.toString(item.getInt("userId")))
                            .userCode(item.getString("usercode"))
                            .loginID(item.getString("loginId"))
                            .level(item.getString("level"))
                            .currencyCode(item.getString("currencyCode"))
                            .build();
                    lstUsers.add(a);
                }
            }
        }
        return lstUsers;
    }

    public static AccountInfo getDownLineUsers(String loginID, String userName) {
        String api = String.format("%s/agent-services/user/sad-downline-list", domainURL);
        String jsn = String.format("{\"userName\":\"%s\",\"loginId\":%s,\"isAgentOnly\":null,\"accStatus\":\"ALL\",\"t\":%s,\"currentPage\":1,\"numOfRows\":20}", userName, loginID, DateUtils.getMilliSeconds());
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("pageInfo")) {
                JSONObject jsnPageInfo = jsonObject.getJSONObject("pageInfo");
                JSONArray arrItems = jsnPageInfo.getJSONArray("items");
                for (int i = 0; i < arrItems.length(); i++) {
                    JSONObject item = arrItems.getJSONObject(i);
                    AccountInfo a = new AccountInfo.Builder()
                            .userID(Integer.toString(item.getInt("userId")))
                            .userCode(item.getString("userCode"))
                            .loginID(item.getString("nickname"))
                            .parentID(Integer.toString(item.getInt("parentId")))
                            .level(item.getString("level"))
                            .currencyCode(item.getString("currencyCode"))
                            .status(item.getString("myStatus"))
                            .build();
                    return a;
                }
            }
        }
        return null;
    }

    public static List<AccountInfo> getDownLineUsers(String loginID, String level, String brandname) {
        return getDownLineUsers(loginID, level, "ACTIVE", brandname);
    }


    public static List<AccountInfo> getDownLineUsers(String loginID, String level, String status, String brandname) {
        List<AccountInfo> lstUsersFilter = new ArrayList<>();
        List<AccountInfo> lstUsers = getAllDownLineUsers(brandname, "", loginID);
        for (int i = 0; i < lstUsers.size(); i++) {
            if (!level.isEmpty()) {
                if (lstUsers.get(i).getLevel().equalsIgnoreCase(level) && lstUsers.get(i).getStatus().equalsIgnoreCase(status)) {
                    lstUsersFilter.add(lstUsers.get(i));
                }
            } else {
                if (lstUsers.get(i).getStatus().equalsIgnoreCase(status)) {
                    lstUsersFilter.add(lstUsers.get(i));
                }
            }
        }
        return lstUsersFilter;
    }

    public static List<AccountInfo> getAllDownLineUsers(String brandName, String userName, String loginID) {
        // String api = defineAPIUrl(brandName);
        List<AccountInfo> lstUsers = new ArrayList<>();
        JSONObject jsonObject = getDownLineJson(brandName, loginID);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("pageInfo")) {
                JSONObject jsnPageInfo = jsonObject.getJSONObject("pageInfo");
                JSONArray arrItems = jsnPageInfo.getJSONArray("items");
                for (int i = 0; i < arrItems.length(); i++) {
                    JSONObject item = arrItems.getJSONObject(i);
                    AccountInfo a = new AccountInfo.Builder()
                            .userID(Integer.toString(item.getInt("userId")))
                            .userCode(item.getString("userCode"))
                            .loginID(item.getString("nickname"))
                            .parentID(Integer.toString(item.getInt("parentId")))
                            .level(item.getString("level"))
                            .currencyCode(item.getString("currencyCode"))
                            .status(item.getString("myStatus"))
                            .build();
                    lstUsers.add(a);
                }
            }
        }
        return lstUsers;
    }

//    public static List<AccountInfo> getAllDriectMember(String brandName, String loginID) {
//        List<AccountInfo> lstUsers = new ArrayList<>();
//        String api = defineAPIUrl(brandName);
//        String jsn = String.format("{\"userName\":\"\",\"loginId\":%s,\"isAgentOnly\":false,\"accStatus\":\"ACTIVE\",\"t\":%s,\"currentPage\":1,\"numOfRows\":200}", loginID, DateUtils.getMilliSeconds());
//        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
//        if (Objects.nonNull(jsonObject)) {
//            if (jsonObject.has("pageInfo")) {
//                JSONObject jsnPageInfo = jsonObject.getJSONObject("pageInfo");
//                JSONArray arrItems = jsnPageInfo.getJSONArray("items");
//                for (int i = 0; i < arrItems.length(); i++) {
//                    JSONObject item = arrItems.getJSONObject(i);
//                    AccountInfo a = new AccountInfo.Builder()
//                            .userID(Integer.toString(item.getInt("userId")))
//                            .userCode(item.getString("userCode"))
//                            .loginID(item.getString("nickname"))
//                            .parentID(Integer.toString(item.getInt("parentId")))
//                            .level(item.getString("level"))
//                            .currencyCode(item.getString("currencyCode"))
//                            .status(item.getString("status"))
//                            .build();
//                    lstUsers.add(a);
//                }
//            }
//        }
//        return lstUsers;
//    }

    public static AccountInfo getAccountInfoInList(List<AccountInfo> lstAccount, String userName) {
        for (AccountInfo acc : lstAccount) {
            if (acc.getLoginID().equalsIgnoreCase(userName) || acc.getUserCode().equalsIgnoreCase(userName))
                return acc;
        }
        System.out.println("Account " + userName + " does not exist in the list");
        return null;
    }

    private static JSONObject getListingCreditCashBalance() {
        String api = String.format("%s/agent-services/user/getListingCreditCashBalance", domainURL);
        String jsn = String.format("{\"currentPage\":1,\"numOfRows\":200,\"products\":\"EXCHANGE\",\"filter\":{\"userName\":\"\",\"status\":\"\",\"levelSearch\":\"ALL\",\"userId\":%s}}", ProfileUtils.getProfile().getUserID());
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);//,DriverManager.getDriver().getCookies().toString());
    }

//    public static JSONObject getBalanceInfoJSONObj() {
//        JSONObject jsonObject = getListingCreditCashBalance();
//        if (Objects.nonNull(jsonObject)) {
//            if (jsonObject.has("extraInfo")) {
//                JSONObject extraInfoObj = jsonObject.getJSONObject("extraInfo");
//                if (jsonObject.has("sub")) {
//                    return extraInfoObj.getJSONObject("sub");
//                }
//            }
//        }
//        System.err.println("ERROR: jsonObject is null at getListingCreditCashBalance");
//        return null;
//    }


    public static List<AccountInfo> getCashCreditListing() {
        List<AccountInfo> lstUsers = new ArrayList<>();
        JSONObject jsonObject = getListingCreditCashBalance();
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("contentList")) {
                JSONArray jsnList = jsonObject.getJSONArray("contentList");
                for (int i = 0; i < jsnList.length(); i++) {
                    JSONObject item = jsnList.getJSONObject(i);
                    double cashBalance = DoubleUtils.roundEvenWithTwoPlaces(item.getDouble("cashBalance"));
                    double availableBalance = DoubleUtils.roundEvenWithTwoPlaces(item.getDouble("availableBalance"));
                    double pnl = DoubleUtils.roundEvenWithTwoPlaces(item.getDouble("pnl"));
                    double outstanding = DoubleUtils.roundEvenWithTwoPlaces(item.getDouble("plOutstanding"));
                    AccountInfo a = new AccountInfo.Builder()
                            .userID(Integer.toString(item.getInt("userId")))
                            .userCode(item.getString("userCode"))
                            .loginID(item.getString("nickName"))
                            .parentID(Integer.toString(item.getInt("parentId")))
                            .status(item.getString("status"))
                            .level(item.getString("level"))
                            .cashBalance(cashBalance)
                            .availableBalance(availableBalance)
                            .currencyCode(item.getString("currencyCode"))
                            .todayWinLoss(pnl)
                            .myOutstanding(outstanding)
                            .build();
                    lstUsers.add(a);
                }
            }
            if (jsonObject.has("extraInfo")) {
                JSONObject item = jsonObject.getJSONObject("extraInfo");
                if (item.has("lineInfo")) {
                    if (item.has("sub")) {
                        //handle for SAT
                        JSONObject jsnSubInfo = item.getJSONObject("sub");
                        double yourCreditCashBalance = DoubleUtils.roundEvenWithTwoPlaces(jsnSubInfo.getDouble("cashBalance"));
                        JSONObject accountInfo = item.getJSONArray("lineInfo").getJSONObject(0);
                        lstUsers.add(new AccountInfo.Builder()
                                .userID(Integer.toString(accountInfo.getInt("userId")))
                                .userCode(accountInfo.getString("usercode"))
                                .loginID(accountInfo.getString("loginId"))
                                .availableBalance(yourCreditCashBalance)
                                .level(accountInfo.getString("level"))
                                .currencyCode(accountInfo.getString("currencyCode"))
                                .build());
                    } else {
                        //handle for others
                        double yourCreditCashBalance = DoubleUtils.roundEvenWithTwoPlaces(item.getDouble("myCreditBalance"));
                        JSONObject accountInfo = item.getJSONArray("lineInfo").getJSONObject(0);
                        lstUsers.add(new AccountInfo.Builder()
                                .userID(Integer.toString(accountInfo.getInt("userId")))
                                .userCode(accountInfo.getString("usercode"))
                                .loginID(accountInfo.getString("loginId"))
                                .availableBalance(yourCreditCashBalance)
                                .level(accountInfo.getString("level"))
                                .currencyCode(accountInfo.getString("currencyCode"))
                                .build());
                    }

                }
            }
        } else {
            System.err.println("ERROR: jsonObject is null at getCashCreditListing");
        }
        return lstUsers;
    }

    public static Double getMyCreditCashBalance() {
        JSONObject jsonObject = getListingCreditCashBalance();
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("extraInfo")) {
                JSONObject jsnExtraInfo = jsonObject.getJSONObject("extraInfo");
                if (Objects.nonNull(jsnExtraInfo)) {
                    {
                        if(jsnExtraInfo.has("sub")) {
                            //handle for SAT
                            JSONObject jsnSubInfo = jsnExtraInfo.getJSONObject("sub");
                            return jsnSubInfo.getDouble("cashBalance");
                        } else {
                            //handle for other brands
                            return jsnExtraInfo.getDouble("myCreditBalance");
                        }
                    }
                }
            }
        }
        System.err.println("ERROR: jsonObject is null at getCashCreditListing");
        return -1.0;
    }
    public static String getRandomLoginID(){
        AccountInfo accountInfo = ProfileUtils.getProfile();
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(ProfileUtils.getAppName(), accountInfo.getUserCode(), accountInfo.getUserID());
        for (AccountInfo account : listAccount){
            if (!account.getLoginID().isEmpty()){
                return account.getLoginID();
            }
        }
        return "";
    }
}
