package membersite.controls.app;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.beforelogin.popups.SATUnderageGamblingPopup;
import membersite.pages.all.tabexchange.HomePage;
import org.openqa.selenium.By;

public class HeaderControl extends BaseElement {
    private String _xpath = "";
    public Button btnLogin = Button.xpath("//*[contains(@class,'btn-in-out') or contains(@class,'btn btn-login')]");
    public Image imgHome = Image.xpath("//i[contains(@class,'fa-home')]");

   /* private HeaderControl(By locator) {
        super(locator);
        _xpath = xpath;
    }*/

    public HeaderControl(By locator,String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static HeaderControl xpath(String xpathExpression) {
        return new HeaderControl(By.xpath(xpathExpression), xpathExpression);
    }

    public SATUnderageGamblingPopup clickLogin() {
        if(btnLogin.isDisplayed()){
            btnLogin.click();
        }
        return new SATUnderageGamblingPopup();
    }
    public boolean isLoginBtnDisplay() {
        return btnLogin.isDisplayed();
    }

    public String getLoginBtnText() {
        return btnLogin.getText();
    }

    public HomePage login(String username, String password, boolean skipByDefault){
        SATUnderageGamblingPopup popup = clickLogin();
        LoginPopup loginPopup = popup.clickConfirmation();
        return loginPopup.login(username,password,skipByDefault);
    }


}
