package agentsite.pages.all.agentmanagement;

import com.paltech.element.common.*;
import agentsite.controls.Table;
import agentsite.pages.all.agentmanagement.createdownlineagent.*;
import agentsite.pages.all.components.LeftMenu;
import agentsite.pages.all.components.SecurityPopup;
import agentsite.pages.all.components.SuccessPopup;
import agentsite.pages.oldUI.agentmanagement.createdownlineagent.PositionTakingSection;

public class CreateDownLineAgentPage extends LeftMenu {

    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[@class='title']");
    public SuccessPopup successPopup = SuccessPopup.xpath("//app-alert");

    // Info section
    public AccountInfoSection accInfoSection= AccountInfoSection.xpath("//div[@id='account']//app-agency-account-ui");

    //Transer Setting Section
    public TransferSettingSection transferSettingSection = TransferSettingSection.xpath("//div[@id='transfer-settings']");

    // Credit Balance Section
    public CreditBalanceSection creditBalanceSection = CreditBalanceSection.xpath("//div[@id='credit-balance-setting']//app-credit-setting-exchange");

    // Cash Balance Section
    public CashBalanceSection cashBalanceSection = CashBalanceSection.xpath("//div[@id='credit-balance-setting']//app-credit-setting-exchange");

    // Rate Setting Section
    public RateSettingSection rateSettingSection = RateSettingSection.xpath("//div[contains(@class,'ratesetting')]");

    //Product Settings
    public ProductSettingsSection productSettingsSection = ProductSettingsSection.xpath("//app-edit-agent//div[@id='product-settings']");

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
    public Label lblMaxExposureHint = Label.xpath("//table[@class='ptable info credit-balance-table']//span[@class='extra-title']");
    public Label lblMaxExposure = Label.xpath("//div[@id='EXCHANGE-risk-settings']//table[@class='ptable info credit-balance-table']//tr[2]/td[1]");
    public TextBox txtMaxExposure = TextBox.id("riskSetting");

    // Product Settings - Exchange Tab
    public Label lblProductSetting = Label.xpath("//div[@id='product-settings']/div[@class='psection']");
    public Tab tabExchange = Tab.xpath("//tabset[@id='productSetting']//span[text()='Exchange']");
    public Tab chbExchange = Tab.xpath("//tabset[@id='productSetting']//span[text()='Exchange']//preceding::input[@type='checkbox'][1]");
    public Tab tabExchangeGames = Tab.xpath("//tabset[@id='productSetting']//span[text()='Exchange Games']");
    public Tab cbhExchangeGames = Tab.xpath("//tabset[@id='productSetting']//span[text()='Exchange Games']//preceding::input[@type='checkbox'][1]");
    public Tab tabLiveDealerAsian = Tab.xpath("//tabset[@id='productSetting']//span[text()='Live Dealer Asian']");
    public Tab chbLiveDealerAsian = Tab.xpath("//tabset[@id='productSetting']//span[text()='Live Dealer Asian']//preceding::input[@type='checkbox'][1]");
    public Tab tabLiveDealerEuropean = Tab.xpath("//tabset[@id='productSetting']//span[text()='Live Dealer European']");
    public Tab chbLiveDealerEuropean = Tab.xpath("//tabset[@id='productSetting']//span[text()='Live Dealer European']//preceding::input[@type='checkbox'][1]");

    // tottal column of tblSportSetting is dynamic. It's based on the active sport from upline or from BO setting
    public Table tblSportSetting = Table.xpath("//div[@class='marketSettingWrapper']//table[contains(@class,'info ptable sportTable')]",1);

    //Exchange Product - Bet Settings

    public Label lblBetSettings = Label.xpath("//div[@id='EXCHANGE-bet-settings']/div[@class='psection']");
    public Label lblEGBetSettings = Label.xpath("//div[@id='EXCH_GAMES-bet-settings']/div[@class='psection']");

    private int totalBetSettingsColumns = 7;
    private int totalEGBetSettingColumns = 8;
    public Table tblBetSettings = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')]",totalBetSettingsColumns);
    public Table tblEGBetSettings = Table.xpath("//div[@id='EXCH_GAMES-bet-settings']//table[contains(@class,'betTable')]",totalEGBetSettingColumns);


    //Exchange Product - Tax Settings
    private int totalTaxSettingsColumns = 6;

    public Label lblTaxSettings = Label.xpath("//div[@id='EXCHANGE-tax-settings']/div[@class='psection']");
    public Label lblEGTaxSettings = Label.xpath("//div[@id='EXCH_GAMES-tax-settings']/div[@class='psection']");
    public Table tblTaxSettings = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table[contains(@class,'betTable')]",totalTaxSettingsColumns);
    public Table tblEGTaxSettings = Table.xpath("//div[@id='EXCH_GAMES-tax-settings']//table[contains(@class,'betTable')]",totalEGBetSettingColumns);

    //Exchange Product - Position Taking
    private int totalPositionTakingColumns = 7;
    public Label lblPositionTakingListing = Label.xpath("//div[@id='EXCHANGE-position-taking']/div[@class='psection']");
    public Label lblEGPositionTakingListing = Label.xpath("//div[@id='EXCH_GAMES-position-taking']/div[@class='psection']");
    public Table tblPositionTakingListing = Table.xpath("//div[@id='EXCHANGE-position-taking']//table[contains(@class,'ptable info betTable')]",totalPositionTakingColumns);
    public Table tblEGPositionTakingListing = Table.xpath("//div[@id='EXCH_GAMES-position-taking']//table[contains(@class,'ptable info betTable')]",totalEGBetSettingColumns);

    public CheckBox chbLive = CheckBox.id("live");
    public CheckBox chbNonLive = CheckBox.id("nonlive");

    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@class='pbtn']");
    public Button btnCancel = Button.xpath("//div[@class='paction']/button[@class='pCancel']");
    public Label  lblErrorMsg = Label.xpath("//div[@class='paction']/span[@class='error-msg']");

    public Label lblMessage = Label.xpath("//div[@class='modal-body modal-body-fit-with-content']");

    public String createDonwline( String password, String level) {
        String username =accInfoSection.getUserName();
        accInfoSection.txtPassword.sendKeys(password);
        if(!level.isEmpty()){
        accInfoSection.ddpLevel.selectByVisibleText(level);}
        waitingLoadingSpinner();
        btnSubmit.click();
        waitingLoadingSpinner();
        return username;
    }

    public void confirmSecurityCode(String securityCode) {
        securityPopup.submitSecurityCode(securityCode);
        waitingLoadingSpinner();
    }

    public void createDonwline(String loginID, String password, String accountStatus) {
        accInfoSection.inputInfo(loginID, password, accountStatus);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public String getMessageUpdate(boolean isClose) {
        String message = successPopup.getContentMessage();
        if (isClose) {
            successPopup.close();
        }
        return message;
    }

    public String activeProduct(String productName,boolean isClose){
        productSettingsSection.activeProduct(productName);
       return getMessageUpdate(isClose);
    }
}
