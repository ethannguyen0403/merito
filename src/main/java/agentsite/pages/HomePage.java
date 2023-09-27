package agentsite.pages;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.*;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.quicksearch.QuickSearch;
import agentsite.pages.components.SecurityPopup;
import agentsite.pages.components.header.Header;
import agentsite.pages.components.leftmenu.LeftMenu;
import agentsite.pages.marketsmanagement.*;
import agentsite.pages.report.*;
import agentsite.pages.riskmanagement.AgentExposureLimitPage;
import agentsite.pages.riskmanagement.NetExposurePage;
import agentsite.pages.riskmanagement.VolumeMonitorPage;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Icon;
import com.paltech.utils.StringUtils;

import static common.AGConstant.HomePage.*;

public class HomePage extends LoginPage {
    public Header header;
    public LeftMenu leftMenu;
    public QuickSearch quickSearch;
    public int colName = 1;
    public int colValue = 2;
    protected String successIcon = "//span[contains(@class,'psuccess')]";
    protected String errorIcon = "//span[contains(@class,'perror')]";
    public static SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    static Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    private int totalCol = 2;
    Table tblSMAInfo = Table.xpath("//table[@class='ptable report ng-scope']", totalCol);
    public HomePage(String types) {
        super(types);
//        footer = ComponentsFactory.footerObject(_type);
        header = ComponentsFactory.headerObject(_type);
        leftMenu = ComponentsFactory.leftMenuObject(_type);
        quickSearch = ComponentsFactory.quickSearchObject(_type);
    }

    public static void confirmSecurityCode(String securityCode) {
        try {
            securityPopup.submitSecurityCode(StringUtils.decrypt(securityCode));
            waitingLoadingSpinner();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LoginPage logout() {
        this.header.logout();
        return new LoginPage(this._type);
    }

    public AccountBalancePage clickHomeIcon() {
        header.clickHomeIcon();
        DriverManager.getDriver().switchToWindow();
        return new AccountBalancePage(_type);
    }

    public DownLineListingPage clickDownLineListing() {
        quickSearch.clickDownLineListing();
        DownLineListingPage page = new DownLineListingPage(_type);
        page.waitingLoadingSpinner();
        ;
        return page;
    }

    public ProfitAndLossPage clickProfitAndLossinQuickSearch() {
        quickSearch.clickProfitAndLoss();
        ProfitAndLossPage page = new ProfitAndLossPage(_type);
        page.waitingLoadingSpinner();
        return page;
    }

    public ClientLedgerPage clickClientLedgerinQuickSearch() {
        quickSearch.clickClientLedger();
        ClientLedgerPage page = new ClientLedgerPage(_type);
        page.waitingLoadingSpinner();
        return page;
    }

    public UpdateSecurityCodePage clickSecurityCodeHeaderMenu(String securityCode) {
        header.clickSecurityCodeHeaderMenu(securityCode);
        return new UpdateSecurityCodePage(_type);
    }

    public DownLineListingPage navigateDownlineListingPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING);
        waitingLoadingSpinner();
        return new DownLineListingPage(_type);
    }

