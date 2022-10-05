package membersite.controls.app;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import membersite.controls.DropDownBox;
import org.openqa.selenium.By;
import membersite.pages.all.beforelogin.popups.UnderageGamblingPopup;
import membersite.pages.funsport.home.popups.FairenterLoginPopup;

/**
 * @author isabella.huynh
 * created on 3/10/2020
 */
public class FairenterLoginControl extends BaseElement {
	static String _xpath ;
	public Button btnExit = Button.xpath("//button[contains(@class,'exitAction')]/span");
	public Button btnConfirm = Button.xpath("//button[contains(@class,'btn btn-login confirmAction')]");
	public Label lnlAgeVerifyLableMsg = Label.xpath("//div[@class='ageVerify-container']//div[@class='text-msg']");
	public Label lnlAgeVerifyLableSubMsg = Label.xpath("//div[@class='ageVerify-container']//div[@class='sub-text-msg']");

	private FairenterLoginControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
	}

	public static FairenterLoginControl xpath(String xpathExpression) {
		return new FairenterLoginControl(By.xpath(xpathExpression), xpathExpression);
	}
	public FairenterLoginPopup openFairenterLoginPopup(){
		btnConfirm.click();
		return new FairenterLoginPopup();
	}
}
