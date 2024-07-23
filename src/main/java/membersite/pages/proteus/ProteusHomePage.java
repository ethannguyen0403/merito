package membersite.pages.proteus;

import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import membersite.controls.proteus.AppConfirmModulePopup;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Odds;
import membersite.objects.proteus.Order;
import membersite.pages.HomePage;
import org.apache.commons.lang3.text.WordUtils;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.*;

import static common.MemberConstants.GMT_7;
import static common.ProteusConstant.*;

public class ProteusHomePage extends HomePage {

    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading-text')]/p");
    private Image imgSpinner = Image.xpath("//em[contains(@class,'fa-4x fa-spin')]");
    public Label lblBetSlipTab = Label.xpath("//app-bet-slip//div[text()='BET SLIP']");
    public Label lblBetSlipTabNumber = Label.xpath("//app-bet-slip//div[text()='BET SLIP']/following-sibling::div[1]");
    private Label lblPendingTab = Label.xpath("//app-bet-slip//div[text()='PENDING BETS']");
    public Label lblPlaceBetError = Label.xpath("//app-confirm-modal//div[contains(@class,'modal-body')]//div");
    public Label lblBetSlipMsgEmptyNoBets = Label.xpath("//app-bet-slip//div[contains(@class, 'no-bets-container')]//div[2]");
    public Label lblBetSlipMsgEmptyClickOdds = Label.xpath("//app-bet-slip//div[contains(@class, 'no-bets-container')]//div[3]");
    public Label lblErrorMsgOver = Label.xpath("//div[@class='single-bets-container']//div[@class='err-msg m-2 ng-star-inserted']");
    public  Button btnRemoveAll = Button.xpath("//span[contains(@class, 'remove-all')]");
    // Bet Slip UI
    String btnXRemoveBetXpath = "(//i[contains(@class, 'remove-icon')])[%d]";
    String betslipRootXpath = "//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]";
    String lblEventNameXpath = "//span[@class='teams-name']";
    String lblLiveScoreXpath ="//span[@class='live-score']";
    String lblSummaryInfoXpath = "//div[contains(@class,'bet-title')]";
    String lblHDPPointXpath = "//div[contains(@class,'fw-semibold')]";
    String lblOddsXpath = "//div[contains(@class,'odds-text')]";
    String lblStakeXpath = "//input[contains(@class,'stake-input')]";
    String lblMinBetXpath = "//div[contains(@class,'limit-stake-container')]//span[contains(text(),'Min bet')]/span";
    String lblMinBetValueXpath ="//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][1]/span[1]/span";
    String lblMaxBetXpath =  "//div[contains(@class,'limit-stake-container')]//span[contains(text(),'Max bet')]/span";
    String lblMaxBetValueXpath =  "//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][1]/span[2]/span";
    String lblMatchMaxXpath = "//div[contains(@class,'limit-stake-container')]//span[contains(text(),'Match Max')]/span";
    String txtStakeXpath = "//input[contains(@class,'stake-input')]";
    Button btnPlaceBet = Button.xpath("//app-open-bets//button[contains(@class,'btn-place-bet')]");
    public AppConfirmModulePopup confirmModulePopup = AppConfirmModulePopup.xpath("//app-confirm-modal");
    // End Bet Slip UI
    public CheckBox chkAcceptBetterOdd = CheckBox.xpath("//app-open-bets//input[@type='checkbox']");
    String pendingBetRootXpath = "//app-pending-bets//div[contains(@class,'pending-item')][%d]";
    String lblPendingBetEventNameXpath = "//div[contains(@class,'event-name')]";
    String lblPendingOrderIdXpath = "//div[contains(@class,'item-header')]/div[1]";
    String lblPendingBetPlaceDateXpath = "//div[contains(@class,'item-header')]/div[1]/span";
    String lblPendingBetStatusXpath = "//div[contains(@class,'order-status')]/div";
    String lblPendingLeagueNameXpath = "//div[contains(@class,'league-name')]";
    String lblPendingSelectionXpath = "//span[contains(@class,'team-name')]";
    String lblPendingOddsXpath = "//span[contains(@class,'odds')]";
    String lblPendingStakeLabelXpath = "//div[contains(@class,'item-content')][2]/div[1]/span[1]";
    String lblPendingStakeValueXpath = "//div[contains(@class,'item-content')][2]/div[1]/span[2]";
    String lblPendingRiskLabelXpath = "//div[contains(@class,'item-content')][2]/div[2]/span[1]";
    String lblPendingRiskValueXpath = "//div[contains(@class,'item-content')][2]/div[2]/span[2]";
    String lblPendingWinLabelXpath = "//div[contains(@class,'item-content')][2]/div[3]/span[1]";
    String lblPendingWinValueXpath = "//div[contains(@class,'item-content')][2]/div[3]/span[2]";
    String lblPendingCashoutXpath = "//button[contains(@class,'cash-out')]";
    // Start Pending Bet

