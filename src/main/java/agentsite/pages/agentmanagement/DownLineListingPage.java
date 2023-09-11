package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.downlinelisting.DownlineListing;
import agentsite.pages.agentmanagement.downlinelisting.EditDownlinePopup;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.SecurityPopup;
import com.paltech.element.common.*;

public class DownLineListingPage extends CreateDownLineAgentPage {
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public EditDownlinePopup editDownlinePopup;
    public DropDownBox ddbAccountStatus = DropDownBox.id("status");
    public DropDownBox ddbLevel = DropDownBox.id("userLevel");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public Button btnSubmit = Button.id("submitBtn");
    public Button btnOK = Button.xpath("//button[text()='OK']");
    public int userCodeCol = 2;
    public int loginIDCol = 3;
    public Label lblErrorMsg = Label.xpath("//div[@class='paction']/span[@id='error-msg']");
    public Label lblNoRecord = Label.xpath("//table[contains(@class,'ptable report')]//span[contains(@class,'no-record')]");
    private int totalColumn = 19;
    public Table tblDowlineListing = Table.xpath("//table[contains(@class,'ptable report')]", totalColumn);
    public DownlineListing downlineListing;
    public DownLineListingPage(String type) {
        super(type);
        _type = type;
        downlineListing = ComponentsFactory.downlineListingPage(_type);
    }

}
