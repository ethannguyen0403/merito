package agentsite.pages.all.components;

import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class DialogPopup {
    Popup popup = Popup.xpath("//div[contains(@class,'dialog')]");
    Label lblTitle = Label.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'modal-header')]/div");
    Icon icClose = Icon.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'modal-header')]/button");
    Label lblContent = Label.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'modal-body')]");
    Button btnOK = Button.xpath("//div[contains(@class,'modal-footer')]//button[contains(text(),'Ok') or contains(@class,'btn-warning')]");
    Button btnCancel = Button.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'footer')]//button[contains(@ng-click,'cancel')]");

    /* public DialogPopup(By locator, String xpathExpression) {
         super(locator);
         this._xPath = xpathExpression;
         lblTitle = Label.xpath(String.format("%s//div[contains(@class,'modal-header')]",_xPath));
         lblContent = Label.xpath(String.format("%s//div[contains(@class,'modal-body')]",_xPath));
         btnOK = Button.xpath(String.format("%s//div[contains(@class,'modal-footer')]/button",_xPath));
         icClose = Icon.xpath(String.format("%s//div[contains(@class,'modal-header')]//button"));
     }
     public static DialogPopup xpath(String xpathExpression) {
         return new DialogPopup(By.xpath(xpathExpression), xpathExpression);
     }*/
    public String getContentMessage() {
        lblContent.isDisplayed(1);
        return lblContent.getText();
    }

    public String getTitle() {
        lblTitle.isDisplayed(1);
        return lblTitle.getText().trim();
    }

    public void confirmOK() {
        btnOK.click();
    }

    public void close() {
        icClose.click();
    }
}
