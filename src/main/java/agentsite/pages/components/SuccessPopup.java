package agentsite.pages.components;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

public class SuccessPopup extends BaseElement {
    public Label lblTitle = Label.xpath("//div[contains(@class,'modal-header')]");
    public Label lblContent = Label.xpath("//div[contains(@class,'modal-body modal-body-fit-with-content')]");
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Button btnOK = Button.xpath("//div[contains(@class,'modal-footer')]/button");
    private String _xPath;

    public SuccessPopup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
//        lblTitle = Label.xpath(String.format("%s//div[contains(@class,'modal-header')]", _xPath));
//        lblContent = Label.xpath(String.format("%s//div[contains(@class,'modal-body modal-body-fit-with-content')]", _xPath));
//        btnOK = Button.xpath(String.format("%s//div[contains(@class,'modal-footer')]/button", _xPath));
    }

    public static SuccessPopup xpath(String xpathExpression) {
        return new SuccessPopup(By.xpath(xpathExpression), xpathExpression);
    }

    public String getContentMessage() {
        lblContent.isDisplayed(1);
        return lblContent.getText();
    }

    public void close() {
        btnOK.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isDisplayed() {
        iconLoadSpinner.waitForControlInvisible(2, 4);
        return super.isDisplayed();
    }
}
