package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.objects.AccountBalance;
import membersite.pages.*;
import membersite.pages.AccountStatementPage;
import membersite.pages.MyBetsPage;
import membersite.pages.ProfitAndLossPage;
import membersite.pages.SportPage;
import membersite.pages.casino.*;
import membersite.pages.components.changepasswordpopup.FairChangePasswordPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.ps38preferences.PS38PreferencesPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;
import membersite.pages.popup.MyMarketPopup;


import static common.CasinoConstant.LIVE_DEALER_TEXT;
import static common.CasinoConstant.MAPPING_CASINO_PRODUCT_UI;

public class Fair999Header extends Header1 {
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    Image imgLogo = Image.xpath("//a[contains(@class,'logo')]");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//div[contains(@class,'account d-block')]", "", "//ul[contains(@class,'dropdown-menu')]//li");
    public Label lblMyAccount = Label.xpath("//div[contains(@class,'account d-block')]//span[text()='My Account']");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')] | //a[contains(text(),' EXCHANGE GAMES ')]");
    private Label imgSpinner = Label.xpath("//div[@class='lds-spinner']");
    private Label lblMyBet = Label.xpath("//a[contains(@class,'menu-mybet')]");
    private Link lnkMyMarkets = Link.xpath("//div[contains(@class,'header-content-info')]//span[text()='My Markets']");
    private Label lblBalanceTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[@class='balance'][1]/span[@class='title']");
    private Label lblBalanceCurrency = Label.xpath("//app-top-panel//div[@class='balance'][1]//span[@class='bal-val'][1]");
    private Label lblBalance = Label.xpath("//app-top-panel//div[@class='balance'][1]//span[@class='bal-val'][2]");
    private Label lblLiabilityTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[contains(@class,'title')]");
    private Label lblLiabilityCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[contains(@class,'lia-val')][1]");
    private Label lblLiability = Label.xpath("(//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')])[1]/span[@class='lia-val'][1]");
    private String sportMenuXpath = "//a//div[contains(text(),'%s')]";
    String productMenuXpath = "//app-product-tab-v2//a[(text()='%s')]";
    String productLiveDealerXpath = "//app-live-dealer//a[text()='%s']";
    // Before Login
    public SATUnderageGamblingPopup clickLogin() {
        if (btnLogin.isDisplayed()) {
            btnLogin.click();
        }
        return new SATUnderageGamblingPopup();

    }

    private SATLoginPopup openLoginPopup() {
        SATUnderageGamblingPopup satUnderageGamblingPopup = clickLogin();
        return satUnderageGamblingPopup.clickConfirmation();
    }

    public void clickLeftMenuIcon() {
        imgLeftMenu.click();
    }

    /**
     * This is open main sport menu with the corresponding page
     *
     * @param pageName : ex: Soccer, Home, In-Play, Basketball, Cricket
     * @return
     */
    public SportPage navigateSportMenu(String pageName, String brand) {
        clickHeaderMenu(pageName);
        return new SportPage(brand);
    }

    @Override
    public InPlayPage navigateInPlayPage(String brand) {
        Label.xpath("//a//*[contains(text(),'In-Play')]").click();
        return new InPlayPage(brand);
    }

    private void clickHeaderMenu(String sportMenu){
        Menu menu = Menu.xpath(String.format(sportMenuXpath, sportMenu));
        if (!menu.isDisplayed(5)) {
            System.out.println(String.format("There is no %s menu display", sportMenu));
            return;
        }
        menu.click();
    }
    public RacingPage navigateRacing(String pageName, String brand) {
        clickHeaderMenu(pageName);
        return new RacingPage(brand);
    }

    @Override
    public void login(String username, String password, boolean skipByDefault) {
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password, skipByDefault);
    }

    public String loginInvalid(String username, String password) {
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password, false);
        return loginPopup.lblErrorMessage.getText();
    }
    public boolean isLeftMenuIcondisplay() {
        return imgLeftMenu.isDisplayed();
    }

    // AfterLogin
    @Override
    public boolean isMyAccountDisplay() {
        return ddmAccount.isDisplayed();
    }
    @Override
    public boolean isLoginSuccess() {
        return lblMyAccount.isDisplayed();
    }
    @Override
    public boolean isMyAccountContains(String menu) {
        return ddmAccount.isContainSubmenu(menu);
    }

    @Override
    public void openMyAccount() {
        ddmAccount.click();
    }

    public membersite.pages.AccountStatementPage openAccountStatement(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.AccountStatementPage page = new AccountStatementPage(type);
        page.accountStatementContainer.waitLoadReport();
        page.waitLoadReport();
        return page;
    }

    @Override
    public MyLastLoginPage openMyLastLogins(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Last Logins"));
        DriverManager.getDriver().switchToWindow();
        return new MyLastLoginPage(type);
    }

    @Override
    public PS38PreferencesPopup openPS38PreferencesPopup() {
        DriverManager.getDriver().switchToParentFrame();
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("PS38 Preferences"));
        return new PS38PreferencesPopup();
    }

    public membersite.pages.MyBetsPage openMyBets(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.MyBetsPage page = new MyBetsPage(type);
        page.myBetsContainer.waitLoadReport();
        return page;
    }

    public membersite.pages.ProfitAndLossPage openProfitAndLoss(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.ProfitAndLossPage page = new ProfitAndLossPage(type);
        page.profitAndLossContainer.waitLoadReport();
        return page;
    }

    public FairChangePasswordPopup openChangePasswordPopup() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        try {
            //wait for locator visible in viewport
            Thread.sleep(300);
        }catch (Exception e){
        }
        return new FairChangePasswordPopup();
    }


    public AccountBalance getUserBalance() {
        waitSpinLoad();
        lblBalance.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblBalance.getText())
                .exposure(lblLiability.getText())
                .build();
    }

    public void openExchangeGame() {
        tabExchangeGames.click();
    }

