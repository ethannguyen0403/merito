package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.createuser.CreateUser;
import agentsite.pages.components.ComponentsFactory;

public class CreateUserPage extends CreateDownLineAgentPage {
    public CreateUser createUserPage;

    public CreateUserPage(String types) {
        super(types);
        _type = types;
        createUserPage = ComponentsFactory.createUserPage(_type);
    }

    public void createUserWithDeposit(String loginId, String password, String creditLimit, String firsTimeDeposit) {
        createUserPage.createUserWithDeposit(loginId, password, creditLimit, firsTimeDeposit);
    }

    public String createUser(String loginId, String password) {
        return createUserPage.createUser(loginId, password);
    }

    public boolean isCreateUserSuccessCorrect() {
        return createUserPage.isCreateUserSuccessCorrect();
    }

}
