package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import com.paltech.utils.DoubleUtils;
import membersite.controls.Table;
import membersite.objects.funsport.SelectedOdd;
import membersite.objects.Wager;
import org.openqa.selenium.By;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class MyBetControl extends BaseElement {
	// e.g //div[@id='openbets']
	static String _xpath;
	private DropDownBox ddbEventName;
	private CheckBox chkOrderByMatchedDate;
	private CheckBox chkBetInfo;
	private Link lnkMyBet;
	public Label lblUnMatched;
	public Button btnCancelAllUnmatched;
	private Label lblNodata;
	private Label lblNodata1;
	private Label lblSingleEventTitle;
	private Table tblFancyBet;
	public Button btnUpdate;
	public Button btnReset;
	public Button btnViewOpenBet;
	private Label lblPlaceBetInprogress;
	private Label lbltEventMarket = Label.xpath("//div[@class='betslip-content']//div[contains(@class,'single-market-title-display')]");
	private DropDownBox ddbEventMarket =DropDownBox.xpath("//div[@class='betslip-content']//select[contains(@class,'open-markets')]");

	private MyBetControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		ddbEventName = DropDownBox.xpath(String.format("%s/div[@class='betslip-content']//select", _xpath));
		chkBetInfo = CheckBox.xpath(String.format("%s/div[@class='betslip-content']//input[@id='show-bet-info']", xpath));
		chkOrderByMatchedDate = CheckBox.id("order-matched-date");
		lnkMyBet = Link.xpath(String.format("%s//p[@class='guide-text ']/a", _xpath));
		//TODO: update later
		lblUnMatched = Label.xpath(String.format("%s//div[@class='unmatched-bets']", _xpath));
		btnCancelAllUnmatched = Button.id("cancel-unmatched");
		lblNodata=  Label.xpath(String.format("%s//p[@class='guide-text']", _xpath));
		lblNodata1=  Label.xpath(String.format("%s//p[@class='guide-text mybets']", _xpath));
		lblSingleEventTitle = Label.xpath(String.format("%s//div[contains(@class,'single-market-title-display')]",_xpath));
		tblFancyBet = Table.xpath(String.format("%s//table",_xpath),6);
		btnUpdate = Button.id("update-bets");
		btnReset = Button.id("cancel-reset-bets");
		btnViewOpenBet=Button.id("view-open-bets");
		lblPlaceBetInprogress = Label.xpath("//div[contains(@class,'placebet inprogress')]");

	}
	
	public static MyBetControl xpath(String xpathExpression) {
		return new MyBetControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getGuideText(){
		return lblNodata.getText();
	}
	public String getGuideTextSecondRow(){
		return lblNodata1.getText() + lnkMyBet.getText()+".";
	}
	public String getEventMarketTitle(){
		if(lbltEventMarket.isDisplayed())
			return lbltEventMarket.getText().trim();
		if(ddbEventMarket.isDisplayed())
			return ddbEventMarket.getFirstSelectedOption().trim();
		return "";
	}
	public void clickViewOpenbet(){
		btnViewOpenBet.isClickable(30);
		btnViewOpenBet.click();
	}
	public boolean isControlDisable(Button button){
		String attribute = button.getAttribute("class");
		return attribute.contains("btn-disabled");	}
	/**
	 *
	 * @param limit stands for a number of wagers
	 * @return
	 */
	public List<SelectedOdd> getWagers(boolean isBack, int limit) {
		List<SelectedOdd> lstWagers = new ArrayList<>();
		if (isBetUnmatched()) { // wager is unmatched in span. Sometimes, the span from unmatched to matched is short so we have to handle this case
			lstWagers = getWagersUnmatched(isBack, limit);
			System.out.println(String.format("Bet is unmatched after place bet: %s",lstWagers.get(0).getMarketName()));
		} else { // wager is matched
			limit = (limit < 1) ? (1*3) : (limit*3);
			// waiting for loading completely
			activeBetInfoAndMatchedBetsOption();
		/*	chkOrderByMatchedDate.isDisplayed();
			chkOrderByMatchedDate.isInvisible(3);
			chkBetInfo.select();
			chkOrderByMatchedDate.select();
			// waiting for loading completely
			chkOrderByMatchedDate.isInvisible(2);*/

			String tblXpath = String.format("%s//div[@class='betslip-content']//div[contains(@class,'matched-bets')]//table[contains(@class,'bets matched-sort')]", _xpath);
			Table tblMyBet = Table.xpath(tblXpath, 5);
			if (!tblMyBet.isDisplayed()){
				tblMyBet = Table.xpath("%s//div[@class='betslip-content']//div[contains(@class,'matched-bets')]//table", 5);
			}

			List<ArrayList<String>> lstRecords = tblMyBet.getRowsWithoutHeader(limit,false);
			String eventName = "";
			String marketName = "";
			for (int i=0; i < lstRecords.size(); i++) {
				List<String> lstRow = lstRecords.get(i);
				if (lstRow.size() == 1) {
					String[] eventMatch = lstRow.get(0).split("-");
					eventName = eventMatch.length==2 ? eventMatch[0].trim() : eventMatch[0].trim();
					marketName = eventMatch.length==2 ? eventMatch[1].trim() : eventMatch[0].trim();
				}
				if (lstRow.size() == 5) {
					String oddRate = lstRow.get(2).replace(",", "");
					SelectedOdd wager = new SelectedOdd.Builder()
							.eventName(eventName)
							.marketName(marketName)
							.selectedTeam(lstRow.get(1)) // colSelection
							.oddRate(Double.parseDouble(oddRate)) // colOdds
							.stake(lstRow.get(3)) //colStake
							.build();
					if (isBack) {
						wager.setProfit(DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(lstRow.get(4).replace(",", ""))));
						double liability = DoubleUtils.roundUpWithTwoPlaces(wager.getOddRate() - wager.getProfit());
						wager.setLiability(liability);
					} else {
						wager.setLiability(DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(lstRow.get(4).replace(",", ""))));
						wager.setProfit(DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(lstRow.get(3).replace(",", ""))));
					}
					lstWagers.add(wager);
				}
			}
		}
		return lstWagers;
	}

	public String getEventMarket(String text,boolean isEventName){
		String[] eventMatch = text.split("-");
		String eventName = eventMatch.length==2 ? eventMatch[0].trim() : eventMatch[0].trim();
		String marketName = eventMatch.length==2 ? eventMatch[1].trim() : eventMatch[0].trim();
		if(isEventName)
			return eventName;
		return marketName;
	}

	public void openMyBetWindow() {
		lnkMyBet.click();
	}

	public void cancelAllBetUnmatched() {
		if (btnCancelAllUnmatched.isDisplayedShort(3)){
			btnCancelAllUnmatched.click();
		}
	}
	/**
	 * Get the market info: sport id, event id, market id, market type
	 * @return
	 */

	public List<ArrayList<String>> forecastLstBasedMatchedBetFromAPI(String sportid, String eventID, String marketid, String marketType,List<String> lstSelection){
		List<Wager> lstWager = BetUtils.getMatchedOpenBet(sportid,eventID,marketid,marketType);
		return BetUtils.calculateForecast(BetUtils.getProfitandLiabilityBySelection(lstWager,lstSelection));
	}

	public void updateUnmatchBet(SelectedOdd unmatchedWager, String stake){
		unmatchedWager.getTxtStake().sendKeys(stake);
		btnUpdate.click();
		lblPlaceBetInprogress.isDisplayedShort(10);
		lblPlaceBetInprogress.isInvisible(90);
		btnViewOpenBet.isClickable(20);
	}

