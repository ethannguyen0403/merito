package backoffice.pages.bo.home;

import backoffice.pages.bo._components.LeftMenu;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Actions;


public class HomePage extends LeftMenu {
    public Label lblPageTitle = Label.id("bo-page-title");
    public Label lblSuccessAlert = Label.xpath("//div[@class='message-box']//div[contains(@class,'alert-success')]/span");
    public Label lblDangerAlert = Label.xpath("//div[contains(@class,'alert-danger')]/span");
    public Button btnCloseActiveTab = Button.xpath("//a[contains(@class,'nav-link active')]//span[contains(@class,'bs-remove-tab')]");

    public void closeActiveTab() {
        DriverManager.getDriver().switchToDefaultContent();
        Rectangle reg = btnCloseActiveTab.getWebElement().getRect();
        Actions actions = new Actions(DriverManager.getDriver());
        System.out.println("X " + reg.getX()+reg.getWidth());
        System.out.println("Y " + reg.getY());
        actions.moveByOffset(reg.getX()+reg.getWidth(), reg.getY()).click();
        actions.build().perform();
    }

}
