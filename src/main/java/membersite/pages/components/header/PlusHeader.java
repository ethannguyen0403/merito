package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.pages.AccountStatementPage;
import membersite.pages.MyBetsPage;
import membersite.pages.ProfitAndLossPage;
import membersite.pages.all.tabexchangegame.EGHomePage;
import membersite.pages.components.changepasswordpopup.SATChangePasswordPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;

public class PlusHeader extends Header {
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//header/app-top-panel//span[contains(@class,'title dropdown-toggle')]","","//ul[contains(@class,'dropdown-menu')]//li");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");

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

    public EGHomePage openExchangeGame(){
        tabExchangeGames.click();
        return new EGHomePage();
    }
    public void clickLogo(){imgLogo.click();}

}



