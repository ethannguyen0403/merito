package agentsite.pages.agentmanagement.createdownlineagent.commissionsettingsection;

import agentsite.pages.HomePage;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import org.testng.Assert;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;

public class CommissionSettingSection extends HomePage {
    protected String productCode;
    private String commissionXpath ="//div[@id='%s-commission-settings']//select";
    private String productXpath = "//div[@id='%s-commission-settings']//td";

    public CommissionSettingSection(String types) {
        super(types);
    }

    protected String mapProductNameToCode(String productName) {
        return PRODUCT_NAME_TO_CODE.get(productName);
    }
    public String getCommissionSettingSectionTitle(String productName) {
        return "";
    }

    public void verifyUIDisplayCorrect(String productName){
        productCode = mapProductNameToCode(productName);
        DropDownBox ddbCommission = DropDownBox.xpath(String.format(commissionXpath,productCode));
        Label lblProduct = Label.xpath(String.format(productXpath, productCode));
        Assert.assertTrue(ddbCommission.isDisplayed(), "FAILED! Commission dropdown does not display");
        Assert.assertTrue(lblProduct.getText().trim().contains(productName), String.format("FAILED! Product %s label does not display", productName));
    }
}
