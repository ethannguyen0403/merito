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

}
