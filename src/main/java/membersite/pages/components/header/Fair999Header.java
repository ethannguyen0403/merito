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
import membersite.pages.components.changepasswordpopup.SATChangePasswordPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;
import membersite.pages.popup.MyMarketPopup;

public class Fair999Header extends Header {
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    Image imgLogo = Image.xpath("//a[contains(@class,'logo')]");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//div[contains(@class,'account d-block')]","","//ul[contains(@class,'dropdown-menu')]//li");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");
    private Label imgSpinner = Label.xpath("//div[@class='lds-spinner']");
    private Link lnkMyMarkets = Link.xpath("//span[@class='link mymarkets']");
    private Label lblBalanceTitle = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[@class='balance']/span[@class='title']");
    private Label lblBalanceCurrency = Label.xpath("//app-top-panel//div[@class='balance'][1]//span[@class='bal-val'][1]");
    private Label lblBalance = Label.xpath("//app-top-panel//div[@class='balance'][1]//span[@class='bal-val'][2]");
    private Label lblLiabilityCurrency = Label.xpath("//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')]/span[contains(@class,'lia-val')][1]");
    private Label lblLiability = Label.xpath("(//div[contains(@class,'profit-group d-none')]/div[contains(@class,'liability')])[1]/span[@class='lia-val'][1]");
    private String sportMenuXpath = "//a//div[contains(text(),'%s')]";
    Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
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
    public void clickLeftMenuIcon(){
        imgLeftMenu.click();
    }
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
    @Override
    public void login(String username, String password, boolean skipByDefault){
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password,skipByDefault);
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

    public membersite.pages.AccountStatementPage openAccountStatement(String type){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.AccountStatementPage page = new AccountStatementPage(type);
        page.accountStatementContainer.waitLoadReport();
        return page;
    }

    public membersite.pages.MyBetsPage openMyBets(String type){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.MyBetsPage page = new MyBetsPage(type);
        page.myBetsContainer.waitLoadReport();
        return page;
    }

    public membersite.pages.ProfitAndLossPage openProfitAndLoss(String type){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.ProfitAndLossPage page = new ProfitAndLossPage(type);
        page.profitAndLossContainer.waitLoadReport();
        return page;
    }

    public SATChangePasswordPopup openChangePasswordPopup(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new SATChangePasswordPopup();
    }


    public AccountBalance getUserBalance() {
        lblBalance.isDisplayed();
        return new AccountBalance.Builder()
                .balance(lblBalance.getText())
                .exposure(lblLiability.getText())
                .build();
    }
    public void openExchangeGame(){
        tabExchangeGames.click();
    }
    public void clickProduct(String product){
        Tab productTab = Tab.xpath(String.format("//a[contains(text(),'%s')]",product));
        productTab.click();
    }

    public void clickMainMenu(String menu){
        Label lblMenu = Label.xpath(String.format("//app-sport-menu-bar//ul[@class='navbar-nav']//a/*[text()=' %s ']"));
        lblMenu.click();
    }

    public void clickLogo(){imgLogo.click();}
    public void waitSpinLoad(){
        imgSpinner.waitForControlInvisible(1,2);
    }

    public MyMarketPopup openMyMarketPopup(){
        lnkMyMarkets.click();
        return new MyMarketPopup();
    }
    public void logout(){ ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"));   }

}



