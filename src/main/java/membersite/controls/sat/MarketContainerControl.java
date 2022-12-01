package membersite.controls.sat;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import membersite.pages.all.beforelogin.popups.UnderageGamblingPopup;
import membersite.pages.all.tabexchange.components.popups.RulePopup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class MarketContainerControl extends BaseElement {
	public static String _xpath;
	private Label lblEventMarketName ;
	public Label lblSuspend ;
	private Label lblEventStarTime = Label.xpath("//div[contains(@class,'market-wrapper-info')]//div[@class='float-left']");
	private String lblOddContainerxPath ;
	private String lblSelectionListXPath;
	private String lblSelectionName ;
	private String lblOddListXPath ;
	private String lblOddItem;
	private boolean _isMatchOdds;
	private Label lblInplay = Label.xpath("//div[contains(@class,'text-inplay')]");
	private Button btnRule = Button.xpath("//span[contains(@class,'market-rules-span')]");
	private Label lblMatched = Label.xpath("//span[@class='total-matched-label']");
	public Label lblTotalSelections = Label.xpath("//span[contains(@class,'selections-count')]");
	private String xPathForeCastLable = "//div[contains(@class,'runner-name')]//span[contains(@class,'forecast-liability-container')]";
	// WWicket Fancy section
	private Label lblWcFancyMarketName = Label.xpath("//div[contains(@class,'fancy-container')]//span[@class='market-name']");


	public enum Status {NA, IN_PLAY, COMING}

	public MarketContainerControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;// Define if that market is suspended" There are no markets available!"
		//_isMatchOdds = isMatchOdds;
		lblEventMarketName =Label.xpath(String.format("%s//div[contains(@class,'title')]",_xpath));
		lblSuspend = Label.xpath(String.format("%s//span[contains(@class,'suspended-label')]",_xpath));
		lblOddContainerxPath = String.format("%s//div[contains(@class,'table-odds')]",_xpath);
		lblSelectionListXPath = String.format("%s//div[contains(@class,'market-container')]/following-sibling::div",lblOddContainerxPath);
		lblSelectionName ="//div[contains(@class,'runner-name')]//span[2]";
		lblOddListXPath = "//div[contains(@class,'cell-odds')]";
		lblOddItem = "//div[contains(@class,'pending-odds')]";

	}
	public static MarketContainerControl xpath(String xpathExpression) {
		return new MarketContainerControl(By.xpath(xpathExpression), xpathExpression);
	}

	public void waitControlLoadCompletely(int second)
	{
		String title = lblEventMarketName.getText();
		String temp = "";
		for(int i = 0; i < second; i++)
		{
			temp = lblEventMarketName.getText();
			if(!title.isEmpty() || !title.equals(temp))
				break;
			waitForElementToBePresent(lblEventMarketName.getLocator());
		}
	}

	/*public List<String> getForeCast()
	{
		List<String> forecastList = new ArrayList<>();
		int totalSelection = getTotalSelection();
		Label lblForecast;
		for(int i = 0; i< totalSelection; i++)
		{
			lblForecast = Label.xpath(String.format("(%s)[%d]", xPathForeCastLable,i+1));
			if(lblForecast.isDisplayed()){
				forecastList.add(lblForecast.getText());
			}else
				return null;
		}
		return forecastList;
	}*/
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
		waitControlLoadCompletely(timeOutShortInSeconds);
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

	public Market getMarket(Event event, int selectionIndex, boolean isBack)
	{
		//waitControlLoadCompletely(timeOutShortInSeconds);
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
		if(date.contains(FEMemberConstants.STARTINGIN))
			prefit = FEMemberConstants.STARTINGIN;
		else if(date.contains(FEMemberConstants.STARTINGSOON))
			prefit = FEMemberConstants.STARTINGSOON;
		else
			prefit = date.trim().split(" ")[0];
		String returnDate="";
		switch (prefit){
			case FEMemberConstants.TODAY:
				returnDate = date.replace(FEMemberConstants.TODAY,String.format("%s,",DateUtils.getDate(0, "MMM dd", FEMemberConstants.TIMEZONE)));
				break;
			case FEMemberConstants.TOMORROW:
				returnDate = date.replace(FEMemberConstants.TOMORROW,String.format("%s,",DateUtils.getDate(1, "MMM dd", FEMemberConstants.TIMEZONE)));
				break;
			case FEMemberConstants.STARTINGIN:
				returnDate = date.replace(FEMemberConstants.STARTINGIN,String.format("%s,",DateUtils.getDate(0, "MMM dd", FEMemberConstants.TIMEZONE)));
				break;
			case FEMemberConstants.STARTINGSOON:
				returnDate = date.replace(FEMemberConstants.STARTINGSOON,String.format("%s,",DateUtils.getDate(0, "MMM dd", FEMemberConstants.TIMEZONE)));
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
				if(!lblBackOdds.get(i).isClickable(timeOutShortInSeconds)){
					System.out.println("Market Page - Back Odds buttons are not clickable");
					return false;
				}

				if(!lblLayOdds.get(i).isClickable(timeOutShortInSeconds))
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
		try{

		} catch (WebDriverException ex)
		{

		}
	}
}
