package backoffice.utils.operations;

import baseTest.BaseCaseTest;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;

import java.io.IOException;
import java.util.*;

public class PerformanceUtils {
    static String accepts = "application/json, text/plain, */*";
    static String bodyUpdatePT = "{\n" +
            "  \"userId\": %s,\n" +
            "  \"ptSetting\": [\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"SOCCER\",\n" +
            "      \"displaySport\": \"Soccer\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"CRICKET\",\n" +
            "      \"displaySport\": \"Cricket\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"LINE\",\n" +
            "      \"displaySport\": \"Line Market\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"FANCY\",\n" +
            "      \"displaySport\": \"Fancy\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"KIRON\",\n" +
            "      \"displaySport\": \"Virtual Cricket\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"BOOKMAKER\",\n" +
            "      \"displaySport\": \"Bookmaker\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"DECIMAL\",\n" +
            "      \"displaySport\": \"Decimal Cricket\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"TENNIS\",\n" +
            "      \"displaySport\": \"Tennis\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"BASKETBALL\",\n" +
            "      \"displaySport\": \"Basketball\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"HORSE\",\n" +
            "      \"displaySport\": \"Horse Racing\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"GREYHOUND\",\n" +
            "      \"displaySport\": \"Greyhound Racing\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"currentPt\": \"%s\",\n" +
            "      \"maxPt\": 25,\n" +
            "      \"sport\": \"OTHER\",\n" +
            "      \"displaySport\": \"Other\",\n" +
            "      \"percentageUpdate\": 0,\n" +
            "      \"statusUpdate\": true\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    public static Map<String, String> ptMap = new LinkedHashMap<String, String>() {
        {
            put("Soccer", "");
            put("Cricket", "");
            put("Line Market", "");
            put("Fancy", "");
            put("Virtual Cricket", "");
            put("Bookmaker", "");
            put("Decimal Cricket", "");
            put("Tennis", "");
            put("Basketball", "");
            put("Horse Racing", "");
            put("Greyhound Racing", "");
            put("Other", "");

        }
    };

    public static Map<String, String> setAllPTToSports(String ptAmount) {
        for (Map.Entry<String, String> entry : ptMap.entrySet()) {
            entry.setValue(ptAmount);
        }
        return ptMap;
    }

    public static void createNewLine(String brandID, String levelAgent, String lineName, String mappedAccountCode) throws IOException {
        String endpoint = String.format("%s/pt-performance-service/%s", BaseCaseTest.backofficeUrl, "line-management/create");
        String body = String.format("brandId=%s&level=%s&lineName=%s&uplineId=-1&mappedAccountCode=%s", brandID, levelAgent, lineName,
                mappedAccountCode);
        HttpResponse response = WSUtils.sendPOSTRequestWithCookies(endpoint, Configs.HEADER_FORM_URLENCODED, body,
                DriverManager.getDriver().getCookies().toString(), accepts);
        if (response.getStatusLine().getStatusCode() != 200) {
            System.err.println("Response code is not equal 200. Code is: " + response.getStatusLine().getStatusCode());
        } else {
            System.out.println("Success to create new line on Performance page");
        }
    }

    public static void updatePTLine(Map<String, String> ptMap, String userId) throws IOException {
        String endpoint = String.format("%s/pt-performance-service/%s", BaseCaseTest.backofficeUrl, "pt-setting/update-pt");
        String body =
                String.format(bodyUpdatePT, userId, ptMap.get("Soccer"), ptMap.get("Cricket"), ptMap.get("Line Market"), ptMap.get("Fancy"),
                        ptMap.get("Virtual Cricket"), ptMap.get("Bookmaker"), ptMap.get("Decimal Cricket"), ptMap.get("Tennis"),
                        ptMap.get("Basketball"),
                        ptMap.get("Horse Racing"), ptMap.get("Greyhound Racing"), ptMap.get("Other"));
        HttpResponse response = WSUtils.sendPOSTRequestWithCookies(endpoint, Configs.HEADER_JSON, body,
                handleCookieForRequest(DriverManager.getDriver().getCookies()), accepts);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        if (response.getStatusLine().getStatusCode() != 200) {
            System.err.println("Response code is not equal 200. Code is: " + response.getStatusLine().getStatusCode() + " Reason: " +
                    response.getStatusLine().getReasonPhrase());
        } else {
            System.out.println("Success to update PT on Performance page");
        }
    }

    private static String handleCookieForRequest(Set<Cookie> cookies) {
        List<Cookie> cookieList = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        cookieList.addAll(cookies);
        for (Cookie cookie : cookieList) {
            if (cookie.toString().contains("mc=") || cookie.toString().contains("MESESSION=") || cookie.toString().contains("SID=")) {
                newList.add(cookie.toString().split(" ")[0]);
            }
        }
        return newList.toString().replace(",", "");
    }

    public static JSONArray getLineMemberInfo(String lineID) {
        String toDate = DateUtils.getDate(-1, "yyyy-MM-dd", "GMT-4");
        String fromDate = DateUtils.getDate(-2, "yyyy-MM-dd", "GMT-4");
        String urlGet = String.format(
                "%s/pt-performance-service/pt-setting/performance-listing?lineId=%s&currencyType=F&noOfBet=0&fromDate=%s&toDate=%s&currentPage=1",
                BaseCaseTest.backofficeUrl, lineID, fromDate, toDate);
        Map<String, String> headersGet = new HashMap<String, String>() {
            {
                put("Accept", accepts);
                put("Accept-encoding", "gzip, deflate, br, zstd");
                put("Accept-language", "en-US,en;q=0.9");
                put("Cookie", handleCookieForRequest(DriverManager.getDriver().getCookies()));
            }
        };
        return WSUtils.getGETJSONArraytWithDynamicHeaders(urlGet, headersGet);
    }

    public static String getMemberID(String lineID) {
        String memberID = "";
        JSONArray response = getLineMemberInfo(lineID);
        if (Objects.nonNull(response)) {
            memberID = "" + response.getJSONObject(0).getInt("userId");
        }
        if (memberID.isEmpty()) {
            System.err.println("Member not exist!");
        }
        return memberID;
    }

    public static void deleteLine(String lineID) throws IOException {
        String endpoint = String.format("%s/pt-performance-service/%s", BaseCaseTest.backofficeUrl, "line-management/remove");
        String body = String.format("memberId=%s", lineID);
        HttpResponse response = WSUtils.sendPOSTRequestWithCookies(endpoint, Configs.HEADER_FORM_URLENCODED, body,
                DriverManager.getDriver().getCookies().toString(), accepts);
        if (response.getStatusLine().getStatusCode() != 200) {
            System.err.println("Response code is not equal 200. Code is: " + response.getStatusLine().getStatusCode() + " Reason: " +
                    response.getStatusLine().getReasonPhrase());
        } else {
            System.out.println("Success to delete line on Performance page");
        }
    }

    public static JSONArray getAllLineInfo() {
        String url = String.format("%s/pt-performance-service/line-management", BaseCaseTest.backofficeUrl);
        return
                WSUtils.getGETJSONArrayWithCookies(url, Configs.HEADER_FORM_URLENCODED, DriverManager.getDriver().getCookies().toString(),
                        accepts);
    }

    public static String getBrandID(String brandName) {
        String url = String.format("%s/pt-performance-service/brands", BaseCaseTest.backofficeUrl);
        String brandID = "";
        JSONArray response =
                WSUtils.getGETJSONArrayWithCookies(url, Configs.HEADER_FORM_URLENCODED, DriverManager.getDriver().getCookies().toString(),
                        accepts);
        if (Objects.nonNull(response)) {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                if (jsonObject.getString("displayName").equalsIgnoreCase(brandName)) {
                    brandID = "" + jsonObject.getInt("brandId");
                    break;
                }
            }
        }
        return brandID;

    }

    public static String getLineID(String lineName) {
        String lineID = "";
        JSONArray response = getAllLineInfo();
        if (Objects.nonNull(response)) {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                if (jsonObject.getString("lineName").equalsIgnoreCase(lineName)) {
                    lineID = "" + jsonObject.getInt("memberId");
                    break;
                }
            }
        }
        if (lineID.isEmpty()) {
            System.err.println("Line not exist!");
        }
        return lineID;
    }
}
