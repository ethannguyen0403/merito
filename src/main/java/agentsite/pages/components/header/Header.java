package agentsite.pages.components.header;

import agentsite.controls.DropDownList;
import agentsite.controls.LefMenuIcon;
import agentsite.pages.AccountBalancePage;
import agentsite.pages.UpdateSecurityCodePage;
import agentsite.pages.components.ChangePasswordPopup;
import agentsite.pages.components.SecurityPopup;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;

import static common.AGConstant.PASSWORD;
import static common.AGConstant.SECURITY_CODE;

public class Header {
    public Button btnSignOut = Button.xpath("//button[contains(@class,'pbtn sign-out')]");
    public DropDownList ddlMenu = DropDownList.xpath("//div[contains(@class,'header-contain menu-btn')]","//ul[@class='dropdown']/li");
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[contains(@class, 'title')]");
    public LefMenuIcon iconLeftMenu = LefMenuIcon.xpath("//span[contains(@class,'action-icon collapse')]");
    public Icon iconHeaderMenu = Icon.xpath("//span[contains(@class,'header-btn')]");
    public Icon iconEmail = Icon.xpath("//span[@class='mail-btn fa fa-envelope']");
    public DropDownBox ddbLanguage = DropDownBox.id("language");
    public Label lblTimeZone = Label.xpath("//div[contains(@class,'time-contain')]");
    public Icon iconHome = Icon.xpath("//span[@class='action-icon home']");
    public void logout(){
        btnSignOut.click();
    }
    public void clickHomeIcon(){
        iconHome.click();
    }

    public boolean isSignOutDisplay(){return btnSignOut.isDisplayed();}

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
        return null;
    }
}
