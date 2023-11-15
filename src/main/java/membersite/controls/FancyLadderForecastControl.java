package membersite.controls;

import com.paltech.constant.StopWatch;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import controls.Table;
import membersite.objects.sat.FancyMarket;
import org.openqa.selenium.By;

import java.util.Objects;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class FancyLadderForecastControl extends BaseElement {
    private String _xpath;
    private Label lblTitle = Label.xpath(String.format("%s//h2", _xpath));
    public FancyLadderForecastControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;

    }

    public static FancyLadderForecastControl xpath(String xpathExpression) {
        return new FancyLadderForecastControl(By.xpath(xpathExpression), xpathExpression);
    }

    public String getTitle() {
        return lblTitle.getText().trim();
    }

}
