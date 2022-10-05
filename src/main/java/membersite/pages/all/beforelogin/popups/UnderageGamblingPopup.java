package membersite.pages.all.beforelogin.popups;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class UnderageGamblingPopup {
    public Popup popupUnderageGambling = Popup.xpath("//div[@class='login-popup-content']");
    public Label lblTitle = Label.xpath("//div[@class='title-popup']//b");
    public Label lblContent = Label.xpath("//div[@class='content-popup']/p");
    public Button btnConfirm = Button.xpath("//button[contains(@class,'btn-in-out')]");
    public Button btnExit = Button.xpath("//button[contains(@class,'btn-in-out exit')]");

    public LoginPopup clickConfirmation() {
        btnConfirm.click();
        return new LoginPopup();
    }
}
