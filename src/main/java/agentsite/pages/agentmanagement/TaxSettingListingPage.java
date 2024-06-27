package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.taxsettinglisting.TaxSettingListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.TextBox;

import java.util.List;

public class TaxSettingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.id("memberCode");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public TaxSettingListing taxSettingListing;
    public TaxSettingListingPage(String types) {
        super(types);
        _type = types;
        taxSettingListing = ComponentsFactory.taxSettingPage(_type);
    }
    public List<String> getProducts(){
        return taxSettingListing.ddbProduct.getOptions();
    }

    public void verifyUITaxSetting(String userCode) {
        taxSettingListing.verifyUITaxSetting(userCode);
    }

}