    // End Pending Bet UI

    public ProteusHomePage(String types) {
        super(types);
    }

    public void waitiFrameLoad(){
        lblLoading.waitForControlInvisible(2,2);
    }
    public void waitForSpinnerLoading() {
        imgSpinner.waitForControlInvisible(2, 5);
    }

   public AccountBalance calculateExpecteBalance(AccountBalance beforeBalance, Order order){
        double toRisk = order.getRisk();
        double balance = Double.parseDouble(beforeBalance.getBalance()) - toRisk;
        double exposure = Double.parseDouble(beforeBalance.getExposure()) + (toRisk * (-1));
        return new AccountBalance.Builder()
                .balance(String.format("%,.2f",balance))
                .exposure(String.format("%,.2f",exposure))
                .build();

   }
   public EuroViewPage selectEuroView(){
        selectView(EURO_VIEW);
        EuroViewPage euroViewPage = new EuroViewPage(this._type);
        euroViewPage.waitForSpinnerLoading();
        return euroViewPage;
    }
    public AsianViewPage selectAsianView(){
        selectView(ASIAN_VIEW);
        AsianViewPage asianViewPage = new AsianViewPage(this._type);
        asianViewPage.waitForSpinnerLoading();
        return asianViewPage;
    }

    private void selectView(String view){
        // Display view
        String currentView = lblView.getText();
        if (currentView.equals(view))
            lblView.click();
    }

    public void switchTabBetSlip(String tabName) {
        if (tabName.equalsIgnoreCase("bet slip")) {
            lblBetSlipTab.click();
        } else if (tabName.equalsIgnoreCase("pending bets")) {
            lblPendingTab.click();
        }
    }

    protected String definePeriod(Market market) {
        String match = "";
        if (market.getPeriodId() == 0)
            match = "Match";
        else
            match = "1st Half";
        return match;
    }
    public String defineMarketName(Market market) {
        String sportName = market.getSportName();
        switch (sportName){
            case "Soccer":
                switch (market.getBetType())
                {
                    case "MONEYLINE":
                        return "1X2";
                    case "SPREAD":
                        return "Handicap";
                    case "TOTAL_POINTS":
                        return "Total";
                    default:
                        return "";
                }
            case "Tennis":
                switch (market.getBetType())
                {
                    case "MONEYLINE":
                        return "Money Line";
                    case "SPREAD":
                        return "Handicap";
                    case "TOTAL_POINTS":
                        return "Total Points";
                    default:
                        return " ";
                }
                default:
                    return "";
        }
    }
    protected String defineSelectionName(Market market,String selection) {
        Double oddValue = market.getOddsInfoBySelection(selection).getHdp();
        String oddLabel = "";
        switch (market.getBetType())
        {
            case "MONEYLINE":
                switch (selection){
                    case "HOME":
                        return market.getHomeName();
                    case "AWAY":
                        return market.getAwayName();
                    default:
                        return "Draw";}
            case "SPREAD":
                switch (selection) {
                    // plus or minus when selecting HOME or AWAY might be depended on negative or positive HDP point
                    case "HOME":
                        oddLabel = oddValue < 0 ? String.valueOf(oddValue) : "+" + String.valueOf(oddValue);
                        return String.format("%s %s", market.getHomeName(), oddLabel);
                    default:
                        oddLabel = oddValue < 0 ? "+" + String.valueOf(Math.abs(oddValue)) : String.valueOf(oddValue);
                        return String.format("%s %s", market.getAwayName(), oddLabel);
                }
            case "TOTAL_POINTS":
                switch (selection){
                    case "OVER":
                        return String.format("Over %s",market.getOddsInfoBySelection(selection).getHdp());
                    default:
                        return String.format("Under %s",market.getOddsInfoBySelection(selection).getHdp());
                }
            default:
                return "";
        }

    }

