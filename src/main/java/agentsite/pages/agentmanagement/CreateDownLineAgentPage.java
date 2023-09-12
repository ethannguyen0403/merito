package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.createdownlineagent.*;
import agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection.AccountBalanceTransferConditionInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.AccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.BetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.CashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.CreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.PositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productsettingsection.ProductSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection.ProductStatusSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.RateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.RiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.TaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.transfersettingsection.TransferSettingSection;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.SecurityPopup;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.common.*;

public class CreateDownLineAgentPage extends HomePage {

    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[@class='title']");
    public SuccessPopup successPopup = SuccessPopup.xpath("//app-alert | //app-alert-create");

    // Info section
    public AccountInfoSection accInfoSection = AccountInfoSection.xpath("//div[@id='account']//app-agency-account-ui");

    //Transer Setting Section
    public TransferSettingSection transferSettingSection = TransferSettingSection.xpath("//div[@id='transfer-settings']");

    // Credit Balance Section
    public agentsite.pages.agentmanagement.createdownlineagent.CreditBalanceSection creditBalanceSection = agentsite.pages.agentmanagement.createdownlineagent.CreditBalanceSection.xpath("//div[@id='credit-balance-setting']//app-credit-setting-exchange");

    // Cash Balance Section
    public agentsite.pages.agentmanagement.createdownlineagent.CashBalanceSection cashBalanceSection = agentsite.pages.agentmanagement.createdownlineagent.CashBalanceSection.xpath("//div[@id='credit-balance-setting']");

    // Rate Setting Section
    public RateSettingSection rateSettingSection = RateSettingSection.xpath("//div[contains(@class,'ratesetting')]");

    public PositionTakingSection positionTakingExchangeSection = PositionTakingSection.xpath("//app-ptsetting//div[@id='EXCHANGE-position-taking']");
    public PositionTakingSection positionTakingExchangeGAMESection = PositionTakingSection.xpath("//app-ptsetting//div[@id='EXCH_GAMES-position-taking']");

    /*// Credit Balance Section
    public Label lblCreditLimit = Label.xpath("//div[@id='EXCHANGE-credit-balance']//table[contains(@class,'credit-balance-table')]//span[contains(,@class,'')]");
    public TextBox txtCreditLimit = TextBox.id("creditLimit-EXCHANGE");
    public Label lblAgentMaxCredit = Label.xpath("//div[@id='EXCHANGE-credit-balance']//table[contains(@class,'credit-balance-table')]//body/tr[3]/td[1]");
    public TextBox txtAgentMaxCredit = TextBox.id("smaMaxCredit-EXCHANGE");
    public Label lblMemberMaxCredit = Label.xpath("//div[@id='EXCHANGE-credit-balance']//table[contains(@class,'credit-balance-table')]//body/tr[4]/td[1]");
    public TextBox txtMemberMaxCredit = TextBox.id("memberMaxCredit-EXCHANGE");

    // Exchange Tab  - Rate Setting
    public Label lblRateSetting = Label.xpath("//div[contains(@class,'ratesetting')]/div[@class='psection']");
   // public Table tblRateSetting = Table.xpath("//div[contains(@class,'ratesetting')]//table[contains(@class,'credit-balance-table')]",2);
    public Label lblRate = Label.xpath("//div[contains(@class,'ratesetting')]//table[contains(@class,'credit-balance-table')]//td[1]");
    public TextBox txtRate = TextBox.xpath("//input[contains(@class,'rate-input')]");*/

    // Exchange Tab  - Risk Setting
    public Label lblRiskSetting = Label.xpath("//div[@id='EXCHANGE-risk-settings']/div[@class='psection']");

    // Product Settings - Exchange Tab
    public Label lblProductSetting = Label.xpath("//div[@id='product-settings']/div[@class='psection']");
    public Tab tabExchangeGames = Tab.xpath("//tabset[@id='productSetting']//span[text()='Exchange Games']");

    //Exchange Product - Bet Settings

