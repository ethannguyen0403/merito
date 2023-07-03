package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import api.objects.meritoAPI.AccountStatement;
import api.objects.meritoAPI.result.AccountStatementResult;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static api.testcases.BaseCaseAPI.isAddHeader;

public class AccountStatementUtils {
    private static JSONObject accountStatementJson(String token, String fromDate, String toDate){
        String api = String.format("%sbetting-api/member/accountStatement", Environment.domainULR);
        //2021-11-01
        String jsn = String.format("{\"fromRecord\":0,\"recordCount\":100,\"fromDate\":\"%s\",\"toDate\":\"%s\"}", fromDate,toDate);
//        return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,jsn, "",Configs.HEADER_JSON,"token",token);
        if(!isAddHeader) {
            return WSUtils.getPOSTJSONObjectWithCookiesHasHeader(api, Configs.HEADER_JSON,jsn, "",Configs.HEADER_JSON,"token",token);
        }else {
            String id_random = StringUtils.generateNumeric(10);
            Map<String, String> headersParam = new HashMap<String, String>() {
                {
                    put("X-Request-ID", id_random);
                    put("X-Request-Meta", jsn);
                    put("token", token);
                    put("Content-Type", Configs.HEADER_JSON);
                }
            };
            return WSUtils.getPOSTJSONObjectWithDynamicHeaders(api, jsn, headersParam);
        }
    }
    //testgtrt

    public static AccountStatementResult getAccountStatementResult(String token, String fromDate, String toDate) {
        JSONObject jsonObject = accountStatementJson(token,fromDate,toDate);
        List<AccountStatement> lst = new ArrayList<>();
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONArray resultArr = jsonObject.getJSONArray("result");
            JSONObject eventResultObj = resultArr.getJSONObject(0);
            lst.add(getAccountStatement(eventResultObj));
            return new AccountStatementResult.Builder()
                    .isSuccess(isSuccess)
                    .orderList(lst)
                    .build();
        }
        return null;
    }

    private static AccountStatement getAccountStatement(JSONObject jObj) {
        return new AccountStatement.Builder()
                .betId(jObj.getInt("betId"))
                .amount(jObj.getDouble("amount"))
                .balance(jObj.getDouble("balance"))
                .marketId(jObj.getInt("marketId"))
                .settlementDate(jObj.getString("settlementDate"))
                .orderPlaceTime(jObj.getString("orderPlaceTime"))
                .build();
    }

}