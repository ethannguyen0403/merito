package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.objects.AccountBalance;
import membersite.pages.*;
import membersite.pages.casino.*;
import membersite.pages.components.changepasswordpopup.SATChangePasswordPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.ps38preferences.PS38PreferencesPopup;
import membersite.pages.components.signinform.SATSignInPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;
import membersite.pages.popup.MyMarketPopup;
import membersite.utils.betplacement.BetUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static common.CasinoConstant.*;

public class SatHeader extends Header1 {
    Image imgLogo = Image.xpath("//a[contains(@class,'logo')]");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    String productMenuXpath = "//app-product-tab-v2//a[(text()='%s')]";
    String productLiveDealerXpath = "//app-live-dealer//a[text()='%s']";
    Label lblMarquee = Label.xpath("//div[@class='marquee']");
    private TextBox txtUsername = TextBox.name("username");
    private TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//div[contains(@class,'account d-block')]", "", "//ul[contains(@class,'dropdown-menu')]//li");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");
    private Label imgSpinner = Label.xpath("//div[contains(@class,'lds-spinner')]");
    private Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    private Menu menuSports = Menu.xpath("//app-sport-menu-bar//ul[@class='navbar-nav']//a");
    private String sportMenuXpath = "//a//div[contains(text(),'%s')]";
    private String sportMenuOldUIXpath = "//a[contains(@data-sport-name,'%s')]";
    private Link lnkMyMarkets = Link.xpath("//div[contains(@class,'account d-none')]");
    private Label lblBalanceTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[@class='balance'][1]/span[@class='title']");
    private Label lblBalanceCurrency = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='bal-val'][1]");
    private Label lblBalance = Label.xpath("//app-top-panel//div[contains(@class,'profit-group d-none')]//div[@class='balance'][2]//span[@class='bal-val']");
    private Label lblCashBalance = Label.xpath("//app-top-panel//div[contains(@class,'profit-group d-none')]//div[@class='balance']//span[@class='bal-val']");
    private Label lblLiabilityCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[contains(@class,'lia-val')][1]");
    private Label lblLiability = Label.xpath("(//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')])[1]/span[@class='lia-val'][1]");
    private Menu menuEguzi = Menu.xpath("//app-live-dealer//a[contains(@routerlink, 'ezugi')]");
    public static Button btnDeposit = Button.xpath("//button[contains(@class,'btn-deposit')]");
    // Before Login
    public SATUnderageGamblingPopup clickLogin() {
        if (btnLogin.isDisplayed()) {
            btnLogin.click();
        }
        return new SATUnderageGamblingPopup();

    }

    public void clickLeftMenuIcon() {
        imgLeftMenu.click();
    }

    private SATLoginPopup openLoginPopup() {
        SATUnderageGamblingPopup satUnderageGamblingPopup = clickLogin();
        return satUnderageGamblingPopup.clickConfirmation();
    }

    @Override
    public SATSignInPopup openSigninPopup() {
        btnJoinNow.click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.getCause();
        }
        return new SATSignInPopup();
    }

    @Override
    public void login(String username, String password, boolean skipByDefault) {
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password, skipByDefault);
    }

    @Override
    public void signin(String username, String password, String email, String currency, String phone) {
        SATSignInPopup signInPopup = openSigninPopup();
        signInPopup.signin(username, password, email, currency, phone);
    }

    public String loginInvalid(String username, String password) {
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password, false);
        return loginPopup.lblErrorMessage.getText();
    }

    // AfterLogin
    @Override
    public boolean isMyAccountDisplay() {
        return ddmAccount.isDisplayed();
    }

    @Override
    public boolean isMyAccountContains(String menu) {
        return ddmAccount.isContainSubmenu(menu);
    }

    @Override
    public void openMyAccount() {
        ddmAccount.click();
    }

    @Override
    public boolean isDepositButtonDisplayed() {return btnDeposit.isDisplayed();}

    public AccountStatementPage openAccountStatement(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        DriverManager.getDriver().switchToWindow();
        AccountStatementPage page = new AccountStatementPage(type);
        page.accountStatementContainer.waitLoadReport();
        return page;
    }

    public MyBetsPage openMyBets(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"));
        DriverManager.getDriver().switchToWindow();
        MyBetsPage page = new MyBetsPage(type);
        page.myBetsContainer.waitLoadReport();
        return page;
    }

    public PaymentPage openDepositPage(String type) {
        btnDeposit.click();
        PaymentPage page = new PaymentPage(type);
        page.lblTitle.waitForElementToBePresent(page.lblTitle.getLocator(), 2);
        return page;
    }

    public ProfitAndLossPage openProfitAndLoss(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"));
        DriverManager.getDriver().switchToWindow();
        ProfitAndLossPage page = new ProfitAndLossPage(type);
        page.profitAndLossContainer.waitLoadReport();
        return page;
    }

    public SATChangePasswordPopup openChangePasswordPopup() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new SATChangePasswordPopup();
    }

    @Override
    public PS38PreferencesPopup openPS38PreferencesPopup() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("PS38 Preferences"));
        return new PS38PreferencesPopup();
    }

    public void openExchangeGame() {
        tabExchangeGames.click();
    }

    @Override
    public LiveDealerAsianPage openLiveDealerAsian() {
        clickProduct(CASINO);
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("LIVE_DEALER_ASIAN"));
        return new LiveDealerAsianPage();
    }

    @Override
    public LiveDealerEuropeanPage openLiveDealerEuro() {
        clickProduct(CASINO);
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("LIVE_DEALER_EUROPEAN"));
        return new LiveDealerEuropeanPage();
    }

    @Override
    public EvolutionPage openEvolution() {
        Tab productTab = Tab.xpath(String.format("//a[text()=' %s '] | //a[text()='%s']", MAPPING_CASINO_PRODUCT_UI.get("EVOLUTION"), MAPPING_CASINO_PRODUCT_UI.get("EVOLUTION")));
        //In Sat, Evolution tab menu is displayed in duplicate, 1 is Evolution and 1 is Evolution WhiteCliff
        for(WebElement products: productTab.getWebElements()){
            products.click();
            waitSpinLoad();
            String casinoCode = DriverManager.getDriver().getCurrentUrl();
            if(!casinoCode.contains(MAPPING_CASINO_PRODUCT_SUFFIX_URL.get("Evolution"))){
                continue;
            }
            Label lblEvolution = Label.xpath(String.format("//app-ezugi//span[contains(text(), '%s')]", MAPPING_CASINO_PRODUCT_UI.get("EVOLUTION")));
            lblEvolution.click();
        }
        return new EvolutionPage();
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
    public EvolutionWhiteCliffPage openEvolutionWhiteCliff() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("EVOLUTION_WHITE_CLIFF"));
        return new EvolutionWhiteCliffPage();
    }

    @Override
    public GameHallPage openGameHall() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("GAME_HALL"));
        GameHallPage gameHallPage = new GameHallPage();
        gameHallPage.waitFrameLoad();
        return new GameHallPage();
    }
    @Override
    public VivoPage openVivo() {
        clickProduct(MAPPING_CASINO_PRODUCT_UI.get("VIVO"));
        VivoPage vivoPage = new VivoPage();
        vivoPage.waitFrameLoad();
        return vivoPage;
    }

    public void clickProduct(String product) {
        Tab productTab = Tab.xpath(String.format("//a[text()=' %s '] | //a[text()='%s']", product, product));
        Tab targetTab = productTab.isDisplayed() ? productTab :
                Tab.xpath(String.format("//a[text()=' %s '] | //a[text()='%s']", product.toUpperCase(), product.toUpperCase()));
        if (targetTab.getWebElements().size() > 1) {
            targetTab.getWebElements().get(1).click();
        } else {
            targetTab.click();
        }
    }

    public void clickMainMenu(String menu) {
        Label lblMenu = Label.xpath(String.format("//app-sport-menu-bar//ul[@class='navbar-nav']//a/*[text()=' %s ']"));
        lblMenu.click();
    }

    public void clickLogo() {
        imgLogo.click();
    }

    public void waitSpinLoad() {
        imgSpinner.waitForControlInvisible(1, 2);
    }

    public String getLogoSrc() {
        return imgLogo.getAttribute("src");
    }

    public boolean isLeftMenuIcondisplay() {
        return imgLeftMenu.isDisplayed();
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

    private void clickHeaderMenu(String sportMenu){
        Menu menu = Menu.xpath(String.format(sportMenuXpath, sportMenu));
        if (!menu.isDisplayed(5)) {
            System.out.println(String.format("There is no %s menu display", sportMenu));
            return;
        }
        menu.click();
    }
    public MyMarketPopup openMyMarketPopup() {
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

    public AccountBalance getUserCashBalance() {
        lblCashBalance.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblCashBalance.getText())
                .exposure(lblLiability.getText())
                .build();
    }


    public boolean isProductActive(String productName) {
        for (String s : translateProductsActive(BetUtils.getProductInfo())) {
            if (s.equals(productName))
                return true;
        }
        return false;
    }

    private List<String> translateProductsActive(List<String> productCode) {
        if (Objects.nonNull(productCode)) {
            List<String> productTranslate = new ArrayList<>();
            for (String s : productCode) {
                productTranslate.add(MemberConstants.HomePage.PRODUCTS.get(s));
            }
            return productTranslate;
        } else {
            System.out.println("There is no product active!");
            return null;
        }
    }

    public String getLiabilityTextColor() {
        return lblLiability.getColour("color");
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
        double balanceDoub = Double.valueOf(balance.replaceAll(",", "").toString());
      /*  double liabilityDoubBefore =Double.valueOf(liabilityBeforePlaceBetOnMarket.replaceAll(",", "").toString());
        double liabilityDoubAfter =Double.valueOf(liabilityAfterPlaceOnMarket.replaceAll(",", "").toString());*/
        double balanceReturn = balanceDoub - liabilityBeforePlaceBetOnMarket + liabilityAfterPlaceOnMarket;
        return String.format(Locale.getDefault(), "%,.2f", balanceReturn);
    }

//    public String calculateExposureKept(String liabilityBeforePlaceBet, Wager wagerBack, Wager wagerLay) {
//        double profitBack = 0.0;
//        double liabilityLay = 0.0;
//        if(!Objects.isNull(wagerBack) && !Objects.isNull(wagerLay)) {
//            profitBack = wagerBack.getProfitFancyWager();
//        } else {
//            if(wagerBack.getPayout() == 100.0 || wagerBack.getPayout() == 0) {
//                profitBack = wagerBack.getProfitFancyWager();
//            } else {
//                profitBack = wagerBack.getStake();
//            }
//        }
//        if(!Objects.isNull(wagerLay)) {
//            liabilityLay = wagerLay.getLiabilityFancyWager();
//        }
//        //return liability calculation (if wagerBack is null will use the wagerLay to return)
//        if(!Objects.isNull(wagerBack)) {
//            if(wagerBack.getPayout() == 100 || wagerBack.getPayout() == 0) {
//                return String.format("%,.2f",Double.valueOf(liabilityBeforePlaceBet.replace(",","")) - (profitBack + liabilityLay));
//            } else {
//                return String.format("%,.2f",Double.valueOf(liabilityBeforePlaceBet.replace(",","")) - Math.abs(liabilityLay - profitBack));
//            }
//        } else {
//            if(wagerLay.getPayout() == 100 || wagerLay.getPayout() == 0) {
//                return String.format("%,.2f",Double.valueOf(liabilityBeforePlaceBet.replace(",","")) - (profitBack + liabilityLay));
//            } else {
//                return String.format("%,.2f",Double.valueOf(liabilityBeforePlaceBet.replace(",","")) - Math.abs(liabilityLay - profitBack));
//            }
//        }
//
//    }

    public String getMarqueeMessage() {
        String announcementMessage = "";
        if (lblMarquee.isDisplayed()) {
            announcementMessage = lblMarquee.getText();
        }
        return announcementMessage;
    }

    public boolean isProductTabDisplay(String productName) {
        String productTab = productName;
        if (!productName.equals(MemberConstants.HomePage.PRODUCTS.get("SUPER_SPADE")) && !productName.equals(MemberConstants.HomePage.PRODUCTS.get("EZUGI"))) {
            if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("EXCHANGE"))) {
                productTab = " Exchange ";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("DIGIENT"))) {
                productTab = " LOTTERY & SLOTS ";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("EXCH_GAMES"))) {
                productTab = " EXCHANGE GAMES ";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("VIVO"))) {
                productTab = " Vivo ";
            } else if (productName.equals(MemberConstants.HomePage.PRODUCTS.get("GAME_HALL"))) {
                productTab = " GAME HALL ";
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

    public RacingPage navigateRacing(String pageName, String brand) {
        clickHeaderMenu(pageName);
        return new RacingPage(brand);
    }
    public String getBalanceLabel() {
        return lblBalanceTitle.getText();
    }
}



