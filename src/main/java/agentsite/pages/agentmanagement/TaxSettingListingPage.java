package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.taxsettinglisting.TaxSettingListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.*;

import java.util.HashMap;

public class TaxSettingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.id("memberCode");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public int totalColum = 13;
    public int usernameCol = 2;
    public int loginIDCol = 3;
    public int chbCol = 5;
    public int soccerCol = 7;
    public Table tblTax = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
    public TextBox txtSoccer = TextBox.xpath("//input[@id='backSOCCER']");
    public TextBox txtTennis = TextBox.xpath("//input[@id='backTENNIS']");
    public TextBox txtCricket = TextBox.xpath("//input[@id='backCRICKET']");
    public TextBox txtBasketball = TextBox.xpath("//input[@id='backBASKETBALL']");
    public TextBox txtOther = TextBox.xpath("//input[@id='backOTHER']");
    public Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']");
    public Button btnUpdate = Button.id("bntUpdate");
    public TaxSettingListing taxSettingListing;
    public TaxSettingListingPage(String types) {
        super(types);
        _type = types;
        taxSettingListing = ComponentsFactory.taxSettingPage(_type);
    }
}
