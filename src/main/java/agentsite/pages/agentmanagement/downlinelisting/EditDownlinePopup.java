package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection.ProductStatusSettingSection;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;

public class EditDownlinePopup extends EditDownLinePage {
    public Label lblTitle;
    public Button btnOK = Button.xpath("//button[contains(@class,'btn btn-warning')]");
    public ProductStatusSettingSection productStatusSettingSection;
    public Button btnSubmit = Button.xpath("//button[contains(@id,'submitBtn')]");
    private String _xPath = "//div[@id='account']//app-agency-account-ui";
    private Label lblMessage = Label.xpath("//div[contains(@class,'modal-body-fit-with-content')]");

    public EditDownlinePopup(String type) {
        super(type);
        _type = type;
        productStatusSettingSection = ComponentsFactory.productStatusSettingInfoObject(_type);

    }

    public String getMessage() {
        return lblMessage.getText();

    }

    public String getTitle() {
        lblTitle.isDisplayed(1);
        return lblTitle.getText();
    }

}
