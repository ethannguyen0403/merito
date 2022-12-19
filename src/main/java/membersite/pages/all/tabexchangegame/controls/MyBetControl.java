package membersite.pages.all.tabexchangegame.controls;

import com.paltech.element.BaseElement;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class MyBetControl extends BaseElement {

	static String _xpath;
	public UnmatchedBetControl unmatchedBetControl;

	private MyBetControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		unmatchedBetControl = UnmatchedBetControl.xpath(String.format("%s//div[@class='unmatched']", _xpath));

	}
	
	public static MyBetControl xpath(String xpathExpression) {
		return new MyBetControl(By.xpath(xpathExpression), xpathExpression);
	}


}
