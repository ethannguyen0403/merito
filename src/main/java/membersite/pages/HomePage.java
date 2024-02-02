package membersite.pages;

import agentsite.pages.report.PS38SportsResultsPage;
import com.paltech.driver.DriverManager;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.sat.Event;
import membersite.pages.casino.CasinoHomePage;
import membersite.pages.casino.CasinoProduct;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.betslipcontainer.BetsSlipContainer;
import membersite.pages.components.minimybetcontainer.MiniMyBetsContainer;
import membersite.pages.components.nextupracingcontainer.NextUpRacingContainer;
import membersite.pages.exchangegames.EGHomePage;
import membersite.pages.popup.BannerPopup;
import membersite.pages.proteus.ProteusHomePage;
import membersite.utils.betplacement.BetUtils;

import java.util.Locale;

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

    public CasinoHomePage openCasinoGame(CasinoProduct product){
        header.openCasinoGame(product);
        CasinoHomePage page = new CasinoHomePage(_type, product);
        return page;
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

}
