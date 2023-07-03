package backoffice.pages.bo.operations.component;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class ConfirmIPMapppingPopup {
    public Popup popupNotification = Popup.xpath("//app-ip-comfim-dialog");
    Label lblTitle = Label.xpath("//app-ip-comfim-dialog//div[@class='title']//h1");
    Label lblContent = Label.xpath("//app-ip-comfim-dialog//div[@class='mat-dialog-content']");
    Button btnClose = Button.xpath("//app-ip-comfim-dialog//button[contains(@class,'btn-outline-secondary')]");
    Button btnConfirm = Button.xpath("//app-ip-comfim-dialog//button[contains(@class,'btn-core')]");

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void confirm() {
        btnConfirm.click();
    }

    public String getContent() {
        return lblContent.getText();
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    public boolean isDisplayed() {
        popupNotification.isInvisible(2);
        return popupNotification.isDisplayed();
    }

}
