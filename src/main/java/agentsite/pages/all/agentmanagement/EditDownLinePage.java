package agentsite.pages.all.agentmanagement;

public class EditDownLinePage extends CreateDownLineAgentPage {
    public String getLoginIDValue() {
        return accInfoSection.tblAccountInfo.getControlOfCell(1, 1, 2, null).getText().trim();
    }

    public void inputInfoSection(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax, boolean isSubmit) {
        accInfoSection.inputInfo(password, accountStatus, firstName, lastName, phone, mobile, fax);
        if (isSubmit) ;
        btnSubmit.click();
        waitingLoadingSpinner();
    }

}
