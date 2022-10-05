package membersite.controls.app;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import membersite.common.FEMemberConstants;
import membersite.controls.DropDownBox;
import membersite.controls.DropDownMenu;
import membersite.objects.AccountBalance;
import membersite.pages.all.beforelogin.popups.SATUnderageGamblingPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import membersite.pages.all.home.LandingPage;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.funsport.home.popups.LoginPopup;
import membersite.pages.funsport.home.popups.UnderageGamblingPopup;
import membersite.pages.all.tabexchange.components.popups.MyMarketPopup;
import membersite.pages.funsport.tabexchange.AccountStatementPage;
import membersite.pages.funsport.tabexchange.MyBetsPage;
import membersite.pages.funsport.tabexchange.SearchResultPage;
import membersite.pages.funsport.tabexchange.components.popups.ChangePasswordPopupOldUI;
import membersite.utils.betplacement.BetUtils;

import java.util.*;

public class FSHeaderControl extends BaseElement {
    private String _xpath ;
    public Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    public Label imgSpinner = Label.xpath("//div[@class=lds-spinner']");
     public Label lblTimezone = Label.xpath("//div[contains(@class,'time-contain') or contains(@class,'timer-contain')]");
    public Button btnLogin = Button.xpath("//input[contains(@class,'btn-login')]");
    public Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    public Image imgHome = Image.xpath("//i[contains(@class,'fa-home')]");
    public Menu menuChangePassword = Menu.xpath("//div[contains(@class,'account d-block')]//ul[@class='dropdown-menu']//li[9]/span");
    public Link lnkMyBetsHistory = Link.xpath("//span[@class='link mybets']");
    public Link lnkMyMarkets = Link.xpath("//span[@class='link mymarkets']");
    public Label lblLiabilityCurrency = Label.xpath("//table[@id='user-balance']//tr[2]//td[@class='balance-value']/span[@class='balance numb']");
    public Label lblLiabilityValue = Label.xpath("//table[@id='user-balance']//tr[1]//td[@class='balance-value']/span[@class='my-bet-credit']");
    public TextBox txtFindTeams = TextBox.xpath("//div[@class='input-search']//input[@id='search-global']");
    public membersite.controls.DropDownBox ddbMyAccount = DropDownBox.xpath("//div[@id='my-account-dropdown']", "//ul[@class='dropdown-menu dates filter-by-date']//a");
    public Tab tabExchangeGames = Tab.xpath("//a[@id='exchange-games']");
    public Tab tabExchange= Tab.xpath("//a[@id='exchange']");
    public Tab tabLiveDealer = Tab.xpath("//a[@id='live-dealer']");
    public Label lblBalanceTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='title']");
    public Label lblBalanceCurrency = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='bal-val'][1]");
    public Label lblBalance = Label.xpath("//span[@class='my-bet-credit']");
    public DropDownMenu ddmAccount = DropDownMenu.xpath("//div[@id='my-account-dropdown']","","//ul[contains(@class,'dropdown-menu')]//li");
    private FSHeaderControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static FSHeaderControl xpath(String xpathExpression) {
        return new FSHeaderControl(By.xpath(xpathExpression), xpathExpression);
    }

    public UnderageGamblingPopup clickLogin() {
        if(btnLogin.isDisplayed()){
            btnLogin.click();
        }
        return new UnderageGamblingPopup();
    }

    public boolean isLoginBtnDisplay() {
        return btnLogin.isDisplayed();
    }

    public String getLoginBtnText() {
        return btnLogin.getText();
    }

    public HomePage login(String username, String password, boolean skipByDefault){
        UnderageGamblingPopup popup = clickLogin();
        LoginPopup loginPopup = popup.clickConfirmation();
        return loginPopup.login(username,password,skipByDefault);
    }



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


    public String calculateBalance(String balance, String liability) {
        double balanceDoub = Double.valueOf(balance.replaceAll(",", "").toString());
        double liabilityDoub = Double.valueOf(liability.replaceAll(",", "").toString());
        double balanceReturn = balanceDoub - liabilityDoub;
        return String.format(Locale.getDefault(), "%,.2f", balanceReturn);
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
    public MyMarketPopup openMyMarketPopup(){
        lnkMyMarkets.click();
        return new MyMarketPopup();
    }

    public SearchResultPage findEvent(String searchData){
        txtFindTeams.isDisplayedShort(10);
        txtFindTeams.click();
        txtFindTeams.sendKeys(searchData);
        txtFindTeams.type(false,Keys.ENTER);
        SearchResultPage page = new SearchResultPage();
        page.lblSearchResult.isDisplayedShort(10);
        return new SearchResultPage();
    }
    public List<String> getMyAccountListMenu(){
        return Arrays.asList(ddbMyAccount.getText().split("\n"));
    }
    public LandingPage logout() {
        ddbMyAccount.selectByVisibleText(FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"), true);
        return new LandingPage();
    }
    public AccountStatementPage openAccountStatement(){
        ddbMyAccount.selectByVisibleText(FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"),true);
        DriverManager.getDriver().switchToWindow();
        AccountStatementPage page = new AccountStatementPage();
        page.btnLoadReport.isTextDisplayed(FEMemberConstants.AccountStatementPage.LOAD_REPORT,5);
        return page;
    }
    public MyBetsPage openMyBets(){
        ddbMyAccount.selectByVisibleText(FEMemberConstants.HomePage.DDB_MY_ACCOUNT_FS.get("My Bets/History"),true);
        DriverManager.getDriver().switchToWindow();
        MyBetsPage page = new MyBetsPage();
        page.btnLoadReport.isTextDisplayed(FEMemberConstants.MyBetsPage.LOAD_REPORT,5);
        page.lblTimezone.isTextDisplayed(FEMemberConstants.MyBetsPage.NOTES,5);
        return page;
    }
    public ChangePasswordPopupOldUI openChangePasswordPopup(){
        ddmAccount.clickSubMenu(FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new ChangePasswordPopupOldUI();
    }
    public HomePage clickLogo() {
        imgLogo.click();
        return new HomePage();
    }
    public AccountBalance getUserBalance() {
        lblBalance.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblBalance.getText())
                .exposure(lblLiabilityValue.getText())
                .build();
    }

}
