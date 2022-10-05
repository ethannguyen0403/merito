package agentsite.pages.all.components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.utils.StringUtils;
import agentsite.controls.LefMenuList;
import org.openqa.selenium.support.PageFactory;
import agentsite.pages.all.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.all.agentmanagement.CreateUserPage;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.SubUserListingPage;
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
    public Link lnkConfigureNickname = Link.xpath("//p[contains(@class,'nickname')]/a");
    public Link lnkConfigureNicOTP = Link.xpath("//p[contains(@class,'asia-otp-label')]/a");
    public Label lblTransactionHistoryMenu = Label.xpath("//div[@class='my-account-menu']//ul[contains(@class,'dropdown')]//a[text()='Transaction History']");
    //public Menu menuAgencyManagement = Menu.xpath("//div[@id='agency']//div[@class='asia-menu-title']/span[2]");
    String menuXpath = "//div[@class='leftmenu']//div[contains(@page-title,'%s')]/a";

    /*public Label subMenuCreateCompany = Label.xpath(String.format(menuXpath,"Create Company"));
        public Label subMenuCreateDownLineAgent = Label.xpath(String.format(menuXpath,"Create Downline Agent"));
        public Label subMenuCreateUser = Label.xpath(String.format(menuXpath,"Create User"));
        public Label subMenuDownLineListing = Label.xpath(String.format(menuXpath,"Downline Listing"));
        public Label subMenuPositionTakingListing =  Label.xpath(String.format(menuXpath,"Position Taking Listing"));
        public Label subMenuDepositWithdrawal =  Label.xpath(String.format(menuXpath,"Deposit/Withdraw"));
        public Label subMenuTransfer =  Label.xpath(String.format(menuXpath,"Transfer"));
        public Label subMenuBetSettingListing = Label.xpath(String.format(menuXpath,"Bet Setting Listing"));
        public Label subMenuCommissionSettingListing =  Label.xpath(String.format(menuXpath,"Commission Listing"));
        public Label subMenuTaxSettingListing =  Label.xpath(String.format(menuXpath,"Tax Settings"));
        public Label subMenuSubUserListing =  Label.xpath(String.format(menuXpath,"Sub User Listing"));
        public Label subMenuExpandAgencyManagement =  Label.xpath("//div[@id='agency']/following-sibling::div[@class='submenu']//div[@class='collapse-icon']");
        public Label subMenuFollowBets =  Label.xpath(String.format(menuXpath,"Follow Bets"));
        public Label subMenuCreditBalanceListing =  Label.xpath(String.format(menuXpath,"Credit/Balance Listing"));
        public Label subMenuShadowPlayersListing = Label.xpath(String.format(menuXpath,"Shadow Player Listing"));
        public Label subMenuPTPlayersListing = Label.xpath(String.format(menuXpath,"PT Player Listing"));
        public Label subMenuRiskSettingListing = Label.xpath(String.format(menuXpath,"Risk Setting Listing"));
        public Label subMenuEventBetSizeSetting =  Label.xpath(String.format(menuXpath,"Event Bet Size Settings"));
        public Label subMenuAnnouncement =  Label.xpath(String.format(menuXpath,"Announcement"));
        public Label menuRiskManagement =  Label.xpath("//span[contains(@class,'icon-risk')]/parent::div/span[2]");
        public Label subMenuAnalysisOfRunningMarkets = Label.xpath(String.format(menuXpath,"Analysis of Running Markets"));
        public Label subMenuAgentExposureLimit = Label.xpath(String.format(menuXpath,"Agent Exposure Limit"));

        public Label menuReport = Label.xpath("//span[contains(@class,'icon-report')]/parent::div/span[2]");
        public Label subMenuUnsettleBet = Label.xpath(String.format(menuXpath,"Unsettled Bet"));
        public Label subMenuProfitAndLoss = Label.xpath(String.format(menuXpath,"Profit And Loss"));
        public Label subMenuWinLoss = Label.xpath("//div[@class='leftmenu']//a[text()='Win Loss']");
        public Label subMenuWinLossSimple = Label.xpath(String.format(menuXpath,"Win Loss Simple"));
        public Label subMenuWinLossDetail = Label.xpath(String.format(menuXpath,"Win Loss Detail"));
        public Label subMenuPositionTakingReport = Label.xpath(String.format(menuXpath,"Position Taking Report"));
        public Label subMenuCancelledBets =Label.xpath(String.format(menuXpath,"Cancelled Bets"));
        public Label subMenuStatementReport = Label.xpath(String.format(menuXpath,"Statement Report"));
        public Label subMenuTransferLog = Label.xpath(String.format(menuXpath,"Transfer Log"));
        public Label subMenuBFVoidedDiscrepancy = Label.xpath(String.format(menuXpath,"BF Voided Discrepancy"));
        public Label subMenuPortalSummary = Label.xpath(String.format(menuXpath,"Portal Summarry"));
        public Label subMenuReportConfiguration = Label.xpath(String.format(menuXpath,"Report Configuration"));
        public Label subMenuViewLog = Label.xpath(String.format(menuXpath,"View Log"));
        public Label subMenuExpandReport = Label.xpath("//div[@id='report']/following-sibling::div[@class='submenu']//div[@class='collapse-icon']");
        public Label subMenuClientLedger = Label.xpath(String.format(menuXpath,"Client Ledger"));
        public Label subMenuWinLossBySportAndMarketType =Label.xpath(String.format(menuXpath,"Win Loss By Sport And Market Type"));
        public Label subMenuWinLossByEvent = Label.xpath(String.format(menuXpath,"Win Loss By Event"));
        public Label lblTransactionHIstory = Label.xpath("//div[@class='my-account-menu']//ul//a[text()='Transaction History']");
        public Label subMenuTransactionHistory = Label.xpath(String.format(menuXpath,"Transaction History"));
        public Label subMenuTopGainersTopLosers = Label.xpath(String.format(menuXpath,"Top Gainers & Top Loser"));
        public Label subMenuBigStakeConfiguration = Label.xpath(String.format(menuXpath,"Big Stake Configuration"));
        public Label subMenuFollowAndSmallBetsPerformance = Label.xpath(String.format(menuXpath,"Follow Bets Performance"));

        public Label menuMarketsManagement = Label.xpath("//span[contains(@class,'icon-markets')]/parent::div/span[2]");
        public Label subMenuBlockRacing =  Label.xpath(String.format(menuXpath,"Block Racing"));
        public Label subMenuBlockUnlockEvents =  Label.xpath(String.format(menuXpath,"Block/Unblock Events"));
        public Label subMenuCurrentBlocking = Label.xpath(String.format(menuXpath,"Current Blocking"));
        public Label subMenuBlockUnblockCompetition = Label.xpath(String.format(menuXpath,"Block/Unblock Competitions"));
        public Label subMenuLiquidityThreshold =  Label.xpath(String.format(menuXpath,"Liquidity Threshold"));
        public Label subMenuBlockingLog =  Label.xpath(String.format(menuXpath,"Blocking Log"));
        private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]/div");


        public Label menuFraudDetection = Label.xpath("//span[contains(@class,'icon-fraud')]/parent::div/span[2]");
        public Label subMenuFraudDetection = Label.xpath(String.format(menuXpath,"Fraud Detection"));
        public Label subMenuWagerOddsHistory = Label.xpath(String.format(menuXpath,"Wager Odds History"));
        public Label subMenuFraudPermission = Label.xpath(String.format(menuXpath,"Fraud Permission"));*/
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

    /************************
     * Agency Management
     ***********************//*
    public CreateDownLineAgentPage navigateCreateDownLineAgent() {
        if (subMenuCreateDownLineAgent.isInvisible(3)) {
            menuAgencyManagement.click();
        }
        subMenuCreateDownLineAgent.click();
        return new CreateDownLineAgentPage();
    }

    public CreateUserPage navigateCreateUser() {
        if (!subMenuCreateUser.isDisplayed()) {
            menuAgencyManagement.click();
        }
        subMenuCreateUser.click();
        return new CreateUserPage();
    }

    public DownLineListingPage navigateDownLineListing() {
        if (subMenuDownLineListing.isInvisible(3)) {
            menuAgencyManagement.click();
        }
        subMenuDownLineListing.click();
        return new DownLineListingPage();
    }

    public PositionTakingListingPage navigatePositionTakingListing() {
        subMenuPositionTakingListing.isDisplayed(5);
        if (subMenuPositionTakingListing.isInvisible(3)) {
            menuAgencyManagement.click();
        }
        subMenuPositionTakingListing.click();
        waitingForLoading();
        return new PositionTakingListingPage();
    }
    public CommissionSettingListingPage navigateCommissionSettingListing() {
        if (!subMenuCommissionSettingListing.isDisplayed(1)){
                subMenuExpandAgencyManagement.click();
        }
        subMenuCommissionSettingListing.click();
        waitingForLoading();
        return new CommissionSettingListingPage();
    }
    public TaxSettingListingPage navigateTaxSettingListing() {
        subMenuTaxSettingListing.isDisplayed(5);
        if (subMenuTaxSettingListing.isInvisible(3))
            subMenuExpandAgencyManagement.click();

        subMenuTaxSettingListing.click();
        waitingForLoading();
        return new TaxSettingListingPage();
    }*/
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
   /* public BetSettingListingPage navigateBetSettingListing() {
        subMenuBetSettingListing.isDisplayed(5);
        if (subMenuBetSettingListing.isInvisible(3))
            subMenuExpandAgencyManagement.click();

        subMenuBetSettingListing.click();
        waitingForLoading();
      
        return new BetSettingListingPage();
    }

    public DepositWithdrawalPage navigateDepositWithdrawal() {
        subMenuDepositWithdrawal.isDisplayed(5);
        if (subMenuDepositWithdrawal.isInvisible(3)) {
            menuAgencyManagement.click();        }
        subMenuDepositWithdrawal.click();
        return new DepositWithdrawalPage();
    }*/
   /* public DepositWithdrawalPage navigateDepositWithdrawal(String securityCode) throws Exception {
        DepositWithdrawalPage page = navigateDepositWithdrawal();
        page.securityPopup.submitSecurityCode(StringUtils.decrypt(securityCode));
        page.waitingLoadingSpinner();
        return page;
    }

    public FollowBetsPage navigateFollowBets() {
        if (subMenuFollowBets.isInvisible(1)) {
            subMenuExpandAgencyManagement.click();
        }
        subMenuFollowBets.click();
        waitingForLoading();
      
        return new FollowBetsPage();
    }

    public ShadowPlayersListingPage navigateShadowPlayersListing() {
        subMenuShadowPlayersListing.isDisplayed(1);
        if (subMenuShadowPlayersListing.isInvisible(1)) {
            menuAgencyManagement.click();        }
        subMenuShadowPlayersListing.click();
        waitingForLoading();
      
        return new ShadowPlayersListingPage();
    }

    public PTPlayersListingPage navigatePTlayersListing() {
        subMenuPTPlayersListing.isDisplayed(1);
        if (subMenuPTPlayersListing.isInvisible(1)) {
            menuAgencyManagement.click();        }
        subMenuPTPlayersListing.click();
        waitingForLoading();
      
        return new PTPlayersListingPage();
    }

    public EventBetSizeSettingsPage navigateEventBetSiteSettings() {
        if (subMenuEventBetSizeSetting.isInvisible(1)) {
            menuAgencyManagement.click();        }
        subMenuEventBetSizeSetting.click();
        waitingForLoading();
        return new EventBetSizeSettingsPage();
    }
    *//************************
     * Report
     ***********************//*
    public UnsettledBetPage navigateUnsettledBet() {
        if (subMenuUnsettleBet.isInvisible(3)) {
            menuReport.click();
        }
        subMenuUnsettleBet.click();
        waitingForLoading();
      
        return new UnsettledBetPage();
    }

    public ProfitAndLossPage navigateProfitAndLoss() {
        if (subMenuProfitAndLoss.isInvisible(3)) {
            menuReport.click();
        }
        subMenuProfitAndLoss.click();
        waitingForLoading();
      
        return new ProfitAndLossPage();
    }

    public WinLossSimplePage navigateWinLossSimple() {
        if (subMenuWinLossSimple.isInvisible(3)) {
            menuReport.click();
            subMenuWinLoss.click();
        }
        subMenuWinLossSimple.click();
        waitingForLoading();
        return new WinLossSimplePage();
    }

    public WinLossDetailPage navigateWinLossDetail() {
        if (subMenuWinLossDetail.isInvisible(3)) {
            menuReport.click();
            subMenuWinLoss.click();
        }
        subMenuWinLossDetail.click();
        waitingForLoading();
        return new WinLossDetailPage();
    }
    public PositionTakingReportPage navigatePositionTakingReport() {
        if (subMenuPositionTakingReport.isInvisible(3)) {
            menuReport.click();
        }
        subMenuPositionTakingReport.click();
        waitingForLoading();
        return new PositionTakingReportPage();
    }

    public CancelledBetsPage navigateCancelledBets() {
        if (subMenuCancelledBets.isInvisible(3)) {
            menuReport.click();
        }
        subMenuCancelledBets.click();
        waitingForLoading();
        return new CancelledBetsPage();
    }
    public StatementReportPage navigateStatementReport() {
        if (subMenuStatementReport.isInvisible(3)) {
            menuReport.click();
        }
        subMenuStatementReport.click();
        waitingForLoading();
        return new StatementReportPage();
    }
    public TransferLogPage navigateTransferLog() {
        if (subMenuTransferLog.isInvisible(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuTransferLog.click();
        waitingForLoading();
        return new TransferLogPage();
    }
    public PortalSummaryPage navigatePortalSummary() {
        if (subMenuPortalSummary.isInvisible(3)) {
            menuReport.click();
        }
        subMenuPortalSummary.click();
        waitingForLoading();
        return new PortalSummaryPage();
    }
    public ReportConfigurationPage navigateReportConfiguration() {
        if (subMenuPortalSummary.isInvisible(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuPortalSummary.click();
        subMenuReportConfiguration.click();
        waitingForLoading();
        return new ReportConfigurationPage();
    }
    public BFVoidedDiscrepancyPage navigateBFVoidedDiscrepancy() {
        if (!subMenuBFVoidedDiscrepancy.isDisplayed(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuBFVoidedDiscrepancy.click();
        return new BFVoidedDiscrepancyPage();
    }
    public ViewLogPage navigateViewLog() {
        if (!subMenuViewLog.isDisplayed(1)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuViewLog.click();
        waitingForLoading();
        return new ViewLogPage();
}
    public FollowBetPerformancePage navigateFollowAndSmallBetsPerformance() {
        if (!subMenuFollowAndSmallBetsPerformance.isDisplayed(1)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuFollowAndSmallBetsPerformance.click();
        waitingForLoading();
        return new FollowBetPerformancePage();
    }
    public ClientLedgerPage navigateClientLedger() {
        if (subMenuClientLedger.isInvisible(3)) {
            menuReport.click();
        }
        subMenuClientLedger.click();
        waitingForLoading();
        return new ClientLedgerPage();
    }

    public WinLossBySportAndMarketTypePage navigateWinLossBySportAndMarketType() {
        if (subMenuWinLossBySportAndMarketType.isInvisible(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuWinLoss.click();
        subMenuWinLossBySportAndMarketType.click();
        waitingForLoading();
        return new WinLossBySportAndMarketTypePage();
    }

    public WinLossByEventPage navigateWinLossByEvent() {
        if (subMenuWinLossByEvent.isInvisible(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuWinLoss.click();
        subMenuWinLossByEvent.click();
        waitingForLoading();
        return new WinLossByEventPage();
    }

    public AnalysisOfRunningMarketsPage navigateAnalysisOfRunningMarkets() {
        if (!subMenuAnalysisOfRunningMarkets.isDisplayed(1)) {
            menuRiskManagement.click();
        }
        subMenuAnalysisOfRunningMarkets.click();
        return new AnalysisOfRunningMarketsPage();
    }

    public TopGainersTopLosersPage navigateTopGainersTopLosers() {
        if (subMenuTopGainersTopLosers.isInvisible(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuTopGainersTopLosers.click();
        waitingLoadingSpinner();
        return new TopGainersTopLosersPage();
    }
    public BigStakeConfigurationPage navigateBigStakeConfiguration() {
        if (subMenuTopGainersTopLosers.isInvisible(3)) {
            menuReport.click();
            if (subMenuTopGainersTopLosers.isInvisible(3))
                subMenuExpandReport.click();
        }
        subMenuTopGainersTopLosers.click();
        subMenuBigStakeConfiguration.click();
        waitingLoadingSpinner();
        return new BigStakeConfigurationPage();
    }

    public TransactionHistoryPage navigateTransactionHistory() {
        btnMyAccount.click();
        lblTransactionHIstory.click();
        waitingForLoading();
        return new TransactionHistoryPage();
    }

    *//************************
     * Markets Management
     ***********************//*
    public BlockRacingPage navigateBlockRacing() {
        if (subMenuBlockRacing.isInvisible(3)) {
            menuMarketsManagement.click();
        }
        subMenuBlockRacing.click();
        waitingForLoading();
        return new BlockRacingPage();
    }

    public BlockUnblockEventPage navigateBlockUnblockEvents() {
        if (subMenuBlockUnlockEvents.isInvisible(3)) {
            menuMarketsManagement.click();
        }
        subMenuBlockUnlockEvents.click();
        BlockUnblockEventPage page = new BlockUnblockEventPage();
        waitingForLoading();
      
        return new BlockUnblockEventPage();
    }

    public CurrentBlockingPage navigateCurrentBlocking() {
        if (subMenuCurrentBlocking.isInvisible(3)) {
            menuMarketsManagement.click();
        }
        subMenuCurrentBlocking.click();
        waitingForLoading();
        CurrentBlockingPage page = new CurrentBlockingPage();
      
        return page;
    }
    public BlockUnblockCompetitionPage navigateBlockUnblockCompetition() {
        if (subMenuBlockUnblockCompetition.isInvisible(3)) {
            menuMarketsManagement.click();
        }
        subMenuBlockUnblockCompetition.click();
        waitingForLoading();
        BlockUnblockCompetitionPage page = new BlockUnblockCompetitionPage();
      
        return page;
    }

    public LiquidityThresholdPage navigateLiquidityThreshold() {
        if (subMenuLiquidityThreshold.isInvisible(3)) {
            menuMarketsManagement.click();
        }
        subMenuLiquidityThreshold.click();
        waitingForLoading();
        LiquidityThresholdPage page = new LiquidityThresholdPage();
        return page;
    }
    public BlockingLogPage navigateBlockingLog() {
        if (subMenuBlockingLog.isInvisible(3)) {
            menuMarketsManagement.click();
        }
        subMenuBlockingLog.click();
        waitingForLoading();
      
        return new BlockingLogPage();
    }
    *//************************
     * Fraud Detection
     ***********************//*
    public FraudDetectionPage navigateFraudDetection() {
        if (subMenuFraudDetection.isInvisible(3)) {
            menuFraudDetection.click();
        }
        subMenuFraudDetection.click();
        FraudDetectionPage page = new FraudDetectionPage();
        waitingForLoading();
        page.waitingLoadingSpinner();
      
        return page;
    }
    public WagerOddsHistoryPage navigateWagerOddsHistory() {
        if (subMenuWagerOddsHistory.isInvisible(3)) {
            menuFraudDetection.click();
        }
        subMenuWagerOddsHistory.click();
        WagerOddsHistoryPage page = new WagerOddsHistoryPage();
        waitingForLoading();
        page.waitingLoadingSpinner();
      
        return page;
    }

    public FraudPermissionPage navigateFraudPermission() {
        if (subMenuFraudPermission.isInvisible(3)) {
            menuFraudDetection.click();
        }
        subMenuFraudPermission.click();
        FraudPermissionPage page = new FraudPermissionPage();
        waitingForLoading();
        page.waitingLoadingSpinner();
      
        return page;
    }



    */

    /************************
     * Old UI
     ***********************//*
    public SubUserListingPage navigateSubUserListingoldUI() {
        if (subMenuSubUserListing.isInvisible(3))
            subMenuExpandAgencyManagement.click();
        subMenuSubUserListing.click();
        waitingForLoading();
        return new SubUserListingPage();
    }
    public CreateDownLineAgentPageOldUI navigateCreateDownLineAgentOldUI() {
        if (subMenuCreateDownLineAgent.isInvisible(3)) {
            menuAgencyManagement.click();
        }
        subMenuCreateDownLineAgent.click();
        return new CreateDownLineAgentPageOldUI();
    }
    public CreateUserPageOldUI navigateCreateUserOldUI() {
        if (!subMenuCreateUser.isDisplayed()) {
            menuAgencyManagement.click();
        }
        subMenuCreateUser.click();
      
        return new CreateUserPageOldUI();
    }

    public WinLossSimplePage navigateWinLossSimpleOldUI() {
        if (subMenuWinLossSimple.isInvisible(3)) {
            menuReport.click();
        }
        subMenuWinLossSimple.click();
        waitingForLoading();
        return new WinLossSimplePage();
    }
    public WinLossDetailPage navigateWinLossDetailOldUI() {
        if (subMenuWinLossDetail.isInvisible(3)) {
            menuReport.click();
        }
        subMenuWinLossDetail.click();
        waitingForLoading();
        return new WinLossDetailPage();
    }
    public WinLossByEventPage navigateWinLossByEventOldUI() {
        if (subMenuWinLossByEvent.isInvisible(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuWinLossByEvent.click();
        waitingForLoading();
        return new WinLossByEventPage();
    }
    public WinLossBySportAndMarketTypePage navigateWinLossBySportAndMarketTypeOldUI() {
        if (subMenuWinLossBySportAndMarketType.isInvisible(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuWinLossBySportAndMarketType.click();
        waitingForLoading();
        return new WinLossBySportAndMarketTypePage();
    }
    public AnalysisOfRunningMarketsPage navigateAnalysisOfRunningMArketsOldUI() {
        if (!subMenuAnalysisOfRunningMarkets.isDisplayed(3)) {
            menuReport.click();
            subMenuExpandReport.click();
        }
        subMenuAnalysisOfRunningMarkets.click();
        return new AnalysisOfRunningMarketsPage();
    }
    public TransactionHistoryPage navigateTransactionHistorOldUIy() {
        if (subMenuTransactionHistory.isInvisible(3)) {
            menuReport.click();
            if (subMenuTransactionHistory.isInvisible(3))
                subMenuExpandReport.click();
        }
        subMenuTransactionHistory.click();
        waitingForLoading();
        return new TransactionHistoryPage();
    }
*/
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
