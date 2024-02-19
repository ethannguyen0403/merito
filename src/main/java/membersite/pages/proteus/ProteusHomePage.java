package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import membersite.controls.proteus.AppConfirmModulePopup;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Odds;
import membersite.objects.proteus.Order;
import membersite.objects.proteus.ProteusBetslip;
import membersite.pages.HomePage;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.*;

import static common.MemberConstants.GMT_7;
import static common.ProteusConstant.*;

public class ProteusHomePage extends HomePage {

    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading-text')]/p");
    private Image imgSpinner = Image.xpath("//em[contains(@class,'fa-4x fa-spin')]");
    private String moreMarketXpath = "//app-league-asian//table[@eventid='%s']//th[contains(@class,'more-markets')]";
    private Label lblMoreMarketDetails = Label.xpath("(//app-market-asian//div[@class='market-detail']//div[contains(@class,'market-item')]/span)");
    private Label lblBetSlipTab = Label.xpath("//app-bet-slip//div[text()='BET SLIP']");
    private Label lblPendingTab = Label.xpath("//app-bet-slip//div[text()='PENDING BETS']");
    public Label lblPlaceBetError = Label.xpath("//app-confirm-modal//div[contains(@class,'modal-body')]//div");
    // Bet Slip UI
    String betslipRootXpath = "//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]";
    String lblEventNameXpath = "//span[@class='teams-name']";
    String lblLiveScoreXpath ="//span[@class='live-score']";
    String lblSummaryInfoXpath = "//div[contains(@class,'bet-title')]";
    String lblHDPPointXpath = "//div[contains(@class,'fw-semibold')]";
    String lblOddsXpath = "//div[contains(@class,'odds-text')]";
    String lblStakeXpath = "//input[contains(@class,'stake-input')]";
    String lblMinBetXpath = "//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][1]/span[1]";
    String lblMinBetValueXpath ="//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][1]/span[1]/span";
    String lblMaxBetXpath =  "//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][1]/span[2]";
    String lblMaxBetValueXpath =  "//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][1]/span[2]/span";
    String lblMatchMaxXpath = "//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][2]/span[1]";
    String txtStakeXpath = "//input[contains(@class,'stake-input')]";
    Button btnPlaceBet = Button.xpath("//app-open-bets//button[contains(@class,'btn-place-bet')]");
    AppConfirmModulePopup confirmModulePopup = AppConfirmModulePopup.xpath("//app-confirm-modal");
    // End Bet Slip UI
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

    public String getCurrentUserOddsGroup(int eventId) {
        AsianViewPage asianViewPage = selectAsianView();
        asianViewPage.selectPeriodTab(EARLY_PERIOD);
        Label lblEvent = Label.xpath(String.format("//app-events//table[@eventid='%s']", eventId));
        if(lblEvent.isDisplayed()) {
            return lblEvent.getAttribute("oddgroup");
        } else {
            asianViewPage.selectPeriodTab(TODAY_PERIOD);
            return lblEvent.getAttribute("oddgroup");
        }
    }

    public List<Double> getListOddsByGroup(String oddsGroup, List<Double> lstBaseOdds, String oddsType) {
        switch (oddsGroup) {
            case "B":
            case "C":
            case "D":
            case "E":
                return lstAdjustOddsByGroup(oddsGroup, lstBaseOdds, oddsType);
            default:
                //group A return same list odds
                return lstBaseOdds;
        }
    }

