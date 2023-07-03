package api.utils.B2B;

import com.paltech.utils.WSUtils;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2BWSUtils extends WSUtils {
    public static JSONObject getPOSTJSONObjectWithTokenHeaders(String url, String headers, String agentKey, String token, String json) {
        try {
            HttpResponse response = sendPOSTRequestWithTokenHeader(url, headers, agentKey, token, json);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder strBuilder = new StringBuilder();
            String line = "";

            while ((line = rd.readLine()) != null) {
                strBuilder.append(line);
            }

            return new JSONObject(strBuilder.toString());
        } catch (IOException var9) {
            System.out.println("Exception: IOException occurs at getGETJSONResponse");
            return null;
        }
    }

    public static HttpResponse sendPOSTRequestWithTokenHeader(String url, String header, String agentKey, String token, String json) throws IOException {
        B2BHelper helper = new B2BHelper();
        return helper.sendPostRequestWithTokenHeaders(url, header, agentKey, token, json);
    }

    public static JSONObject getPOSTJSONObjectWithHeaders(String url, String headers, String agentKey, String authorization, String json) {
        try {
            HttpResponse response = sendPOSTRequestWithHeader(url, headers, agentKey, authorization, json);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder strBuilder = new StringBuilder();
            String line = "";

            while ((line = rd.readLine()) != null) {
                strBuilder.append(line);
            }

            return new JSONObject(strBuilder.toString());
        } catch (IOException var9) {
            System.out.println("Exception: IOException occurs at getGETJSONResponse");
            return null;
        }
    }

    public static HttpResponse sendPOSTRequestWithHeader(String url, String header, String agentKey, String authorization, String json) throws IOException {
        B2BHelper helper = new B2BHelper();
        return helper.sendPostRequestWithHeaders(url, header, agentKey, authorization, json);
    }

    public static JSONObject getPOSTJSONObjectWithSecretHeaders(String url, String headers, String agentKey, String authorization) {
        try {
            HttpResponse response = sendPOSTJSONObjectWithSecretHeaders(url, headers, agentKey, authorization);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder strBuilder = new StringBuilder();
            String line = "";

            while ((line = rd.readLine()) != null) {
                strBuilder.append(line);
            }

            return new JSONObject(strBuilder.toString());
        } catch (IOException var9) {
            System.out.println("Exception: IOException occurs at getGETJSONResponse");
            return null;
        }
    }

    public static HttpResponse sendPOSTJSONObjectWithSecretHeaders(String url, String header, String agentKey, String authorization) throws IOException {
        B2BHelper helper = new B2BHelper();
        return helper.sendPostRequestGetAuthenticate(url, header, agentKey, authorization);
    }

}
