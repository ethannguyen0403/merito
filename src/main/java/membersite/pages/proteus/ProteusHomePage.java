package membersite.pages.proteus;

import backoffice.pages.bo._components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import membersite.controls.proteus.AppConfirmModulePopup;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Odds;
import membersite.objects.proteus.ProteusBetslip;
import membersite.objects.proteus.ProteusGeneralEvent;
import membersite.pages.HomePage;
import org.testng.Assert;

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
    String lblMaxBetXpath =  "//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][1]/span[1]";
    String lblMatchMaxXpath = "//div[contains(@class,'limit-stake-container')]/div[contains(@class,'limit-stake')][2]/span[1]";
    String  txtStakeXpath = "//input[contains(@class,'stake-input')]";
    Button btnPlaceBet = Button.xpath("//app-open-bets//button[contains(@class,'btn-place-bet')]");
    AppConfirmModulePopup confirmModulePopup = AppConfirmModulePopup.xpath("//app-confirm-modal");

    public ProteusHomePage(String types) {
        super(types);
    }

    public void waitiFrameLoad(){
        lblLoading.waitForControlInvisible(2,2);
    }
    public void waitForSpinnerLoading() {
        imgSpinner.waitForControlInvisible(2, 5);
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

    private String definePeriod(Market market) {
        String match = "";
        if (market.getPeriodId() == 0)
            match = "Match";
        else
            match = "1st Half";
        return match;
    }
    private String defineMarketName(Market market) {
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
    private String defineSelectionName(Market market,String selection) {
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
                        return String.format("%s %.2f",market.getHomeName(),market.getOddsInfoBySelection(selection).getHdp());
                    default:
                        return String.format("%s %.2f",market.getAwayName(),market.getOddsInfoBySelection(selection).getHdp());
                }
            case "TOTAL_POINTS":
                switch (selection){
                    case "OVER":
                        return String.format("Over %.2f",market.getOddsInfoBySelection(selection).getHdp());
                    default:
                        return String.format("Under %.2f",market.getOddsInfoBySelection(selection).getHdp());
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
        String expectedOdds = String.format("@%.3f",market.getOddsInfoBySelection("HOME").getOdds());
        if(oddsType.toLowerCase().equalsIgnoreCase(AMERICAN)){
            expectedOdds = String.format("@%.0f",market.getOddsInfoBySelection("HOME").getOdds());
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
            confirmModulePopup.getContent();
    }

    private void inputStake(Market market,String stake) {
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", market.getEventId());
        TextBox txtStake = TextBox.xpath(String.format("%s%s", betslipRootXpath, txtStakeXpath));
        txtStake.sendKeys(stake);
    }
    public void placeNoBet(Market market,String stake, boolean isAcceptedBetterOdds,boolean isPlaceBet) {
        inputStake(market,stake);
        //if(isAcceptedBetterOdds)
            //handle check on Accept Better Odds checkbox here
        if(isPlaceBet)
            // click place bet button and confirm Ok
            clickPlaceBet(true);
        else
            // click place bet and do nothing
            clickPlaceBet(false);
       // return a Order object
    }

    // End Bet Slip, Pending Bets section


}
