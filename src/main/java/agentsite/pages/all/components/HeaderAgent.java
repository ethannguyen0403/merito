package agentsite.pages.all.components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import agentsite.controls.DropDownList;
import agentsite.controls.LefMenuIcon;
import agentsite.pages.all.home.AccountBalancePage;
import agentsite.pages.all.home.LoginPage;
import agentsite.pages.all.home.UpdateSecurityCodePage;

import static agentsite.common.AGConstant.PASSWORD;
import static agentsite.common.AGConstant.SECURITY_CODE;

public class HeaderAgent extends BasePage {
    public Button btnSignOut = Button.xpath("//button[contains(@class,'pbtn sign-out')]");
    public Icon iconHeaderMenu = Icon.xpath("//span[contains(@class,'header-btn')]");
    public Icon iconEmail = Icon.xpath("//span[@class='mail-btn fa fa-envelope']");
    public DropDownBox ddbLanguage = DropDownBox.id("language");
    public Label lblTimeZone = Label.xpath("//div[contains(@class,'time-contain')]");
    public Icon iconHome = Icon.xpath("//span[@class='action-icon home']");
   // public Icon iconCollapseOrExpand = Icon.xpath("//span[contains(@class,'action-icon collapse')]");
   // public Icon iconExpand = Icon.xpath("//span[contains(@class,'action-icon collapse expand')]");
    public DropDownList ddlMenu = DropDownList.xpath("//div[contains(@class,'header-contain menu-btn')]","//ul[@class='dropdown']/li");
    public LefMenuIcon iconLeftMenu = LefMenuIcon.xpath("//span[contains(@class,'action-icon collapse')]");

    public LoginPage logout() {
        btnSignOut.click();
        LoginPage loginPage = new LoginPage();
        loginPage.txtUsername.isDisplayed();
        return loginPage;
    }

    /**
     * If input security code, auto full fill security code
     * @param securityCode
     * @return
     */
    public ChangePasswordPopup clickChangePasswordHeader(String securityCode){
        ddlMenu.clickMenu(PASSWORD);
        SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
        if(securityPopup.isDisplayed()){
            if(!securityCode.isEmpty())
                securityPopup.submitSecurityCode(securityCode);
        }
        return ChangePasswordPopup.xpath("//app-change-password") ;
    }

    /**
     * If input security code, auto full fill security code
     * @param securityCode
     * @return
     */
    public UpdateSecurityCodePage clickSecurityCodeHeaderMenu(String securityCode){
        ddlMenu.clickMenu(SECURITY_CODE);
        SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
        if(securityPopup.isDisplayed()){
            if(!securityCode.isEmpty())
                securityPopup.submitSecurityCode(securityCode);
        }
        return new UpdateSecurityCodePage();
    }

    public AccountBalancePage clickHomeIcon(){
        iconHome.click();
        DriverManager.getDriver().switchToWindow();
        return new AccountBalancePage();
    }


}
