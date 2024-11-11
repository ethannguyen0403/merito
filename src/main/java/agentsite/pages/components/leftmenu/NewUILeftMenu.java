package agentsite.pages.components.leftmenu;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import common.AGConstant;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static common.AGConstant.HomePage.*;

public class NewUILeftMenu extends LeftMenu {
    Icon iconHome = Icon.xpath("//span[@class='action-icon home']");
    Label lblAppLeftMenu = Label.xpath("//div[@class='side-left']//app-left-menu");
    Image imgLogo = Image.xpath("//app-left-menu//span[@class='applogo']");
    Link lnkConfigureOTP = Link.xpath("//p[contains(@class,'asia-otp-label')]/a");

    Button btnMyAccount = Button.xpath("//button[contains(@class,'my-account')]");
    Label lblSubUserMenu = Label.xpath("//div[@class='my-account-menu']//ul[contains(@class,'dropdown')]//a[text()='Sub User']");
    Label lblSummary = Label.xpath("//div[@class='my-account-menu']//ul[contains(@class,'dropdown')]//a[text()='Summary']");
    Link lnkConfigureNickname = Link.xpath("//p[contains(@class,'nickname')]/a");
    Link lnkConfigureNicOTP = Link.xpath("//p[contains(@class,'asia-otp-label')]/a");
    Label lblTransactionHistoryMenu = Label.xpath("//div[@class='my-account-menu']//ul[contains(@class,'dropdown')]//a[text()='Transaction History']");
    String menuXpath = "//div[@class='leftmenu']//div[contains(@page-title,'%s')]/a";

    public <T> T clickSubMenu(String menu, String submenu, Class<T> expectedPage) {
        leftMenuList.clickSubMenu(menu, submenu);
        return PageFactory.initElements(DriverManager.getDriver(), expectedPage);
    }

//    public void waitingLoadingSpinner() {
//        iconLoadSpinner.waitForControlInvisible(2, 2);
//    }


    public List<String> defineBalanceInfoQuickSearch(boolean isCredit) {
        if (isCredit)
            return defineBalanceInfoCreditQuickSearch();
        return defineBalanceInfoCreditCashQuickSearch();
    }

