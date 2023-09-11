package agentsite.pages.agentmanagement;

import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.objects.agent.BetSetting;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.betsettinglisting.BetSettingListing;
import agentsite.pages.components.ComponentsFactory;
import agentsite.ultils.agencymanagement.BetSettingUtils;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BetSettingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.id("userName");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public DropDownBox ddbLevel = DropDownBox.id("userLevel");
    public Button btnSubmit = Button.xpath("//button[@class='pbtn search']");
    public Label lblTitlePage = Label.xpath("//app-title-dashboard//div[@class='title']//label");
    public int totalColum = 10;
    public int usernameCol = 2;
    public int chbCol = 6;
    public int soccerCol = 10;
    public Row sportsRow = Row.xpath("//div[@id='betSetting']//table[@class='ptable info ng-star-inserted']/tr");
    public Table tblDownline = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
    public TextBox txtMinBet = TextBox.xpath("(//table[@class='ptable info search-region']//input)[1]");
    public TextBox txtMaxBet = TextBox.xpath("(//table[@class='ptable info search-region']//input)[2]");
    public TextBox txtMaxLiabilityPerMarket = TextBox.xpath("(//table[@class='ptable info search-region']//input)[3]");
    public TextBox txtMaxWinPerMarket = TextBox.xpath("(//table[@class='ptable info search-region']//input)[4]");
    public Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']");
    public Button btnUpdate = Button.xpath("//button[contains(@class,'directDownline')]");

    public BetSettingListing betSettingListing;

    public BetSettingListingPage(String types) {
        super(types);
        _type = types;
        betSettingListing = ComponentsFactory.betSettingPage(_type);
    }
}
