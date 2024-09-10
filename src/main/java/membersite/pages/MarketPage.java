package membersite.pages;

import com.paltech.element.common.Label;
import com.paltech.utils.DoubleUtils;
import membersite.controls.FancyContainerControl;
import membersite.controls.FancyContainerControlOldUI;
import membersite.controls.OneClickBettingControl;
import membersite.controls.WicketBookmakerContainerControl;
import membersite.objects.Wager;
import membersite.objects.sat.*;
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
        oneClickBettingControl = OneClickBettingControl.xpath("(//div[@id='one-click-betting'] | //app-one-click//div[@class='one-click ng-star-inserted'])");
        wcFancyContainerControl = FancyContainerControl.xpath("//span[text()='Wicket Fancy']//ancestor::div[contains(@class,'fancy-container')]");
        odlUIFancyContainerControl = FancyContainerControlOldUI.xpath("//div[@id='fair-27-fancy']");
        centralFancyContainerControl = FancyContainerControl.xpath("//span[text()='Central Fancy']//ancestor::div[contains(@class,'fancy-container')]");
        fancyContainerControl = FancyContainerControl.xpath("//span[text()='Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    }

    public String getEventTitle() {
        Label lblEvent = Label.xpath("//div[@class='market-info']//div[contains(@class, 'titles')]");
        return lblEvent.getText().trim();
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

    public void verifyForeCastIsCorrect(List<ArrayList<String>> foreCastDataBefore, List<ArrayList<String>> foreCastDataAfter, Order order) {
        if(foreCastDataBefore.get(0).get(1).equalsIgnoreCase("")) {
            String profit = String.format("%.2f",
                    order.getProfit(order.getIsBack(), Double.valueOf(order.getOdds()), Double.valueOf(order.getStake())));
            String liability = String.format("%s%.2f","-",
                    order.getLiablity(order.getIsBack(), Double.valueOf(order.getOdds()), Double.valueOf(order.getStake())));
            for (ArrayList<String> lstForeCast : foreCastDataAfter) {
                if(lstForeCast.contains(order.getSelectionName()) && order.getIsBack()) {
                    Assert.assertEquals(lstForeCast.get(1), profit,
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                } else if (lstForeCast.contains(order.getSelectionName()) && !order.getIsBack()) {
                    Assert.assertEquals(lstForeCast.get(1), liability,
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                } else if (!lstForeCast.contains(order.getSelectionName()) && order.getIsBack()) {
                    Assert.assertEquals(lstForeCast.get(1), liability,
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                } else {
                    Assert.assertEquals(lstForeCast.get(1), profit,
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                }
            }
        } else {
            float profitFloat = 0;
            float liabilityFloat = 0;
            String homeForecast = foreCastDataBefore.get(0).get(1);
            String awayForecast = foreCastDataBefore.get(1).get(1);
            String profit = String.format("%.2f",
                    order.getProfit(order.getIsBack(), Double.valueOf(order.getOdds()), Double.valueOf(order.getStake())));
            String liability = String.format("%s%.2f","-",
                    order.getLiablity(order.getIsBack(), Double.valueOf(order.getOdds()), Double.valueOf(order.getStake())));
            //find the selection are betting then calculate expected profit/ liability
            for (int i = 0; i < foreCastDataBefore.size(); i++) {
                if(foreCastDataBefore.get(i).get(0).equalsIgnoreCase(order.getSelectionName())) {
                    //HOME - BACK
                    if (i == 0 && order.getIsBack()) {
                        profitFloat = Float.valueOf(homeForecast) +  Float.valueOf(profit);
                        liabilityFloat =  Float.valueOf(awayForecast) + Float.valueOf(liability);
                        break;
                    }
                    //HOME - LAY
                    else if (i == 0 && !order.getIsBack()) {
                        profitFloat = Float.valueOf(homeForecast) +  Float.valueOf(liability);
                        liabilityFloat =  Float.valueOf(awayForecast) + Float.valueOf(profit);
                        break;
                    }
                    //AWAY - BACK
                    else if (i == 1 && order.getIsBack()) {
                        profitFloat = Float.valueOf(homeForecast) + Float.valueOf(liability);
                        liabilityFloat = Float.valueOf(awayForecast) + Float.valueOf(profit);
                        break;
                    }
                    //AWAY - LAY
                    else if (i == 1 && !order.getIsBack()) {
                        profitFloat = Float.valueOf(homeForecast) + Float.valueOf(profit);
                        liabilityFloat = Float.valueOf(awayForecast) + Float.valueOf(liability);
                        break;
                    }
                    //DRAW - BACK
                    else if (i == 2 && order.getIsBack()) {
                        profitFloat = Float.valueOf(homeForecast) + Float.valueOf(liability);
                        liabilityFloat = Float.valueOf(awayForecast) + Float.valueOf(profit);
                        break;
                    }
                    //DRAW - LAY
                    else if (i == 2 && !order.getIsBack()) {
                        profitFloat = Float.valueOf(homeForecast) + Float.valueOf(profit);
                        liabilityFloat = Float.valueOf(awayForecast) + Float.valueOf(liability);
                        break;
                    }
                }
            }
            for (ArrayList<String> lstForeCast : foreCastDataAfter) {
                if(lstForeCast.contains(order.getSelectionName()) && order.getIsBack()) {
                    Assert.assertEquals(lstForeCast.get(1), String.format("%.2f", profitFloat),
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                } else if (lstForeCast.contains(order.getSelectionName()) && !order.getIsBack()) {
                    Assert.assertEquals(lstForeCast.get(1), String.format("%.2f",liabilityFloat),
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                } else if (!lstForeCast.contains(order.getSelectionName()) && order.getIsBack()) {
                    Assert.assertEquals(lstForeCast.get(1), String.format("%.2f",liabilityFloat),
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                } else {
                    Assert.assertEquals(lstForeCast.get(1), String.format("%.2f", profitFloat),
                            String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
                }
            }
        }
    }

    public void verifyForeCastGoalLineIsCorrect(List<ArrayList<String>> foreCastDataAfter, Order order) {
        String profit = String.format("%.2f",
                order.getProfit(order.getIsBack(), Double.valueOf(order.getOdds()), Double.valueOf(order.getStake())));
        String liability = String.format("%s%.2f", "-",
                order.getLiablity(order.getIsBack(), Double.valueOf(order.getOdds()), Double.valueOf(order.getStake())));
        for (int i = 0; i < foreCastDataAfter.size(); i++) {
            if(i==0) {
                Assert.assertEquals(foreCastDataAfter.get(i).get(1), profit,
                        String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
            } else {
                Assert.assertEquals(foreCastDataAfter.get(i).get(1), liability,
                        String.format("FAILED! Fore cast on selection %s is not correct", order.getSelectionName()));
            }
        }
    }

    public void verifyForeCastInningRunIsCorrect(List<ArrayList<String>> foreCastDataAfter, Order order) {
        String firstScoreRow = String.format("%s - %s", 0, Integer.valueOf(order.getOdds()) - 1);
        String secondScoreRow = String.format("%s+",order.getOdds());
        for (ArrayList<String> lstForeCast : foreCastDataAfter) {
            if(!order.getIsBack()) {
                if(lstForeCast.get(0).contains("-")) {
                    Assert.assertEquals(lstForeCast.get(0), firstScoreRow,"FAILED! ");
                    Assert.assertEquals(lstForeCast.get(1),String.format("-%s", order.getStake()));
                } else {
                    Assert.assertEquals(lstForeCast.get(0), secondScoreRow,"FAILED! ");
                    Assert.assertEquals(lstForeCast.get(1),String.format("%s", order.getStake()));
                }

            } else {
                if(lstForeCast.get(0).contains("-")) {
                    Assert.assertEquals(lstForeCast.get(0), firstScoreRow,"FAILED! ");
                    Assert.assertEquals(lstForeCast.get(1),String.format("%s", order.getStake()));
                } else {
                    Assert.assertEquals(lstForeCast.get(0), secondScoreRow,"FAILED! ");
                    Assert.assertEquals(lstForeCast.get(1),String.format("-%s", order.getStake()));
                }
            }
        }
    }

    public void verifyForeCastHandicapIsCorrect(List<ArrayList<String>> foreCastDataAfter, Order order) {
        String selection = order.getSelectionName();
        String hpdScore = selection.split("[+-]")[1];
        for (int i = 0; i < foreCastDataAfter.size(); i++) {
            if(foreCastDataAfter.get(i).get(0).equalsIgnoreCase("Draw")) {
                if(Float.valueOf(foreCastDataAfter.get(i).get(1)) == Float.valueOf(hpdScore)) {
                    Assert.assertEquals(foreCastDataAfter.get(i).get(1),"0.00","FAILED! ");
                } else {
                    Assert.assertEquals(foreCastDataAfter.get(i).get(1),String.format("-%.2f", Float.valueOf(order.getStake())),"FAILED! ");
                }
            } else if (Float.valueOf(foreCastDataAfter.get(i).get(0).replaceAll("[+-]","")) >= Float.valueOf(hpdScore) && i < 5) {
                Assert.assertEquals(foreCastDataAfter.get(i).get(1),"0.00","FAILED! ");
            } else {
                Assert.assertEquals(foreCastDataAfter.get(i).get(1),String.format("-%.2f", Float.valueOf(order.getStake())),"FAILED! ");
            }
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
        Assert.assertEquals(DoubleUtils.roundUpWithTwoPlaces(originalExposure), Math.floor(calculateExposure * 100) / 100, 0.015, String.format("FAILED! Exposure kept is not correct expected is %s, actual is %s", originalExposure, Math.floor(calculateExposure * 100) / 100));
        Assert.assertEquals(Math.floor(forecast * 100)/ 100, fancyMarket.getMarketLiability(), 0.015, String.format("FAILED! Liability forecast is not correct expected is %s, actual is %s", Math.floor(forecast * 100)/ 100, fancyMarket.getMarketLiability()));
    }

    public void verifySelectedSelectionDisplayOnBetSlip(Event event, int selectionSize, boolean isBack) {
        for (int i = 1; i <= selectionSize; i++) {
            Market market = marketOddControl.getMarket(event, i, isBack);
            String odds = market.getBtnOdd().getText();
            market.getBtnOdd().click();
            Assert.assertEquals(betsSlipContainer.getBet(i-1).getOdds(), odds, "FAILED! Selection is not displayed on Bet Slip");
        }
    }

    public String defineUnmatchedBackOdds(String originalOdds) {
        float convertOdds = Float.valueOf(originalOdds);
        if(convertOdds >= 1.01 && convertOdds < 2) {
            return "2.00";
        } else if (convertOdds >= 2 && convertOdds < 3) {
            return "3.00";
        } else if (convertOdds >= 3 && convertOdds < 4) {
            return "4.00";
        } else if (convertOdds >= 4 && convertOdds < 6) {
            return "6.00";
        } else if (convertOdds >= 6 && convertOdds < 10) {
            return "10.00";
        } else if (convertOdds >= 10 && convertOdds < 20) {
            return "20.00";
        } else if (convertOdds >= 20 && convertOdds < 30) {
            return "30.00";
        } else if (convertOdds >= 30 && convertOdds < 50) {
            return "50.00";
        } else if (convertOdds >= 50 && convertOdds < 100) {
            return "100.00";
        } else {
            return "110.00";
        }
    }

    public String clickOdds(Market market) {
        //try to click 2 times on odds
        for (int i = 0; i < 2; i++) {
            market.getBtnOdd().click();
            if(betsSlipContainer.txtOdds.isDisplayed()) {
                return market.getBtnOdd().getText();
            }
            i++;
        }
        return "";
    }
}
