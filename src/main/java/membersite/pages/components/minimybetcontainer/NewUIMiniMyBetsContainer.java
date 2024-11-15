package membersite.pages.components.minimybetcontainer;

import com.paltech.element.common.*;
import membersite.controls.MiniMyBetControl;
import membersite.objects.Wager;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class NewUIMiniMyBetsContainer extends MiniMyBetsContainer {
    public MiniMyBetControl fancyMiniMyBet = MiniMyBetControl.xpath("//app-fancy-bets");
    public MiniMyBetControl bookMakerMiniMyBet = MiniMyBetControl.xpath("//app-bookmaker-bets");
    public MiniMyBetControl fancyMiniBetSlip = MiniMyBetControl.xpath("//app-fancy-betslip");
    public MiniMyBetControl bookMakerMiniBetSlip = MiniMyBetControl.xpath("//app-normal-betslip");
    public MiniMyBetControl normalMiniBetSlip = MiniMyBetControl.xpath("//app-normal-bets");
    private String xPathSelection = "//div[contains(@class,'%s') and contains(@class,'row-open-bet')]";
    private Label lblBetslipErrorMessage = Label.xpath("//div[contains(@class,'betslip-error')]");
    private Label lblErrorMessage = Label.xpath("//div[contains(@class,'bet-info error')]");
    private Link lnkCancelAll = Link.xpath("//div[contains(@class,'cancel-all-bet')]//a");
    private String lblProfitLiabilityXPath = "//app-matched-bets//span[contains(@class,'col-3 text-right')]";
    private CheckBox chkAverageOdds = CheckBox.xpath("//label[contains(@class,'chk-average-odds')]");
    private CheckBox chkOrderByMatchedDate = CheckBox.id("order-matched-date");
    private CheckBox chkBetInfo = CheckBox.xpath("/div[@class='betslip-content']//input[@id='show-bet-info']");
    private Link lnkMyBet = Link.xpath("//p[@class='guide-text ']/a");
    private Label lblUnMatched = Label.xpath("//div[@class='unmatched-bets']");
    private Icon iconRemoveBet;
    private Image imgSpin = Image.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    private Label lblAccepting = Label.xpath("//div[contains(@class,'loading-text ng-tns-c44-2')]");
    public Button btnMultipleBetslip = Button.xpath("//app-bet-slip//button[text()='Multiple']");

    public void cancelAllBetUnmatched() {
        if (lnkCancelAll.isDisplayed())
            lnkCancelAll.click();
    }
    public String getBetslipErrorMessage() {
        return lblBetslipErrorMessage.getText();
    }
    public String getPlaceBetErrorMessage() {
        lblAccepting.waitForControlInvisible(1, 3);
        return lblErrorMessage.getText();
    }

    public List<Order> getOrder(boolean isMatched, boolean isBack, int limit) {
        List<Order> lstOrders = new ArrayList<>();
        String layOrBack = isBack ? "back" : "lay";

        if (limit < 1) {
            System.out.println("There is no row returned");
            return lstOrders;
        }
        String matchUnmatchedXpath = "";
        if (!isMatched) {
            matchUnmatchedXpath = String.format("//app-unmatched-bets%s", String.format(xPathSelection, layOrBack));
        } else {
            matchUnmatchedXpath = String.format("//app-matched-bets%s", String.format(xPathSelection, layOrBack));
        }
        int countUnmatchedWager = Label.xpath(matchUnmatchedXpath).getWebElements().size();
        for (int i = 0; i < countUnmatchedWager; i++) {
            lstOrders.add(getOrder(isMatched, isBack));
            if (limit <= i + 1) {
                return lstOrders;
            }
        }
        return lstOrders;
    }

    public Order getOrder(boolean isMatched, boolean isBack) {
        String layOrBack = isBack ? "back" : "lay";
        String matchUnmatchXpath = "";
        if (!isMatched) {
            matchUnmatchXpath = String.format("//app-unmatched-bets%s", String.format(xPathSelection, layOrBack));
        } else {
            matchUnmatchXpath = String.format("//app-matched-bets%s", String.format(xPathSelection, layOrBack));
        }
        String stake = Label.xpath(String.format("%s[%d]//span[3]", matchUnmatchXpath, 1)).getText();
        return new Order.Builder()
                .selectionName(Label.xpath(String.format("%s[%d]//span[contains(@class,'runner-name')]", matchUnmatchXpath, 1)).getText())
                .odds(Label.xpath(String.format("%s[%d]/span[2]", matchUnmatchXpath, 1)).getText())
                .stake(stake)
                .isBack(isBack)
                .profit(isBack ? Label.xpath(String.format("%s[%d]/span[4]", matchUnmatchXpath, 1)).getText() : stake)
                .liablity(isBack ? stake : Label.xpath(String.format("%s[%d]/span[4]", matchUnmatchXpath, 1)).getText())
                .build();
    }

    public void verifyInfoPlacedMatchedBet(Market market, String stake, Order order) {
        String actualProfitLiability = Label.xpath(lblProfitLiabilityXPath).getText().trim();
        String oddsPlaceBet = market.getOdds();
        if(!oddsPlaceBet.equals(order.getOdds())) {
            oddsPlaceBet = order.getOdds();
        }
        if(order.getIsBack()) {
            String expectedProfit = String.format("%.2f", Double.valueOf(stake) * (Double.valueOf(oddsPlaceBet) - 1));
            Assert.assertEquals(actualProfitLiability, expectedProfit, "FAILED! Profit of selection is not correct");
            Assert.assertEquals(market.getSelectionName(), order.getSelectionName(), "FAILED! Selection name is not correct");
            Assert.assertEquals(String.format("%.2f",Float.valueOf(stake)), order.getStake(), "FAILED! Stake is not correct");
            Assert.assertEquals(String.format("%.2f",Float.valueOf(oddsPlaceBet)), order.getOdds(), "FAILED! Odds is not correct");
        } else {
            String expectedLiability = String.format("%.2f", Double.valueOf(stake) * (Double.valueOf(oddsPlaceBet) - 1));
            Assert.assertEquals(actualProfitLiability, expectedLiability, "FAILED! Profit of selection is not correct");
            Assert.assertEquals(market.getSelectionName(), order.getSelectionName(), "FAILED! Selection name is not correct");
            Assert.assertEquals(String.format("%.2f",Float.valueOf(stake)), order.getStake(), "FAILED! Stake is not correct");
            Assert.assertEquals(String.format("%.2f",Float.valueOf(oddsPlaceBet)), order.getOdds(), "FAILED! Odds is not correct");
        }
    }
    public void removeBet(boolean isBack) {
        String layOrBack = isBack ? "back" : "lay";

        iconRemoveBet = Icon.xpath(String.format("(//app-unmatched-bets%s//span[contains(@class,'remove-bet')])[1]", String.format(xPathSelection, layOrBack)));
        iconRemoveBet.click();
        imgSpin.waitForControlInvisible();
    }

    public boolean isUnmatchedBetsEmpty() {
        iconRemoveBet = Icon.xpath("(//app-unmatched-bets//div[@class='row']//span[@class='remove-bet'])[1]");
        iconRemoveBet.isInvisible(4);
        return iconRemoveBet.isPresent();
    }

//    public List<ArrayList<String>> forecastLstBasedMatchedBetFromAPI(List<String> marketInfo, List<String> lstSelection) {
//        List<Wager> lstWager = BetUtils.getMatchedOpenBet(marketInfo.get(0), marketInfo.get(1), marketInfo.get(2), marketInfo.get(3));
//        return BetUtils.calculateForecast(BetUtils.getProfitandLiabilityBySelection(lstWager, lstSelection));
//    }

    public List<ArrayList> getBookmakerMatchBets() {
        return bookMakerMiniMyBet.getMatchBets(false);
    }

    public List<ArrayList> getMatchedFancyInMiniMyBet() {
        return fancyMiniMyBet.getMatchBets(true);
    }

    public List<Order> getMatchedNormalInMiniMyBet() {
        List<Order> lstOrder = new ArrayList<>();
        List<ArrayList> lstMatchBets = normalMiniBetSlip.getMatchNormalBets();
        for (int i = 0; i < lstMatchBets.size(); i++) {
            Order ord = new Order.Builder().selectionName(String.valueOf(lstMatchBets.get(i).get(0)))
                    .odds(String.valueOf(lstMatchBets.get(i).get(1)))
                    .stake(String.valueOf(lstMatchBets.get(i).get(2)))
                    .isBack(String.valueOf(lstMatchBets.get(i).get(4)).equalsIgnoreCase("BACK") ? true : false).build();
            if(String.valueOf(lstMatchBets.get(i).get(4)).equalsIgnoreCase("BACK")) {
                ord.setProfit(String.valueOf(lstMatchBets.get(i).get(3)));
            } else {
                ord.setLiability(String.valueOf(lstMatchBets.get(i).get(3)));
            }
            lstOrder.add(ord);
        }
        return lstOrder;
    }
    public List<Order> getUnmatchedNormalInMiniMyBet() {
        List<Order> lstOrder = new ArrayList<>();
        List<ArrayList> lstUnMatchBets = normalMiniBetSlip.getUnmatchNormalBets();
        for (int i = 0; i < lstUnMatchBets.size(); i++) {
            Order ord = new Order.Builder().selectionName(String.valueOf(lstUnMatchBets.get(i).get(0)))
                    .odds(String.valueOf(lstUnMatchBets.get(i).get(1)))
                    .stake(String.valueOf(lstUnMatchBets.get(i).get(2)))
                    .isBack(String.valueOf(lstUnMatchBets.get(i).get(4)).equalsIgnoreCase("BACK") ? true : false).build();
            if(String.valueOf(lstUnMatchBets.get(i).get(4)).equalsIgnoreCase("BACK")) {
                ord.setProfit(String.valueOf(lstUnMatchBets.get(i).get(3)));
            } else {
                ord.setLiability(String.valueOf(lstUnMatchBets.get(i).get(3)));
            }
            lstOrder.add(ord);
        }
        return lstOrder;
    }

    public List<ArrayList> getFancyBetSlipInfo() {
        return fancyMiniBetSlip.getBetSlipInfo(true);
    }

    public List<ArrayList> getBookmakerBetSlipInfo() {
        return bookMakerMiniBetSlip.getBetSlipInfo(false);
    }

    public boolean isMultiTabBetSlipEnabled() {
        return btnMultipleBetslip.isEnabled();
    }

    public void clickMultiTabBetSlip() { btnMultipleBetslip.click();}
    public boolean isMultiTabBetSlipSelected() {
        return btnMultipleBetslip.getAttribute("className").contains("mode-active");
    }
}
