package membersite.controls.proteus;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import org.openqa.selenium.By;

public class AppConfirmModulePopup extends BaseElement {
    private String _xpathPopup = "";
    private Label lblTitle;
    private Label lblContent;
    private Button btnCancel;
    private Button btnOK;

    private AppConfirmModulePopup(By locator, String xpathPopup) {
        super(locator);
        this._xpathPopup = xpathPopup;
        lblTitle = Label.xpath(String.format("%s%s", _xpathPopup, "//div[contains(@class,'modal-header')]/span"));
        lblContent = Label.xpath(String.format("%s%s", _xpathPopup, "//div[contains(@class,'body-text')]//div"));
        btnCancel = Button.xpath(String.format("%s%s", _xpathPopup, "//button[contains(@class,'btn-cancel')]"));
        btnOK = Button.xpath(String.format("%s%s", _xpathPopup, "//button[contains(@class,'btn-ok')]"));
    }

    public static AppConfirmModulePopup xpath(String xpathPopup) {
        return new AppConfirmModulePopup(By.xpath(xpathPopup), xpathPopup);
    }

    public void clickCloseBtn() {
        btnCancel.click();
    }

    public void confirm() {
        btnOK.waitForElementToBePresent(btnOK.getLocator());
        btnOK.click();
    }

    public String getContent() {
        return lblContent.getText();
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    @Override
    public boolean isDisplayed() {
        Popup popup = Popup.xpath(this._xpathPopup);
        popup.isInvisible(2);
        return popup.isDisplayed();
    }

}
