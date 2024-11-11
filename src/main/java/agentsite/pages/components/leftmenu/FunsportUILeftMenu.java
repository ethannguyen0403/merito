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

public class FunsportUILeftMenu extends LeftMenu {
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

    public void navigatePS38SportsResultsPage() {
        clickSubMenu(REPORT, PS38_SPORTS_RESULTS);
    }

    @Override
    public boolean isDisplayPS38SportsResults() {
        return leftMenuList.isSubMenuDisplay(REPORT, PS38_SPORTS_RESULTS);
    }

    @Override
    public void navigateWinLossSimplePage() {
        clickSubMenu(REPORT, WIN_LOSS_SIMPLE_OLDUI);
    }

    public void navigateWinLossBySportAndMarketTypePage() {
        clickSubMenu(REPORT, WIN_LOSS_BY_MARKET_TYPE_OLDUI);
    }

    public void navigateStatementReportPage() {
        clickSubMenu(REPORT, STATEMENT_REPORT);
    }

    public void navigateWinLossDetailPage() {
        String winLossDetailMenu;
        AccountInfo accountInfo = ProfileUtils.getProfile();
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(ProfileUtils.getAppName(), accountInfo.getUserCode(), accountInfo.getUserID());
        winLossDetailMenu = String.format(WIN_LOSS_BY_DETAIL_OLDUI, ProfileUtils.convertDownlineByBrand(listAccount.get(0).getLevel(), ProfileUtils.getAppName()));
        clickSubMenu(REPORT, winLossDetailMenu);
    }

    public void navigateWinLossByEventPage() {
        clickSubMenu(REPORT, WIN_LOSS_BY_EVENT_OLDUI);
    }

    public void navigateClientLedgerPage() {
        clickSubMenu(REPORT, CLIENT_LEDGER);
    }

    public boolean isListSubMenuDisplayCorrect(String menu) {
        if(menu.equals(AGENCY_MANAGEMENT)){
            List<String> lstSubMenu = leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
            return lstSubMenu.equals(AGConstant.AgencyManagement.LIST_SUBMENU_AGENCY_MANAGEMENT_FUNSPORT);
        }
        if(menu.equals(REPORT)) {
            List<String> lstSubReprotMenu = leftMenuList.getListSubMenu(REPORT);
            return lstSubReprotMenu.equals(AGConstant.Report.LIST_SUB_MENU_CONTROL_BLOCKING_FUNSPORT);
        }
        return false;
    }

    public void verifyListSubMenuDisplayCorrect() {
        List<String> lstMenu = Arrays.asList(AGENCY_MANAGEMENT,REPORT);
        for (String menu : lstMenu) {
            Assert.assertTrue(isListSubMenuDisplayCorrect(menu), String.format("FAILED! List Sub Menu of %s displays incorrect",menu));
        }
    }
    @Override
    public void navigateTransactionHistoryPage() {
        clickSubMenu(REPORT, TRANSACTION_HISTORY);
    }

    @Override
    public void navigateSubUserListingPage() {
        clickSubMenu(AGENCY_MANAGEMENT, SUB_USER_LISTING);
    }

    @Override
    public void navigateMonitoredAccountsPage() {
        clickSubMenu(REPORT, MONITORED_ACCOUNT);
    }

    @Override
    public void navigateAgentExposureLimitPage() {
        clickSubMenu(REPORT, AGENT_EXPOSURE_LIMIT);
    }
}
