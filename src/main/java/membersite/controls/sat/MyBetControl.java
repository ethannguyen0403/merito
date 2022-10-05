package membersite.controls.sat;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import membersite.objects.Wager;
import membersite.objects.sat.Order;
import org.openqa.selenium.By;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author isabella.huynh
 * created on 3/10/2020
 */
public class MyBetControl extends BaseElement {
	// e.g //div[@class='container-mybets']
	static String _xpath;
	private Link lnkCancelAll;
	private CheckBox chkAverageOdds;
	private CheckBox chkOrderByMatchedDate;
	private CheckBox chkBetInfo;
	private Link lnkMyBet;
	private Label lblUnMatched;
	private Label lblErrorMessage;
	private Icon iconRemoveBet;
	private Image imgSpin;
	private String xPathSelection;
	public MiniMyBetControl fancyMiniMyBet = MiniMyBetControl.xpath("//app-fancy-bets");
	public MiniMyBetControl bookMakerMiniMyBet = MiniMyBetControl.xpath("//app-bookmaker-bets");
	private MyBetControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		chkBetInfo = CheckBox.xpath(String.format("%s/div[@class='betslip-content']//input[@id='show-bet-info']", xpath));
		chkOrderByMatchedDate = CheckBox.id("order-matched-date");
		lnkMyBet = Link.xpath(String.format("%s//p[@class='guide-text ']/a", _xpath));
		lnkCancelAll = Link.xpath("//div[contains(@class,'cancel-all-bet')]//a");
		chkAverageOdds = CheckBox.xpath("//label[contains(@class,'chk-average-odds')]");
		lblUnMatched = Label.xpath(String.format("%s//div[@class='unmatched-bets']", _xpath));
		lblErrorMessage = Label.xpath("//div[contains(@class,'bet-info error')]");
		imgSpin = Image.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
		xPathSelection="//div[contains(@class,'%s') and contains(@class,'row-open-bet')]";
	}

	public static MyBetControl xpath(String xpathExpression) {
		return new MyBetControl(By.xpath(xpathExpression), xpathExpression);
	}

	public void cancelAllBetUnmatched() {
		if(lnkCancelAll.isDisplayed())
			lnkCancelAll.click();
	}

	public Order getOrder(boolean isMatched, boolean isBack)
	{
		String layOrBack =isBack?"back":"lay";
		String matchUnmatchXpath ="";
		if(!isMatched) {
			matchUnmatchXpath = String.format("//app-unmatched-bets%s", String.format(xPathSelection,layOrBack));
		}else{
			matchUnmatchXpath= String.format("//app-matched-bets%s", String.format(xPathSelection,layOrBack));
		}
		String stake =Label.xpath(String.format("%s[%d]//span[3]",matchUnmatchXpath,1)).getText();
		 return new Order.Builder()
				.selectionName(Label.xpath(String.format("%s[%d]//span[contains(@class,'runner-name')]",matchUnmatchXpath,1)).getText())
				.odds(Label.xpath(String.format("%s[%d]//span[2]",matchUnmatchXpath,1)).getText())
				.stake(stake)
				.profit(isBack? Label.xpath(String.format("%s[%d]//span[4]",matchUnmatchXpath,1)).getText():stake)
				.liablity(isBack? stake:Label.xpath(String.format("%s[%d]//span[4]",matchUnmatchXpath,1)).getText())
				.build();
	}

	public List<Order> getOrder(boolean isMatched, boolean isBack, int limit)
	{
		List<Order> lstOrders = new ArrayList<>();
		String layOrBack =isBack?"back":"lay";

		if(limit < 1){
			System.out.println("There is no row returned");
			return lstOrders ;
		}
		String matchUnmatchedXpath ="";
		if(!isMatched) {
			matchUnmatchedXpath = String.format("//app-unmatched-bets%s", String.format(xPathSelection,layOrBack));
		}else{
			matchUnmatchedXpath= String.format("//app-matched-bets%s", String.format(xPathSelection,layOrBack));
		}
		int countUnmatchedWager = Label.xpath(matchUnmatchedXpath).getWebElements().size();
		for(int i = 0; i< countUnmatchedWager;i++)
		{
			lstOrders.add(getOrder(isMatched,isBack));
			if (limit <= i+1){
				return lstOrders;
			}
		}
		return lstOrders;
	}

	public void removeBet( boolean isBack)
	{
		String layOrBack =isBack?"back":"lay";

		iconRemoveBet = Icon.xpath(String.format("(//app-unmatched-bets%s//span[@class='remove-bet'])[1]", String.format(xPathSelection,layOrBack)));
		iconRemoveBet.click();
		imgSpin.waitForControlInvisible();
	}

	public boolean isUnmatchedBetsEmpty()
	{
		iconRemoveBet = Icon.xpath("(//app-unmatched-bets//div[@class='row']//span[@class='remove-bet'])[1]");
		iconRemoveBet.isInvisible(4);
		return iconRemoveBet.isPresent();
	}
	public String getPlaceBetErrorMessage(){
		return lblErrorMessage.getText();
	}

	public List<ArrayList<String>> forecastLstBasedMatchedBetFromAPI(List<String> marketInfo,List<String> lstSelection){
		List<Wager> lstWager = BetUtils.getMatchedOpenBet(marketInfo.get(0),marketInfo.get(1),marketInfo.get(2),marketInfo.get(3));
		return BetUtils.calculateForecast(BetUtils.getProfitandLiabilityBySelection(lstWager,lstSelection));
	}
}
