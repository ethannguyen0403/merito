package api.utils.B2B.appUitls;

import api.objects.B2B.resultObj.ListPlayerInfoObj;
import api.objects.B2B.resultObj.PlayerInfoObj;
import api.objects.B2B.resultObj.WagerObj;
import objects.Environment;
import org.json.JSONArray;
import org.json.JSONObject;
import api.utils.B2B.B2BWSUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static api.common.B2BAPIConstant.*;

public class PlayerInfoUtils {
    private static JSONObject getInfoJson(String agentKey, String token, String json) throws UnsupportedEncodingException {
       String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token,json);
        String api = String.format("%s%s?body=%s", Environment.domainULR,PLAYER_INFO_URL,bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8,agentKey, token,null);
    }
    private static JSONObject getListPlayerInfoJson(String agentKey, String token,String json) throws UnsupportedEncodingException {
        String bodyUrlEncrypt = EncryptUtils.encryptBody(agentKey, token,json);
        String api = String.format("%s%s?body=%s", Environment.domainULR,PLAYER_LIST_INFO_URL,bodyUrlEncrypt);
        return B2BWSUtils.getPOSTJSONObjectWithTokenHeaders(api, HEADER_FORM_URLENCODED_NONUTF8,agentKey, token,null);
    }
    public static PlayerInfoObj getPlayerInfo(String agentKey, String token, String json) throws UnsupportedEncodingException {
        JSONObject jsonObject = getInfoJson(agentKey,token,json);
        if(Objects.nonNull(jsonObject)){
            int isSuccess = jsonObject.getInt("status");
            if(isSuccess == 1) {
                JSONObject resultObj = jsonObject.getJSONObject("data");
                return new PlayerInfoObj.Builder()
                        .result(isSuccess)
                        .userId(resultObj.getString("userId"))
                        .userName(resultObj.getString("username"))
                        .status(resultObj.getString("status"))
                        .balance(resultObj.getDouble("balance"))
                        .exposure(resultObj.getDouble("exposure"))
                        .createDate(resultObj.getString("createdDate"))
                        .build();
            }
        }
        return null;
    }

    public static ListPlayerInfoObj getListPlayerInfo(String agentKey, String token, String json) throws UnsupportedEncodingException {
        JSONObject jsonObject = getListPlayerInfoJson(agentKey,token,json);
        List<PlayerInfoObj> lst = new ArrayList<>();

        if(Objects.nonNull(jsonObject)){
            int isSuccess = jsonObject.getInt("status");
            if(isSuccess == 1) {
                JSONObject resultObj = jsonObject.getJSONObject("data");
                JSONArray recordsArr = resultObj.getJSONArray("records");
                for ( int i = 0; i<recordsArr.length(); i++ ) {
                    JSONObject playerObj = recordsArr.getJSONObject(i);
                    lst.add(new PlayerInfoObj.Builder()
                            .userId(playerObj.getString("userId"))
                            .userName(playerObj.getString("username"))
                            .status(playerObj.getString("status"))
                            .balance(playerObj.getDouble("balance"))
                            .exposure(playerObj.getDouble("exposure"))
                            .createDate(playerObj.getString("createdDate"))
                            .build());
                }
                return new ListPlayerInfoObj.Builder()
                        .result(isSuccess)
                        .accountList(lst)
                        .build();
            }
        }
        return null;
    }


}
