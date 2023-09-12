package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.ptlisting.PositionTakingListing;
import agentsite.pages.components.ComponentsFactory;

public class PositionTakingListingPage extends HomePage {
    public PositionTakingListing positionTakingListing;
    public PositionTakingListingPage(String types) {
        super(types);
        _type = types;
        positionTakingListing = ComponentsFactory.positionTakingListingPage(_type);
    }

}
