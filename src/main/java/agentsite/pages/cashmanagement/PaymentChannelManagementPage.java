package agentsite.pages.cashmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.cashmanagement.paymentchannelmanagement.PaymentChannelManagement;
import agentsite.pages.components.ComponentsFactory;

import java.util.*;


public class PaymentChannelManagementPage extends HomePage {
    public PaymentChannelManagement paymentChannelManagement;
    public PaymentChannelManagementPage(String types) {
        super(types);
        _type = types;
        paymentChannelManagement = ComponentsFactory.paymentChannelManagement(_type);
    }

    public Map<String, String> getListPaymentStatus() {
        return paymentChannelManagement.getListPaymentStatus();
    }

    public void verifyListChannelDisplayCorrect() {
        paymentChannelManagement.verifyListChannelDisplayCorrect();
    }

    public PaymentHistoryPopup clickViewLink(String paymentChannel) {
        paymentChannelManagement.clickViewLink(paymentChannel);
        return new PaymentHistoryPopup();
    }
}