    private List<String> defineBalanceInfoCreditCashQuickSearch() {
        List<ArrayList<String>> lstDownlineInfo = ProfileUtils.getDownlineBalanceInfo();
        String level;
        List<String> lst = new LinkedList<String>(Arrays.asList(
                "Available Balance",
                "My Outstanding",
                "Total Outstanding",
                "Today Win Loss",
                "Yesterday Win Loss"
        ));
        // define downline Credit Used
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Available Balance"));
            } else {
                lst.add(String.format("Total %s Available Balance", level));
            }

        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);

            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member A/C/S/I", level));
            } else
                lst.add(String.format("Total %s A/C/S/I/B", level));
        }
        return lst;
    }

    private List<String> defineBalanceInfoCreditQuickSearch() {
        List<ArrayList<String>> lstDownlineInfo = ProfileUtils.getDownlineBalanceInfo();
        String level;
        List<String> lst = new LinkedList<String>(Arrays.asList(
                "Downline Balance",
                "Yesterday Downline Balance",
                "Total Balance",
                "Transferable Balance",
                "My Outstanding",
                "Total Outstanding",
                "Today Win Loss",
                "Yesterday Win Loss",
                "My Credit"
        ));
        // define downline Credit Used
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Credit Used"));
            } else {
                lst.add(String.format("Total %s Credit Used", level));
            }
        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member A/C/S/I", level));
            } else
                lst.add(String.format("Total %s A/C/S/I/B", level));
        }
        return lst;
    }

    @Override
    public void navigatePS38SportsResultsPage() {
        clickSubMenu(AGENCY_MANAGEMENT, PS38_SPORTS_RESULTS);
    }

    @Override
    public boolean isDisplayPS38SportsResults() {
        return leftMenuList.isSubMenuDisplay(AGENCY_MANAGEMENT, PS38_SPORTS_RESULTS);
    }

    @Override
    public void navigateWinLossSimplePage() {
        clickSubMenu(REPORT, WIN_LOSS_NEWUI, WIN_LOSS_SIMPLE_NEWUI);
    }

    @Override
    public void navigateWinLossAnalysisPage() {
        clickSubMenu(REPORT, WIN_LOSS_NEWUI, WIN_LOSS_SIMPLE_NEWUI);
    }

    public void navigateWinLossBySportAndMarketTypePage() {
        clickSubMenu(REPORT, WIN_LOSS_NEWUI, WIN_LOSS_BY_MARKET_TYPE_NEWUI);
    }

    public void navigateStatementReportPage() {
        clickSubMenu(AGENCY_MANAGEMENT, STATEMENT_REPORT);
    }

    public void navigateWinLossDetailPage() {
        String winLossDetailMenu;
        AccountInfo accountInfo = ProfileUtils.getProfile();
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(ProfileUtils.getAppName(), accountInfo.getUserCode(), accountInfo.getUserID());
        winLossDetailMenu = String.format(WIN_LOSS_BY_DETAIL_NEWUI, ProfileUtils.convertDownlineByBrand(listAccount.get(0).getLevel(), ProfileUtils.getAppName()));
        clickSubMenu(REPORT, winLossDetailMenu);
    }

    public void navigateWinLossByEventPage() {
        clickSubMenu(REPORT, WIN_LOSS_BY_EVENT_NEWUI);
    }

    public void navigateClientLedgerPage() {
        clickSubMenu(AGENCY_MANAGEMENT, CLIENT_LEDGER);
    }

    public boolean isListSubMenuDisplayCorrect(String menu) {
        if(menu.equals(AGENCY_MANAGEMENT)){
            List<String> lstSubMenu = leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
            return lstSubMenu.equals(AGConstant.AgencyManagement.LIST_SUBMENU_AGENCY_MANAGEMENT_NEW);
        }
        if(menu.equals(RISK_MANAGEMENT)){
            List<String> lstSubMenu = leftMenuList.getListSubMenu(RISK_MANAGEMENT);
            return lstSubMenu.equals(AGConstant.RiskManagement.LIST_SUBMENU_RISK_MANAGEMENT_NEWUI);
        }
        if(menu.equals(REPORT)) {
            List<String> lstSubReprotMenu = leftMenuList.getListSubMenu(REPORT);
            return lstSubReprotMenu.equals(AGConstant.Report.LIST_SUB_MENU_CONTROL_BLOCKING_NEWUI);
        }
        if(menu.equals(MARKET_MANAGEMENT)){
            List<String> lstSubMenu = leftMenuList.getListSubMenu(MARKET_MANAGEMENT);
            return lstSubMenu.equals(AGConstant.MarketsManagement.LIST_SUBMENU_MARKETS_MANAGEMENT);
        }
        if(menu.equals(FRAUD_DETECTION)){
            List<String> lstSubMenu = leftMenuList.getListSubMenu(FRAUD_DETECTION);
            return lstSubMenu.equals(AGConstant.FraudDetection.LIST_SUBMENU_FRAUD_DETECTION);
        }
        return false;
    }

    public void verifyListSubMenuDisplayCorrect() {
        List<String> lstMenu = Arrays.asList(AGENCY_MANAGEMENT, RISK_MANAGEMENT, REPORT, MARKET_MANAGEMENT, FRAUD_DETECTION);
        for (String menu : lstMenu) {
            Assert.assertTrue(isListSubMenuDisplayCorrect(menu));
        }
    }

    public void navigateIPMonitoringPage() {
        clickSubMenu(RISK_MANAGEMENT, IP_MONITORING);
    }

    public void navigateMonitoredAccountsPage() {
        clickSubMenu(RISK_MANAGEMENT, MONITORED_ACCOUNT);
    }

    @Override
    public void navigateTransactionHistoryPage() {
        btnMyAccount.moveAndHoverOnControl();
        lblTransactionHistoryMenu.click();
    }

    @Override
    public void navigateSubUserListingPage() {
        btnMyAccount.moveAndHoverOnControl();
        lblSubUserMenu.click();
    }
}
