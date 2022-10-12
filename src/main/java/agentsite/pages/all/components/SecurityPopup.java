package agentsite.pages.all.components;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;

public class SecurityPopup extends BaseElement {
    private String _xPath;
    Label lblTitle ;
    Label lblContent;
    TextBox txtSecurityCode;
    Button btnSubmit ;
    Button btnCancel ;
    public SecurityPopup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblTitle = Label.xpath(String.format("%s//div[@class='modal-header']/div",this._xPath));
        lblContent =Label.xpath(String.format("%s//div[@id='validatePin']/div",this._xPath));
        txtSecurityCode =TextBox.xpath(String.format("%s//input[@id='pin']",this._xPath));
        btnSubmit= Button.xpath(String.format("%s//div[@class='modal-footer']/button[@class='pbtn']",this._xPath));
        btnCancel = Button.xpath(String.format("%s//div[@class='modal-footer']/button[@class='cancel']",this._xPath));
    }

    public static SecurityPopup xpath(String xpathExpression) {
        return new SecurityPopup(By.xpath(xpathExpression), xpathExpression);
    }

    public void submitSecurityCode(String securityCode) {
        txtSecurityCode.isDisplayed();
        txtSecurityCode.sendKeys(securityCode);
        btnSubmit.click();
    }
}
