package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.commissionlisting.CommissionSettingListing;
import agentsite.pages.components.ComponentsFactory;

public class CommissionSettingListingPage extends HomePage {
    public CommissionSettingListing commissionSettingListing;
    public CommissionSettingListingPage(String types) {
        super(types);
        _type = types;
        commissionSettingListing = ComponentsFactory.commissionSettingListing(_type);
    }

}
