package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.editdownlinelisting.EditDownlineListing;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.BaseElement;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;

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

    public void selectProduct(String productName) {
        Label lblProduct = Label.xpath(String.format("//span[text()='%s']", productName));
        lblProduct.click();
    }

    public void updateProteusPTMarket(String sportName, Map<String, String> betType, String ps38TabName, String position, String amount){
        positionTakingSectionPS38.expandSport(sportName, true);
        DropDownBox ddbPosition = positionTakingSectionPS38.getDropDownPTControl(sportName, betType, ps38TabName, position, "select");
        new BaseElement(ddbPosition.getLocator()).scrollToThisControl(false);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new BaseElement(ddbPosition.getLocator()).click();new BaseElement(ddbPosition.getLocator()).click();
        ddbPosition.selectByVisibleText(amount);
    }

}
