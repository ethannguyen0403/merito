package backoffice.pages.bo._components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import backoffice.common.BOConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import backoffice.pages.bo.accountmanagement.*;
import backoffice.pages.bo.adminmanagement.AdminProfilePage;
import backoffice.pages.bo.adminmanagement.AdminUserManagementPage;
import backoffice.pages.bo.adminmanagement.RoleManagementPage;
import backoffice.pages.bo.frauddetection.FraudDetectionPage;
import backoffice.pages.bo.frauddetection.OddsMatchedHistoryPage;
import backoffice.pages.bo.frauddetection.WagerOddsHistoryPage;
import backoffice.pages.bo.marketmanagement.*;
import backoffice.pages.bo.operations.*;
import backoffice.pages.bo.reports.PunterPerformancePage;
import backoffice.pages.bo.reports.WinLossDetailPage;
import backoffice.pages.bo.settlement.FancyResultPage;
import backoffice.pages.bo.settlement.ResettlementLogPage;
import backoffice.pages.bo.settlement.WagerResettlementPage;
import backoffice.pages.bo.system.*;
import backoffice.pages.bo.temp.*;
import backoffice.pages.bo.tools.ExposureAnalysisPage;
import backoffice.pages.bo.tools.MarketBetListPage;

public class LeftMenu extends Header {
    Label lblOverLay = Label.xpath("//div[@class='overlay']");

    Menu menuSystem = Menu.name("system");
    SubMenu smCurrencyManagement = SubMenu.name("currency-management");
    SubMenu smCurrencyCountryMapping = SubMenu.name("currency-country-mapping");
    SubMenu smCountryIPMapping = SubMenu.name("country-ip-mapping");
    SubMenu smPrioritySettings = SubMenu.name("priority-settings");
    SubMenu smBetFairInformation = SubMenu.name("betfair-information");
    public SubMenu smProductMaintenance = SubMenu.name("product-maintenance");
    SubMenu smBetFairAccountInfo = SubMenu.name("betfair-account-info");
    SubMenu smBetFairTaxRecrawl = SubMenu.name("betfair-tax-re-crawl");
    SubMenu smProviderCurrencyMapping = SubMenu.name("provider-currency-mapping");
    SubMenu smFollowBetToThirdParty =SubMenu.name("follow-bets-to-3rd-party");

    Menu menuOperation = Menu.name("operations");
    SubMenu smWagerVoidUnvoid = SubMenu.name("wager-void-un-void");
    SubMenu smLiveStreamingManagement = SubMenu.name("live-streaming-management");
    SubMenu smAnnouncementManagement = SubMenu.name("announcement-management");
    SubMenu smBannerManagement = SubMenu.name("banner-management");
    SubMenu smPerformance = SubMenu.name("performance");


    Menu menuMarketManagement = Menu.id("market-management");
    SubMenu smFindBlockedMarket = SubMenu.name("find-blocked-market");
    SubMenu smEventMarketsStatus = SubMenu.name("events-markets-status");
    SubMenu smBlockingSettings =SubMenu.name("blocking-settings");
    SubMenu smCompetitionBlocking =SubMenu.name("competition-blocking");
    SubMenu smLiquidityThresholdSettings = SubMenu.name("liquidity-threshold-settings");
    SubMenu smBeforeLoginManagement = SubMenu.name("before-login-management");
    SubMenu smLiquidityThresholdLog =SubMenu.name("liquidity-threshold-log");

    Menu menuFraudDetection = Menu.id("menu__fraud-detection");
    SubMenu smOddsMatcedHistory = SubMenu.name("odds-matched-history");
    SubMenu smWagerOddsHistory = SubMenu.name("wager-odds-history");
    SubMenu smFraudDetection = SubMenu.xpath("//a[@name='fraud-detection' and @title='Fraud Detection']");

