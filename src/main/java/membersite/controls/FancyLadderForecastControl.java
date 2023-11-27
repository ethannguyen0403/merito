package membersite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;


/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class FancyLadderForecastControl extends BaseElement {
    private String _xpath;
    private String titleXpath ="%s//h2";
    private Label lblTitle;
    public FancyLadderForecastControl(By locator, String xpath) {
        super(locator);
        this._xpath = xpath;
        lblTitle = Label.xpath(String.format(titleXpath, _xpath));
    }

    public static FancyLadderForecastControl xpath(String xpathExpression) {
        return new FancyLadderForecastControl(By.xpath(xpathExpression), xpathExpression);
    }

    public String getTitle() {
        return lblTitle.getText().trim();
    }

}
