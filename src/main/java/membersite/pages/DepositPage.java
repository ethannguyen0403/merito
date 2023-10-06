package membersite.pages;

import agentsite.controls.DropDownList;
import com.paltech.element.common.Label;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.deposit.DepositContainer;
import java.util.List;
import java.util.Map;

public class DepositPage extends HomePage {

    public Label lblTitle = Label.xpath("//app-deposit//h5");
    private DropDownList ddlMenuTab = DropDownList.xpath("//app-payments//div[@class='top-navbar-container']","//ul[@class='navbar-nav align-items-center']//li");
    public DepositContainer depositContainer;

    public DepositPage(String types) {
        super(types);
        depositContainer = ComponentsFactory.depositContainerObject(types);
    }

    public List<String> getListPaymentChannel() {
        return depositContainer.getListPaymentChannel();
    }

    public void verifyListPaymentChannelDisplayCorrect(Map<String, String> mapPaymentSetting) { depositContainer.verifyListPaymentChannelDisplayCorrect(mapPaymentSetting);}

    public void switchTab(String tabName) {
        ddlMenuTab.clickMenu(tabName.toUpperCase());
        waitPageLoad();
    }

    public void selectPaymentType(String paymentType) {
        depositContainer.selectPaymentType(paymentType);
    }

    public void deposit(String paymentType, String amount, String transactionId, boolean isUploadImg, boolean isSubmit) { depositContainer.deposit(paymentType, amount, transactionId, isUploadImg, isSubmit);}

    public void verifyDepositSuccessMessage(String brandName) { depositContainer.verifyDepositSuccessMessage(brandName);}
}
