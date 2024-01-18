package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.proteus.ptlisting.PositionTakingListingPS38;
import agentsite.pages.agentmanagement.ptlisting.PositionTakingListing;
import agentsite.pages.components.ComponentsFactory;

public class PositionTakingListingPage extends HomePage {
    public PositionTakingListing positionTakingListing;
    public PositionTakingListingPS38 positionTakingListingPS38;
    public PositionTakingListingPage(String types) {
        super(types);
        _type = types;
        positionTakingListing = ComponentsFactory.positionTakingListingPage(_type);
        positionTakingListingPS38 = new PositionTakingListingPS38();
    }
}
