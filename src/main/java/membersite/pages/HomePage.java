package membersite.pages;

import com.paltech.driver.DriverManager;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.sat.Event;
import membersite.pages.casino.*;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.betslipcontainer.BetsSlipContainer;
import membersite.pages.components.minimybetcontainer.MiniMyBetsContainer;
import membersite.pages.components.nextupracingcontainer.NextUpRacingContainer;
import membersite.pages.components.ps38preferences.PS38PreferencesPopup;
import membersite.pages.exchangegames.EGHomePage;
import membersite.pages.popup.BannerPopup;
import membersite.pages.popup.MyMarketPopup;
import membersite.pages.proteus.ProteusHomePage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;

import java.util.Locale;

import static baseTest.BaseCaseTest.defineCasinoURL;
import static common.CasinoConstant.*;
import static common.CasinoConstant.SUPERNOWA;
import static common.MemberConstants.PS38;

public class HomePage extends LandingPage {
    public BetsSlipContainer betsSlipContainer;
    public MiniMyBetsContainer myBetsContainer;
    public NextUpRacingContainer nextUpRacingContainer;
    BannerPopup bannerPopup = BannerPopup.xpath("//div[@class='main-banner-popup-content']");

    public HomePage(String types) {
        super(types);
        betsSlipContainer = ComponentsFactory.betsSlipContainerObject(types);
        myBetsContainer = ComponentsFactory.miniMyBetsContainerObject(types);
        nextUpRacingContainer = ComponentsFactory.nextUpRacingContainerObject(types);
    }
    public MyMarketPopup openMyMarket() {
        return header.openMyMarketPopup();
    }
    public MarketPage openMarketInMyMarketPopup(String marketName) {
        MyMarketPopup myMarketPopup = new MyMarketPopup();
        if (!myMarketPopup.tbMyMarkets.isDisplayed()) {
            myMarketPopup = openMyMarket();
        }
        myMarketPopup.navigateToMarket(marketName);
        DriverManager.getDriver().switchToWindow();
        MarketPage marketPage = new MarketPage(_type);
        marketPage.waitMenuLoading();
        return marketPage;
    }
    public MarketPage clickFristNextUpHR() {
        if (!nextUpRacingContainer.hasNextUpHR())
            return null;
        nextUpRacingContainer.clickFristHR();
        return new MarketPage(_type);
    }

    public MarketPage clickFristNextUpGH() {
        if (!nextUpRacingContainer.hasNextUpGR())
            return null;
        nextUpRacingContainer.clickFristGH();
        return new MarketPage(_type);
    }

    public void closeBannerPopup() {
        if (bannerPopup.isDisplayed()) {
            System.out.println("---Step Close banner popup");
            bannerPopup.closeBannerPopup();
        }
    }

    public boolean isMyAccountDisplay() {
        return header.isMyAccountDisplay();
    }

    public boolean isLoginSuccess() {
        return header.isLoginSuccess();
    }

    public boolean isMyAccountContains(String menu) {
        return header.isMyAccountContains(menu);
    }

    public EGHomePage openExchangeGame() {
        header.openExchangeGame();
        EGHomePage exPage = new EGHomePage(_type);
        exPage.gcBaccarat.waitForControlInvisible(2, 1);
        DriverManager.getDriver().switchToFrame(0);
        return exPage;
    }

    public PS38PreferencesPopup openPS38PreferencesPopup(){
      return header.openPS38PreferencesPopup();
    }

    public EvolutionPage openEvolution(){
        return header.openEvolution();
    }
    public LiveDealerAsianPage openLiveDealerAsian(){
        return header.openLiveDealerAsian();
    }
    public LiveDealerEuropeanPage openLiveDealerEuro(){
        return header.openLiveDealerEuro();
    }
    public LotterySlotsPage openLotteryAndSlots(){
        return header.openLotteryAndSlots();
    }
    public PragmaticPage openPragmatic(){
        return header.openPragmatic();
    }
    public SupernowaCasinoPage openSupernowa(){
        return header.openSupernowa();
    }
    public EvolutionWhiteCliffPage openEvolutionWhiteCliff(){
        return header.openEvolutionWhiteCliff();
    }
    public GameHallPage openGameHall(){
        return header.openGameHall();
    }
    public VivoPage openVivo(){
        return header.openVivo();
    }
    public IonPage openIon(){
        return header.openIon();
    }

