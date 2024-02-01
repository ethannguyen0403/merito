package membersite.pages;


import com.paltech.element.common.Label;
import membersite.controls.FancyContainerControl;
import membersite.controls.FancyContainerControlOldUI;
import membersite.controls.OneClickBettingControl;
import membersite.controls.WicketBookmakerContainerControl;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Market;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.marketcontainer.MarketContainerControl;
import membersite.pages.components.racingmarketcontainer.RacingMarketContainer;
import membersite.pages.popup.RulePopup;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MarketPage extends HomePage {
    public MarketContainerControl marketOddControl;
    public RacingMarketContainer racingMarketContainer;
    public OneClickBettingControl oneClickBettingControl;
    public FancyContainerControl wcFancyContainerControl;
    public FancyContainerControl centralFancyContainerControl;
    public FancyContainerControl fancyContainerControl;
    public FancyContainerControlOldUI odlUIFancyContainerControl;

    public MarketPage(String types) {
        super(types);
        marketOddControl = ComponentsFactory.marketOddControlObject(types);
        racingMarketContainer = ComponentsFactory.racingMarketContainerObject(types);
        oneClickBettingControl = OneClickBettingControl.xpath("//div[@id='one-click-betting']");
        wcFancyContainerControl = FancyContainerControl.xpath("//span[text()='Wicket Fancy']//ancestor::div[contains(@class,'fancy-container')]");
        odlUIFancyContainerControl = FancyContainerControlOldUI.xpath("//div[@id='fair-27-fancy']");
        centralFancyContainerControl = FancyContainerControl.xpath("//span[text()='Central Fancy']//ancestor::div[contains(@class,'fancy-container')]");
        fancyContainerControl = FancyContainerControl.xpath("//span[text()='Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    }

    public void placeBet(Market market,String stake){
        String odds;
        // Handle place bet when place bet if empty odds
        if(!market.getBtnOdd().isDisplayed())
            odds ="1.01";
        else if( market.getBtnOdd().getText().equals(""))
            odds = "1.01";
        else
            odds = market.getBtnOdd().getText();
        market.getBtnOdd().click();
        // Place bet with odds:%s Stake: %s", odds, stake))
        betsSlipContainer.placeBet(odds, stake);
    }
    public void activeProduct(String products) {
        marketOddControl.activeProduct(products);
    }

    public Market getBookmakerMarketInfo(BookmakerMarket bookmakerMarket, boolean isBack) {
        WicketBookmakerContainerControl wcBookmakerContainerControl;
        switch (bookmakerMarket.getMaketType()) {
            case "WICKET_BOOKMAKER":
                wcBookmakerContainerControl = WicketBookmakerContainerControl.xpath("//wicket-bookmarker-odds//div[contains(@class,'table-odds')]");
                return wcBookmakerContainerControl.getBookmakerMarketInfo(bookmakerMarket, isBack);
            case "ARTEMIS_BOOKMAKER":
                wcBookmakerContainerControl = WicketBookmakerContainerControl.xpath("//app-artmis-bookmaker-odds//div[contains(@class,'table-odds')]");
                return wcBookmakerContainerControl.getBookmakerMarketInfo(bookmakerMarket, isBack);
            default:
                wcBookmakerContainerControl = WicketBookmakerContainerControl.xpath("//central-bookmarker-odds//div[contains(@class,'table-odds')]");
                return wcBookmakerContainerControl.getBookmakerMarketInfo(bookmakerMarket, isBack);
        }
    }

    public List<ArrayList> getBookmakerMiniMyBet() {
        return myBetsContainer.getBookmakerMatchBets();
    }

    public FancyMarket getFancyMarketInfo(FancyMarket fcMarket) {
        return marketOddControl.getFancyMarketInfo(fcMarket);
    }

    public FancyMarket getArtemisFancyMarketInfo(FancyMarket fcMarket, int runnerNo) {
        return marketOddControl.getArtemisFancyMarketInfo(fcMarket, runnerNo);
    }

    public RulePopup openRules(){
        marketOddControl.clickRuleButton();
        return new RulePopup();
    }
    public void addFancyOdds(FancyMarket fcMarket, boolean isBack) {
        switch (fcMarket.getMaketType()) {
            case "WICKET_FANCY":
                wcFancyContainerControl.clickFancyOdds(fcMarket, isBack);
                return;
            case "CENTRAL_FANCY":
                centralFancyContainerControl.clickFancyOdds(fcMarket, isBack);
                return;
            default:
                fancyContainerControl.clickFancyOdds(fcMarket, isBack);
                return;
        }

    }

    public void addArtemisFancyOdds(FancyMarket fcMarket, boolean isBack, int runnerNo) {
        fancyContainerControl.clickArtemisFancyOdds(fcMarket, isBack, runnerNo);
    }

    public void placeFancy(FancyMarket fancyMarket, boolean isBack, String stake) {
        System.out.println("Click on odds");
        addFancyOdds(fancyMarket, isBack);
        betsSlipContainer.placeBet(stake);
    }

    public void placeArtemisFancy(FancyMarket fancyMarket, boolean isBack, String stake, int runnerNo) {
        System.out.println("Click on odds");
        addArtemisFancyOdds(fancyMarket, isBack, runnerNo);
        betsSlipContainer.placeBet(stake);
    }

    public Wager defineFancyWager(FancyMarket fcMarket, boolean isBack, double stake) {
        String runnerName;
        String betType = isBack ? "BACK" : "LAY";
        double odds = isBack ? fcMarket.getOddsYes() : fcMarket.getOddsNo();
        int payout = isBack ? fcMarket.getRateYes() : fcMarket.getRateNo();
        if(fcMarket.getNumberOfActiveRunner() >= 2) {
            runnerName = fcMarket.getSelection();
        } else {
            runnerName = isBack ? "Yes [L]" : "No [K]";
        }
        return new Wager.Builder()
                .betType(betType)
                .marketName(fcMarket.getMarketName())
                .odds(odds)
                .payout(payout)
                .stake(stake)
                .runnerName(runnerName)
                .numberOfActiveRunner(fcMarket.getNumberOfActiveRunner())
                .build();
    }

    public Wager defineBookmakerWager(BookmakerMarket market, boolean isBack, double stake) {
        String betType = isBack ? "BACK" : "LAY";
        String odds = isBack? market.getOddsBack() : market.getOddsLay();
        String runnerName = market.getSelectionName().trim();
        return new Wager.Builder()
                .betType(betType)
                .marketName(market.getMarketName())
                .odds(Double.parseDouble(odds))
                .stake(stake)
                .runnerName(runnerName)
                .marketType(market.getMaketType())
                .build();
    }

    public Wager defineWager(Market market, boolean isBack, double stake, double handicap) {
        String betType = isBack ? "BACK" : "LAY";
        double odds = Double.parseDouble(market.getOdds());
        String runnerName = market.getSelectionName().trim();
        return new Wager.Builder()
                .betType(betType)
                .marketName(market.getMarketName())
                .odds(odds)
                .stake(stake)
                .runnerName(runnerName)
                .handicap(handicap)
                .marketType(market.getMarketType())
                .build();
    }

    public double liabilityExchangeMarket(List<Wager> normalMarket) {
        if (Objects.isNull(normalMarket))
            return 0;
        List<Integer> oddsRange = oddRange(normalMarket);
        List<Double> lstForecastAWager = getForeCastOnWager(normalMarket.get(0), oddsRange);
        if (normalMarket.size() > 1) {
            for (int i = 1; i < normalMarket.size(); i++) {
                lstForecastAWager = sumData(lstForecastAWager, getForeCastOnWager(normalMarket.get(i), oddsRange));
            }
        }
        return Collections.max(lstForecastAWager);
    }

    public double liabilityFCMarket(List<Wager> fcWagerList) {
        if (Objects.isNull(fcWagerList))
            return 0;
        List<Integer> oddsRange = oddRange(fcWagerList);
        List<Double> lstForecastAWager = getForeCastOnWager(fcWagerList.get(0), oddsRange);
        List<Double> lstLiability = new ArrayList<>();
        if (fcWagerList.size() > 1) {
            for (int i = 1; i < fcWagerList.size(); i++) {
                lstLiability = sumData(lstForecastAWager, getForeCastOnWager(fcWagerList.get(i), oddsRange));
            }
        }
        /* return Collections.max(lstLiability);*/
        double liability = Collections.max(lstLiability);
        if (Collections.max(lstForecastAWager) != 0)
            return liability * (-1);
        return liability;
    }

    private List<Double> sumData(List<Double> lst1, List<Double> lst2) {
        List<Double> lstResult = new ArrayList<>();
        for (int i = 0; i < lst1.size(); i++) {
            lstResult.add(lst1.get(i) + lst2.get(i));
        }
        return lstResult;
    }

    private List<Double> getForeCastOnWager(Wager wg, List<Integer> range) {
        List<Double> lstLiabilityFCForecast = new ArrayList<>();
        wg.getOdds();
        wg.getPayout();
        wg.getStake();
        //TODO: Incase payout is not 100 -> forecast is incorrect -> handle script in this case
        for (int odds : range) {
            if (wg.getBetType().equalsIgnoreCase("BACK")) {
                if (odds >= wg.getOdds())
                    lstLiabilityFCForecast.add(wg.getProfit());
                else
                    lstLiabilityFCForecast.add(wg.getLiability());
            } else {
                if (odds >= wg.getOdds())
                    lstLiabilityFCForecast.add(wg.getLiability());
                else
                    lstLiabilityFCForecast.add(wg.getProfit());
            }
        }
        return lstLiabilityFCForecast;
    }

    private List<Integer> oddRange(List<Wager> fcWagerList) {
        List<Integer> oddsRangeLst = new ArrayList<>();
        int maxIndex = fcWagerList.size();
        oddsRangeLst.add(0);
        for (Wager wg : fcWagerList) {
            oddsRangeLst.add((int) wg.getOdds());
        }
        oddsRangeLst.add(fcWagerList.size() - 1, 9999);

        Collections.sort(oddsRangeLst);
        int min = oddsRangeLst.get(0) - 1;
        int max = oddsRangeLst.get(maxIndex) + 1;
        oddsRangeLst.set(0, min);
        oddsRangeLst.set(maxIndex, max);
        return oddsRangeLst;
    }

    public List<ArrayList> getFancyMiniMyBet() {
        return myBetsContainer.getMatchedFancyInMiniMyBet();
    }

    public boolean verifyOddsIsClickAbleAsBetableStaus(Market market, boolean isBetable) {
        market.getBtnOdd().click();
        Label btnOdds = market.getBtnOdd();
        if (isBetable) {
            if (btnOdds.isEnabled())
                return true;
            return false;
        } else {
            if (!btnOdds.isEnabled())
                return true;
            return false;
        }
    }

    public String getMinMaxLable(FancyMarket fcMarket) {
        switch (fcMarket.getMaketType()) {
            case "WICKET_FANCY":
                return wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
            case "CENTRAL_FANCY":
                return centralFancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
            default:
                return fancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
        }
    }

    public String getMinMaxOFFancyMarket(String minMax, boolean isMinStake) {
        //  String[] minMax = wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
        String[] minMaxArr = minMax.split("/");
        if (isMinStake)
            return minMaxArr[0].trim().replaceAll(",", "");
        return minMaxArr[1].trim().replaceAll(",", "");

    }

    public List<ArrayList> getFancyBetSlipMiniMyBet() {
        return myBetsContainer.getFancyBetSlipInfo();
    }

    public List<ArrayList> getBookmakerBetSlipMiniMyBet() {
        return myBetsContainer.getBookmakerBetSlipInfo();
    }

    public void openFancyLadderForecast(FancyMarket fcMarket) { marketOddControl.openFancyLadderForecast(fcMarket);}

    public boolean isLadderForecastDisplay(FancyMarket fcMarket) {return marketOddControl.isLadderForecastDisplay(fcMarket);}

    public double getMaxWinLoseMarket(FancyMarket fcMarket, boolean isBack) {
        double maxWLCalculate;
        if(isBack) {
            if (fcMarket.getRateYes() <= 100) {
                return fcMarket.getMaxSetting();
            } else {
                return maxWLCalculate = Math.floor(fcMarket.getMaxSetting() / fcMarket.getRateYes() * 100);
            }
        } else {
            if (fcMarket.getRateNo() <= 100) {
                return fcMarket.getMaxSetting();
            } else {
                return maxWLCalculate = Math.floor(fcMarket.getMaxSetting() / fcMarket.getRateNo() * 100);
            }
        }

    }

    public void verifyExposureKeptCorrectly(double originalExposure, FancyMarket fcMarket) {
        double sumBackWin = 0.0;
        double sumBackLose = 0.0;
        double sumLayWin = 0.0;
        double sumLayLose = 0.0;
        double forecast = 0.0;
        //TODO need update to get list matched bets by API
        List<ArrayList> lstMatchedBet = getFancyMiniMyBet();
        List<ArrayList> lstMatchedBetByMarket = new ArrayList<>();
        if(Objects.nonNull(lstMatchedBet)) {
            for (int i = 0; i < lstMatchedBet.size(); i++) {
                if(lstMatchedBet.get(i).get(0).toString().equalsIgnoreCase(fcMarket.getMarketName())) {
                    lstMatchedBetByMarket.add(lstMatchedBet.get(i));
                    String betType = lstMatchedBet.get(i).get(5).toString();
                    if (betType.equalsIgnoreCase("BACK")) {
                        sumBackWin += Double.valueOf(lstMatchedBet.get(i).get(4).toString());
                        sumBackLose += Double.valueOf(lstMatchedBet.get(i).get(3).toString());
                    } else {
                        sumLayWin += Double.valueOf(lstMatchedBet.get(i).get(4).toString());
                        sumLayLose += Double.valueOf(lstMatchedBet.get(i).get(3).toString());
                    }
                }
            }
            if(lstMatchedBetByMarket.size() > 1) {
                if(fcMarket.getRateYes() == 100 || fcMarket.getRateYes() == 0) {
                    forecast = -sumLayWin - sumBackWin;
                } else {
                    forecast = -sumLayWin + sumBackWin;
                }
            } else {
                if(lstMatchedBet.get(0).get(5).toString().equalsIgnoreCase("BACK")) {
                    forecast = -sumBackLose;
                } else {
                    forecast = -sumLayWin;
                }
            }
        }
        FancyMarket fancyMarket = getFancyMarketInfo(fcMarket);
        double newExposure = Double.parseDouble(BetUtils.getUserBalance().getExposure()) ;
        double calculateExposure = newExposure - fancyMarket.getMarketLiability();
        Assert.assertEquals(originalExposure, Math.floor(calculateExposure * 100) / 100, 0.01, String.format("FAILED! Exposure kept is not correct expected is %s, actual is %s", originalExposure, Math.floor(calculateExposure * 100) / 100));
        Assert.assertEquals(Math.floor(forecast * 100)/ 100, fancyMarket.getMarketLiability(), 0.01, String.format("FAILED! Liability forecast is not correct expected is %s, actual is %s", Math.floor(forecast * 100)/ 100, fancyMarket.getMarketLiability()));
    }

}
