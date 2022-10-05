package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;
import membersite.pages.all.tabexchange.RacingMarketPage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class HighLightRaceControl extends BaseElement {
	static String _xpath = "";
	private Label lblNetxRace;
	private Link lnkFirstRace;
	private Label lblCommingUp;

	private HighLightRaceControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		lblCommingUp = Label.xpath(String.format("%s//div[@class='comingup-races-content']/span",_xpath));
		lnkFirstRace = Link.xpath(String.format("%s//a[@class='clickThrough first-race']",_xpath));
		lblNetxRace = Label.xpath(String.format("%s//div[contains(@class,'comingup')]//h2",_xpath));

	}
	
	public static HighLightRaceControl xpath(String xpathExpression) {
		return new HighLightRaceControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getNexRaceLabel(){
		return lblNetxRace.getText();
	}

	public List<String> getComingUpRaceInfo(int raceIndex){
		List<String> raceInfoList = new ArrayList<>();
		Link lnlRace = Link.xpath(String.format("//div[@id='coming-up-race']//ul/li[%s]/a",raceIndex));
		raceInfoList.add(lnlRace.getText());
		raceInfoList.add(lnlRace.getAttribute("title"));
		raceInfoList.add(lnlRace.getAttribute("href"));
		raceInfoList.add(lnlRace.getAttribute("data-event-id"));
		raceInfoList.add(lnlRace.getAttribute("data-market-id"));
		raceInfoList.add(lnlRace.getAttribute("data-venue"));
		return raceInfoList;
	}
	public RacingMarketPage clickCommingUpRace(int raceIndex){
		Link lnlRace = Link.xpath(String.format("//div[@id='coming-up-race']//ul/li[%s]/a",raceIndex));
		lnlRace.click();
		return new RacingMarketPage();
	}


}
