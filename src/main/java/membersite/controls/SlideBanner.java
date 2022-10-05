package membersite.controls;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SlideBanner extends BaseElement {
	private String xpathLocatorItems;
	protected final int timeOutInSeconds = 10;

	public SlideBanner(By locator,  String locatorChildren) {
		super(locator);
		xpathLocatorItems = locatorChildren;
	}

	/**
	 *
	 * @param xpathParent is used for moving cursor of move and hover on this control to show the hidden items
	 * @param xpathChildren to get text of items within ddb
	 * @return DropDownBox
	 */
	public static SlideBanner xpath(String xpathParent,String xpathChildren) {
		return new SlideBanner(By.xpath(xpathParent), xpathChildren);
	}


}