    public CasinoHomePage openCasinoGameByUrl(String productName){
        String url = defineCasinoURL(_type,MAPPING_CASINO_PRODUCT_SUFFIX_URL.get(productName));
        DriverManager.getDriver().get(url);
        if (productName.equalsIgnoreCase(LIVE_DEALER_EURO)) {
            return new LiveDealerEuropeanPage();
        } else if (productName.equalsIgnoreCase(EVOLUTION)) {
            return new EvolutionPage();
        } else if (productName.equalsIgnoreCase(LOTTERY_AND_SLOTS)) {
            return new LotterySlotsPage();
        } else if (productName.equalsIgnoreCase(PRAGMATIC)) {
            return new PragmaticPage();
        } else if (productName.equalsIgnoreCase(SUPERNOWA)) {
            return new SupernowaCasinoPage();
        }  else if (productName.equalsIgnoreCase(LIVE_DEALER_ASIAN)) {
            return new LiveDealerAsianPage();
        } else if (productName.equalsIgnoreCase(GAME_HALL)) {
            return new GameHallPage();
        } else if (productName.equalsIgnoreCase(VIVO)) {
            return new VivoPage();
        }
        return null;
    }

    public void clickProduct(String product) {
        header.clickProduct(product);
    }

    public ProteusHomePage activePS38Product() {
        header.clickProduct(PS38);
        ProteusHomePage page = new ProteusHomePage(this._type);
        page.waitiFrameLoad();
        DriverManager.getDriver().switchToFrame(0);
        return page;
    }

    public MarketPage clickEvent(Event event) {
        String appName = BetUtils.getAppName();
        if (appName.equals("satsport")) {
            eventContainerControl.clickEvent(event);
        } else
            eventContainerControl.clickEvent(event);
        waitPageLoad();
        return new MarketPage(this._type);
    }

    public Event setEventLink(Event event) {
        return eventContainerControl.setEventLink(event);

    }

    public void waitMenuLoading() {
        leftMenu.waitMenuLoading();
    }


    public String calculateBalance(String balance, String liability) {
        double balanceDoub = Double.valueOf(balance.replaceAll(",", "").toString());
        double liabilityDoub = Double.valueOf(liability.replaceAll(",", "").toString());
        double balanceReturn = balanceDoub - liabilityDoub;
        return String.format(Locale.getDefault(), "%,.2f", balanceReturn);
    }

    /**
     * Recalulate balance after place on a market
     *
     * @param balance
     * @param liabilityBeforePlaceBetOnMarket
     * @param liabilityAfterPlaceOnMarket
     * @return
     */
    public String calculateBalanceAfterPlaceBet(String balance, Double liabilityBeforePlaceBetOnMarket, Double liabilityAfterPlaceOnMarket) {
        double balanceDoub = Double.valueOf(balance.replaceAll(",", "").toString());
      /*  double liabilityDoubBefore =Double.valueOf(liabilityBeforePlaceBetOnMarket.replaceAll(",", "").toString());
        double liabilityDoubAfter =Double.valueOf(liabilityAfterPlaceOnMarket.replaceAll(",", "").toString());*/
        double balanceReturn = balanceDoub - liabilityBeforePlaceBetOnMarket + liabilityAfterPlaceOnMarket;
        return String.format(Locale.getDefault(), "%,.2f", balanceReturn);
    }

    public String getEventIDHasProductData(String product) {
        return eventContainerControl.getEventIDHasMarketData(product);
    }

    public MarketPage clickOnEvent(String eventId) {
        eventContainerControl.clickOnRowofEventID(eventId);
        return new MarketPage(_type);
    }

    public String defineErrorMessage(double stake, double minStake, double maxStake, AccountBalance accountBalance) {
        // double balance = Double.valueOf(accountBalance.getBalance().replaceAll(",", ""));
        if (stake < minStake || stake > maxStake)
            return String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f", minStake), String.format("%,.2f", maxStake), String.format("%,.2f", stake));
        return MemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE;

    }
    public boolean isEventDisplay(String eventName) {
       return eventContainerControl.isEventDisplay(eventName);
    }

    public MarketPage clickEventName(String eventName) {
        eventContainerControl.clickOnRowofEventName(eventName);
        waitPageLoad();
        return new MarketPage(_type);
    }

    public MarketPage clickMenuIndex(int marketIndex) {
        leftMenu.clickMenuIndex(marketIndex);
        return new MarketPage(_type);
    }

    public boolean isProductDisplayed (String productName){
        return header.isProductTabDisplay(productName);
    }

    public LandingPage logout() {
        header.logout();
        return new LandingPage(_type);
    }

    public void verifyUserBalanceAfterPlacePS38(AccountBalance accountBalance){
        //switch to main frame
        DriverManager.getDriver().switchToDefaultContent();
        AccountBalance actualBalance = getUserBalance();
        Assert.assertEquals(actualBalance.getBalance(),accountBalance.getBalance(),"Failed! User Balance is incorrect");
        Assert.assertEquals(actualBalance.getExposure(),accountBalance.getExposure(),"Failed! User Exposure is incorrect");
        //switch to proteus iFrame
        DriverManager.getDriver().switchToFrame(0);
    }

}
