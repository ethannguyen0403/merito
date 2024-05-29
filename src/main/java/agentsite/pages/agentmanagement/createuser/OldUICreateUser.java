package agentsite.pages.agentmanagement.createuser;


import common.AGConstant;

public class OldUICreateUser extends CreateUser {

    public OldUICreateUser(String types) {
        super(types);
    }

    public void createUserWithDeposit(String loginId, String password, String creditLimit, String firsTimeDeposit) {
        accountInforSection.inputInfo(loginId, password, "");
        cashBalanceInforSection.inputCashBalanceInfo(creditLimit, firsTimeDeposit);
        getSubmitBtn().click();
        waitingLoadingSpinner();
    }

    public String createUser(String loginId, String password) {
        accountInforSection.inputInfo(loginId, password, "Active");
        getSubmitBtn().click();
        waitingLoadingSpinner();
        successPopup.isDisplayed();
//        successPopup.waitForElementToBePresent(successPopup.getLocator(), 5);
        return loginId;
    }

    public boolean isCreateUserSuccessCorrect() {
        return successPopup.getContentMessage().equals(AGConstant.AgencyManagement.CreateUser.CREATE_USER_SUCCEESS_MESSAGE);
    }

}
