package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.MyBetsPage;
import membersite.pages.components.changepasswordpopup.FunsportChangePasswordPopup;
import membersite.pages.components.loginform.FairenterLoginPopup;
import membersite.pages.components.underagegamblingpopup.FairenterUnderageGamblingPopup;

public class FairenterHeader extends Header {
    private String _xpath ;
    private Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    private Label imgSpinner = Label.xpath("//div[@class=lds-spinner']");
    private Label lblTimezone = Label.xpath("//div[contains(@class,'time-contain') or contains(@class,'timer-contain')]");
    private Button btnLogin = Button.xpath("//input[contains(@class,'btn-login')]");

    private DropDownMenu ddmAccount =DropDownMenu.xpath("//div[@id='my-account-dropdown']","","//ul[contains(@class,'dropdown-menu')]//li");

    public FairenterLoginPopup clickConfirm() {
       clickLogin().clickConfirmation();
        return new FairenterLoginPopup();
    }
    public FairenterUnderageGamblingPopup clickLogin() {
        // there is no Login button, Gamnling is display in landing page
        return new FairenterUnderageGamblingPopup();

    }
    @Override
    public void login(String username, String password, boolean skipByDefault){
        FairenterLoginPopup fairenterLoginPopup = clickConfirm();
        fairenterLoginPopup.login(username,password,skipByDefault);
    }
    public String loginInvalid(String username, String password){
        FairenterLoginPopup fairenterLoginPopup = clickConfirm();
        fairenterLoginPopup.login(username,password,false);
        return fairenterLoginPopup.lblErrorMessage.getText();
    }
    @Override
    public boolean isMyAccountDisplay(){
        return ddmAccount.isDisplayed();
    }

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

    public FunsportChangePasswordPopup openChangePasswordPopup(){
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new FunsportChangePasswordPopup();
    }

}
