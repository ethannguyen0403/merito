package membersite.utils.casino;

import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PragmaticUtils extends CasinoUtils{

    private String getMgckeyToken(){
        //extract Mgckey in url for api from url
        String url = DriverManager.getDriver().getCurrentUrl();
        Pattern pattern = Pattern.compile("mgckey=([^&]+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("mgckey parameter not found in the URL.");
        }
        return null;
    }

    protected double getBalance(){
        double balance = -1;
        String endPoint = String.format("%s/reloadBalance.do?mgckey=%s", DriverManager.getDriver().getCurrentUrl().split("/html")[0], getMgckeyToken());
        try {
            //res is not in json format so use HttpResponse to get res value
            HttpResponse response = WSUtils.sendGETRequest(endPoint);
            String res =  EntityUtils.toString(response.getEntity());
            //use regex to get balance from res
            Pattern pattern = Pattern.compile("balance=([0-9]+.[0-9]+)");
            Matcher matcher = pattern.matcher(res);
            matcher.find();
            balance = Double.valueOf(matcher.group(1));
        }catch (Exception e){
        }
       return balance;
    }
}
