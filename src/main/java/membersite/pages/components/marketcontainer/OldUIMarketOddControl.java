package membersite.pages.components.marketcontainer;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import common.MemberConstants;
import membersite.controls.OddPageControl;
import membersite.objects.funsport.Odd;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class OldUIMarketOddControl extends BaseElement {
	private String _xpath =""; //div[@id='fullMarketOdds']
	public  Label lblEventTitle = Label.xpath("//span[contains(@class,'title favorites-title')]");
	public Label lblMarketTitle = Label.xpath("//div[@id='fullMarketOdds']//h2//span[@class='star-text']");
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

	private OldUIMarketOddControl(By locator, String xpath, boolean isMatchOdds) {
		super(locator);
		_xpath = xpath;
		_isMatchOdds = isMatchOdds;

	}
	public static OldUIMarketOddControl xpath(String xpathExpression, boolean isMatchOdds) {
		return new OldUIMarketOddControl(By.xpath(xpathExpression), xpathExpression, isMatchOdds);
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
			String xpathMarkets = String.format("%s", _xpath);
			int i = 1;
			String xpathMarket = String.format("%s[%s]", xpathMarkets, i);
			Label lblMarketName = Label.xpath(String.format("%s//span[@class='star-text']",xpathMarket));
			if (!lblMarketName.isPresent(2)){
				return null;
			}
			Label lblSuspend = Label.xpath(String.format("%s//div[@class='status-overlay suspended']", xpathMarket));
			if (!lblSuspend.isDisplayedShort(2)) {
				Icon iconInPlay = Icon.xpath(String.format("%s//span[@class='market-status-icon market-inplay']", _xpath));
				//Label lnkStartTime = Label.xpath(String.format("%s//div[@class='status going-inplay time-inplay']", xpathMarket));
				String backOrLay = isBack ? "back" : "lay";
				String marketName =lblMarketName.getText();
				int iSelectedTeam = _isMatchOdds ? (team.equals(OddPageControl.Team.HOME) ? 1 : 2) : (team.equals(OddPageControl.Team.HOME) ? 1 : team.equals(OddPageControl.Team.DRAW) ? 3 : 2);
				Link lnkHome = Link.xpath(String.format("%s//table/tbody/tr[1]//td[@class='runner-cell']/span", xpathMarket));
				Link lnkAway = Link.xpath(String.format("%s//table/tbody/tr[2]//td[@class='runner-cell']/span", xpathMarket));
				String homeTeam = lnkHome.getText();
				String awayTeam = lnkAway.getText();
				String selectedTeam = team.equals(OddPageControl.Team.HOME) ? homeTeam : team.equals(OddPageControl.Team.DRAW) ? MemberConstants.BetSlip.DRAW : awayTeam;
				String xpathOdd = String.format("%s//table/tbody/tr[%s]//td[@class='odds highlight']//div[contains(@class,' %s')]/span[@class='price']", xpathMarket,iSelectedTeam, backOrLay);
				Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)

				if (lnkSelectedOdd.isDisplayedShort(2)) {
					boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
					String oddRate = lnkSelectedOdd.getText();

					// check whether odd rate is empty than we will edit odd = 100 for Back
					String checkOddRate = oddRate.isEmpty() ? "100" : oddRate;
					boolean isAllow = true;
					// check whether Lay odd rate is less than 4 we will place this bet
					if(!isBack && Double.parseDouble(checkOddRate) > 4) {
						System.out.println("DEBUG: Lay odds is too large, skip the test case");
						isAllow = false;
						return null;
					}
					if(isAllow){
						return getOdd(lnkSelectedOdd, oddRate,marketName, isInPlayOnOdd, selectedTeam, i);
					}
				}
			}
			return null;
	}

	public Odd getSelectionOdds(int selectionIndex, boolean isBack) {
		String xpathMarkets = String.format("%s", _xpath);
		int i = 1;
		String xpathMarket = String.format("%s[%s]", xpathMarkets, i);
		Label lblMarketName = Label.xpath(String.format("%s//span[@class='star-text']",xpathMarket));
		if (!lblMarketName.isPresent(2)){
			return null;
		}
		Label lblSuspend = Label.xpath(String.format("%s//div[@class='status-overlay suspended']", xpathMarket));
		if (!lblSuspend.isDisplayedShort(2)) {
			Icon iconInPlay = Icon.xpath(String.format("%s//span[@class='market-status-icon market-inplay']", _xpath));
			//Label lnkStartTime = Label.xpath(String.format("%s//div[@class='status going-inplay time-inplay']", xpathMarket));
			String backOrLay = isBack ? "back" : "lay";
			String marketName =lblMarketName.getText();
			int iSelectedTeam = selectionIndex;
			Link lnlSelection = Link.xpath(String.format("%s//table/tbody/tr[%s]//td[@class='runner-cell']/span", xpathMarket,iSelectedTeam));
			String selectionName = lnlSelection.getText();
		String xpathOdd = String.format("%s//table/tbody/tr[%s]//td[@class='odds highlight']//div[contains(@class,' %s ')]/span[@class='price']", xpathMarket,iSelectedTeam, backOrLay);
			Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)

			if (lnkSelectedOdd.isDisplayedShort(2)) {
				boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
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

	public List<ArrayList<String>>  getUIForeCast(){
		List<ArrayList<String>> forecastList = new ArrayList<>();
		String xPath;
		Label lblSelection;
		Label lblForecast;
		int i =1;
		while (true){
			xPath =String.format("//table/tbody/tr[%s]//td[@class='runner-cell']/span",i);
			lblSelection = Label.xpath(xPath);
			if(!lblSelection.isDisplayed())
				return forecastList;
			lblForecast = Label.xpath(String.format("%s/following::div[@class='actual-pnl'][1]/span",xPath));
			forecastList.add( new ArrayList<String>( Arrays.asList(lblSelection.getText(), lblForecast.getText())));
			i = i+1;
		}
	}

	public List<String> getMarketInfo(){
		Label lblFirstSelection = Label.xpath("//table/tbody/tr[@class='runner-even event-meta'][1]") ;
		String databet =lblFirstSelection.getAttribute("data-bet");
		String[] lstDataBet = databet.split("\\|");
		List<String> lstInfo = new ArrayList<>();
		//add event id
		lstInfo.add(lstDataBet[0]);
		//add event name
		lstInfo.add(lstDataBet[1]);
		// add market id
		lstInfo.add(lstDataBet[2]);
		// add market name
		lstInfo.add(lstDataBet[3]);
		// add market type
		lstInfo.add(lstDataBet[4]);
		return lstInfo;
	}
	public List<String> getListSelection(){

		Label lblSelection;
		int i =1;
		List<String> lstSelection = new ArrayList<>();
		while (true){
			lblSelection = Label.xpath(String.format("//table/tbody/tr[%s]//td[@class='runner-cell']/span",i));
			if(!lblSelection.isDisplayed())
				return lstSelection;
			lstSelection.add(lblSelection.getText());
			i = i+1;
		}
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
