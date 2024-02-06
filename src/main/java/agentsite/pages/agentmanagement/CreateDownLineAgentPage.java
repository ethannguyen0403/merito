package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection.AccountBalanceTransferConditionInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.AccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.BetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.CashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.commissionsettingsection.CommissionSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.CreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.PositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection.ProductStatusSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.RateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.RiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.TaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.transfersettingsection.TransferSettingSection;
import agentsite.pages.agentmanagement.proteus.createdownlineagent.BetSettingSectionPS38;
import agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection.CommissionSectionPS38;
import agentsite.pages.agentmanagement.proteus.createdownlineagent.PositionTakingSectionPS38;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.SecurityPopup;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.common.*;

import java.util.Map;

public class CreateDownLineAgentPage extends HomePage {

    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[@class='title']");
    public SuccessPopup successPopup = SuccessPopup.xpath("//app-alert | //app-alert-create");
    public Label lblCreateSuccess = Label.xpath("//div[contains(@class,'modal-body modal-body-fit-with-content')]");

    // Exchange Tab  - Risk Setting
    public Label lblRiskSetting = Label.xpath("//div[@id='EXCHANGE-risk-settings']/div[@class='psection']");

    // Product Settings - Exchange Tab
    public Label lblProductSetting = Label.xpath("//div[contains(@id,'product-settings')]/div[@class='psection']");
    public Tab tabExchangeGames = Tab.xpath("//tabset[@id='productSetting']//span[text()='Exchange Games']");
    // Switch Tabs PS38
    public Button btnCancelSwitchTab = Button.xpath("//button[contains(@class, 'btn-cancel')]");
    public Button btnSwitchTab = Button.xpath("//button[contains(@class, 'btn') and contains(text(), 'Switch Tabs')]");
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
    public BetSettingSection betSettingInforSection;
    public TaxSettingSection taxSettingInforSection;
    public ProductStatusSettingSection productStatusSettingInforSection;
    public AccountBalanceTransferConditionInforSection accountBalanceTransferConditionInforSection;
    public PositionTakingSection positionTakingInforSection;
    public TransferSettingSection transferSettingInforSection;
    public CommissionSettingSection commissionSettingSection;
    //proteus
    public BetSettingSectionPS38 betSettingSectionPS38;
    public PositionTakingSectionPS38 positionTakingSectionPS38;
    public CommissionSectionPS38 commissionSectionPS38;

    protected String _type;
    private int totalBetSettingsColumns = 9;
    public Table tblBetSettings = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')]", totalBetSettingsColumns);
    private int totalEGBetSettingColumns = 8;
    public Table tblEGBetSettings = Table.xpath("//div[@id='EXCH_GAMES-bet-settings']//table[contains(@class,'betTable')]", totalEGBetSettingColumns);
    public Table tblEGTaxSettings = Table.xpath("//div[@id='EXCH_GAMES-tax-settings']//table[contains(@class,'betTable')]", totalEGBetSettingColumns);
    public Table tblEGPositionTakingListing = Table.xpath("//div[@id='EXCH_GAMES-position-taking']//table[contains(@class,'ptable info betTable')]", totalEGBetSettingColumns);
    //Exchange Product - Tax Settings
    private int totalTaxSettingsColumns = 7;
    public Table tblTaxSettings = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table[contains(@class,'betTable')]", totalTaxSettingsColumns);
    //Exchange Product - Position Taking
    private int totalPositionTakingColumns = 12;
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
        commissionSettingSection = ComponentsFactory.commissionSettingInfoObject(_type);
        taxSettingInforSection = ComponentsFactory.taxSettingInfoObject(_type);
        positionTakingInforSection = ComponentsFactory.positionTakingInfoObject(_type);
        productStatusSettingInforSection = ComponentsFactory.productStatusSettingInfoObject(_type);
        accountBalanceTransferConditionInforSection = ComponentsFactory.accountBalanceTransferConditionInfoObject(_type);
        transferSettingInforSection = ComponentsFactory.transferSettingInfoObject(_type);
        betSettingSectionPS38 = new BetSettingSectionPS38();
        positionTakingSectionPS38 = new PositionTakingSectionPS38();
        commissionSectionPS38 = new CommissionSectionPS38();
    }

    public String createDownline(String loginID, String password, String accountStatus) {
        String username = "";
        switch (_type) {
            case "satsport":
                accountInforSection.inputInfo(loginID, password, accountStatus);
                username = accountInforSection.getUserName();
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

    public void selectProduct(String productName) {
        Label lblProduct = Label.xpath(String.format("//span[text()='%s']", productName));
        lblProduct.click();
        //Wait 1s for element of PS38 dropdown data loaded
        try {
            Thread.sleep(1000);
        }catch (Exception e){
        }
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
        btnSubmit.click();
        return getMessageUpdate(isClose);
    }
    public String inActiveProduct(String productName, boolean isClose) {
        productStatusSettingInforSection.updateProduct(productName,false);
        btnSubmit.click();
        return getMessageUpdate(isClose);
    }

    public void updateProducts(Map<String, Boolean> products){
        productStatusSettingInforSection.updateProducts(products);
        btnSubmit.click();
        getMessageUpdate(true);
    }
}