//	/**
//	 * Get the market info: sport id, event id, market id, market type
//	 * @return
//	 */
//	List<Wager> lstWager = BetUtils.getMatchedOpenBet(marketInfo.get(0),marketInfo.get(1),marketInfo.get(2),marketInfo.get(3));
//
//	public List<ArrayList<String>> forecastLstBasedMatchedBetFromAPI(List<String> marketInfo,List<String> lstSelection){
//		List<Wager> lstWager = BetUtils.getMatchedOpenBet(marketInfo.get(0),marketInfo.get(1),marketInfo.get(2),marketInfo.get(3));
//		return BetUtils.calculateForecast(BetUtils.getProfitandLiabilityBySelection(lstWager,lstSelection));
//	}
	/***********************
	 * Private methods
	 ***********************/
	private void activeBetInfoAndMatchedBetsOption(){
		if(!chkBetInfo.isSelected()) {
			chkBetInfo.select();
		}
		if(chkOrderByMatchedDate.isDisplayed()) {
			if (!chkOrderByMatchedDate.isSelected())
				chkOrderByMatchedDate.select();
		}
	}
	public List<SelectedOdd> getWagersUnmatched(boolean isBack, int limit){
		List<SelectedOdd> lstWagers = new ArrayList<>();
		limit = (limit < 1) ? (1*3) : (limit*3);
		// waiting for loading completely
		activeBetInfoAndMatchedBetsOption();

		String tblXpath = String.format("%s//div[@class='betslip-content']//div[@class='unmatched-bets']//table", _xpath);
		Table tblMyBet = Table.xpath(tblXpath, 5);

		List<ArrayList<String>> lstRecords = tblMyBet.getRowsWithoutHeader(limit,false);
		String eventName = "";
		String marketName = "";
		for (int i=0; i < lstRecords.size(); i++) {
			List<String> lstRow = lstRecords.get(i);
			if (lstRow.size() == 1) {
				String[] eventMatch = lstRow.get(0).split("-");
				eventName = eventMatch.length==2 ? eventMatch[0].trim() : eventMatch[0].trim();
				marketName = eventMatch.length==2 ? eventMatch[1].trim() : eventMatch[0].trim();
			}
			if (lstRow.size() == 5) {
				TextBox txtOdds =TextBox.xpath(tblMyBet.getControlXPathOfCell(1,3,i+1,"input"));//lstRow.get(2).replace(",", "");
				String oddRate = txtOdds.getAttribute("value");//lstRow.get(2).replace(",", "");
				TextBox txtStake =TextBox.xpath(tblMyBet.getControlXPathOfCell(1,4,i+1,"input")) ;
				String stake =txtStake.getAttribute("value");
				String profitLiability = Label.xpath(tblMyBet.getControlXPathOfCell(1,5,i+1,"div")).getText();
				SelectedOdd wager = new SelectedOdd.Builder()
						.eventName(eventName)
						.marketName(marketName)
						.selectedTeam(lstRow.get(1)) // colSelection
						.oddRate(Double.parseDouble(oddRate)) // colOdds
						.stake(stake) //colStake
						.txtOdd( txtOdds)
						.txtStake(txtStake)
						.build();
				if (isBack) {
					wager.setProfit(DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(profitLiability.replace(",", ""))));
					double liability = DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(stake.replace(",", "")));
					wager.setLiability(liability);

				} else {
					wager.setLiability(DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(profitLiability.replace(",", ""))));
					wager.setProfit(DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(stake.replace(",", ""))));
				}
				lstWagers.add(wager);
			}
		}

