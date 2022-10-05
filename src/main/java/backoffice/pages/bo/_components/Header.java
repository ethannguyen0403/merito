package backoffice.pages.bo._components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import backoffice.controls.DropDownBox;
import backoffice.pages.bo.home.LoginPage;

public class Header extends BasePage {
    Image imgLogo = Image.xpath("//a[@class='main-logo']");
    public Button btnLogOut = Button.id("logout");
    public Label lblUsername = Label.xpath("//ul[@id='m-admin']//i[contains(@class,'fa-user')]/following::span[1]");
    public Label lblServerTime = Label.id("serverTime");
    DropDownBox ddbLanguage = DropDownBox.xpath("//div[@id='language']/span[@class='logout']", "//ul[@id='lang-list']/li/a");
    public Tab tabActive = Tab.xpath("//ul[contains(@class,'nav-tabs')]//li[contains(@class,'active')]");
    public LoginPage logout() {
        if(!btnLogOut.isDisplayed())
            DriverManager.getDriver().switchToDefaultContent();
        btnLogOut.click();
        LoginPage page = new LoginPage();
        page.txtUsername.isDisplayed(1);
        return page;
    }

    public void waitTabActive(String tab){
        if(!tabActive.getText().equalsIgnoreCase(tab)){
            tabActive.isTextDisplayed(tab,2);
        }
        if(!tabActive.getText().equalsIgnoreCase(tab)){
            DriverManager.getDriver().refresh();
        }
        tabActive.isTextDisplayed(tab,2);
    }

}
