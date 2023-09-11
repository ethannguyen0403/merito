package agentsite.pages.agentmanagement.createuser;

import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.*;


public class CreateUser extends CreateDownLineAgentPage {
    public CreateUser createUserPage;
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public CreateUser(String types) {
        super(types);
        _type = types;
        createUserPage = ComponentsFactory.createUserPage(_type);
    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public void createUserWithDeposit(String loginId, String password, String creditLimit, String firsTimeDeposit) {}

    public String createUser(String loginId, String password) {return null;}

    public boolean isCreateUserSuccessCorrect() {return false;}

}
