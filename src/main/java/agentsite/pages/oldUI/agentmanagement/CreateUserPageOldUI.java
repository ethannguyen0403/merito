package agentsite.pages.oldUI.agentmanagement;

import agentsite.pages.oldUI.agentmanagement.CreateDownLineAgentPageOldUI;

public class CreateUserPageOldUI extends CreateDownLineAgentPageOldUI {
    // Info section
   // public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    // Info section
    //public AccountInfoSection accInfoSection= AccountInfoSection.xpath("//div[@id='account']//app-agency-account-ui");
  //  public CashBalanceSection balanceSection = CashBalanceSection.xpath("//div[@id='credit-balance-setting']");
   // public SuccessPopup successPopup = SuccessPopup.xpath("//app-alert");
   // public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@class='pbtn']");
   // public Button btnCancel = Button.xpath("//div[@class='paction']/button[@class='pCancel']");
   // public Label  lblErrorMsg = Label.xpath("//div[@class='paction']/span[@class='error-msg']");
    public String createUser( String password) {
        String username =accInfoSection.getUserName();
        accInfoSection.txtPassword.sendKeys(password);
        btnSubmit.click();
        return username;
    }
    public void createUser(String loginID, String password) {
        accInfoSection.txtPassword.sendKeys(password);
        btnSubmit.click();
    }
    public void createUser( String password, String creditLimit,String firsTimeDeposit) {
        accInfoSection.txtPassword.sendKeys(password);
        if(!creditLimit.isEmpty())
             creditBalanceSection.txtCreditLimit.sendKeys(creditLimit);
        if(!firsTimeDeposit.isEmpty())
            creditBalanceSection.txtInitiationDeposit.sendKeys(firsTimeDeposit);
        btnSubmit.click();
    }

}
