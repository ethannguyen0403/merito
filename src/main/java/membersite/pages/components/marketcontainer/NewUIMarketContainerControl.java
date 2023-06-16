package membersite.pages.components.marketcontainer;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import com.paltech.utils.DateUtils;
import common.MemberConstants;
import membersite.controls.FancyContainerControl;
import membersite.controls.FancyContainerControlOldUI;
import membersite.objects.sat.Event;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Market;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;
import membersite.pages.popup.RulePopup;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class NewUIMarketContainerControl extends MarketContainerControl {
	public static String _xpath;
	private Label lblEventStarTime = Label.xpath("//div[contains(@class,'market-wrapper-info')]//div[@class='float-left']");
	private Label lblInplay = Label.xpath("//div[contains(@class,'text-inplay')]");
	private Button btnRule = Button.xpath("//span[contains(@class,'market-rules-span')]");
	private Label lblMatched = Label.xpath("//span[@class='total-matched-label']");
	private Label lblTotalSelections = Label.xpath("//span[contains(@class,'selections-count')]");
	private String xPathForeCastLable = "//div[contains(@class,'runner-name')]//span[contains(@class,'forecast-liability-container')]";
	// WWicket Fancy section
	private Label lblWcFancyMarketName = Label.xpath("//div[contains(@class,'fancy-container')]//span[@class='market-name']");
	private Label lblEventMarketName =Label.xpath("//div[contains(@class,'highlight-page market')]//div[contains(@class,'title')]");
	private Label lblSuspend = Label.xpath("//div[contains(@class,'highlight-page market')]//span[contains(@class,'suspended-label')]");
	private String lblOddContainerxPath = "//div[contains(@class,'highlight-page market')]//div[contains(@class,'table-odds')]";
	private String lblSelectionListXPath = "//div[contains(@class,'highlight-page market')]//div[contains(@class,'table-odds')]//div[contains(@class,'market-container')]/following-sibling::div";
	private String lblSelectionName ="//div[contains(@class,'runner-name')]//span[2]";
	private String lblOddListXPath = "//div[contains(@class,'cell-odds')]";
	private String lblOddItem = "//div[contains(@class,'pending-odds')]";
	private Tab tabWicketFancy =Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.WICKET_FANCY_TITILE));
	private Tab tabCentralFancy =  Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.CENTRAL_FANCY_TITILE));
	private Tab tabManualOdds = Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.CENTRAL_BOOKMAKER_TITLE));
	private Tab tabWicketBookmaker = Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.WICKET_BOOKMAKER_TITLE));
	private Tab tabFancy = Tab.xpath(String.format("//div[contains(@class,'fancy-container')]//span[text()='%s']", MemberConstants.FANCY_TITILE));
	public Label oddsSpinIcon = Label.xpath("//div[@id='odds-content']//div[@class='snipper-content']");
	public FancyContainerControl wcFancyContainerControl = FancyContainerControl.xpath("//span[text()='Wicket Fancy']//ancestor::div[contains(@class,'fancy-container')]");
	public FancyContainerControlOldUI odlUIFancyContainerControl = FancyContainerControlOldUI.xpath("//div[@id='fair-27-fancy']");
	public FancyContainerControl centralFancyContainerControl = FancyContainerControl.xpath("//span[text()='Central Fancy']//ancestor::div[contains(@class,'fancy-container')]");
	public FancyContainerControl fancyContainerControl = FancyContainerControl.xpath("//span[text()='Fancy']//ancestor::div[contains(@class,'fancy-container')]");
	public enum Status {NA, IN_PLAY, COMING}
	
	public List<ArrayList<String>> getUIForeCast()
	{
		List<ArrayList<String>> forecastList = new ArrayList<>();
		int totalSelection = getTotalSelection();
		String forecastValue;
		String selectionName;
		for(int i = 0; i< totalSelection; i++)
		{
			forecastValue = Label.xpath(String.format("(%s)[%d]", xPathForeCastLable,i+1)).getText();
			selectionName = Label.xpath(String.format("%s[%s]%s",lblSelectionListXPath,i+1,lblSelectionName)).getText();
			forecastList.add( new ArrayList<String>( Arrays.asList(selectionName, forecastValue)));
		}
		return forecastList;
	}

	public List<String> getListSelection(){
		List<String> selectionlst = new ArrayList<>();
		for (WebElement e: Label.xpath(String.format("%s%s",lblSelectionListXPath,lblSelectionName)).getWebElements()) {
			selectionlst.add(e.getText());
		}
		return selectionlst;
	}

	/*public List<String> calculateForecast(List<String> forecasteBeforePlaceMatched ,int placedSelection, boolean isBack, String profit, String liabilty)
	{
		Double _profit = Double.parseDouble(profit);
		Double _liability = Double.parseDouble(liabilty)*(-1);
		List<String> forecastList = new ArrayList<>();

		for(int i = 0; i< forecasteBeforePlaceMatched.size(); i++)
		{
			if(forecasteBeforePlaceMatched.get(i) == "") {
				forecastList.add("0");
			}
			Double _currentForecast = Double.parseDouble(forecasteBeforePlaceMatched.get(i));
			if(placedSelection == i+1) {
				if(isBack)
					forecastList.add(String.format("%.2f", _currentForecast + _profit));
				else
					forecastList.add(String.format("%.2f", _currentForecast + _liability));
			}
			else
			{
				if(isBack)
					forecastList.add(String.format("%.2f", _currentForecast + _liability));
				else
					forecastList.add(String.format("%.2f", _currentForecast + _profit));
			}
		}
		return forecastList;
	}
*/

	public boolean isLblInPlayDisplay()
	{
		return lblInplay.isDisplayed();
	}

	public String getEventStartTime(){
		return lblEventStarTime.getText();
	}

	public String getRuleButton(){
		return btnRule.getText();
	}
	public RulePopup clickRuleButton(){
		btnRule.click();
		return new RulePopup();
	}
	public String getTotalMatched(){
		return lblMatched.getText();
	}

	private int getTotalSelection()
	{
		return Label.xpath(String.format("%s%s",lblSelectionListXPath,lblSelectionName)).getWebElements().size();
	}

	public List<Label> getOddsListLabel(int selectionIndex, boolean isBack)
	{
		List<Label> list = new ArrayList<>();
		String xPathOddsList = String.format("%s[%d]%s",lblSelectionListXPath,selectionIndex,lblOddListXPath);

		int countOddsLabel = Label.xpath(xPathOddsList).getWebElements().size();
		String backOrLay = isBack ? "back" : "lay";

		if(isBack) {
			for(int i= countOddsLabel/2  ; i>0 ; i--) {
				list.add(Label.xpath(String.format("%s[%d]%s",xPathOddsList,i,lblOddItem)));
			}
		}else{
			for(int i= countOddsLabel/2; i<countOddsLabel; i++){
				list.add(Label.xpath(String.format("%s[%d]%s",xPathOddsList,i+1,lblOddItem)));
			}
		}
		return list;
	}

	private Double getOdds(String xpath)
	{
		Label lblOdd = Label.xpath(xpath);
		Double oddsValue = !lblOdd.getText().isEmpty()? Double.parseDouble(lblOdd.getText()) : 1.01;
		return oddsValue;
	}

	public String getTitle(boolean isEventName)
	{
		String title =  getTitle();
		if(Objects.isNull(title)){
			lblEventMarketName.getText(1);
		}
		String[] titles = title.split("/");
		return isEventName? titles[0].trim() : titles[1].trim();
	}

	public String getTitle()
	{
		lblEventMarketName.isTextDisplayed("Market",1);
		return lblEventMarketName.getText();
	}

	public int getSelectionHaveMinOdds(boolean isBack)
	{
		Label lblSelections = Label.xpath(lblSelectionListXPath);
		int totalSelection = lblSelections.getWebElements().size();
		int index = 1;
		Double odd = 0.0;
		Double min = Double.parseDouble(getOddsListLabel(index,isBack).get(0).getText());

		// find the selection have BACK/ODDS odds is min
		for (int i = 0; i < totalSelection; i++)
		{
			odd = Double.parseDouble(getOddsListLabel(i+1,isBack).get(0).getText());
			if(min > odd){
				min = odd;
				index = i +1;
			}
		}
		return index;
	}
	public void waitControlLoadCompletely(int second)
	{
		String title = lblEventMarketName.getText();
		String temp = "";
		for(int i = 0; i <= second; i++)
		{
			temp = lblEventMarketName.getText();
			if(!title.isEmpty() || !title.equals(temp))
				break;
			else {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			i = i +1;
		}
	}
	public Market getMarket(Event event, int selectionIndex, boolean isBack)
	{
		waitControlLoadCompletely(2);
		String marketName = getTitle(false);
		String selectionName = Label.xpath(String.format("%s[%d]%s",lblSelectionListXPath,selectionIndex,lblSelectionName)).getText();

		return getMarket(event,marketName,selectionName,isBack, getOddsListLabel(selectionIndex,isBack).get(0));
	}

	public UnderageGamblingPopup clickOdd(Market market) {
		market.getBtnOdd().click();
		return new UnderageGamblingPopup();
	}

	private Market getMarket (Event event, String marketName, String selectionName,boolean isBack,Label odds) {
		return new Market.Builder()
				.eventName(event.getEventName())
				.marketName(marketName)
				.selectionName(selectionName)
				.isBack(isBack)
				.btnOdds(odds)
				.build();
	}

	public String convertDate(String date){
		date = date.replace("\n"," ");
		String prefit;
		if(date.contains(MemberConstants.STARTINGIN))
			prefit = MemberConstants.STARTINGIN;
		else if(date.contains(MemberConstants.STARTINGSOON))
			prefit = MemberConstants.STARTINGSOON;
		else
			prefit = date.trim().split(" ")[0];
		String returnDate="";
		switch (prefit){
			case MemberConstants.TODAY:
				returnDate = date.replace(MemberConstants.TODAY,String.format("%s,",DateUtils.getDate(0, "MMM dd", MemberConstants.TIMEZONE)));
				break;
			case MemberConstants.TOMORROW:
				returnDate = date.replace(MemberConstants.TOMORROW,String.format("%s,",DateUtils.getDate(1, "MMM dd", MemberConstants.TIMEZONE)));
				break;
			case MemberConstants.STARTINGIN:
				returnDate = date.replace(MemberConstants.STARTINGIN,String.format("%s,",DateUtils.getDate(0, "MMM dd", MemberConstants.TIMEZONE)));
				break;
			case MemberConstants.STARTINGSOON:
				returnDate = date.replace(MemberConstants.STARTINGSOON,String.format("%s,",DateUtils.getDate(0, "MMM dd", MemberConstants.TIMEZONE)));
				break;
			default:
				returnDate = DateUtils.convertToDate(date,"MMM dd, hh:mm").toString();
				//returnDate = String.format("%s,",DateUtils.getDate(0, "MMM dd hr:mn",Constants.TIMEZONE));
				// default statements
		}
		return returnDate;
	}

	public boolean verifyOddsIsClickable(Event event)
	{
		int getTotalSelection = getTotalSelection();
		for(int i = 0; i< getTotalSelection ; i++)
		{
			List<Label> lblBackOdds = getOddsListLabel(i+1,true);
			List<Label> lblLayOdds = getOddsListLabel(i+1,false);
			for(int j =0; j<lblBackOdds.size(); j++)
			{
				if(!lblBackOdds.get(i).isClickable(2)){
					System.out.println("Market Page - Back Odds buttons are not clickable");
					return false;
				}

				if(!lblLayOdds.get(i).isClickable(3))
				{
					System.out.println("Market Page - Lay Odds buttons are not clickable");
					return false;
				}
			}
		}
		System.out.println("Market Page - Odds buttons are clickable");
		return true;
	}

	/**
	 * get the info [Sport id, event id, market id, market type]
	 * @return
	 */
	public List<String> getInfoFromURL(){
		List<String> infoURLLst= new ArrayList<>();
		String url = DriverManager.getDriver().getCurrentUrl();
		//https://fastg.beatus88.com/x/#/1/home/exchange/sport/market?sid=1&eid=30742993&mid=185825782&mtype=MATCH_ODDS
		url = url.split("sid=")[1];
		String temp =url.split("&",0)[0];
		infoURLLst.add(temp);
		url = url.split("eid=")[1];
		temp =url.split("&",0)[0];
		infoURLLst.add(temp);
		url = url.split("mid=")[1];
		temp=url.split("&",0)[0];
		infoURLLst.add(temp);
		url = url.split("mtype=")[1];
		temp =url.split("&",0)[0];
		infoURLLst.add(temp);
		return infoURLLst;
	}

	public void clickOdds(Market market){
		Label lblOdds = market.getBtnOdd();
	}

	public void activeProduct(String products){
		switch (products){
		case "Wicket Fancy":
			tabWicketFancy.click();
			return;
		case "Central Fancy":
			tabCentralFancy.click();
			return;
		case "Manual Odds":
			tabManualOdds.click();
			return;
		case "Fancy":
			tabFancy.click();
			return;
		default:
			tabWicketBookmaker.click();
			return;
	}}
	public FancyMarket getFancyMarketInfo(FancyMarket fcMarket){
		switch (fcMarket.getMaketType()){
			case "WICKET_FANCY":
				return wcFancyContainerControl.getFancyMarketInfo(fcMarket);
			case "CENTRAL_FANCY":
				return centralFancyContainerControl.getFancyMarketInfo(fcMarket);
			default:
				return fancyContainerControl.getFancyMarketInfo(fcMarket);
		}
	}


}
