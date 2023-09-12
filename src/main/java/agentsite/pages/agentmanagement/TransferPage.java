package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.transfer.Transfer;
import agentsite.pages.components.ComponentsFactory;

public class TransferPage extends HomePage {
    public Transfer transferPage;
    public TransferPage(String types) {
        super(types);
        _type = types;
        transferPage = ComponentsFactory.transferPage(_type);
    }
}