    public Label lblBetSettings = Label.xpath("//div[@id='EXCHANGE-bet-settings']/div[@class='psection']");
    public Label lblEGBetSettings = Label.xpath("//div[@id='EXCH_GAMES-bet-settings']/div[@class='psection']");
    public Label lblTaxSettings = Label.xpath("//div[@id='EXCHANGE-tax-settings']/div[@class='psection']");
    public Label lblEGTaxSettings = Label.xpath("//div[@id='EXCH_GAMES-tax-settings']/div[@class='psection']");
    public Label lblPositionTakingListing = Label.xpath("//div[@id='EXCHANGE-position-taking']/div[@class='psection']");
    public Label lblEGPositionTakingListing = Label.xpath("//div[@id='EXCH_GAMES-position-taking']/div[@class='psection']");
    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@class='pbtn']");
    public Button btnCancel = Button.xpath("//div[@class='paction']/button[@class='pCancel']");
    public Label lblErrorMsg = Label.xpath("//div[@class='paction']/span[@class='error-msg']");

    public AccountInforSection accountInforSection;
    public CreditBalanceSection creditBalanceInforSection;
    public RiskSettingSection riskSettingInforSection;
    public CashBalanceSection cashBalanceInforSection;
    public RateSettingsSection rateSettingInforSection;
    public ProductSettingSection productSettingInforSection;
    public agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.RateSettingsSection rateSettingInforSection;
    public BetSettingSection betSettingInforSection;
    public TaxSettingSection taxSettingInforSection;
    public ProductStatusSettingSection productStatusSettingInforSection;
    public AccountBalanceTransferConditionInforSection accountBalanceTransferConditionInforSection;
    public PositionTakingSection positionTakingInforSection;
    public TransferSettingSection transferSettingInforSection;
    protected String _type;
    private int totalBetSettingsColumns = 7;
    public Table tblBetSettings = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')]", totalBetSettingsColumns);
    private int totalEGBetSettingColumns = 8;
    public Table tblEGBetSettings = Table.xpath("//div[@id='EXCH_GAMES-bet-settings']//table[contains(@class,'betTable')]", totalEGBetSettingColumns);
    public Table tblEGTaxSettings = Table.xpath("//div[@id='EXCH_GAMES-tax-settings']//table[contains(@class,'betTable')]", totalEGBetSettingColumns);
    public Table tblEGPositionTakingListing = Table.xpath("//div[@id='EXCH_GAMES-position-taking']//table[contains(@class,'ptable info betTable')]", totalEGBetSettingColumns);
    //Exchange Product - Tax Settings
    private int totalTaxSettingsColumns = 6;
    public Table tblTaxSettings = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table[contains(@class,'betTable')]", totalTaxSettingsColumns);
    //Exchange Product - Position Taking
    private int totalPositionTakingColumns = 7;
    public Table tblPositionTakingListing = Table.xpath("//div[@id='EXCHANGE-position-taking']//table[contains(@class,'ptable info betTable')]", totalPositionTakingColumns);

    public CreateDownLineAgentPage(String types) {
        super(types);
        _type = types;
        accountInforSection = ComponentsFactory.accInfoObject(_type);
        creditBalanceInforSection = ComponentsFactory.creditBalanceInfoObject(_type);
        riskSettingInforSection = ComponentsFactory.riskSettingInfoObject(_type);
        cashBalanceInforSection = ComponentsFactory.cashBalanceInfoObject(_type);
        rateSettingInforSection = ComponentsFactory.rateSettingInfoObject(_type);
        betSettingInforSection = ComponentsFactory.betSettingInfoObject(_type);
        taxSettingInforSection = ComponentsFactory.taxSettingInfoObject(_type);
        positionTakingInforSection = ComponentsFactory.positionTakingInfoObject(_type);
        productStatusSettingInforSection = ComponentsFactory.productStatusSettingInfoObject(_type);
        accountBalanceTransferConditionInforSection = ComponentsFactory.accountBalanceTransferConditionInfoObject(_type);
        transferSettingInforSection = ComponentsFactory.transferSettingInfoObject(_type);
    }

    public String createDownline(String loginID, String password, String accountStatus) {
        String username = "";
        switch (_type) {
            case "satsport":
                accountInforSection.inputInfo(loginID, password, accountStatus);
                btnSubmit.click();
                waitingLoadingSpinner();
                break;
            default:
                username = accountInforSection.getUserName();
                accountInforSection.inputInfo(password, accountStatus);
                waitingLoadingSpinner();
                btnSubmit.click();
                waitingLoadingSpinner();
                return username;
        }
        return username;
    }


    public String getMessageUpdate(boolean isClose) {
        String message = successPopup.getContentMessage();
        if (isClose) {
            successPopup.close();
        }
        return message;
    }

    public String activeProduct(String productName, boolean isClose) {
        productStatusSettingInforSection.updateProduct(productName,true);
        return getMessageUpdate(isClose);
    }
}
