package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.editdownlinelisting.EditDownlineListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.Button;

public class EditDownLinePage extends CreateDownLineAgentPage {
    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@id='submitBtn']");
    public EditDownlineListing editDownlineListing;
    public EditDownLinePage(String types) {
        super(types);
        _type = types;
        editDownlineListing = ComponentsFactory.editDownlineListing(_type);
    }

}
