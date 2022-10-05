package membersite.controls.app;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Image;
import membersite.controls.DropDownBox;
import org.openqa.selenium.By;
import membersite.pages.all.beforelogin.popups.UnderageGamblingPopup;

/**
 * @author isabella.huynh
 * created on 3/10/2020
 */
public class FunsportLoginControl extends BaseElement {
	static String _xpath ;
	Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
	public Button btnLogin = Button.xpath("//input[contains(@class,'btn btn-login btn-sm')]");
	CheckBox chkRememberMe = CheckBox.id("remember-me");
	DropDownBox ddbLanguage = DropDownBox.xpath("//div[@id='language']/span[@class='logout']", "//div[@id='select-language']/ul[@id='lang-list']//a");

	private FunsportLoginControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
	}

	public static FunsportLoginControl xpath(String xpathExpression) {
		return new FunsportLoginControl(By.xpath(xpathExpression), xpathExpression);
	}
	public UnderageGamblingPopup clickLoginBtn() {
		btnLogin.click();
		return new UnderageGamblingPopup();
	}
}
