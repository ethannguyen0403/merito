package agentsite.pages.all.agentmanagement;

public class EditUserPage extends CreateDownLineAgentPage {
    public String getLoginIDValue() {
        return accInfoSection.tblAccountInfo.getControlOfCell(1, 1, 2, null).getText().trim();
    }
}
