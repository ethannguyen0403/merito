package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.betsettinglisting.BetSettingListing;
import agentsite.pages.components.ComponentsFactory;

public class BetSettingListingPage extends HomePage {
    public BetSettingListing betSettingListing;

    public BetSettingListingPage(String types) {
        super(types);
        _type = types;
        betSettingListing = ComponentsFactory.betSettingPage(_type);
    }
}