    Menu menuSettlement = Menu.name("settlement");
    SubMenu smWagerResettlement = SubMenu.name("wager-resettlement");
    SubMenu smFancyResult = SubMenu.name("fancy-result");
    SubMenu smResettlementLogPage =SubMenu.name("resettlement-void-log");

    Menu menuAccountManagement = Menu.id("account-management");
    SubMenu smResetAccountPassword = SubMenu.name("reset-account-password");
    SubMenu smReopenUser = SubMenu.name("reopen-user");
    SubMenu smLoginInfo = SubMenu.name("login-info");
    SubMenu smPlayerInfo =SubMenu.name("player-info");
    SubMenu smAPIPlayer = SubMenu.name("api-player");
    SubMenu smCryptoAccessManagement = SubMenu.name("crypto-access-management");
    SubMenu smAtlanticAccessManagement = SubMenu.name("atlantic-access-management");

    Menu menuReports = Menu.name("reports");
    SubMenu smWinLossDetails = SubMenu.name("win-loss-detail");
    SubMenu smPunterPerformance = SubMenu.name("punter-performance");


    Menu menuTools = Menu.name("tools");
    SubMenu smExposureAnalysis=SubMenu.name("exposure-analysis");
    SubMenu smMarketBetList = SubMenu.name("market-bet-list");


    Menu menuAdminManagement = Menu.name("admin-management");
    SubMenu smAdminProfile = SubMenu.name("admin-profile");
    SubMenu smRoleManagement = SubMenu.name("role-management");
    SubMenu smAdminUserManagement = SubMenu.name("admin-user-management");


    SubMenu smBrandManagement = SubMenu.name("m-admin3");
    SubMenu smDNS = SubMenu.name("m-admin5");
    SubMenu smWinLossByMarket = SubMenu.name("rp01");
    SubMenu smRunnerStatus = SubMenu.name("ss03");
    SubMenu smPersonalMessage = SubMenu.name("op15");
    Menu menuB2BWhileLabel = Menu.xpath("//div[@id='m-white-label'][1]");
    Menu menuB2BSeamlessWallet = Menu.xpath("//div[@id='m-white-label'][2]");
    SubMenu smDailyOnlineMember = SubMenu.name("mm04");
    SubMenu smWhoOnlineList = SubMenu.name("mm05");
    SubMenu smLoginActivity = SubMenu.name("mm07");
    SubMenu smNetProfit = SubMenu.name("rp03");
    SubMenu smNetProfitConfiguration = SubMenu.name("rp04");
    SubMenu smLiabilityForeCast = SubMenu.name("rp02");
    SubMenu smLateBets = SubMenu.name("t05");
    SubMenu smAccessActivity = SubMenu.name("t06");
    SubMenu smPositionTakingConfiguration =SubMenu.name("t101");


    TextBox txtQuickSearch = TextBox.xpath("//div[contains(@class,'searchbox')]//input");
    Label lblQuickSearch = Label.xpath("//div[@class='ui-helper-hidden-accessible']");
    public Image imgBallRotate = Image.xpath("//div[contains(@class,'la-ball-clip-rotate')]/div");
    private String iFrameXpath = "//iframe[contains(@scr,'%s')]";
    public void waitSpinIcon(){
       imgBallRotate.isDisplayed(2);
        imgBallRotate.isInvisible(1);
    }

    /*************
     * Search Page
     *************/
    public void searchPage(String pageName)
    {
        txtQuickSearch.sendKeys(pageName);
        lblQuickSearch.getText();
        lblQuickSearch.isPresent(1);
        txtQuickSearch.type(false,Keys.ARROW_DOWN);
        txtQuickSearch.type(false,Keys.ENTER);
        waitSpinIcon();
    }

