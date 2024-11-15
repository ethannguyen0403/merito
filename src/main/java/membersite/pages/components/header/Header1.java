package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import membersite.objects.AccountBalance;
import membersite.pages.*;
import membersite.pages.casino.*;
import membersite.pages.components.changepasswordpopup.ChangePasswordPopup;
import membersite.pages.components.ps38preferences.PS38PreferencesPopup;
import membersite.pages.components.signinform.SignInPopup;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;
import membersite.pages.popup.MyMarketPopup;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class Header1 {
    public String getName() {
        return "This is header";
    }

    public void login(String username, String password, boolean skipByDefault) {
    }

    public void signin(String username, String password, String email, String currency, String phone) {
    }

    public SignInPopup openSigninPopup() {
        return null;
    }
    public String loginInvalid(String username, String password) {
        return "";
    }

    public UnderageGamblingPopup clickLogin() {
        return new UnderageGamblingPopup();
    }

    public boolean isMyAccountDisplay() {
        return true;
    }

    public boolean isLoginSuccess() {
        return true;
    }

    public boolean isMyAccountContains(String menu) {
        return false;
    }

    public void openMyAccount(){
    }

    public AccountStatementPage openAccountStatement(String type) {
        return new AccountStatementPage(type);
    }

    public MyBetsPage openMyBets(String type) {
        return new MyBetsPage(type);
    }

    public ProfitAndLossPage openProfitAndLoss(String type) {
        return new ProfitAndLossPage(type);
    }

    public ChangePasswordPopup openChangePasswordPopup() {
        return new ChangePasswordPopup();
    }

    public MyLastLoginPage openMyLastLogins(String type){
        return new MyLastLoginPage(type);
    }

    public PS38PreferencesPopup openPS38PreferencesPopup(){
        return new PS38PreferencesPopup();
    }

    public void openExchangeGame() {
        return;
    }

//    public void openCasinoGame(CasinoProduct product){return;}
    public LiveDealerAsianPage openLiveDealerAsian(){return null;}
    public LiveDealerEuropeanPage openLiveDealerEuro(){return null;}
    public EvolutionPage openEvolution(){return null;}
    public LotterySlotsPage openLotteryAndSlots(){return null;}
    public PragmaticPage openPragmatic(){return null;}
    public SupernowaCasinoPage openSupernowa(){return null;}
    public EvolutionWhiteCliffPage openEvolutionWhiteCliff(){return null;}
    public GameHallPage openGameHall(){return null;}
    public VivoPage openVivo(){return null;}
    public IonPage openIon(){return null;}
    public void clickProduct(String product) {
    }

    public void clickMainMenu(String menu) {
    }

    public void clickLogo() {
    }

    public void waitSpinLoad() {
    }

    public String getLogoSrc() {
        return "";
    }

    public boolean isLeftMenuIcondisplay() {
        return false;
    }

    public void clickLeftMenuIcon() {
    }

    public SportPage navigateSportMenu(String pageName, String brand) {
        return new SportPage(brand);
    }

    public InPlayPage navigateInPlayPage(String brand){
        return new InPlayPage(brand);
    }

    public FavouritesPage navigateFavouritesPage(String brand){
        return new FavouritesPage(brand);
    }

    public RacingPage navigateRacing(String pageName, String brand) {
        return new RacingPage(brand);
    }

    public void openMyMarketPopup() {
    }

    public AccountBalance getUserBalance() {
        return null;
    }

    public AccountBalance getUserCashBalance() {
        return null;
    }

    public boolean isProductActive(String productName) {
        return false;
    }

    public String getBalanceLabel() {
        return "";
    }

    public String getLiabilityLabel() {
        return "";
    }

    public String getMyBetsLabel() {
        return "";
    }

    public String getMyMarketLabel() {
        return "";
    }

    public String getMyAccountLabel() {
        return "";
    }

    public String getLiabilityTextColor() {
        return "";
    }

    public void navigateToMarketIndexOnMarketPopup(int index) {
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
        return "";
    }

    public String getMarqueeMessage() {
        return "";
    }

    public boolean isProductTabDisplay(String productName) {
        return false;
    }

    public void logout() {
        return;
    }

    public PaymentPage openDepositPage(String brand) {
        return new PaymentPage(brand);
    }

    public boolean isDepositButtonDisplayed() {return false;}

    public void verifyPageOpenInNewTab(String pageName, boolean isSwitchToDefaultTab) {
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains(pageName),
                String.format("FAILED! Page %s is not redirect correct!", pageName));
        Set<String> windows = DriverManager.getDriver().getWindowHandles();
        Iterator<String> windowsIter = windows.iterator();
        String parentWindow = windowsIter.next();
        Assert.assertTrue(windows.size() == 2, "FAILED! Account statement is not opened in new tab");
        if (isSwitchToDefaultTab) {
            DriverManager.getDriver().close();
            DriverManager.getDriver().switchToWindow(parentWindow);
        }
    }
    public void verifyHeaderUI(){}
}