    public AnnoucementPage navigateAnnoucementPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT);
        waitingLoadingSpinner();
        return new AnnoucementPage(_type);
    }

    public CreditBalanceListingPage navigateCreditBalanceListingPage(String securityCode) {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, CREDIT_BALANCE_LISTING);
        CreditBalanceListingPage page = new CreditBalanceListingPage(_type);
        page.confirmSecurityCode(securityCode);
        return page;
    }

    public CreateUserPage navigateCreateUserPage(String securityCode) {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER);
        CreateUserPage page = new CreateUserPage(_type);
        page.confirmSecurityCode(securityCode);
        return page;
    }

    public DepositWithdrawalPage navigateDepositWithdrawalPage(String securityCode) {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW);
        DepositWithdrawalPage page = new DepositWithdrawalPage(_type);
        page.confirmSecurityCode(securityCode);
        return page;
    }

    public EventBetSizeSettingsPage navigateEventBetSizeSettingsPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, EVENT_BET_STIE_SETTINGS);
        waitingLoadingSpinner();
        return new EventBetSizeSettingsPage(_type);
    }

    public FollowBetsPage navigateFollowBetsPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, FOLLOW_BETS);
        waitingLoadingSpinner();
        return new FollowBetsPage(_type);
    }

    public PositionTakingListingPage navigatePositionTakingListingPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING);
        waitingLoadingSpinner();
        return new PositionTakingListingPage(_type);
    }

    public TransferPage navigateTransferPage(String securityCode) {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, TRANSFER);
        TransferPage page = new TransferPage(_type);
        page.confirmSecurityCode(securityCode);
        return page;
    }

    public SubUserListingPage navigateSubUserListingPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, SUB_USER_LISTING);
        waitingLoadingSpinner();
        return new SubUserListingPage(_type);
    }

    public TaxSettingListingPage navigateTaxSettingListingPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, TAX_SETTING_LISTING);
        waitingLoadingSpinner();
        return new TaxSettingListingPage(_type);
    }

    public RiskSettingListingPage navigateRiskSettingListingPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, RISK_SETTING_LISTING);
        waitingLoadingSpinner();
        return new RiskSettingListingPage(_type);
    }

    public BetSettingListingPage navigateBetSettingListingPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING);
        waitingLoadingSpinner();
        return new BetSettingListingPage(_type);
    }

    public CommissionSettingListingPage navigateCommissionSettingListingPage() {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, COMMISSION_LISTING);
        waitingLoadingSpinner();
        return new CommissionSettingListingPage(_type);
    }

    public BlockUnblockEventPage navigateBlockUnblockEventsPage() {
        leftMenu.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT);
        waitingLoadingSpinner();
        return new BlockUnblockEventPage(_type);
    }

    public BlockingLogPage navigateBlockingLogPage() {
        leftMenu.clickSubMenu(MARKET_MANAGEMENT, BLOCKING_LOG);
        waitingLoadingSpinner();
        return new BlockingLogPage(_type);
    }

    public BlockRacingPage navigateBlockRacingPage() {
        leftMenu.clickSubMenu(MARKET_MANAGEMENT, BLOCK_RACING);
        waitingLoadingSpinner();
        return new BlockRacingPage(_type);
    }

    public DownLineListingPage navigateDownlineListingFromQuickSearch() {
        quickSearch.clickDownLineListing();
        waitingLoadingSpinner();
        return new DownLineListingPage(_type);
    }

    public CreateDownLineAgentPage navigateCreateDownLineAgentPage(String securityCode) {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT);
        CreateDownLineAgentPage page = new CreateDownLineAgentPage(_type);
        page.confirmSecurityCode(securityCode);
        return page;
    }

    public BlockUnblockCompetitionPage navigateBlockUnblockCompetitionPage() {
        leftMenu.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_COMPETITION);
        waitingLoadingSpinner();
        return new BlockUnblockCompetitionPage(_type);
    }

    public CurrentBlockingPage navigateCurrentBlockingPage() {
        leftMenu.clickSubMenu(MARKET_MANAGEMENT, CURRENT_BLOCKING);
        waitingLoadingSpinner();
        return new CurrentBlockingPage(_type);
    }

    public LiquidityThresholdPage navigateLiquidityThresholdPage() {
        leftMenu.clickSubMenu(MARKET_MANAGEMENT, LIQUIDITY_THRESHOLD);
        waitingLoadingSpinner();
        return new LiquidityThresholdPage(_type);
    }

    public SuspendUnsuspendMarketPage navigateSuspendUnsuspendMarketPage() {
        leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        leftMenu.clickSubMenu(MARKET_MANAGEMENT, SUSPEND_UNSUSPEND_MARKETS);
        return new SuspendUnsuspendMarketPage(_type);
    }

    public AgentExposureLimitPage navigateAgentExposureLimitPage() {
        leftMenu.clickSubMenu(RISK_MANAGEMENT, AGENT_EXPOSURE_LIMIT);
        waitingLoadingSpinner();
        return new AgentExposureLimitPage(_type);
    }

    public NetExposurePage navigateNetExposurePage() {
        leftMenu.clickSubMenu(RISK_MANAGEMENT, NET_EXPOSURE);
        waitingLoadingSpinner();
        return new NetExposurePage(_type);
    }

    public VolumeMonitorPage navigateVolumeMonitorPage() {
        leftMenu.clickSubMenu(RISK_MANAGEMENT, VOLUME_MONITOR);
        waitingLoadingSpinner();
        return new VolumeMonitorPage(_type);
    }

    public AnalysisOfRunningMarketsPage navigateAnalysisOfRunningMarketsPage() {
        leftMenu.navigateAnalysisOfRunningMarketsPage();
        waitingLoadingSpinner();
        return new AnalysisOfRunningMarketsPage(_type);
    }

    public BFVoidedDiscrepancyPage navigateBFVoidedDiscrepancyPage() {
        leftMenu.clickSubMenu(REPORT, BF_VOIDED_DISCREAPANCY);
        waitingLoadingSpinner();
        return new BFVoidedDiscrepancyPage(_type);
    }

    public BigStakeConfigurationPage navigateBigStakeConfigurationPage() {
        leftMenu.navigateBigStakeConfigurationPage();
        waitingLoadingSpinner();
        return new BigStakeConfigurationPage(_type);
    }

    public CancelledBetsPage navigateCancelledBetsPage() {
        leftMenu.clickSubMenu(REPORT, CANCELLED_BETS);
        waitingLoadingSpinner();
        return new CancelledBetsPage(_type);
    }

    public ClientLedgerPage navigateClientLedgerPage() {
        leftMenu.navigateClientLedgerPage();
        waitingLoadingSpinner();
        return new ClientLedgerPage(_type);
    }

    public FollowBetPerformancePage navigateFollowBetPerformancePage() {
        leftMenu.clickSubMenu(REPORT, CLIENT_LEDGER);
        waitingLoadingSpinner();
        return new FollowBetPerformancePage(_type);
    }

    public PositionTakingReportPage navigatePositionTakingReportPage() {
        leftMenu.clickSubMenu(REPORT, POSITION_TAKING_REPORT);
        waitingLoadingSpinner();
        return new PositionTakingReportPage(_type);
    }

    public ProfitAndLossPage navigateProfitAndLossPage() {
        leftMenu.clickSubMenu(REPORT, PROFIT_AND_LOSS);
        waitingLoadingSpinner();
        return new ProfitAndLossPage(_type);
    }

    public ReportConfigurationPage navigateReportConfigurationPage() {
        leftMenu.clickSubMenu(REPORT, "");
        return new ReportConfigurationPage(_type);
    }

    public StatementReportPage navigateStatementReportPage() {
        leftMenu.navigateStatementReportPage();
        waitingLoadingSpinner();
        return new StatementReportPage(_type);
    }

    public TopGainersTopLosersPage navigateTopGainersTopLosersPage() {
        leftMenu.clickSubMenu(REPORT, TOP_GAINER_TOP_LOSER);
        waitingLoadingSpinner();
        return new TopGainersTopLosersPage(_type);
    }

    public TransactionHistoryPage navigateTransactionHistoryPage() {
        leftMenu.clickSubMenu(REPORT, TRANSACTION_HISTORY);
        waitingLoadingSpinner();
        return new TransactionHistoryPage(_type);
    }

    public TransferLogPage navigateTransferLogPage() {
        leftMenu.clickSubMenu(REPORT, TRANSFER_LOG);
        waitingLoadingSpinner();
        return new TransferLogPage(_type);
    }

    public UnsettledBetPage navigateUnsettledBetPage() {
        leftMenu.clickSubMenu(REPORT, UNSETTLED_BET);
        waitingLoadingSpinner();
        return new UnsettledBetPage(_type);
    }

    public ViewLogPage navigateViewLogPage() {
        leftMenu.clickSubMenu(REPORT, VIEW_LOG);
        waitingLoadingSpinner();
        return new ViewLogPage(_type);
    }

    public WinLossByEventPage navigateWinLossByEventPage() {
        leftMenu.navigateWinLossByEventPage();
        waitingLoadingSpinner();
        return new WinLossByEventPage(_type);
    }

    public WinLossBySportAndMarketTypePage navigateWinLossBySportAndMarketTypePage() {
        leftMenu.navigateWinLossBySportAndMarketTypePage();
        waitingLoadingSpinner();
        return new WinLossBySportAndMarketTypePage(_type);
    }

    public WinLossDetailPage navigateWinLossDetailPage() {
        leftMenu.navigateWinLossDetailPage();
        waitingLoadingSpinner();
        return new WinLossDetailPage(_type);
    }

    public WinLossSimplePage navigateWinLossSimplePage() {
        leftMenu.navigateWinLossSimplePage();
        waitingLoadingSpinner();
        return new WinLossSimplePage(_type);
    }

    public static void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public AccountBalancePage navigateAccountBalance() {
//        AccountBalancePage page;
//        if (brandName.equalsIgnoreCase("satsport")) {
//            clickHomeIcon();
//        } else {
//            btnMyAccount.click();
//            if (lblSubUserMenu.isDisplayed()) {
//                lblSummary.click();
//            }
//        }
//        waitingLoadingSpinner();
        return new AccountBalancePage(_type);
    }

    public PS38SportsResultsPage navigatePS38SportsResultsPage() {
        leftMenu.navigatePS38SportsResultsPage();
        waitingLoadingSpinner();
        return new PS38SportsResultsPage(_type);
    }

    public boolean isDisplayPS38SportsResults() {
        return leftMenu.isDisplayPS38SportsResults();
    }

    public CreateCompanyPage navigateCreateCompanyPage(String securityCode) {
        leftMenu.clickSubMenu(AGENCY_MANAGEMENT, CREATE_COMPANY);
        waitingLoadingSpinner();
        CreateCompanyPage page = new CreateCompanyPage(_type);
        page.confirmSecurityCode(securityCode);
        return page;
    }
}
