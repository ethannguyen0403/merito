package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import controls.Table;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class OneClickBettingControl extends BaseElement {
	static String _xpath = "";
	private Label lbl1ClickBet;
	private Label lblErrorMsg;//div[@id='one-click-betting']//div[@class='errorMsg']
	private Label lblStatus;//div[@id='one-click-betting']//div[@class='track']//span
	private Button btnEdit;//div[@id='one-click-betting']//input[contains(@class,'btn-edit-stake')]
	private Button btnCancel;//div[@id='one-click-betting']//input[contains(@class,'btn-cancel-stake')]
	private Button btnSave;//div[@id='one-click-betting']//input[contains(@class,'btn-save-stake')]
//	private Button btnStake1;//div[@id='one-click-betting']//input[contains(@class,'btn-save-stake')]
//	private Button btnStake2;//div[@id='one-click-betting']//input[contains(@class,'btn-save-stake')]
//	private Button btnStake3;//div[@id='one-click-betting']//input[contains(@class,'btn-save-stake')]
//	private TextBox txtStake1;//div[@id='one-click-betting']//input[contains(@class,'btn-save-stake')]
//	private TextBox txtStake2;//div[@id='one-click-betting']//input[contains(@class,'btn-save-stake')]
//	private TextBox txtStake3;//div[@id='one-click-betting']//input[contains(@class,'btn-save-stake')]
	private Table tblStake;

	private OneClickBettingControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		lblErrorMsg = Label.xpath(String.format("%s//div[@class='errorMsg']",_xpath));
		lblStatus = Label.xpath(String.format("%s//div[@class='track']//span",_xpath));
		btnEdit = Button.xpath(String.format("%s//input[contains(@class,'btn-edit-stake')]",_xpath));
		btnCancel = Button.xpath(String.format("%s//input[contains(@class,'btn-cancel-stake')]",_xpath));
		lbl1ClickBet = Label.xpath(String.format("%s//label[@class='lable-click-bet']",_xpath));
//		btnSave = Button.xpath(String.format("%s//input[contains(@class,'btn-save-stake')]",_xpath));
//		btnStake1 = Button.xpath(String.format("%s//div[@class='errorMsg']",_xpath));
//		btnStake2 = Button.xpath(String.format("%s//div[@class='errorMsg']",_xpath));
//		btnStake3 = Button.xpath(String.format("%s//div[@class='errorMsg']",_xpath));
//		txtStake1 = TextBox.xpath(String.format("%s//div[@class='errorMsg']",_xpath));
//		txtStake2 = TextBox.xpath(String.format("%s//div[@class='errorMsg']",_xpath));
//		txtStake3 = TextBox.xpath(String.format("%s//div[@class='errorMsg']",_xpath));
		tblStake = Table.xpath(String.format("%s//div[@class='default-click']/table",_xpath),3);
	}
	
	public static OneClickBettingControl xpath(String xpathExpression) {
		return new OneClickBettingControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getOneClickStatus()
	{
		return lblStatus.getText();
	}

	public String getOneClickLabel(){
		return lbl1ClickBet.getText();
	}

	public void activeClick(){
		if(lblStatus.getText().equalsIgnoreCase("OFF"))
			lblStatus.click();
	}

	public void inactive1Click(){
		if(lblStatus.getText().equalsIgnoreCase("ON"))
			lblStatus.click();
	}

}
