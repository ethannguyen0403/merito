package api.utils.B2B.appUitls;

import api.objects.B2B.resultObj.ResultB2BObj;
import api.utils.B2B.B2BWSUtils;
import objects.Environment;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import static api.common.B2BAPIConstant.ENCRYPT_URL;
import static api.common.B2BAPIConstant.HEADER_FORM_URLENCODED_NONUTF8;

public class EncryptUtils {
    private static JSONObject encrypt(String agentKey, String token, String json) {
        String api = String.format("%s%s?body=%s", Environment.domainULR, ENCRYPT_URL, json);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8, agentKey, token, null);
    }

    public static ResultB2BObj getEncrypt(String agentKey, String token, String json) {
        JSONObject jsonObject = encrypt(agentKey, token, json);
        if (Objects.nonNull(jsonObject)) {
            int isSuccess = jsonObject.getInt("status");
            String encryptdata = jsonObject.getString("data");
            return new ResultB2BObj.Builder()
                    .result(isSuccess)
                    .message(encryptdata)
                    .build();
        }
        return null;
    }

    /**
     * This method use to encypt json body
     *
     * @param agentKey
     * @param token
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encryptBody(String agentKey, String token, String json) throws UnsupportedEncodingException {
        // ex: json input value; {userId:'newaccissa'}
        String jsonEncodeUrl = java.net.URLEncoder.encode(json, "UTF-8");
        // step 1 call encode json: %7BuserId%3A'newaccissa'%7D
        String resultJson = getEncrypt(agentKey, token, jsonEncodeUrl).getMessage();
        // ressult Json: tPPW8vAoYr8LkKu2iH46/YnEJE8pi+rpAROumXx4jml2QPK943ahewo2nDyfKD5LXgUctyX5j/KkExEm+weeYA==
        String encodeBody = java.net.URLEncoder.encode(resultJson, "UTF-8");
        // encodeBody = tPPW8vAoYr8LkKu2iH46%2FYnEJE8pi%2BrpAROumXx4jml2QPK943ahewo2nDyfKD5LXgUctyX5j%2FKkExEm%2BweeYA%3D%3D
        return encodeBody;
    }
}