    /*************
     * System
     *************/
    public BetFairAccountInfoPage navigateBetFairAccountInfo() {
        smBetFairAccountInfo.click();
        // waiting for loading completely
        smBetFairAccountInfo.isInvisible(2);
        DriverManager.getDriver().switchToFrame(1);
        return new BetFairAccountInfoPage();
    }
    public BetFairInfoPage navigateBetFairInfo() {
        smBetFairInformation.click();
        // waiting for loading completely
        smBetFairInformation.isInvisible(2);
        DriverManager.getDriver().switchToFrame(1);
        return new BetFairInfoPage();
    }
    public CountryIPMappingPage navigateCountryIPMapping() {
        smCountryIPMapping.click();
        // waiting for loading completely
        smCountryIPMapping.isInvisible(2);
        DriverManager.getDriver().switchToFrame(1);
        smCountryIPMapping.isInvisible(2);
        return new CountryIPMappingPage();
    }
    public CurrencyCountryMappingPage navigateCurrencyCountryMapping() {
        smCurrencyCountryMapping.click();
        // waiting for loading completely
        smCurrencyCountryMapping.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smCurrencyCountryMapping.isInvisible(2);
        return new CurrencyCountryMappingPage();
    }
    public CurrencyManagementPage navigateCurrencyManagement() {
        smCurrencyManagement.click();
        // waiting for loading completely
        smCurrencyManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smCurrencyManagement.isInvisible(2);
        return new CurrencyManagementPage();
    }
    public FollowBetToThirdPartyPage navigateFollowBetToThirdParty() {
        smFollowBetToThirdParty.click();
        // waiting for loading completely
        smFollowBetToThirdParty.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smFollowBetToThirdParty.isInvisible(2);
        return new FollowBetToThirdPartyPage();
    }
    public PrioritySettingsPage navigatePrioritySettings() {
        smPrioritySettings.click();
        // waiting for loading completely
        smPrioritySettings.isInvisible(2);
        // DriverManager.getDriver().switchToFrame("page-s06");
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new PrioritySettingsPage();
    }
    public ProductMaintenancePage navigateProductMaintenance() {
        smProductMaintenance.click();
        // waiting for loading completely
        smProductMaintenance.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new ProductMaintenancePage();
    }
    public ProviderCurrencyMappingPage navigateProviderCurrencyMapping() {
        smProviderCurrencyMapping.click();
        // waiting for loading completely
        smProviderCurrencyMapping.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new ProviderCurrencyMappingPage();
    }

    public BetFairTaxRecrawlPage navigateBetFairTaxRecrawl() {
        smBetFairTaxRecrawl.click();
        // waiting for loading completely
        smBetFairTaxRecrawl.isInvisible(2);
        DriverManager.getDriver().switchToFrame(1);
        return new BetFairTaxRecrawlPage();
    }
    /*************
     * Operations
     *************/
    public AnnouncementManagementPage navigateAnnouncementManagement() {
        smAnnouncementManagement.click();
        // waiting for loading completely
        smAnnouncementManagement.isInvisible(2);
          int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smAnnouncementManagement.isInvisible(2);
        return new AnnouncementManagementPage();
    }
    public BannerManagementPage navigateBannerManagement() {
        smBannerManagement.click();
        // waiting for loading completely
        smBannerManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smBannerManagement.isInvisible(2);
        return new BannerManagementPage();
    }
    public LiveStreamingManagementPage navigateLiveStreamingManagement() {
        smLiveStreamingManagement.click();
        // waiting for loading completely
        smLiveStreamingManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smLiveStreamingManagement.isInvisible(2);
        LiveStreamingManagementPage page = new LiveStreamingManagementPage();
        page.waitSpinIcon();
        return page;
    }
    public PersonalMessagePage navigatePersonalMessage() {
        smPersonalMessage.click();
        // waiting for loading completely
        smPersonalMessage.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smPersonalMessage.isInvisible(2);
        return new PersonalMessagePage();
    }
    public WagerVoidUnvoidPage navigateWagerVoidUnvoid() {
        smWagerVoidUnvoid.click();
        // waiting for loading completely
        smWagerVoidUnvoid.isInvisible(2);
        waitTabActive(BOConstants.Operations.VoidUnvoidWager.TITLE);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smBannerManagement.isInvisible(2);
        return new WagerVoidUnvoidPage();
    }

