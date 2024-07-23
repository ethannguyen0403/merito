package agentsite.pages.components;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection.CommissionSectionPS38;
import agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection.CommissionSectionPS38Agent;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;

import java.util.List;

import static common.AGConstant.PS38;

public class CommissionPopup {

     Button btnSave =Button.xpath("//button[@class='btn btn-submit']");
    public DropDownBox ddbProduct = DropDownBox.xpath("//app-commission//div[@class='row productSettingsTitle']/select");
    // ddbOddGroups only appears at CO level
    public DropDownBox ddbOddGroups = DropDownBox.xpath("//app-commission//div[contains(@class, 'groupActive')]");

    public CommissionSectionPS38 filterPS38Product(){
        ddbProduct.selectByVisibleText(PS38);
        return new CommissionSectionPS38Agent();
    }

    public void savePopup(){
        btnSave.click();
        HomePage.waitingLoadingSpinner();
    }

    public List<String> getProductList(){
        return ddbProduct.getOptions();
    }

}
