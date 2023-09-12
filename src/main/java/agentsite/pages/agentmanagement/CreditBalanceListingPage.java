package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.creditbalancelisting.creditbalancelisting.CreditBalanceListing;
import agentsite.pages.components.ComponentsFactory;

public class CreditBalanceListingPage extends HomePage {
    public CreditBalanceListing creditBalanceListing;
    public CreditBalanceListingPage(String types) {
        super(types);
        creditBalanceListing = ComponentsFactory.creditBalanceListing(types);
    }

}
