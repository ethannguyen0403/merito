package membersite.controls;

import com.paltech.element.BaseElement;
import org.openqa.selenium.By;

public class SlideBanner extends BaseElement {
    protected final int timeOutInSeconds = 10;
    private String xpathLocatorItems;

    public SlideBanner(By locator, String locatorChildren) {
        super(locator);
        xpathLocatorItems = locatorChildren;
    }

    /**
     * @param xpathParent   is used for moving cursor of move and hover on this control to show the hidden items
     * @param xpathChildren to get text of items within ddb
     * @return DropDownBox
     */
    public static SlideBanner xpath(String xpathParent, String xpathChildren) {
        return new SlideBanner(By.xpath(xpathParent), xpathChildren);
    }


}
