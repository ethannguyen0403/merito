package backoffice.pages.bo._components;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import org.openqa.selenium.By;

public class StaticConfirmPopup extends BaseElement {
    private String _xpathPopup = "";
    private Label lblTitle ;
    private Label lblContent ;
    private Button btnClose ;
    private Button btnConfirm ;

    private StaticConfirmPopup(By locator, String xpathPopup,String xContent, String xPConfrimBtn) {
        super(locator);
        this._xpathPopup = xpathPopup;
        lblTitle = Label.xpath(String.format("%s%s",_xpathPopup,"//div[contains(@class,'title')]"));
        lblContent = Label.xpath(String.format("%s//%s",_xpathPopup,xContent));
        btnClose = Button.xpath(String.format("%s%s",_xpathPopup,"//button[contains(@class,'btn-outline-secondary')]"));
        btnConfirm = Button.xpath(String.format("%s//%s",_xpathPopup,xPConfrimBtn));
    }
    public static StaticConfirmPopup xpath(String xpathPopup,String xPContent,String xPConfirmBtn) {
        return new StaticConfirmPopup(By.xpath(xpathPopup), xpathPopup,xPContent,xPConfirmBtn);
    }

    public void clickCloseBtn(){
        btnClose.click();
    }

    public void confirm(){
        btnConfirm.click();
    }

    public String getContent(){
        return lblContent.getText();
    }

    public String getTitle(){
        return lblTitle.getText();
    }

    @Override
    public boolean isDisplayed(){
        Popup popup = Popup.xpath(this._xpathPopup);
        popup.isInvisible(2);
        return popup.isDisplayed();
    }

}
