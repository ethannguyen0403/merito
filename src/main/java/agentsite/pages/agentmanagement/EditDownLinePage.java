package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.editdownlinelisting.EditDownlineListing;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.SuccessPopup;

import java.util.List;
import java.util.Map;

public class EditDownLinePage extends CreateDownLineAgentPage {
    public EditDownlineListing editDownlineListing;
    private SuccessPopup successPopup = SuccessPopup.xpath("//app-alert");
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
    public void updateProducts(Map<String, Boolean> products){
        productStatusSettingInforSection.updateProducts(products);
        btnSubmit.click();
        getMessageUpdate(true);
    }

    public List<String> getProductsTab(){
        return productStatusSettingInforSection.getProductsTabs();
    }


}
