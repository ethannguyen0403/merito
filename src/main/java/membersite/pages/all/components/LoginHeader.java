package membersite.pages.all.components;

import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Image;
import membersite.controls.DropDownBox;
import membersite.pages.all.beforelogin.popups.UnderageGamblingPopup;

public class LoginHeader extends Footer {
    Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    public Button btnLogin = Button.xpath("//input[contains(@class,'btn btn-login btn-sm')]");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    DropDownBox ddbLanguage = DropDownBox.xpath("//div[@id='language']/span[@class='logout']", "//div[@id='select-language']/ul[@id='lang-list']//a");

    public UnderageGamblingPopup clickLoginBtn() {
        btnLogin.click();
        return new UnderageGamblingPopup();
    }
}
