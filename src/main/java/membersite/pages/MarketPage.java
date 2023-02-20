package membersite.pages;


import membersite.controls.funsport.OneClickBettingControl;
import membersite.controls.sat.FancyContainerControl;
import membersite.controls.sat.FancyContainerControlOldUI;
import membersite.controls.sat.WicketBookmakerContainerControl;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Market;
import membersite.pages.components.marketcontainer.MarketContainerControl;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.racingmarketcontainer.RacingMarketContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MarketPage extends HomePage{
    public MarketContainerControl marketOddControl ;
    public RacingMarketContainer racingMarketContainer;
    public OneClickBettingControl oneClickBettingControl ;
    public FancyContainerControl wcFancyContainerControl;
    public FancyContainerControlOldUI odlUIFancyContainerControl;
    public FancyContainerControl centralFancyContainerControl ;
    public FancyContainerControl fancyContainerControl ;

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
    public void activeProduct(String products){
        marketOddControl.activeProduct(products);
    }

    public Market getBookmakerMarketInfo(BookmakerMarket bookmakerMarket, boolean isBack){
        WicketBookmakerContainerControl wcBookmakerContainerControl;
        switch (bookmakerMarket.getMaketType()){
            case "WICKET_BOOKMAKER":
                wcBookmakerContainerControl = WicketBookmakerContainerControl.xpath("//span[text()='Wicket Bookmaker']//ancestor::div[contains(@class,'fancy-container')]//wicket-bookmarker-odds//div[contains(@class,'table-odds')]");
                return wcBookmakerContainerControl.getBookmakerMarketInfo(bookmakerMarket,isBack);
            default :
                wcBookmakerContainerControl = WicketBookmakerContainerControl.xpath("//span[text()='Manual Odds']//ancestor::div[contains(@class,'fancy-container')]//central-bookmarker-odds//div[contains(@class,'table-odds')]");
                return wcBookmakerContainerControl.getBookmakerMarketInfo(bookmakerMarket,isBack);
        }
    }

    public List<ArrayList> getBookmakerMiniMyBet(){
        return myBetsContainer.getBookmakerMatchBets();
    }

    public FancyMarket getFancyMarketInfo(FancyMarket fcMarket){
       return marketOddControl.getFancyMarketInfo(fcMarket);
    }

    public void addFancyOdds(FancyMarket fcMarket, boolean isBack){
        switch (fcMarket.getMaketType()){
            case "WICKET_FANCY":
                wcFancyContainerControl.clickFancyOdds(fcMarket,isBack);
                return;
            case "CENTRAL_FANCY":
                centralFancyContainerControl.clickFancyOdds(fcMarket,isBack);
                return;
            default:
                fancyContainerControl.clickFancyOdds(fcMarket,isBack);
                return;
        }

    }

    public void placeFancy(FancyMarket fancyMarket, boolean isBack, String stake){
        addFancyOdds(fancyMarket,isBack);
        betsSlipContainer.placeBet(stake);
    }

    public Wager defineFamcyWager(FancyMarket fcMarket,boolean isBack,double stake)
    {
        String betType = isBack?"BACK":"LAY";
        double odds = isBack ? fcMarket.getOddsYes(): fcMarket.getOddsNo();
        int payout = isBack? fcMarket.getRateYes(): fcMarket.getRateNo();
        String runnerName = isBack?"Yes [L]" : "No [K]";
        return new Wager.Builder()
                .betType(betType)
                .marketName(fcMarket.getMarketName())
                .odds(odds)
                .payout(payout)
                .stake(stake)
                .runnerName(runnerName)
                .build();
    }

    public Wager defineWager(Market market,boolean isBack,double stake, double handicap)
    {
        String betType = isBack?"BACK":"LAY";
        double odds =  Double.parseDouble(market.getOdds());
        String runnerName =market.getSelectionName().trim();
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

    public double liabilityExchangeMarket(List<Wager> normalMarket){
        if(Objects.isNull(normalMarket))
            return 0;
        List<Integer> oddsRange = oddRange(normalMarket);
        List<Double> lstForecastAWager = getForeCastOnWager(normalMarket.get(0), oddsRange);
        if(normalMarket.size()>1){
            for(int i = 1; i< normalMarket.size();i++){
                lstForecastAWager = sumData(lstForecastAWager , getForeCastOnWager(normalMarket.get(i), oddsRange));
            }
        }
        return Collections.max(lstForecastAWager) ;
    }

    public double liabilityFCMarket(List<Wager> fcWagerList){
        if(Objects.isNull(fcWagerList))
            return 0;
        List<Integer> oddsRange = oddRange(fcWagerList);
        List<Double> lstForecastAWager = getForeCastOnWager(fcWagerList.get(0), oddsRange);
        List<Double> lstLiability = new ArrayList<>();
        if(fcWagerList.size()>1){
            for(int i = 1; i< fcWagerList.size();i++){
                lstLiability = sumData(lstForecastAWager , getForeCastOnWager(fcWagerList.get(i), oddsRange));
            }
        }
        /* return Collections.max(lstLiability);*/
        double liability = Collections.max(lstLiability);
        if(Collections.max(lstForecastAWager)!=0)
            return liability *(-1);
        return liability;
    }

    private List<Double> sumData(List<Double> lst1, List<Double> lst2){
        List<Double> lstResult = new ArrayList<>();
        for(int i = 0; i < lst1.size(); i++){
            lstResult.add(lst1.get(i) + lst2.get(i));
        }
        return lstResult;
    }

    private List<Double> getForeCastOnWager(Wager wg, List<Integer> range){
        List<Double> lstLiabilityFCForecast = new ArrayList<>();
        wg.getOdds();
        wg.getPayout();
        wg.getStake();
        //TODO: Incase payout is not 100 -> forecast is incorrect -> handle script in this case
        for (int odds: range){
            if(wg.getBetType().equalsIgnoreCase("BACK")) {
                if (odds >= wg.getOdds())
                    lstLiabilityFCForecast.add(wg.getProfit());
                else
                    lstLiabilityFCForecast.add(wg.getLiability() );
            }
            else {
                if (odds >= wg.getOdds())
                    lstLiabilityFCForecast.add(wg.getLiability());
                else
                    lstLiabilityFCForecast.add(wg.getProfit());
            }
        }
        return lstLiabilityFCForecast;
    }
    private List<Integer> oddRange(List<Wager> fcWagerList){
        List<Integer> oddsRangeLst = new ArrayList<>();
        int maxIndex = fcWagerList.size();
        oddsRangeLst.add(0);
        for(Wager wg : fcWagerList){
            oddsRangeLst.add((int)wg.getOdds());
        }
        oddsRangeLst.add(fcWagerList.size()-1,9999);

        Collections.sort(oddsRangeLst);
        int min = oddsRangeLst.get(0) -1;
        int max = oddsRangeLst.get(maxIndex)+1;
        oddsRangeLst.set(0, min);
        oddsRangeLst.set(maxIndex, max);
        return oddsRangeLst;
    }
    public List<ArrayList> getFancyMiniMyBet(){
        return myBetsContainer.getMatchedFancyInMiniMyBet();
    }

}
