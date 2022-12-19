package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import controls.Table;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class SelectedOddControl extends BaseElement {
	static String _xpath = "";
	private Button btnPlaceBet;
	private Button btnCancelAllSelections;
	private CheckBox chkConfirm;
	public Label lblClickOnOdd = Label.xpath("//div[@id='place-bets']/p");
	private Table tblSelectedOdd = Table.xpath("//div[@id='bet-lay']//table[@class='bets lay']", 4);

	private SelectedOddControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		btnPlaceBet = Button.id("place-bet");
		btnCancelAllSelections = Button.id("clear-slip");
		chkConfirm = CheckBox.id("confirmation");
	}
	
	public static SelectedOddControl xpath(String xpathExpression) {
		return new SelectedOddControl(By.xpath(xpathExpression), xpathExpression);
	}

	public void cancelAllSelections() {
		btnCancelAllSelections.click();
	}

//	public Odd getSelectedOdd () {
//
//	}
}
