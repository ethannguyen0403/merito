package membersite.controls.sat;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.utils.DateUtils;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import membersite.pages.all.beforelogin.popups.UnderageGamblingPopup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class SATMarketContainerControl extends BaseElement {
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
	public Label lblTotalSelections = Label.xpath("//th[@class='selections-count']/span");
	private String xPathForeCastLable = "//div[contains(@class,'runner-name')]//span[@class='forecast-liability-container']";
	// WWicket Fancy section
	private Label lblWcFancyMarketName = Label.xpath("//div[contains(@class,'fancy-container')]//span[@class='market-name']");


	public enum Status {NA, IN_PLAY, COMING}

	public SATMarketContainerControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;// Define if that market is suspended" There are no markets available!"
		//_isMatchOdds = isMatchOdds;
		lblEventMarketName =Label.xpath(String.format("%s//div[contains(@class,'title')]",_xpath));
		lblSuspend = Label.xpath(String.format("%s//span[contains(@class,'suspended-label')]",_xpath));
		lblOddContainerxPath = String.format("%s//div[contains(@class,'table-odds')]",_xpath);
		lblSelectionListXPath = String.format("%s//div[@class='market-container']/following-sibling::div",lblOddContainerxPath);
		lblSelectionName ="//div[contains(@class,'runner-name')]//span[2]";
		lblOddListXPath = "//div[contains(@class,'cell-odds')]";
		lblOddItem = "//div[contains(@class,'pending-odds')]";

	}
	public static SATMarketContainerControl xpath(String xpathExpression) {
		return new SATMarketContainerControl(By.xpath(xpathExpression), xpathExpression);
	}
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

	public List<String> getForeCast()
	{
		List<String> forecastList = new ArrayList<>();
		int totalSelection = getTotalSelection();

		Label lblForecast;

		for(int i = 0; i< totalSelection; i++)
		{
			lblForecast = Label.xpath(String.format("(%s)[%d]", xPathForeCastLable,i+1));
			forecastList.add(lblForecast.getText());
		}
		return forecastList;
	}

	public List<String> calculateForecast(List<String> forecasteBeforePlaceMatched ,int placedSelection, boolean isBack, String profit, String liabilty)
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
//	public RulePopup clickRuleButton(){
//		btnRule.click();
//		return new RulePopup();
//	}
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
		String xPathOddsList = String.format("%s[%d]%s[%s]",lblSelectionListXPath,selectionIndex,lblOddListXPath);


		int countOddsLabel = Label.xpath(xPathOddsList).getWebElements().size();
	///	String backOrLay = isBack ? "back" : "lay";

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

	public List<String> getListSelection(){
		List<String> selectionlst = new ArrayList<>();
		for (WebElement e: Label.xpath(String.format("%s%s",lblSelectionListXPath,lblSelectionName)).getWebElements()) {
			selectionlst.add(e.getText());
		}
		return selectionlst;
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
		waitControlLoadCompletely(timeOutShortInSeconds);
		String marketName = getTitle(false);
		String selectionName = Label.xpath(String.format("%s[%d]%s",lblSelectionListXPath,selectionIndex,lblSelectionName)).getText();
		return getMarket(event,marketName,selectionName,isBack, getOddsListLabel(selectionIndex,isBack).get(0));
	}

	public UnderageGamblingPopup clickOdd(Market market) {
		market.getBtnOdd().click();
		return new UnderageGamblingPopup();
	}

	private Market getMarket (Event event, String marketName, String selectionName, boolean isBack, Label odds) {
		return new Market.Builder()
				.eventName(event.getEventName())
				.marketName(marketName)
				.selectionName(selectionName)
				.isBack(isBack)
				.btnOdds(odds)
				.build();
	}

//	public String convertDate(String date){
//		return  convertDate(date,"IST");
//	}
	public String convertDate(String date,String timeZone){
		String prefit = date.split(" ")[0];
		String returnDate="";
		switch (prefit){
			case MemberConstants.TODAY:
				returnDate = date.replace(MemberConstants.TODAY,String.format("%s,",DateUtils.getDate(0, "MMM dd",timeZone)));
				break;
			case MemberConstants.TOMORROW:
				returnDate = date.replace(MemberConstants.TOMORROW,String.format("%s,",DateUtils.getDate(1, "MMM dd",timeZone)));
				break;
			case MemberConstants.STARTINGIN:
				returnDate = date.replace(MemberConstants.STARTINGIN,String.format("%s,",DateUtils.getDate(0, "MMM dd",timeZone)));
				break;
			default:
				returnDate = DateUtils.convertToDate(date,"MMM dd, hh:mm").toString();
				//returnDate = String.format("%s,",DateUtils.getDate(0, "MMM dd hr:mn",FEMemberConstants.TIMEZONE));
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

}
