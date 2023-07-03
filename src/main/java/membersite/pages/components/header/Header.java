package membersite.pages.components.header;

import com.paltech.element.common.Tab;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.pages.*;
import membersite.pages.exchangegames.EGHomePage;
import membersite.pages.popup.MyMarketPopup;
import membersite.pages.components.changepasswordpopup.ChangePasswordPopup;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;

import java.util.Locale;

public class Header {
     public String getName(){
         return "This is header";
     }

    public void login(String username, String password,boolean skipByDefault){
    }
    public void signin(String username, String password, String email, String currency, String phone){
    }
    public String loginInvalid(String username, String password){
       return "";
    }

    public UnderageGamblingPopup clickLogin(){
         return new UnderageGamblingPopup();
    }

    public boolean isMyAccountDisplay(){
         return true;
    }

    public boolean isMyAccountContains(String menu){
        return false;
    }

    public AccountStatementPage openAccountStatement(String type){
       return new AccountStatementPage(type);
    }

    public MyBetsPage openMyBets(String type){
        return new MyBetsPage(type);
    }

    public ProfitAndLossPage openProfitAndLoss(String type){
        return new ProfitAndLossPage(type);
    }

    public ChangePasswordPopup openChangePasswordPopup(){
       return new ChangePasswordPopup();
    }

    public void openExchangeGame(){return ;}
    public void clickProduct(String product){    }
    public void clickMainMenu(String menu){}
    public void clickLogo(){}
    public void waitSpinLoad(){
    }
    public String getLogoSrc(){return "";}
    public boolean isLeftMenuIcondisplay(){return false;}

    public void clickLeftMenuIcon(){}

    public SportPage navigateSportMenu(String pageName, String brand) {
         return new SportPage(brand);
    }
    public RacingPage navigateRacing(String pageName, String brand) {
        return new RacingPage(brand);
    }

    public MyMarketPopup openMyMarketPopup(){
        return new MyMarketPopup();
    }

    public AccountBalance getUserBalance() {
        return null;
    }
    public boolean isProductActive(String productName)
    {
        return false;
    }

    public String getBalanceLabel(){return "";}
    public String getLiabilityLabel(){return "";}

    public String getLiabilityTextColor(){return "";}
    public void navigateToMarketIndexOnMarketPopup(int index){}
    /**
     * Recalulate balance after place on a market
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

    public boolean isProductTabDisplay(String productName)
    {
        return false;
    }

    public void logout(){return;    }

}
