package backoffice.pages.bo.home;

import backoffice.pages.bo._components.LeftMenu;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;


public class HomePage extends LeftMenu {
    public Label lblPageTitle = Label.id("bo-page-title");
    public Label lblSuccessAlert = Label.xpath("//div[@class='message-box']//div[contains(@class,'alert-success')]/span");
    public Label lblErrorAlert = Label.xpath("//div[contains(@class,'error-message')]");
    public Label lblDangerAlert = Label.xpath("//div[contains(@class,'alert-danger')]/span");
    public Button btnCloseActiveTab = Button.xpath("//ul[contains(@class,'nav nav-tabs')]//a[contains(@class,'nav-link active')]//span[contains(@class,'bs-remove-tab')]");

    public void closeActiveTab() {
        btnCloseActiveTab.click();
    }

}
