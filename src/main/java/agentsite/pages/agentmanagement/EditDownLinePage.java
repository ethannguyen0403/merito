package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.editdownlinelisting.EditDownlineListing;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;

import java.util.List;

public class EditDownLinePage extends CreateDownLineAgentPage {
    public EditDownlineListing editDownlineListing;
    private SuccessPopup successPopup = SuccessPopup.xpath("//app-alert");
    private Button btnSubmit = Button.id("submitBtn");
    public Label lblErrorMsg = Label.id("error-msg");
    public EditDownLinePage(String types) {
        super(types);
        _type = types;
        editDownlineListing = ComponentsFactory.editDownlineListing(_type);
    }

    @Override
    public String getMessageUpdate(boolean isClose) {
        String message = successPopup.getContentMessage();
        if (isClose) {
            successPopup.close();
        }
        return message;
    }
    @Override
    public String activeInactiveProduct(String productName,boolean isActive, boolean isClose){
        productStatusSettingInforSection.updateProduct(productName,isActive);
        btnSubmit.click();
        return getMessageUpdate(isClose);
    }


    public List<String> getProductsTab(){
        return productStatusSettingInforSection.getProductsTabs();
    }

    @Override
    public void selectProduct(String productName) {
       super.selectProduct(productName);
    }

    public void updateCashBalance(String maxPlayerCredit, boolean isSubmit) {
        creditBalanceInforSection.updateCashBalance(maxPlayerCredit);
        if(isSubmit)
            btnSubmit.click();
    }
}
