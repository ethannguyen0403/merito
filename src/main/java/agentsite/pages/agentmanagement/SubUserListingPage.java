package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.subuserlisting.SubUserListing;
import agentsite.pages.components.ComponentsFactory;

public class SubUserListingPage extends HomePage {
    public SubUserListing subUserListing;
    public SubUserListingPage(String types) {
        super(types);
        _type = types;
        subUserListing = ComponentsFactory.subUserListingPage(_type);
    }

}
