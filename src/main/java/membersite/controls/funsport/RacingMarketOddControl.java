package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import membersite.objects.funsport.Odd;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class RacingMarketOddControl extends BaseElement {
	private String _xpath =""; //div[@id='fullMarketOdds']
	public Label lblVenusName = Label.xpath("//span[@class='venue-name']");
	public Label lblAddFavourite = Label.xpath("//div[@id='fullMarketOdds']//h2//i[contains(@class,'add-multi-market')]");
	public Label lblGoingInplay = Label.xpath("//div[@id='fullMarketOdds']//span[@class='market-status-icon market-going-inplay']");
	public Label lblRule = Label.xpath("//div[@id='fullMarketOdds']//span[@class='market-rules-span']");

	public Label lblHomeName = Label.xpath("//div[@id='odds-content']//div[@class='home-team']");
	public Label lblAwayName = Label.xpath("//div[@id='odds-content']//div[@class='away-team']");
	public Label lblInplayStatus = Label.xpath("//div[@id='odds-content']//span[@class='status inplay']");
	public Label lblViewColumn = Label.xpath("//div[@id='odds-content']//div[@class='toggle-container clearfix']//ul//li[@class='toggle-column']//input");
	public Label lblViewLiner = Label.xpath("//div[@id='odds-content']//div[@class='toggle-container clearfix']//ul//li[@class='toggle-linear']//input");
	public Label lblViewAs = Label.xpath("//div[@id='odds-content']//div[@class='toggle-container clearfix']//ul/li[@class='toggle-intro']");

	private Label lblSportName;
	private Label lblSportHighLight;
	private Label lblMarketName;
	private Label lblNoEvent;
	public enum Team {HOME, DRAW, AWAY}
	public enum Status {NA, IN_PLAY, COMING}
	private boolean _isMatchOdds;

	private RacingMarketOddControl(By locator, String xpath, boolean isMatchOdds) {
		super(locator);
		_xpath = xpath;
		_isMatchOdds = isMatchOdds;
		lblMarketName =Label.xpath("//span[@class='venue-name']");
	}
	public static RacingMarketOddControl xpath(String xpathExpression, boolean isMatchOdds) {
		return new RacingMarketOddControl(By.xpath(xpathExpression), xpathExpression, isMatchOdds);
	}

	public Odd getSelectionOdds(int selectionIndex, boolean isBack) {
		String xpathMarkets = String.format("%s", _xpath);
		int i = 1;
		String xpathMarket = String.format("%s[%s]", xpathMarkets, i);
	/*	Label lblMarketName = Label.xpath(String.format("%s//span[@class='star-text']",xpathMarket));
		if (!lblMarketName.isPresent(2)){
			return null;
		}*/
		Label lblSuspend = Label.xpath(String.format("%s//div[@class='status-overlay suspended']", xpathMarket));
		if (!lblSuspend.isDisplayedShort(2)) {
			Icon iconInPlay = Icon.xpath(String.format("%s//span[@class='market-status-icon market-inplay']", _xpath));
			//Label lnkStartTime = Label.xpath(String.format("%s//div[@class='status going-inplay time-inplay']", xpathMarket));
			String backOrLay = isBack ? "back" : "lay";
			String marketName =lblMarketName.getText();
			int iSelectedTeam = selectionIndex;
			Link lnlSelection = Link.xpath(String.format("%s//table/tbody/tr[%s]//td[contains(@class,'runner-cell')]//h3", xpathMarket,iSelectedTeam));
			String selectionName = lnlSelection.getText();
			String xpathOdd = String.format("%s//table/tbody/tr[%s]//td[@class='odds highlight']//div[contains(@class,' %s')]//span[@class='price']", xpathMarket,iSelectedTeam, backOrLay);
			Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)
			System.out.println( "Lay odd price is display: "+lnkSelectedOdd.isPresent());
			if (lnkSelectedOdd.isPresent()) {
				boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
				if(!isBack)
					return getOdd(lnkSelectedOdd, "",marketName, isInPlayOnOdd, selectionName, i);
				String oddRate = lnkSelectedOdd.getText();
				// check whether odd rate is empty than we will edit odd = 100 for Back
				String checkOddRate = oddRate.isEmpty() ? "100" : oddRate;
				boolean isAllow = true;
				// check whether Lay odd rate is less than 4 we will place this bet
				if(!isBack && Double.parseDouble(checkOddRate) > 4) {
					isAllow = false;
				}
				if(isAllow){
					return getOdd(lnkSelectedOdd, oddRate,marketName, isInPlayOnOdd, selectionName, i);
				}
			}
		}
		return null;
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate,String marketName, boolean isInPlay, String selectedTeam, int eventIndex) {
		return new Odd.Builder()
				.eventName(marketName)
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.build();
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate,String homeTeam, String awayTeam, boolean isInPlay, String selectedTeam, int eventIndex) {
		return new Odd.Builder()
				.eventName(String.format("%s v %s", homeTeam, awayTeam))
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.build();
	}

}
