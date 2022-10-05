package agentsite.pages.oldUI.agentmanagement;

import agentsite.pages.oldUI.agentmanagement.CreateDownLineAgentPageOldUI;
import com.paltech.element.common.*;

public class EditDownLinePageOldUI extends CreateDownLineAgentPageOldUI {
    Label lblUserName = Label.xpath("//span[@id='username-prefix']");
    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@id='submitBtn']");
    public Button btnCancel = Button.xpath("//div[@class='paction']/button[@id='cancelBtn']");
    public String getUserName(){
        return lblUserName.getText();
    }
}
