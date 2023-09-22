package agentsite.pages.agentmanagement.createuser;

import agentsite.pages.agentmanagement.CreateDownLineAgentPage;

public class CreateUser extends CreateDownLineAgentPage {

    public CreateUser(String types) {
        super(types);
    }

    public void createUserWithDeposit(String loginId, String password, String creditLimit, String firsTimeDeposit) {}

    public String createUser(String loginId, String password) {return null;}

    public boolean isCreateUserSuccessCorrect() {return false;}

}
