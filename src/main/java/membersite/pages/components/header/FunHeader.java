package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.pages.MyBetsPage;
import membersite.pages.popup.MyMarketPopup;
import membersite.pages.components.changepasswordpopup.FunsportChangePasswordPopup;
import membersite.pages.components.loginform.FunLoginPopup;
import membersite.pages.components.underagegamblingpopup.FunsportUnderageGamblingPopup;

public class FunHeader extends Header {
    private String _xpath ;
    private Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    private Label imgSpinner = Label.xpath("//div[@class=lds-spinner']");
    private Label lblTimezone = Label.xpath("//div[contains(@class,'time-contain') or contains(@class,'timer-contain')]");
    private Button btnLogin = Button.xpath("//input[contains(@class,'btn-login')]");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");
    private Link lnkMyMarkets = Link.xpath("//span[@class='link mymarkets']");
    private DropDownMenu ddmAccount =DropDownMenu.xpath("//div[@id='my-account-dropdown']","","//ul[contains(@class,'dropdown-menu')]//li");
    @Override
    public String getName(){
        return "This is funsport header";
    }

    @Override
    public FunsportUnderageGamblingPopup clickLogin() {
        if(btnLogin.isDisplayed()){
            btnLogin.click();
        }
        return new FunsportUnderageGamblingPopup();
    }
    private FunLoginPopup openLoginPopup(){
        FunsportUnderageGamblingPopup funsportUnderageGamblingPopup = clickLogin();
        return funsportUnderageGamblingPopup.clickConfirmation();
    }

    @Override
    public void login(String username, String password, boolean skipByDefault){
        FunLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password,skipByDefault);
    }

    public String loginInvalid(String username, String password){
        FunLoginPopup loginPopup = openLoginPopup();
        loginPopup.login(username, password,false);
        return loginPopup.lblErrorMessage.getText();
    }
    @Override
    public boolean isMyAccountDisplay(){
        return ddmAccount.isDisplayed();
    }
    public boolean isMyAccountContains(String menu){
        return ddmAccount.isContainSubmenu(menu);
    }

    public membersite.pages.AccountStatementPage openAccountStatement(String type){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"));
        DriverManager.getDriver().switchToWindow();
        membersite.pages.AccountStatementPage page = new membersite.pages.AccountStatementPage(type);
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

    public FunsportChangePasswordPopup openChangePasswordPopup(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new FunsportChangePasswordPopup();

    }

    public void openExchangeGame(){
        tabExchangeGames.click();
    }

    public void clickLogo(){imgLogo.click();}
    public MyMarketPopup openMyMarketPopup(){
        lnkMyMarkets.click();
        return new MyMarketPopup();
    }
    public void logout(){ ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"));   }

}
