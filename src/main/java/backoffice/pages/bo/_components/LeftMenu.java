package backoffice.pages.bo._components;

import backoffice.pages.bo.paymentmanagement.DepositWithdrawalTransactionsPage;
import backoffice.pages.bo.paymentmanagement.PaymentConfigurationPage;
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

    Menu menuSystem = Menu.id("menu__system'");
    SubMenu smCurrencyManagement = SubMenu.xpath("//a[@title='Currency Management']//parent::ul");
    SubMenu smCurrencyCountryMapping = SubMenu.xpath("//a[@title='Currency - Country Mapping']//parent::ul");
    SubMenu smCountryIPMapping = SubMenu.xpath("//a[@title='Country - IP Mapping']//parent::ul");
    SubMenu smLiabilityAnalysis = SubMenu.xpath("//a[@title='Liability Analysis']//parent::ul");
    SubMenu smPrioritySettings = SubMenu.xpath("//a[@title='Priority Settings']//parent::ul");
    public SubMenu smProductMaintenance = SubMenu.xpath("//a[@title='Product Maintenance']//parent::ul");
    SubMenu smBetFairAccountInfo = SubMenu.xpath("//a[@title='BetFair Account Info']//parent::ul");
    SubMenu smBetFairInformation = SubMenu.xpath("//a[@title='Betfair Information']//parent::ul");
    SubMenu smProviderCurrencyMapping = SubMenu.xpath("//a[@title='Provider Currency Mapping']//parent::ul");
    SubMenu smSmallBetConfiguration = SubMenu.xpath("//a[@title='Small Bet Configuration']//parent::ul");
    SubMenu smLiabilityThresholdSettings = SubMenu.xpath("//a[@title='Liability Threshold Settings']//parent::ul");
    SubMenu smBetFairTaxRecrawl = SubMenu.name("betfair-tax-re-crawl");
    SubMenu smFollowBetToThirdParty = SubMenu.name("follow-bets-to-3rd-party");

    Menu menuOperation = Menu.id("menu__operations");
    SubMenu smWagerVoidUnvoid = SubMenu.xpath("//a[@title='Wager Void / Un-void']//parent::ul");
    SubMenu smLiveStreamingManagement = SubMenu.xpath("//a[@title='Live Streaming Management']//parent::ul");
    SubMenu smAnnouncementManagement = SubMenu.xpath("//a[@title='Announcement Management']//parent::ul");
    SubMenu smMixedPTConfiguration = SubMenu.name("mixed-pt-configuration");
    SubMenu smBannerManagement = SubMenu.xpath("//a[@title='Banner Management']//parent::ul");
    SubMenu smPerformance = SubMenu.xpath("//a[@title='Performance']//parent::ul");

    Menu menuPaymentManagement = Menu.name("payment-management");
    SubMenu smPaymentConfiguration = SubMenu.name("payment-configuration");
    SubMenu smDepositWithdrawalTransactions = SubMenu.name("deposit-withdrawal-transactions");

    Menu menuMarketManagement = Menu.id("menu__market-management");
    SubMenu smFindBlockedMarket = SubMenu.xpath("//a[@title='Find Blocked Market']//parent::ul");
    SubMenu smEventMarketsStatus = SubMenu.xpath("//a[@title='Events/Markets Status']//parent::ul");
    SubMenu smEventMarketLog = SubMenu.xpath("//a[@title='Event/Market Log']//parent::ul");
    SubMenu smBlockingSettings = SubMenu.xpath("//a[@title='Blocking Settings']//parent::ul");
    SubMenu smCompetitionBlocking = SubMenu.xpath("//a[@title='Competition Blocking']//parent::ul");
    SubMenu smLiquidityThresholdSettings = SubMenu.xpath("//a[@title='Liquidity Threshold Settings']//parent::ul");
    SubMenu smLiquidityThresholdLog = SubMenu.xpath("//a[@title='Liquidity Threshold Log']//parent::ul");
    SubMenu smBeforeLoginManagement = SubMenu.xpath("//a[@title='Before Login Management']//parent::ul");
    SubMenu smBlockUnblockEvent = SubMenu.xpath("//a[@title='Block/Unblock Events']//parent::ul");


    Menu menuFraudDetection = Menu.id("menu__fraud-detection");
    SubMenu smFraudDetection = SubMenu.xpath("//a[@title='Fraud Detection']//parent::ul");
    SubMenu smOddsMatcedHistory = SubMenu.xpath("//a[@title='Odds Matched History']//parent::ul");
    SubMenu smWagerOddsHistory = SubMenu.xpath("//a[@title='Wager Odds History']//parent::ul");

    Menu menuSettlement = Menu.id("menu__settlement");
    SubMenu smWagerResettlement = SubMenu.xpath("//a[contains(@title,'Wager Resettlement')]//parent::ul");
    SubMenu smResettlementLogPage = SubMenu.xpath("//a[@title='Resettlement/Void Log']//parent::ul");
    SubMenu smFancyResult = SubMenu.xpath("//a[@title='Fancy Result']//parent::ul");

    Menu menuAccountManagement = Menu.id("menu__account-management");
    SubMenu smResetAccountPassword = SubMenu.xpath("//a[contains(@title,'Reset Account Password')]//parent::ul");
    SubMenu smReopenUser = SubMenu.xpath("//a[contains(@title,'Reopen User')]//parent::ul");
    SubMenu smLoginInfo = SubMenu.xpath("//a[contains(@title,'Login Info')]//parent::ul");
    SubMenu smPlayerInfo = SubMenu.xpath("//a[@title='Player Info']//parent::ul");
    SubMenu smAPIPlayer = SubMenu.xpath("//a[@title='API Player']//parent::ul");
    SubMenu smCryptoAccessManagement = SubMenu.xpath("//a[contains(@title,'Crypto Access Management')]//parent::ul");
    SubMenu smAtlanticAccessManagement = SubMenu.xpath("//a[contains(@title,'Atlantic Access Management')]//parent::ul");

    Menu menuReports = Menu.id("menu__reports");
    SubMenu smWinLossDetails = SubMenu.xpath("//a[contains(@title,'Win Loss Detail')]//parent::ul");
    SubMenu smPunterPerformance = SubMenu.xpath("//a[contains(@title,'Punter Performance')]//parent::ul");


    Menu menuTools = Menu.id("menu__tools");
    SubMenu smExposureAnalysis= SubMenu.xpath("//a[@title='Exposure Analysis']//parent::ul");
    SubMenu smMarketBetList = SubMenu.xpath("//a[@title='Market Bet List']//parent::ul");


    Menu menuAdminManagement = Menu.id("menu__admin-management");
    SubMenu smAdminProfile = SubMenu.name("admin-profile");
    SubMenu smRoleManagement = SubMenu.xpath("//a[contains(@title,'Role Management')]//parent::ul");
    SubMenu smAdminUserManagement = SubMenu.xpath("//a[contains(@title,'Admin User Management')]//parent::ul");


    SubMenu smBrandManagement = SubMenu.name("m-admin3");
    SubMenu smDNS = SubMenu.name("m-admin5");
    SubMenu smWinLossByMarket = SubMenu.name("rp01");
    SubMenu smRunnerStatus = SubMenu.name("ss03");
    SubMenu smPersonalMessage = SubMenu.name("op15");
    Menu menuB2BWhileLabel = Menu.id("menu__b2b-white-label");
    Menu menuB2BSeamlessWallet = Menu.id("menu__b2b-seamless-wallet");
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
        waitSpinIcon();
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
    public MixedPTConfigurationPage navigateMixedPTConfiguration() {
        smMixedPTConfiguration.click();
        // waiting for loading completely
        smMixedPTConfiguration.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smMixedPTConfiguration.isInvisible(2);
        return new MixedPTConfigurationPage();
    }
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
        FancyResultPage fancyResultPage = new FancyResultPage();
        if (!fancyResultPage.btnSearch.isDisplayed()){
            DriverManager.getDriver().refresh();
        }
        return fancyResultPage;
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
    public BlockUnblockEventPage navigateBlockUnblockEvents() {
        smBlockUnblockEvent.click();
        // waiting for loading completely
        smBlockUnblockEvent.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        smBlockUnblockEvent.isInvisible(2);
        BlockUnblockEventPage page = new BlockUnblockEventPage();
        page.waitSpinIcon();
        return page;
    }
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
    public EventMarketLogPage navigateEventMarketLog() {
        smEventMarketLog.click();
        // waiting for loading completely
        smEventMarketLog.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new EventMarketLogPage();
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
    public SmallBetConfigurationPage navigateSmallBetConfiguration() {
        smSmallBetConfiguration.click();
        smSmallBetConfiguration.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new SmallBetConfigurationPage();
    }

    public PaymentConfigurationPage navigatePaymentConfigurationPage() {
        smPaymentConfiguration.click();
        smPaymentConfiguration.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new PaymentConfigurationPage();
    }

    public DepositWithdrawalTransactionsPage navigateDepositWithdrawalTransactionsPage() {
        smDepositWithdrawalTransactions.click();
        smDepositWithdrawalTransactions.isInvisible(2);
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
        return new DepositWithdrawalTransactionsPage();
    }
}
