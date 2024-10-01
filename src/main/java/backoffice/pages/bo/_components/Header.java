package backoffice.pages.bo._components;

import backoffice.controls.DropDownBox;
import backoffice.pages.bo.adminmanagement.AdminProfilePage;
import backoffice.pages.bo.home.LoginPage;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import org.openqa.selenium.By;

public class Header extends BasePage {
    public Button btnLogOut = Button.id("logout");
    public Label lblUsername = Label.xpath("//ul[@id='m-admin']//i[contains(@class,'fa-user')]/following::span[contains(text(),'Welcome')]");
    public Label lblServerTime = Label.id("serverTime");
    public Tab tabActive = Tab.xpath("//ul[contains(@class,'nav-tabs')]//li[contains(@class,'active')]");
    Image imgLogo = Image.xpath("//a[@class='main-logo']");
    DropDownBox ddbLanguage = DropDownBox.xpath("//div[@id='language']/span[@class='logout']", "//ul[@id='lang-list']/li/a");
    DropDownBox ddWelcomeUser = DropDownBox.xpath("//ul[@id='m-admin']//i[contains(@class,'fa-user')]/following::span[contains(text(),'Welcome')]","");
    public LoginPage logout() {
        if (!btnLogOut.isDisplayed())
            DriverManager.getDriver().switchToDefaultContent();
        btnLogOut.click();
        LoginPage page = new LoginPage();
        page.txtUsername.isDisplayed(1);
        return page;
    }

    public void waitTabActive(String tab) {
        if (!tabActive.getText().equalsIgnoreCase(tab)) {
            tabActive.isTextDisplayed(tab, 2);
        }
        if (!tabActive.getText().equalsIgnoreCase(tab)) {
            DriverManager.getDriver().refresh();
        }
        tabActive.isTextDisplayed(tab, 2);
    }

    /**
     *
     * @param name: The pages below Welcome User label
     */
    void clickPage(String name){
        lblUsername.click();
        Button btnPage = Button.xpath(String.format("//popover-container//span[contains(text(),'%s')]//parent::button",name));
        btnPage.waitForElementToBePresent(btnPage.getLocator(),5);
        btnPage.click();
    }
    public AdminProfilePage navigateUserProfile(){
        clickPage("User Profile");
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        AdminProfilePage adminProfilePage = new AdminProfilePage();
        adminProfilePage.waitSpinIcon();
        return adminProfilePage;
    }
}
