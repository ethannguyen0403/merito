package agentsite.pages.components;

import agentsite.controls.MenuTree;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;

public class QuickSetting extends QuickSearch {
    public Label lblAccStatus = Label.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//span");
    public DropDownBox ddbAccStatus = DropDownBox.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//select");
    public Button btnSubmit = Button.xpath("//div[contains(@class,'quickSettings')]//div[@class='setting-acc-status']//button");
    public MenuTree mtSettingMenu = MenuTree.xpath("//div[contains(@class,'quickSettings')]//div[@class='item-list']//ul", "//li");

    public SuccessPopup updateStatus(String status) {
        ddbAccStatus.selectByVisibleContainsText(status);
        btnSubmit.click();
        return SuccessPopup.xpath("//app-alert");
    }

    public UserProfilePopup openUserProfile() {
        mtSettingMenu.clickMenu("User Profile");
        return new UserProfilePopup();
    }
}
