package agentsite.pages.agentmanagement;

import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.commissionlisting.CommissionSettingListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.*;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CommissionSettingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.id("userName");
    public DropDownBox ddbAccountStatus = DropDownBox.id("status");
    public DropDownBox ddbProduct = DropDownBox.id("product");
    public DropDownBox ddbLevel = DropDownBox.id("level");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public Label lblBreadcrumb = Label.xpath("//div[contains(@class,'downline-bar')]");
    public Button btnUpdate = Button.xpath("//button[contains(@class,'directDownline')]");
    public Label lblMemberBreadcrumb = Label.xpath("//div[@id='commission-member']/div[@class='psection']");
    public int colUsername = 2;
    public int colLoginID = 2;
    public int chbCol = 5;
    public int tblAgentCommissionTotalCol = 10;
    public Table tblAgentCommission = Table.xpath("//div[@class='downline-listings']//table", tblAgentCommissionTotalCol);
    public int tblMemberCommissionTotalCol = 11;
    public Table tblMemberCommission = Table.xpath("//div[@id='commission-member']//table", tblMemberCommissionTotalCol);

    public CommissionSettingListing commissionSettingListing;
    public CommissionSettingListingPage(String types) {
        super(types);
        _type = types;
        commissionSettingListing = ComponentsFactory.commissionSettingListing(_type);
    }

}