/*
		String layOrBack = isBack ? "back" : "lay";
		List<SelectedOdd> lstWagers = new ArrayList<>();
		// TODO: update 15 Jan 2020
		DropDownBox ddbEventAndMarketName = DropDownBox.xpath("//div[@id='openbets-place']//select");
		Label lblMarketName = Label.xpath(String.format("%s//div[@id='openbets-place']/div[contains(@class,'single-market')]", _xpath));
		//get Event name and handle case single bet will have no Event-Market dropbox
		String[] eventAndMarketName = lblMarketName.isDisplayedShort(3) ? lblMarketName.getText().split(" - ") : ddbEventAndMarketName.getFirstSelectedOption().split(" - ");
		String eventName = eventAndMarketName[0];
		String marketName = eventAndMarketName.length==2 ? eventAndMarketName[1] : "";

		String xpathTable = String.format("(%s//div[@class='unmatched-bets']//table[@class='bets %s'])[1]/tbody/tr[1]", _xpath, layOrBack);
		Icon iconRemove = Icon.xpath(String.format("%s//div[@class='remove']", xpathTable));
		Label lblSelectedTeam = Label.xpath(String.format("%s/td[@class='runner']", xpathTable));
		TextBox txtOddRate = TextBox.xpath(String.format("%s/td[@class='odds']//input", xpathTable));
		TextBox txtStake = TextBox.xpath(String.format("%s//input[contains(@class, 'stake')]", xpathTable));
		Label lblProfitLiability = Label.xpath(String.format("%s//div[contains(@class, 'profit')]", xpathTable));

		String oddRate = txtOddRate.getAttribute().replace(",", "");
		SelectedOdd wager = new SelectedOdd.Builder()
				.marketName(marketName)
				.eventName(eventName)
				.selectedTeam(lblSelectedTeam.getText())
				.oddRate(Double.parseDouble(oddRate))
				.stake(txtStake.getAttribute())
				.iconRemove(iconRemove)
				.isUnmatched(true)
				.build();

		String profitLiability = lblProfitLiability.getText().replace(",", "");
		double profitOrLiability = profitLiability.isEmpty() ? -1 : Double.parseDouble(profitLiability);
		if (isBack) {
			wager.setProfit(profitOrLiability);

			double liability = wager.getOddRate() - wager.getProfit();
			wager.setLiability(liability);
		} else {
			wager.setLiability(profitOrLiability);

			double profit = wager.getOddRate() - wager.getLiability();
			wager.setProfit(profit);
		}
		lstWagers.add(wager);
		return lstWagers;*/
		return lstWagers;
	}

	public boolean isBetUnmatched(){
		return (lblUnMatched.isDisplayed());
    }

	private boolean isWaittingPlaceBet(){
		if (lblUnMatched.isDisplayedShort(3)){
			return (!lblUnMatched.isInvisible(40));
		}
		return false;
	}

	public List<ArrayList<String>> getFancyWager(){
		return tblFancyBet.getRows(true);
	}

}
