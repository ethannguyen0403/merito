package membersite.pages.all.components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import membersite.common.FEMemberConstants;
import membersite.controls.aposta.APHeaderControl;
import membersite.controls.app.FSHeaderControl;
import membersite.controls.app.FairenterUnderGamblingForm;
import membersite.controls.app.SATHeaderControl;
import membersite.objects.AccountBalance;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.beforelogin.popups.UnderageGamblingPopup;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.components.popups.MyMarketPopup;
import membersite.pages.all.tabexchangegame.EGHomePage;
import membersite.pages.all.tablotteryslot.LotterySlotsPage;
import membersite.utils.betplacement.BetUtils;

import java.util.*;

import static common.MeritoConstant.*;

public class Header extends Footer {
    public SATHeaderControl satHeaderControl = (SATHeaderControl) SATHeaderControl.xpath("//header");
    public FSHeaderControl fsHeaderControl = FSHeaderControl.xpath("");
    public APHeaderControl apHeaderControl = APHeaderControl.xpath("//header");
    public FairenterUnderGamblingForm fairenterUnderGamblingForm = FairenterUnderGamblingForm.xpath("");
   // public Image imgLogo = Image.xpath("//a[contains(@class,'logo ml-2')]/img");
    public Label imgSpinner = Label.xpath("//div[@class=lds-spinner']");
    public Icon iconAnnouncement = Icon.xpath("//span[@class='imgannouncement']/img");
    public Label lblMyBets= Label.xpath("//a[contains(@class,'menu-mybet')]");
    public Label lblMyMarkets  = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'account d-none')]/span[contains(text(),'My Markets')]");
    public Label lblMyAccount =Label.xpath("//div[contains(@class,'account d-block')]/span[contains(@class,'title d-none')]");
    public Label lblBalanceTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='title']");
    public Label lblBalanceCurrency = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='bal-val'][1]");
    public Label lblBalance = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='bal-val'][2]");
    public Label lblLiabilityCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[contains(@class,'lia-val')][1]");
    public Label lblLiability = Label.xpath("(//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')])[1]/span[@class='lia-val'][1]");
    public Label lblOutstandingTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[@class='title']");
    public String productMenuXpath = "//div[@class='product-tab-wrapper' or @class='mod-main-navigation ']//a[text()=' %s ' or text() = '%s'] | .//div[text()=' %s ']";
    public Tab tabExchange = Tab.xpath("//a[contains(text(),'Exchange')]");
    public Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");
    public Tab tabLiveDealer = Tab.xpath("//a[contains(text(),'Live Dealer')]");
    public Tab tabLotteryAndSlots = Tab.xpath("//a[contains(text(),'Lottery & Slots')]");
    public Icon iconHome = Icon.xpath("//i[contains(@class,'fa-home')]");
    public Label lblMarquee = Label.xpath("//div[@class='marquee']");
    public Label lblBalanceSAT = Label.xpath("(//span[@class='bal-val'])[2]");

    public Label lblTimezone = Label.xpath("//div[contains(@class,'time-contain') or contains(@class,'timer-contain')]");
    public Button btnLogin = Button.xpath("//*[contains(@class,'btn-in-out') or contains(@class,'btn btn-login')]");
    public Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    public Image imgHome = Image.xpath("//i[contains(@class,'fa-home')]");

   // public Label lblLiabilityCurrency = Label.xpath("//table[@id='user-balance']//tr[2]//td[@class='balance-value']/span[@class='balance numb']");
    public Label lblLiabilityValue = Label.xpath("//table[@id='user-balance']//tr[1]//td[@class='balance-value']/span[@class='my-bet-credit']");

    public boolean isProductActive(String productName)
    {
        for (String s: translateProductsActive(BetUtils.getProductInfo())) {
            if(s.equals(productName))
                return true;
        }
        return false;
    }
    public List<String> translateProductsActive(List<String> productCode)
    {
        if(Objects.nonNull(productCode))
        {
            List<String> productTranslate = new ArrayList<>();
            for (String s : productCode) {
                productTranslate.add(FEMemberConstants.HomePage.PRODUCTS.get(s));
            }
            return productTranslate;
        }else{
            System.out.println("There is no product active!");
            return null;
        }
    }

    public LoginPopup clickLoginBtn() {
        btnLogin.click();
        return new LoginPopup();
    }

    public UnderageGamblingPopup openUnderGablingPopup() {
        btnLogin.click();
        return new UnderageGamblingPopup();
    }


    public String getMarqueeMessage(){

        return lblMarquee.getText();
    }

    public <T> T switchProduct(String productName,Class<T> expectedPage)
    {
        Tab productTab = Tab.xpath(String.format(productMenuXpath,productName));
        productTab.click();
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }

    public String getTimeZoneByBrand(String skinName){
        switch (skinName){
            case "satsport":
                return "Note : Date will be based on time zone IST";
            default:
                return "Note : Date will be based on time zone GMT-04:00";
        }
    }

    public List<String> getAccountMenu(String skinName){
        switch (skinName){
            case "funsport":
                return fsHeaderControl.getMyAccountListMenu();
            case "aposta":
                return apHeaderControl.getAccountMenuList();
            case "fair999new":
                return apHeaderControl.getAccountMenuList();
            default:
                return satHeaderControl.getMyAccountListMenu();
        }
    }
    public Object openChangePasswordPopup(String skin){
        switch (skin){
            case "satsport":
                return satHeaderControl.openChangePasswordPopup();
            case "fairexchange":
                return satHeaderControl.openChangePasswordPopup();
            case "aposta":
                return apHeaderControl.openChangePasswordPopup();
            case "fair999new":
                return apHeaderControl.openChangePasswordPopup();
            default:
                return fsHeaderControl.openChangePasswordPopup();
        }
    }

    public boolean isProductTabDisplay(String productName)
    {
        String productTab = productName;
        if (!productName.equals(FEMemberConstants.HomePage.PRODUCTS.get("SUPER_SPADE")) && !productName.equals(FEMemberConstants.HomePage.PRODUCTS.get("EZUGI"))) {
            if (productName.equals(FEMemberConstants.HomePage.PRODUCTS.get("EXCHANGE"))) {
                productTab = "Exchange";
            } else if (productName.equals(FEMemberConstants.HomePage.PRODUCTS.get("DIGIENT"))) {
                productTab = "Lottery & Slots";
            } else if (productName.equals(FEMemberConstants.HomePage.PRODUCTS.get("EXCH_GAMES"))) {
                productTab = "Exchange Games";
            }
        } else {
            productTab = "Live Dealer";
        }

        return Tab.xpath(String.format(this.productMenuXpath, productTab, productTab, productTab)).isDisplayed();
    }

    public HomePage switchExchangeTab() {
        tabExchange.click();
        return new HomePage();
    }

    public EGHomePage switchExchangeGameTab() {
        tabExchangeGames.click();
        return new EGHomePage();
    }
    public membersite.pages.all.tablivedealer.components.LiveDealer switchLiveDealerTab() {
        tabLiveDealer.click();
        return new membersite.pages.all.tablivedealer.components.LiveDealer();
    }
    public LotterySlotsPage switchLotterySlotsTab() {
        tabLotteryAndSlots.click();
        return new LotterySlotsPage();
    }

    public AccountBalance getUserBalance() {
        lblBalance.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblBalance.getText())
                .exposure(lblLiability.getText())
                .build();
    }

    //TODO: separate account into a control
    public AccountBalance getUserBalanceSAT() {
        lblBalanceSAT.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblBalanceSAT.getText())
                .exposure(lblLiability.getText())
                .build();
    }
    public String calculateBalance(String balance, String liability) {
        double balanceDoub =Double.valueOf(balance.replaceAll(",", "").toString());
        double liabilityDoub =Double.valueOf(liability.replaceAll(",", "").toString());
        double balanceReturn = balanceDoub - liabilityDoub;
        return String.format(Locale.getDefault(),"%,.2f",balanceReturn);
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

    public MyMarketPopup openMyMarkets()
    {
        lblMyMarkets.click();
        return new MyMarketPopup();
    }

    public String getPageUrl()
    {
        return DriverManager.getDriver().getCurrentUrl();
    }
    public String getPageTitle()
    {
        return DriverManager.getDriver().getTitle();
    }

    public void closeBrowserTab()
    {
        DriverManager.getDriver().close();
        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandle());
    }
    public void switchToPreviousTab()
    {
        String wh1= DriverManager.getDriver().getWindowHandle();
        for(String handle : DriverManager.getDriver().getWindowHandles()) {
            if (!handle.equals(wh1)) {
                DriverManager.getDriver().switchTo().window(handle);
            }
        }
    }

    public HomePage login(String brandName,String username, String password, boolean skipByDefault){
        switch (brandName){
            case FAIREXCHANGE:
                return apHeaderControl.login(username,password,skipByDefault);
            case FUNSPORT:
                return fsHeaderControl.login(username,password,skipByDefault);
            case FAIRENTER:
                return fairenterUnderGamblingForm.login(username,password,skipByDefault);
            default:
                return satHeaderControl.login(username,password,skipByDefault);
        }

    }
    public AccountBalance getPlayerBalance(String brandName){
        switch (brandName){
            case FUNSPORT:
                return fsHeaderControl.getUserBalance();
            default:
                return satHeaderControl.getUserBalanceSAT();
        }

    }


}
