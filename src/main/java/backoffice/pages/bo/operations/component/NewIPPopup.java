package backoffice.pages.bo.operations.component;

import com.paltech.element.common.*;

public class NewIPPopup {
    public Popup popupNewBanner = Popup.xpath("//mat-dialog-container");
    public Label lblTitle = Label.xpath("//mat-dialog-container//span[contains(@class,'dialog-title')]");
    public TextBox txtIP = TextBox.xpath("//mat-dialog-container//input");
    public Icon iconX = Icon.xpath("//mat-dialog-container//span[contains(@class,'close-btn')]");
    public Button btnSave = Button.xpath("//div[contains(@class, 'footer')]//button[contains(@class,'btn-save')]");
    public Button btnClose = Button.xpath("//div[contains(@class, 'footer')]//button[contains(@class,'btn-outline-secondary')]");

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void clickXIcon() {
        iconX.click();
    }

    public boolean isDisplayed(){
        popupNewBanner.isInvisible(1);
        return popupNewBanner.isDisplayed();
    }
}
