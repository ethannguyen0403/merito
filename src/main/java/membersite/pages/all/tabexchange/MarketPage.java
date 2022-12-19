package membersite.pages.all.tabexchange;

import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import common.MemberConstants;
import membersite.controls.funsport.MyBetControl;
import membersite.controls.funsport.OneClickBettingControl;
import membersite.controls.sat.*;
import membersite.objects.Wager;
import membersite.objects.funsport.Odd;
import membersite.objects.funsport.SelectedOdd;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Market;
import membersite.pages.all.tabexchange.components.popups.RulePopup;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class MarketPage extends SportPage {

    public MarketContainerControl marketContainerControl = MarketContainerControl.xpath("//div[contains(@class,'highlight-page market')]");
    public Label lblTitle = Label.xpath("//div[@class='sports star-favourites-container']//span[@class='title favorites-title ']");
    public Icon icFavorite = Icon.xpath("//i[contains(@class,'multi-market add-multi-market glyphicon')]");
    public Label lblMarketStartTime = Label.xpath("//div[@class='sports star-favourites-container']//div[@id='market-date']");
   // public membersite.controls.sat.BetSlipControl betSlipControlSAT = membersite.controls.sat.BetSlipControl.xpath("//div[contains(@class,'container-bet-slip')]");
  //  public BetSlipControl betSlipControl = BetSlipControl.xpath("//div[@id='betslip-container']");
    public MyBetControl myBetControl = MyBetControl.xpath("//div[@id='openbets']");
    public OneClickBettingControl oneClickBettingControl = OneClickBettingControl.xpath("//div[@id='one-click-betting']");
    public FancyContainerControl wcFancyContainerControl = FancyContainerControl.xpath("//span[text()='Wicket Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    public FancyContainerControlOldUI odlUIFancyContainerControl = FancyContainerControlOldUI.xpath("//div[@id='fair-27-fancy']");
    public FancyContainerControl centralFancyContainerControl = FancyContainerControl.xpath("//span[text()='Central Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    public FancyContainerControl fancyContainerControl = FancyContainerControl.xpath("//span[text()='Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    public membersite.controls.sat.BetSlipControl betSlipControlSAT= membersite.controls.sat.BetSlipControl.xpath("//app-bet-slip");
    public membersite.controls.sat.MyBetControl myBetControlSAT = membersite.controls.sat.MyBetControl.xpath("//app-open-bets");
    private Tab tabWicketFancy =Tab.xpath(String.format("//span[text()='%s']", MemberConstants.WICKET_FANCY_TITILE));
    private Tab tabCentralFancy =  Tab.xpath(String.format("//span[text()='%s']", MemberConstants.CENTRAL_FANCY_TITILE));
    private Tab tabManualOdds = Tab.xpath(String.format("//span[text()='%s']", MemberConstants.CENTRAL_BOOKMAKER_TITLE));
    private Tab tabWicketBookmaker = Tab.xpath(String.format("//span[text()='%s']", MemberConstants.WICKET_BOOKMAKER_TITLE));
    private Tab tabFancy = Tab.xpath(String.format("//div[contains(@class,'fancy-container')]//span[text()='%s']", MemberConstants.FANCY_TITILE));
    public Label oddsSpinIcon = Label.xpath("//div[@id='odds-content']//div[@class='snipper-content']");
    private Button btnRule = Button.xpath("//app-market//span[@class='market-rules-span']");

    public void clickOdd(Odd odd) {
        if(Objects.isNull(odd)){
            System.out.println(String.format("DEBUG: Please check odds before place bet on market %s and selection %s",odd.getEventName(),odd.getSelectedTeam()));
        }
        System.out.println("Debug: Click on Odd of the market: "+ odd.getEventName() +"Selection "+odd.getSelectedTeam() +"Odd Type"+odd.getOdd().getText());
        odd.getOdd().click();
    }
    /**
     * Get the market info: sport id, event id, market id, market type
     * @return
     */

    public List<ArrayList<String>> forecastLstBasedMatchedBetFromAPI(String sportid, String eventID, String marketid, String marketType,List<String> lstSelection){
        List<Wager> lstWager = BetUtils.getMatchedOpenBet(sportid,eventID,marketid,marketType);
        return BetUtils.calculateForecast(BetUtils.getProfitandLiabilityBySelection(lstWager,lstSelection));
    }

    public boolean verifyForecast(String currency,List<ArrayList<String>> actualForecast, List<ArrayList<String>> expectedForecast){

        return true;
    }

    public void activeProduct(String products){
        switch (products){
            case "Wicket Fancy":
                tabWicketFancy.click();
                return;
            case "Central Fancy":
                tabCentralFancy.click();
                return;
            case "Manual Odds":
                tabManualOdds.click();
                return;
            case "Fancy":
                tabFancy.click();
                return;
            default:
                tabWicketBookmaker.click();
                return;
        }
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
        betSlipControlSAT.placeBet(stake);
    }

    public String getMinMaxLable(FancyMarket fcMarket){
        switch (fcMarket.getMaketType()){
            case "WICKET_FANCY":
                return wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
            case "CENTRAL_FANCY":
                return centralFancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
            default:
                return fancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
        }
    }

    public FancyMarket getFancyMarketInfo(FancyMarket fcMarket){
        switch (fcMarket.getMaketType()){
            case "WICKET_FANCY":
                return wcFancyContainerControl.getFancyMarketInfo(fcMarket);
            case "CENTRAL_FANCY":
                return centralFancyContainerControl.getFancyMarketInfo(fcMarket);
            default:
                return fancyContainerControl.getFancyMarketInfo(fcMarket);
        }
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

    public String getMinMaxOFFancyMarket(String minMax,boolean isMinStake){
      //  String[] minMax = wcFancyContainerControl.getMinMaxOFFancyMarket(fcMarket);
        String[] minMaxArr =  minMax.split("/");
        if(isMinStake)
            return minMaxArr[0].trim().replaceAll(",","");
        return minMaxArr[1].trim().replaceAll(",","");

    }

    public List<ArrayList> getFancyMiniMyBet(){
        return myBetControlSAT.fancyMiniMyBet.getMatchBets();
    }

    public List<ArrayList<String>> getFancyMiniMyBetOldUI(){
        return myBetControloldUI.getFancyWager();
    }

    public List<ArrayList> getBookmakerMiniMyBet(){
        return myBetControlSAT.bookMakerMiniMyBet.getMatchBets();
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
    private FancyContainerControlOldUI get27FancyMarketControl(String marketName){
        String fcXpath = "//div[@id='fair-27-fancy']//div[@class='FAIR_27FANCY'][%s]";
        FancyContainerControlOldUI fancyContainerControlOldUI;
        String fcMarketName;
        int i = 1;
        while (true){
            fancyContainerControlOldUI = FancyContainerControlOldUI.xpath(String.format(fcXpath,i));
            if(!fancyContainerControlOldUI.isDisplayed()) {
                System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container",marketName));
               return null;
            }
            if (fancyContainerControlOldUI.getMarketName().equalsIgnoreCase(marketName)) {
                return fancyContainerControlOldUI;
            }
            i++;
        }
    }
    public FancyMarket setF27ancyMarketInfo(FancyMarket fcMarket){
        FancyContainerControlOldUI fancyContainerControlOldUI =  get27FancyMarketControl(fcMarket.getMarketName());
        return fancyContainerControlOldUI.setFancyMarketInfo(fcMarket);
    }
    public void place27Fancy(FancyMarket fancyMarket,boolean isBack, String stake){
        if(isBack)
            fancyMarket.getBtnYes().click();
        else
            fancyMarket.getBtnNo().click();
        List<SelectedOdd> lstSelectedOdd = betSlipControlOldUI.getSelectedOdds27Fancy(1);
        SelectedOdd selectedOdd = lstSelectedOdd.get(0);
        betSlipControlOldUI.placeBet27Fancy(selectedOdd,stake);
    }

    public boolean verifyOddsIsClickAbleAsBetableStaus(Market market, boolean isBetable){
        market.getBtnOdd().click();
        Label btnOdds = market.getBtnOdd();
        if(isBetable){
            if(btnOdds.isEnabled())
                return true;
            return false;
        }
        else {
            if(!btnOdds.isEnabled())
                return true;
            return false;
        }
    }

    public RulePopup openRules(){
        btnRule.click();
        return new RulePopup();
    }
}
