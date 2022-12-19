package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import common.MemberConstants;
import membersite.objects.funsport.Odd;
import org.openqa.selenium.By;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class EventOddContentControl extends BaseElement {
	private String _xpath =""; //div[@id='odds-content']
	public Label lblHomeName = Label.xpath("//div[@id='odds-content']//div[@class='home-team']");
	public Label lblAwayName = Label.xpath("//div[@id='odds-content']//div[@class='away-team']");
	public Label lblInplayStatus = Label.xpath("//div[@id='odds-content']//span[@class='status inplay']");
	public Label lblViewColumn = Label.xpath("//div[@id='odds-content']//div[@class='toggle-container clearfix']//ul//li[@class='toggle-column']//input");
	public Label lblViewLiner = Label.xpath("//div[@id='odds-content']//div[@class='toggle-container clearfix']//ul//li[@class='toggle-linear']//input");
	public Label lblViewAs = Label.xpath("//div[@id='odds-content']//div[@class='toggle-container clearfix']//ul/li[@class='toggle-intro']");
	//i[@class='multi-market add-multi-market multi_plus_sign_icon']

	private Label lblSportName;
	private Label lblSportHighLight;
	private Label lblMarketName;
	private Label lblNoEvent;
	public enum Team {HOME, DRAW, AWAY}
	public enum Status {NA, IN_PLAY, COMING}
	private boolean _isMatchOdds;

	private EventOddContentControl(By locator, String xpath, boolean isMatchOdds) {
		super(locator);
		_xpath = xpath;
		_isMatchOdds = isMatchOdds;

	}
	public static EventOddContentControl xpath(String xpathExpression, boolean isMatchOdds) {
		return new EventOddContentControl(By.xpath(xpathExpression), xpathExpression, isMatchOdds);
	}

	public String getEventName(){
		String homeTeam = lblHomeName.getText().trim();
		String awayTeam = lblAwayName.getText().trim();
		return String.format("%s v %s", homeTeam, awayTeam);
	}

	public boolean isViewColumnEnable(){
		return lblViewColumn.isEnabled();
	}

	public boolean isLineViewEnable(){
		return lblViewLiner.isEnabled();
	}

	public Odd getMatchOdds(Team team, boolean isBack) {
		return getMatchOdds(team,isBack,1,1);
	}

	public Odd getMatchOdds(Team team, boolean isBack,int limit, int eventIndex) {
		String xpathMarkets = String.format("%s//div[@class='grid-view-odd-group']/div[@id='grid-view-odd']", _xpath);
		int i = Math.max(eventIndex, 1);
		while(true) {
			String xpathMarket = String.format("%s[%s]", xpathMarkets, i);
			Label lblMarketName = Label.xpath(String.format("%s//span[@class='market-title']", xpathMarket));
			if (!lblMarketName.isPresent(2)) {
				return null;
			}
			Label lblSuspend = Label.xpath(String.format("%s//div[@class='status-overlay suspended']", xpathMarket));
			if (!lblSuspend.isDisplayedShort(2)) {
				Icon iconInPlay = Icon.xpath(String.format("%s//span[@class='status inplay']", _xpath));
				//Label lnkStartTime = Label.xpath(String.format("%s//div[@class='status going-inplay time-inplay']", xpathMarket));
				String backOrLay = isBack ? "back" : "lay";
				String marketName = lblMarketName.getText();
				int iSelectedTeam = _isMatchOdds ? (team.equals(OddPageControl.Team.HOME) ? 1 : 2) : (team.equals(OddPageControl.Team.HOME) ? 1 : team.equals(OddPageControl.Team.DRAW) ? 3 : 2);
				Link lnkHome = Link.xpath(String.format("%s//table/tbody/tr[1]//td[@class='runner-cell']/span", xpathMarket));
				Link lnkAway = Link.xpath(String.format("%s//table/tbody/tr[2]//td[@class='runner-cell']/span", xpathMarket));
				String homeTeam = lnkHome.getText();
				String awayTeam = lnkAway.getText();
				String selectedTeam = team.equals(OddPageControl.Team.HOME) ? homeTeam : team.equals(OddPageControl.Team.DRAW) ? MemberConstants.BetSlip.DRAW : awayTeam;
				String xpathOdd = String.format("%s//table/tbody/tr[%s]//button[@class='selection-btn powertip %s']/span[@class='price']", xpathMarket, iSelectedTeam, backOrLay);
				Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)
				Icon icFavorite = Icon.xpath(String.format("%s//i[contains(@class,'multi-market add-multi-market')]",xpathMarket));

				if (lnkSelectedOdd.isDisplayedShort(2)) {
					boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
					String oddRate = lnkSelectedOdd.getText();

					// check whether odd rate is empty than we will edit odd = 100 for Back
					String checkOddRate = oddRate.isEmpty() ? "100" : oddRate;
					boolean isAllow = true;
					// check whether Lay odd rate is less than 4 we will place this bet
					if (!isBack && Double.parseDouble(checkOddRate) > 4) {
						System.out.println("DEBUG: Do not allow as LAY odds "+ oddRate +" is too big");
						isAllow = false;
					}
					if (isAllow) {
						return getOdd(lnkSelectedOdd, oddRate, marketName, isInPlayOnOdd, selectedTeam, i,icFavorite);
					}
				}
				if (i > limit) {
					return null;
				}
				i++;
			}
		}
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate,String marketName, boolean isInPlay, String selectedTeam, int eventIndex,Icon icFavorite) {
		return new Odd.Builder()
				.eventName(marketName)
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.icFavorite(icFavorite)
				.build();
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate,String homeTeam, String awayTeam, boolean isInPlay, String selectedTeam, int eventIndex,Icon icFavorite) {
		return new Odd.Builder()
				.eventName(String.format("%s v %s", homeTeam, awayTeam))
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.icFavorite(icFavorite)
				.build();
	}

	public void addRemoveFavorite(Odd odds, boolean isAdd){
		String attribute = odds.getCcFavorite().getAttribute("class");
		Icon icFav;
		if(attribute.contains("plus") && isAdd==true) {
			//Add to favorite
			odds.getCcFavorite().click();
			return;
		}
		if(attribute.contains("minus") && isAdd==false) {
			//remove out favorite
			odds.getCcFavorite().click();
			return;
		}
	}
}
