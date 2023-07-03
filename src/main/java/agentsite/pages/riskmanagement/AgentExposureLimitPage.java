package agentsite.pages.riskmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class AgentExposureLimitPage extends HomePage {
    public Label lblUsername = Label.xpath("//div[@id='search-region']/table//tr[1]/td[1]/label");
    public Button btnSubmit = Button.xpath("//div[@id='search-region']/table//tr[1]//button");
    public TextBox txtUsername = TextBox.xpath("//div[@id='search-region']/table//tr[1]//input");
    public Table tblDownline = Table.xpath("//div[contains(@class,'wrap full-width')]//table", 8);

    public AgentExposureLimitPage(String types) {
        super(types);
    }

    public void search(String username) {
        if (!username.isEmpty()) {
            txtUsername.sendKeys(username);
        }
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void clickUserName(String userName) {
        tblDownline.getControlByDifferentTextOnRow(userName, 1, 2, 1, "span", false).click();
        waitingLoadingSpinner();
    }
}
