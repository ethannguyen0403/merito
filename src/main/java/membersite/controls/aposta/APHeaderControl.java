package membersite.controls.aposta;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.objects.AccountBalance;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.home.LandingPage;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.all.tabexchange.components.popups.ChangePasswordPopup;
import membersite.pages.aposta.MyBetsPage;

import java.util.List;

public class APHeaderControl extends BaseElement {
    private String _xpath ;
    public Image imgLogo = Image.xpath("//a[@class='logo ml-2']/img");
    public TimeZoneLabel lblTimeZone = TimeZoneLabel.xpath("////app-timer-bar");
    private Button btnLogin = Button.xpath("//button[contains(@class,'btn-in-out')]");
    public Image imgLeftMenu = Image.xpath("//div[contains(@class,'left-menu-icon')]/i");
    public DropDownMenu ddmTimeZone = DropDownMenu.xpath("//div[contains(@class,'account dropdown-timezone')]","//span[contains(@class,'title d-none d-lg-flex')]","//ul[@class='dropdown-menu form-control time-zone']");
    public DropDownMenu ddmAccount = DropDownMenu.xpath("//div[contains(@class,'account d-block')]","//span[contains(@class,'title d-none d-lg-flex')]","//ul[contains(@class,'dropdown-menu')]//li");
    public DropDownMenu ddmLanguage = DropDownMenu.xpath("//app-language","//button[contains(@class,'btn-language')]","//ul[contains(@class,'dropdown-menu')]//li");
    public MenuControl mainMenu = MenuControl.xpath("//app-product-tab//nav//ul[contains(@class,'navbar-nav')]//li");
    public Label lblBal = Label.xpath("//div[contains(@class,'profit-group d-none')]//div[@class='balance']//span[@class='title']");
    public Label lblBalCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]//div[@class='balance']//span[@class='font-weight-bold']");
    public Label lblBalValue = Label.xpath("//div[contains(@class,'profit-group d-none')]//div[@class='balance']//a");
    public Label lblLiability = Label.xpath("//div[contains(@class,'profit-group d-none')]//div[@class='liability ml-3']//span[@class='title']");
    public Label lblLiabilityCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]//div[@class='liability ml-3']//span[@class='lia-val mr-2']");
    public Label lblLiabilityValue = Label.xpath("//div[contains(@class,'profit-group d-none')]//div[@class='liability ml-3']//a");
    private Label lblTimeZoneDate = Label.xpath("");
    private APHeaderControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static APHeaderControl xpath(String xpathExpression) {
        return new APHeaderControl(By.xpath(xpathExpression), xpathExpression);
    }

    public LoginPopup clickLogin() {
        if(btnLogin.isDisplayed()){
            btnLogin.click();
        }
        return new LoginPopup();
    }
    public boolean isLoginBtnDisplay() {
        return btnLogin.isDisplayed();
    }

    public String getLoginBtnText() {
        return btnLogin.getText();
    }

    public HomePage login(String username, String password, boolean skipByDefault){
        LoginPopup loginPopup = clickLogin();
        return loginPopup.login(username,password,skipByDefault);
    }


    public HomePage clickLogo() {
        imgLogo.click();
        return new HomePage();
    }

    public void selectLanguage(String language){
        ddmLanguage.clickSubMenu(language);
    }
    public LoginPopup clickLoginBtn() {
        btnLogin.isDisplayed(5);
        btnLogin.click();
        return new LoginPopup();
    }

    public List<String> getAccountMenuList(){
        return ddmAccount.getOptions();
    }

    public LandingPage logout(){
        ddmAccount.clickSubMenu(MemberConstants.LoginPage.LBL_LOGLOUT);
        return new LandingPage();
    }

    public AccountBalance getUserBalance() {
        lblBalValue.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblBalValue.getText())
                .exposure(lblLiabilityValue.getText())
                .build();
    }

    public AccountStatementPage openAccountStatementPage(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        AccountStatementPage page = new AccountStatementPage();
        DriverManager.getDriver().switchToWindow();
       // page.btnLoadReport.isTextDisplayed(FEMemberConstants.AccountStatementPage.LOAD_REPORT,5);
        return page;
    }

    public MyBetsPage clickLiabilityValue(){
        lblLiabilityValue.click();
        DriverManager.getDriver().switchToWindow();
        MyBetsPage page = new MyBetsPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.AccountStatementPage.LOAD_REPORT,5);
        return page;
    }
    public AccountStatementPage clickBalanceValue(){
        lblLiabilityValue.click();
        DriverManager.getDriver().switchToWindow();
        AccountStatementPage page = new AccountStatementPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.AccountStatementPage.LOAD_REPORT,5);
        return page;
    }

    public MyBetsPage openMyBetPage(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"));
        MyBetsPage page = new MyBetsPage();
        DriverManager.getDriver().switchToWindow();
        return page;
    }

    public ProfitAndLossPage openProfitLossPage(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"));
        ProfitAndLossPage page = new ProfitAndLossPage();
        DriverManager.getDriver().switchToWindow();
        return page;
    }
    public <T> T navigateSportMenu(String pageName, Class<T> expectedPage) {
        mainMenu.clickMenu(pageName,false);
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }

    public ChangePasswordPopup openChangePasswordPopup() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new ChangePasswordPopup();
    }

    public void clickLeftMenu(){
        imgLeftMenu.click();
    }
}
