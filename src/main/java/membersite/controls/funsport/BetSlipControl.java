package membersite.controls.funsport;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import membersite.common.FEMemberConstants;
import membersite.controls.Table;
import membersite.objects.AccountBalance;
import membersite.objects.funsport.SelectedOdd;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class BetSlipControl extends BaseElement {
	// e.g //div[@id='betslip-container']
	static String _xpath = "";
	public Button btnPlaceBet;
	public Button btnUpdateBet;
	public Button btnConfirm;
	public Button btnCancelAllSelections;
	public Label lblClickOnOdds;
	private CheckBox chkConfirm;
	private Table tblStake;
	private Table tblSelectedOdds;
	private Label lblTotalLiability;
	private Label lblPleaseWaitWhilst;
	private Label lblMiddleContent;
	private Label lblNodata;
	private Label lblPlaceBetInprogress;
	private Label lblBetSlipErrorStakeMsg;
	private Label lblErrorTitle = Label.xpath("//div[@id='betslip']//p[contains(@class,'error status-title')]");

	private BetSlipControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		btnPlaceBet = Button.id("place-bet");
		btnConfirm = Button.id("confirm-bet");
		btnCancelAllSelections = Button.xpath(String.format("%s//input[@id='clear-slip']", _xpath));
		chkConfirm = CheckBox.xpath(String.format("%s//input[@id='confirmation']", _xpath));
		tblStake = Table.xpath(String.format("%s//div[@class='footer']//table", _xpath), 5);
		tblSelectedOdds = Table.xpath(String.format("%s//div[@id='bet-back']//table[@class='bets back']", _xpath), 4);
		lblClickOnOdds = Label.xpath(String.format("%s//p[@class='guide-text in-market']", _xpath));
		lblTotalLiability = Label.xpath(String.format("%s//div[@class='footer']//span[@class='total-liability']", _xpath));
		lblPleaseWaitWhilst = Label.xpath(String.format("%s//div[@id='betslip']//p[@class='pleasewait']", _xpath));
		lblMiddleContent = Label.id("middle-content");
		lblNodata = Label.xpath(String.format("%s//div[@id='place-bets']/p",_xpath));
		btnUpdateBet = Button.id("update-bets");
		lblPlaceBetInprogress = Label.xpath("//div[contains(@class,'betslip en_US placebet inprogress')]");
		lblBetSlipErrorStakeMsg = Label.xpath(String.format("%s//div[contains(@class,'betslip-error-stake-msg')]",_xpath));
	}
	
	public static BetSlipControl xpath(String xpathExpression) {
		return new BetSlipControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getGuideText(){
		return lblNodata.getText();
	}

	public void cancelAllSelections() {
		btnCancelAllSelections.click();
	}

	public void inputStake (SelectedOdd selectedOdd, String stake) {
		TextBox txtStake = selectedOdd.getTxtStake();
		txtStake.sendKeys(stake);
		// waiting for loading completely
		btnPlaceBet.isInvisible(2);
	}

	public void inputOdds(SelectedOdd selectedOdd, int value) throws InterruptedException {
		String selectOdds = selectedOdd.getTxtOdd().getAttribute("value");
		String oddsUpdate= String.format("%s",(int)Double.parseDouble(selectOdds)+value);
		System.out.println(String.format("Update %s odds from %s to %s",selectedOdd.getIsBack()?"Back":"Lay",selectOdds,oddsUpdate));
		TextBox txtOdds = selectedOdd.getTxtOdd();
		txtOdds.type(false,oddsUpdate);
	}

	public SelectedOdd placeBet(SelectedOdd selectedOdd, String stake) {
		// delete All Bets unmatched
		System.out.println("INFO: Clear all bets unmatched");
		Button btnCancelAllUnmatched = Button.id("cancel-unmatched"); // MyBetControl also has this xpath
		if (btnCancelAllUnmatched.isDisplayedShort(3)) {
			btnCancelAllUnmatched.click();
		}
		lblMiddleContent.waitForControlInvisible(5, 10);
		// update SelectedOdd
        selectedOdd = updateInfoAfterSetStake(selectedOdd, stake);

		btnPlaceBet.click();
		// waiting for this bet matched
		if(btnConfirm.isDisplayed(5)) {
			btnConfirm.click();
		}
		lblPleaseWaitWhilst.isDisplayedShort(6);
		lblPleaseWaitWhilst.isInvisible(50);
		lblPlaceBetInprogress.isInvisible(60);
		return selectedOdd;
	}

	public SelectedOdd updateUnmatchedBet(SelectedOdd selectedOdd,String stake){
		SelectedOdd newBet = selectedOdd;
		updateInfoAfterSetStake(selectedOdd,stake);
		btnUpdateBet.click();
		// waiting for this bet matched
		if(btnConfirm.isDisplayed(5)) {
			btnConfirm.click();
		}
		lblPleaseWaitWhilst.isDisplayedShort(6);
		return newBet;
	}

	public void placeBet27Fancy( SelectedOdd selectedOdd,String stake) {
		selectedOdd.getTxtStake().click();
		selectedOdd.getTxtStake().sendKeys(stake);
		btnPlaceBet.click();
		// waiting for this bet matched
		if(btnConfirm.isDisplayed(5)) {
			btnConfirm.click();
		}
		lblPleaseWaitWhilst.isDisplayedShort(6);
		lblPleaseWaitWhilst.isInvisible(50);
	}

	/**
	 *
	 * @param limit > 0
	 * @return
	 */
	public List<SelectedOdd> getSelectedOdds (int limit) {
		limit = (limit < 1) ? (1) : (limit);
		List<SelectedOdd> lstSelectedOdds = new ArrayList<>();
		if (lblClickOnOdds.isInvisible(2)) {
			int i = 1;
			String generalXpath = "//div[@id='bet-back']//table[@class='bets back']/tbody";
			boolean isBack = true;
			if (!Label.xpath(generalXpath).isDisplayedShort(2)){
                generalXpath = generalXpath.replace("back", "lay");
                isBack = false;
            }
			while(true) {
				String xpathEvent = String.format("(%s//tr[@class='not-market'])[%s]", generalXpath, i);
				String xpathOdd = String.format("(%s//tr[@class='bet-info'])[%s]", generalXpath, i);

				Label lblEventName= Label.xpath(String.format("%s//span", xpathEvent));
				Icon iconInPlay =   Icon.xpath(String.format("%s//div[@class='inplay']", xpathEvent));

				Icon iconRemove = Icon.xpath(String.format("%s//div[@class='remove']", xpathOdd));
				Label lblTeam = Label.xpath(String.format("%s//span[@class='runner-name']", xpathOdd));
				Label lblMarketName = Label.xpath(String.format("%s//span[contains(@class,'market-name')]", xpathOdd));
				TextBox txtOdd = TextBox.xpath(String.format("%s//input[contains(@class,'odds-input')]", xpathOdd));
				TextBox txtStake = TextBox.xpath(String.format("%s//input[contains(@class,'stake-input')]", xpathOdd));
				Label lblCurrency = Label.xpath(String.format("%s//div[@class='profit']/span[1]", xpathOdd));
				Label lblProfitOrLiability = Label.xpath(String.format("%s//div[@class='profit']/span[2]", xpathOdd));

				String oddRate = txtOdd.getAttribute().replace(",", "");
                oddRate = oddRate.isEmpty() ? "-1" : oddRate;
				SelectedOdd selectedOdd = new SelectedOdd.Builder()
						.eventName(lblEventName.getText())
						.isInPlay(iconInPlay.isDisplayed())
						.iconRemove(iconRemove)
						.selectedTeam(lblTeam.getText())
						.marketName(lblMarketName.getText())
						.oddRate(Double.parseDouble(oddRate))
						.txtOdd(txtOdd)
						.currency(lblCurrency.getText())
						.txtStake(txtStake)
						.stake(txtStake.getAttribute())
						.lblProfit(lblProfitOrLiability)
						.lblLiability(lblProfitOrLiability)
                        .isBack(isBack)
						.build();
				lstSelectedOdds.add(selectedOdd);
				i++;
				if (i > limit) {
					return lstSelectedOdds;
				}
			}
		}
		return lstSelectedOdds;
	}
	/**
	 *
	 * @param limit > 0
	 * @return
	 */
	public List<SelectedOdd> getErrorBet (int limit) {
		limit = (limit < 1) ? (1) : (limit);
		List<SelectedOdd> lstSelectedOdds = new ArrayList<>();
		if (lblClickOnOdds.isInvisible(2)) {
			int i = 1;
			String generalXpath = "//div[@id='betslip-container']//table[@class='bets back']/tbody";
			boolean isBack = true;
			if (!Label.xpath(generalXpath).isDisplayedShort(2)){
				generalXpath = generalXpath.replace("back", "lay");
				isBack = false;
			}
			while(true) {
				// get Event in sport page
				String xpathEvent = String.format("%s//tr[contains(@class,'not-market')][%s]", generalXpath, i);
				Label lblEventName= Label.xpath(String.format("%s//span", xpathEvent));

				String xpathOdd = String.format("%s//tr[%s]", generalXpath, i*2);
				Label lblTeam = Label.xpath(String.format("%s//span[@class='runner-name']", xpathOdd));
				// get market name row [1]
				Label lblOdd = Label.xpath(String.format("%s//td[2]", xpathOdd));
				Label lblStake = Label.xpath(String.format("%s//td[3]", xpathOdd));
				Label lblProfitOrLiability = Label.xpath(String.format("%s//div[@class='profit']/span[2]", xpathOdd));
				Label lblErrorMessage = Label.xpath(String.format("%s//tr[%s]/td",generalXpath, i*3));
				SelectedOdd selectedOdd = new SelectedOdd.Builder()
						.eventName(lblEventName.getText())
						.marketName(lblEventName.getText())
						.selectedTeam(lblTeam.getText())
						.oddRate(Double.parseDouble(lblOdd.getText().trim()))
						.stake(lblStake.getText().trim())
						.lblProfit(lblProfitOrLiability)
						.lblLiability(lblProfitOrLiability)
						.errorMessage(lblErrorMessage.getText())
						.isBack(isBack)
						.build();
				lstSelectedOdds.add(selectedOdd);
				i++;
				if (i > limit) {
					return lstSelectedOdds;
				}
			}
		}
		return lstSelectedOdds;
	}
	/**
	 *
	 * @param limit > 0
	 * @return
	 */
	public List<SelectedOdd> getSelectedOdds27Fancy (int limit) {
		limit = (limit < 1) ? 1 : limit;
		List<SelectedOdd> lstSelectedOdds = new ArrayList<>();
		if (lblClickOnOdds.isInvisible(2)) {
			int i = 1;
			String generalXpath = "//div[@id='fancy_placebet']//table[@class='bets back']/tbody";
			while(true) {
				String xpathEvent = String.format("(%s//tr[@class='not-market'])[%s]", generalXpath, i);
				String xpathOdd = String.format("(%s//tr[contains(@class,'bet-info')])[%s]", generalXpath, i);
				Label lblEventName= Label.xpath(String.format("%s//span", xpathEvent));
				Icon iconInPlay =  Icon.xpath(String.format("%s//div[@class='inplay']", xpathEvent));

				Icon iconRemove = Icon.xpath(String.format("%s//div[@class='remove']", xpathOdd));
				Label lblTeam = Label.xpath(String.format("%s//span[@class='runner-name']", xpathOdd));
				//Label lblMarketName = Label.xpath(String.format("%s//span[contains(@class,'market-name')]", xpathOdd));
				TextBox txtOdd = TextBox.xpath(String.format("%s//input[contains(@class,'odds-input')]", xpathOdd));
				TextBox txtStake = TextBox.xpath(String.format("%s//input[contains(@class,'stake-input')]", xpathOdd));
				Label lblCurrency = Label.xpath(String.format("%s//div[@class='profit']/span[1]", xpathOdd));
				Label lblProfitOrLiability = Label.xpath(String.format("%s//div[@class='profit']/span[2]", xpathOdd));

				String oddRate = txtOdd.getAttribute().replace(",", "");
				oddRate = oddRate.isEmpty() ? "-1" : oddRate;
				SelectedOdd selectedOdd = new SelectedOdd.Builder()
						.eventName(lblEventName.getText())
						.isInPlay(iconInPlay.isDisplayed())
						.iconRemove(iconRemove)
						.selectedTeam(lblTeam.getText())
						.marketName(lblEventName.getText())
						.oddRate(Double.parseDouble(oddRate))
						.txtOdd(txtOdd)
						.currency(lblCurrency.getText())
						.txtStake(txtStake)
						.stake(txtStake.getAttribute())
						.lblProfit(lblProfitOrLiability)
						.lblLiability(lblProfitOrLiability)
						//.isBack(isBack)
						.build();
				lstSelectedOdds.add(selectedOdd);
				i++;
				if (i > limit) {
					return lstSelectedOdds;
				}
			}
		}
		return lstSelectedOdds;
	}


	public SelectedOdd updateInfoAfterSetStake (SelectedOdd selectedOdd, String stake) {
		// waiting for loading completely
		selectedOdd.getTxtStake().isClickable(40);
		TextBox txtStake = selectedOdd.getTxtStake();
		txtStake.isEnabled();
		txtStake.sendKeys(stake);
        // update selected odd info
        String sProfitOrLiability = selectedOdd.getLblProfit().getText();
        sProfitOrLiability = sProfitOrLiability.isEmpty() ? "-1" : sProfitOrLiability;
		// re-set Odds value
		selectedOdd.setOddRate(Double.parseDouble(selectedOdd.getTxtOdd().getAttribute("value").trim()));
		// re-set stake value
		selectedOdd.setStake(selectedOdd.getTxtStake().getAttribute("value"));
		// set profit and liability of bet
		if (selectedOdd.getIsBack()) {
            System.out.println("Debug: profit of Back bet" + sProfitOrLiability);
            selectedOdd.setProfit(Double.parseDouble(sProfitOrLiability));
            selectedOdd.setLiability(Double.parseDouble(stake));
        } else {
            selectedOdd.setProfit(Double.parseDouble(stake));
            System.out.println("Debug: liability of lay bet" + sProfitOrLiability);
            selectedOdd.setLiability(Double.parseDouble(sProfitOrLiability));
        }
        return selectedOdd;
	}

	public boolean isPlaceBetDisabled() {
		String attribute = btnPlaceBet.getAttribute("class");
		return attribute.contains("disabled");
	}

	public String getErrorTitle() {
		return lblErrorTitle.getText();
	}
	public String getBetSlipErrorStakeMsg() {
		return lblBetSlipErrorStakeMsg.getText();
	}

}
