package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.ptlisting.PositionTakingListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.*;
import common.AGConstant;

public class PositionTakingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.xpath("//input[contains(@class,'user-name')]");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//td[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//td[text()='Product']//following::select[1]");
    public DropDownBox ddbLevel = DropDownBox.xpath("//span[text()='Level']//following::select[1]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public int totalColum = 16;
    public int usernameCol = 2;
    public int loginIDCol = 3;
    public int chbCol = 5;
    public int soccerCol = 8;
    public Table tblDownline = Table.xpath("//app-agency-position-taking//table[contains(@class,'directDownline table-responsive')]", totalColum);
    public Label lblUsername = Label.xpath(String.format("//td[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_USERNAME));
    public Label lblAccountStatus = Label.xpath(String.format("//td[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_ACCOUNTSTATUS));
    public Label lblLevel = Label.xpath(String.format("//span[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_LEVEL));
    public Label lblProduct = Label.xpath(String.format("//td[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_PRODUCT));
    public Label lblBreadcrumb = Label.xpath("//span[@class='downline']");
    public Button btnUpdate = Button.xpath("//div[contains(@class,'paction2')]//button[contains(@class,'pbtn')]");
    public Label lblNoRecord = Label.xpath("//td[@class='no-record']");
    public PositionTakingListing positionTakingListing;
    public PositionTakingListingPage(String types) {
        super(types);
        _type = types;
        positionTakingListing = ComponentsFactory.positionTakingListingPage(_type);
    }

}
