package agentsite.pages.agentmanagement;

import com.paltech.element.common.Label;

public class CreateCompanyPage extends CreateDownLineAgentPage {
    public Label lblCashOut = Label.xpath("//app-agency-account-ui//div[contains(text(), 'Cash Out')]");
    public Label lblCashOutPS38 = Label.xpath("//app-agency-account-ui//div[contains(text(), 'Cash Out')]/following::div[3]");


    public CreateCompanyPage(String types) {
        super(types);
        _type = types;
    }

    @Override
    public void selectProduct(String productName) {
        super.selectProduct(productName);
    }
}
