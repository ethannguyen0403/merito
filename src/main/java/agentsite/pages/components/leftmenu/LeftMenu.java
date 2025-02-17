package agentsite.pages.components.leftmenu;

import agentsite.controls.LefMenuList;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;

import java.util.List;

public class LeftMenu {
    public Label lblAppLeftMenu = Label.xpath("//div[@class='side-left']//app-left-menu");
    public Label lblWelcome = Label.xpath("//p[contains(@class,'greeting')]");
    public Label lblLoginID = Label.xpath("//p[contains(@class,'usercode')]");
    public Button tabMainMenu = Button.xpath("//app-left-menu//div[@class='menu-mode-container']//button[1]");
    public Button tabQuickSearch = Button.xpath("//app-left-menu//div[@class='menu-mode-container']//button[2]");
    public LefMenuList leftMenuList = LefMenuList.xpath("//div[@class='leftmenu']");

    public String getConfigureOTP() {
        return "";
    }

    public void clickSubMenu(String menu, String submenu) {
        switchMainMenu();
        leftMenuList.clickSubMenu(menu, submenu);
    }

    public void clickSubMenu(String menu, String submenu1, String submenu2) {
        leftMenuList.clickSubMenu(menu, submenu1, submenu2);
    }

    public boolean isMenuExpanded(String menu) {
        String attribute = leftMenuList.getmenuAtribuite(menu, "class");
        return attribute.contains("active");
    }

    public void switchMainMenu() {
        tabMainMenu.click();
    }

    public void switchQuickSearch() {
        tabQuickSearch.click();
    }


    public void navigateAnalysisOfRunningMarketsPage() {
    }

    public List<String> defineBalanceInfoQuickSearch(boolean isCredit) {
        return null;
    }

    private List<String> defineBalanceInfoCreditCashQuickSearch() {
        return null;
    }

    private List<String> defineBalanceInfoCreditQuickSearch() {
        return null;
    }

    public void navigatePS38SportsResultsPage() {

    }

    public boolean isDisplayPS38SportsResults() {
        return true;
    }

    public void navigateWinLossSimplePage() {
    }

    public void navigateBigStakeConfigurationPage() {
    }

    public void navigateWinLossBySportAndMarketTypePage() {
    }

    public void navigateStatementReportPage() {
    }

    public void navigateWinLossDetailPage() {
    }

    public void navigateWinLossAnalysisPage() {
    }

    public void navigateWinLossByEventPage() {
    }

    public void navigateClientLedgerPage() {
    }

    public boolean isListSubMenuDisplayCorrect(String menu) {
        return false;
    }

    public void verifyListSubMenuDisplayCorrect() {
    }

    public void navigateIPMonitoringPage() {
    }

    public void navigateMonitoredAccountsPage() {
    }

    public void navigateTransactionHistoryPage() {
    }

    public void navigateSubUserListingPage() {
    }

    public void navigateAgentExposureLimitPage() {
    }

}
