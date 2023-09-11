package agentsite.pages.agentmanagement.createcompany;

import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import com.paltech.element.common.Icon;


public class CreateCompanyPage extends CreateDownLineAgentPage {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");

    public CreateCompanyPage(String types) {
        super(types);

    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

}