    /** This method define template of summary info in Bet Slip
     * Format: yyyy-MM-DD marketType match leagueNAme
     * @param market
     * @return the format yyyy-MM-DD marketType match leagueNAme
     */
    private String defineSummaryInfoInBetSlip(Market market) {
        String match = definePeriod(market);
        String marketName = defineMarketName(market);
        String eventStartTime = market.getEventStartTime().replace("Z", ".00+00:00");
        String eventDate = DateUtils.convertDateToNewTimeZone(eventStartTime, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "", "yyyy-MM-dd", GMT_7);
        return String.format("%s %s - %s - %s", eventDate, marketName, match, WordUtils.capitalizeFully(market.getLeagueName()).toUpperCase());
    }

    public void verifyBetSlipIsEmpty(){
        lblBetSlipMsgEmptyNoBets.isDisplayed();
        Assert.assertEquals(lblBetSlipTab.getText(), BET_SLIP_TAB, "FAILED! Bet slip tab contains number");
        Assert.assertEquals(lblBetSlipMsgEmptyNoBets.getText(), BETSLIP_NO_BETS_MSG, "FAILED! Msg no bet on bet slip empty is not correct");
        Assert.assertEquals(lblBetSlipMsgEmptyClickOdds.getText(), BETSLIP_CLICK_ODDS_MSG, "FAILED! Msg click odds on bet slip empty is not correct");
    }
    public String defineExpectedOdds(Market market, String oddsType, Boolean isNegativeOdds) {
        //in case moneyline, odds type HK and MY will be not converted
        if(market.getBetType().equalsIgnoreCase(TEXT_MONEYLINE)) {
            if (oddsType.equalsIgnoreCase(DECIMAL) || oddsType.equalsIgnoreCase(HONGKONG) || oddsType.equalsIgnoreCase(MALAY)) {
                return String.format("@%.3f",market.getOddsInfoBySelection(defineSelectionBaseOnOdds(market, false)).getOdds());
            } else {
                String selection = defineSelectionBaseOnOdds(market, isNegativeOdds);
                if(isNegativeOdds) {
                    return String.format("@%.0f",market.getOddsInfoBySelection(selection).getOdds());
                } else {
                    return String.format("@+%.0f",market.getOddsInfoBySelection(selection).getOdds());
                }

            }
        } else {
            if (oddsType.equalsIgnoreCase(DECIMAL) || oddsType.equalsIgnoreCase(HONGKONG)) {
                return String.format("@%.3f",market.getOddsInfoBySelection(defineSelectionBaseOnOdds(market, false)).getOdds());
            }
            String selection = defineSelectionBaseOnOdds(market, isNegativeOdds);
            if(isNegativeOdds){
                if(oddsType.equalsIgnoreCase(AMERICAN)) {
                    return String.format("@%.0f",market.getOddsInfoBySelection(selection).getOdds());
                } else if (oddsType.equalsIgnoreCase(MALAY)) {
                    return String.format("@%.3f",market.getOddsInfoBySelection(selection).getOdds());
                }
            } else {
                if(oddsType.equalsIgnoreCase(AMERICAN)) {
                    return String.format("@+%.0f",market.getOddsInfoBySelection(selection).getOdds());
                } else if (oddsType.equalsIgnoreCase(MALAY)) {
                    return String.format("@+%.3f",market.getOddsInfoBySelection(selection).getOdds());
                }
            }
        }
        return "";
    }

    public void verifyBetSlipInfo(Market market, boolean isNegativeOdds, String oddsType) {
        String selection = defineSelectionBaseOnOdds(market, isNegativeOdds);
        String expectedSelection= defineSelectionName(market,selection);
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", market.getEventId());
        String eventName = Label.xpath(String.format("%s%s", betslipRootXpath,lblEventNameXpath)).getText();
        String summaryInfo = Label.xpath(String.format("%s%s", betslipRootXpath, lblSummaryInfoXpath)).getText();
        String selectionName = Label.xpath(String.format("%s%s", betslipRootXpath, lblHDPPointXpath)).getText();
        String odds = Label.xpath(String.format("%s%s", betslipRootXpath,lblOddsXpath)).getText().replace("⠀","");
        // handle special character get from UI for negative odds actual @−253, expected @-253 but return assert failed
        if(odds.contains("−"))
            odds = odds.replace("−","-");
        String expectedOdds = defineExpectedOdds(market, oddsType, isNegativeOdds);
        Assert.assertEquals(odds,expectedOdds,"FAILED! Odds is incorrect");
        Assert.assertEquals(eventName,market.getEventName(),"FAILED! Event Name is incorect");
        Assert.assertEquals(summaryInfo,defineSummaryInfoInBetSlip(market),"FAILED! Summary info is incorrect");
        Assert.assertEquals(selectionName,expectedSelection,"FAILED! Selection name is incorrect");
    }

