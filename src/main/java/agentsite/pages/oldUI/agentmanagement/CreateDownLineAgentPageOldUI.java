package agentsite.pages.oldUI.agentmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import agentsite.pages.all.agentmanagement.createdownlineagent.CreditBalanceSection;
import agentsite.pages.all.components.LeftMenu;
import agentsite.pages.all.components.SecurityPopup;
import agentsite.pages.all.components.SuccessPopup;
import agentsite.pages.oldUI.agentmanagement.createdownlineagent.AccountInfoSection;
import agentsite.pages.oldUI.agentmanagement.createdownlineagent.ProductSettingsSection;

public class CreateDownLineAgentPageOldUI extends LeftMenu {
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[@class='title']");
    public SuccessPopup successPopup = SuccessPopup.xpath("//app-alert");
    // Info section
    public AccountInfoSection accInfoSection= AccountInfoSection.xpath("//div[@id='account']//app-agency-account-ui");
    // Cash Balance Section
    public CreditBalanceSection creditBalanceSection = CreditBalanceSection.xpath("//div[@id='credit-balance-setting']");
    //Product Settings
    public ProductSettingsSection productSettingsSection = ProductSettingsSection.xpath("//div[@id='product-settings']");
    public Button btnSubmit = Button.xpath("//div[@class='paction']//button[@id='submitBtn']");
    public Button btnCancel = Button.xpath("//div[@class='paction']/button[@id='pCancel']");
    public Label  lblErrorMsg = Label.xpath("//div[@class='paction']/span[@class='error-msg']");
    public Label lblMessage = Label.xpath("//div[@class='modal-body modal-body-fit-with-content']");
    private Label lblOverLay = Label.xpath("//div[contains(@class,'overlay')]");

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
        if (securityPopup.isDisplayed()) {
            securityPopup.submitSecurityCode(securityCode);
        }
        waitingLoadingSpinner();
    }

    public void updateLiveNoneLive(boolean isLive, boolean isNoneLive, boolean isSubmit){
        productSettingsSection.productStatusSettingsSection.updateLiveNonLive(false,true);
        if(isSubmit)
            btnSubmit.click();
    }
    public boolean isLiveCbChecked()
    {
        return productSettingsSection.productStatusSettingsSection.isLiveCbChecked();
    }
    public boolean isNoneLiveCbChecked()
    {
        return productSettingsSection.productStatusSettingsSection.isNoneLiveCbChecked();
    }

    public void updateSport(String sportName,boolean isActive, boolean isSubmit)
    {
        productSettingsSection.productStatusSettingsSection.updateSport(sportName,isActive);
        if(isSubmit)
            btnSubmit.click();
    }
    public boolean isSportActive(String sportName)
    {
        return productSettingsSection.productStatusSettingsSection.isSportActive(sportName);
    }

    public void updateMarket(String sportName, String marketName, boolean isActive, boolean isSubmit){
        productSettingsSection.productStatusSettingsSection.updateMarket(sportName,marketName,isActive);
        if(isSubmit)
            btnSubmit.click();
    }

    public boolean isMarketActive(String sportName, String marketName, boolean isClosePopup){
        productSettingsSection.productStatusSettingsSection.openEditMarketPopup(sportName);
        productSettingsSection.productStatusSettingsSection.editMarketPopup.searchMarket(marketName);
        boolean status =productSettingsSection.productStatusSettingsSection.isMarketActive(marketName);
        if(isClosePopup)
            productSettingsSection.productStatusSettingsSection.closeEditMarket();
        return status;
    }
    public String getPageTitle(){
        return lblPageTitle.getText();
    }

    public String getMessageUpdate(boolean isClose)
    {
        lblOverLay.isInvisible(1);
        String message = successPopup.getContentMessage();
        if(isClose) {
            successPopup.close();
            waitingLoadingSpinner();
        }
        return  message;
    }
}
