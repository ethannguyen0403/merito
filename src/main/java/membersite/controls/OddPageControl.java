package membersite.controls;

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
public class OddPageControl extends BaseElement {
	private String _xpath = ""; //div[@id='odds-content']
	private Label lblNoEvent;
	public enum Team {HOME, DRAW, AWAY}
	public enum Status {NA, IN_PLAY, COMING}
	private boolean _isMoneyLine;

	private OddPageControl(By locator, String xpath, boolean isMoneyLine) {
		super(locator);
		_xpath = xpath;
		lblNoEvent = Label.xpath("//div[@id='content-odds']/div[@class='highlight-odds-error']");
		_isMoneyLine = isMoneyLine;

	}
	
	public static OddPageControl xpath(String xpathExpression, boolean isMoneyLine) {
		return new OddPageControl(By.xpath(xpathExpression), xpathExpression, isMoneyLine);
	}

//	public EventPage clickEvent(String event){
//		System.out.println("Debug: Click on event "+event);
//		Link.xpath(String.format("%s//span[contains(@class,'home-team') and contains(text(),'%s')]",_xpath,event)).click();
//		return new EventPage();
//	}
//	public EventPage clickEvent(Odd odds){
//		System.out.println("Debug: Click on event "+odds.getEventName());
//		Link.xpath(String.format("%s//div[@id='content-odds']//ul[@class='list-coupon']/li[@class='vevent coming-up'][%s]//div[@class='market-details']",_xpath,odds.getEventIndex())).click();
//		return new EventPage();
//	}
//
//	public EventPage clickOnRowofEventName(String eventName)
//	{
//		// On Highlight page, click on the event in input parameter
//
//		int i =1;
//		String rowXpath= "//div[@id='odds-content']//div[@class='sport-highlight-content']//ul[@class='list-coupon']/li";
//		Label lblHome;
//		while (true){
//			lblHome = Label.xpath(String.format("%s[%d]//span[contains(@class,'home-team')]",rowXpath,i));
//			if(!lblHome.isDisplayed()) {
//				System.out.println("Debug! Not found event to click");
//				return null;
//			}
//			//lblHome = Label.xpath(String.format("%s[%d]//span[contains(@class,'home-team')]",rowXpath,i));
//			String homnTeam = lblHome.getText().trim();
//			String awayTeam = Label.xpath(String.format("%s[%d]//span[contains(@class,'away-team')]",rowXpath,i)).getText().trim();
//			String expectedName = String.format("%s v %s",homnTeam,awayTeam);
//			if(expectedName.equalsIgnoreCase(eventName)){
//				lblHome.click();
//				return new EventPage();
//			}
//			i++;
//			// check api tha has fany
//		}
//	}

	public int getTotalEventInHighlight(){
		String xpathEvents;
		if (lblNoEvent.isInvisible(2)) {
			xpathEvents = String.format("%s//div[@id='content-odds']//ul[@class='list-coupon']/li[@class='vevent coming-up']", _xpath);
			Label.xpath(xpathEvents).getWebElements();
		}
		return -1;
	}

