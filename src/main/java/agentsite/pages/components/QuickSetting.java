package agentsite.pages.components;

import agentsite.controls.MenuTree;
import agentsite.pages.HomePage;
import agentsite.pages.components.quicksearch.QuickSearch;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;

public class QuickSetting extends QuickSearch {
    public Label lblAccStatus = Label.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//span");
    public DropDownBox ddbAccStatus = DropDownBox.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//select");
    public Button btnSubmit = Button.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//button");
        public MenuTree mtSettingMenu = MenuTree.xpath("//div[contains(@class,'quickSettings')]//div[contains(@class,'item-list')]//ul", "//li");

//    public QuickSetting(String types) {
//        super(types);
//    }

    public SuccessPopup updateStatus(String status) {
        ddbAccStatus.selectByVisibleContainsText(status);
        btnSubmit.click();
        return SuccessPopup.xpath("//app-alert");
    }

    public UserProfilePopup openUserProfile() {
        mtSettingMenu.clickMenu("User Profile");
        return new UserProfilePopup();
    }

    public CommissionPopup openCommission(){
        mtSettingMenu.clickMenu("Commission");
        HomePage.waitingLoadingSpinner();
        return new CommissionPopup();
    }

    public PositionTakingPopup openPositionTaking(){
        mtSettingMenu.clickMenu("Position Taking");
        HomePage.waitingLoadingSpinner();
        return new PositionTakingPopup();
    }
}