    /*************
     * Settlement
     *************/
    public FancyResultPage navigateFancyResult() {
        smFancyResult.click();
        // waiting for loading completely
        smFancyResult.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
//        smFancyResult.isInvisible(2);
        return new FancyResultPage();
    }
    public WagerResettlementPage navigateWagerResettlement() {
        smWagerResettlement.click();
        smWagerResettlement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new WagerResettlementPage();
    }
    public ResettlementLogPage navigateResettlementLog() {
        smResettlementLogPage.click();
        // waiting for loading completely
        smResettlementLogPage.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new ResettlementLogPage();
    }

    /*************
     * Fraud Detection
     *************/
    public OddsMatchedHistoryPage navigateOddsMatchedHistory() {
        smOddsMatcedHistory.click();
        // waiting for loading completely
        smOddsMatcedHistory.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new OddsMatchedHistoryPage();
    }
    public WagerOddsHistoryPage navigateWagerOddsHistory() {
        smWagerOddsHistory.click();
        smWagerOddsHistory.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new WagerOddsHistoryPage();
    }
    public FraudDetectionPage navigateFraudDetection() {
        smFraudDetection.click();
        smFraudDetection.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new FraudDetectionPage();
    }

    /*******************
     * Account Management
     *******************/
    public APIPlayerPage navigateAPIPlayer() {
        smAPIPlayer.click();
        // waiting for loading completely
        smAPIPlayer.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new APIPlayerPage();
    }
    public AtlanticAccessManagementPage navigateAtlanticAccessManagement() {
        smAtlanticAccessManagement.click();
        // waiting for loading completely
        smAtlanticAccessManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new AtlanticAccessManagementPage();
    }
    public CryptoAccessManagementPage navigateCryptoAccessManagement() {
        smCryptoAccessManagement.click();
        // waiting for loading completely
        smCryptoAccessManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new CryptoAccessManagementPage();
    }
    public LoginInfoPage navigateLoginInfo() {
        smLoginInfo.click();
        // waiting for loading completely
        smLoginInfo.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        lblOverLay.isDisplayedShort(4);
        return new LoginInfoPage();
    }
    public PlayerInfoPage navigatePlayerInfo() {
        smPlayerInfo.click();
        // waiting for loading completely
        smPlayerInfo.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new PlayerInfoPage();
    }
    public ReopenUserPage navigateReopenUser() {
        smReopenUser.click();
        // waiting for loading completely
        smReopenUser.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        lblOverLay.isDisplayedShort(4);
        return new ReopenUserPage();
    }
    public ResetAccountPasswordPage navigateResetAccountPassword() {
        smResetAccountPassword.click();
        // waiting for loading completely
        smResetAccountPassword.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        lblOverLay.isDisplayedShort(4);
        return new ResetAccountPasswordPage();
    }

    /*************
     * Reports
     *************/
    public PunterPerformancePage navigatePunterPerformance() {
        smPunterPerformance.click();
        // waiting for loading completely
        smPunterPerformance.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new PunterPerformancePage();
    }
    public WinLossDetailPage navigateWinLossDetails() {
        smWinLossDetails.click();
        // waiting for loading completely
        smWinLossDetails.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        lblOverLay.isDisplayedShort(4);
        lblOverLay.isInvisible(5);
        WinLossDetailPage wlPage = new WinLossDetailPage();
        if(!wlPage.ddbPortal.isDisplayed())
            DriverManager.getDriver().refresh();
        return wlPage;
    }

    /*************
     * Tools
     *************/
    public ExposureAnalysisPage navigateExposureAnalysis() {
        smExposureAnalysis.click();
        // waiting for loading completely
        smExposureAnalysis.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new ExposureAnalysisPage();
    }
    public MarketBetListPage navigateMarketBetList() {
        smMarketBetList.click();
        // waiting for loading completely
        smMarketBetList.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new MarketBetListPage();
    }

