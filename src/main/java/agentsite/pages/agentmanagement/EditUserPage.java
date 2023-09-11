package agentsite.pages.agentmanagement;

public class EditUserPage extends CreateDownLineAgentPage {
    public EditUserPage(String types) {
        super(types);
    }

    public String getLoginIDValue() {
        return accountInforSection.tblAccountInfo.getControlOfCell(1, 1, 2, null).getText().trim();
    }
}
