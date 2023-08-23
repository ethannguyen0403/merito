package agentsite.pages.agentmanagement.createuser;


import common.AGConstant;

public class NewUICreateUser extends CreateUser {

    public NewUICreateUser(String types) {
        super(types);
    }

    public void createUserWithDeposit(String loginId, String password, String creditLimit, String firsTimeDeposit) {
        accountInforSection.inputInfo(password, "");
        cashBalanceInforSection.inputCashBalanceInfo(firsTimeDeposit);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public String createUser(String loginId, String password) {
        String username = "";
        username = accountInforSection.getUserName();
        accountInforSection.txtPassword.sendKeys(password);
        waitingLoadingSpinner();
        btnSubmit.click();
        waitingLoadingSpinner();
        return username;
    }

    public boolean isCreateUserSuccessCorrect() {
        return successPopup.getContentMessage().equals(AGConstant.AgencyManagement.CreateUser.MEMBER_CREATE_SUCCEESS_MESSAGE);
    }
}
