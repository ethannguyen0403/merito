package agentsite.pages.all.components;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

public class SuccessPopup extends BaseElement {
    private String _xPath;
    Label lblTitle;
    Label lblContent;
    Button btnOK ;

    public SuccessPopup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
         lblTitle = Label.xpath(String.format("%s//div[contains(@class,'modal-header')]",_xPath));
         lblContent = Label.xpath(String.format("%s//div[contains(@class,'modal-body modal-body-fit-with-content')]",_xPath));
         btnOK = Button.xpath(String.format("%s//div[contains(@class,'modal-footer')]/button",_xPath));
    }
    public static SuccessPopup xpath(String xpathExpression) {
        return new SuccessPopup(By.xpath(xpathExpression), xpathExpression);
    }
    public String getContentMessage() {
        lblContent.isDisplayed(1);
       return lblContent.getText();
    }

    public void close(){
        btnOK.click();
    }
}
