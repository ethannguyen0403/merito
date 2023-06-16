package agentsite.pages.agentmanagement;

import com.paltech.element.common.Button;

import java.util.List;

public class EditDownLinePage extends CreateDownLineAgentPage {
    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@id='submitBtn']");
    public EditDownLinePage(String types) {
        super(types);
    }
    public String getLoginIDValue() {
        return accInfoSection.tblAccountInfo.getControlOfCell(1, 1, 2, null).getText().trim();
    }

    public void inputInfoSection(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax, boolean isSubmit) {
        accInfoSection.inputInfo(password, accountStatus, firstName, lastName, phone, mobile, fax);
        if (isSubmit) ;
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void setTransaction(boolean isDaily, List<String> days, boolean isSubmit){
        transferSettingSection.setTransfer(isDaily,days);
        btnSubmit.click();
    }

}
