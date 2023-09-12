package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.taxsettinglisting.TaxSettingListing;
import agentsite.pages.components.ComponentsFactory;

public class TaxSettingListingPage extends HomePage {
    public TaxSettingListing taxSettingListing;
    public TaxSettingListingPage(String types) {
        super(types);
        _type = types;
        taxSettingListing = ComponentsFactory.taxSettingPage(_type);
    }

}