    public void verifyBetSlipInfo(Market market, String selection, String oddsType) {
        String expectedSelection= defineSelectionName(market,selection);
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", market.getEventId());
        String eventName = Label.xpath(String.format("%s%s", betslipRootXpath,lblEventNameXpath)).getText();
        String summaryInfo = Label.xpath(String.format("%s%s", betslipRootXpath, lblSummaryInfoXpath)).getText();
        String selectionName = Label.xpath(String.format("%s%s", betslipRootXpath, lblHDPPointXpath)).getText();
        String odds = Label.xpath(String.format("%s%s", betslipRootXpath,lblOddsXpath)).getText().replace("⠀","");
        String expectedOdds = defineExpectedOdds(market, oddsType, null);
        // handle special character get from UI for negative odds actual @−253, expected @-253 but return assert failed
        if(odds.contains("−"))
            odds = odds.replace("−","-");
        Assert.assertEquals(odds,expectedOdds,"FAILED! Odds is incorrect");
        Assert.assertEquals(eventName,market.getEventName(),"FAILED! Event Name is incorect");
        Assert.assertEquals(summaryInfo,defineSummaryInfoInBetSlip(market),"FAILED! Summary info is incorrect");        Assert.assertEquals(selectionName,expectedSelection,"FAILED! Selection name is incorrect");
//      min/max/maxpermatch will check in other method
//        String stake = Label.xpath(String.format("%s%s", betslipRootXpath, lblStakeXpath)).getText();
//        String minBet = Label.xpath(String.format("%s%s", betslipRootXpath,lblMinBetXpath)).getText();
//        String maxBet = Label.xpath(String.format("%s%s", betslipRootXpath, lblMaxBetXpath)).getText();
//        String matchMax = Label.xpath(String.format("%s%s", betslipRootXpath,lblMatchMaxXpath)).getText();

    }

    public void verifyMinMaxMatchOnBetSlip(String expectedMin,String expectedMax,String expectedMatch){
        String minBet = Label.xpath(String.format("//app-open-bets//app-bet-item%s", lblMinBetXpath)).getText().replace(".00", "");
        String maxBet = Label.xpath(String.format("//app-open-bets//app-bet-item%s", lblMaxBetXpath)).getText().replace(".00", "");
        String matchMax = Label.xpath(String.format("//app-open-bets//app-bet-item%s", lblMatchMaxXpath)).getText().replace(".00", "");
        Assert.assertEquals(minBet, expectedMin,"FAILED! Min bet on Bet slip is not correct");
        Assert.assertEquals(maxBet, expectedMax,"FAILED! Max bet on Bet slip is not correct");
        Assert.assertEquals(matchMax, expectedMatch,"FAILED! Match max bet on Bet slip is not correct");
    }

    public String defineSelectionBaseOnOdds(Market market, boolean isNegativeOdd){
        String selection = "";
        for (Odds o: market.getOdds()){
            if(o.getOdds() < 0 && isNegativeOdd){
                return o.getTeam();
            }
            if(o.getOdds() > 0 && !isNegativeOdd){
                return o.getTeam();
            }
        }
        return selection;
    }

    public void removeAddedBets(Market market) {
        List<String> lstOrderID = new ArrayList<>();
        try {
            new ArrayList<>(Label.xpath("//app-single-bets//div[contains(@class, 'bet-slip-item')]").getWebElements()).stream()
                    .forEach(s -> lstOrderID.add(s.getAttribute("orderid").trim()));
        } catch (Exception e) {
            System.out.println("ERROR! Can not get list of selection on bet slip. Cause: " + e.getMessage());
            return;
        }
        for (int i = 0; i < lstOrderID.size(); i++) {
            if (lstOrderID.get(i).contains(String.valueOf(market.getEventId())) &&
                    lstOrderID.get(i).contains(String.valueOf(market.getBetType()))) {
                removeAddedBetByIndex(i + 1);
                break;
            }
        }
    }

