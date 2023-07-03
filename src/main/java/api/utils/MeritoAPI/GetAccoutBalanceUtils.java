package api.utils.MeritoAPI;

import com.paltech.constant.Configs;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import api.objects.meritoAPI.AccountBalance;
import objects.Environment;
import org.json.JSONObject;

import java.util.Objects;

public class GetAccoutBalanceUtils {
    private static JSONObject getAccountBalanceAPIJson(String token){
        String api = String.format("%sbetting-api/member/accountBalanceInfo?_ts=%s", Environment.domainULR, DateUtils.getMilliSeconds());
        return WSUtils.sendGETRequestWithCookiesHasHeader(api, Configs.HEADER_JSON, "",Configs.HEADER_JSON,"token",token);
    }
    public static AccountBalance getAccountBalanceAPI(String token) {
        JSONObject jsonObject = getAccountBalanceAPIJson(token);
        if (Objects.nonNull(jsonObject)) {
            boolean isSuccess = jsonObject.getBoolean("isSuccess");
            JSONObject resultObj = jsonObject.getJSONObject("result");
            return new AccountBalance.Builder()
                    .isSuccess(isSuccess)
                    .availableBalance(resultObj.getDouble("availableBalance"))
                    .exposure(resultObj.getDouble("retainedTax"))
                    .exposure(resultObj.getDouble("exposure"))
                    .currency(resultObj.getString("currency"))
                    .build();
        }
        return null;
    }

}
