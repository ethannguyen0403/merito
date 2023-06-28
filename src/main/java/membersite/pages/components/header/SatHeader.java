package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.objects.AccountBalance;
import membersite.pages.AccountStatementPage;
import membersite.pages.MyBetsPage;
import membersite.pages.ProfitAndLossPage;
import membersite.pages.SportPage;
import membersite.pages.components.signinform.SATSignInPopup;
import membersite.pages.components.signinform.SignInPopup;
import membersite.pages.popup.MyMarketPopup;
import membersite.pages.components.changepasswordpopup.SATChangePasswordPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SatHeader extends Header {
    private TextBox txtUsername = TextBox.name("username");
    private TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    Image imgLogo = Image.xpath("//a[contains(@class,'logo')]");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//div[contains(@class,'account d-block')]","","//ul[contains(@class,'dropdown-menu')]//li");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");
    private Label imgSpinner = Label.xpath("//div[@class=lds-spinner']");
    private Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    private Menu menuSports = Menu.xpath("//app-sport-menu-bar//ul[@class='navbar-nav']//a");
    private String sportMenuXpath = "//a//div[contains(text(),'%s')]";
    private String sportMenuOldUIXpath = "//a[contains(@data-sport-name,'%s')]";
    private Link lnkMyMarkets = Link.xpath("//div[contains(@class,'account d-none')]");
    private Label lblBalanceTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='title']");
    private Label lblBalanceCurrency = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='bal-val'][1]");
    private Label lblBalance = Label.xpath("//app-top-panel//div[contains(@class,'profit-group d-none')]//div[@class='balance'][2]//span[@class='bal-val']");
    private Label lblLiabilityCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[contains(@class,'lia-val')][1]");
    private Label lblLiability = Label.xpath("(//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')])[1]/span[@class='lia-val'][1]");
    String productMenuXpath = "//div[@class='product-tab-wrapper' or @class='mod-main-navigation ']//a[text()=' %s ' or text() = '%s'] | .//div[text()=' %s ']";
    Label lblMarquee = Label.xpath("//div[@class='marquee']");
    // Before Login
    public SATUnderageGamblingPopup clickLogin() {
        if(btnLogin.isDisplayed()){
            btnLogin.click();
        }
        return new SATUnderageGamblingPopup();

    }

    private SATLoginPopup openLoginPopup(){
        SATUnderageGamblingPopup satUnderageGamblingPopup = clickLogin();
        return satUnderageGamblingPopup.clickConfirmation();
    }

    private SATSignInPopup openSigninPopup(){
        btnJoinNow.click();
        return new SATSignInPopup();
    }
    @Override
    public void login(String username, String password, boolean skipByDefault){
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password,skipByDefault);
    }
    @Override
    public void signin(String username, String password, String email, String currency, String phone){
        SATSignInPopup signInPopup = openSigninPopup();
        signInPopup.signin(username, password,email, currency, phone);
    }

    public String loginInvalid(String username, String password){
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password,false);
       return loginPopup.lblErrorMessage.getText();
    }
    // AfterLogin
    @Override
     public boolean isMyAccountDisplay(){
        return ddmAccount.isDisplayed();
    }

    @Override
    public boolean isMyAccountContains(String menu){
        return ddmAccount.isContainSubmenu(menu);
    }

    public AccountStatementPage openAccountStatement(String type){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        DriverManager.getDriver().switchToWindow();
        AccountStatementPage page = new AccountStatementPage(type);
        page.accountStatementContainer.waitLoadReport();
        return page;
    }

    public MyBetsPage openMyBets(String type){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"));
        DriverManager.getDriver().switchToWindow();
        MyBetsPage page = new MyBetsPage(type);
        page.myBetsContainer.waitLoadReport();
        return page;
    }

    public ProfitAndLossPage openProfitAndLoss(String type){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"));
        DriverManager.getDriver().switchToWindow();
        ProfitAndLossPage page = new ProfitAndLossPage(type);
        page.profitAndLossContainer.waitLoadReport();
        return page;
    }

    public SATChangePasswordPopup openChangePasswordPopup(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new SATChangePasswordPopup();
    }

    public void openExchangeGame(){
        tabExchangeGames.click();
    }

    public void clickLogo(){imgLogo.click();}

    public void waitSpinLoad(){
        imgSpinner.waitForControlInvisible(1,2);
    }
    public String getLogoSrc(){return imgLogo.getAttribute("src");}
    public boolean isLeftMenuIcondisplay(){return imgLeftMenu.isDisplayed();}

    /**
     * This is open main sport menu with the corresponding page
     * @param pageName : ex: Soccer, Home, In-Play, Basketball, Cricket
     * @return
     */
    public SportPage navigateSportMenu(String pageName, String brand) {
        Menu menu = Menu.xpath(String.format(sportMenuXpath, pageName));
        if(!menu.isDisplayed(5)){
            System.out.println(String.format("There is no %s menu display", pageName));
            return null;
        }
        menu.click();
        return new SportPage(brand);
    }
    public MyMarketPopup openMyMarketPopup(){
        lnkMyMarkets.click();
        return new MyMarketPopup();
    }

    public AccountBalance getUserBalance() {
        lblBalance.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblBalance.getText())
                .exposure(lblLiability.getText())
                .build();
    }


    public boolean isProductActive(String productName)
    {
        for (String s: translateProductsActive(BetUtils.getProductInfo())) {
            if(s.equals(productName))
                return true;
        }
        return false;
    }
    private List<String> translateProductsActive(List<String> productCode)
    {
        if(Objects.nonNull(productCode))
        {
            List<String> productTranslate = new ArrayList<>();
            for (String s : productCode) {
                productTranslate.add(MemberConstants.HomePage.PRODUCTS.get(s));
            }
            return productTranslate;
        }else{
            System.out.println("There is no product active!");
            return null;
        }
    }

    public String getLiabilityTextColor(){return lblLiability.getColour("color");}

    public void navigateToMarketIndexOnMarketPopup(int index){

    }

    /**
     * Recalulate balance after place on a market
     * @param balance
     * @param liabilityBeforePlaceBetOnMarket
     * @param liabilityAfterPlaceOnMarket
     * @return
     */
    public String calculateBalanceAfterPlaceBet(String balance, Double liabilityBeforePlaceBetOnMarket, Double liabilityAfterPlaceOnMarket) {
        double balanceDoub =Double.valueOf(balance.replaceAll(",", "").toString());
      /*  double liabilityDoubBefore =Double.valueOf(liabilityBeforePlaceBetOnMarket.replaceAll(",", "").toString());
        double liabilityDoubAfter =Double.valueOf(liabilityAfterPlaceOnMarket.replaceAll(",", "").toString());*/
        double balanceReturn = balanceDoub - liabilityBeforePlaceBetOnMarket + liabilityAfterPlaceOnMarket;
        return String.format(Locale.getDefault(),"%,.2f",balanceReturn);
    }

    public String getMarqueeMessage() {
        String announcementMessage = "";
        if(lblMarquee.isDisplayed()){
            announcementMessage = lblMarquee.getText();
        }
        return announcementMessage;
    }

    public boolean isProductTabDisplay(String productName)
    {
        String productTab = productName;
        if (!productName.equals(MemberConstants.HomePage.PRODUCTS.get("SUPER_SPADE")) && !productName.equals(MemberConstants.HomePage.PRODUCTS.get("EZUGI"))) {
            if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("EXCHANGE"))) {
                productTab = "Exchange";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("DIGIENT"))) {
                productTab = "Lottery & Slots";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("EXCH_GAMES"))) {
                productTab = "Exchange Games";
            }
        } else {
            productTab = "Live Dealer";
        }

        return Tab.xpath(String.format(this.productMenuXpath, productTab, productTab, productTab)).isDisplayed();
    }
    public void logout(){ ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"));   }

}



