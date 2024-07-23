package agentsite.pages.components;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.proteus.createdownlineagent.PositionTakingSectionPS38;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import controls.Table;

import static common.AGConstant.PS38;

public class PositionTakingPopup {
    public DropDownBox ddbProduct = DropDownBox.xpath("//app-position-taking//div[@class='row productSettingsTitle']/select");
    public Table tblPT = Table.xpath("//app-product-status//table[@class='ptable report']", 2);
    public Button btnSave = Button.xpath("//button[@class='btn btn-submit']");
    public Button btnYes = Button.xpath("//button[@class='btn']");
    public Button btnCancel = Button.xpath("//button[@class='btn btn-cancel']");

    public PositionTakingSectionPS38 filterPS38Product() {
        HomePage.waitingLoadingSpinner();
        ddbProduct.selectByVisibleText(PS38);
        return new PositionTakingSectionPS38();
    }

    public void confirmPopup(){
        btnSave.click();
        HomePage.waitingLoadingSpinner();
        btnYes.isDisplayed();
        btnYes.click();
        HomePage.waitingLoadingSpinner();
        //reconfirm if check box Check all is ticked
        if(btnYes.isDisplayed())
            btnYes.click();
       btnCancel.click();
    }
}