    public static List<Double> lstAdjustOddsByGroup(String oddsGroup, List<Double> lstBaseOdds, String oddsType) {
        List<Double> lstOddsAdjust = new ArrayList<>();
        if (!oddsGroup.equalsIgnoreCase("A")) {
            double vigAdjustment = Double.parseDouble(ODDS_GROUP_ADJUSTMENT_MAPPING.get(oddsGroup));
            if(Objects.nonNull(lstBaseOdds)) {
                lstBaseOdds.removeIf(n -> n == 0);
                double vig = 0;
                List<Double> lstProbabilityBase = new ArrayList<>();
                List<Double> lstFairProbability = new ArrayList<>();
                List<Double> lstFattenedProbability = new ArrayList<>();
                List<Double> lstDecimalOdds = new ArrayList<>();
                List<Double> lstRoundedDecimalOdds = new ArrayList<>();
                for (int i = 0; i < lstBaseOdds.size(); i++) {
                    lstProbabilityBase.add((1/Double.valueOf(lstBaseOdds.get(i))));
                    vig += (1/Double.valueOf(lstBaseOdds.get(i)));
                }
                if(Objects.nonNull(lstProbabilityBase)) {
                    for (int i = 0; i < lstProbabilityBase.size(); i++) {
                        lstFairProbability.add(lstProbabilityBase.get(i) / vig);
                    }
                }
                if(Objects.nonNull(lstFairProbability)) {
                    for (int i = 0; i < lstFairProbability.size(); i++) {
                        lstFattenedProbability.add(lstFairProbability.get(i) * (vig + (vigAdjustment/100)));
                        lstDecimalOdds.add(1/ lstFattenedProbability.get(i));
                    }
                }
                if (Objects.nonNull(lstDecimalOdds)) {
                    for (int i = 0; i < lstDecimalOdds.size(); i++) {
                        if(lstDecimalOdds.get(i) > 2) {
                            lstRoundedDecimalOdds.add(Math.floor(lstDecimalOdds.get(i) * 100) / 100);
                        } else {
                            lstRoundedDecimalOdds.add(Math.floor(lstDecimalOdds.get(i) * 1000) / 1000);
                        }
                    }
                }
                if(oddsType.equalsIgnoreCase("DEC")) {
                    return lstRoundedDecimalOdds;
                } else if (oddsType.equalsIgnoreCase("HK")) {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                        //rounding to 3 decimal places
                        lstOddsAdjust.add(Math.floor((lstRoundedDecimalOdds.get(i) - 1) * 10000) / 10000);
                    }
                    return lstOddsAdjust;
                } else if (oddsType.equalsIgnoreCase("ID")) {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                        //rounding to 3 decimal places
                        if(lstRoundedDecimalOdds.get(i) >= 2) {
                            lstOddsAdjust.add(Math.floor((lstRoundedDecimalOdds.get(i) - 1) * 10000) / 10000);
                        } else {
                            lstOddsAdjust.add(Math.abs(Math.floor((-1/(lstRoundedDecimalOdds.get(i) - 1)) * 10000) / 10000));
                        }
                    }
                    return lstOddsAdjust;
                } else if (oddsType.equalsIgnoreCase("MY")) {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                        //rounding to 3 decimal places
                        if(lstRoundedDecimalOdds.get(i) <= 2) {
                            lstOddsAdjust.add(Math.floor((lstRoundedDecimalOdds.get(i) - 1) * 10000) / 10000);
                        } else {
                            lstOddsAdjust.add(Math.abs(Math.floor((-1/(lstRoundedDecimalOdds.get(i) - 1)) * 10000) / 10000));
                        }
                    }
                    return lstOddsAdjust;
                } else {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                        //return list no matter negative number
                        if(lstRoundedDecimalOdds.get(i) < 2) {
                            lstOddsAdjust.add(Double.valueOf(Math.abs(Math.round(-100/ (lstRoundedDecimalOdds.get(i) - 1)))));
                        } else {
                            lstOddsAdjust.add(Math.floor(((lstRoundedDecimalOdds.get(i) - 1) * 100) * 10000) / 10000);
                        }
                    }
                    return lstOddsAdjust;
                }
            }
        }
        return lstOddsAdjust;
    }

    public static List<Odds> convertOddsToGroup(Market market, String oddsGroup, String oddsType) {
        List<Odds> lstOddsAdjusted = new ArrayList<>();
        List<Odds> lstBaseOdds = market.getOdds();
        if (!oddsGroup.equalsIgnoreCase("A")) {
            double vigAdjustment = Double.parseDouble(ODDS_GROUP_ADJUSTMENT_MAPPING.get(oddsGroup));
            if(Objects.nonNull(lstBaseOdds)) {
                double vig = 0;
                List<Double> lstProbabilityBase = new ArrayList<>();
                List<Double> lstFairProbability = new ArrayList<>();
                List<Double> lstFattenedProbability = new ArrayList<>();
                List<Double> lstDecimalOdds = new ArrayList<>();
                List<Double> lstRoundedDecimalOdds = new ArrayList<>();
                for (int i = 0; i < lstBaseOdds.size(); i++) {
                    lstProbabilityBase.add((1/Double.valueOf(lstBaseOdds.get(i).getOdds())));
                    vig += (1/Double.valueOf(lstBaseOdds.get(i).getOdds()));
                }
                if(Objects.nonNull(lstProbabilityBase)) {
                    for (int i = 0; i < lstProbabilityBase.size(); i++) {
                        lstFairProbability.add(lstProbabilityBase.get(i) / vig);
                    }
                }
                if(Objects.nonNull(lstFairProbability)) {
                    for (int i = 0; i < lstFairProbability.size(); i++) {
                        lstFattenedProbability.add(lstFairProbability.get(i) * (vig + (vigAdjustment/100)));
                        lstDecimalOdds.add(1/ lstFattenedProbability.get(i));
                    }
                }
                if (Objects.nonNull(lstDecimalOdds)) {
                    for (int i = 0; i < lstDecimalOdds.size(); i++) {
                        if(lstDecimalOdds.get(i) > 2) {
                            lstRoundedDecimalOdds.add(Math.floor(lstDecimalOdds.get(i) * 100) / 100);
                        } else {
                            lstRoundedDecimalOdds.add(Math.floor(lstDecimalOdds.get(i) * 1000) / 1000);
                        }
                    }
                }
                if(oddsType.equalsIgnoreCase(DECIMAL)) {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                       market.getOdds().get(i).setOdds(lstRoundedDecimalOdds.get(i));
                    }
                    return market.getOdds();
                } else if (oddsType.equalsIgnoreCase(HONGKONG)) {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                        //rounding to 3 decimal places
                        market.getOdds().get(i).setOdds(Math.floor((lstRoundedDecimalOdds.get(i) - 1) * 10000) / 10000);
                    }
                    return market.getOdds();
                }
                else if (oddsType.equalsIgnoreCase(MALAY)) {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                        //rounding to 3 decimal places
                        if (lstRoundedDecimalOdds.get(i) <= 2) {
                            market.getOdds().get(i).setOdds(Math.floor((lstRoundedDecimalOdds.get(i) - 1) * 10000) / 10000);
                        } else {
                            market.getOdds().get(i).setOdds(Math.abs(Math.floor((-1 / (lstRoundedDecimalOdds.get(i) - 1)) * 10000) / 10000));
                        }
                    }
                    return market.getOdds();
                }
                else {
                    for (int i = 0; i < lstRoundedDecimalOdds.size(); i++) {
                        //return list no matter negative number
                        if(lstRoundedDecimalOdds.get(i) < 2) {
                            market.getOdds().get(i).setOdds(Double.valueOf(Math.ceil(Math.abs(-100/ (lstRoundedDecimalOdds.get(i) - 1)))));
                        } else {
                            market.getOdds().get(i).setOdds(Math.floor(((lstRoundedDecimalOdds.get(i) - 1) * 100) * 10000) / 10000);
                        }
                    }
                    return market.getOdds();
                }
            }
        }
        return lstOddsAdjusted;
    }
    public void compareOddsShowCorrect(List<Double> lstOddsConvert, List<Double> lstOddsActual, double tolerance) {
        Collections.sort(lstOddsConvert);
        Collections.sort(lstOddsActual);
        Assert.assertEquals(lstOddsConvert.size(), lstOddsActual.size(), String.format("FAILED! Number of odds between 2 compare list is not same convert %s actual %s", lstOddsConvert, lstOddsActual));
        for (int i = 0; i < lstOddsConvert.size(); i++) {
            Assert.assertEquals(lstOddsConvert.get(i), lstOddsActual.get(i), tolerance, String.format("FAILED! Odds does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        }
    }

    public void openMoreMarkets(String eventId) {
        Label lblMoreMarket = Label.xpath(String.format(moreMarketXpath, eventId));
        if(lblMoreMarket.isDisplayed()) {
            lblMoreMarket.click();
            waitForSpinnerLoading();
        }
    }

    public void selectMoreMarket(String marketName) {
        for (int i = 0; i < lblMoreMarketDetails.getWebElements().size(); i++) {
            String xpath = lblMoreMarketDetails.getLocator().toString().replace("By.xpath: ","") + String.format("[%s]", i + 1);
            Label lblMarketName = Label.xpath(xpath);
            if(lblMarketName.getText().trim().contains(marketName)) {
                lblMarketName.click();
            }
        }
    }

    public void switchTabBetSlip(String tabName) {
        if (tabName.equalsIgnoreCase("bet slip")) {
            lblBetSlipTab.click();
        } else if (tabName.equalsIgnoreCase("pending bets")) {
            lblPendingTab.click();
        }
    }

    public void verifyToRiskToWinShowCorrect(String stake, String odds) {
        String oddsFormat = odds.replace("−","");
        double toRisk = Math.floor(Double.valueOf(stake) * Double.valueOf(oddsFormat) * 100) / 100;
        double toWin = Math.floor(toRisk / Double.valueOf(oddsFormat) * 100) / 100;
    }

    // Start Bet Slip, Pending Bets section

    public String definePeriod(Market market) {
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
                switch (selection){
                    case "HOME":
                        return String.format("%s %s",market.getHomeName(),market.getOddsInfoBySelection(selection).getHdp());
                    default:
                        return String.format("%s %s",market.getAwayName(),market.getOddsInfoBySelection(selection).getHdp());
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
    private String defineSummaryInfoInBetSlip(Market market){
        String match= definePeriod(market);
        String marketName = defineMarketName(market);
        String eventStartTime = market.getEventStartTime().replace("Z",".00+00:00");
        String eventDate = DateUtils.convertDateToNewTimeZone(eventStartTime,"yyyy-MM-dd'T'HH:mm:ss.SSSXXX","","yyyy-MM-dd",GMT_7);
        return String.format("%s %s - %s - %s",eventDate,marketName, match, market.getLeagueName());

    }

    public void verifyBetSlipInfo(Market market, String selection, String oddsType) {
        String expectedSelection= defineSelectionName(market,selection);
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", market.getEventId());
        String eventName = Label.xpath(String.format("%s%s", betslipRootXpath,lblEventNameXpath)).getText();
        String summaryInfo = Label.xpath(String.format("%s%s", betslipRootXpath, lblSummaryInfoXpath)).getText();
        String selectionName = Label.xpath(String.format("%s%s", betslipRootXpath, lblHDPPointXpath)).getText();
        String odds = Label.xpath(String.format("%s%s", betslipRootXpath,lblOddsXpath)).getText();
        String expectedOdds = String.format("@%.3f",market.getOddsInfoBySelection(selection).getOdds());
        if(oddsType.toLowerCase().equalsIgnoreCase(AMERICAN)){
            expectedOdds = String.format("@%.0f",market.getOddsInfoBySelection(selection).getOdds());
        }
        // handle special character get from UI for negative odds actual @−253, expected @-253 but return assert failed
        if(odds.contains("−"))
            odds = odds.replace("−","-");
        Assert.assertEquals(odds,expectedOdds,"FAILED! Odds is incorrect");
        Assert.assertEquals(eventName,market.getEventName(),"FAILED! Event Name is incorect");
        Assert.assertEquals(summaryInfo,defineSummaryInfoInBetSlip(market),"FAILED! Summary info is incorrect");
        Assert.assertEquals(selectionName,expectedSelection,"FAILED! Selection name is incorrect");
//      min/max/maxpermatch will check in other method
//        String stake = Label.xpath(String.format("%s%s", betslipRootXpath, lblStakeXpath)).getText();
//        String minBet = Label.xpath(String.format("%s%s", betslipRootXpath,lblMinBetXpath)).getText();
//        String maxBet = Label.xpath(String.format("%s%s", betslipRootXpath, lblMaxBetXpath)).getText();
//        String matchMax = Label.xpath(String.format("%s%s", betslipRootXpath,lblMatchMaxXpath)).getText();

    }

    private void clickPlaceBet(boolean isConfirm) {
        btnPlaceBet.jsClick();
        if(isConfirm)
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
    private String defineStakeIn(Market market,String stake){
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
        System.out.println("Input stake :" +stakeIn);
        inputStake(market,stakeIn);
        //if(isAcceptedBetterOdds)
            //handle check on Accept Better Odds checkbox here
        if(isPlaceBet)
            // click place bet button and confirm Ok
            clickPlaceBet(true);
        else
            // click place bet and do nothing
            clickPlaceBet(false);

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
        double oddsValue = Double.parseDouble(df.format(orderExpected.getOdds().getOdds()));
        String expectedOdds = String.format(oddsValue +" (%s)",orderExpected.getOddsSign());
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

    // End Bet Slip, Pending Bets section
    public ProteusBetslip getBetSlipInfo(String eventId) {
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", eventId);
        Label lblEventName = Label.xpath(String.format("%s%s", betslipRootXpath, "//span[@class='teams-name']"));
        Label lblSummaryInfo = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'bet-title')]"));
        Label lblHDPPoint = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'fw-semibold')]"));
        Label lblOdds = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'odds-text')]//span"));
        Label lblStake = Label.xpath(String.format("%s%s", betslipRootXpath, "//input[contains(@class,'stake-input')]"));
        Label lblMinBet = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'limit-stake-container')]//span[contains(text(),'Min bet')]/span"));
        Label lblMaxBet = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'limit-stake-container')]//span[contains(text(),'Max bet')]/span"));
        Label lblMatchMax = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'limit-stake-container')]//span[contains(text(),'Match Max')]/span"));
        return new ProteusBetslip.Builder().eventName(lblEventName.getText().trim())
                .summaryEventInfo(lblSummaryInfo.getText().trim())
                .hdpPoint(lblHDPPoint.getText().trim())
                .odds(lblOdds.getText().trim())
                .stake(lblStake.getAttribute("value"))
                .minBet(lblMinBet.getText().trim())
                .maxBet(lblMaxBet.getText().trim())
                .maxMatch(lblMatchMax.getText().trim())
                .build();
    }

}