	public Odd getOdd(Team team, boolean isBack, boolean isOddEmpty, boolean is12, int limit, int eventIndex) {
		if (lblNoEvent.isInvisible(2)) {
			String xpathEvents = String.format("%s//div[@id='content-odds']//ul[@class='list-coupon']/li[@class='vevent coming-up']", _xpath);
			int i = Math.max(eventIndex, 1);
			while(true) {
				String xpathEvent = String.format("%s[%s]", xpathEvents, i);
				Label lblEvent = Label.xpath(xpathEvent);
				if (!lblEvent.isPresent(2)){
					return null;
				}
				Label lblSuspend = Label.xpath(String.format("%s//ul/li//div[@class='status-overlay']", xpathEvent));
				if (!lblSuspend.isDisplayedShort(2)) {
					Icon iconInPlay = Icon.xpath(String.format("%s/span[@class='in-play-icon']", xpathEvent));
					Link lnkHome = Link.xpath(String.format("%s//span[@class='home-team']", xpathEvent));
					Link lnkAway = Link.xpath(String.format("%s//span[@class='away-team']", xpathEvent));
					Label lnkStartTime = Label.xpath(String.format("%s//div[@class='start-time']", xpathEvent));
					Label lnkDataEvent = Label.xpath(String.format("%s//a[@class='event-link']", xpathEvent));
					String backOrLay = isBack ? "back" : "lay";
					String homeTeam = lnkHome.getText();
					String awayTeam = lnkAway.getText();
					String dataEventName = lnkDataEvent.getAttribute("data-eventname");
					int iSelectedTeam = is12 ? (team.equals(Team.HOME) ? 1 : 2) : (team.equals(Team.HOME) ? 1 : team.equals(Team.DRAW) ? 2 : 3);
					String selectedTeam = team.equals(Team.HOME) ? homeTeam : team.equals(Team.DRAW) ? MemberConstants.BetSlip.DRAW : awayTeam;

					String xpathOdd = String.format("%s//li[@class='prices-list']//li[@class='%s-cell'][%s]//span", xpathEvent, backOrLay, iSelectedTeam);
					Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)

					if (lnkSelectedOdd.isDisplayedShort(2)) {
						boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
						String oddRate = lnkSelectedOdd.getText();

						// check whether odd rate is less than 4 we will place this bet
						String checkOddRate = oddRate.isEmpty() ? "100" : oddRate;
						boolean isAllow = true;
						if(!isBack && Double.parseDouble(checkOddRate) > 4) {
							System.out.println("\b DEBUG: Lay Odds of Event "+dataEventName+"on selection:" + selectedTeam
									+" greater 4, just allow place Lay odd <4 to prevent the risk. Current odds is "+ checkOddRate);
							isAllow = false;
						}

						if (isOddEmpty) {
							if (oddRate.isEmpty()) {
								return getOdd(lnkSelectedOdd, oddRate, homeTeam, awayTeam, isInPlayOnOdd, selectedTeam, i,dataEventName);
							}
						} else {
							if (isAllow && !oddRate.isEmpty()) {
								return getOdd(lnkSelectedOdd, oddRate, homeTeam, awayTeam, isInPlayOnOdd, selectedTeam, i,dataEventName);
							}
						}
					}
				}
				if (i > limit) {
					return null;
				}
				i++;
			}
		}
		return null;
	}


	public Odd getOdd(Team team, boolean isBack, boolean isOddEmpty, Status statusType, int limit, int eventIndex) {
		if (lblNoEvent.isInvisible(2)) {
			String xpathEvents = String.format("%s//div[@id='content-odds']//ul[@class='list-coupon']/li[@class='vevent coming-up']", _xpath);
			int i = Math.max(eventIndex, 1);
			while(true) {
				String xpathEvent = String.format("%s[%s]", xpathEvents, i);
				Label lblEvent = Label.xpath(xpathEvent);
				if (!lblEvent.isPresent(2)){
					return null;
				}
				Label lblSuspend = Label.xpath(String.format("%s//ul/li//div[@class='status-overlay']", xpathEvent));
				if (!lblSuspend.isDisplayedShort(2)) {
					Icon iconInPlay = Icon.xpath(String.format("%s/span[@class='in-play-icon']", xpathEvent));
					Link lnkHome = Link.xpath(String.format("%s//span[@class='home-team']", xpathEvent));
					Link lnkSeparate = Link.xpath(String.format("%s//span[@class='sep']", xpathEvent));
					Link lnkAway = Link.xpath(String.format("%s//span[@class='away-team']", xpathEvent));
					Label lnkStartTime = Label.xpath(String.format("%s//div[@class='start-time']", xpathEvent));
					Label lnkDataEvent = Label.xpath(String.format("%s//a[@class='event-link']", xpathEvent));
					String backOrLay = isBack ? "back" : "lay";
					String homeTeam = lnkHome.getText();
					String separateCharacter = lnkSeparate.getText(); // v  or @
					String awayTeam = lnkAway.getText();
					String eventName = String.format("%s %s %s", homeTeam, separateCharacter, awayTeam);
					String dataEventName = lnkDataEvent.getAttribute("data-eventname");
					int iSelectedTeam = _isMoneyLine ? (team.equals(Team.HOME) ? 1 : 2) : (team.equals(Team.HOME) ? 1 : team.equals(Team.DRAW) ? 2 : 3);
					String selectedTeam = team.equals(Team.HOME) ? homeTeam : team.equals(Team.DRAW) ? MemberConstants.BetSlip.DRAW : awayTeam;

					String xpathOdd = String.format("%s//li[@class='prices-list']//li[@class='%s-cell'][%s]//span", xpathEvent, backOrLay, iSelectedTeam);
					Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)

					if (lnkSelectedOdd.isDisplayedShort(2)) {
						boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
						String oddRate = lnkSelectedOdd.getText();

						// check whether odd rate is less than 4 we will place this bet
						String checkOddRate = oddRate.isEmpty() ? "100" : oddRate;
						boolean isAllow = true;
						if(!isBack && Double.parseDouble(checkOddRate) > 4) {
							isAllow = false;
						}
						switch (statusType) {
							case NA:
								if (isOddEmpty) {
									if (oddRate.isEmpty()) {
										return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i,dataEventName);
									}
								} else {
									if (isAllow && !oddRate.isEmpty()) {
										return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i,dataEventName);
									}
								}
							case IN_PLAY:
								if (isInPlayOnOdd) {
									if (isOddEmpty) {
										if (oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i,dataEventName);
										}
									} else {
										if (isAllow && !oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i,dataEventName);
										}
									}
								}
							case COMING:
								if (!isInPlayOnOdd) {
									if (isOddEmpty) {
										if (oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i,dataEventName);
										}
									} else {
										if (isAllow && !oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i,dataEventName);
										}
									}
								}
						}
					}
				}
				if (i - eventIndex > limit) {
					return null;
				}
				i++;
			}
		}
		return null;
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate, String homeTeam, String awayTeam, boolean isInPlay, String selectedTeam, int eventIndex,String dataEventName) {
		return new Odd.Builder()
				.eventName(String.format("%s v %s", homeTeam, awayTeam))
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.dataEventName(dataEventName)
				.build();
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate, String eventName, boolean isInPlay, String selectedTeam, int eventIndex) {
		return new Odd.Builder()
				.eventName(eventName)
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.build();
	}
	private Odd getOdd (Link lnkSelectedOdd, String oddRate, String eventName, boolean isInPlay, String selectedTeam, int eventIndex,String dataEventName) {
		return new Odd.Builder()
				.eventName(eventName)
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.dataEventName(dataEventName)
				.build();
	}
}