    public void removeAddedBetByIndex(int index){
        Button.xpath(String.format(btnXRemoveBetXpath, index)).click();
    }

    public void removeBetsByRemoveAll(boolean isConfirm){
        btnRemoveAll.click();
        if(confirmModulePopup.isDisplayed() && isConfirm){
            confirmModulePopup.confirm();
            waitForSpinnerLoading();
        }
    }

    private void clickPlaceBet(boolean isConfirm) {
        btnPlaceBet.jsClick();
        if(isConfirm && confirmModulePopup.isDisplayed())
        {
            confirmModulePopup.confirm();
            waitForSpinnerLoading();
        }

    }

    private void inputStake(Market market,String stake) {
        betslipRootXpath = String.format(betslipRootXpath,market.getEventId());
        TextBox txtStake = TextBox.xpath(String.format("%s%s", betslipRootXpath, txtStakeXpath));
        txtStake.sendKeys(stake);
    }
    public String defineStakeIn(Market market,String stake){
        // define stake. If input stake = minbet, we get min bet value from UI
        // if input stake = maxbe, we get min bet value from UI
        // otherwise input the stake value
        String stakeIn = stake;
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", market.getEventId());
        if(stakeIn.equalsIgnoreCase("minbet")){
            Label lblMinBetValue =  Label.xpath(String.format("%s%s",betslipRootXpath,lblMinBetValueXpath));
            lblMinBetValue.waitForElementToBePresent(lblMinBetValue.getLocator(),1);
            stakeIn = lblMinBetValue.getText().trim();
        }

        if(stakeIn.equalsIgnoreCase("maxbet")){
            Label lblMaxBetValue =  Label.xpath(String.format("%s%s",betslipRootXpath,lblMaxBetValueXpath));
            lblMaxBetValue.waitForElementToBePresent(lblMaxBetValue.getLocator(),1);
            stakeIn = lblMaxBetValue.getText().trim();
        }
        return stakeIn;
    }
    public Order placeNoBet(Market market,String stake, boolean isAcceptedBetterOdds,boolean isPlaceBet) {
        String stakeIn = defineStakeIn(market,stake);
        inputStake(market,stakeIn);
        //if(isAcceptedBetterOdds)
        //handle check on Accept Better Odds checkbox here
        if(isPlaceBet) {
            // click place bet button and confirm Ok
            clickPlaceBet(true);
            // get the first order in Pending Bet after place a bet and define the expected order info
            Order fistPendingOrder = getTheFistPendingOrder();
            return new Order.Builder()
                    .market(market)
                    .stake(Double.valueOf(stakeIn))
                    .oddsAccept(fistPendingOrder.getOddsAccept())
                    .orderID(fistPendingOrder.getOrderID())
                    .status(fistPendingOrder.getStatus())
                    .placeDate(fistPendingOrder.getPlaceDate())
                    .build();
        } else {
            // click place bet and do nothing
            clickPlaceBet(false);
            //use to verify stake input
            return new Order.Builder().stake(Double.valueOf(stakeIn)).build();
        }
    }

    public void verifyPendingBetInfo(Order order,String currency){
        int index = getOrderByID(Integer.toString(order.getOrderID()));
        verifyPendingBetInfo(index,order,currency);
    }

