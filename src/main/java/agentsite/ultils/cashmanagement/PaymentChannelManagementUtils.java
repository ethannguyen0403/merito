package agentsite.ultils.cashmanagement;

import agentsite.objects.agent.PaymentChannel;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseTest.domainCashURL;

public class PaymentChannelManagementUtils {

    public static List<PaymentChannel> getPaymentChannelJson() {
        List<PaymentChannel> lstPayment = new ArrayList<PaymentChannel>();
        String api = String.format("%s/agent-services-new/payment-channel/list", domainCashURL);
        //  JSONObject jsonObject = WSUtils.getGETJSONResponse(api, Configs.HEADER_JSON_CHARSET,Configs.HEADER_JSON);//, DriverManager.getDriver().getCookies().toString(),true);
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lstPayment.add(new PaymentChannel.Builder()
                        .id(jsonObject.getInt("id"))
                        .code(jsonObject.getString("code"))
                        .name(jsonObject.getString("name"))
                        .status(jsonObject.getString("status"))
                        .build());
            }

        } else {
            System.err.println("ERROR: jsonObject is null at getProfile");
        }
        return lstPayment;
    }

    public static List<PaymentChannel> getPaymentChannelList(String status) {
        List<PaymentChannel> lstPaymentFilter = new ArrayList<>();
        List<PaymentChannel> lstPayment = getPaymentChannelJson();
        for (int i = 0; i < lstPayment.size(); i++) {
            if (lstPayment.get(i).getPaymentStatus().equalsIgnoreCase(status)) {
                lstPaymentFilter.add(lstPayment.get(i));
            }
        }
        return lstPaymentFilter;
    }
}
