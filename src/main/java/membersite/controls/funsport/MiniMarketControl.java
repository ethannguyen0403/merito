package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class MiniMarketControl extends BaseElement {
	static String _xpath = "";
	private Label lblEventHeaderHomeTeam;
	private Label lblEventHeaderAwayTeam;
	private Label lblMarketTitle;
	private Label lblEventTitle;
	private Button btnRemove;

	private MiniMarketControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		lblEventHeaderHomeTeam=Label.xpath("//div[contains(@class,'header-content')]//div[@class='home-team']");
		lblEventHeaderAwayTeam=Label.xpath("//div[contains(@class,'header-content')]//div[@class='away-team']");
		lblMarketTitle = Label.xpath(String.format("%s//span[@class='market-title']",_xpath));
		lblEventTitle = Label.xpath(String.format("%s//span[@class='event-title']",_xpath));
		btnRemove = Button.xpath(String.format("%s//span[@class='btn btn-sm btn-danger close-rules']",_xpath));
	}
	
	public static MiniMarketControl xpath(String xpathExpression) {
		return new MiniMarketControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getEventTitle(){
		if(haveOnlyOneMarket()){
			return String.format("%s v %s",lblEventHeaderHomeTeam.getText(),lblEventHeaderAwayTeam.getText());
		}
		return lblEventTitle.getText().trim();
	 }
	public String getMarketTitle(){
		return lblMarketTitle.getText().trim();
	}


	public boolean haveOnlyOneMarket(){
		return lblEventHeaderHomeTeam.isDisplayed();
	}

	public boolean isMarketDisplay(){
		return lblMarketTitle.isDisplayed();
	}

	public void clickRemove(){
		btnRemove.click();
	}

}
