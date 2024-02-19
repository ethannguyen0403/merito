package agentsite.pages.agentmanagement;



public class CreateCompanyPage extends CreateDownLineAgentPage {

    public CreateCompanyPage(String types) {
        super(types);
        _type = types;
    }

    @Override
    public void selectProduct(String productName) {
        super.selectProduct(productName);
    }
}
