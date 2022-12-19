package membersite.controls.sat;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import controls.Table;
import membersite.objects.sat.Event;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.utils.betplacement.FancyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class SATEventContainerControl extends BaseElement {
	private String _xpath ;//= "//div[@class='container-event-info']";
	private Label lblSportHeader = Label.xpath("//div[@class='sport-header']/h2");
	private String lblSuspendXPath;
	private String lblInplayXPath;
	private String lblEventNameXPath;
	private String lblListEventXPath;
	private Label lblNoEvent;
	private Button btnOdds;
	public enum Status {NA, IN_PLAY, COMING}
	private String lblEventStartTimeXpath = "%s//div[contains(@class,'event-start-time')]/span";
	private Table tblEvents;
	private SATEventContainerControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		lblNoEvent = Label.xpath("//div[@class='text-center']"); // Define if exist the label" There are no markets available!"
		lblSuspendXPath = "%s//div[contains(@class,'status-overlay')]";
		lblInplayXPath= "%s//*[contains(@class,'inplay')][2]";
		lblEventNameXPath = "%s//span[@class='home-team'or contains(@class,'meto-text-primary')]";
		lblListEventXPath ="%s//li[contains(@class,'coming-up')]";
		tblEvents = Table.xpath(String.format("%s//table[contains(@class,'table-odds')]",_xpath),1);
	}

	public static SATEventContainerControl xpath(String xpathExpression) {
		return new SATEventContainerControl(By.xpath(xpathExpression), xpathExpression);
	}
	public String getSportHeader()
	{
		return lblSportHeader.getText();
	}

	public List<String> getAllEventHighlight()
	{
		if (!lblNoEvent.isDisplayedShort(3)) {
			List<String> lstEvent = new ArrayList<>();
			String xpathEvents = String.format(lblListEventXPath, _xpath);
			Link lnkEventName = Link.xpath(String.format(lblEventNameXPath, xpathEvents));
			for (WebElement e : lnkEventName.getWebElements()) {
				lstEvent.add(e.getText());
			}
			return lstEvent;
		}
		return null;
	}

	public Event setEventLink(Event event){
		if (!lblNoEvent.isDisplayedShort(3)) {
			String xpathEvents = String.format(lblListEventXPath, _xpath);
			int i = 1;
			int lstTotal = 100;
			Event newEvent = event;
			while(true) {
				String xpathEvent = String.format("(%s)[%s]", xpathEvents, i);
				Label lblEvent = Label.xpath(xpathEvent);
				if (!lblEvent.isPresent(2)){
					System.out.println("Event "+ event.getEventName() + " does not display in the list");
					return null;
				}
				Link lnkEventName = Link.xpath(String.format(lblEventNameXPath, xpathEvent));
				if(event.getEventName().contains(lnkEventName.getText().trim())){
					String eventStartTime = Label.xpath(String.format(lblEventStartTimeXpath,xpathEvent)).getText().trim();
					newEvent.setLinkEvent(lnkEventName);
					newEvent.setStartTime(eventStartTime);
					return newEvent;
				}
				if (i > lstTotal) {
					System.out.println("Event " + event.getEventName() + " does not display in the list");
					return null;
				}
				i++;
			}
		} else {
			System.out.println("Event "+ event.getEventName() + " does not display in the list");
			return null;
		}
	}

	public Event getEvent(boolean isInplay, boolean isSuspend, int limit, int eventIndex){
		if (!lblNoEvent.isDisplayedShort(3)) {
			String xpathEvents = String.format(lblListEventXPath, _xpath);
			int i = Math.max(eventIndex, 1);
			Label lblEvents = Label.xpath(String.format("%s", xpathEvents));
			int lstTotal = lblEvents.getWebElements().size();
			if(limit!= 0)
			{
				lstTotal = limit;
			}
			while(true) {
				String xpathEvent = String.format("(%s)[%s]", xpathEvents, i);
				Label lblEvent = Label.xpath(xpathEvent);
				if (!lblEvent.isPresent(2)){
					return null;
				}
				Label lblSuspend = Label.xpath(String.format(lblSuspendXPath, xpathEvent));
				Icon iconInPlay = Icon.xpath(String.format(lblInplayXPath, xpathEvent));
				Link lnkEventName = Link.xpath(String.format(lblEventNameXPath, xpathEvent));
				boolean _isInplay =iconInPlay.isDisplayedShort(2) ;
				boolean _isSuspend =lblSuspend.isDisplayedShort(2) ;
				if(_isInplay == isInplay && _isSuspend ==isSuspend){
					String eventStartTime = Label.xpath(String.format(lblEventStartTimeXpath,xpathEvent)).getText().trim();
					return getEvent(lnkEventName, iconInPlay.isDisplayedShort(2), lblSuspend.isDisplayedShort(2),eventStartTime);
				}

				if (i > lstTotal)
					return null;
				i++;
			}
		} else {
			return null;
		}

	}

	public boolean isOddsUnclickable(String eventName){
	//	int i = getEventIndex(eventName);
		String xpath = String.format("//span[contains(text(),'%s')]//following::li[1]//button",eventName);
		int n = Button.xpath(String.format(xpath,eventName)).getWebElements().size();
		for(int i =0; i<n; i++)
		{
			Button btnOdd = Button.xpath(String.format("(%s)[%d]",xpath,i+1));
			if(!btnOdd.isClickable(timeOutShortInSeconds)){
				System.out.println("ERROR! Odds button list in event page able to click");
				return false;
			}
		}
		return true;
	}

	public void clickEvent(Event event)
	{
		event.getLinkEvent().click();
	}
	public void clickEvent(String event)
	{
		if (!lblNoEvent.isDisplayedShort(3)) {
			String xpathEvents = String.format(lblListEventXPath, _xpath);
			Link lnkEventName = Link.xpath(String.format(lblEventNameXPath, xpathEvents));
			boolean flag = false;
			for (WebElement e :lnkEventName.getWebElements()) {
				if(e.getText().equals(event)) {
					e.click();
					flag = true;
					break;
				}
			}
			if(!flag){
				System.out.println(String.format("Debug: No click action on the event %s ",event));
			}

		}
	}

	private Event getEvent (Link lnkEvent, boolean isInplay, boolean isSuspend, String startTime) {
		return new Event.Builder()
				.eventName(lnkEvent.getText())
				.lnkEvent(lnkEvent)
				.isSuspend(isSuspend)
				.inPlay(isInplay)
				.startTime(startTime)
				.build();
	}

	private boolean getEventIDHasPorductData(String productName,String eventID){
		return FancyUtils.isEventHasMarketType(eventID,productName);
	}

	/**
	 * To get the event that has Wicket Fancy and and click on that event
	 * @return
	 */
	public String getEventIDHasMarketData(String product){
		// Scan all the list event in Cricket sport page
		int i =1;
		Link lnkEvent;
		while (true){
			lnkEvent =(Link) tblEvents.getControlOfCell(1,1,i,"div[@class='market-container']");
			if(!lnkEvent.isDisplayed())
				return null;
			// get event id
			String eventID=lnkEvent.getAttribute("id");

			if(getEventIDHasPorductData(product,eventID)){
				return eventID;
			}
			i++;
			// check api tha has fany
		}
	}

	public MarketPage clickOnRowofEventID(String eventID)
	{
		int i =1;
		Link lnkEvent;
		while (true){
			lnkEvent =(Link) tblEvents.getControlOfCell(1,1,i,"div[@class='market-container']");
			if(!lnkEvent.isDisplayed())
				return null;
			if(lnkEvent.getAttribute("id").equalsIgnoreCase(eventID)){
				lnkEvent.click();
				MarketPage marketPage = new MarketPage();
				marketPage.waitMenuLoading();
				return marketPage;
			}
			i++;
			// check api tha has fany
		}
	}

	private int getEventIndex(String eventName){
		int i =1;
		Link lnkEvent;
		while (true){
			lnkEvent =(Link) tblEvents.getControlOfCell(1,1,i,"span[contains(@class,'home-team')]");
			if(!lnkEvent.isDisplayed()) {
				System.out.println("Debug! Not found event to click");
				return 0;
			}
			if(lnkEvent.getText().trim().contains(eventName)){
				return i;
			}
			i++;
		}
	}

	public Event getEventInfo(String eventName){
		int index = getEventIndex(eventName);


			String xpathEvents = String.format(lblListEventXPath, _xpath);

			Label lblEvents = Label.xpath(String.format("%s", xpathEvents));

				String xpathEvent = String.format("(%s)[%s]", xpathEvents, index);
				Label lblEvent = Label.xpath(xpathEvent);
				if (!lblEvent.isPresent(2)){
					return null;
				}
				Label lblSuspend = Label.xpath(String.format(lblSuspendXPath, xpathEvent));
				Icon iconInPlay = Icon.xpath(String.format(lblInplayXPath, xpathEvent));
				Link lnkEventName = Link.xpath(String.format(lblEventNameXPath, xpathEvent));
				boolean _isInplay =iconInPlay.isDisplayedShort(2) ;
				boolean _isSuspend =lblSuspend.isDisplayedShort(2) ;

					String eventStartTime = Label.xpath(String.format(lblEventStartTimeXpath,xpathEvent)).getText().trim();
					return getEvent(lnkEventName, _isInplay, _isSuspend,eventStartTime);


		}





}