    private int getOrderByID(String orderID){
        int i = 1;
        while (true){
            String pendingBetXpath = String.format(pendingBetRootXpath,i);
            // get value from the UI
            Label lblID = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingOrderIdXpath));
            if(!lblID.isDisplayed())
                return 0;
            if(lblID.getText().contains(orderID))
                return i;
            i = i +1;
        }


    }

    /**
     * Get the info after place bet in Pending bet section that we cannot control: status, place date, odds, order Id
     * @return Order
     */
    private Order getTheFistPendingOrder(){
        String pendingBetXpath = String.format(pendingBetRootXpath,1);
        String idValue = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingOrderIdXpath)).getText();
        String orderId = idValue.split("\n")[0].split("ID:")[1].trim();
        String placeDate = idValue.split("\n")[1].trim();
        String status = Label.xpath(String.format("%s%s", pendingBetXpath, lblPendingBetStatusXpath)).getText();
        String odds =Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingOrderIdXpath)).getText();
        return new Order.Builder()
                .orderID(Integer.parseInt(orderId))
                .placeDate(placeDate)
                .status(status)
                //.oddsAccept(Double.valueOf(odds))
                .placeDate(placeDate)
                .build();
    }
    private void verifyPendingBetInfo(int index, Order orderExpected, String currency){
        String pendingBetXpath = String.format(pendingBetRootXpath,index);
        // get value from the UI
        String id = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingOrderIdXpath)).getText();
        String placeDate = Label.xpath(String.format("%s%s", pendingBetXpath, lblPendingBetPlaceDateXpath)).getText();
        String status = Label.xpath(String.format("%s%s", pendingBetXpath, lblPendingBetStatusXpath)).getText();
        String eventName = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingBetEventNameXpath)).getText();
        String leagueName =Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingLeagueNameXpath)).getText();
        String odds =Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingOddsXpath)).getText();
        String teamName =Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingSelectionXpath)).getText();
        String stakeLabel = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingStakeLabelXpath)).getText();
        String stakeValue = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingStakeValueXpath)).getText();
        String riskLabel= Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingRiskLabelXpath)).getText();
        String riskValue = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingRiskValueXpath)).getText();
        String winLabel = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingWinLabelXpath)).getText();
        String winValue = Label.xpath(String.format("%s%s", pendingBetXpath,lblPendingWinValueXpath)).getText();

        // Verify UI value with the expected
        if(Objects.nonNull(orderExpected.getOrderID()))
            Assert.assertEquals(id.trim(), String.format("ID: %d\n" +
                    "%s",orderExpected.getOrderID(),orderExpected.getPlaceDate()),"FAILED! Order ID header is incorrect");
        Assert.assertEquals(status, orderExpected.getStatus(),"FAILED! Status is incorrect");
        Assert.assertEquals(eventName, orderExpected.getMarket().getEventName(),"FAILED! Event Name is incorrect");
        Assert.assertEquals(leagueName, defineSummaryInfoInBetSlip(orderExpected.getMarket()),"FAILED! League is incorrect");
        Assert.assertEquals(teamName,defineSelectionName(orderExpected.getMarket(), orderExpected.getOdds().getTeam()),"FAILED! Team name is incorrect");
        DecimalFormat df = new DecimalFormat("0.#");
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(3);
        double oddsValue = Double.parseDouble(df.format(orderExpected.getOdds().getOdds()));
        String expectedOdds = String.format("@" + oddsValue +" (%s)",orderExpected.getOddsSign());
        if(orderExpected.getMarket().getOddsFormat().toLowerCase().equalsIgnoreCase(AMERICAN)){
            expectedOdds = String.format("@%.0f (%s)", orderExpected.getOdds().getOdds(),orderExpected.getOddsSign());
        }
        // handle special character get from UI for negative odds actual @−253, expected @-253 but return assert failed
        if(odds.contains("−"))
            odds = odds.replace("−","-");
        Assert.assertEquals(odds,expectedOdds,"FAILED! Odds is incorrect");
        Assert.assertEquals(stakeLabel,"Stake:","Failed! Stake label is incorrect");
        Assert.assertEquals(stakeValue, String.format("%s %.2f",currency, orderExpected.getStake())," Failed! Stake value is incorrect");
        Assert.assertEquals(riskLabel,"Risk:","Failed! Risk label is incorrect");
        Assert.assertEquals(riskValue, String.format("%s %.2f",currency, orderExpected.getRisk())," Failed! Risk value is incorrect");
        Assert.assertEquals(winLabel,"Win:","Failed! Win label is incorrect");
        Assert.assertEquals(winValue, String.format("%s %.2f",currency, orderExpected.getWin())," Failed! Win value is incorrect");
    }

    public void verifyErrorMsgOverBalance(){
        if(confirmModulePopup.isDisplayed()){
            confirmModulePopup.confirm();
            waitForSpinnerLoading();
        }
        Assert.assertEquals(lblErrorMsgOver.getText().replaceAll("\\d", "").trim(), BETSLIP_OVER_BALANCE_MSG, "FAILED! Error massage is not correct");
    }
}
