package membersite.pages.exchangegames.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class UnmatchedBetControl extends BaseElement {
	static String _xpath;
	private Label lblBetId;

	private UnmatchedBetControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		lblBetId = Label.xpath(String.format("%s//div[@class='bet-info']/span[1]",_xpath));

	}
	
	public static UnmatchedBetControl xpath(String xpathExpression) {
		return new UnmatchedBetControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getBetId(){
      if(lblBetId.isDisplayed(10)){
      	return lblBetId.getText();
	  }
      return null;
	}


}
