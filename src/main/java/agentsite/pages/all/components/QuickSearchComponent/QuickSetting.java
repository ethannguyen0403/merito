package agentsite.pages.all.components.QuickSearchComponent;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import agentsite.controls.MenuTree;
import agentsite.pages.all.components.QuickSearch;
import agentsite.pages.all.components.SuccessPopup;

public class QuickSetting extends QuickSearch {
    public Label lblAccStatus = Label.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//span");
    public DropDownBox ddbAccStatus = DropDownBox.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//select");
    public Button btnSubmit = Button.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//button");
    public MenuTree mtSettingMenu = MenuTree.xpath("//div[contains(@class,'quickSettings')]//div[@class='item-list']//ul", "//li");

    public SuccessPopup updateStatus(String status) {
        ddbAccStatus.selectByVisibleContainsText(status);
        btnSubmit.click();
        waitingLoadingSpinner();
        return SuccessPopup.xpath("//app-alert");
    }

    public UserProfilePopup openUserProfile(){
        mtSettingMenu.clickMenu("User Profile");
        return new UserProfilePopup();
    }
}
