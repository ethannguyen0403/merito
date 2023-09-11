package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.editdownlinelisting.EditDownlineListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.Button;

public class EditDownLinePage extends CreateDownLineAgentPage {
    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@id='submitBtn']");
    public EditDownlineListing editDownlineListing;
    public EditDownLinePage(String types) {
        super(types);
        _type = types;
        editDownlineListing = ComponentsFactory.editDownlineListing(_type);
    }

    public String getLoginIDValue() {
        return accountInforSection.tblAccountInfo.getControlOfCell(1, 1, 2, null).getText().trim();
    }

//    public void inputInfoSection(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax, boolean isSubmit) {
//        accInfoSection.inputInfo(password, accountStatus, firstName, lastName, phone, mobile, fax);
//        if (isSubmit) ;
//        btnSubmit.click();
//        waitingLoadingSpinner();
//    }

//    public void setTransaction(boolean isDaily, List<String> days, boolean isSubmit) {
//        transferSettingSection.setTransfer(isDaily, days);
//        btnSubmit.click();
//    }

}
