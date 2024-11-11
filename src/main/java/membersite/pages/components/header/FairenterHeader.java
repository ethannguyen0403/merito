package membersite.pages.components.header;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.MemberConstants;
import membersite.controls.DropDownMenu;
import membersite.pages.AccountStatementPage;
import membersite.pages.MyBetsPage;
import membersite.pages.SportPage;
import membersite.pages.components.changepasswordpopup.FunsportChangePasswordPopup;
import membersite.pages.components.loginform.FairenterLoginPopup;
import membersite.pages.components.underagegamblingpopup.FairenterUnderageGamblingPopup;

public class FairenterHeader extends Header1 {
    private Image imgLogo = Image.xpath("//span[@class='sprite-logos']");
    private Tab tabExchangeGames = Tab.xpath("//a[contains(text(),'Exchange Games')]");
    private DropDownMenu ddmAccount = DropDownMenu.xpath("//div[@id='my-account-dropdown']", "", "//ul[contains(@class,'dropdown-menu')]//li");
    private Link lnkMyMarkets = Link.xpath("//span[@class='link mymarkets']");
    private String sportMenuXpath = "//a[contains(text(),'%s')]";

    public FairenterLoginPopup clickConfirm() {
        clickLogin().clickConfirmation();
        return new FairenterLoginPopup();
    }

    public FairenterUnderageGamblingPopup clickLogin() {
        // there is no Login button, Gamnling is display in landing page
        return new FairenterUnderageGamblingPopup();

    }

    @Override
    public void login(String username, String password, boolean skipByDefault) {
        FairenterLoginPopup fairenterLoginPopup = clickConfirm();
        fairenterLoginPopup.login(username, password, skipByDefault);
    }

    public String loginInvalid(String username, String password) {
        FairenterLoginPopup fairenterLoginPopup = clickConfirm();
        fairenterLoginPopup.login(username, password, false);
        return fairenterLoginPopup.lblErrorMessage.getText();
    }

    @Override
    public boolean isMyAccountDisplay() {
        return ddmAccount.isDisplayed();
    }

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

    public FunsportChangePasswordPopup openChangePasswordPopup() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"));
        return new FunsportChangePasswordPopup();
    }

    public void openExchangeGame() {
        tabExchangeGames.click();
    }

    public void clickLogo() {
        imgLogo.click();
    }

    public void openMyMarketPopup() {
        lnkMyMarkets.click();
    }

    public SportPage navigateSportMenu(String pageName, String brand) {
        clickHeaderMenu(pageName);
        return new SportPage(brand);
    }

    private void clickHeaderMenu(String sportMenu){
        Menu menu = Menu.xpath(String.format(sportMenuXpath, sportMenu));
        if (!menu.isDisplayed(5)) {
            System.out.println(String.format("There is no %s menu display", sportMenu));
            return;
        }
        menu.click();
    }

    public void logout() {
        ddmAccount.clickSubMenu(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"));
    }

}
