package agentsite.pages.all.components;

import agentsite.pages.all.agentmanagement.*;
import agentsite.pages.all.home.AccountBalancePage;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.utils.StringUtils;
import agentsite.controls.LefMenuList;
import org.openqa.selenium.support.PageFactory;
import agentsite.pages.all.home.HomePage;
import agentsite.ultils.account.ProfileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;

public class LeftMenu extends HeaderAgent {

    public Label lblAppLeftMenu = Label.xpath("//div[@class='side-left']//app-left-menu");
    public Image imgLogo = Image.xpath("//app-left-menu//span[@class='applogo']");
    public Button tabMainMenu = Button.xpath("//app-left-menu//div[@class='menu-mode-container']//button[1]");
    public Button tabQuickSearch = Button.xpath("//app-left-menu//div[@class='menu-mode-container']//button[2]");
    public Link lnkConfigureOTP = Link.xpath("//p[contains(@class,'asia-otp-label')]/a");
    public Label lblWelcome = Label.xpath("//p[contains(@class,'greeting')]");
    public Label lblLoginID = Label.xpath("//p[contains(@class,'usercode')]");
    public Button btnMyAccount = Button.xpath("//button[contains(@class,'my-account')]");
    public Label lblSubUserMenu = Label.xpath("//div[@class='my-account-menu']//ul[contains(@class,'dropdown')]//a[text()='Sub User']");
    public Label lblSummary = Label.xpath("//div[@class='my-account-menu']//ul[contains(@class,'dropdown')]//a[text()='Summary']");
    public Link lnkConfigureNickname = Link.xpath("//p[contains(@class,'nickname')]/a");
    public Link lnkConfigureNicOTP = Link.xpath("//p[contains(@class,'asia-otp-label')]/a");
    public Label lblTransactionHistoryMenu = Label.xpath("//div[@class='my-account-menu']//ul[contains(@class,'dropdown')]//a[text()='Transaction History']");
    String menuXpath = "//div[@class='leftmenu']//div[contains(@page-title,'%s')]/a";

    public LefMenuList leftMenuList = LefMenuList.xpath("//div[@class='leftmenu']/div[3]");

    public <T> T clickSubMenu(String menu, String submenu, Class<T> expectedPage) {
        leftMenuList.clickSubMenu(menu, submenu);
        waitingForLoading();
        return PageFactory.initElements(DriverManager.getDriver(), expectedPage);
    }

    public boolean isMenuExpanded(String menu) {
        String attribute = leftMenuList.getmenuAtribuite(menu, "class");
        return attribute.contains("active");
    }

    public HomePage navigateHome() {
        iconHome.click();
        // waiting for loading completely
        iconHome.isInvisible(2);
        return new HomePage();
    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public void switchMainMenu() {
        tabMainMenu.click();
        waitingLoadingSpinner();
    }

    public QuickSearch switchQuickSearch() {
        tabQuickSearch.click();
        return new QuickSearch();
    }

    public DepositWithdrawalPage navigateDepositWithdrawal(String securityCode) throws Exception {
        DepositWithdrawalPage page = clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, DepositWithdrawalPage.class);
        if (!securityCode.isEmpty()) {
            page.securityPopup.submitSecurityCode(StringUtils.decrypt(securityCode));
        }
        waitingForLoading();
        return page;
    }

    public CreateDownLineAgentPage navigateCreateDownLineAgentPage(String securityCode) throws Exception {
        CreateDownLineAgentPage page = clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);
        if (page.securityPopup.isDisplayed()) {
            if (!securityCode.isEmpty()) {
                page.securityPopup.submitSecurityCode(StringUtils.decrypt(securityCode));
            }
        }
        waitingForLoading();
        return page;
    }

    public CreditBalanceListingPage navigateCreditBalanceListingPage(String securityCode) throws Exception {
        CreditBalanceListingPage page = clickSubMenu(AGENCY_MANAGEMENT, CREDIT_BALANCE_LISTING, CreditBalanceListingPage.class);
        if (page.securityPopup.isDisplayed()) {
            if (!securityCode.isEmpty()) {
                page.securityPopup.submitSecurityCode(StringUtils.decrypt(securityCode));
            }
        }
        waitingForLoading();
        return page;
    }

    public TransferPage navigateTransferPage(String securityCode) throws Exception {
        TransferPage page = clickSubMenu(AGENCY_MANAGEMENT, TRANSFER, TransferPage.class);
        if (page.securityPopup.isDisplayed()) {
            if (!securityCode.isEmpty()) {
                page.securityPopup.submitSecurityCode(StringUtils.decrypt(securityCode));
            }
        }
        waitingForLoading();
        return page;
    }


    public CreateUserPage navigateCreateUserPage(String securityCode) throws Exception {
        CreateUserPage page = clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);
        if (page.securityPopup.isDisplayed()) {
            if (!securityCode.isEmpty()) {
                page.securityPopup.submitSecurityCode(StringUtils.decrypt(securityCode));
            }
        }
        waitingForLoading();
        return page;
    }
    public SubUserListingPage navigateSubUserListing() {
        SubUserListingPage page;
        if (ProfileUtils.getNewUI() == 1) {
            btnMyAccount.click();
            if (lblSubUserMenu.isDisplayed()) {
                lblSubUserMenu.click();
                waitingForLoading();
            }
        } else {
            clickSubMenu(AGENCY_MANAGEMENT, SUB_USER_LISTING, SubUserListingPage.class);
        }
        return new SubUserListingPage();
    }

    private void waitingForLoading() {
        waitingLoadingSpinner();
    }

    public List<String> defineBalanceInfoQuickSearch(boolean isCredit){
        if(isCredit)
            return defineBalanceInfoCreditQuickSearch();
        return defineBalanceInfoCreditCashQuickSearch();
    }

    private List<String> defineBalanceInfoCreditCashQuickSearch(){
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
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);
            if(level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Available Balance"));
            }else {
                lst.add(String.format("Total %s Available Balance", level));
            }

        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);

            if(level.equalsIgnoreCase("PL")){
                lst.add(String.format("Total Member A/C/S/I", level));
            }else
                lst.add(String.format("Total %s A/C/S/I/B", level));
        }
        return lst;
    }
    private List<String> defineBalanceInfoCreditQuickSearch(){
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
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);
            if(level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Credit Used"));
            }else {
                lst.add(String.format("Total %s Credit Used", level));
            }
        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);
            if(level.equalsIgnoreCase("PL")){
                lst.add(String.format("Total Member A/C/S/I", level));
            }else
                lst.add(String.format("Total %s A/C/S/I/B", level));
        }
        return lst;
    }
}
