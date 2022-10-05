package agentsite.pages.all.agentmanagement;

public class CreateUserPage extends CreateDownLineAgentPage {

//    // Info section
//    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
//    // Info section
//    public AccountInfoSection accInfoSection= AccountInfoSection.xpath("//div[@id='account']//app-agency-account-ui");
//
//    // Credit Balance Section
//    public CreditBalanceSection creditBalanceSection = CreditBalanceSection.xpath("//div[@id='credit-balance-setting']//app-credit-setting-exchange");
//
//    // Cash Balance Section
//    public CashBalanceSection cashBalanceSection= CashBalanceSection.xpath("//div[@id='credit-balance-setting']//app-credit-setting-exchange");

//    public TextBox txtLoginID = TextBox.xpath("(//input[@name='lastName'])[1]");
//    public TextBox txtPassword = TextBox.name("password");
//    public DropDownBox ddrAccountStatus = DropDownBox.name("status");
//    public TextBox txtFirstName = TextBox.name("firstName");
//    public TextBox txtLastName = TextBox.xpath("(//input[@name='lastName'])[2]");
//    public TextBox txtMobile = TextBox.name("mobile");
//    public SuccessPopup successPopup = SuccessPopup.xpath("//app-alert");
//
//// Cash Balance Section
//    public TextBox txtCreditInitiation = TextBox.name("cashBalance");
//    public TextBox txtCreditLimit = TextBox.name("creditLimit");
//    public Label lblFirstTimeCreditLimit = Label.xpath("//app-credit-setting-exchange//span[contains(@class,'creditLimit')]");
//    public Label lblCreditLimit = Label.xpath("//div[@id='EXCHANGE-credit-balance']//table[contains(@class,'credit-balance-table')]//span[contains(@class,'creditLimit')]");
//    // Product Settings - Exchange Tab
//    public Label lblProductSetting = Label.xpath("//div[@id='product-settings']/div[@class='psection']");
//
//    public CheckBox chbLive = CheckBox.id("live");
//    public CheckBox chbNonLive = CheckBox.id("nonlive");
//
//    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@class='pbtn']");
//    public Button btnCancel = Button.xpath("//div[@class='paction']/button[@class='pCancel']");
//    public Label  lblErrorMsg = Label.xpath("//div[@class='paction']/span[@class='error-msg']");
//
//    // Bet Settings
//    public TextBox txtSoccerMinBet = TextBox.id("SOCCERmintxt");
//    public TextBox txtSoccerMaxBet = TextBox.id("SOCCERmaxtxt");
//
//    public Label lblMessage = Label.xpath("//div[@class='modal-body modal-body-fit-with-content']");
//
//    public double getCreditLimit(String currency){
//        String creditLimit  = lblCreditLimit.getText().split(currency)[1].trim();
//       return Double.valueOf(creditLimit.replaceAll(",", "").toString());
//    }
//
//    public void confirmSecurityCode(String securityCode){
//        securityPopup.submitSecurityCode(securityCode);
//        waitingLoadingSpinner();
//    }
//
    public String createUser( String password) {
        String username =accInfoSection.getUserName();
        accInfoSection.txtPassword.sendKeys(password);
        waitingLoadingSpinner();
        btnSubmit.click();
        waitingLoadingSpinner();
        return username;
    }
    public void createUser(String loginID, String password) {
        accInfoSection.txtLoginID.sendKeys(loginID);
        accInfoSection.txtPassword.sendKeys(password);
        btnSubmit.click();
    }
    public void createUser( String password, String creditLimit,String firsTimeDeposit) {
        accInfoSection.inputInfo("",password,"");
        if(!creditLimit.isEmpty())
            cashBalanceSection.txtCreditLimit.sendKeys(creditLimit);
        if(!firsTimeDeposit.isEmpty())
            cashBalanceSection.txtFirstTimeDeposit.sendKeys(firsTimeDeposit);
        btnSubmit.click();
    }
    public void createUser(String loginID, String password, String creditLimit,String firsTimeDeposit) {
        accInfoSection.inputInfo(loginID,password,"");
        if(!creditLimit.isEmpty())
            creditBalanceSection.txtCreditLimit.sendKeys(creditLimit);
        if(!firsTimeDeposit.isEmpty())
            cashBalanceSection.txtFirstTimeDeposit.sendKeys(firsTimeDeposit);
        btnSubmit.click();
    }
}
