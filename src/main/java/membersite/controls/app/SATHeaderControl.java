package membersite.controls.app;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownBox;
import membersite.controls.DropDownMenu;
import membersite.objects.AccountBalance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.beforelogin.popups.SATUnderageGamblingPopup;
import membersite.pages.all.home.LandingPage;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.MyBetsPage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.all.tabexchange.components.popups.ChangePasswordPopup;
import membersite.pages.all.tabexchange.components.popups.MyMarketPopup;
import membersite.pages.funsport.tabexchange.SearchResultPage;

import java.util.*;

public class SATHeaderControl extends BaseElement {
    private String _xpath ;
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    public Image imgLogo = Image.xpath("//a[contains(@class,'logo')]/img");
    public DropDownBox ddbMyAccount = DropDownBox.xpath("//div[contains(@class,'account d-block')]", "//ul[contains(@class,'dropdown-menu')]//a");
    public Label imgSpinner = Label.xpath("//div[@class=lds-spinner']");
    public Menu menuChangePassword = Menu.xpath("//div[@class='account d-block']//ul[@class='dropdown-menu']//span[text()='Change Password']");
    public Icon iconAnnouncement = Icon.xpath("//span[@class='imgannouncement']/img");
    public Label lblMyBets= Label.xpath("//a[contains(@class,'menu-mybet')]");
    public Label lblMyMarkets  = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'account d-none')]/span[contains(text(),'My Markets')]");
    public Label lblMyAccount =Label.xpath("//div[contains(@class,'account d-block')]/span[contains(@class,'title d-none')]");
    public Label lblBalanceTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='title']");
    public Label lblBalanceCurrency = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='bal-val'][1]");
    public Label lblBalance = Label.xpath("//app-top-panel//div[contains(@class,'header-content-info')]//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='bal-val'][2]");
   // public Label lblLiabilityCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[contains(@class,'lia-val')][1]");
    public Label lblLiability = Label.xpath("(//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')])[1]/span[@class='lia-val'][1]");
    public Label lblOutstandingTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[@class='title']");

    public Icon iconHome = Icon.xpath("//i[contains(@class,'fa-home')]");
    public Label lblMarquee = Label.xpath("//div[@class='marquee']");
    public Label lblBalanceSAT = Label.xpath("(//span[@class='bal-val'])[2]");

    public Label lblTimezone = Label.xpath("//div[contains(@class,'time-contain') or contains(@class,'timer-contain')]");
    //public Button btnLogin = Button.xpath("//*[contains(@class,'btn-in-out') or contains(@class,'btn btn-login')]");
    public Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    public Image imgHome = Image.xpath("//i[contains(@class,'fa-home')]");

    public Link lnkMyBetsHistory = Link.xpath("//span[@class='link mybets']");
    public Link lnkMyMarkets = Link.xpath("//span[@class='link mymarkets']");
    public Label lblLiabilityCurrency = Label.xpath("//table[@id='user-balance']//tr[2]//td[@class='balance-value']/span[@class='balance numb']");
    public Label lblLiabilityValue = Label.xpath("//table[@id='user-balance']//tr[1]//td[@class='balance-value']/span[@class='my-bet-credit']");
    public TextBox txtFindTeams = TextBox.id("search-global");
    public DropDownMenu ddmAccount = DropDownMenu.xpath("//div[contains(@class,'account d-block')]","","//ul[contains(@class,'dropdown-menu')]//li");
    private SATHeaderControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static SATHeaderControl xpath(String xpathExpression) {
        return new SATHeaderControl(By.xpath(xpathExpression), xpathExpression);

    }

    public SATUnderageGamblingPopup clickLogin() {
        if(btnLogin.isDisplayed()){
            btnLogin.click();
        }
        return new SATUnderageGamblingPopup();
    }
    public boolean isLoginBtnDisplay() {
       return btnLogin.isDisplayed();
    }

    public String getLoginBtnText() {
        return btnLogin.getText();
    }

    public HomePage login(String username, String password, boolean skipByDefault){
        SATUnderageGamblingPopup popup = clickLogin();
        LoginPopup loginPopup = popup.clickConfirmation();
        return loginPopup.login(username,password,skipByDefault);
    }

    public LoginPopup clickLoginBtn() {
        btnLogin.click();
        return new LoginPopup();
    }

    public  SATUnderageGamblingPopup openUnderGablingPopup() {
        btnLogin.isClickable(3);
        btnLogin.click();
        return new SATUnderageGamblingPopup();
    }

    public HomePage clickLogo() {
        imgLogo.click();
        return new HomePage();
    }

    public String getMarqueeMessage(){

        return lblMarquee.getText();
    }



    public LandingPage logout() {
        ddbMyAccount.selectByVisibleText(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"), true);
        return new LandingPage();
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

    public MyMarketPopup openMyMarkets()
    {
        lblMyMarkets.click();
        return new MyMarketPopup();
    }

    public AccountStatementPage openAccountStatement(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
     //  ddbMyAccount.selectByVisibleText(FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"),true);
        DriverManager.getDriver().switchToWindow();
        AccountStatementPage page = new AccountStatementPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.AccountStatementPage.LOAD_REPORT,5);
        return page;
    }

    public MyBetsPage openMyBets(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"));
        /*ddbMyAccount.selectByVisibleText(FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"),true);*/
        DriverManager.getDriver().switchToWindow();
        MyBetsPage page = new MyBetsPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.MyBetsPage.LOAD_REPORT,5);
        page.lblTimezone.isTextDisplayed(MemberConstants.MyBetsPage.NOTES,5);
        return page;
    }

    public ProfitAndLossPage openProfitAndLoss(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"));/*
        ddbMyAccount.selectByVisibleText(FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"),true);*/
        DriverManager.getDriver().switchToWindow();
        ProfitAndLossPage page = new ProfitAndLossPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.ProfitAndLossPage.LOAD_REPORT,5);
        return page;
    }



    public ChangePasswordPopup openChangePasswordPopup(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new ChangePasswordPopup();
    }

    public List<String> getMyAccountListMenu(){
        ddmAccount.isDisplayed(3);
        return Arrays.asList(ddbMyAccount.getText().split("\n"));
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
        txtFindTeams.sendKeys(searchData);
        txtFindTeams.sendKeys(Keys.ENTER);
        return new SearchResultPage();
    }
}
