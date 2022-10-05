package membersite.pages.all.beforelogin.popups;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class SATUnderageGamblingPopup {
    public Popup popupUnderageGambling = Popup.xpath("//div[@class='login-popup-content']");
    public Label lblTitle = Label.xpath("//div[@class='title-popup']//b");
    public Label lblContent = Label.xpath("//div[@class='confirm-text']");
    public Button btnConfirm = Button.xpath("//button[contains(@class,'btn-verification btn-confirm')]");
    public Button btnExit = Button.xpath("//button[@class='btn-verification']");

    public LoginPopup clickConfirmation() {
        btnConfirm.click();
        return new LoginPopup();
    }
}