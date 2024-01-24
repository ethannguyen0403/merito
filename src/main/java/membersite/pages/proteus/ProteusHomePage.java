package membersite.pages.proteus;

import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import membersite.pages.HomePage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
}
