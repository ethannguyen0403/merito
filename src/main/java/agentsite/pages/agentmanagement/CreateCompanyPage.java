package agentsite.pages.agentmanagement;

import agentsite.pages.agentmanagement.createcompany.CreateCompany;
import agentsite.pages.components.ComponentsFactory;

public class CreateCompanyPage extends CreateDownLineAgentPage {
    public CreateCompany createCompany;

    public CreateCompanyPage(String types) {
        super(types);
        _type = types;
        createCompany = ComponentsFactory.createCompanyPage(_type);
    }

}
