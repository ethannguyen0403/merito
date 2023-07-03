package agentsite.controls.NetExposure;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 */
public class EventTitleControl extends BaseElement {
    protected String _xPath = null;//tr[contains(@class,'header-title')]
    private Icon icColapse;
    private Icon icFavorite;
    private Label lblEventName;


    public EventTitleControl(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        icColapse = Icon.xpath(String.format("%s//i[contains(@class,'icon-collapse')]"));
        icFavorite = Icon.xpath(String.format("%s//i[contains(@class,'icon-favorite')]"));
        lblEventName = Label.xpath(String.format("%s//div[@class='market-name']"));

    }

    public static EventTitleControl xpath(String xpathExpression) {
        return new EventTitleControl(By.xpath(xpathExpression), xpathExpression);
    }

    public boolean isCollapsed() {
        String attribute = icColapse.getAttribute("class");
        return attribute.contains("fa-chevron-down");
    }

    public boolean isExpand() {
        String attribute = icColapse.getAttribute("class");
        return attribute.contains("fa-chevron-up");
    }

    public void expandCollapse(boolean isExpand) {
        String attribute = icColapse.getAttribute("class");
        if (isExpand && attribute.contains("fa-chevron-down")) {
            this.click();
        }
        if (!isExpand && attribute.contains("fa-chevron-up")) {
            this.click();
        }
    }

    public void addFavorite() {
        String property = icFavorite.getAttribute("class");
        if (!property.contains("icon-favorite-active"))
            icFavorite.click();
    }

    public void removeFavorite() {
        String property = icFavorite.getAttribute("class");
        if (property.contains("icon-favorite-active"))
            icFavorite.click();
    }

    public String getEventName() {
        return lblEventName.getText().trim();
    }

    public String getUnmatchedStake() {
        return Label.xpath(String.format("%s/td[2]")).getText();
    }

    public String getMatchedStake() {
        return Label.xpath(String.format("%s/td[3]")).getText();
    }

    public String getHomeTeamForecast() {
        return Label.xpath(String.format("%s/td[4]")).getText();
    }

    public String getDrawTeamForecast() {
        return Label.xpath(String.format("%s/td[5]")).getText();
    }

    public String getAwayTeamForecast() {
        return Label.xpath(String.format("%s/td[6]")).getText();
    }

}
