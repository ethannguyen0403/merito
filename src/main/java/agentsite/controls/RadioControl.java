package agentsite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;


public class RadioControl extends BaseElement {
    String _xPath;
    Label _label;

    public RadioControl(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        this._label = Label.xpath(String.format("%s/label", _xPath));

    }

    public static RadioControl xpath(String xpathExpression) {
        return new RadioControl(By.xpath(xpathExpression), xpathExpression);
    }

    public String getLabel() {
        return _label.getText();
    }


}
