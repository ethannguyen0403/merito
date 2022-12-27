package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Image;
import com.paltech.element.common.TextBox;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.MyBetsPage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.components.changepasswordpopup.SATChangePasswordPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;

public class Fair999Header extends Header {
    public TextBox txtUsername = TextBox.name("username");
    public TextBox txtPassword = TextBox.name("password");
    private Button btnLogin = Button.xpath("//header//button[contains(@class,'btn-in-out')]");
    private Button btnJoinNow = Button.xpath("//header//button[contains(@class,'join-now')]");
    Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    CheckBox chkRememberMe = CheckBox.id("remember-me");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//div[contains(@class,'account d-block')]","","//ul[contains(@class,'dropdown-menu')]//li");

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

    public AccountStatementPage openAccountStatement(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        DriverManager.getDriver().switchToWindow();
        AccountStatementPage page = new AccountStatementPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.AccountStatementPage.LOAD_REPORT,5);
        return page;
    }

    public MyBetsPage openMyBets(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"));
        DriverManager.getDriver().switchToWindow();
        MyBetsPage page = new MyBetsPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.MyBetsPage.LOAD_REPORT,5);
        page.lblTimezone.isTextDisplayed(MemberConstants.MyBetsPage.NOTES,5);
        return page;
    }

    public ProfitAndLossPage openProfitAndLoss(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"));
        DriverManager.getDriver().switchToWindow();
        ProfitAndLossPage page = new ProfitAndLossPage();
        page.btnLoadReport.isTextDisplayed(MemberConstants.ProfitAndLossPage.LOAD_REPORT,5);
        return page;
    }

    public SATChangePasswordPopup openChangePasswordPopup(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new SATChangePasswordPopup();
    }


}



