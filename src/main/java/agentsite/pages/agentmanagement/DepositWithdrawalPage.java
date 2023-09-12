package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.depositwithdrawal.*;
import agentsite.pages.components.ComponentsFactory;

public class DepositWithdrawalPage extends HomePage {
    public DepositWithdraw depositWithdraw;
    public DepositWithdrawalPage(String types) {
        super(types);
        depositWithdraw = ComponentsFactory.depositWithdraw(types);
    }

}
