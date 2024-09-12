package backoffice.pages.bo._components;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import org.openqa.selenium.By;

public class AppConfirmPopup extends BaseElement {
    private String _xpathPopup = "";
    private Label lblTitle;
    private Label lblContent;
    private Button btnClose;
    private Button btnConfirm;

    private AppConfirmPopup(By locator, String xpathPopup) {
        super(locator);
        this._xpathPopup = xpathPopup;
        lblTitle = Label.xpath(String.format("%s%s", _xpathPopup, "//h5//span"));
        lblContent = Label.xpath(String.format("%s%s", _xpathPopup, "//h6"));
        btnClose = Button.xpath(String.format("%s%s", _xpathPopup, "//button[contains(@class,'btn-secondary')]"));
        btnConfirm = Button.xpath(String.format("%s%s", _xpathPopup, "//button[contains(@class,'btn-warning')]"));
    }

    public static AppConfirmPopup xpath(String xpathPopup) {
        return new AppConfirmPopup(By.xpath(xpathPopup), xpathPopup);
    }

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void confirm() {
        if (btnConfirm.isDisplayed())
            btnConfirm.click();
            btnConfirm.waitForControlInvisible();
    }

    public String getContent() {
        try{
            Thread.sleep(300);//wait for locator visible on screen
        }catch (Exception e){
        }
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
