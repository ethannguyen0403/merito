package api.utils.B2B;

import com.paltech.constant.Helper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class B2BHelper extends Helper {

    public HttpResponse sendPostRequestWithHeaders(String url, String contentType, String agentKey, String authorization, String json)  {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            if (json != null) {
                StringEntity stringEntity = new StringEntity(json);
                post.setEntity(stringEntity);
            }

          //  post.setHeader("Cookie", cookies);
            if (agentKey != null) {
                post.setHeader("AgentKey", agentKey);
            }
            if (authorization != null) {
                post.setHeader("Authorization", authorization);
            }
            if (contentType != null) {
                post.setHeader("Content-Type", contentType);
            }

            return client.execute(post);
        } catch (Exception var9) {
            System.err.println(String.format("ERROR: Exception occurs at sendPostRequestWithCookies page bypass '%s' security code by '%s'", url, var9.getMessage()));
            return null;
        }
    }
    public HttpResponse sendPostRequestWithTokenHeaders(String url, String contentType, String agentKey, String token, String json){
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            if (json != null) {
                StringEntity stringEntity = new StringEntity(json);
                post.setEntity(stringEntity);
            }

            //  post.setHeader("Cookie", cookies);
            if (agentKey != null) {
                post.setHeader("agentKey", agentKey);
            }
            if (token != null) {
                post.setHeader("agentToken", token);
            }
            if (contentType != null) {
                post.setHeader("Content-Type", contentType);
            }

            return client.execute(post);
        } catch (Exception var9) {
            System.err.println(String.format("ERROR: Exception occurs at sendPostRequestWithCookies page bypass '%s' security code by '%s'", url, var9.getMessage()));
            return null;
        }
    }
    public HttpResponse sendPostRequestGetAuthenticate(String url, String contentType, String agentKey, String secretKey){
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);


            //  post.setHeader("Cookie", cookies);
            if (agentKey != null) {
                post.setHeader("AgentKey", agentKey);
            }
            if (secretKey != null) {
                post.setHeader("secretKey", secretKey);
            }
            if (contentType != null) {
                post.setHeader("Content-Type", contentType);
            }

            return client.execute(post);
        } catch (Exception var9) {
            System.err.println(String.format("ERROR: Exception occurs at sendPostRequestWithCookies page bypass '%s' security code by '%s'", url, var9.getMessage()));
            return null;
        }
    }
}
