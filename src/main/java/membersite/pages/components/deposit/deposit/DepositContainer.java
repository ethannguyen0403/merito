package membersite.pages.components.deposit.deposit;

import java.util.List;
import java.util.Map;

public class DepositContainer {
    public List<String> getListPaymentChannel() {return null;}

    public void verifyListPaymentChannelDisplayCorrect(Map<String, String> mapPaymentSetting, boolean isActive) {
    }
    public void selectPaymentType(String paymentType) {

    }

    public void deposit(String paymentType, String amount, String transactionId, boolean isUploadImg, boolean isSubmit) {}

    public void verifyDepositSuccessMessage(String brandName) {}

    public String getRefNo() {return "";}

    public List<String> getListQuickDepositAmount() {return null;}
}
