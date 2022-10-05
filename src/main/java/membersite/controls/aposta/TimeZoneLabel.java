package membersite.controls.aposta;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

public class TimeZoneLabel extends BaseElement {
    private String _xpath ;
    private Icon icClock;
    private Label lblDate;
    private Label lblTime;
    private Label lblGMT;

    private TimeZoneLabel(By locator, String xpathExpression) {
        super(locator);
        this._xpath = xpathExpression;
        icClock = Icon.xpath((String.format("%s//i",_xpath)));
        lblDate = Label.xpath(String.format("%s//span[1]",_xpath));
        lblTime = Label.xpath(String.format("%s//span[2]",_xpath));
        lblGMT = Label.xpath(String.format("%s//span[3]",_xpath));
    }

    public static TimeZoneLabel xpath(String xpathExpression) {
        return new TimeZoneLabel(By.xpath(xpathExpression), xpathExpression);
    }

    public String getTimeZone(){
        return String.format("%s %s %s",lblDate.getText().trim(),lblTime.getText().trim(),lblGMT.getText().trim());
    }

    public boolean isClockIconDisplay(){
        return icClock.isDisplayed();
    }
}
