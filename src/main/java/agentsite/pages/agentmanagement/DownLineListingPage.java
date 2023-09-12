package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.downlinelisting.DownlineListing;
import agentsite.pages.agentmanagement.downlinelisting.EditDownlinePopup;
import agentsite.pages.components.ComponentsFactory;

public class DownLineListingPage extends CreateDownLineAgentPage {
    public EditDownlinePopup editDownlinePopup;
    public DownlineListing downlineListing;
    public DownLineListingPage(String type) {
        super(type);
        _type = type;
        downlineListing = ComponentsFactory.downlineListingPage(_type);
    }

}