    /*************
     * Admin Management
     *************/
    public AdminProfilePage navigateAdminProfile() {
        smAdminProfile.click();
        // waiting for loading completely
        smAdminProfile.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new AdminProfilePage();
    }
    public AdminUserManagementPage navigateAdminUserManagement() {
        smAdminUserManagement.click();
        // waiting for loading completely
        smAdminUserManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new AdminUserManagementPage();
    }
    public RoleManagementPage navigateRoleManagement() {
        smRoleManagement.click();
        // waiting for loading completely
        smRoleManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new RoleManagementPage();
    }


    /*************
     * Market Management
     *************/
    public BeforeLoginManagementPage navigateBeforeLoginManagement() {
        smBeforeLoginManagement.click();
        // waiting for loading completely
        smBeforeLoginManagement.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smBeforeLoginManagement.isInvisible(2);
        return new BeforeLoginManagementPage();
    }
    public BlockingSettingsPage navigateBlockingSettings() {
        smBlockingSettings.click();
        // waiting for loading completely
        smBlockingSettings.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smBlockingSettings.isInvisible(2);
        return new BlockingSettingsPage();
    }
    public CompetitionBlockingPage navigateCompetitionBlocking() {
        smCompetitionBlocking.click();
        // waiting for loading completely
        smCompetitionBlocking.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smCompetitionBlocking.isInvisible(2);
        return new CompetitionBlockingPage();
    }
    public EventMarketStatusPage navigateEventMarketStatus() {
        smEventMarketsStatus.click();
        // waiting for loading completely
        smEventMarketsStatus.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new EventMarketStatusPage();
    }
    public FindBlockedMarketPage navigateFindBlockedMarket() {
        smFindBlockedMarket.click();
        // waiting for loading completely
        smFindBlockedMarket.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new FindBlockedMarketPage();
    }
    public LiquidityThresholdLogPage navigateLiquidityThresholdLog() {
        smLiquidityThresholdLog.click();
        // waiting for loading completely
        smLiquidityThresholdLog.isInvisible(2);
        DriverManager.getDriver().switchToFrame(1);
        return new LiquidityThresholdLogPage();
    }
    public LiquidityThresholdSettingsPage navigateLiquidityThresholdSettings() {
        smLiquidityThresholdSettings.click();
        // waiting for loading completely
        smLiquidityThresholdSettings.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smLiquidityThresholdSettings.isInvisible(2);
        return new LiquidityThresholdSettingsPage();
    }


    public PositionTakingConfigurationPage navigatePositionTakingConfiguration() {
        smPositionTakingConfiguration.click();
        // waiting for loading completely
        smPositionTakingConfiguration.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new PositionTakingConfigurationPage();
    }
    public PerformancePage navigatePerformance() {
        smPerformance.click();
        // waiting for loading completely
        smPerformance.isInvisible(2);
        DriverManager.getDriver().switchToFrame(1);
        return new PerformancePage();
    }
    public NetProfitPage navigateNetProfit() {
        smNetProfit.click();
        // waiting for loading completely
        smNetProfit.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new NetProfitPage();
    }
    public NetProfitConfigurationPage navigateNetProfitConfiguration() {
        smNetProfitConfiguration.click();
        // waiting for loading completely
        smNetProfitConfiguration.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new NetProfitConfigurationPage();
    }
    public WinLossByMarketPage navigateWinLossByMarket() {
        smWinLossByMarket.click();
        // waiting for loading completely
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smWinLossByMarket.isInvisible(2);
        return new WinLossByMarketPage();
    }
    public RunnerStatusPage navigateRunnerStatus() {
        smRunnerStatus.click();
        smRunnerStatus.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new RunnerStatusPage();
    }
    public LoginActivityPage navigateLoginActivity() {
        smLoginActivity.click();
        // waiting for loading completely
        smLoginActivity.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        lblOverLay.isDisplayedShort(4);
//        lblOverLay.isInvisible(7);
        return new LoginActivityPage();
    }

}
