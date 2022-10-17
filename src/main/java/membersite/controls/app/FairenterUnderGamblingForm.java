package membersite.controls.app;

import agentsite.common.AGConstant;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import membersite.pages.all.beforelogin.popups.LoginForm;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.beforelogin.popups.SATUnderageGamblingPopup;
import membersite.pages.all.tabexchange.HomePage;
import org.openqa.selenium.By;

public class FairenterUnderGamblingForm extends BaseElement {
    private String _xpath ;
    public Button btnExit = Button.xpath("//button[contains(@class,'exitAction')]");
    public Button btnConfirm = Button.xpath("//button[contains(@class,'confirmAction')]");
    public Label lblUnderageGamblingIsProhobite = Label.xpath("//div[contains(@class,'ageVerify-container')]//div[contains(@class,'confirm-backgroup')]/div[@class='text-msg']");
    public Label lblConfirmMessage = Label.xpath("//div[contains(@class,'ageVerify-container')]//div[contains(@class,'confirm-backgroup')]/div[@class='sub-text-msg']");
    private FairenterUnderGamblingForm(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static FairenterUnderGamblingForm xpath(String xpathExpression) {
        return new FairenterUnderGamblingForm(By.xpath(xpathExpression), xpathExpression);
    }
    public LoginForm clickConfirmation() {
        btnConfirm.click();
        return new LoginForm();
    }

    public HomePage login(String username, String password, boolean skipByDefault){
        LoginForm loginForm =clickConfirmation();
        return loginForm.login(username,password,skipByDefault);
    }

}
