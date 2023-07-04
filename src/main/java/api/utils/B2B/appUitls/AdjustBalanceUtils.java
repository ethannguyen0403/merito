package api.utils.B2B.appUitls;

import api.objects.B2B.resultObj.BalanceObj;
import api.utils.B2B.B2BWSUtils;
import objects.Environment;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import static api.common.B2BAPIConstant.ADJUST_BALANCE_URL;
import static api.common.B2BAPIConstant.HEADER_FORM_URLENCODED_NONUTF8;

public class AdjustBalanceUtils {
    private static JSONObject adjust(String agentKey, String token, String json) throws UnsupportedEncodingException {
        String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token, json);
        String api = String.format("%s%s?body=%s", Environment.domainULR, ADJUST_BALANCE_URL, bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8, agentKey, token, null);
    }

    public static BalanceObj adjustBalance(String agentKey, String token, String json) throws UnsupportedEncodingException {
        JSONObject jsonObject = adjust(agentKey, token, json);
        if (Objects.nonNull(jsonObject)) {
            int isSuccess = jsonObject.getInt("status");
            if (isSuccess == 1) {
                JSONObject resultObj = jsonObject.getJSONObject("data");
                return new BalanceObj.Builder()
                        .result(isSuccess)
                        .userId(resultObj.getString("userId"))
                        .amount(resultObj.getDouble("amount"))
                        .balanceBefore(resultObj.getDouble("balanceBefore"))
                        .balanceAfter(resultObj.getDouble("balanceAfter"))
                        .date(resultObj.getString("date"))
                        .transactionId(resultObj.getLong("transactionId"))
                        .build();
            }
        }
        return null;
    }
}
