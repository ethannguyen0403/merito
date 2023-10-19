package agentsite.pages.cashmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.cashmanagement.quickdepositconfiguration.QuickDepositConfiguration;
import agentsite.pages.components.ComponentsFactory;

import java.util.List;

public class QuickDepositConfigurationPage extends HomePage {

    public QuickDepositConfiguration quickDepositConfiguration;
    public QuickDepositConfigurationPage(String types) {
        super(types);
        _type = types;
        quickDepositConfiguration = ComponentsFactory.quickDepositConfiguration(_type);
    }

    public void clickClear() {quickDepositConfiguration.clickClear();}

    public List<String> getListQuickDepositAmount() {return quickDepositConfiguration.getListQuickDepositAmount();}

    public void updateQuickDepositAmount(List<String> lstUpdateValue) {quickDepositConfiguration.updateQuickDepositAmount(lstUpdateValue);}

    public void verifyQuickDepositAmount(List<String> lstUpdateValue) {quickDepositConfiguration.verifyQuickDepositAmount(lstUpdateValue);}
}
