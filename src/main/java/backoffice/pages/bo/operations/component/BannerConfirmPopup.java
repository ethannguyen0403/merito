package backoffice.pages.bo.operations.component;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;


public class BannerConfirmPopup {
    public Popup popup = Popup.xpath("//app-banner-confirm-dialog");
    private Label lblTitle = Label.xpath("//app-banner-confirm-dialog//h4");
    private Button btnCancel = Button.xpath("//app-banner-confirm-dialog//button[contains(@class,'btn-cancel')]");
    private Label lblContent = Label.xpath("//app-banner-confirm-dialog//div[contains(@class,'dialog-body')]/div");
    private Button btnOk = Button.xpath("//app-banner-confirm-dialog//button[contains(@class,'btn-confirm')]");

    public String getTitle() {
        return lblTitle.getText();
    }

    public void clickCancelBtn() {
        btnCancel.click();
    }

    public void clickOK() {
        btnOk.click();
    }

    public String getContentMessage() {
        return lblContent.getText();
    }

    public boolean isDisplayed() {
        popup.isInvisible(2);
        return popup.isDisplayed();
    }

}