//    @Override
//    public void openCasinoGame(CasinoProduct product) {
//        if (product.equals(CasinoProduct.EVOLUTION) || product.equals(CasinoProduct.LIVE_DEALER_ASIAN)) {
//            clickProduct("Live Dealer");
//            if(product.equals(CasinoProduct.LIVE_DEALER_ASIAN)){
//                clickProduct(product.toString());
//                return;
//            }
//            clickProduct("European Room");
//            Tab.xpath(String.format("//div[contains(@class, 'european-room')]//span[text()='%s']", product.toString())).click();
//            return;
//        }
//        clickProduct(product.toString());
//    }

    @Override
    public EvolutionPage openEvolution() {
        clickProduct(LIVE_DEALER_TEXT);
        Label.xpath(String.format("//a[contains(@class, 'menu-item-link') and contains(., '%s')]", MAPPING_CASINO_PRODUCT_UI.get("EVOLUTION"))).click();
        return new EvolutionPage();
    }
    @Override
    public LiveDealerAsianPage openLiveDealerAsian() {
        clickProduct(LIVE_DEALER_TEXT);
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("LIVE_DEALER_ASIAN"));
        return new LiveDealerAsianPage();
    }

    @Override
    public LiveDealerEuropeanPage openLiveDealerEuro() {
        clickProduct(LIVE_DEALER_TEXT);
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("LIVE_DEALER_EUROPEAN"));
        return new LiveDealerEuropeanPage();
    }

    @Override
    public LotterySlotsPage openLotteryAndSlots() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("LOTTERY_SLOTS"));
        return new LotterySlotsPage();
    }
    @Override
    public PragmaticPage openPragmatic() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("PRAGMATIC"));
        return new PragmaticPage();
    }
    @Override
    public SupernowaCasinoPage openSupernowa() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("SUPERNOWA_CASINO"));
        return new SupernowaCasinoPage();
    }

    @Override
    public GameHallPage openGameHall() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("GAME_HALL"));
        GameHallPage gameHallPage = new GameHallPage();
        gameHallPage.waitFrameLoad();
        return gameHallPage;
    }
    @Override
    public EvolutionWhiteCliffPage openEvolutionWhiteCliff() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("EVOLUTION_WHITE_CLIFF"));
        return new EvolutionWhiteCliffPage();
    }
    @Override
    public VivoPage openVivo() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("VIVO"));
        VivoPage vivoPage = new VivoPage();
        vivoPage.waitFrameLoad();
        return vivoPage;
    }
    @Override
    public IonPage openIon() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("ION"));
        return new IonPage();
    }
    public void clickProduct(String product) {
        Tab productTab = Tab.xpath(String.format("//a[contains(text(),'%s')]", product));
        Tab targetTab = productTab.isDisplayed() ? productTab : Tab.xpath(String.format("//a[contains(text(),'%s')]", product.toUpperCase()));
        targetTab.click();
    }

    public boolean isCasinoProductDisplayed(String product) {
        Tab productTab = Tab.xpath(String.format("//a[contains(text(),'%s')]", product));
        return productTab.isDisplayed();
    }

    public void clickMainMenu(String menu) {
        Label lblMenu = Label.xpath(String.format("//app-sport-menu-bar//ul[@class='navbar-nav']//a/*[text()=' %s ']", menu));
        lblMenu.click();
    }

    public void clickLogo() {
        imgLogo.click();
    }

    public void waitSpinLoad() {
        imgSpinner.waitForControlInvisible(1, 2);
    }

    public MyMarketPopup openMyMarketPopup() {
        lnkMyMarkets.click();
        MyMarketPopup myMarketPopup = new MyMarketPopup();
        try {
            // wait for pop up visible on screen
            Thread.sleep(500);
        }catch (Exception e){
        }
        return myMarketPopup;
    }

    public boolean isProductTabDisplay(String productName) {
        String productTab = productName;
        if (!productName.equals(MemberConstants.HomePage.PRODUCTS.get("SUPER_SPADE")) && !productName.equals(MemberConstants.HomePage.PRODUCTS.get("EZUGI"))) {
            if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("EXCHANGE"))) {
                productTab = "Exchange";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("DIGIENT"))) {
                productTab = " LOTTERY & SLOTS ";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("EXCH_GAMES"))) {
                productTab = " EXCHANGE GAMES ";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("GAME_HALL"))) {
                productTab = " GAME HALL ";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("VIVO"))) {
                productTab = " Vivo ";
            }
            return Tab.xpath(String.format(this.productMenuXpath, productTab)).isDisplayed();
        } else {
            productTab = " LIVE DEALER ";
            clickProduct(productTab);
            if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("SUPER_SPADE"))) {
                productTab = "Asian Room";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("EZUGI"))) {
                productTab = "European Room";
            }
            return Tab.xpath(String.format(this.productLiveDealerXpath, productTab)).isDisplayed();
        }
    }
    public void logout() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"));
    }

    public String getBalanceLabel() {
       return lblBalanceTitle.getText();
    }
    public String getLiabilityLabel() {
        return lblLiabilityTitle.getText();
    }
    public String getMyBetsLabel() {
        return lblMyBet.getText();
    }

    public String getMyMarketLabel() {
        return lnkMyMarkets.getText();
    }

    public String getMyAccountLabel() {
        return lblMyAccount.getText();
    }
    public String getLiabilityTextColor() {
            return lblLiability.getColour("color");
    }
}



