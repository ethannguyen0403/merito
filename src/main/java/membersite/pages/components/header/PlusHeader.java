package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.pages.AccountStatementPage;
import membersite.pages.MyBetsPage;
import membersite.pages.ProfitAndLossPage;
import membersite.pages.RacingPage;
import membersite.pages.components.changepasswordpopup.SATChangePasswordPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;

public class PlusHeader extends Header1 {
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//header//app-top-panel//div[contains(@class,'account dropdown-account')]", "", "//ul[contains(@class,'dropdown-menu')]//li");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");

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

    @Override
    public void login(String username, String password, boolean skipByDefault) {
        SATLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password, skipByDefault);
    }

    public void clickLeftMenuIcon() {
        imgLeftMenu.click();
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

    public membersite.pages.AccountStatementPage openAccountStatement(String type) {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.AccountStatementPage page = new AccountStatementPage(type);
        page.accountStatementContainer.waitLoadReport();
        return page;
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

    public SATChangePasswordPopup openChangePasswordPopup() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new SATChangePasswordPopup();
    }

    public void openExchangeGame() {
        tabExchangeGames.click();
    }

    public void clickLogo() {
        imgLogo.click();
    }

    public void logout() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"));
    }

    public RacingPage navigateRacing(String pageName, String brand) {
        return new RacingPage(brand);
    }
}



